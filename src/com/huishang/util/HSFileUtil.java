package com.huishang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import net.sf.json.JSONObject;

/**
 * 徽商 文件工具类
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年5月16日 上午9:32:58 
 *
 */
public class HSFileUtil {
	/**
	 * 文件下载
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月13日 下午4:56:21
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            下载文件名称
	 * @param dataStr
	 *            日期 YYYYMMDD
	 * @return
	 * @throws Exception
	 */
	public static boolean downFile(String filePath, String fileName, String dataStr) throws Exception {
		boolean flag = false;
		String[] fileNames = { fileName };
		String return_code = null;
		JSONObject map = null;
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < fileNames.length; i++) {
			params.put("coinstCode", HSignUtil.COINSTCODE);
			params.put("bankCode", HSignUtil.BANKCODE);
			params.put("transDate", dataStr);
			params.put("fileName", fileNames[i]);
			JSONObject paramsJson = JSONObject.fromObject(params);
			String pam = paramsJson.toString();
			HttpClient httpClient;
			PostMethod method;
			httpClient = new HttpClient();
			method = new PostMethod(HSignUtil.BATCHRESURL);
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
				File newFile = new File(filePath);
				File file1 = new File(newFile + "/" + fileNames[i]);
				File file2 = new File(newFile + "/" + fileNames[i] + ".BAK");
				String newFileName = file1.getPath();
				if (!newFile.exists() && !newFile.isDirectory())// 判断文件夹是否存在，不存在就创建
				{
					newFile.mkdirs();
				}
				out = new FileOutputStream(newFile + "/" + fileNames[i]);
				String str = new String(_file, "GBK");
				out.write(str.getBytes());
				File fi = new File(HSignUtil.ENCRYPTION_FILE);
				FileReader fre = new FileReader(fi);
				BufferedReader bre = new BufferedReader(fre);
				String privateKey = bre.readLine();
				HSignUtil.decryptHand(file1, file2, privateKey);
				out.flush();
				out.close();
				File tmp = new File(file2.getPath() + ".bak");
				tmp.createNewFile();
				HSignUtil.transFormToTmpFile(file2, tmp);
				String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));

				if (!md5.equals(signdecrypt)) {
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
				flag = true;
			} else if (return_code.equals("0002")) {
				System.out.println("下载请求参数无法识别");
			}
		}
		return flag;
	}

}
