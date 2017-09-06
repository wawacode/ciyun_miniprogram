package com.centrin.ciyun.entity.med.vo;


public class MedMapItemVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String medItemName;
    private Long stdItemId;
    private String stdItemName;
    private Integer stdItemResultType;
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedItemName() {
		return this.medItemName;
	}

	public void setMedItemName(String medItemName) {
		this.medItemName = medItemName;
	}

	public Long getStdItemId() {
		return stdItemId;
	}

	public void setStdItemId(Long stdItemId) {
		this.stdItemId = stdItemId;
	}

	public String getStdItemName() {
		return stdItemName;
	}

	public void setStdItemName(String stdItemName) {
		this.stdItemName = stdItemName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStdItemResultType() {
		return stdItemResultType;
	}

	public void setStdItemResultType(Integer stdItemResultType) {
		this.stdItemResultType = stdItemResultType;
	}
	
}