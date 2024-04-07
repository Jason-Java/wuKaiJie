package com.example.projectone.table;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

//朋友列表  自己的姓名、朋友的姓名列表
public class Friends extends LitePalSupport {
    @Column(nullable = false,unique = true)
    private  String username;

    @Column(nullable = false)
    private List<String> friends = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
