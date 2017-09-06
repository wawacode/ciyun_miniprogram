package com.centrin.ciyun.entity.med;

import java.util.Date;
import java.util.List;

public class MedStdDepartment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String departmentName;
	private Integer departmentOrder;
	private String remarks;
	private Date createTime;
	private List<MedStdItem> itemList;
	
	public MedStdDepartment(){}
	
	public MedStdDepartment(Long id, String departmentName){
		this.id = id;
		this.departmentName = departmentName;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentOrder() {
		return this.departmentOrder;
	}

	public void setDepartmentOrder(Integer departmentOrder) {
		this.departmentOrder = departmentOrder;
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

	public List<MedStdItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MedStdItem> itemList) {
		this.itemList = itemList;
	}
	
	public static String getNameFromCache(long id){
		/*MedStdDepartment  dept= (MedStdDepartment) MedStdCache.get(CacheType.MED_STD_DEPARTMENT, id);
		if(dept==null)
			return "";
		return dept.getDepartmentName();*/
		return "";
	}
}