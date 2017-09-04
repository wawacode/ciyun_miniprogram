package com.centrin.ciyun.entity.person;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.centrin.webbase.util.DataEncUtil;

/**
 * PerPersonUser entity. @author MyEclipse Persistence Tools
 */

public class PerPersonUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9138099509942719884L;
	
	private String userId;
	private String personId;
	private String nickName;
	private String loginName;
	private String loginNameEnc;
	private String password;
	private Timestamp lastLoginTime;
	private String lastLoginIp;
	private int loginCount;
	private int createType;
	private String regUuid;
	private String loginUuid;
	private String deviceCode;
	/** 密文 */
	private String createUserEnc;
	private String createUser;
	private Timestamp createTime;
	private Timestamp modifyTime;
	/** 密文 */
	private String modifyUserEnc;
	private String modifyUser;
	private String remarks;
	private int isSet = 1;
	private int isGuild = 1;
	private int isopennotice;
	private String regIp;
	private int errorCnt = 0;// 连续登录错误次数
	private Timestamp lockTime;// 连续登录错误次数超过允许的阈值就锁定登录一段时间，记录锁定的当前时间
	private Timestamp firstErrorTime;// 第一次错误登录的时间
	// 总积分
	private int totalIntegral;
	// 当前积分
	private int curIntegral;
	private String channelCode;//渠道编号, 对应bus_channel表主键
	private int isLife=2;//是否做过生活问卷调查  1-做过  2-没有做过

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public int getIsopennotice() {
		return isopennotice;
	}

	public void setIsopennotice(int isopennotice) {
		this.isopennotice = isopennotice;
	}

	/** default constructor */
	public PerPersonUser() {
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getCreateType() {
		return this.createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public String getCreateUser() {
		if(StringUtils.isEmpty(this.createUserEnc))
			return null;
		this.createUser= com.centrin.webbase.util.DataEncUtil.decstr(createUserEnc);		
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		if(!StringUtils.isEmpty(createUser))
			this.createUserEnc=DataEncUtil.encstr(createUser);
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		if(StringUtils.isEmpty(this.modifyUserEnc))
			return null;
		this.modifyUser= com.centrin.webbase.util.DataEncUtil.decstr(modifyUserEnc);
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		if (!StringUtils.isEmpty(modifyUser))
			this.modifyUserEnc = DataEncUtil.encstr(modifyUser);
		this.modifyUser = modifyUser;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsSet() {
		return this.isSet;
	}

	public void setIsSet(int isSet) {
		this.isSet = isSet;
	}

	public String getRegUuid() {
		return regUuid;
	}

	public void setRegUuid(String regUuid) {
		this.regUuid = regUuid;
	}

	public String getLoginUuid() {
		return loginUuid;
	}

	public void setLoginUuid(String loginUuid) {
		this.loginUuid = loginUuid;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getIsGuild() {
		return isGuild;
	}

	public void setIsGuild(int isGuild) {
		this.isGuild = isGuild;
	}

	public Timestamp getLockTime() {
		return lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

	public int getErrorCnt() {
		return errorCnt;
	}

	public void setErrorCnt(int errorCnt) {
		this.errorCnt = errorCnt;
	}

	public Timestamp getFirstErrorTime() {
		return firstErrorTime;
	}

	public void setFirstErrorTime(Timestamp firstErrorTime) {
		this.firstErrorTime = firstErrorTime;
	}

	public int getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(int totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public int getCurIntegral() {
		return curIntegral;
	}

	public void setCurIntegral(int curIntegral) {
		this.curIntegral = curIntegral;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	/**
	 * @return the isLife
	 */
	public int getIsLife() {
		return isLife;
	}

	/**
	 * @param isLife the isLife to set
	 */
	public void setIsLife(int isLife) {
		this.isLife = isLife;
	}
	public String getLoginNameEnc() {
		return loginNameEnc;
	}

	public void setLoginNameEnc(String loginNameEnc) {
		this.loginNameEnc = loginNameEnc;
	}
	public String getCreateUserEnc() {
		return createUserEnc;
	}

	public void setCreateUserEnc(String createUserEnc) {
		this.createUserEnc = createUserEnc;
	}

	public String getModifyUserEnc() {
		return modifyUserEnc;
	}

	public void setModifyUserEnc(String modifyUserEnc) {
		this.modifyUserEnc = modifyUserEnc;
	}
	
}