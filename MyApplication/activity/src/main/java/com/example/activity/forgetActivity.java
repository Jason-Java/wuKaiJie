package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class forgetActivity extends AppCompatActivity {
    private int[] arrayPictures = new int[]{R.drawable.tp1,R.drawable.tp2,R.drawable.tp3,R.drawable.tp4,R.drawable.tp5,R.drawable.tp6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        TextView tv = findViewById(R.id.display_name);
        tv.setText(name);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(forgetActivity.this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posistion, long id) {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("imageid",arrayPictures[posistion]);
                intent.putExtras(bundle1);
                setResult(0x110,intent);
                finish();
            }
        });


        //返回
        Button bt = findViewById(R.id.out);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{
        public Context mcontext;
        //编写构造方法
        public ImageAdapter(Context context){
            mcontext = context;
        }

        @Override
        public int getCount() {
            return arrayPictures.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null){
                imageView = new ImageView(mcontext);
                imageView.setLayoutParams(new GridView.LayoutParams(100,100));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            }else{
                imageView = (ImageView) view;
            }
            imageView.setImageResource(arrayPictures[position]);
            return imageView;
        }
    }
}