package com.ice.androidpractice10_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        startWebView("https://www.baidu.com");
    }

    private void startWebView(String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//设置进度条
        WebSettings webSettings = webView.getSettings();//获取WebView的设置
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认编码方式

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //允许js打开新窗口
        webSettings.setJavaScriptEnabled(true);//支持js脚本

        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏缩放控件

        webSettings.setUseWideViewPort(true);//将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕的大小

        webSettings.setBlockNetworkImage(false);//若为true则不显示网页图片
        webSettings.setDomStorageEnabled(true);//支持H5本地存储
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//在5.0以上，允许加载http和https的混合内容

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            //打开网页时不调用系统浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try{
                    //因为WebView只支持http和https两种协议，遇到其他协议可能会崩溃
                    if (url.startsWith("http:")||url.startsWith("https:")){
                        view.loadUrl(url);
                        return true;
                    }else{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){
                    return false;
                }

            }
        });

        //更新ProgressBar进度条
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }

            }
        });

    }
    //按后退键后退网页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断是后退键并且WebView可以后退
        if ((keyCode == KEYCODE_BACK)&&webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //关闭WebView的Activity时要Destroy它，否则会继续播放声音
    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);//清除缓存
        webView.destroy();
    }
}
