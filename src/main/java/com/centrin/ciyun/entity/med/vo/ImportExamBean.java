package com.centrin.ciyun.entity.med.vo;

public class ImportExamBean  implements java.io.Serializable {
	private String hmoId; //健康管理机构ID
	private String entName;//企业名称
	private String loginName = "sysadmin"; //登陆账号
	private int serviceId; //医生组ID
	private Integer autoPerGroup = 1; //是否自动创建分组
	public String getHmoId() {
		return hmoId;
	}
	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public Integer getAutoPerGroup() {
		return autoPerGroup;
	}
	public void setAutoPerGroup(Integer autoPerGroup) {
		this.autoPerGroup = autoPerGroup;
	}
	
}
