package com.example.momeryleak.DaggerDemo.KotlinLearn

import android.util.Log

/**
 * Created by alone-nine-sword on 20-1-9.
 */
class KotlinDemoClass :InterKitHello {
    var Hellosay ="hello 2"
    var suibianname=2;

    constructor(Hellosay: String) {
        this.Hellosay = Hellosay
    }

    override fun HelloKotlin(mess: String) {
        Log.e("HelloKotlin","****"+this.Hellosay)

    }

    fun getHellosaypi():String{
        return Hellosay;
    }


    fun suibian():Int {
        return suibianname;
    }

}