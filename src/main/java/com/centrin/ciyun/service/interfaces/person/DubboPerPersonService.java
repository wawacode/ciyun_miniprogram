package com.centrin.ciyun.service.interfaces.person;

import com.centrin.ciyun.entity.person.PerPersonMp;
import com.centrin.webbase.ServiceResult;

public interface DubboPerPersonService {
//	/**
//	 * 用户注册
//	 * @param verifyCodeId
//	 * @param pwd
//	 * @param nickName
//	 * @param ip
//	 * @param channelType
//	 * @param uuid
//	 * @param vipVolumeCode
//	 * @param remarks
//	 * @param channelCode
//	 * @return
//	 */
//	public ServiceResult userRegist(Long verifyCodeId, String pwd, String nickName, String ip, int channelType, String uuid, String vipVolumeCode,String remarks, String channelCode);
//	
//	/**
//	 * 用户登录
//	 * @param loginname
//	 * @param pwd
//	 * @param type 1 pcsite 2 移动app
//	 * @return
//	 */
//	public ServiceResult login(String loginname, String pwd, String ip,
//			int type, String uuid, String deviceCode);

	
	
	
	
//	/**
//	 * 更新用户信息（同时更新token）
//	 * @param person
//	 * @param user
//	 * @return
//	 */
//	public ServiceResult updateUserBasicInfo(String token,String personId, String loginName,String nickName,
//			PerPerson person);
	
	
	
	
	
	/**
	 * 直接从体检报告加人，不去考虑那个groupid怎么来的和怎么处理的，填什么就是什么
		 * 体检报告加人不选组
	 * @param hmoId
	 * @param groupId
	 * @param mobile
	 * @param name
	 * @param birthday
	 * @param idtype
	 * @param idno
	 * @param gender
	 * @param weight
	 * @param height
	 * @return
	 */
		public ServiceResult addPersonFromMedrpt(String hmoId,int serviceId,String groupId,String mobile,String name,String birthday,int gender,int idtype,String idno,
				float weight,int height
				,String createuser,String remarks);
		
		/**
		 * 
		 * @param hmoId
		 * @param groupId
		 * @param serviceId
		 * @param mobile
		 * @param name
		 * @param age
		 * @param idtype
		 * @param idno
		 * @param gender
		 * @return
		 */
		public ServiceResult addPersonFromAdmin(String hmoId,String groupId,int serviceId,String mobile,String name,
				String birthday,int gender,int idtype,String idno,String createuser,String remarks);
		
		
		/**
		 * 微信绑定的时候，一开始是什么都没有
		 * @param mpnum
		 * @param openId
		 * @param mobile
		 * @return
		 */
		public ServiceResult weixinBind(String mpnum,String openId,String mobile,String ip);
		
		 /**
		  * app的绑定，这里不考虑验证码这个事情
		  * @param mobile
		  * @param ip
		  * @return
		  */ 
		public ServiceResult appMobileBind(String mobile, String uuid,String deviceCode,String ip) ;
			 
		/**
		 * 
		 * @param mobile
		 * @param uuid 
		 * @param code  渠道/vip标记
		 * @param nickname 昵称
		 * @param age 年龄
		 * @param gender 性别
		 * @param weight 体重
		 * @param height 身高
		 * @param apptype ios/android
		 * @param ip ip
		 * @return
		 */
		public ServiceResult appRegister(String mobile,String uuid,String channel,String vipcode,String nickname,String birthday,
				int gender,float weight,
				int height,float version,String ip,String remarks);

		
		/**
		 * linkpersonid给mobile注册
		 * @param linkPersonId
		 * @param mobile
		 * @param uuid
		 * @param nickname
		 * @param relationname
		 * @param birthday
		 * @param gender
		 * @param height
		 * @param version
		 * @param ip
		 * @return
		 */
  		public ServiceResult familyRegister(String linkPersonId,String mobile, String uuid,
				 String nickname,String relationname, String birthday, int gender,  int height,
				  float version, String ip);
		
		/**
		 * 上传头像
		 * @param personId
		 * @param pic
		 * @param ip
		 * @param type 1微信2app3管理台
		 * @param createUser
		 * @return
		 */
		public ServiceResult uploadUserPic(String personId,String pic,String ip,int type,String createUser);
		
		/**
		 * 
		 * @param mpnum
		 * @param openid
		 * @return
		 */
		public PerPersonMp savePersonMp(String mpnum,String openid,String personid,String mobile);
		/**
		 * 
		 * @param mpnum
		 * @param openid
		 * @return
		 */
		public int deletePersonMp(String mpNum, String openId,String personId) ;
		
		public ServiceResult updateUsernameOnly(String personId, String userName);
		

		/**
		 * 更新用户基本信息
		 * @param personId
		 * @param nickname
		 * @param birthday
		 * @param idtype
		 * @param idno
		 * @param email
		 * @return
		 */
		public ServiceResult updateBasicInfo(String personId, String nickName,String userName,
				String pic,
				String birthday, int idtype, String idno, int gender,int height,double weight,String email,String createUser);	
		
		public ServiceResult updatePersonInfo(String personId,String hmoId,String groupId,
				String userName, String birthday, int idtype, String idno,
				int gender, int height, double weight, String email,String createUser);
		
		/***
		 * 更新personid的登录状态1是登录2是退出
		 * @param personId
		 * @param state
		 */
		public void updatePersonLoginState(String personId,int state);
		/**
		 * 修改用户基本信息
		 * @param personId
		 * @param nickname
		 * @param birthday
		 * @param gender
		 * @param height
		 * @return
		 */
//		public ServiceResult updateBasicInfo(String personId,String nickname,String birthday,int gender,int height);
		/**
		 * 修改手机号
		 * @param personId
		 * @param mobile
		 * @param ip
		 * @param createUser
		 * @param comments
		 * @return
		 */
		public ServiceResult modifyMobile(String personId,String hmoId,String mobile,String ip,String createUser,String comments);
		
		/**
		 * 删除一个手机号，物理删除
		 * @param mobile
		 * @return
		 */
		public int deletePersons(int type,String object);
		
		public ServiceResult deletePersonFromHmo(String personId,String hmoId,String operator,String comments) ;
		
		public ServiceResult login(String loginname, String pwd, String ip,
				int type, String uuid, String deviceCode) ;
	
		/**
		 * 微信小程序绑定
		 * 
		 * @param mpnum
		 * @param openId
		 * @param mobile
		 * @return
		 */
		public ServiceResult weixinMinaBind(String mpnum, String openId, String mobile, String ip);
}
