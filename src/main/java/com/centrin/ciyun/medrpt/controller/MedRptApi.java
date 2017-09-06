package com.centrin.ciyun.medrpt.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.centrin.ciyun.common.checks.VisitCheck;
import com.centrin.ciyun.common.constant.Constant;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.common.util.SessionValidateUtil;
import com.centrin.ciyun.entity.med.MedExamRpt;
import com.centrin.ciyun.entity.med.vo.MedReportDetail;
import com.centrin.ciyun.entity.vo.HidMedCorpInfoVo;
import com.centrin.ciyun.medrpt.domain.req.BaseEntity;
import com.centrin.ciyun.medrpt.domain.req.MedCorpRuleParam;
import com.centrin.ciyun.medrpt.domain.req.MedFindRptParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.HidMedCorpVo;
import com.centrin.ciyun.medrpt.domain.vo.PerPersonVo;
import com.centrin.ciyun.medrpt.service.MiniMedExamRptService;

@RestController
@RequestMapping("/user/medrpt")
@VisitCheck(true)
public class MedRptApi {
	private static final Logger LOGGER = LoggerFactory.getLogger(MedRptApi.class);
	
	@Autowired
	private MiniMedExamRptService miniMedExamRptService;
	
	@RequestMapping("/list")
	@ResponseBody
	public HttpResponse<List<MedExamRpt>> list(@RequestBody BaseEntity rptEntity, HttpSession session){
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("从前端获取的参数值{thridSession:" + rptEntity.getThirdSession()+"}");
		}
		HttpResponse<List<MedExamRpt>>  listResp = new HttpResponse<List<MedExamRpt>>();
		if (null == SessionValidateUtil.getKeyAndOpenIdStr(session, rptEntity.getThirdSession())) {
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
	public HttpResponse<MedReportDetail> viewRptDetail(@PathVariable("id") Long medrptId, @RequestBody BaseEntity detailEntity, HttpSession session) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("从前端获取的参数值{thridSession:" + detailEntity.getThirdSession()+", id:"+medrptId+"}");
		}
		HttpResponse<MedReportDetail> reportDetailResp = new HttpResponse<MedReportDetail>();
		if (null == SessionValidateUtil.getKeyAndOpenIdStr(session, detailEntity.getThirdSession())) {
			reportDetailResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			reportDetailResp.setMessage("sessionkey在慈云平台已过期");
			return reportDetailResp;
		}
		try {
			PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
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
	public HttpResponse<List<HidMedCorpVo>>  listMedCorp(@RequestBody BaseEntity rptEntity, HttpSession session) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("从前端获取的参数值{thridSession:" + rptEntity.getThirdSession()+"}");
		}
		HttpResponse<List<HidMedCorpVo>> medCorpDictResp = new HttpResponse<List<HidMedCorpVo>>();
		if (null == SessionValidateUtil.getKeyAndOpenIdStr(session, rptEntity.getThirdSession())) {
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
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("从前端获取的参数值" + corpRuleParam.toString());
		}
		HttpResponse<HidMedCorpInfoVo> medCorpRulesResp = new HttpResponse<HidMedCorpInfoVo>();
		if (null == SessionValidateUtil.getKeyAndOpenIdStr(session, corpRuleParam.getThirdSession())) {
			medCorpRulesResp.setResult(ReturnCode.EReturnCode.THIRD_SESSION_KEY.key.intValue());
			medCorpRulesResp.setMessage("sessionkey在慈云平台已过期");
			return medCorpRulesResp;
		}
		try {
			PerPersonVo perPerson = (PerPersonVo)session.getAttribute(Constant.USER_SESSION);
			corpRuleParam.setTelephone(perPerson.getTelephone());
			corpRuleParam.setSex(perPerson.getSex());
			corpRuleParam.setUserName(perPerson.getUserName());
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
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("从前端获取的参数值" + medFindRptParam.toString());
		}
		HttpResponse<Map<String, String>> queryMedRptResp = new HttpResponse<Map<String, String>>();
		if (null == SessionValidateUtil.getKeyAndOpenIdStr(session, medFindRptParam.getThirdSession())) {
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
