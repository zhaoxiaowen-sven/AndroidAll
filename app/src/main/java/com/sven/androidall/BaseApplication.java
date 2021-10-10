package com.sven.androidall;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zhaoxiaowen on 2021/2/27
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        ARouter.init(this);
    }
}
