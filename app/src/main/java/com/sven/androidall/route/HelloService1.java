package com.sven.androidall.route;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/route/hello1")
public class HelloService1 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello2 " + name;
    }

    @Override
    public void init(Context context) {

    }
}
