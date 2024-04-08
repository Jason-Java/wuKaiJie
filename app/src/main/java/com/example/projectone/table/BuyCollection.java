package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class BuyCollection extends LitePalSupport {

    private String username;

    private String buyName;

    private String buyshop;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getBuyshop() {
        return buyshop;
    }

    public void setBuyshop(String buyshop) {
        this.buyshop = buyshop;
    }
}
