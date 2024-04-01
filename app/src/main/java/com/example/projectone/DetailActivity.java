package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    Intent intent;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.detailToolbar);
        webView = findViewById(R.id.detialWebview);

        intent = getIntent();
        url = intent.getStringExtra("url");

        //页面支持缩放,并且解决了正在加载的问题
        WebSettings webSettings =  webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);


        webView.loadUrl(url);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}