package com.ptpl.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.httpClient.HttpClientHandler;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.moneymoremore.util.SignUtils;
import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBalanceQueryServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.Arith;
/**
 * 用户余额查询接口
 * @author admin
 *
 */
public class UserBalanceQueryServiceImpl implements UserBalanceQueryServiceI{
	
	/**账户信息*/
	@Autowired
	UserAccountMapper userAccountMapper;
	protected static DecimalFormat df1 = new DecimalFormat("##########0.00");
	

	public  String saveUserBalance(UserFsAccountInfo userFsAccountInfo) throws Exception{
		UserBalanceQueryServiceImpl ubq = new  UserBalanceQueryServiceImpl();
		 String result = ubq.getUserBlanaceParams(userFsAccountInfo);
		 System.out.println(result);
		 String data = result;
		 if(result!=null && result !=""){
			 String [] str = result.split("\\|");
			 System.out.println(str[0]+"*********"+str[1]+"---------"+str[2]);
			 Double AvlBal = Double.valueOf(str[1]); //可用余额
			 Double FrzBal = Double.valueOf(str[2]); //冻结余额
			 Double AcctBal = Arith.add(AvlBal, FrzBal);//账户余额(总金额)
			 UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(userFsAccountInfo.getBaseid());
			 if(!df1.format(userAccount.getAvlbalance()).equals(df1.format(AvlBal))){
				 data+=","+"AvlBalFalse";
			 }
			 if(!df1.format(userAccount.getFreezebalance()).equals(df1.format(FrzBal))){
				 data+=","+"FrzBalFalse";
			 }
			 if(!df1.format(userAccount.getBalance()).equals(df1.format(AcctBal))){
				 data+=","+"AcctBalFalse";
			 }
			 
		 }
		return data;
	}
	//更新本地数据库用户账户信息
	public void updateUserAccount(String result,UserFsAccountInfo userFsAccountInfo){
		 String [] str = result.split("\\|");
		 Double AvlBal = Double.valueOf(str[1]); //可用余额
		 Double FrzBal = Double.valueOf(str[2]); //冻结余额
		 Double AcctBal = Arith.add(AvlBal, FrzBal);//账户余额(总金额)
		 UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(userFsAccountInfo.getBaseid());
		 userAccount.setAvlbalance(AvlBal); //可用余额
		 userAccount.setBalance(AcctBal); //总金额
		 userAccount.setFreezebalance(FrzBal);//冻结余额
		 //假如有
		 userAccountMapper.updateUseraccount(userAccount);
		
	}
	


	/**
	 * 余额查询发送请求（拼接请求参数）
	 * @param huifuparam
	 * @return
	 * @throws Exception
	 */
	public  String getUserBlanaceParams(UserFsAccountInfo userFsAccountInfo){
		/**查询账户的乾多多标识:用户的乾多多标识*/
		String PlatformId = userFsAccountInfo.getMoneymoremoreid();
		/**平台乾多多账户类型 : 1为托管账户 2为自有账户  当查询平台账户时必填*/
		String PlatformType = "2";
		/**查询类型:空.查询余额1.查询账户信息2.查询银行卡信息*/	
		String QueryType = "";
		/**平台乾多多标识*/
		String PlatformMoneymoremore = userFsAccountInfo.getMercustid();
		
		 StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(PlatformId))
					.append(StringUtils.trimToEmpty(PlatformType))
					.append(StringUtils.trimToEmpty(QueryType))
					.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
					
		String plainStr = buffer.toString();
		System.out.println(plainStr);
		//私钥签名
		String privateResult = "";
		
		RsaHelper rsa = RsaHelper.getInstance();
		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		System.out.println(privateResult);
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("PlatformId", PlatformId);
		hashMap.put("PlatformType", PlatformType);
		hashMap.put("QueryType", QueryType);
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("SignInfo", privateResult);
		String resultStr = "";
		try {
			resultStr = SignUtils.doPost(hashMap, MMMParams.BALANCEQUERY);//HttpClient 提交
			System.out.println(resultStr+"返回参数");
		} catch (ClientProtocolException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}	
			return resultStr;
	}
	
	
	/**
	 * 查询用户余额
	 */
	/*public String getQueryBalanceBgByUsrCustId(String UsrCustId){
		String result =  "";
		try {
			Map<String,String> hashMap = getUserBlanaceParams(UsrCustId);
			result = doPost(hashMap);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return result;
	}*/
    /**
     * (余额查询返回参数) 
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
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

}
