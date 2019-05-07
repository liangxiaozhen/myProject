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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
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
public class RechargeTest {

    static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
//  static String uri = "http://localhost:8080/dbesb/api/requestEncrypt";


  private  static String signPrivatePath="D:/wskey/sign_.key";

  private static  String signPublicPath="D:/wskey/verify_.cer";

  private  static String decryptPath="D:/wskey/decrypts_.key";

  private static  String encryptPath="D:/wskey/encryptions_.cer";
  
  
//直销银行电子账户充值
  public void test5810() throws Exception{
      LinkedHashMap reqMap = new LinkedHashMap();
      reqMap.put("TRXCODE", "5810");//交易代码	TRXCODE
      reqMap.put("BANKCODE", "30040000");//银行代码	BANKCODE

      reqMap.put("TRXDATE", "20170331");//交易日期	TRXDATE
      reqMap.put("TRXTIME", "102100");//交易时间	TRXTIME
      reqMap.put("COINSTCODE", "800114");//合作单位编号	COINSTCODE
      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
      reqMap.put("SEQNO","20170421133000001118");//交易流水号	SEQNO
      reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
      reqMap.put("RETCODE", "");//应答码	RETCODE
      reqMap.put("RETMSG", "");//应答码描述	RETMSG
      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED

      reqMap.put("CARDNBR", "01");//电子账户
      reqMap.put("CARD_BIND", "");//绑定卡卡号
      reqMap.put("CURRENCY", "156");//币种
      reqMap.put("AMOUNT", "100");//金额
      reqMap.put("KEYTYPE", "");//证件类型
      reqMap.put("IDNO", "630105198506020131");//证件号码 IDNO
      reqMap.put("SURNAME", "\\u9676\\u71d5");//姓名 SURNAME
      //reqMap.put("MOBILE", "18677771111");//手机号码 MOBILE
      reqMap.put("MOBILE", "18677771114");//手机号码 MOBILE
      reqMap.put("AUTH_FLAG", "N");//ESB代发实名认证标志
      //下面为可选参数
      reqMap.put("AUTHSEQ", "");//实名认证流水号
      reqMap.put("BANK_CODE", "");//开户银行代码
      reqMap.put("BANK_NAME_EN", "");//开户银行英文缩写
      reqMap.put("BANK_NAME_CN", "");//开户银行中文名称
      reqMap.put("ISS_BANK_PROVINCE", "");//开户行省份
      reqMap.put("ISS_BANK_CITY", "");//开户行城市
      reqMap.put("CALL_BACK_ADRRESS", "");//回调地址
      reqMap.put("SMS_CODE", "");//短信验证码
      reqMap.put("SMS_SEQ", "");//短信序列号
      reqMap.put("USR_IP", "");//客户IP
      reqMap.put("RESERVED", "");//保留域
      
   
      //第三方平台用户编号 USNO
      // 客户IP USR_IP 
      //保留域 RESERVED
      //
      ArrayList<String> resColumnList = new ArrayList();

      resColumnList.add("KEYTYPE");
      resColumnList.add("IDNO");
      resColumnList.add("NAME");
      resColumnList.add("CARDNBR");

      testCommon2(reqMap, resColumnList,null);
  }
  
  
   
  
  public void testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList,String code) throws  Exception{
	  	testCommon2(reqMap, resColumnList,code,"");
	  }

  //公共方法调用2
  public void testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList,String code,String version) throws  Exception
  {
      Map map=new HashMap();
      map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行号
      map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
      map.put("APIVERSION",version);//为报文版本号
      
      String sign1 = HSignUtil.getRASSign(reqMap);//加签名
      System.out.println(sign1);
       reqMap.put("SIGN", sign1);
       String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
       map.put("reqMap",data);//加密后的json参数
       
      String result1 = HSignUtil.doHttpClient(map);//发送HttpClient请求
       
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
//	    	  String  RESERVED = jsonObject.getString("RESERVED");// 保留域 RESERVED
   	  
 
    	  
       
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
  /**
	 * 余额查询发送
	 * @param @param usercustid
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/UserRechargeCall", method = { RequestMethod.POST, RequestMethod.GET })
	public  Map<String,String> queryBanlance(String usercustid) throws Exception {
		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		//请求头
		String orderno = StringUtil.getNo();//交易流水号
		  reqMap.put("TRXCODE", "5824");
	      reqMap.put("BANKCODE", "30040000");
	      reqMap.put("TRXDATE",TRXDATE);
	      reqMap.put("TRXTIME", TRXTIME);
	      reqMap.put("COINSTCODE", "800114");
	      reqMap.put("COINSTCHANNEL","000002");
	      reqMap.put("SEQNO",orderno);//交易流水号
	      reqMap.put("SOURCE", "A0");
	      reqMap.put("RETCODE", "");
	      reqMap.put("RETMSG", "");
	      reqMap.put("HEADRESERVED", "");
	      
	      //请求参数
	      reqMap.put("CARDNBR",usercustid); //电子账号 也就是用户客户号 "9930040050240500013"
	      reqMap.put("PINFLAG", "0");//0-不检查密码     1-检查取现密码
	      reqMap.put("PIN", "");//密码 ANSI98格式，详见“信息安全处理”PIN加密部分
	      reqMap.put("STARTDATE", TRXDATE); //起始日期
	      reqMap.put("ENDDATE", ""); //结束日期
	      reqMap.put("RTN_IND", ""); //翻页标志:首次查询上送空；翻页查询上送1；
	      reqMap.put("NX_INPD", ""); //交易日期:翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录交易日期；
	      reqMap.put("NX_RELD", ""); //自然日期 :翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录消费日期；
	      reqMap.put("NX_INPT", ""); //交易时间:翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录交易时间；
	      reqMap.put("NX_TRNN", ""); //交易流水号:翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录交易号；
	      reqMap.put("TRANTYPE", ""); //空：全部交易类型 7777：基金分红9999：查非分红 其他值：指定交易类型
	      reqMap.put("TYPE_FLAG", "1"); //18域为9999时有效1：转入交易；2：转出交易；3：转入及转出交易；
	      reqMap.put("RESERVED", ""); //保留域
	
	      
	      String result = fenzhuang(reqMap,"");
	      Map<String,String> hashMap2 = blanceCallBack(result);
			return hashMap2;
	}
	/**
	 * 查询余额返回
	 * @param @param result
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public Map<String,String> blanceCallBack(String result){
		System.out.println(result);
		 Map<String,String> hashMap = new HashMap<>();
		  List<String> kjList = new ArrayList<>();
		  kjList.add("CARDNBR");//卡号
		  kjList.add("NAME");//持卡人姓名
		  kjList.add("STARTDATE");//起始日期
		  kjList.add("ENDDATE");//结束日期
		  kjList.add("CURRNO");//本次返回交易条数
		  kjList.add("RTN_IND");//翻页标志
		  kjList.add("HS_VALD");//入帐日期
		  kjList.add("HS_INPD");//交易日期
		  kjList.add("HS_RELD");//自然日期
		  kjList.add("HS_INPT");//交易时间
		  kjList.add("HS_TRNN");//交易流水号
		  kjList.add("TRANTYPE");//交易类型
		  kjList.add("O_R_FLAG");//冲正/撤销标志
		  kjList.add("BILL_AMT");//交易金额
		  kjList.add("BILL_AMS");//交易金额符号
		  kjList.add("AUTHCODE");//授权代码
		  kjList.add("DESLINE");//交易描述
		  kjList.add("CURR_BAL");//交易后余额
		  kjList.add("NOTE");//摘要
		  kjList.add("FORCARDNBR");//对手交易账号
		  kjList.add("RESERVED");//保留域
		 String resultdata = HSignUtil.getDecryptRSAByte(result);//解密
       System.out.println("*************"+resultdata+"************");
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resultdata);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(kjList);//把3031接口返回报文参数和1到11域拼接一起
	 	  
	 	 int listLength = resColumnList.size();
	     if(responseMap.get("RETCODE") == null){
	    	  hashMap.put("result", "network");// "因网络响应不及时,处理失败";
	    	  return hashMap;
	      }
	        
	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
	      System.out.println(responseMap);
	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
	     if(!verifyResult){
	    	hashMap.put("result", "network");// "因网络响应不及时,处理失败";
	    	return hashMap;
	    }
	    System.out.println(responseMap.get("RETCODE")+"============="); //应答码
	    
	    JSONObject json = JSONObject.fromObject(resultdata);
	    if(json.getString("RETCODE").equals("00000000")){
	    	hashMap.put("CARDNBR", json.getString("CARDNBR"));
	    	hashMap.put("NAME", json.getString("NAME"));
	    	hashMap.put("AVAIL_BAL", json.getString("AVAIL_BAL"));
	    	hashMap.put("CURR_BAL", json.getString("CURR_BAL"));
	    }
		return hashMap;
		
	}
	/**
	 * 后台提交
	 * @param @param reqMap
	 * @param @param version
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String fenzhuang(LinkedHashMap<String,String> reqMap,String version) throws Exception{
		String TaskResult = "";
		if(reqMap.size() > 0){
			   Map<String,Object> map = new HashMap<>();
			   map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行代码
		       map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
		       map.put("APIVERSION",version);//为报文版本号
		       
		       String sign = HSignUtil.getRASSign(reqMap);//加签名
		       reqMap.put("SIGN", sign);
		       	
		       String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
		       
		       map.put("reqMap",data);//加密后的json参数
		       TaskResult = HSignUtil.doHttpClient(map);//发送HttpClient请求
		}
		return TaskResult;
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
      
  }
}
