package com.centrin.ciyun.entity.med;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class MedExamSummary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private long examRptId;
	private Long refOrganId;
	private String organName = null;
	private String departmentName = null;
	private Integer organOrder;
	private String summary = null;
	private String doctor = null;					
	private String dempartmentDoctor = null;		//科室小结医生
	private Date examTime;
	private String preExamTime; //检查日期字符串
	private String revDoctor = null;  //审核医生
	private Date revTime;      //审核时间
	private String preRevTime = null; //审核日期字符串
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
		if (StringUtils.isEmpty(summary)) {
			summary = null;
		}
		this.summary = summary;
	}

	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		if (StringUtils.isEmpty(doctor)) {
			doctor = null;
		}
		this.doctor = doctor;
	}

	public Date getExamTime() {
		return this.examTime;
	}

	public void setExamTime(Date examTime) {
		if(examTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			setPreExamTime(sdf.format(examTime));
		}
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
		if (StringUtils.isEmpty(organName)) {
			organName = null;
		}
		this.organName = organName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		if (StringUtils.isEmpty(departmentName)) {
			departmentName = null;
		}
		this.departmentName = departmentName;
	}

	public String getDempartmentDoctor() {
		return dempartmentDoctor;
	}

	public void setDempartmentDoctor(String dempartmentDoctor) {
		if (StringUtils.isEmpty(dempartmentDoctor)) {
			dempartmentDoctor = null;
		}
		this.dempartmentDoctor = dempartmentDoctor;
	}

	public String getRevDoctor() {
		return revDoctor;
	}

	public void setRevDoctor(String revDoctor) {
		if (StringUtils.isEmpty(revDoctor)) {
			revDoctor = null;
		}
		this.revDoctor = revDoctor;
	}

	public Date getRevTime() {
		return revTime;
	}

	public void setRevTime(Date revTime) {
		if(revTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			setPreRevTime(sdf.format(revTime));
		}
		this.revTime = revTime;
	}

	public Integer getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}

	public String getPreExamTime() {
		return preExamTime;
	}

	public void setPreExamTime(String preExamTime) {
		if (StringUtils.isEmpty(preExamTime)) {
			preExamTime = null;
		}
		this.preExamTime = preExamTime;
	}

	public String getPreRevTime() {
		return preRevTime;
	}

	public void setPreRevTime(String preRevTime) {
		if (StringUtils.isEmpty(preRevTime)) {
			preRevTime = null;
		}
		this.preRevTime = preRevTime;
	}
	
}