package com.ice.androidpractice17_service1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View .OnClickListener{
    private Button btn_startService,btn_stopService;
    private Button btn_bindService,btn_unbindService;
    //新建一个匿名的ServiceConnection类，重写连接和断开服务的两个方法处理自己的逻辑代码
    private ServiceConnection connection = new ServiceConnection() {
        //连接服务，接收MyService类返回的mBinder
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("ServiceConnection", "onServiceConnected: 服务已连接");
            //新建一个playMusicBinder类去实现自己的逻辑
            MyService.PlayMusicBinder playMusicBinder = (MyService.PlayMusicBinder) iBinder;
            playMusicBinder.startPlayMusic();
            playMusicBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("ServiceConnection", "onServiceDisconnected: 服务已断开" );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_startService = findViewById(R.id.btn_startService);
        btn_stopService = findViewById(R.id.btn_stopService);
        btn_bindService = findViewById(R.id.btn_bindService);
        btn_unbindService = findViewById(R.id.btn_unbindService);
        btn_startService.setOnClickListener(this);
        btn_stopService.setOnClickListener(this);
        btn_bindService.setOnClickListener(this);
        btn_unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //开始服务
            case R.id.btn_startService:
                Intent intent_start = new Intent(this,MyService.class);
                startService(intent_start);//开启服务
                break;
             //停止服务
            case R.id.btn_stopService:
                Intent intent_stop = new Intent(this,MyService.class);
                stopService(intent_stop);//停止服务，也可以在Service里任意位置调用stopSelf()停止服务
                break;
             //绑定服务
            case R.id.btn_bindService:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
             //断开绑定服务
            case R.id.btn_unbindService:
                unbindService(connection);
                break;
             default:
                 break;
        }
    }

}
