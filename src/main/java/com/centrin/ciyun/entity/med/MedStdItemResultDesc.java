package com.centrin.ciyun.entity.med;

import java.util.Date;




public class MedStdItemResultDesc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Long itemId;
	private Integer abnormalType;
	private String abnormalDesc;
	private Date createTime;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getAbnormalType() {
		return this.abnormalType;
	}

	public void setAbnormalType(Integer abnormalType) {
		this.abnormalType = abnormalType;
	}

	public String getAbnormalDesc() {
		return this.abnormalDesc;
	}

	public void setAbnormalDesc(String abnormalDesc) {
		this.abnormalDesc = abnormalDesc;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}