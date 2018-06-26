package com.example.moduleview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.moduleview.Modal.BaseResult;
import com.example.moduleview.developerandroid.CustomView.BezierView;
import com.example.moduleview.developerandroid.CustomView.WqqBezierView;

import java.util.ArrayList;
import java.util.List;

public class BserActivity extends AppCompatActivity {

    private WqqBezierView mBezierView;
    List <BaseResult> baseResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bser);
        mBezierView = (WqqBezierView) findViewById(R.id.bezier);
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(10, 301));
        pointList.add(new Point(110, 302));
        pointList.add(new Point(210, 310));
        pointList.add(new Point(310, 300));
        pointList.add(new Point(410, 305));
        pointList.add(new Point(510, 308));
        pointList.add(new Point(610, 306));
        baseResults=new ArrayList<>();
        BaseResult baseResult=new BaseResult(2017,6,1,12,22);
        baseResult.setValue(200+(int)(Math.random()*90)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult);
        BaseResult baseResult1=new BaseResult(2017,6,2,12,22);
        baseResult1.setValue(170+(int)(Math.random()*90)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult1);
        BaseResult baseResult2=new BaseResult(2017,6,2,12,23);
        baseResult2.setValue(170+(int)(Math.random()*90)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult2);
        BaseResult baseResult3=new BaseResult(2018,6,2,12,23);
        baseResult3.setValue(180+(int)(Math.random()*90)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult3);
        BaseResult baseResult4=new BaseResult(2018,6,2,12,23);
        baseResult4.setValue(250+(int)(Math.random()*90)+"",120+(int)(Math.random()*50)+"");
        baseResults.add(baseResult4);

        baseResults.add(new BaseResult(2018,6,8,12,22));
        baseResults.add(new BaseResult(2018,6,25,12,22));

        mBezierView.setPointList(pointList);
        mBezierView.setBaseResults(baseResults);
        mBezierView.startAnimation(3000);
    }


}
