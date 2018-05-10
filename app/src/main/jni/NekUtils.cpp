//
// Created by 苏虎 on 2018/1/11.
//

#include "com_suhu_android_ndk_NdkUtils.h"
NIEXPORT jstring JNICALL Java_com_suhu_android_ndk_NdkUtils_getString
  (JNIEnv * env, jclass class1){

    return env->NewStringUTF("form c");
}