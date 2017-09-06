package com.centrin.ciyun.entity.vo;

public class ConsultNotifyVo implements java.io.Serializable {
	private String sender;
	private String personId;
	private int notifyType;
	private long consultId;
	private int consultType;
	private int consultState;
	private int consultReadState;
	private int consultFollowFlag;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public int getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public int getConsultType() {
		return consultType;
	}

	public void setConsultType(int consultType) {
		this.consultType = consultType;
	}

	public int getConsultState() {
		return consultState;
	}

	public void setConsultState(int consultState) {
		this.consultState = consultState;
	}

	public int getConsultReadState() {
		return consultReadState;
	}

	public void setConsultReadState(int consultReadState) {
		this.consultReadState = consultReadState;
	}

	public int getConsultFollowFlag() {
		return consultFollowFlag;
	}

	public void setConsultFollowFlag(int consultFollowFlag) {
		this.consultFollowFlag = consultFollowFlag;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
