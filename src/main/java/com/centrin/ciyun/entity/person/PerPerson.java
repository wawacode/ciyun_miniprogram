package com.centrin.ciyun.entity.person;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.centrin.ciyun.common.util.PrettyDateFormat;
import com.centrin.ciyun.entity.hmo.HmoCorporation;
import com.centrin.ciyun.enumdef.IdType;
import com.centrin.ciyun.service.interfaces.hmo.HmoCorporationService;
import com.centrin.webbase.CommonData;
import com.centrin.webbase.WebContextWrapper;
import com.centrin.webbase.util.Chinese2Pinyin;
import com.centrin.webbase.util.DataEncUtil;
public class PerPerson implements java.io.Serializable {

	private static final long serialVersionUID = -7877782402265062703L;
	private String personId;
	private String userName;
	private String userNameEnc;
	private int gender = 3;
	private Date birthday;
	private int age = 20;
	private String pic;
	private String email;
	private String mobile;
	private String mobileEnc;
	private int idType;
	private String idTypeDesc;
	private String idNo;
	private String idNoEnc;
	private int createType;
	private String createUser;
	/** 密文 */
	private String createUserEnc;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private String modifyUser;
	/** 密文 */
	private String modifyUserEnc;
	private String remarks;
	private int height = 170;
	private double weight = 80;
	private int mobileState;//mobile是否验证:1是, 2否，和mobile字段对应
	private String sportFocus;
	private String healthFocus;
	private String birthdayDesc;
	private int isPlaned;
	private int serviceId;
 	private String channelCode;
	private int groupNum;
	private int curServiceLevel=1;
	private String curHmoId;
	private int onlineFlag;
	private int recordUnreadNum;
	private Date recordUpdateTime;
	private String dosefName;
	private Double healthScore;
	private Date vipExpireDate;//VIP截止日期
	private int hasVerifyName = 2;
	private String tag;
	private int corpFlag;
	private String initCorp;
	/** 用户名拼音首字母 */
	private String namePinyin;	
	
	
	public String getUserNameEnc() {
		return userNameEnc;
	}

	public void setUserNameEnc(String userNameEnc) {
		this.userNameEnc = userNameEnc;
	}

	public String getMobileEnc() {
		return mobileEnc;
	}

	public void setMobileEnc(String mobileEnc) {
		this.mobileEnc = mobileEnc;
	}

	public String getIdNoEnc() {
		return idNoEnc;
	}

	public void setIdNoEnc(String idNoEnc) {
		this.idNoEnc = idNoEnc;
	}

	public int getCorpFlag() {
		return corpFlag;
	}

	public void setCorpFlag(int corpFlag) {
		this.corpFlag = corpFlag;
	}

	public String getInitCorp() {
		return initCorp;
	}

