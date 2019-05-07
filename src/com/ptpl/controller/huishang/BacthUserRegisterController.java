package com.ptpl.controller.huishang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import com.huishang.util.AesUtil;
import com.huishang.util.GZip;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

 
/**
 * 
* @ClassName: BacthUserRegisterController 
* @Description: TODO(批量开户) 
* @author cjm 
* @date 2017年4月10日 上午11:32:22 
*
 */
@Controller
@RequestMapping("/huishang/bacthUserRegister")
public class BacthUserRegisterController extends BaseController{
	
    public static final String TMP="tmp";
    
    public static final String BAK=".BAK";
    
    public static  String keyPath="A842aed1aAA39dCb";



	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;

	@RequestMapping("/test")
	public String test (HttpServletRequest request ,HttpServletResponse response){
		
		UserBaseAccountInfo accountInfo = new UserBaseAccountInfo();
		accountInfo.setId(new BigDecimal(451789322));
		accountInfo.setCertificationtype("01");
		accountInfo.setRealname("马龙");
		accountInfo.setMobilephone("13256925985");
		accountInfo.setAccounttype((short)1);
		accountInfo.setCertificationnumber("370902198707122418");
		accountInfo.setSex((short)1);
		
		UserBaseAccountInfo accountInfo1 = new UserBaseAccountInfo();
		accountInfo1.setId(new BigDecimal(451789321));
		accountInfo1.setCertificationtype("01");
		accountInfo1.setRealname("姜志娟");
		accountInfo1.setMobilephone("13256925987");
		accountInfo1.setAccounttype((short)1);
		accountInfo1.setCertificationnumber("341181198505146849");
		accountInfo1.setSex((short)2);		  
		
		UserBaseAccountInfo accountInfo3 = new UserBaseAccountInfo();
		accountInfo3.setId(new BigDecimal(451789323));
		accountInfo3.setCertificationtype("01");
		accountInfo3.setRealname("施雯");
		accountInfo3.setMobilephone("13256925982");
 		accountInfo3.setAccounttype((short)1);
		accountInfo3.setCertificationnumber("320405198907182223");
		accountInfo3.setSex((short)2);
		 
		
		List<UserBaseAccountInfo>  userBaseAccountInfos =  new ArrayList<>();
		userBaseAccountInfos.add(accountInfo);
		userBaseAccountInfos.add(accountInfo1);
		userBaseAccountInfos.add(accountInfo3);
		
		test1(userBaseAccountInfos);
		
		return null;
	}
	
	
	public  String test1(List<UserBaseAccountInfo>  userBaseAccountInfos){
 		Date date = new Date();
		/*文件命名格式：
		$$$$-APPZX????-XXXXXX-YYYYMMDD 
		($$$$为银行编号, XXXXXX为批次号，????为产品号， YYYYMMDD为8位日期)*/
 		String bankCodeNo = "3004-";
 		String productNo  = "APPZX0002-";
		String batchNo 	  = "000007-";
		String TRXDATE 	  = StringUtil.formatDate(StringUtil.getDateByString("2016-05-10", "yyyy-MM-dd"), "yyyyMMdd");
		File file = new File(HSignUtil.FILEUPLOAD + HSignUtil.BATCHUSERREGISTERFILEUPLOAD);
 		if(!file.exists()){
			file.mkdirs();
		}

 		String newFileName  = bankCodeNo+productNo + batchNo + TRXDATE;
 		FileOutputStream fileOutputStream = null;
  		try {
			fileOutputStream = new FileOutputStream(new File(file,newFileName));
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		}
		 
  		PrintWriter out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			for(UserBaseAccountInfo userBaseAccountInfo: userBaseAccountInfos){
 				out.print(userBaseAccountInfo.getCertificationnumber());//1	证件编号*	IDNO	A	18	1	18	M	企业开户上送组织机构代码/社会信用号
				out.print(userBaseAccountInfo.getCertificationtype());//2	证件类型*	IDTYPE	A	2	19	20	M	"01-身份证18位
				out.print(isChinese(userBaseAccountInfo.getRealname(), 60));//3	中文姓名*	NAME	A	60	21	80	M	企业开户时上送企业名称
				out.print(userBaseAccountInfo.getSex().toString());//4	性别	GEN	A	1	81	81	C	"1-男,2-女
				out.print(isChinese(userBaseAccountInfo.getMobilephone().toString(), 12));//5	手机号码	MOPHONE	A	12	82	93	M	个人账户必填；
 				out.print(userBaseAccountInfo.getAccounttype().toString().equals("1") ? "0" : "1");//6	账户类型	ACCTYPE	A	1	94	94		"0：个人账户；
				out.print(isChinese(userBaseAccountInfo.getEmail() == null ?"":userBaseAccountInfo.getEmail(), 40));//7	E-mail地址	EMAIL	A	40	95	134	C
				out.print(isChinese(userBaseAccountInfo.getId().toString(), 60));//8	请求方用户ID	APPID	A	60	135	194	M	发放方保证唯一
				out.print(isChinese("", 9));//9	组织机构代码	INSTID	A	9	195	203	C
				out.print(isChinese("", 30));//10	税务登记号	TAXID	A	30	204	233	C
				out.print(isChinese("", 20));//11	渠道推荐码	ADNO	A	20	234	253	
				out.print(isChinese("", 1));//12	账户类型	ACC-TYPE	N	1	254	254	M
				out.print(isChinese("", 2));//13	基金公司代码	FUCOMCODE	A	2	255	256	C
				out.print(isChinese("", 100));//14	请求方保留信息	INFO	A	100	257	356
				out.print(isChinese("", 32));//15	对公账户号	CACCOUNT	A	32	357	388	C	当企业开户时必输入，作为企业电子账户的绑定账号
				out.print(isChinese("", 18));//16	营业执照编号1	BUSID	A	18	389	406	C
				out.print(isChinese("", 27));//17	保留域	REVERS	A	27	407	433 C
				
   				out.print("\n");
 			}
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				
			}
 		}
 		return null;
	}
	
	
	
	//HttpClient文件批量上传
    public static void testHttpUpload() throws Exception
    {
        PostMethod method;
        HttpClient httpClient = new HttpClient();
        StringPart sp = null;
        FilePart fp = null;
        String[] filePath = {"D:/batchfile/repayment/20160510/"};
        String[] fileName = {"3004-14-Z01-REPTRAN-000001-20160510"};
        String return_code = null;
        JSONObject map = null;
        Map<String,Object>params = null;
        JSONObject paramsJson = null;
        String pam = null;
        Part[] parts = null;
        for(int i=0;i<filePath.length;i++)
        {
            params = new HashMap<String,Object>();
            params.put("coinstCode", HSignUtil.COINSTCODE);
            params.put("bankCode",HSignUtil.BANKCODE);
            params.put("transDate", "20160510");
            params.put("fileName",fileName[i]);
            
            File file1 = new File("D:/batchfile/repayment/20160510/3004-14-Z01-REPTRAN-000001-20160510");
            String md5encryptionPath = HSignUtil.ENCRYPTPATH;
            String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);	//获取加密公钥字符串
            File tmp = new File(file1.getPath()+".bak");
            tmp.createNewFile();
            HSignUtil.transFormToTmpFile(file1, tmp);
            String m = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));
            String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(tmp), encryptionKey4Server);//进行摘要并对摘要进行加密
            params.put("sign", encryptData);
            
            
            paramsJson = JSONObject.fromObject(params);
            pam = paramsJson.toString();
            sp = new StringPart("parameters",pam);
            sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码
             
            File file2 = new File("D:/batchfile/repayment/20160510/3004-14-Z01-REPTRAN-000001-20160510."+TMP);
            File fi = new File(HSignUtil.ENCRYPTION_FILE);
            FileReader fre = new FileReader(fi);
            BufferedReader bre = new BufferedReader(fre);
            String privateKey = bre.readLine();
            
            HSignUtil.encryptHand(file1, file2, privateKey);//加密处理
            if (file1.exists())
            {
                //file1.renameTo(new File(oldFile));
            }
            if(file2.exists())
            {
                 fp = new FilePart("file",file2);
            }
            
            String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(file1)));

            method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/uploadEncrypt");
            parts = new Part[]{fp,sp};
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(new MultipartRequestEntity(parts,method.getParams()));
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.executeMethod(method);

            String responseMaps = method.getResponseBodyAsString();
            System.out.println(responseMaps);
            map = JSONObject.fromObject(responseMaps);
            return_code = (String) map.get("return_code");
            if (return_code.equals("0000")){
                System.out.println("文件上传成功");
            }else if(return_code.equals("0004"))
            {
                System.out.println("文件已上传，请勿重复上传");
            }
            else if(return_code.equals("0003")){
                System.out.println("上传文件过大");
            }
        }
    }
    
    
  //HttpClient文件批量下载
    public void testHttDownload() throws Exception {
        String[] fileName = {"3004-EVE0001-20170424"};//3004-EVE0001-20150320  3004-APPZX0002RES-000006-20160510
        String return_code = null;
        String return_msg = null;
        long size = 0;
        JSONObject map = null;
        long start = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", HSignUtil.COINSTCODE);
            params.put("bankCode", HSignUtil.BANKCODE);
            params.put("transDate", "20170424");
            params.put("fileName", fileName[i]);
            //params.put("SIGN", "JSA4THDXtp0ifs27iaEaww/A2cCZinTkFuVbDC+wyqXsifBU43hMVH8Hm4gQPe48lctpCPCAj4W/JUyzvjobD3A4vJPqq5eGp54TnGfTB265WqQE7KdTAK1zzfQqjaJYJy1RCWbqWifC6ZiK76nM1C9He4Ar0jfTRpVW7G3+mms=");
            JSONObject paramsJson = JSONObject.fromObject(params);
            System.out.println(paramsJson);
            String pam = paramsJson.toString();
            HttpClient httpClient;
            PostMethod method;
            httpClient = new HttpClient();
            method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptWithSign");
//            method = new PostMethod("http://localhost:8080/dbesb/file/downloadEncryptWithSign");
            method.getParams().setContentCharset("GBK");
            method.setParameter("parameters", pam);
            httpClient.executeMethod(method);

            String responseMap = method.getResponseBodyAsString();
            map = JSONObject.fromObject(responseMap);
            FileOutputStream out = null;
            return_code = (String) map.get("return_code");
            System.out.println(return_code);
            if(return_code.equals("0002"))
            {
                System.out.println("下载请求参数无法识别");
            }
            
            String sign = (String) map.get("sign");
            String decryptKeyPath = HSignUtil.DECRYPTPATH;	//拼接解密私钥路径
        	String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        	String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
 
            
            
            if (return_code.equals("0000")) {
                String file = (String)map.get("file");
                byte[] _file = file.getBytes("GBK");
                File newFile = new File("D:/DEMO/client/download/30040000");
                File file1 = new File(newFile+"/"+fileName[i]);
                File file2 = new File(newFile+"/"+fileName[i]+BAK);
                String newFileName = file1.getPath();
                if  (!newFile .exists()  && !newFile .isDirectory())//判断文件夹是否存在，不存在就创建
                {
                    newFile.mkdirs();
                }
                out = new FileOutputStream(newFile+"/"+fileName[i]);
                String str = new String(_file,"GBK");
                out.write(str.getBytes());
                File fi = new File(HSignUtil.ENCRYPTION_FILE);
                FileReader fre = new FileReader(fi);
                BufferedReader bre = new BufferedReader(fre);
                String privateKey = bre.readLine();
                HSignUtil.decryptHand(file1, file2, privateKey);
                out.flush();
                out.close();
                File tmp = new File(file2.getPath()+".bak");
                tmp.createNewFile();
                HSignUtil.transFormToTmpFile(file2, tmp);
                String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));
                
                if(md5.equals(signdecrypt)){
                	System.out.println("文件验签成功！");
                	System.out.println("摘要内容："+signdecrypt);
            	}else{
            		System.out.println("文件验签失败！");
            		System.out.println("sign摘要内容："+signdecrypt);
            		System.out.println("系统计算摘要内容："+md5);
            	}
                if (file1.exists())
                {
                    file1.delete();
                }
                if(file2.exists())
                {
                    file2.renameTo(new File(newFileName));
                }

                System.out.println("文件下载成功");
            }else if(return_code.equals("0002"))
            {
                System.out.println("下载请求参数无法识别");
            }
        }
    }
    
    
    
    
    
  //文件tar包下载
    public void testHttDownloadforTar() throws Exception {
        String[] fileName = {"3004-APPZX0002RES-000001-20170410"};
        String return_code = null;
        String return_msg = null;
        long size = 0;
        JSONObject map = null;
        long start = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", "800114");
            params.put("bankCode", "30040000");
            params.put("transDate", "20170410");
            params.put("fileName", fileName[i]);
            //params.put("SIGN", "JSA4THDXtp0ifs27iaEaww/A2cCZinTkFuVbDC+wyqXsifBU43hMVH8Hm4gQPe48lctpCPCAj4W/JUyzvjobD3A4vJPqq5eGp54TnGfTB265WqQE7KdTAK1zzfQqjaJYJy1RCWbqWifC6ZiK76nM1C9He4Ar0jfTRpVW7G3+mms=");
            JSONObject paramsJson = JSONObject.fromObject(params);
            String pam = paramsJson.toString();
            HttpClient httpClient;
            PostMethod method;
            httpClient = new HttpClient();
            method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptforTarWithSign");
//            method = new PostMethod("http://localhost:8181/dbesb/file/downloadEncryptforTarWithSign");
            method.getParams().setContentCharset("utf-8");
            method.setParameter("parameters", pam);
            
            
            
            httpClient.executeMethod(method);
            
            InputStream inputstream = null; 
            FileOutputStream out = null;   
            String dowloadPath = HSignUtil.FILEUPLOAD + HSignUtil.BATCHUSERREGISTERFILEUPLOAD;
        	File newFile = new File(dowloadPath);
            
            		
            
        	inputstream = method.getResponseBodyAsStream();
        	
        	List<String> fileNames = new ArrayList<String>();
        	GZip.unGzipFiles(new BufferedInputStream(inputstream), dowloadPath, fileNames);

        	File fileTmp = new File(newFile+ "/"+ fileName[i]+ TMP);
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
            
            if (fileTmp.exists()){
            	fileTmp.delete();
            }
            
            String[] fileAndSign = fileString.split("sign:");
            
            String fileContent = fileAndSign[0];
            String sign = fileAndSign[1];
            
            String decryptKeyPath = HSignUtil.DECRYPTPATH;	//拼接解密私钥路径
        	String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        	String signdecrypt = "";
        	try {
        		signdecrypt	= new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
            	
			} catch (Exception e) {
				e.printStackTrace();
			}
        	

        	
            File file1 = new File(newFile+"/"+fileName[i]);
            File file2 = new File(newFile+"/"+fileName[i]+TMP);
            
            if  (!newFile .exists()  && !newFile .isDirectory())//判断文件夹是否存在，不存在就创建
            {
                newFile.mkdirs();
            }

            String newFileName = file1.getPath();
        	
        	out = new FileOutputStream(newFile+"/"+fileName[i]);
        	out.write(fileContent.getBytes());
        	File fi = new File(HSignUtil.ENCRYPTION_FILE);
        	FileReader fileReader = new FileReader(fi);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	String privateKey = bufferedReader.readLine();
         	HSignUtil.decryptHand(file1, file2,privateKey);
        	out.flush();
        	out.close();
        
            
            File tmp1 = new File(newFile+"/"+fileName[i]+".bak");
            tmp1.createNewFile();
            HSignUtil.transFormToTmpFile(file2, tmp1);
            String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp1)));
            
            if(md5.equals(signdecrypt)){
            	System.out.println("文件验签成功！");
            	System.out.println("摘要内容："+signdecrypt);
        	}else{
        		System.out.println("文件验签失败！");
        		System.out.println("sign摘要内容："+signdecrypt);
        		System.out.println("系统计算摘要内容："+md5);
        	}
            if (file1.exists())
            {
                file1.delete();
            }

            if (tmp1.exists()){
            	tmp1.delete();
            }
            if(file2.exists())
            {
                boolean realNameflag = file2.renameTo(new File(newFileName));
                if(realNameflag)file2.delete();
            }

            System.out.println("文件下载成功");
        	
        }
    }
 
	 
 public String isChinese(String s,int length) {
		
		int len = 0;
		for(int i=0;i < s.length();i++){
			char c = s.charAt(i);
			// 根据字节码判断
			if( c >= 0x4E00 &&  c <= 0x9FA5){
				//中文字符
				len+=2;
			}else{
				len+=1;
			}
		}
		for(int i=0;i<length-len;i++){
			s+=" ";
		}
		return s;
	}
 
 public static void main(String[] args) {
	 try {
		 new BacthUserRegisterController().testHttDownload();
//		 new BacthUserRegisterController().testHttDownloadforTar();
		 
//		 testHttpUpload();
	} catch (Exception e) {
 		e.printStackTrace();
	}
}
}
