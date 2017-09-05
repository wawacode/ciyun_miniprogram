package com.centrin.ciyun.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "ciyun")
public class CiyunUrlUtil {
	private String sendSmsUrl;//发送短信地址
	private String hangtianrptUrl; //航天接口请求地址
	private String templateId; //慈云平台ID
	
	/**
	 * 慈云发送短信地址
	 * @return
	 */
	public String getSendSmsUrl(){
		return sendSmsUrl;
	}
	
	public String getHangtianrptUrl() {
		return hangtianrptUrl;
	}

	public String getTemplateId() {
		return templateId;
	}
	
}
