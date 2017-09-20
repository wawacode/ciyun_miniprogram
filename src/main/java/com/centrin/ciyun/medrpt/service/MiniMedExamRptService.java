/*
 * 文件名：com.centrin.ciyun.medrpt.service.MiniMedExamRptService.java
 * <p>
 *  <li>简述：<一句话介绍java文件的作用></li>
 *  <li>详述：<详细介绍详细介绍该文件></li>
 * </p>
 * @Copyright: Copyright (c) 2017(或详细描述公司/组织/个人的版权所属)
 * 修改内容：[新增/修改/添加/删除]
 * 修改时间：2017年9月5日 上午11:30:59
 * 修改人：yanxf
 * 
 */
package com.centrin.ciyun.medrpt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.centrin.ciyun.common.constant.ReturnCode;
import com.centrin.ciyun.common.util.CiyunUrlUtil;
import com.centrin.ciyun.common.util.SysParamUtil;
import com.centrin.ciyun.common.util.http.HttpUtils;
import com.centrin.ciyun.entity.hid.HidCertificates;
import com.centrin.ciyun.entity.hid.HidMedCorp;
import com.centrin.ciyun.entity.hid.HidWxKey;
import com.centrin.ciyun.entity.med.MedExamAdvice;
import com.centrin.ciyun.entity.med.MedExamRpt;
import com.centrin.ciyun.entity.med.MedExamRptSynthetic;
import com.centrin.ciyun.entity.med.MedExamSummary;
import com.centrin.ciyun.entity.med.vo.MedReportDetail;
import com.centrin.ciyun.entity.med.vo.MedReportDetail.MedDetail;
import com.centrin.ciyun.entity.vo.HidMedCorpInfoVo;
import com.centrin.ciyun.entity.vo.HidMedCorpRuleInfo;
import com.centrin.ciyun.enumdef.MedExamRptSyntheticEnum;
import com.centrin.ciyun.enumdef.ExamExtrasTempleteType.EExamExtrasTempleteType;
import com.centrin.ciyun.enumdef.MedReportOperator.EMedReportOperator;
import com.centrin.ciyun.medrpt.domain.req.MedCorpRuleParam;
import com.centrin.ciyun.medrpt.domain.req.MedFindRptParam;
import com.centrin.ciyun.medrpt.domain.resp.HttpResponse;
import com.centrin.ciyun.medrpt.domain.vo.CorpDetailVo;
import com.centrin.ciyun.medrpt.domain.vo.HidMedCorpVo;
import com.centrin.ciyun.service.interfaces.bus.MedexamRptSyntheticInterface;
import com.centrin.ciyun.service.interfaces.hid.IDubboHidMedCorpService;
import com.centrin.ciyun.service.interfaces.hid.IDubboHidWxKeyService;
import com.centrin.ciyun.service.interfaces.person.PersonQueryService;
import com.centrin.ciyun.service.interfaces.system.SystemParamCacheInterface;
import com.centrin.webbase.ServiceResult;
import com.ciyun.dubbo.interfaces.med.IMedExamRptService;
import com.ciyun.rptgw.encrypt.CyCipher;
import com.ciyun.rptgw.encrypt.Encryption;

