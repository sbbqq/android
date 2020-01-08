package com.example.momeryleak;

import android.content.Context;
import android.util.Log;

/**
 * Created by alone-nine-sword on 20-1-3.
 */

    public class SingletonManager {

        private static SingletonManager singleton;
        private Context context;

        private SingletonManager(Context context) {
            this.context = context;
        }

        public synchronized static SingletonManager getInstance(Context context) {
            if (singleton == null) {
                Log.e("getSington","******null*****");
                singleton = new SingletonManager(context);
            }else{
                Log.e("getSington","******not null*****");
            }
            return singleton;
        }
}
