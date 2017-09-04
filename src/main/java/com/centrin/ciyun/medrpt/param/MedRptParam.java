package com.centrin.ciyun.medrpt.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedRptParam extends BaseEntity{

	private int cardType;//证件类型 1：身份证 2：回乡证 3：护照 4：军官证 5：医保卡号 6：警察证 23：员工号 26：唯一号
	private String cardNo;//证件号码
	private int sex;//用户性别 1：男 2：女 3：未知
	private String telephone;//手机号码
	private String medDate;//体检日期
	private String recordId;//档案ID
	private String username;//用户性别
	
}
