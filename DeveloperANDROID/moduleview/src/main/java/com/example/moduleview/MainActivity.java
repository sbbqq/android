package com.example.moduleview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.moduleview.developerandroid.CustomView.Main2Activity;

public class MainActivity extends Activity {

  TextView textView,textHuixing,txPathMeasure,txpasaier,txblood,txTvtest,txrili;
  TextView txDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.txnextAc);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DragHelperLearnActivity.class));
            }
        });
        textHuixing=(TextView)findViewById(R.id.txnextHuixing);
        textHuixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        txPathMeasure=(TextView)findViewById(R.id.txpathmeasure);
        txPathMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PathMeasureActivity.class));
            }
        });
        txpasaier=(TextView)findViewById(R.id.beisaier);
        txpasaier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BserActivity.class));
            }
        });
        txblood=(TextView)findViewById(R.id.txblood);
        txblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BloodActivity.class));
            }
        });
        txTvtest=(TextView)findViewById(R.id.txtvtest);
        txTvtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TvControlerActivity.class));
            }
        });
        txrili=(TextView)findViewById(R.id.txrili);
        txrili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RiliActivity.class));
            }
        });
        txDate=(TextView)findViewById(R.id.txdate);
        txDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DatestyleshowActivity.class));
            }
        });
    }
}
