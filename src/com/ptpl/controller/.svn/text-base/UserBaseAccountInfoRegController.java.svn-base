package com.ptpl.controller;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.MessageTemplate;
import com.ptpl.model.PromoLevelRestrict;
import com.ptpl.model.PromoRegInfo;
import com.ptpl.model.PromoTotalRestrict;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.MessageTemplateServiceI;
import com.ptpl.service.PromoLevelRestrictServiceI;
import com.ptpl.service.PromoRegInfoServiceI;
import com.ptpl.service.PromoTotalRestrictServiceI;
import com.ptpl.service.SMSChannelServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.SysNoticeBizServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserNameRuleModuleServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.ShareCodeUtil;
import com.ptpl.web.util.StringUtil;

/**
 * 用户注册
 * 
 * @author xiaoy
 *
 * @date 2016年11月17日 下午12:22:48
 */
@Controller
@RequestMapping("/register/userBaseInfo")
public class UserBaseAccountInfoRegController extends BaseController {
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private PromoTotalRestrictServiceI promoTotalRestrictService;
	@Autowired
	private PromoLevelRestrictServiceI promoLevelRestrictService;
	@Autowired
	private UserNameRuleModuleServiceI userNameRuleModuleService;
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	@Autowired
	private AgentGradePromoAuthServiceI agentGradePromoAuthService;
	@Autowired
	private AGTPLinkServiceI AGTPLinkService;
	@Autowired
	private SysNoticeBizServiceI sysNoticeBizService;
	@Autowired
	private SMSChannelServiceI smsChannelService;
	@Autowired
	private MessageTemplateServiceI messageTemplateService;
	@Autowired
	private PromoRegInfoServiceI promoRegInfoService;
	@Autowired
	private SMSSendServiceI smsSendService;
	// 生成用户号
	@Autowired
	private OracleSequenceMaxValueIncrementer accountnumber;

	private Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
	// 用户名最小长度
	private int minLength = 5;
	// 用户名最大长度
	private int maxLength = 15;
	// 用户名规则 a允许和手机号相同 ，b不允许和手机号相同
	private String nameRule = "a";
	// 1 允许包含中文 2不允许包含中文
	private int isChinese = 1;

	@RequestMapping(value = "/register")
	public String register() {
		UserNameRuleModule unrm = new UserNameRuleModule();
		// 注册类型
		unrm.setRegistertype((short) 2);
		unrm = userNameRuleModuleService.selective(unrm).get(0);
		Short isUse = unrm.getIsuse();
		if (isUse.equals((short) 1)) {
			minLength = unrm.getUsernamelength();
			maxLength = unrm.getUsernamemaxlength();
			nameRule = unrm.getSetrule();
			isChinese = unrm.getIschinese();
		}
		request.setAttribute("minLength", minLength);
		request.setAttribute("maxLength", maxLength);
		request.setAttribute("nameRule", nameRule);
		request.setAttribute("isChinese", isChinese);
		return "user/manager/register";
	}

	@RequestMapping(value = "/getSysTime")
	public void getSysTime() throws Exception {
		sendJsonData(response, String.valueOf(new Date().getTime()));
	}

	/**
	 * 注册第三步
	 * 
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "registerThirdSteps", method = RequestMethod.POST)
	public ModelAndView registerThirdSteps(Integer num) {
		if (num != null) {
			Object obj = session.getAttribute("num");
			if (obj != null && (int) obj == num.intValue()) {
				// 保存
				int iden = setUser();
				if (iden > 0) {
					String uname = (String) session.getAttribute("uname");
					ModelAndView mav = new ModelAndView();
					mav.setViewName("/user/manager/register3");
					mav.addObject("uname", uname);
					session.setAttribute("uname", "");
					session.setAttribute("pwd", "");
					session.setAttribute("promoCode", "");
					session.setAttribute("mobile", "");
					session.setAttribute("num", "");
					return mav;
				}
			}
		}
		return null;
	}

	/**
	 * 保存用户名
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年3月17日 下午6:43:41
	 * @return
	 */
	private int setUser() {
		String uname = (String) session.getAttribute("uname");
		String pwd = (String) session.getAttribute("pwd");
		String promoCode = (String) session.getAttribute("promoCode");
		String mobile = (String) session.getAttribute("mobile");
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		// 用户号
		ubai.setAccountnumber(accountnumber.nextStringValue());
		// 注册IP
		ubai.setRegip(StringUtil.getIpAddr(request));
		// 注册Cookie
		ubai.setRegcookie(setCookie());
		// 手机号码
		ubai.setMobilephone(AES.getEncrypt(mobile));
		// 用户名
		ubai.setLoginname(AES.getEncrypt(uname));
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
		return userBaseAccountInfoService.insertForRegister(ubai, hashed, (short) 2, (short) 1, promoCode, request,
				response);
	}

