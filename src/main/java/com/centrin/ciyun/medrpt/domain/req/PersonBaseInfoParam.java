package com.centrin.ciyun.medrpt.domain.req;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonBaseInfoParam extends BaseEntity{
	private String nickName;//用户昵称
	private Integer gender;//用户性别 1：男 2：女 3：未知
	private Integer age;//用户年龄
	private Float height;//用户身高
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
