package com.centrin.ciyun.medrpt.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.param.CommonParam;
import com.centrin.ciyun.medrpt.param.PersonBaseInfoParam;
import com.centrin.ciyun.medrpt.service.UserLoginService;

@RestController
@RequestMapping("/user/authorize")
public class UserLoginApi {
	
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
		
		res = userLoginService.getThidSessionByCode(param.getCode(), param.getRequest());
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
		
		res = userLoginService.valSignature(param);
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
		res = userLoginService.validateSmscode(param);
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
		res = userLoginService.login(param);
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
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key);
		}
		return res;
	}

}
