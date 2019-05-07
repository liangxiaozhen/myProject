package com.ptpl.controller.ui.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.MySessionContext;
import com.ptpl.controller.ui.service.UIAppMasterServiceI;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserLog;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.model.WithdrawsCashRate;
import com.ptpl.model.WithdrawsCashRstr;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.service.UserRiskServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.service.WithdrawsCashRateServiceI;
import com.ptpl.service.WithdrawsCashRstrServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

public class UIAppMasterImpl implements UIAppMasterServiceI {

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

	@Autowired
	private WithdrawsCashRstrServiceI withdrawsCashRstrService;

	@Autowired
	private UserWithdrawsCashServiceI userWithdrawsCashService;

	@Autowired
	private WithdrawsCashRateServiceI withdrawsCashRateService;

	/**
	 * 
	 * @Title: userLogin @Description: TODO(用户登录接口) @param @param
	 *         request @param @param response @param @return 设定文件 @return Map
	 *         <String,String> 返回类型 @author cjm @throws
	 */
	@Override
	public Map<String, String> userLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> hashMap = new HashMap<>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtil.isEmpty(username)) {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "username_null");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户账号不能为空！");
			return hashMap;
		}

		if (StringUtil.isEmpty(password)) {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "password_null");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户登录密码不能为空！");
			return hashMap;
		}

		UserBaseAccountInfo userBaseAccountInfo = new UserBaseAccountInfo();
		username = AES.getEncrypt(username);
		userBaseAccountInfo.setLoginname(username);// 用户名
		userBaseAccountInfo.setEmail(username);// 邮箱
		userBaseAccountInfo.setMobilephone(username);// 手机号码

		// 根据用户名查询密码
		UserAccountSafeInfo userAccountSafeIn = userAccountSafeInfoServiceI
				.getLoginPassWordByLoginName(userBaseAccountInfo);
		if (userAccountSafeIn == null || userAccountSafeIn.getLoginpassword() == null) {// 用户名或密码错误
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "psdOrUsername_error");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户名或密码错误！");
			return hashMap;
		}

		// 判断密码是否正确
		boolean pswFlag = BCrypt.checkpw(password, userAccountSafeIn.getLoginpassword());// 正确时为true；
		if (!pswFlag) {// 用户名或密码错误
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "psdOrUsername_error");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户名或密码错误！");
			return hashMap;
		}

		// 用户基本信息对象
		UserBaseAccountInfo userBaseAccountInfo2 = userBaseAccountInfoServiceI
				.selectByPrimaryKey(userAccountSafeIn.getBaseid());
		if (userBaseAccountInfo2 == null) {// 用户名或密码错误
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "psdOrUsername_error");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户名或密码错误！");
			return hashMap;
		}

		/* 账户状态(1正常\0停用) */
		String status = userAccountSafeIn.getStatus().toString();
		if (status != null && status.equalsIgnoreCase("0")) {// 用户账号已停用
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "userDisable");
			hashMap.put(AppSession_Constant.MESSAGE, "登录失败！用户账号已停用！请联系客服");
			return hashMap;
		}

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
		userLogServiceI.insert(getUserLog(userAccountSafeIn, ipAddr, username));
 		
 		request.getSession().setAttribute(AppSession_Constant.APPUSER, BaseController.getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo2));// UserBaseAccountInfo
		// 把用户托管账户信息实体对象放进session
		// 用户托管账户信息实体对象
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI
				.findUserFsAccountInfoByBaseId(userAccountSafeIn.getBaseid());
		if (userFsAccountInfo != null) {
  			request.getSession().setAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO, BaseController.getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
		}
		// 把用户账号安全信息放进session
		request.getSession().setAttribute(AppSession_Constant.APPUSERACCOUNTSAFEINFO, userAccountSafeIn);

		String sessionId = request.getSession().getId();
		MySessionContext.AddSession(request.getSession());

		hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
		hashMap.put(AppSession_Constant.RESULTCODE, "success");
		hashMap.put(AppSession_Constant.SESSIONID, sessionId);
		hashMap.put(AppSession_Constant.MESSAGE, "登录成功！");
		return hashMap;
	}

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
		return userLog;
	}

	/**
	 * 提现验证
	 */
	@Override
	public Map<String, String> checkWithdraws(HttpServletRequest request, UserBaseAccountInfo ubai,
			UserAccountSafeInfo uasi) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		map.put(AppSession_Constant.MESSAGE, "提现金额不能为0");
		String doubleStr = request.getParameter("money");
		if (StringUtil.isEmpty(doubleStr)) {
			return map;
		}
		Double money = Double.valueOf(doubleStr);
		if (null != money && money > 0) {
			Short ugrade = uasi.getUgrade();// 会员等级
			/*
			 * 提现设置
			 */
			WithdrawsCashRstr wdcRstr = new WithdrawsCashRstr();
			wdcRstr.setUgrade((short) 99);
			List<WithdrawsCashRstr> wdcRstrList = withdrawsCashRstrService.selective(wdcRstr);
			if (wdcRstrList.size() == 0) {
				wdcRstr.setUgrade(ugrade);
				wdcRstrList = withdrawsCashRstrService.selective(wdcRstr);
				if (wdcRstrList.size() == 0) {
					map.put(AppSession_Constant.MESSAGE, "提示：提现设置未设置，请联系客服。");
					return map;
				}
			}
			wdcRstr = wdcRstrList.get(0);
			// 单笔最低金额
			Double lowestmoney = wdcRstr.getLowestmoney();
			// 单笔最高金额
			Double highestmoney = wdcRstr.getHighestmoney();
			// 日提现金额限制
			Double daymoneyrest = wdcRstr.getDaymoneyrest();
			// 日提现次数
			Integer daytimesrest = wdcRstr.getDaytimesrest();
			if (lowestmoney > money || money >= highestmoney) {
				map.put(AppSession_Constant.MESSAGE, "提示：单笔提现金额范围：" + lowestmoney + "~" + highestmoney);
				return map;
			}
			UserWithdrawsCash uwdCash = userWithdrawsCashService.selectCountAmountForId(ubai.getId());
			int dayNum = uwdCash.getDaytotal();
			double dayAmount = uwdCash.getDayamount();
			if (dayNum >= daytimesrest.intValue()) {
				map.put(AppSession_Constant.MESSAGE, "提示：超出当日提现次数。");
				return map;
			}
			if (Arith.sub(daymoneyrest, dayAmount) < money) {
				map.put(AppSession_Constant.MESSAGE, "提示：超出当日提现额度。");
				return map;
			}
			/*
			 * 提现费率
			 */
			WithdrawsCashRate wdcRate = new WithdrawsCashRate();
			wdcRate.setUgrade((short) 99);
			wdcRate.setMinmoney(money);
			wdcRate = withdrawsCashRateService.getWithdrawsCashRate(wdcRate);
			if (null == wdcRate) {
				WithdrawsCashRate wdcRate1 = new WithdrawsCashRate();
				wdcRate1.setUgrade(ugrade);
				wdcRate1.setMinmoney(money);
				wdcRate = withdrawsCashRateService.getWithdrawsCashRate(wdcRate1);
				if (null == wdcRate) {
					map.put(AppSession_Constant.MESSAGE, "提示：提现费率未设置，请联系客服。");
					return map;
				}
			}
			Short wdcMode = wdcRate.getWdcmode();// 收费类型
			if (wdcMode == null) {
				map.put(AppSession_Constant.MESSAGE, "提示：提现费率设置未完整，请联系客服。");
				return map;
			}
			if (wdcMode.intValue() == 1) {// 固定金额
				Double quota = wdcRate.getQuota();
				if (null == quota) {
					map.put(AppSession_Constant.MESSAGE, "提示：提现费率设置未完整，请联系客服。");
					return map;
				}
				request.getSession().setAttribute(AppSession_Constant.FEE, quota);
			}
			if (wdcMode.intValue() == 2) {// 比例
				Double rate = wdcRate.getWdcrate();
				Double minFee = wdcRate.getMinfee();
				Double maxFee = wdcRate.getMaxfee();
				if (null == rate) {
					map.put(AppSession_Constant.MESSAGE, "提示：提现费率设置未完整，请联系客服。");
					return map;
				}
				Double fee = Arith.mul(Arith.div(rate, 100d), money);
				if (null != minFee) {
					if (fee.doubleValue() < minFee.doubleValue()) {
						fee = minFee;
					}
				}
				if (null != maxFee) {
					if (fee.doubleValue() > maxFee.doubleValue()) {
						fee = maxFee;
					}
				}
				request.getSession().setAttribute(AppSession_Constant.FEE, fee);
				request.getSession().setAttribute(AppSession_Constant.AMOUNT, money);
			}
			request.getSession().setAttribute(AppSession_Constant.CHECKFLAG, "pass");
		}
		map.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
		map.put(AppSession_Constant.MESSAGE, "验证成功");
		return map;
	}

}
