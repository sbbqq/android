package com.example.momeryleak.DaggerDemo.KotlinLearn

import android.util.Log

/**
 * Created by alone-nine-sword on 20-1-9.
 */
class KotlinDemoClass :InterKitHello {
    var Hellosay:String ="hello 2"

    constructor(Hellosay: String) {
        this.Hellosay = Hellosay
    }

    override fun HelloKotlin(mess: String) {
        Log.e("HelloKotlin","****"+this.Hellosay)

    }

    fun getHellosay():String{
        return this.Hellosay
    }

}