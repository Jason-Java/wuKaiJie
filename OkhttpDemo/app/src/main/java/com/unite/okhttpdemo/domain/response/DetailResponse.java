package com.unite.okhttpdemo.domain.response;

/**
 *详情网络请求分析类
 */
public class DetailResponse<T> extends BaseResponse{

    /**
     * 真实数据
     * 类似泛型
     */
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
