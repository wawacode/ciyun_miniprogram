package com.centrin.ciyun.entity.med;

import java.util.Date;




public class MedStdDiseastat implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String statisticsName;
	private Integer gender;
	private Integer state;
	private Date createTime;
	private String remarks;

	public MedStdDiseastat(){}
	public MedStdDiseastat(Long id){
		this.id = id;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatisticsName() {
		return this.statisticsName;
	}

	public void setStatisticsName(String statisticsName) {
		this.statisticsName = statisticsName;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}