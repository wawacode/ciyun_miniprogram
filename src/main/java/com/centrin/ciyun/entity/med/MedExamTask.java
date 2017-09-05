package com.centrin.ciyun.entity.med;



public class MedExamTask implements java.io.Serializable{
	private Long id;
	private Long examRptId;
	private Integer operTable;
	private Integer operType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExamRptId() {
		return examRptId;
	}
	public void setExamRptId(Long examRptId) {
		this.examRptId = examRptId;
	}
	public Integer getOperTable() {
		return operTable;
	}
	public void setOperTable(Integer operTable) {
		this.operTable = operTable;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	
}
