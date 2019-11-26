package com.ice.androidpractice05_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.img2);//改变ImageView的src，也可以用setImageBitmap()指定一个bitmap对象
        imageView.setAlpha(0.5f);//设置图片透明度，取值0-1
        imageView.setScaleType(ImageView.ScaleType.CENTER);//设置ScaleType缩放方式
    }
}
