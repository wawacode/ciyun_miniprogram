package com.centrin.ciyun.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * <li>简述：<一句话介绍类的作用></li>
 * <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * 
 * @author yanxf
 * @since 1.0
 * @see
 */
public class SignatureUtils {

	private SignatureUtils() {

	}

	public static String generateSignatureStr(List<String> paramList) {
		String[] signArray = new String[paramList.size()];
		paramList.toArray(signArray);
		Arrays.sort(signArray);
		String arrayStr = ArrayUtil.arraysToString(signArray);
		String signStr = SHA1.getDigestOfString(arrayStr);
		return signStr;
	}
}
