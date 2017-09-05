package com.centrin.ciyun.entity.med.vo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrganInfoEntity  implements java.io.Serializable {
	private int rptMode = 0; // 0:不针对该值进行处理 1:科室 -大项-小项 2：科室-小项 3：大项-小项
	private String organName;// 科室或项目组名称
	private int organOrder = 0; // 排序
	private Map<String, OrganInfoEntity> clazzItemMap = new LinkedHashMap<String, OrganInfoEntity>(); // 大项列表
	private List<OrganInfoEntity> clazzItemList = new LinkedList<OrganInfoEntity>(); // 大项列表
	private List<String> mediaList = new LinkedList<String>();
	private List<SummaryRst> summarys = new LinkedList<SummaryRst>();  //展示在详情中的小结
	private List<ItemRst> items = new LinkedList<ItemRst>();

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Map<String, OrganInfoEntity> getClazzItemMap() {
		return clazzItemMap;
	}

	public void setClazzItemMap(Map<String, OrganInfoEntity> clazzItemMap) {
		this.clazzItemMap = clazzItemMap;
	}

	public List<OrganInfoEntity> getClazzItemList() {
		return clazzItemList;
	}

	public void setClazzItemList(List<OrganInfoEntity> clazzItemList) {
		this.clazzItemList = clazzItemList;
	}

	public List<String> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<String> mediaList) {
		this.mediaList = mediaList;
	}

	public List<ItemRst> getItems() {
		return items;
	}

	public void setItems(List<ItemRst> items) {
		this.items = items;
	}


	public List<SummaryRst> getSummarys() {
		return summarys;
	}

	public void setSummarys(List<SummaryRst> summarys) {
		this.summarys = summarys;
	}
 

	public int getRptMode() {
		return rptMode;
	}

	public void setRptMode(int rptMode) {
		this.rptMode = rptMode;
	}

	public int getOrganOrder() {
		return organOrder;
	}

	public void setOrganOrder(int organOrder) {
		this.organOrder = organOrder;
	}
	
	public static class SummaryRst  implements java.io.Serializable{
		private Long examRptId;
		private String summary;
		private String doctor;
		private String examTime;
		private String organName;
		private String departmentName;
		private String revDoctor;
		private String revTime;
		public Long getExamRptId() {
			return examRptId;
		}
		public void setExamRptId(Long examRptId) {
			this.examRptId = examRptId;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getDoctor() {
			return doctor;
		}
		public void setDoctor(String doctor) {
			this.doctor = doctor;
		}
		public String getExamTime() {
			return examTime;
		}
		public void setExamTime(String examTime) {
			this.examTime = examTime;
		}
		public String getOrganName() {
			return organName;
		}
		public void setOrganName(String organName) {
			this.organName = organName;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		public String getRevDoctor() {
			return revDoctor;
		}
		public void setRevDoctor(String revDoctor) {
			this.revDoctor = revDoctor;
		}
		public String getRevTime() {
			return revTime;
		}
		public void setRevTime(String revTime) {
			this.revTime = revTime;
		}
		
	}

	public static class ItemRst implements java.io.Serializable{
		private String itemId;
		private String itemName;
		private String itemResult;
		private String limit;
		private String stdItemId;
		private String stdValue;
		private int type; // 0历史数据展示为图形,1代表展示文字2. 历史数据展示为相对值图形
		private int over;
		private String stdRange; //上下限是类似>,<等之类的值
		private String unit;//单位

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

		public String getItemResult() {
			return itemResult;
		}

		public void setItemResult(String itemResult) {
			this.itemResult = itemResult;
		}

		public String getLimit() {
			return limit;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public String getStdItemId() {
			return stdItemId;
		}

		public void setStdItemId(String stdItemId) {
			this.stdItemId = stdItemId;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getOver() {
			return over;
		}

		public void setOver(int over) {
			this.over = over;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getStdValue() {
			return stdValue;
		}

		public void setStdValue(String stdValue) {
			this.stdValue = stdValue;
		}

		public String getStdRange() {
			return stdRange;
		}

		public void setStdRange(String stdRange) {
			this.stdRange = stdRange;
		}
		
	}

}
