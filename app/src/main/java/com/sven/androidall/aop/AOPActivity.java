package com.sven.androidall.aop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.apt_annotation.BindView;
import com.example.apt_api.launcher.AutoBind;
import com.sven.androidall.R;
import com.sven.androidall.contants.RouteConstants;

@Route(path = RouteConstants.AOP)
public class AOPActivity extends Activity {
    @BindView(value = R.id.test_textview)
    public TextView testTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        AutoBind.getInstance().inject(this);
        testTextView.setText("APT 测试");
    }
}
