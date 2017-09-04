package com.centrin.ciyun.medrpt.service;

import javax.servlet.http.HttpServletRequest;

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
import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.common.util.http.HttpUtils;
import com.centrin.ciyun.medrpt.param.CommonParam;

@Service
public class UserLoginService {

	private static Logger logger = LoggerFactory.getLogger(UserLoginService.class);
	@Autowired
	private SysParamUtil sysParamUtil;
	@Autowired
	private CiyunUrlUtil ciyunUrlUtil;
	
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
		
		//step2: 生成会话key
		String key = SequenceUtils.getTimeMillisSequence();
		
		//step3: 将sessionkey和openId存储在session中
		request.getSession().setAttribute(key, sessionKey + "#" +openId);
		
		res.setResult(ReturnCode.EReturnCode.OK.key);
		res.setMessage(ReturnCode.EReturnCode.OK.value);
		JSONObject datas = new JSONObject();
		datas.put("thirdSession", key);
		res.setDatas(datas);
		return res;
	}
	
	/**
	 * 
	 * @param param
	 * @param keyAndOpendId  保存在session中的sessionKey和用户的openid
	 * @return
	 */
	public HttpResponse valSignature(CommonParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param);
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
	public String getKeyAndOpenIdStr(CommonParam param){
		Object sessionValue = param.getRequest().getSession().getAttribute(param.getThirdSession());
		if(sessionValue == null || StringUtils.isEmpty(sessionValue.toString())){
			return "";
		}
		return sessionValue.toString();
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public HttpResponse validateNote(CommonParam param){
		HttpResponse res = new HttpResponse();
		//step1: 获取session的sessionKey和openId的字符串
		String keyAndOpendId = getKeyAndOpenIdStr(param);
		if(StringUtils.isEmpty(keyAndOpendId)){
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			return res;
		}
		
		//step2: 发送短信验证码
		String sendSmsUrl = ciyunUrlUtil.getSendSmsUrl();
		String note = VerifyCodeUtil.getSmsCode();
		logger.info("注册手机验证码：" + note);
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", param.getTelephone());
		jsonParam.put("message", "【慈云健康】" + note + "，您此次操作的验证码，2分钟内有效");
		
		//HttpUtils.httpObject(resultClass, sendSmsUrl, jsonParam, "");
		res.setResult(ReturnCode.EReturnCode.OK.key);
		res.setMessage(ReturnCode.EReturnCode.OK.value);
		return res;
	}
}