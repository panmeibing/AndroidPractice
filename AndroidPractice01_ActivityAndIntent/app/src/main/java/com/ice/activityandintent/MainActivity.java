package com.ice.activityandintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button main_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_btn = findViewById(R.id.main_btn);
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "这是第一个Activity的数据";
                //显式意图
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("MainActivity_Data",data);//第一个参数是键，第二个是值
                startActivityForResult(intent,1);//两个参数，第一个是intent，第二个是请求码，值唯一就行
                /*
                //隐式意图
                Intent intent = new Intent("com.ice.activityandintent.ACTION_START");
                intent.addCategory("com.ice.activityandintent.MY_CATEGORY");
                startActivity(intent);
                 */
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                String returnedData = data.getStringExtra("SecondActivity_Data");
                Log.e("MainAcitivity", returnedData);
                break;
        }
    }
}
