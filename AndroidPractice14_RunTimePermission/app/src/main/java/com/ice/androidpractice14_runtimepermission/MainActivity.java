package com.ice.androidpractice14_runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View .OnClickListener{
    private Button btn_single,btn_multiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_single = findViewById(R.id.btn_single);
        btn_single.setOnClickListener(this);
        btn_multiple = findViewById(R.id.btn_multiple);
        btn_multiple.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_single:
                requestPermission_single();
                break;
            case R.id.btn_multiple:
                requestPermission_multiple();
                break;
             default:
                 break;
        }
    }

    private void requestPermission_single(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            Log.e("11111111", "requestPermission_single: 已经获得写入权限了");//处理自己的代码
        }
    }

    private void requestPermission_multiple(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
           permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,2);
        }else {
            Log.e("222222222", "requestPermission_multiple: 已经获得所有权限");//处理自己的代码
        }
    }


    //不管用户是否授权，都会回调这个函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("1111111", "onRequestPermissionsResult: 申请单个权限授权成功" );//处理自己的代码
                }else{
                    Log.e("1111111", "onRequestPermissionsResult: 申请单个权限授权失败" );
                }
                break;
            case 2:
                if (grantResults.length>0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Log.e("2222222222", "onRequestPermissionsResult: 用户拒绝了一个或多个权限");
                            return;
                        }
                    }
                    //处理自己的代码
                    Log.e("2222222222", "onRequestPermissionsResult: 用户授权了所有权限");//处理自己的代码
                }else {
                    Log.e("2222222222", "onRequestPermissionsResult: 程序运行错误" );
                }
                break;
             default:
                 break;
        }
    }
}
