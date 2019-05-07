package com.huishang.util;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.ptpl.web.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.*;
/**
 * 
* @ClassName: HSignUtil 
* @Description: TODO(徽商银行HttpClient工具类) 
* @author cjm 
* @date 2017年3月31日 下午2:44:47 
*
 */
public class HSignUtil {
	
	public HSignUtil(){}
	
	private static Properties props      = new Properties(); 
	private static Properties apiProps   = new Properties(); 
	public static final String DISK      = "D:"; //证书磁盘  windows为"D:"  linux为""
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("HS_000200.properties"));
			apiProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ptpapi_url.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return props.getProperty(key);
	}
	
	public static String getApiProps(String key){
		return apiProps.getProperty(key);
	}
	
	
	
	  public static final String PRODUCT 		   = HSignUtil.getValue("PRODUCT");//卡产品编号 PRODUCT
	  
	  public static final String COINSTCODE	       = HSignUtil.getValue("COINSTCODE");//合作单位编号	COINSTCODE
	  
	  public static final String FUISSUER	   	   = HSignUtil.getValue("FUISSUER");//产品发行方	FUISSUER
	  
	  public static final String SOURCE			   = HSignUtil.getValue("SOURCE");//内部渠道号
	  
	  
	  //发送路由地址
	  public static String HTTP                              = "http://";
	  public static String URI 								 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("URI");//发送路由后台提交数据
	  public static String RECHARGE 						 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("RECHARGE");//页面充值
	  public static String PINSETTING                        = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("PINSETTING");//交易密码设置 不加密
	  public static String PINSETTINGSIGN  				     = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("PINSETTINGSIGN");//交易密码设置 加密
	  public static String WITHHOLDING 						 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("WITHHOLDING");//提现 不加密
	  public static String WITHHOLDINGSIGN 					 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("WITHHOLDINGSIGN");//提现 加密
	  public static String TRUSTEEPAY 						 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("TRUSTEEPAY");  //受托支付
	  public static String BIDAPPLY 						 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("BIDAPPLY");  //投标申请   不加密
	  public static String BIDAPPLYSIGN 					 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("BIDAPPLYSIGN");  //投标申请  加密
	  public static String DEBTCESSIONSIGN 					 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("DEBTCESSIONSIGN");  //债转申请
	  public static String AUTOBIDAPPLY 					 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("AUTOBIDAPPLY");  //自动投标签约  不加密
	  public static String AUTOBIDAPPLYSIGN 				 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("AUTOBIDAPPLYSIGN");  //自动投标签约  加密
	  public static String AUTODEBTCESSION  				 = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("AUTODEBTCESSION");   //自动债转签约
	  public static String AUTHAUTOBUSINESS                  = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("AUTHAUTOBUSINESS");   //投资业务授权   包含自动投标签约和自动债转签约
	  public static String BATCREQHURL                       = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("BATCREQHURL");//文件上传url
	  public static String BATCHRESTARURL                    = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("BATCHRESTARURL");//tar文件下载url
	  public static String BATCHRESURL                       = HTTP+HSignUtil.getApiProps("BASEHOST")+HSignUtil.getApiProps("BATCHRESURL");//文件下载url
 
	  //发送徽商地址
