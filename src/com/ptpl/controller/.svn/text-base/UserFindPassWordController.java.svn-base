package com.ptpl.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.EmailChannel;
import com.ptpl.model.EmailRecord;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.EmailChannelServiceI;
import com.ptpl.service.EmailRecordServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: UserFindPassWordController 
* @Description: TODO(用户找回密码    不限制登录 不属于安全中心操作) 
* @author cjm 
* @date 2017年3月15日 下午7:14:55 
*
 */
@Controller
@RequestMapping("/findpwd")
public class UserFindPassWordController extends BaseController{
	
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;//用户安全信息Service
	
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;//用户基本信息Service
	
	@Autowired
	private EmailChannelServiceI emailChannelServiceI;
	
	@Autowired
	private EmailRecordServiceI  emailRecordServiceI;
	
	@Autowired
	private SMSSendServiceI smsSendService;//发送短信接口
	/**
	 * 
	* @Title: finduser 
	* @Description: TODO(根据用户名检查用户是否存在) 
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
    * @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/finduser",method = RequestMethod.POST)
	public String finduser(HttpServletResponse response,HttpServletRequest request ){
		String uname = request.getParameter("uname");
		uname = setEncrypt(uname);
		Map<String,String> hashMap = new HashMap<String,String>();
		if(StringUtil.isEmpty(uname)){//用户名不能为null
			hashMap.put("result", "uname_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;	
		}
		
		UserBaseAccountInfo userBaseAccountInfo2 = new UserBaseAccountInfo();
 		userBaseAccountInfo2.setLoginname(uname);//用户名
 		userBaseAccountInfo2.setEmail(uname);//邮箱
 		userBaseAccountInfo2.setMobilephone(uname);//手机号码
 		//根据用户名查询密码
		UserAccountSafeInfo  userAccountSafeIn = userAccountSafeInfoServiceI.getLoginPassWordByLoginName(userBaseAccountInfo2);
		if(userAccountSafeIn == null){//用户名错误
			hashMap.put("result", "uname_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(userAccountSafeIn.getBaseid());
		if(userBaseAccountInfo == null ){//用户名错误
			hashMap.put("result", "uname_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
		session.setAttribute(Session_Constant.USERFINDPWDUSERSAFE, userAccountSafeIn);
		session.setAttribute(Session_Constant.USERFINDPWDUSERBASE, getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo));
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
	* @Title: emailPwdCheck 
	* @Description: TODO(检查用户邮箱是否正确) 
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/emailPwdCheck",method = RequestMethod.POST)
	public String emailPwdCheck(HttpServletResponse response,HttpServletRequest request){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
 		 Map<String,String> hashMap = new HashMap<String,String>();
		 if(userAccountSafeIn == null || userBaseAccountInfo == null){//session 失效 重新操作
			 hashMap.put("result", "user_error");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;  
		 }
		 
		 String email = request.getParameter("email");
		 if(StringUtil.isEmpty(email)){//邮箱不能为null
			 hashMap.put("result", "email_null");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null; 
		 }
		 
		 if(StringUtil.isEmpty(userBaseAccountInfo.getEmail())
				 || userBaseAccountInfo.getIsemailverify().equals((short)0)){//邮箱不正确
			 hashMap.put("result", "email_error");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null; 
		 }
		 
		 
		 if(email.equalsIgnoreCase(userBaseAccountInfo.getEmail())){//邮箱正确
			 String emailCode 	= "";//加密后的邮箱账号
			 String toKCode 	= "";//加密后的uid
			 String code 		= "";//加密后的时间
			 String username	= "";//用户名
			 String from_email  = "";//发送的邮箱账号
			 String password    = "";//发送的邮箱密码
			 String smtp_server = "";//邮件stmp服务器
			 String basePath = StringUtil.getBasePath(request);
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
					  System.out.println("code: "+code);
					  username	  = userBaseAccountInfo.getLoginname();//用户名
					  from_email  = channel2.getEmailsend();//发送的邮箱账号
					  password    = channel2.getEmailpass();//发送的邮箱密码
					  smtp_server = channel2.getEmailsmtp();//邮件stmp服务器
					  falg = SendMailTempalte.sendEamilForFindPwd(email, emailCode, toKCode, code, username, from_email, password, smtp_server, request);//发送邮件信息SendMailTempalte
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
 				 hashMap2.put("method", "sendEamilForFindPwd");
  				 String content = JSON.toJSONString(hashMap2);
				 emailRecord.setEmailcontent(content);//发送的email 内容
				 emailRecord.setEmail(email);//接受email账号
				 emailRecord.setSendemail(from_email);//发送端email
				 emailRecord.setSendtime(date);//发送时间
				 emailRecordServiceI.insertSelective(emailRecord);//记录邮件发送记录
 				 hashMap.put("result", "success");
 				 hashMap.put("email", email);
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
		 }else{
	 		 hashMap.put("result", "email_error");//邮箱不正确
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		 }
	}
	
	@RequestMapping(value = "/emailPwdByEmailLink")
	public ModelAndView emailPwdByEmailLink(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		
		String emailAccount = AES.getDecrypt(request.getParameter("email"),Session_Constant.EMAILCODEKEY);
		String id = AES.getDecrypt(request.getParameter("toKCode"),Session_Constant.EMAILCODEKEY);//用户的id
		String code = AES.getDecrypt(request.getParameter("code"), Session_Constant.EMAILCODEKEY);//时间
		System.out.println("用户的id："+id);
		System.out.println("时间： "+code);
		Date date = new Date();
		//如果相隔时间大于24小时
		if(date.getTime()-Long.parseLong(code)>24*60*60*1000){
			System.out.println("超过24小时，连接已失效");
			return null;
		}
		//根据id获得用户
		UserBaseAccountInfo ubai = userBaseAccountInfoServiceI.getUbaiAndUasiById(new BigDecimal(id));
		System.out.println("信息安全表id："+ubai.getUasi().getId());
		System.out.println("信息安全表： "+ubai.getUasi());
		ModelAndView mv= new ModelAndView();
		mv.addObject("ubai",getDecryptionUserBaseAccountInfoDetail(ubai));
		mv.setViewName("user/manager/findpwd/emailpwd2");
		return mv;
	}
	
	/**
	 * 
	* @Title: pwdtype 
	* @Description: TODO(选择手机/邮箱/密保问题方式找回密码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/pwdtype", method = RequestMethod.POST)
	public String pwdtype(HttpServletRequest request,HttpServletResponse response){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
 		 Map<String,String> hashMap = new HashMap<String,String>();
		 if(userAccountSafeIn == null || userBaseAccountInfo == null){//session 失效 重新操作
			 hashMap.put("result", "user_error");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;  
		 }
		 
		 String mark = request.getParameter("mark");
		 if(StringUtil.isEmpty(mark)){//参数错误
			 hashMap.put("result", "params_error");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		 }
  		 
		 if(mark.equalsIgnoreCase("phone")){//根据手机号找回
			 /*手机验证 1已验证 0未验证*/
			 if(userBaseAccountInfo.getIsmobileverify()!=null&&userBaseAccountInfo.getIsmobileverify().equals((short)1)){
				 hashMap.put("result", "phone_success");
			 }else{
				 hashMap.put("result", "phone_fail");
			 }
			 String str = JSON.toJSONString(hashMap);
 			 try {
 				 StringUtil.sendJsonData(response, str);
 			 } catch (IOException e) {
 				 e.printStackTrace();
 			 }
 		 }else if(mark.equalsIgnoreCase("encrypt")){//根据密保问题找回
 			 if(StringUtils.isNotBlank((userAccountSafeIn.getQuestion1()))
 			    &&StringUtils.isNotBlank((userAccountSafeIn.getQuestion2()))
 			    &&StringUtils.isNotBlank((userAccountSafeIn.getQuestion3()))){
 				 hashMap.put("result", "mibao_success");
 			 }else{
 				hashMap.put("result", "mibao_fail");
 			 }
 			 String str = JSON.toJSONString(hashMap);
 			 try {
 				 StringUtil.sendJsonData(response, str);
 			 } catch (IOException e) {
 				 e.printStackTrace();
 			 }
		 }else if(mark.equalsIgnoreCase("email")){//根据邮箱找回
			 if(userBaseAccountInfo.getIsemailverify() != null 
					 && userBaseAccountInfo.getIsemailverify().equals((short)1)){/*邮件验证 1已验证 0未验证*/
				 hashMap.put("result", "email_success");
				 String str = JSON.toJSONString(hashMap);
				 try {
					StringUtil.sendJsonData(response, str);
				 } catch (IOException e) {
	 				e.printStackTrace();
				 } 
				 return null;
			 }
			 
			 hashMap.put("result", "email_fail");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			 } catch (IOException e) {
 				e.printStackTrace();
			 } 
			 return null;
 		 }
		 
		return null;
	}
	
	/**
	 * 
	* @Title: phonepwd 
	* @Description: TODO(跳转根据手机号找回密码) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/phonepwd")
	public String phonepwd(HttpServletRequest request,HttpServletResponse response){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
		if(userAccountSafeIn == null || userBaseAccountInfo == null){
			return "user/manager/findpwd/findpwd";
		}
		return "user/manager/findpwd/phonepwd";
	}
	
	/**
	 * 
	* @Title: mibaopwd 
	* @Description: TODO(跳转根据密保问题找回密码页面) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/mibaopwd")
	public String mibaopwd(HttpServletRequest request,HttpServletResponse response){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
		if(userAccountSafeIn == null || userBaseAccountInfo == null){
			return "user/manager/findpwd/findpwd";
			
		}
		//return "user/manager/findpwd/findpwd";
		return "user/manager/findpwd/mibaopwd";
	}
	
	/**
	 * 
	* @Title: emailpwd 
	* @Description: TODO(跳转根据邮箱找回密码页面) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/emailpwd")
	public String emailpwd(HttpServletRequest request,HttpServletResponse response){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
		if(userAccountSafeIn == null || userBaseAccountInfo == null){
			return "user/manager/findpwd/findpwd";
		}
		
		if(userBaseAccountInfo.getIsemailverify() != null 
				&& userBaseAccountInfo.getIsemailverify().equals((short)1)){
			return "user/manager/findpwd/emailpwd";
		}
		
		return "user/manager/findpwd/findpwd";
	}
	
	/**
	 * 
	* @Title: pwdtypeList 
	* @Description: TODO(返回根据什么方式找回密码页面) 
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/pwdtypeList")
	public String pwdtypeList(HttpServletResponse response,HttpServletRequest request ){
		UserAccountSafeInfo userAccountSafeIn = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERSAFE);
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
		if(userAccountSafeIn != null && userBaseAccountInfo != null){
			return "user/manager/findpwd/pwdtype";
		}
		return "user/manager/findpwd/findpwd";//session 失效重新操作
	}
	
	//密保问题找回密码
	@RequestMapping(value="/validateQuestion",method = RequestMethod.POST)
	public void validateQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String baseid = request.getParameter("baseid");//用户的id 
		String answer1 = URLDecoder.decode(request.getParameter("answer1"),"UTF-8");
		String answer2 = URLDecoder.decode(request.getParameter("answer2"),"UTF-8");
		String answer3 = URLDecoder.decode(request.getParameter("answer3"),"UTF-8");
		//根据baseid获取用户信息安全表
		UserAccountSafeInfo uasi = userAccountSafeInfoServiceI.selectBaseId(new BigDecimal(baseid));
		Map<String,String> map = new HashMap<String,String>();
		if(uasi!=null){
			if(!answer1.equals(uasi.getAnswer1())){
				map.put("result1", "error1");
			}
			if(!answer2.equals(uasi.getAnswer2())){
				map.put("result2", "error2");
			}
			if(!answer3.equals(uasi.getAnswer3())){
				map.put("result3", "error3");
			}
			if(answer1.equals(uasi.getAnswer1())&&answer2.equals(uasi.getAnswer2())&&answer3.equals(uasi.getAnswer3())){
				map.put("result", "success");
			}
		}else{
			map.put("result4", "error4");
		}
		String jsonStr = JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	//重置密码
	@RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
	public void resetPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取用户的登录名
		//String loginname = URLDecoder.decode(request.getParameter("loginname"),"UTF-8");
		//获取baseid
		String id = request.getParameter("id");
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(StringUtils.isNotBlank(request.getParameter("pwd")));
		if(StringUtils.isNotBlank(request.getParameter("pwd"))){
			String pwd = URLDecoder.decode(request.getParameter("pwd"),"UTF-8");
			String repwd = URLDecoder.decode(request.getParameter("repwd"),"UTF-8");
			System.out.println("repwd:"+repwd);
			UserAccountSafeInfo uasi = new UserAccountSafeInfo();
			uasi.setId(new BigDecimal(id));
			//验证密码
			boolean flagPwd = checkPwd(pwd, map);
			//验证确认密码
			boolean flagRepwd = checkRepwd(pwd, repwd, map);
			if(flagPwd && flagRepwd){
				//BCrypt 加密密码后更新
				String encodePwd = BCrypt.hashpw(repwd, BCrypt.gensalt());
				uasi.setLoginpassword(encodePwd);//设置加密后的密码
				int row =userAccountSafeInfoServiceI.update(uasi);
				System.out.println("row: "+row);
				if(row > 0){
					map.put("result", "success");
				}
			}
		}else{
			map.put("pwdErrMsg", "密码不能为空");
		}
		sendJsonData(response, JSON.toJSONString(map));
	}
	
	/**
	 * UI 3.验证密码 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 */
	private boolean checkPwd(String pwd, Map<String, Object> map) {
		if (StringUtil.isEmpty(pwd)) {
			map.put("pwdErrMsg", "密码不能为空");
			return false;
		}
		if (pwd.length() < 8 || pwd.length() > 20) {
			map.put("pwdErrMsg", "密码长度为8～20位字符");
			return false;
		}
		//Pattern p = Pattern.compile("^[a-zA-Z0-9-_]+$");
		Pattern p = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
		Matcher m = p.matcher(pwd);
		if (!m.find()) {
			map.put("pwdErrMsg", "必须包含数字和字母");
			return false;
		}
		p = Pattern.compile("^\\S+$");
		m = p.matcher(pwd);
		if (!m.find()) {
			map.put("pwdErrMsg", "密码不能包含空格");
			return false;
		}
		return true;
	}
	/**
	 * UI 4.验证重复密码 两次密码不一样 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 */
	private boolean checkRepwd(String pwd, String repwd, Map<String, Object> map) {
		if (!pwd.equals(repwd)) {
			map.put("repwdErrMsg", "两次输入的密码不一致");
			return false;
		}
		return true;
	}
	
	//点击获取验证码
	@RequestMapping(value = "/clickGet",method = RequestMethod.POST)
	public void clickGet(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,String> map = new HashMap<String,String>();
		String phone = request.getParameter("phone");
		System.out.println("邪门了phone：  "+phone);
		UserBaseAccountInfo ubai = /*new UserBaseAccountInfo()*/(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USERFINDPWDUSERBASE);
		//ubai.setMobilephone(phone);
		//ubai= userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
		if(StringUtils.isBlank(phone)){
			map.put("result", "phone_null");//手机号码为空
			String str = JSON.toJSONString(map);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}
		if(!StringUtil.isMobile(phone)){
			//手机号码格式不正确
			map.put("result", "phone_error");
			String str = JSON.toJSONString(map);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}else{			
			if(!phone.equals(ubai.getMobilephone())){
				//用户未注册该手机号
				map.put("result", "unRegistered");
				String str = JSON.toJSONString(map);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					
				}
				return;
			}
			if(!ubai.getIsmobileverify().equals((short)1)){//手机未验证
				map.put("result", "unVerified");
				String str = JSON.toJSONString(map);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					
				}
				return;
			}
		}
		
		Random random = new Random();
		String code = String.valueOf(random.nextInt(899999) + 100000)/*"222222"*/;
		System.out.println("code: "+code);
