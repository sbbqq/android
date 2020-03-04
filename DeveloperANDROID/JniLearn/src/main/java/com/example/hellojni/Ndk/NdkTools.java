package com.example.hellojni.Ndk;

/**
 * Created by alone-nine-sword on 20-3-4.
 */

public class NdkTools {
    public static native String getStringFromNDK();
    static {
        System.loadLibrary("HelloJni-jni");
    }
}
