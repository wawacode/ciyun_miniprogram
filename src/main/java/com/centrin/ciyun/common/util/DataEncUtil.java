/**    
 * @Title: PwdUtil.java  
 * @Package com.swd.common.util  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author     
 * @date Jan 27, 2012 4:23:29 PM 
 */
package com.centrin.ciyun.common.util;

/**
 * @author
 * 
 */
public class DataEncUtil {

	/**
	 * 对pwd进行加密
	 * 
	 * @param str
	 * @return
	 * @author
	 */
	public static String encstr(String str) {
		if(str == null){
			return null;
		}
		Des des = new Des();
		try{
			return des.getEncString(str);
		}catch(Exception e){
			return str;
		}
	}

	/**
	 * 对str进行解密
	 * @param str
	 * @return
	 */
	public static String decstr(String str){
		if(str == null){
			return null;
		}
		Des des = new Des();
		try{
			return des.getDesString(str);
		}catch(Exception e){
			return str;
		}
	}

	public static void main(String[] args) {
		String str="中文的啊大大";
		System.out.println(encstr(str));
		System.out.println(decstr("JOqS/7NgTjrH6RuOHxww6w=="));
	}
}