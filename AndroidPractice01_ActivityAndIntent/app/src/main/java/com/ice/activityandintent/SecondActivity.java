package com.ice.activityandintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button second_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String data = intent.getStringExtra("MainActivity_Data");
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

        second_btn = findViewById(R.id.second_btn);
        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.putExtra("SecondActivity_Data","这是第第二个Activity");
                setResult(RESULT_OK,intent2);//向上一个activity传递数据
                finish();//关掉当前activity
            }
        });
    }
}
