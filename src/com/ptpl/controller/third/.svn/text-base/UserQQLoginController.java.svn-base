package com.ptpl.controller.third;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
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
import com.ptpl.web.util.AES;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.web.util.ShareCodeUtil;
import com.ptpl.web.util.StringUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.Avatar;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;
import com.qq.connect.utils.RandomStatusGenerator;

import net.sf.json.JSONArray;
import weibo4j.Account;
import weibo4j.Users;
import weibo4j.http.HttpClient;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

@Controller
@RequestMapping("/qq")
public class UserQQLoginController extends BaseController {
	
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
	/**
	 * 用户基本账户信息ServiceI
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	
	@Autowired
    private UserBaseAccountInfoMapper userBaseAccountInfoMapper;

	/**
	 * 登录按钮入口
	 * 
	 * @throws QQConnectException
	 */
	@RequestMapping("/Lgtoqq")
	public void Lgtoqq(HttpServletRequest request, HttpServletResponse response)
			throws IOException, QQConnectException {
		response.setContentType("text/html;charset=utf-8");
		/* session.removeAttribute("enterqqkey"); */
		response.sendRedirect(new Oauth().getAuthorizeURL(request));
		session.setAttribute("enterqqkey", "loginQQkey");
		/*
		 * if(session.getAttribute("enterqqkey")!=null){
		 * enterqqkey=session.getAttribute("enterqqkey").toString();
		 * if(null!=enterqqkey&&enterqqkey=="loginQQkey"){
		 * response.sendRedirect(new Oauth().getAuthorizeURL(request)); }else{
		 * enterqqkey="loginQQkey"; response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); } }else{ response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); session.setAttribute("enterqqkey",
		 * "loginQQkey"); }
		 */
	}

	/**
	 * 注册按钮入口
	 * 
	 * @throws QQConnectException
	 */
	@RequestMapping("/Rgtoqq")
	public void Rgtoqq(HttpServletRequest request, HttpServletResponse response)
			throws IOException, QQConnectException {
		response.setContentType("text/html;charset=utf-8");
		/* session.removeAttribute("enterqqkey"); */
		response.sendRedirect(new Oauth().getAuthorizeURL(request));
		session.setAttribute("enterqqkey", "rsQQkey");
		/*
		 * String enterqqkey=null; if(session.getAttribute("enterqqkey")!=null){
		 * enterqqkey=session.getAttribute("enterqqkey").toString();
		 * if(null!=enterqqkey&&enterqqkey=="rsQQkey"){
		 * response.sendRedirect(new Oauth().getAuthorizeURL(request)); }else{
		 * enterqqkey="rsQQkey"; response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); } }else{ response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); session.setAttribute("enterqqkey",
		 * "rsQQkey"); }
		 */
	}

	/**
	 * 用户后台绑定入口
	 * 
	 * @throws 苏景焱
	 */
	@RequestMapping("/bindQQ")
	public void bindQQ(HttpServletRequest request, HttpServletResponse response)
			throws IOException, QQConnectException {
		//System.out.println("后台绑定。。。。。。。。。。。。。。。");
		response.setContentType("text/html;charset=utf-8");
		 /*session.removeAttribute("enterqqkey"); */
		response.sendRedirect(new Oauth().getAuthorizeURL(request));
		session.setAttribute("enterqqkey", "bdQQkey");
		
		/* * if(session.getAttribute("enterqqkey")!=null){
		 * enterqqkey=session.getAttribute("enterqqkey").toString();
		 * if(null!=enterqqkey&&enterqqkey=="bdQQkey"){
		 * response.sendRedirect(new Oauth().getAuthorizeURL(request)); }else{
		 * enterqqkey="bdQQkey"; response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); } }else{ response.sendRedirect(new
		 * Oauth().getAuthorizeURL(request)); session.setAttribute("enterqqkey",
		 * "bdQQkey"); }*/
		 
	}
	
	/*@RequestMapping("/bindQQ")   // 5/18
	public void bindWB(HttpServletRequest request, HttpServletResponse response) throws IOException, WeiboException, QQConnectException {
		response.setContentType("text/html;charset=utf-8");
		Oauth oauth = new Oauth();
		//String url = oauth.authorize("code", "");
		//String url = oauth.getAuthorizeURL("code", "");
		String enterwbkey=null;
			//创建session
			HttpSession session=request.getSession(false);
				if(session.getAttribute("enterqqkey")!=null){
					enterwbkey=session.getAttribute("enterqqkey").toString();
					if(null!=enterwbkey&&enterwbkey=="bdQQkey"){
						response.sendRedirect(new Oauth().getAuthorizeURL(request));
					}else{
						response.sendRedirect(new Oauth().getAuthorizeURL(request));
						enterwbkey="bdQQkey";
					}
				}else{
					response.sendRedirect(new Oauth().getAuthorizeURL(request));
					session.setAttribute("enterqqkey", "bdQQkey");
				}
	}*/
	

