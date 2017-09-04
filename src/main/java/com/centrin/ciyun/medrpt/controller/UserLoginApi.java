package com.centrin.ciyun.medrpt.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.medrpt.param.CommonParam;

@RestController
@RequestMapping("/user/authorize")
public class UserLoginApi {
	
	/**
	 * 获取thirdSession
	 * @param param
	 * @return
	 */
	@RequestMapping("/getThirdSession")
	public HttpResponse getThidSession(@RequestBody CommonParam param){
		return null;
	}

}