/**
 * <p>
 *  <li>简述：<一句话介绍类的作用></li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
@Service
@SuppressWarnings("unchecked")
public class MiniMedExamRptService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MiniMedExamRptService.class);
	
	private static final String ID_CARD_NAME = "idCard";
	
	@Autowired
	private IMedExamRptService iMedExamRptService;
	
	@Autowired
	private SysParamUtil sysParamUtil;
	
	@Autowired
	private CiyunUrlUtil ciyunUrlUtil;
	
	@Autowired
	private IDubboHidMedCorpService dubboHidMedCorpService;
	
	@Autowired
	private IDubboHidWxKeyService hidWxKeyService;
	
	@Autowired
	private SystemParamCacheInterface systemParamCacheInterface;
	
	@Autowired
	private PersonQueryService personQueryService;
	
	@Autowired
	private MedexamRptSyntheticInterface medexamRptSyntheticInterface;
	
	/**
	 * 
	 * <p>
	 *  <li>根据personid查询体检报告列表</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param personId 用户personid
	 * @return
	 *
	 */
	public HttpResponse<List<MedExamRpt>> listRpt(String personId) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("MiniMedExamRptService#listRpt 参数列表 [personId:" +personId+ "]");
		}
		HttpResponse<List<MedExamRpt>> reportResp = new HttpResponse<List<MedExamRpt>>();
		if (StringUtils.isEmpty(personId)) {
			reportResp.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			reportResp.setMessage("personid为空");
			return reportResp;
		}
		List<MedExamRpt> medExamRptList = iMedExamRptService.medPrimReportList(personId);
		if (null == medExamRptList) {
			MedExamRptSynthetic rptSynthetic = medexamRptSyntheticInterface.findExampleRptLab(MedExamRptSyntheticEnum.TableName.MED_EXAM_RPT);
			if (rptSynthetic != null) {
				medExamRptList = iMedExamRptService.queryByRptIds(new Object[]{Long.parseLong(rptSynthetic.getRealrptId())});
			}
		}
		reportResp.setDatas(medExamRptList);
		return reportResp;
	}
	
	/**
	 * 
	 * <p>
	 *  <li>根据体检报告ID查询体检报告详情信息</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param rptId
	 * @return
	 *
	 */
	public HttpResponse<MedReportDetail> viewRptDetail(String personId, Long medrptId) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("MiniMedExamRptService#viewRptDetail 参数列表 [rptId: " +medrptId+ ", personId: "+personId+"]");
		}
		HttpResponse<MedReportDetail> reportDetailsResp = new HttpResponse<MedReportDetail>();
		if (null == medrptId || medrptId <= 0) {
			reportDetailsResp.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			reportDetailsResp.setMessage("rptId为空或参数不正确");
			return reportDetailsResp;
		}
		MedReportDetail medReportDetail = iMedExamRptService.viewRowDetail(EMedReportOperator.USER, personId, medrptId, EExamExtrasTempleteType.MINIPROGRAM, sysParamUtil.getMpNum());
		//替换体检报告中小结和建议的换行符
		replaceLineStr(medReportDetail);
		//转换日期
		formatDate(medReportDetail);
		reportDetailsResp.setDatas(medReportDetail);
		return reportDetailsResp;
	}
	
	private void formatDate(MedReportDetail medReportDetail) {
		if(medReportDetail != null){
			//替换建议
			if(medReportDetail.getAdviceList() != null && !medReportDetail.getAdviceList().isEmpty()){
				for(MedExamAdvice advice : medReportDetail.getAdviceList()){
					advice.setCreateTime(advice.getCreateTime());
				}
			}
			//替换第一层小结
			if(medReportDetail.getSummaryList() != null && !medReportDetail.getSummaryList().isEmpty()){
				for(MedExamSummary summary : medReportDetail.getSummaryList()){
					summary.setExamTime(summary.getExamTime());
					summary.setRevTime(summary.getRevTime());
				}
			}
			//替换内层内容
			if (medReportDetail.getMedDetails() != null && !medReportDetail.getMedDetails().isEmpty()) {
				Map<String, MedDetail> mapDetails = medReportDetail.getMedDetails();
				for (Iterator<Map.Entry<String, MedDetail>> iter = mapDetails.entrySet().iterator();iter.hasNext();) {
					MedDetail values = iter.next().getValue();
					if (values != null && values.getSummaryList() != null && !values.getSummaryList().isEmpty()) {
						for(MedExamSummary summary : values.getSummaryList()){
							summary.setExamTime(summary.getExamTime());
							summary.setRevTime(summary.getRevTime());
						}
					}
				}
			}
		}
	}
	
	/**
	 * 替换体检报告中小结和建议的换行符
	 * @param medReportDetail 体检报告详情对象
	 */
	public void replaceLineStr(MedReportDetail medReportDetail){
		if(medReportDetail != null){
			//替换建议
			if(medReportDetail.getAdviceList() != null && !medReportDetail.getAdviceList().isEmpty()){
				for(MedExamAdvice advice : medReportDetail.getAdviceList()){
					if(StringUtils.isNotBlank(advice.getAdvice()) && advice.getAdvice().indexOf("<br/>") > -1){
						advice.setAdvice(advice.getAdvice().replace("<br/>", "\n"));
					}
				}
			}
			
			//替换小结
			if(medReportDetail.getSummaryList() != null && !medReportDetail.getSummaryList().isEmpty()){
				for(MedExamSummary summary : medReportDetail.getSummaryList()){
					if(StringUtils.isNotBlank(summary.getSummary()) && summary.getSummary().indexOf("<br/>") > -1){
						summary.setSummary(summary.getSummary().replace("<br/>", "\n"));
					}
				}
			}
			
		}
		
	}
	
	/**
	 * 
	 * <p>
	 *  <li>查询机构中所有体检中心及地址映射信息</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @return
	 *
	 */
	public HttpResponse<List<HidMedCorpVo>>  listMedCorp() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("MiniMedExamRptService#listMedCorp");
		}
		HttpResponse<List<HidMedCorpVo>> medCorpDictResp = new HttpResponse<List<HidMedCorpVo>>();
		Map<String, List<HidMedCorp>> hidMedCorpDict = dubboHidMedCorpService.queryHidMedCorpGroupAreaMap();
		List<HidMedCorpVo> listMedCorpVo = new ArrayList<>();
		if (hidMedCorpDict != null && !hidMedCorpDict.isEmpty()) {
			for (Iterator<Map.Entry<String, List<HidMedCorp>>> iter = hidMedCorpDict.entrySet().iterator(); iter.hasNext(); ) {
				Map.Entry<String, List<HidMedCorp>> entry = iter.next();
				String key = entry.getKey();
				String[] keyArray = key.split(",");
				List<HidMedCorp> medCorpList = entry.getValue();
				HidMedCorpVo medCorpVo = new HidMedCorpVo();
				medCorpVo.setCity(keyArray[1]);
				if (medCorpList != null && !medCorpList.isEmpty()) {
					for (HidMedCorp medCorp : medCorpList) {
						medCorpVo.getHidMedCorpList().add(new CorpDetailVo(medCorp.getMedCorpId(),medCorp.getCorpName()));
					}
				}
				listMedCorpVo.add(medCorpVo);
			} 
		}
		medCorpDictResp.setDatas(listMedCorpVo);
		return medCorpDictResp;
	}
	
	/**
	 * 
	 * <p>
	 *  <li>根据体检中心ID查询体检中心找报告规则</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @return
	 *
	 */
	public HttpResponse<HidMedCorpInfoVo>  queryhidMedRules(MedCorpRuleParam corpRuleParam) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("MiniMedExamRptService#queryhidMedRules 参数信息为：" + (null == corpRuleParam ? "空" : corpRuleParam.toString()));
		}
		HttpResponse<HidMedCorpInfoVo> hidRuleResp = new HttpResponse<HidMedCorpInfoVo>();
		if (null == corpRuleParam || StringUtils.isEmpty(corpRuleParam.getMedCorpId())) {
			LOGGER.error("参数medCorpId为空");
			hidRuleResp.setResult(ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			hidRuleResp.setMessage(ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			return hidRuleResp;
		}
		HidMedCorp medCorp = dubboHidMedCorpService.queryhidMedRules(corpRuleParam.getMedCorpId(), 1);
		if (null == medCorp) {
			LOGGER.error("根据体检中心ID["+corpRuleParam.getMedCorpId()+"], 状态["+1+"]查询体检中心记录为空");
			hidRuleResp.setResult(ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
			hidRuleResp.setMessage(ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
			return hidRuleResp;
		}
		if (StringUtils.isNotBlank(medCorp.getRuleIds())) {
			HidMedCorpInfoVo hidMedCorpRule = new HidMedCorpInfoVo();
			hidMedCorpRule.setMedCorpId(corpRuleParam.getMedCorpId());
			hidMedCorpRule.setSex(corpRuleParam.getSex());
			hidMedCorpRule.setTelephone(corpRuleParam.getTelephone());
			hidMedCorpRule.setUserName(corpRuleParam.getUserName());
			hidMedCorpRule.setRuleIds(medCorp.getRuleIds());
			if (medCorp.getRuleIds().contains(ID_CARD_NAME)) {
				String carTypes = medCorp.getRuleCardType();
				if (StringUtils.isEmpty(carTypes)) {
					LOGGER.error("体检中心报告证件类型为空");
					hidRuleResp.setResult(ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
					hidRuleResp.setMessage(ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
					return hidRuleResp;
				} else {
					String[] cardTypeArray = carTypes.split("\\|");
					Map<String, HidCertificates> hidCertificateMap = (Map<String, HidCertificates>) systemParamCacheInterface.getAllMap("BUS_CARD_TYPE_ENUM");
					if (null == hidCertificateMap || hidCertificateMap.isEmpty()) {
						LOGGER.error("证件类型缓存为空，请检查admin系统缓存是否正常");
						hidRuleResp.setResult(ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
						hidRuleResp.setMessage(ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
						return hidRuleResp;
					}
					Map<String, String> chooseRuleMap = new LinkedHashMap<>();
					for (String idcard : cardTypeArray) {
						if (hidCertificateMap.containsKey(idcard)) {
							HidCertificates hidCertificate = hidCertificateMap.get(idcard);
							//针对航天体检中心的特殊处理，目前只导入报告，航天只能根据体检号导入报告，门诊号是化验单报告的查询条件
							if ("CYH3211204110000".equals(medCorp.getHmoId()) && 30 == hidCertificate.getCerId().intValue()) {
								continue;
							}
							chooseRuleMap.put(idcard, hidCertificate.getCerName());
						}
					}
					hidMedCorpRule.setRuleCardType(chooseRuleMap);
					if (LOGGER.isInfoEnabled()) {
						LOGGER.info("规则详情信息："+hidMedCorpRule.toString());
					}
				}
			}
			hidRuleResp.setDatas(hidMedCorpRule);
		} else {
			LOGGER.error("体检中心报告查询规则为空");
			hidRuleResp.setResult(ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
			hidRuleResp.setMessage(ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
			return hidRuleResp;
		}
		return hidRuleResp;
	}
	
	/**
	 * 
	 * <p>
	 *  <li>根据条件找回报告</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param medFindRptParam 找回报告参数对象
	 * @param session
	 *
	 */
	public JSONObject queryMedRpt(@RequestBody MedFindRptParam medFindRptParam) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("MiniMedExamRptService#queryMedRpt 参数信息为：" + (null == medFindRptParam ? "空" : medFindRptParam.toString()));
		}
		JSONObject jsonResp = new JSONObject();
		jsonResp.put("rptSize", 0);
		jsonResp.put("result", ReturnCode.EReturnCode.OK.key.intValue());
		if (null == medFindRptParam || StringUtils.isEmpty(medFindRptParam.getMedCorpId())) {
			LOGGER.error("参数medCorpId为空");
			jsonResp.put("result", ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			jsonResp.put("message", ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			return jsonResp;
		}
		//适配微信找报告构造HidMedCorpRuleInfo对象
		HidMedCorpRuleInfo ruleInfo = new HidMedCorpRuleInfo();
		ruleInfo.setIdCard(medFindRptParam.getIdCard());
		ruleInfo.setIdCardType(medFindRptParam.getIdCardType());
		ruleInfo.setMedDate(medFindRptParam.getMedDate());
		ruleInfo.setMedPersonNo(medFindRptParam.getMedPersonNo());
		ruleInfo.setMobile(medFindRptParam.getMobile());
		ruleInfo.setSex(medFindRptParam.getSex());
		ruleInfo.setUserName(medFindRptParam.getUserName());
		ruleInfo.setIsFamily(false);
		int rptSize = 0; //标示导入几份报告
		HidMedCorp medCorp = dubboHidMedCorpService.queryhidMedRules(medFindRptParam.getMedCorpId(), 1);
		if (null == medCorp || StringUtils.isEmpty(medCorp.getRuleIds())) {
			LOGGER.error("参数medCorp或ruleIds为空");
			jsonResp.put("result", ReturnCode.EReturnCode.PARAM_IS_NULL.key.intValue());
			jsonResp.put("message", ReturnCode.EReturnCode.PARAM_IS_NULL.value);
			return jsonResp;
		}
		List<String> listRules = Arrays.asList(medCorp.getRuleIds().split("\\|"));
		//判断当前中心是航天健康管理中心
		if ("CYH3211204110000".equals(medCorp.getHmoId())) {
			ruleInfo.setMyMobile(ruleInfo.getMobile());
			String idNo = ruleInfo.getIdCard();
			if(StringUtils.isNotBlank(idNo)){
				idNo = idNo.trim().toUpperCase();
				ruleInfo.setIdCard(idNo);
			}
			JSONObject jsonResult = loadHangTianRptLab(ciyunUrlUtil.getHangtianrptUrl(), medCorp.getHmoId(), medCorp.getMedCorpId(), 1, ruleInfo);
			int result = ReturnCode.EReturnCode.OK.key.intValue();
			String message = "";
			if (null == jsonResult || null == jsonResult.get("result")) {
				result = ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue();
				message = ReturnCode.EReturnCode.SYSTEM_BUSY.value;
			} else {
				result = jsonResult.getIntValue("result");
				message = jsonResult.getString("errMsg");
				rptSize = jsonResult.getInteger("successCount") == null ? 0 : jsonResult.getIntValue("successCount");
			}
			jsonResp.put("result", result != 0 ? ReturnCode.EReturnCode.OTHER_FAILED.key.intValue() : result);
			jsonResp.put("message", message);
			
			//android微信访问时要记录数据库里面已经有的报告数
			/*if ("-10000".equals(result)) {
				int count = iMedExamRptService.countMedRpt(medFindRptParam.getPersonId());
				model.addAttribute("count", count);
			}*/
		} else {//其他直接在慈云库中查询，然后修改对应表的记录
			ServiceResult sr = iMedExamRptService.queryRpt(ruleInfo, medFindRptParam.getMedCorpId(), medFindRptParam.getPersonId(), listRules);
			if (null == sr || sr.getResult() != 0) {
				jsonResp.put("result", ReturnCode.EReturnCode.OTHER_FAILED.key.intValue());
				jsonResp.put("message", null == sr ? ReturnCode.EReturnCode.SYSTEM_BUSY.value : sr.getMsg());
				return jsonResp;
			}
			List<MedExamRpt> listMedExamRpt = (List<MedExamRpt>)sr.getParams();
			if (listMedExamRpt != null && !listMedExamRpt.isEmpty()) {
				try {
					/**
					 * 1.修改这些报告的personId为userInfo的personId.
					 * 2.更新总表med_exam_rpt_synthetic记录
					 * 3。自动打标签
					 * 4.体征数据
					 * 5.ice发送通知
					 */
					iMedExamRptService.updateRpt(listMedExamRpt,personQueryService.getUserinfoByPersonId(medFindRptParam.getPersonId()));
					rptSize = listMedExamRpt.size();
				} catch (Exception ex) {
					LOGGER.error("", ex);
					rptSize = 0;
					jsonResp.put("result", ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
					jsonResp.put("message", "体检报告找回失败");
				}
			} else {
				rptSize = 0;
			}
		}
		jsonResp.put("rptSize", rptSize);
		return jsonResp;
	}
	
	
	/**
	 * 
	 * <p>
	 *  <li>调用接口获取体检报告或化验单</li>
	 * </p>
	 * @author yanxf
	 * @since 1.0
	 * @param mode  1:体检报告  2：化验单
	 * @param url
	 * @param hmoId
	 * @param medCorpId
	 * @param device 0:指app  1:ios  2:android
	 * @param ruleInfo
	 * @return
	 *
	 */
	private JSONObject loadHangTianRptLab(final String url, String hmoId, String medCorpId, int device, HidMedCorpRuleInfo ruleInfo) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
		try {
			final JSONObject json = new JSONObject();
			json.put("auth", "1");
			json.put("ciyun_id", ciyunUrlUtil.getTemplateId());
			json.put("med_id", medCorpId);
			HidWxKey wxKey = hidWxKeyService.getWxKeyByMedCorpId(medCorpId);
			if (null == wxKey) {
				LOGGER.error("体检中心秘钥对象为空");
				resultJson.put("result", ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
				resultJson.put("message", ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
				return resultJson;
			}
			String publicKey = wxKey.getPublicKey();
			Encryption encryptStr = new CyCipher().encrpt(publicKey, JSON.toJSONString(ruleInfo), CyCipher.KeyEnum.publicKey);
			json.put("key", encryptStr.getKey());
			json.put("body", encryptStr.getEncryptContent());
			if (0 == device || 1 == device) {
				String result = HttpUtils.sendHttpPost(url, json.toJSONString());
				resultJson = JSON.parseObject(result);
			} else {
				new Thread(){
					public void run() {
						//此处只发送请求，结果不做处理,只针对android微信，原因：
						//android微信里面ajax请求有10秒限制，超过10秒后会自动重发请求
						HttpUtils.sendHttpPost(url, json.toJSONString());
					}
				}.start();
				
				resultJson.put("result", ReturnCode.EReturnCode.DATA_NOT_EXISTS.key.intValue());
				resultJson.put("message", ReturnCode.EReturnCode.DATA_NOT_EXISTS.value);
			}
		} catch(Exception e) {
			resultJson.put("result", ReturnCode.EReturnCode.SYSTEM_BUSY.key.intValue());
			resultJson.put("message", ReturnCode.EReturnCode.SYSTEM_BUSY.value);
			LOGGER.error("", e);
		}
		return resultJson;
	}
	
}
