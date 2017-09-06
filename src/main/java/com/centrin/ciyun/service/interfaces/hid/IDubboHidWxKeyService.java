package com.centrin.ciyun.service.interfaces.hid;

import com.centrin.ciyun.entity.hid.HidWxKey;

/**
 * <p>
 *  <li>dubbo服务加密秘钥操作类</li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
public interface IDubboHidWxKeyService {

	/**
	 * 根据体检中心ID查询HidWxKey对象
	 * @param medCorpId 体检中心ID
	 * @return HidWxKey 加密秘钥类对象
	 */
	public HidWxKey getWxKeyByMedCorpId(String medCorpId);

}