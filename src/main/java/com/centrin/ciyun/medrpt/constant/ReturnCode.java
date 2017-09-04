package com.centrin.ciyun.medrpt.constant;

public final class ReturnCode {

	/**
	 * 10000：成功 
	 */
	public static final int OK = 10000;
	
	/**
	 * 20001：手机号不对
	 */
	public static final int PHONE_IS_WRONG = 20001;
	
	/**
	 * 20002：短信验证码不对 
	 */
	public static final int NOTE_IS_WRONG = 20002;
	
	/**
	 * 20003：获取短信验证码超过上限
	 */
	public static final int NOTE_UPPER_LIMIT = 20003;
	
	/**
	 * 20004：输入参数为空
	 */
	public static final int PARAM_IS_NULL = 20004;
	
	/**
	 * 30001：第三方服务器会话key失效或不对 
	 */
	public static final int THIRD_SESSION_KEY = 30001;
	
	/**
	 * 30002：数据校验失败
	 */
	public static final int DATA_VALIDATE_FAIL = 30002;
	
}
