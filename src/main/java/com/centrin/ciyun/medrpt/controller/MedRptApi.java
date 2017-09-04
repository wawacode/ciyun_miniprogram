package com.centrin.ciyun.medrpt.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrin.ciyun.common.util.http.HttpResponse;
import com.centrin.ciyun.medrpt.param.PersonBaseInfoParam;

@RestController
@RequestMapping("/user/medrpt")
public class MedRptApi {
	@RequestMapping("/saveUserinfo")
	public HttpResponse saveBaseInfo(@RequestBody PersonBaseInfoParam info){
		return null;
	}
}
