package com.ice.androidpractice21_localbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyReceiver myReceiver;
    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ice.localBroadcast.MY_BROADCAST");
        myReceiver = new MyReceiver();
        localBroadcastManager.registerReceiver(myReceiver,intentFilter);//注册广播

        Intent intent = new Intent("com.ice.localBroadcast.MY_BROADCAST");
        localBroadcastManager.sendBroadcast(intent);//发送广播

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myReceiver);//取消注册
    }
}
