package com.jachs.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

/***
 * @author zhanchaohan
 */
public class HttpClientUtill {
	private static final int CONNECTIONTIMEOUT = 15000;
	private static final int PARAMETERTIMEOUT = 60000;

	/**
	 * 发起POST请求
	 * 
	 * @param url       url
	 * @param paramJson 参数的json格式
	 */
	public String sendPost(String url, String paramJson) {
		if(url.startsWith("https")){  
	       //https请求
	       Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(),443);
	       Protocol.registerProtocol("https", myhttps);
	    }
		HttpClient httpClient = new HttpClient();
		
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTIONTIMEOUT);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, PARAMETERTIMEOUT);
		postMethod.addRequestHeader("Content-Type", "application/json");
		try {
			RequestEntity entity = new StringRequestEntity(paramJson, "application/json", "UTF-8");
			postMethod.setRequestEntity(entity);

			httpClient.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发起GET请求
	 *
	 * @param urlParam url请求，包含参数
	 */
	public String sendGet(String urlParam) {
		if(urlParam.startsWith("https")){  
         //https请求
         Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(),443);
         Protocol.registerProtocol("https", myhttps);
        }  
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTIONTIMEOUT);
		GetMethod getMethod = new GetMethod(urlParam);
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, PARAMETERTIMEOUT);
		getMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
		try {
			httpClient.executeMethod(getMethod);
			String result = new String(getMethod.getResponseBody(), "UTF-8");
			getMethod.releaseConnection();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
