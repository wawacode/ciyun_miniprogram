package com.centrin.ciyun.medrpt.domain.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryVo {
	private String organName;//科室名称或大项名称
	private List<Summary> summaryList;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Summary implements Serializable{
		
		private static final long serialVersionUID = 1L;
		private String summary; //小结内容
		private String doctor; //检查医生
		private String revDoctor; //审核医生
	}
}
