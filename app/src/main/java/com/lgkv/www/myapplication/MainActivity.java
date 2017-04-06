package com.lgkv.www.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bigsun.www.mylibrary.lgkv.LG;
import com.bigsun.www.mylibrary.lgkv.interf.LogInterceptor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LG.init(this)
                .setmParser(null) //json解析方式，默认GSON
                .setmConverter(null) // 类型解析方式，默认（Parser在这当中调用）
                .setmSerializer(null) // 序列化方式，默认
                .setmEncryption(null) // 加密方式，默认Base64
                .setmStorage(null) // 数据读取方式，默认SharedPreferences
                .setmLogInterceptor(new MyLogInterceptor()) // Log打印方式，默认不打印
                .build();
        List<Demo> list = getDemoData();
        LG.put("ListData", list);
    }
    public List<Demo> getDemoData() {
        List<Demo> list = new ArrayList<>();
        for (int i =  0 ; i < 10;i++) {
            Demo demo = new Demo();
            demo.setId(String.valueOf(i));
            demo.setName(String.valueOf("name:"+ i));
            List<Demo.EntityDemo> ll = new ArrayList<>();
            for (int j= 0;j<3;j++) {
                Demo.EntityDemo entityDemo = new Demo.EntityDemo();
                entityDemo.setAge("age"+j);
                ll.add(entityDemo);
            }
            list.add(demo);
        }
        return list;
    }

    class MyLogInterceptor implements LogInterceptor {
        @Override public void onLog(String message) {
            Log.d("LG",message);
        }
    }

}
