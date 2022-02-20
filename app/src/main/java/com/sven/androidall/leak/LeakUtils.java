package com.sven.androidall.leak;

import android.content.Context;

public class LeakUtils {
    private static LeakUtils leakUtils;
    private final Context context;

    private LeakUtils(Context context) {
        this.context = context;
    }

    public static LeakUtils getInstance(Context context) {
        if (leakUtils == null) {
            synchronized (LeakUtils.class) {
                if (leakUtils == null) {
                    leakUtils = new LeakUtils(context);
                }
            }
        }
        return leakUtils;
    }
}
