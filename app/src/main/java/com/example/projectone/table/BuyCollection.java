package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class BuyCollection extends LitePalSupport {

    private String userName;

    private String buyName;

    private String buyShop;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getBuyShop() {
        return buyShop;
    }

    public void setBuyShop(String buyShop) {
        this.buyShop = buyShop;
    }
}
