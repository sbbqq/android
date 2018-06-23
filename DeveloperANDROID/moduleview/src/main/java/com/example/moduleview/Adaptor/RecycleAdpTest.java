package com.example.moduleview.Adaptor;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.moduleview.R;

import java.util.ArrayList;


/**
 * Created by wqq on 18-6-21.
 */

public class RecycleAdpTest extends RecyclerView.Adapter<RecycleAdpTest.MyViewHolder> {
    ArrayList<String>arrayList;
    Context context;
    @Override
    public RecycleAdpTest.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolderchild myViewHolder=new MyViewHolderchild(LayoutInflater.from(context).inflate(R.layout.layout_item_recycle,null,false));
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
          holder.setUpView(position);
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public abstract  class MyViewHolder<T> extends RecyclerView.ViewHolder
    {
        View itemView;
        Button tv;

        public MyViewHolder(View view)
        {
            super(view);
            this.itemView=view;
            view.setFocusable(true);
            tv = (Button) view.findViewById(R.id.txt_recycle_item_test);
        }
        public abstract void setUpView(int position);
    }

    public class MyViewHolderchild extends MyViewHolder{

        public MyViewHolderchild(View view) {
            super(view);
        }

        @Override
        public void setUpView(final int position) {
            tv.setText(arrayList.get(position));
         itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 if(hasFocus){
                     Log.e("getfocus",position+"");
                     focusStatus(v);
                 }
                 else{
                     Log.e("leavefocus",position+"");
                     normalStatus(v);
                 }
             }
         });

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.e("click",position+"");
             }
         });
        }



        private void focusStatus(View itemView){
            itemView.setBackgroundColor(Color.RED);
            if(itemView!=null){
                if(Build.VERSION.SDK_INT>=21){
//                ViewCompat.animate(itemView).scaleX(1.20f).scaleY(1.20f).translationZ(1).start();

                    ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.0f,1.0f);
                    ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.0f,1.0f);
                    ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",0f,1.0f);
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.setDuration(200);
                    animatorSet.start();
                }else{
//                ViewCompat.animate(itemView).scaleX(1.20f).scaleY(1.20f).start();
                    ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.0f,1.0f);
                    ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.0f,1.0f);
                    ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",0f,1.0f);
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.setDuration(200);
                    animatorSet.start();

                    ViewGroup parent= (ViewGroup) itemView.getParent();
                    parent.requestLayout();
                    parent.invalidate();
                }
            }

        }

        private void normalStatus(View itemView){
            itemView.setBackgroundColor(Color.WHITE);
            if(itemView!=null){
                if(Build.VERSION.SDK_INT>=21){
//                ViewCompat.animate(itemView).scaleX(1.0f).scaleY(1.0f).translationZ(0f).start();

                    ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.0f,1.0f);
                    ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.0f,1.0f);
                    ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",1.0f,0f);
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.start();
                }else{
//                ViewCompat.animate(itemView).scaleX(1.0f).scaleY(1.0f).translationZ(0f).start();
                    ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1f,1.0f);
                    ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1f,1.0f);
                    ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",1.0f,0f);
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.start();

                    ViewGroup parent= (ViewGroup) itemView.getParent();
                    parent.requestLayout();
                    parent.invalidate();
                }

            }
        }
    }




    public RecycleAdpTest(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public RecycleAdpTest(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



}

