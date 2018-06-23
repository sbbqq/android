package com.example.moduleview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        wqqBloodView.setBloodList(bloodList);
       wqqBloodView.setPointList(pointList);
       wqqBloodView.startAnimation(2000);
    }
}
