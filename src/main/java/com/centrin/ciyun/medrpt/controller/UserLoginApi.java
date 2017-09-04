package com.centrin.ciyun.medrpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.centrin.ciyun.common.util.http.HttpResponse;

@RequestMapping("/user/authorize")
public class UserLoginApi {
	
	@RequestMapping("/getThirdSession")
	public HttpResponse getThidSession(){
		return null;
	}

}