	public void setInitCorp(String initCorp) {
		this.initCorp = initCorp;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCurEntId() {
		return null;
	}

	public String getCurHmoName() {
		if (!StringUtils.isEmpty(curHmoId)) {
			HmoCorporationService corporationService = (HmoCorporationService)WebContextWrapper.getBean("hmoCorporationService");
			HmoCorporation corporation = corporationService.view(curHmoId);
			if (corporation != null)
				return corporation.getName();
		}
		return "";
	}
	
	public String getCurEntName() {
		return null;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserName() {
		if(StringUtils.isEmpty(this.userNameEnc))
			return null;
		this.userName= com.centrin.webbase.util.DataEncUtil.decstr(userNameEnc);
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		if(!StringUtils.isEmpty(userName))
			this.userNameEnc=DataEncUtil.encstr(userName);
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		if (birthday != null) {
			birthdayDesc = PrettyDateFormat.dateToString(birthday, "yyy-MM-dd");
		}
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEmail() {

		return email;
	}

	public String getEmailFilter() {
		if (!StringUtils.isEmpty(email)) {
			return com.centrin.webbase.util.StringUtils.toViewMail(email);
		}
		return null;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		if(StringUtils.isEmpty(this.mobileEnc))
			return null;
		this.mobile= com.centrin.webbase.util.DataEncUtil.decstr(mobileEnc);
		return this.mobile;
	}

	public String getMobileFilter() {
		if (!StringUtils.isEmpty(this.mobile)) {
			return com.centrin.webbase.util.StringUtils.toViewMobile(this.mobile);
		}
		return null;
	}

	public void setMobile(String mobile) {
		if(!StringUtils.isEmpty(mobile))
			this.mobileEnc=DataEncUtil.encstr(mobile);
		this.mobile = mobile;
	}

	public String getIdNo() {
		if(StringUtils.isEmpty(this.idNoEnc))
			return null;
		this.idNo= com.centrin.webbase.util.DataEncUtil.decstr(idNoEnc);
		return this.idNo;
	}

	public String getIdNoFilter() {
		if (!StringUtils.isEmpty(this.getIdNo())) {
			return com.centrin.webbase.util.StringUtils.toViewIdCard(this.getIdNo());
		}
		return null;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
		if(!StringUtils.isEmpty(idNo))
			this.idNoEnc=DataEncUtil.encstr(idNo);
	}

	public int getCountry() {
		return 0;
	}

	public int getCreateType() {
		return createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public String getCreateUser() {
		if(StringUtils.isEmpty(this.createUserEnc))
			return null;
		this.createUser= com.centrin.webbase.util.DataEncUtil.decstr(createUserEnc);
		return createUser;
	}

	public void setCreateUser(String createUser) {
		if(!StringUtils.isEmpty(createUser))
			this.createUserEnc=DataEncUtil.encstr(createUser);
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		if(StringUtils.isEmpty(this.modifyUserEnc))
			return null;
		this.modifyUser= com.centrin.webbase.util.DataEncUtil.decstr(modifyUserEnc);
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		if (!StringUtils.isEmpty(modifyUser))
			this.modifyUserEnc = DataEncUtil.encstr(modifyUser);
		this.modifyUser = modifyUser;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
		this.idTypeDesc = IdType.ENUMMAP.get(idType);
		if (idTypeDesc == null) {
			idTypeDesc = "";
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getMobileState() {
		return mobileState;
	}

	public void setMobileState(int mobileState) {
		this.mobileState = mobileState;
	}

	public String getSportFocus() {
		return sportFocus;
	}

	public void setSportFocus(String sportFocus) {
		this.sportFocus = sportFocus;
	}

	public String getHealthFocus() {
		return healthFocus;
	}

	public void setHealthFocus(String healthFocus) {
		this.healthFocus = healthFocus;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthdayDesc() {
		return birthdayDesc;
	}

	public String getIdTypeDesc() {
		return idTypeDesc;
	}

	public void setIdTypeDesc(String idTypeDesc) {
		this.idTypeDesc = idTypeDesc;
	}

	public String getPicUrl() {
		if(this.pic!=null&&(pic.toLowerCase().startsWith("http://")||pic.toLowerCase().startsWith("https://")))
			return pic;
		if (this.pic != null && this.pic.indexOf(".") >= 0) {
			/*return CommonData.getWebServerSmallUrl(EFileUploadServiceType.USER_AVATARS,this.pic);*/
			return "";
		} else {
			if(this.gender==1)
				return CommonData.getStrParam("DEFAULT_HEAD_IMG");
				else
					return CommonData.getStrParam("default_woman_head"); 
		}
	 
	}

	public String getShortPicUrl() {
		if (this.pic != null && this.pic.indexOf(".") >= 0) {
			return getPicUrl();
		} else {
			return null;
		}
	}


	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getIsPlaned() {
		return isPlaned;
	}

	public void setIsPlaned(int isPlaned) {
		this.isPlaned = isPlaned;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}


	public int getCurServiceLevel() {
		return curServiceLevel;
	}

	public void setCurServiceLevel(int curServiceLevel) {
		this.curServiceLevel = curServiceLevel;
	}

	public String getCurHmoId() {
		return curHmoId;
	}

	public void setCurHmoId(String curHmoId) {
		this.curHmoId = curHmoId;
	}

	public int getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(int onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public int getRecordUnreadNum() {
		return recordUnreadNum;
	}

	public void setRecordUnreadNum(int recordUnreadNum) {
		this.recordUnreadNum = recordUnreadNum;
	}

	public Date getRecordUpdateTime() {
		return recordUpdateTime;
	}

	public void setRecordUpdateTime(Date recordUpdateTime) {
		this.recordUpdateTime = recordUpdateTime;
	}


	public void setBirthdayDesc(String birthdayDesc) {
		this.birthdayDesc = birthdayDesc;
	}

	public Double getHealthScore() {
		if(healthScore==null)
			return 0D;
		return healthScore;
	}

	public void setHealthScore(Double healthScore) {
		this.healthScore = healthScore;
	}

	public Date getVipExpireDate() {
		return vipExpireDate;
	}

	public void setVipExpireDate(Date vipExpireDate) {
		this.vipExpireDate = vipExpireDate;
	}

	public String getDosefName() {
		return dosefName;
	}

	public void setDosefName(String dosefName) {
		this.dosefName = dosefName;
	}

	public int getBindType() {
		return 0;
	}
//
	public void setBindType(int bindType) {
		 
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public int getHasVerifyName() {
		return hasVerifyName;
	}

	public void setHasVerifyName(int hasVerifyName) {
		this.hasVerifyName = hasVerifyName;
	}
	public String getNamePinyin() {
		String username = this.getUserName();
		if (StringUtils.isNotBlank(username)) {
			this.namePinyin = Chinese2Pinyin.to2simplepinyin(username);
			if (StringUtils.isNotBlank(namePinyin) && this.namePinyin.length() > 1) {
				namePinyin = namePinyin.trim().substring(0, 1).toUpperCase();
			}
		}

		return this.namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
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
