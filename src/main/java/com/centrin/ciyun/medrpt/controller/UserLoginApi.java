package com.centrin.ciyun.medrpt.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.medrpt.domain.req.CommonParam;
import com.centrin.ciyun.medrpt.domain.req.PersonBaseInfoParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.service.UserLoginService;

@RestController
@RequestMapping("/user/authorize")
public class UserLoginApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginApi.class);
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param param 请求参数封装对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getThirdSession")
	public HttpResponse getThidSession(@RequestBody CommonParam param){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getCode())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		try{
			res = userLoginService.getThidSessionByCode(param.getCode(), param.getSession());
		}catch(Exception ex){
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}
	
	/**
	 * 数据签名校验
	 * @param param 请求参数封装对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getThirdSession")
	public HttpResponse valSignature(@RequestBody CommonParam param){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getRawData()) || 
				StringUtils.isEmpty(param.getSignature()) || StringUtils.isEmpty(param.getThirdSession())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		try{
			res = userLoginService.valSignature(param);
		}catch(Exception ex){
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
		
	}
	
	/**
	 * 发送短信验证码
	 * @param param 请求参数封装对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validsmscode")
	public HttpResponse validateSmscode(@RequestBody CommonParam param){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getTelephone()) || StringUtils.isEmpty(param.getThirdSession())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		try{
			res = userLoginService.validateSmscode(param);
		}catch(Exception ex){
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}
	
	/**
	 * 用户注册/登录
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public HttpResponse login(@RequestBody CommonParam param){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getTelephone()) 
				|| StringUtils.isEmpty(param.getThirdSession()) || StringUtils.isEmpty(param.getSmscode())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		try{
			res = userLoginService.login(param);
		}catch(Exception ex){
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}
	
	/**
	 * 保存用户基本信息
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUserinfo")
	public HttpResponse saveUserinfo(@RequestBody PersonBaseInfoParam param){
		HttpResponse res = new HttpResponse();
		if(param == null ||  StringUtils.isEmpty(param.getThirdSession())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		try {
			res = userLoginService.saveUserinfo(param);
		} catch (Exception ex) {
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}

}
