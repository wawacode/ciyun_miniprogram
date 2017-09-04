package com.centrin.ciyun.common.util;

/**
 * 手机号码处理类
 * Created by zhangjiecong on 2017/1/4.
 */
public class MobileUtil {

    /**
     *  将手机号码处理为 134****5678
     * @param mobile
     * @return
     */
    public static String mobileFormat(String mobile) {
        char[] mobiles = mobile.toCharArray();
        mobiles[3] = '*';
        mobiles[4] = '*';
        mobiles[5] = '*';
        mobiles[6] = '*';
        return String.valueOf(mobiles);
    }
}
