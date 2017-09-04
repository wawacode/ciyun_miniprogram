/*
 * 文件名：com.centrin.ciyun.medrpt.configuration.RptDubboConfig.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月4日 下午2:04:40
 * 修改人：yanxf
 * 
 */
package com.centrin.ciyun.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>
 *  <li>简述：<一句话介绍类的作用></li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
@Configuration
@ImportResource({"classpath:dubbo-conf.xml"})
public class RptDubboConfiguration {

}
