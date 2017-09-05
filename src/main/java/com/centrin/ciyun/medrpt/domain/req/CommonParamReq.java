package com.centrin.ciyun.medrpt.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonParamReq extends BaseEntity{

	private String code; //小程序的登录凭证code
	private String telephone;//用户登录手机号码
	private String note;//短信验证码
	private String rawData;//用户数据
	private String signature;//签名
	private String medCorpId;//体检中心ID
	
}
