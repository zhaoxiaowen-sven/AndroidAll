package com.sven.androidall.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sven.androidall.R;
import com.sven.androidall.contants.RouteConstants;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zhaoxiaowen on 2021/2/27
 */
@Route(path = RouteConstants.HANDLER_PAGE)
public class HandlerActivity extends Activity {
    private static final String TAG = "HandlerActivityImpl";

    private Button mButton;
    private Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mButton = findViewById(R.id.handler_button1);
        mButton2 = findViewById(R.id.handler_button2);

        HandlerThread handlerThread = new HandlerThread("t");
        handlerThread.start();

        final Handler threadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handle msg" + msg.arg1);
            }
        };

        final Looper threadHandlerLooper = threadHandler.getLooper();
        threadHandlerLooper.getQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.i(TAG, "queueIdle");
                return true;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "post ");
                    }
                });
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发第一个消息
                Message msg = Message.obtain();
                msg.arg1 = 101;
                threadHandler.sendMessage(msg);

                int token = postSyncBarrier(threadHandlerLooper);
                Log.i(TAG, "token " + token);
                // 发第二个消息
                Message msg2 = Message.obtain();
                msg2.arg1 = 102;
                threadHandler.sendMessage(msg2);


                // 发第三个消息
                Message msg3 = Message.obtain();
                msg3.arg1 = 103;
                msg3.setAsynchronous(true);
                threadHandler.sendMessage(msg3);

//                removeSyncBarrier(threadHandlerLooper, token);
            }
        });
    }

    public int postSyncBarrier(Looper looper) {
        try {
            Method method = MessageQueue.class.getDeclaredMethod("postSyncBarrier");
            return (int) method.invoke(looper.getQueue());
        } catch (Exception e) {
            Log.e(TAG, "postSyncBarrier ", e);
            return -1;
        }
    }

    public void removeSyncBarrier(Looper looper, int token) {
        try {
            Method method = MessageQueue.class.getDeclaredMethod("removeSyncBarrier", int.class);
            method.invoke(looper.getQueue(), token);
        } catch (Exception e) {
            Log.e(TAG, "removeSyncBarrier ", e);
        }
    }
}