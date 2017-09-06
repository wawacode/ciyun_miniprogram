package com.centrin.ciyun.entity.med.vo;

import java.util.ArrayList;
import java.util.List;

import com.centrin.ciyun.entity.med.MedExamOrgan;

public class RptOrganMapVo  implements java.io.Serializable {
	private MedExamOrgan parentExamOrgan;
	private List<MedExamOrgan> childExamOrganList = new ArrayList<MedExamOrgan>();
	public MedExamOrgan getParentExamOrgan() {
		return parentExamOrgan;
	}
	public void setParentExamOrgan(MedExamOrgan parentExamOrgan) {
		this.parentExamOrgan = parentExamOrgan;
	}
	public List<MedExamOrgan> getChildExamOrganList() {
		return childExamOrganList;
	}
	public void setChildExamOrganList(List<MedExamOrgan> childExamOrganList) {
		this.childExamOrganList = childExamOrganList;
	}
	
} 
