package com.centrin.ciyun.service.interfaces.person;

import java.util.List;

import com.centrin.ciyun.entity.person.PerPerson;
import com.centrin.ciyun.entity.person.PerPersonMp;
import com.centrin.ciyun.entity.person.PerPersonUser;



/**
 * 与人相关的服务
 * @author neilhaha
 *
 */
public interface PersonQueryService {

	/**
	 * 
	 * @param mpnum
	 * @param personId
	 * @return
	 */
	public PerPersonMp queryFromMpByOpenId( String mpnum,String openId) ;
	
	/**
	 * 
	 * @param mpnum
	 * @param personId
	 * @return
	 */
	public List<PerPersonMp> getMpsByMpnumAndPersonId( String mpNum,String personId) ;
	
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public PerPerson getPersonByMobile(String mobile) ;
	/**
	 * 获取perPerson信息
	 * @param personId
	 * @return
	 */
	public PerPerson getPersonByPersonId(String personId);
	
	public PerPersonUser getPersonUserByPersonId(String personId);
	
	/**
	 * 
	 * @param personId
	 * @return
	 */
	public List<PerPerson> getPersonByPersonId(List<String> personId) ;
	
	/**
	 * 获取用户的基本信息
	 * @param personId
	 * @return
	 */
	/*public Userinfo getUserinfoByPersonId(String personId);*/
	
	
	public PerPersonUser getPersonUserByLoginname(String loginname) ;
	
	/**
	 * 通过userName与mobile查询用户personId，用户名与手机号码至少一个不为空，都不为空时为and关系
	 * @param userName 用户名名
	 * @param mobile 手机号码
	 * @return
	 */
	public List<String> getPersonIdsByMobileAndUname(String userName, String mobile);
}
