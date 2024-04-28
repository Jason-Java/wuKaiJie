package com.example.demo01.domain;

import java.io.Serializable;

/**
 *所有模型父类
 * Serializable是一个Java标记接口，用于标记一个类可以被序列化。序列化是将对象的状态转换为可以存储或传输的形式的过程。
 */
public class BaseModel implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 创建时间
     */
    private String created_at;

    /*
    更新时间
     */
    private String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
