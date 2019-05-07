package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 根据身份证号码查询 电子账号
 * @author admin
 *
 */
public class QueryUserCardnbrByCarNo {

	
	public static String doParams(String IDNO) {
		String KEYTYPE = "02";
		if(IDNO.length() > 15){
			KEYTYPE = "01";
		}
		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		// 请求报文头
		reqMap.put("TRXCODE", "5800"); // 交易代码TRXCODE——4位交易代码
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
		reqMap.put("KEYTYPE",KEYTYPE); // 01-身份证18位 02-身份证15位
		reqMap.put("CUSTID", IDNO); // 证件号码
	 
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
 
	private static void callBack(String respoResult){
		String result = HSignUtil.getDecryptRSAByte(respoResult); // 接受数据后进行解密
		JSONObject jsonObject = JSONObject.fromObject(result);
		// 响应报文头
		String TRXCODE = jsonObject.getString("TRXCODE"); // 交易代码TRXCODE——与请求一致
		String BANKCODE = jsonObject.getString("BANKCODE"); // 银行代码BANKCODE——与请求一致
		String TRXDATE = jsonObject.getString("TRXDATE"); // 交易日期TRXDATE——与请求一致
		String TRXTIME = jsonObject.getString("TRXTIME"); // 交易时间TRXTIME——与请求一致
		String COINSTCODE = jsonObject.getString("COINSTCODE"); // 合作单位编号COINSTCODE——与请求一致
		String COINSTCHANNEL = jsonObject.getString("COINSTCHANNEL"); // 合作单位渠道COINSTCHANNEL——与请求一致
		String SEQNO = jsonObject.getString("SEQNO"); // 交易流水号SEQNO——与请求一致
		String SOURCE = jsonObject.getString("SOURCE"); // ESB内部渠道SOURCE——与请求一致
		String RETCODE = jsonObject.getString("RETCODE"); // 应答码RETCODE
		String RETMSG = jsonObject.getString("RETMSG"); // 应答码描述RETMSG
		String HEADRESERVED = jsonObject.getString("HEADRESERVED"); //报文头保留域HEADRESERVED
		System.out.println("========交易代码TRXCODE================"+TRXCODE);
		System.out.println("========银行代码BANKCODE================"+BANKCODE);
		System.out.println("========交易日期TRXDATE================"+TRXDATE);
		System.out.println("========交易时间TTRXTIME================"+TRXTIME);
		System.out.println("========合作单位编号COINSTCODE================"+COINSTCODE);
		System.out.println("========合作单位渠道COINSTCHANNEL================"+COINSTCHANNEL);
		System.out.println("========交易流水号SEQNO================"+SEQNO);
		System.out.println("========ESB内部渠道SOURCE================"+SOURCE);
		System.out.println("========应答码RETCODE================"+RETCODE);
		System.out.println("========应答码描述RETMSG================"+RETMSG);
		System.out.println("========HEADRESERVED================"+HEADRESERVED);
		// 响应报文
		String KEYTYPE = jsonObject.getString("KEYTYPE");// 证件类型
		String CUSTID = jsonObject.getString("CUSTID"); // 证件号码
		String NAME = jsonObject.getString("NAME"); // 持卡人姓名
		String COUNT = jsonObject.getString("COUNT"); // 持卡人姓名
		String RTN_IND = jsonObject.getString("RTN_IND"); // 持卡人姓名
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(TRXCODE));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(SEQNO));
		buffer.append(StringUtils.trimToEmpty(SOURCE));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		buffer.append(StringUtils.trimToEmpty(HEADRESERVED));
		
		buffer.append(StringUtils.trimToEmpty(KEYTYPE));
		buffer.append(StringUtils.trimToEmpty(CUSTID));
		buffer.append(StringUtils.trimToEmpty(NAME));
		buffer.append(StringUtils.trimToEmpty(COUNT));
		buffer.append(StringUtils.trimToEmpty(RTN_IND));
	 
