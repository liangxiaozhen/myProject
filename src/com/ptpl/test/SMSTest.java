package com.ptpl.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ptpl.web.util.HttpClientUtil;

public class SMSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for(int i=0;i<100000;i++){
//			String round = String.valueOf(Math.round(Math.random() * 1000000+999999 ));
//			System.out.println(round.substring(1));
//		}
		
		SMSTest.smsTest();
	}
	
	
	
	public static void smsTest(){
		
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strURL = "http://dx.ipyy.net/sms.aspx";
		String account = "moxie";
		String password = "moxieruanjian";
		String userid= "";
		String mobile= "13913514863";
		String content= "验证码:123456 【莫邪软件】";
		String sendTime= sf.format(new Date());
		String action = "send";
		String extno ="";

		
		HttpClientUtil hcu = new HttpClientUtil();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("password", password);
		map.put("userid", userid);
		map.put("mobile", mobile);
		map.put("content", content);
		map.put("sendTime", sendTime);
		map.put("action", action);
		map.put("extno", extno);
		
		String charset = "UTF-8";
		int proxy = 0;
		
		String str [] =hcu.doPost(strURL, map, charset, proxy);
		
		System.out.println(str[0]);
		System.out.println(str[1]);
	}

}
