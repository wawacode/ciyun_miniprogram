package com.centrin.ciyun.entity.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <p>
 *  该类智能用于存放规则,添加新的规则后在该类添加即可,页面元素属性和该类属性名对应
 * </p>
 * @author YANXF
 * @date 2015-12-17
 * @version 1.0
 * @since 1.0
 * @see 
 *
 */
public class HidMedCorpRuleInfo  implements java.io.Serializable {
	private String userName;  //用户姓名
	private Integer sex = 3;  //1:男 2:女  3:未知
	private String myMobile; //当前登录用户的手机号   为亲友的报告时，此处设为空
	private String mobile; //用户填写的手机号
	private Integer idCardType=0;   //证件类型，不需要配置xml文件或库中，只是用户选择
	private String idCard;    //证件号码
	private String   medDate;   //体检日期
	private String medPersonNo; //档案ID
	private Boolean  isFamily; 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	 
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMedDate() {
		return medDate;
	}
	public void setMedDate(String medDate) {
		this.medDate = medDate;
	}
	public Integer getIdCardType() {
		return idCardType;
	}
	public void setIdCardType(Integer idCardType) {
		this.idCardType = idCardType;
	}
	
	public String getMedPersonNo() {
		return medPersonNo;
	}
	public void setMedPersonNo(String medPersonNo) {
		this.medPersonNo = medPersonNo;
	}
	
	public String getMyMobile() {
		return myMobile;
	}
	public void setMyMobile(String myMobile) {
		this.myMobile = myMobile;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public Boolean getIsFamily() {
		return isFamily;
	}
	public void setIsFamily(Boolean isFamily) {
		this.isFamily = isFamily;
	}

}
