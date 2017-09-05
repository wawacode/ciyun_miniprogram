package com.centrin.ciyun.entity.vo;

import java.util.Date;

public class MedExamRptVo implements java.io.Serializable {
	private long id;
	private Integer importWay;
	private String telephone;
	private String name;
	private int gender;
	private String medCorpName;
	private String entName;
	private String contractName;
	private String hmoId;
	private String personId;
	private int recordFlag = 1;
	private Date createTime; //导入时间
	private Date medDate; //体检时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getImportWay() {
		return importWay;
	}
	public void setImportWay(Integer importWay) {
		this.importWay = importWay;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getMedCorpName() {
		return medCorpName;
	}
	public void setMedCorpName(String medCorpName) {
		this.medCorpName = medCorpName;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getHmoId() {
		return hmoId;
	}
	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public int getRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(int recordFlag) {
		this.recordFlag = recordFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getMedDate() {
		return medDate;
	}
	public void setMedDate(Date medDate) {
		this.medDate = medDate;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
}
