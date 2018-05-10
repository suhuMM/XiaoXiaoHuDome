package com.suhu.android.bean;

import java.lang.reflect.Proxy;

/**
 * @author: tiger
 * @email: suhu0824@gmail.com
 * @data: 2018/2/5 22:38
 * @description:
 */

public class TestProxyFactory {
    public static void test(){
        User user = new User();
        User userProxy = (User) new ProxyFactory(user).getProxyInstance();
        userProxy.save();
        userProxy.print();
    }


}
