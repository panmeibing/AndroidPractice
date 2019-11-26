package com.ice.androidpractice20_orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MyBroadcastReceiver", "onReceive: 接收到自定义广播");
        abortBroadcast();//截断广播
    }
}
