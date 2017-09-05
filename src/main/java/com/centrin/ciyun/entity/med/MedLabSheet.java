package com.centrin.ciyun.entity.med;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.centrin.ciyun.common.util.PrettyDateFormat;
import com.centrin.ciyun.entity.hid.HidMedCorp;
import com.centrin.webbase.util.DataEncUtil;
/**
 * 航天化验单基本信息
 * @author liqiangbiao
 * 2016-5-19
 */

public class MedLabSheet  implements java.io.Serializable{
	private Long id;
	private String hmoId;
	private String medCorpId;
	private String patientId;//患者编号（或门诊号）：0000496910
	private String telephone;//电话：13693233226
	private String telephoneEnc;//telephone的加密
	private String personId;
	private int patientType;//类别：门诊
	private Integer patientAge;//病人年龄：39岁
	private String name;//患者姓名：冯玉珂
	private String nameEnc;  //name的加密
	private Integer gender; //
	private int priority;//检验级别：普通，加急
	private String barcodeNum;//条形码号：1000221655
	private String diagInfo;//诊断：非毒性单个甲状腺结节
	private String specimen;//样本：全血
	private Date specimenTime;//取样时间：2014/8/9 8:43:30
	private int resultStatus;//检查状态：完成
	private String billingDept;//开单科室：健康管理门诊
	private String billingDoctor;//开单医生：景力
	private String verifiedDoctor;//核准医生：孙靖
	private Date verifiedTime;//核准时间:2014/8/9 9:15:03
	private String transcriptionDoctor;//收样医生：姜展朋
	private int idType = 1;
	private String idNo;//身份证：230305197503184810
	private String idNoEnc;////idNo的加密
	private String formNo;//测试表单号：25
	private String bedNo;//病床号：
	private String inpatientNo;//住院病号：363523
	private String createUser;
	private Date createTime;//记录创建时间
	private String modifyUser;
	private Date modifyTime;

	
	@Override
	public String toString() {
		return "MedLabSheet [id=" + id + ", hmoId=" + hmoId + ", medCorpId="
				+ medCorpId + ", patientId=" + patientId + ", telephone="
				+ telephone + ", personId=" + personId + ", patientType="
				+ patientType + ", patientAge=" + patientAge + ", name=" + name
				+ ", gender=" + gender + ", priority=" + priority
				+ ", barcodeNum=" + barcodeNum + ", diagInfo=" + diagInfo
				+ ", specimen=" + specimen + ", specimenTime=" + specimenTime
				+ ", resultStatus=" + resultStatus + ", billingDept="
				+ billingDept + ", billingDoctor=" + billingDoctor
				+ ", verifiedDoctor=" + verifiedDoctor + ", verifiedTime="
				+ verifiedTime + ", transcriptionDoctor=" + transcriptionDoctor
				+ ", idType=" + idType + ", idNo=" + idNo + ", formNo="
				+ formNo + ", bedNo=" + bedNo + ", inpatientNo=" + inpatientNo
				+ ", createUser=" + createUser + ", createTime=" + createTime
				+ ", modifyUser=" + modifyUser + ", modifyTime=" + modifyTime
				+ "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHmoId() {
		return hmoId;
	}
	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}
	public String getMedCorpId() {
		return medCorpId;
	}
	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getTelephone() {
		if(StringUtils.isEmpty(this.telephoneEnc))
			return null;
		this.telephone= com.centrin.webbase.util.DataEncUtil.decstr(telephoneEnc);
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		if(!StringUtils.isEmpty(telephone))
			this.telephoneEnc=DataEncUtil.encstr(telephone);
		this.telephone = telephone;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public int getPatientType() {
		return patientType;
	}
	public void setPatientType(int patientType) {
		this.patientType = patientType;
	}
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public String getName() {
		if(StringUtils.isEmpty(this.nameEnc))
			return null;
		this.name= com.centrin.webbase.util.DataEncUtil.decstr(nameEnc);
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
		if(!StringUtils.isEmpty(name))
			this.nameEnc=DataEncUtil.encstr(name);
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getBarcodeNum() {
		return barcodeNum;
	}
	public void setBarcodeNum(String barcodeNum) {
		this.barcodeNum = barcodeNum;
	}
	public String getDiagInfo() {
		return diagInfo;
	}
	public void setDiagInfo(String diagInfo) {
		this.diagInfo = diagInfo;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public Date getSpecimenTime() {
		return specimenTime;
	}
	public void setSpecimenTime(Date specimenTime) {
		this.specimenTime = specimenTime;
	}
	public int getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getBillingDept() {
		return billingDept;
	}
	public void setBillingDept(String billingDept) {
		this.billingDept = billingDept;
	}
	public String getBillingDoctor() {
		return billingDoctor;
	}
	public void setBillingDoctor(String billingDoctor) {
		this.billingDoctor = billingDoctor;
	}
	public String getVerifiedDoctor() {
		return verifiedDoctor;
	}
	public void setVerifiedDoctor(String verifiedDoctor) {
		this.verifiedDoctor = verifiedDoctor;
	}
	public Date getVerifiedTime() {
		return verifiedTime;
	}
	public void setVerifiedTime(Date verifiedTime) {
		this.verifiedTime = verifiedTime;
	}
	
	public String getVerifiedTimeString() {
		if(verifiedTime==null) {
			return "体检日期为空";
		}
		return PrettyDateFormat.dateToString(verifiedTime, PrettyDateFormat.sdf);
	}
	
	public String getTranscriptionDoctor() {
		return transcriptionDoctor;
	}
	public void setTranscriptionDoctor(String transcriptionDoctor) {
		this.transcriptionDoctor = transcriptionDoctor;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		if(StringUtils.isEmpty(this.idNoEnc))
			return null;
		this.idNo= com.centrin.webbase.util.DataEncUtil.decstr(idNoEnc);
		return this.idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
		if(!StringUtils.isEmpty(idNo))
			this.idNoEnc=DataEncUtil.encstr(idNo);
	}
	public String getFormNo() {
		return formNo;
	}
	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getInpatientNo() {
		return inpatientNo;
	}
	public void setInpatientNo(String inpatientNo) {
		this.inpatientNo = inpatientNo;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getMedCorpName() {
		String medCorpName = "";
		try {
			if(StringUtils.isNotEmpty(medCorpId) ){
				medCorpName = HidMedCorp.getNameFromCache(medCorpId);
			}
		} catch (Exception e) {
			 
		}
		return medCorpName;
	}
	public String getTelephoneEnc() {
		return telephoneEnc;
	}
	public void setTelephoneEnc(String telephoneEnc) {
		this.telephoneEnc = telephoneEnc;
	}
	public String getNameEnc() {
		return nameEnc;
	}
	public void setNameEnc(String nameEnc) {
		this.nameEnc = nameEnc;
	}
	public String getIdNoEnc() {
		return idNoEnc;
	}
	public void setIdNoEnc(String idNoEnc) {
		this.idNoEnc = idNoEnc;
	}
}
