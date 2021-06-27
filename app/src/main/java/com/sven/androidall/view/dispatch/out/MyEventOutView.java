package com.sven.androidall.view.dispatch.out;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

public class MyEventOutView extends androidx.appcompat.widget.AppCompatTextView {

    private static final String TAG = "MyEventOutView";
    private int mLastX;
    private int mLastY;

    public MyEventOutView(Context context) {
        this(context, null);
    }

    public MyEventOutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyEventOutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "子view中，dispatchTouchEvent = " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res = super.onTouchEvent(event);
        Log.d(TAG, "子view中，onTouchEvent = " + event.getAction() + ", res = " + res);
        return res;
    }
}
