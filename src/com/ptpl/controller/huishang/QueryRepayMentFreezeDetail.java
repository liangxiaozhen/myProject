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
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 还款冻结 查询
 * @author admin
 *
 */
public class QueryRepayMentFreezeDetail {

	public static String doParams(String CARDNBR,String oldBatchNo){
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		String SEQNO   = StringUtil.getNo();//交易流水号

		LinkedHashMap<String,String> reqMap = new LinkedHashMap<>();
		reqMap.put("TRXCODE", "5822");//交易代码	TRXCODE
		reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE
		reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
		reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
		reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
		reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
		reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
		reqMap.put("SOURCE", HSignUtil.SOURCE);//ESB内部渠道	SOURCE
		reqMap.put("RETCODE", "");//应答码	RETCODE
		reqMap.put("RETMSG", "");//应答码描述	RETMSG
		reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED

		reqMap.put("CARDNBR", CARDNBR);//借款人电子账号
		reqMap.put("PINFLAG", "0");//是否检查密码标志0:不检查密码 1:检查取现密码 2:检查查询密码
		reqMap.put("PIN", "");//密码
		reqMap.put("SERI_NO", oldBatchNo);//申请流水号  原还款冻结交易的流水号
		if(BaseController.logger.isDebugEnabled()){
			BaseController.logger.debug("QueryRepayMentFreezeDetail.java单笔还款冻结查询！HttpCliet提交查询,原还款冻结交易的流水号是:"+oldBatchNo);
		}
		String result = "";
		try {
			result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
  
	public static Map<String,Object> callBack(String result){
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<String> userRegList = new ArrayList<>();
		userRegList.add("CARDNBR");//电子账号	CARDNBR	A	19	M	
		userRegList.add("NAME");//姓名	NAME	A	60	C	
		userRegList.add("FUISSUER");//产品发行方	FUISSUER	A	4	C	同请求
		userRegList.add("PRMNO");//标的编号	PRODUCT	A	6	C	同请求
		userRegList.add("FRZAMT");//冻结金额	AMOUNT	N	13,2	C	同请求
		userRegList.add("STATE");//0: 正常；1：已撤销； 
 		String resresult = HSignUtil.getDecryptRSAByte(result);//解密
		Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resresult);//数据转化Map
		List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5816接口返回报文参数和1到11域拼接一起
		int listLength = resColumnList.size();
 		StringBuffer responseMapMerged = new StringBuffer();
		for (int i = 0; i < listLength; i++) {
			Object columnvalue = responseMap.get(resColumnList.get(i));
			if(columnvalue != null){
				responseMapMerged.append(responseMap.get(resColumnList.get(i)));
			}
		}
 
		//验签
		String responseSign = (String) responseMap.get("SIGN");
		boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
		if(!verifyResult){//验证签名失败
			if(BaseController.logger.isDebugEnabled()){
				BaseController.logger.debug("QueryRepayMentFreezeDetail.java单笔还款冻结查询，验证签名失败！");
			}
			hashMap.put(RepayMent_Constant.MSG, "单笔还款冻结查询，验证签名失败！");
			hashMap.put(RepayMent_Constant.FALG, false);
			hashMap.put(RepayMent_Constant.RESULT, "verifyResultFlag");
			return hashMap;
		}
		 
		if (!"00000000".equals(responseMap.get("RETCODE"))){//这里进行给用户提示
			if(BaseController.logger.isDebugEnabled()){
				BaseController.logger.debug("QueryRepayMentFreezeDetail.java单笔还款冻结查询失败,返回码是："+responseMap.get("RETCODE"));
			}
			System.out.println("================"+responseMap.get("RETCODE"));
			hashMap.put(RepayMent_Constant.MSG, "单笔还款冻结查询失败，返回码是:"+responseMap.get("RETCODE"));
			hashMap.put(RepayMent_Constant.FALG, false);
			hashMap.put(RepayMent_Constant.RESULT, "retcodeFlag");
			return hashMap;
		}
	 
		JSONObject json 	= JSONObject.fromObject(responseMap);
		String CARDNBR 		= json.getString("CARDNBR");//电子账号	CARDNBR	A	19	M	
		String NAME 		= json.getString("NAME");//姓名	NAME	A	60	C	
		String FUISSUER 	= json.getString("FUISSUER");//产品发行方	FUISSUER	A	4	C	同请求
		String PRMNO 		= json.getString("PRMNO");//标的编号	PRODUCT	A	6	C	同请求
		String FRZAMT 		= json.getString("FRZAMT");//冻结金额	AMOUNT	N	13,2	C	同请求
		String STATE 		= json.getString("STATE");//0: 正常；1：已撤销
		System.out.println("======CARDNBR========="+CARDNBR);
		System.out.println("======NAME========="+NAME);
		System.out.println("======PRMNO========="+PRMNO);
		System.out.println("======FUISSUER========="+FUISSUER);
		System.out.println("======FRZAMT========="+FRZAMT);
		System.out.println("======STATE========="+STATE);
		hashMap.put(RepayMent_Constant.FALG, true);
		hashMap.put(RepayMent_Constant.MSG, "单笔还款冻结查询成功");
		hashMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
		hashMap.put("CARDNBR", CARDNBR);
		hashMap.put("NAME", NAME);
		hashMap.put("PRMNO", PRMNO);
		hashMap.put("FUISSUER", FUISSUER);
		hashMap.put("FRZAMT", FRZAMT);
		hashMap.put("STATE", STATE);
 		return hashMap;
   	}
	
	public static void main(String[] args) {
		String rest = doParams("9930040290000600013", "00020020170630175624379615");
		Map<String,Object> hashmap = callBack(rest);
		System.out.println(JSON.toJSONString(hashmap));
	}
	
	
	
}
