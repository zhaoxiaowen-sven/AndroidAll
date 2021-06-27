package com.sven.androidall.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sven.androidall.R;

import static com.sven.androidall.contants.RouteConstants.EVENT_PAGE;

@Route(path = EVENT_PAGE)
public class ViewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event);
        setContentView(R.layout.activity_outevent);
    }

}
