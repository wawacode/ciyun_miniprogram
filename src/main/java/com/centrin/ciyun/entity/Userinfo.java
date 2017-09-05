package com.centrin.ciyun.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.centrin.webbase.CommonData;

/**
 * 登录后放在会话中的数据信息 其他需要的人员数据，通过personid到数据库或kv中去拿
 * 
 * @author 刘哈哈
 * 
 */
public class Userinfo implements java.io.Serializable {

	private static final long serialVersionUID = 7800084981161370380L;

	private String userid;
	private String personid;
	private int serviceId;
	private String loginname;
	private String username;
	private String nickName;
	/**
	 * 当前所在的企业id
	 */
	private String curentid;
	private String curentname;
	/**
	 * 方便用来判断是否需要认证的1需要认证2和3不需要
	 */
	private int feetype;
	private int age = 18;
	private String birthday;
	private int gender = 1;
	private String pic;

	private int height;
	private float weight;
	private int idtype;
	private String idno;
	private String email;
	private String mobile;
	private double healthScore;//健康
	/**
	 * 是否有回答对应的问题
	 */
	private int isset;

	/**
	 * 首页是否显示新手引导 1显示 2不显示
	 */
	private int isguild;
	/**
	 * 通知的 1有公告并显示，2有公告并关闭，3无公告
	 */
	private int noticeState = 1;

	private String uuid;
	private String devicecode;
	private int loginid;
	private int logincount;
	private Date lastlogintime;
	private String lastloginip;
	private String healthFocus;
	private String sportFocus;

	// 总积分
	private int totalIntegral;
	// 当前积分
	private int curIntegral;
	
	 
	private Date vipExpireDate;//VIP截止日期
	private Date regTime;//注册时间
	private int curServiceLevel; //会员等级
	private String curHmoId;//当前健管机构
	private int isLife;//是否做过生活问卷调查  1-做过 2-没有做过
	private int bindType=1;
	
	
	private int corpFlag;//1是机构客户2是互联网客户
	
	
	public int getCorpFlag() {
		return corpFlag;
	}

	public void setCorpFlag(int corpFlag) {
		this.corpFlag = corpFlag;
	}

	public String getUserid() {
		return userid;
	}
   
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurentid() {
		return curentid;
	}

	public void setCurentid(String curentid) {
		this.curentid = curentid;
	}

	public String getCurentname() {
		return curentname;
	}

	public void setCurentname(String curentname) {
		this.curentname = curentname;
	}


	public int getFeetype() {
		return feetype;
	}

	public void setFeetype(int feetype) {
		this.feetype = feetype;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getIdtype() {
		return idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getIsset() {
		return isset;
	}

	public void setIsset(int isset) {
		this.isset = isset;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public int getLoginid() {
		return loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHealthFocus() {
		return healthFocus;
	}

	public void setHealthFocus(String healthFocus) {
		this.healthFocus = healthFocus;
	}

	public int getNoticeState() {
		return noticeState;
	}

	public void setNoticeState(int noticeState) {
		this.noticeState = noticeState;
	}

	public int getIsguild() {
		return isguild;
	}

	public void setIsguild(int isguild) {
		this.isguild = isguild;
	}

	public String getSportFocus() {
		return sportFocus;
	}

	public void setSportFocus(String sportFocus) {
		this.sportFocus = sportFocus;
	}

	public String getPicUrl() {
 //		if (this.pic != null && this.pic.indexOf(".") >= 0) {
//			return CommonData.getWebServerSmallUrl(EFileUploadServiceType.USER_AVATARS ,this.pic);
//		} else {
//			if(gender==1){
//				return  CommonData.getStrParam("DEFAULT_HEAD_IMG");
//			}else{
//				return CommonData.getStrParam("default_woman_head");
//			}
//			
//		} 
		
		/*if(this.pic!=null&&(pic.toLowerCase().startsWith("http://")||pic.toLowerCase().startsWith("https://")))
			return pic;
		if (this.pic != null && this.pic.indexOf(".") >= 0) {
			return CommonData.getWebServerSmallUrl(EFileUploadServiceType.USER_AVATARS,this.pic);
		} else {
			
			if(this.gender==1)
				return CommonData.getStrParam("DEFAULT_HEAD_IMG");
				else
					return CommonData.getStrParam("default_woman_head"); 
			
		}*/
		return "";
	 
	}

	/**
	 * 获取界面显示的名称
	 * 
	 * @return
	 */
	public String getShowName() {
		if (StringUtils.isEmpty(username))
			return loginname;
		return username;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
 

	public Date getVipExpireDate() {
		return vipExpireDate;
	}

	public void setVipExpireDate(Date vipExpireDate) {
		this.vipExpireDate = vipExpireDate;
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

	public double getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(double healthScore) {
		this.healthScore = healthScore;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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

	/**
	 * @return the bindType
	 */
	public int getBindType() {
		return bindType;
	}

	/**
	 * @param bindType the bindType to set
	 */
	public void setBindType(int bindType) {
		this.bindType = bindType;
	}

	 
	
}