package com.ice.androidpractice17_service1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    //新建一个继承于Binder的内部类，用于实现自己的逻辑代码
    class PlayMusicBinder extends Binder {
        public void startPlayMusic(){
            Log.e("DownloadBinder", "startDownload: 开始播放音乐");
        }
        public int getProgress(){
            Log.e("DownloadBinder", "getProgress: 当前播放进度是XX%" );
            return 0;
        }

    }

    private PlayMusicBinder mBinder = new PlayMusicBinder();//实例化内部类
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;//返回新建的内部类，Activity连接服务时会接收
    }
    //服务被创建时调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate: 服务已经被创建");
    }
    //服务被启动时调用，服务只能被创建一次，但是可以被调用多次
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand: 服务已经启动");
        return super.onStartCommand(intent, flags, startId);

    }
    //服务被销毁时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "onDestroy: 服务已经销毁");
    }


}
