package com.centrin.ciyun.enumdef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 活动参与者类型
 * 
 */
public class ExamExtrasTempleteType {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum EExamExtrasTempleteType {

		WEIXIN(1, "微信端"), DOCTOR(2, "健管师端"), HMO(3, "健管机构端"), APP(4, "APP端"), PC(5,"PC端"),MINIPROGRAM(6,"小程序");
		public String value;
		public Integer key;

		/**
		 * 构造方法
		 * 
		 * @param value
		 * @param key
		 */
		private EExamExtrasTempleteType(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (EExamExtrasTempleteType ect : EExamExtrasTempleteType.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}
}