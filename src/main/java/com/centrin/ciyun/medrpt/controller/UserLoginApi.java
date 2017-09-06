package com.centrin.ciyun.medrpt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.checks.VisitCheck;
import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.common.util.SequenceUtils;
import com.centrin.ciyun.medrpt.domain.req.CommonParam;
import com.centrin.ciyun.medrpt.domain.req.PersonBaseInfoParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.ciyun.medrpt.service.UserLoginService;

@RestController
@RequestMapping("/user/authorize")
public class UserLoginApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginApi.class);
	@Autowired
	private UserLoginService userLoginService;
	
	/*private void setPersonSession(HttpSession session) {
		PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
		if (null == perPerson) {
			perPerson = new PerPersonVo();
			perPerson.setPersonId("p160526143010037");
			perPerson.setSex(3);
			perPerson.setTelephone("15818549310");
			perPerson.setUserName("yanxf");
			
			perPerson.setOpenId("o2V2_t0Iehxk0uDWMbF0x0000000");
			perPerson.setSessionKey("HyVFkGl5F5OQWJZZaNzBBg==");
			perPerson.setThirdSession("20170906151818847Z0000195845750015394");
			session.setAttribute(Constant.USER_SESSION, perPerson);
		}

	}*/
	
	/**
	 * 根据小程序的登录授权code获取thirdSession
	 * @param param 请求参数封装对象
	 * @return
	 */
	@VisitCheck(false)
	@ResponseBody
	@RequestMapping("/getThirdSession")
	public HttpResponse getThidSession(@RequestBody CommonParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getCode())){
			LOGGER.error("UserLoginApi >> getThidSession >> 请求参数为空");
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		try{
			res = userLoginService.getThidSessionByCode(param.getCode(), session);
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
	@VisitCheck(false)
	@ResponseBody
	@RequestMapping("/valSignature")
	public HttpResponse valSignature(@RequestBody CommonParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		if(param == null || param.getRawData() == null || 
				StringUtils.isEmpty(param.getSignature()) || StringUtils.isEmpty(param.getThirdSession())){
			LOGGER.error("UserLoginApi >> valSignature >> 请求参数为空");
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		try{
			//setPersonSession(session);
			res = userLoginService.valSignature(param, session);
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
	@VisitCheck(false)
	@ResponseBody
	@RequestMapping("/validsmscode")
	public HttpResponse validateSmscode(@RequestBody CommonParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getTelephone()) || StringUtils.isEmpty(param.getThirdSession())){
			LOGGER.error("UserLoginApi >> validateSmscode >> 请求参数为空");
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		try{
			//setPersonSession(session);
			res = userLoginService.validateSmscode(param, session);
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
	@VisitCheck(false)
	@ResponseBody
	@RequestMapping("/login")
	public HttpResponse login(@RequestBody CommonParam param, HttpServletRequest request){
		HttpResponse res = new HttpResponse();
		if(param == null || StringUtils.isEmpty(param.getThirdSession())){
			LOGGER.error("UserLoginApi >> login >> 请求参数thirdSession为空");
			res.setMessage("请求参数thirdSession为空");
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		try{
			//setPersonSession(request.getSession());
			res = userLoginService.login(param, request);
		}catch(Exception ex){
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}
	
	/**
	 * 修改用户基本信息
	 * @param param
	 * @return
	 */
	@VisitCheck(false)
	@ResponseBody
	@RequestMapping("/updateUserinfo")
	public HttpResponse updateUserinfo(@RequestBody PersonBaseInfoParam param, HttpSession session){
		HttpResponse res = new HttpResponse();
		if(param == null ||  StringUtils.isEmpty(param.getThirdSession())){
			LOGGER.error("UserLoginApi >> updateUserinfo >> 请求参数为空");
			res.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			res.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			return res;
		}
		try {
			res = userLoginService.updateUserinfo(param, session);
		} catch (Exception ex) {
			LOGGER.error("", ex);
			res.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			res.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
		}
		return res;
	}

}
