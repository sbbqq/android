package com.example.moduleview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.moduleview.Adaptor.RecycleAdpTest;
import com.example.moduleview.Interface.InPosition;

import java.util.ArrayList;

public class RecycleViewlearnActivity extends Activity implements InPosition {
RecyclerView recyclerView,recyclerView2;
ArrayList<String>arrayList;
RecycleAdpTest adpTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_viewlearn);

        recyclerView=(RecyclerView)findViewById(R.id.recycleviewtest);
        recyclerView2=(RecyclerView)findViewById(R.id.recycleviewtest2);
        inidata();
    }
    public void inidata(){
        arrayList=new ArrayList<>();
        for(int i=0;i<8;i++){
            arrayList.add(i+"");
        }
        adpTest=new RecycleAdpTest(arrayList,this);
        adpTest.setInPosition(this);
        recyclerView.setAdapter(adpTest);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setAdapter(adpTest);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,3));


    }


    @Override
    public void transpos(int nowpos, int lastpos) {
         if(nowpos>lastpos){
             if(nowpos>1)
                 recyclerView.scrollToPosition(nowpos+1);
         }
         else{
            if(nowpos<arrayList.size()-1)
            {
                recyclerView.scrollToPosition(nowpos-1);
            }
         }
    }
}
