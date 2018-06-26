package com.example.moduleview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moduleview.Modal.BaseResult;
import com.example.moduleview.Modal.Blood;
import com.example.moduleview.developerandroid.CustomView.WqqBloodView;

import java.util.ArrayList;
import java.util.List;

public class BloodActivity extends AppCompatActivity {
    WqqBloodView wqqBloodView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blood);
        wqqBloodView=(WqqBloodView)findViewById(R.id.wqqblood);
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(10, 200));
        pointList.add(new Point(110, 200));
        pointList.add(new Point(210, 100));
        pointList.add(new Point(310, 200));
        pointList.add(new Point(410, 100));
        pointList.add(new Point(510, 100));
        pointList.add(new Point(610, 100));
        pointList.add(new Point(710, 200));
        pointList.add(new Point(810, 100));
        List<Blood>bloodList=new ArrayList<>();
        for(int i=0;i<7;i++){
            Blood blood=new Blood(120+(int)(Math.random()*50),70+(int)(Math.random()*20));
            bloodList.add(blood);
        }

        List<BaseResult> baseResults = new ArrayList<>();
        BaseResult baseResult=new BaseResult(2017,6,1,12,22);
          baseResult.setValue(70+(int)(Math.random()*20)+"",120+(int)(Math.random()*50)+"");
       baseResults.add(baseResult);
        BaseResult baseResult1=new BaseResult(2017,6,2,12,22);
        baseResult1.setValue(70+(int)(Math.random()*20)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult1);
        BaseResult baseResult2=new BaseResult(2017,6,2,12,23);
        baseResult2.setValue(70+(int)(Math.random()*20)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult2);
        BaseResult baseResult3=new BaseResult(2018,6,2,12,23);
        baseResult3.setValue(70+(int)(Math.random()*20)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult3);
        BaseResult baseResult4=new BaseResult(2018,6,2,12,23);
        baseResult4.setValue(70+(int)(Math.random()*20)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult4);
        baseResults.add(new BaseResult(2018,6,8,12,22));
        baseResults.add(new BaseResult(2018,6,25,12,22));
        wqqBloodView.setBaseResults(baseResults);
       wqqBloodView.startAnimation(2000);
    }
}
