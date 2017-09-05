package com.centrin.ciyun.enumdef;

import java.util.LinkedHashMap;
import java.util.Map;

public class MedReportOperator {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum EMedReportOperator {
		USER(1, "用户"), JGS(2, "JGS"),FAMILY(3,"亲友");
		public Integer key;
		public String value;

		private EMedReportOperator(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (EMedReportOperator ect : EMedReportOperator.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}
}