package com.bawei.apptwo.ui.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.bawei.apptwo.R;
import com.bawei.apptwo.ui.custom.CustomView;
import com.bawei.apptwo.ui.custom.CustomWather;

public class MainActivity extends AppCompatActivity {

    private CustomView view;
    private CustomWather wather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.title);
        wather = findViewById(R.id.water);
        String[] str = new String[]{
                "水煮肉片", "锅包肉", "鱼香肉丝", "水煮鱼", "木须肉", "地三鲜", "尖椒干豆腐"
                , "干锅土豆片", "汉堡", "炸鸡", "可乐", "啤酒", "火腿", "米饭"
        };
        for (int i = 0; i < str.length; i++) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(str[i]);
            textView.setTextSize(20);
            textView.setBackgroundResource(R.drawable.custom);
            wather.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,ShowDataActivity.class));
                }
            });
        }
    }

}
