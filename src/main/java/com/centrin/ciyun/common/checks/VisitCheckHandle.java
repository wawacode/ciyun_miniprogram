package com.centrin.ciyun.common.checks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class VisitCheckHandle {
	
	private static Map<Method,Check> map = new HashMap<Method,Check>();
	
	/**
	 * 检查某个方法是否需要登录
	 * @param method
	 * @return
	 */
	public static Boolean isNeedLogin(Method method){
		if(map.get(method)==null){
			init(method);
		}
		return map.get(method).isNeedLogin();
	}
	
	private static void init(Method method){
		if(method.isAnnotationPresent(VisitCheck.class)){
			Check  c = new Check();
			c.setNeedLogin(method.getAnnotation(VisitCheck.class).value());
			map.put(method, c);
		}else if(method.getDeclaringClass().isAnnotationPresent(VisitCheck.class)){
			Check  c = new Check();
			c.setNeedLogin(method.getDeclaringClass().getAnnotation(VisitCheck.class).value());
			map.put(method, c);
		}else{
			map.put(method,getDefaultCheck());
		}
	}
	
	private static Check defaultCheck = null;
	@VisitCheck
	private static Check getDefaultCheck(){
		try {
			if(defaultCheck==null){
				Method m = VisitCheckHandle.class.getDeclaredMethod("getDefaultCheck");
				defaultCheck = new VisitCheckHandle.Check();
				defaultCheck.setNeedLogin(m.getAnnotation(VisitCheck.class).value());
			}
		} catch (Exception e) {
		}
		return defaultCheck;
	}
	
	
	 static class Check{
		boolean needLogin;
		public boolean isNeedLogin() {
			return needLogin;
		}
		public void setNeedLogin(boolean needLogin) {
			this.needLogin = needLogin;
		}
	}
}
