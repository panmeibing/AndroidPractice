package com.ice.androidpractice19_normalbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "程序启动完成" );
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//添加监听网络改变的动作
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);//注册广播
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//取消注册广播
        Log.e("MainActivity", "onDestroy: 取消注册广播");
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //获取连接管理器
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            //使用连接管理器获取网络信息
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //判断网络是否可用
            if (networkInfo != null && networkInfo.isAvailable()){
                Log.e("NetworkChangeReceiver","当前网络可用");
                //判断是否WiFi
                NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (wifi != null && wifi.isConnected()){
                    Log.e("NetworkChangeReceiver", "onReceive: 当前网络是WiFi");
                }
                //判断是否移动数据
                NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobile != null && mobile.isConnected()){
                    Log.e("NetworkChangeReceiver", "onReceive: 当前网络是数据流量");
                }
            }else {
                Log.e("NetworkChangeReceiver","当前网络不可用");
            }
        }
    }
}
