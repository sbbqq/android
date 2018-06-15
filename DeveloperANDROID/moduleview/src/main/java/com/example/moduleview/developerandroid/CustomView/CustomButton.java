package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.moduleview.R;

/**
 * Created by wqq on 18-6-15.
 */

public class CustomButton extends android.support.v7.widget.AppCompatButton {
    int state=1;//1 left kong 2 left 实心　３　 left kong 4 right shi
    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public int getState() {
        return state;

    }

    public void setState(int state) {
        this.state = state;
    }
    public void init(){
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    setState(2);
                }
                else{
                    setState(1);
                }
                invalidate();
            }
        });
        this.setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(state==1){
            this.setBackground(getResources().getDrawable(R.drawable.unchose));
        }
        else if(state==2){
            this.setBackground(getResources().getDrawable(R.drawable.chose));
        }
        else if(state==3){
            this.setBackground(getResources().getDrawable(R.drawable.chose));
        }
        else if(state==4){
            this.setBackground(getResources().getDrawable(R.drawable.chose));
        }
    }

}
