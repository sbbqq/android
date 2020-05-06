// HelloaidlInterface.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements
import com.example.aidlserver.City;
interface HelloaidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            String getName();
            int Add(int one,int two);
            int addCity(inout City c );
            int getNumberOfCity();

}
