package com.centrin.ciyun.enumdef;

public  class MedExamRptSyntheticEnum {
	
	/**
	 * 表名
	 * @author hql
	 *
	 */
	public static enum TableName{

		MED_EXAM_RPT("MED_EXAM_RPT","体检报告分析版"),
		
		MED_EXAM_RPT_IMAGE("MED_EXAM_RPT_IMAGE","体检报告图片版"),
		
		MED_LAB_SHEET("MED_LAB_SHEET","化验单分析版"),
		
		BUS_INTERPRET_RPT_RESULT("BUS_INTERPRET_RPT_RESULT","体检报告解读分析"),
		
		HID_SPECIAL_MANAGE("HID_SPECIAL_MANAGE","专项管理报告"),
		
		HID_SPECIAL_MANAGE_REPORT("HID_SPECIAL_MANAGE_REPORT","日常记录分析报告");
		
		private  TableName(String tableName,String tableDesc){
			this.tableDesc= tableDesc;
			this.tableName=tableName;
		}
		public String tableName;
		public String tableDesc;
	}
	
	/**
	 * 分析报告子类型
	 * @author hql
	 * 1、	体检报告解读分析 2、专项管理报告 3、危险分层报告 4、运动分析报告等等
	 */
	public static enum AnalysisChildType{
		
		BUS_INTERPRET_RPT_RESULT(1,"体检报告解读分析"),
		
		HID_SPECIAL_MANAGE(2,"专项管理报告"),
		
		RISK_STRATIFICATION_REPORT(3,"危险分层报告"),
		
		HID_SPECIAL_MANAGE_REPORT(4,"日常记录分析报告")
		;
		
		private  AnalysisChildType(Integer type,String name){
			this.type= type;
			this.name=name;
		}
		public Integer type;
		public String name;
	}
	
	
}
