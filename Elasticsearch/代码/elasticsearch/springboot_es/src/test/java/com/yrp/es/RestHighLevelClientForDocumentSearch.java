package com.yrp.es;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: YeRenping
 * @Date: 2023/1/28
 * @Description:
 */
@Slf4j
@SpringBootTest
public class RestHighLevelClientForDocumentSearch {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentSearch(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Test
    public void searchAll() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        // 查询条件构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 配置查询所有matchAll
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 传入“查询条件构造器”
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

        // 获取总条数
        long count = searchRsp.getHits().getTotalHits().value;
        log.info("count = {}",count);
        // 获取最大得分max_score
        double maxScore = searchRsp.getHits().getMaxScore();
        log.info("maxScore = {}", maxScore);
        // 获取每一条文档
        SearchHit[] hits = searchRsp.getHits().getHits();
        log.info("-------------------------------");
        for (SearchHit hit : hits) {
            log.info("{}",hit);
        }
    }

    /**
     * 关键词term查询
     * GET /products/_search
     * {
     *   "query": {
     *     "term": {
     *       "title": {
     *         "value": "猪猪"
     *       }
     *     }
     *   }
     * }
     * @throws IOException
     */
    @Test
    public void searchByTerm() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        // 查询条件构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 配置查询所有matchAll
        sourceBuilder.query(QueryBuilders.termQuery("title", "贺"));
        // 传入“查询条件构造器”
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    /**
     * 范围查询
     * @throws IOException
     * GET /products/_search
     * {
     *   "query": {
     *     "range": {
     *       "price": {
     *         "gte": 80,
     *         "lte": 120
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void searchByRange() throws IOException {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
        rangeQueryBuilder.gte("80");
        rangeQueryBuilder.lte("120");
        searchByCondition(rangeQueryBuilder);
    }

    /**
     *  基于关键词的前缀查询(prefix)
     * @throws IOException
     * GET /products/_search
     * {
     *   "query": {
     *     "prefix": {
     *       "title": {
     *         "value": "贺"
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void searchByPrefix() throws IOException {
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("title", "猪猪");
        searchByCondition(prefixQueryBuilder);
    }

    /**
     *  通配符wildcard 查询
     * GET /products/_search
     * {
     *   "query": {
     *     "wildcard": {
     *       "title": {
     *         "value": "贺*"
     *       }
     *     }
     *   }
     * }
     * }
     * @throws IOException
     */
    @Test
    public void searchByWildcard() throws IOException {
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("title", "贺*");
        searchByCondition(wildcardQueryBuilder);
    }


    /**
     * # 布尔查询
     * # 标题里面必须带有猪猪
     * GET /products/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "term": {
     *             "title": {
     *               "value": "猪猪"
     *             }
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     * @throws IOException
     */
    @Test
    public void searchByBoolMust() throws IOException {
        BoolQueryBuilder mustQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title", "猪猪"));
        searchByCondition(mustQueryBuilder);
    }


    /**
     * 分页查询 -查询所有（每页显示10条）
     * GET /products/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   }
     *   , "from": 0
     *   , "size": 10
     * }
     */
    @Test
    private  void searchPage() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .from(0) // 起始页
                .size(10); // 每页显示的条数
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    /**
     * # 对查询结果进行排序sort
     * # asc 升序 desc 降序
     * GET /products/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   }
     *   , "sort": [
     *     {
     *       "price": {
     *         "order": "asc"
     *       }
     *     }
     *   ]
     * }
     * @throws IOException
     */
    @Test
    public void searchBySort() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .sort("price",SortOrder.ASC);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    /**
     * # 返回想要的指定字段
     *
     * GET /products/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   }
     *   , "_source": ["title","description"]
     * }
     * @throws IOException
     */
    @Test
    public void searchWantField() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String[] fields = {"title","description"};
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .fetchSource(fields,new String[]{}); // include  包含  exludes 排除
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    /**
     * # 高亮查询结果
     * GET /products/_search
     * {
     *   "query": {
     *     "term": {
     *       "description": {
     *         "value": "老婆"
     *       }
     *     }
     *   }
     *   , "highlight": {
     *       "pre_tags": ["<span style='color:red;'> "],
     *       "post_tags": ["</span>"],
     *       "fields": {
     *         "name":{}
     *       }
     *   }
     * }
     * @throws IOException
     */
    @Test
    public void searchHightLight() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 高亮字段
        highlightBuilder.field("description");
        // 红色标签
        highlightBuilder.preTags("<span style='color:red;'> ");
        highlightBuilder.postTags("</span>");
        sourceBuilder.query(QueryBuilders.termQuery("description", "老婆"))
                .highlighter(highlightBuilder);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    /**
     *
     * @param queryBuilder 查询条件
     * @throws IOException
     */
    private  void searchByCondition(QueryBuilder queryBuilder) throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        // 查询条件构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 配置查询所有matchAll
        sourceBuilder.query(queryBuilder);
        // 传入“查询条件构造器”
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
}
