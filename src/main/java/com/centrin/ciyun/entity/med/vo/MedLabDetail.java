package com.centrin.ciyun.entity.med.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.centrin.ciyun.entity.med.MedLabItem;
import com.centrin.ciyun.entity.med.MedLabSheet;

@SuppressWarnings("serial")
public class MedLabDetail  implements Serializable {
	private MedLabSheet medLabSheet;
	private Map<String, LabDetail> labDetails = new LinkedHashMap<String, LabDetail>();// 每个体检部门信息以及对应部门的体检项目
	public MedLabSheet getMedLabSheet() {
		return medLabSheet;
	}
	public void setMedLabSheet(MedLabSheet medLabSheet) {
		this.medLabSheet = medLabSheet;
	}
	 
	public Map<String, LabDetail> getLabDetails() {
		return labDetails;
	}
	public void setLabDetails(Map<String, LabDetail> labDetails) {
		this.labDetails = labDetails;
	}

	public static class LabDetail  implements java.io.Serializable{
		private int labMode = 0; //1:科室-大项-小项  2：科室-小项  3：大项-小项,,,一份报告中可能多种层次的结构都有
		private String organName; //科室或大项目名称
		private List<MedLabItem> itemList = new ArrayList<MedLabItem>();   //rptLevel为1时，这里没有值
		private Map<String, LabDetail> itemClassList = new LinkedHashMap<String, LabDetail>(); // 大项列表，k为大项，v为图片和小项

		public int getLabMode() {
			return labMode;
		}

		public void setLabMode(int labMode) {
			this.labMode = labMode;
		}

		public List<MedLabItem> getItemList() {
			return itemList;
		}

		public void setItemList(List<MedLabItem> itemList) {
			this.itemList = itemList;
		}

		public Map<String, LabDetail> getItemClassList() {
			return itemClassList;
		}

		public void setItemClassList(Map<String, LabDetail> itemClassList) {
			this.itemClassList = itemClassList;
		}

		public String getOrganName() {
			return organName;
		}

		public void setOrganName(String organName) {
			this.organName = organName;
		}
	}
}
