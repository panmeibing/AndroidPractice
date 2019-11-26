package com.ice.androidpractice11_httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHttpURLConnection();
            }
        });
    }

    private void startHttpURLConnection(){
        //耗时操作必须要放在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();//打开openConnection链接
                    connection.setRequestMethod("GET");//以GET方式发起请求（默认）

                    /*//若是以POST方式提交数据
                    connection.setRequestMethod("POST");
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    outputStream.writeBytes("usename=name&password=111111");//提交的数据用&分割     */

                    connection.setConnectTimeout(5000);//设置请求超时时间为5秒
                    connection.setReadTimeout(5000);//设置读取超时时间为5秒
                    InputStream inputStream = connection.getInputStream();//获得数据流
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    dealWithResult(response.toString());//处理请求得到的数据
                }catch (Exception e){e.printStackTrace();}
                finally {
                    if (reader != null){
                        try{reader.close();}catch (Exception e){e.printStackTrace();}
                    }
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void dealWithResult(final String res){
        //在UI主线程操作，比如说用TextView显示
        new Handler(getApplicationContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.e("res", "run: "+res );
            }
        });

    }
}