	/**
	 * 验证手机短信验证码
	 * 
	 * @param ckCode
	 * @param num
	 * @param mobile
	 */
	@RequestMapping(value = "/checkMobileCode", method = RequestMethod.POST)
	public void checkMobileCode(String ckCode, Integer num, String mobile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (num != null) {
			Object obj = session.getAttribute("num");
			if (obj == null || (int) obj != num.intValue()) {
				map.put("errCode", "1");
				sendJsonData(response, JSON.toJSONString(map));
				return;
			} else {
				Object obj2 = session.getAttribute("mobile");
				// 手机号不匹配
				if (StringUtils.isEmpty(mobile) || !String.valueOf(obj2).equals(mobile)) {
					map.put("errCode", "2");
					sendJsonData(response, JSON.toJSONString(map));
					return;
				}
				// 验证码不能为空
				if (StringUtils.isEmpty(ckCode)) {
					map.put("ckCodeErrMsg", "0");
					sendJsonData(response, JSON.toJSONString(map));
					return;
				}
				Object obj3 = session.getAttribute("mobileCode");
				// 验证码已失效
				if (obj3 == null) {
					map.put("ckCodeErrMsg", "2");
					sendJsonData(response, JSON.toJSONString(map));
					return;
				}
				// 验证码输入有误
				if (!String.valueOf(obj3).equals(ckCode)) {
					map.put("ckCodeErrMsg", "1");
					sendJsonData(response, JSON.toJSONString(map));
					return;
				}
				// 验证成功
				map.put("errCode", "0");
				sendJsonData(response, JSON.toJSONString(map));
			}
		}
	}

	/**
	 * 注册第二步 UI
	 * 
	 * @param mobile
	 * @param uname
	 * @param pwd
	 * @param promoCode
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "/registerSecondSteps", method = RequestMethod.POST)
	public ModelAndView registerSecondSteps(Integer num) {
		if (num != null) {
			Object obj = session.getAttribute("num");
			if (obj != null) {
				if (num.intValue() == (int) obj) {
					ModelAndView mav = new ModelAndView();
					String mobile = (String) session.getAttribute("mobile");
					mav.addObject("uname", session.getAttribute("uname"));
					mav.addObject("mobile", mobile);
					// num
					int anum = (int) (Math.random() * 124754);
					mav.addObject("num", anum);
					session.setAttribute("num", anum);
					// 发送短信
					smsSend(mobile);
					mav.addObject("regSdMsgCodeRst", "succ");
					// 发送短信时间
					mav.addObject("messSendTime", new Date().getTime());
					mav.setViewName("/user/manager/register2");
					System.out.println("第二步 跳转成功。。");
					return mav;
				}
			}
		}
		return null;
	}

	/**
	 * 注册第一步验证 UI
	 * 
	 * @param mobile
	 * @param uname
	 * @param pwd
	 * @param repwd
	 * @param imgCode
	 * @param promoCode
	 * @throws Exception
	 */
	@RequestMapping(value = "registerFirstSteps", method = RequestMethod.POST)
	public void registerFirstSteps(String mobile, String uname, String pwd, String repwd, String imgCode,
			String promoCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype((short) 2);
		unrm = userNameRuleModuleService.selective(unrm).get(0);
		// boolean flagImgCode = false;
		/*
		 * 1.验证手机号 不为空 长度不等于11， 手机格式
		 */
		boolean flagMobile = checkMobile(mobile, map);
		/*
		 * 2.验证用户名
		 */
		boolean flagUname = checkUname(uname, mobile, map, unrm);
		/*
		 * 3.验证密码
		 */
		boolean flagPwd = checkPwd(pwd, map);
		/*
		 * 4.验证再次密码
		 */
		boolean flagRepwd = checkRepwd(pwd, repwd, map);
		if (flagMobile && flagUname && flagPwd && flagRepwd) {
			map.put("errCode", "0");
			int num = (int) (Math.random() * 124754);
			map.put("num", num);
			session.setAttribute("num", num);
			session.setAttribute("mobile", mobile);
			session.setAttribute("uname", uname);
			session.setAttribute("pwd", pwd);
			session.setAttribute("promoCode", promoCode);
			System.out.println("第一步 验证成功。。");
		}
		sendJsonData(response, JSON.toJSONString(map));
	}

