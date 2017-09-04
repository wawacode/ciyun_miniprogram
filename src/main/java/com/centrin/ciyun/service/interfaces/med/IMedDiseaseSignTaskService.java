package com.centrin.ciyun.service.interfaces.med;

import com.centrin.webbase.ServiceResult;

/**
 * 
 * <p>
 *  <li>简述：操作打标签</li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @date 2017年3月27日
 * @since 1.0
 * @version 1.0
 */
public interface IMedDiseaseSignTaskService {
	/**
	 * 
	 * <p>
	 *  <li>简述：保存体检报告任务</li>
	 *  <li>详述：<详细介绍方法的作用，注意事项></li>
	 * </p>
	 * @author yanxf
	 * @since <该方法起源的类版本号>
	 * @param rptId 体检报告ID
	 * @param type  操作类型，1：体检报告
	 * @return
	 *
	 */
	public ServiceResult saveOrUpdateSignTask(Long rptId, Integer type);
}
