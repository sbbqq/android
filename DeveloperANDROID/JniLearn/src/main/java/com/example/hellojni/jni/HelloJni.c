//
// Created by alone-nine-sword on 20-3-4.
//


#include "com_example_hellojni_Ndk_NdkTools.h"

JNIEXPORT jstring JNICALL Java_com_example_hellojni_Ndk_NdkTools_getStringFromNDK
  (JNIEnv *env, jobject obj){

     return (*env)->NewStringUTF(env,"Hellow World，this content is from c");
  }