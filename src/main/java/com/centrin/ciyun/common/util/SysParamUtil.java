package com.centrin.ciyun.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "miniprogram")
public class SysParamUtil {
	private String mpNum;//小程序原始ID
	private String appId;//小程序的APPID
	private String appSecret;//小程序的APPSECRET
	private String sessionKeyUrl;//根据code获取session_key的请求地址
	
	/**
	 * 小程序原始ID
	 * @return
	 */
	public String getMpNum(){
		return mpNum;
	}
	
	/**
	 * 小程序的APPID
	 * @return
	 */
	public String getAppId(){
		return appId;
	}
	
	/**
	 * 小程序的APPSECRET
	 * @return
	 */
	public String getAppSecret(){
		return appSecret;
	}
	
	/**
	 * 根据code获取session_key的请求地址
	 * @return
	 */
	public String getSessionKeyUrl(){
		return sessionKeyUrl;
	}

}
