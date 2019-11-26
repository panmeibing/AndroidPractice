package com.ice.androidpractice18_service2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MainActivity", "当前线程是: "+Thread.currentThread().getName() );
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                intent.putExtra("data","this is intent data");
                startService(intent);
            }
        });
    }
}
