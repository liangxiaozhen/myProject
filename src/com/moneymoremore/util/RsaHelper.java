package com.moneymoremore.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * RSA 签名、验证、加密、解密帮助类
 * 
 * @author sam
 * 
 */
public class RsaHelper
{
	//私匙
	public static final String privateString = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI83tngBn+sn4WaUuJhINWp5pMHPyCJZ35Ca+9W3UmwhTSRUyyzsEMg9P5V+CZXl7C0HZ8CLlVDcAySHWic7p7PVPdLjib7AMM1BSRUW7n+qBqH+130LCBXGkxP5D8t4rXZSIF/xpzbPIkRJRaVXPeUSMR3amcrHFji8N8SrP5jzAgMBAAECgYBfZrVd63cjpkU3mAn/JtquOqnNkSO3/jY2lYb8LknxTZ2dff55xibHvEUGyBaKi8ZvmGc8k767RlOxmZgsWkizOdnFh7k8nlBawXCt91dqpeGTFS48FVgBaMOqA/VXB/kbh15JCqeCKBTdEvlN6PbOt9J87RxgKp7rIjM4Dd/1gQJBANVSllqOP6ffMs6bbuMn7iqc+oKY7GHZgUn/RRSIFwqvZBSJPTWRdaUMraig4wvmqH3uKdloGRKLym1Crf8tQPkCQQCr3qrpKOHNCtp4/cdeeysyr7xGVaNzoZG23Alk+sNRC/vQUjgfBQaMyxvD7lvrJJ7GQ0zz3QiXsfGz+BPl3hBLAkAk4QxBR4pVuU6NTW3UM5kHIPBmF432NKYXZtxIemaRYFUK/9bnJeiSyihTmMznx86lRsFRZp+/Bw/N59Mw5F/xAkBV+GWph4ebVwh9aQQnIJk1G26kCAZgurr2qw4P8tdsXQAy7k2gRpVGK/rRgRN9ha3eIqAFg9TefGYYzci2POQNAkAardV37v9k704oSbJI2ZtYUmvgxAZ29hnx/EWShLjndWT+oQamAC/RYXpSdqkQS/eVLYSQw8vpn7Omo5JCt9la";

	//公匙
	public static final String publicString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPN7Z4AZ/rJ+FmlLiYSDVqeaTBz8giWd+QmvvVt1JsIU0kVMss7BDIPT+VfgmV5ewtB2fAi5VQ3AMkh1onO6ez1T3S44m+wDDNQUkVFu5/qgah/td9CwgVxpMT+Q/LeK12UiBf8ac2zyJESUWlVz3lEjEd2pnKxxY4vDfEqz+Y8wIDAQAB";
	
	// 签名对象
	private Signature sign;
	private static final RsaHelper rsaHelper = new RsaHelper();
	
	
	private String pubkey;
	private String prikey_pkcs8;
	private String prikey_openssl;
	
	private RsaHelper()
	{
		try
		{
			sign = Signature.getInstance("SHA1withRSA");
		}
		catch (NoSuchAlgorithmException nsa)
		{
			System.out.println("" + nsa.getMessage());
		}
	}
	
	public static RsaHelper getInstance()
	{
		return rsaHelper;
	}
	
