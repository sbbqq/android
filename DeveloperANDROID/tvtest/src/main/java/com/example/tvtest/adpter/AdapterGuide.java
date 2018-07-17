package com.example.tvtest.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvtest.R;

import java.util.List;

/**
 * Created by wqq on 18-7-16.
 */

public class AdapterGuide extends RecyclerView.Adapter<AdapterGuide.MyviewHolder> {
    List <String>  guidenames;
    Context context;
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyviewHolderChild(LayoutInflater.from(context).inflate(R.layout.layout_guide,parent,false));
    }



    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        Log.e("pos",position+"");
         holder.setView(position);
    }

    @Override
    public int getItemCount() {
        return guidenames.size();
    }

    public abstract class  MyviewHolder  extends  RecyclerView.ViewHolder{
        TextView txguideName;

        public MyviewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setView(int pos);
    }

    public class MyviewHolderChild  extends MyviewHolder{

        public MyviewHolderChild(View itemView) {
            super(itemView);
            txguideName=(TextView)itemView.findViewById(R.id.tx_guide);
        }

        @Override
        public void setView(int pos) {
            txguideName.setText(guidenames.get(pos));
        }
    }


    public AdapterGuide(List<String> guidenames, Context context) {
        this.guidenames = guidenames;
        this.context = context;
    }
}
