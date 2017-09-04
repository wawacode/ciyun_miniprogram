package com.centrin.ciyun.entity.med;

import java.util.Date;

/**
 * MedExamExtras entity. @author MyEclipse Persistence Tools
 */
public class MedExamExtras implements java.io.Serializable {

	// Fields

	private Long id;
	private Long examRptId;
	private String extraStr;
	private String createUser;
	private Date createTime;

	// Constructors

	/** default constructor */
	public MedExamExtras() {
	}

	/** minimal constructor */
	public MedExamExtras(Long id, Long examRptId, String createUser,
			Date createTime) {
		this.id = id;
		this.examRptId = examRptId;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	/** full constructor */
	public MedExamExtras(Long id, Long examRptId, String extraStr,
			String createUser, Date createTime) {
		this.id = id;
		this.examRptId = examRptId;
		this.extraStr = extraStr;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExamRptId() {
		return this.examRptId;
	}

	public void setExamRptId(Long examRptId) {
		this.examRptId = examRptId;
	}

	public String getExtraStr() {
		return this.extraStr;
	}

	public void setExtraStr(String extraStr) {
		this.extraStr = extraStr;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}