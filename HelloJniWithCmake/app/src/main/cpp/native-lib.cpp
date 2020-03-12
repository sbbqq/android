#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_alone_1nine_1sword_hellojniwithcmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, 一帘幽梦";
    return env->NewStringUTF(hello.c_str());
}
