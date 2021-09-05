package com.sven.androidall;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sven.androidall.asm.InjectTest;

import static com.sven.androidall.contants.RouteConstants.AOP;

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

                InjectTest injectTest=new InjectTest();
                injectTest.sayHello();
            }
        });

    }
}