 		String SUBPACKS = jsonObject.getString("SUBPACKS");
 	    if(SUBPACKS != null){
 	    	JSONArray arr = JSONArray.fromObject(SUBPACKS);
 	    	Iterator<?> it = arr.iterator(); 
	 	   	String CARDNBR = "";//电子账号
	 		String OPENDATE = "";//开户日期
	 		String CD_STAT = "";//电子账户状态
	 		String FRZ_STAT = "";//冻结状态
	 		String PINLOS_CD = "";//密码挂失状态
	 		String PRODUCT = "";//产品编号
	 		String PRO_DESC = "";//产品名称
	 		String USNO = "";//第三方平台用户编号
  	    	while(it.hasNext()){
 	    		JSONObject json = (JSONObject) it.next();
 	    		 CARDNBR = json.getString("CARDNBR");//电子账号
 	    		 OPENDATE = json.getString("OPENDATE");//开户日期
 	    		 CD_STAT = json.getString("CD_STAT");//电子账户状态
 	    		 FRZ_STAT = json.getString("FRZ_STAT");//冻结状态
 	    		 PINLOS_CD = json.getString("PINLOS_CD");//密码挂失状态
 	    		 PRODUCT = json.getString("PRODUCT");//产品编号
 	    		 PRO_DESC = json.getString("PRO_DESC");//产品名称
 	    		 USNO = json.getString("USNO");//第三方平台用户编号
 	    		buffer.append(StringUtils.trimToEmpty(CARDNBR));
 	    		buffer.append(StringUtils.trimToEmpty(OPENDATE));
 	    		buffer.append(StringUtils.trimToEmpty(CD_STAT));
 	    		buffer.append(StringUtils.trimToEmpty(FRZ_STAT));
 	    		buffer.append(StringUtils.trimToEmpty(PINLOS_CD));
 	    		buffer.append(StringUtils.trimToEmpty(PRODUCT));
 	    		buffer.append(StringUtils.trimToEmpty(PRO_DESC));
 	    		buffer.append(StringUtils.trimToEmpty(USNO));
 	    	 
 	    		
 	    		System.out.println("==========CARDNBR==========="+CARDNBR);
 	    		System.out.println("=========OPENDATE============"+OPENDATE);
 	    		System.out.println("========CD_STAT============="+CD_STAT);
 	    		System.out.println("========FRZ_STAT============="+FRZ_STAT);
 	    		System.out.println("========PINLOS_CD============="+PINLOS_CD);
 	    		System.out.println("=========PRODUCT============"+PRODUCT);
 	    		System.out.println("=======PRO_DESC=============="+PRO_DESC);
 	    		System.out.println("========USNO============="+USNO);
 	    	}
 	    }
  
		String str = buffer.toString();
		String responseSign = jsonObject.getString("SIGN");
 		boolean verifyResult = HSignUtil.getVerify(str, responseSign);

