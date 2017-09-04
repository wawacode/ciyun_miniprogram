package com.centrin.ciyun.medrpt.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.medrpt.param.PersonBaseInfo;

@RequestMapping("/user/medrpt")
public class MedRptApi {
	@RequestMapping("/userinfo")
	public HttpResponse saveBaseInfo(@RequestBody PersonBaseInfo info){
		return null;
	}
}
