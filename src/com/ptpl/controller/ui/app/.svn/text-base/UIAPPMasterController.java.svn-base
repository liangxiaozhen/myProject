package com.ptpl.controller.ui.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.MySessionContext;
import com.ptpl.controller.ui.service.UIAppFindPasswordServiceI;
import com.ptpl.controller.ui.service.UIAppMasterServiceI;
import com.ptpl.controller.ui.service.UIAppOpenAnAccountServiceI;
import com.ptpl.controller.ui.service.UIAppSecurityCenterServiceI;
import com.ptpl.controller.ui.service.UIAppUserRechargeServiceI;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/ui/app")
public class UIAPPMasterController extends BaseController {

	@Autowired
	private UIAppMasterServiceI appMasterI;
	/** 充值记录 */
	@Autowired
	UIAppUserRechargeServiceI uIAppUserRechargeService;
	/**
	 * 用户账号安全信息service
	 */
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;

	/**
	 * 用户基本信息service
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;

	/**
	 * 用户托管账户信息service
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;

	@Autowired
	private UIAppSecurityCenterServiceI appSecurityCenterServiceI;
	@Autowired
	private UIAppFindPasswordServiceI uiAppFindPasswordService;// 手机修改密码

	@Autowired
	private UserBankCardServiceI userBankCardService;

	@Autowired
	private UserWithdrawsCashServiceI userWithdrawsCashService;

	@Autowired
	private UIAppOpenAnAccountServiceI appOpenAnAccountServiceI;

	/**
	 * 
	 * @Title: masterController @Description: TODO(拦截器不拦截) @param @param
	 *         request @param @param response @param @return 设定文件 @return String
	 *         返回类型 @author cjm @throws
	 */
	@RequestMapping(value = "/master", method = { RequestMethod.POST, RequestMethod.GET })
	public String masterController(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		String Method = request.getParameter("Method");
		Map<String, String> hashMap = new HashMap<>();
		if (StringUtil.isEmpty(Method)) {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "Method_null");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：操作失败！Method参数不能为空！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 方法总控制
		if (Method.equals("userlogin")) {// 用户登录
			Map<String, String> hashMap2 = appMasterI.userLogin(request, response);// 调用用户登录接口
			String str = JSON.toJSONString(hashMap2);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else if (Method.equals("getValidationCode")) {
			// 通过手机修改密码时，点击获取验证码
			String phoneNumber = request.getParameter("phoneNumber");
			Map<String, String> map = uiAppFindPasswordService.getValidationCode(phoneNumber, session);
			try {
				sendJsonData(response, JSON.toJSONString(map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Method.equals("checkPhoneNumberCode")) {
			// 用户填写完手机验证码之后 ，开始验证
			String phoneNumber = request.getParameter("phoneNumber");
			String code = request.getParameter("code");
			Map<String, String> map = uiAppFindPasswordService.checkPhoneNumberCode(phoneNumber, code, session);
			try {
				sendJsonData(response, JSON.toJSONString(map));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (Method.equals("phoneResetPassword")) {
			// 用户填写新的密码，开始重置密码了
			String phoneNumber = request.getParameter("phoneNumber");// 新密码
			String code = request.getParameter("code");// 确认新密码
			String pwd = request.getParameter("pwd");// 新密码
			String repwd = request.getParameter("repwd");// 确认新密码
			Map<String, Object> map = uiAppFindPasswordService.phoneResetPassword(phoneNumber, code, pwd, repwd,
					session);
			try {
				sendJsonData(response, JSON.toJSONString(map));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "Method_error");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：操作失败！Method参数错误！请传正确的参数值！！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}

	/**
	 * 
	 * @Title: userMasterController @Description: TODO(拦截器 拦截 ) @param @param
	 *         request @param @param response @param @return 设定文件 @return String
	 *         返回类型 @author cjm @throws
	 */
	@RequestMapping(value = "/userMaster", method = { RequestMethod.POST, RequestMethod.GET })
	public String userMasterController(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		String sessionId = request.getParameter("sessionId");
		if (StringUtil.isEmpty(sessionId)) {
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "sesstionId_null");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：sesstionId 找不到！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		HttpSession session = MySessionContext.getSession(sessionId);

		if (session == null) {
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.LOGOUT);
			hashMap.put(AppSession_Constant.RESULTCODE, "logout");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：因您操作超时！请重新登录！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) session
				.getAttribute(AppSession_Constant.APPUSER);// 普通用户基本信息表
		UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) session
				.getAttribute(AppSession_Constant.APPUSERACCOUNTSAFEINFO);// 普通用户安全信息表
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) session
				.getAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO);// 第三方托管账号信息

		String Method = request.getParameter("Method");
		Map<String, String> hashMap = new HashMap<>();
		if (StringUtil.isEmpty(Method)) {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "Method_null");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：操作失败！Method参数不能为空！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		if (Method.equals("userRealName")) {// 实名认证接口
			hashMap = appSecurityCenterServiceI.realNameCertification(request, response, userBaseAccountInfo, session);

		} else if (Method.equals("sendPhoneCode")) {// 发送手机短信验证码
			hashMap = appSecurityCenterServiceI.sendPhoneCode(request, response, userBaseAccountInfo, session);

		} else if (Method.equals("checkPhoneCode")) {// 验证手机短信验证码是否正确
			hashMap = appSecurityCenterServiceI.checkPhoneCode(request, response, session);

		} else if (Method.equals("sendNewPhoneCode")) {// 发送新手机短信验证码
			hashMap = appSecurityCenterServiceI.sendNewPhoneCode(request, response, session);

		} else if (Method.equals("phoneUpdate")) {// 修改手机号码
			hashMap = appSecurityCenterServiceI.phoneUpdate(request, response, userBaseAccountInfo, session);

		} else if (Method.equals("emailBind")) {// 新邮箱绑定接口
			hashMap = appSecurityCenterServiceI.emailBind(request, response, userBaseAccountInfo);

		} else if (Method.equals("emailUpdateCheck")) {// 邮箱修改接口
			hashMap = appSecurityCenterServiceI.emailUpdateCheck(request, response, userBaseAccountInfo);

		} else if (Method.equals("getUserBaseDetail")) {// 查询用户基本信息
			hashMap = appSecurityCenterServiceI.getUserBaseDetail(request, response, userBaseAccountInfo,
					userFsAccountInfo);
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			hashMap.put(AppSession_Constant.RESULTCODE, "success");
			hashMap.put(AppSession_Constant.MESSAGE, "查询成功！");
		} else if (Method.equals("checkWithdraws")) {// 提现验证
			hashMap = appMasterI.checkWithdraws(request, userBaseAccountInfo, userAccountSafeInfo);
		} else if (Method.equals("openAnAccount")) {// 银行电子账号开立
			hashMap = appOpenAnAccountServiceI.userOpenAnAccount(request, response, userBaseAccountInfo, session);

		} else if (Method.equals("queryblane")) {// 余额查询
			hashMap = uIAppUserRechargeService.queryBlane(userFsAccountInfo);

		} else if (Method.equals("getuserinfo")) {// 充值页面参数显示
			hashMap = uIAppUserRechargeService.getUserInfo(userBaseAccountInfo, userFsAccountInfo);

		} else if (Method.equals("getmessage")) {// 获取短信验证码
			hashMap = uIAppUserRechargeService.getmessage(userBaseAccountInfo, userFsAccountInfo);

		} else if (Method.equals("confirmrecharge")) {// 确认充值
			hashMap = uIAppUserRechargeService.confirmRecharge(userBaseAccountInfo, userFsAccountInfo,request);

		} else if (Method.equals("updateLoginPassWord")) {// 修改登录密码
			hashMap = appSecurityCenterServiceI.updateLoginPassWord(request, response, userBaseAccountInfo,
					userAccountSafeInfo, session);
		} else {
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "Method_error");
			hashMap.put(AppSession_Constant.MESSAGE, "提示：操作失败！Method参数错误！请传正确的参数值！！");
		}

		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * APP端提现
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月17日 下午2:00:53
	 * @param sessionId
	 */
	@RequestMapping(value = "drawmoney")
	public void userDrawMoney(String sessionId) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		if (StringUtil.isNotEmpty(sessionId)) {
			HttpSession session = MySessionContext.getSession(sessionId);
			if (session != null) {
				UserBaseAccountInfo userInfo = (UserBaseAccountInfo) session.getAttribute(AppSession_Constant.APPUSER);// 普通用户基本信息表
				//UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) session.getAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO); 第三方托管账号信息
				String check = (String) request.getSession().getAttribute(AppSession_Constant.CHECKFLAG);
				Double fee = (Double) request.getSession().getAttribute(AppSession_Constant.FEE);
				Double amount = (Double) request.getSession().getAttribute(AppSession_Constant.AMOUNT);
				if ("pass".equals(check)) {
					request.getSession().setAttribute(AppSession_Constant.CHECKFLAG, "");
					if (amount != null && amount > 0) {
						userWithdrawsCashService.withdrawsCash(request, response, amount, fee, userInfo, (short)2);
					}
				}
			}
		}
		try {
			response.sendRedirect(request.getContextPath() + "/withdrawError.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/tradePassWord")
	public void tradePassWord(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");

		String sessionId = request.getParameter("sessionId");
		if (StringUtil.isNotEmpty(sessionId)) {
			HttpSession session = MySessionContext.getSession(sessionId);
			if (session != null) {
				UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) session
						.getAttribute(AppSession_Constant.APPUSER);// 普通用户基本信息表
				UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) session
						.getAttribute(AppSession_Constant.APPUSERACCOUNTSAFEINFO);// 普通用户安全信息表
				UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) session
						.getAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO);// 第三方托管账号信息
				if (userBaseAccountInfo != null && userAccountSafeInfo != null && userFsAccountInfo != null) {
					appOpenAnAccountServiceI.tradePassWordHtml(request, response, userBaseAccountInfo,
							userFsAccountInfo, session);
					return;
				}
			}
		}

		try {
			request.setAttribute("Msg", "未通过交易密码验证！请重新验证手机短信验证码！");
			request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWordError.jsp").forward(request,
					response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
}
