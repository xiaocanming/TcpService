package com.xcm.tcpservice.common.util;

/**
 * @描述 Bytes数组处理类
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
public class BytesUtil {
    /**
     * 转换byte数组为short（大端）
     *
     * @return
     */
    public static short byte2Toshort(byte[] b,int startindex){
        short l = 0;
        for (int i = 0; i < 2; i++) {
            l<<=8; //<<=和我们的 +=是一样的，意思就是 l = l << 8
            l |= (b[startindex+i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
        }
        return l;
    }

    /**
     * 转换byte数组为short（大端）
     * @param s
     * @return
     */
    public static byte[] unsignedShortToByte2(int s) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (s >> 8 & 0xFF);
        targets[1] = (byte) (s & 0xFF);
        return targets;
    }

}
