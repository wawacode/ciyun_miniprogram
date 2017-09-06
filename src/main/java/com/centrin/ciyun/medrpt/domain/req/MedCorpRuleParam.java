/*
 * 文件名：com.centrin.ciyun.medrpt.domain.req.MedCorpParam.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月5日 下午5:20:35
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
@AllArgsConstructor
@NoArgsConstructor
public class MedCorpRuleParam extends BaseEntity{
	private String medCorpId;
	private Integer sex = 3;
	private String telephone;
	private String userName;
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
