package com.yrp.es;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: YeRenping
 * @Date: 2023/1/28
 * @Description:
 */
@SpringBootTest
@Slf4j
public class RestHighLevelClientForDocumentTest {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentTest(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    /**
     * 创建文档
     */
    @Test
    public void saveDoc() throws IOException {

        // 索引的名称
        IndexRequest indexReq = new IndexRequest("products");
        // 文档的内容 id = 1，source内容
        indexReq.id("1").source("{\n" + "  \"title\":\"贺猪猪\",\n" + "  \"price\":\"88.8\",\n"
                + "  \"description\":\"我的老婆\",\n" + "  \"create_time\":\"2023-01-01\"\n" + "}", XContentType.JSON);
        // 参数1：索引的请求对象 参数2：索引的配置对象
        IndexResponse indexRsp = restHighLevelClient.index(indexReq, RequestOptions.DEFAULT);
        System.out.println(indexRsp.status());
    }


    /**
     * 更新文档
     */
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("products", "1");
        updateRequest.doc("{\n" + "    \"title\":\"红色\"\n" + "  }", XContentType.JSON);
        UpdateResponse updateRsp = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateRsp.status());
    }


    /**
     * 删除文档
     */
    @Test
    public void delDoc() throws IOException {
        DeleteRequest deleteReq = new DeleteRequest("products", "1");
        DeleteResponse deleteRsp = restHighLevelClient.delete(deleteReq, RequestOptions.DEFAULT);
        System.out.println(deleteRsp.status());
    }


    /**
     * 根据id去查询文档
     */
    @Test
    public void queryById() throws IOException {
        GetRequest getRequest = new GetRequest("products", "1");
        GetResponse searchRsp = restHighLevelClient.get(getRequest,RequestOptions.DEFAULT);
        String id = searchRsp.getId();
        String res = searchRsp.getSourceAsString();
        System.out.println(id);
        System.out.println(res);
    }
}
