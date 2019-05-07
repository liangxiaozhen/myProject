/**
 * 说明： 配置信息仅供Demo使用，开发请根据实际情况配置
 * 
 *汇付天下有限公司
 *
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.moneymoremore.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

 
 
public class SignUtils implements Serializable {
    private static final long  serialVersionUID        = 3640874934537168392L;

     /**
     * 
    * @Title: doPost 
    * @Description: TODO(乾多多接口 后台提交) 
    * @param @param params 请求参数
    * @param @return httpUrl 请求路径
    * @param @throws ClientProtocolException
    * @param @throws IOException  参数说明 
    * @return String    返回类型 
    * @author cjm
    * @throws
     */
    // 如果关注性能问题可以考虑使用HttpClientConnectionManager
   	public static String doPost(Map<String, String> params,String httpUrl) throws ClientProtocolException, IOException{
   		String result = null;
   		HttpPost httpPost = new HttpPost(httpUrl);
   		CloseableHttpClient httpclient = HttpClients.createDefault();
   		if (params != null)
   		{
   			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
   			httpPost.setEntity(formEntiry);
   			CloseableHttpResponse response = httpclient.execute(httpPost);
   			try
   			{
   				HttpEntity entity = response.getEntity();
   				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   					result = EntityUtils.toString(entity, "UTF-8");
   				    EntityUtils.consume(entity);
   			} finally
   			{
   				response.close();
   			}
   		}
   		return result;
   	}
}
