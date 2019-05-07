package com.ptpl.controller.huifu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
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
import com.ptpl.web.util.MD5;

/**
 * 汇付天下-标的投标信息查询
 * @author zhenglm
 */
public class QueryBidInfoDetailController {

	//如果关注性能问题可以考虑使用HttpClientConnectionManager
	public String doPost(Map<String, String> params) throws ClientProtocolException, IOException
	{
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

	/**
	 * 模拟标的投标信息查询接口请求参数
	 * @throws Exception 
	 * 
	 */
	private Map<String, String> getParams() throws Exception {
		/* 版本号 */
		String version = "10";
		/* 消息类型 */
		String cmdId = "QueryBidInfoDetail";
		/* 商户客户号 */
		String merCustId = "6000060004166478";
		String proId = "GJ20161125458244";
		String PageNum = "1";
		String PageSize = "50";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("ProId", proId);
		params.put("PageNum", PageNum);
		params.put("PageSize", PageSize);

		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version)).append(StringUtils.trimToEmpty(cmdId))
		.append(StringUtils.trimToEmpty(merCustId)).append(StringUtils.trimToEmpty(proId))
		.append(StringUtils.trimToEmpty(PageNum)).append(StringUtils.trimToEmpty(PageSize));
		String plainStr = buffer.toString();
		System.out.println(plainStr);
		MD5 m = new MD5();
		String Str = m.getMD5Info(plainStr).toLowerCase();
		params.put("ChkValue", SignUtils.encryptByRSA(Str));

		return params;
	}

	public static Double queryBidInfo() throws Exception{
		QueryBidInfoDetailController ts = new QueryBidInfoDetailController();
		Map<String, String> params = ts.getParams();
		// 1. result 为同步返回的结果(jason格式)，可转换成对应的实体对象
		// 2. 注意：此返回结果中没有使用encode，所以不需要做decode处理
		// 3. 验证签名的方式与异步应答的验签相同，可参照异步应答接收的处理方式
		String result = ts.doPost(params);
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject
		JSONArray BidLogDtoList = json.getJSONArray("BidLogDtoList");
		System.out.println("投标查询结果==================================="+BidLogDtoList);

		Iterator<Object> it = BidLogDtoList.iterator(); 
		Double TransAmt = (double) 0;
		while(it.hasNext()){
			JSONObject json2 = (JSONObject) it.next();
			if(json2.getString("TransStat").equals("N")){
				String transAmt = json2.getString("TransAmt");
				TransAmt += new BigDecimal(transAmt).doubleValue();
			}
		}
		System.out.println("总交易金额==================================="+TransAmt);
		return TransAmt;
	}

	/**
	 * 模拟接口调用 - 标的投标信息查询
	 * 根据接口文档使用post方式
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		QueryBidInfoDetailController ts = new QueryBidInfoDetailController();
		Map<String, String> params = ts.getParams();
		// 1. result 为同步返回的结果(jason格式)，可转换成对应的实体对象
		// 2. 注意：此返回结果中没有使用encode，所以不需要做decode处理
		// 3. 验证签名的方式与异步应答的验签相同，可参照异步应答接收的处理方式
		String result = ts.doPost(params);
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject
		JSONArray BidLogDtoList = json.getJSONArray("BidLogDtoList");
		System.out.println("投标查询结果==================================="+BidLogDtoList);

		Iterator<Object> it = BidLogDtoList.iterator(); 
		Double TransAmt = (double) 0;
		while(it.hasNext()){
			JSONObject json2 = (JSONObject) it.next();
			if(json2.getString("TransStat").equals("C")){
				String transAmt = json2.getString("TransAmt");
				String OrdId = json2.getString("OrdId");
				System.out.println("投标成功订单号"+OrdId);
				String OrdDate = json2.getString("OrdDate");
				System.out.println("投标成功订单日期"+OrdDate);
				System.out.println("投标成功订单金额"+transAmt);
				System.out.println("================分割线===================");
				System.out.println("\n");
				TransAmt += new BigDecimal(transAmt).doubleValue();
			}
		}
		System.out.println("总交易金额==================================="+TransAmt);
	}
}
