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
import com.centrin.ciyun.common.util.CiyunUrlUtil;
import com.centrin.ciyun.common.util.SHA1;
import com.centrin.ciyun.common.util.SequenceUtils;
import com.centrin.ciyun.common.util.SysParamUtil;
import com.centrin.ciyun.common.util.VerifyCodeUtil;
import com.centrin.ciyun.common.util.http.HttpUtils;
import com.centrin.ciyun.entity.person.PerPersonMp;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.ciyun.medrpt.param.CommonParam;
import com.centrin.ciyun.medrpt.param.PersonBaseInfoParam;
import com.centrin.ciyun.service.interfaces.person.PersonQueryService;

@Service
public class UserLoginService {

	private static Logger logger = LoggerFactory.getLogger(UserLoginService.class);
	@Autowired
	private SysParamUtil sysParamUtil;
	@Autowired
	private CiyunUrlUtil ciyunUrlUtil;
	@Autowired
	private PersonQueryService personQueryService;
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param code 小程序登录授权code
	 * @param request 请求对象
	 * @return
	 */
	public HttpResponse getThidSessionByCode(String code, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
		//step1:给获取session_key地址的参数赋值
		String sessionKeyUrl = sysParamUtil.getSessionKeyUrl();
		sessionKeyUrl.replace("%APPID%", sysParamUtil.getAppId());
		sessionKeyUrl.replace("%SECRET%", sysParamUtil.getAppSecret());
		sessionKeyUrl.replace("%JSCODE%", code);
		String result = HttpUtils.sendHttpsUrl(sessionKeyUrl, "get");
		if(StringUtils.isEmpty(result)){
			logger.error("UserLoginService >> getThidSessionByCode >> result为空！");
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			return res;
		}
		
		JSONObject json = JSONObject.parseObject(result);
		if(json == null){
			logger.error("UserLoginService >> getThidSessionByCode >> json对象为null！");
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			return res;
		}
		
		if(ReturnCode.EReturnCode.CODE_IS_WRONG.key == json.getIntValue("errcode")){
			logger.error("UserLoginService >> getThidSessionByCode >> " + ReturnCode.EReturnCode.CODE_IS_WRONG.value);
			res.setResult(ReturnCode.EReturnCode.CODE_IS_WRONG.key);
			res.setMessage(ReturnCode.EReturnCode.CODE_IS_WRONG.value);
			return res;
		}
		
		String openId = json.getString("openid");
		String sessionKey= json.getString("session_key");
		
		//step2：生成会话key
		String key = SequenceUtils.getTimeMillisSequence();
		
		//step3：将sessionkey和openId存储在session中
		request.getSession().setAttribute(key, sessionKey + "#" +openId);
		
		//step4：根据openId和mpNum查询用户是否绑定了小程序
		PersonQueryService personQueryService = null;
		PerPersonMp perPersonMp = personQueryService.queryFromMpByOpenId(sysParamUtil.getMpNum(), openId);
		if(perPersonMp != null){
			PerPersonVo personVo = new PerPersonVo();
			personVo.setOpenId(openId);
			personVo.setMpNum(sysParamUtil.getMpNum());
			personVo.setSessionKey(sessionKey);
			personVo.setPersonId(perPersonMp.getPersonId());
			//step5: 将用户信息保存在session中
			request.getSession().setAttribute(Constant.USER_SESSION, personVo);
		}
		
		res.setResult(ReturnCode.EReturnCode.OK.key);
		res.setMessage(ReturnCode.EReturnCode.OK.value);
		JSONObject datas = new JSONObject();
		datas.put("thirdSession", key);
		res.setDatas(datas);
		return res;
	}
	
	/**
	 * 数据签名校验
	 * @param param 请求参数对象
	 * @param keyAndOpendId  保存在session中的sessionKey和用户的openid
	 * @return
	 */
	public HttpResponse valSignature(CommonParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param.getRequest().getSession(), param.getThirdSession());
		if(StringUtils.isEmpty(keyAndOpendId)){
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2: 对用户数据加密
		String signature2 = SHA1.getSHA1(param.getRawData() + keyAndOpendId.split("#")[0]);
		if(logger.isInfoEnabled()){
			logger.info("UserLoginService >> valSignature >> 传输的签名参数为：" + param.getSignature());
			logger.info("UserLoginService >> valSignature >> 存储在会话的值为：" + keyAndOpendId);
			logger.info("UserLoginService >> valSignature >> 加密后的签名为：" + signature2);
		}
		
		//step3: 数据签名校验
		if(!param.getSignature().equals(signature2)){
			logger.error("UserLoginService >> valSignature >> " + ReturnCode.EReturnCode.DATA_VALIDATE_FAIL.value);
			res.setResult(ReturnCode.EReturnCode.DATA_VALIDATE_FAIL.key);
			res.setMessage(ReturnCode.EReturnCode.DATA_VALIDATE_FAIL.value);
			return res;
		}
		res.setResult(ReturnCode.EReturnCode.OK.key);
		res.setMessage(ReturnCode.EReturnCode.OK.value);
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
	public HttpResponse validateSmscode(CommonParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param.getRequest().getSession(), param.getThirdSession());
		if(StringUtils.isEmpty(keyAndOpendId)){
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2: 发送短信验证码
		String sendSmsUrl = ciyunUrlUtil.getSendSmsUrl();
		String smscode = VerifyCodeUtil.getSmsCode();
		logger.info("注册手机验证码：" + smscode);
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", param.getTelephone());
		jsonParam.put("message", "【慈云健康】" + smscode + "，您此次操作的验证码，2分钟内有效");
		param.getRequest().getSession().setAttribute(Constant.SMSCODE_SESSION, smscode + "#" + System.currentTimeMillis());
		res = HttpUtils.httpObject(HttpResponse.class, sendSmsUrl, jsonParam, "");
		if(res.getResult() != ReturnCode.EReturnCode.OK.key){
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return res;
	}
	
	/**
	 * 用户注册/登录
	 * @param param 请求参数对象
	 * @return
	 */
	public HttpResponse login(CommonParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param.getRequest().getSession(), param.getThirdSession());
		if(StringUtils.isEmpty(keyAndOpendId)){
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2：从session查询用户是否存在
		PerPersonVo personVo = (PerPersonVo)param.getRequest().getSession().getAttribute(Constant.USER_SESSION);
		if(personVo == null){
			//step2.1：存在，直接返回信息
			//step2.2：不存在，调用添加用户的接口
			//step3：
			String openId = keyAndOpendId.split("#")[1];
		}
		
		
		return res;
	}
	
	
	/**
	 * 保存用户基本信息
	 * @param param 请求参数对象
	 * @return
	 */
	public HttpResponse saveUserinfo(PersonBaseInfoParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param.getRequest().getSession(), param.getThirdSession());
		if(StringUtils.isEmpty(keyAndOpendId)){
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		String openId = keyAndOpendId.split("#")[1];

	
		return res;
	}
}
