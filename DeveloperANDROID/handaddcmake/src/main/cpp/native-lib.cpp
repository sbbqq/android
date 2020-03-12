//
// Created by alone-nine-sword on 20-3-12.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_handaddcmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, 一帘幽梦,也来风雨声，";
    return env->NewStringUTF(hello.c_str());
}
