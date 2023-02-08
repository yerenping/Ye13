package com.yrp.es;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.yrp.entity.Product;

/**
 * @author: YeRenping
 * @Date: 2023/1/29
 * @Description:
 */
@Slf4j
@SpringBootTest
public class RestHighLevelClientForObject {
    private final RestHighLevelClient restHighLevelClient;
    @Autowired
    public RestHighLevelClientForObject(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }



    /**
     * 将对象放入es中
     */
    @Test
    public void addObject() throws IOException {
        Product lyl = new Product().setId(2).setTitile("蓝牙亮222").setDescription("洗白白222").setPrice(129.9);
        // 录入es
        IndexRequest indexReq = new IndexRequest("products");
        indexReq.id(lyl.getId().toString()) // 文档id
                .source(JSON.toJSONString(lyl), XContentType.JSON); // 将对象转成json
        IndexResponse indexRsp = restHighLevelClient.index(indexReq, RequestOptions.DEFAULT);

    }

    /**
     * ES中文档转化成对象（含高亮的结果跑）
     */
    @Test
    public void JsonToObject() throws IOException {
        SearchRequest products = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("description", "白白"))
                .from(0)
                .size(20)
                .highlighter(new HighlightBuilder().preTags("<span style='color:red;'> ").postTags("</span>").field("description"));
        products.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(products, RequestOptions.DEFAULT);
        // 获取json数据
        SearchHit[] hits = search.getHits().getHits();
        List<Product> list = Arrays.stream(hits)
                .map(new Function<SearchHit, Product>() {
                    @Override
                    public Product apply(SearchHit documentFields) {
                        String sourceAsString = documentFields.getSourceAsString();
                        Product product = JSON.parseObject(sourceAsString, Product.class);
                        // 处理高亮
                        Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
                        if (highlightFields.containsKey("description")) {
                            String description = highlightFields.get("description").fragments()[0].toString();
                            product.setDescription(description);
                        }
                        return product;
                    }
                })
                .collect(Collectors.toList());
        System.out.println(list);
    }

}
