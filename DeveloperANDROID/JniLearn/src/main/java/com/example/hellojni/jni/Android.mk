LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := HelloJni-jni

LOCAL_SRC_FILES := HelloJni.c

include $(BUILD_SHARED_LIBRARY)