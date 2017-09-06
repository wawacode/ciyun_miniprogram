package com.centrin.ciyun.entity.med.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 体检详情
 * @author lzh
 * @time 2014-9-19 下午03:03:44
 * 
 */
public class IPhyExamDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mcresultnm;
	private List<Fields> outLimits; // 超标项
	private Check preCheck;// 上一次体检
	private Check nextCheck;// 下一次体检
	private List<ReportDetail> reportDetail; // 体检详情
	private List<OrganInfoEntity> primReportDetail; //原始报告详情

	public List<OrganInfoEntity> getPrimReportDetail() {
		return primReportDetail;
	}

	public void setPrimReportDetail(List<OrganInfoEntity> primReportDetail) {
		this.primReportDetail = primReportDetail;
	}

	public String getMcresultnm() {
		return mcresultnm;
	}

	public void setMcresultnm(String mcresultnm) {
		this.mcresultnm = mcresultnm;
	}

	public List<Fields> getOutLimits() {
		return outLimits;
	}

	public void setOutLimits(List<Fields> outLimits) {
		this.outLimits = outLimits;
	}

	public Check getPreCheck() {
		return preCheck;
	}

	public void setPreCheck(Check preCheck) {
		this.preCheck = preCheck;
	}

	public Check getNextCheck() {
		return nextCheck;
	}

	public void setNextCheck(Check nextCheck) {
		this.nextCheck = nextCheck;
	}

	public List<ReportDetail> getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(List<ReportDetail> reportDetail) {
		this.reportDetail = reportDetail;
	}

	public static class Check  implements java.io.Serializable{
		private String time;
		private String id;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}
	
	public static class ReportDetail  implements java.io.Serializable{
		private String groupName;  //体检项目分类名
		private String groupIndex; //显示顺序
		private List<Fields> fields;
		private List<String> medias;

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public String getGroupIndex() {
			return groupIndex;
		}

		public void setGroupIndex(String groupIndex) {
			this.groupIndex = groupIndex;
		}

		public List<Fields> getFields() {
			return fields;
		}

		public void setFields(List<Fields> fields) {
			this.fields = fields;
		}

		public List<String> getMedias() {
			return medias;
		}

		public void setMedias(List<String> medias) {
			this.medias = medias;
		}
	}
	
	public static class Fields  implements java.io.Serializable{
		private String field; // 体检项名称
		private String value; //展示值
		private String limit;// 格式：下限/上限
		private String stdRange; //类似<,>类的上下限
		private int type; // 0历史数据展示为图形,1代表展示文字2. 历史数据展示为相对值图形
		private int over; // 1-未超标 2-已超标
		private String fieldId; // 体检项标示
		private String unit;//单位

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLimit() {
			return limit;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getOver() {
			return over;
		}

		public void setOver(int over) {
			this.over = over;
		}

		public String getFieldId() {
			return fieldId;
		}

		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getStdRange() {
			return stdRange;
		}

		public void setStdRange(String stdRange) {
			this.stdRange = stdRange;
		}

	}


}
