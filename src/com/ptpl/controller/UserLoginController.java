package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.web.util.SMSSend;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserLog;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.service.UserRiskServiceI;
import com.ptpl.web.util.StringUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

/**
 * 
 * @ClassName: UserLoginController
 * @Package com.ptpl.controller
 * @Description: TODO(普通用户 登录 控制中心 )
 * @author chenjiaming
 * @date 2016年7月14日 下午3:04:23
 * @version V1.0
 */
@Controller
public class UserLoginController extends BaseController {
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;

	/**
	 * 用户账户信息安全Service
	 */
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	/**
	 * 用户基本信息Service
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**
	 * 用户日志信息 Service
	 */
	@Autowired
	private UserLogServiceI userLogServiceI;

	/**
	 * 用户托管账户信息ServiceI
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;

	/**
	 * 用户风控 service
	 */
	@Autowired
	private UserRiskServiceI userRiskService;

	// 6/6
	@Autowired
	SMSSendServiceI smsSendService;

	@Autowired
	private SMSSendServiceI sMSSendServiceI;

	/**
	 * 
	 * @Title: toUserLogin @Description: TODO(跳转 用户登录页面) @param @return
	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
	 */
	@RequestMapping(value = "/user/tologin", method = { RequestMethod.GET, RequestMethod.POST })
	public String toUserLogin(HttpServletRequest request) {

		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
				.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
		String str = request.getParameter("ab");
		if (userBaseAccountInfo != null && userAccountSafeInfo != null) {
			// return "redirect:touser.action";
			return "user/manager/managerCenter";
		} else {
			if (str != null && !str.trim().isEmpty()) {// 判断是否是因为被挤下来而被跳转到登入页面
				request.setAttribute("aabb", "被迫下线！");
			}
			return "user/manager/login";
			// return "user/login";
		}
	}

