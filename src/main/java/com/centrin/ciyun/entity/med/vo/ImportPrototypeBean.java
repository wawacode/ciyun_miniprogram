package com.centrin.ciyun.entity.med.vo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.centrin.ciyun.entity.med.MedExamAdvice;
import com.centrin.ciyun.entity.med.MedExamItem;
import com.centrin.ciyun.entity.med.MedExamOrgan;
import com.centrin.ciyun.entity.med.MedExamSummary;
import com.centrin.ciyun.entity.med.MedImportLog;

public class ImportPrototypeBean  implements java.io.Serializable {
	private String medCorpId;				//体检中心Id	
	private String medCorpName;				//体检中心名称
	private String gender;					//性别 	1
	private String examCode;				//体检号 	1
	private Integer idType=1;               //证件类型  默认 1:身份证号 	1
	private String birty;					//出生日期 	1
	private String idNo;					//身份证  	1
	private String corpId;					//企业Id		2
	private String corpName;				//企业名称		2
	private String contractName;			//企业合同名称	2
	private String contractDate;			//企业合同日期	2
	private String examNo;					//预约号 	2
	private String name;					//客户名称
	private Integer age;						//年龄	 2
	private String telephone;				//手机号 	2
	private String emoloyeeNo;				//员工号	2
	private String department;				//部门 	2
	private String post;					//职务  	2
	private String medDate;					//报告日期		5
	private Integer medYear;				//体检年份，统计所用
	
	private int jyjcItemName = 2; //唐山新华健康小项中是否含有基因检测的项目  1:有 2：没有
	
	private Map<MedExamOrgan,Set<MedExamOrgan>> organMap;
	private List<MedExamItem> itemList;
	private List<MedExamSummary> summaryList;
	private List<MedExamAdvice> adviceList;
	private MedImportLog medImportLog;
	private List<MedExamMediaVo> mediaList;
	private JSONArray extrasJson; //扩展字段
	
	
	
	
	/*private String examTime;				//检查日期		2 3 4
	private String recordType;				//记录类型		3 4 5
	
	private String igId;					//项目组Id	3
	private String igName;					//项目组名称	3
	private Long igOrder;					//项目组顺序
	private String itemId;					//小项目Id	3
	private String itemName;				//小项目名称	3
	private Integer itemOrder;				//小项目顺序 	3
	private String itemResult;				//小项目结果	3
	private String unit;					//单位		3
	private String itemResultType;			//结果类型		3
	private Double hight;					//上限		3
	private Double low;						//下限		3
	private String doctor;					//检查医生		3
	private Long dempartmentOrder;			//科室顺序
	private String dempartmentId;			//科室ID			4
	private String dempartmentName;			//科室名称		3	4
	
	private String summary;					//科室小结		4 5
	private String dempartmentDoctor;		//科室小结医生	4
	private String advice;					//建议        		5
	private String doctorBegin;				//初检医生		5
	private String doctorFirst;				//终检医生		5
	private String medDate;					//报告日期		5
*/	
	
	public Map<MedExamOrgan, Set<MedExamOrgan>> getOrganMap() {
		return organMap;
	}
	public void setOrganMap(Map<MedExamOrgan, Set<MedExamOrgan>> organMap) {
		this.organMap = organMap;
	}
	public List<MedExamItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<MedExamItem> itemList) {
		this.itemList = itemList;
	}
	public List<MedExamSummary> getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(List<MedExamSummary> summaryList) {
		this.summaryList = summaryList;
	}
	public List<MedExamAdvice> getAdviceList() {
		return adviceList;
	}
	public void setAdviceList(List<MedExamAdvice> adviceList) {
		this.adviceList = adviceList;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (StringUtils.isNotEmpty(gender)) {
			if("1".equals(gender.trim()) || "男".equals(gender.trim())){
				this.gender = "1";
			}else if("2".equals(gender.trim()) || "女".equals(gender.trim())){
				this.gender = "2";
			}else if("3".equals(gender.trim()) || "未知".equals(gender.trim())){
				this.gender = "3";
			}
		} else {
			this.gender = "3";
		}
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBirty() {
		return birty;
	}
	public void setBirty(String birty) {
		this.birty = birty;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/*public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}*/
	public String getEmoloyeeNo() {
		return emoloyeeNo;
	}
	public void setEmoloyeeNo(String emoloyeeNo) {
		this.emoloyeeNo = emoloyeeNo;
	}
	/*public String getDempartmentId() {
		return dempartmentId;
	}
	public void setDempartmentId(String dempartmentId) {
		this.dempartmentId = dempartmentId;
	}
	public String getDempartmentName() {
		return dempartmentName;
	}
	public void setDempartmentName(String dempartmentName) {
		this.dempartmentName = dempartmentName;
	}
	public Long getDempartmentOrder() {
		return dempartmentOrder;
	}
	public void setDempartmentOrder(Long dempartmentOrder) {
		this.dempartmentOrder = dempartmentOrder;
	}
	public String getIgId() {
		return igId;
	}
	public void setIgId(String igId) {
		this.igId = igId;
	}
	public String getIgName() {
		return igName;
	}
	public void setIgName(String igName) {
		this.igName = igName;
	}
	public Long getIgOrder() {
		return igOrder;
	}
	public void setIgOrder(Long igOrder) {
		this.igOrder = igOrder;
	}
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
	public Integer getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getItemResultType() {
		return itemResultType;
	}
	public void setItemResultType(String itemResultType) {
		this.itemResultType = itemResultType;
	}
	public Double getHight() {
		return hight;
	}
	public void setHight(Double hight) {
		this.hight = hight;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDempartmentDoctor() {
		return dempartmentDoctor;
	}
	public void setDempartmentDoctor(String dempartmentDoctor) {
		this.dempartmentDoctor = dempartmentDoctor;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getDoctorBegin() {
		return doctorBegin;
	}
	public void setDoctorBegin(String doctorBegin) {
		this.doctorBegin = doctorBegin;
	}
	public String getDoctorFirst() {
		return doctorFirst;
	}
	public void setDoctorFirst(String doctorFirst) {
		this.doctorFirst = doctorFirst;
	}*/
	public String getMedDate() {
		return medDate;
	}
	public void setMedDate(String medDate) {
		this.medDate = medDate;
	}
	/*public String getItemResult() {
		return itemResult;
	}
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}*/

	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getMedCorpId() {
		return medCorpId;
	}
	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}
	public String getMedCorpName() {
		return medCorpName;
	}
	public void setMedCorpName(String medCorpName) {
		this.medCorpName = medCorpName;
	}
	public Integer getMedYear() {
		return medYear;
	}
	public void setMedYear(Integer medYear) {
		this.medYear = medYear;
	}
	public MedImportLog getMedImportLog() {
		return medImportLog;
	}
	public void setMedImportLog(MedImportLog medImportLog) {
		this.medImportLog = medImportLog;
	}
	public List<MedExamMediaVo> getMediaList() {
		return mediaList;
	}
	public void setMediaList(List<MedExamMediaVo> mediaList) {
		this.mediaList = mediaList;
	}
	public JSONArray getExtrasJson() {
		return extrasJson;
	}
	public void setExtrasJson(JSONArray extrasJson) {
		this.extrasJson = extrasJson;
	}
	public int getJyjcItemName() {
		return jyjcItemName;
	}
	public void setJyjcItemName(int jyjcItemName) {
		this.jyjcItemName = jyjcItemName;
	}
}