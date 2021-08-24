package com.sven.androidall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import static com.sven.androidall.contants.RouteConstants.EVENT_PAGE;
import static com.sven.androidall.contants.RouteConstants.LIFE_CYCLE;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";
    Button mButton;
    TextView mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mButton = findViewById(R.id.toHandler);
        mEditText = findViewById(R.id.edit1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build(HANDLER_PAGE).navigation();
//                ARouter.getInstance().build(EVENT_PAGE).navigation();
                ARouter.getInstance().build(LIFE_CYCLE).navigation();
            }
        });

    }
}
