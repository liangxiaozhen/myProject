package com.ptpl.controller.moneymoremore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.moneymoremore.util.HttpClientUtil;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;

/**
 * 乾多多余额查询接口-查询绑定快捷支付银行卡
 * @ClassName: MMMQueryBankCardController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenglm
 * @date 2017年2月24日 下午4:51:26
 */
public class MMMQueryBankCardController {
	
	/**
	 * 组装乾多多查询绑定快捷支付银行卡参数
	 * @Title: getParams
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return Map<String,String>    返回类型
	 */
	private static Map<String, String> getParams(String pId) {
		
		String PlatformId = pId; // 查询账户的乾多多标识
		Integer PlatformType = 2; // 平台乾多多账户类型（当查询平台账户时必填）-1.托管账户，2.自有账户
		Integer QueryType = 2; // 查询类型-空.查询余额，1.查询账户信息，2.查询银行卡信息
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("PlatformId", PlatformId);
		params.put("PlatformType", PlatformType.toString());
		params.put("QueryType", QueryType.toString());
		params.put("PlatformMoneymoremore", PlatformMoneymoremore);

		/*组装加签字符串原文
		注意加签字符串的组装顺序参 请照接口文档*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(PlatformId))
				.append(PlatformType)
				.append(QueryType)
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
		
		String plainStr = buffer.toString();
 		String privateResult = "";
 		
 		RsaHelper rsa = RsaHelper.getInstance();
 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		params.put("SignInfo", privateResult);

		return params;
	}
	
	/**
	 * 发送请求查询乾多多绑定快捷支付银行卡信息
	 * @Title: getResult
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return void    返回类型
	 */
	public static List<String> getResult(String pId) {
		Map<String, String> params = getParams(pId);
		List<String> list = new ArrayList<String>();
		// 发送请求获取返回结果
		String[] result = HttpClientUtil.doPostQueryCmd("http://test.moneymoremore.com:88/main/loan/balancequery.action", params);
		String[] arr1 = result[1].split(","); // 获取快捷支付卡信息（绑定多张快捷支付银行卡之间用英文逗号（,）分隔）
		for(String arr2 : arr1){
			String[] cardNos = arr2.split("\\|"); // 获取快捷支付卡号
	 		RsaHelper rsa = RsaHelper.getInstance();
			String cardNo = rsa.decryptData(cardNos[0], RsaHelper.privateString); // RSA解密卡号
			System.out.println("卡号="+cardNo);
			list.add(cardNo);
		}
		return list;
	}
}
