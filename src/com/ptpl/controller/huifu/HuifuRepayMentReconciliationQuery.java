package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import org.springframework.util.Assert;

import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: HuifuRepayMentReconciliationQuery 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(还款对账) 
* @author cjm
* @date 2016年10月11日 下午3:33:57 
* @version V1.0
 */
public class HuifuRepayMentReconciliationQuery{

	/**
	 * 
	* @Title: doRepayMentReconciliationQuery 
	* @Description: TODO(汇付调用还款对账) 
	* @param @param beginDateStr 开始日期
	* @param @param endDate 结束日期
	* @param @param pageNum 页数
	* @param @param pageSize 条数
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	public static String doRepayMentReconciliationQuery(String beginDateStr,
			String endDate,String pageNum,String pageSize){
		Assert.hasText(beginDateStr, "beginDateStr 不能为null");
		Assert.hasText(endDate, "endDate 不能为null");
		Assert.hasText(pageNum, "pageNum 不能为null");
		Assert.hasText(pageSize, "pageSize 不能为null");
 		String result = "";
		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setVersion("10");
		huifuParams.setCmdId("Reconciliation");
 		huifuParams.setBeginDate(beginDateStr);
		huifuParams.setEndDate(endDate);
		huifuParams.setPageNum(pageNum);
		huifuParams.setPageSize(pageSize);
		huifuParams.setQueryTransType("REPAYMENT");
		/*Version+CmdId+MerCustId+BeginDate+
		EndDate+PageNum+PageSize+QueryTransType*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBeginDate()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getEndDate()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getPageNum()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getPageSize()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getQueryTransType()));
 		String str = buffer.toString();
 		String ChkValue = "";
 		try {
			ChkValue = SignUtils.encryptByRSA(str);
			if(StringUtil.isNotEmpty(ChkValue)){
				huifuParams.setChkValue(ChkValue);
 			}
		} catch (Exception e) {
 			e.printStackTrace();
		}
		
 		Map<String,String> hashMap = new HashMap<String,String>();
 		hashMap.put("Version", huifuParams.getVersion());
 		hashMap.put("CmdId", huifuParams.getCmdId());
 		hashMap.put("MerCustId", huifuParams.getMerCustId());
 		hashMap.put("BeginDate", huifuParams.getBeginDate());
 		hashMap.put("EndDate", huifuParams.getEndDate());
 		hashMap.put("PageNum", huifuParams.getPageNum());
 		hashMap.put("PageSize", huifuParams.getPageSize());
 		hashMap.put("QueryTransType", huifuParams.getQueryTransType());
 		hashMap.put("ChkValue", huifuParams.getChkValue());
 		hashMap.put("CharSet", huifuParams.getCharSet());
 		try {
			 result = SignUtils.doPost(hashMap);
 		} catch (ClientProtocolException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
 		return result;
 	}
	
	 
}
