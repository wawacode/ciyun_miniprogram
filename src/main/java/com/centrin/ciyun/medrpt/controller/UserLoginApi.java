package com.centrin.ciyun.medrpt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.medrpt.param.CommonParam;
import com.centrin.ciyun.medrpt.service.UserLoginService;

@RestController
@RequestMapping("/user/authorize")
public class UserLoginApi {
	
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param param 请求参数封装对象
	 * @param request 请求对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getThirdSession")
	public HttpResponse getThidSession(@RequestBody CommonParam param, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getCode())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		
		res = userLoginService.getThidSessionByCode(param.getCode(), request);
		return res;
	}
	
	/**
	 * 数据签名校验
	 * @param param 请求参数封装对象
	 * @param request 请求对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getThirdSession")
	public HttpResponse valSignature(@RequestBody CommonParam param, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getRawData()) || 
				StringUtils.isEmpty(param.getSignature()) || StringUtils.isEmpty(param.getThirdSession())){
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key);
			return res;
		}
		
		Object sessionValue = request.getSession().getAttribute(param.getThirdSession());
		if(sessionValue == null || StringUtils.isEmpty(sessionValue.toString())){
			res.setMessage(ReturnCode.EReturnCode.THIRD_SESSION_KEY.value);
			res.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key);
			return res;
		}
		
		res = userLoginService.valSignature(param, sessionValue.toString());
		return res;
		
	}

}
