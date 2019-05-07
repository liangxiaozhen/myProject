package com.ptpl.controller.third;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoLevelRestrict;
import com.ptpl.model.PromoTotalRestrict;
import com.ptpl.model.ThirdPbLogin;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.PromoLevelRestrictServiceI;
import com.ptpl.service.PromoTotalRestrictServiceI;
import com.ptpl.service.ThirdPbLoginServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserNameRuleModuleServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
import com.ptpl.web.Iconstant.Iconstant;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.ShareCodeUtil;
import com.ptpl.web.util.StateUtils;
import com.ptpl.web.util.StringUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.Avatar;

import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

/**
 * 
 * @ClassName: UserWeiBoLoginController
 * @Package com.ptpl.controller
 * @Description: TODO(第三方登录授权 新浪微博 登录 Controller 层)
 * @author chenjiaming
 * @date 2016年8月1日 下午2:43:16
 * @version V1.0
 */
@Controller
public class UserWeiBoLoginController extends BaseController {
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;

	/**
	 * 用户基本信息 service
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**
	 * 用户账号安全信息 service
	 */
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	/**
	 * 第三方登录授权 service
	 */
	@Autowired
	private ThirdPbLoginServiceI thirdPbLoginServiceI;
	/**
	 * 用户账户 service
	 */
	@Autowired
	private UserAccountServiceI userAccountServiceI;
	// 生成用户号
	@Autowired
	OracleSequenceMaxValueIncrementer accountnumber;
	/**
	 * 用户推广ServiceI
	 */
	@Autowired
	private UserPromoServiceI userPromoService;
	/**
	 * 推广限制ServiceI
	 */
	@Autowired
	private PromoTotalRestrictServiceI promoTotalRestrictService;
	/**
	 * 推广各层人数限制ServiceI
	 */
	@Autowired
	private PromoLevelRestrictServiceI promoLevelRestrictService;
	/**
	 * 用户第三方推广ServiceI
	 */
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	/**
	 * 代理级别的推广权限ServiceI
	 */
	@Autowired
	private AgentGradePromoAuthServiceI agentGradePromoAuthService;
	/**
	 * 代理级别推广公司关联表ServiceI
	 */
	@Autowired
	private AGTPLinkServiceI AGTPLinkService;
	/**
	 * 注册规则ServiceI
	 */
	@Autowired
	private UserNameRuleModuleServiceI userNameRuleModuleService;
	/**登录按钮入口
	 * @throws QQConnectException 
	 */
	@RequestMapping("/sian/toweibo")
	public void toweibo(HttpServletRequest request, HttpServletResponse response) throws IOException, WeiboException {
		response.setContentType("text/html;charset=utf-8");
		
		Oauth oauth = new Oauth();
		String url = oauth.authorize("code", "");
		System.out.println(1);
		System.out.println(url);
			String enterwbkey=null;
			HttpSession session=request.getSession(false);
				if(session.getAttribute("enterwbkey")!=null){
					enterwbkey=session.getAttribute("enterwbkey").toString();
					if(null!=enterwbkey&&enterwbkey=="loginWBkey"){
						response.sendRedirect(url);
					}else{
						response.sendRedirect(url);
						enterwbkey="loginWBkey";
					}
				}else{
					response.sendRedirect(url);
					session.setAttribute("enterwbkey", "loginWBkey");
				}
	}

