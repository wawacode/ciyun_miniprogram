package com.centrin.ciyun.entity.vo;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HidMedCorpInfoVo  implements java.io.Serializable {
	private String userName;
	private Integer sex = 3;
	private String telephone;
	private String medCorpId;
	private String ruleIds;
	private Map<String, String> ruleCardType;
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
