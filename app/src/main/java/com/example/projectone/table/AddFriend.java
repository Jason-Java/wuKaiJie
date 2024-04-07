package com.example.projectone.table;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

//添加朋友表格 用户姓名、添加姓名、验证信息、是否同意（0表示还未看、1表示拒绝、2表示同意）、时间（发送时间）
public class AddFriend extends LitePalSupport {
    @Column(nullable = false)
    private  String username;

    @Column(nullable = false)
    private  String addname;

    @Column(nullable = false)
    private  String say;

    @Column(nullable = false)
    private int apply;

    @Column(nullable = false)
    private Date date;

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    public int getApply() {
        return apply;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
