package com.suhu.android.ndk;

/**
 * @author: 苏虎
 * @email: suhu0824@gmail.com
 * @data: 2018/1/11 21:27
 * @description:
 */

public class NdkUtils {

    static {
        System.loadLibrary("hello");
    }

    public static native String getString();

}
