package com.centrin.ciyun.common.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;

public class SessionValidateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionValidateUtil.class);
	
	private SessionValidateUtil() {
		
	}
	
	/**
	 * 获取session的sessionKey和openId的字符串
	 * @param param 请求参数对象
	 * @param String sessionKey和openId的字符串
	 * @return
	 */
	public static PerPersonVo getKeyAndOpenIdStr(HttpSession session, String thirdSession){
		PerPersonVo personVo = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
		int count = 0;
		if (null == personVo) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("当前会话已过期,请重新登录");
			}
			count++;
		} else if (StringUtils.isEmpty(thirdSession) || StringUtils.isEmpty(personVo.getThirdSession())) {
			LOGGER.error("会话中的thirdSession为空或从参数中获取的thirdSession为空");
			count++;
		} else if(!personVo.getThirdSession().equals(thirdSession)){
			LOGGER.error("当前请求中传递的参数thirdSession与会话中的thirdSession不匹配，请确认当前操作是否合法");
			count++;
		}
		if(count > 0){
			session.invalidate();
			return null;
		}
		return personVo;
	}
}
