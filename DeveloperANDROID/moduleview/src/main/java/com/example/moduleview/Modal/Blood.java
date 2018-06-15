package com.example.moduleview.Modal;

/**
 * Created by wqq on 18-6-13.
 */

public class Blood {
    int Highvalue;
    int Lowvalue;

    public int getHighvalue() {
        return Highvalue;
    }

    public void setHighvalue(int highvalue) {
        Highvalue = highvalue;
    }

    public int getLowvalue() {
        return Lowvalue;
    }

    public void setLowvalue(int lowvalue) {
        Lowvalue = lowvalue;
    }

    public Blood(int highvalue, int lowvalue) {
        Highvalue = highvalue;
        Lowvalue = lowvalue;
    }
}
