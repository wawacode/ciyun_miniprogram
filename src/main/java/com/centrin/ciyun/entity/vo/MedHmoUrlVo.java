package com.centrin.ciyun.entity.vo;
public class MedHmoUrlVo implements java.io.Serializable {
	/**
	 * 健管机构ID
	 */
	private String hmoId;
	/**
	 * 体检报告上传url
	 */
	private String rptUrl;
	
	/**
	 * 化验单上传url
	 */
	private String labUrl;
	
	/**
	 * 其他url（比如报告和化验单的url相同,可以使用这种）
	 */
	private String otherUrl;

	public String getHmoId() {
		return hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getRptUrl() {
		return rptUrl;
	}

	public void setRptUrl(String rptUrl) {
		this.rptUrl = rptUrl;
	}

	public String getLabUrl() {
		return labUrl;
	}

	public void setLabUrl(String labUrl) {
		this.labUrl = labUrl;
	}

	public String getOtherUrl() {
		return otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}
	
}