	/**
	 * 用户授权后回调进来
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @throws QQConnectException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loginCallBack")
	public String loginCallBack(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state) throws QQConnectException, IOException {
		if (code != null && state != null) {
			String redirectUrl ="http://my.ganjiangps.com/qq/loginCallBack.action";
			//String redirectUrl = "http://192.168.0.131:8080/ptpjx/qq/loginCallBack.action";
			//String redirectUrl = "http://116.30.194.139:8831/ptpjx/qq/loginCallBack.action";
			String clientSt = "fc83b8075c856984d0bc65159080b4cf";
			String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + 101385467
							+ "&client_secret=" + clientSt + "&code=" + code + "&redirect_uri=" + redirectUrl;
			HttpClient client = new HttpClient();
			String mapRes = client.getTokenByUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			if ("".equals(mapRes) || null == mapRes) {
				return "redirect:/user/tologin.action";
			}
			String[] params = mapRes.split("&");
			for (int i = 0; i < params.length; i++) {
				String[] p = params[i].split("=");
				if (p.length == 2) {
					map.put(p[0], p[1]);
				}
			}
			if (map.get("access_token") != null && (!map.get("access_token").equals(""))) {
				String accessToken = map.get("access_token");
				OpenID openIDObj = new OpenID(accessToken);
				String openId = openIDObj.getUserOpenID();// 用户ID
				if (StringUtil.isNotEmpty(openId)) {
					String userInfoURL = "https://graph.qq.com/user/get_user_info?access_token=" + accessToken
							+ "&oauth_consumer_key=" + 101385467 + "&openid=" + openId;
					String strBRes = client.getTokenByUrl(userInfoURL);
					Map<String, String> map2 = new HashMap<String, String>();
					if ("".equals(strBRes) || null == strBRes) {
						return "redirect:/user/tologin.action";
					}
					Object succesResponse = JSON.parse(strBRes); // 先转换成Object
					map2 = (Map<String, String>) succesResponse; // Object强转换为Map
					String nkName = map2.get("nickname");
					String uImg = map2.get("figureurl");
					String gender = map2.get("gender");
					if (session.getAttribute("enterqqkey") != null
							&& session.getAttribute("enterqqkey").equals("rsQQkey")) {//---------
						ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
						thirdPbLogin.setThirdpuid(openId);
						thirdPbLogin.setThirdAPPName("QQ");
						thirdPbLogin.setThirdpname(nkName);
						ThirdPbLogin thirdPbLogin1 = thirdPbLoginServiceI.getBaseIdByOtherParams(thirdPbLogin);
						if (thirdPbLogin1 != null && thirdPbLogin1.getBaseid() != null) {
							UserBaseAccountInfo ubaseA = userBaseAccountInfoService.getUserBaseAccountInfoById(thirdPbLogin1.getBaseid());//(1)
							String name1 = AES.getDecrypt(ubaseA.getLoginname());//登录yongh
							HttpSession session = request.getSession();
							String sessionId = session.getId();
							MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
		 					// 把用户基本信息放进session
					 		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubaseA));
							request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId())); // 5/19
							request.getSession().setAttribute(Session_Constant.USER, ubaseA);
							return "user/manager/managerCenter";
						} else {
							//按照系统规则注册新用户并绑定此QQ号
							return registerBdQQ(openId, nkName, uImg);
						}
					} else if (session.getAttribute("enterqqkey") != null//**********
							&& session.getAttribute("enterqqkey").equals("loginQQkey")) {
						ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
						thirdPbLogin.setThirdpuid(openId);
						thirdPbLogin.setThirdAPPName("QQ");
						ThirdPbLogin thirdPbLogin1 = thirdPbLoginServiceI.selectByThirdUserIdAndThirdName(thirdPbLogin);
						if (thirdPbLogin1 != null && thirdPbLogin1.getBaseid() != null) {
							UserBaseAccountInfo ubaseA = userBaseAccountInfoService
									.getUserBaseAccountInfoById(thirdPbLogin1.getBaseid());
							// 对sessionId进行保存处理======
							String name1 = AES.getDecrypt(ubaseA.getLoginname());//登录yongh
							HttpSession session = request.getSession();
							String sessionId = session.getId();
							MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
		 					// 把用户基本信息放进session
					 		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubaseA));
							//System.out.println("-------------->"+userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId()));
							request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId())); // 5/19
							request.getSession().setAttribute(Session_Constant.USER, ubaseA);
							return "user/manager/managerCenter";
						} else {
							thirdPbLogin.setImgurl(uImg);
							thirdPbLogin.setThirdpname(nkName);
							request.getSession().setAttribute("thirdPbLogin", JSON.toJSONString(thirdPbLogin));
							return "third/fasle";
						}
					} else if (session.getAttribute("enterqqkey") != null
							&& session.getAttribute("enterqqkey").equals("bdQQkey")) {
						UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession()
								.getAttribute(Session_Constant.USER);
						ThirdPbLogin thirdL = new ThirdPbLogin();
						if (user != null && (!user.toString().isEmpty())) {
							UserBaseAccountInfo userBaseA = userBaseAccountInfoServiceI
									.getUserBaseAccountInfoById(user.getId());
							//userBaseA.setRealname(nkName);
							userBaseAccountInfoServiceI.update(userBaseA);
							thirdL.setThirdpname(nkName);
							thirdL.setBaseid(userBaseA.getId());
							thirdL.setThirdpuid(openId);
							thirdL.setThirdAPPName("QQ");
							thirdL.setImgurl(uImg);
							Date dt = new Date();
							thirdL.setAuthbinddate(dt);
							thirdPbLoginServiceI.insertSelective(thirdL);
							return "redirect:/third/bingDing.action";
						}
					}
				}

			}
			return "redirect:/user/tologin.action";  
			//return "third/fasle";  //  5/17
		} else {
			return "redirect:/user/tologin.action";
		}
	}

	/**
	 *  按照系统规则注册新用户并绑定此QQ号
	 * @param openId
	 * @param nkName
	 * @param uImg
	 * @return
	 */
	private String registerBdQQ(String openId, String nkName, String uImg) {
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
			Date dt = new Date();
			ubai1.setRegdate(dt);
			int count = userBaseAccountInfoService.insertSelective(ubai1);
			if (count > 0) {
				UserBaseAccountInfo ubai2 = userBaseAccountInfoService.getuserloginname(name);
				ThirdPbLogin thirdL = new ThirdPbLogin();
				thirdL.setThirdpname(nkName);
				thirdL.setAuthbinddate(dt);
				thirdL.setImgurl(uImg);
				thirdL.setBaseid(ubai2.getId());
				thirdL.setThirdpuid(openId);
				thirdL.setThirdAPPName("QQ");
				int count2 = thirdPbLoginServiceI.insertSelective(thirdL);
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
				//System.out.println("++++++++++++>>>"+count3);
				if (count2 > 0 && count3 > 0) {
					UserBaseAccountInfo ubaseA = userBaseAccountInfoService.getUserBaseAccountInfoById(ubai2.getId());  //(2)
					String name1 = AES.getDecrypt(ubaseA.getLoginname());//登录yongh
					// 对sessionId进行保存处理=====
					HttpSession session = request.getSession();
					String sessionId = session.getId();
					MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
					// 把用户基本信息放进session                    
			 		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubaseA));
					request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubaseA.getId())); // 5/19
					request.getSession().setAttribute(Session_Constant.USER, ubaseA);
					return "user/manager/managerCenter";
				}
			}
		}
		return "redirect:/user/tologin.action";
	}

	/**
	 * 
	 * @Title: insertThirdPBLogin @Description: TODO(第三方绑定 方法) @param @param
	 *         baseID 用户 ID @param @param thirdpuid 第三方ID @param @return
	 *         参数说明 @return boolean 返回类型 返回true 绑定成功 @author cjm @throws
	 */
	public boolean insertThirdPBLogin(BigDecimal baseID, String thirdpuid) {
		boolean flag = true;
		Assert.notNull(thirdpuid, "'thirdpuid' 第三方ID 不能为null");
		Assert.notNull(baseID, "'baseID' 用户ID 不能为null");
		ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
		thirdPbLogin.setBaseid(baseID);// 用户ID
		thirdPbLogin.setThirdpuid(thirdpuid); // 第三方绑定用户ID
		thirdPbLogin.setStatus((short) 1); // 绑定状态 1绑定成功 0 未绑定
		thirdPbLogin.setThirdpname("QQ"); // 第三方名称
		thirdPbLogin.setAuthbinddate(new Date()); // 绑定时间
		int count = thirdPbLoginServiceI.insertSelective(thirdPbLogin);
		if (count > 0) {
			return flag;
		}
		return false;
	}

	/**
	 * 点击确定注册后，返回JSON数据
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "confirm")
	public void confirm(String name) throws Exception {
		String data = "tologin";
		String thirdID = (String) request.getSession().getAttribute(Session_Constant.USER_QQ_USER_CODE);
		if (StringUtil.isNotEmpty(thirdID)) {
			UserBaseAccountInfo ubai = new UserBaseAccountInfo();
			ubai.setLoginname(name);
			UserBaseAccountInfo record = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
			if (record != null) {
				data = "fail";
			} else {
				UserAccountSafeInfo userAccountSafeInfo = new UserAccountSafeInfo();
				/** 用户基本信息 */
				setUserBaseAccountInfo(ubai);
				/** 用户账户安全信息 */
				setUserAccountSafeInfo(userAccountSafeInfo, ubai);
				/** 用户账户 */
				setUserAccount(ubai);
				/** 用户推广设置 */
				setUserPromo(ubai, null);
				/** 用户第三方推广设置 */
				setUserPromoThirdParty(ubai.getId());
				/*
				 * 绑定
				 */
				boolean flag = insertThirdPBLogin(ubai.getId(), thirdID);
				if (flag) {
					// 用户账户安全信息表实体类对象
					session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfo);
					// 用户基本信息表实体类对象
					session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubai));
					data = "success";
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 用户账户
	 * 
	 * @param ubai
	 */
	private void setUserAccount(UserBaseAccountInfo ubai) {
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
	private void setUserAccountSafeInfo(UserAccountSafeInfo userAccountSafeInfo, UserBaseAccountInfo ubai) {
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
	private void setUserBaseAccountInfo(UserBaseAccountInfo ubai) {
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
	private void setUserPromo(UserBaseAccountInfo ubai, String promoCode) {
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
		if (StringUtil.isNotEmpty(promoCode)) {
			// 通过推广码查询
			UserPromo up = userPromoService.selectByPromoCode(promoCode);
			if (up != null) {
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
				PromoTotalRestrict ptr = promoTotalRestrictService.selectByPrimaryKey(new BigDecimal(1));
				// 按层级升序排列
				List<PromoLevelRestrict> plrList = promoLevelRestrictService.selective(null);
				// 推广层数限制
				int levelLmit = ptr.getLevellimit().intValue();
				// 推广限制类型
				Short restrictType = ptr.getRestricttype();
				// 1 推广总人数限制
				if (restrictType.equals((short) 1)) {
					// 未超过限制人数，推广有效
					if (ptr.getTotalnumlimit() > validNum) {
						userPromo.setIsvalid2((short) 1);
						// 有效人数
						up.setValidnum(up.getValidnum() + 1);
					}
				}
				// 2推广层级人数限制
				if (restrictType.equals((short) 2)) {
					if (levelLmit > 1) {
						// 获取推广层级限制对象
						PromoLevelRestrict promoLR = plrList.get(0);
						// 未超过限制人数，推广有效
						if (promoLR.getLevellimitnum() > validNum) {
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
				try {
					// 上级推广码
					String code = up.getSuppromocode();
					boolean flag = true;
					for (int i = 3; i < 101; i++) {
						UserPromo userP = userPromoService.selectByPromoCode(code);
						/*
						 * 利用反射机制设置上级用户名和是否有效
						 */
						Class<?> clazz = userPromo.getClass();
						// 上级用户suplevels
						Method m1 = clazz.getDeclaredMethod("setSuplevels" + i, String.class);
						// 是否有效 isvalid
						Method m2 = clazz.getDeclaredMethod("setIsvalid" + i, Short.class);
						if (userP != null) {
							m1.invoke(userPromo, userP.getLoginname());
							m2.invoke(userPromo, (short) 0);
							if (restrictType.equals((short) 1) && flag) {
								// 未超过限制人数，推广有效
								if (ptr.getTotalnumlimit() > userP.getValidnum()) {
									// 上级推广码
									String supPromoCode = up.getSuppromocode();
									if (StringUtil.isNotEmpty(supPromoCode)) {
										int isvalid = up.getIsvalid2().intValue();
										// 上级推广有效
										if (isvalid == 1) {
											m2.invoke(userPromo, (short) 1);
											userP.setValidnum(userP.getValidnum() + 1);
										} else {
											flag = false;
										}
									} else {
										userP.setValidnum(userP.getValidnum() + 1);
										m2.invoke(userPromo, (short) 1);
									}
								}
							}
							if (restrictType.equals((short) 2) && flag) {
								if (levelLmit >= i) {
									UserPromo userP2 = new UserPromo();
									m1.invoke(userP2, userP.getLoginname());
									m2.invoke(userP2, (short) 1);
									PromoLevelRestrict plevelr = plrList.get(i - 2);
									// 当前用户的下i层有效人数
									List<UserPromo> up2List = userPromoService.selective(userP2);
									if (up2List.size() < plevelr.getLevellimitnum().intValue()) {
										// 上级推广码
										String supPromoCode = up.getSuppromocode();
										if (StringUtil.isNotEmpty(supPromoCode)) {
											int isvalid = up.getIsvalid2().intValue();
											// 上级推广有效
											if (isvalid == 1) {
												m2.invoke(userPromo, (short) 1);
												userP.setValidnum(userP.getValidnum() + 1);
											} else {
												flag = false;
											}
										} else {
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
				} catch (Exception e) {
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
	private void setUserPromoThirdParty(BigDecimal id) {
		// TODO Auto-generated method stub
		UserPromoThirdParty uptp = new UserPromoThirdParty();
		uptp.setUpid(id);
		List<AgentGradePromoAuth> agpaList = agentGradePromoAuthService.selective(null);
		for (AgentGradePromoAuth agpa : agpaList) {
			// 推广链接地址
			uptp.setThirdpartycode(agpa.getThirdpartycode());
			// 推广第三方名称
			uptp.setThirdpartyname(agpa.getThirdpartyname());
			// 开关
			uptp.setIsopen((short) 0);
			if (agpa.getIsopen().equals((short) 1)) {
				AGTPLink link = AGTPLinkService.selectByProxyGradeAndAGAPID(agpa.getId(), new BigDecimal(23));
				uptp.setIsopen(link.getIsopen());
			}
			userPromoThirdPartyService.insertSelective(uptp);
		}
	}

	/**
	 * qq登录失败后注册
	 */
	@RequestMapping(value = "/lgIndexForQQ", method = { RequestMethod.POST, RequestMethod.GET })
	public String lgIndexForQQ(@RequestParam(value = "thirdPbLogin") ThirdPbLogin thirdPbLogin) {
		String loginname = thirdPbLogin.getThirdpname();
		String thirdpuid = thirdPbLogin.getThirdpuid();
		UserBaseAccountInfo userBai = userBaseAccountInfoService.getuserloginname(loginname);
		ThirdPbLogin tl = new ThirdPbLogin();
		tl.setThirdpuid(thirdpuid);
		tl = thirdPbLoginServiceI.selectByThirdUserIdAndThirdNameBaseID(tl);
		if (userBai == null && tl == null) {
			UserNameRuleModule unrm = userNameRuleModuleService.selective(null).get(4);
			if (unrm.getIsuse() == (short) 1) {
				int randomLength = unrm.getRandomlength().intValue();
				String nameVal = "";
				String presetStr = unrm.getPresetstr();
				nameVal = presetStr + StringUtil.getN(randomLength);
				UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
				Date dt = new Date();
				ubai1.setLoginname(nameVal);
				ubai1.setRealname(loginname);
				ubai1.setRegdate(dt);
				int count = userBaseAccountInfoService.insertSelective(ubai1);
				UserBaseAccountInfo ubai2 = userBaseAccountInfoService.getuserloginname(nameVal);
				ThirdPbLogin thirdL = new ThirdPbLogin();
				thirdL.setThirdpname(loginname);
				thirdL.setAuthbinddate(dt);
				thirdL.setBaseid(ubai2.getId());
				thirdL.setImgurl(thirdPbLogin.getImgurl());
				thirdL.setThirdpuid(thirdpuid);
				thirdL.setThirdAPPName("QQ");
				int count2 = thirdPbLoginServiceI.insertSelective(thirdL);
				if (count2 > 0) {

					// 对sessionId进行保存处里，
					HttpSession session = request.getSession();
					String sessionId = session.getId();
					MyMapSessionId.getInstance().AddSession(ubai2.getLoginname(), sessionId);

					request.getSession().setAttribute("userBaseAccountInfo", JSON.toJSONString(ubai2));
					return "user/manager/managerCenter";
				}
				// sessionId进行保存处理
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				MyMapSessionId.getInstance().AddSession(ubai1.getLoginname(), sessionId);

				request.getSession().setAttribute("userBaseAccountInfo", JSON.toJSONString(ubai1));
				return "user/manager/managerCenter";
			}
		} else {
			return "false";
		}
		// sessionId进行保存处理
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		MyMapSessionId.getInstance().AddSession(userBai.getLoginname(), sessionId);

		request.getSession().setAttribute("userBaseAccountInfo", JSON.toJSONString(userBai));
		return "user/manager/managerCenter";
	}

	/**
	 * qq登录(登录入口)
	 */
	@RequestMapping(value = "/lgIndex", method = { RequestMethod.POST, RequestMethod.GET })
	public String lgIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "redirect:/qq/Lgtoqq.action";
		//return "third/false";
	}
	

	/**
	 * * 注册页面入口：qq第三方注册(一键注册)
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.POST, RequestMethod.GET })
	public String register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "redirect:/qq/Rgtoqq.action";
	}

	/**
	 * * 注册页面入口：qq第三方注册(一键注册)
	 */
	@RequestMapping(value = "/regALL", method = { RequestMethod.POST, RequestMethod.GET })
	public String regALL() {

		return "third/register";
	}

	/**
	 * 用户绑定（登录失败后执行：绑定已通过普通注册但没绑定此qq的本站用户）
	 */
	@RequestMapping(value = "/bangDingQQLogin", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String bangDingQQLogin(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
		String retRs = null;
		// 获取浏览器传递的用户名
		String loginUsername = request.getParameter("loginname");
		// 获取浏览器传递的密码
		String password = request.getParameter("password");
		if(StringUtil.isNotEmpty(loginUsername)){
		UserBaseAccountInfo userBaseAccountInfo2 = new UserBaseAccountInfo();
		//加密 
		loginUsername = setEncrypt(loginUsername);
		userBaseAccountInfo2.setLoginname(loginUsername);// 用户名
		userBaseAccountInfo2.setEmail(loginUsername);// 邮箱
		userBaseAccountInfo2.setMobilephone(loginUsername);// 手机号码
		retRs = "fail";
		// 根据用户名查询密码
		UserAccountSafeInfo userAccountSafeIn = userAccountSafeInfoServiceI
				.getLoginPassWordByLoginName(userBaseAccountInfo2);
		if (userAccountSafeIn == null || userAccountSafeIn.getLoginpassword() == null) {// 用户名或密码错误
			return retRs;
		} else {
			// 判断密码是否正确
			boolean pswFlag = BCrypt.checkpw(password, userAccountSafeIn.getLoginpassword());
			if (!pswFlag) {// 用户名或密码错误
				retRs = "fail";
				return retRs;
			} else {
				UserBaseAccountInfo ubai = userBaseAccountInfoServiceI
						.getUserBaseAccountInfoById(userAccountSafeIn.getBaseid());
				ThirdPbLogin thirdL = new ThirdPbLogin();
				// 綁定qq
				Object userObj = session.getAttribute("thirdPbLogin");
				net.sf.json.JSONObject jsonU = net.sf.json.JSONObject.fromObject(userObj);
				//JSONArray jsonU = net.sf.json.JSONArray.fromObject(userObj);   // 5/17
				if (jsonU != null && (!jsonU.equals(""))) {
//					String imgurl = jsonU.getString("imgurl");
//					String thirdpname = jsonU.getString("thirdpname");
//					String thirdpuid = jsonU.getString("thirdpuid");
					Object imgurl = jsonU.get("imgurl");
					Object thirdpname = jsonU.get("thirdpname");
					Object thirdpuid = jsonU.get("thirdpuid");
 					thirdL.setThirdpname((String) thirdpname);
					thirdL.setBaseid(ubai.getId());
					thirdL.setThirdpuid((String) thirdpuid);
					thirdL.setThirdAPPName("QQ");
					thirdL.setImgurl((String) imgurl);
					Date dt = new Date();
					thirdL.setAuthbinddate(dt);
					thirdPbLoginServiceI.insertSelective(thirdL);
					
					String name1 = AES.getDecrypt(ubai.getLoginname());//登录yongh
					HttpSession session = request.getSession();
					String sessionId = session.getId();
					MyMapSessionId.getInstance().AddSession(name1, sessionId);//异地登录
					//request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfoServiceI.selectBaseId(ubai.getId())); // 5/24
 					// 把用户基本信息放进session
			 		session.setAttribute(Session_Constant.USER, getDecryptionUserBaseAccountInfoDetail(ubai));// UserBaseAccountInfo
			     	// 用户托管账户信息实体对象
					UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI
							.findUserFsAccountInfoByBaseId(userAccountSafeIn.getBaseid());
					// 把用户托管账户信息实体对象放进session
					if (userFsAccountInfo != null) {
						session.setAttribute(Session_Constant.USERFSACCOUNTINFO, getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
					}
					// 把用户账号安全信息放进session
					session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeIn);
					retRs = "success";
					return retRs;
				}
				return retRs;
			}
		}
	}
		return null;
}

	/**
	 * * 绑定本站用户(qq绑定)
	 */
	@RequestMapping(value = "/loginQQenter", method = { RequestMethod.POST, RequestMethod.GET })
	public String loginQQenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("loginname");
		String pwd = request.getParameter("password");
		
		loginName = setEncrypt(loginName);
		System.out.println(loginName);
	    pwd = setEncrypt(pwd);
	    System.out.println(pwd);
	    Map<String,Object> map =  new HashMap<String, Object>();
	    map.put("loginName",loginName);
	    map.put("Password", pwd);
	    List<UserBaseAccountInfo> list = userBaseAccountInfoMapper.getByUsername1(map);
	    System.out.println("-------------------------------------"+list);
	    
		
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);//获取userBaseAccountInfo 对象
		System.out.println("111--------------"+userBaseAccountInfo);
		if(userBaseAccountInfo !=null){//对sessionId进行保存
			HttpSession session=request.getSession();
			String sessionId=session.getId();
			MyMapSessionId m=MyMapSessionId.getInstance();
			m.AddSession(userBaseAccountInfo.getLoginname(), sessionId);//将sessionId以登入名为key，sessionId为value保存
		}
		
		// 获取浏览器传递的用户名
		return "user/manager/managerCenter";
	}
}


/**
 * try { AccessToken accessTokenObj = (new
 * Oauth()).getAccessTokenByRequest(request); if
 * (StringUtil.isNotEmpty(accessTokenObj.getAccessToken())) { String accessToken
 * = accessTokenObj.getAccessToken(); OpenID openIDObj = new
 * OpenID(accessToken); String openId = openIDObj.getUserOpenID();// 用户ID if
 * (StringUtil.isNotEmpty(openId)) { UserInfo user = new UserInfo(accessToken,
 * openId); UserInfoBean uifBean=user.getUserInfo(); String
 * nkName=uifBean.getNickname(); Avatar at=uifBean.getAvatar(); String
 * uImg=at.getAvatarURL50();
 * if(session.getAttribute("rsKey")!=null&&session.getAttribute("rsKey").equals(
 * "rsQQkey")){ ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
 * thirdPbLogin.setThirdpuid(openId); thirdPbLogin.setThirdAPPName("QQ");
 * thirdPbLogin.setThirdpname(nkName); ThirdPbLogin thirdPbLogin1 =
 * thirdPbLoginServiceI.getBaseIdByOtherParams(thirdPbLogin); if (thirdPbLogin1
 * != null&& thirdPbLogin1.getBaseid() != null){
 * request.getSession().setAttribute("thirdPbLogin1", thirdPbLogin1); return
 * "user/manager/managerCenter"; }else{ UserNameRuleModule unrm =
 * userNameRuleModuleService.selective(null).get(4); if (unrm.getIsuse() ==
 * (short) 1) { int randomLength = unrm.getRandomlength().intValue(); String
 * name = ""; String presetStr = unrm.getPresetstr(); name = presetStr +
 * StringUtil.getN(randomLength); UserBaseAccountInfo ubai1 = new
 * UserBaseAccountInfo(); ubai1.setLoginname(name); ubai1.setRealname(nkName);
 * Date dt=new Date(); ubai1.setRegdate(dt); int
 * count=userBaseAccountInfoService.insertSelective(ubai1); if(count>0){
 * UserBaseAccountInfo ubai2=userBaseAccountInfoService.getuserloginname(name);
 * ThirdPbLogin thirdL=new ThirdPbLogin(); thirdL.setThirdpname(nkName);
 * thirdL.setAuthbinddate(dt); thirdL.setImgurl(uImg);
 * thirdL.setBaseid(ubai2.getId()); thirdL.setThirdpuid(openId);
 * thirdL.setThirdAPPName("QQ"); int count2
 * =thirdPbLoginServiceI.insertSelective(thirdL); if(count2>0){
 * request.getSession().setAttribute("thirdL", thirdL); return
 * "user/manager/managerCenter"; } } } } }else
 * if(session.getAttribute("lgKey")!=null&&session.getAttribute("lgKey").equals(
 * "loginQQkey")){ ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
 * thirdPbLogin.setThirdpuid(openId); thirdPbLogin.setThirdAPPName("QQ");
 * ThirdPbLogin thirdPbLogin1 =
 * thirdPbLoginServiceI.selectByThirdUserIdAndThirdName(thirdPbLogin); if
 * (thirdPbLogin1 != null&& thirdPbLogin1.getBaseid() != null){
 * request.getSession().setAttribute("thirdPbLogin1", thirdPbLogin1); return
 * "user/manager/managerCenter"; }else{
 * request.getSession().setAttribute("thirdPbLogin", thirdPbLogin1); return
 * "fasle"; } } } } }catch(Exception e) {
 * 
 * }
 */

/* if (thirdPbLogin1 != null ) { */// 用户已经绑定过了
/*
 * UserAccountSafeInfo accountSafeInfo = userAccountSafeInfoServiceI
 * .selectByBaseId(thirdPbLogin1.getBaseid());// 用户账号安全信息实体类
 */ /*
	 * if (accountSafeInfo != null && accountSafeInfo.getRisklevel() != null &&
	 * userBaseAccountInfo != null) { // 用户风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6
	 * 冻结） String risklevel = accountSafeInfo.getRisklevel().toString(); if
	 * (risklevel.equals("1") || risklevel.equals("2") || risklevel.equals("3"))
	 * { // 用户账号安全信息实体对象
	 * session.setAttribute(Session_Constant.USERACCOUNTSAFEINFO,
	 * accountSafeInfo); // 用户基本信息实体对象
	 * session.setAttribute(Session_Constant.USER, userBaseAccountInfo); //
	 * 跳转登录后的页面 return "redirect:/user/touser.action"; } else { //
	 * 已经绑定了的用户存在安全隐患 解除绑定 int count = 0; // 删除已经绑定过的用户信息 count =
	 * thirdPbLoginServiceI.deleteById(thirdPbLogin1.getId()); if (count > 0) {
	 * // 跳转绑定用户页面 return "redirect:/qq/toqq.action"; } } }
	 */
/*
 * try { response.setContentType("text/html;charset=utf-8"); PrintWriter out =
 * response.getWriter();
 * 
 * UserInfo qzoneUserInfo = new UserInfo(accessToken, openId); UserInfoBean
 * userInfoBean = qzoneUserInfo.getUserInfo(); if (userInfoBean.getRet() == 0) {
 * out.println("获取用户的QQ头像====");
 * out.println("昵称是=========="+userInfoBean.getNickname() + "<br/>");
 * out.println("性别是===="+userInfoBean.getGender() + "<br/>"); out.println(
 * "黄钻等级： " + userInfoBean.getLevel() + "<br/>"); out.println("会员 : " +
 * userInfoBean.isVip() + "<br/>"); out.println("黄钻会员： " +
 * userInfoBean.isYellowYearVip() + "<br/>"); out.println( "<image src=" +
 * userInfoBean.getAvatar().getAvatarURL30() + "/><br/>"); out.println(
 * "<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
 * out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() +
 * "/><br/>"); } com.qq.connect.api.weibo.UserInfo weiboUserInfo = new
 * com.qq.connect.api.weibo.UserInfo(accessToken, openId);
 * com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean =
 * weiboUserInfo.getUserInfo(); if (weiboUserInfoBean.getRet() == 0) {
 * //获取用户的微博头像----------------------start out.println("获取用户的微博头像====");
 * out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() +
 * "/><br/>"); out.println("<image src=" +
 * weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>"); out.println(
 * "<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
 * } out.println("用户名注册！！ 这里进行用户注册 绑定操作"); } catch (IOException e) {
 * e.printStackTrace(); }
 */
/*
 * UserNameRuleModule unrm = userNameRuleModuleService.selective(null).get(4);
 * if (unrm.getIsuse() == (short) 1) { String nameRule = unrm.getNamerule(); int
 * randomLength = unrm.getRandomlength().intValue(); String name = ""; if
 * (nameRule.equals("a")) { String presetStr = unrm.getPresetstr(); boolean flag
 * = true; // 如果系统匹配的用户名存在，则重新匹配直到用户名不存在 while (flag) { name = presetStr +
 * StringUtil.getN(randomLength); UserBaseAccountInfo ubai1 = new
 * UserBaseAccountInfo(); ubai1.setLoginname(name); UserBaseAccountInfo record =
 * userBaseAccountInfoService .getUserBaseAccountInfoByOneCondition(ubai1); if
 * (record == null) flag = false; } } if (nameRule.equals("b")) { boolean flag =
 * true; while (flag) { name = String.valueOf(StringUtil.getN(randomLength));
 * UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
 * ubai1.setLoginname(name); UserBaseAccountInfo record =
 * userBaseAccountInfoService .getUserBaseAccountInfoByOneCondition(ubai1); if
 * (record == null) flag = false; } } request.setAttribute("isCustom",
 * unrm.getIscustom()); request.setAttribute("userName", name);
 * request.getSession().setAttribute(Session_Constant.USER_QQ_USER_CODE,
 * openId); return "user/qq/qqMessage"; } } } else { return
 * "redirect:/user/tologin.action"; } } catch (QQConnectException e) {
 * e.printStackTrace(); } return null; }
 */