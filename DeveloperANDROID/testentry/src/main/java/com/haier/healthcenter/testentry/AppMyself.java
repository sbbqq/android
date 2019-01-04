package com.haier.healthcenter.testentry;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by wqq on 18-12-13.
 */

public class AppMyself extends Application {
    public AppMyself() {
        super();
        Log.e("app-construct","*****************");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("app-oncreate","*****************");
        // 在调用init方法前设置自定义更新对话框布局


//  调用init方法
        Bugly.init(getApplicationContext(),"75869cccb5", false);
        CrashReport.initCrashReport(getApplicationContext(), "75869cccb5", true);


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }
}
