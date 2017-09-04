package com.centrin.ciyun.entity.hid;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;

import com.centrin.ciyun.service.interfaces.hid.IDubboHidMedCorpService;
import com.centrin.webbase.WebContextWrapper;

public class HidMedCorp implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String medCorpId;
	private String corpName;
	private String parentId;
	private Integer state=-1;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private String address;
	private String contacter;
	private String telephone;
	private String fax;
	private String createUser;
	private Date createTime;
	private String remarks;
	private String ciyunId; //慈云平台唯一标示
	private String hmoId;//健管机构ID
	private String hmoName; //健管机构的名称
	private String ipaddress;//体检中心服务器Ip地址
	private Integer serviceId; //体检中心关联的默认健管师组id
	private Integer ruleState = 2;  // 1:是  2：否
	private String ruleIds; //规则ID
	private String ruleCardType; //证件类型
	private Integer rptNotifyFlag = 1;//是否短信通知  1:是 2：否
	private String rptNotifySms;  //通知短信
	private Integer rptNotifyCycle;  //通知短信在体检日期前 n 天之内才下发短信，
	private String templeteId; //模板ID
	private Long areaId; //地域ID
	private String areaName; //地域名称
	private	Integer areaOrder; //地域排序
	private Integer corpOrder = 0; //机构排序
	private Integer autoPerGroup = 1; //自动创建用户组 1:是  2：否
	public Integer getRuleState() {
		return ruleState;
	}

	public void setRuleState(Integer ruleState) {
		this.ruleState = ruleState;
	}

	public String getHmoName() {
		return hmoName;
	}

	public void setHmoName(String hmoName) {
		this.hmoName = hmoName;
	}

	public String getMedCorpId() {
		return this.medCorpId;
	}

	public void setMedCorpId(String medCorpId) {
		this.medCorpId = medCorpId;
	}

	public String getCorpName() {
		return this.corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static String getNameFromCache(String corpId) {
		try {
			if (StringUtils.isBlank(corpId)) {
				return "";
			}
			IDubboHidMedCorpService dubboHidMedCorpService =(IDubboHidMedCorpService) WebContextWrapper.getBean("iDubboHidMedCorpService");
			HidMedCorp hidMedCorp = dubboHidMedCorpService.queryHidByMedCorpId(corpId);
			return null == hidMedCorp ? "" : hidMedCorp.getCorpName();
		} catch (Exception e) {
			return "";
		}
	}

	public String getCiyunId() {
		return ciyunId;
	}

	public void setCiyunId(String ciyunId) {
		this.ciyunId = ciyunId;
	}

	public String getHmoId() {
		return hmoId;
	}

	public void setHmoId(String hmoId) {
		this.hmoId = hmoId;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getRuleIds() {
		return ruleIds;
	}

	public void setRuleIds(String ruleIds) {
		this.ruleIds = ruleIds;
	}

	public String getRuleCardType() {
		return ruleCardType;
	}

	public void setRuleCardType(String ruleCardType) {
		this.ruleCardType = ruleCardType;
	}

	public Integer getRptNotifyFlag() {
		return rptNotifyFlag;
	}

	public void setRptNotifyFlag(Integer rptNotifyFlag) {
		this.rptNotifyFlag = rptNotifyFlag;
	}

	public String getRptNotifySms() {
		return rptNotifySms;
	}

	public void setRptNotifySms(String rptNotifySms) {
		this.rptNotifySms = rptNotifySms;
	}

	public String getTempleteId() {
		return templeteId;
	}

	public void setTempleteId(String templeteId) {
		this.templeteId = templeteId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaOrder() {
		return areaOrder;
	}

	public void setAreaOrder(Integer areaOrder) {
		this.areaOrder = areaOrder;
	}

	public Integer getCorpOrder() {
		return corpOrder;
	}

	public void setCorpOrder(Integer corpOrder) {
		this.corpOrder = corpOrder;
	}

	public Integer getRptNotifyCycle() {
		return rptNotifyCycle;
	}

	public void setRptNotifyCycle(Integer rptNotifyCycle) {
		this.rptNotifyCycle = rptNotifyCycle;
	}

	public Integer getAutoPerGroup() {
		return autoPerGroup;
	}

	public void setAutoPerGroup(Integer autoPerGroup) {
		this.autoPerGroup = autoPerGroup;
	}
}