package com.unite.okhttpdemo.domain;

import com.unite.okhttpdemo.domain.response.BaseResponse;

/**
 *密码登录获取的信息
 */
public class PasswordLogin extends BaseResponse {

    private Boolean success;

    private String token;

    private Integer expires_in;

    private String token_type;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
