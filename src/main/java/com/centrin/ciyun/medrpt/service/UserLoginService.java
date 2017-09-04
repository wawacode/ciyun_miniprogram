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
import com.centrin.ciyun.common.util.SHA1;
import com.centrin.ciyun.common.util.SequenceUtils;
import com.centrin.ciyun.common.util.SysParamUtil;
import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.common.util.http.HttpUtils;
import com.centrin.ciyun.medrpt.param.CommonParam;

@Service
public class UserLoginService {

	private static Logger logger = LoggerFactory.getLogger(UserLoginService.class);
	@Autowired
	private SysParamUtil sysParamUtil;
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param code 小程序登录授权code
	 * @param request 请求对象
	 * @return
	 */
	public HttpResponse getThidSessionByCode(String code, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
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
		
		//生成会话key
		String key = SequenceUtils.getTimeMillisSequence();
		
		//将sessionkey和openId存储在session中
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
	public HttpResponse valSignature(CommonParam param, String keyAndOpendId){
		HttpResponse res = new HttpResponse();
		String signature2 = SHA1.getSHA1(param.getRawData() + keyAndOpendId.split("#")[0]);
		if(logger.isInfoEnabled()){
			logger.info("UserLoginService >> valSignature >> 传输的签名参数为：" + param.getSignature());
			logger.info("UserLoginService >> valSignature >> 存储在会话的值为：" + keyAndOpendId);
			logger.info("UserLoginService >> valSignature >> 加密后的签名为：" + signature2);
		}
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
}
