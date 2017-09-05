package com.centrin.ciyun.entity.vo;
/**
 * @名称: MpInfoVo.java
 * @包名： com.centrin.ciyun.entity
 * @描述: TODO
 * @作者： lwh
 * @日期：2016-2-24 下午03:03:38
 */
public class MpInfoVo  implements java.io.Serializable {
	
	private String mpNum;
	private String mpName;
	private String appId;
	private String appSecret;
	public String getMpNum() {
		return mpNum;
	}
	public void setMpNum(String mpNum) {
		this.mpNum = mpNum;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	

}
