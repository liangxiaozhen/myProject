package  com.huishang.file;
import com.huishang.file.RSAUtils;
import com.huishang.util.GZip;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;






import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileTransferDemoEncrypted extends TestCase {
    public static final String TMP="tmp";
    public static  String keyPath="A842aed1aAA39dCb";

    public void testReloadKeys() throws Exception{
    	 PostMethod method;
    	 HttpClient httpClient = new HttpClient();
    	 StringPart sp=null;
    	 JSONObject map=null;
    	 Map<String,Object>params=null;
    	 JSONObject paramsJson=null;
    	 String pam=null;
    	 Part[] parts=null;
    	 params = new HashMap<String,Object>();
    	 params.put("coinst", "000047");
    	 params.put("selectValue", "1");
    	 params.put("type", "1");
    	 params.put("bankcode", "30040000");
    	 paramsJson = JSONObject.fromObject(params);
    	 pam = paramsJson.toString();
    	 sp = new StringPart("parameters",pam);
         sp.setCharSet("GBK");
         method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/reload/reloadKeys");
         method.getParams().setContentCharset("GBK");
         method.setParameter("coinst", "000047");
         method.setParameter("selectValue", "1");
         method.setParameter("type", "1");
         method.setParameter("bankcode", "30040000");
         httpClient.executeMethod(method);
    }
    //HttpClient文件批量上传
    public void testHttpUpload() throws Exception
    {
        PostMethod method;
        HttpClient httpClient = new HttpClient();
        StringPart sp=null;
        FilePart fp=null;
        String[] filePath={"D:/DEMO/client/upload/"};
        String[] fileName={"3005-YL-Z01-FINTRAN-003153-20160825"};
        String return_code=null;
        JSONObject map=null;
        Map<String,Object>params=null;
        JSONObject paramsJson=null;
        String pam=null;
        Part[] parts=null;
        Part[] sps=null;
        long start=System.currentTimeMillis();
        for(int i=0;i<filePath.length;i++)
        {
            params = new HashMap<String,Object>();
            params.put("coinstCode", "000029");
            params.put("bankCode","30050000");
            params.put("transDate", "20161114");
            params.put("fileName",fileName[i]);
            
            File file1 = new File("D:/DEMO/client/upload/3005-YL-Z01-FINTRAN-003153-20160825.TXT");
            String md5encryptionPath="D:/DEMO/encryptions_.cer";
            String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);	//获取加密公钥字符串
            File tmp = new File(file1.getPath()+".bak");
            tmp.createNewFile();
            transFormToTmpFile(file1, tmp);
            String m = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));
            String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(tmp), encryptionKey4Server);//进行摘要并对摘要进行加密
            params.put("sign", encryptData);
            
            
            paramsJson = JSONObject.fromObject(params);
            pam=paramsJson.toString();
            sp = new StringPart("parameters",pam);
            sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码

            
            File file2 = new File("D:/DEMO/client/upload/3005-YL-Z01-FINTRAN-003153-20160825.TXT."+TMP);
            String newFileName=file1.getPath();
            String oldFile=file1.getPath()+BAK;
         /*   File fi = new File(keyPath);
            FileReader fre = new FileReader(fi);
            BufferedReader bre = new BufferedReader(fre);
            String privateKey = bre.readLine();*/
            encryptHand(file1, file2, keyPath);//加密处理
            if (file1.exists())
            {
                //file1.renameTo(new File(oldFile));
            }
            if(file2.exists())
            {
                //file2.renameTo(new File(newFileName));
//                File f=new File(newFileName);
                fp = new FilePart("file",file2);
            }
            
            String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(file1)));

            // method = new PostMethod("http://localhost:8080/dbesb/file/uploadEncrypt");
           method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbdev/file/uploadEncrypt");
            parts = new Part[]{fp,sp};
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(new MultipartRequestEntity(parts,method.getParams()));
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.executeMethod(method);

            String responseMaps = method.getResponseBodyAsString();
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
        String[] fileName={"OFD_025_AJ_20160331_01.TXT"};
        String return_code=null;
        String return_msg=null;
        long size=0;
        JSONObject map=null;
        long start=System.currentTimeMillis();
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", "000048");
            params.put("bankCode", "30050000");
            params.put("transDate", "20160405");
            params.put("fileName", fileName[i]);
            //params.put("SIGN", "JSA4THDXtp0ifs27iaEaww/A2cCZinTkFuVbDC+wyqXsifBU43hMVH8Hm4gQPe48lctpCPCAj4W/JUyzvjobD3A4vJPqq5eGp54TnGfTB265WqQE7KdTAK1zzfQqjaJYJy1RCWbqWifC6ZiK76nM1C9He4Ar0jfTRpVW7G3+mms=");
            JSONObject paramsJson = JSONObject.fromObject(params);
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
            
            String sign = (String) map.get("sign");
            String decryptKeyPath="D:/DEMO/decryptions_.key";	//拼接解密私钥路径
        	String decryptKey4Server=RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        	String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
 
            
            
            if (return_code.equals("0000")) {
                String file=(String)map.get("file");
                byte[] _file=file.getBytes("GBK");
                File newFile =new File("D:/DEMO/client/download/30040000");
                File file1=new File(newFile+"/"+fileName[i]);
                File file2=new File(newFile+"/"+fileName[i]+BAK);
                String newFileName=file1.getPath();
                if  (!newFile .exists()  && !newFile .isDirectory())//判断文件夹是否存在，不存在就创建
                {
                    newFile.mkdirs();
                }
                out=new FileOutputStream(newFile+"/"+fileName[i]);
                String str=new String(_file,"GBK");
                out.write(str.getBytes());
                decryptHand(file1, file2);
                out.flush();
                out.close();
                File tmp = new File(file2.getPath()+".bak");
                tmp.createNewFile();
                transFormToTmpFile(file2, tmp);
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
        String[] fileName = {"aa"};
        String return_code = null;
        String return_msg = null;
        long size = 0;
        JSONObject map = null;
        long start = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", "000001");
            params.put("bankCode", "30040000");
            params.put("transDate", "20150821");
            params.put("fileName", fileName[i]);
            //params.put("SIGN", "JSA4THDXtp0ifs27iaEaww/A2cCZinTkFuVbDC+wyqXsifBU43hMVH8Hm4gQPe48lctpCPCAj4W/JUyzvjobD3A4vJPqq5eGp54TnGfTB265WqQE7KdTAK1zzfQqjaJYJy1RCWbqWifC6ZiK76nM1C9He4Ar0jfTRpVW7G3+mms=");
            JSONObject paramsJson = JSONObject.fromObject(params);
            String pam = paramsJson.toString();
            HttpClient httpClient;
            PostMethod method;
            httpClient = new HttpClient();
            //method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbdev/file/downloadEncryptforTarWithSign");
            method = new PostMethod("http://localhost:8181/dbesb/file/downloadEncryptforTarWithSign");
            method.getParams().setContentCharset("GBK");
            method.setParameter("parameters", pam);
            
            
            
            httpClient.executeMethod(method);
            
            InputStream inputstream = null; 
            FileOutputStream out = null;   
            String dowloadPath ="D:/DEMO/client/download/30040000";
        	File newFile =new File(dowloadPath);
            
            		
            
        	inputstream = method.getResponseBodyAsStream();
        	
        	List<String> fileNames = new ArrayList<String>();
        	GZip.unGzipFiles(new BufferedInputStream(inputstream), dowloadPath, fileNames);

        	File fileTmp=new File(newFile+ "/"+ fileName[i]+ TMP);
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
            
            String decryptKeyPath="D:/DEMO/30040000/000001/client/decrypts.key";	//拼接解密私钥路径
        	String decryptKey4Server=RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        	String signdecrypt = "";
        	try {
        		signdecrypt	= new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
            	
			} catch (Exception e) {
				e.printStackTrace();
			}
        	

        	
            File file1=new File(newFile+"/"+fileName[i]);
            File file2=new File(newFile+"/"+fileName[i]+TMP);
            
            if  (!newFile .exists()  && !newFile .isDirectory())//判断文件夹是否存在，不存在就创建
            {
                newFile.mkdirs();
            }

            String newFileName=file1.getPath();
        	
        	out=new FileOutputStream(newFile+"/"+fileName[i]);
        	out.write(fileContent.getBytes());
        	decryptHand(file1, file2);
        	out.flush();
        	out.close();
        
            
            File tmp1 = new File(newFile+"/"+fileName[i]+".bak");
            tmp1.createNewFile();
            transFormToTmpFile(file2, tmp1);
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




    public static void decryptHand(File file1,File file2){
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
    
	public static void decryptHand(File file1, File file2, String keyPath) {
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


    public static final String BAK=".BAK";
    public static void main(String[] args) {

        File file1 = new File("D:/DEMO/a-b-c-d-e-f");
        File file2 = new File("D:/DEMO/a-b-c-d-e-f"+BAK);
        String newFileName=file1.getPath();
        //encryptHand(file1, file2);//加密处理
        decryptHand(file1, file2);//解密处理
        if (file1.exists())
        {
            file1.delete();
        }
        if(file2.exists())
        {
            file2.renameTo(new File(newFileName));
        }

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
}
