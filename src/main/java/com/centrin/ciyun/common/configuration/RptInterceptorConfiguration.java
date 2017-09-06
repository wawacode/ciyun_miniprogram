/*
 * 文件名：com.centrin.ciyun.common.configuration.RptInterceptorConfiguration.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月6日 下午5:26:52
 * 修改人：yanxf
 * 
 */
package com.centrin.ciyun.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.centrin.ciyun.common.util.MedExamRptInterceptor;

/**
 * <p>
 * <li>简述：<一句话介绍类的作用></li>
 * <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * 
 * @author yanxf
 * @since 1.0
 * @see
 */
@Configuration
public class RptInterceptorConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MedExamRptInterceptor()).addPathPatterns("/user");
		super.addInterceptors(registry);
	}
}
