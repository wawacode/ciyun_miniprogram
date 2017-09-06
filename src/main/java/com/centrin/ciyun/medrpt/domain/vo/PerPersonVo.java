package com.centrin.ciyun.medrpt.domain.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerPersonVo implements java.io.Serializable {

	private String sessionKey;//小程序的session_key
	private String openId;//用户的openId
	private String mpNum;//小程序原始ID
	private String personId;//用户ID
	private String telephone; //用户登录手机号
	private Integer sex = 3; //用户性别
	private String userName; //用户姓名
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
