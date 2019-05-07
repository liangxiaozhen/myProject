package com.ptpl.controller.ui.service;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.StringUtil;

public interface UIAppFindPasswordServiceI{
	
	/**
	 * 通过手机修改密码时，点击获取验证码
	 * @param phoneNumber 电话号码
	 * @throws Exception 
	 */
	
	public Map<String,String>  getValidationCode(String phoneNumber,HttpSession session);
	

	/**
	 * 用户填写完手机验证码之后 ，开始验证
	 * @param  phoneNumber 手机号
	 * @param  code  验证码
	 * @throws Exception 
	 */
	public Map<String,String> checkPhoneNumberCode(String phoneNumber,String code,HttpSession session);
	
	/**
	 * 用户填写新的密码，开始重置密码了
	 * @param pwd     新密码
	 * @param repwd   确认新密码
	 * @throws Exception 
	 */
	public Map<String,Object> phoneResetPassword(String phoneNumber,String code,String pwd,String repwd,HttpSession session);
	
}
