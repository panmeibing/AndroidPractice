package com.ice.androidpractice07_progressdialogandalertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button pgDialog_btn,alertDialog_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgDialog_btn = findViewById(R.id.pgDialog_btn);
        pgDialog_btn.setOnClickListener(this);
        alertDialog_btn = findViewById(R.id.alertDialog_btn);
        alertDialog_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pgDialog_btn:
                showProgressDialog();
                break;
            case R.id.alertDialog_btn:
                showAlertDialog();
                break;
            default:
                 break;
        }
    }

    private void showProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("提示");//设置标题
        progressDialog.setMessage("这是一个ProgressDialog");//设置提示内容
        progressDialog.setCancelable(true);//设置是否可以取消，false则不能取消（比如说强制升级APP）
        progressDialog.show();
        //progressDialog.dismiss();//当完成操作后调用此方法隐藏progressDialog
    }

    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("提示：");//设置标题
        alertDialog.setMessage("这是一个alertDialog");//设置内容
        alertDialog.setCancelable(false);//设置是否取消
        //设置积极按钮
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"点击了'确定'按钮",Toast.LENGTH_SHORT).show();
            }
        });
        //设置消极按钮
        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"点击了'取消'按钮",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();//让alertDialog显示，不要忘了这句代码
    }
}
