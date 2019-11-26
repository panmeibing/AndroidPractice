package com.ice.androidpractice09_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContact();//往contactList添加数据
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);//添加布局管理器
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);设置为横向水平滚动，默认是垂直
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);
    }

    private void addContact(){
        for (int i = 0;i<3;i++){
            Contact LiuMengLi = new Contact("柳梦璃",R.drawable.img);
            contactList.add(LiuMengLi);
            Contact HanLingSha = new Contact("韩菱纱",R.drawable.img);
            contactList.add(HanLingSha);
            Contact YunTianHe = new Contact("云天河",R.drawable.img);
            contactList.add(YunTianHe);
            Contact MuRongZiYing = new Contact("慕容紫英",R.drawable.img);
            contactList.add(MuRongZiYing);
        }

    }
}
