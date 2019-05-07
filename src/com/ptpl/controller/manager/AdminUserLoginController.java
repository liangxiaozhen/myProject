package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleUser;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserLog;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.RoleUserServiceI;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: AdminUserLoginController 
* @Package com.ptpl.controller 
* @Description: TODO(系统管理模块   登录控制中心) 
* @author chenjiaming
* @date 2016年7月6日 上午10:40:27 
* @version V1.0
 */
@Controller
public class AdminUserLoginController extends BaseController {
	
	@Autowired
	AdminUserServiceI adminUserServiceI;
	/**
	 * 用户日志信息 Service
	 */
	@Autowired
	private UserLogServiceI userLogServiceI;
	 
 	/**
  	 * 用户登录
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@SuppressWarnings("unused")
	@RequestMapping(value="/logined",method={RequestMethod.POST,RequestMethod.GET})
	 public String login(AdminUser user,HttpServletRequest request, HttpServletResponse response)throws Exception{
 		 if(user != null){
 			 //获取传递过来的验证码
 			 System.out.println(AES.getEncrypt("徽商测试用户"));
			 String code = request.getParameter("code");
			 if(StringUtil.isNotEmpty(user.getUsername()) && StringUtil.isNotEmpty(user.getPassword()) && StringUtil.isNotEmpty(code)){
 				 String jsonstr ="";
				 Map<String,String> hashMap = new HashMap<String,String>();
				//判断验证码是否一致
				 boolean flagcode = true;//StringUtil.verifyCodeCompare(code, request);
				 if(flagcode){
					 AdminUser user1 = adminUserServiceI.findAdminUserPSWByUsName(user);
					 if(user1 != null){
 						 if(user1.getForbid()== null){
							 user1.setForbid("0");
						 }
						 if(user1.getErrorcount()== null){
							 user1.setErrorcount(0);
						 }
						 Integer errorCount = user1.getErrorcount();
						 //判断密码是否正确
						 boolean flag = BCrypt.checkpw(user.getPassword(),user1.getPassword());
						//判断用户禁止状态
						 if(user1.getForbid().equals("1")){
							 hashMap.put("result", "forbid_error");
						 }else{
							 if(flag){
									 session.setAttribute(Session_Constant.ADMINUSER, user1);
									 user1.setLogintime(new Date());
									 adminUserServiceI.updateAdminUser(user1);
									 String ipAddr=StringUtil.getIpAddr(request);
									 //保存管理员登录日志信息
									 userLogServiceI.insertSelective(getUserLog(user1,ipAddr,user1.getUsername(),request));
									 hashMap.put("result", "success");
 							 }else{
									 errorCount =errorCount+1;
									 if(errorCount <=5){
										 user1.setErrorcount(errorCount);
									 }else{
										 //更新最后登录时间
										 user1.setLogintime(new Date());
										 //设置禁止登录
										 user1.setForbid("1");
										 errorCount=5;
									 }
									 adminUserServiceI.updateAdminUser(user1);
									 hashMap.put("errorCount",String.valueOf(errorCount));
									 hashMap.put("result", "password_error");
								 }
						 } 
					 }else{
						 hashMap.put("result", "account_error");
					 }
				 }else{
					 hashMap.put("result", "code_error");
				 }
				 jsonstr = JSON.toJSONString(hashMap);
				 StringUtil.sendJsonData(response, jsonstr);
			 }
		 } 
 		 return null;
 	 }
  	/**
  	 * 注册用户
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@RequestMapping(value="/register",method={RequestMethod.POST,RequestMethod.GET})
  	public String register(AdminUser adminUser,HttpServletRequest request, HttpServletResponse response){
  		 if(adminUser != null){
  			 if(StringUtil.isNotEmpty(adminUser.getUsername()) && StringUtil.isNotEmpty(adminUser.getPassword())){
  				 //根据用户名或邮箱查询用户是否存在
  				 AdminUser user = adminUserServiceI.findAdminUserPSWByUsName(adminUser);
  				 Map<String,String> hashMap = new HashMap<String,String>();
  				 //判断用户名是否存在
	  			 if(user != null){
 					 try {
 						 hashMap.put("result", "account_error");
 						 String strJson = JSON.toJSONString(hashMap);
						 StringUtil.sendJsonData(response, strJson);
					} catch (IOException e) {
 						e.printStackTrace();
					}
 	  			 }else{
	  				 try {
		  				 int row = 0;
		  				 //密码加密
		  	 		     String str = BCrypt.hashpw(adminUser.getPassword(), BCrypt.gensalt());
		  	 		     adminUser.setPassword(str);
		  	 		     //分配默认登录输入错误次数
		  	 		     adminUser.setErrorcount(0);
		  	 		     //分配默认禁止登录状态 0未禁止1禁止登录
		  	 		     adminUser.setForbid("0");
		  	 		     //注册时间
		  	 		     adminUser.setRegdate(new Date());
		  				 row = adminUserServiceI.insertSelective(adminUser);
					 		if(row > 0){
								 hashMap.put("result","success");
							 }else{
								 hashMap.put("result", "fail");
							 }
 		  				 String strJson = JSON.toJSONString(hashMap);
		  				 StringUtil.sendJsonData(response, strJson);
					} catch (IOException e) {
	 					e.printStackTrace();
					}
	  			 }
  			 }
   		 }
  		return null;
  	}
  	
  	/**
  	 * 修改密码
  	 * @param adminUser
  	 * @return
  	 */
  	@RequestMapping(value="/updatepsw",method=RequestMethod.POST)
  	public String updatepsw(AdminUser adminUser,HttpServletResponse response){
  		if(adminUser != null){
   			Map<String,String> hashMap = new HashMap<String,String>();
  			if(StringUtil.isNotEmpty(adminUser.getPassword())){
  				try {
	  				int count = 0;
	  				adminUser.setModifypwdtime(new Date());
	  				//密码加密
	  				 String ptr = BCrypt.hashpw(adminUser.getPassword(), BCrypt.gensalt());
	  				 adminUser.setPassword(ptr);
	  				 count = adminUserServiceI.updateAdminUser(adminUser);
	  				if(count > 0){
	  					hashMap.put("result", "success");
	  				}else{
	  					hashMap.put("result", "fail");
	  				}
					String str = JSON.toJSONString(hashMap);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
   			}else{ 
   				try {
					hashMap.put("result", "password_error");
					String str = JSON.toJSONString(hashMap);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
  			}
  		} 
  		return null;
  	}
   /**
    * 查询用户是否唯一
    * @author cjm
    * @param adminUser
    * @param request
    * @param response
    * @return
    */
  	@RequestMapping(value="/getbyuser",method={RequestMethod.POST,RequestMethod.GET})
  	public String getbyuser(AdminUser adminUser,HttpServletRequest request, HttpServletResponse response){
   		if(adminUser != null){
   			AdminUser user = adminUserServiceI.findAdminUserPSWByUsName(adminUser);
   			Map<String,String> map = new HashMap<String,String>();
   			if(user != null){
   				try {
	   				map.put("result", "fail");
	   				String str = JSON.toJSONString(map);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
   			}else{
   				try {
	   				map.put("result", "success");
	   				String str = JSON.toJSONString(map);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
   			} 
  		}
  		return null;
  	}
  	 
	/**
  	 * 查询邮箱是否唯一
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@RequestMapping(value="/getbyemail",method={RequestMethod.POST,RequestMethod.GET})
  	public String getbyemail(AdminUser adminUser,HttpServletRequest request, HttpServletResponse response){
   		if(adminUser != null){
   			AdminUser user = adminUserServiceI.findAdminUserByEamil(adminUser);
   			Map<String,String> map = new HashMap<String,String>();
   			if(user != null){
   				try {
	   				map.put("result", "fail");
	   				String str = JSON.toJSONString(map);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
   			}else{
   				try {
	   				map.put("result", "success");
	   				String str = JSON.toJSONString(map);
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
   			} 
  		}
  		return null;
  	}
  	/**
  	 * 注销登录
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
   	@RequestMapping(value="/logout")
  	public String logout(){
   		session.invalidate();
  		return "redirect:/admin/login.action";
  	}
   	/**
  	 * 跳转登录页面
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@RequestMapping("/admin/login")
  	public String login(HttpServletRequest request){
  		AdminUser adminUser = getAdminUser();
  		if(adminUser != null){
  			 return "admin/index2";
  		}else{
   			return "admin/login";
  		}
  	}
	/**
  	 * 跳转找回密码页面
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@RequestMapping("/forget")
  	public String forget(){
     	return "admin/forget";
  	}
	/**
  	 * 跳转注册页面
  	 * @author cjm
  	 * @param adminUser
  	 * @param request
  	 * @param response
  	 * @return
  	 */
  	@RequestMapping("/reg")
  	public String reg(){
    	return "admin/register";
  	}
  	 
  	/**
	 * 
	* @Title: getUserLog 
	* @Description: TODO(获取用户日志信息 ) 
	* @param @param userAccountSafeInfo
	* @param @param ip 传递进来的最后登录ip地址
	* @param @param username 用户登录名
	* @param @return  参数说明 
	* @return UserLog    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public UserLog getUserLog(AdminUser adminUser,String ip,String username,HttpServletRequest request){
		UserLog userLog = new UserLog();
		//最后登录ip地址
		if(ip != null){
			userLog.setIp(ip);
 		}
		if(adminUser.getId() != null){
	 		//用户Id
			userLog.setBaseid(adminUser.getId());
		}
		if(username != null){
			//用户名
			userLog.setUsername(username);
 		}
		//用户类型1普通用户 2管理员用户
		userLog.setUsertype((short)2);
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			 String name = cookie.getName();
			 if(name != null && name.equals("JSESSIONID")){
				 String value = cookie.getValue();
				 if(value != null){
					 //更新最后登录cookie值
					 userLog.setCookie(value);
				 }
			 }
		  }
 		//登录时间
		userLog.setLogintime(new Date());
		userLog.setOpercontent("管理用户登录");
		return userLog;
	}
	//管理用户登录入口
	@RequestMapping(value = "/toadmin", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView toadmin(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/index");
		Enumeration temp = req.getHeaderNames();
		while (temp.hasMoreElements()) {
			String paramName = (String) temp.nextElement();
			System.out.println(paramName + "=" + req.getHeader(paramName) + "<br>");
			
		}
		return modelAndView;
	}
  }
