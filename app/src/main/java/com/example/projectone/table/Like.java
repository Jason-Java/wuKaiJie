package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class Like extends LitePalSupport {
    private String username;

    private String likename;

    //分享发布的时间
    private String enjoydate;

    //是否点赞
    private int like;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLikename() {
        return likename;
    }

    public void setLikename(String likename) {
        this.likename = likename;
    }

    public String getEnjoydate() {
        return enjoydate;
    }

    public void setEnjoydate(String enjoydate) {
        this.enjoydate = enjoydate;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
