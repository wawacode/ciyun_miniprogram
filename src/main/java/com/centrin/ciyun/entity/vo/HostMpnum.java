package com.centrin.ciyun.entity.vo;

public class HostMpnum implements java.io.Serializable {

	/**
	 * 域名前缀
	 */
	private String hostprefix;
	/**
	 * 公众号
	 */
	private String mpnum;
	/**
	 * 短信签名
	 */
	private String smssig;
	
	private String appId;
	private String appSecret;
 	private String scope; 
	
	private String returnUrl;
	
	
	
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
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
	 
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getHostprefix() {
		return hostprefix;
	}
	public void setHostprefix(String hostprefix) {
		this.hostprefix = hostprefix;
	}
	public String getMpnum() {
		return mpnum;
	}
	public void setMpnum(String mpnum) {
		this.mpnum = mpnum;
	}
	public String getSmssig() {
		return smssig;
	}
	public void setSmssig(String smssig) {
		this.smssig = smssig;
	}
	
	
}
