package com.example.projectone.my.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.projectone.manage.BuyCollectionManage;
import com.example.projectone.manage.BuyManage;
import com.example.projectone.R;
import com.example.projectone.databinding.BuyitemBinding;
import com.example.projectone.table.Buy;
import com.example.projectone.table.BuyCollection;

import java.util.ArrayList;
import java.util.List;

public class MyBuyCollectionAdapter extends BaseRecyclerViewAdapter<BuyCollection>{
    List<BuyCollection> buyCollections;
    BuyCollection buyCollection;
    List<Buy> buys = new ArrayList<>();
    Buy buy;
    String username;
    private BuyitemBinding binding;

    public MyBuyCollectionAdapter(Context mContext,String username) {
        super(R.layout.mybuycollectionitem, mContext,username);
        this.username = username;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, BuyCollection bean, int position) {
        buyCollection = bean;

        holder.setText(R.id.myBuyName,buyCollection.getBuyName(),null);
        buys = BuyManage.FindBuyCollection(buyCollection.getBuyName(),buyCollection.getBuyShop());
        buy = buys.get(0);
        holder.setText(R.id.myBuyPrice,buy.getBuyPrice()+"元",null);
        holder.setText(R.id.myBuyShop,buy.getBuyShop(),null);
        holder.setImageResource(R.id.myBuyImage,buy.getBuyImage(),null);

        //点击收藏Button
        holder.setButton(R.id.myBuyButton, new BaseRecyclerViewHolder.CallBack<Button>() {
            @Override
            public void run(Button button) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buyCollections = BuyCollectionManage.Find(username,holder.getText(R.id.myBuyName),holder.getText(R.id.myBuyShop));
                        if (buyCollections.size() == 0){
                            BuyCollectionManage.Add(username,holder.getText(R.id.myBuyName),holder.getText(R.id.myBuyShop));
                            holder.setButton(R.id.myBuyButton,"已收藏",R.color.bottomcolor);
                        }else {
                            BuyCollectionManage.Delete(username,holder.getText(R.id.myBuyName),holder.getText(R.id.myBuyShop));
                            holder.setButton(R.id.myBuyButton,"收藏",R.color.nochoose);
                        }
                    }
                });
            }
        });
    }
}
