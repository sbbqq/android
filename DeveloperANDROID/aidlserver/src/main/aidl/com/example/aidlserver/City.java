package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by alone-nine-sword on 20-4-29.
 */

public class City implements Parcelable {
    private String name;
    private int code;

    public City(String name, int code) {
        this.name = name;
        this.code = code;
    }

    protected City(Parcel in) {
        this.code=in.readInt();
        this.name=in.readString();
        Log.e("onstruct-city","**************8");
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        Log.e("writeToParcle","*****************");
        dest.writeInt(this.code);
        dest.writeString(this.name);
    }


    public void readFromParcel(Parcel in){
        Log.e("ReadToParcle","**********name:*******"+this.name+"code:"+this.code);
        this.name=in.readString();
        this.code=in.readInt();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
