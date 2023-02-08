package com.yrp.es;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * @author: YeRenping
 * @Date: 2023/1/28
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ElasticsearchOprationaTest {

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ElasticsearchOprationaTest(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Test
    public void saveDocument(){

    }
}
