package com.centrin.ciyun.entity.vo;

import java.util.Date;

public class HomeColumnVo  implements java.io.Serializable {
	private String id;
	private String type;
	private String name;
	private Date date;// 显示最近7天,否则显示空, date, lastDate只能赋值一个
	private Date lastDate;// 显示上次操作时间
	private String param;// 参数信息，比如存放血糖的类型,bloodsugartype=${param}
	private int serviceType;// ITEM(1, "体征项"), SPORT(2, "运动项"), CONSULT(3,
							// "健康咨询 "), MEDICAL_REPORT(4, "体检报告");

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
}
