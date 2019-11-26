package com.ice.androidpractice20_orderedbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //发送自定义的标准广播
//        Intent intent = new Intent("com.ice.android.MYBROADCAST");
//        sendBroadcast(intent);

        //发送自定义的有序广播
        Intent intent = new Intent("com.ice.android.MYBROADCAST");
        sendOrderedBroadcast(intent,null);//第二个参数是receivePermission
    }
}
