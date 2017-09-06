package com.centrin.ciyun.enumdef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author
 * 
 */
public class UserLoginStatus {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum ELoginStatus {

		REGISTER_NO(0, "未注册"), 
		REGISTER_YES(1, "已注册"), 
		LOGIN_ALREADY(2, "已登录");
		
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