package com.sven.androidall.asm;

import android.app.Fragment;
import android.util.Log;



public class FragmentAsmInjector {
    private static final String TAG = "FragmentAsmInjector";

    public static void afterFragmentResume(Fragment fragment) {
        Log.e(TAG, "afterFragmentResume: fragment is " + fragment);
    }
}
