package com.ptpl.controller.huifu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.HuifuParams;

public class QueryCardInfoController extends BaseController {
	
	/**
	 * 银行卡查询返回数据格式转换
	 * @param huifuparam
	 * @return
	 * @throws Exception
	 */
	public JSONArray doQueryCardInfo(HuifuParams huifuparam) throws Exception{
		huifuparam.setUsrCustId("6000060004190334");
		Map<String,String> params = getBankCardParams(huifuparam);
		String result = doBankCardPost(params);
		System.out.println("查询银行卡接口返回结果：=========="+result);
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject
		JSONArray json2 = json.getJSONArray("UsrCardInfolist"); // 转换UsrCardInfolist为JSONArray
		return json2;
	}
	
	/**
	 * 银行卡查询接口（拼接请求参数）
	 * @param huifuparam
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getBankCardParams(HuifuParams huifuparam) throws Exception {

		huifuparam.setCmdId("QueryCardInfo"); // 消息类型
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", huifuparam.getVersion());
		params.put("CmdId", huifuparam.getCmdId());
		params.put("MerCustId", huifuparam.getMerCustId());
		params.put("UsrCustId", huifuparam.getUsrCustId());
		params.put("CardId", huifuparam.getCardId());
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion())).append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId())).append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getCardId()));
		String plainStr = buffer.toString();
		String ChkValue ="";
	    try {
			  ChkValue = SignUtils.encryptByRSA(plainStr);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		params.put("ChkValue", ChkValue);
		return params;
	}
	
    /**
     * (银行卡查询 发送请求) 
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doBankCardPost(Map<String, String> params) throws ClientProtocolException, IOException {
        String result = null;
      //  List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        EntityBuilder builder = EntityBuilder.create();
        try {
            HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
      //      builder.setParameters(nvps);
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(response);
            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getReasonPhrase().equals("OK")
                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
		return result;     
    }

}
