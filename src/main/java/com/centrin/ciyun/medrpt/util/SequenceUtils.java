package com.centrin.ciyun.medrpt.util;

import java.text.SimpleDateFormat;

/**
 * <p>
 *  <li>保证唯一的递增的序列号字符串</li>
 * </p>
 * @author jacky
 * @since  1.0
 * @see 
 */
public class SequenceUtils {
	/**
     * @return 形如 yyyyMMddHHmmssSSS-Z0000019558195832297 的(38位)保证唯一的递增的序列号字符串，
     * 主要用于数据库的主键，方便基于时间点的跨数据库的异步数据同步。
     * 前半部分是currentTimeMillis，后半部分是nanoTime（正数）补齐20位的字符串，
     * 如果通过System.nanoTime()获取的是负数，则通过nanoTime = nanoTime+Long.MAX_VALUE+1;
     * 转化为正数或零。
     */
    public static String getTimeMillisSequence(){
        long nanoTime = System.nanoTime();
        String preFix="";
        if (nanoTime<0){
            preFix="A";//负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题。
            nanoTime = nanoTime+Long.MAX_VALUE+1;
        }else{
            preFix="Z";
        }
        String nanoTimeStr = String.valueOf(nanoTime);
         
        int difBit=String.valueOf(Long.MAX_VALUE).length()-nanoTimeStr.length();
        for (int i=0;i<difBit;i++){
            preFix = preFix+"0";
        }
        nanoTimeStr = preFix+nanoTimeStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //24小时制
        String timeMillisSequence=sdf.format(System.currentTimeMillis())+"-"+nanoTimeStr; 
         
        return timeMillisSequence;      
    }
    
    public static void main(String[] args){
    	System.out.println(SequenceUtils.getTimeMillisSequence());
    }
}
