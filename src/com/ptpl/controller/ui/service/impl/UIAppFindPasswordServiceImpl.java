package com.ptpl.controller.ui.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.service.UIAppFindPasswordServiceI;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.StringUtil;

public class UIAppFindPasswordServiceImpl implements UIAppFindPasswordServiceI{
	
	//自己定义常量   存放电话号码
	public static String APPPHONENUMBER = "APP_PHONE_NUMBER";
	//存放手机验证码
	public static String APPPHONECODE = "APP_PHONE_CODE";
	
	@Autowired
	@Qualifier("userAccountSafeInfoService")
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;//用户安全信息Service

	@Autowired
	@Qualifier("userBaseAccountInfoService")
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;//用户基本信息Service
	@Autowired
	private SMSSendServiceI smsSendService;//发送短信接口
		
	/**
	 * 通过手机修改密码时，点击获取验证码
	 * @param phoneNumber 电话号码
	 * @throws Exception 
	 */
	//@RequestMapping("/getValidationCode")
	public Map<String,String>  getValidationCode(String phoneNumber,HttpSession session){
		
		System.out.println("电话号码： "+phoneNumber);//13923495439
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isNotBlank(phoneNumber)){
			if(!StringUtil.isMobile(phoneNumber)){
				map.put("status", "0");
				map.put("resultCode", "fail");//手机号码格式不正确
				map.put("Msg", "手机号码格式不正确");
			}else{
				UserBaseAccountInfo ubai = new UserBaseAccountInfo();
				ubai.setMobilephone(BaseController.setEncrypt(phoneNumber));
				ubai = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
				if(ubai == null){
					//该手机号未注册
					map.put("status", "0");
					map.put("resultCode", "fail");
					map.put("Msg", "该手机号未注册");
				}else{
					Random random = new Random();
					String code = String.valueOf(random.nextInt(899999) + 100000)/*"222222"*/;
					System.out.println("code: "+code);
					//boolean flag = SMSSend.smsSend(phoneNumber, code)/*true*/;
					boolean flag = smsSendService.SMSSend4Code(ubai.getMobilephone(), code, ubai.getLoginname(),ubai.getId());
					if(flag){
						System.out.println("session: "+session);
						session.setAttribute(APPPHONENUMBER, phoneNumber);//存放手机电话号码
						session.setAttribute(APPPHONECODE, code);//存放验证码
						map.put("status", "1");
						map.put("resultCode", "success");
						map.put("Msg", "验证码已发送");
					}else{
						map.put("status", "0");
						map.put("resultCode", "fail");
						map.put("Msg", "验证码发送失败");
					}
				}
			}
		}else{
			map.put("status", "0");
			map.put("resultCode", "fail");//手机号码为空
			map.put("Msg", "手机号码为空");
		}
		return map;
	}

	/**
	 * 用户填写完手机验证码之后 ，开始验证
	 * @param  phoneNumber 手机号
	 * @param  code  验证码
	 * @throws Exception 
	 */
	//@RequestMapping("/checkPhoneNumberCode")
	public Map<String,String> checkPhoneNumberCode(String phoneNumber,String code,HttpSession session){
		
		Map<String,String> map = new HashMap<String,String>();
		//获取session中的code和phoneNumber
		String session_phone = (String)session.getAttribute(APPPHONENUMBER);		
		String session_code = (String)session.getAttribute(APPPHONECODE);
		System.out.println("app_session_phone: "+session_phone);
		System.out.println("app_session_code: "+session_code);
		if(!code.equals(session_code)){
			//验证码错误
			map.put("status", "0");
			map.put("resultCode", "fail");
			map.put("Msg", "验证码错误");
			return map;
		}
		if(!phoneNumber.equals(session_phone)){
			//电话号码错误
			map.put("status", "0");
			map.put("resultCode", "fail");
			map.put("Msg", "电话号码错误");
			return map;
		}	
		if(code.equals(session_code)&&phoneNumber.equals(session_phone)){
			//验证通过
			map.put("status", "1");
			map.put("resultCode", "success");
			map.put("Msg", "验证成功");
		}
		return map;
	}
	
	/**
	 * 用户填写新的密码，开始重置密码了
	 * @param pwd     新密码
	 * @param repwd   确认新密码
	 * @throws Exception 
	 */
	//@RequestMapping("/phoneResetPassword")
	public Map<String,Object> phoneResetPassword(String phoneNumber,String code,String pwd,String repwd,HttpSession session){
		
		Map<String,Object> map = new HashMap<String,Object>();
		//验证新密码
		boolean flagPwd = checkPwd(pwd,map);
		if(!flagPwd){
			return map;
		}
		//验证确认新密码
		boolean flagRepwd = checkRepwd(pwd, repwd, map);
		if(!flagRepwd){
			return map;
		}
		if(flagPwd&&flagRepwd){
			//获取session中的电话号码
			String session_phone = (String)session.getAttribute(APPPHONENUMBER);
			String session_code = (String)session.getAttribute(APPPHONECODE);
			if(session_phone==null || session_code==null){
				map.put("status", "0");
				map.put("resultCode", "fail");
				map.put("Msg", "操作超时");
				return map;
			}
			if(!phoneNumber.equals(session_phone)){
				map.put("status", "0");
				map.put("resultCode", "fail");
				map.put("Msg", "手机号码错误");
				return map;
			}
			if(!code.equals(session_code)){
				map.put("status", "0");
				map.put("resultCode", "fail");
				map.put("Msg", "验证码错误");
				return map;
			}
			System.out.println("重置密码时session_phone： "+session_phone);
			UserBaseAccountInfo ubai = userBaseAccountInfoServiceI.getUbaiAndUasiByPhone(BaseController.setEncrypt(session_phone));
			UserAccountSafeInfo uasi = new UserAccountSafeInfo();
			uasi.setId(ubai.getUasi().getId());
			//BCrypt 加密密码后更新
			String encodePwd = BCrypt.hashpw(repwd, BCrypt.gensalt());
			uasi.setLoginpassword(encodePwd);//设置加密后的密码
			int row =userAccountSafeInfoServiceI.update(uasi);
			System.out.println("row: "+row);
			if(row > 0){
				map.put("status", "1");
				map.put("resultCode", "success");
				map.put("Msg", "修改成功");
			}else{
				map.put("status", "0");
				map.put("resultCode", "fail");
				map.put("Msg", "修改失败");
			}
		}
		return map;
	}
	
	/**
	 * UI 3.验证密码 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 */
	private boolean checkPwd(String pwd, Map<String, Object> map) {
		if (StringUtil.isEmpty(pwd)) {
			map.put("status", "0");
			map.put("resultCode", "fail");//密码不能为空
			map.put("Msg","新密码不能为空");
			return false;
		}
		if (pwd.length() < 8 || pwd.length() > 20) {
			map.put("status", "0");
			map.put("resultCode", "fail");//密码长度为8～20位字符
			map.put("Msg","新密码长度需为8~20位字符");
			return false;
		}
		//Pattern p = Pattern.compile("^[a-zA-Z0-9-_]+$");
		Pattern p = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
		Matcher m = p.matcher(pwd);
		if (!m.find()) {
			map.put("status", "0");
			map.put("resultCode", "fail");//必须包含数字和字母
			map.put("Msg", "新密码必须包含数字和字母");
			return false;
		}
		p = Pattern.compile("^\\S+$");
		m = p.matcher(pwd);
		if (!m.find()) {
			map.put("status","0");
			map.put("resultCode", "fail");//密码不能包含空格
			map.put("Msg", "新密码不能包含空格");
			return false;
		}
		return true;
	}
	/**
	 * UI 4.验证重复密码 两次密码不一样 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 */
	private boolean checkRepwd(String pwd, String repwd, Map<String, Object> map) {
		if (!pwd.equals(repwd)) {
			map.put("status", "0");
			map.put("resultCode", "fail");//两次输入的密码不一致
			map.put("Msg", "两次输入的密码不一致");
			return false;
		}
		return true;
	}

}
