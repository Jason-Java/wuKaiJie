package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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


//        设置WebViewClient：
//        为WebView设置一个WebViewClient。这个客户端会处理各种导航事件，包括加载网页、加载页面资源、处理页面错误等。
//        通过设置WebViewClient，你可以控制WebView的行为，防止它打开系统浏览器。
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // 返回true表示由WebView处理该请求，而不是调用系统浏览器
                return false;
            }

            // 对于API 24以下的版本，你可能还需要覆盖这个方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回true表示由WebView处理该请求，而不是调用系统浏览器
                return false;
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


////    设置Intent拦截：
////    有时，一些链接可能会通过Intent尝试在外部浏览器中打开。为了拦截这些Intent，
////    你需要在你的Activity中覆盖onNewIntent方法，并检查是否有任何与浏览器相关的Intent被触发。
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
//            Uri uri = intent.getData();
//            if (uri != null) {
//                // 加载URI到WebView而不是在外部浏览器中打开
//                webView.loadUrl(uri.toString());
//            }
//        }
//    }
}