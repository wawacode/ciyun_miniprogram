/*
 * 文件名：com.centrin.ciyun.common.util.MedExamRptInterceptor.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月6日 下午5:22:40
 * 修改人：yanxf
 * 
 */
package com.centrin.ciyun.common.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.centrin.ciyun.common.checks.VisitCheckHandle;
import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.webbase.util.IpUtil;

/**
 * <p>
 *  <li>简述：<一句话介绍类的作用></li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
public class MedExamRptInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MedExamRptInterceptor.class);

	/**
	 * <p>
	 *  <li>简述：<一句话描述方法的作用></li>
	 *  <li>详述：<详细介绍方法的作用，注意事项></li>
	 * </p>
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("ln", System.currentTimeMillis());
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("开始处理请求:"
					+ request.getRequestURI()
					+ ",IP:"
					+ IpUtil.getIpAddr(request)
					+ ",时间:"
					+ PrettyDateFormat.dateToString(Calendar.getInstance()
							.getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		//step1:从缓存中查找是否需要登录标示
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		boolean needLogin = VisitCheckHandle.isNeedLogin(method);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("用户拦截器：needLogin = " + needLogin);
		}
		//step2:如果服务器端session已过期，则统一由客户端发起登录请求进行二次登录,不管微信维护的session是否过期
		if (needLogin) {
			PerPersonVo perPerson = (PerPersonVo)request.getSession().getAttribute(Constant.USER_SESSION);
			if (null == perPerson) {
				HttpResponse<String> errResp = new HttpResponse<>();
				errResp.setResult(ReturnCode.EReturnCode.SESSION_INFO_NOT_EXISTS.key.intValue());
				errResp.setMessage(ReturnCode.EReturnCode.SESSION_INFO_NOT_EXISTS.value);
				try(OutputStream ostream = response.getOutputStream();) {
					ostream.write(JSON.toJSONString(errResp).getBytes("UTF-8"));
				} catch(Exception ex) {
					LOGGER.error("", ex);
				}
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <p>
	 *  <li>简述：<一句话描述方法的作用></li>
	 *  <li>详述：<详细介绍方法的作用，注意事项></li>
	 * </p>
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object handler, ModelAndView modelAndView)
			throws Exception {
	}
	
	/**
	 * <p>
	 *  <li>简述：<一句话描述方法的作用></li>
	 *  <li>详述：<详细介绍方法的作用，注意事项></li>
	 * </p>
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("完成处理请求:" + request.getRequestURI() + " 使用时间:"
					+ (System.currentTimeMillis() - ((long)request.getAttribute("ln"))) + " ms.");
		}
	}
}
