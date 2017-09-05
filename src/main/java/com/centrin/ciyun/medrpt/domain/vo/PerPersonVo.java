package com.centrin.ciyun.medrpt.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerPersonVo {

	private String sessionKey;//小程序的session_key
	private String openId;//用户的openId
	private String mpNum;//小程序原始ID
	private String personId;//用户ID
}
