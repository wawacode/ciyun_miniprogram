package com.centrin.ciyun.entity.med.vo;
public class MedExamMediaVo  implements java.io.Serializable {
	private String fileExtension;
	private String refOrganId;
	private String organName;
	private String media;
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getRefOrganId() {
		return refOrganId;
	}
	public void setRefOrganId(String refOrganId) {
		this.refOrganId = refOrganId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	
}
