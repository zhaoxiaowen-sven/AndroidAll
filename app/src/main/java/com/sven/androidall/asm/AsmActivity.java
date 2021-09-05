package com.sven.androidall.asm;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sven.androidall.R;
import com.sven.androidall.contants.RouteConstants;

@Route(path = RouteConstants.ASM)
public class AsmActivity extends Activity {
    private static final String TAG = "AsmActivity";
    private Fragment1 fragment = new Fragment1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.获取fragmentManager
        FragmentManager fragmentManager = getFragmentManager();
        // 2.获取FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 3.创建需要的Fragment
        // Fragment fragment = new Fragment1();
        // 4.动态添加fragment
        // 将创建的fragment添加到Activity布局文件中定义的占位符中（FrameLayout）
        fragmentTransaction.add(R.id.fragment_container, fragment, "fragment1").commit();
    }
}
