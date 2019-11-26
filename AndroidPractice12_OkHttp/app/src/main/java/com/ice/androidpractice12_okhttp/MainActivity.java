package com.ice.androidpractice12_okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_get,btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get = findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);
        btn_post = findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get:
                request_get();
                break;
            case R.id.btn_post:
                request_post();
                break;
             default:
                 break;
        }
    }

    private void request_get(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                //.addHeader("User-Agent","xxx")//设置请求头
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            //请求失败时调用
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (e instanceof SocketTimeoutException){
                    Log.e("OkHttp请求超时异常", "onFailure: "+e.toString());
                }
                if (e instanceof ConnectException){
                    Log.e("OkHttp请求连接异常", "onFailure: "+e.toString());
                }
            }
            //请求成功时调用
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res = response.body().string();//返回字符串
                //InputStream inputStream = response.body().byteStream();//返回流，比如说用于下载图片等文件
                Log.e("OkHttp GET请求成功", "onFailure: "+res);
            }
        });
    }

    private void request_post(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()//创建一个请求体
                .add("name","name")
                .add("password","123456")
                .build();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody) //添加请求体
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res = response.body().string();
                Log.e("OkHttp POST请求成功", "onFailure: "+res);
            }
        });
    }
}
