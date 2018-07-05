package com.example.moduleview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.moduleview.Adaptor.RecycleAdpFamily;
import com.example.moduleview.Adaptor.RecycleAdpTest;
import com.example.moduleview.Interface.InPosition;
import com.example.moduleview.Modal.Attribute;
import com.example.moduleview.Modal.Family;

import java.util.ArrayList;

public class RecycleViewlearnActivity extends Activity implements InPosition {
RecyclerView recyclerView,recyclerView2;
ArrayList<Family>arrayList;
RecycleAdpFamily adpTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_viewlearn);

        recyclerView=(RecyclerView)findViewById(R.id.recycleviewtest);
       // recyclerView2=(RecyclerView)findViewById(R.id.recycleviewtest2);
        inidata();
    }
    public void inidata(){
        arrayList=new ArrayList<>();
        for(int i=0;i<8;i++){
            Family family=new Family(i+"hhhfdsf",1);
             for(int j=0;j<4;j++){
             Attribute attribute=new Attribute(j,"ij:"+i+j);
             family.getAttributes().add(attribute);

             }
            arrayList.add(family);
        }
        adpTest=new RecycleAdpFamily(arrayList,this);
        adpTest.setInPosition(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView2.setAdapter(adpTest);
//        recyclerView2.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(adpTest);
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

    @Override
    public void dataNotify() {
        adpTest.notifyDataSetChanged();
    }
}
