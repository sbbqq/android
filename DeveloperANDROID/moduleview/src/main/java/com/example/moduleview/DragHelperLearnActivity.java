package com.example.moduleview;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.moduleview.developerandroid.CustomView.CoolView;

public class DragHelperLearnActivity extends Activity {
  CoolView view;
  Button buttonone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_drag_helper_learn,null,false);
        setContentView(linearLayout);
        buttonone=(Button)findViewById(R.id.btnone);
        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btnclick,","******************");
            }
        });
        //view=new CoolView(this);
       // linearLayout.addView(view);
    }
}
