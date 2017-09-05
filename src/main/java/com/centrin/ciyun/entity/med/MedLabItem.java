package com.centrin.ciyun.entity.med;

import java.util.Date;


/**
 * 航天化验单结果
 * @author liqiangbiao
 * 2016-5-19
 */

public class MedLabItem  implements java.io.Serializable{
	private Long id;
	private Long labSheetId;  //MedLabSheet#id
	private String barcodeNum;//检验流水号MedLabSheet#barcodeNum
	private String itemName;//项目名称
	private String itemCode;//项目编码
	private String itemResult;//结果值
	private String itemUnit;//单位
	private Integer itemResultType; //结果类型
	private Double itemLow;//结果下限
	private Double itemHight;//结果上限
	private String itemRangeStr; //结果参考范围
	private Integer isNormal;//结果标记
	private Date resultDateTime;//检验时间
	private Long departmentId;
	private String departmentName; //科室名称
	private Long classId;
	private String className;  //organName
	private String createUser;
	private Date createTime;//记录创建时间
	private String modifyUser;
	private Date modifyTime;

	
	
	@Override
	public String toString() {
		return "MedLabItem [id=" + id + ", labSheetId=" + labSheetId
				+ ", barcodeNum=" + barcodeNum + ", itemName=" + itemName
				+ ", itemCode=" + itemCode + ", itemResult=" + itemResult
				+ ", itemUnit=" + itemUnit + ", isNormal=" + isNormal
				+ ", resultDateTime=" + resultDateTime + ", referenceResult="
				+ itemRangeStr + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", classId=" + classId
				+ ", className=" + className + ", createUser=" + createUser
				+ ", createTime=" + createTime + ", modifyUser=" + modifyUser
				+ ", modifyTime=" + modifyTime + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLabSheetId() {
		return labSheetId;
	}
	public void setLabSheetId(Long labSheetId) {
		this.labSheetId = labSheetId;
	}
	public String getBarcodeNum() {
		return barcodeNum;
	}
	public void setBarcodeNum(String barcodeNum) {
		this.barcodeNum = barcodeNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemResult() {
		return itemResult;
	}
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	public Date getResultDateTime() {
		return resultDateTime;
	}
	public void setResultDateTime(Date resultDateTime) {
		this.resultDateTime = resultDateTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Double getItemLow() {
		return itemLow;
	}
	public void setItemLow(Double itemLow) {
		this.itemLow = itemLow;
	}
	public Double getItemHight() {
		return itemHight;
	}
	public void setItemHight(Double itemHight) {
		this.itemHight = itemHight;
	}
	public String getItemRangeStr() {
		return itemRangeStr;
	}
	public void setItemRangeStr(String itemRangeStr) {
		this.itemRangeStr = itemRangeStr;
	}
	public Integer getItemResultType() {
		return itemResultType;
	}
	public void setItemResultType(Integer itemResultType) {
		this.itemResultType = itemResultType;
	}
	public Integer getIsNormal() {
		return isNormal;
	}
	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	
}

