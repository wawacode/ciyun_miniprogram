package com.centrin.ciyun.entity.med;

import java.util.Date;

public class MedStdDisease implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Long organId;
	private String diseaseName;
	private String codingIcd;
	private String controlCode;
	private String mnemonic;
	private String humanSystem;
	private Integer diseaseOrder;
	private Integer gender;
	private Integer significantPostive;
	private Integer state;
	private Date createTime;
	private String remarks;
	private Integer notifyState;
	private Integer notifyCycle;
	private String  notifyConts;
	private Integer userVisible;
	private String retestItems;
	private Integer classify;
	
	public MedStdDisease(){}

	public MedStdDisease(Long id){
		this.id = id;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getOrganId() {
		return this.organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getCodingIcd() {
		return this.codingIcd;
	}

	public void setCodingIcd(String codingIcd) {
		this.codingIcd = codingIcd;
	}

	public String getControlCode() {
		return this.controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getMnemonic() {
		return this.mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getHumanSystem() {
		return this.humanSystem;
	}

	public void setHumanSystem(String humanSystem) {
		this.humanSystem = humanSystem;
	}

	public Integer getDiseaseOrder() {
		return this.diseaseOrder;
	}

	public void setDiseaseOrder(Integer diseaseOrder) {
		this.diseaseOrder = diseaseOrder;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getSignificantPostive() {
		return this.significantPostive;
	}

	public void setSignificantPostive(Integer significantPostive) {
		this.significantPostive = significantPostive;
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

	public Integer getNotifyState() {
		return notifyState;
	}

	public void setNotifyState(Integer notifyState) {
		this.notifyState = notifyState;
	}

	public Integer getNotifyCycle() {
		return notifyCycle;
	}

	public void setNotifyCycle(Integer notifyCycle) {
		this.notifyCycle = notifyCycle;
	}

	public String getNotifyConts() {
		return notifyConts;
	}

	public void setNotifyConts(String notifyConts) {
		this.notifyConts = notifyConts;
	}

	public Integer getUserVisible() {
		return userVisible;
	}

	public void setUserVisible(Integer userVisible) {
		this.userVisible = userVisible;
	}

	public String getRetestItems() {
		return retestItems;
	}

	public void setRetestItems(String retestItems) {
		this.retestItems = retestItems;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

}