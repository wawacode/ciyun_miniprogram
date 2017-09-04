package com.centrin.ciyun.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	private static Log LOG = LogFactory.getLog(HttpUtil.class);

	private static int TIME_OUT = 30 * 1000;
	public static final String ERROR = "ERROR";
	
	public static String sendRequest(String urlstr, String postdata, String method) {
		StringBuilder tempStr = new StringBuilder();
		InputStream in = null;
		BufferedReader rd = null;
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(urlstr);
			urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setRequestMethod(method.toUpperCase());
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(TIME_OUT);
			   //设置http头 消息  
			urlConn.setRequestProperty("Content-Type","application/json; charset=UTF-8");  //设定 请求格式 json，也可以设定xml格式的  
			if (StringUtils.isNotEmpty(postdata)) {
				// 组装请求包体json
				urlConn.getOutputStream().write(postdata.getBytes("utf-8"));
				urlConn.getOutputStream().flush();
			}
			in = urlConn.getInputStream();
			rd = new BufferedReader(new InputStreamReader(in, "utf-8"));

			String tmps = null;
			while ((tmps = rd.readLine()) != null) {
				tempStr.append(tmps.trim());
			}
		} catch (MalformedURLException e) {
			LOG.error("MalformedURLException:" + urlstr, e);
			return ERROR;
		} catch (IOException e) {
			LOG.error("IOException:" + urlstr, e);
			return ERROR;
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					LOG.error("rd.close()的IOException:" + urlstr, e);
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConn != null)
				urlConn.disconnect();
		}
		return tempStr.toString();
	}

	/**
	 * 发送请求数据到服务端接口地址
	 *
	 * @param urlstr
	 * @param postdata
	 * @return
	 */
	public static String sendRequest(String urlstr, String postdata) {
		return sendRequest(urlstr, postdata, "post");
	}
}
