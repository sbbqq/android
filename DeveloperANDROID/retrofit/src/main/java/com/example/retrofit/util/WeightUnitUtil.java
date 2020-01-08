package com.haiyisoft.haieryl.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 体重单位转化相关类
 * @author 01478664
 * @createdate 2017年11月23日 下午4:40:44
 */
public class WeightUnitUtil {

    public static float KG2JIN(float kgWeight) {
        return kgWeight * 2;
    }

    public static String KG2ST(float kgWeight) {
        float f1 = KG2LB(kgWeight);
        int b1 = (int)(f1 / 14);
        float f2 = f1 % 14;
        BigDecimal bigConvert = new BigDecimal(f2);
        f2 = bigConvert.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return b1 + ":" + f2;
    }

    public static String KG2STVer2(float kgWeight) {
        float f1 = kgWeight * 2.2046226f;
        int b1 = (int)(f1 / 14);
        float f2 = f1 % 14;
        BigDecimal bigConvert = new BigDecimal(f2);
        f2 = bigConvert.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return b1 + ":" + f2;
    }

    public static String LB2ST(float lbWeight) {
        float f1 = lbWeight;
        int b1 = (int)(f1 / 14);
        float f2 = f1 % 14;
        BigDecimal bigConvert = new BigDecimal(f2);
        f2 = bigConvert.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return b1 + ":" + f2;
    }

    public static float JIN2KG(float jinWeight) {
        return jinWeight * 0.5f;
    }

    // 威盛康kg转lb公式,适用于MCU的处理方式，所有除法后都要将小数位去掉
    public static float KG2LB_WSK(float kgWeight) {
        float weight1 = kgWeight * 10;
        int iWeight = (int)((weight1 * 11023) / 5000);
        int iWeight1 = (iWeight + 1) / 2;
        float weight2 = ((float)(iWeight1 * 2) / 10f);
        return weight2;
    }

    public static float KG2LB(float kgWeight) {
        // return kgWeight * 2.2046226f;
        float f1 = KG2LB_WSK(kgWeight);
        return f1;

    }

    public static float LB2KG(float lbWeight) {
        return lbWeight * 0.4535924f;
    }

    public static float ST2LB(String stWeight) {
        float fRet = 0;
        String[] s1 = stWeight.split(":");
        if (s1.length == 2) {
            fRet = (Integer.parseInt(s1[0]) * 14 + Float.parseFloat(s1[1]));
        }
        return fRet;
    }

    public static float ST2KG(String stWeight) {
        float fRet = 0;
        String[] s1 = stWeight.split(":");
        if (s1.length == 2) {
            float lbValue = (Integer.parseInt(s1[0]) * 14 + Float.parseFloat(s1[1]));
            fRet = LB2KG(lbValue);
        }
        // DecimalFormat df1 =new DecimalFormat("#0.0");
        // fRet = Float.parseFloat(df1.format(fRet));
        BigDecimal b = new BigDecimal(fRet);
        fRet = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
        return fRet;
    }

    /**
     * @Description 解析体重值
     * @create by 01478664
     * @createdate 2017年11月23日 下午2:01:02
     * @return JSONObject
     * @param highWeight
     * @param lowWeight
     * @param scaleProperty
     * @param isCloudProtocal
     * @return
     */
    public static JSONObject parser(byte highWeight, byte lowWeight, byte scaleProperty,
            boolean isCloudProtocal) {

        JSONObject json = new JSONObject();

        byte[] bWeight1 = new byte[2];
        bWeight1[0] = highWeight;
        bWeight1[1] = lowWeight;

        int iDigit = 1;
        float scaleTmpWeight = BytesUtil.bytesToInt(bWeight1) / 10f;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        BigDecimal bigConvert = new BigDecimal(scaleTmpWeight);
        scaleTmpWeight = bigConvert.setScale(iDigit, BigDecimal.ROUND_HALF_UP)
                .floatValue();

        DecimalFormat df1 = new DecimalFormat("#0.0",symbols);
        String scaleWeight = df1.format(scaleTmpWeight);
        json.put("scaleWeight", scaleWeight);

        return json;
    }
}
