package com.centrin.ciyun.entity.med;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MedStdItemClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
//	private transient MedStdDepartment medStdDepartment;
	private long departmentId;
	private String igName;
	private Integer igOrder;
	private String remarks;
	private Date createTime;
	private List<MedStdItem> itemList;

	public MedStdItemClass(){}
	
	public MedStdItemClass(Long id, String igName){
		this.id = id;
		this.igName = igName;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public MedStdDepartment getMedStdDepartment() {
		/*if(departmentId<=0)
			return null;
		return (MedStdDepartment) MedStdCache.get(CacheType.MED_STD_DEPARTMENT, departmentId);*/
		return null;
	}

//	public void setMedStdDepartment(MedStdDepartment medStdDepartment) {
//		this.medStdDepartment = medStdDepartment;
//	}

	public String getIgName() {
		return this.igName;
	}

	public void setIgName(String igName) {
		this.igName = igName;
	}

	public Integer getIgOrder() {
		return this.igOrder;
	}

	public void setIgOrder(Integer igOrder) {
		this.igOrder = igOrder;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public static String getNameFromCache(long id){
		/*MedStdItemClass  c= (MedStdItemClass) MedStdCache.get(CacheType.MED_STD_ITEM_CLASS, id);
		if(c==null)
			return "";
		return c.getIgName();*/
		return null;
	}

	public List<MedStdItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MedStdItem> itemList) {
		this.itemList = itemList;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
}