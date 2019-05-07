package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moneymoremore.util.HttpClientHandler;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.EmailChannel;
import com.ptpl.model.EmailRecord;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserLog;
import com.ptpl.service.EmailChannelServiceI;
import com.ptpl.service.EmailRecordServiceI;
import com.ptpl.service.SMSSendRecordServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.service.UserNameRuleModuleServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.IdcardValidator;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.StringUtil;

/**
 * 
 * @ClassName: UserSecurityCenterController
 * @Package com.ptpl.controller
 * @Description: TODO(用户安全中心)
 * @author chenjiaming
 * @date 2016年7月15日 下午3:09:35
 * @version V1.0
 */
@Controller
@RequestMapping("/user/securitycenter")
public class UserSecurityCenterController extends BaseController {
 	
	@Autowired
	private SMSSendServiceI sMSSendServiceI;
	/**
	 * 邮件发送记录service
	 */
	@Autowired
	private EmailRecordServiceI emailRecordServiceI;
	/**
	 * 用户账号安全信息service
	 */
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;

	/**
	 * 用户基本信息service
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**
	 * 用户名规则设置service
	 */
	@Autowired
	private UserNameRuleModuleServiceI userNameRuleModuleService;
	
	/**
	 * 用户托管账户信息service
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	
	@Autowired
	private EmailChannelServiceI emailChannelServiceI; 
	
	@Autowired
	private UserLogServiceI userLogServiceI;
 	 
	/**
	 * 
	 * @Title: list
	 *  @Description: TODO(跳转用户安全中心list 页面) 
	 *  @param @return
	 *  参数说明 @return String 返回类型 
	 *  @author chenjiaming 
	 *  @throws
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response , Model model) {
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();//用户基本信息
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();//用户安全信息
 		if(userBaseAccountInfo == null && userAccountSafeInfo == null){
			return "user/manager/login";
		}
 		
 		UserFsAccountInfo userFsAccountInfo	= userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
 		 //把用户托管账户信息实体对象放进session
		 if(userFsAccountInfo != null){
 			 session.setAttribute(Session_Constant.USERFSACCOUNTINFO,getDecryptionUserFsAccountInfoDetail(userFsAccountInfo)); 
		 }
		 
		 if(StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone())
				 && userBaseAccountInfo.getIsmobileverify() != null && userBaseAccountInfo.getIsmobileverify().equals((short)1)){//显示加密后的手机号码
			 String ss = userBaseAccountInfo.getMobilephone().substring(0,userBaseAccountInfo.getMobilephone().length()-(userBaseAccountInfo.getMobilephone().substring(3)).length())+"****"+userBaseAccountInfo.getMobilephone().substring(7);  
			 userBaseAccountInfo.setMobilephonestr(ss);
		 }
		 
		 if(StringUtil.isNotEmpty(userBaseAccountInfo.getCertificationnumber()) && userBaseAccountInfo.getIsreally() != null && userBaseAccountInfo.getIsreally().equals((short)1)){//显示加密后的身份证号码
			 String srt = userBaseAccountInfo.getCertificationnumber().substring(0, 1)+"**********"+userBaseAccountInfo.getCertificationnumber().substring(userBaseAccountInfo.getCertificationnumber().length()-1);
			 userBaseAccountInfo.setCertificationnumberstr(srt);
		 }
		 
		 if(StringUtil.isNotEmpty(userBaseAccountInfo.getEmail()) && userBaseAccountInfo.getIsemailverify() != null && userBaseAccountInfo.getIsemailverify().equals((short)1)){//显示加密后的邮箱账号
			 String srt = (userBaseAccountInfo.getEmail().substring(0, 2)+"****" + userBaseAccountInfo.getEmail().split("@")[1]);
 			 userBaseAccountInfo.setEmailstr(srt);
		 }
		 
  		 if(userAccountSafeInfo != null && userAccountSafeInfo.getLastlogintime() != null){
  			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 			userAccountSafeInfo.setLastlogintimestr(dateFormat.format(userAccountSafeInfo.getLastlogintime()));
  		 }
   		 
 		 session.setAttribute("userBaseAccountInfo", userBaseAccountInfo);
 		 session.setAttribute("userAccountSafeInfo", userAccountSafeInfo);
 		 session.setAttribute("userFsAccountInfo", userFsAccountInfo);
   	     return "user/manager/securitycenter/list";
	}
	
	@RequestMapping(value = "/openAnAccountCheck",method = RequestMethod.POST)
	public String openAnAccountCheck(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		Map<String,String> hashMap = new HashMap<String,String>();
		if(userBaseAccountInfo.getLoginname() == null){//开户必须填写用户名
			hashMap.put("result", "loginname_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(userBaseAccountInfo.getIsmobileverify() == null || !userBaseAccountInfo.getIsmobileverify().equals((short)1)){/*手机验证 1已验证 0未验证*/
			hashMap.put("result", "phone_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(userBaseAccountInfo.getIsemailverify() == null || !userBaseAccountInfo.getIsemailverify().equals((short)1)){/*邮件验证 1已验证 0未验证*/
			hashMap.put("result", "email_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(userBaseAccountInfo.getIsreally() == null || !userBaseAccountInfo.getIsreally().equals((short)1)){/*是否实名认证: 1已认证 0 未认证*/
			hashMap.put("result", "realName_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
//		if(userAccountSafeInfo.getQuestion1() == null 
//				|| userAccountSafeInfo.getQuestion2() == null
//				|| userAccountSafeInfo.getQuestion3() == null
//				|| userAccountSafeInfo.getAnswer1() == null
//				|| userAccountSafeInfo.getAnswer2() == null
//				|| userAccountSafeInfo.getAnswer3() == null){
//			
//			hashMap.put("result", "question_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
		session.setAttribute(Session_Constant.SECURITYCENTEROPENANACCOUNTCODE, "success");
		hashMap.put("result", "success");
		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
				e.printStackTrace();
		}
  		return null;
		
	}
	
	/**
	 * 
	* @Title: getopenAnAccountList 
	* @Description: TODO(跳转用户开户页面) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/getopenAnAccountList")
	public String getopenAnAccountList(HttpServletRequest request,HttpServletResponse response){
		String str = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTEROPENANACCOUNTCODE);
		if(StringUtil.isEmpty(str)){
			return null;
		}
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		UserAccountSafeInfo accountSafeInfo = getUserAccountSafeInfo();
		request.setAttribute("baseAccountInfo", baseAccountInfo);
		request.setAttribute("accountSafeInfo", accountSafeInfo);
 		return "user/manager/securitycenter/openAnAccount";
	}
	
	/*
	 * 其他业务 跳转开户或者 实名认证页面 
	 * 
	 */
	@RequestMapping(value = "/findopenAnAccountList")
	public String findOpenAnAccountList(HttpServletRequest request,HttpServletResponse response){
 		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		UserAccountSafeInfo accountSafeInfo = getUserAccountSafeInfo();
		if(baseAccountInfo.getIsreally().equals((short)0)){//未实名认证 跳转实名认证页面
			return "user/manager/securitycenter/realNameAuthentication";
		}
		request.setAttribute("baseAccountInfo", baseAccountInfo);
		request.setAttribute("accountSafeInfo", accountSafeInfo);
 		return "user/manager/securitycenter/openAnAccount";
	}
  	
//	/**
//	 * 
//	* @Title: sendSSMByOpenAnAccount 
//	* @Description: TODO(发送短信（第三方开户）) 
//	* @param @param request
//	* @param @param response
//	* @param @return    设定文件 
//	* @return String    返回类型 
//	* @author   cjm  
//	* @throws
//	 */
//	@RequestMapping(value = "/sendSSMByOpenAnAccount",method=RequestMethod.POST)
//	public String sendSSMByOpenAnAccount(HttpServletRequest request ,HttpServletResponse response){
//		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
// 		Map<String,String> hashMap = new HashMap<String,String>();
// 		String phone = baseAccountInfo.getMobilephone();
// //		Random random = new Random();
////		String code = String.valueOf(random.nextInt(899999) + 100000);
////		boolean falg = SMSSend.smsSend(phone, code);
//		String code = "111111";
//		session.setAttribute(Session_Constant.SECURITYCENTEROPENANACCOUNTSENDSSM, code);
//		boolean falg = true;
//		if(falg){
//			hashMap.put("result","success");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}else{
//			hashMap.put("result","fail");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}
// 	}
	
// /**
//  * 
// * @Title: openAnAccount 
// * @Description: TODO(第三方开户) 
// * @param @param request
// * @param @param response
// * @param @return    设定文件 
// * @return String    返回类型 
// * @author   cjm  
// * @throws
//  */
//  @RequestMapping(value = "/openAnAccount",method = RequestMethod.POST)
//  public String openAnAccount(HttpServletRequest request ,HttpServletResponse response){
//	  UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
//  	  String code = request.getParameter("code");
//	  Map<String,String> hashMap = new HashMap<String,String>();
// 	  String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTEROPENANACCOUNTSENDSSM);
//	  if(tocode == null ){
//		  hashMap.put("result", "tocode_null");
//		  String str = JSON.toJSONString(hashMap);
//		  try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		  return null; 
//	  }
// 	  
//	  if(StringUtil.isEmpty(code)){//请输入验证码
//		  hashMap.put("result", "code_null");
//		  String str = JSON.toJSONString(hashMap);
//		  try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		  return null;
//	  }
//  	   
// 	  if(!tocode.equals(code)){//验证码不一致
//		  hashMap.put("result", "code_error");
//		  String str = JSON.toJSONString(hashMap);
//		  try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		  return null; 
//	  }
// 	 
//	 UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(baseAccountInfo.getId());
//	 int count = 0;
//	 if(userFsAccountInfo == null){
//		 UserFsAccountInfo accountInfo = new UserFsAccountInfo();
//		 accountInfo.setBaseid(baseAccountInfo.getId());  //用户ID
//		 accountInfo.setIsopenfsinfo((short)1);  //是否开通托管账户  1已开通 0未开通
//		 accountInfo.setOpeningtime(new Date());  //开通时间
//		 accountInfo.setUsrname(baseAccountInfo.getRealname());  //用户真实名称
//		 accountInfo.setAccounttype((short)1);  //账户类型（1个人，2企业）
//		 count =  userFsAccountInfoServiceI.insertSelective(accountInfo);
//	 }else {
//		 userFsAccountInfo.setUsrname(baseAccountInfo.getRealname());  //用户真实名称
//		 userFsAccountInfo.setIsopenfsinfo((short)1);  //是否开通托管账户  1已开通 0未开通
//		 count = userFsAccountInfoServiceI.updateById(userFsAccountInfo);
//	 } 
//	 
//	 if(count > 0){
//		 session.removeAttribute(Session_Constant.SECURITYCENTEROPENANACCOUNTSENDSSM);
//		 hashMap.put("result", "success");
//		  String str = JSON.toJSONString(hashMap);
//		  try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		  return null;
//	 }else{
//		 hashMap.put("result", "fail");
//		  String str = JSON.toJSONString(hashMap);
//		  try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		  return null;
//	 }
//	 
//   }
  
  /**
   * 
  * @Title: sendSSMByLoginPassWord 
  * @Description: TODO(发送短信验证码（重置登录密码）) 
  * @param @param request
  * @param @param response
  * @param @return    设定文件 
  * @return String    返回类型 
  * @author   cjm  
  * @throws
   */
  @RequestMapping(value = "/sendSSMByLoginPassWord",method = RequestMethod.POST)
  public String sendSSMByLoginPassWord(HttpServletRequest request,HttpServletResponse response){
	  UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
	  Map<String,String> hashMap = new HashMap<String,String>();
	  if(baseAccountInfo == null 
			  ||  baseAccountInfo.getIsmobileverify() == null || !(baseAccountInfo.getIsmobileverify().equals((short)1))
			  || baseAccountInfo.getMobilephone() == null){//参数非法
		 hashMap.put("result", "params_error"); 
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	  }
	  
//  		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999) + 100000);
//		boolean falg = sMSSendServiceI.SMSSend4Code(baseAccountInfo.getMobilephone(), code, baseAccountInfo.getLoginname(), baseAccountInfo.getId());
// 		
		String code = "111111";
		boolean falg = true;
		session.setAttribute(Session_Constant.SECURITYCENTERLOGINPASSWORDSENDSSM, code);//
	    if(falg){
	    	hashMap.put("result", "success"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }else{
	    	 hashMap.put("result", "fail"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }
   }
  
  /**
   * 
  * @Title: loginPwdChange 
  * @Description: TODO(修改登录密码) 
  * @param @param request
  * @param @param response
  * @param @return    设定文件 
  * @return String    返回类型 
  * @author   cjm  
  * @throws
   */
  @RequestMapping(value = "/loginPwdChange",method = RequestMethod.POST)
  public  String loginPwdChange(HttpServletRequest request,HttpServletResponse response){
	  UserAccountSafeInfo accountSafeInfo = getUserAccountSafeInfo();
	  Map<String,String> hashMap = new HashMap<String,String>();
	  if(accountSafeInfo == null){
		  try {
			StringUtil.sendJsonData(response, "logout");
		} catch (IOException e) {
 			e.printStackTrace();
		} 
		  return null;
	  }
	  
	  String code = request.getParameter("code");
	  String password = request.getParameter("newpwd");
	  if(StringUtil.isEmpty(code)){//验证码不能空
		  hashMap.put("result", "code_null");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  return null;
	  }
	  
	  if(StringUtil.isEmpty(password)){//新登录密码不能空
		  hashMap.put("result", "newpwd_null");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  return null;
	  }
	  
	  String toCode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERLOGINPASSWORDSENDSSM);
	  if(StringUtil.isEmpty(toCode)){//手机验证码短信未发送
		  hashMap.put("result", "toCode_null");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  return null;
	  }
	  
	  if(!code.equals(toCode)){//短信验证码不一致
		  hashMap.put("result", "code_error");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  return null;
	  }
 	  
	  String pwt = BCrypt.hashpw(password, BCrypt.gensalt());
	  accountSafeInfo.setLoginpassword(pwt);//修改登录密码
	  int count = 0;
	  count = userAccountSafeInfoServiceI.update(accountSafeInfo);
	  if(count > 0){
		  hashMap.put("result", "success");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
			insertUserLogDateil(request, getUserBaseAccountInfo(), "修改登录密码");//保存用户操作日志
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  
		  return null;
	  }else{
		  hashMap.put("result", "fail");
		  String str = JSON.toJSONString(hashMap);
		  try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		  return null;
	  }
 	  
  }
  
 /**
  *  
 * @Title: sendSSMByPhoneInsert 
 * @Description: TODO(发送手机短信验证码（手机号码验证）) 
 * @param @param request
 * @param @param response
 * @param @return    设定文件 
 * @return String    返回类型 
 * @author   cjm  
 * @throws
  */
 @RequestMapping(value = "/sendSSMByPhoneInsert",method = RequestMethod.POST)
 public String sendSSMByPhoneInsert(HttpServletRequest request,HttpServletResponse response){
	 UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
	 Map<String,String> hashMap = new HashMap<String,String>();
	 String phone = request.getParameter("phone");
	 if(StringUtil.isEmpty(phone)){//手机号码为null
		 hashMap.put("result", "phone_null");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 if(!StringUtil.isMobile(phone)){//手机号码格式不正确
		 hashMap.put("result", "phone_error");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 
	 UserBaseAccountInfo ubaiw = new UserBaseAccountInfo();
	 ubaiw.setMobilephone(setEncrypt(phone));//加密手机号码查询
	 UserBaseAccountInfo userBaseAccountInfo2 = null;
	 String magess = "";
	 try{
 		  userBaseAccountInfo2 = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubaiw);
	 }catch(Exception e){
		 magess = "1";
	 }
	 
	 if(magess.equalsIgnoreCase("1")){//网络异常
		 hashMap.put("result", "network_error");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			
		}
		return null;
	 }
	 
	 if(userBaseAccountInfo2 != null){//手机号已注册
		 hashMap.put("result", "phoneed");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			
		}
		return null;
	 }
	 
	 if(accountInfo.getIsmobileverify() != null && accountInfo.getIsmobileverify().equals((short)1)){//手机已验证
		 hashMap.put("result", "authenticated");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			
		}
		return null;
	 }
	 
// 		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999) + 100000);
//		boolean falg = sMSSendServiceI.SMSSend4Code(accountInfo.getMobilephone(), code, accountInfo.getLoginname(), accountInfo.getId());
   
		String code = "111111";
	    boolean falg = true;
	    if(falg){
	    	session.setAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM, code);//短信验证码
	    	session.setAttribute(Session_Constant.SECURITYCENTERPHONE, phone);//手机号码
	    	hashMap.put("result", "success"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }else{
	    	hashMap.put("result", "fail"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }
   }
 
 /**
  * 
 * @Title: phoneInsertSave 
 * @Description: TODO(手机验证) 
 * @param @param request
 * @param @param response
 * @param @return    设定文件 
 * @return String    返回类型 
 * @author   cjm  
 * @throws
  */
 @RequestMapping(value = "/phoneInsertSave",method = RequestMethod.POST)
 public String phoneInsertSave(HttpServletRequest request,HttpServletResponse response){
	 UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
	 String code = request.getParameter("code");
	 String phone = request.getParameter("phone");
	 String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM);//短信验证码
	 String tophone = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONE);//手机号码
	 Map<String,String> hashMap = new HashMap<String,String>();
	 if(StringUtil.isEmpty(code)){
		 hashMap.put("result", "code_null"); //验证码不能为null
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 if(StringUtil.isEmpty(phone)){
		 hashMap.put("result", "phone_null"); //手机号码不嫩为null
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 if(StringUtil.isEmpty(tocode) || StringUtil.isEmpty(tophone)){
		 hashMap.put("result", "tocode_null"); //请点击发送短信验证码
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 if(!code.equalsIgnoreCase(tocode)){
		 hashMap.put("result", "code_error"); //短信验证码错误
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 
	 if(!tophone.equalsIgnoreCase(phone)){//请输入发送短信验证码的手机号
		 hashMap.put("result", "tophone_null"); 
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
	 accountInfo =  getEncryptUserBaseAccountInfoDetail(accountInfo);//从session 取出来的数据是解密的数据  需要再次加密保存进数据库  然后解密保存进session
	 accountInfo.setMobilephone(setEncrypt(phone));
	 accountInfo.setIsmobileverify((short)1);
	 int count = 0;
	 count =  userBaseAccountInfoServiceI.update(accountInfo);
	 if(count > 0){
		 session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(accountInfo));// UserBaseAccountInfo
   		 hashMap.put("result", "success"); //验证成功
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
			insertUserLogDateil(request, getUserBaseAccountInfo(), "手机号码验证");//保存用户操作日志
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }else{
		 hashMap.put("result", "fail"); //验证失败
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
	 }
  }
   
  /**
	 * 
	* @Title: realNameAuthenticationlist 
	* @Description: TODO(显示实名制认证页面) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/realNameAuthenticationlist")
	public String realNameAuthenticationlist(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		if(baseAccountInfo != null && !baseAccountInfo.getIsreally().equals((short)1)){
			return "user/manager/securitycenter/realNameAuthentication";
		}
		return "user/manager/securitycenter/list";
	}
	
	/**
	 * 
	* @Title: realNameAuthentication 
	* @Description: TODO(实名制认证) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/realNameAuthentication",method = RequestMethod.POST)
	public String realNameAuthentication(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		String realname = request.getParameter("realname");
		String identity = request.getParameter("identity");
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(realname)){
			hashMap.put("result", "realname_null");//请输入身份证姓名
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		if(StringUtil.isEmpty(identity)){
			hashMap.put("result", "identity_null");//请输入身份证号码
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		if(baseAccountInfo.getIsreally() != null && baseAccountInfo.getIsreally().equals((short)1)){
			hashMap.put("result", "realNameed");//已实名认证
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		 IdcardValidator idcardValidator = new IdcardValidator();
		 boolean falg = idcardValidator.isValidatedAllIdcard(identity);//身份证检查
		 if(!falg){//身份证检验失败
			 hashMap.put("result", "identity_error");
			  String str = JSON.toJSONString(hashMap);
			  try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			  return null;
		 }
		 
		 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		 ubai.setCertificationnumber(setEncrypt(identity));
		 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		 if(userBaseAccountInfo45 != null){//身份证号已存在
			 hashMap.put("result", "identity_error2");
			  String str = JSON.toJSONString(hashMap);
			  try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			  return null; 
		 }
		 
		 
		 	String url = "http://api.id98.cn/api/idcard";
			Map<String,String> HttpClHashMap = new HashMap<>();
			HttpClHashMap.put("appkey", "c62f920f2738cec64dde7cefa2c26b81");
			HttpClHashMap.put("name", realname);
			HttpClHashMap.put("cardno", identity);
 			String result = "";
			try {
				result = doPost(HttpClHashMap, url);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
 			if(StringUtil.isEmpty(result)){
				 hashMap.put("result", "fail");//实名认证失败
					String str = JSON.toJSONString(hashMap);
					try {
						StringUtil.sendJsonData(response, str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null; 
			 }
			JSONObject jsonObject = JSONObject.parseObject(result);
			String isok = jsonObject.getString("isok");//0：查询失败 ， 1：查询成功
			String code = jsonObject.getString("code");//1	一致 2	不一致 3	无此身份证号码
			String data = jsonObject.getString("data");
			JSONObject jsonObject2 = JSONObject.parseObject(data);
			String sex = jsonObject2.getString("sex");//M：男性 ， F：女性
			if(!(isok.equals("1") && code.equals("1"))){//code:1 一致 ; isok:1  查询成功
				hashMap.put("result", "matching");//姓名匹配失败
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			} 
			if(StringUtil.isNotEmpty(sex)){
 				Short sexShort = (short) (sex.equals("M") ? 1 : 0 );
				baseAccountInfo.setSex(sexShort);
			}
			String certificationtype = "01"; 
			/*证件类型  01-身份证18位
					02-身份证15位
					20-其它
					25-企业社会信用代码
			注：企业开户时上送20或社会信用号25*/
			if(StringUtils.trimToEmpty(identity).length() < 18){
				certificationtype = "02";
			}
			
			baseAccountInfo =  getEncryptUserBaseAccountInfoDetail(baseAccountInfo);//从session 取出来的数据是解密的数据  需要再次加密保存进数据库  然后解密保存进session

			baseAccountInfo.setRealname(setEncrypt(realname));
			baseAccountInfo.setCertificationnumber(setEncrypt(identity));
			baseAccountInfo.setIsreally((short)1); /*是否实名认证: 1已认证 0 未认证*/
			baseAccountInfo.setCertificationtype(certificationtype);
			int count = 0;
			count = userBaseAccountInfoServiceI.update(baseAccountInfo);
			if(count > 0){
				session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(baseAccountInfo));// 解密保存进session
 				hashMap.put("result", "success");//实名认证成功
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
					insertUserLogDateil(request, getUserBaseAccountInfo(), "实名认证");//保存用户操作日志
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}else {
				hashMap.put("result", "fail");//实名认证失败
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
	}
	
	/**
	 * 
	* @Title: sendSSmBySQInsert 
	* @Description: TODO(密保问题设置 发送手机验证码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendSSmBySQInsert",method = RequestMethod.POST)
	public String sendSSmBySQInsert(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		Map<String,String> hashMap = new HashMap<>();
		if(baseAccountInfo.getMobilephone() == null || baseAccountInfo.getIsmobileverify() == null || !baseAccountInfo.getIsmobileverify().equals((short)1)){
			hashMap.put("result", "phone_error");//手机未验证
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
// 		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999) + 100000);
//		boolean falg = sMSSendServiceI.SMSSend4Code(baseAccountInfo.getMobilephone(), code, baseAccountInfo.getLoginname(), baseAccountInfo.getId());
 		 
		
		String code = "111111";
	    boolean falg = true;
	    if(falg){
	    	session.setAttribute(Session_Constant.SECURITYCENTERQUESTIONSM, code);//短信验证码
 	    	hashMap.put("result", "success"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }else{
	    	hashMap.put("result", "fail"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }
  	}
	
	/**
	 * 
	* @Title: securityQuestionInsertOne 
	* @Description: TODO(验证手机短信验证码（密保问题设置/修改）) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/securityQuestionInsertOne",method = RequestMethod.POST)
	public String securityQuestionInsertOne(HttpServletRequest request,HttpServletResponse response){
		String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERQUESTIONSM);
 		String code = request.getParameter("code");
 		Map<String,String> hashMap = new HashMap<>();
 		if(StringUtil.isEmpty(code)){//短信验证为空
 			hashMap.put("result", "code_null"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		}
 		
 		if(StringUtil.isEmpty(tocode)){
 			hashMap.put("result", "tocode_null");//请点击短信发送按钮
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		}
 		
 		if(!code.equals(tocode)){
 			hashMap.put("result", "code_error");//短信验证码错误
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		}
     	hashMap.put("result", "success");//验证通过
     	session.removeAttribute(Session_Constant.SECURITYCENTERQUESTIONSM);
     	session.setAttribute(Session_Constant.SECURITYCENTERQUESTIONCHECKSUCCESS, "success");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: securityQuestionInsertTwo 
	* @Description: TODO(密保问题设置  选择问题和填写答案) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/securityQuestionInsertTwo",method = RequestMethod.POST)
	public String securityQuestionInsertTwo(HttpServletRequest request,HttpServletResponse response){
		String question1  =  request.getParameter("question1"); 
		String question2  =  request.getParameter("question2");
		String question3  =  request.getParameter("question3"); 
		question1 = getQuestion(question1);
		question2 = getQuestion(question2);
		question3 = getQuestion(question3);
 		String answer1    =  request.getParameter("answer1");
		String answer2	  =  request.getParameter("answer2");
		String answer3	  =  request.getParameter("answer3");
 		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(question1)){//请选择问题一
			hashMap.put("result", "question1_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(answer1)){//请输入答案一
			hashMap.put("result", "answer1_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(question2)){//请选择问题二
			hashMap.put("result", "question2_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(answer2)){//请输入答案二
			hashMap.put("result", "answer2_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(question3)){//请选择问题三
			hashMap.put("result", "question3_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		 
		if(StringUtil.isEmpty(answer3)){//请输入答案三
			hashMap.put("result", "answer3_null");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		String hashMap2 = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERQUESTIONCHECKSUCCESS);
		if(StringUtil.isEmpty(hashMap2)){//操作超时 手机短信实失效
			hashMap.put("result", "timer_out");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
 		
		hashMap.put("result", "success");
 		hashMap.put("question1", question1);
		hashMap.put("question2", question2);
		hashMap.put("question3", question3);
		hashMap.put("answer1", answer1);
		hashMap.put("answer2", answer2);
		hashMap.put("answer3", answer3);
 		String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: securityQuestionInsertThree 
	* @Description: TODO(密保问题设置  确认密保问题和答案 保存) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/securityQuestionInsertThree",method = RequestMethod.POST)
	public String securityQuestionInsertThree(HttpServletRequest request,HttpServletResponse response){
		String question1  =  request.getParameter("question1"); 
		String question2  =  request.getParameter("question2");
		String question3  =  request.getParameter("question3"); 
  		String answer1    =  request.getParameter("answer1");
		String answer2	  =  request.getParameter("answer2");
		String answer3	  =  request.getParameter("answer3");
 		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(question1)){ 
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(answer1)){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(question2)){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(answer2)){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		if(StringUtil.isEmpty(question3)){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		 
		if(StringUtil.isEmpty(answer3)){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		
		String hashMap2 = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERQUESTIONCHECKSUCCESS);
		if(StringUtil.isEmpty(hashMap2)){//操作超时 手机短信实失效
			hashMap.put("result", "timer_out");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
 			}
			 return null;
		}
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		Short isUpdate = 1;
		if(userAccountSafeInfo.getAnswer1() != null 
				|| userAccountSafeInfo.getAnswer2() != null
				| userAccountSafeInfo.getQuestion1() != null
				|| userAccountSafeInfo.getQuestion2() != null){
			isUpdate = 2;
		}
 		userAccountSafeInfo.setAnswer1(answer1);
 		userAccountSafeInfo.setAnswer2(answer2);
 		userAccountSafeInfo.setAnswer3(answer3);
 		userAccountSafeInfo.setQuestion1(question1);
 		userAccountSafeInfo.setQuestion2(question2);
 		userAccountSafeInfo.setQuestion3(question3);
 		userAccountSafeInfo.setCustquestion("2");/* 客户自定义安保问题 1自定义 2系统问题*/
 		int count = 1;
 		count = userAccountSafeInfoServiceI.update(userAccountSafeInfo);
 		if(count > 0){
 			session.removeAttribute(Session_Constant.SECURITYCENTERQUESTIONCHECKSUCCESS);
 			hashMap.put("result", "success");
 	  		String str = JSON.toJSONString(hashMap);
 			 try {
 				StringUtil.sendJsonData(response, str);
 				String stre = "密保问题设置";
 				if(isUpdate.equals((short)2)){
 					stre = "密保问题修改";
 				}
 				insertUserLogDateil(request, getUserBaseAccountInfo(), stre);//保存用户操作日志
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}else{
 			hashMap.put("result", "fail");
 	  		String str = JSON.toJSONString(hashMap);
 			 try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
		 
		return null;
	}
	
	
	/**
	 * 
	* @Title: sendEmailByBindEmail 
	* @Description: TODO(发送邮箱验证链接（邮箱绑定）) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendEmailByBindEmail",method = RequestMethod.POST)
	public String sendEmailByBindEmail(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(email)){//邮箱账号不能为null
			hashMap.put("result", "email_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!StringUtil.isEmail(email)){//邮箱账号格式错误
			hashMap.put("result", "email_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(accountInfo != null && accountInfo.getIsemailverify() != null && accountInfo.getIsemailverify().equals((short)1)){//邮箱已验证过了
			hashMap.put("result", "authenticated");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		

		 UserBaseAccountInfo ubaiw = new UserBaseAccountInfo();
		 ubaiw.setEmail(setEncrypt(email));
		 UserBaseAccountInfo userBaseAccountInfo2 = null;
		 String magess = "";
		 try{
	 		  userBaseAccountInfo2 = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubaiw);
		 }catch(Exception e){
			 magess = "1";
		 }
		 
		 if(magess.equalsIgnoreCase("1")){//网络异常
			 hashMap.put("result", "network_error");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				
			}
			return null;
		 }
		 
		 if(userBaseAccountInfo2 != null){//邮箱已注册
			 hashMap.put("result", "email_registered");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				
			}
			return null;
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
				  toKCode 	  = AES.getEncrypt(accountInfo.getId().toString(), Session_Constant.EMAILCODEKEY);//加密后的uid
				  code 		  = AES.getEncrypt(String.valueOf(date.getTime()), Session_Constant.EMAILCODEKEY);//加密后的时间
				  username	  = accountInfo.getLoginname();//用户名
				  from_email  = channel2.getEmailsend();//发送的邮箱账号
				  password    = channel2.getEmailpass();//发送的邮箱密码
				  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
				  falg = SendMailTempalte.sendEamilForBindEmail(email, emailCode, toKCode, code, username, from_email, password, smtp_server, request);//发送邮件信息SendMailTempalte
			 }
		 }
 		 EmailRecord emailRecord = new EmailRecord();
		 emailRecord.setEmail(email);//发送email
		 emailRecord.setBaseid(accountInfo.getId());//用户ID
		 emailRecord.setUsername(accountInfo.getLoginname() == null ? email : accountInfo.getLoginname());//用户名
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
  		if(falg){
 			 hashMap.put("result", "success");
			 hashMap.put("email", email);
			 String str = JSON.toJSONString(hashMap);
			 emailRecord.setStatus((short)1);/*发送状态 （1成功 0失败）*/
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 try {
				 StringUtil.sendJsonData(response, str);
				 insertUserLogDateil(request, getUserBaseAccountInfo(), "发送邮箱验证链接");//保存用户操作日志
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }else{
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 emailRecord.setStatus((short)0);/*发送状态 （1成功 0失败）*/
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 try {
				 StringUtil.sendJsonData(response, str);
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }
 		
 	}
	
	/**
	 * 
	* @Title: bindEmail 
	* @Description: TODO(邮件验证) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/bindEmail")
	public String bindEmail(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		String toKCode = request.getParameter("toKCode");
 		if(StringUtil.isEmpty(code) || StringUtil.isEmpty(email) || StringUtil.isEmpty(toKCode)){
 			request.setAttribute("magess", "邮箱验证失败！因参数错误");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
		
  		String DecryptCode = AES.getDecrypt(code, Session_Constant.EMAILCODEKEY);
		String DecryptEmail = AES.getDecrypt(email, Session_Constant.EMAILCODEKEY);
		String DecryptToKCode = AES.getDecrypt(toKCode, Session_Constant.EMAILCODEKEY);
 		Date date = new Date();
		long timerSs = (1000 * 60 * 60 * 60 * 24 );//24小时
 		if((date.getTime() - Long.parseLong(DecryptCode)) > timerSs){//时间超时
 			request.setAttribute("magess","邮箱验证失败！因时间超时");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
		
 		UserBaseAccountInfo  baseAccountInfo  = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(DecryptToKCode));
		if(baseAccountInfo == null){
			request.setAttribute("magess", "邮箱验证失败！因参数错误");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
		
		baseAccountInfo.setEmail(setEncrypt(DecryptEmail));
		baseAccountInfo.setIsemailverify((short)1);
		int count = 0;
		count = userBaseAccountInfoServiceI.update(baseAccountInfo);//这里没有从session 取对象  不需要再次加密
		if(count > 0){
			UserBaseAccountInfo accountInfo1 = getUserBaseAccountInfo();
			baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);
			if(accountInfo1 != null){
				session.setAttribute(Session_Constant.USER, baseAccountInfo);// 
 			}
			request.setAttribute("magess","绑定!");
			insertUserLogDateil(request, baseAccountInfo, "邮箱验证");//保存用户操作日志
			return "user/manager/securitycenter/smallfile/listTemplateByEmailSuccess";
		}else {
			request.setAttribute("magess","因网络异常!邮件绑定失败！请重新操作！");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
 	}
	
	
	/***********************************************安全中心  修改开始***********************************************************************/
	/**
	 * 
	* @Title: sendSSMByOldPhone 
	* @Description: TODO(发送手机短信验证码  （根据原手机号码）) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendSSMByOldPhone",method = RequestMethod.POST)
	public String sendSSMByOldPhone(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> hashMap = new HashMap<String,String>();
		String phone = request.getParameter("phone");
		if(StringUtil.isEmpty(phone)){//手机为null
			hashMap.put("result", "phone_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!StringUtil.isMobile(phone)){//手机格式错误
			hashMap.put("result", "phone_format");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(!accountInfo.getMobilephone().equals(phone)){//原手机号错误
			hashMap.put("result", "phone_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
// 		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999) + 100000);
//		boolean flag = sMSSendServiceI.SMSSend4Code(accountInfo.getMobilephone(), code, accountInfo.getLoginname(), accountInfo.getId());
   
		boolean flag = true;
		String code = "111111";
 		session.setAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM, code);
		if(flag){
			hashMap.put("result", "success");//短信发送成功
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}else{
			hashMap.put("result", "fail");//短信发送失败
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
}
	/**
	 * 
	* @Title: updatePhoneByOldPhoneOne 
	* @Description: TODO(检查输入的手机号、验证码是否正确) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/updatePhoneByOldPhoneOne",method = RequestMethod.POST)
	public String updatePhoneByOldPhoneOne(HttpServletRequest request,HttpServletResponse response){
 		String code = request.getParameter("code");//短信验证码
		String phone = request.getParameter("phone");//原手机号码
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(phone)){//手机号为null
			hashMap.put("result", "phone_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(StringUtil.isEmpty(code)){//手机短信验证码为null
			hashMap.put("result", "code_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
   		
		if(!StringUtil.isMobile(phone)){//手机号格式错误
			hashMap.put("result", "phone_format");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		
		String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM);
		if(StringUtil.isEmpty(tocode)){//请点击短信验证码发送按钮
			hashMap.put("result", "tocode_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!code.equals(tocode)){//短信验证码错误
			hashMap.put("result", "code_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(!accountInfo.getMobilephone().equals(phone)){//输入的手机号与原手机号不一致
			hashMap.put("result", "phone_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
 	 
		
		hashMap.put("result", "success");
		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
				e.printStackTrace();
		}
  		return null;
	}
	
	/**
	 * 
	* @Title: sendSSMByNewPhone 
	* @Description: TODO(发送短信验证码（新手机短信验证码）) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendSSMByNewPhone",method = RequestMethod.POST)
	public String sendSSMByNewPhone(HttpServletRequest request,HttpServletResponse response){
		String phone = request.getParameter("phone");
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(phone)){//手机号码为null
			hashMap.put("result", "phone_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!StringUtil.isMobile(phone)){//手机号码格式错误
			hashMap.put("result", "phone_format");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM);
		if(StringUtil.isEmpty(tocode)){//操作超时
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo = new UserBaseAccountInfo();
		accountInfo.setMobilephone(setEncrypt(phone));
		UserBaseAccountInfo accountInfo2 = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(accountInfo);
		if(accountInfo2 != null){//手机号已注册
			hashMap.put("result", "phone_existing");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
// 		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999) + 100000);
//		boolean falg = sMSSendServiceI.SMSSend4Code(phone, code);
 		 
    
		boolean falg = true;
		String code = "111111";
		session.setAttribute(Session_Constant.SECURITYCENTERNEWPHONESENDSSM, code);
		session.setAttribute(Session_Constant.SECURITYCENTERPHONE, phone);
		if(falg){
			hashMap.put("result", "success");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}else{
			hashMap.put("result", "fail");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
 	}
	
	/**
	 * 
	* @Title: updatePhoneByOldPhoneTwo 
	* @Description: TODO(保存新手机号码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value ="/updatePhoneByOldPhoneTwo",method = RequestMethod.POST)
	public String updatePhoneByOldPhoneTwo(HttpServletRequest request,HttpServletResponse response){
		String phone = request.getParameter("phone");
		String code  = request.getParameter("code");
		Map<String,String> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(phone)){//手机号码为null
			hashMap.put("result", "phone_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(StringUtil.isEmpty(code)){//验证码为null
			hashMap.put("result", "code_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERNEWPHONESENDSSM);
		String tophone = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONE);
		if(StringUtil.isEmpty(tocode) || StringUtil.isEmpty(tophone)){//操作超时请重新操作
			hashMap.put("result", "tocode_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!tocode.equals(code)){//验证码错误
			hashMap.put("result", "code_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!tophone.equals(tophone)){//请输入发送短信的手机号码
			hashMap.put("result", "phone_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		baseAccountInfo =  getEncryptUserBaseAccountInfoDetail(baseAccountInfo);//从session 取出来的数据是解密的数据  需要再次加密保存进数据库  然后解密保存进session

		baseAccountInfo.setMobilephone(setEncrypt(phone));
		int count = 0;
		count = userBaseAccountInfoServiceI.update(baseAccountInfo);
		if(count > 0){
			session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(baseAccountInfo));// 解密保存进session

			session.removeAttribute(Session_Constant.SECURITYCENTERNEWPHONESENDSSM);
			session.removeAttribute(Session_Constant.SECURITYCENTERPHONE);
    		hashMap.put("result", "success");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
				insertUserLogDateil(request, getUserBaseAccountInfo(), "修改手机号码");//保存用户操作日志
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
			
		}else{
			hashMap.put("result", "fail");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
			
		}
 	}
	
	/**
	 * 
	* @Title: sendEmailByOldEmail 
	* @Description: TODO(发送邮箱验证码 根据原邮箱修改手机号码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendEmailByOldEmail",method = RequestMethod.POST)
	public String sendEmailByOldEmail(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		Map<String,String> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(baseAccountInfo.getEmail()) || !baseAccountInfo.getIsemailverify().equals((short)1)){//邮箱未验证或邮箱为空
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
  		 String username	= "";//用户名
		 String from_email  = "";//发送的邮箱账号
		 String password    = "";//发送的邮箱密码
		 String smtp_server = "";//邮件stmp服务器
		 Random random = new Random();
 		 String code = String.valueOf(random.nextInt(899999)+100000);//验证码
 		 Date date =  new Date();
		 EmailChannel emailChannel = new EmailChannel();
		 emailChannel.setIsuse((short)0);//是否启用1禁用0启用
		 List<EmailChannel> emailChannels = emailChannelServiceI.findEmailChannelsByEmailChannel(emailChannel);
		 boolean falg = true;
 		 if(emailChannels.size() > 0){
			 EmailChannel channel2 = StringUtil.getEmailChannel(emailChannels);
			 if(channel2.getEmailsmtp() != null && channel2.getEmailsend() != null && channel2.getEmailpass() != null){
 				  username	  = baseAccountInfo.getLoginname();//用户名
				  from_email  = channel2.getEmailsend();//发送的邮箱账号
				  password    = channel2.getEmailpass();//发送的邮箱密码
				  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
				  falg = SendMailTempalte.sendEamilCodeByUpdatePhone(baseAccountInfo.getEmail(), code, username, from_email, password, smtp_server);//发送邮件信息SendMailTempalte
			 }
		 }
 		 EmailRecord emailRecord = new EmailRecord();
 		 emailRecord.setEmail(baseAccountInfo.getEmail());//发送email
 		 emailRecord.setBaseid(baseAccountInfo.getId());//用户ID
 		 emailRecord.setUsername(baseAccountInfo.getLoginname() == null ? baseAccountInfo.getEmail() : baseAccountInfo.getLoginname());//用户名
 		 emailRecord.setSendtype((short)2);  /*发送方式（1手工,2系统）*/
 		 Map<String,String> hashMap2 = new HashMap<String,String>();
 		 hashMap2.put("code", code);
 		 hashMap2.put("username", username);
 		 hashMap2.put("email", baseAccountInfo.getEmail());
 		 hashMap2.put("method", "sendEamilCodeByUpdatePhone");
 		 String content = JSON.toJSONString(hashMap2);
 		 emailRecord.setEmailcontent(content);//发送的email 内容
 		 emailRecord.setSendemail(from_email);//发送端email
 		 emailRecord.setSendtime(date);//发送时间
   		if(falg){
   			 emailRecord.setStatus((short)1); /*发送状态 （1成功 0失败）*/
 			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 session.setAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM, code);
			 hashMap.put("result", "success");
 			 String str = JSON.toJSONString(hashMap);
			 try {
				 StringUtil.sendJsonData(response, str);
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }else{
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 emailRecord.setStatus((short)0); /*发送状态 （1成功 0失败）*/
 			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 try {
				 StringUtil.sendJsonData(response, str);
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }
  	}
	
	/**
	 * 
	* @Title: updatePhoneByOldEmailOne 
	* @Description: TODO(根据原邮箱修改手机号 验证邮箱验证码是否正确) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/updatePhoneByOldEmailOne",method = RequestMethod.POST)
	public String updatePhoneByOldEmailOne(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		Map<String,String> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(code)){//请输入邮箱验证码
			hashMap.put("result", "code_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		
		String tocode = (String) request.getSession().getAttribute(Session_Constant.SECURITYCENTERPHONESENDSSM);
		if(StringUtil.isEmpty(tocode)){//请点击邮箱验证码发送按钮
			hashMap.put("result", "tocode_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		if(!code.equals(tocode)){//验证码错误
			hashMap.put("result", "code_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		hashMap.put("result", "success");
  		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: UpdateEmailCheck 
	* @Description: TODO(检查原邮箱是否正确) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/UpdateEmailCheck" , method = RequestMethod.POST)
	public String UpdateEmailCheck (HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		Map<String , String> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(email)){//邮箱账号不能为null
			hashMap.put("result", "email_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		
		if(!StringUtil.isEmail(email)){//邮箱账号格式错误
			hashMap.put("result", "email_format");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(!accountInfo.getEmail().equals(email)){//邮箱账号和原邮箱账号不一致
			hashMap.put("result", "email_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		hashMap.put("result", "success");
		session.setAttribute(Session_Constant.EMAILSUCCESSCODE, "success");
 		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
 	}
	
	/**
	 * 
	* @Title: sendEmailByNewEmail 
	* @Description: TODO(给新邮箱发送验证链接) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendEmailByNewEmail",method = RequestMethod.POST)
	public String sendEmailByNewEmail(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		String emailcode = (String) request.getSession().getAttribute(Session_Constant.EMAILSUCCESSCODE);
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(email)){//请输入邮箱账号
			hashMap.put("result", "email_null");
 	 		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		}
		
		if(!StringUtil.isEmail(email)){//邮箱格式错误
			hashMap.put("result", "email_format");
 	 		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		}
		
		if(StringUtil.isEmpty(emailcode)){//请返回上一步重新操作
			hashMap.put("result", "emailcode_error");
 	 		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo accountInfo4 = new UserBaseAccountInfo();
		accountInfo4.setEmail(setEncrypt(email));
		UserBaseAccountInfo accountInfo2  =	userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(accountInfo4);
		if(accountInfo2 != null){//邮箱已注册
			hashMap.put("result", "email_existing");
 	 		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo	accountInfo = getUserBaseAccountInfo();
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
				  toKCode 	  = AES.getEncrypt(accountInfo.getId().toString(), Session_Constant.EMAILCODEKEY);//加密后的uid
				  code 		  = AES.getEncrypt(String.valueOf(date.getTime()), Session_Constant.EMAILCODEKEY);//加密后的时间
				  username	  = accountInfo.getLoginname();//用户名
				  from_email  = channel2.getEmailsend();//发送的邮箱账号
				  password    = channel2.getEmailpass();//发送的邮箱密码
				  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
				  falg = SendMailTempalte.sendEamilForUpdateEmail(email, emailCode, toKCode, code, username, from_email, password, smtp_server, request);//发送邮件信息SendMailTempalte
			 }
		 }
 		 
 		 EmailRecord emailRecord = new EmailRecord();
 		 emailRecord.setEmail(email);//发送email
 		 emailRecord.setBaseid(accountInfo.getId());//用户ID
 		 emailRecord.setUsername(accountInfo.getLoginname() == null ? email : accountInfo.getLoginname());//用户名
 		 emailRecord.setSendtype((short)2);  /*发送方式（1手工,2系统）*/
 		 Map<String,String> hashMap2 = new HashMap<String,String>();
 		 hashMap2.put("emailCode", emailCode);
 		 hashMap2.put("toKCode", toKCode);
 		 hashMap2.put("code", code);
 		 hashMap2.put("username", username);
 		 hashMap2.put("email", email);
 		 hashMap2.put("method", "sendEamilForUpdateEmail");
 		 String content = JSON.toJSONString(hashMap2);
 		 emailRecord.setEmailcontent(content);//发送的email 内容
 		 emailRecord.setSendemail(from_email);//发送端email
 		 emailRecord.setSendtime(date);//发送时间
 		if(falg){
 			 emailRecord.setStatus((short)1);/*发送状态 （1成功 0失败）*/
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 hashMap.put("result", "success");
			 hashMap.put("email", email);
			 String str = JSON.toJSONString(hashMap);
			 try {
				 StringUtil.sendJsonData(response, str);
				 insertUserLogDateil(request, getUserBaseAccountInfo(), "发送邮箱修改验证链接");//保存用户操作日志
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }else{
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 emailRecord.setStatus((short)0);/*发送状态 （1成功 0失败）*/
			 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
			 try {
				 StringUtil.sendJsonData(response, str);
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 return null;
		 }
  	}
	
	
	/**
	 * 
	* @Title: updateEmail 
	* @Description: TODO(邮件验证(邮箱修改)) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/updateEmail")
	public String updateEmail(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		String toKCode = request.getParameter("toKCode");
 		if(StringUtil.isEmpty(code) || StringUtil.isEmpty(email) || StringUtil.isEmpty(toKCode)){
 			request.setAttribute("magess", "因参数错误");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
		
		 
 		String DecryptCode = AES.getDecrypt(code, Session_Constant.EMAILCODEKEY);
		String DecryptEmail = AES.getDecrypt(email, Session_Constant.EMAILCODEKEY);
		String DecryptToKCode = AES.getDecrypt(toKCode, Session_Constant.EMAILCODEKEY);
 		Date date = new Date();
		long timerSs = (1000 * 60 * 60 * 60 * 24 );//24小时
 		if((date.getTime() - Long.parseLong(DecryptCode)) > timerSs){//时间超时
 			request.setAttribute("magess","因时间超时");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
 		
		UserBaseAccountInfo  baseAccountInfo  = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(DecryptToKCode));
		if(baseAccountInfo == null){
			request.setAttribute("magess", "邮箱验证失败！因参数错误");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
		
		baseAccountInfo.setEmail(setEncrypt(DecryptEmail));
		baseAccountInfo.setIsemailverify((short)1);
		int count = 0;
		count = userBaseAccountInfoServiceI.update(baseAccountInfo);
		if(count > 0){
			UserBaseAccountInfo accountInfo1 = getUserBaseAccountInfo();
			baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);
			if(accountInfo1 != null){
				session.setAttribute(Session_Constant.USER, baseAccountInfo);// UserBaseAccountInfo
 			}
			request.setAttribute("magess","重置");
			insertUserLogDateil(request, baseAccountInfo, "修改邮箱");//保存用户操作日志
			return "user/manager/securitycenter/smallfile/listTemplateByEmailSuccess";
		}else {
			request.setAttribute("magess","因网络异常");
			return "user/manager/securitycenter/smallfile/listTemplateByEmailFail";
		}
 	}
	 
	/**
	 * 
	* @Title: getQuestion 
	* @Description: TODO(根据传过来的数字返回文字) 
	* @param @param string
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	public String getQuestion(String string) {
		String str = "";
		switch (string) {
		case "1":
			str = "您母亲的生日是？";
			break;
		case "2":
			str = "您母亲的姓名是？";
			break;
		case "3":
			str = "您父亲的生日是？";
			break;
		case "4":
			str = "您父亲的姓名是？";
			break;
		case "5":
			str = "您孩子的生日是？";
			break;
		case "6":
			str = "您孩子的姓名是？";
			break;
		case "7":
			str = "您配偶的生日是？";
			break;
		case "8":
			str = "您配偶的姓名是？";
			break;
		case "9":
			str = "您的出生地是哪里？";
			break;
		case "10":
			str = "您最喜欢什么颜色？";
			break;
		case "11":
			str = "您是什么学历？";
			break;
		case "12":
			str = "您的属相是什么的？";
			break;
		case "13":
			str = "您小学就读的是哪所学校？";
			break;
		case "14":
			str = "您最崇拜谁？";
			break;
		case "15":
			str = "您打字经常用什么输入法？";
			break;
		case "16":
			str = "您是什么时间参加工作的？";
			break;
		default:
			break;
		}
		return str;
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
   	
   	
   	/**
   	 * 保存用户操作日志信息
   	 * @param request
   	 * @param userBaseAccountInfo
   	 * @param opercontent
   	 */
   	public void insertUserLogDateil(HttpServletRequest request,
   			UserBaseAccountInfo userBaseAccountInfo,String opercontent){
   		if(StringUtil.isEmpty(opercontent)){
   			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,保存用户操作日志内容不能为空  'opercontent' 不能为空");
   			}
   			return ;
   		}
   		if(userBaseAccountInfo == null){
   			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,保存用户操作日志内容不能为空  'userBaseAccountInfo' 不能为空 ");
   			}
   			return ;
   		}
		// 更新最后登录ip
		String ipAddr = StringUtil.getIpAddr(request);
		UserLog userLog = new UserLog();
		userLog.setBaseid(userBaseAccountInfo.getId()); //用户Id
		userLog.setUsername(userBaseAccountInfo.getLoginname()); //用户名
		userLog.setUsertype((short)1); //用户类型 1普通用户  2管理员用户
		if(StringUtil.isNotEmpty(ipAddr)){
			userLog.setIp(ipAddr); //IP
		}
		
		Cookie[] cookies = request.getCookies();
		String cookieStr = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name != null && name.equals("JSESSIONID")) {
					String value = cookie.getValue();
					if (value != null) {
						cookieStr = value;
						break;
					}
				}
			}
		}
		if(StringUtil.isNotEmpty(cookieStr)){
 			// 更新最后登录cookie值
			userLog.setCookie(cookieStr); //cookie
		}
		Date date = new Date();
		userLog.setOpercontent(opercontent); //操作内容（涉及修改）
		userLog.setOpertime(date); //操作时间
		int count = 0;
		count = userLogServiceI.insertSelective(userLog);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,用户日志保存数据库失败！！！！保存的Baseid是："
			+userBaseAccountInfo.getId()+",Username是："+userBaseAccountInfo.getLoginname()
			+",ip是："+ipAddr+",cookie是："+cookieStr+",opercontent是："+opercontent+",Opertime是:"+StringUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
   			}
		}
   		 
   	}
 
}
