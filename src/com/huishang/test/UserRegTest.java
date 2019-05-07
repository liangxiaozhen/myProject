package com.huishang.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.Base64Utils;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.huishang.util.UnicodeAndStringUtil;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;
 
/**
 * 
* @ClassName: UserRegTest 
* @Description: TODO(开户测试文件  可删除) 
* @author cjm 
* @date 2017年3月31日 上午11:24:53 
*
 */
public class UserRegTest {
  //直销银行电子账户开立
  public void test5810() throws Exception{
      LinkedHashMap reqMap = new LinkedHashMap();
      reqMap.put("TRXCODE", "5810");//交易代码	TRXCODE
      reqMap.put("BANKCODE", "30040000");//银行代码	BANKCODE
      Date date = new Date();
	 String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——YYYYMMDD
	 String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间	TRXTIME——hhmmss
      reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
      reqMap.put("TRXTIME",TRXTIME);//交易时间	TRXTIME
      reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
      reqMap.put("SEQNO",StringUtil.getNo());//交易流水号	SEQNO
      reqMap.put("SOURCE", HSignUtil.SOURCE);//ESB内部渠道	SOURCE
      reqMap.put("RETCODE", "");//应答码	RETCODE
      reqMap.put("RETMSG", "");//应答码描述	RETMSG
      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED

      reqMap.put("KEYTYPE", "01");//证件类型 KEYTYPE
       reqMap.put("IDNO", "331024197802046394");//证件号码 IDNO
       reqMap.put("SURNAME", "\\u5e9e\\u7076\\u58f0");//姓名 SURNAME

       reqMap.put("MOBILE", "13653269013");//手机号码 MOBILE
      
      reqMap.put("PRODUCT", HSignUtil.PRODUCT);//卡产品编号 PRODUCT
      reqMap.put("SMSFLAG", "1");//是否开通短信通知 SMSFLAG
      reqMap.put("RISK_YN", "1");//风险评估标志 RISK_YN
      reqMap.put("RISK_LEL", "");//风险评级等级 RISK_LEL
      reqMap.put("ACC_TYPE", "1");//账户类型 ACC_TYPE
      reqMap.put("FUCOMCODE", "");//基金公司代码 FUCOMCODE
      reqMap.put("ADNO", "recommandcode");//推荐人代码 ADNO
      reqMap.put("RECARD", "6212262201023557228");//绑定卡号 RECARD
      reqMap.put("GENDER", "F");//性别 GENDER F 女  M 男
//      reqMap.put("USNO", "1324324");//第三方平台用户编号 USNO
      // 客户IP USR_IP 
//      reqMap.put("RESERVED","6212262201023557228");//保留域 //保留域 RESERVED
      //
      ArrayList<String> resColumnList = new ArrayList();

      resColumnList.add("KEYTYPE");
      resColumnList.add("IDNO");
      resColumnList.add("NAME");
      resColumnList.add("CARDNBR");

      testCommon2(reqMap, resColumnList,null);
  }
 
  public void test3031() throws Exception{
	  
  }
  
   
  
