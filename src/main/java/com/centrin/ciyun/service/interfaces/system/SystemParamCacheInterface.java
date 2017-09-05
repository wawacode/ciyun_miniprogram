package com.centrin.ciyun.service.interfaces.system;

import java.util.Map;

/**
 * <p>
 *  <li>获取系统参数接口</li>
 * </p>
 * @author zhangjiecong
 * @since  1.0
 * @see 
 */
public interface SystemParamCacheInterface {
	/**
	 * 获取所有的系统参数map集合
	 * @param mpNum
	 * @param openId
	 * @param personId
	 */
	public Map<String, String> findSysParamsMap();
	
	/**
	 * 获取所有的系统字段集合
	 * @param mpNum
	 * @param openId
	 * @param personId
	 */
	public Map<String, Map> findAllDict();
	
	/**
	 * 获取enum集合
	 * @param mpNum
	 * @param openId
	 * @param personId
	 */
	public Map<String, Map> findAllEnumdef();
	
	/**
	 * 
	 * <p>
	 *  <li>获取map类型的缓存</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @return
	 *
	 */
	public Map<?, ?> getAllMap(String cacheType);
}
