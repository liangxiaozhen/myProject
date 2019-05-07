//package com.ptpl.controller.third;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSON;
//import com.ptpl.constant.Session_Constant;
//import com.ptpl.controller.BaseController;
//import com.ptpl.model.ThirdPbLogin;
//import com.ptpl.model.UserAccount;
//import com.ptpl.model.UserAccountSafeInfo;
//import com.ptpl.model.UserBaseAccountInfo;
//import com.ptpl.service.ThirdPbLoginServiceI;
//import com.ptpl.service.UserAccountSafeInfoServiceI;
//import com.ptpl.service.UserAccountServiceI;
//import com.ptpl.service.UserBaseAccountInfoServiceI;
//import com.ptpl.web.Iconstant.Iconstant;
//import com.ptpl.web.util.SMSSend;
//import com.ptpl.web.util.SendMailTempalte;
//import com.ptpl.web.util.StringUtil;
//
//import weibo4j.Oauth;
//import weibo4j.model.User;
//import weibo4j.model.WeiboException;
//
///**
// * 
//* @ClassName: UserWeiBoLoginController 
//* @Package com.ptpl.controller 
//* @Description: TODO(第三方登录授权  新浪微博 登录 Controller 层) 
//* @author  chenjiaming
//* @date 2016年8月1日 下午2:43:16 
//* @version V1.0
// */
//@Controller
// public class UserWeiBoLoginController2 extends BaseController{
//	/**
//	 * 用户基本信息 service
//	 */
//	@Autowired
//	private UserBaseAccountInfoServiceI  userBaseAccountInfoServiceI;
//	/**
//	 * 用户账号安全信息 service
//	 */
//	@Autowired
//	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
//	/**
//	 * 第三方登录授权 service 
//	 */
//	
//	@Autowired
//	private ThirdPbLoginServiceI thirdPbLoginServiceI;
//	/**
//	 * 用户账户 service 
//	 */
//	@Autowired
//	private UserAccountServiceI userAccountService;
//	/**
//	 * 
//	* @Title: UserloginSianWeiBo 
//	* @Description: TODO(跳转微博登录页面) 
//	* @param @param request
//	* @param @return
//	* @param @throws WeiboException  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/toweibo",method=RequestMethod.GET)
//	public String UserloginSianWeiBo(HttpServletRequest request) throws WeiboException{
// 		Oauth oauth = new Oauth();
//		String url = oauth.authorize("code", "");
//		return "redirect:"+url;
// 	}
//	
//	/**
//	 * 
//	* @Title: toCode 
//	* @Description: TODO(微博用户同意授权后 进来了) 
//	* @param @param session
//	* @param @param request
//	* @param @return
//	* @param @throws WeiboException  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/toCode",method=RequestMethod.GET)
// 	public ModelAndView toCode(HttpSession session,HttpServletRequest request) throws WeiboException{
//		//获取用户同意授权后的参数
//		String code = request.getParameter("code");
//		ModelAndView modelAndView = new ModelAndView();
//		if(StringUtil.isNotEmpty(code)){
//			//授权对象类
//			Oauth oauth = new Oauth();
//			//获取令牌
//			weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
// 		    //获取用户ID主键 
//		    String uid = accessToken.getUserUid();
// 		    if(StringUtil.isNotEmpty(uid)){
// 		    	//第三方实体类
//		    	ThirdPbLogin  thirdPbLogin = new ThirdPbLogin();
//		    	//set注入第三方用户ID
//		    	thirdPbLogin.setThirdpuid(uid);
//		    	//set注入第三方名称
//		    	thirdPbLogin.setThirdpname("新浪微博");
//		    	//查询是否已经绑定
//		    	ThirdPbLogin thirdPbLogin2 = thirdPbLoginServiceI.selectByThirdUserIdAndThirdName(thirdPbLogin);
//		    	if(thirdPbLogin2 != null){//已经绑定过了
//		    		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//		    		ubai.setId(thirdPbLogin2.getBaseid());
//		    		//用户基本信息
//		    		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//		    		//用户安全基本信息
//		    		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(thirdPbLogin2.getBaseid());
//		    		if( userAccountSafeInfo != null && userBaseAccountInfo != null
//		    				&& userAccountSafeInfo.getRisklevel() != null
//		    				&& userAccountSafeInfo.getStatus() != null){
//			    			//用户风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结）
//							String risklevel =  userAccountSafeInfo.getRisklevel().toString();
//							/* 账户状态(1正常\0停用) */
//							String status  =  userAccountSafeInfo.getStatus().toString();
//							if(status.equalsIgnoreCase("1") && risklevel.equalsIgnoreCase("1")){
//								//用户账号安全信息实体对象
//								session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfo);
//								//用户基本信息实体对象
//								session.setAttribute(Session_Constant.USER, userBaseAccountInfo);
//								//跳转登录后的页面
//								modelAndView.setViewName("redirect:/user/touser.action");
//	 						 }else{
//		 							 if(status.equalsIgnoreCase("1") ||  risklevel.equalsIgnoreCase("2") || risklevel.equalsIgnoreCase("3")){//用户风控指引名单
//		 								session.setAttribute("userBaseAccountInfo",userBaseAccountInfo);
//		 								modelAndView.setViewName("redirect:/jumpupdatepsw.action");
//		 							 }else{//用户风控风险名单或黑名单
//		  								 int count = 0;
//		 								 //删除已经绑定过的用户信息
//		 								 count = thirdPbLoginServiceI.deleteById(thirdPbLogin2.getId());
//		 								 if(count > 0){
//		 									 //调用微博Api接口
//		 									 weibo4j.Users users = new weibo4j.Users(accessToken.getAccessToken());
//		 									 //根据用户ID查询用户对应的信息
//		 									 weibo4j.model.User weiBouser = users.showUserById(uid);
//		 									 session.setAttribute(Session_Constant.USER_WEIBO_USER_CODE, weiBouser);
//		 									 //跳转绑定用户页面
//		 									 modelAndView.setViewName("redirect:/sian/bindUser.action");
//		 								 }
//		 							 }
//	 						 }
//		    		} 
// 		    	}else{//没有和新浪微博进行绑定过  跳转绑定页面
//  		    		//调用微博Api接口
//		    		weibo4j.Users users = new weibo4j.Users(accessToken.getAccessToken());
//		    		//根据用户ID查询用户对应的信息
//		    		weibo4j.model.User weiBouser = users.showUserById(uid);
//		    		session.setAttribute(Session_Constant.USER_WEIBO_USER_CODE, weiBouser);
// 		    		//跳转绑定用户页面
//		    		modelAndView.setViewName("redirect:/sian/bindUser.action");
//		    	}
//		    }
// 		}else{
//			//重定向到登录页面
//			modelAndView.setViewName("redirect:/user/tologin.action");
//		}
//		return modelAndView ;
//  	}
//	
//	/**
//	 * 
//	* @Title: thirdBindUserByRegister 
//	* @Description: TODO(注册用户并绑定微博 通过手机号注册) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/thirdBindUserAndRegisterPassedPhone",method={RequestMethod.POST,RequestMethod.GET})
//	public String thirdBindUserANdRegisterPassedPhone(HttpServletRequest request,HttpServletResponse response){
//			//获取用户名
//			String username = request.getParameter("loginname");
//			//获取密码
//			String password = request.getParameter("password");
//			//获取手机号码
//			String mobilephone = request.getParameter("mobilephone"); 
//			//获取验证码
//			String code = request.getParameter("code");
//			//获取手机验证码
//			String pcode = request.getParameter("pcode");
//	 		Map<String,String> maps = new HashMap<String,String>();
// 		    try {
//	 			if(StringUtil.isNotEmpty(code)){//验证码不为空进来了
//					//校验验证码是否一致
//					boolean flag = StringUtil.verifyCodeCompare(code, request);
// 					if(flag){//验证码正确进来了
//						if(StringUtil.isNotEmpty(pcode)){//手机验证码不为空进来了
//							 //获取session里的手机验证码
//							 String pcode2 = (String) request.getSession().getAttribute(Session_Constant.USER_WEIBO_PHONE_CODE);
//							 System.out.println(pcode2+"===================="+pcode);
// 							 if(StringUtil.isNotEmpty(pcode2)){//手机验证码已发送了
//								 if(pcode2.equalsIgnoreCase(pcode)){//手机验证码校验一致进来了
//									 if(StringUtil.isNotEmpty(username)){//用户名不为空进来了
//										 	//用户基本信息实体对象
//	 									    UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//										    //set注入 用户名
//										    ubai.setLoginname(username);
//										    //根据用户名查询是否存在
//										 	UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//										 	if(baseAccountInfo == null){//用户名没有注册
//										 		if(StringUtil.isNotEmpty(password)){//密码不为空进来了
//										 			if(StringUtil.isNotEmpty(mobilephone)){//手机号码不为空进来了
//										 				if(StringUtil.isMobile(mobilephone)){//手机号码格式正确进来了
//										 					ubai.setLoginname(null);
//										 					//set注入 手机号码
//										 					ubai.setMobilephone(mobilephone);
//										 					//根据手机号查询是否存在
//	 									 					baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//	 									 					//第三方应用微博 用户实体对象
//							 							 	User user = (User) request.getSession().getAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//										 					if(baseAccountInfo == null){//手机号没有注册
//										 					   if(user != null){//微博用户不为空进来了
//										 							 //用户名
//											 						 ubai.setLoginname(username);
//											 						 //注册时间
//											 						 ubai.setRegdate(new Date());
//											 						 //注册（正常1、审核中2、停用0）
//											 						 ubai.setStatus((short)1);
//											 						 //注册ip
//											 						 ubai.setRegip(StringUtil.getIpAddr(request));
//											 						 //设置cookie
//											 						request.setAttribute("cookieName", Iconstant.PRECOOKIE);
//											 						String regcookie = StringUtil.getCookieValue(request);
//											 						if(StringUtil.isNull(regcookie)){
//											 							StringUtil.addCookie(response, request);
//											 						}
//	 										 						ubai.setRegcookie(regcookie);
//											 						//用户类型：（个人、企业）1个人 0企业
//											 						ubai.setAccounttype((short)1);
//											 						//注册类型：（邮件注册1、手机注册2）
//											 						ubai.setRegtype((short)2);
//											 						//手机验证1已验证 0未验证
//											 						ubai.setIsmobileverify((short)1);
//		 									 						 int count = 0;
//											 						//注册用户基本信息
//											 						 count = userBaseAccountInfoServiceI.insertSelective(ubai);
//											 						 if(count > 0){
//											 							 //用户账号安全信息实体对象
//											 							 UserAccountSafeInfo accountSafeInfo = new UserAccountSafeInfo();
//											 							 //用户账户实体对象
//											 							 UserAccount userAccount = new UserAccount();
//											 							 //密码加密
//											 							 String str = BCrypt.hashpw(password, BCrypt.gensalt());
//											 							 //用户密码
//											 							 accountSafeInfo.setLoginpassword(str);
//											 							 //用户ID
//											 							 accountSafeInfo.setBaseid(ubai.getId());
//											 							 //风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结）
//											 							 accountSafeInfo.setRisklevel((short)1);
//											 							//注册（正常1、审核中2、停用0）
//											 							accountSafeInfo.setStatus((short)1);
//											 							 //生成20位000占位符
//	 										 					    	 String thirdpbloginstr = StringUtil.getPlaceholder(20);
//											 					    	 String thirdpbloginstr2 = StringUtil.setPlaceholder(thirdpbloginstr,0);
//											 					    	 //第三方绑定（qq，微信，新浪微博）0000000000根据占位判断  1微博 2qq
//	 										 							 accountSafeInfo.setThirdpblogin(thirdpbloginstr2);
//											 							 count = 0;
//											 							 userAccountSafeInfoServiceI.insert(accountSafeInfo);
//											 							 userAccount.setBaseid(ubai.getId());
//											 							 count = userAccountService.insert(userAccount);
//											 							 if(count > 0){
//	 									 								 	//第三方实体对象
//																	 		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
//																	 		//set注入 用户ID
//																	 		thirdPbLogin.setBaseid(ubai.getId());
//																	 		//第三方名称
//																	 		thirdPbLogin.setThirdpname("新浪微博");
//																	 		//第三方用户ID
//																	 		thirdPbLogin.setThirdpuid(user.getId());
//																	 		//绑定时间
//																	 		thirdPbLogin.setAuthbinddate(new Date());
//																	 		//绑定状态1 绑定 0未绑定
//																	 		thirdPbLogin.setStatus((short)1);
//	 																 		count = 0;
//																	 		count = thirdPbLoginServiceI.insertSelective(thirdPbLogin);
//																	 	   if(count > 0){
//																	 		   //删除微博session
//																	 		   session.removeAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//																	 		   //删除微博手机验证码session
//																	 		   session.removeAttribute(Session_Constant.USER_WEIBO_PHONE_CODE);
//																	 		   //用户账号安全信息实体对象
//																				session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, accountSafeInfo);
//																				//用户基本信息实体对象
//																				session.setAttribute(Session_Constant.USER, ubai);
//																	 		   //绑定成功
//																	 		   maps.put("result", "success");
//																	 	   }else{
//																	 		   //绑定失败 删除已插入数据库的信息
//																	 		  userAccountSafeInfoServiceI.deleteByBaseId(ubai.getId());
//																	 		  userAccountService.deleteByBaseId(ubai.getId());
//																	 		  userBaseAccountInfoServiceI.deleteById(ubai.getId());
//	  																 		  //绑定失败
//														 					  maps.put("result", "fail");
//																	 	   }
//	 										 							 }else{
//										 								 	 //注册失败
//													 						 maps.put("result", "fail");
//											 							 } 
//		 									 						 } 
//										 					   	}else{
//	 									 							//跳转首页
//										 					   		maps.put("result", "logout");	 	
//										 					   	}
//	 									 					}else{
//										 						//手机号已经注册过了
//										 						maps.put("result", "mobilephone_fail");
//										 					}
//										 				}else{
//										 					//手机号码格式不正确
//										 					maps.put("result", "mobilephone_error");
//										 				}
//										 			}else{
//										 				//手机号码传递过来的是空值
//										 				maps.put("result", "mobilephone_null");
//										 			}
//										 		}else{
//										 			//密码传递过来的是空值
//										 			maps.put("result", "password_null");
//										 		}
//	 									 	}else{
//	 									 		//用户名已经注册了
//	 								 			maps.put("result", "username_error");
//	 									 	}
//										}else{
//											//用户名传递过来的是空值
//											maps.put("result", "username_null");
//										}
//								 }else{
//									 //手机验证码校验失败
//									 maps.put("result", "pcode_error");
//								 }
//	 						 }else{
//								 	//用户名没有点击发送短信按钮
//									maps.put("result", "pcode2_null");
//							 }
//						 }else{
//							//手机验证码传递过来的是空值
//							maps.put("result", "pcode_null");
//						 }
//					}else{
//						//验证码校验不一致
//						maps.put("result", "code_error");
//					}  
//				}else{
//					//验证码传递过来的是空值
//					maps.put("result", "code_null");
//				}
//	 			//json输出
//	 			String str = JSON.toJSONString(maps);
//	 			StringUtil.sendJsonData(response, str);
//	 		} catch (IOException e) {
//	 			e.printStackTrace();
//	 		}
//	 		return null;
// 	}
//	
//	/**
//	 * 
//	* @Title: thirdBindUserANdRegisterPassedEmail 
//	* @Description: TODO(注册用户并绑定微博 通过邮箱注册) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String  返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/thirdBindUserAndRegisterPassedEmail",method={RequestMethod.POST,RequestMethod.GET})
//	public String thirdBindUserANdRegisterPassedEmail(HttpServletRequest request,HttpServletResponse response){
//		//获取用户登录名
//		String username = request.getParameter("username");
//		//获取注册的邮箱账号
//		String email = request.getParameter("email");
//		//获取注册密码
//		String password = request.getParameter("password");
//		//获取验证码
//		String code = request.getParameter("code");
//		//获取邮箱验证码
//		String ecode = request.getParameter("ecode");
//		Map<String,String> maps = new HashMap<String,String>();
//		try {
//			if(StringUtil.isNotEmpty(email)){//邮箱不为空进来了
//				 if(StringUtil.isEmail(email)){//邮箱格式正确进来了
//					 UserBaseAccountInfo  ubai = new UserBaseAccountInfo();
//					 ubai.setEmail(email);
//					 UserBaseAccountInfo accountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//					 if(accountInfo == null){//邮箱没有注册
//						 if(StringUtil.isNotEmpty(username)){
//							 ubai.setEmail(null);
//							//用户名
//							 ubai.setLoginname(username);
//							 accountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//							  if(accountInfo == null){//用户名没有注册
//								  if(StringUtil.isNotEmpty(password)){//密码不为空进来了
//									  	if(StringUtil.isNotEmpty(code)){//验证码不为空进来了
//									  		 if(StringUtil.isNotEmpty(ecode)){//邮箱验证码不为空进来了
//									  			   String secode = (String) request.getSession().getAttribute(Session_Constant.USER_WEIBO_EMAIL_CODE);
//									  			   System.out.println(secode+"============="+ecode);
// 									  			   if(StringUtil.isNotEmpty(secode) && secode.equalsIgnoreCase(ecode)){//校验成功
//									  				   //第三方应用微博 用户实体对象
//									  				    User user = (User) request.getSession().getAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//									  				    if(user != null){
//									  				    	 //用户邮箱
//									  						 ubai.setEmail(email);
// 									 						 //注册时间
//									 						 ubai.setRegdate(new Date());
//									 						 //注册（正常1、审核中2、停用0）
//									 						 ubai.setStatus((short)1);
//									 						 //注册ip
//									 						 ubai.setRegip(StringUtil.getIpAddr(request));
//									 						 //设置cookie
//									 						request.setAttribute("cookieName", Iconstant.PRECOOKIE);
//									 						String regcookie = StringUtil.getCookieValue(request);
//									 						if(StringUtil.isNull(regcookie)){
//									 							StringUtil.addCookie(response, request);
//									 						}
//									 						ubai.setRegcookie(regcookie);
//									 						//用户类型：（个人、企业）1个人 0企业
//									 						ubai.setAccounttype((short)1);
//									 						//注册类型：（邮件注册1、手机注册2）
//									 						ubai.setRegtype((short)2);
//									 						 int count = 0;
//									 						//注册用户基本信息
//									 						count = userBaseAccountInfoServiceI.insertSelective(ubai);
//									 						if(count > 0){
//									 							 //用户账号安全信息实体对象
//									 							 UserAccountSafeInfo accountSafeInfo = new UserAccountSafeInfo();
//									 							 //用户账户实体对象
//									 							 UserAccount userAccount = new UserAccount();
//									 							 //密码加密
//									 							 String str = BCrypt.hashpw(password, BCrypt.gensalt());
//									 							 //用户密码
//									 							 accountSafeInfo.setLoginpassword(str);
//									 							 //用户ID
//									 							 accountSafeInfo.setBaseid(ubai.getId());
//									 							 //风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结）
//									 							 accountSafeInfo.setRisklevel((short)1);
//									 							//注册（正常1、审核中2、停用0）
//										 						 accountSafeInfo.setStatus((short)1);
//									 							 //生成20位000占位符
//									 					    	 String thirdpbloginstr = StringUtil.getPlaceholder(20);
//									 					    	 String thirdpbloginstr2 = StringUtil.setPlaceholder(thirdpbloginstr,0);
//									 					    	 //第三方绑定（qq，微信，新浪微博）0000000000根据占位判断  1微博 2qq
//									 							 accountSafeInfo.setThirdpblogin(thirdpbloginstr2);
//									 							 count = 0;
//									 							 userAccountSafeInfoServiceI.insert(accountSafeInfo);
//									 							 userAccount.setBaseid(ubai.getId());
//									 							 count = userAccountService.insert(userAccount);
//									 							 if(count > 0){
//  								 								 	//第三方实体对象
//															 		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
//															 		//set注入 用户ID
//															 		thirdPbLogin.setBaseid(ubai.getId());
//															 		//第三方名称
//															 		thirdPbLogin.setThirdpname("新浪微博");
//															 		//第三方用户ID
//															 		thirdPbLogin.setThirdpuid(user.getId());
//															 		//绑定时间
//															 		thirdPbLogin.setAuthbinddate(new Date());
//															 		//绑定状态1 绑定 0未绑定
//															 		thirdPbLogin.setStatus((short)1);
//															 		count = 0;
//															 		count = thirdPbLoginServiceI.insertSelective(thirdPbLogin);
//															 	    if(count > 0){
//															 	    	//删除微博session
//															 		    session.removeAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//															 		    //删除邮箱验证码session
//															 		    session.removeAttribute(Session_Constant.USER_WEIBO_EMAIL_CODE);
//															 		    //用户账号安全信息实体对象
//																		session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, accountSafeInfo);
//																		//用户基本信息实体对象
//																		session.setAttribute(Session_Constant.USER, ubai);
//															 		    //绑定成功
//															 		    maps.put("result", "success");
//															 	    }else{
//															 		   //绑定失败 删除已插入数据库的信息
//															 		   userAccountSafeInfoServiceI.deleteByBaseId(ubai.getId());
//															 		   userAccountService.deleteByBaseId(ubai.getId());
//															 		   userBaseAccountInfoServiceI.deleteById(ubai.getId());
//																 	   //绑定失败
//												 					   maps.put("result", "fail");
//															 	    }
//									 							 }else{
//								 								 	 //注册失败
//											 						 maps.put("result", "fail");
//									 							 }
//									 						 }else{
//									 							 //注册失败
//										 						 maps.put("result", "fail");
//									 						 }
//									  				    }else{
//									  				    	 //微博User 失效
//									 						 maps.put("result", "user_null");
//									  				    }
//									  			   }else{
//									  				   	//邮箱验证码不一致		
//													 	maps.put("result", "ecode_error");    
//									  			   }
//									  		 }else{
//									  			//邮箱验证码为空
//											 	maps.put("result", "ecode_null"); 
//									  		 }
//			 						  	}else{
//									  		//验证码为空
//									 		maps.put("result", "code_null");
//									  	}
//								  }else{
//										//密码为空
//								 		maps.put("result", "password_null");
//								  }
//							  }else{
//								  	//用户名已经注册
//							 		maps.put("result", "username_fail"); 
//							  }
//						 }else{
//							 	//用户名为空
//						 		maps.put("result", "username_null"); 
//						 }
// 					 }else{
//						 	//邮箱已经注册
//					 		maps.put("result", "email_fail"); 
//					 } 
// 				 }else{
//					//邮箱格式不正确
//		 			maps.put("result", "email_error");
//				 }
//			}else{
//				//邮箱为空
// 				maps.put("result", "email_null");
// 			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		return null;
//	}
//	/**
//	 * 
//	* @Title: ThirdBindUser 
//	* @Description: TODO(已注册用户绑定微博) 
//	* @param @param request
//	* @param @param response
//	* @param @param userBaseAccountInfo2
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws UPDATEBINDDATE
//	 */
//	@RequestMapping(value="/sian/thirdBindUser",method=RequestMethod.POST)
//	public String ThirdBindUser(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo2){
// 		//获取用户名
//		String username = request.getParameter("loginname");
//		//获取密码
//		String password = request.getParameter("password");
//		Map<String,String> maps = new HashMap<String,String>();
//		try {
//			if(StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password)){
//				//根据用户名查询密码
//				UserAccountSafeInfo  userAccountSafeIn = userAccountSafeInfoServiceI.getLoginPassWordByLoginName(userBaseAccountInfo2);
//				if(userAccountSafeIn != null 
//						&& userAccountSafeIn.getLoginpassword() != null
//						&& userAccountSafeIn.getRisklevel() != null
//						&& userAccountSafeIn.getStatus() != null){
//					//用户风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结）
//					String risklevel =  userAccountSafeIn.getRisklevel().toString();
//					/* 账户状态(1正常\0停用) */
//					String status = userAccountSafeIn.getStatus().toString();
//					if(risklevel.equalsIgnoreCase("1") 
//							&& status.equalsIgnoreCase("1") 
//							|| risklevel.equalsIgnoreCase("2")
//							|| risklevel.equalsIgnoreCase("3")){
//						 //校验密码是否正确
//						 boolean pswflag = BCrypt.checkpw(password,userAccountSafeIn.getLoginpassword());
//						 if(pswflag){
//							 	//第三方应用微博 用户实体对象
//							 	User user = (User) request.getSession().getAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//							 	if(user != null){
//							 		//第三方实体对象
//							 		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
//							 		//用户ID
//							 		thirdPbLogin.setBaseid(userAccountSafeIn.getBaseid());
// 							 		//第三方名称
//							 		thirdPbLogin.setThirdpname("新浪微博");
//							 		//查询是否存在 避免多次绑定
//							 		ThirdPbLogin thirdPbLogin2 = thirdPbLoginServiceI.selectByThirdUserIdAndThirdNameBaseID(thirdPbLogin);
// 							 		//用户基本信息对象
//							 		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//						    		ubai.setId(userAccountSafeIn.getBaseid());
//					 				//查询用户基本信息
//						    		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//							 		if(thirdPbLogin2 == null){
//							 			//绑定时间
//							 			thirdPbLogin.setAuthbinddate(new Date());
//							 			//第三方用户ID
//								 		thirdPbLogin.setThirdpuid(user.getId());
//							 			//绑定状态 1绑定 0 未绑定
//							 			thirdPbLogin.setStatus(new Short("1"));
//							 			int count = 0;
//							 			//保存第三方登录信息
//							 			count = thirdPbLoginServiceI.insertSelective(thirdPbLogin);
//							 			if(count > 0 ){
//							 				//用户账号安全信息
// 								    		session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeIn);
// 								    		//用户基本信息
//											session.setAttribute(Session_Constant.USER, userBaseAccountInfo);
//											//清除微博session
//											session.removeAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//							 				//绑定成功
//							 				maps.put("result", "success");
//							 			}else{
//							 				//绑定失败
//							 				maps.put("result", "fail");
//							 			}
// 							 		}else{//已经绑定过了  后面绑定的覆盖前面已经绑定的，确保一个用户只能绑定一个微博
// 							 			//绑定覆盖时间
// 							 			thirdPbLogin2.setUpdatebinddate(new Date());
//							 			//第三方用户ID
// 							 			thirdPbLogin2.setThirdpuid(user.getId());
//							 			//绑定状态 1绑定 0 未绑定
// 							 			thirdPbLogin2.setStatus(new Short("1"));
//							 			int count = 0;
//							 			//更新保存第三方登录信息
//							 			count = thirdPbLoginServiceI.updateById(thirdPbLogin2);
//							 			if(count > 0 ){
//							 				//用户账号安全信息
// 								    		session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeIn);
// 								    		//用户基本信息
//											session.setAttribute(Session_Constant.USER, userBaseAccountInfo);
//											//清除微博session
//											session.removeAttribute(Session_Constant.USER_WEIBO_USER_CODE);
//							 				//绑定成功
//							 				maps.put("result", "success");
//							 			}else{
//							 				//绑定失败
//							 				maps.put("result", "fail");
//							 			}
//  							 		}
//							 	}else{
//							 		//微博用户session 失效 跳转首页 重新绑定
// 									maps.put("result", "logout");
//							 	}
//						 }else{
//							//账号密码错误
//							maps.put("result", "password_error");
//						 }
//					}else{
//						//账号存在安全隐患 禁止绑定
//						maps.put("result", "risklevel_error");
//					}
// 				}else{
//					//账号不存在
//					maps.put("result", "username_error");
//				}
//				
//			}else{
//				//传递过来的用户名和密码为空
//				maps.put("result", "username_null");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
// 		return null;
// 	}
//	/**
//	 * 
//	* @Title: tellPhoneCode 
//	* @Description: TODO(发送手机注册 验证码) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/sianPhoneCode",method = RequestMethod.POST)
//	public String tellPhoneCode(HttpServletRequest request,HttpServletResponse response){
//		//获取手机号码
//		String phone = request.getParameter("mobilephone");
//		Map<String,String> maps = new HashMap<String,String>();
//		try {
//			if(StringUtil.isNotEmpty(phone)){//手机号不为空进来了
//				if(StringUtil.isMobile(phone)){//手机号码格式正确进来了
//					UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//					ubai.setMobilephone(phone);
// 					//根据手机号查询是否存在
//					UserBaseAccountInfo	baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//					if(baseAccountInfo != null){
//						maps.put("result", "phone_exit");
//					}else{
// 						String tellpCode = String.valueOf(StringUtil.getN(6));
//						System.out.println(tellpCode);
//						SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						//调用短信接口发送手机短信验证码
//					    SMSSend.smsSend(phone, tellpCode, Iconstant.SMS_COMPANYNAME, sf);
//						session.setAttribute(Session_Constant.USER_WEIBO_PHONE_CODE, tellpCode);
//						maps.put("result", "success");
//					}
//				}else{
//					//手机号格式错误
//					maps.put("result", "phone_error");
//				}
//			}else{
//				//手机号为空值
//				maps.put("result", "phone_null");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		return null;
//	}
//	/**
//	 * 
//	* @Title: sendEmailCode 
//	* @Description: TODO(发送邮箱注册验证码) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/sian/sendEmailCode",method = RequestMethod.POST)
// 	public String sendEmailCode(HttpServletRequest request,HttpServletResponse response){
//		String email = request.getParameter("email");
//		Map<String,String> maps = new HashMap<String,String>();
//		try {
//			if(StringUtil.isNotEmpty(email)){//邮箱不为空进来了
//				 if(StringUtil.isEmail(email)){//邮箱正确进来了
//					 UserBaseAccountInfo  ubai = new UserBaseAccountInfo();
//					 ubai.setEmail(email);
//					 UserBaseAccountInfo accountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//					 if(accountInfo == null ){//邮箱没有注册可以发送
//						 //邮箱验证码
//						 String random = String.valueOf(StringUtil.getN(6));
//						 //调用邮箱发送接口发送短信
//						 boolean flag  = SendMailTempalte.sendEamilCode(email, random);
// 						 if(flag){
//							 session.setAttribute(Session_Constant.USER_WEIBO_EMAIL_CODE, random);
//							 //邮箱验证码发送成功
//							 maps.put("result", "success");
//							 
//						 }else{
//							 //邮箱验证码发送失败
//							 maps.put("result", "fail"); 
//						 }
// 					 }else{
// 						 //邮箱已存在了
//						 maps.put("result", "email_fail"); 
//					 }
//				 }else{
//					 //邮箱错误
//					 maps.put("result", "email_error");
//				 }
//			}else{
//				//邮箱为空值
//				maps.put("result", "email_null");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	* @Title: toBindThirUser 
//	* @Description: TODO(跳转用户绑定微博页面) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping("/sian/bindUser")
// 	public String toBindThirUser(HttpServletRequest request){
//		User user = (User) request.getSession().getAttribute(Session_Constant.USER_WEIBO_USER_CODE);
// 		if(user != null){
//			return "user/weibo/weibobindUserBaseInfo";
// 		}else{
// 			//跳转首页
//	 		return "redirect:/user/tologin.action";
// 		}
//	}
// 
// }
