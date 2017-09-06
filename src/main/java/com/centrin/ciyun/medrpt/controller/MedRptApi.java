package com.centrin.ciyun.medrpt.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.entity.hid.HidMedCorp;
import com.centrin.ciyun.entity.med.MedExamRpt;
import com.centrin.ciyun.entity.med.vo.MedReportDetail;
import com.centrin.ciyun.entity.vo.HidMedCorpInfoVo;
import com.centrin.ciyun.medrpt.domain.req.BaseEntity;
import com.centrin.ciyun.medrpt.domain.req.MedCorpRuleParam;
import com.centrin.ciyun.medrpt.domain.req.MedFindRptParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.ciyun.medrpt.service.MiniMedExamRptService;

@RestController
@RequestMapping("/user/medrpt")
public class MedRptApi {
	private static final Logger LOGGER = LoggerFactory.getLogger(MedRptApi.class);
	
	@Autowired
	private MiniMedExamRptService miniMedExamRptService;
	
	@RequestMapping("/list")
	@ResponseBody
	public HttpResponse<List<MedExamRpt>> list(@RequestBody BaseEntity rptEntity, HttpSession session){
		HttpResponse<List<MedExamRpt>>  listResp = new HttpResponse<List<MedExamRpt>>();
		if (null == rptEntity || StringUtils.isEmpty(rptEntity.getThirdSession())) {
			LOGGER.error("慈云平台生成的sessionkey已失效");
			listResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			listResp.setMessage("sessionkey在慈云平台已过期");
			return listResp;
		}
		try {
			PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
			listResp = miniMedExamRptService.listRpt(perPerson.getPersonId());
		} catch(Exception ex) {
			LOGGER.error("", ex);
			listResp.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			listResp.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return listResp;
	}
	
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public HttpResponse<MedReportDetail> viewRptDetail(@PathVariable("id") Long medrptId, HttpSession session) {
		PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
		HttpResponse<MedReportDetail> reportDetailResp = null;
		try {
			reportDetailResp = miniMedExamRptService.viewRptDetail(perPerson.getPersonId(), medrptId);
		} catch(Exception ex) {
			LOGGER.error("", ex);
			reportDetailResp = new HttpResponse<MedReportDetail>();
			reportDetailResp.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			reportDetailResp.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return reportDetailResp;
	}
	
	@RequestMapping("/listMedCorp")
	@ResponseBody
	public HttpResponse<Map<String, List<HidMedCorp>>>  listMedCorp(@RequestBody BaseEntity rptEntity, HttpSession session) {
		HttpResponse<Map<String, List<HidMedCorp>>> medCorpDictResp = new HttpResponse<Map<String, List<HidMedCorp>>>();
		if (null == rptEntity || StringUtils.isEmpty(rptEntity.getThirdSession())) {
			LOGGER.error("慈云平台生成的sessionkey已失效");
			medCorpDictResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			medCorpDictResp.setMessage("sessionkey在慈云平台已过期");
			return medCorpDictResp;
		}
		try {
			medCorpDictResp = miniMedExamRptService.listMedCorp();
		} catch(Exception ex) {
			LOGGER.error("", ex);
			medCorpDictResp.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			medCorpDictResp.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return medCorpDictResp;
	}
	
	@RequestMapping("/queryRptRules")
	@ResponseBody
	public HttpResponse<HidMedCorpInfoVo>  queryRptRules(@RequestBody MedCorpRuleParam corpRuleParam, HttpSession session) {
		HttpResponse<HidMedCorpInfoVo> medCorpRulesResp = new HttpResponse<HidMedCorpInfoVo>();
		if (null == corpRuleParam || StringUtils.isEmpty(corpRuleParam.getThirdSession())) {
			LOGGER.error("慈云平台生成的sessionkey已失效");
			medCorpRulesResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			medCorpRulesResp.setMessage("sessionkey在慈云平台已过期");
			return medCorpRulesResp;
		}
		try {
			medCorpRulesResp = miniMedExamRptService.queryhidMedRules(corpRuleParam);
		} catch(Exception ex) {
			LOGGER.error("", ex);
			medCorpRulesResp.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			medCorpRulesResp.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return medCorpRulesResp;
	}
	
	@RequestMapping("/importRpt")
	@ResponseBody
	public HttpResponse<Map<String, String>> queryMedRpt(@RequestBody MedFindRptParam medFindRptParam, HttpSession session) {
		HttpResponse<Map<String, String>> queryMedRptResp = new HttpResponse<Map<String, String>>();
		if (null == medFindRptParam || StringUtils.isEmpty(medFindRptParam.getThirdSession())) {
			LOGGER.error("慈云平台生成的sessionkey已失效");
			queryMedRptResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			queryMedRptResp.setMessage("sessionkey在慈云平台已过期");
			return queryMedRptResp;
		}
		try {
			PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
			medFindRptParam.setPersonId(perPerson.getPersonId());
			JSONObject objResp = miniMedExamRptService.queryMedRpt(medFindRptParam);
			if (objResp.getIntValue("result") != ReturnCode.EReturnCode.OK.key.intValue()) {
				queryMedRptResp.setResult(objResp.getIntValue("result"));
				queryMedRptResp.setMessage(objResp.getString("message"));
			} else {
				Map<String, String> dataMap = new LinkedHashMap<>();
				dataMap.put("rptSize", objResp.getString("rptSize"));
				queryMedRptResp.setDatas(dataMap);
			}
		} catch(Exception ex) {
			LOGGER.error("", ex);
			queryMedRptResp.setResult(ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			queryMedRptResp.setMessage(ReturnCode.EReturnCode.SYSTEM_BUSY.value);
		}
		return queryMedRptResp;
	}
	
}
