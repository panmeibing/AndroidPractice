package com.ice.androidpractice13_jsonobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_JSON,btn_GSON;
    private String json = "[{'id':'1','name':'name1','sex':'nan'},{'id':'2','name':'name2','sex':'nv'},{'id':'3','name':'name3','sex':'nan'}]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_JSON = findViewById(R.id.btn_JSON);
        btn_JSON.setOnClickListener(this);
        btn_GSON = findViewById(R.id.btn_GSON);
        btn_GSON.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_JSON:
                parse_JOSNObject(json);
                break;
            case R.id.btn_GSON:
                parse_GSON(json);
                break;
        }
    }

    private void parse_JOSNObject(String jsonData){
        try {
            //如果得到的json不是数组，就不要用JSONArray，否则会报错
            //直接用 JSONObject jsonObject =  new JSONObject(jsonData);
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String sex = jsonObject.getString("sex");
                Log.e("JSON", "parseJOSNObject: "+"id:"+id+",name:"+name+",sex:"+sex );
            }
        }catch (Exception e){e.printStackTrace();}

    }

    private void parse_GSON(String jsonData){
        Gson gson = new Gson();
        List<User> userList = gson.fromJson(jsonData,new TypeToken<List<User>>(){}.getType());
        for (User user:userList){
            Log.e("parse_GSON", "id: "+user.getId()+",name:"+user.getName()+",sex:"+user.getSex() );
        }
    }


}

