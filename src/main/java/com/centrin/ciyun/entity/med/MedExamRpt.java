package com.centrin.ciyun.entity.med;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.centrin.ciyun.entity.hid.HidMedCorp;
import com.centrin.webbase.util.DataEncUtil;

public class MedExamRpt implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	// private MedImportLog medImportLog;
	private long importId;
	private String medCorpId;
	private String medCorpName;
	private String hmoId;
	private String contractName;
	private Date contractDate;
	private String name;
	private String nameEnc; // name的加密
	private Date dayOfBirth;
	private int medAge;
	private int gender;
	private String telephone;
	private String telephoneEnc;  // telephone的加密
	private int    idType = 1;
	private String idNo;
	private String idNoEnc; // idNo的加密
	private String medPersonId;
	private String medExamNo;
	private String medExamCode;
	private String medExamType;
	private Date medDate;
	private String preMedDate;
	private int auditState;
	private String personId;
	private String entName;
	private int isPublic;
	private int transformState;
	private Date createTime;
	private String createUser;
	private Date modifyTime;
	private String modifyUser;
	private String remarks;
	private Integer medYear;
	private Integer recordFlag;  //记录结果集标志位
	private long rptTemplateId = -1L;
	public MedExamRpt(){}
	
	public MedExamRpt(Long id){
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public MedImportLog getMedImportLog() { return this.medImportLog; }
	 * 
	 * public void setMedImportLog(MedImportLog medImportLog) {
	 * this.medImportLog = medImportLog; }
	 */

	public String getMedCorpId() {
		return this.medCorpId;
	}

	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}

	public String getHmoId() {
		return this.hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
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

	public Date getDayOfBirth() {
		return this.dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public int getMedAge() {
		return this.medAge;
	}

	public void setMedAge(int medAge) {
		this.medAge = medAge;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public String getMedPersonId() {
		return this.medPersonId;
	}

	public void setMedPersonId(String medPersonId) {
		this.medPersonId = medPersonId;
	}

	public String getMedExamNo() {
		return this.medExamNo;
	}

	public void setMedExamNo(String medExamNo) {
		this.medExamNo = medExamNo;
	}

	public String getMedExamCode() {
		return this.medExamCode;
	}

	public void setMedExamCode(String medExamCode) {
		this.medExamCode = medExamCode;
	}

	public String getMedExamType() {
		return this.medExamType;
	}

	public void setMedExamType(String medExamType) {
		this.medExamType = medExamType;
	}

	public Date getMedDate() {
		return this.medDate;
	}

	public void setMedDate(Date medDate) {
		if(medDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			setPreMedDate(sdf.format(medDate));
		}
		this.medDate = medDate;
	}

	public String getPreMedDate() {
		return preMedDate;
	}

	public void setPreMedDate(String preMedDate) {
		this.preMedDate = preMedDate;
	}

	public int getAuditState() {
		return this.auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public int getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
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

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getTransformState() {
		return transformState;
	}

	public void setTransformState(int transformState) {
		this.transformState = transformState;
	}
	
	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		if (importId != null) {
			this.importId = importId;
		} else {
			this.importId = -1L;
		}
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public void setImportId(long importId) {
		this.importId = importId;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getMedCorpName() {
		try {
			if(StringUtils.isEmpty(medCorpName) && StringUtils.isNotEmpty(medCorpId) ){
				medCorpName = HidMedCorp.getNameFromCache(medCorpId);
			}
		} catch (Exception e) {
			if(StringUtils.isEmpty(medCorpName)){
				medCorpName = medCorpId;
			}
		}
		return medCorpName;
	}

	public void setMedCorpName(String medCorpName) {
		this.medCorpName = medCorpName;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public Integer getMedYear() {
		return medYear;
	}

	public void setMedYear(Integer medYear) {
		this.medYear = medYear;
	}
	public Integer getRecordFlag() {
		return recordFlag;
	}

	public void setRecordFlag(Integer recordFlag) {
		this.recordFlag = recordFlag;
	}

	public String getNameEnc() {
		return nameEnc;
	}

	public void setNameEnc(String nameEnc) {
		this.nameEnc = nameEnc;
	}

	public String getTelephoneEnc() {
		return telephoneEnc;
	}

	public void setTelephoneEnc(String telephoneEnc) {
		this.telephoneEnc = telephoneEnc;
	}

	public String getIdNoEnc() {
		return idNoEnc;
	}

	public void setIdNoEnc(String idNoEnc) {
		this.idNoEnc = idNoEnc;
	}

	public long getRptTemplateId() {
		return rptTemplateId;
	}

	public void setRptTemplateId(long rptTemplateId) {
		this.rptTemplateId = rptTemplateId;
	}

	
}