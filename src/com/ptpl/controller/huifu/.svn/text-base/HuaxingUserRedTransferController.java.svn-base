package com.ptpl.controller.huifu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.huaxing.util.DES3EncryptAndDecrypt;
import com.huaxing.util.MyRSA;
import com.huaxing.util.RSA;


public class HuaxingUserRedTransferController {

	public static void main(String[] args) throws Exception {
		
		transferAccounts();
	}
	
	//@RequestMapping("/transferAccounts")
	public static void transferAccounts() throws Exception{
		
		//1.XMLPARA节点的明文为：
		String TDATA = "<TDATA>"
						+	"<MERCHANTID>商户的唯一编号</MERCHANTID>"
						+	"<APPID>PC</APPID>"
						+	"<ACNO>收款账号</ACNO>"
						+	"<ACNAME>收款户名</ACNAME>"
						+	"<AMOUNT>交易金额</AMOUNT>"
						+"</TDATA>";
		
		//2.对XMLPARA节点的明文进行加密
		String XMLPara_Encrypt = DES3EncryptAndDecrypt.DES3EncryptMode(TDATA);
		
		//3.XMLPARA节点的密文为：
		String XMLPara_Secret = "<XMLPARA>"+XMLPara_Encrypt+"</XMLPARA>";
		
		//4.组成基本xml报文
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd,HHmmss");
		String dateStr = sdf.format(date);
		//System.out.println("dateStr====="+dateStr);
		String[] dateArr = dateStr.split(","); 
		
		String XMLPara_Message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
								+"<Document>"
								+"<header>"
								+	"<encryptData></encryptData>"//加密域
								+	"<serverFlow>服务流水号</serverFlow>"//服务流水号
								+	"<status>0</status>"//业务状态
								+	"<serverTime>"+dateArr[1]+"</serverTime>"//服务时间  HHmmss
								+	"<errorCode>0</errorCode>"//错误代码
								+	"<errorMsg></errorMsg>"//错误信息
								+	"<serverDate>"+dateArr[0]+"</serverDate>"//服务日期  yyyyMMdd
								+"</header>"
								+"<body>"
								+	"<TRANSCODE>OGW00076</TRANSCODE>"//交易码
								+	XMLPara_Secret
								+"</body>"
								+"</Document>";
		
		//5.对组成的基本xml报文提取MD5摘要
		String md5Result = MyRSA.MD5(XMLPara_Message);
		
		//6.对MD5摘要进行签名,使用己方私钥对摘要进行签名
		//生成私钥
		String privateKeyStr = RSA.getPivateKey(RSA.initKey());
		System.out.println("生成私钥：   "+privateKeyStr);
		//生成公钥
		String publicKeyStr = RSA.getPublicKey(RSA.initKey());
		System.out.println("生成公钥：   "+publicKeyStr);
		
		//私钥签名
		PrivateKey privateKey = MyRSA.restorePrivateKey(MyRSA.decryptBASE64(privateKeyStr));
		byte[] encodedText = MyRSA.RSAEncode(privateKey, md5Result.getBytes("UTF-8"));
		System.out.println("签名后的数据：    "+encodedText);
		
		//将签名后的数据转换为十六进制的字符串
		//String privateResult = MyRSA.byteArrayToHexString(encodedText);//报文头前面256位的私钥签名后的结果privateResult
		String privateResult = byteArrayToHexString(encodedText);//报文头前面256位的私钥签名后的结果privateResult
		System.out.println("签名后的256位数据===="+privateResult);
		System.out.println("privateResult的长度为===="+privateResult.length());
				
		//拼接安全域
		//privateResult的长度
		int length = privateResult.length();
		int count = 0;//用来记录长度的位数
		while(length>0){
			length = length/10;
			count++;
		}

		StringBuffer sb = new StringBuffer("0");
		for(int i=0;i<7-count;i++){
			sb.append("0");
		}
		if(privateResult.length()>0){
			sb.append(Integer.toString(privateResult.length()));
		}
		System.out.println("安全域：    "+sb.toString());
		
		//最终的报文
		String XMLPara_Final = "001X11" +sb.toString() +privateResult + XMLPara_Message;
				
		System.out.println("最终的报文为====="+XMLPara_Final);
		
		//模拟同步交易发送报文
		URL postUrl = null;
		StringBuffer strBuffer = null;
		try{
			postUrl = new URL("http://183.63.131.106:40011/extService/ghbExtService.do"); //URL请求地址
			HttpURLConnection urlcon =  (HttpURLConnection) postUrl.openConnection();
			int contentLength = XMLPara_Final.getBytes().length;  //获取报文长度
			urlcon.setConnectTimeout(1000*15);
			urlcon.setReadTimeout(1000*60*2);
			urlcon.setRequestMethod("POST");//post请求方式
			urlcon.setUseCaches(false);//post请求不能使用缓存
			urlcon.setRequestProperty("Content-Length", String.valueOf(contentLength));
			urlcon.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			urlcon.setDoInput(true);//默认为true
			urlcon.setDoOutput(true);//默认为true
			//urlcon.connect();//urlcon.getOutputStream()会隐含的进行connect();
			DataOutputStream output =  new DataOutputStream(urlcon.getOutputStream());
			output.writeBytes(XMLPara_Final);
			output.flush();
			output.close();
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
			strBuffer = new StringBuffer();
			String str = null;
			while((str = buffer.readLine())!=null){
				strBuffer.append(str);
			}
			
			System.out.println("返回的报文为：     "+strBuffer);
			buffer.close();
			urlcon.disconnect();//断开连接
			
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			//1.对返回的报文截取签名原文
			String XMLPara_Cut = strBuffer.substring(strBuffer.indexOf("<?xml"));
			//2.将收到的整个<?xml>报文进行md5提取报文摘要
			String md5Result_Cut = MyRSA.MD5(XMLPara_Cut);
			//3.对返回的报文截取签名后的数据
			String privateResult_Cut = strBuffer.substring(strBuffer.indexOf(sb.toString()), strBuffer.indexOf("<?xml"));
			System.out.println("privateResult_Cut：   "+privateResult_Cut);
			System.out.println("字符串长度是否为256：         "+privateResult_Cut.length());
			//4.将签名后的数据转化为字节数组    privateResult_Cut 和上面的 privateResult应该是一样的
			byte[] encodedText_Cut = MyRSA.hexStringToByte(privateResult_Cut);
			//5.用公钥验签，验签后的值与第一步MD5摘要值相同，则证明未被篡改
			//验签
			//公钥解密
			PublicKey  publicKey = MyRSA.restorePublicKey(MyRSA.decryptBASE64(publicKeyStr));
			String decodeStr = MyRSA.RSADecode(publicKey, encodedText_Cut);
			System.out.println("公钥解密：    "+decodeStr);
			
			if(decodeStr.equals(md5Result_Cut)){
				System.out.println("一致，未被篡改");
			}
			
		}
	}
		
	// 将字节数组转换为十六进制字符串
	private static String byteArrayToHexString(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++)
		{
			strDigest += byteToHexString(bytearray[i]);
		}
		return strDigest;
	}
	
	// 将字节转换为十六进制字符串
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}
