package com.centrin.ciyun.entity.med;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MedExamAdvice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	// private MedExamRpt medExamRpt;
	private long examRptId;
	private String summary;
	private String advice;
	private String doctorBegin;
	private String doctorLast;
	private Date createTime;
	private String preCreateTime; //
	public String getPreCreateTime() {
		return preCreateTime;
	}

	public void setPreCreateTime(String preCreateTime) {
		this.preCreateTime = preCreateTime;
	}

	private String remarks;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getDoctorBegin() {
		return this.doctorBegin;
	}

	public void setDoctorBegin(String doctorBegin) {
		this.doctorBegin = doctorBegin;
	}

	public String getDoctorLast() {
		return doctorLast;
	}

	public void setDoctorLast(String doctorLast) {
		this.doctorLast = doctorLast;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		if(createTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			setPreCreateTime(sdf.format(createTime));
		}
		this.createTime = createTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getExamRptId() {
		return examRptId;
	}

	public void setExamRptId(long examRptId) {
		this.examRptId = examRptId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}