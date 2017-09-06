package com.centrin.ciyun.entity.med.vo;

import java.util.List;


public class IPhyExamItemFormWritten  implements java.io.Serializable {
	private String title;
	private String type;
	private List<?> content;//不要指定类型，因为有子类，该类型不确定
	private List<ItemResultDesc> itemResultDesc;

	/**
	 * 最近一条记录的判断。
	 * 1偏低2偏高3其他异常
	 */
	private int verify;
	
	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<?> getContent() {
		return content;
	}

	public void setContent(List<?> content) {
		this.content = content;
	}


	public List<ItemResultDesc> getItemResultDesc() {
		return itemResultDesc;
	}

	public void setItemResultDesc(List<ItemResultDesc> itemResultDesc) {
		this.itemResultDesc = itemResultDesc;
	}


	public static class Content implements java.io.Serializable{
		private String time;
		private String text;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}
	
	public static class ItemResultDesc  implements java.io.Serializable{
		private int type;
		private String name;
		private String desc;
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
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
