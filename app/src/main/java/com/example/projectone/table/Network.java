package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 *局域网聊天
 */
public class Network extends LitePalSupport {
    private String name;
    private String host;
    private String text;
    private Date date;
    private int news;

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
