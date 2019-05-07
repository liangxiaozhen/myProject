package com.ptpl.controller.ui.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huishang.util.HSignUtil;
import com.moneymoremore.util.HttpClientHandler;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.service.UIAppSecurityCenterServiceI;
import com.ptpl.model.EmailChannel;
import com.ptpl.model.EmailRecord;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.service.EmailChannelServiceI;
import com.ptpl.service.EmailRecordServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.BankCardUtil;
import com.ptpl.web.util.IdcardValidator;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: UIAppSecurityCenterServiceImpl 
* @Description: TODO(安全中心 接口) 
* @author cjm 
* @date 2017年6月11日 上午10:03:18 
*
 */
public class UIAppSecurityCenterServiceImpl implements UIAppSecurityCenterServiceI{

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Autowired
	private  UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	
	@Autowired
	private  UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	 
  	@Autowired
	private  EmailChannelServiceI emailChannelServiceI;
 	
 	@Autowired
	private EmailRecordServiceI emailRecordServiceI;
  	 
	/**
	 * 
	* @Title: realNameCertification 
	* @Description: TODO(实名制认证 接口) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> realNameCertification(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session){
		Map<String,String> hashMap = new HashMap<>();
 		String realName = request.getParameter("realName");
		String cardNumber = request.getParameter("cardNumber");
		if(StringUtil.isEmpty(realName)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "realName_null");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：认证失败！身份证姓名不能为空！");
			return hashMap;
		}
		
		if(StringUtil.isEmpty(cardNumber)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_null");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：认证失败！身份证号码不能为空！");
			return hashMap;
		}
		
		if(userBaseAccountInfo.getIsreally() != null && userBaseAccountInfo.getIsreally().equals((short)1)){
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "realNameed");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：已实名认证！");
 			return hashMap;
		}
		
		 IdcardValidator idcardValidator = new IdcardValidator();
		 boolean falg = idcardValidator.isValidatedAllIdcard(cardNumber);//身份证检查
		 if(!falg){//身份证检验失败
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：认证失败！请输入正确的身份证号码或联系客服！");
	 		 return hashMap;
		 }
		 
		 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		 ubai.setCertificationnumber(cardNumber);
		 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		 if(userBaseAccountInfo45 != null){//身份证号已存在
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_binding");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：认证失败！您输入的身份证号码已经绑定过其他用户了！");
	 		 return hashMap;
		 }
		 
		String url = "http://api.id98.cn/api/idcard";
		Map<String,String> HttpClHashMap = new HashMap<>();
		HttpClHashMap.put("appkey", "c62f920f2738cec64dde7cefa2c26b81");
		HttpClHashMap.put("name", realName);
		HttpClHashMap.put("cardno", cardNumber);
		String result = "";
		try {
			result = doPost(HttpClHashMap, url);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
 		if(StringUtil.isEmpty(result)){
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "network_anomalies");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：因网络响应不及时,认证失败！请重新操作或联系客服!");
	 		 return hashMap;
		 }
  		
 		JSONObject jsonObject = JSONObject.parseObject(result);
		String isok = jsonObject.getString("isok");//0：查询失败 ， 1：查询成功
		String code = jsonObject.getString("code");//1	一致 2	不一致 3	无此身份证号码
		String data = jsonObject.getString("data");
		JSONObject jsonObject2 = JSONObject.parseObject(data);
		String sex = jsonObject2.getString("sex");//M：男性 ， F：女性
		if(!(isok.equals("1") && code.equals("1"))){//code:1 一致 ; isok:1  查询成功
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "matching");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示:身份证认证失败!请检查您输入的身份证姓名和号码是否一致！");
	 		 return hashMap;
		}
 		
		if(StringUtil.isNotEmpty(sex)){
			Short sexShort = (short) (sex.equals("M") ? 1 : 0 );
			userBaseAccountInfo.setSex(sexShort);
		}
		
		String certificationtype = "01"; 
		/*证件类型  01-身份证18位
				02-身份证15位
				20-其它
				25-企业社会信用代码
		注：企业开户时上送20或社会信用号25*/
		
		if(StringUtils.trimToEmpty(cardNumber).length() < 18){
			certificationtype = "02";
		}
		
