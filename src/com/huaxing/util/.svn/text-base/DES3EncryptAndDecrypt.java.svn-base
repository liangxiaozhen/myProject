package com.huaxing.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DES3EncryptAndDecrypt {

private static String ALGORITHM="DESede";
	
	//3des应用加密密码（由华兴银行统一分配)
	private static String Des3Key="A1B2C3D4E5F6G7H8I9J0K1L2";
	
	public static String  DES3EncryptMode(String src) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		DESedeKeySpec desKeySpec = new DESedeKeySpec(Des3Key.getBytes()); //创建一个 DESedeKeySpec 对象，使用 key 中的前 24 个字节作为 DES-EDE 密钥的密钥内容
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); //返回转换指定算法的秘密密钥的 SecretKeyFactory 对象。
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec); //根据提供的密钥规范（密钥材料）生成 SecretKey 对象。
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);//初始化该 Cipher：加密、解密、密钥包装或密钥解包，具体取决于 opmode 的值。 这里是加密
		return new String(Base64.encodeBase64(cipher.doFinal(src.getBytes("UTF-8"))));//Base64 加密
	}
	
	
	public static String  DES3DecryptMode(String src) throws Exception {		
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		DESedeKeySpec desKeySpec = new DESedeKeySpec(Des3Key.getBytes()); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte [] dd=cipher.doFinal(Base64.decodeBase64(src.getBytes("UTF-8")));//Base64 解密
		return new String(dd,"UTF-8");
	}
	
    /** 余额查询**/
    public static String ZTSA54Q67() throws Exception{
		String TDATA=""
			+"<TDATA>"
			+"<MERCHANTID>P2P001</MERCHANTID>"
			+"<MERCHANTNAME>P2P测试</MERCHANTNAME>"
			+"<ACNO>6236882299000000435</ACNO>"
			+"<ACNAME>PTP测试7</ACNAME>"
		    +"</TDATA>";
		String XMLPARA=DES3EncryptMode(TDATA);
		return XMLPARA;
    }
    
    public static void main(String args[]) throws Exception {
    	String encrypt=ZTSA54Q67();
    	System.out.println(Des3Key.getBytes().length);
    	System.out.println("加密后的内容：     "+encrypt);
    	System.out.println("解密后的内容：     "+DES3DecryptMode(encrypt));
   }
}
