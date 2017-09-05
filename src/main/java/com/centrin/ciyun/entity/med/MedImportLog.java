package com.centrin.ciyun.entity.med;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.centrin.ciyun.entity.hid.HidMedCorp;
import com.centrin.ciyun.service.interfaces.hid.IDubboHidMedCorpService;
import com.centrin.webbase.WebContextWrapper;

public class MedImportLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private HidMedCorp hidMedCorp;
	private String medCorpId;
	private String hmoId;
	private String createUser;
	private String comments;
	private Integer importWay;
	private String ip;
	private String remarks;
	private Date createTime;
	private Integer importStatus;
	private String fileId;
	
	public MedImportLog(){}

	public MedImportLog(Long id){
		this.id = id;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HidMedCorp getHidMedCorp() {
		if(null == hidMedCorp && StringUtils.isNotEmpty(medCorpId)){
			try {
				IDubboHidMedCorpService dubboHidMedCorpService =(IDubboHidMedCorpService) WebContextWrapper.getBean("iDubboHidMedCorpService");
				hidMedCorp = dubboHidMedCorpService.queryHidByMedCorpId(medCorpId);
			} catch (Exception e) {
				hidMedCorp = null;
			}
		}
		return this.hidMedCorp;
	}

	public void setHidMedCorp(HidMedCorp hidMedCorp) {
		this.hidMedCorp = hidMedCorp;
	}

	public String getHmoId() {
		return this.hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getImportWay() {
		return this.importWay;
	}

	public void setImportWay(Integer importWay) {
		this.importWay = importWay;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getImportStatus() {
		return importStatus;
	}

	public void setImportStatus(Integer importStatus) {
		this.importStatus = importStatus;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getMedCorpId() {
		if(StringUtils.isEmpty(medCorpId) && hidMedCorp!=null && StringUtils.isNotEmpty(hidMedCorp.getMedCorpId())){
			medCorpId = hidMedCorp.getMedCorpId();
		}
		return medCorpId;
	}

	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}
}