package com.example.retrofit.util;

import android.util.Log;

/**
 * @Description 字节工具包
 * @author 01478664
 * @createdate 2017年11月23日 上午10:48:54
 */
public class BytesUtil {

    /**
     * 字节截取函数
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        if ((count > 0) && (src.length >= (begin + count))) {
            byte[] bs = new byte[count];
            for (int i = begin; i < begin + count; i++) {
                bs[i - begin] = src[i];
            }
            return bs;
        } else {
            return null;
        }
    }

    /**
     * 解析MAC串为 byte[]
     */
    public static byte[] getMacBytes(String mac) {
        byte[] macBytes = new byte[6];
        String[] strArr = mac.split(":");

        for (int i = 0; i < strArr.length; i++) {
            int value = Integer.parseInt(strArr[i], 16);
            macBytes[i] = (byte)value;
        }
        return macBytes;
    }

    /**
     * 字节转换为字符串的转换工具函数
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 字节转换为字符串的转换工具函数
     */
    public static String bytesToPrintString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                hv = "0" + hv;
            }
            stringBuilder.append("0x" + hv);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    /**
     * 解析字节转换为整形
     */
    public static int bytesToInt(byte[] src) {
        String tmp = BytesUtil.bytesToHexString(src);


        int sum = 0;
        try {
            sum = Integer.parseInt(tmp, 16);
            Log.e("tmp",tmp+"tmpToInt:--->"+sum);
        } catch (NumberFormatException e) {

        }
        return sum;
    }

    public static byte getDatasXor(byte[] src, int istart, int iend) {
        byte dataCheckByte = src[istart];
        for (byte i = (byte)(istart + 1); i <= iend; i++) {
            dataCheckByte ^= src[i];
        }
        return dataCheckByte;
    }

    public static byte[] shortToByteArray(short s) {
        byte[] bRet = new byte[2];
        bRet[0] = (byte)(s >> 8);
        bRet[1] = (byte)(s >> 0);

        return bRet;
    }

    public static void putInt(byte[] bb, int x, int index) {
        bb[index + 0] = (byte)(x >> 24);
        bb[index + 1] = (byte)(x >> 16);
        bb[index + 2] = (byte)(x >> 8);
        bb[index + 3] = (byte)(x >> 0);
    }

    public static void putShort(byte b[], short s, int index) {
        b[index + 0] = (byte)(s >> 8);
        b[index + 1] = (byte)(s >> 0);
    }

    public static String byteToBit(byte b) {
        return "" + (byte)((b >> 7) & 0x1) + (byte)((b >> 6) & 0x1)
                + (byte)((b >> 5) & 0x1) + (byte)((b >> 4) & 0x1)
                + (byte)((b >> 3) & 0x1) + (byte)((b >> 2) & 0x1)
                + (byte)((b >> 1) & 0x1) + (byte)((b >> 0) & 0x1);
    }

    public static byte getCmdId(byte bProperty) {
        String sProperty = BytesUtil.byteToBit(bProperty);
        return Byte.parseByte(sProperty.substring(7, 8));
    }

    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 0; i <= 7; i++) {
            array[i] = (byte)(b & 1);
            b = (byte)(b >> 1);
        }
        return array;
    }

}
