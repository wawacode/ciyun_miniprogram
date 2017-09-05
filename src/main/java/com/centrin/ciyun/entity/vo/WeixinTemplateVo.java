package com.centrin.ciyun.entity.vo;

import java.util.List;

public class WeixinTemplateVo implements java.io.Serializable {
	private String templateId;
	private String url;
	private String openId;
	private String mpNum;
	private String topcolor = "#FF0000";
	private List<Param> data;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMpNum() {
		return mpNum;
	}

	public void setMpNum(String mpNum) {
		this.mpNum = mpNum;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public List<Param> getData() {
		return data;
	}

	public void setData(List<Param> data) {
		this.data = data;
	}

	public static class Param implements java.io.Serializable{
		private String key;
		private String value;
		private String color = "#173177";

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

	}
}
