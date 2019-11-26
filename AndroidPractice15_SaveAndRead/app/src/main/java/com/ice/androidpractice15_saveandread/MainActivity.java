package com.ice.androidpractice15_saveandread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_save, btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_save = findViewById(R.id.btn_save);
        btn_read = findViewById(R.id.btn_read);
        btn_save.setOnClickListener(this);
        btn_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveFile();//保存的文件在data/data/包名/files目录下
                break;
            case R.id.btn_read:
                readFile();//读取已经保存的文件
                break;
            default:
                break;
        }
    }


    private void saveFile() {
        FileOutputStream outputStream;
        BufferedWriter writer = null;
        try {
            //指定文件名和保存方式，Context.MODE_PRIVATE会覆盖文件，MODE_APPEND会追加文件内容
            outputStream = openFileOutput("test", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write("这个是需要保存的文本内容。");//写入内容
            Log.e("save", "文件已保存");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void readFile() {
        FileInputStream inputStream;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            inputStream = openFileInput("test");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", "读取文件错误: " + e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("read", "readFile: " + builder.toString());
        //return builder.toString();
    }
}
