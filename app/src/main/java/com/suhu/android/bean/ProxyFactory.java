package com.suhu.android.bean;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: tiger
 * @email: suhu0824@gmail.com
 * @data: 2018/2/5 22:33
 * @description:
 */

public class ProxyFactory {

    private static final String TAG = "ProxyFactory";
    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.i(TAG,"动态代理开始");
                Object returnValue = method.invoke(object,args);
                Log.i(TAG,"动态代理结束");
                return returnValue;
            }
        });
    }


}
