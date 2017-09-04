package com.centrin.ciyun.entity.hmo;

import java.util.Date;

/**
 * HmoCorporation entity. @author MyEclipse Persistence Tools
 */

public class HmoCorporation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 569803731779813540L;
	private String hmoId;
	private String name;
	private String pinyin;
	private String shortName;
	private String logo;
	private int state;
	private Date expireDate;
	private String contacter;
	private String phone;
	private String fax;
	private String address;
	private String website;
	private String createUser;
	private Date createTime;
	private String modifyUser;
	private Date modifyTime;
	private String remarks;
	
	
	
	private String loginName;
	private int userId;
	private int createType;
	private String image;
	private int display;
	private String serviceLogo;
	private String mpNum;
	private String domain; //域名前缀
	private String smsSig; //短信签名
	private int payflag;
	
	//机构公众号支付信息
	private String mchId;//微信公众号支付商户ID
	private String appId;//微信公众号支付appId
	private String paySignKey;//微信公众号支付签名秘钥
	private String signType;//微信公众号支付签名秘钥
	private String appSecret;//微信公众号支付appSecret
	private Integer provinceId;//归属省
	private Integer cityId;//归属市区
	private Integer districtId;//归属区域
	private String shopPic;//商城logo
	private String shopPicUrl;//商城logo
	private Integer listFlag;//是否显示到机构列表里面1是2否
	private Integer orderNum = 0;//显示顺序, 显示顺序是从大到小
	private int smsFlag=2;//是否允许医生短信群发  1:是 2:否
	private String consultPhone;//咨询电话（包括基因检测咨询）	

	// Constructors

	/** default constructor */
	public HmoCorporation() {
	}

	// Property accessors

	public String getHmoId() {
		return this.hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCreateType() {
		return createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public String getLogoUrl() {
		/*if (StringUtils.isBlank(logo)) {
			return null;
		}
		return CommonData.getWebServerUrl(EFileUploadServiceType.ENT_LOGO,logo);*/
		return "";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageUrl() {
		/*if (StringUtils.isBlank(image)) {
			return "";
		}
		return CommonData.getWebServerUrl(EFileUploadServiceType.ENT_LOGO,image);*/
		return "";
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public String getServiceLogo() {
		return serviceLogo;
	}

	public void setServiceLogo(String serviceLogo) {
		this.serviceLogo = serviceLogo;
	}

	public String getServiceLogoUrl() {
		
		/*if (StringUtils.isBlank(serviceLogo)) {
			return null;
		}
		return CommonData.getWebServerUrl(EFileUploadServiceType.ENT_LOGO,serviceLogo);*/
		return "";
	}
	public String getShopPicUrl() {
		
		/*if (StringUtils.isBlank(this.shopPic)) {
			return null;
		}
		return CommonData.getWebServerUrl(EFileUploadServiceType.ENT_LOGO,shopPic);*/
		return "";
	}
	public String getMpNum() {
		return mpNum;
	}

	public void setMpNum(String mpNum) {
		this.mpNum = mpNum;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSmsSig() {
		return smsSig;
	}

	public void setSmsSig(String smsSig) {
		this.smsSig = smsSig;
	}

	public int getPayflag() {
		return payflag;
	}

	public void setPayflag(int payflag) {
		this.payflag = payflag;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPaySignKey() {
		return paySignKey;
	}

	public void setPaySignKey(String paySignKey) {
		this.paySignKey = paySignKey;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	public Integer getListFlag() {
		return listFlag;
	}

	public void setListFlag(Integer listFlag) {
		this.listFlag = listFlag;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public int getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(int smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getConsultPhone() {
		return consultPhone;
	}

	public void setConsultPhone(String consultPhone) {
		this.consultPhone = consultPhone;
	}
	
}