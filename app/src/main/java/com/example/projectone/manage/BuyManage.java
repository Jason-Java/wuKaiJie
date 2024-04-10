package com.example.projectone.manage;


import android.util.Log;

import com.example.projectone.table.Buy;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class BuyManage {
    private  static List<Buy> buys = new ArrayList<>();
    private static Buy buy;

    public static List<Buy> FindAll(){
        return LitePal.findAll(Buy.class);
    }

    public static List<Buy> FindBuyShop(String buyShop){
        return LitePal.where("buyShop = ?",buyShop).find(Buy.class);
    }

    public static List<Buy> FindBuyCollection(String buyName,String buyShop){
        return LitePal.where("buyName = ? and buyShop = ?",buyName,buyShop).find(Buy.class);
    }

    public static List<Buy> FindBuyName(String buyName){
        return LitePal.where("buyName like ?","%"+buyName+"%").find(Buy.class);
    }

    public static void AddBuy(String buyImage,String buyName,int buyPrice,String buyUrl,String buyShop){
        buy = new Buy();
        buy.setBuyImage(buyImage);
        buy.setBuyName(buyName);
        buy.setBuyPrice(buyPrice);
        buy.setBuyUrl(buyUrl);
        buy.setBuyShop(buyShop);

        try{
            buy.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "buy异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }
}
