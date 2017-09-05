package com.centrin.ciyun.entity.med.vo;

import java.util.LinkedList;
import java.util.List;

import com.centrin.ciyun.entity.med.vo.OrganInfoEntity.ItemRst;

public class PrimExamDetails  implements java.io.Serializable {
	private String summary;//用于保存一份报告的汇总组合的字符串
	private String advices; //用于保存一份报告的建议组合的字符串
	private String summaryExplain;  //汇总分析（包括主检分析，检查所见和扩展字段等）
	private List<ItemRst> outLimits = new LinkedList<ItemRst>(); // 超标项
	private List<OrganInfoEntity> primReportDetail; //原始报告详情
	public List<ItemRst> getOutLimits() {
		return outLimits;
	}
	public void setOutLimits(List<ItemRst> outLimits) {
		this.outLimits = outLimits;
	}
	public List<OrganInfoEntity> getPrimReportDetail() {
		return primReportDetail;
	}
	public void setPrimReportDetail(List<OrganInfoEntity> primReportDetail) {
		this.primReportDetail = primReportDetail;
	}
	public String getSummaryExplain() {
		return summaryExplain;
	}
	public void setSummaryExplain(String summaryExplain) {
		this.summaryExplain = summaryExplain;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAdvices() {
		return advices;
	}
	public void setAdvices(String advices) {
		this.advices = advices;
	}
}
