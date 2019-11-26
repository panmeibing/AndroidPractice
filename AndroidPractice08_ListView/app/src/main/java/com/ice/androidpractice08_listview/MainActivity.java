package com.ice.androidpractice08_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Contact> contactsList = new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        listView = findViewById(R.id.listView);
        //若只显示文本数据，则可以使用内置的adapter
        String[] data = {"这是数据1","这是数据2","这是数据3","这是数据4","这是数据5","这是数据6","这是数据7"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        */
        listView = findViewById(R.id.listView);
        addContact();
        ContactAdapter myAdapter = new ContactAdapter(MainActivity.this,R.layout.contact_layout,contactsList);
        listView.setAdapter(myAdapter);

        //监听点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactsList.get(i);
                Toast.makeText(getApplicationContext(),contact.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        //监听长按事件
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Contact contact = contactsList.get(i);
//                Toast.makeText(getApplicationContext(),contact.getName(),Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

    }

    private void addContact(){
        for (int i = 0;i<5;i++){
            Contact liumengli = new Contact("柳梦璃",R.drawable.img);
            contactsList.add(liumengli);
            Contact hanlingsha = new Contact("韩菱纱",R.drawable.img);
            contactsList.add(hanlingsha);
            Contact yuntianhe = new Contact("云天河",R.drawable.img);
            contactsList.add(yuntianhe);
            Contact murongziying = new Contact("慕容紫英",R.drawable.img);
            contactsList.add(murongziying);
        }

    }
}