	/**
	 * UI 4.验证重复密码 两次密码不一样 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 * 
	 * @param pwd
	 * @param repwd
	 * @param map
	 * @return
	 */
	private boolean checkRepwd(String pwd, String repwd, Map<String, Object> map) {
		if (StringUtil.isEmpty(repwd)) {
			map.put("repwdErrMsg", "密码不能为空");
			return false;
		}
		if (!repwd.equals(pwd)) {
			map.put("repwdErrMsg", "两次输入的密码不一致");
			return false;
		}
		if (repwd.length() < 8 || repwd.length() > 20) {
			map.put("repwdErrMsg", "重复密码长度为8～20位字符");
			return false;
		}
		Pattern p = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
		Matcher m = p.matcher(repwd);
		if (!m.find()) {
			map.put("repwdErrMsg", "必须包含数字和字母");
			return false;
		}
		p = Pattern.compile("^\\S+$");
		m = p.matcher(repwd);
		if (!m.find()) {
			map.put("repwdErrMsg", "重复密码不能包含空格");
			return false;
		}
		return true;
	}

	/**
	 * UI 3.验证密码 密码不能为空 密码长度为8～20位字符 必须包含数字和字母 密码不能包含空格
	 * 
	 * @param pwd
	 * @param map
	 * @return
	 */
	private boolean checkPwd(String pwd, Map<String, Object> map) {
		if (StringUtil.isEmpty(pwd)) {
			map.put("pwdErrMsg", "密码不能为空");
			return false;
		}
		if (pwd.length() < 8 || pwd.length() > 20) {
			map.put("pwdErrMsg", "密码长度为8～20位字符");
			return false;
		}
		Pattern p = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
		Matcher m = p.matcher(pwd);
		if (!m.find()) {
			map.put("pwdErrMsg", "必须包含数字和字母");
			return false;
		}
		p = Pattern.compile("^\\S+$");
		m = p.matcher(pwd);
		if (!m.find()) {
			map.put("pwdErrMsg", "密码不能包含空格");
			return false;
		}
		return true;
	}

	/**
	 * UI 2.验证用户名 用户名不能为空 用户名长度为5~15位字符 用户名只能包含英文字母、数字、“-”、“_” 用户名不能以数字开头
	 * 用户名不能以“-”开头 用户名不能以“_”开头 用户名不能以“-”结尾 用户名不能以“_”结尾
	 * 
	 * @param uname
	 * @param map
	 * @return
	 */
	private boolean checkUname(String uname, String mobile, Map<String, Object> map, UserNameRuleModule unrm) {
		if (unrm.getIsuse().equals((short) 1)) {
			minLength = unrm.getUsernamelength();
			maxLength = unrm.getUsernamemaxlength();
			nameRule = unrm.getSetrule();
			isChinese = unrm.getIschinese();
		}
		if (StringUtil.isEmpty(uname)) {
			map.put("unameErrMsg", "用户名不能为空");
			return false;
		}
		if (uname.length() > maxLength || uname.length() < minLength) {
			map.put("unameErrMsg", "用户名长度为" + minLength + "~" + maxLength + "位字符");
			return false;
		}
		if (nameRule.equals("b")) {
			if (uname.equals(mobile)) {
				map.put("unameErrMsg", "用户名不能和手机号相同");
				return false;
			}
		}
		Pattern p = null;
		Matcher m = null;
		if (isChinese == 2) {
			p = Pattern.compile("^[a-zA-Z0-9-_]+$");
			m = p.matcher(uname);
			if (!m.find()) {
				map.put("unameErrMsg", "用户名只能包含英文字母、数字、“-”、“_”");
				return false;
			}
		}
		/*
		 * if (nameRule.equals("b")) { p = Pattern.compile("^[0-9]"); m =
		 * p.matcher(uname); if (m.find()) { map.put("unameErrMsg",
		 * "用户名不能以数字开头"); return false; } }
		 */
		p = Pattern.compile("^[-]");
		m = p.matcher(uname);
		if (m.find()) {
			map.put("unameErrMsg", "用户名不能以“-”开头");
			return false;
		}
		p = Pattern.compile("^[_]");
		m = p.matcher(uname);
		if (m.find()) {
			map.put("unameErrMsg", "用户名不能以“_”开头");
			return false;
		}
		p = Pattern.compile("[-]$");
		m = p.matcher(uname);
		if (m.find()) {
			map.put("unameErrMsg", "用户名不能以“-”结尾 ");
			return false;
		}
		p = Pattern.compile("[_]$");
		m = p.matcher(uname);
		if (m.find()) {
			map.put("unameErrMsg", "用户名不能以“_”结尾 ");
			return false;
		}
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		ubai.setLoginname(AES.getEncrypt(uname));
		UserBaseAccountInfo ubai1 = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
		if (ubai1 != null) {
			map.put("unameErrMsg", "此用户名已存在");
			return false;
		}
		return true;
	}

