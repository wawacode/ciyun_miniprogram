package com.centrin.ciyun.medrpt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.common.constant.ReturnCode.EReturnCode;
import com.centrin.ciyun.common.util.CiyunUrlUtil;
import com.centrin.ciyun.common.util.SHA1;
import com.centrin.ciyun.common.util.SequenceUtils;
import com.centrin.ciyun.common.util.SessionValidateUtil;
import com.centrin.ciyun.common.util.SysParamUtil;
import com.centrin.ciyun.common.util.VerifyCodeUtil;
import com.centrin.ciyun.common.util.http.HttpUtils;
import com.centrin.ciyun.entity.person.PerPerson;
import com.centrin.ciyun.entity.person.PerPersonMp;
import com.centrin.ciyun.enumdef.UserLoginStatus.ELoginStatus;
import com.centrin.ciyun.enumdef.personStatus.EPersonStatus;
import com.centrin.ciyun.medrpt.domain.req.CommonParam;
import com.centrin.ciyun.medrpt.domain.req.PersonBaseInfoParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.ciyun.service.interfaces.person.DubboPerPersonService;
import com.centrin.ciyun.service.interfaces.person.PersonQueryService;
import com.centrin.webbase.ServiceResult;

@Service
public class UserLoginService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);
	@Autowired
	private SysParamUtil sysParamUtil;
	@Autowired
	private CiyunUrlUtil ciyunUrlUtil;
	@Autowired
	private PersonQueryService personQueryService;
	@Autowired
	private DubboPerPersonService dubboPerPersonService;
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param code 小程序登录授权code
	 * @param request 请求对象
	 * @return
	 */
	public HttpResponse getThidSessionByCode(String code, HttpSession session){
		HttpResponse res = new HttpResponse();
		//step1:给获取session_key地址的参数赋值
		String sessionKeyUrl = sysParamUtil.getSessionKeyUrl();
		sessionKeyUrl = sessionKeyUrl.replace("%APPID%", sysParamUtil.getAppId());
		sessionKeyUrl = sessionKeyUrl.replace("%SECRET%", sysParamUtil.getAppSecret());
		sessionKeyUrl = sessionKeyUrl.replace("%JSCODE%", code);
		String result = HttpUtils.sendHttpsUrl(sessionKeyUrl, "POST");
		if(StringUtils.isEmpty(result)){
			LOGGER.error("UserLoginService >> getThidSessionByCode >> result为空！");
			res.setResult(EReturnCode.SYSTEM_BUSY.key.intValue());
			res.setMessage(EReturnCode.SYSTEM_BUSY.value);
			return res;
		}
		
		JSONObject json = JSONObject.parseObject(result);
		if(json == null){
			LOGGER.error("UserLoginService >> getThidSessionByCode >> json对象为null！");
			res.setResult(EReturnCode.SYSTEM_BUSY.key.intValue());
			res.setMessage(EReturnCode.SYSTEM_BUSY.value);
			return res;
		}
		
		if(EReturnCode.CODE_IS_WRONG.key.intValue() == json.getIntValue("errcode")){
			LOGGER.error("UserLoginService >> getThidSessionByCode >> " + EReturnCode.CODE_IS_WRONG.value);
			res.setResult(EReturnCode.CODE_IS_WRONG.key.intValue());
			res.setMessage(EReturnCode.CODE_IS_WRONG.value);
			return res;
		}
		
		if(EReturnCode.CODE_IS_USED.key.intValue() == json.getIntValue("errcode")){
			LOGGER.error("UserLoginService >> getThidSessionByCode >> " + EReturnCode.CODE_IS_USED.value);
			res.setResult(EReturnCode.CODE_IS_USED.key.intValue());
			res.setMessage(EReturnCode.CODE_IS_USED.value);
			return res;
		}
		
		String openId = json.getString("openid");
		String sessionKey= json.getString("session_key");
		
		//step2：生成会话key
		String thirdSession = SequenceUtils.getTimeMillisSequence();
		
		PerPersonVo personVo = new PerPersonVo();
		personVo.setOpenId(openId);
		personVo.setMpNum(sysParamUtil.getMpNum());
		personVo.setSessionKey(sessionKey);
		personVo.setThirdSession(thirdSession);
		
		JSONObject datas = new JSONObject();
		datas.put("thirdSession", thirdSession);
		
		//step4：根据openId和mpNum查询用户是否绑定了小程序
		PerPersonMp perPersonMp = personQueryService.queryFromMpByOpenId(sysParamUtil.getMpNum(), openId);
		if(perPersonMp != null){
			PerPerson person = personQueryService.getPersonByPersonId(perPersonMp.getPersonId());
			if(person != null){
				personVo.setPersonId(person.getPersonId());
				personVo.setSex(person.getGender());
				personVo.setTelephone(person.getMobile());
				personVo.setUserName(person.getUserName());
			}
			datas.put("personStatus", EPersonStatus.YES_LOGIN.key);
		}else{
			datas.put("personStatus", EPersonStatus.NOREG_AND_NOREG_NOLOG.key);
		}
		
		//step5: 用户绑定小程序的信息保存在session中
		session.removeAttribute(Constant.USER_SESSION);
		session.setAttribute(Constant.USER_SESSION, personVo);
		
		res.setResult(EReturnCode.OK.key.intValue());
		res.setMessage(EReturnCode.OK.value);
		res.setDatas(datas);
		return res;
	}
	
	/**
	 * 数据签名校验
	 * @param param 请求参数对象
	 * @param keyAndOpendId  保存在session中的sessionKey和用户的openid
	 * @return
	 */
	public HttpResponse valSignature(CommonParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		//step1: 校验用户会话：校验通过，返回用户信息对象；校验不通过，返回null
		PerPersonVo personVo = SessionValidateUtil.getKeyAndOpenIdStr(session, param.getThirdSession());
		if(null == personVo){
			LOGGER.error("慈云平台生成的sessionkey已失效");
			res.setResult(EReturnCode.THIRD_SESSION_KEY.key.intValue());
			res.setMessage(EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2: 对用户数据加密
		String signature2 = SHA1.getSHA1(JSONObject.toJSONString(param.getRawData()) + personVo.getSessionKey());
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("UserLoginService >> valSignature >> 传输的签名参数为：" + param.getSignature());
			LOGGER.info("UserLoginService >> valSignature >> 用户数据：" + JSONObject.toJSONString(param.getRawData()));
			LOGGER.info("UserLoginService >> valSignature >> 要加密的串：" + param.getRawData() + personVo.getSessionKey());
			LOGGER.info("UserLoginService >> valSignature >> 加密后的签名为：" + signature2);
		}
		
		//step3: 数据签名校验
		if(!param.getSignature().equals(signature2)){
			LOGGER.error("UserLoginService >> valSignature >> " + EReturnCode.DATA_VALIDATE_FAIL.value);
			res.setResult(EReturnCode.DATA_VALIDATE_FAIL.key.intValue());
			res.setMessage(EReturnCode.DATA_VALIDATE_FAIL.value);
			return res;
		}
		res.setResult(EReturnCode.OK.key.intValue());
		res.setMessage(EReturnCode.OK.value);
		return res;
	}
	
	/**
	 * 获取session的sessionKey和openId的字符串
	 * @param param 请求参数对象
	 * @param String sessionKey和openId的字符串
	 * @return
	 */
	public String getKeyAndOpenIdStr(HttpSession session, String thirdSession){
		Object sessionValue = session.getAttribute(thirdSession);
		if(sessionValue == null || StringUtils.isEmpty(sessionValue.toString())){
			return "";
		}
		return sessionValue.toString();
	}
	
	/**
	 * 发送短信验证码
	 * @param param 请求参数对象
	 * @return
	 */
	public HttpResponse validateSmscode(CommonParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		//step1: 校验用户会话：校验通过，返回用户信息对象；校验不通过，返回null
		PerPersonVo personVo = SessionValidateUtil.getKeyAndOpenIdStr(session, param.getThirdSession());
		if(null == personVo){
			LOGGER.error("慈云平台生成的sessionkey已失效");
			res.setResult(EReturnCode.THIRD_SESSION_KEY.key.intValue());
			res.setMessage(EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2: 发送短信验证码
		String sendSmsUrl = ciyunUrlUtil.getSendSmsUrl();
		String smscode = VerifyCodeUtil.getSmsCode();
		LOGGER.info("注册手机验证码：" + smscode);
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", param.getTelephone());
		jsonParam.put("message", "【慈云健康】" + smscode + "，您此次操作的验证码，2分钟内有效");
		session.setAttribute(Constant.SMSCODE_SESSION, smscode + "#" + System.currentTimeMillis());
		res = HttpUtils.httpObject(HttpResponse.class, sendSmsUrl, jsonParam, "");
		if(res.getResult() != EReturnCode.OK.key.intValue()){
			res.setResult(EReturnCode.NOTE_SEND_FAIL.key.intValue());
		}
		return res;
	}
	
	/**
	 * 用户注册/登录
	 * @param param 请求参数对象
	 * @return
	 */
	public HttpResponse login(CommonParam param, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
		//step1: 校验用户会话：校验通过，返回用户信息对象；校验不通过，返回null
		PerPersonVo personVo = SessionValidateUtil.getKeyAndOpenIdStr(request.getSession(), param.getThirdSession());
		if(null == personVo){
			LOGGER.error("慈云平台生成的sessionkey已失效");
			res.setResult(EReturnCode.THIRD_SESSION_KEY.key.intValue());
			res.setMessage(EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		if(param == null || StringUtils.isEmpty(param.getTelephone()) || StringUtils.isEmpty(param.getSmscode())){
			LOGGER.error("UserLoginApi >> login >> 请求手机号码或短信验证码为空");
			res.setMessage("请求手机号码或短信验证码为空");
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		
		//step3: 校验短信验证码
		validateSmsCode(request.getSession(), res, param.getSmscode());
		if(res.getResult() != EReturnCode.OK.key.intValue()){
			return res;
		}
		
		//step4：用户绑定小程序的信息不存在于session中
		//调用添加用户的接口
		ServiceResult sr = dubboPerPersonService.weixinMinaBind(sysParamUtil.getMpNum(), personVo.getOpenId(), param.getTelephone(), request.getRemoteAddr());
			
		if(sr.getResult() == EReturnCode.OK.key.intValue() || sr.getResult() == 1){ //未注册
			res.setResult(EReturnCode.OK.key.intValue());
			res.setMessage(EReturnCode.OK.value);
			JSONObject datas = new JSONObject();
			datas.put("isRegisterAndLogin", ELoginStatus.REGISTER_FIRST);
			res.setDatas(datas);
		}else if(sr.getResult() == 9999){ //已注册
			res.setResult(EReturnCode.OK.key.intValue());
			res.setMessage(EReturnCode.OK.value);
			JSONObject datas = new JSONObject();
			datas.put("isRegisterAndLogin", ELoginStatus.YES_REGISTER_NO_LOGIN);
			res.setDatas(datas);
		}
		
		PerPerson person = (PerPerson)sr.getParams();
		if(person == null){
			LOGGER.error("UserLoginService >> login >> 调用dubbo添加用户的接口，返回的params is null");
			res.setResult(EReturnCode.DATA_NOT_EXISTS.key.intValue());
			res.setMessage(EReturnCode.DATA_NOT_EXISTS.value);
			return res;
		}
		
		//step5: 将绑定小程序的用户信息存储在session
		addPersonToSession(request.getSession(), personVo, person);
		
		return res;
	}
	
	/**
	 * 校验短信验证码
	 * @param session 会话对象
	 * @param res 返回的封装对象
	 * @param smsCode 短信验证码
	 */
	public void validateSmsCode(HttpSession session, HttpResponse res, String smsCode){
		Object smsCodeSession = session.getAttribute(Constant.SMSCODE_SESSION);
		if(smsCodeSession == null || StringUtils.isEmpty(smsCodeSession.toString())){
			LOGGER.error("慈云平台生成的sessionkey已失效");
			res.setResult(EReturnCode.NOTE_IS_INVALID.key);
			res.setMessage(EReturnCode.NOTE_IS_INVALID.value);
			return;
		}
		
		//校验短信验证码是否过期
		if(System.currentTimeMillis()- Long.parseLong(smsCodeSession.toString().split("#")[1]) >= Constant.EFFECTIVE_TIME){
			LOGGER.error(EReturnCode.NOTE_IS_INVALID.value);
			res.setResult(EReturnCode.NOTE_IS_INVALID.key.intValue());
			res.setMessage(EReturnCode.NOTE_IS_INVALID.value);
			return;
		}
		
		if(!smsCodeSession.toString().split("#")[0].equals(smsCode)){
			LOGGER.error(EReturnCode.NOTE_IS_WRONG.value);
			res.setResult(EReturnCode.NOTE_IS_WRONG.key.intValue());
			res.setMessage(EReturnCode.NOTE_IS_WRONG.value);
			return;
		}
		
	}
	
	/**
	 * 将绑定小程序的用户信息存储在session
	 * @param session 当前会话对象
	 * @param personVo 用户输出对象
	 * @param person 用户对象
	 */
	public void addPersonToSession(HttpSession session, PerPersonVo personVo, PerPerson person){
		personVo.setPersonId(person.getPersonId());
		personVo.setSex(person.getGender());
		personVo.setTelephone(person.getMobile());
		personVo.setUserName(person.getUserName());
		//用户绑定小程序的信息保存在session中
		session.setAttribute(Constant.USER_SESSION, personVo);
	}
	
	/**
	 * 修改用户基本信息
	 * @param param 请求参数对象
	 * @return
	 */
	public HttpResponse updateUserinfo(PersonBaseInfoParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		//step1: 校验用户会话：校验通过，返回用户信息对象；校验不通过，返回null
		PerPersonVo personVo = SessionValidateUtil.getKeyAndOpenIdStr(session, param.getThirdSession());
		if(null == personVo){
			LOGGER.error("慈云平台生成的sessionkey已失效");
			res.setResult(EReturnCode.THIRD_SESSION_KEY.key.intValue());
			res.setMessage(EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2：修改用户信息
		ServiceResult sr = dubboPerPersonService.updateBasicInfo(personVo.getPersonId(), param.getNickName(), null, null, null, 0, null, param.getGender() == null ? 3 : param.getGender().intValue(), param.getHeight() == null? 0 :param.getHeight().intValue(), 0, "", param.getNickName());
		if(sr.getResult() != EReturnCode.OK.key.intValue()){
			res.setResult(EReturnCode.SYSTEM_BUSY.key.intValue());
			res.setMessage(sr.getMsg());
			return res;
		}
		
		res.setResult(EReturnCode.OK.key.intValue());
		res.setMessage(EReturnCode.OK.value);
		return res;
	}

}