		userBaseAccountInfo.setRealname(realName);
		userBaseAccountInfo.setCertificationnumber(cardNumber);
		userBaseAccountInfo.setIsreally((short)1); /*是否实名认证: 1已认证 0 未认证*/
		userBaseAccountInfo.setCertificationtype(certificationtype);
		int count = 0;
		count = userBaseAccountInfoServiceI.update(userBaseAccountInfo);
		if(count > 0){
			 session.setAttribute(AppSession_Constant.APPUSER, BaseController.getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo));
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE,"success");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示:实名认证成功！");
	 		 return hashMap;
		}else {
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			 hashMap.put(AppSession_Constant.MESSAGE, "提示:因网络响应不及时,身份证认证失败!请重新操作或联系客服!");
	 		 return hashMap;
		}
	}
	 
	/**
	 * 
	* @Title: sendPhoneCode 
	* @Description: TODO(发送手机验证码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
 
	@Override
	public Map<String, String> sendPhoneCode(HttpServletRequest request, HttpServletResponse response,
			UserBaseAccountInfo userBaseAccountInfo,HttpSession session) {
		 Map<String,String> hashMap = new HashMap<>();
		 if(userBaseAccountInfo.getMobilephone() == null || userBaseAccountInfo.getIsmobileverify() == null || !userBaseAccountInfo.getIsmobileverify().equals((short)1)){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "phone_validation");
			 hashMap.put(AppSession_Constant.MESSAGE, "短信发送失败！原手机号码未验证！请先验证手机号码！");
			 return hashMap;
		 }
		 
//		Random random = new Random();
////	String code = String.valueOf(random.nextInt(899999) + 100000);
////	boolean falg = SMSSend.smsSend(phone, code);
		 
		 String code = "111111";
		 boolean falg = true;
		 if(falg){
			 session.setAttribute(AppSession_Constant.PHONECODE, code);
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE, "success");
			 hashMap.put(AppSession_Constant.MESSAGE, "短信发送成功");
			 return hashMap;
		 }else{
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			 hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,短信发送失败！请重新操作或联系客服！");
			 return hashMap; 
		 }
		 
	}
	
	/**
	 * 
	* @Title: checkPhoneCode 
	* @Description: TODO(验证原手机短信验证码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> checkPhoneCode(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 Map<String,String> hashMap = new HashMap<>();
		 String code = request.getParameter("code");
		 if(StringUtil.isEmpty(code)){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "code_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！请输入短信验证码！");
			 return hashMap;
		 }
		 
		 String tokenCode = (String) session.getAttribute(AppSession_Constant.PHONECODE);
		 if(StringUtil.isEmpty(tokenCode)){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "tokenCode_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！请点击短信发送按钮！");
			 return hashMap;
		 }
		 
		 if(!tokenCode.equals(code)){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "code_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！请输入正确的手机短信验证码！");
			 return hashMap;
		 }
 		 
		 session.removeAttribute(AppSession_Constant.PHONECODE);
		 session.setAttribute(AppSession_Constant.TRADEPASSWORDCHECK, AppSession_Constant.TRADEPASSWORDCHECK);//交易密码验证 和 登录密码验证
		 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
		 hashMap.put(AppSession_Constant.RESULTCODE, "success");
		 hashMap.put(AppSession_Constant.MESSAGE, "手机短信验证码验证成功!");
		 return hashMap;
 	}
	
	/**
	 * 
	* @Title: sendPhoneSsmCode 
	* @Description: TODO(发送新手机验证码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> sendNewPhoneCode(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 Map<String,String> hashMap = new HashMap<>();
		 String phone = request.getParameter("phone");
		 if(StringUtil.isEmpty(phone)){
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "phone_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！请输入手机号码！");
			 return hashMap;
		 }
		 
		 if(!StringUtil.isMobile(phone)){
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "phone_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！请输入正确的手机号码！");
			 return hashMap;
		 }
		 
		 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		 ubai.setMobilephone(phone);
		 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		 if(userBaseAccountInfo45 != null){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "phone_binding");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！您输入的手机号码已经绑定过其他用户了!");
			 return hashMap;
		 }
		 
//		Random random = new Random();
////	String code = String.valueOf(random.nextInt(899999) + 100000);
////	boolean falg = SMSSend.smsSend(phone, code);
		 
		 String code = "111111";
		 boolean falg = true;
		 if(falg){
			 session.setAttribute(AppSession_Constant.NEWPHONECODE, code);
			 session.setAttribute(AppSession_Constant.PHONE, phone);
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE, "success");
			 hashMap.put(AppSession_Constant.MESSAGE, "短信发送成功");
			 return hashMap;
		 }else{
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			 hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,短信发送失败！请重新操作或联系客服！");
			 return hashMap; 
		 }
	}
	
	/**
	 * 
	* @Title: phoneUpdate 
	* @Description: TODO(修改手机号码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> phoneUpdate(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session){
		Map<String,String> hashMap = new HashMap<>();
		String phone = request.getParameter("phone");
		String code  = request.getParameter("code");
 		if(StringUtil.isEmpty(phone)){//手机号码为null
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "phone_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入新手机号码");
 			 return hashMap;
 		}
		
		if(StringUtil.isEmpty(code)){//验证码为null
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "code_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入新手机短信验证码!");
 			 return hashMap;
		}
		
		String tocode = (String) session.getAttribute(AppSession_Constant.NEWPHONECODE);
		String tophone = (String) session.getAttribute(AppSession_Constant.PHONE);
		if(StringUtil.isEmpty(tocode) || StringUtil.isEmpty(tophone)){//操作超时请重新操作
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "tocode_null");
			hashMap.put(AppSession_Constant.MESSAGE, "请点击短信发送按钮发送短信！");
			return hashMap;
		}

		if(!tocode.equals(code)){//验证码错误
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "code_error");
			hashMap.put(AppSession_Constant.MESSAGE, "请输入正确的短信验证码！");
			return hashMap;
		}

		if(!tophone.equals(phone)){//请输入发送短信的手机号码
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "phone_error");
			hashMap.put(AppSession_Constant.MESSAGE, "请输入发送短信验证码的新手机号！");
			return hashMap;
		}
 
		userBaseAccountInfo.setMobilephone(phone); /*手机号：（唯一）*/
		userBaseAccountInfo.setIsmobileverify((short)1);/*手机验证 1已验证 0未验证*/
		int count = 0;
		count = userBaseAccountInfoServiceI.update(userBaseAccountInfo);
		if(count > 0){
			session.removeAttribute(AppSession_Constant.NEWPHONECODE);
			session.removeAttribute(AppSession_Constant.PHONE);
		 
			session.setAttribute(AppSession_Constant.APPUSER, BaseController.getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo));
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			hashMap.put(AppSession_Constant.RESULTCODE, "success");
			hashMap.put(AppSession_Constant.MESSAGE, "新手机修改成功！");
			return hashMap;
		}else{
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,修改手机号码失败！请重新操作或联系客服！");
			return hashMap;
 		}
 	}
	
	/**
	 * 
	* @Title: emailBind 
	* @Description: TODO(新邮箱绑定) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> emailBind(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo){
		Map<String,String> hashMap = new HashMap<>();
		String email = request.getParameter("email");
  		if(StringUtil.isEmpty(email)){//email为null
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入新邮箱账号！");
 			 return hashMap;
 		}
   		
  		if(!StringUtil.isEmail(email)){//邮箱账号格式错误为null
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入新正确的邮箱账号！");
			 return hashMap;
		}
  		
  		if(userBaseAccountInfo.getIsemailverify() != null && userBaseAccountInfo.getIsemailverify().equals((short)1)){
  			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "email_validationed");
			hashMap.put(AppSession_Constant.MESSAGE, "您已经绑定过邮箱账号了！");
			return hashMap;
  		}
  		
  		 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		 ubai.setEmail(email);;
		 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		 if(userBaseAccountInfo45 != null){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_binding");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！您输入的邮箱账号已经绑定过其他用户了");
			 return hashMap;
		 }
  		
		 String emailCode 	= "";//加密后的邮箱账号
		 String toKCode 	= "";//加密后的uid
		 String code 		= "";//加密后的时间
		 String username	= "";//用户名
		 String from_email  = "";//发送的邮箱账号
		 String password    = "";//发送的邮箱密码
		 String smtp_server = "";//邮件stmp服务器
 		 Date date =  new Date();
		 EmailChannel emailChannel = new EmailChannel();
		 emailChannel.setIsuse((short)0);//是否启用1禁用0启用
		 List<EmailChannel> emailChannels = emailChannelServiceI.findEmailChannelsByEmailChannel(emailChannel);
		 boolean falg = false;
 		 if(emailChannels.size() > 0){
			 EmailChannel channel2 = StringUtil.getEmailChannel(emailChannels);
			 if(channel2.getEmailsmtp() != null && channel2.getEmailsend() != null && channel2.getEmailpass() != null){
				  emailCode   = AES.getEncrypt(email, Session_Constant.EMAILCODEKEY);//加密后的邮箱账号
				  toKCode 	  = AES.getEncrypt(userBaseAccountInfo.getId().toString(), Session_Constant.EMAILCODEKEY);//加密后的uid
				  code 		  = AES.getEncrypt(String.valueOf(date.getTime()), Session_Constant.EMAILCODEKEY);//加密后的时间
				  username	  = userBaseAccountInfo.getLoginname();//用户名
				  from_email  = channel2.getEmailsend();//发送的邮箱账号
				  password    = channel2.getEmailpass();//发送的邮箱密码
				  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
				  falg = SendMailTempalte.sendEamilForBindEmail(email, emailCode, toKCode, code, username, from_email, password, smtp_server, request);//发送邮件信息SendMailTempalte
			 }
		 }
		 
 		if(falg){
			 EmailRecord emailRecord = new EmailRecord();
			 emailRecord.setEmail(email);//发送email
			 emailRecord.setBaseid(userBaseAccountInfo.getId());//用户ID
			 emailRecord.setUsername(userBaseAccountInfo.getLoginname() == null ? email : userBaseAccountInfo.getLoginname());//用户名
			 emailRecord.setSendtype((short)2);  /*发送方式（1手工,2系统）*/
			 Map<String,String> hashMap2 = new HashMap<String,String>();
			 hashMap2.put("emailCode", emailCode);
			 hashMap2.put("toKCode", toKCode);
			 hashMap2.put("code", code);
			 hashMap2.put("username", username);
			 hashMap2.put("email", email);
			 hashMap2.put("method", "sendEamilForBindEmail");
			 String content = JSON.toJSONString(hashMap2);
			 emailRecord.setEmailcontent(content);//发送的email 内容
			 emailRecord.setSendemail(from_email);//发送端email
			 emailRecord.setSendtime(date);//发送时间
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE, "success");
			 hashMap.put(AppSession_Constant.MESSAGE, "验证链接已发至您的邮箱,请在24小时内完成验证");
 			 return hashMap;
		 }else{
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "fail");
			 hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,邮件验证发送失败！请重新操作！");
			 return hashMap;
		 }
 	}
	
	/**
	 * 
	* @Title: emailBind 
	* @Description: TODO(邮箱修改) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String>  emailUpdateCheck(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo){
 		Map<String,String> hashMap = new HashMap<>();
		String email = request.getParameter("email");
		String newEmail = request.getParameter("newEmail");
		
  		if(StringUtil.isEmpty(email)){//email为null
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入原邮箱账号！");
 			 return hashMap;
 		}
   		
  		if(!StringUtil.isEmail(email)){//邮箱账号格式错误为null
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入正确的原邮箱账号！");
			 return hashMap;
		}
  		
  		if(StringUtil.isEmpty(newEmail)){//email为null
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newEmail_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入新邮箱账号！");
			 return hashMap;
		}
  		
 		if(!StringUtil.isEmail(newEmail)){//邮箱账号格式错误为null
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newEmail_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "请输入正确的新邮箱账号！");
			 return hashMap;
		}
 		
 		if(userBaseAccountInfo.getIsemailverify() != null && userBaseAccountInfo.getIsemailverify().equals((short)0)){
  			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "email_validationed");
			hashMap.put(AppSession_Constant.MESSAGE, "您未绑定过邮箱账号！");
			return hashMap;
  		}
  		
 		if(!userBaseAccountInfo.getEmail().equals(email)){
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "email_check");
			 hashMap.put(AppSession_Constant.MESSAGE, "您输入的原邮箱错误,请输入您原来绑定的邮箱账号！");
			 return hashMap;
 		}
 		
  		 
  		
  		 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		 ubai.setEmail(newEmail);;
		 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		 if(userBaseAccountInfo45 != null){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newEmail_binding");
			 hashMap.put(AppSession_Constant.MESSAGE, "操作失败！您输入的新邮箱账号已经绑定过其他用户了");
			 return hashMap;
		 }
		
 		 String emailCode 	= "";//加密后的邮箱账号
		 String toKCode 	= "";//加密后的uid
		 String code 		= "";//加密后的时间
		 String username	= "";//用户名
		 String from_email  = "";//发送的邮箱账号
		 String password    = "";//发送的邮箱密码
		 String smtp_server = "";//邮件stmp服务器
 		 Date date =  new Date();
		 EmailChannel emailChannel = new EmailChannel();
		 emailChannel.setIsuse((short)0);//是否启用1禁用0启用
		 List<EmailChannel> emailChannels = emailChannelServiceI.findEmailChannelsByEmailChannel(emailChannel);
		 boolean falg = false;
 		 if(emailChannels.size() > 0){
			 EmailChannel channel2 = StringUtil.getEmailChannel(emailChannels);
			 if(channel2.getEmailsmtp() != null && channel2.getEmailsend() != null && channel2.getEmailpass() != null){
				  emailCode   = AES.getEncrypt(newEmail, Session_Constant.EMAILCODEKEY);//加密后的邮箱账号
				  toKCode 	  = AES.getEncrypt(userBaseAccountInfo.getId().toString(), Session_Constant.EMAILCODEKEY);//加密后的uid
				  code 		  = AES.getEncrypt(String.valueOf(date.getTime()), Session_Constant.EMAILCODEKEY);//加密后的时间
				  username	  = userBaseAccountInfo.getLoginname();//用户名
				  from_email  = channel2.getEmailsend();//发送的邮箱账号
				  password    = channel2.getEmailpass();//发送的邮箱密码
				  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
				  falg = SendMailTempalte.sendEamilForUpdateEmail(newEmail, emailCode, toKCode, code, username, from_email, password, smtp_server, request);//发送邮件信息SendMailTempalte
			 }
		 }
 		 
 		if(falg){
			 EmailRecord emailRecord = new EmailRecord();
			 emailRecord.setEmail(email);//发送email
			 emailRecord.setBaseid(userBaseAccountInfo.getId());//用户ID
			 emailRecord.setUsername(userBaseAccountInfo.getLoginname() == null ? email : userBaseAccountInfo.getLoginname());//用户名
			 emailRecord.setSendtype((short)2);  /*发送方式（1手工,2系统）*/
			 Map<String,String> hashMap2 = new HashMap<String,String>();
			 hashMap2.put("emailCode", emailCode);
			 hashMap2.put("toKCode", toKCode);
			 hashMap2.put("code", code);
			 hashMap2.put("username", username);
			 hashMap2.put("email", newEmail);
			 hashMap2.put("method", "sendEamilForUpdateEmail");
			 String content = JSON.toJSONString(hashMap2);
			 emailRecord.setEmailcontent(content);//发送的email 内容
 			 emailRecord.setSendemail(from_email);//发送端email
			 emailRecord.setSendtime(date);//发送时间
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
  			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE, "success");
			 hashMap.put(AppSession_Constant.MESSAGE, "验证链接已发至您的邮箱,请在24小时内完成验证");
			 return hashMap;
		 }else{
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			 hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,邮件验证发送失败！请重新操作！");
			 return hashMap;
		 }
 	}
 	
	/**
	 * 
	* @Title: updateLoginPassWord 
	* @Description: TODO(修改登录密码 ) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> updateLoginPassWord(HttpServletRequest request,HttpServletResponse response,
			UserBaseAccountInfo userBaseAccountInfo,UserAccountSafeInfo userAccountSafeInfo,HttpSession session){
		Map<String,String> hashMap = new HashMap<>();
		String tradePassWordCheck = (String) session.getAttribute(AppSession_Constant.TRADEPASSWORDCHECK);
		if(StringUtil.isEmpty(tradePassWordCheck)){
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "check_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "未通过短信验证！请先验证短信！");
			 return hashMap;
		}
		
		if(!tradePassWordCheck.equals(AppSession_Constant.TRADEPASSWORDCHECK)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "check_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "未通过短信验证！请先验证短信！");
			 return hashMap;
		}
		
 		String newPassWord = request.getParameter("password");
 		if(StringUtil.isEmpty(newPassWord)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newPassWord_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "新登录密码不能为空！");
			 return hashMap;
		}
 		 
		/*
		 * 验证密码
		 */
 		if (newPassWord.length() < 8 || newPassWord.length() > 20) {
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newPassWord_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "新登录密码长度为8～20位字符！");
			 return hashMap;
		}
 		
 		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z@#$*]{8,16}$";
 		if (!newPassWord.matches(reg)) {
 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "newPassWord_error");
			 hashMap.put(AppSession_Constant.MESSAGE, "新登录密码必须包含数字和字母！");
			 return hashMap;
		}
		 
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(newPassWord, BCrypt.gensalt());
		userAccountSafeInfo.setLoginpassword(hashed);
		int count = 0;
		count = userAccountSafeInfoServiceI.update(userAccountSafeInfo);
		if(count > 0){
			 session.removeAttribute(AppSession_Constant.TRADEPASSWORDCHECK);
			 session.setAttribute(AppSession_Constant.APPUSERACCOUNTSAFEINFO, userAccountSafeInfo);
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			 hashMap.put(AppSession_Constant.RESULTCODE, "success");
			 hashMap.put(AppSession_Constant.MESSAGE, "修改密码成功！");
			 return hashMap;
		}else{
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			 hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,密码修改失败！");
			 return hashMap;
		}
 	}
	/**
	 * 
	* @Title: getUserBaseDetail 
	* @Description: TODO(返回用户基本信息) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return UserBaseAccountInfo    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> getUserBaseDetail(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo){
		Map<String,String> hashMap = new HashMap<>();
 		String  imagepath 				= "";//头像地址
		String  email					= "";//邮箱账号
		String  emailstr				= "";//邮箱账号
 		String  certificationnumber		= "";//身份证号码
 		String certificationnumberstr	= "";
		String  mobilephone				= "";//手机号
		String  mobilephonestr			= "";//手机号
 		String  accounttype				= "1";//账号类型
		String  ismobileverify			= "0";//手机是否验证
		String  isemailverify			= "0";//邮箱是否验证
		String  isreally				= "0";//是否实名认证
		String  loginname				= "";//用户名
		String  id						= userBaseAccountInfo.getId().toString();//用户主键ID
		String  isopenfsinfo			= "0";//是否开通托管
		String  tradepass				= "0";//是否设置交易密码
		String  realName				= "";
		String  realNameStr				= "";
		 
  		if(userBaseAccountInfo.getMobilephone() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone())
				 && userBaseAccountInfo.getIsmobileverify() != null && userBaseAccountInfo.getIsmobileverify().equals((short)1)){//显示加密后的手机号码
  			mobilephone   = userBaseAccountInfo.getMobilephone();  
  			mobilephonestr = userBaseAccountInfo.getMobilephone().substring(0,userBaseAccountInfo.getMobilephone().length()-(userBaseAccountInfo.getMobilephone().substring(3)).length())+"****"+userBaseAccountInfo.getMobilephone().substring(7);  
  	 		 
  		}
		 
		 if(userBaseAccountInfo.getCertificationnumber() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getCertificationnumber())  && userBaseAccountInfo.getIsreally() != null && userBaseAccountInfo.getIsreally().equals((short)1)){//显示加密后的身份证号码
			 certificationnumber    = userBaseAccountInfo.getCertificationnumber();
			 certificationnumberstr = userBaseAccountInfo.getCertificationnumber().substring(0, 1)+"**********"+userBaseAccountInfo.getCertificationnumber().substring(userBaseAccountInfo.getCertificationnumber().length()-1);
 		 }
		 
		 if(userBaseAccountInfo.getEmail() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getEmail())  && userBaseAccountInfo.getIsemailverify() != null && userBaseAccountInfo.getIsemailverify().equals((short)1)){//显示加密后的邮箱账号
			 email    = userBaseAccountInfo.getEmail();
			 emailstr = (userBaseAccountInfo.getEmail().substring(0, 2)+"****" + userBaseAccountInfo.getEmail().split("@")[1]);
			 
 		 }
		 
		 if(userBaseAccountInfo.getImagepath() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getImagepath())){//头像图片路径
			 imagepath = userBaseAccountInfo.getImagepath();
		 }
		 
		 if(userBaseAccountInfo.getAccounttype() != null){//用户类型：（个人、企业）1个人 0企业
			 accounttype = userBaseAccountInfo.getAccounttype().toString();
		 }
		 
		 if(userBaseAccountInfo.getIsmobileverify() != null){//手机验证 1已验证 0未验证
			 ismobileverify = userBaseAccountInfo.getIsmobileverify().toString();
		 }
		 
		 if(userBaseAccountInfo.getIsemailverify() != null){//邮件验证 1已验证 0未验证
			 isemailverify = userBaseAccountInfo.getIsemailverify().toString();
		 }
		 
		 if(userBaseAccountInfo.getIsreally() != null){//是否实名认证: 1已认证 0 未认证
			 isreally = userBaseAccountInfo.getIsreally().toString();
		 }
		 
		 if(userBaseAccountInfo.getLoginname() != null){//用户登录名
			 loginname = userBaseAccountInfo.getLoginname();
		 }
		 
		 if(userBaseAccountInfo.getRealname() != null){//身份证姓名
			 realName 	 = userBaseAccountInfo.getRealname();
			 realNameStr = userBaseAccountInfo.getRealname().substring(0, 1) +"**";
 		 }
  		 
		 if(userFsAccountInfo != null && userFsAccountInfo.getIsopenfsinfo().equals((short)1)){
			 isopenfsinfo =  userFsAccountInfo.getIsopenfsinfo().toString();//是否开通托管
			 if(userFsAccountInfo.getTradepass() != null && userFsAccountInfo.getTradepass().equals((short)0)){
				 userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());//解决用户开户后    第一次设置交易密码session更新不及时的问题
				 if(userFsAccountInfo != null){
					 if(userFsAccountInfo.getUsrcustid() != null){//电子账号
						 userFsAccountInfo.setUsrcustid(AES.getDecrypt(userFsAccountInfo.getUsrcustid()));
						}
						
						if(userFsAccountInfo.getUsrname() != null){//真实姓名
							userFsAccountInfo.setUsrname(AES.getDecrypt(userFsAccountInfo.getUsrname()));
						}
						
						if(userFsAccountInfo.getFsmobile() != null){//手机号码
							userFsAccountInfo.setFsmobile(AES.getDecrypt(userFsAccountInfo.getFsmobile()));
						}
					 request.getSession().setAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO, BaseController.getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
				 }
 			 }
			 
 			 if(userFsAccountInfo.getTradepass() != null && userFsAccountInfo.getTradepass().equals((short)1)){
 				tradepass = userFsAccountInfo.getTradepass().toString();//是否设置交易密码
			 }
		 }
		 
		 hashMap.put("imagepath", imagepath);
		 hashMap.put("email", email);
		 hashMap.put("emailstr", emailstr);
 		 hashMap.put("certificationnumber", certificationnumber);
 		 hashMap.put("certificationnumberstr", certificationnumberstr);
 		 hashMap.put("mobilephone", mobilephone);
		 hashMap.put("mobilephonestr", mobilephonestr);
 		 hashMap.put("accounttype", accounttype);
		 hashMap.put("ismobileverify", ismobileverify);
		 hashMap.put("isemailverify", isemailverify);
		 hashMap.put("isreally", isreally);
		 hashMap.put("loginname", loginname);
		 hashMap.put("id", id);
		 hashMap.put("isopenfsinfo", isopenfsinfo);
		 hashMap.put("tradepass", tradepass);
		 hashMap.put("realName", realName);
		 hashMap.put("realNameStr", realNameStr);
    	 return hashMap ;
	}
 	
	 // 如果关注性能问题可以考虑使用HttpClientConnectionManager
   	public static String doPost(Map<String, String> params,String httpUrl) throws ClientProtocolException, IOException{
   		String result = null;
   		HttpPost httpPost = new HttpPost(httpUrl);
   		CloseableHttpClient httpclient = HttpClients.createDefault();
   		if (params != null)
   		{
   			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
   			httpPost.setEntity(formEntiry);
   			CloseableHttpResponse response = httpclient.execute(httpPost);
   			try
   			{
   				HttpEntity entity = response.getEntity();
   				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   					result = EntityUtils.toString(entity, "UTF-8");
   				    EntityUtils.consume(entity);
   			} finally
   			{
   				response.close();
   			}
   		}
   		return result;
   	}
}
