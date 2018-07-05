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

import com.example.moduleview.Interface.InPosition;
import com.example.moduleview.Modal.Family;
import com.example.moduleview.R;
import com.example.moduleview.developerandroid.CustomView.FamailyGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wqq on 18-6-21.
 */

public class RecycleAdpFamily extends RecyclerView.Adapter<RecycleAdpFamily.MyViewHolder> {

    public  List<Family>families;
    Context context;
    InPosition inPosition;
    int lastposition=0;
    @Override
    public RecycleAdpFamily.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("onCreateVIEWHOLDER","*********************"+viewType);
        View view=LayoutInflater.from(context).inflate(R.layout.layout_hehe,null,false);


         FamailyGroup famailyGroup=(FamailyGroup)view.findViewById(R.id.familygroup);
         famailyGroup.setRecycleAdpFamily(this);
        MyViewHolderchild myViewHolder=new MyViewHolderchild(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("ONbINDVIEWHOLDER","**************************"+position);
          holder.setUpView(position);
    }




    @Override
    public int getItemCount() {
        return families.size();
    }




    public abstract  class MyViewHolder<T> extends RecyclerView.ViewHolder
    {
        View itemView;
        TextView tv;
        FamailyGroup famailyGroup;
        TextView tevblood,txvweight,txvsugar,txvt;
        TextView txvalue;

        public MyViewHolder(View view)
        {
            super(view);
            this.itemView=view;
            view.setFocusable(true);
            tv = (TextView) view.findViewById(R.id.tx_attr_whom);
            famailyGroup=(FamailyGroup)view.findViewById(R.id.familygroup);
            Log.e("familyid",famailyGroup.getId()+"");

            //txvalue=(TextView)famailyGroup.findViewById(R.id.tx_attr_value);

        }
        public abstract void setUpView(int position);
    }

    public class MyViewHolderchild extends MyViewHolder{


        public MyViewHolderchild(View view) {
            super(view);
            Log.e("myHodlchidl init","********************");
        }

        @Override
        public void setUpView(final int position) {
            Log.e("setUpview",position+"");

           famailyGroup.setFamily(families.get(position));

           //famailyGroup.setFamily(families.get(position));
            tevblood=(TextView)famailyGroup.findViewById(R.id.tx_attr_value_blood);
            txvsugar=(TextView)famailyGroup.findViewById(R.id.tx_attr_value_bloodsugar);
            txvweight=(TextView)famailyGroup.findViewById(R.id.tx_attr_value_weight);
            txvt=(TextView)famailyGroup.findViewById(R.id.tx_attr_value_tempature);
            tv = (TextView) famailyGroup.findViewById(R.id.tx_attr_whom);
            tv.setText(families.get(position).getWhom());
           // txvalue.setText(families.get(position));
            if(tevblood!=null){
              tevblood.setText(families.get(position).getAttrByFlag(0).getValue());
                Log.e("not-null","********************"+position);
            }
            else{
                Log.e("null","********************"+position);
            }
            if(txvsugar!=null){
                tevblood.setText(families.get(position).getAttrByFlag(1).getValue());
            }
            else{
               // Log.e("null","*********************8");
            }
            if(txvweight!=null){
              tevblood.setText(families.get(position).getAttrByFlag(2).getValue());
            }
            else{
             //   Log.e("null","*********************8");
            }
            if(txvt!=null){
                tevblood.setText(families.get(position).getAttrByFlag(3).getValue());
            }
            else{
               // Log.e("null","*********************8");
            }
         itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 if(hasFocus){
                     Log.e("getfocus",position+"");
                     focusStatus(v);
                     inPosition.transpos(position,lastposition);

                 }
                 else{
                     Log.e("leavefocus",position+"");
                     normalStatus(v);
                     lastposition=position;
                 }
             }
         });

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.e("click",position+"");
             }
         });
         //inPosition.dataNotify();
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



    public RecycleAdpFamily(List<Family> families, Context context) {
        Log.e("adpter-construct","*****************");
        this.families = families;
        this.context = context;
    }


    public InPosition getInPosition() {
        return inPosition;
    }

    public void setInPosition(InPosition inPosition) {
        this.inPosition = inPosition;
    }
}

