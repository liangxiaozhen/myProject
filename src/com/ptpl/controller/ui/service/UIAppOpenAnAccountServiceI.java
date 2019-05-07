package com.ptpl.controller.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;

public interface UIAppOpenAnAccountServiceI {

	/**
	 * 
	* @Title: userOpenAnAccount 
	* @Description: TODO(徽商账号开立) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> userOpenAnAccount(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,HttpSession session);
  
	/**
	 * 
	* @Title: tradePassWord 
	* @Description: TODO(设置/修改交易密码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @param userFsAccountInfo
	* @param @param session
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> tradePassWord(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo,HttpSession session);
	
	void tradePassWordHtml(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo,HttpSession session);
	
}
