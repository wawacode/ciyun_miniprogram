package com.centrin.ciyun.entity.bus;

import java.util.Date;
import java.util.List;

import com.centrin.ciyun.entity.med.MedExamOrgan;
import com.centrin.ciyun.entity.med.MedExamSummary;

public class SignsMarkEntity  implements java.io.Serializable{
	private String personId; // 人员ID
	private Long medrptId; // 体检报告ID
	private String entId; // 企业ID
	private String userName; // 用户姓名
	private Date medDate; // 体检日期
	private String strMedDate; //体检日期字符串
	private String medExamType;// 体检类型
	private String medCorpId; //体检机构ID
	private int gender; //人员性别
	private List<MedExamOrgan> listMedExamOrgan;
	
	private List<MedExamSummary> relationExamSummary;

	//private List<PerPersonDiseaseDetail> details;
	
	//private List<PerDoctorCommonSigns> listCommonSigns;
	
	private List<Object[]> listObject;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getMedDate() {
		return medDate;
	}

	public void setMedDate(Date medDate) {
		this.medDate = medDate;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Long getMedrptId() {
		return medrptId;
	}

	public void setMedrptId(Long medrptId) {
		this.medrptId = medrptId;
	}

	public List<MedExamOrgan> getListMedExamOrgan() {
		return listMedExamOrgan;
	}

	public void setListMedExamOrgan(List<MedExamOrgan> listMedExamOrgan) {
		this.listMedExamOrgan = listMedExamOrgan;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	/*public List<PerPersonDiseaseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PerPersonDiseaseDetail> details) {
		this.details = details;
	}*/

	public String getMedExamType() {
		return medExamType;
	}

	public void setMedExamType(String medExamType) {
		this.medExamType = medExamType;
	}

	public List<MedExamSummary> getRelationExamSummary() {
		return relationExamSummary;
	}

	public void setRelationExamSummary(List<MedExamSummary> relationExamSummary) {
		this.relationExamSummary = relationExamSummary;
	}

	public String getMedCorpId() {
		return medCorpId;
	}

	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	/*public List<PerDoctorCommonSigns> getListCommonSigns() {
		return listCommonSigns;
	}

	public void setListCommonSigns(List<PerDoctorCommonSigns> listCommonSigns) {
		this.listCommonSigns = listCommonSigns;
	}*/

	public String getStrMedDate() {
		return strMedDate;
	}

	public void setStrMedDate(String strMedDate) {
		this.strMedDate = strMedDate;
	}

	public List<Object[]> getListObject() {
		return listObject;
	}

	public void setListObject(List<Object[]> listObject) {
		this.listObject = listObject;
	}
	
}