		if (!verifyResult){
			System.out.println("验证签名失败...");
		} else {
			System.out.println("验证签名成功");
		}
		if ("00000000".equals(jsonObject.getString("RETCODE"))){
			System.out.println("操作成功 00000000");
			 
 		} else {
			System.out.println("操作失败,错误代码："+jsonObject.getString("RETCODE"));
		}
	}
	
	public static Map<String,Object> callBackDeal(String respoResult){
 		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("falg", false);
		hashMap.put("Msg", "");
		String result = HSignUtil.getDecryptRSAByte(respoResult); // 接受数据后进行解密
		JSONObject jsonObject = JSONObject.fromObject(result);
 	    Map responseMap = HSignUtil.parseJSON2Map(result);
 		List<String> resColum = new ArrayList<>();
		resColum.add("KEYTYPE");
		resColum.add("CUSTID");
		resColum.add("NAME");
		resColum.add("COUNT");
		resColum.add("RTN_IND");
 		List<String> listCount = HSignUtil.getResponseHead(resColum);
 		StringBuffer buffer = new StringBuffer();
 		for(int i = 0;i < listCount.size() ;i++){
 			Object columnvalue = responseMap.get(listCount.get(i));
 			if(columnvalue != null){
 				buffer.append(columnvalue);
 			}
 		}
 		String CARDNBR = "";//电子账号
 		String OPENDATE = "";//开户日期
 		String CD_STAT  = "";//电子账户状态
 		String FRZ_STAT = "";//冻结状态
 		String PINLOS_CD = "";//密码挂失状态
 		String PRODUCT  = "";//产品编号
 		String PRO_DESC = "";//产品名称
 		String USNO     = "";//第三方平台用户编号
  	    if(jsonObject.getString("SUBPACKS") != null){
 	    	JSONArray arr = JSONArray.fromObject(jsonObject.getString("SUBPACKS"));
 	        int size = arr.size();
 	        for(int i = 0 ; i < size ; i++){
 	        	 JSONObject jsonObj = arr.getJSONObject(i);
 	        	 CARDNBR = jsonObj.getString("CARDNBR");//电子账号
	    		 OPENDATE = jsonObj.getString("OPENDATE");//开户日期
	    		 CD_STAT = jsonObj.getString("CD_STAT");//电子账户状态
	    		 FRZ_STAT = jsonObj.getString("FRZ_STAT");//冻结状态
	    		 PINLOS_CD = jsonObj.getString("PINLOS_CD");//密码挂失状态
	    		 PRODUCT = jsonObj.getString("PRODUCT");//产品编号
	    		 PRO_DESC = jsonObj.getString("PRO_DESC");//产品名称
	    		 USNO = jsonObj.getString("USNO");//第三方平台用户编号
 	        	 buffer.append(CARDNBR);
 	        	 buffer.append(OPENDATE);
 	        	 buffer.append(CD_STAT);
 	        	 buffer.append(FRZ_STAT);
 	        	 buffer.append(PINLOS_CD);
 	        	 buffer.append(PRODUCT);
 	        	 buffer.append(PRO_DESC);
 	        	 buffer.append(USNO);
  	        }
 	    }
  
		String str = buffer.toString();
		String responseSign = jsonObject.getString("SIGN");
 		boolean verifyResult = HSignUtil.getVerify(str, responseSign);
 		if (!verifyResult){
 			System.out.println("徽商开户掉单处理！ 根据用户身份证号码查询电子账号验证签名失败！");
 			return hashMap;
		}  
  		
		if (!"00000000".equals(jsonObject.getString("RETCODE"))){
			hashMap.put("falg", false);
			hashMap.put("Msg", "返回码是："+jsonObject.getString("RETCODE"));
			return hashMap;
  		}
		
 		String COINSTCODE = jsonObject.getString("COINSTCODE"); // 合作单位编号COINSTCODE——与请求一致
 		String SEQNO = jsonObject.getString("SEQNO"); // 交易流水号SEQNO——与请求一致
 		String NAME = jsonObject.getString("NAME"); // 持卡人姓名
 		 
		UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
		userFsAccountInfo.setUsrcustid(CARDNBR);  //用户商户号
		userFsAccountInfo.setMercustid(COINSTCODE);  //平台标识号
		userFsAccountInfo.setRespcode(jsonObject.getString("RETCODE"));  //应答返回码
		userFsAccountInfo.setTrxid(SEQNO);  //交易唯一标识
		userFsAccountInfo.setChannelidentifier("徽商银行");  //托管通道标识 如汇付天下 宝付
		userFsAccountInfo.setIsopenfsinfo((short)1);  //是否开通托管账户  1已开通 0未开通
		userFsAccountInfo.setOpeningtime(new Date());  //开通时间
		userFsAccountInfo.setRemark("徽商开户掉单 处理添加");  //备注
		userFsAccountInfo.setUsrname(BaseController.setEncrypt(NAME));  //用户真实名称
		userFsAccountInfo.setAccounttype((short)1);  //账户类型（1个人，2企业）
 		userFsAccountInfo.setAccPurpose((short)1); //账户用途（1普通，2红包，3手续费）
 		userFsAccountInfo.setBaseid(new BigDecimal(USNO));//用户baseId
		hashMap.put("falg", true);
		hashMap.put("Msg", "信息查询成功！");
		hashMap.put("list", userFsAccountInfo);
    	return hashMap;
	}
	
	public static void main(String[] args) {
		String res = doParams("410184198902070039");
		Map<String,Object> map = callBackDeal(res);
		System.out.println(JSON.toJSON(map));
	}
}
