package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class Comment extends LitePalSupport {

    private String username;

    private String commentname;

    //分享发布的时间
    private String enjoydate;
    //评论的时间
    private String commentdate;

    private String commenttext;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentname() {
        return commentname;
    }

    public void setCommentname(String commentname) {
        this.commentname = commentname;
    }

    public String getEnjoydate() {
        return enjoydate;
    }

    public void setEnjoydate(String enjoydate) {
        this.enjoydate = enjoydate;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }
}
