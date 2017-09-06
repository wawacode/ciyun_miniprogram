package com.centrin.ciyun.entity.med;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;

import com.centrin.webbase.CommonData;
import com.centrin.webbase.util.DataEncUtil;

/**
 * 
 * 
 * 
 * MedExamRptSynthetic entity. @author MyEclipse Persistence Tools
 */
public class MedExamRptSynthetic implements java.io.Serializable {

	private static final long serialVersionUID = -3106873937874893086L;
	private String rptId;
	private String personId;
	private String tableName;
	private String realrptId;
	private int format;
	private int type;
	private int childType;
	private String orgName;
	private String execDate;
	private String name;
	private int fileNum;
	private String relationRptId;
	private int readFlag;
	private Date readTime;
	private String readFromChannel;
	private int transFlag;
	private int signFlag;
	private String url;
	private Date rptCreateTime;
	private Date createTime;
	private String createUser;
	private String remarks;
	private String hmoId;
	private String userName;
	private String userNameEnc;
	private int interpretState;


	public String getRptId() {
		return this.rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRealrptId() {
		return this.realrptId;
	}

	public void setRealrptId(String realrptId) {
		this.realrptId = realrptId;
	}

	public int getFormat() {
		return this.format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getChildType() {
		return this.childType;
	}

	public void setChildType(int childType) {
		this.childType = childType;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getExecDate() {
		return this.execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFileNum() {
		return this.fileNum;
	}

	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}

	public int getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}

	public Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public int getTransFlag() {
		return this.transFlag;
	}

	public void setTransFlag(int transFlag) {
		this.transFlag = transFlag;
	}

	public String getUrl() {
		return this.url;
	}
	
	public String getRealurl(){
		String _url="";
 		if(type==2&&format==1){
 			if(StringUtils.isEmpty(personId))
 				personId="p150213145810000";
   			_url=CommonData.getStrParam("ciyun_weixin_url")+"/user/medlab/labdetail/"+this.realrptId+"?personId="+personId;
 		}else if (!StringUtils.isEmpty(url))
			_url = CommonData.getStrParam("ciyun_weixin_url") +url; 
 		return _url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getRptCreateTime() {
		return this.rptCreateTime;
	}

	public void setRptCreateTime(Date rptCreateTime) {
		this.rptCreateTime = rptCreateTime;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getReadFromChannel() {
		return readFromChannel;
	}

	public void setReadFromChannel(String readFromChannel) {
		this.readFromChannel = readFromChannel;
	}

	public int getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(int signFlag) {
		this.signFlag = signFlag;
	}

	public String getHmoId() {
		return hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getRelationRptId() {
		return relationRptId;
	}

	public void setRelationRptId(String relationRptId) {
		this.relationRptId = relationRptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(StringUtils.isNotEmpty(userName))
			this.userNameEnc = DataEncUtil.encstr(userName);
		this.userName = userName;
	}

	public int getInterpretState() {
		return interpretState;
	}

	public void setInterpretState(int interpretState) {
		this.interpretState = interpretState;
	}

	public String getUserNameEnc() {
		return userNameEnc;
	}

	public void setUserNameEnc(String userNameEnc) {
		if(StringUtils.isNotEmpty(userNameEnc))
			this.userName = DataEncUtil.decstr(userNameEnc);
		this.userNameEnc = userNameEnc;
	}

}