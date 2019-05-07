package com.huishang.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cupdata.zicon.util.RSAEncryptUtil;

public class SignString {
	private  static String signPrivatePath="D:/DEMO/30040000/000006/sign_.key";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String a=signString();
		System.out.println(a);

	}
	public static String signString() throws Exception {
    	LinkedHashMap reqMap = new LinkedHashMap();
    	reqMap.put("TRXDATE", "20160415");
        reqMap.put("TRXTIME", "091540");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("COINSTCODE", "000006");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("CARDNBR", "6212461410000016897");
        reqMap.put("SERI_NO", "20161212101900012016121210190001");
        reqMap.put("FORGERPWD_URL", "http://www.baidu.com");
        reqMap.put("TRANSACTION_URL", "http://www.baidu.com");
        reqMap.put("REBACK_URL", "");
        reqMap.put("SUCCESSRESULT_URL", "http://www.baidu.com");
    	String requestMapMerged = mergeMap(reqMap);
    	String sign1 = RSAEncryptUtil.MD5WithRSASign(requestMapMerged.getBytes("UTF-8"), getSignPrivateKey4Client(signPrivatePath));
    	
		return sign1;
	}
	private  static String mergeMap(Map map){

        String requestMerged = "";
        StringBuffer buff = new StringBuffer();

        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

        Map.Entry<String, String> entry;

        while (iter.hasNext()) {

            entry = iter.next();
            System.out.print(entry.getKey() + ", ");
            if (!"SIGN".equals(entry.getKey())){
                buff.append(entry.getValue().trim());
            }

//        value = entry.getValue();

        }
        System.out.println(" ");

        requestMerged = buff.toString();
        return requestMerged;

    }
	 public static String getSignPrivateKey4Client(String keyPath){
	        StringBuffer privateBuffer=new StringBuffer();
	        try {
	            //InputStream inputStream = Thread.currentThread().getContextClassLoader()
	            //       .getResourceAsStream(signPrivatePath);
	            FileInputStream fi=new FileInputStream(keyPath);
	            InputStreamReader inputReader = new InputStreamReader(fi);
	            BufferedReader bufferReader = new BufferedReader(inputReader);
	            // 读取一行
	            String line = "";
	            while ((line=bufferReader.readLine())!=null) {
	                privateBuffer.append(line);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return privateBuffer.toString();
	    }

}
