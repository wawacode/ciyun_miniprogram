package com.centrin.ciyun.enumdef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 体检报告比较类型
 * 
 * @author
 */
public class MedRptCompareType {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum EMedRptCompareType {
		ADVICE(1, "ADVICE"), EXCEPTION(2, "EXCEPTION"), DETAIL(3, "DETAIL");
		public Integer key;
		public String value;

		private EMedRptCompareType(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (EMedRptCompareType ect : EMedRptCompareType.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}
}