package com.example.tvtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.tvtest.adpter.AttrValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SouExchange extends AppCompatActivity {
    LinearLayout soulone,soultwo;
    RelativeLayout relativeLayout;
    Button btnexchange;
    boolean changed=false;
    WeightShow weightShow;
    AttrValue attrValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_exchange);
        soulone=(LinearLayout)findViewById(R.id.soulone);
        soultwo=(LinearLayout)findViewById(R.id.soultwo);
        btnexchange=(Button)findViewById(R.id.btnsoulexchagne);
        relativeLayout=(RelativeLayout)findViewById(R.id.relparent);
        weightShow=(WeightShow)findViewById(R.id.weighshow);

        List<String> clors = new ArrayList<>();
        clors.add("#10D3DE");
        clors.add("#26A6FF");
        clors.add("#FF6E00");
        clors.add("#FF475D");
        List<String> standnames = new ArrayList<>();
        standnames.add("偏瘦");
        standnames.add("标准");
        standnames.add("偏胖");
        standnames.add("肥胖");
        List<String> standvaluess = new ArrayList<>();
        standvaluess.add("180");
        standvaluess.add("222");
        standvaluess.add("270");
        attrValue = new AttrValue(standnames, standvaluess, clors);
        weightShow.setAttrValue(attrValue);
        weightShow.setProgress(0.68f);
        Log.e("soulonewidth",soulone.getMeasuredWidth()+"");
        timeTranform();

        btnexchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // relativeLayout.getChildAt(0).layout(920,0,1920,1080);
               // relativeLayout.requestLayout();
                Log.e("change","*******************");
                if(!changed) {
                    soulone.layout(920, 0, 1920, 1080);
                    soultwo.layout(0, 0, 920, 1080);
                }
                else{
                    soulone.layout(0, 0, 1000, 1080);
                    soultwo.layout(1000, 0, 1920, 1080);
                }
                changed=!changed;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("width",soulone.getMeasuredWidth()+"");
    }


    public void timeTranform(){
        //使用默认时区和语言环境获得一个日历
        Calendar cale = Calendar.getInstance();
        //将Calendar类型转换成Date类型
        Date tasktime=cale.getTime();
        //设置日期输出的格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //格式化输出
       Log.e("time",df.format(tasktime));
    }
}
