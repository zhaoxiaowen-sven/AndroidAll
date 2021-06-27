package com.sven.androidall.view.dispatch.out;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 外部拦截处理
 */
public class MyEventOutLayout extends LinearLayout {
    private static final String TAG = "MyEventOutLayout";
    private int mLastXIntercept;
    private int mLastYIntercept;
    public MyEventOutLayout(Context context) {
        this(context, null);
    }

    public MyEventOutLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyEventOutLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "父view， onInterceptTouchEvent" + event.getAction());
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;

            case MotionEvent.ACTION_MOVE:
                // 父容器需要当前点击事件
                if (Math.abs(x - mLastXIntercept) > Math.abs(y - mLastYIntercept)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;

            default:
                break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "父view中，onTouchEvent = " + event.getAction());
        return super.onTouchEvent(event);
    }
}
