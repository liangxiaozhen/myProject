/**
 * 说明： 配置信息仅供Demo使用，开发请根据实际情况配置
 * 
 *汇付天下有限公司
 *
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.util;

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

import com.huifu.util.httpClient.HttpClientHandler;

import chinapnr.SecureLink;

public class SignUtils implements Serializable {
    private static final long  serialVersionUID        = 3640874934537168392L;

    /** MD5签名类型 **/
    public static final String SIGN_TYPE_MD5           = "M";

    /** RSA签名类型 **/
    public static final String SIGN_TYPE_RSA           = "R";

    /** RSA验证签名成功结果 **/
    public static final int    RAS_VERIFY_SIGN_SUCCESS = 0;

    /** 商户客户号 **/
    public static final String RECV_MER_ID             = "531137";

    /** 商户公钥文件地址 **/
    public static final String MER_PUB_KEY_PATH        = "D:/keys/PgPubk.key";

    /** 商户私钥文件地址 **/
    public static final String MER_PRI_KEY_PATH        = "D:/keys/MerPrK531137.key";
    
    /**
     * RSA方式加签
     * 
     * @param custId
     * @param forEncryptionStr
     * @param charset
     * @return
     * @throws Exception 
     */
    public static String encryptByRSA(String forEncryptionStr) throws Exception {
        SecureLink sl = new SecureLink();
        int result = sl.SignMsg(RECV_MER_ID, MER_PRI_KEY_PATH, forEncryptionStr);
        if (result < 0) {
            // 打印日志 
            throw new Exception();
        }
        return sl.getChkValue();
    }
	/**
	 * 解签
	 * @param forEncryptionStr
	 * @param chkValue
	 * @return
	 * @throws Exception
	 */
    public static boolean verifyByRSA(String forEncryptionStr, String chkValue)
                                                                                       throws Exception {
        try {
            int verifySignResult = new SecureLink().VeriSignMsg(MER_PUB_KEY_PATH, forEncryptionStr, chkValue);
            return verifySignResult == RAS_VERIFY_SIGN_SUCCESS;
        } catch (Exception e) {
            // 打印日志
            throw new Exception();
        }
    }
    /**
     * 
    * @Title: doPost 
    * @Description: TODO(汇付接口 后台提交) 
    * @param @param params 请求参数
    * @param @return
    * @param @throws ClientProtocolException
    * @param @throws IOException  参数说明 
    * @return String    返回类型 
    * @author cjm
    * @throws
     */
    // 如果关注性能问题可以考虑使用HttpClientConnectionManager
   	public static String doPost(Map<String, String> params) throws ClientProtocolException, IOException{
   		String result = null;
   		HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
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
