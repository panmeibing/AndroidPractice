package com.ice.androidpractice18_service2;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String data = intent.getStringExtra("data");
        Log.e("MyIntentService", "onHandleIntent: "+data );
        Log.e("MyIntentService", "当前的线程是: "+Thread.currentThread().getName() );
    }

    @Override
    public void onDestroy() {
        Log.e("MyIntentService", "线程已被销毁 ");
        super.onDestroy();

    }
}
