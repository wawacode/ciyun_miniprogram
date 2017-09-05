package com.centrin.ciyun.entity.med;

import java.util.Date;




public class MedStdDiseaseStatistics implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private MedStdDisease medStdDisease;
	private MedStdDiseastat medStdDiseastat;
	private Date createTime;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MedStdDisease getMedStdDisease() {
		return this.medStdDisease;
	}

	public void setMedStdDisease(MedStdDisease medStdDisease) {
		this.medStdDisease = medStdDisease;
	}

	public MedStdDiseastat getMedStdDiseastat() {
		return this.medStdDiseastat;
	}

	public void setMedStdDiseastat(MedStdDiseastat medStdDiseastat) {
		this.medStdDiseastat = medStdDiseastat;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}