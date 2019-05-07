package com.ptpl.controller.huishang;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.huishang.file.RSAUtils;
import com.huishang.util.GZip;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 徽商银行批量文件下载
 * @ClassName: FileHttpDownload
 * @Description: TODO(徽商银行批量文件下载)
 * @author zhenglm
 * @date 2017年4月24日 下午3:04:10
 *
 */
public class FileHttpDownload {
	
	private BacthFileRecordServiceI bacthFileRecordService = SpringContextHolder.getBean(BacthFileRecordServiceI.class);
	
	private static final String BAK = ".BAK";

	// HttpClient文件批量下载
	public String testHttDownload(String name, String date) throws Exception {
		System.out.println(name);
		String[] fileName = { name };
		String return_code = null;
		JSONObject map = null;
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < fileName.length; i++) {
			params.put("coinstCode", "800114"); // 合作单位编号
			params.put("bankCode", "30040000"); // 银行代码
			params.put("transDate", "20160510"); // 交易日期
			params.put("fileName", fileName[i]);
			JSONObject paramsJson = JSONObject.fromObject(params);
			String pam = paramsJson.toString();
			HttpClient httpClient;
			PostMethod method;
			httpClient = new HttpClient();
			method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptWithSign");
			method.getParams().setContentCharset("GBK");
			method.setParameter("parameters", pam);
			httpClient.executeMethod(method);

			String responseMap = method.getResponseBodyAsString();
			map = JSONObject.fromObject(responseMap);
			FileOutputStream out = null;
			return_code = (String) map.get("return_code");

			String sign = (String) map.get("sign");
			String decryptKeyPath = HSignUtil.DECRYPTPATH; // 拼接解密私钥路径
			String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
			String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign, decryptKey4Server)));

			if (return_code.equals("0000")) {
				String file = (String) map.get("file");
				byte[] _file = file.getBytes("GBK");
				File newFile = new File(HSignUtil.FILEUPLOAD+HSignUtil.FINTRAN+File.separator+StringUtil.formatDate(new Date(), "yyyyMMdd"));
				File file1 = new File(newFile + "/" + fileName[i]);
				File file2 = new File(newFile + "/" + fileName[i] + BAK);
				String newFileName = file1.getPath();
				if (!newFile.exists() && !newFile.isDirectory())// 判断文件夹是否存在，不存在就创建
				{
					newFile.mkdirs();
				}
				out = new FileOutputStream(newFile + "/" + fileName[i]);
				String str = new String(_file, "GBK");
				out.write(str.getBytes());
				String keypath = HSignUtil.ENCRYPTION_FILE;
				BufferedReader bre = new BufferedReader(new FileReader(new File(keypath)));
				String keyPath = bre.readLine();
				//
				decryptHand(file1, file2, keyPath);
				out.flush();
				out.close();
				File tmp = new File(file2.getPath() + ".bak");
				tmp.createNewFile();
				transFormToTmpFile(file2, tmp);
				String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));

				if (md5.equals(signdecrypt)) {
					System.out.println("文件验签成功！");
					System.out.println("摘要内容：" + signdecrypt);
				} else {
					System.out.println("文件验签失败！");
					System.out.println("sign摘要内容：" + signdecrypt);
					System.out.println("系统计算摘要内容：" + md5);
				}
				if (file1.exists()) {
					file1.delete();
				}
				if (file2.exists()) {
					file2.renameTo(new File(newFileName));
				}

				System.out.println("文件下载成功");
			} else if (return_code.equals("0002")) {
				System.out.println("下载请求参数无法识别");
			}
		}
		return return_code;
	}

	// 文件tar包下载
	public String httpDownloadforTar(String fileName, BacthFileRecord bfr) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		HttpClient httpClient = null;
		PostMethod method = null;

		params.put("coinstCode", "800114");
		params.put("bankCode", "30040000");
		params.put("transDate", "20160510");
		params.put("fileName", fileName);
		JSONObject paramsJson = JSONObject.fromObject(params);
		String pam = paramsJson.toString();
		httpClient = new HttpClient();
		method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptforTarWithSign");
		method.getParams().setContentCharset("GBK");
		method.setParameter("parameters", pam);

		httpClient.executeMethod(method);
		InputStream inputstream = null;
		FileOutputStream out = null;
		String dowloadPath = HSignUtil.FILEUPLOAD + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date());

		File newFile = new File(dowloadPath);
		inputstream = method.getResponseBodyAsStream();
		List<String> fileNames = new ArrayList<String>();
		GZip.unGzipFiles(new BufferedInputStream(inputstream), dowloadPath, fileNames);

		File fileTmp = new File(newFile + "/" + fileName + "tmp");
		FileInputStream in = new FileInputStream(fileTmp);
		byte[] bs = new byte[in.available()];
		String fileString = null;
		try {
			in.read(bs);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}

		fileString = new String(bs, "GBK");
		if (fileTmp.exists()) {
			fileTmp.delete();
		}

		String[] fileAndSign = fileString.split("sign:");
		String fileContent = fileAndSign[0];
		String sign = fileAndSign[1];
		String decryptKeyPath = HSignUtil.DECRYPTPATH;
		String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);

		String signdecrypt = "";
		try {
			signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign, decryptKey4Server)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file1 = new File(newFile + "/" + fileName);
		File file2 = new File(newFile + "/" + fileName + ".tmp");
		if (!newFile.exists() && !newFile.isDirectory()) {
			// 判断文件夹是否存在，不存在就创建
			newFile.mkdirs();
		}

		String newFileName = file1.getPath();

		out = new FileOutputStream(newFile + "/" + fileName);
		out.write(fileContent.getBytes());
		String keypath = HSignUtil.ENCRYPTION_FILE;
		BufferedReader bre = bre = new BufferedReader(new FileReader(new File(keypath)));
		String keyPath = bre.readLine();
		decryptHand(keyPath, file1, file2);
		out.flush();
		out.close();

		File tmp1 = new File(newFile + "/" + fileName + ".bak");
		System.out.println("tmp1的路径：" + tmp1.getPath());
		tmp1.createNewFile();
		transFormToTmpFile(file2, tmp1);
		String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp1)));

		if (md5.equals(signdecrypt)) {
			System.out.println("文件验签成功");
			System.out.println("摘要内容： " + signdecrypt);
			
        	//更新批量文件记录表
        	bfr.setDealTime(new Date());//处理文件时间
        	bfr.setIsDealResult((short)1);//是否已处理结果文件
        	bfr.setDealResult((short)1);//处理结果
        	bacthFileRecordService.update(bfr);
        	
		} else {
			System.out.println("文件验签失败");
			System.out.println("sign摘要内容： " + signdecrypt);
			System.out.println("系统计算摘要内容： " + md5);
		}

		if (file1.exists()) {
			file1.delete();
		}
		if (tmp1.exists()) {
			tmp1.delete();
		}
		if (file2.exists()) {
			boolean realNameflag = file2.renameTo(new File(newFileName));
			if (realNameflag) {
				file2.delete();
			}
		}

		System.out.println("文件下载成功");
		return file1.getPath();
	}

	private void decryptHand(File file1, File file2, String keyPath) {
		String line = "";
		byte[] decryptFrom = null;
		byte[] decryptResult = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "GBK"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "GBK"));
			if ((line = br.readLine()) != null) {
				// 解密
				String result = "";
				decryptFrom = parseHexStr2Byte(result + line);
				decryptResult = decryptByAES(decryptFrom, keyPath);// 逐行解密
				result = new String(decryptResult, "GBK");
				bw.write(result);
			}
			while ((line = br.readLine()) != null) {
				// 解密
				String result = "";
				decryptFrom = parseHexStr2Byte(result + line);
				decryptResult = decryptByAES(decryptFrom, keyPath);// 逐行解密
				result = new String(decryptResult, "GBK");
				bw.newLine();
				bw.write(result);
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param keyPath
	 *            解密密钥
	 * @return
	 */
	private byte[] decryptByAES(byte[] content, String keyPath) throws NoSuchAlgorithmException {

		SecretKeySpec key = null;
		try {
			SecretKey secretKey = getKey(keyPath);
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
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
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

	public void decryptHand(String keyPath, File file1, File file2) {
		String line = "";
		byte[] decryptFrom = null;
		byte[] decryptResult = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "GBK"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "GBK"));
			if ((line = br.readLine()) != null) {
				// 解密
				String result = "";
				decryptFrom = parseHexStr2Byte(result + line);
				decryptResult = decryptByAES(decryptFrom, keyPath);// 逐行解密
				result = new String(decryptResult, "GBK");
				bw.write(result);
			}
			while ((line = br.readLine()) != null) {
				// 解密
				String result = "";
				decryptFrom = parseHexStr2Byte(result + line);
				decryptResult = decryptByAES(decryptFrom, keyPath);// 逐行解密
				result = new String(decryptResult, "GBK");
				bw.newLine();
				bw.write(result);
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
