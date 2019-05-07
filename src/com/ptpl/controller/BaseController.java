package com.ptpl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.User;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.web.Iconstant.Iconstant;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StateUtils;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

import com.ptpl.web.util.AES;


public class BaseController {
	
	public static Log logger = LogFactory.getLog(BaseController.class);
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected SimpleDateFormat sf1 =new SimpleDateFormat("yyyyMMdd");
	protected SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
	protected static DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00");
	protected static DecimalFormat df1 = new DecimalFormat("##########0.00");
	//状态处理工具
	public static StateUtils state = new StateUtils(); 
	//AES加密解密处理类
	public static AES aes = new AES();
	/**
	 * @author cjm
	 * @param request
	 * @param response
	 * @param session
	 * 
	 */
	@ModelAttribute  
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,HttpSession session){  
	    this.response= response;  
	    this.request = request;
	    this.session = session;
	}
	
	
	public  SimpleDateFormat sd =new SimpleDateFormat("yyyyMMdd");

	//初始化分页相关信息
		protected void initPage(Map<String,Object> map, String pageNum, String pageSize){
			Integer num = 1;
			Integer size = 20;
			
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			//String sortString = "id.desc";
		//	Order.formString(sortString);
			PageHelper.startPage(num, size);
//			map.put("startIndex", BaseController.getStartIndex(num,size));
//			map.put("endIndex", BaseController.getStartIndex(num, size)+size);
//			map.put("pageNum", num);
//			map.put("totalPage", totalPage);
			map.put("pageSize", size);
//			map.put("totalCount", totalCount);
			
		}
		protected  PageInfo<Object> initPagehelper(Map map,List list){
			PageInfo<Object> pagehelper = new PageInfo<Object>(list);
			pagehelper.setFirstPage(1);
			Integer lastPageNum =0;
			Integer size = (Integer)map.get("pageSize");
			
			if(pagehelper.getTotal()%size==0){
				lastPageNum = (int)pagehelper.getTotal()/size;
			}else{
				lastPageNum = (int)pagehelper.getTotal()/size + 1 ;
			}
			
			pagehelper.setLastPage(lastPageNum);
			
			return pagehelper;
		}
		/**
		 * 通过JSON向前台发送数据
		 * 
		 * @param data
		 * @throws IOException
		 */
		protected void sendJsonData(HttpServletResponse response, String data)
				throws Exception
		{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(data);
			out.flush();
			out.close();
		}
		
		//获取用户信息
		public UserBaseAccountInfo getUserBaseAccountInfo(){
			UserBaseAccountInfo ubai = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			if(ubai==null){
				return null;
			}else{
				return ubai;
			}
			
		}
		//获取用户安全信息
		public UserAccountSafeInfo getUserAccountSafeInfo(){
			UserAccountSafeInfo uasi = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
			if(uasi==null){
				return null;
			}else{
				return uasi;
			}
			
		}
		/**
		 * 
		* @Title: getAdminUser 
		* @Description: TODO(获取系统管理员信息) 
		* @param @return  参数说明 
		* @return AdminUser    返回类型 
		* @author chenjiaming
		* @throws
		 */
		public AdminUser getAdminUser(){
			AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			if(adminUser == null){
				return null;
			}else{
				return adminUser;
			}
		}

		public StateUtils getState() {
			return state;
		}
		public void setState(StateUtils state) {
			this.state = state;
		}
		
		/**
		 * 设置Cookie
		 * 
		 * @return
		 */
		public String setCookie()
		{
			request.setAttribute("cookieName", Iconstant.PRECOOKIE);
			String regcookie = StringUtil.getCookieValue(request);
			if (StringUtil.isNull(regcookie))
			{
				StringUtil.addCookie(response, request);
			}
			return regcookie;
		}

		
		
		//将相关数据放入model
