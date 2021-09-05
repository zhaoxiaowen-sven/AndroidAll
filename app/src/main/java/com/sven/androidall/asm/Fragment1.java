package com.sven.androidall.asm;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sven.androidall.R;

/**
 * Created by SVEN on 2017/6/6.
 */

public class Fragment1 extends Fragment {

    private static final String TAG = "FragmentLife";

    public void printSth() {
        Log.i(TAG, "fragment3 printSth");
    }

    @Override
    public void onAttach(Context context) {
        printLog("onAttach...");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLog("onCreate...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        printLog("onCreateView...");
        // return inflater.inflate(R.layout.fragment1, container, false);
        View v = inflater.inflate(R.layout.fragment1, container, false);
        Button button = (Button) v.findViewById(R.id.jump2fragment2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                jump2fragment2();
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        printLog("onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        printLog("onResume...");
        super.onResume();
    }

    @Override
    public void onPause() {
        printLog("onPause...");
        super.onPause();
    }

    @Override
    public void onStop() {
        printLog("onStop...");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        printLog("onDestroyView...");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        printLog("onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        printLog("onDetach...");
        super.onDetach();
    }

    private void printLog(String msg) {
        Log.i(TAG, msg);
    }
}
