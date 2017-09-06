package com.centrin.ciyun.enumdef;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 注册用户类型
 * 
 * @author
 * 
 */
public class IdType {
	public static Map<Integer, String> ENUMMAP = new LinkedHashMap<Integer, String>();

	public enum EIdType {

		UNKNOW(0, "未知"), IDCARD(1, "身份证"), RETURNNO(2, "回乡证"), PASSPORT(3, "护照"), SOLIDER(4,
				"军官证"), YIBAOKA(5,"医保卡号"),JINGCHAZHENG(6,"警察证"),OTHER(20, "其他");
		public String value;
		public Integer key;

		/**
		 * 构造方法
		 * 
		 * @param value
		 * @param key
		 */
		private EIdType(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static {
		for (EIdType ect : EIdType.values()) {
			ENUMMAP.put(ect.key, ect.value);
		}
	}

	public static Integer getKeyByValue(String value) {
		Iterator<Integer> iter = ENUMMAP.keySet().iterator();
		while (!StringUtils.isEmpty(value) && iter.hasNext()) {
			Integer iKey = iter.next();
			String sValue = ENUMMAP.get(iKey).replace(" ", "");
			if (sValue.equals(value)) {
				return iKey;
			}
		}
		return 0;
	}
}