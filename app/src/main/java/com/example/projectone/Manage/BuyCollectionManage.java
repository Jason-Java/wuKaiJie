package com.example.projectone.Manage;

import android.util.Log;

import com.example.projectone.table.BuyCollection;

import org.litepal.LitePal;

import java.util.List;

public class BuyCollectionManage {
    private static List<BuyCollection> buyCollections;
    private static BuyCollection buyCollection;

    public static List<BuyCollection> FindCollection(String userName){
        return LitePal.where("userName = ?",userName).find(BuyCollection.class);
    }

    public static List<BuyCollection> Find(String userName,String buyName,String buyShop){
        return LitePal.where("userName = ? and buyName = ? and buyShop = ?",userName,buyName,buyShop).find(BuyCollection.class);
    }

    public static List<BuyCollection> Find(String userName,String buyName){
        return LitePal.where("userName = ? and buyName = ?",userName,buyName).find(BuyCollection.class);
    }

    public static void Add(String userName,String buyName,String buyShop){
        buyCollection = new BuyCollection();
        buyCollection.setUserName(userName);
        buyCollection.setBuyName(buyName);
        buyCollection.setBuyShop(buyShop);

        try{
            buyCollection.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "buyCollection异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }

    public static void Delete(String userName,String buyName,String buyShop){
        LitePal.deleteAll(BuyCollection.class,"userName = ? and buyName = ? and buyShop = ?",userName,buyName,buyShop);
    }
}
