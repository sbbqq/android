package com.haiyisoft.haieryl.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 蓝牙数据解析工具类
 * @author 01478664
 * @createdate 2017年11月23日 上午10:27:07
 */
public class BluetoothUtil {

    /**
     * @Description 解析体脂称传来的体重和电阻数据（0x06,0x03,0xe1,0x07,0x11,0x22...），转化为标准值
     * @create by 01478664
     * @createdate 2017年11月23日 上午10:42:30
     * @return void
     * @param bBuffer
     * @throws Exception
     */
    public static JSONObject analysisWeightAndResistance(byte[] bBuffer) throws Exception {

        if (bBuffer == null) {
            throw new Exception("帧格式错误 -- 帧为空");
        }

        if (bBuffer.length < 14) {
            throw new Exception("帧格式错误 -- 帧长度不对");
        }

        // 计算电阻值
        float Resistance = BytesUtil.bytesToInt(new byte[] {bBuffer[10], bBuffer[9]});
        Resistance = (Resistance * 0.1f);

        // 计算体重
        byte scaleProperty = bBuffer[15];
        // 解析体重值
        JSONObject parserReturn = WeightUnitUtil.parser(bBuffer[12], bBuffer[11],
                scaleProperty, true);
        parserReturn.put("resistance", Resistance);
        return parserReturn;
    }

    public static void main(String[] args) {
        byte[] frame = {0x06, 0x03, (byte)0xe1, 0x07, 0x0b, 0x16, 0x0c, 0x24, 0x2f, 0x72,
                0x15, 0x0f, 0x02, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00};
        JSONObject json = null;
        try {
            json = analysisWeightAndResistance(frame);
            System.out.println(json.toJSONString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
