package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
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
    public FamailyGroup(Context context) {
        super(context);
        this.context=context;
        ini();
    }

    public FamailyGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
       ini();
    }

    public FamailyGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        ini();
    }


    public Family getFamily() {
        return family;

    }

    public void setFamily(Family family) {
        this.family = family;
        //ini();
        iniView();

    }
   public void ini(){
       relativeLayoutHead=(RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_item_head,null,false);
      relativeLayoutHead.setLayoutParams(new LayoutParams(640,160));
       imageViewbody=new ImageView(context);
       imageViewbody.setImageResource(R.drawable.test);
       imageViewbody.setId(R.id.fam_pic);
       imageViewbody.setLayoutParams(new LayoutParams(160,120));
       addView(relativeLayoutHead);
       addView(imageViewbody);
       //requestLayout();
       iniView();

   }
    private void iniView(){

        for(int i=0;i<family.getAttributes().size();i++){
            LinearLayout linearLayoutattr=(LinearLayout)LayoutInflater.from(context).inflate(R.layout.layout_item_value,null,false);
            linearLayoutattr.setLayoutParams(new LayoutParams(width/3,height/4));
            addView(linearLayoutattr);
        }
        //requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
         for(int i=0;i<getChildCount();i++){
             if(i==0){
                 getChildAt(i).layout(0,0,width,height/4);
             }
             else if(i==1){
                 getChildAt(i).layout(width/3,height/4,2*width/3,height);
             }
             else if(i==2||i==3||i==4){
                 int left=0;int right=width/3;
                 int top=height/4+(i-2)*height/4;
                 int bot=top+getChildAt(i).getMeasuredHeight();
                 getChildAt(i).layout(left,right,top,bot);
             }
             else if(i==5||i==6||i==7){
                 int left=width*2/3;int right=width;
                 int top=height/4+(i-5)*height/4;
                 int bot=top+getChildAt(i).getMeasuredHeight();
                 getChildAt(i).layout(left,right,top,bot);
             }
         }
         Log.e("sizechild",getChildCount()+"");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
         for(int i=0;i<getChildCount();i++){
             measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);

         }
         width=getMeasuredWidth();
         height=getMeasuredHeight();
         setMeasuredDimension(640,840);


    }

}