//	  public static String URI 								 = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";//后台提交数据	  
//	  public static String RECHARGE 						 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/recharge";//页面充值	  
//	  public static String PINSETTING                        = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/pinsetting";//交易密码设置 不加密		  
//	  public static String PINSETTINGSIGN  				     = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/pinsettingSign";//交易密码设置 加密	  
//	  public static String WITHHOLDING 						 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/withholding";//提现 不加密		  
//	  public static String WITHHOLDINGSIGN 					 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/withholdingSign";//提现 加密	  
//	  public static String TRUSTEEPAY 						 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/trusteePay";  //受托支付		  
//	  public static String BIDAPPLY 						 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/bidApply";  //投标申请   不加密			  
//	  public static String BIDAPPLYSIGN 					 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/bidApplySign";  //投标申请  加密				  
//	  public static String DEBTCESSIONSIGN 					 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/debtCessionSign";  //债转申请					  
//	  public static String AUTOBIDAPPLY 					 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/autoBidApply";  //自动投标签约  不加密						  
//	  public static String AUTOBIDAPPLYSIGN 				 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/autoBidApplySign";  //自动投标签约  加密							  
//	  public static String AUTODEBTCESSION  				 = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/autoDebtCession";   //自动债转签约	  
//	  public static String AUTHAUTOBUSINESS                  = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/authAutoBusiness";   //投资业务授权   包含自动投标签约和自动债转签约    
//	  public static String BATCREQHURL                       = "http://onlineuat.cupdata.com:50001/dbesbsit/file/uploadEncrypt";//文件上传url    
//	  public static String BATCHRESTARURL                    = "http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptforTarWithSign";//tar文件下载url    
//	  public static String BATCHRESURL                       = "http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptWithSign";//文件下载url
	  
	  //发送徽商地址-生产环境
	  
	  
	  
      
      //文件路径:  /batchfile/合作单位编号/业务/时间（yyyymmdd）
	  //例如                  /batchfile/800114/fintran/20170601
      public static final String FILEUPLOAD                  = HSignUtil.DISK +File.separator + "batchfile" + File.separator + HSignUtil.COINSTCODE;//批量文件跟路径
      
      public static final String BATCHUSERREGISTERFILEUPLOAD = File.separator+"bacthUserRegister";//批量开户文件路径
      
      public static final String FINTRAN                     = File.separator+"fintran"; // 融资扣款文件路径
      
      public static final String REPAYMENT                   = File.separator+"repayment";//到期还款文件路径
      
      public static final String REDTRANSTER                 = File.separator+"trqt";//红包转账文件路径
      
      public static final String BID                   		 = File.separator+"bid";//债权迁移文件路径
      
      public static final String SIGTRAN					 = File.separator+"signtran";//签约关系迁移文件路径
      
      public static final String TRNCR 					     = File.separator+"trncr";//批量债权转让文件路径
      
      public static final String TRQT					 	 = File.separator+"trqt";//批量发红包文件路径
      
      public static final String BIDIN					 	 = File.separator+"bidin";//批量标的录入文件路径
      
      public static final String EVE					 	 = File.separator+"eve";//交易明细流水文件路径
      
      public static final String ALEVE					 	 = File.separator+"aleve";//全流水文件路径
      
      
      public static final String KEYPATH           = HSignUtil.DISK + "/wskey/"+HSignUtil.COINSTCODE;//密钥文件路径
       
	  public static final String SIGNPRIVATEPATH   = HSignUtil.KEYPATH + "/sign_.key";

	  public static final String SIGNPUBLICPATH    = HSignUtil.KEYPATH + "/verify_.cer";

	  public static final String DECRYPTPATH       = HSignUtil.KEYPATH + "/decryptions_.key";

	  public static final String ENCRYPTPATH       = HSignUtil.KEYPATH + "/encryptions_.cer";
	  
	  public static final String ENCRYPTION_FILE   = HSignUtil.KEYPATH + "/encryption_file_"+HSignUtil.COINSTCODE+".key";
	  
