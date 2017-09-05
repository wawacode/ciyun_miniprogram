package com.centrin.ciyun.common.util.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author
 * 
 */
public class LoginStatus {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum ELoginStatus {

		REGISTER_NO(1, "未注册"), 
		REGISTER_YES(2, "已注册"), 
		LOGIN_ALREADY(3, "已登录");
		
		public String value;
		public int key;

		/**
		 * 构造方法
		 * 
		 * @param value
		 * @param key
		 */
		private ELoginStatus(int key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (ELoginStatus ect : ELoginStatus.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}
}