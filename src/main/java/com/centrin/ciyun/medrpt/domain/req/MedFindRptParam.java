/*
 * 文件名：com.centrin.ciyun.medrpt.domain.req.MedFindRptParam.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月5日 下午8:21:03
 * 修改人：yanxf
 * 
 */
package com.centrin.ciyun.medrpt.domain.req;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  <li>简述：<一句话介绍类的作用></li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedFindRptParam extends BaseEntity{
	private String medCorpId; //体检中心ID
	private String userName;  //用户姓名
	private Integer sex = 3;  //1:男 2:女  3:未知
	private String mobile; //用户填写的手机号
	private Integer idCardType = 1;   //证件类型，不需要配置xml文件或库中，只是用户选择
	private String idCard;    //证件号码
	private String   medDate;   //体检日期
	private String medPersonNo; //档案ID
	private String personId; //用户personid
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
