package com.centrin.ciyun.service.interfaces.hid;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 	日常记录
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
public interface IHidItemRecordDiyService {
	
	/**
	 * 删除体征记录
	 * @param rptId 报告id
	 * @return
	 */
	public void deleteByRptId(Long rptId);

	/**
	 * 保存血糖
	 * @param type 000061616100", "空腹血糖" 000061616300", "餐后1小时血糖", "000061616400","餐后2小时血糖"
	 * @param value     血糖值 
	 * @param hight     身高
	 * @param age       年龄
	 * @param gender    性别
	 * @param doTime    录入时间
	 * @param personId  personId
	 * @param userId    userId
	 * @param username
	 * @param reportId  体检报告id
	 * @return
	 */
	@Transactional
	public long saveBloodsugar(String type, float value, int hight, int age,
			int gender, Date doTime, String personId, String userId,
			String username, long reportId);

	/**
	 * 保存尿酸
	 * @param type  "000061612300", "尿酸"
	 * @param value     尿酸值
	 * @param hight    身高
	 * @param age       年龄
	 * @param gender    性别
	 * @param doTime    录入时间
	 * @param personId  personId
	 * @param userId    userId
	 * @param username
	 * @param reportId  体检报告id
	 * @return
	 */
	@Transactional
	public long saveUricacid(String type, float value, int hight, int age,
			int gender, Date doTime, String personId, String userId,
			String username, long reportId);

	/**
	 * 保存体重
	 * @param type  "000001010200", "体重"
	 * @param value     尿酸值
	 * @param hight    身高
	 * @param age       年龄
	 * @param gender    性别
	 * @param doTime    录入时间
	 * @param personId  personId
	 * @param userId    userId
	 * @param username
	 * @param reportId  体检报告id
	 * @return
	 */
	@Transactional
	public long saveWeight(String type, float value, int hight, int age,
			int gender, Date doTime, String personId, String userId,
			String username, long reportId);
	
	/**
	 * 保存血压
	 * 
	 * @param type
	 *            g000001
	 * @param diastolic
	 *            舒张压
	 * @param systolic
	 *            收缩压
	 * @param pulse
	 *            脉搏
	 * @param age
	 *            年龄
	 * @param gender
	 *            性别
	 * @param doTime
	 *            录入时间
	 * @param personId
	 *            personId
	 * @param username
	 * @param reportId
	 *            体检报告id
	 * @return
	 */
	public long saveBooldPressure(float diastolic, float systolic, float pulse,
			int gender, Date doTime, String personId, String username,
			 long reportId);
	
	/**
	 * 
	 * <p>
	 *  <li>当客户找回体检报告后，需要将之前生成日常记录中的personid做相应修改</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param oldPersonId 旧personid
	 * @param newPersonId 新personid
	 * @param rptId   体检报告ID
	 * @return
	 *
	 */
	public long updateRecordOwner(String oldPersonId, String newPersonId, String rptId);

}