//		boolean flag = SMSSend.smsSend(phone, code)/*true*/;
		boolean flag = smsSendService.SMSSend4Code(ubai.getMobilephone(), code, ubai.getLoginname(), ubai.getId());
		if(flag){
			System.out.println();
			session.setAttribute(Session_Constant.PHONERETRIEVEPASSWORD, code);//短信验证码
	    	session.setAttribute(Session_Constant.MOBILEPHONESECURITY, phone);//手机号码
	    	map.put("result", "success");
	    	String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}else{
			map.put("result", "fail");
	    	String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		} 
	}
	
	//用户填写完验证码之后，开始重置密码
	@RequestMapping(value="/phoneResetPassword",method=RequestMethod.POST)
	public void phoneResetPassword(){
		String phone = request.getParameter("phone");//电话号码
		String code = request.getParameter("code");//验证码
		String session_phone = (String) request.getSession().getAttribute(Session_Constant.MOBILEPHONESECURITY);
		String session_code = (String) request.getSession().getAttribute(Session_Constant.PHONERETRIEVEPASSWORD);
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isBlank(session_phone)||StringUtils.isBlank(session_code)){
			//Session已过期
			map.put("result", "session_error");
			String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}
		if(!phone.equals(session_phone)){
			map.put("result", "phone_error");
			String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}
		if(!code.equals(session_code)){
			map.put("result", "code_error");
			String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			return;
		}
		if(phone.equals(session_phone)&&code.equals(session_code)){
			map.put("result", "success");
			String str = JSON.toJSONString(map);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}
		return;
	}
}
