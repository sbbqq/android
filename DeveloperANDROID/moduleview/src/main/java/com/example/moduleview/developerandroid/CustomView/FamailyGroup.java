package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moduleview.Adaptor.RecycleAdpFamily;
import com.example.moduleview.Modal.Family;
import com.example.moduleview.R;

import java.util.List;

/**
 * Created by alone-nine-sword on 18-6-27.
 */

public class FamailyGroup extends ViewGroup {
    Family family;
    Context context;
    RelativeLayout relativeLayoutHead;
    ImageView imageViewbody;
    int width,height;
    RecycleAdpFamily recycleAdpFamily;

    Paint paintBg;
    Path pathbg;
    boolean IsFocus=false;
    public FamailyGroup(Context context) {
        super(context);
        this.context=context;
        inidata();
        //ini();
    }

    public FamailyGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
      inidata();
       //ini();
    }

    public FamailyGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        inidata();

       // ini();
    }


    public Family getFamily() {
        return family;

    }

    public void setFamily(Family family) {
        this.family = family;
        //ini();
        iniView();

    }
    public void inidata(){
        setWillNotDraw(false);

        paintBg=new Paint();
        paintBg.setStrokeWidth(10);
        paintBg.setAntiAlias(true);
        paintBg.setStyle(Paint.Style.STROKE);
        paintBg.setColor(Color.WHITE);
        pathbg=new Path();
    }
   public void ini(){

        Log.e("init","***************************");
       relativeLayoutHead=(RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_item_head,null,false);
      relativeLayoutHead.setLayoutParams(new LayoutParams(640,160));
       imageViewbody=new ImageView(context);
       imageViewbody.setImageResource(R.drawable.test);
       imageViewbody.setId(R.id.fam_pic);
       imageViewbody.setLayoutParams(new LayoutParams(160,120));
       addView(relativeLayoutHead);
       addView(imageViewbody);

       for(int i=0;i<family.getAttributes().size();i++){
           Log.e("addattr",i+"type:"+family.getAttributes().get(i).getFlag());
           Log.e("weight","weight"+width);
           switch (family.getAttributes().get(i).getFlag()){////0 血压 1 血糖 2 体温 3 体重
               case 0:
                   LinearLayout linearLayoutattr=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_item_value_blood,null,false);
                   // TextView textValue=(TextView)linearLayoutattr.findViewById(R.id.tx_attr_value_blood);
                   // textValue.setText(family.getAttributes().get(i).getValue()+"");
                   linearLayoutattr.setLayoutParams(new LayoutParams(640/3,840/4));
                   addView(linearLayoutattr);
                   break;
               case 1:
                   LinearLayout linearLayoutattrsugar=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_item_value_bloodsugar,null,false);
                   // TextView textValuesugar=(TextView)linearLayoutattrsugar.findViewById(R.id.tx_attr_value_bloodsugar);
                   //textValuesugar.setText(family.getAttributes().get(i).getValue()+"");
                   linearLayoutattrsugar.setLayoutParams(new LayoutParams(640/3,840/4));
                   addView(linearLayoutattrsugar);
                   break;
               case 2:
                   LinearLayout linearLayoutattrtempature=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_item_value_tempature,null,false);
                   //TextView textValuetempatur=(TextView)linearLayoutattrtempature.findViewById(R.id.tx_attr_value_tempature);
                   //textValuetempatur.setText(family.getAttributes().get(i).getValue()+"");
                   linearLayoutattrtempature.setLayoutParams(new LayoutParams(640/3,840/4));
                   addView( linearLayoutattrtempature);
                   break;
               case 3:
                   LinearLayout linearLayoutattrweight=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_item_value_weight,null,false);
                   // TextView textValueweight=(TextView)linearLayoutattrweight.findViewById(R.id.tx_attr_value_blood);
                   // textValueweight.setText(family.getAttributes().get(i).getValue()+"");
                   linearLayoutattrweight.setLayoutParams(new LayoutParams(640/3,840/4));
                   addView(linearLayoutattrweight);
                   break;
           }

           // requestLayout();


           ;
       }
   }
    public void iniView(){
          removeAllViews();
        Log.e("0childcount",getChildCount()+"");
         ini();



        Log.e("1childcount",getChildCount()+"");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
         for(int i=0;i<getChildCount();i++){
             if(i==0){
                 getChildAt(i).layout(0,0,width,height/4);
                 Log.e("witch:",i+"<--:i"+"top:"+0+"bot:"+0+height/4+"left:"+0+"right:"+width);
             }
             else if(i==1){
                 getChildAt(i).layout(width/3,height/4,2*width/3,height);
             }
             else if(i==2||i==3||i==4){
                 int left=0;int right=width/3;
                 int top=height/4+(i-2)*height/4;
                 int bot=top+height/4;
                getChildAt(i).layout(left,top,right,bot);
                // getChildAt(i).layout(0,0,width,height/4);
                 Log.e("witch:",i+"<--:i"+"top:"+top+"bot:"+bot+"left:"+left+"right:"+right);
             }
             else if(i==5||i==6||i==7){
                 int left=width*2/3;int right=width;
                 int top=height/4+(i-5)*height/4;
                 int bot=top+getChildAt(i).getMeasuredHeight();
                 getChildAt(i).layout(left,top,right,bot);
             }
         }
         Log.e("sizechild",getChildCount()+"");
       // recycleAdpFamily.notifyDataSetChanged();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("notifyied","*************************");
        drawBg(canvas,IsFocus);
       // recycleAdpFamily.notifyDataSetChanged();
    }


    public void drawBg(Canvas canvas,boolean isFocus){
        pathbg.reset();
             if(isFocus){
                 pathbg.addRoundRect(new RectF(0,0,width,height),new float[]{20.0f,20.0f,20.0f,20.0f,20.0f,20.0f,20.0f,20.0f}, Path.Direction.CCW);
             }else{
             pathbg.reset();
             Log.e("reset","******************leave");
             }
             canvas.drawPath(pathbg,paintBg);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(640,840);
         for(int i=0;i<getChildCount();i++){
             measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);

         }


        width=getMeasuredWidth();
        height=getMeasuredHeight();
        Log.e("widthonMeasure",width+"");
        //ini();


    }

    public RecycleAdpFamily getRecycleAdpFamily() {
        return recycleAdpFamily;
    }

    public void setRecycleAdpFamily(RecycleAdpFamily recycleAdpFamily) {
        this.recycleAdpFamily = recycleAdpFamily;
    }


    public boolean isFocus() {
        return IsFocus;
    }

    public void setFocus(boolean focus) {
        IsFocus = focus;
    }
}
