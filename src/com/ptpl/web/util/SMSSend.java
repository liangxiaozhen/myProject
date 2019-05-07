package com.ptpl.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chuanglan.sms.request.SmsSendRequest;
import com.chuanglan.sms.response.SmsSendResponse;
import com.chuanglan.sms.util.ChuangLanSmsUtil;
import com.ipi.cloud.interfaces.dto.ErrorInfo;
import com.ipi.cloud.sms.access.request.CloudSmsCore;
import com.ipi.cloud.sms.access.vo.SmsAccount;
import com.ipi.cloud.sms.access.vo.response.SmsBatchSmbitResult;
import com.ipi.cloud.sms.access.vo.response.SmsBatchSumbitResponse;

public class SMSSend {

	/**
	 * 华信发送短信
	 * 
	 * @param mobilephone
	 *            发送号码
	 * @param content
	 *            内容
	 * @param name
	 *            签名
	 * @return
	 */
	public static String smsSend(String mobilephone, String content, String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strURL = "http://dx.ipyy.net/sms.aspx";
		String account = "moxie";
		String password = "moxieruanjian";
		String userid = "";
		String mobile = mobilephone;
		String ncontent = content + name;
		String sendTime = sdf.format(new Date());
		String action = "send";
		String extno = "";

		HttpClientUtil hcu = new HttpClientUtil();

		Map<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("password", password);
		map.put("userid", userid);
		map.put("mobile", mobile);
		map.put("content", ncontent);
		map.put("sendTime", sendTime);
		map.put("action", action);
		map.put("extno", extno);

		String charset = "UTF-8";
		int proxy = 0;

		String str[] = hcu.doPost(strURL, map, charset, proxy);
		System.out.println(str.length);
		System.out.println(str[0]);
		System.out.println(str[1]);

		return "";
	}

	/**
	 * 短信通发送短信
	 * 
	 * @param interUrl
	 *            接口地址
	 * @param entNo
	 *            企业编号
	 * @param account
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @param destPhone
	 *            目标号码，多个用英文逗号隔开
	 * @param msg
	 *            短信内容
	 */
	public static boolean smsSend(String interUrl, String entNo, String account, String password, String destPhone,
			String msg) {
		// String account,String password,String destPhone,String msg
		// // 接口地址
		// String interUrl = "http://120.24.230.105:6666/interface/SendMsg";
		// // 企业编号
		// String entNo = "21471837331005";
		// // 用户账号
		// String account = "13644057565";
		// // 用户密码
		// String password = "kk951357";
		// // 目标号码，多个用英文逗号隔开
		// String destPhone = "18273293855";
		// // 短信内容
		// String msg = "您好，你在陆金所的注册验证码为6369, 收到验证码后的1小时内注册，若非本人操作，请忽略本条短信内容";

		/*** 调用接口进行请求批量发送 ***/
		SmsBatchSumbitResponse resp = CloudSmsCore.getInstance().batchSendSms(interUrl,
				new SmsAccount(entNo, account, password), destPhone, msg);
		System.out.println("发送状态： " + resp.isSuccess());
		if (resp.isSuccess()) // 提交成功
		{
			SmsBatchSmbitResult result = resp.getBatchSmbitResult();
			// 根据结果进行业务处理
			System.out.println(result);
		} else { // 提交返回错误
			ErrorInfo errorInfo = resp.getErrorInfo();
			// 根据结果进行相关错误处理
			System.out.println(errorInfo);
		}
		return resp.isSuccess();
	}

	/**
	 * 短信通
	 * 
	 * @param destPhone
	 *            发送的手机号码 以,分隔
	 * @param msg
	 *            短信内容
	 */
	public static boolean smsSend(String destPhone, String msg) {
		// 接口地址
		String interUrl = "http://120.24.230.105:6666/interface/SendMsg";
		// 企业编号
		String entNo = "21471837331005";
		// 用户账号
		String account = "13644057565";
		// 用户密码
		String password = "kk951357";

		/*** 调用接口进行请求批量发送 ***/
		SmsBatchSumbitResponse resp = CloudSmsCore.getInstance().batchSendSms(interUrl,
				new SmsAccount(entNo, account, password), destPhone, msg);
		System.out.println("发送状态： " + resp.isSuccess());
		if (resp.isSuccess()) // 提交成功
		{
			SmsBatchSmbitResult result = resp.getBatchSmbitResult();
			// 根据结果进行业务处理
			System.out.println(result);
		} else { // 提交返回错误
			ErrorInfo errorInfo = resp.getErrorInfo();
			// 根据结果进行相关错误处理
			System.out.println(errorInfo);
		}
		return resp.isSuccess();
	}

	/**
	 * 发送短信（短信通） 手机号码以,隔开
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月1日 下午5:34:55 
	 * @param interUrl 接口地址
	 * @param entNo    企业号
	 * @param account   账号
	 * @param password  密码
	 * @param destPhone 手机号
	 * @param msg       内容
	 * @return  true 成功, false 失败
	 */
	public static SmsBatchSumbitResponse smsSendDXT(String interUrl, String entNo, String account, String password, String destPhone,
			String msg) {
		/*** 调用接口进行请求批量发送 ***/
		SmsBatchSumbitResponse resp = CloudSmsCore.getInstance().batchSendSms(interUrl,
				new SmsAccount(entNo, account, password), destPhone, msg);
		System.out.println("发送状态： " + resp.isSuccess());
		/*
		if (resp.isSuccess()) // 提交成功
		{
			SmsBatchSmbitResult result = resp.getBatchSmbitResult();
			// 根据结果进行业务处理
			System.out.println(result);
		} else { // 提交返回错误
			ErrorInfo errorInfo = resp.getErrorInfo();
			// 根据结果进行相关错误处理
			System.out.println(errorInfo);
		}*/
		return resp;
	}
	/**
	 *  发送普通短信（创蓝云） 
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月2日 上午8:44:28 
	 * @param url     短信地址
	 * @param account  账号
	 * @param pswd     密码
	 * @param phone    手机
	 * @param msg      内容
	 * @return
	 */
	public static SmsSendResponse smsSendCL(String url,String account,String pswd,String phone,String msg){

		//普通短信地址
		//String smsSingleRequestServerUrl = "http://vsms.253.com/msg/send/json";
		// 短信内容
	   // String msg = "【253云通讯】你好,你的验证码是123456";
		//手机号码
		//String phone = "18273293855";
		//状态报告
		//String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,"true");
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		return smsSingleResponse;
	}
	public static void main(String[] args) {
		// smsSend("18212345678", "123456...", "【莫邪软件】");
		smsSend("18212345678", "验证码:" + StringUtil.getN(6));
	}
}
