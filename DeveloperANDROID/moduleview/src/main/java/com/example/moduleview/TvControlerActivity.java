package com.example.moduleview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class TvControlerActivity extends AppCompatActivity {
 Button btnclick,btnleft,btnright,btntop,btnbottom,btnrili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_controler);
        btnclick=(Button)findViewById(R.id.button2);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingying","*********************");
            }
        });
        btnclick.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("getfoucus","****************");
                    btnclick.setBackgroundColor(Color.RED);
                }
                else{
                    Log.e("Lose","****************");
                    btnclick.setBackgroundColor(Color.WHITE);
                }
            }
        });

        btnclick.setFocusable(true);
        btnclick.requestFocus();


        btnleft=(Button)findViewById(R.id.button5);
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingying","*********************");
            }
        });
        btnleft.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("getfoucus","****************");
                    btnleft.setBackgroundColor(Color.RED);
                }
                else{
                    Log.e("Lose","****************");
                    btnleft.setBackgroundColor(Color.WHITE);
                }
            }
        });

        btnleft.setFocusable(true);
        btnright=(Button)findViewById(R.id.button6);
        btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingying","*********************");
            }
        });
        btnright.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("getfoucus","****************");
                    btnright.setBackgroundColor(Color.RED);
                }
                else{
                    Log.e("Lose","****************");
                    btnright.setBackgroundColor(Color.WHITE);
                }
            }
        });
        btnright.setFocusable(true);

        btntop=(Button)findViewById(R.id.button3);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingying","*********************");
            }
        });
        btntop.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("getfoucus","****************");
                    btntop.setBackgroundColor(Color.RED);
                }
                else{
                    Log.e("Lose","****************");
                    btntop.setBackgroundColor(Color.WHITE);
                }
            }
        });
        btntop.setFocusable(true);


        btnbottom=(Button)findViewById(R.id.button4);
        btnbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingying","*********************");
            }
        });
        btnbottom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("getfoucus","****************");
                    btnbottom.setBackgroundColor(Color.RED);
                }
                else{
                    Log.e("Lose","****************");
                    btnbottom.setBackgroundColor(Color.WHITE);
                }
            }
        });
        btnbottom.setFocusable(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
                Log.e("sure","你按下中间键");
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                Log.e("sure","你按下下方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                Log.e("Left","你按下左方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Log.e("right","你按下右方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_UP:
                Log.e("up","你按下上方向键");
                break;
        }
        return super.onKeyDown(keyCode, event);

    }
}
