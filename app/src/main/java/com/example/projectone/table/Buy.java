package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class Buy extends LitePalSupport {

    private String buyImage;

    private String buyName;

    private int buyPrice;

    private String buyUrl;

    private String buyShop;

    public String getBuyShop() {
        return buyShop;
    }

    public void setBuyShop(String buyshop) {
        this.buyShop = buyshop;
    }

    public String getBuyImage() {
        return buyImage;
    }

    public void setBuyImage(String buyImage) {
        this.buyImage = buyImage;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }
}
