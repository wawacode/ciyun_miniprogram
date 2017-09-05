package com.centrin.ciyun.entity.vo;

import java.io.Serializable;
import java.util.List;

public class ServiceListVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long queryTime;
	private List<ServiceVo> service;
	
	public long getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(long queryTime) {
		this.queryTime = queryTime;
	}

	public List<ServiceVo> getService() {
		return service;
	}

	public void setService(List<ServiceVo> service) {
		this.service = service;
	}

	public static class ServiceVo implements java.io.Serializable{
		private long consultId;
		private int type;
		private String name;
		private int state;
		private int readState;
		private String applyTime;
		private String linkUrl;
		private String desc;
		public long getConsultId() {
			return consultId;
		}
		public void setConsultId(long consultId) {
			this.consultId = consultId;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getReadState() {
			return readState;
		}
		public void setReadState(int readState) {
			this.readState = readState;
		}
		public String getApplyTime() {
			return applyTime;
		}
		public void setApplyTime(String applyTime) {
			this.applyTime = applyTime;
		}
		public String getLinkUrl() {
			return linkUrl;
		}
		public void setLinkUrl(String linkUrl) {
			this.linkUrl = linkUrl;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
