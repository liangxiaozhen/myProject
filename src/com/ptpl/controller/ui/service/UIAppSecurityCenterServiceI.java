package com.ptpl.controller.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;

/**
 * 
* @ClassName: UIAppSecurityCenterServiceI 
* @Description: TODO(安全中心接口) 
* @author cjm 
* @date 2017年6月11日 上午10:03:38 
*
 */
public interface UIAppSecurityCenterServiceI {

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
	public Map<String,String> realNameCertification(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session);

	/**
	 * 
	* @Title: sendPhoneSsmCode 
	* @Description: TODO(发送手机验证码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> sendPhoneCode(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session);


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
	public Map<String,String> sendNewPhoneCode(HttpServletRequest request,HttpServletResponse response,HttpSession session);
	
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
	public Map<String,String> checkPhoneCode(HttpServletRequest request,HttpServletResponse response,HttpSession session);
	/**
	 * 
	* @Title: phoneUpdate 
	* @Description: TODO(修改手机号码   ) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> phoneUpdate(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session);

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
	public Map<String,String> emailBind(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo);
	
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
	public Map<String,String>  emailUpdateCheck(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo);
 	 
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
			UserBaseAccountInfo userBaseAccountInfo,UserAccountSafeInfo userAccountSafeInfo,HttpSession session);
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
	public Map<String,String> getUserBaseDetail(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo);
}
