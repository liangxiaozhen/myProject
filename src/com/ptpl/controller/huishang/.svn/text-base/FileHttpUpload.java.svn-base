package com.ptpl.controller.huishang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import com.huishang.file.RSAUtils;
import com.huishang.util.HSignUtil;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;

import net.sf.json.JSONObject;

/**
 * 徽商银行批量文件上传
 * @ClassName: FileHttpUpload
 * @Description: TODO(徽商银行批量文件上传)
 * @author zhenglm
 * @date 2017年4月24日 下午3:02:03
 *
 */
public class FileHttpUpload {

	// HttpClient文件批量上传
	public String testHttpUpload(String path, String name, String date, BacthFileRecordServiceI bacthFileRecordService, BacthFileRecord bfr) throws Exception {
		PostMethod method;
		HttpClient httpClient = new HttpClient();
		StringPart sp = null;
		FilePart fp = null;
		String[] filePath = { path };
		String[] fileName = { name };
		String return_code = null;
		JSONObject map = null;
		Map<String, Object> params = null;
		JSONObject paramsJson = null;
		String pam = null;
		Part[] parts = null;
		for (int i = 0; i < filePath.length; i++) {
			params = new HashMap<String, Object>();
			params.put("coinstCode", HSignUtil.COINSTCODE); // 合作单位编号
			params.put("bankCode", "30040000"); // 银行代码
			params.put("transDate", date); // 交易日期
			params.put("fileName", fileName[i]); // 文件名称

			File file1 = new File(path + name);
			String md5encryptionPath = HSignUtil.ENCRYPTPATH;
			String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath); // 获取加密公钥字符串
			File tmp = new File(file1.getPath() + ".bak");
			tmp.createNewFile();
			transFormToTmpFile(file1, tmp);
			String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(tmp), encryptionKey4Server);// 进行摘要并对摘要进行加密
			params.put("sign", encryptData);

			paramsJson = JSONObject.fromObject(params);
			pam = paramsJson.toString();
			sp = new StringPart("parameters", pam);
			sp.setCharSet("GBK");// 这里要设置字符编码，不然会乱码

			File file2 = new File(path + name + ".txt");
			String keypath = HSignUtil.ENCRYPTION_FILE;
			BufferedReader bre = new BufferedReader(new FileReader(new File(keypath)));
			String keyPath = bre.readLine();
			encryptHand(file1, file2, keyPath);// 加密处理
			if (file2.exists()) {
				fp = new FilePart("file", file2);
			}
			method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbdev/file/uploadEncrypt");
			parts = new Part[] { fp, sp };
			method.getParams().setContentCharset("UTF-8");
			method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			httpClient.executeMethod(method);

			String responseMaps = method.getResponseBodyAsString();
			map = JSONObject.fromObject(responseMaps);
			return_code = (String) map.get("return_code");
			bfr.setSubmitTime(new Date());//上传文件时间
			if (return_code.equals("0000")) {
				bfr.setIsSend((short) 1);//是否已发送银行
				bfr.setSendResult((short) 1);//发送结果
				System.out.println("文件上传成功");
			} else if (return_code.equals("0004")) {
				bfr.setIsSend((short) 0);//是否已发送银行
				bfr.setSendResult((short) 0);//发送结果
				System.out.println("文件已上传，请勿重复上传");
			} else if (return_code.equals("0003")) {
				bfr.setIsSend((short) 0);//是否已发送银行
				bfr.setSendResult((short) 0);//发送结果
				System.out.println("上传文件过大");
			}
			if(!return_code.equals("0004")){
				bacthFileRecordService.insert(bfr);
			}
		}
		return return_code;
	}

	/**
	 * 加密,读取旧的文件的内容加密后写入新的文件
	 * 
	 * @param file1
	 * @param file2
	 * @param keyPath
	 *            密钥路径
	 * @return 返回文件内容
	 */
	private String encryptHand(File file1, File file2, String keyPath) {
		String result = "";
		String line = "";
		byte[] bt;
		String encryptResultStr = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "GBK"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "GBK"));
			if ((line = br.readLine()) != null) {
				bt = encryptByAES(result + line, keyPath);// 逐行加密
				encryptResultStr = parseByte2HexStr(bt);
				bw.write(encryptResultStr);
			}
			while ((line = br.readLine()) != null) {
				bt = encryptByAES(result + line, keyPath);// 逐行加密
				encryptResultStr = parseByte2HexStr(bt);
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
	 * @param content
	 *            需要加密的内容
	 * @param keyPath
	 *            加密密码
	 * @return
	 */
	private byte[] encryptByAES(String content, String keyPath) throws NoSuchAlgorithmException {

		SecretKeySpec key = null;
		try {
			SecretKey secretKey = getKey(keyPath);
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

	// 初始化密钥
	private SecretKey getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException(" 初始化密钥出现异常 ");
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private String parseByte2HexStr(byte buf[]) {
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

	private void transFormToTmpFile(File f1, File tmp) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f1.getPath()), "GBK"));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp.getPath()), "GBK"));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!("").equals(line)) {
					bw.write(line);
					bw.flush();
				}

			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
}
