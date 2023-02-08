package com.yrp.es;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
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
public class RestHighLevelClientForDocumentAggsSearch {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentAggsSearch(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * 基于term进行聚合，基于字段分组聚合
     * GET /products/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   }
     *   , "aggs": {
     *     "price_group": {
     *       "terms": {
     *         "field": "price",
     *         "size": 10
     *       }
     *     }
     *   }
     * }
     * @throws IOException
     */
    @Test
    public void testAggregation() throws IOException {
        SearchRequest  searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //用来设置聚合处理
        sourceBuilder
                .query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders.terms("price_group").field("price"))
                .size(0);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

        // 处理聚合的结果
        Aggregations aggregations = searchRsp.getAggregations();
        ParsedDoubleTerms parsedDoubleTerms = aggregations.get("price_group");
        List<? extends Terms.Bucket> buckets = parsedDoubleTerms.getBuckets();
        buckets.stream().forEach(new Consumer<Terms.Bucket>() {
            @Override
            public void accept(Terms.Bucket bucket) {
                double key = (double)bucket.getKey();
                long docCount = bucket.getDocCount();
                log.info("key = {}, docCount = {}",key,docCount);
            }
        });
    }

    /**
     * 聚合操作 求平均值
     * @throws IOException
     */
    @Test
    public void testAggregationGetMax() throws IOException {
        SearchRequest  searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //用来设置聚合处理
        sourceBuilder
                .query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders.avg("avg_sum").field("price"))
                .size(0);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

        // 处理聚合的结果
        Aggregations aggregations = searchRsp.getAggregations();
        ParsedAvg priceMax = aggregations.get("avg_sum");
        System.out.println(priceMax.getValue());
    }


}
