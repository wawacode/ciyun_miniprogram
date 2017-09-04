package com.centrin.ciyun.entity.med;

import java.util.Date;

public class MedExamSummary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private long examRptId;
	private Long refOrganId;
	private String organName;
	private String departmentName;
	private Integer organOrder;
	private String summary;
	private String doctor;					
	private String dempartmentDoctor;		//科室小结医生
	private Date examTime;
	private String revDoctor;  //审核医生
	private Date revTime;      //审核时间
	private Date createTime;
	private Integer signFlag; //1:确定 2：不确定
	

	public Long getRefOrganId() {
		return refOrganId;
	}

	public void setRefOrganId(Long refOrganId) {
		this.refOrganId = refOrganId;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public Date getExamTime() {
		return this.examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getExamRptId() {
		return examRptId;
	}

	public void setExamRptId(long examRptId) {
		this.examRptId = examRptId;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrganName() {
		return organName;
	}

	public Integer getOrganOrder() {
		return organOrder;
	}

	public void setOrganOrder(Integer organOrder) {
		this.organOrder = organOrder;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDempartmentDoctor() {
		return dempartmentDoctor;
	}

	public void setDempartmentDoctor(String dempartmentDoctor) {
		this.dempartmentDoctor = dempartmentDoctor;
	}

	public String getRevDoctor() {
		return revDoctor;
	}

	public void setRevDoctor(String revDoctor) {
		this.revDoctor = revDoctor;
	}

	public Date getRevTime() {
		return revTime;
	}

	public void setRevTime(Date revTime) {
		this.revTime = revTime;
	}

	public Integer getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}
	
}