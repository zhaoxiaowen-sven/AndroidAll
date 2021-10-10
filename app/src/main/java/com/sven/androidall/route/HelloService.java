package com.sven.androidall.route;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloService extends IProvider {
    String sayHello(String name);
}
