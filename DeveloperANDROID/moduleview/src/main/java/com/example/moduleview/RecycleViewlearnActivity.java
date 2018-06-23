package com.example.moduleview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moduleview.Adaptor.RecycleAdpTest;

import java.util.ArrayList;

public class RecycleViewlearnActivity extends Activity {
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
        recyclerView.setAdapter(adpTest);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setAdapter(adpTest);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,3));

    }


}
