package com.centrin.ciyun.service.interfaces.hid;

import com.centrin.ciyun.entity.hid.HidExtrasTemplete;


/**
 * <p>
 *  <li>dubbo服务  体检中心模板类服务</li>
 * </p>
 * @author fortyfour
 * 
 */
public interface IDubboHidExtrasTempleteService {
	
	/**
	 * 获取体检中心模板
	 * @param fileId 模板上传的id
	 * @return 体检中心模板
	 */
	public HidExtrasTemplete getHidExtrasTempleteById(String fileId);
	
}
