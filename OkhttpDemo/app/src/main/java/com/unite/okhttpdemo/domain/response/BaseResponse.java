package com.unite.okhttpdemo.domain.response;

/**
 *通用网络请求响应模型
 */
public class BaseResponse {
    /**
     * 状态码
     * 只有发生了错误才有值
     * int就算不创默认为0
     * Integer初始化为null
     */
    private Integer status;

    /**
     * 出错的提示信息
     * 发生了错误不一定有
     */
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
