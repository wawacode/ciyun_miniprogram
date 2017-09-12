package com.centrin.ciyun.entity.med;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class MedExamItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;			//自增id,主键
	// private MedExamRpt medExamRpt;
	private long examRptId;		//体检报告的id
	private Long refOrganId;	//所属体检机构的引用组织id
	private String itemId;		//小项id
	private String itemName = null;	//体检机构中，原始项目的名字
	private Integer itemOrder;	//小项在体检机构里的显示顺序
	private String itemUnit = null;	//在体检机构里，小项的单位名称（如果有单位）
	private String itemResult = null;	//小项的原始结果
	private Integer itemResultType;	//体检机构里，项目的结果类型 定性，定量的说明 1=定量(数值型) 2=定性(结果值编号化:无排列顺序) 3=定性(結果值编号化:有排列顺序) 4=定性(文字说明)
	private Integer itemDecDigits;	//如果结果类型是数值型，那么显示时保留的有效小数位 当值为-1时，不做约束
	private Double itemLow;			//小项的下限
	private Double itemHight;		//小项的上限
	private Date examTime;			//检查时间
	private String doctor;			//检查医生
	private Integer transferState;	//小项在换转过程中，所处的状态 1 未转换  2 转换完成  3 人工修改过  4忽略此项目
	private Date transferDate;		//小项转化时间
	private String modifyUser;		//修改人
	private Date modifyTime;		//小项被修改的时间
	private Long stdItemId;			//转换后，对应的标准的小项id
	private Integer stdItemOrder;	//
	private String stdItemValueStr;	//
	private Long stdItemClassId;	//	
	private Integer stdItemClassOrder;//
	private Long stdDepartId;			//
	private Integer stdDepartOrder;		//
	private Double stdItemValueNum;		//
	private String stdUnitName;			//
	private Double stdItemLow;			//
	private Double stdItemHigh;			//
	private Integer stdItemResultType;	//
	private String stdRange = null;			//  这里改为存放api传来的不规则的上下限，不再存放标准项的上下限，以前都是空的20161101
	private Integer isNormal;			//
	private Date createTime;			//
	private String createUser;			//
	private int itemRecType;			//
	private Integer organOrder;			//
	private String organName = null;			//
	private String departmentName = null;		//
	private Long itemLinkStd;			//

	public String getStdItemName() {
		return itemName == null ? null : itemName;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		if (StringUtils.isEmpty(itemName)) {
			itemName = null;
		}
		this.itemName = itemName;
	}

	public Integer getItemOrder() {
		return this.itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public String getItemUnit() {
		return this.itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		if (StringUtils.isEmpty(itemUnit)) {
			itemUnit = null;
		}
		this.itemUnit = itemUnit;
	}

	public String getItemResult() {
		return this.itemResult;
	}

	public void setItemResult(String itemResult) {
		if (StringUtils.isEmpty(itemResult)) {
			itemResult = null;
		}
		this.itemResult = itemResult;
	}

	public Integer getItemResultType() {
		return this.itemResultType;
	}

	public void setItemResultType(Integer itemResultType) {
		this.itemResultType = itemResultType;
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

	public Double getItemHight() {
		return this.itemHight;
	}

	public void setItemHight(Double itemHight) {
		this.itemHight = itemHight;
	}

	public Date getExamTime() {
		return this.examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public Integer getTransferState() {
		return this.transferState;
	}

	public void setTransferState(Integer transferState) {
		this.transferState = transferState;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getStdItemId() {
		return this.stdItemId;
	}

	public void setStdItemId(Long stdItemId) {
		this.stdItemId = stdItemId;
	}

	public Integer getStdItemOrder() {
		return this.stdItemOrder;
	}

	public void setStdItemOrder(Integer stdItemOrder) {
		this.stdItemOrder = stdItemOrder;
	}

	public String getStdItemValueStr() {
		return this.stdItemValueStr;
	}

	public void setStdItemValueStr(String stdItemValueStr) {
		this.stdItemValueStr = stdItemValueStr;
	}

	public Long getStdItemClassId() {
		return this.stdItemClassId;
	}

	public void setStdItemClassId(Long stdItemClassId) {
		this.stdItemClassId = stdItemClassId;
	}

	public Integer getStdItemClassOrder() {
		return this.stdItemClassOrder;
	}

	public void setStdItemClassOrder(Integer stdItemClassOrder) {
		this.stdItemClassOrder = stdItemClassOrder;
	}

	public Long getStdDepartId() {
		return this.stdDepartId;
	}

	public void setStdDepartId(Long stdDepartId) {
		this.stdDepartId = stdDepartId;
	}

	public Integer getStdDepartOrder() {
		return this.stdDepartOrder;
	}

	public void setStdDepartOrder(Integer stdDepartOrder) {
		this.stdDepartOrder = stdDepartOrder;
	}

	public Double getStdItemValueNum() {
		return this.stdItemValueNum;
	}

	public void setStdItemValueNum(Double stdItemValueNum) {
		this.stdItemValueNum = stdItemValueNum;
	}

	public String getStdUnitName() {
		return this.stdUnitName;
	}

	public void setStdUnitName(String stdUnitName) {
		this.stdUnitName = stdUnitName;
	}

	public Double getStdItemLow() {
		return this.stdItemLow;
	}

	public void setStdItemLow(Double stdItemLow) {
		this.stdItemLow = stdItemLow;
	}

	public Double getStdItemHigh() {
		return this.stdItemHigh;
	}

	public void setStdItemHigh(Double stdItemHigh) {
		this.stdItemHigh = stdItemHigh;
	}

	public String getStdRange() {
		return this.stdRange;
	}

	public void setStdRange(String stdRange) {
		if (StringUtils.isEmpty(stdRange)) {
			stdRange = null;
		}
		this.stdRange = stdRange;
	}

	public Integer getIsNormal() {
		return this.isNormal;
	}

	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Long getRefOrganId() {
		return refOrganId;
	}

	public void setRefOrganId(Long refOrganId) {
		this.refOrganId = refOrganId;
	}

	public long getExamRptId() {
		return examRptId;
	}

	public void setExamRptId(long examRptId) {
		this.examRptId = examRptId;
	}

	public Integer getStdItemResultType() {
		return stdItemResultType;
	}

	public void setStdItemResultType(Integer stdItemResultType) {
		this.stdItemResultType = stdItemResultType;
	}

	public int getItemRecType() {
		return itemRecType;
	}

	public void setItemRecType(int itemRecType) {
		this.itemRecType = itemRecType;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		if (StringUtils.isEmpty(organName)) {
			organName = null;
		}
		this.organName = organName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		if (StringUtils.isEmpty(departmentName)) {
			departmentName = null;
		}
		this.departmentName = departmentName;
	}

	public Long getItemLinkStd() {
		return itemLinkStd;
	}

	public void setItemLinkStd(Long itemLinkStd) {
		this.itemLinkStd = itemLinkStd;
	}

	public Integer getOrganOrder() {
		return organOrder;
	}

	public void setOrganOrder(Integer organOrder) {
		this.organOrder = organOrder;
	}
}