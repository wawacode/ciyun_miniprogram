package com.centrin.ciyun.entity.med.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.centrin.ciyun.entity.med.MedExamAdvice;
import com.centrin.ciyun.entity.med.MedExamRpt;
import com.centrin.ciyun.entity.med.MedExamSummary;

/**
 * 微信体检报告详情
 * 
 */
public class WeixinMedrptDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private MedExamRpt medExamRpt;// 体检报告基本内容
	private List<MedExamSummary> summaryList;//小结信息
	private List<MedExamAdvice> adviceList;// 汇总信息
	private List<ItemGroup> groupList;  //报告详情
	private List<ExamItem> exceptionItems;//超标项列表
	private int exceptionItemNum;// 异常记录数
	private int itemNum;// 总记录数
	private Date rptDate;//报告日期
	private String corpName; //企业名称
	
	
	
	public static class ItemGroup implements java.io.Serializable{
	    private Integer groupOrder;
		private String groupName;
		private Long groupId;
		private int itemNum;
		private int exceptionItemNum;
		private List<ExamItem> itemList;
		public Integer getGroupOrder() {
			return groupOrder;
		}
		public void setGroupOrder(Integer groupOrder) {
			this.groupOrder = groupOrder;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
		public int getItemNum() {
			return itemNum;
		}
		public void setItemNum(int itemNum) {
			this.itemNum = itemNum;
		}
		public int getExceptionItemNum() {
			return exceptionItemNum;
		}
		public void setExceptionItemNum(int exceptionItemNum) {
			this.exceptionItemNum = exceptionItemNum;
		}
		public List<ExamItem> getItemList() {
			return itemList;
		}
		public void setItemList(List<ExamItem> itemList) {
			this.itemList = itemList;
		}
	}
	public int getExceptionItemNum() {
		return exceptionItemNum;
	}

	public void setExceptionItemNum(int exceptionItemNum) {
		this.exceptionItemNum = exceptionItemNum;
	}

	public MedExamRpt getMedExamRpt() {
		return medExamRpt;
	}

	public void setMedExamRpt(MedExamRpt medExamRpt) {
		this.medExamRpt = medExamRpt;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public List<MedExamAdvice> getAdviceList() {
		return adviceList;
	}

	public void setAdviceList(List<MedExamAdvice> adviceList) {
		this.adviceList = adviceList;
	}

	public Date getRptDate() {
		return rptDate;
	}

	public void setRptDate(Date rptDate) {
		this.rptDate = rptDate;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public List<MedExamSummary> getSummaryList() {
		return summaryList;
	}

	public void setSummaryList(List<MedExamSummary> summaryList) {
		this.summaryList = summaryList;
	}
	
	public List<ItemGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<ItemGroup> groupList) {
		this.groupList = groupList;
	}

	public List<ExamItem> getExceptionItems() {
		return exceptionItems;
	}

	public void setExceptionItems(List<ExamItem> exceptionItems) {
		this.exceptionItems = exceptionItems;
	}

	public static class ExamItem  implements java.io.Serializable{
		private String itemId; // 体检项标示
		private String itemName; // 体检项名称
		private String itemUnit;
		private String itemResult;
		private Integer itemResultType;
		private Double itemLow;
		private Double itemHight;
		private Integer isNormal;
		
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public String getItemUnit() {
			return itemUnit;
		}
		public void setItemUnit(String itemUnit) {
			this.itemUnit = itemUnit;
		}
		public String getItemResult() {
			return itemResult;
		}
		public void setItemResult(String itemResult) {
			this.itemResult = itemResult;
		}
		public Integer getItemResultType() {
			return itemResultType;
		}
		public void setItemResultType(Integer itemResultType) {
			this.itemResultType = itemResultType;
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
		public Integer getIsNormal() {
			return isNormal;
		}
		public void setIsNormal(Integer isNormal) {
			this.isNormal = isNormal;
		}
	}
}
