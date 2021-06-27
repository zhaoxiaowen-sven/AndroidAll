package com.sven.androidall.view.dispatch.inner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 内部拦截
 */
public class MyEventLayout extends LinearLayout {
    private static final String TAG = "MyEventLayout";

    public MyEventLayout(Context context) {
        this(context, null);
    }

    public MyEventLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyEventLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                intercepted = true;
                break;
        }
        Log.d(TAG, "父view中， onInterceptTouchEvent = " + action + ", intercepted = " + intercepted);
        return intercepted;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "父view中，dispatchTouchEvent = " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "父view中，onTouchEvent = " + event.getAction());
        return super.onTouchEvent(event);
    }
}
