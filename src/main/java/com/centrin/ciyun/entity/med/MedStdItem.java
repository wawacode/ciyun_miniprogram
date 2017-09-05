package com.centrin.ciyun.entity.med;

import java.util.Date;

public class MedStdItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
//	private transient MedStdItemUnit medStdItemUnit;
//	private transient MedStdItemClass medStdItemClass;
	private Long unitId;
	private long classId;
	private String itemName;
	private String className;
	private long departmentId;
	private String departmentName;
	private Integer resultType;
	private Integer itemOrder;
	private Integer itemDecDigits;
	private Double itemLow;
	private Double itemHigh;
	private String itemRange;
	private Integer useStdRange;
	private String viewName;
	private String examGoal;
	private String abnormalExpression;
	private String remarks;
	private Integer isNewValue;
	private Date createTime;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public MedStdItemUnit getMedStdItemUnit() {
		/*if (unitId != null && unitId > 0)
			return (MedStdItemUnit) MedStdCache.get(
					CacheType.MED_STD_ITEM_UNIT, unitId);*/
		return null;
	}

	public String getUnitName() {
		/*if (unitId != null && unitId > 0) {
			MedStdItemUnit unit = (MedStdItemUnit) MedStdCache.get(
					CacheType.MED_STD_ITEM_UNIT, unitId);
			return unit == null ? "" : unit.getUnitName();
		}*/
		return "";
	}

//	public void setMedStdItemUnit(MedStdItemUnit medStdItemUnit) {
//		this.medStdItemUnit = medStdItemUnit;
//	}
	public MedStdItemClass getMedStdItemClass() {
		/*if (classId > 0)
			return (MedStdItemClass) MedStdCache.get(
					CacheType.MED_STD_ITEM_CLASS, classId);*/
		return null;
	}

//	public void setMedStdItemClass(MedStdItemClass medStdItemClass) {
//		this.medStdItemClass = medStdItemClass;
//	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getResultType() {
		return this.resultType;
	}

	public void setResultType(Integer resultType) {
		this.resultType = resultType;
	}

	public Integer getItemOrder() {
		return this.itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public Integer getItemDecDigits() {
		return this.itemDecDigits;
	}

	public void setItemDecDigits(Integer itemDecDigits) {
		this.itemDecDigits = itemDecDigits;
	}

	public Double getItemLow() {
		return this.itemLow;
	}

	public void setItemLow(Double itemLow) {
		this.itemLow = itemLow;
	}

	public Double getItemHigh() {
		return this.itemHigh;
	}

	public void setItemHigh(Double itemHigh) {
		this.itemHigh = itemHigh;
	}

    public String getItemRange()
    {
    	return itemRange;
    }

    public void setItemRange(String itemRange)
    {
    	this.itemRange = itemRange;
    }

	public String getAbnormalExpression() {
		return this.abnormalExpression;
	}

	public void setAbnormalExpression(String abnormalExpression) {
		this.abnormalExpression = abnormalExpression;
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

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Integer getUseStdRange() {
		return useStdRange;
	}

	public void setUseStdRange(Integer useStdRange) {
		this.useStdRange = useStdRange;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getExamGoal() {
		return examGoal;
	}

	public void setExamGoal(String examGoal) {
		this.examGoal = examGoal;
	}

	public Integer getIsNewValue() {
		return isNewValue;
	}

	public void setIsNewValue(Integer isNewValue) {
		this.isNewValue = isNewValue;
	}
	
//	private void writeObject(java.io.ObjectOutputStream stream) throws IOException
//	 {
//        System.out.println("medstditem自己控制反序列化的过程");
//        stream.defaultWriteObject();
//	  }
//	 
//	private void readObject(java.io.ObjectInputStream stream) throws IOException , ClassNotFoundException{
//		 System.out.println("medstditem自己控制序列化的过程");
//		 stream.defaultReadObject();
//		 stream.readObject();
//		 medStdItemClass = (MedStdItemClass) stream.readObject();
//		 medStdItemUnit = (MedStdItemUnit) stream.readObject();
//	 }

}