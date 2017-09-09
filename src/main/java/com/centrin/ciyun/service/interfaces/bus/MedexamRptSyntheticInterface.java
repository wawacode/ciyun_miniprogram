package com.centrin.ciyun.service.interfaces.bus;


import java.util.List;

import com.centrin.ciyun.entity.med.MedExamRptSynthetic;
import com.centrin.ciyun.enumdef.MedExamRptSyntheticEnum.AnalysisChildType;
import com.centrin.ciyun.enumdef.MedExamRptSyntheticEnum.TableName;
import com.centrin.webbase.ServiceResult;


/**
 * 体检报告综合，调用添加报告、更新报告所有人、更新报告阅读时间、报告删除等
 * @author neilhaha
 *
 */
public interface MedexamRptSyntheticInterface  {
	
	/**
	 * 寻找样例报告，isRead为3
	 * med_exam_rpt,med_lab_sheet
	 * @return
	 */
	public MedExamRptSynthetic findExampleRptLab(TableName table);
	
	/**
	 * 各类检查报告，体检报告、化验单等。当前来源于med_exam_rpt,med_lab_sheet和med_exam_rpt_image
	 * @param rptId
	 * @param format 1:分析表 2图片 3pdf 
	 * @param type 只能是1234567 参考ExamRptType.xml
	 * @param orgname 机构名称
	 * @param exdate 体检日期 yyyy-MM-dd
	 * @param rptname 体检报告名称
	 * @param transferState 报告转换状态 format == 1 && type ==1 时该参数有效
	 * @personId 用户ID
	 * @return
	 * 特别注意：对应图片报告(format==2||format==3)的时候，体检报告数字不变，如果是分析版报告和化验单报告则数字加1
	 */
	public ServiceResult addExamRpt(String rptId,int format,int fileNum,int type,TableName table ,String orgname,String exdate,String rptname,String personId,String hmoId,String userName, int transferState);
	
	/**
	 * 查看已存在的记录
	 * @param tablename
	 * @param rptId
	 * @return
	 */
	public MedExamRptSynthetic find(TableName table,String rptId);
	
	/**
	 * 查找制定类型数据
	 * @param personId (该参数不能为空)
	 * @param type  (该参数不能为空，参考ExamRptType.xml)
	 * @param childtype （可以为null）
	 * @param format （值大于0时执行匹配查询）
	 * @return
	 */
	public List<MedExamRptSynthetic> listByType(String personId,int type,AnalysisChildType childtype,int format);
	
	/**
	 * 检查分析类报告
	 * @param rptId
	 * @param childtype 1、	体检报告解读分析 2、专项管理报告 3、危险分层报告 4、运动分析报告，5、中新惠尔评测
	 * @param orgname  机构名称
	 * @param exdate  时间段 
	 * @param rptname 报告名称
	 * 特别注意：对于专项管理报告，如果已经存在，说明添加了子报告，数字+1，设为未读，报告总数+1.
	 * 其他类型的分析报告因为都来源于用户，不需要修改用户的报告未读数
	 * @return
	 */
	public ServiceResult addAnalysis(String rptId,AnalysisChildType childtype,TableName table,String orgname,String exdate,String rptname,String personId,String hmoId);
	
	/**
	 * 更新基本信息
	 * @param table
	 * @param rptId
	 * @param childType 图片版报告、分析版报告、化验单、该参数填空
	 * @param orgname
	 * @param exdate
	 * @param rptname
	 * @param hmoId
	 * @return
	 */
	public ServiceResult updateRptBaseInfo(TableName table ,String rptId,String newOrgname,String newExcdate,String newRptname,String newHmoid);
	
	/**
	 * 更新基本信息
	 * @param table
	 * @param rptId
	 * @param childType 图片版报告、分析版报告、化验单、该参数填空
	 * @param orgname
	 * @param exdate
	 * @param rptname
	 * @param hmoId
	 * @return
	 */
	public ServiceResult updateRptInfo(TableName table ,String rptId,String newOrgname,String newExcdate,String newRptname,String newHmoid,Integer newRype);
	
	/**
	 * 更新报告的拥有者
	 * @param rptId
	 * @param tablename
	 * @param transferState 报告的format==1 && type ==1 时该参数有效
	 * @return
	 */
	public ServiceResult updateRptOwner(String rptId,String personId,TableName table,String userName, int transferState);
	
	/**
	 * 设置为已读
	 * @param rptId
	 * @param tablename
	 * @return
	 * 报告READ_FLAG变为2时的来源，微信平台为公众号MP_NUM,ANDROID为ANDROID，IOS为IOS
	 */
	public ServiceResult setRptHasbeenRead(String rptId,TableName table,String chnnel);
	
	
	/**
	 * 更新文件数量，针对format=2，3
	 * @param rptId
	 * @param tablename
	 * @param fileNum
	 * @return
	 */
	public ServiceResult updateRptFileNum(String rptId,TableName table,int fileNum);
	
	
	
	/**
	 * 设置为已转化
	 * @param rptId
	 * @param formaterRptId 结构话后的id
	 * @param tablename
	 * @return
	 */
	public ServiceResult setRptHasbeenTransflag(String rptId,String formaterRptId,TableName table);
	
	
	/**
	 * 更新打标签标记
	 * @param rptId
	 * @param tablename
	 * @param isSystemAuto 是否系统自动打标签（手动打填false）
	 * @return
	 */
	public ServiceResult updateSignFlag(String rptId,TableName table,boolean isSystemAuto);
	
	/**
	 *  删除这份报告
	 * @param rptId
	 * @param tablename
 	 * @return
	 */
	public ServiceResult deleteARpt(String rptId,TableName table);
	
	/**
	 * 根据类型查找
	 * @param personId
	 * @param types
	 * @return
	 */
	public List<MedExamRptSynthetic> findByTables(String personId,TableName[] tables);
	
	/**
	 * 更新体检报告解读状态
	 * 
	 * @param realrptId
	 * @param tableName
	 * @param state
	 *            解读状态
	 * @return
	 */
	public ServiceResult updateInterpretState(String realrptId,String tableName, int state);
}