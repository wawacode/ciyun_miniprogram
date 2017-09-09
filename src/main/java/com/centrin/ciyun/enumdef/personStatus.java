package com.centrin.ciyun.enumdef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author
 * 
 */
public class personStatus {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum EPersonStatus {

		NOREG_AND_NOREG_NOLOG(0, "用户未注册过慈云平台或已注册但是未登录过慈云小程序"), 
		YES_LOGIN(1, "登录过慈云小程序");
		
		public String value;
		public int key;

		/**
		 * 构造方法
		 * 
		 * @param value
		 * @param key
		 */
		private EPersonStatus(int key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (EPersonStatus ect : EPersonStatus.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}
}