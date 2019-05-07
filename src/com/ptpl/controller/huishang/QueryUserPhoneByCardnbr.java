package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 根据电子账号查询用户手机号码
 * @author admin
 *
 */
public class QueryUserPhoneByCardnbr {

	public static String doParams(String CARDNBR) {
 		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		// 请求报文头
		reqMap.put("TRXCODE", "5841"); // 交易代码TRXCODE——4位交易代码
		reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——YYYYMMDD
		String TRXTIME = StringUtil.formatDate(date, "HHmmss"); // 交易时间	TRXTIME——hhmmss
		reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——YYYYMMDD
		reqMap.put("TRXTIME", TRXTIME); // 交易时间	TRXTIME——hhmmss
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
		reqMap.put("COINSTCHANNEL","000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
		reqMap.put("SEQNO",StringUtil.getNo()); // 交易流水号SEQNO——8 ~ 20 位数字流水号
		reqMap.put("SOURCE", HSignUtil.SOURCE); // ESB内部渠道SOURCE——A0
		reqMap.put("RETCODE", ""); // 应答码RETCODE——填空
		reqMap.put("RETMSG", ""); // 应答码描述RETMSG——填空
		reqMap.put("HEADRESERVED", ""); // 报文头保留域HEADRESERVED——可选（将baseid放入报文头保留域）
		
		// 请求报文
 		reqMap.put("CARDNBR", CARDNBR); // 电子账号
 		reqMap.put("PINFLAG", "0"); // 使用密码标志  0：不使用密码 1：使用消费密码 2：使用查询密码
 		reqMap.put("PIN", ""); // 密码
 		reqMap.put("OPTION", "0"); // 0：查询 1：修改
  		reqMap.put("MOPHONE", ""); // 手机号
 		String result = null;
		try {
			result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Map<String,Object> callBack(String respoResult){
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("falg", false);
		hashMap.put("Msg", "");
 		String result = HSignUtil.getDecryptRSAByte(respoResult); // 接受数据后进行解密
		JSONObject jsonObject = JSONObject.fromObject(result);
 	    Map responseMap = HSignUtil.parseJSON2Map(result);
 		List<String> resColum = new ArrayList<>();
		resColum.add("CARDNBR");
		resColum.add("NAME");
		resColum.add("MOPHONE");
		resColum.add("RACECODE");
		resColum.add("CUSTRREF");
		resColum.add("REMARK");
		resColum.add("RESERVED");
  		List<String> listCount = HSignUtil.getResponseHead(resColum);
 		StringBuffer buffer = new StringBuffer();
 		for(int i = 0;i < listCount.size() ;i++){
 			Object columnvalue = responseMap.get(listCount.get(i));
 			if(columnvalue != null){
 				buffer.append(columnvalue);
 			}
 		}
   
		String str = buffer.toString();
		String responseSign = jsonObject.getString("SIGN");
 		boolean verifyResult = HSignUtil.getVerify(str, responseSign);
 		if (!verifyResult){//验证签名失败
 			System.out.println("徽商开户掉单处理！根据用户电子账号查询开户手机号码验证签名失败！");
 			return hashMap;
 		}  
  		
		if (!"00000000".equals(jsonObject.getString("RETCODE"))){
			hashMap.put("falg", false);
			hashMap.put("Msg", "返回码是："+jsonObject.getString("RETCODE"));
			return hashMap;
  		}
		
		hashMap.put("falg", true);
		hashMap.put("Msg", "查询手机号码成功！");
		hashMap.put("list", jsonObject.getString("MOPHONE"));
		return hashMap;
	}
	
	public static void main(String[] args) {
		String res = doParams("9930040290000250025");
		Map<String,Object> map = callBack(res);
		System.out.println(JSON.toJSON(map));
		//18963656295
		//17065523665
	}
}
