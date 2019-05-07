package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishang.util.HSignUtil;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
import com.sun.org.glassfish.external.amx.MBeanListener.Callback;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: PhoneUpdateController 
* @Description: TODO(徽商手机号码修改) 
* @author cjm 
* @date 2017年4月7日 上午11:03:16 
*
 */
@Controller
@RequestMapping("/huishang/phoneUpdate")
public class PhoneUpdateController {

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	
	@RequestMapping("/test")
	public String doPhoneUpdate(HttpServletRequest request,HttpServletResponse response){
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		String SEQNO   = StringUtil.getNo();//交易流水号
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		ubai.setLoginname("徽商测试用户8");
		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
		
		String CARDNBR  = userFsAccountInfo.getUsrcustid();//12	电子账号	CARDNBR	A	19	M	
		String PINFLAG  = "0";//13	使用密码标志	PINFLAG	A	1	M	"0：不使用密码 1：使用消费密码2：使用查询密码"
		String PIN 	    = "";//14	密码	PIN	A	8	C	ANSI98格式，详见“信息安全处理”PIN加密部分
		String OPTION   = "1";//15	选项	OPTION	N	1	M	"0：查询 1：修改"
		String MOPHONE  = "18963656295";//16	手机号	MOPHONE	A	12	C	
		String REMARK   = userBaseAccountInfo.getId().toString();//17	备注	REMARK	A	200	C	第三方平台保留使用，原样返回
		String RESERVED = "";//18	保留域	RESERVED	A	200	C	
		
		
		  LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
	      reqMap.put("TRXCODE", "5841");//交易代码	TRXCODE
	      reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE
	
	      reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
	      reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
	      reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
	      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
	      reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
	      reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
	      reqMap.put("RETCODE", "");//应答码	RETCODE
	      reqMap.put("RETMSG", "");//应答码描述	RETMSG
	      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
		
	      reqMap.put("CARDNBR", CARDNBR);//电子账号	CARDNBR	A	19	M	
	      reqMap.put("PINFLAG", PINFLAG);//使用密码标志	PINFLAG	A	1	M	"0：不使用密码 1：使用消费密码2：使用查询密码"
	      reqMap.put("PIN", PIN);//密码	PIN	A	8	C	ANSI98格式，详见“信息安全处理”PIN加密部分
	      reqMap.put("OPTION", OPTION);//选项	OPTION	N	1	M	"0：查询 1：修改"
	      reqMap.put("MOPHONE", MOPHONE);//手机号	MOPHONE	A	12	C
	      reqMap.put("REMARK", REMARK);//备注	REMARK	A	200	C	第三方平台保留使用，原样返回
//	      reqMap.put("RESERVED", "");//保留域	RESERVED	A	200	C
	       try {
			String result = HSignUtil.HttpClientTask(reqMap, "");
			Callback(result);
			
			
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return null;
	}
	
	
	public String Callback(String respoResult){
		
		System.out.println(respoResult+"==========");
		  Map<String,String> hashMap = new HashMap<>();
  		  List<String> userRegList = new ArrayList<>();
 
		  
		  userRegList.add("CARDNBR"); //12	电子账号	CARDNBR	A	19	M	同请求
		  userRegList.add("NAME");//13	持卡人姓名	NAME	A	60	C	
		  userRegList.add("MOPHONE"); //14	手机号	MOPHONE	A	12	C	
		  userRegList.add("RACECODE"); // 15	证件类型	RACECODE	A	2	C	
		  userRegList.add("CUSTRREF"); // 16	证件号	CUSTRREF	A	18	C	
		  userRegList.add("REMARK"); // 17	备注	REMARK	A	200	C	同请求
//		  userRegList.add("RESERVED"); // 18	保留域	RESERVED	A	200	C	

		  
 		  String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
	 	   
	     
	      int listLength = resColumnList.size();
	      if(responseMap.get("RETCODE") == null)
	      {
//	    	  hashMap.put("result", "network");// "因网络响应不及时,处理失败";
//	    	  return hashMap;
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
//  	    	
//  	    	hashMap.put("result", "network");// "因网络响应不及时,处理失败";
//  	    	return hashMap;
  	    	System.out.println("==============延签失败");
  	    	return null;
  	    }
  	  
	 
		    JSONObject json 	= JSONObject.fromObject(responseMap);
 		    String CARDNBR 		=   json.getString("CARDNBR"); //12	电子账号	CARDNBR	A	19	M	同请求
		    String NAME 		=  json.getString("NAME");//13	持卡人姓名	NAME	A	60	C	
		    String MOPHONE 		=  json.getString("MOPHONE"); //14	手机号	MOPHONE	A	12	C	
		    String RACECODE 	=  json.getString("RACECODE"); // 15	证件类型	RACECODE	A	2	C	
		    String COINSTCODE 	=  json.getString("CUSTRREF"); // 16	证件号	CUSTRREF	A	18	C	
		    String REMARK 		=  json.getString("REMARK"); // 17	备注	REMARK	A	200	C	同请求
//			String RESERVED 	=  json.getString("RESERVED"); // 18	保留域	RESERVED	A	200	C	
		    System.out.println("=====CARDNBR======="+CARDNBR);
		    System.out.println("=====NAME======="+NAME);
		    System.out.println("=====MOPHONE======="+MOPHONE);
		    System.out.println("=====RACECODE======="+RACECODE);
		    System.out.println("=====COINSTCODE======="+COINSTCODE);
		    System.out.println("=====REMARK======="+REMARK);
		    UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(new BigDecimal(REMARK));
		    
		    userFsAccountInfo.setFsmobile(MOPHONE);
		    userFsAccountInfoServiceI.updateById(userFsAccountInfo);
		return null;
		
		
	}
  }
