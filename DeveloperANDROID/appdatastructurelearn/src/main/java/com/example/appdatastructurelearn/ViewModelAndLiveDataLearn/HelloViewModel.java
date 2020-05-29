package com.example.appdatastructurelearn.ViewModelAndLiveDataLearn;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.sql.Time;
import java.util.Timer;

/**
 * Created by alone-nine-sword on 20-5-28.
 */

public class HelloViewModel extends ViewModel {
    MutableLiveData<String> citys;
    private String TemValue="0000";

    public void setCitys(MutableLiveData<String> citys) {
        this.citys = citys;
    }

    public MutableLiveData<String> getCitys() {
        if(citys==null)
        {
            citys=new MutableLiveData<>();
        }
        //moniDataChange();

        return citys;
    }

    public void Loadata(){



                   // citys.setValue(TemValue);

                }





    }


