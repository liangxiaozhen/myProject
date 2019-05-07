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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Controller;

import com.huishang.file.RSAUtils;
import com.huishang.util.GZip;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.huishang.model.FinTranResult;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
public class MakeLoansController {

	public static final String TMP = "tmp";
	public static String keyPath = "A842aed1aAA39dCb";

	// HttpClient文件批量上传
	public static void testHttpUpload(String path,String name,String date) throws Exception {
		PostMethod method;
		HttpClient httpClient = new HttpClient();
		StringPart sp = null;
		FilePart fp = null;
		String[] filePath = {path};
		String[] fileName = {name};
		String return_code = null;
		JSONObject map = null;
		Map<String, Object> params = null;
		JSONObject paramsJson = null;
		String pam = null;
		Part[] parts = null;
		for (int i = 0; i < filePath.length; i++) {
			params = new HashMap<String, Object>();
			params.put("coinstCode", "800114"); // 合作单位编号
			params.put("bankCode", "30040000"); // 银行代码
			params.put("transDate", date); // 交易日期
			params.put("fileName", fileName[i]); // 文件名称

			File file1 = new File(path+name);
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

			File file2 = new File(path+name+".txt");
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
//			httpClient.executeMethod(method);

			String responseMaps = method.getResponseBodyAsString();
			map = JSONObject.fromObject(responseMaps);
			return_code = (String) map.get("return_code");
			if (return_code.equals("0000")) {
				System.out.println("文件上传成功");
			} else if (return_code.equals("0004")) {
				System.out.println("文件已上传，请勿重复上传");
			} else if (return_code.equals("0003")) {
				System.out.println("上传文件过大");
			}
		}
	}

	// HttpClient文件批量下载
	public static void testHttDownload(String name, String date) throws Exception {
		System.out.println(name);
		String[] fileName = {name};
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
				File newFile = new File("D:/DEMO/client/download/30040000");
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
				BufferedReader bre =  new BufferedReader(new FileReader(new File(keypath)));
				String keyPath = bre.readLine();
				//
				decryptHand(file1, file2,keyPath);
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
	}

	// 文件tar包下载
	public static void httpDownloadforTar(String fileName) throws Exception{
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
	        String dowloadPath ="D:/DEMO/client/download/30040000/"+new SimpleDateFormat("yyyyMMdd").format(new Date());
	        
	        File newFile = new File(dowloadPath);
	        inputstream = method.getResponseBodyAsStream();
	        List<String> fileNames = new ArrayList<String>();
	        GZip.unGzipFiles(new BufferedInputStream(inputstream), dowloadPath, fileNames);
	        
	        File fileTmp = new File(newFile+"/"+fileName+"tmp");
	        FileInputStream in = new FileInputStream(fileTmp);
	        byte[] bs = new byte[in.available()];
	        String fileString = null;
	        try {
	        	in.read(bs);
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally{
				if(in != null){
					in.close();
				}
			}
	        
	        fileString = new String(bs,"GBK");
	        if(fileTmp.exists()){
	        	fileTmp.delete();
	        }
	        
	        String[] fileAndSign = fileString.split("sign:");
	        String fileContent = fileAndSign[0];
	        String sign = fileAndSign[1];
	        String decryptKeyPath = HSignUtil.DECRYPTPATH;
	        String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
	        
	        String signdecrypt = "";
	        try {
	        	signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        File file1 = new File(newFile+"/"+fileName);
	        File file2 = new File(newFile+"/"+fileName+".tmp");
	        if(!newFile.exists() && !newFile.isDirectory()){
	        	//判断文件夹是否存在，不存在就创建
	        	newFile.mkdirs();
	        }
	        
	        String newFileName = file1.getPath();
	        
	        out = new FileOutputStream(newFile+"/"+fileName);
	        out.write(fileContent.getBytes());
	        String keypath = HSignUtil.ENCRYPTION_FILE;
			BufferedReader bre = bre = new BufferedReader(new FileReader(new File(keypath)));
			String keyPath = bre.readLine();
			decryptHand(keyPath,file1,file2);
	        out.flush();
	        out.close();
	        
	        File tmp1 = new File(newFile+"/"+fileName+".bak");
	        System.out.println("tmp1的路径："+tmp1.getPath());
	        tmp1.createNewFile();
	        transFormToTmpFile(file2, tmp1);
	        String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp1)));
	        
	        if(md5.equals(signdecrypt)){
	        	System.out.println("文件验签成功");
	        	System.out.println("摘要内容： "+signdecrypt);
	        }else{
	        	System.out.println("文件验签失败");
	        	System.out.println("sign摘要内容： "+signdecrypt);
	        	System.out.println("系统计算摘要内容： "+md5);
	        }
	        
	        if(file1.exists()){
	        	file1.delete();
	        }
	        if(tmp1.exists()){
	        	tmp1.delete();
	        }
	        if(file2.exists()){
	        	boolean  realNameflag = file2.renameTo(new File(newFileName));
	        	if(realNameflag){
	        		file2.delete();
	        	}
	        }
	        