	/**注册按钮入口
	 * @throws QQConnectException 
	 * @throws WeiboException 
	 */
	@RequestMapping("/weibo/registerForWB")
	public void Rgtoqq(HttpServletRequest request, HttpServletResponse response) throws IOException, WeiboException {
		response.setContentType("text/html;charset=utf-8");
		Oauth oauth = new Oauth();
		String url = oauth.authorize("code", "");
		String enterwbkey=null;
			//创建session
			HttpSession session=request.getSession(false);
		
				if(session.getAttribute("enterwbkey")!=null){
					enterwbkey=session.getAttribute("enterwbkey").toString();
					if(null!=enterwbkey&&enterwbkey=="rsWBkey"){
						response.sendRedirect(url);
					}else{
						response.sendRedirect(url);
						enterwbkey="rsWBkey";
					}
				}else{
					response.sendRedirect(url);
					session.setAttribute("enterwbkey", "rsWBkey");
				}
	}
	/**绑定按钮入口
	 * @throws QQConnectException 
	 * @throws WeiboException 
	 */
	@RequestMapping("/weibo/bindWB")
	public void bindWB(HttpServletRequest request, HttpServletResponse response) throws IOException, WeiboException {
		response.setContentType("text/html;charset=utf-8");
		Oauth oauth = new Oauth();
		String url = oauth.authorize("code", "");
		String enterwbkey=null;
			//创建session
			HttpSession session=request.getSession(false);
			
				if(session.getAttribute("enterwbkey")!=null){
					enterwbkey=session.getAttribute("enterwbkey").toString();
					if(null!=enterwbkey&&enterwbkey=="bdWBkey"){
						response.sendRedirect(url);
					}else{
						session.removeAttribute(enterwbkey);
                        enterwbkey="bdWBkey";
						session.setAttribute("enterwbkey", enterwbkey);
						response.sendRedirect(url);
					}
				}else{
					response.sendRedirect(url);
					session.setAttribute("enterwbkey", "bdWBkey");
				}
	}
	/**
	 * 微博登录失败后（点击注册）
	 */
	@RequestMapping(value = "/weibo/rsForWBafterLgFail", method = { RequestMethod.POST,
	RequestMethod.GET })
	public String lgIndexForWB(@RequestParam(value = "thirdPbLogin") ThirdPbLogin thirdPbLogin,HttpServletRequest request) {
			HttpSession sessioon=request.getSession();
			String loginname=thirdPbLogin.getThirdpname();
			String thirdpuid=thirdPbLogin.getThirdpuid();
			UserBaseAccountInfo userBai=userBaseAccountInfoServiceI.getuserloginname(loginname);
			ThirdPbLogin tl=new ThirdPbLogin();
			tl.setThirdpuid(thirdpuid);
			tl=thirdPbLoginServiceI.selectByThirdUserIdAndThirdNameBaseID(tl);
			if(userBai==null&&tl==null){
				UserNameRuleModule unrm = userNameRuleModuleService.selective(null).get(4);
				if (unrm.getIsuse() == (short) 1) {
					int randomLength = unrm.getRandomlength().intValue();
					String nameVal = "";
					String presetStr = unrm.getPresetstr();
					nameVal = presetStr + StringUtil.getN(randomLength);
					UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
					ubai1.setLoginname(nameVal);
					ubai1.setRealname(loginname);
					Date dt=new Date();
					ubai1.setRegdate(dt);
					int count=userBaseAccountInfoServiceI.insertSelective(ubai1);
					if(count>0){
						UserBaseAccountInfo ubai2=userBaseAccountInfoServiceI.getuserloginname(nameVal);
							ThirdPbLogin thirdL=new ThirdPbLogin();
							 thirdL.setThirdpname(loginname);
							 thirdL.setAuthbinddate(dt);
							 thirdL.setBaseid(ubai2.getId());
							 thirdL.setThirdpuid(thirdpuid);
							 thirdL.setThirdAPPName("WB");
							 thirdL.setImgurl(thirdPbLogin.getImgurl());
							 int count2 =thirdPbLoginServiceI.insertSelective(thirdL);
							 	if(count2>0){
							 		
							 		//对sessionId进行保存
							 		String sessionId=session.getId();
							 		MyMapSessionId.getInstance().AddSession(ubai2.getLoginname(), sessionId);
							 		
							 		
									request.getSession().setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubai2));
									return "user/manager/managerCenter";
							 	}
					}
					
					//对sessionId进行保存
			 		String sessionId=session.getId();
			 		MyMapSessionId.getInstance().AddSession(ubai1.getLoginname(), sessionId);
			 		
			 		
					request.getSession().setAttribute(Session_Constant.USER,ubai1);
					return "user/manager/managerCenter";
				}
			}else{
				return "false";
			}
			
			//对sessionId进行保存
	 		String sessionId=session.getId();
	 		MyMapSessionId.getInstance().AddSession(userBai.getLoginname(), sessionId);
			
			request.getSession().setAttribute(Session_Constant.USER,userBai);
		return "user/manager/managerCenter";
	}
	
	/**
	*用户绑定（登录失败后执行：绑定已通过普通注册但没绑定此微博的本站用户）
	 */
	@RequestMapping(value= "/weibo/bdYonghu" ,method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String bdYonghu(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String retRs=null;
		//获取浏览器传递的用户名
		String loginUsername = request.getParameter("loginname");
 		//获取浏览器传递的密码
		String password = request.getParameter("password");
		//加密 
		loginUsername = setEncrypt(loginUsername);
		if(loginUsername !=null && (!loginUsername.equals(""))){
		UserBaseAccountInfo userBaseAccountInfo2 = new UserBaseAccountInfo();
 		userBaseAccountInfo2.setLoginname(loginUsername);//用户名
 		userBaseAccountInfo2.setEmail(loginUsername);//邮箱
 		userBaseAccountInfo2.setMobilephone(loginUsername);//手机号码
 		retRs="fail";
 		
 		//根据用户名查询密码
		UserAccountSafeInfo  userAccountSafeIn = userAccountSafeInfoServiceI.getLoginPassWordByLoginName(userBaseAccountInfo2);
		if(userAccountSafeIn == null || userAccountSafeIn.getLoginpassword() == null){//用户名或密码错误
			return retRs;
		}else{
					//判断密码是否正确
					boolean pswFlag = BCrypt.checkpw(password,userAccountSafeIn.getLoginpassword());
			 		if(!pswFlag){//用户名或密码错误
						return retRs;
			 		}else{
			 			UserBaseAccountInfo ubai= userBaseAccountInfoServiceI.getUserBaseAccountInfoById(userAccountSafeIn.getBaseid());
			 			ThirdPbLogin thirdL=new ThirdPbLogin();
			 			//綁定wb
			 			Object userObj=session.getAttribute("thirdPbLogin");
						net.sf.json.JSONObject jsonU = net.sf.json.JSONObject.fromObject(userObj);
							if(jsonU!=null&&(!jsonU.equals(""))){
								 String imgurl=jsonU.getString("imgurl");
								 String thirdpname=jsonU.getString("thirdpname");
								 String thirdpuid=jsonU.getString("thirdpuid");
								 thirdpname=setEncrypt(thirdpname);
								 //ubai.setRealname(thirdpname);    // 5/23
								 int count = userBaseAccountInfoServiceI.update(ubai);  // 5/23
								 if(count > 0){
							     thirdpname=getDecrypt(thirdpname);
								 thirdL.setThirdpname(thirdpname);
								 thirdL.setBaseid(ubai.getId());
								 thirdL.setThirdpuid(thirdpuid);
								 thirdL.setImgurl(imgurl);
								 thirdL.setThirdAPPName("WB");
								 thirdL.setBaseid(userAccountSafeIn.getBaseid());
								 Date dt =new Date();
								 thirdL.setAuthbinddate(dt);
								 thirdPbLoginServiceI.insertSelective(thirdL);
								 String name1 = AES.getDecrypt(ubai.getLoginname());//登录yongh
									HttpSession session1 = request.getSession();
									String sessionId = session1.getId();
									MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
									//request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubai.getId())); // 5/24
				 					// 把用户基本信息放进session
							 		session1.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubai));// UserBaseAccountInfo
							     	// 用户托管账户信息实体对象
									UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI
											.findUserFsAccountInfoByBaseId(userAccountSafeIn.getBaseid());
									// 把用户托管账户信息实体对象放进session
									if (userFsAccountInfo != null) {
										session1.setAttribute(Session_Constant.USERFSACCOUNTINFO, getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
									}
									// 把用户账号安全信息放进session
									session1.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeIn);
								 
								 //request.getSession().setAttribute(Session_Constant.USER,getDecryptionUserBaseAccountInfoDetail(ubai));
					 			 retRs="success";
								 return retRs;
								 }
							}
							return retRs;
			 		}
		}
 	}
 		return null;
}
	
	/**
	 * * 已经绑定好后，跳入用户后台
	 */
	@RequestMapping(value = "/weibo/bangDingWBLogin", method = { RequestMethod.POST,RequestMethod.GET })
	public String bangDingWBLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);//获取userBaseAccountInfo 对象
		//对sessionId保存处理
		if(userBaseAccountInfo !=null){
			HttpSession session=request.getSession();
			String sessionId=session.getId();
			MyMapSessionId m=MyMapSessionId.getInstance();
			m.AddSession(userBaseAccountInfo.getLoginname(), sessionId);//将sessionId以登入名为key，sessionId为value保存
		}
		return "user/manager/managerCenter";
	}
	/**
	 * 
	 * @Title: toCode @Description: TODO(微博用户同意授权后 进来了) @param @param
	 * session @param @param request @param @return @param @throws
	 * WeiboException 参数说明 @return String 返回类型 @author 苏景焱  @throws
	 */
	@RequestMapping(value = "/sian/toCode", method = RequestMethod.GET)
	public String toCode(HttpServletRequest request) throws WeiboException {
		HttpSession session = request.getSession(false);
		Object enterwbkey = session.getAttribute("enterwbkey");
		// 授权对象类
		Oauth oauth = new Oauth();
		// 获取用户同意授权后的参数
		String code = request.getParameter("code");
		System.out.println("``===`" + code);
		if (StringUtil.isNotEmpty(code)) {

			try {
				// 获取令牌
				weibo4j.http.AccessToken accessTokenObj = oauth.getAccessTokenByCode(code);
				if (StringUtil.isNotEmpty(accessTokenObj.getAccessToken())) {
					String accessToken = accessTokenObj.getAccessToken();
					String uidJson = accessTokenObj.getUserUid();
					// System.out.println(uid232);
					// Account account =new Account(accessToken);
					// JSONObject uidJson = account.getUid();
					if (uidJson != null && (!uidJson.equals(""))) {
						// String uid = uidJson.getString("uid");
						Users users = new Users(accessToken);
						User weiboUser = users.showUserById(uidJson);
						String nkName = weiboUser.getName();
						String wbId = weiboUser.getId();
						String uImg = weiboUser.getProfileImageUrl();
						if (enterwbkey != null && (enterwbkey.toString().equals("rsWBkey"))) {
							ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
							thirdPbLogin.setThirdpuid(wbId);
							thirdPbLogin.setThirdAPPName("WB");
							thirdPbLogin.setThirdpname(nkName);

							ThirdPbLogin thirdPbLogin1 = thirdPbLoginServiceI.getBaseIdByOtherParams(thirdPbLogin);

							// 如果已经存在绑定账号，则直接跳入用户后台
							if (thirdPbLogin1 != null && thirdPbLogin1.getBaseid() != null) {
								UserBaseAccountInfo ubaseA = userBaseAccountInfoServiceI
										.getUserBaseAccountInfoById(thirdPbLogin1.getBaseid());// (1)
								String name1 = AES.getDecrypt(ubaseA.getLoginname());// 登录yongh
								HttpSession session1 = request.getSession();
								// 对sessionId进行保存处理 ，为后期判断是否有多地登入情况
								String sessionId = session1.getId();
								MyMapSessionId.getInstance().AddSession(name1, sessionId);// 异地登录
								// 把用户基本信息放进session
								session.setAttribute(Session_Constant.USER,
										getDecryptionUserBaseAccountInfoDetail(ubaseA));
								request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
										userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId())); // 5/19
								request.getSession().setAttribute(Session_Constant.USER, ubaseA);
								return "user/manager/managerCenter";
							} else {
								// 按照系统规则注册新用户并绑定此微博号
								return regsterBdWB(request, nkName, wbId, uImg);
							}
							// 登录入口
						} else if (enterwbkey != null && (enterwbkey.toString().equals("loginWBkey"))) {
							ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
							thirdPbLogin.setThirdpuid(wbId);
							thirdPbLogin.setThirdAPPName("WB");
							ThirdPbLogin thirdPbLogin1 = thirdPbLoginServiceI
									.selectByThirdUserIdAndThirdName(thirdPbLogin);
							// 如果已经存在绑定账号，则直接跳进用户后台，否则，进入登录失败页面，提示注册或者绑定已经注册过的本站用户
							if (thirdPbLogin1 != null && thirdPbLogin1.getBaseid() != null) {
								/*
								 * request.getSession().setAttribute(
								 * "thirdPbLogin", thirdPbLogin1);
								 */
								UserBaseAccountInfo ubai2 = userBaseAccountInfoServiceI
										.getUserBaseAccountInfoById(thirdPbLogin1.getBaseid());
								String name1 = AES.getDecrypt(ubai2.getLoginname());// 登录yongh
								HttpSession session1 = request.getSession();
								// 对sessionId进行保存处理 ，为后期判断是否有多地登入情况
								String sessionId = session1.getId();
								MyMapSessionId.getInstance().AddSession(name1, sessionId);// 异地登录
								// request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
								// userAccountSafeInfoServiceI.selectBaseId(ubai.getId()));
								// // 5/24
								// 把用户基本信息放进session
								session1.setAttribute(Session_Constant.USER,
										getDecryptionUserBaseAccountInfoDetail(ubai2));
								request.getSession().setAttribute(Session_Constant.USER, ubai2);
								request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
										userAccountSafeInfoServiceI.selectBaseId(ubai2.getId()));
								return "user/manager/managerCenter";
							} else {
								// 进入登录失败页面
								thirdPbLogin.setImgurl(uImg);
								thirdPbLogin.setThirdpname(nkName);
								request.getSession().setAttribute("thirdPbLogin", JSON.toJSONString(thirdPbLogin));
								return "user/weibo/WBloginFail";
							}
						} else if (session.getAttribute("enterwbkey") != null
								&& session.getAttribute("enterwbkey").equals("bdWBkey")) {
							UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession()
									.getAttribute(Session_Constant.USER);
							ThirdPbLogin thirdL = new ThirdPbLogin();
							if (user != null && (!user.toString().isEmpty())) {
								UserBaseAccountInfo userBaseA = userBaseAccountInfoServiceI
										.getUserBaseAccountInfoById(user.getId());
								// userBaseA.setRealname(nkName);
								userBaseAccountInfoServiceI.update(userBaseA);
								thirdL.setThirdpname(nkName);
								thirdL.setBaseid(userBaseA.getId());
								thirdL.setThirdpuid(wbId);
								thirdL.setThirdAPPName("WB");
								thirdL.setImgurl(uImg);
								Date dt = new Date();
								thirdL.setAuthbinddate(dt);
								thirdPbLoginServiceI.insertSelective(thirdL);
								return "redirect:/third/bingDing.action";
							}
						}

					}

				}
			} catch (WeiboException e) {
				e.printStackTrace();
			}
		} else {
			// 重定向到登录页面
			return "redirect:/user/tologin.action";
		}
		return "redirect:/user/tologin.action";
	}

	/**
	 * 按照系统规则注册新用户并绑定此微博号
	 * @param request
	 * @param nkName
	 * @param wbId
	 * @param uImg
	 * @return
	 */
	private String regsterBdWB(HttpServletRequest request, String nkName, String wbId, String uImg) {
		UserNameRuleModule unrm = userNameRuleModuleService.selective(null).get(4);
		if (unrm.getIsuse() == (short) 1) {
			int randomLength = unrm.getRandomlength().intValue();
			String name = "";
			String presetStr = unrm.getPresetstr();
			name = presetStr + StringUtil.getN(randomLength);
			UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
			name=setEncrypt(name);
			//nkName=setEncrypt(nkName);
			ubai1.setLoginname(name);
			//ubai1.setRealname(nkName);
			Date dt=new Date();
			ubai1.setRegdate(dt);
			int count=userBaseAccountInfoServiceI.insertSelective(ubai1);
			if (count > 0) {
				UserBaseAccountInfo ubai2=userBaseAccountInfoServiceI.getuserloginname(name);
				// nkName=getDecrypt(nkName);
				ThirdPbLogin thirdL=new ThirdPbLogin();
				thirdL.setBaseid(ubai2.getId());
				 thirdL.setThirdpname(nkName);
				 thirdL.setAuthbinddate(dt);
				 thirdL.setThirdpuid(wbId);
				 thirdL.setImgurl(uImg);
				 thirdL.setThirdAPPName("WB");
				 int count2 =thirdPbLoginServiceI.insertSelective(thirdL);
			    UserAccountSafeInfo userAccountSafeInfo = new UserAccountSafeInfo();
			    // baseID
			    userAccountSafeInfo.setBaseid(ubai2.getId());
				// 风险等级
				userAccountSafeInfo.setRisklevel((short) 1);
				// 会员等级
				userAccountSafeInfo.setUgrade((short) 0);
				// 会员类型
				userAccountSafeInfo.setUgradetype((short) 1);
				// 账户状态 1正常
				userAccountSafeInfo.setStatus((short) 1);
				int count3 = userAccountSafeInfoServiceI.insert(userAccountSafeInfo);
			 	if(count2 > 0 && count3 > 0){
					/*request.getSession().setAttribute("thirdPbLogin", thirdPbLogin1);*/
					String name1 = AES.getDecrypt(ubai2.getLoginname());//登录yongh
					HttpSession session1 = request.getSession();
					//对sessionId进行保存处理 ，为后期判断是否有多地登入情况
					String sessionId = session1.getId();
					MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
					// 把用户基本信息放进session
			 		session1.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubai2));
					request.getSession().setAttribute(Session_Constant.USER,ubai2);
					request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubai2.getId()));
					return "user/manager/managerCenter";
				}
			}
		}
		return "redirect:/user/tologin.action";
	}
	/*// 获取用户ID主键
	String uid = accessToken.getUserUid();
	if (StringUtil.isNotEmpty(uid))
	{
		// 第三方实体类
		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
		// set注入第三方用户ID
		thirdPbLogin.setThirdpuid(uid);
		// set注入第三方名称
		thirdPbLogin.setThirdpname("新浪微博");
		// 查询是否已经绑定
		ThirdPbLogin thirdPbLogin2 = thirdPbLoginServiceI
				.selectByThirdUserIdAndThirdName(thirdPbLogin);
		if (thirdPbLogin2 != null)
		{// 已经绑定过了 这里走安全风控路线
			UserBaseAccountInfo ubai = new UserBaseAccountInfo();
			ubai.setId(thirdPbLogin2.getBaseid());// 用户ID
			// 用户基本信息
			UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI
					.getUserBaseAccountInfoByOneCondition(ubai);
			// 用户安全基本信息
			UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI
					.selectByBaseId(thirdPbLogin2.getBaseid());
			if (userAccountSafeInfo != null && userBaseAccountInfo != null
					&& userAccountSafeInfo.getRisklevel() != null
					&& userAccountSafeInfo.getStatus() != null)
			{
				// 用户风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结）
				String risklevel = userAccountSafeInfo.getRisklevel().toString();
				 账户状态(1正常\0停用) 
				String status = userAccountSafeInfo.getStatus().toString();
				if (status.equalsIgnoreCase("1") && risklevel.equalsIgnoreCase("1"))
				{
					// 用户账号安全信息实体对象
					session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
							userAccountSafeInfo);
					// 用户基本信息实体对象
					session.setAttribute(Session_Constant.USER, userBaseAccountInfo);
					// 跳转登录后的页面
					modelAndView.setViewName("redirect:/user/touser.action");
				}
			}
		} else
		{// 没有和新浪微博进行绑定过 跳转绑定页面 这里进行用户名规则 用户注册绑定操作
			
			 * try { StringUtil.sendJsonData(response,
			 * "用户名注册！！ 这里进行用户注册 绑定操作"); } catch (IOException e) {
			 * e.printStackTrace(); }
			 
			request.getSession().setAttribute(Session_Constant.USER_WEIBO_USER_CODE, uid);
			modelAndView.setViewName("user/weibo/weiboMessage");
			return modelAndView;
		}
	}*/
	/**
	 * 
	 * @Title: insertThirdPBLogin @Description: TODO(第三方绑定 方法) @param @param
	 * baseID 用户 ID @param @param thirdpuid 第三方ID @param @return 参数说明 @return
	 * boolean 返回类型 返回true 绑定成功 @author cjm @throws
	 */
	@RequestMapping(value = "/account/bingding", method = RequestMethod.GET)
	public boolean insertThirdPBLogin(BigDecimal baseID, String thirdpuid)
	{
		boolean flag = false;
		Assert.notNull(thirdpuid, "'thirdpuid' 第三方ID 不能为null");
		Assert.notNull(baseID, "'baseID' 用户ID 不能为null");

		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
		thirdPbLogin.setBaseid(baseID);// 用户ID
		thirdPbLogin.setThirdpuid(thirdpuid); // 第三方绑定用户ID
		thirdPbLogin.setStatus((short) 1); // 绑定状态 1绑定成功 0 未绑定
		thirdPbLogin.setThirdpname("新浪微博"); // 第三方名称
		thirdPbLogin.setAuthbinddate(new Date()); // 绑定时间
		int count = 0;
		count = thirdPbLoginServiceI.insertSelective(thirdPbLogin);
		if (count > 0)
		{
			flag = true;
		}
		return flag;
	}
	/**
	 * 点击确定注册后，返回JSON数据
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/sian/confirm")
	public void confirm() throws Exception
	{
		String data = "tologin";
		String thirdID = (String) request.getSession()
				.getAttribute(Session_Constant.USER_WEIBO_USER_CODE);
		if (StringUtil.isNotEmpty(thirdID))
		{
			UserNameRuleModule unrm = userNameRuleModuleService.selective(null).get(4);
			data = "close";
			if (unrm.getIsuse().equals((short) 1))
			{
				UserBaseAccountInfo ubai = new UserBaseAccountInfo();
				/** 用户基本信息 */
				setUserBaseAccountInfo(ubai,unrm);
				/** 用户账户安全信息 */
				setUserAccountSafeInfo(ubai);
				/** 用户账户 */
				setUserAccount(ubai);
				/** 用户推广设置 */
				setUserPromo(ubai, null);
				/** 用户第三方推广设置 */
				setUserPromoThirdParty(ubai.getId());
				/*
				 * 绑定
				 */
				insertThirdPBLogin(ubai.getId(), thirdID);
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
 
	/**
	 * 用户账户
	 * 
	 * @param ubai
	 */
	private void setUserAccount(UserBaseAccountInfo ubai)
	{
		UserAccount userAccount = new UserAccount();
		userAccount.setBaseid(ubai.getId());
		userAccountServiceI.insert(userAccount);
	}
	/**
	 * 用户账户安全信息
	 * 
	 * @param ubai
	 * @param hashed
	 */
	private void setUserAccountSafeInfo(UserBaseAccountInfo ubai)
	{
		UserAccountSafeInfo userAccountSafeInfo = new UserAccountSafeInfo();
		// baseID
		userAccountSafeInfo.setBaseid(ubai.getId());
		// 风险等级
		userAccountSafeInfo.setRisklevel((short) 1);
		// 会员等级
		userAccountSafeInfo.setUgrade((short) 0);
		// 会员类型
		userAccountSafeInfo.setUgradetype((short) 1);
		// 账户状态 1正常
		userAccountSafeInfo.setStatus((short) 1);
		userAccountSafeInfoServiceI.insert(userAccountSafeInfo);
	}
	/**
	 * 用户基本账户信息
	 * 
	 * @param ubai
	 * @param regcookie
	 */
	private void setUserBaseAccountInfo(UserBaseAccountInfo ubai,UserNameRuleModule unrm)
	{
		String nameRule=unrm.getNamerule();
		int randomLength=unrm.getRandomlength().intValue();
		String name="";
		if(nameRule.equals("a")){
			name=unrm.getPresetstr()+StringUtil.getN(randomLength);
		}
		if(nameRule.equals("b")){
			name=StringUtil.getN(randomLength)+"";
		}
		//用户名
		ubai.setLoginname(name);
		// 用户号
		ubai.setAccountnumber(accountnumber.nextStringValue());
		// 注册IP
		ubai.setRegip(StringUtil.getIpAddr(request));
		// 注册Cookie
		ubai.setRegcookie(setCookie());
		// 账户类型 个人
		ubai.setAccounttype((short) 1);
		// 注册类型
		ubai.setRegtype((short) 5);
		// 注册时间
		ubai.setRegdate(new Date());
		userBaseAccountInfoServiceI.insertSelective(ubai);
	}
	/**
	 * 用户推广设置
	 * 
	 * @param ubai
	 * @param promoCode
	 *            推广码
	 * @throws Exception
	 */
	private void setUserPromo(UserBaseAccountInfo ubai, String promoCode)
	{
		UserPromo userPromo = new UserPromo();
		// ID
		userPromo.setId(ubai.getId());
		// 托管开通
		userPromo.setIsopenfsinfo((short) 0);
		// 推荐码
		userPromo.setPromocode(ShareCodeUtil.toSerialCode(ubai.getId().longValue()));
		// 推广码未修改
		userPromo.setIsmodify((short) 0);
		// 注册时间
		userPromo.setRegdate(ubai.getRegdate());
		// 推广人数
		userPromo.setPromonum(0l);
		// 有效推广人数
		userPromo.setValidnum(0l);
		// 推广费总收入
		userPromo.setPromofee(0d);
		// 推广层数 （本级）
		userPromo.setPromolevels(1l);
		// 推广代理等级
		userPromo.setProxygrade(new BigDecimal(23));
		// 验证推广码
		if (StringUtil.isNotEmpty(promoCode))
		{
			// 通过推广码查询
			UserPromo up = userPromoService.selectByPromoCode(promoCode);
			if (up != null)
			{
				/*
				 * 被推广人
				 */
				long parentLevels = up.getPromolevels();
				long validNum = up.getValidnum();
				// 新用户层级
				userPromo.setPromolevels(parentLevels + 1);
				// 新用户上级用户名
				userPromo.setSuploginname(up.getLoginname());
				// 新用户上级姓名
				userPromo.setSupname(up.getName());
				// 新用户上级推广码
				userPromo.setSuppromocode(promoCode);
				userPromo.setSuplevels2(up.getLoginname());
				userPromo.setIsvalid2((short) 0);
				/*
				 * 推广限制
				 */
				PromoTotalRestrict ptr = promoTotalRestrictService
						.selectByPrimaryKey(new BigDecimal(1));
				// 按层级升序排列
				List<PromoLevelRestrict> plrList = promoLevelRestrictService.selective(null);
				// 推广层数限制
				int levelLmit = ptr.getLevellimit().intValue();
				// 推广限制类型
				Short restrictType = ptr.getRestricttype();
				// 1 推广总人数限制
				if (restrictType.equals((short) 1))
				{
					// 未超过限制人数，推广有效
					if (ptr.getTotalnumlimit() > validNum)
					{
						userPromo.setIsvalid2((short) 1);
						// 有效人数
						up.setValidnum(up.getValidnum() + 1);
					}
				}
				// 2推广层级人数限制
				if (restrictType.equals((short) 2))
				{
					if (levelLmit > 1)
					{
						// 获取推广层级限制对象
						PromoLevelRestrict promoLR = plrList.get(0);
						// 未超过限制人数，推广有效
						if (promoLR.getLevellimitnum() > validNum)
						{
							userPromo.setIsvalid2((short) 1);
							// 有效人数
							up.setValidnum(up.getValidnum() + 1);
						}
					}
				}
				/*
				 * 推广人
				 */
				up.setPromonum(up.getPromonum() + 1);
				userPromoService.updateByPrimaryKey(up);
				try
				{
					// 上级推广码
					String code = up.getSuppromocode();
					boolean flag = true;
					for (int i = 3; i < 101; i++)
					{
						UserPromo userP = userPromoService.selectByPromoCode(code);
						/*
						 * 利用反射机制设置上级用户名和是否有效
						 */
						Class<?> clazz = userPromo.getClass();
						// 上级用户suplevels
						Method m1 = clazz.getDeclaredMethod("setSuplevels" + i, String.class);
						// 是否有效 isvalid
						Method m2 = clazz.getDeclaredMethod("setIsvalid" + i, Short.class);
						if (userP != null)
						{
							m1.invoke(userPromo, userP.getLoginname());
							m2.invoke(userPromo, (short) 0);
							if (restrictType.equals((short) 1) && flag)
							{
								// 未超过限制人数，推广有效
								if (ptr.getTotalnumlimit() > userP.getValidnum())
								{
									// 上级推广码
									String supPromoCode = up.getSuppromocode();
									if (StringUtil.isNotEmpty(supPromoCode))
									{
										int isvalid = up.getIsvalid2().intValue();
										// 上级推广有效
										if (isvalid == 1)
										{
											m2.invoke(userPromo, (short) 1);
											userP.setValidnum(userP.getValidnum() + 1);
										} else
										{
											flag = false;
										}
									} else
									{
										userP.setValidnum(userP.getValidnum() + 1);
										m2.invoke(userPromo, (short) 1);
									}
								}
							}
							if (restrictType.equals((short) 2) && flag)
							{
								if (levelLmit >= i)
								{
									UserPromo userP2 = new UserPromo();
									m1.invoke(userP2, userP.getLoginname());
									m2.invoke(userP2, (short) 1);
									PromoLevelRestrict plevelr = plrList.get(i - 2);
									// 当前用户的下i层有效人数
									List<UserPromo> up2List = userPromoService.selective(userP2);
									if (up2List.size() < plevelr.getLevellimitnum().intValue())
									{
										// 上级推广码
										String supPromoCode = up.getSuppromocode();
										if (StringUtil.isNotEmpty(supPromoCode))
										{
											int isvalid = up.getIsvalid2().intValue();
											// 上级推广有效
											if (isvalid == 1)
											{
												m2.invoke(userPromo, (short) 1);
												userP.setValidnum(userP.getValidnum() + 1);
											} else
											{
												flag = false;
											}
										} else
										{
											m2.invoke(userPromo, (short) 1);
											userP.setValidnum(userP.getValidnum() + 1);
										}
									}
								}
							}
							// 当前用户推广的上级推荐码
							code = userP.getSuppromocode();
							up.setSuppromocode(userP.getSuppromocode());
							up.setIsvalid2(userP.getIsvalid2());
							// 上级用户推广人数+1
							userP.setPromonum(userP.getPromonum() + 1);
							userPromoService.updateByPrimaryKey(userP);
						}
						// 上级推荐码为null,跳出循环
						if (StringUtil.isEmpty(code))
							break;
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		userPromoService.insertSelective(userPromo);
	}
	/**
	 * 推广第三方设置
	 * 
	 * @param id
	 */
	private void setUserPromoThirdParty(BigDecimal id)
	{
		// TODO Auto-generated method stub
		UserPromoThirdParty uptp = new UserPromoThirdParty();
		uptp.setUpid(id);
		List<AgentGradePromoAuth> agpaList = agentGradePromoAuthService.selective(null);
		for (AgentGradePromoAuth agpa : agpaList)
		{
			// 推广链接地址
			uptp.setThirdpartycode(agpa.getThirdpartycode());
			// 推广第三方名称
			uptp.setThirdpartyname(agpa.getThirdpartyname());
			// 开关
			uptp.setIsopen((short) 0);
			if (agpa.getIsopen().equals((short) 1))
			{
				AGTPLink link = AGTPLinkService.selectByProxyGradeAndAGAPID(agpa.getId(),
						new BigDecimal(23));
				uptp.setIsopen(link.getIsopen());
			}
			userPromoThirdPartyService.insertSelective(uptp);
		}
	}
	
	/**
	 * 微博第三方注册
	 */
	@RequestMapping(value = "/weibo/lgIndexForWB", method = { RequestMethod.POST,
	RequestMethod.GET })
	public String lgIndexForWB(HttpServletRequest request) {
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);//获取userBaseAccountInfo 对象
		if(userBaseAccountInfo !=null){//对sessionId进行保存
			HttpSession session=request.getSession();
			String sessionId=session.getId();
			MyMapSessionId m=MyMapSessionId.getInstance();
			m.AddSession(userBaseAccountInfo.getLoginname(), sessionId);//将sessionId以登入名为key，sessionId为value保存
		}
			return "user/manager/managerCenter";
	}
}