  public void testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList,String code) throws  Exception{
  	testCommon2(reqMap, resColumnList,code,"");
  }

  //公共方法调用2
  public void testCommon2(LinkedHashMap<String,String> reqMap, ArrayList<String> resColumnList,String code,String version) throws  Exception
  {
//      Map map=new HashMap();
//      map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行号
//      map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
//      map.put("APIVERSION",version);//为报文版本号
//      
//      String sign1 = HSignUtil.getRASSign(reqMap);//加签名
//      System.out.println(sign1);
//       reqMap.put("SIGN", sign1);
//       String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
//       map.put("reqMap",data);//加密后的json参数
       
      String result1 = HSignUtil.HttpClientTask(reqMap,"");//发送HttpClient请求
       
      callBcak(result1);
//         callBcak2(result1);//徽商银行原来样例拼接
  }
  
  
 public void callBcak2(String result1){
	  
	  String result = HSignUtil.getDecryptRSAByte(result1);//解密
	  List<String> userRegList = new ArrayList<>();
	  userRegList.add("KEYTYPE");// 证件类型 KEYTYPE
	  userRegList.add("IDNO");//证件号码	IDNO
	  userRegList.add("NAME");// 姓名	NAME
	  userRegList.add("CARDNBR");// 电子账户	CARDNBR
	  userRegList.add("USNO");// 第三方平台用户编号	USNO
	  userRegList.add("RESERVED");// 第三方平台用户编号	USNO
	  
 	  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);
	  
 	  List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
 	  
       StringBuffer responseMapMerged = new StringBuffer();
     
      int listLength = resColumnList.size();
      if(responseMap.get("RETCODE") == null)
      {
          System.out.println("操作失败:"+responseMap.get("RETMSG"));
          throw new ArrayIndexOutOfBoundsException("请求参数非法");
          //InvocationTargetException
      }
      JSONObject json = JSONObject.fromObject(responseMap);
      //for循环次数
      int forLen = 0;
      //如果是5849交易
      if("5849".equals(responseMap.get(resColumnList.get(0)))){
      	forLen = listLength-1;
      }else{
      	forLen = listLength;
      }
      for (int i = 0; i < forLen; i++) {
      	Object columnvalue=responseMap.get(resColumnList.get(i));
      	if(columnvalue!=null){
      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
      	}
      }
      
      if(json.get("SUBPACKS")!=null)
      {
          JSONArray SUBPACKS=(JSONArray)json.get("SUBPACKS");
          System.out.println("SUBPACKS:"+SUBPACKS);
            responseMapMerged.append(responseMap.get(resColumnList.get(listLength-1)));
      }


      //验签
      String responseSign = (String) responseMap.get("SIGN");

      System.out.println("responseMerged: "+responseMapMerged.toString());

      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);
	    if (!verifyResult){
	    System.out.println("验证签名失败...");
	    return;
	} else {
	    System.out.println("验证签名成功");
	    String TRXCODE = responseMap.get("TRXCODE").toString();
	    String BANKCODE = responseMap.get("BANKCODE").toString();
	    String TRXDATE = responseMap.get("TRXDATE").toString();
	    String TRXTIME = responseMap.get("TRXTIME").toString();
	    String COINSTCODE = responseMap.get("COINSTCODE").toString();
	    String COINSTCHANNEL = responseMap.get("COINSTCHANNEL").toString();
 	    String SOURCE = responseMap.get("SOURCE").toString();
	    String RETCODE = responseMap.get("RETCODE").toString();
	    String RETMSG = responseMap.get("RETMSG").toString();
	    String HEADRESERVED = responseMap.get("HEADRESERVED").toString();
	    String KEYTYPE = responseMap.get("KEYTYPE").toString();
	    String IDNO = responseMap.get("IDNO").toString();
	    String NAME = responseMap.get("NAME").toString();
	    String USNO = responseMap.get("USNO").toString();
	    String CARDNBR = responseMap.get("CARDNBR").toString();
	    
//	    String RESERVED = responseMap.get("RESERVED").toString();
 	    
	    System.out.println("========TRXCODE================"+TRXCODE);
	      System.out.println("========BANKCODE================"+BANKCODE);
	      System.out.println("========TRXDATE================"+TRXDATE);
	      System.out.println("========TRXTIME================"+TRXTIME);
	      System.out.println("========COINSTCODE================"+COINSTCODE);
	      System.out.println("========COINSTCHANNEL================"+COINSTCHANNEL);
	      System.out.println("========SEQNO================"+COINSTCHANNEL);
	      System.out.println("========SOURCE================"+SOURCE);
	      System.out.println("========RETCODE================"+RETCODE);
	      System.out.println("========RETMSG================"+RETMSG);
	      System.out.println("========HEADRESERVED================"+HEADRESERVED);
	       
    	  System.out.println("========KEYTYPE================"+KEYTYPE);
    	  System.out.println("========IDNO================"+IDNO);
    	  System.out.println("========NAME================"+NAME);
    	  System.out.println("========CARDNBR================"+CARDNBR);
    	  System.out.println("========USNO================"+USNO);
//    	  System.out.println("========RESERVED================"+RESERVED);
	}
	
	if ("00000000".equals(responseMap.get("RETCODE"))){
	    System.out.println("操作成功");
	} else {
	    System.out.println("操作失败,错误代码："+responseMap.get("RETCODE"));
	}

  }
  
  
  public void callBcak(String result1){
	  
	  String result = HSignUtil.getDecryptRSAByte(result1);//解密
      
      JSONObject jsonObject = JSONObject.fromObject(result);
      
      String  TRXCODE 		= jsonObject.getString("TRXCODE");//交易代码 TRXCODE
      String  BANKCODE 		= jsonObject.getString("BANKCODE");//银行代码	BANKCODE
      String  TRXDATE 		= jsonObject.getString("TRXDATE");//交易日期	TRXDATE
      String  TRXTIME 		= jsonObject.getString("TRXTIME");//交易时间	TRXTIME
      String  COINSTCODE 	= jsonObject.getString("COINSTCODE");//合作单位编号	COINSTCODE
      String  COINSTCHANNEL = jsonObject.getString("COINSTCHANNEL");//合作单位渠道	COINSTCHANNEL
      String  SEQNO 		= jsonObject.getString("SEQNO");//交易流水号	SEQNO
      String  SOURCE 		= jsonObject.getString("SOURCE");//ESB内部渠道	SOURCE
      String  RETCODE 		= jsonObject.getString("RETCODE");//应答码	RETCODE
      String  RETMSG 		= jsonObject.getString("RETMSG");//应答码描述	RETMSG
      String  HEADRESERVED 	= jsonObject.getString("HEADRESERVED");//报文头保留域	HEADRESERVED
      
      String responseSign = jsonObject.getString("SIGN");
      System.out.println("========TRXCODE================"+TRXCODE);
      System.out.println("========BANKCODE================"+BANKCODE);
      System.out.println("========TRXDATE================"+TRXDATE);
      System.out.println("========TRXTIME================"+TRXTIME);
      System.out.println("========COINSTCODE================"+COINSTCODE);
      System.out.println("========COINSTCHANNEL================"+COINSTCHANNEL);
      System.out.println("========SEQNO================"+SEQNO);
      System.out.println("========SOURCE================"+SOURCE);
      System.out.println("========RETCODE================"+RETCODE);
      System.out.println("========RETMSG================"+RETMSG);
      System.out.println("========HEADRESERVED================"+HEADRESERVED);
      	  String  KEYTYPE 		= jsonObject.getString("KEYTYPE");// 证件类型 KEYTYPE
    	  String  IDNO 			= jsonObject.getString("IDNO");//证件号码	IDNO
    	  String  NAME 			= jsonObject.getString("NAME");// 姓名	NAME
    	  String  CARDNBR 		= jsonObject.getString("CARDNBR");// 电子账户	CARDNBR
    	  String  USNO 			= jsonObject.getString("USNO");// 第三方平台用户编号	USNO
//    	  String  RESERVED 		= jsonObject.getString("RESERVED");// 保留域 RESERVED
    	  System.out.println("========KEYTYPE================"+KEYTYPE);
    	  System.out.println("========IDNO================"+IDNO);
    	  System.out.println("========NAME================"+NAME);
    	  System.out.println("========CARDNBR================"+CARDNBR);
    	  System.out.println("========USNO================"+USNO);
//    	  System.out.println("========RESERVED================"+RESERVED);
         /**
       * 
       * TRXCODE + BANKCODE + TRXDATE + TRXTIME + COINSTCODE
       * COINSTCHANNEL + SEQNO+ SOURCE + RETCODE + RETMSG + HEADRESERVED
       * 
       * KEYTYPE + IDNO + NAME + CARDNBR + USNO
       */
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
    	  buffer.append(StringUtils.trimToEmpty(IDNO));
    	  buffer.append(StringUtils.trimToEmpty(NAME));
    	  buffer.append(StringUtils.trimToEmpty(CARDNBR));
    	  buffer.append(StringUtils.trimToEmpty(USNO));
//    	  buffer.append(StringUtils.trimToEmpty(RESERVED));
    	  
   
      String str = buffer.toString();
      
      boolean verifyResult = HSignUtil.getVerify(str, responseSign);
	    if (!verifyResult){
	    System.out.println("验证签名失败...");
	    return;
	} else {
	    System.out.println("验证签名成功");
	}
	
	if ("00000000".equals(jsonObject.getString("RETCODE"))){
	    System.out.println("操作成功");
	} else {
	    System.out.println("操作失败,错误代码："+jsonObject.getString("RETCODE"));
	}

  }
 
  
 
  
   
  public static void main(String[] args) throws Exception {
//      String request="zhuliang";
      //签名
//      String sign1 = RSAEncryptUtil.MD5WithRSASign(request.getBytes("UTF-8"), getSignPrivateKey4Client(signPrivatePath));
//      String sign1 = RSAEncryptUtil.MD5WithRSASign(request.getBytes("UTF-8"), getSignPrivateKey4Client(encryptPath));
//      String sign1 = RSAEncryptUtil.MD5WithRSASign(request.getBytes("UTF-8"), getSignPrivateKey4Client(signPrivatePath));
      
//     boolean falg =  RSAEncryptUtil.MD5WithRSAVerify(request.getBytes("UTF-8"), decryptPath, sign1);
//      System.out.println(sign1);
//      System.out.println(falg);
	  UserRegTest regTest = new UserRegTest();
	  regTest.test5810();
	  
//	  String user = "蒋学友";
//      String str = UnicodeAndStringUtil.stringByUnicode(user);
//      System.out.println(str);
//     System.out.println(str.replaceAll("\\\\", "\\\\\\\\"));
     
  }
}
