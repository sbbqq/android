package com.example.moduleview.Modal;

/**
 * Created by alone-nine-sword on 18-6-28.
 */

public class Attribute{
    private int flag;//0 血压 1 血糖 2 体温 3 体重
    private String value;//
    private String evalution;//评估

    public Attribute(int flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    public Attribute(int flag, String value, String evalution) {
        this.flag = flag;
        this.value = value;
        this.evalution = evalution;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEvalution() {
        return evalution;
    }

    public void setEvalution(String evalution) {
        this.evalution = evalution;
    }
}