	        System.out.println("文件下载成功");
		
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
	public static String encryptHand(File file1, File file2, String keyPath) {
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

	public static void decryptHand(File file1, File file2) {
		// String result="";
		String line = "";
		byte[] decryptFrom = null;
		byte[] decryptResult = null;

		try {

			// FileReader reader = new FileReader(file1);
			// FileWriter writer = new FileWriter(file2);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "GBK"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "GBK"));
			// BufferedReader br = new BufferedReader(reader);
			// BufferedWriter bw = new BufferedWriter(writer);
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

	public static final String BAK = ".BAK";

	public static void main(String[] args) throws Exception {
		String batchNumber = String.valueOf(StringUtil.getN(6));
		Calendar c = Calendar.getInstance();
		c.set(2016, 04, 10);
		String date = StringUtil.formatDate(c.getTime(), "yyyyMMdd");
		System.out.println(date);
		File file = null;
		
		//文件输出流  如果文件名是一个目录，会抛出异常
		//3004:徽商银行的银行编号
		//放在同一天的文件夹内
		file = new File("D:"+File.separator+"upload"+File.separator+date);
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory()){       
			file.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(file+File.separator+"3004-14-Z01-FINTRAN-"+batchNumber+"-"+date,true);
		//采用GBK编码
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fos,"GBK"),true);
		out.print(batchNumber);// 批次号  6
		out.print("001");// 业务类别 3
		out.print(date); // 日期8
		out.print("9930040050240500013"); // 扣款账号
		out.print(StringUtil.stringLeftPading(12, "", 2)); // 扣账(本金)金额
		out.print(StringUtil.stringLeftPading(12, "", 2)); // 扣账利息金额
		out.print("9930040050240600045"); // 转入账号
		out.print(156);//币种  人民币
		out.print("0");// 转出方手续费扣款方式
		out.print(StringUtil.stringLeftPading(11, "0", 1)); // 转出方手续费扣款金额
		out.print("0");// 转入方手续费扣款方式
		out.print(StringUtil.stringLeftPading(11, "0", 1)); // 转入方手续费扣款金额
		out.print("GJ1234"); // 标的编号
		out.print("20160509110304319159"); // 投标申请授权码
		out.print(StringUtil.stringLeftPading(1, "", 2)); // 还款结束标志
		out.print(StringUtil.stringLeftPading(100, "", 2)); // 第三方保留域
		out.print(StringUtil.stringRightPading(40, "20170411110208334950", 2)); // 投标申请流水号
		out.print(StringUtil.stringLeftPading(60, "", 2)); // 保留域
		out.flush();
		out.close();
		testHttpUpload((file+File.separator).toString(), "3004-14-Z01-FINTRAN-"+batchNumber+"-"+date, date);
//		testHttDownload("3004-14-Z01-FINTRAN-RESULT-526191-20160510", "20160510");
//		httpDownloadforTar("3004-14-Z01-FINTRAN-RESULT-375879-20160510");
//		File file = new File("D:/DEMO/client/download/30040000/20170421/3004-14-Z01-FINTRAN-RESULT-375879-20160510");
//    	FileInputStream fis = new FileInputStream(file.getPath());
//    	BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
//    	String line = null;
//    	while((line = br.readLine())!=null){
//
//    		FinTranResult ft = readRedResult(line);
//    		if(!"00".equals(ft.getRspcode())){
//    			System.out.println("处理失败");
//    			return;
//    		}
//    	}
//		//关闭流
//		fis.close();
		}

public static FinTranResult readRedResult(String line) throws IOException{
	
	FinTranResult ft = new FinTranResult();
	SimpleDateFormat sf1 =new SimpleDateFormat("yyyyMMdd");
	
	byte[] data = line.getBytes("GBK");
	byte[] BATCH = new byte[6];
	System.arraycopy(data, 0, BATCH, 0, 6);
	String batch = new String(BATCH);
	ft.setBatch(batch);
	System.out.println("批次号（6）："+batch);
	
	byte[] TYPE = new byte[3];
	System.arraycopy(data, 6, TYPE, 0, 3);
	String type = new String(TYPE);
	ft.setType(type);
	System.out.println("业务类别（3）："+type);
	
	byte[] DATE = new byte[8];
	System.arraycopy(data, 9, DATE, 0, 8);
	String date = new String(DATE);
	try {
		ft.setDate(sf1.parse(date));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	System.out.println("日期（8）："+date);
	
	byte[] RSPCODE = new byte[2];
	System.arraycopy(data, 17, RSPCODE, 0, 2);
	String rspcode = new String(RSPCODE);
	ft.setRspcode(rspcode);
	System.out.println("处理响应码（2）："+rspcode);
	
	byte[] CARDNBRO = new byte[19];
	System.arraycopy(data, 19, CARDNBRO, 0, 19);
	String cardnnbro = new String(CARDNBRO);
	ft.setCardnbro(cardnnbro);
	System.out.println("扣款电子账号（19）："+cardnnbro);
	
	byte[] AMOUNT = new byte[12];
	System.arraycopy(data, 38, AMOUNT, 0, 12);
	String str = new String(AMOUNT);
	Double amount = new Double(str.substring(0,10)+"."+str.substring(10));
	ft.setAmount(amount);
	System.out.println("扣账（本金）金额（12）："+amount);
	
	byte[] INTAMT = new byte[12];
	System.arraycopy(data, 50, INTAMT, 0, 12);
	String str1 = new String(INTAMT);
	Double intamt = new Double(str1.substring(0,10)+"."+str1.substring(10));
	ft.setIntamt(Double.valueOf(intamt));
	System.out.println("扣账利息金额（12）："+intamt);
	
	byte[] BILLAMOUNT = new byte[12];
	System.arraycopy(data, 62, BILLAMOUNT, 0, 12);
	String str2 = new String(BILLAMOUNT);
	Double billamount = new Double(str2.substring(0,10)+"."+str2.substring(10));
	ft.setBillamount(Double.valueOf(billamount));
	System.out.println("实际扣账金额（12）："+billamount);
	
	byte[] CARDNBRI = new byte[19];
	System.arraycopy(data, 74, CARDNBRI, 0, 19);
	String cardnbri = new String(CARDNBRI);
	ft.setCardnbri(cardnbri);
	System.out.println("转入电子账号（19）："+cardnbri);
	
	byte[] CURR = new byte[3];
	System.arraycopy(data, 93, CURR, 0, 3);
	String curr = new String(CURR);
	ft.setCurr(curr);
	System.out.println("币种（3）："+curr);
	
	byte[] OUTFEEWAY = new byte[1];
	System.arraycopy(data, 96, OUTFEEWAY, 0, 1);
	String outfeeway = new String(OUTFEEWAY);
	ft.setOutfeeway(Short.valueOf(outfeeway));
	System.out.println("转出方手续费扣款方式（1）："+outfeeway);
	
	byte[] OUTFEEAMT = new byte[11];
	System.arraycopy(data, 97, OUTFEEAMT, 0, 11);
	String outfeeamt = new String(OUTFEEAMT);
	ft.setOutfeeamt(Double.valueOf(outfeeamt));
	System.out.println("转出方手续费扣款金额（11）："+outfeeamt);
	
	byte[] ROUTFEEAMT = new byte[11];
	System.arraycopy(data, 108, ROUTFEEAMT, 0, 11);
	String routfeeamt = new String(ROUTFEEAMT);
	ft.setRoutfeeamt(Double.valueOf(routfeeamt));
	System.out.println("转出方手续费实际扣款金额（11）："+routfeeamt);
	
	byte[] INFEEWAY = new byte[1];
	System.arraycopy(data, 119, INFEEWAY, 0, 1);
	String infeeway = new String(INFEEWAY);
	ft.setInfeeway(Short.valueOf(infeeway));
	System.out.println("转入方手续费扣款方式（1）："+infeeway);
	
	byte[] INFEEAMT = new byte[11];
	System.arraycopy(data, 120, INFEEAMT, 0, 11);
	String infeeamt = new String(INFEEAMT);
	ft.setInfeeamt(Double.valueOf(infeeamt));
	System.out.println("转入方手续费扣款金额（11）："+infeeamt);
	
	byte[] RIFEEAMT = new byte[11];
	System.arraycopy(data, 131, RIFEEAMT, 0, 11);
	String rifeeamt = new String(RIFEEAMT);
	ft.setRifeeamt(Double.valueOf(rifeeamt));
	System.out.println("转出方手续费实际扣款金额（11）："+rifeeamt);
	
	byte[] PRODUCT = new byte[6];
	System.arraycopy(data, 142, PRODUCT, 0, 6);
	String product = new String(PRODUCT);
	ft.setProduct(product);
	System.out.println("标的编号（6）："+product);
	
	byte[] SERIALNO = new byte[40];
	System.arraycopy(data, 148, SERIALNO, 0, 40);
	String serialno = new String(SERIALNO);
	ft.setSerialno(serialno);
	System.out.println("投标申请流水号（40）："+serialno);
	
	byte[] THDRESE = new byte[100];
	System.arraycopy(data, 188, THDRESE, 0, 100);
	String thdrese = new String(THDRESE);
	ft.setThdrese(thdrese);
	System.out.println("第三方保留域（100）："+thdrese);
	
	byte[] RESE = new byte[100];
	System.arraycopy(data, 288, RESE, 0, 100);
	String rese = new String(RESE);
	ft.setRese(rese);
	System.out.println("保留域（100）："+rese);
	System.out.println("--------分割线---------");
    return ft;
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
	public static byte[] encryptByAES(String content, String keyPath) throws NoSuchAlgorithmException {

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

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param keyPath
	 *            解密密钥
	 * @return
	 */
	public static byte[] decryptByAES(byte[] content, String keyPath) throws NoSuchAlgorithmException {

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
	public static SecretKey getKey(String strKey) {
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

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
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

	private static void transFormToTmpFile(File f1, File tmp) throws IOException {
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
    public static void decryptHand(String keyPath, File file1,File file2){
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