//		protected void initResult(Model model, List<Object> list, Map<String,Object> map){
//			model.addAttribute("list", list);
//			Iterator it = map.entrySet().iterator(); 
//			while(it.hasNext()){ 
//				Map.Entry m = (Map.Entry)it.next(); 
//				model.addAttribute(m.getKey().toString(), m.getValue());
//		   } 
//		}
//		
//		public static Integer getStartIndex(Integer pageNum, Integer pageSize){
//			Integer res = 0;
//			if(pageNum>0){
//				res = (pageNum-1)*pageSize;
//			}
//			return res;
//		}

		
		/**
		 * AES进行加密
		 */
		public static String setEncrypt(String message) {
			return AES.getEncrypt(message);
		}

		/**
		 * AES进行解密
		 */

		public static String getDecrypt(String message) {
			return AES.getDecrypt(message);
		}
		
		/**
		 * 
		* @Title: getDecryptionUserFsAccountInfoDetail 
		* @Description: TODO(解密返回    数据库加密字段  保存进session) 
		* @param @param userFsAccountInfo
		* @param @return    设定文件 
		* @return UserFsAccountInfo    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static UserFsAccountInfo  getDecryptionUserFsAccountInfoDetail(UserFsAccountInfo userFsAccountInfo){
			if(userFsAccountInfo.getUsrcustid() != null && StringUtil.isNotEmpty(userFsAccountInfo.getUsrcustid())){//电子账号
				userFsAccountInfo.setUsrcustid(AES.getDecrypt(userFsAccountInfo.getUsrcustid()));
			}
			
			if(userFsAccountInfo.getUsrname() != null && StringUtil.isNotEmpty(userFsAccountInfo.getUsrname())){//真实姓名
				userFsAccountInfo.setUsrname(AES.getDecrypt(userFsAccountInfo.getUsrname()));
			}
			
			if(userFsAccountInfo.getFsmobile() != null && StringUtil.isNotEmpty(userFsAccountInfo.getFsmobile())){//手机号码
				userFsAccountInfo.setFsmobile(AES.getDecrypt(userFsAccountInfo.getFsmobile()));
			}
			return userFsAccountInfo;
		}
		
		/**
		 * 
		* @Title: getDecryptionUserBaseAccountInfoDetail 
		* @Description: TODO(解密返回    数据库加密字段 保存进session) 
		* @param @param userBaseAccountInfo
		* @param @return    设定文件 
		* @return UserBaseAccountInfo    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static UserBaseAccountInfo getDecryptionUserBaseAccountInfoDetail(UserBaseAccountInfo userBaseAccountInfo){
			
			if(userBaseAccountInfo.getEmail() != null  && StringUtil.isNotEmpty(userBaseAccountInfo.getEmail())){//邮箱
				userBaseAccountInfo.setEmail(AES.getDecrypt(userBaseAccountInfo.getEmail()));
			}
			
			if(userBaseAccountInfo.getMobilephone() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone())){//手机号码
				userBaseAccountInfo.setMobilephone(AES.getDecrypt(userBaseAccountInfo.getMobilephone()));
			}
			
			if(userBaseAccountInfo.getCertificationnumber() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getCertificationnumber())){//身份证号码
				userBaseAccountInfo.setCertificationnumber(AES.getDecrypt(userBaseAccountInfo.getCertificationnumber()));
			}
			
			if(userBaseAccountInfo.getRealname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getRealname())){//身份证姓名
 				userBaseAccountInfo.setRealname(AES.getDecrypt(userBaseAccountInfo.getRealname().trim()));
 			}
			
			if(userBaseAccountInfo.getLoginname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getLoginname())){//用户名
				userBaseAccountInfo.setLoginname(AES.getDecrypt(userBaseAccountInfo.getLoginname()));
			}
			
		return userBaseAccountInfo;
	}
		
		/**
		 * 
		* @Title: getEncryptUserBaseAccountInfoDetail 
		* @Description: TODO(加密返回    更新保存进数据库) 
		* @param @param userBaseAccountInfo
		* @param @return    设定文件 
		* @return UserBaseAccountInfo    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static UserBaseAccountInfo getEncryptUserBaseAccountInfoDetail(UserBaseAccountInfo userBaseAccountInfo){
			
			if(userBaseAccountInfo.getEmail() != null  && StringUtil.isNotEmpty(userBaseAccountInfo.getEmail())){//邮箱
				userBaseAccountInfo.setEmail(setEncrypt(userBaseAccountInfo.getEmail()));
			}
			
			if(userBaseAccountInfo.getMobilephone() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone())){//手机号码
				userBaseAccountInfo.setMobilephone(setEncrypt(userBaseAccountInfo.getMobilephone()));
			}
			
			if(userBaseAccountInfo.getCertificationnumber() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getCertificationnumber())){//身份证号码
				userBaseAccountInfo.setCertificationnumber(setEncrypt(userBaseAccountInfo.getCertificationnumber()));
			}
			
			if(userBaseAccountInfo.getRealname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getRealname())){//身份证姓名
 				userBaseAccountInfo.setRealname(setEncrypt(userBaseAccountInfo.getRealname().trim()));
 			}
			
			if(userBaseAccountInfo.getLoginname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getLoginname())){//用户名
				userBaseAccountInfo.setLoginname(setEncrypt(userBaseAccountInfo.getLoginname()));
			}
			
		return userBaseAccountInfo;
	}
	 
}
