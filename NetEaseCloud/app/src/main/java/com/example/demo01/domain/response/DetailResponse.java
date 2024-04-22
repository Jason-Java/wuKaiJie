package com.example.demo01.domain.response;

/**
 *详情网络请求分析类
 */
public class DetailResponse<T> extends BaseResponse{

    /**
     * 真实数据
     * 类似泛型
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