//	  public static final String PRODUCT 		   = "0054";//卡产品编号 PRODUCT
//	  
//	  public static final String BANKCODE 		   = "30040000";//银行代码
//	  
//	  public static final String  COINSTCODE	   = "800114";//合作单位编号	COINSTCODE
//	  
//	  public static final String  FUISSUER	   	   = "14";//产品发行方	FUISSUER
//	  
//	  public static final String SOURCE			   = "A0";//内部渠道号
	  
	  
	  public static final String BANKCODE 		   		   = "30040000";//银行代码
	  
	  public static final String FILEBANKCODE	   		   = "3004-";//批量文件银行编号
	  
	  public static final String COINSTCHANNEL_APP         = "000001";//合作单位渠道  手机
	  
	  public static final String COINSTCHANNEL_WEB         = "000002";//合作单位渠道  页面
	  
	  public static final String COINSTCHANNEL_WECHAT      = "000003";//合作单位渠道  微信
	  
	  public static final String COINSTCHANNEL_COUNTER     = "000004";//合作单位渠道  柜面
	  
	  public static final String COINSTCHANNEL_ESB         = "000005";//合作单位渠道  ESB
	  
	 
 	/**
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: getRASSign 
	* @Description: TODO(加签抛送银行的的数据) 
	* @param @param reqMap
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static String getRASSign(LinkedHashMap reqMap) throws UnsupportedEncodingException, Exception{
		String sign = "";
		if(reqMap.size() > 0){
			String requestMapMerged = mergeMap(reqMap);
			sign = RSAEncryptUtil.MD5WithRSASign(requestMapMerged.getBytes("UTF-8"), getSignPrivateKey4Client(HSignUtil.SIGNPRIVATEPATH));//加签名
		}
		return sign;
	}
	
	/**
	 * 
	* @Title: getEncryptRSAByte 
	* @Description: TODO(进行摘要并对摘要进行加密) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static String getEncryptRSAByte(LinkedHashMap reqMap){
		String encryptResult = "";
		if(reqMap.size() > 0){
			JSONObject jsMap = JSONObject.fromObject(reqMap);//把LinkedHashMap 转成json 格式
 			String js = jsMap.toString();
 	        String encryptionKey4Server = RSAUtils.getVerifyKey4Client(HSignUtil.ENCRYPTPATH);	//获取加密公钥字符串
	        try {
	        	encryptResult = RSAUtils.encryptRSAByte(js.getBytes("UTF-8"), encryptionKey4Server);
			} catch (UnsupportedEncodingException e) {
 				e.printStackTrace();
			} catch (Exception e) {
 				e.printStackTrace();
			}//进行摘要并对摘要进行加密
 		}
		return encryptResult;
	}
	
	 /**
	  * 
	 * @Title: doHttpClient 
	 * @Description: TODO(抛送银行) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	public  static String doHttpClient(Map map){
		String result = "";
		if(map.size() > 0){
 			HttpClient httpClient = new HttpClient();
			PostMethod method = new PostMethod(HSignUtil.URI);
//			PostMethod method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt");
 			JSONObject reqdata = JSONObject.fromObject(map);
 			System.out.println(reqdata);
 			method.setParameter("reqdata", reqdata.toString());
 			try {
				httpClient.executeMethod(method);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			try {
				result = method.getResponseBodyAsString();
			} catch (IOException e) {
 				e.printStackTrace();
			}
 		}
 		return result;
	}
	
	/**
	 * 
	* @Title: decryptRSAByte 
	* @Description: TODO(接受数据后进行解密) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static String getDecryptRSAByte(String HttpClientResult){
		String decryptResult = "";
		if(StringUtil.isNotEmpty(HttpClientResult)){
 			  Map jsonMap = JSONObject.fromObject(HttpClientResult);
		      String resultData = jsonMap.get("responseData").toString();
		      //解密调整
 		      String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(HSignUtil.DECRYPTPATH);
 			try {
				decryptResult = RSAUtils.decryptRSAs(resultData,decryptKey4Server);
			} catch (Exception e) {
 				e.printStackTrace();
			}//对请求数据解密
  		}
		return decryptResult;
	}
	
	
	/**
	 * 
	* @Title: getVerify 
	* @Description: TODO(验签) 
	* @param @param responseResult 
	* @param @param responseSign
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static boolean getVerify(String responseResult,String responseSign){
		boolean falg = false;
		try {
			falg = RSAEncryptUtil.MD5WithRSAVerify(responseResult.getBytes("UTF-8"), getVerifyKey4Client(HSignUtil.SIGNPUBLICPATH), responseSign);
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return falg;
	}
	 
	 
	private  static String mergeMap(Map map){

	      String requestMerged = "";
	      StringBuffer buff = new StringBuffer();

	      Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

	      Map.Entry<String, String> entry;

	      while (iter.hasNext()) {

	          entry = iter.next();
	          System.out.print(entry.getKey() + ", ");
	          if (!"SIGN".equals(entry.getKey())){
	              buff.append(entry.getValue().trim());
	          }

//	      value = entry.getValue();

	      }
	      System.out.println(" ");

	      requestMerged = buff.toString();
	      return requestMerged;

	  }
	
	/**
	   * json转map
	   *
	   *  @param jsonStr 源数据
	   *
	   *  return map
	   * */
	  public static Map<String, Object> parseJSON2Map(String jsonStr){
	      Map<String, Object> map = new HashMap<String, Object>();
	      //最外层解析
	      JSONObject json = JSONObject.fromObject(jsonStr);
	      for(Object k : json.keySet()){
	          Object v = json.get(k);
	          //如果内层还是数组的话，继续解析
	          if(v instanceof JSONArray){
	              List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	              Iterator<JSONObject> it = ((JSONArray)v).iterator();
	              while(it.hasNext()){
	                  JSONObject json2 = it.next();
	                  list.add(parseJSON2Map(json2.toString()));
	              }
	              map.put(k.toString(), list);
	          } else {
	              map.put(k.toString(), v);
	          }
	      }
	      return map;
	  }
	
	  /**
	   * 根据私钥文件路径读取私钥
	   * @param
	   * @return
	   */
	  public static String getSignPrivateKey4Client(String keyPath){
	      StringBuffer privateBuffer=new StringBuffer();
	      try {
	          //InputStream inputStream = Thread.currentThread().getContextClassLoader()
	          //       .getResourceAsStream(signPrivatePath);
	          FileInputStream fi=new FileInputStream(keyPath);
	          InputStreamReader inputReader = new InputStreamReader(fi);
	          BufferedReader bufferReader = new BufferedReader(inputReader);
	          // 读取一行
	          String line = "";
	          while ((line=bufferReader.readLine())!=null) {
	              privateBuffer.append(line);
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return privateBuffer.toString();
	  }
	  
	  
	  /**
	   * 根据公钥文件路径读取公钥
	   * @param
	   * @return
	   */
	  public static String getVerifyKey4Client(String keyPath){
	      String key="";
	      try {
	          CertificateFactory cf = CertificateFactory.getInstance("X.509");
	          //InputStream in = Thread.currentThread().getContextClassLoader()
	          //      .getResourceAsStream(signPublicPath);
	          FileInputStream fi=new FileInputStream(keyPath);
	          //生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。
	          Certificate c = cf.generateCertificate(fi);
	          PublicKey publicKey = c.getPublicKey();
	          key=new BASE64Encoder().encodeBuffer(publicKey.getEncoded());
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return key;
	  }
	  
	  /**
	   * 
	  * @Title: getResponseHead 
	  * @Description: TODO(徽商银行HttpClient 请求返回报文参数头) 
	  * @param @param resColumnList  其他接口的参数（12域之后的参数）
	  * @param @return    设定文件 
	  * @return List<String>    返回类型 
	  * @author   cjm  
	  * @throws
	   */
	  public static List<String> getResponseHead(List<String> resColumnList){
  		  List<String> headColumn = new ArrayList<>();
 	      headColumn.add("TRXCODE");//交易代码	TRXCODE
	      headColumn.add("BANKCODE");//银行代码	BANKCODE
	      headColumn.add("TRXDATE");//交易日期	TRXDATE
	      headColumn.add("TRXTIME");//交易时间	TRXTIME
	      headColumn.add("COINSTCODE");//合作单位编号	COINSTCODE
	      headColumn.add("COINSTCHANNEL");//合作单位渠道	COINSTCHANNEL
	      headColumn.add("SEQNO");//交易流水号	SEQNO
	      headColumn.add("SOURCE");//ESB内部渠道	SOURCE
	      headColumn.add("RETCODE");//应答码	RETCODE
	      headColumn.add("RETMSG");//应答码描述	RETMSG
	      headColumn.add("HEADRESERVED");//报文头保留域	HEADRESERVED
	      resColumnList.addAll(0, headColumn);
 	      return resColumnList;
	  }
	  
	  /**
		 * 
		* @Title: HttpClientTask 
		* @Description: TODO(HttpClient 提交 徽商银行) 
		* @param @param reqMap
		* @param @param version
		* @param @return
		* @param @throws UnsupportedEncodingException
		* @param @throws Exception    设定文件 
		* @return String    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static String  HttpClientTask(LinkedHashMap<String,String> reqMap,String version) throws UnsupportedEncodingException, Exception{
			String TaskResult = "";
			if(reqMap.size() > 0){
				   Map<String,Object> map = new HashMap<>();
			       map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行号
			       map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
			       map.put("APIVERSION",version);//为报文版本号
			       
			       //发送路由数据
			       map.put("TRXCODE",reqMap.get("TRXCODE"));//交易码
			       map.put("TRXDATE",reqMap.get("TRXDATE"));//交易日期
			       map.put("TRXTIME",reqMap.get("TRXTIME"));//交易时间
			       map.put("SEQNO",reqMap.get("SEQNO"));//交易流水
			       
	 		       String sign1 = HSignUtil.getRASSign(reqMap);//加签名
	 		       reqMap.put("SIGN", sign1);
	 		       
			       String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
			       
			       map.put("reqMap",data);//加密后的json参数
			       
	 		       TaskResult = HSignUtil.doHttpClient(map);//发送HttpClient请求
	  		}
			return TaskResult;
		}
		
		/**
	     * 加密,读取旧的文件的内容加密后写入新的文件
	     * @param file1
	     * @param file2
	     * @param keyPath	密钥路径
	     * @return	返回文件内容
	     */
	    public static String encryptHand(File file1,File file2, String keyPath){
	        String result="";
	        String line = "";
	        byte[] bt;
	        String encryptResultStr="";
	        try {
	            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (file1),"GBK"));
	            BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file2),"GBK"));
	            if((line=br.readLine())!=null){
	        		bt=encryptByAES(result + line, keyPath);//逐行加密
	                encryptResultStr =parseByte2HexStr(bt);
	                bw.write(encryptResultStr);
	        	}
	            while((line=br.readLine())!=null){
	                bt=encryptByAES(result + line, keyPath);//逐行加密
	                encryptResultStr =parseByte2HexStr(bt);
	                bw.newLine();
	                bw.write(encryptResultStr);
	            }
	            bw.newLine();
	            bw.flush();
	            bw.close();
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
		
		
		
		/**
	     * 加密
	     *
	     * @param content 需要加密的内容
	     * @param keyPath  加密密码
	     * @return
	     */
	    public static byte[] encryptByAES(String content, String keyPath) throws NoSuchAlgorithmException {

	        SecretKeySpec key = null;
	        try {
	            SecretKey secretKey =getKey(keyPath);
	            byte[] enCodeFormat = secretKey.getEncoded();
	            key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            byte[] byteContent = content.getBytes("GBK");
	            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(byteContent);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**解密
	     * @param content  待解密内容
	     * @param keyPath 解密密钥
	     * @return
	     */
	    public static byte[] decryptByAES(byte[] content, String keyPath) throws NoSuchAlgorithmException {

	        SecretKeySpec key = null;
	        try {
	            SecretKey secretKey =getKey(keyPath);
	            byte[] enCodeFormat = secretKey.getEncoded();
	            key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	            byte[] result = cipher.doFinal(content);
	            return result; // 加密
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    //初始化密钥
	    public static SecretKey getKey(String strKey) {
	        try {
	            KeyGenerator _generator = KeyGenerator.getInstance("AES");
	            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
	            secureRandom.setSeed(strKey.getBytes());
	            _generator.init(128,secureRandom);
	            return _generator.generateKey();
	        }  catch (Exception e) {
	            throw new RuntimeException( " 初始化密钥出现异常 " );
	        }
	    }
	    /**将二进制转换成16进制
	     * @param buf
	     * @return
	     */
	    public static String parseByte2HexStr(byte buf[]) {
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < buf.length; i++) {
	            String hex = Integer.toHexString(buf[i] & 0xFF);
	            if (hex.length() == 1) {
	                hex = '0' + hex;
	            }
	            sb.append(hex.toUpperCase());
	        }
	        return sb.toString();
	    }

	    /**将16进制转换为二进制
	     * @param hexStr
	     * @return
	     */
	    public static byte[] parseHexStr2Byte(String hexStr) {
	        if (hexStr.length() < 1)
	            return null;
	        byte[] result = new byte[hexStr.length()/2];
	        for (int i = 0;i< hexStr.length()/2; i++) {
	            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
	            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
	            result[i] = (byte) (high * 16 + low);
	        }
	        return result;
	    }
	    
	    public static void transFormToTmpFile(File f1, File tmp)throws IOException {
			BufferedReader  br = null;	
			BufferedWriter bw = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(f1.getPath()),"GBK"));
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (tmp.getPath()),"GBK"));
				String line = "";
				while((line = br.readLine()) != null){
					if(!("").equals(line)){
						bw.write(line);
						bw.flush();
					}
					
				}
				
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(bw!=null){
					bw.close();
				}
			}
		}
	    
	    
	    public static  String keyPath="A842aed1aAA39dCb";
	    public static void decryptHand(File file1,File file2,String keyPath){
	        // String result="";
	        String line ="";
	        byte[] decryptFrom=null;
	        byte[] decryptResult=null;

	        try{

	            //FileReader reader = new FileReader(file1);
	            //FileWriter writer = new FileWriter(file2);
	            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (file1),"GBK"));
	            BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file2),"GBK"));
	            //BufferedReader br = new BufferedReader(reader);
	            // BufferedWriter bw = new BufferedWriter(writer);
	            if((line = br.readLine())!=null){
	        		//解密
	                String result="";
	                decryptFrom =parseHexStr2Byte(result+line);
	                decryptResult = decryptByAES(decryptFrom, keyPath);//逐行解密
	                result=new String(decryptResult,"GBK");
	                bw.write(result);
	        	}
	            while((line = br.readLine())!=null){
	                //解密
	                String result="";
	                decryptFrom =parseHexStr2Byte(result+line);
	                decryptResult = decryptByAES(decryptFrom, keyPath);//逐行解密
	                result=new String(decryptResult,"GBK");
	                bw.newLine();
	                bw.write(result);
	            }
	            bw.flush();
	            bw.close();
	            br.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }

	    }
	    
	    
	    
	    
}
