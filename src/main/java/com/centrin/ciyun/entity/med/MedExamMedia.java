package com.centrin.ciyun.entity.med;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.centrin.webbase.CommonData;

public class MedExamMedia implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fileId;
	private long examRptId;
	private String fileExtension;
	private String departmentName;
	private String refOrganId;
	private String organName;
	private long stdItemClassId;
	private Date createTime;
	
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public long getExamRptId() {
		return examRptId;
	}
	public void setExamRptId(long examRptId) {
		this.examRptId = examRptId;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRefOrganId() {
		return refOrganId;
	}
	public void setRefOrganId(String refOrganId) {
		this.refOrganId = refOrganId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public long getStdItemClassId() {
		return stdItemClassId;
	}
	public void setStdItemClassId(long stdItemClassId) {
		this.stdItemClassId = stdItemClassId;
	}
	public String getMediaUrl(){
		/*String url = "";
		if (StringUtils.isNotEmpty(fileId)) {
			String tmpFileName = fileId;
			if (fileId.indexOf(".") == -1) {
				tmpFileName = fileId + "." + fileExtension;
			}
			url = CommonData.getWebServerSmallUrl(EFileUploadServiceType.EXAM_RPT_MEDIA, tmpFileName);
		}
		return url;*/
		return "";
	}
	
}
