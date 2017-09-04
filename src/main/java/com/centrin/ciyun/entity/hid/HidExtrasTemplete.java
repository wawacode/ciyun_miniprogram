package com.centrin.ciyun.entity.hid;

import java.sql.Timestamp;

/**
 * HidExtrasTemplete entity. @author MyEclipse Persistence Tools
 */

public class HidExtrasTemplete implements java.io.Serializable {

	// Fields

	private String fileId;
	private String hmoId;
	private String realName;
	private String fileExtension;
	private String remarks;
	private Timestamp createTime;
	private String createUser;
	private Timestamp modifyTime;
	private String modifyUser;

	// Constructors

	/** default constructor */
	public HidExtrasTemplete() {
	}

	/** minimal constructor */
	public HidExtrasTemplete(String fileId, String fileExtension,
			Timestamp createTime, String createUser) {
		this.fileId = fileId;
		this.fileExtension = fileExtension;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/** full constructor */
	public HidExtrasTemplete(String fileId, String hmoId, String fileExtension,
			String remarks, Timestamp createTime, String createUser,
			Timestamp modifyTime, String modifyUser) {
		this.fileId = fileId;
		this.hmoId = hmoId;
		this.fileExtension = fileExtension;
		this.remarks = remarks;
		this.createTime = createTime;
		this.createUser = createUser;
		this.modifyTime = modifyTime;
		this.modifyUser = modifyUser;
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getHmoId() {
		return this.hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}