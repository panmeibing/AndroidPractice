package com.ice.androidpractice16_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button save, read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.save);
        read = findViewById(R.id.read);
        save.setOnClickListener(this);
        read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                //设置文件名和写入模式（MODE_PRIVATE是默认的）
                SharedPreferences.Editor editor = getSharedPreferences("test",MODE_PRIVATE).edit();
                editor.putString("name","柳梦璃");//插入String
                editor.putInt("height",163);//插入int，还有editor.putFloat(),editor.putLong()
                editor.putBoolean("marry",false);//插入boolean
                editor.apply();//一定不要忘了提交数据
                break;
            case R.id.read:
                SharedPreferences preferences = getSharedPreferences("test",MODE_PRIVATE);
                String name = preferences.getString("name","不知名");
                int height = preferences.getInt("height",160);
                boolean marry = preferences.getBoolean("marry",false);
                Log.e("2222", "name:"+name+",height:"+height+",marry:"+marry);
                break;
            default:
                break;
        }
    }
}
