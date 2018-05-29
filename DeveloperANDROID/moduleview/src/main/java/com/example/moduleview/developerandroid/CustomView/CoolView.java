package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.moduleview.R;



/**
 * Created by alone-nine-sword on 18-4-2.
 */

public class CoolView extends ViewGroup {
    private Paint paint;
    private ImageView imageViewMain;
    private int ChildCountOther=3;//
    private int imgDefault=R.drawable.test;
    LayoutParams layoutParams;
    Long Durationg=1000L;
    private boolean StatusOpen=true;
    private final   int Id_Main=1000,Id_One=1001,Id_two=1002,Id_three=1003;
    public CoolView(Context context) {
        super(context);
        imageViewMain=new ImageView(context);
        imageViewMain.setImageResource(imgDefault);
        layoutParams= new WindowManager.LayoutParams();
        layoutParams.height=50;
        layoutParams.width=50;
        imageViewMain.setLayoutParams(layoutParams);
        imageViewMain.setId(Id_Main);
        imageViewMain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("yingyingying","*******************"+v.getId());
                StatusOpen=!StatusOpen;
                invalidate();
                if(StatusOpen)
                {
                    setMeasuredDimension(400,400);
                    for(int i=1;i<getChildCount();i++)
                        getChildAt(i).setVisibility(VISIBLE);
                }
                else{
                    for(int i=1;i<getChildCount();i++)
                        getChildAt(i).setVisibility(GONE);
                    setMeasuredDimension(80,400);
                }
                requestLayout();

            }
        });
        addView(imageViewMain);

        for(int i=0;i<ChildCountOther;i++){
            ImageView imageViewchilrd=new ImageView(context);
            imageViewchilrd.setImageResource(imgDefault);

            imageViewchilrd.setLayoutParams(layoutParams);
            addView(imageViewchilrd);

        }
        this.setBackgroundColor(Color.parseColor("#01FFFFFF"));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout","*****************");
        View one,two,three;
         if(changed){
             View main=getChildAt(0);
             main.layout(getMeasuredWidth()/2-main.getMeasuredWidth()/2,getMeasuredHeight()/2-main.getMeasuredHeight()/2,getMeasuredWidth()/2-main.getMeasuredWidth()/2+main.getMeasuredHeight(),getMeasuredHeight()/2+main.getMeasuredHeight()/2);
             // setAnimation(main,getMeasuredWidth()/2-main.getMeasuredWidth()/2,getMeasuredHeight()/2-main.getMeasuredHeight()/2);
              one=getChildAt(1);
             one.layout(getMeasuredWidth()/2-one.getMeasuredWidth()/2,getMeasuredHeight()/2-one.getMeasuredHeight()/2,getMeasuredWidth()/2-one.getMeasuredWidth()/2+one.getMeasuredHeight(),getMeasuredHeight()/2+one.getMeasuredHeight()/2);
            // setAnimation(one,0,-getMeasuredHeight()/2+one.getMeasuredHeight());
             two=getChildAt(2);
             two.layout(getMeasuredWidth()/2-two.getMeasuredWidth()/2,getMeasuredHeight()/2-two.getMeasuredHeight()/2,getMeasuredWidth()/2-two.getMeasuredWidth()/2+two.getMeasuredHeight(),getMeasuredHeight()/2+two.getMeasuredHeight()/2);

              three=getChildAt(3);
             three.layout(getMeasuredWidth()/2-three.getMeasuredWidth()/2,getMeasuredHeight()/2-three.getMeasuredHeight()/2,getMeasuredWidth()/2-three.getMeasuredWidth()/2+three.getMeasuredHeight(),getMeasuredHeight()/2+three.getMeasuredHeight()/2);

             if(StatusOpen){
                 setAnimation(one,0,-getMeasuredHeight()/2+one.getMeasuredHeight());
                 setAnimation(two,getMeasuredWidth()/2-two.getMeasuredWidth()/2,0);
                 setAnimation(three,0,getMeasuredHeight()/2-one.getMeasuredHeight());
             }
         }


         ;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMeasure","*****************");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View child;
            child=getChildAt(i);
            child.setId(1000+i);
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        if(StatusOpen)
          setMeasuredDimension(400,400);
        else{
            setMeasuredDimension(80,400);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,getMeasuredHeight()/2,getMeasuredWidth(),getMeasuredHeight()/2,new Paint());
    }
    private void drawPartCircle(){

    }

    public void setAnimation(final View v, int EndX, int EndY){
        AnimationSet animset = new AnimationSet(true);
        animset.setInterpolator(new AccelerateDecelerateInterpolator());
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(Durationg);
        rotateAnimation.setFillAfter(true);
        Animation translateAnimation = null;
        if (true) {
            translateAnimation = new TranslateAnimation(0f, EndX, 0f, EndY);
            v.setClickable(true);
           v.setFocusable(true);
        }
//        } else if (mCurrentMenuStatus == Status.CLOSE) {
//            translateAnimation = new TranslateAnimation(viewX, 0f, viewY, 0f);
//            childView.setClickable(false);
//            childView.setFocusable(false);
//        }
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
//                if (mCurrentMenuStatus == Status.CLOSE)
//                    childView.setVisibility(View.GONE);
                Log.e("vid",v.getId()+"");
                switch (v.getId()){
                    case Id_Main:
                       // v.layout(v.getLeft(),0,v.getLeft()+v.getMeasuredWidth(),v.getMeasuredHeight());
                        break;
                    case Id_One:
                        v.layout(v.getLeft(),0,v.getLeft()+v.getMeasuredWidth(),v.getMeasuredHeight());
                        break;
                    case Id_two:
                        v.layout(getMeasuredWidth()-v.getMeasuredWidth(),getMeasuredHeight()/2-v.getMeasuredHeight()/2,getMeasuredWidth(),getMeasuredHeight()/2+v.getMeasuredHeight()/2);
                        break;
                    case Id_three:
                        v.layout(v.getLeft(),getMeasuredHeight()-v.getMeasuredHeight(),v.getRight(),getMeasuredHeight());
                        break;
                }

                  v.setOnClickListener(new OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Log.e("aiyouwei","***************"+v.getId());
                          switch (v.getId()){
                              case Id_Main:
                                  // v.layout(v.getLeft(),0,v.getLeft()+v.getMeasuredWidth(),v.getMeasuredHeight());
                                  break;
                              case Id_One:

                                  break;
                              case Id_two:

                                  break;
                              case Id_three:

                                  break;
                          }
                      }
                  });
            }
        });

        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(Durationg);

        //animset.addAnimation(rotateAnimation);
        animset.addAnimation(translateAnimation);
        v.startAnimation(animset);
        v.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