	/**
	 * @Title: UserLoinged @Description: TODO(用户登录 方法) @param @param
	 *         request @param @param response @param @return 参数说明 @return String
	 *         返回类型 @author chenjiaming @throws
	 */
	@RequestMapping(value = "/user/logined", method = { RequestMethod.POST, RequestMethod.GET })
	public String UserLoinged(HttpServletRequest request, HttpServletResponse response) {
		UserBaseAccountInfo userBaseAccountInfo99 = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
				.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
		if (userBaseAccountInfo99 != null && userAccountSafeInfo != null) {
			return "user/manager/managerCenter";
		}
		// 获取浏览器传递的验证码
		String code = request.getParameter("code");
		code = "111111";
		// 获取浏览器传递的用户名
		String loginUsername = request.getParameter("loginname");
		// 获取浏览器传递的密码
		String password = request.getParameter("password");
		Map<String, String> hashMap = new HashMap<String, String>();
		if (StringUtil.isEmpty(loginUsername)) {// 登录名不能为null
			hashMap.put("result", "loginUserName_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		if (StringUtil.isEmpty(password)) {// 登录密码不能为null
			hashMap.put("result", "password_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		if (StringUtil.isEmpty(code)) {// 验证码不能为null
			hashMap.put("result", "code_null");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 判断验证码是否正确
		boolean flag = StringUtil.verifyCodeCompare(code, request);
		if (!flag) {// 验证码不一致
			hashMap.put("result", "code_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		UserBaseAccountInfo userBaseAccountInfo2 = new UserBaseAccountInfo();
		loginUsername = setEncrypt(loginUsername);
		userBaseAccountInfo2.setLoginname(loginUsername);// 用户名
		userBaseAccountInfo2.setEmail(loginUsername);// 邮箱
		userBaseAccountInfo2.setMobilephone(loginUsername);// 手机号码

		// 根据用户名查询密码
		UserAccountSafeInfo userAccountSafeIn = userAccountSafeInfoServiceI
				.getLoginPassWordByLoginName(userBaseAccountInfo2);
		if (userAccountSafeIn == null || userAccountSafeIn.getLoginpassword() == null) {// 用户名或密码错误
			hashMap.put("result", "loginNameOrPsw_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 判断密码是否正确
		boolean pswFlag = BCrypt.checkpw(password, userAccountSafeIn.getLoginpassword());// 正确时为true；
		if (!pswFlag) {// 用户名或密码错误
			hashMap.put("result", "loginNameOrPsw_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 用户基本信息对象
		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI
				.selectByPrimaryKey(userAccountSafeIn.getBaseid());
		if (userBaseAccountInfo == null) {// 用户名或密码错误
			hashMap.put("result", "loginNameOrPsw_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		String name1 = AES.getDecrypt(userBaseAccountInfo.getLoginname());// 登录yongh
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		MyMapSessionId.getInstance().AddSession(name1, sessionId);// 异地登录

		/* 账户状态(1正常\0停用) */
		String status = userAccountSafeIn.getStatus().toString();
		if (status != null && status.equalsIgnoreCase("0")) {// 用户账号已停用
			hashMap.put("result", "userDisable");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 用户风险等级风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结） 风险暂不处理
		String risklevel = userAccountSafeIn.getRisklevel().toString();

		// 用户托管账户信息实体对象
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI
				.findUserFsAccountInfoByBaseId(userAccountSafeIn.getBaseid());
		// 更新最后登录时间
		userAccountSafeIn.setLastlogintime(new Date());
		// 更新最后登录ip
		String ipAddr = StringUtil.getIpAddr(request);
		userAccountSafeIn.setLastloginip(ipAddr);
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name != null && name.equals("JSESSIONID")) {
					String value = cookie.getValue();
					if (value != null) {
						// 更新最后登录cookie值
						userAccountSafeIn.setLastlogincookie(value);
					}
				}
			}
		}
		// 更新用户用户账户信息安全记录 最后登录Ip cookie 最后登录时间
		userAccountSafeInfoServiceI.update(userAccountSafeIn);
		// 保存用户登录日志信息
		userLogServiceI.insertSelective(getUserLog(userAccountSafeIn, ipAddr, getDecrypt(loginUsername)));
		// 把用户基本信息放进session
		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo));// UserBaseAccountInfo
		// 把用户托管账户信息实体对象放进session
		if (userFsAccountInfo != null) {
			session.setAttribute(Session_Constant.USERFSACCOUNTINFO,
					getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
		}
		// 把用户账号安全信息放进session
		session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeIn);
		try {
			// SpringContextHolder.getBean(UserBalanceQueryServiceI.class).saveUserBalance(userFsAccountInfo);//更新账号金钱
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String Referer = request.getHeader("Referer");
			hashMap.put("result", "success");
			hashMap.put("referer", Referer);

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
	 * @Title: findpwd @Description: TODO(跳转找回密码页面) @param @param
	 *         request @param @param response @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	@RequestMapping("/user/findpwd")
	public String findpwd(HttpServletRequest request, HttpServletResponse response) {
		return "user/manager/findpwd/findpwd";
	}

	/**
	 * 
	 * @Title: register @Description: TODO(跳转注册页面) @param @param
	 *         request @param @param response @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	@RequestMapping("/user/register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "user/manager/register";
	}

	/**
	 * 
	 * @Title: logout @Description: TODO( 用户 退出登录) @param 参数说明 @return void
	 *         返回类型 @author chenjiaming @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		if (userBaseAccountInfo != null) {
			// 将保存在磁对象的SessionId删除
			MyMapSessionId.getInstance().DeleteSessionId(userBaseAccountInfo.getLoginname());
		}
		session.invalidate();
		return "success";
	}

	/**
	 * 
	 * @Title: getUserLog @Description: TODO(获取用户日志信息 ) @param @param
	 *         userAccountSafeInfo @param @param ip 传递进来的最后登录ip地址 @param @param
	 *         username 用户登录名 @param @return 参数说明 @return UserLog 返回类型 @author
	 *         chenjiaming @throws
	 */
	public UserLog getUserLog(UserAccountSafeInfo userAccountSafeInfo, String ip, String username) {
		UserLog userLog = new UserLog();
		// 最后登录ip地址
		if (ip != null) {
			userLog.setIp(ip);
		}
		if (userAccountSafeInfo.getBaseid() != null) {
			// 用户Id
			userLog.setBaseid(userAccountSafeInfo.getBaseid());
		}
		if (username != null) {
			// 用户名
			userLog.setUsername(username);
		}
		// 用户类型1普通用户 2管理员用户
		userLog.setUsertype((short) 1);
		if (userAccountSafeInfo.getLastlogincookie() != null) {
			// cookie
			userLog.setCookie(userAccountSafeInfo.getLastlogincookie());
		}
		// 登录时间
		userLog.setLogintime(new Date());
		userLog.setOpercontent("用户登录");
		return userLog;
	}

	@ResponseBody
	@RequestMapping(value = "Mobilelogin", method = RequestMethod.POST)
	public String registerFirstSteps(String mobile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// boolean flagImgCode = false;
		/*
		 * 1.验证手机号 不为空 长度不等于11， 手机格式
		 */
		mobile = request.getParameter("loginname");
		System.out.println(mobile);
		boolean flagMobile = checkMobile(mobile, map);
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+flagMobile);
		String success = "success";
		String fail = "fail";
		if (flagMobile) {
			// sendJsonData(response, JSON.toJSONString(success));
			return success;
		} else {
			// sendJsonData(response, JSON.toJSONString(fail));
			return fail;
		}

	}

	/**
	 * UI 验证手机号
	 * 
	 * @param moblie
	 * @param map
	 */
	private boolean checkMobile(String mobile, Map<String, Object> map) {
		/*
		 * if (StringUtil.isEmpty(mobile)) { map.put("mobileErrMsg",
		 * "手机号码不能为空"); return false; } if (mobile.length() != 11) {
		 * map.put("mobileErrMsg", "请输入11位手机号码"); return false; } Pattern p =
		 * Pattern.compile(
		 * "^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[0-9])|18[0-9])\\d{8}$"
		 * ); Matcher m = p.matcher(mobile); if (!m.find()) { return false; }
		 */
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		ubai.setMobilephone(mobile);
		mobile = AES.getEncrypt(mobile);
		System.out.println(mobile);
		UserBaseAccountInfo ubai1 = userBaseAccountInfoService.getmobliepho(mobile);
		/*
		 * session.setAttribute("mobilepho", ubai1);
		 * System.out.println("--------------------------------"+ubai1);
		 */
		if (ubai1 == null) {
			return false;
		}
		return true;
	}

	/**
	 * 发送短信验证码        //  6/14
	 * 
	 * @param mobile
	 * @param codedx
	 * @param response
	 *//*
	@RequestMapping(value = "/yzm")
	public void registerSecondSteps(String loginname, String codedx) {
		System.out.println("手机号码：" + loginname);
		// codedx = request.getParameter("codedx");
		// System.out.println("用户手动输入短信验证码："+codedx);
		// 发送短信
		String verifyCode = String.valueOf(StringUtil.getN(6));
		boolean flags = sMSSendServiceI.SMSSend4Code(loginname, verifyCode);

		// boolean flag=smsSendService.SMSSend4Code(mobilephone, verifyCode);
		if (flags) {
			session.setAttribute("mobileCode", verifyCode);
			System.out.println("获取到的短信验证码：" + verifyCode);
		}

	}*/
	// private void smsSend(String mobilephone) {
	// // 正式使用时根据数据库短信接口表的设置获取
	// System.out.println(mobilephone);
	//
	// // 发送短信
	// /*Map<String, String> map = smsSendService.SMSSend(new BigDecimal(1),
	// null, mobilephone, verifyCode, null, null,
	// null);
	// String status = map.get("status");
	// if (status.equals("1")) {
	// session.setAttribute("mobileCode", verifyCode);
	// System.out.println("获取到的短信验证码："+verifyCode);
	// }*/
	//
	// }

	// 核对短信是否正确
	@ResponseBody
	@RequestMapping(value = "/yzmcheck")
	public String yzmcheck(String codedx) {
		System.out.println(".........");
		// System.out.println(num);
		codedx = request.getParameter("codedx");
		// System.out.println("用户手动输入短信验证码："+codedx);
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		mav.addObject("uname", session.getAttribute("uname"));
		// num
		int anum = (int) (Math.random() * 124754);
		mav.addObject("num", anum);
		session.setAttribute("num", anum);
		mav.addObject("messSendTime", new Date().getTime());
		String verifyCode = (String) session.getAttribute("mobileCode");
		if (verifyCode == null || verifyCode == "") {
			return "fail";
		}
		if (verifyCode.equals(codedx)) {
			// mav.setViewName("/user/manager/managerCenter");
			return "success";
		} else {
			return "fail";
		}

	}

	// 短信效验成功
	@RequestMapping(value = "/successful")
	public String bindQQ(HttpServletRequest request, HttpServletResponse response)
			throws IOException, QQConnectException {
		response.setContentType("text/html;charset=utf-8");
		String mobile = request.getParameter("loginname");
		System.out.println(mobile);
		mobile = AES.getEncrypt(mobile);
		UserBaseAccountInfo ubai1 = userBaseAccountInfoService.getmobliepho(mobile);
		UserBaseAccountInfo ubaseA = userBaseAccountInfoService.getUserBaseAccountInfoById(ubai1.getId());
		System.out.println("...................." + ubaseA);
		String name1 = AES.getDecrypt(ubaseA.getLoginname());// 登录yongh
		// 对sessionId进行保存处理=====
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		MyMapSessionId.getInstance().AddSession(name1, sessionId);// 异地登录
		// 把用户基本信息放进session
		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubaseA));
		request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
				userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId())); // 6/8
		request.getSession().setAttribute(Session_Constant.USER, ubaseA);
		return "user/manager/managerCenter";
	}

	// /**
	// *
	// * @Title: updatepsw
	// * @Description: TODO(跳转修改密码页面 只能通过手机短信验证码修改 （用户被添加到了指引名单）)
	// * @param @param request
	// * @param @return 参数说明
	// * @return String 返回类型
	// * @author chenjiaming
	// * @throws
	// */
	// @RequestMapping("/jumpupdatepsw")
	// public String jumpupdatepsw (HttpServletRequest request){
	// UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo)
	// request.getSession().getAttribute("userBaseAccountInfo");
	// if(userBaseAccountInfo != null){
	// request.setAttribute("userBaseAccountInfo", userBaseAccountInfo);
	// return "user/updatepsw";
	// }else{
	// return "redirect:/user/tologin.action";
	// }
	// }

	// @RequestMapping(value="/updatepassword",method=RequestMethod.POST)
	// public String updatepassword(HttpServletRequest
	// request,HttpServletResponse response) throws IOException{
	// UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo)
	// request.getSession().getAttribute("userBaseAccountInfo");
	// String phoneCode = request.getParameter("phonecode");
	// String password = request.getParameter("password");
	// Map<String,String> hashMap = new HashMap<String,String>();
	// if(userBaseAccountInfo != null && userBaseAccountInfo.getId() != null){
	// if(StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(phoneCode)){
	// String code = (String) request.getSession().getAttribute("tellpCode");
	// if(StringUtil.isNotEmpty(code) &&
	// code.equalsIgnoreCase(phoneCode)){//验证码正确
	// UserAccountSafeInfo userAccountSafeInfo =
	// userAccountSafeInfoServiceI.selectByBaseId(userBaseAccountInfo.getId());
	// if(userAccountSafeInfo != null){
	// //加密密码
	// String pwt = BCrypt.hashpw(password, BCrypt.gensalt());
	// userAccountSafeInfo.setLoginpassword(pwt);
	// /* 账户状态(1正常\0停用) */
	// userAccountSafeInfo.setStatus((short) 1);
	// /* 风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结） */
	// userAccountSafeInfo.setRisklevel((short) 1);
	// int count = 0;
	// count = userAccountSafeInfoServiceI.update(userAccountSafeInfo);
	// if(count > 0){
	// count = 0;
	// count = userRiskService.deleteByBaseId(userBaseAccountInfo.getId());
	// if(count > 0){//修改成功
	// session.removeAttribute("userBaseAccountInfo");
	// hashMap.put("result", "success");
	// }else{//修改失败
	// hashMap.put("result", "fail");
	// }
	// }else{//修改失败
	// hashMap.put("result", "fail");
	// }
	// }else{//修改失败
	// hashMap.put("result", "fail");
	// }
	// }else{//手机验证码错误
	// hashMap.put("result", "code_error");
	// }
	// }else{//参数为null
	// hashMap.put("result", "ps_null");
	// }
	// }else{
	// hashMap.put("result", "logout");
	// }
	// String str = JSON.toJSONString(hashMap);
	// StringUtil.sendJsonData(response, str);
	// return null;
	// }

	/**
	 * @throws IOException
	 * 
	 * @Title: sendSsm @Description: TODO(发送短信 用户被添加到了指引名单，修改用户名登录密码
	 *         只能通过手机短信验证码修改) @param @param request @param @param
	 *         response @param @return 参数说明 @return String 返回类型 @author
	 *         chenjiaming @throws
	 */
	// @RequestMapping(value="/updatepsw/sendssm",method = RequestMethod.POST)
	// public String sendSsm(HttpServletRequest request ,HttpServletResponse
	// response) throws IOException{
	// String phone = request.getParameter("phone");
	// if(StringUtil.isNotEmpty(phone) && StringUtil.isMobile(phone)){
	// Map<String,String> hashMap = new HashMap<String,String>();
	// String tellpCode = String.valueOf(StringUtil.getN(6));
	// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// //发送短信
	// System.out.println("========手机短信验证码========"+tellpCode);
	// //SMSSend.smsSend(phone, tellpCode, Iconstant.SMS_COMPANYNAME, sf);
	// session.setAttribute("tellpCode", tellpCode);
	// hashMap.put("result", "success");
	// String str = JSON.toJSONString(hashMap);
	// StringUtil.sendJsonData(response, str);
	// }
	// return null;
	// }

	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getContextClassLoader());
	}
}
