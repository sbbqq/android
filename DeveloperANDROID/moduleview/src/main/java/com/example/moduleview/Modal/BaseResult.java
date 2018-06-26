package com.example.moduleview.Modal;

import android.content.Intent;

/**
 * Created by alone-nine-sword on 18-6-25.
 */

public class BaseResult {
    private int Y,M,D;
    private int Hour,Minite;
    private String FirstValue="80";
    private String SecondValue="120";

    public BaseResult(int y, int m, int d, int hour, int minite) {
        Y = y;
        M = m;
        D = d;
        Hour = hour;
        Minite = minite;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinite() {
        return Minite;
    }

    public void setMinite(int minite) {
        Minite = minite;
    }

    public String getFirstValue() {
        return FirstValue;
    }

    public void setFirstValue(String firstValue) {
        FirstValue = firstValue;
    }

    public String getSecondValue() {
        return SecondValue;
    }

    public void setSecondValue(String secondValue) {
        SecondValue = secondValue;
    }

    private int getIntValue(String value){
        return Integer.parseInt(value);
    }
     private float getfloatValue(String value){
        return Float.parseFloat(value);
     }
     public void setValue(String f,String s){
         this.FirstValue=f;
         this.SecondValue=s;
     }
}
