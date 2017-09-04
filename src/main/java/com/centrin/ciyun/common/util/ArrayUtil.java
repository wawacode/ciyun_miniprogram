package com.centrin.ciyun.common.util;

/**
 * Created by jacky on 2017/3/7.
 */
public class ArrayUtil {

    /**
     * 给字符串数组拼接成一个字符串返回
     * @param array
     * @return
     */
    public static String arraysToString(String[] array){
        String temp = "";
        for(String str : array){
            temp = temp + str;
        }
        return temp;
    }
}
