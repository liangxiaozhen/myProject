package com.ptpl.controller.huishang;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishang.util.GZip;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.StandardSocketFactory;
import com.ptpl.constant.BacthFileRecord_Constant;
import com.ptpl.controller.huishang.model.Trqtres;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;

import net.sf.json.JSONObject;
/**
 * 用户红包转账批量上传（ptpjx）
 * HttpClient文件批量上传
 */
//@Controller
//@RequestMapping("/red")
public class UserRedHttpUpload {
	
	//String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/file/uploadEncrypt";
	//String uri = "http://localhost:8080/ptpAPI/ptproute/file/uploadEncrypt.action";
	//@Resource
	//BacthFileRecordServiceI bacthFileRecordService;
	
	//@RequestMapping("/userRedBatch")
	public synchronized int userHttpUpload(String date,BacthFileRecord bfr,BacthFileRecordServiceI bacthFileRecordService) throws Exception{
		
		HttpClient httpClient = new HttpClient();
		String[] filePath = {bfr.getFilePath()};//文件夹路径  path
		String[] fileName = {bfr.getSendFileName()};//文件名称  name
		Map<String,Object> params = null;
		JSONObject paramsJson=null;
		String pam=null;
		StringPart sp=null;
		FilePart fp = null;
		PostMethod method;
		Part[] parts=null;
		JSONObject map=null;
		String return_code=null;
		int line = 0;
		for(int i=0;i<filePath.length;i++){
			params = new HashMap<String,Object>();
			params.put("coinstCode", /*"800114"*/HSignUtil.COINSTCODE);//机构号  合作单位编号
			params.put("bankCode", /*"30040000"*/HSignUtil.BANKCODE);//银行号  银行代码
			params.put("transDate", date);//交易日期
			params.put("fileName",fileName[i]);//文件名称
			params.put("fileType", BacthFileRecord_Constant.RENTRANSFER);//文件类型
		
			System.out.println("path: "+bfr.getFilePath());
			System.out.println("name: "+bfr.getSendFileName());
			
			File file1 = new File(bfr.getFilePath()+bfr.getSendFileName());
			//String md5encryptionPath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"encryptions_.cer";
			String md5encryptionPath = HSignUtil.ENCRYPTPATH;
			//根据公钥文件读取验签公钥
			String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);
			System.out.println("验签公钥："+encryptionKey4Server);
			System.out.println("file1的path： "+file1.getPath()); //D:\\upload\20170408\3004-TRQT-800114-993281-20170408.TXT
			File bak = new File(file1.getPath()+".bak");
			bak.createNewFile();//创建文件名称
			System.out.println("bak的路径： "+bak.getPath());
			transFormToTmpFile(file1, bak);//file1文件转换成  .bak
			//进行摘要并对摘要进行加密
			String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(bak),encryptionKey4Server);
			params.put("sign", encryptData);
			
			paramsJson = JSONObject.fromObject(params);
			pam = paramsJson.toString();
			sp = new StringPart("parameters",pam);
			sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码
			
			File  file2 = new File(bfr.getFilePath()+bfr.getSendFileName()+".txt");
			//String keypath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"encryption_file_800114.key";
			String keypath = HSignUtil.ENCRYPTION_FILE;
			BufferedReader bre = bre = new BufferedReader(new FileReader(new File(keypath)));
			String keyPath = bre.readLine();
			
			encryptHand(file1,file2,keyPath);//加密处理  file1转换成 .tmp
			
			if(file2.exists()){
				fp = new FilePart("file",file2);
			}
			
			String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(file1)));
			//渠道上传交易文件接口的URL
			method = new PostMethod(/*uri*/HSignUtil.BATCREQHURL);
			parts  = new Part[]{fp,sp};
			method.getParams().setContentCharset("UTF-8");
			method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			httpClient.executeMethod(method);
						
			String responseMaps = method.getResponseBodyAsString();
			map = JSONObject.fromObject(responseMaps);
			return_code = (String)map.get("return_code");
			System.out.println("返回码： "+return_code);
			bfr.setUpResultCode(return_code);//上传文件银行的返回码
			if(return_code.equals("0000")){
				line++;
				System.out.println("文件上传成功");
				bfr.setIsSend((short)1);//是否已发送银行
				bfr.setSendResult((short)1);//发送结果
				bfr.setSubmitTime(new Date());//上传文件时间
			}else if(return_code.equals("0004")){
				 System.out.println("文件已上传，请勿重复上传"); 
			}else if(return_code.equals("0003")){
	            System.out.println("上传文件过大");
	            bfr.setIsSend((short)0);
	            bfr.setSendResult((short)0);//发送结果
	            bfr.setRemark("上传文件过大");
	        }else{
	        	bfr.setIsSend((short)0);
	        	bfr.setSendResult((short)0);//发送结果
	        }
			if(!return_code.equals("0004")){
				bfr.setRemark("红包转账");
				int row = bacthFileRecordService.insert(bfr);
			}
		}
		return line;
	}
    
	private static void transFormToTmpFile(File f1, File bak)throws IOException {
		BufferedReader  br = null;	
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f1.getPath()),"GBK"));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (bak.getPath()),"GBK"));
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
	
	//加密,读取旧的文件的内容加密后写入新的文件
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
    
    //初始化密钥
    public static SecretKey getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128,secureRandom);
            return _generator.generateKey();
        }  catch (Exception e) {
            throw new RuntimeException( "初始化密钥出现异常 " );
        }
    }
    
    /**
     * 将二进制转换成16进制
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
    
}
