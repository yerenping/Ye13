package com.yrp.es;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
public class RestHighLevelClientForDocumentFillterQuery {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentFillterQuery(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     *# filter query 过滤查询： 先过滤 后查询
     * # 过滤查询 ，price在80~120之间的 description带有“老婆”的
     * GET /products/_search
     * {
     *   "query": {
     *     "term": {
     *       "description": {
     *         "value": "老婆"
     *       }
     *     }
     *   }
     *   , "post_filter": {
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
    public  void filterQuery() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 过滤查询
        sourceBuilder.postFilter(QueryBuilders.rangeQuery("price").gte(80).lte(120))
                .query(QueryBuilders.termQuery("description", "老婆"));
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
}
