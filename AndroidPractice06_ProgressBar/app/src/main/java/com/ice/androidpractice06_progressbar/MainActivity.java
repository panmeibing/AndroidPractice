package com.ice.androidpractice06_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View .OnClickListener{
    private Button btn1,btn2;
    private ProgressBar progressBar1,progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        progressBar1 = findViewById(R.id.progressBar1);
        btn1.setOnClickListener(this);
        progressBar2 = findViewById(R.id.progressBar2);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //显示圆形加载样式progressBar
            case R.id.btn1:
                if (progressBar1.getVisibility()==View.GONE){
                    progressBar1.setVisibility(View.VISIBLE);
                }else {
                    progressBar1.setVisibility(View.GONE);
                }
                break;
            //显示进度条样式progressBar
            case R.id.btn2:
                progressBar2.setVisibility(View.VISIBLE);
                int progress = progressBar2.getProgress();
                progress += 10;
                progressBar2.setProgress(progress);
                if(progress > 100){
                    progressBar2.setVisibility(View.GONE);
                }
                break;
             default:
                 break;
        }

    }
}