	/**
	 * UI 验证手机号 不为空 长度不等于11， 手机格式
	 * 
	 * @param moblie
	 * @param map
	 */
	private boolean checkMobile(String mobile, Map<String, Object> map) {
		if (StringUtil.isEmpty(mobile)) {
			map.put("mobileErrMsg", "手机号码不能为空");
			return false;
		}
		if (mobile.length() != 11) {
			map.put("mobileErrMsg", "请输入11位手机号码");
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[0-9])|18[0-9])\\d{8}$");
		Matcher m = p.matcher(mobile);
		if (!m.find()) {
			return false;
		}
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		if(StringUtils.isNotEmpty(mobile)){
		ubai.setMobilephone(AES.getEncrypt(mobile));
		}
		UserBaseAccountInfo ubai1 = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
		if (ubai1 != null) {
			map.put("mobileErrMsg", "此手机号码已存在");
			return false;
		}
		return true;
	}

	/**
	 * 校验用户昵称,邮箱,手机号
	 * 
	 * @param ubai
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyLoginName", method = { RequestMethod.POST, RequestMethod.GET })
	public void verifyLoginName(UserBaseAccountInfo ubai) throws Exception {
		String jsonStr = "add";
		String email = ubai.getEmail();
		String loginName = ubai.getLoginname();
		String mobilephone = ubai.getMobilephone();
		if (StringUtil.isNotEmpty(email))
			AES.getEncrypt(email);
		if (StringUtil.isNotEmpty(loginName))
			AES.getEncrypt(loginName);
		if (StringUtil.isNotEmpty(mobilephone))
			AES.getEncrypt(mobilephone);
		UserBaseAccountInfo record = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
		if (record != null)
			jsonStr = JSON.toJSONString("exist");
		sendJsonData(response, jsonStr);
	}

	/**
	 * 跳转注册页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "toReg", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toReg(String promoCode) {
		List<UserNameRuleModule> list = userNameRuleModuleService.selective(null);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reg");
		mav.addObject("list", list);
		/*
		 * 判断推广码
		 */
		if (StringUtil.isNotEmpty(promoCode)) {
			if (promoCode.length() == 6) {
				mav.addObject("promoCode", promoCode);
			}
			/*
			 * 判断是否有无根推广码，普通推广码长度为6位
			 */
			if (promoCode.length() > 7) {
				mav.addObject("promoCode", promoCode.substring(0, 6));
				String[] codes = promoCode.substring(7).split("-");
				for (String code : codes) {
					if (code.length() != 4) {
						mav.setViewName("error");
						return mav;
					}
					Matcher m = p.matcher(code);
					if (m.find()) {
						mav.setViewName("error");
						return mav;
					}
				}
				Cookie cookie = new Cookie("promoInfo", promoCode);
				cookie.setMaxAge(3600 * 24 * 7);
				response.addCookie(cookie);
			}
		}
		return mav;
	}

	/**
	 * 跳转用户名+手机号注册
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "userReg", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUserReg(ModelAndView mav, String promoCode) {
		mav.setViewName("userReg");
		mav.addObject("promoCode", promoCode);
		return mav;
	}

	/**
	 * 跳转邮箱注册页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "nameReg", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toNameReg(ModelAndView mav, String promoCode) {
		mav.setViewName("nameReg");
		mav.addObject("promoCode", promoCode);
		return mav;
	}

	/**
	 * 跳转邮箱注册页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "mobileReg", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toMobileReg(ModelAndView mav, String promoCode) {
		mav.setViewName("mobileReg");
		mav.addObject("promoCode", promoCode);
		return mav;
	}

	/**
	 * 跳转邮箱注册页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "emailReg", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toEmailReg(ModelAndView mav, String promoCode) {
		mav.setViewName("emailReg");
		mav.addObject("promoCode", promoCode);
		return mav;
	}

	/**
	 * 手机号注册
	 * 
	 * @param ubai
	 * @param promoCode
	 *            推荐码
	 * @return
	 */
	@RequestMapping(value = "/mobileRegister", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView saveMobile(UserBaseAccountInfo ubai, String promoCode) {
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype((short) 3);
		List<UserNameRuleModule> uList = userNameRuleModuleService.selective(unrm);
		unrm = uList.get(0);
		Short isUse = unrm.getIsuse();
		if (!isUse.equals((short) 1))
			return toMobileReg(new ModelAndView(), promoCode);
		// 密码
		String password = request.getParameter("password");
		// 短信验证码
		// String smsmobileCode = "111111";
		String smsmobileCode = request.getParameter("mobileVerifyCode");
		// 系统生成的短信验证码
		// String mobileCode = "111111";
		String mobileCode = (String) request.getSession().getAttribute("mobileCode");
		// 图形验证码
		// String picVerifyCode = "111111";
		String picVerifyCode = request.getParameter("picVerifyCode");
		/*
		 * 回填信息
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		mav.addObject("mobile", ubai.getMobilephone());
		// 手机号码不能为空
		if (StringUtil.isNull(ubai.getMobilephone())) {
			return toMobileReg(mav, promoCode);
		}
		// 密码不能为空
		if (StringUtil.isNullStr(password)) {
			mav.addObject("msg", "pwd");
			return toMobileReg(mav, promoCode);
		}
		// 比较输入的短信验证码和系统发出的短信验证码
		if (StringUtil.isNullStr(smsmobileCode) || !smsmobileCode.equals(mobileCode)) {
			mav.addObject("msg", "smsmobileCode");
			return toMobileReg(mav, promoCode);
		}
		// 比较输入的验证码和实际生成的验证码
		if (!StringUtil.verifyCodeCompare(picVerifyCode, request)) {
			mav.addObject("msg", "picVerifyCode");
			return toMobileReg(mav, promoCode);
		}
		setUserName(ubai, unrm);
		/** 设置cookie */
		String regcookie = setCookie();
		/** 用户基本信息 */
		ubai.setIsmobileverify((short) 1);

		ubai.setIsemailverify((short) 0);
		setUserBaseAccountInfo(ubai, regcookie, (short) 3);
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		/** 用户账户安全信息 */
		setUserAccountSafeInfo(ubai, hashed);
		/** 用户账户 */
		setUserAccount(ubai);
		/** 用户推广设置 */
		setUserPromo(ubai, promoCode);
		/** 用户第三方推广设置 */
		setUserPromoThirdParty(ubai.getId());
		return mav;
	}

	/**
	 * 用户名注册
	 * 
	 * @param ubai
	 * @param promoCode
	 *            推荐码
	 * @return
	 */
	@RequestMapping(value = "/nameRegister", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView saveName(UserBaseAccountInfo ubai, String promoCode) {
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype((short) 1);
		List<UserNameRuleModule> uList = userNameRuleModuleService.selective(unrm);
		unrm = uList.get(0);
		Short isUse = unrm.getIsuse();
		if (!isUse.equals((short) 1))
			return toNameReg(new ModelAndView(), promoCode);
		// 密码
		String password = request.getParameter("password");
		// 图形验证码
		// String picVerifyCode = "111111";
		String picVerifyCode = request.getParameter("picVerifyCode");
		/*
		 * 回填信息
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		mav.addObject("loginName", ubai.getLoginname());
		mav.addObject("promoCode", promoCode);
		// 用户名不能为空
		if (StringUtil.isNull(ubai.getLoginname())) {
			return toNameReg(mav, promoCode);
		}
		// 密码不能为空
		if (StringUtil.isNullStr(password)) {
			mav.addObject("msg", "pwd");
			return toNameReg(mav, promoCode);
		}
		// 比较输入的验证码和实际生成的验证码
		if (!StringUtil.verifyCodeCompare(picVerifyCode, request)) {
			mav.addObject("msg", "picVerifyCode");
			return toNameReg(mav, promoCode);
		}
		/**
		 * 判断用户名是否包含中文
		 */
		if (unrm.getIschinese() != null && unrm.getIschinese().equals((short) 2)) {
			if (StringUtil.isChinese(ubai.getLoginname())) {
				mav.addObject("msg", "isChinese");
				return toNameReg(mav, promoCode);
			}
		}
		/**
		 * 判断用户名长度
		 */
		// 用户名长度必须小于用户名规则设置的长度限制
		if (unrm.getUsernamelength().intValue() > ubai.getLoginname().length()
				|| unrm.getUsernamemaxlength() < ubai.getLoginname().length()) {
			mav.addObject("msg", "userNameLength");
			mav.addObject("minLength", unrm.getUsernamelength().intValue());
			mav.addObject("maxLength", unrm.getUsernamemaxlength().intValue());
			return toNameReg(mav, promoCode);
		}
		/** 设置cookie */
		String regcookie = setCookie();
		/** 用户基本信息 */
		ubai.setIsmobileverify((short) 0);
		ubai.setIsemailverify((short) 0);
		setUserBaseAccountInfo(ubai, regcookie, (short) 1);
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		/** 用户账户安全信息 */
		setUserAccountSafeInfo(ubai, hashed);
		/** 用户账户 */
		setUserAccount(ubai);
		/** 用户推广设置 */
		setUserPromo(ubai, promoCode);
		/** 用户第三方推广设置 */
		setUserPromoThirdParty(ubai.getId());
		return mav;
	}

	/**
	 * 邮箱注册
	 * 
	 * @param ubai
	 * @param promoCode
	 * @return
	 */
	@RequestMapping(value = "/registerEmail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView saveEmail(UserBaseAccountInfo ubai, String promoCode) {
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype((short) 4);
		List<UserNameRuleModule> uList = userNameRuleModuleService.selective(unrm);
		unrm = uList.get(0);
		Short isUse = unrm.getIsuse();
		if (!isUse.equals((short) 1))
			return toEmailReg(new ModelAndView(), promoCode);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		String password = request.getParameter("password");
		// String picVerifyCode = "111111";
		String picVerifyCode = request.getParameter("picVerifyCode");
		/*
		 * 回填信息
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		mav.addObject("email", ubai.getEmail());
		mav.addObject("promoCode", promoCode);
		// 邮箱不能为空
		if (StringUtil.isNull(ubai.getEmail())) {
			return toEmailReg(mav, promoCode);
		}
		// 密码不能为空
		if (StringUtil.isNullStr(password)) {
			mav.addObject("msg", "pwd");
			return toEmailReg(mav, promoCode);
		}
		// 比较输入的验证码和实际生成的验证码
		if (!StringUtil.verifyCodeCompare(picVerifyCode, request)) {
			mav.addObject("msg", "picVerifyCode");
			return toEmailReg(mav, promoCode);
		}
		/**
		 * 设置用户名
		 */
		setUserName(ubai, unrm);
		/** 设置cookie */
		String regcookie = setCookie();
		/** 用户基本信息 */
		ubai.setIsmobileverify((short) 0);
		ubai.setIsemailverify((short) 1);
		setUserBaseAccountInfo(ubai, regcookie, (short) 4);
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		/** 用户账户安全信息 */
		setUserAccountSafeInfo(ubai, hashed);
		/** 用户账户 */
		setUserAccount(ubai);
		/** 用户推广设置 */
		setUserPromo(ubai, promoCode);
		/** 用户第三方推广设置 */
		setUserPromoThirdParty(ubai.getId());
		return mv;
	}

	/**
	 * 根据注册设置 设置用户名
	 * 
	 * @param ubai
	 * @param unrm
	 */
	private void setUserName(UserBaseAccountInfo ubai, UserNameRuleModule unrm) {
		// 系统自动同步手机号
		if (unrm.getSetrule().equals("c")) {
			ubai.setLoginname(ubai.getMobilephone());
		}
		if (unrm.getSetrule().equals("d")) { // 预设前缀字符+随机数
			if (unrm.getNamerule().equals("a")) {
				String presetStr = unrm.getPresetstr();
				String loginName = null;
				boolean flag = true;
				// 如果系统匹配的用户名存在，则重新匹配直到用户名不存在
				while (flag) {
					loginName = presetStr + StringUtil.getN(unrm.getRandomlength().intValue());
					UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
					ubai1.setLoginname(loginName);
					UserBaseAccountInfo record = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai1);
					if (record == null)
						flag = false;
				}
				ubai.setLoginname(loginName);
			}
			// 随机数
			if (unrm.getNamerule().equals("b")) {
				String loginName = null;
				boolean flag = true;
				while (flag) {
					loginName = String.valueOf(StringUtil.getN(unrm.getRandomlength().intValue()));
					UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
					ubai1.setLoginname(loginName);
					UserBaseAccountInfo record = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai1);
					if (record == null)
						flag = false;
				}
				ubai.setLoginname(loginName);
			}
		}
	}

	/**
	 * 用户名 手机号注册
	 * 
	 * @param ubai
	 * @param promoCode
	 * @return
	 */
	@RequestMapping(value = "/userRegister", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView saveUser(UserBaseAccountInfo ubai, String promoCode) {
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype((short) 2);
		List<UserNameRuleModule> uList = userNameRuleModuleService.selective(unrm);
		unrm = uList.get(0);
		Short isUse = unrm.getIsuse();
		if (!isUse.equals((short) 1))
			return toUserReg(new ModelAndView(), promoCode);

		// 密码
		String password = request.getParameter("password");
		// 短信验证码
		// String smsmobileCode = "111111";
		String smsmobileCode = request.getParameter("mobileVerifyCode");
		// 系统生成的短信验证码
		String mobileCode = "111111";
		// String
		// mobileCode=(String)request.getSession().getAttribute("mobileCode");
		// 图形验证码
		// String picVerifyCode =request.getParameter("picVerifyCode");
		/*
		 * 回填信息
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		mav.addObject("loginName", ubai.getLoginname());
		mav.addObject("mobile", ubai.getMobilephone());
		mav.addObject("promoCode", promoCode);
		// 用户名不能为空
		if (StringUtil.isNull(ubai.getLoginname())) {
			return toUserReg(mav, promoCode);
		}
		// 手机号码不能为空
		if (StringUtil.isNull(ubai.getMobilephone())) {
			return toUserReg(mav, promoCode);
		}
		// 密码不能为空
		if (StringUtil.isNullStr(password)) {
			mav.addObject("msg", "pwd");
			return toUserReg(mav, promoCode);
		}
		// 比较输入的短信验证码和系统发出的短信验证码
		if (StringUtil.isNullStr(smsmobileCode) || !smsmobileCode.equals(mobileCode)) {
			mav.addObject("msg", "smsmobileCode");
			return toUserReg(mav, promoCode);
		}
		// b.不能与手机号相同
		if (unrm.getSetrule().equals("b")) {
			if (ubai.getMobilephone().equals(ubai.getLoginname())) {
				mav.addObject("msg", "equal");
				return toUserReg(mav, promoCode);
			}
		}
		/**
		 * 判断用户名是否包含中文
		 */
		if (unrm.getIschinese() != null && unrm.getIschinese().equals((short) 2)) {
			if (StringUtil.isChinese(ubai.getLoginname())) {
				mav.addObject("msg", "isChinese");
				return toUserReg(mav, promoCode);
			}
		}
		// 用户名长度必须小于用户名规则设置的长度限制
		if (unrm.getUsernamelength().intValue() > ubai.getLoginname().length()
				|| unrm.getUsernamemaxlength() < ubai.getLoginname().length()) {
			mav.addObject("msg", "userNameLength");
			mav.addObject("minLength", unrm.getUsernamelength().intValue());
			mav.addObject("maxLength", unrm.getUsernamemaxlength().intValue());
			return toUserReg(mav, promoCode);
		}

		/** 设置cookie */
		String regcookie = setCookie();
		/** 用户基本信息 */
		/* 手机是否验证 */
		ubai.setIsmobileverify((short) 1);
		/* 邮箱是否验证 */
		ubai.setIsemailverify((short) 0);
		setUserBaseAccountInfo(ubai, regcookie, (short) 2);
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		/** 用户账户安全信息 */
		setUserAccountSafeInfo(ubai, hashed);
		/** 用户账户 */
		setUserAccount(ubai);
		/** 用户推广设置 */
		setUserPromo(ubai, promoCode);
		/** 用户第三方推广设置 */
		setUserPromoThirdParty(ubai.getId());
		return mav;
	}

	/**
	 * 用户账户
	 * 
	 * @param ubai
	 */
	private void setUserAccount(UserBaseAccountInfo ubai) {
		UserAccount userAccount = new UserAccount();
		userAccount.setBaseid(ubai.getId());
		userAccountService.insert(userAccount);
	}

	/**
	 * 用户账户安全信息
	 * 
	 * @param ubai
	 * @param hashed
	 */
	private void setUserAccountSafeInfo(UserBaseAccountInfo ubai, String hashed) {
		UserAccountSafeInfo userAccountSafeInfo = new UserAccountSafeInfo();
		// 登录密码(加密后)
		userAccountSafeInfo.setLoginpassword(hashed);
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
		// 是否开通托管账户
		userAccountSafeInfo.setIsfundsupervision((short) 0);
		userAccountSafeInfoService.insert(userAccountSafeInfo);
	}

	/**
	 * 用户基本账户信息
	 * 
	 * @param ubai
	 * @param regcookie
	 */
	private void setUserBaseAccountInfo(UserBaseAccountInfo ubai, String regcookie, Short regType) {
		// 用户号
		ubai.setAccountnumber(accountnumber.nextStringValue());
		// 注册IP
		ubai.setRegip(StringUtil.getIpAddr(request));
		// 注册Cookie
		ubai.setRegcookie(regcookie);
		// 账户类型 个人
		ubai.setAccounttype((short) 1);
		// 注册类型
		ubai.setRegtype(regType);
		// 注册时间
		ubai.setRegdate(new Date());
		// 是否激活
		ubai.setIsactive((short) 1);
		// 是否实名认证
		ubai.setIsreally((short) 0);
		userBaseAccountInfoService.insertSelective(ubai);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param mobilephone
	 *            手机号
	 */
	@RequestMapping(value = "/smsSend", method = RequestMethod.POST)
	public void smsSend(String mobilephone) {
		// UserBaseAccountInfo record = new UserBaseAccountInfo();
		// record.setMobilephone(mobilephone);
		// record =
		// userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(record);
		// if (record != null)
		// return;
		// 正式使用时根据数据库短信接口表的设置获取
		System.out.println(mobilephone);
		String verifyCode = String.valueOf(StringUtil.getN(6));
		// verifyCode = "111111";
		/*
		 * 系统通知业务
		 */
		// SySNoticeBiz sysNoticeBiz =
		// sysNoticeBizService.selectByPrimaryKey(new BigDecimal(3));
		// if (sysNoticeBiz.getIsopen().equals((short) 0))
		// return;
		/*
		 * 短信通道
		 */
		// SMSChannel smsChannel =
		// smsChannelService.selectByPrimaryKey(sysNoticeBiz.getSmscid());
		// if (smsChannel == null)
		// return;
		/*
		 * 短信模板
		 */
		// MessageTemplate messageTemplate =
		// messageTemplateService.selectByPrimaryKey(new BigDecimal(1));
		/*
		 * 短信参数
		 */
		// String interUrl = smsChannel.getSmsurl();
		// String entNo = smsChannel.getExtno();
		// String account = smsChannel.getPusername();
		// String password = smsChannel.getPpassword();
		// String msg = messageTemplate.getContent().replaceAll("\\{C\\}",
		// verifyCode);
		// 发送短信
		boolean flag=smsSendService.SMSSend4Code(mobilephone, verifyCode);
		if(flag){
		session.setAttribute("mobileCode", verifyCode);
		System.out.println(verifyCode);
		}

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
		userPromo.setPromonum(0L);
		// 有效推广人数
		userPromo.setValidnum(0L);
		// 推广费总收入
		userPromo.setPromofee(0d);
		// 推广层数 （本级）
		userPromo.setPromolevels(1L);
		// 推广代理等级
		userPromo.setProxygrade(new BigDecimal(23));
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
		int iden = userPromoService.insertSelective(userPromo);
		if (iden > 0 && up != null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("promoInfo")) {
					// 无根推广码
					String promoInfo = cookie.getValue().toString();
					if (StringUtil.isNotEmpty(promoInfo)) {
						PromoRegInfo promoRegInfo = new PromoRegInfo();
						promoRegInfo.setRegusercode(promoInfo);
						promoRegInfo.setPuserid(up.getId());
						promoRegInfo.setRegtime(ubai.getRegdate());
						promoRegInfo.setRuserid(ubai.getId());
						promoRegInfoService.insertSelective(promoRegInfo);
						cookie.setMaxAge(0);
						cookie.setValue(null);
						response.addCookie(cookie);
					}
				}
			}
		}
	}

	/**
	 * 推广第三方设置
	 * 
	 * @param id
	 */
	private void setUserPromoThirdParty(BigDecimal id) {
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
}
