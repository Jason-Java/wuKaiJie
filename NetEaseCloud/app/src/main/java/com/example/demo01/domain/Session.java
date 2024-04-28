package com.example.demo01.domain;

import java.io.Serializable;

/**
 *登录后的模型
 */
public class Session{
    /**
     * 用户ID
     */
    private String user;

    /**
     * 登录后的Session
     */
    private String session;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
