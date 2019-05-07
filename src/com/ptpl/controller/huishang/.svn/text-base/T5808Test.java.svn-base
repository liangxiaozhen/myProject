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

public class T5808Test {

	public void  test() throws UnsupportedEncodingException, Exception{
	  Date date = new Date();
	  String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
	  String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
	  String SEQNO   = StringUtil.getNo();//交易流水号
	
  	
	  LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
      reqMap.put("TRXCODE", "5808");//交易代码	TRXCODE
      reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE

      reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
      reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
      reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
      reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
      reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
      reqMap.put("RETCODE", "");//应答码	RETCODE
      reqMap.put("RETMSG", "");//应答码描述	RETMSG
      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
  
      
      reqMap.put("CARDNBR", "9930040050240500013"); 
      reqMap.put("PINFLAG", "0"); 
      reqMap.put("PIN", ""); 
      reqMap.put("SERI_NO", "20170424170158588412"); 
      
      String respoResult = HSignUtil.HttpClientTask(reqMap, "");
      
 	  List<String> userRegList = new ArrayList<>();
	  userRegList.add("CARDNBR"); 
	  userRegList.add("NAME"); 
	  userRegList.add("FUISSUER"); 
	  userRegList.add("PRMNO"); 
	  userRegList.add("FORINCOME"); 
	  userRegList.add("BUYDATE"); 
	  userRegList.add("STATE"); 
	  userRegList.add("AUTHCODE"); 
	  userRegList.add("BOSAMT"); 
 	  
//	  userRegList.add("RESERVED");// 保留域 RESERVED
	  
	  String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
	  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);//数据转化Map
	  
 	  List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
 	   
     
      int listLength = resColumnList.size();
      if(responseMap.get("RETCODE") == null)
      {
    	   
      }
        
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
	  if(!verifyResult){
	    	
	    	 System.out.println("==============验证签名失败");
	  }
	  
	  
	  
	  String CARDNBR = (String) responseMap.get("CARDNBR");
	  String NAME = (String) responseMap.get("NAME");
	  String FUISSUER = (String) responseMap.get("FUISSUER");
	  String PRMNO = (String) responseMap.get("PRMNO");
	  String FORINCOME = (String) responseMap.get("FORINCOME");
	  String BUYDATE = (String) responseMap.get("BUYDATE");
	  String STATE = (String) responseMap.get("STATE");
	  String AUTHCODE = (String) responseMap.get("AUTHCODE");
	  String BOSAMT = (String) responseMap.get("BOSAMT");
	  System.out.println("======CARDNBR=============="+CARDNBR);
	  System.out.println("======NAME=============="+NAME);
	  System.out.println("======FUISSUER=============="+FUISSUER);
	  System.out.println("======PRMNO=============="+PRMNO);
	  System.out.println("======FORINCOME=============="+FORINCOME);
	  System.out.println("======BUYDATE=============="+BUYDATE);
	  System.out.println("======STATE=============="+STATE);
	  System.out.println("======AUTHCODE=============="+AUTHCODE);
	  System.out.println("======BOSAMT=============="+BOSAMT);
	 
	  
  }
	
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		T5808Test t5808Test = new T5808Test();
		t5808Test.test();
	}
}
