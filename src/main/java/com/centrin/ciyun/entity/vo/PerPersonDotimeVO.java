package com.centrin.ciyun.entity.vo;

import java.util.Date;

/**
 * PerPersonDotime entity. @author MyEclipse Persistence Tools
 */

public class PerPersonDotimeVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214473893825389906L;
	private String personId;
	private int servicetype;// 操作类别，来自数据字典（1体征项，2运动项 3健康咨询 4体检报告）
	private String doselfId = "5";// 测量项目，这个对应的是hid_item_trend中的id值，
									// 运动项、健康咨询、体检报告都填5
	private Date doTime;
	private String prePersonId;
	private String itemValue;//体征项或运动录入值
	private String itemName;
	private String unit;//单位
	private String bloodsugartype;//血糖类型
	private String showFlag="0";//图表是否显示0不显示 1显示
	private String styleClass;
	public String getPrePersonId() {
		return prePersonId;
	}

	public void setPrePersonId(String prePersonId) {
		this.prePersonId = prePersonId;
	}

	// Constructors

	/** default constructor */
	public PerPersonDotimeVO() {
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.prePersonId = personId;
		this.personId = personId;
	}

	public int getServicetype() {
		return servicetype;
	}

	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}

	public String getDoselfId() {
		return doselfId;
	}

	public void setDoselfId(String doselfId) {
		this.doselfId = doselfId;
	}

	public Date getDoTime() {
		return this.doTime;
	}

	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBloodsugartype() {
		return bloodsugartype;
	}

	public void setBloodsugartype(String bloodsugartype) {
		this.bloodsugartype = bloodsugartype;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}