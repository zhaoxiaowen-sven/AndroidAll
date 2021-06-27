package com.sven.androidall.view.dispatch.inner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

public class MyEventView extends androidx.appcompat.widget.AppCompatTextView {

    private static final String TAG = "MyEventView";
    private int mLastX;
    private int mLastY;

    public MyEventView(Context context) {
        this(context, null);
    }

    public MyEventView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d(TAG, "子view中，dispatchTouchEvent = " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                // 横向交给父view 处理
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    Log.d(TAG, "子view中，requestDisallowInterceptTouchEvent = " + event.getAction());
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res = super.onTouchEvent(event);
        Log.d(TAG, "子view中，onTouchEvent = " + event.getAction() + ", res = " + res);
        return res;
    }
}
