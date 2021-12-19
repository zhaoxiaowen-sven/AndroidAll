package com.sven.androidall;

import static com.sven.androidall.contants.RouteConstants.COROUTINE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sven.androidall.coroutine.CoroutineActivity;

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
//                ARouter.getInstance().build(LIFE_CYCLE).navigation();
//                ARouter.getInstance().build(AOP).navigation();
//                ARouter.getInstance().build(ASM).navigation();
//                ARouter.getInstance().build(COROUTINE).navigation();

                Intent intent = new Intent(LaunchActivity.this, CoroutineActivity.class);
                startActivity(intent);

            }
        });

    }
}
