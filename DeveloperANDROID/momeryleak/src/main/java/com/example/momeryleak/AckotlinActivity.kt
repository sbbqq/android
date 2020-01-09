package com.example.momeryleak

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AckotlinActivity : AppCompatActivity() {
       var count: Int =10;
        var lan="hehe";
        var fz="4.0";
         var ob=LifecycleTry()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ackotlin)


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
