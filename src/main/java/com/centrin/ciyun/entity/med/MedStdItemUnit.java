package com.centrin.ciyun.entity.med;

import java.util.Date;



public class MedStdItemUnit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String unitName;
	private String remarks;
	private Date createTime;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}