	private PrivateKey getPrivateKey(String privateKeyStr) throws Exception
	{
		try
		{
			byte[] privateKeyBytes = b64decode(privateKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			return keyFactory.generatePrivate(privateKeySpec);
		}
		catch (InvalidKeySpecException e)
		{
			System.out.println("Invalid Key Specs. Not valid Key files." + e.getCause());
			return null;
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("There is no such algorithm. Please check the JDK ver." + e.getCause());
			return null;
		}
	}
	
	private PublicKey getPublicKey(String publicKeyStr)  throws Exception
	{
		try
		{
			byte[] publicKeyBytes = b64decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			return keyFactory.generatePublic(publicKeySpec);
			
		}
		catch (InvalidKeySpecException e)
		{
			System.out.println("Invalid Key Specs. Not valid Key files." + e.getCause());
			return null;
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("There is no such algorithm. Please check the JDK ver." + e.getCause());
			return null;
		}
	}
	
	/**
	 * RSA 数据签名
	 * 
	 * @param toBeSigned
	 *            (待签名的原文)
	 * @param priKey
	 *            (RSA私钥）
	 * @return （返回RSA签名后的数据签名数据base64编码）
	 */
	public String signData(String toBeSigned, String priKey)
	{
		
		try
		{
			PrivateKey privateKey = getPrivateKey(priKey);
			byte[] signByte = toBeSigned.getBytes("utf-8");
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initSign(privateKey);
			rsa.update(signByte);
			return b64encode(rsa.sign());
		}
		catch (NoSuchAlgorithmException ex)
		{
			System.out.println(ex);
		}
		catch (InvalidKeyException in)
		{
			System.out.println("Invalid Key file.Please check the key file path" + in.getCause());
		}
		catch (Exception se)
		{
			System.out.println(se);
		}
		return null;
	}
	
	/**
	 * RSA 数据签名验证
	 * 
	 * @param signature
	 *            （RSA签名数据（base64编码）
	 * @param data
	 *            （待验证的数据原文）
	 * @param pubKey
	 *            （RSA公钥数据）
	 * @return 返回验证结果（TRUE:验证成功；FALSE:验证失败）
	 */
	public boolean verifySignature(String signature, String data, String pubKey)
	{
		try
		{
			byte[] signByte = b64decode(signature);
			byte[] dataByte = data.getBytes("utf-8");
			PublicKey publicKey = getPublicKey(pubKey);
			sign.initVerify(publicKey);
			sign.update(dataByte);
			return sign.verify(signByte);
		}
		catch (SignatureException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * base64编码
	 * 
	 * @param data
	 * @return
	 */
	public static String b64encode (byte[] data) throws Exception
	{
		return new BASE64Encoder().encodeBuffer(data);
	}
	
	/**
	 * base64解码
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] b64decode(String data) throws Exception
	{
			return new BASE64Decoder().decodeBuffer(data);
	}
	
	/**
	 * RSA数据加密
	 * 
	 * @param data
	 *            （需要加密的数据）
	 * @param pubKey
	 *            （RSA公钥）
	 * @return 返回加密后的密文（BASE64编码）
	 */
	public String encryptData(String data, String pubKey)
	{
		try
		{
			byte[] dataByte = data.getBytes("utf-8");
			PublicKey publicKey = getPublicKey(pubKey);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			byte[] dataReturn = new byte[]{};
			// StringBuilder sb = new StringBuilder();
			for (int i = 0; i < dataByte.length; i += 100)
			{
				byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(dataByte, i, i + 100));
				// sb.append(new String(doFinal));
				dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
			}

			// return b64encode(cipher.doFinal(dataByte));
			return b64encode(dataReturn);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * RSA数据解密
	 * 
	 * @param encryptedData
	 *            （需要解密的数据base64编码数据）
	 * @param priKey
	 *            （RSA的私钥）
	 * @return 返回解密后的原始明文
	 */
	public String decryptData(String encryptedData, String priKey)
	{
		try
		{
			byte[] encryData = b64decode(encryptedData);
			PrivateKey privateKey = getPrivateKey(priKey);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < encryData.length; i += 128)
			{
				byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(encryData, i, i + 128));
				sb.append(new String(doFinal, "utf-8"));
			}
			
			return sb.toString();
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 得到私钥字符串（经过base64编码）
	 * 
	 * @return
	 */
	public static String getPriKeyString(PrivateKey key, String EncodeType) throws Exception
	{
		// byte[] keyBytes = key.getEncoded();
		// System.out.println("length:"+keyBytes.length);
		byte[] keyBytes = EncodeKey(key, EncodeType);
		String s = (new BASE64Encoder()).encode(keyBytes);
		return s;
	}
	
	/**
	 * 得到公钥字符串（经过base64编码）
	 * 
	 * @return
	 */
	public static String getPubKeyString(PublicKey key) throws Exception
	{
		byte[] keyBytes = key.getEncoded();
		// System.out.println("length:"+keyBytes.length);
		String s = (new BASE64Encoder()).encode(keyBytes);
		return s;
	}
	
	public static byte[] EncodeKey(PrivateKey pKey, String EncodeType) throws Exception
	{
		if (EncodeType.equals("pkcs8"))
		{
			return pKey.getEncoded();
		}
		else if (EncodeType.equals("openssl"))
		{
			RSAPrivateCrtKey rsaKey = (RSAPrivateCrtKey) pKey;
			RSAPrivateKeyStructure rsastruct = new RSAPrivateKeyStructure(rsaKey.getModulus(), rsaKey.getPublicExponent(), rsaKey.getPrivateExponent(), rsaKey.getPrimeP(), rsaKey.getPrimeQ(), rsaKey.getPrimeExponentP(), rsaKey.getPrimeExponentQ(), rsaKey.getCrtCoefficient());
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			DEROutputStream deroutputstream = new DEROutputStream(bytearrayoutputstream);
			deroutputstream.writeObject(rsastruct.getDERObject());
			deroutputstream.close();
			return bytearrayoutputstream.toByteArray();
		}
		return null;
	}
	
	/**
	 * 生成密钥 自动产生RSA1024位密钥；并保持到文件里 rsaPublicKeyFilePath 公钥的文件路径名，例如：d:\publickey.txt rsaPrivateKeyFilePath 公钥的文件路径名，例如：d:\privatekey.txt
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void getAutoCreateRSA() throws NoSuchAlgorithmException, IOException
	{
		try
		{
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.genKeyPair();
			PublicKey puk = kp.getPublic();
			PrivateKey prk = kp.getPrivate();
			
			//System.out.println(prk.getFormat());
			
			pubkey = getPubKeyString(puk);
			prikey_pkcs8 = getPriKeyString(prk, "pkcs8");
			prikey_openssl = getPriKeyString(prk, "openssl");
			
			//System.out.println("pubkey:" + pubkey);
			//System.out.println("prikey_pkcs8:" + prikey_pkcs8);
			//System.out.println("prikey_openssl:" + prikey_openssl);
			
			// System.out.println(prikey.length());
			
			// X509EncodedKeySpec ksp = new X509EncodedKeySpec(puk.getEncoded());
			// FileOutputStream fos = new FileOutputStream("F:\\test-public.key");
			// fos.write(ksp.getEncoded());
			// fos.close();
			//			
			// PKCS8EncodedKeySpec kpr = new PKCS8EncodedKeySpec(prk.getEncoded());
			// fos = new FileOutputStream("F:\\test-private.key");
			// fos.write(kpr.getEncoded());
			// fos.close();
			
			// String rsaPublicKeyFilePath="F:\\java-pub.pem";
			// FileOutputStream pufos = new FileOutputStream(rsaPublicKeyFilePath);
			// ObjectOutputStream puoos = new ObjectOutputStream(pufos);
			// puoos.writeObject(puk);
			// puoos.flush();
			// puoos.close();
			// String rsaPrivateKeyFilePath="F:\\java-pri.pem";
			// FileOutputStream prfos = new FileOutputStream(rsaPrivateKeyFilePath);
			// ObjectOutputStream proos = new ObjectOutputStream(prfos);
			// proos.writeObject(prk);
			// proos.flush();
			// proos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		try
		{
			//RsaHelper rsa = RsaHelper.getInstance();
			//rsa.getAutoCreateRSA();
			
//			String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMBCwTnO1scMGM4YIxhf/oXeE6W1"+
//							"6jJYsA1skxHzbVgPIb/Qv4Fu6UVXydjKJBPVvMH5407Jy9pSbMlWwXCsTJpCBdTeRD4XcbiQeVLH"+
//							"DFQON8QdziH7H1zG5ge6YpIBVPYBIuY6iynl/5TPD/20vb3skrqZOc9Q0eqW5G5xtSgFAgMBAAEC"+
//							"gYAix/DM1G5mc/nIzvUKY9NXxGUphU9o7EJtK0cv6CnP1Gz2ln0OnVH2CXuqjGcab4BGVz6X8km+"+
//							"pUqo4dj82S7CKKjFkcVfbdLpsnlfWFLCPqw7wJKEOeFIfTVnSTOGZTRFc8QEVg0+a9okRoqeqQKC"+
//							"Zn5kWIgp3OcAIdMOetrvwQJBAPSOjIKfiqo5vMtOUoGRUlunDqAhye7aAC0m1USgPKj6gqEs/oPq"+
//							"HB1necQANHj5l2a20ZQikflr7qi3J8/G3I8CQQDJQcWKC/kk9oaJVXSpR01Tl2S3wEcygArBLU7R"+
//							"xMKsa36aJF6vr3emNPNMYY1jICWjNSe/YR7UcXLuG6ZgFiQrAkAocG3xp5oRXezHHZNtE2+v8ibr"+
//							"+cpfcbL3xGUdrPV657m0FzGa9JpjjlnHPFVw76zGclKjkTfcK6nSQj8WD4cnAkBk23QucT+jOXRE"+
//							"oLG9H4Ft8cHEoDRN54L8OkN0tmFE3P3uK2nUK2APyBthXMXpNjQGbV4E95vmpRAOqYagQphPAkAY"+
//							"Txc8LB407H6JktpNj0tGpXNeUdfIZWUybcIiHfc1Wc0rTUaUv+tBe2JCv2WGGmanRune+mkz+qZi"+
//							"UuXawXs7";
//			String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAQsE5ztbHDBjOGCMYX/6F3hOlteoyWLANbJMR"+
//							"821YDyG/0L+BbulFV8nYyiQT1bzB+eNOycvaUmzJVsFwrEyaQgXU3kQ+F3G4kHlSxwxUDjfEHc4h"+
//							"+x9cxuYHumKSAVT2ASLmOosp5f+Uzw/9tL297JK6mTnPUNHqluRucbUoBQIDAQAB";
//			String dataStr = "[{\"LoanOutMoneymoremore\":\"m21\",\"LoanInMoneymoremore\":\"m22\",\"OrderNo\":\"000003\",\"Amount\":\"0.01\",\"Fee\":\"0.01\",\"TransferName\":\"借款\",\"FeeName\":\"平台收手续费\",\"Remark\":\"remark1\"},{\"LoanOutMoneymoremore\":\"m21\",\"LoanInMoneymoremore\":\"m22\",\"OrderNo\":\"000004\",\"Amount\":\"0.02\",\"Fee\":\"0.02\",\"TransferName\":\"借款2\",\"FeeName\":\"平台收手续费2\",\"Remark\":\"remark2中文\"}]p12http://localhost:8080/main/loan/test/testreturn.jsphttp://localhost:8080/main/loan/test/testnotify.jsp";
			// 签名
//			String sign = rsaHelper.signData(dataStr, priKey);
//			System.out.println(sign);
//			System.out.println(dataStr);
//			System.out.println(pubKey);
			// 验签
			//boolean b = rsaHelper.verifySignature(sign, dataStr, pubKey);
			//System.out.println(pubKey);
//			if (b)
//				System.out.println("verify ok");
//			else
//				System.out.println("verify error");
			// // 加密
			// String encryptData = rsa.encryptData(dataStr, pubKey);
			// System.out.println(encryptData);
			// // 解密
			// // String
			// encryptData = "TE/XrpAzcl09Yz6MaEKEvImfA0aJCHFaSqAjvYJEg9vS5N9Gm7FPVZdHzcHV3Zm+1bjzg1jGCXwCKxcBGj64qB9SBa012EQnwi3DQPFtNN081DzKCfFtDMj9pvs6haVZiiA3Ij0gxkDJTYKtMvaYHrT+/ZO5oZOTnX6MWqXuHhU=";
			// String decryptData = rsa.decryptData(encryptData, priKey);
			// System.out.println(decryptData);
			//				
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getPubkey()
	{
		return pubkey;
	}
	
	public void setPubkey(String pubkey)
	{
		this.pubkey = pubkey;
	}

	public String getPrikey_pkcs8()
	{
		return prikey_pkcs8;
	}

	public void setPrikey_pkcs8(String prikeyPkcs8)
	{
		prikey_pkcs8 = prikeyPkcs8;
	}

	public String getPrikey_openssl()
	{
		return prikey_openssl;
	}

	public void setPrikey_openssl(String prikeyOpenssl)
	{
		prikey_openssl = prikeyOpenssl;
	}
	
}
