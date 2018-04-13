package com.example.canveselement.Canves

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by alone-nine-sword on 18-4-13.
 */
abstract class DrawText: View {
     //var paint:Paint();
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var canves=canvas;
       // canves.drawText("hello",0,0,100f,100f,Paint());


    }
    fun iniData(){
         // paint=Paint();
        //paint.style=Paint.Style.FILL;
       // paint.color= Color.RED;
    }
    constructor(context: Context?,attrs: AttributeSet?) :super(context,attrs){

        iniData()
    }




}