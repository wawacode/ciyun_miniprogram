package com.centrin.ciyun.common.util.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;



/**
 * @名称: ClientSSL.java
 * @包名： com.centrin.common.http
 * @描述: TODO
 * @作者： lwh
 * @日期：2013-8-29 下午05:09:43
 */
public class TrustSSL {

	public static class TrustAnyTrustManager implements X509TrustManager {
		 
		public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
			
		}
		
		 
		public void checkServerTrusted(X509Certificate[] chain, String authType)
		 	throws CertificateException {
			
		
		}
		 
		public X509Certificate[] getAcceptedIssuers(){
			return new X509Certificate[] {};
		}
	}
		 
	public static class TrustAnyHostnameVerifier implements HostnameVerifier {
		
		 public boolean verify(String hostname, SSLSession session) {
			 return true;
		 }
	}

}
