package com.yrp.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: YeRenping
 * @Date: 2023/1/28
 * @Description: 商品
 *
 * @Document 将Product这个对象，转成es中的一条文档进行录入
 * createIndex = true 否是创建索引
 */
@Accessors(chain = true)
@Data
// @Document(indexName = "product",createIndex = true)
public class Product {
    /**
     * 用来将放入对象的id 值，作为文档_id进行映射
     */
    @Id
    private Integer id;
    @Field(type = FieldType.Text , analyzer="ik_max_word")
    private String titile;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;


}
