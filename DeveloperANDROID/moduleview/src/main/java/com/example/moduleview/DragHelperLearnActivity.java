package com.example.moduleview;

import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.moduleview.developerandroid.CustomView.CoolView;

public class DragHelperLearnActivity extends Activity {
  CoolView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_drag_helper_learn,null,false);
        setContentView(linearLayout);
        view=new CoolView(this);
        linearLayout.addView(view);
    }
}
