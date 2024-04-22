package com.example.demo01.domain;

/**
 *歌单对象
 */
public class Sheet {

    private String id;
    private String created_at;
    private String updated_at;
    private String title;
    private String icon;
    private String detail;
    private String user_id;
    private int clicks_count;
    private int collects_count;
    private int comments_count;
    private int songs_count;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getClicks_count() {
        return clicks_count;
    }

    public void setClicks_count(int clicks_count) {
        this.clicks_count = clicks_count;
    }

    public int getCollects_count() {
        return collects_count;
    }

    public void setCollects_count(int collects_count) {
        this.collects_count = collects_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getSongs_count() {
        return songs_count;
    }

    public void setSongs_count(int songs_count) {
        this.songs_count = songs_count;
    }
}
