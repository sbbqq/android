package com.example.moduleview;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.moduleview.developerandroid.CustomView.BezierView;
import com.example.moduleview.developerandroid.CustomView.WqqBezierView;

import java.util.ArrayList;
import java.util.List;

public class BserActivity extends AppCompatActivity {

    private WqqBezierView mBezierView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bser);
        mBezierView = (WqqBezierView) findViewById(R.id.bezier);
        mEditText = (EditText) findViewById(R.id.editText);
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(10, 301));
        pointList.add(new Point(110, 302));
        pointList.add(new Point(210, 310));
        pointList.add(new Point(310, 300));
        pointList.add(new Point(410, 305));
        pointList.add(new Point(510, 308));
        pointList.add(new Point(610, 306));


        mBezierView.setPointList(pointList);
        //mBezierView.startAnimation(1000);
    }

    public void onClick(View v) {
        try {
            mBezierView.setLineSmoothness(Float.valueOf(mEditText.getText().toString()));
        } catch (NumberFormatException e) {
        }
        mBezierView.startAnimation(1000);
    }
}
