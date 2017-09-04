package com.centrin.ciyun.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * 获取 短信、邮件随机码
 * 
 */
public class VerifyCodeUtil {

	/**
	 * 获取短信随机码
	 */
	public static String getSmsCode() {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			code += rand;
		}
		return code;
	}

	/**
	 * 获取邮件随机码
	 */
	public static String getEmailCode() {
		String code = UUID.randomUUID().toString().replace("-", "");
		return code;
	}
	
	public static void main(String[] args) {
		System.out.println(getSmsCode());
	}

}
