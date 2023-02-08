package com.yrp.es;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
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
public class RestHighLevelClientForIndexTest {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForIndexTest(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    /**
     * 创建索引以及对应的映射
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
        // 参数1：指定json的存储结构
        // 参数2：指定数据的类型
        createIndexRequest.mapping("{\n" + "    \"properties\": {\n" + "      \"title\":{\n"
                + "        \"type\": \"text\", \n" + "        \"analyzer\": \"ik_max_word\"\n" + "      },\n"
                + "      \"price\":{\n" + "        \"type\": \"double\"\n" + "      },\n" + "      \"description\":{\n"
                + "        \"type\": \"text\",\n" + "        \"analyzer\": \"ik_max_word\"\n" + "      },\n"
                + "      \"create_time\":{\n" + "        \"type\": \"date\"\n" + "      }\n" + "    }\n" + "  }",
                XContentType.JSON);
        // 参数1：创建索引的请求对象
        // 参数2：请求配置对象
        CreateIndexResponse createIndexRsp= restHighLevelClient.indices().create(createIndexRequest,
                RequestOptions.DEFAULT);
        log.info("创建后的状态->{}", createIndexRsp.isAcknowledged());

    }


    /**
     * 删除索引
     */
    @Test
    public void delIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("products");
        AcknowledgedResponse deleteRsp = restHighLevelClient.indices().delete(deleteIndexRequest,
                RequestOptions.DEFAULT);
        log.info("删除后的状态->{}", deleteRsp.isAcknowledged());
    }


}
