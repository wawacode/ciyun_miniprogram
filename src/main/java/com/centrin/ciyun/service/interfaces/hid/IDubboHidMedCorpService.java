package com.centrin.ciyun.service.interfaces.hid;

import java.util.List;

import com.centrin.ciyun.entity.hid.HidMedCorp;

/**
 * <p>
 *  <li>体检中心的dubbo相关操作</li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
public interface IDubboHidMedCorpService {

	/**
	 * 
	 * <p>
	 *  <li>根据体检中心ID查询体检中心对象</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param medCorpId 体检中心ID
	 * @return HidMedCorp 体检中心对象
	 *
	 */
	public HidMedCorp queryHidByMedCorpId(String medCorpId);
	
	/**
	 * 
	 * <p>
	 *  <li>根据健管机构ID查询当前健管机构下所有的体检中心</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param hmoId  健管机构
	 * @return List<HidMedCorp> 体检中心列表
	 *
	 */
	public List<HidMedCorp> queryHidByHmoId(String hmoId);
	
	/**
	 * 
	 * <p>
	 *  <li>查询所有的体检中心</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @return List<HidMedCorp> 体检中心列表
	 *
	 */
	public List<HidMedCorp> queryAllMedCorp();
	
	/**
	 * 
	 * <p>
	 *  <li>根据状态和健管机构id查询健管机构信息</li>
	 * </p>
	 * @author zhangjiecong
	 * @since 1.0
	 * @param state 状态
	 * @param hmoId 健管机构id
	 * @return
	 *
	 */
	public List<HidMedCorp> getHidMedCorpList(int state, String hmoId);
}