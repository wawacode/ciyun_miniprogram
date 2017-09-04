package com.centrin.ciyun.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *  <li>简述：<一句话介绍类的作用></li>
 *  <li>详述：<详细介绍类的方法及作用></li>
 * </p>
 * @author yanxf
 * @since  1.0
 * @see 
 */
public class ApiUtils {
	private ApiUtils() {
		
	}
	
	/**
	 * 
	 * <p>
	 *  <li>简述：生成取消预约的url地址</li>
	 *  <li>详述：<详细介绍方法的作用，注意事项></li>
	 * </p>
	 * @author yanxf
	 * @since <该方法起源的类版本号>
	 * @param cancelBaseUrl
	 * @param expiresInsp
	 * @param mobileEnc
	 * @return
	 *
	 */
	public static String generateCancelInspUrl(String cancelBaseUrl,String medCorpId, String inspId, String mobileEnc) {
		if (StringUtils.isEmpty(cancelBaseUrl)) {
			return "";
		}
		String randNum = "" + System.currentTimeMillis();
		List<String> cancelParamList = new ArrayList<>();
		cancelParamList.add(mobileEnc);
		cancelParamList.add(medCorpId);
		cancelParamList.add(inspId);
		cancelParamList.add(randNum);
		String signature = SignatureUtils.generateSignatureStr(cancelParamList);
		StringBuilder sBuider = new StringBuilder(cancelBaseUrl);
		if (cancelBaseUrl.indexOf("?") != -1) {
			sBuider.append("&");
		} else {
			sBuider.append("?");
		}
		String queryUrl = "mobile="+mobileEnc+"&third_insp_id="+inspId+"&rand_num="+randNum+"&signature="+signature;
		sBuider.append(queryUrl);
		return sBuider.toString().trim();
	}
}
