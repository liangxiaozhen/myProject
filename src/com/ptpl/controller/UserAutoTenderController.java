package com.ptpl.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.moneymoremore.MMMAuthorizationController;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAuthorization;
import com.ptpl.model.UserAutoTender;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserAuthorizationServiceI;
import com.ptpl.service.UserAutoTenderServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;

@Controller
@RequestMapping("/user/autoTender")
public class UserAutoTenderController extends BaseController {

	/** 用户托管账户信息Service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	/** 用户账户信息Service */
	@Autowired
	UserAccountServiceI userAccountService;
	/** 用户基本信息Service */
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	/** 自动投标计划Service */
	@Autowired
	UserAutoTenderServiceI userAutoTenderService;
	/** 用户授权记录Service */
	@Autowired
	UserAuthorizationServiceI userAuthorizationService;

	/***
	 * 跳转到自动投标页面
	 * @return
	 */
	@RequestMapping("/autoTenderList")
	public ModelAndView autoTenderList(){
		ModelAndView mv = new ModelAndView();

		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		if (userBaseAccountInfo == null) {
			mv.setViewName("user/login");
		} else {
//			BigDecimal investorId = userBaseAccountInfo.getId(); // 从账户基本信息表中获取投资方ID
//			UserAccount userAccount = userAccountService.getUserAccountByBaseId(investorId); // 根据baseid查询用户账户信息
//			UserAutoTender tenderPlan = userAutoTenderService.selectTenderPlanByBaseId(investorId); // 根据baseid查询用户自动投标计划
//			mv.addObject("avlbalance", userAccount.getAvlbalance());
//			mv.addObject("tenderPlan", tenderPlan);
//			mv.addObject("df", df);
//			mv.setViewName("user/autoTender/AutoTender_List");
			BigDecimal baseid = userBaseAccountInfo.getId(); // 从账户基本信息表中获取投资方ID
			UserAuthorization userAuthorization = userAuthorizationService.findUserAuthorizationByBaseId(baseid);
			if(userAuthorization != null && userAuthorization.getAuthorizestatus().startsWith("1")){
				mv.addObject("status", 1);
			}else{
				mv.addObject("status", 0);
			}
			mv.setViewName("user/autoTender/AutoTender_List");
		}
		return mv;
	}
	
	@RequestMapping(value = "/authorize", method = { RequestMethod.POST, RequestMethod.GET })
	public void authorize(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo ufs = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		BigDecimal baseid = ufs.getBaseid();
		UserAuthorization userAuthorization = userAuthorizationService.findUserAuthorizationByBaseId(baseid);
		if(userAuthorization == null || userAuthorization.getAuthorizestatus().startsWith("0")){
			MMMAuthorizationController.doRepayMentAuthorizationController(request, response, ufs.getMoneymoremoreid(), "1", "", baseid.toString(), "", "");
		}else{
			MMMAuthorizationController.doRepayMentAuthorizationController(request, response, ufs.getMoneymoremoreid(), "", "1", baseid.toString(), "", "");
		}
	}

	/***
	 * 开启自动投标计划
	 * @param huifuparam
	 * @param userAutoTender
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/openAutoTender")
	public String openAutoTender(HuifuParams huifuparam, UserAutoTender userAutoTender) throws Exception {
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		String message = "";
		System.out.println("message==========="+message);
		if(userBaseAccountInfo == null){
			return "redirect:user/login.action";
		}else{
			BigDecimal investorId = userBaseAccountInfo.getId(); // 从账户基本信息表中获取投资方ID
			huifuparam.setCmdId("AutoTenderPlan"); // 消息类型
			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(investorId); // 根据baseid查询用户托管账户信息
			if(userFsAccountInfo != null){
				huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); // 用户客户号
				String tenderplantype = request.getParameter("tenderplantype"); // 获取自动投标计划类型
				if(tenderplantype.equalsIgnoreCase("")){
					message = "请选择投标计划类型";
				}else if(tenderplantype.equalsIgnoreCase("W")){	// 完全授权
					huifuparam.setTenderPlanType("W");
				}else if(tenderplantype.equalsIgnoreCase("P")){	// 部分授权
					huifuparam.setTenderPlanType("P");
					if(request.getParameter("transamt").trim().equals("")){	// 投标金额限制
						message = "请输入单笔最大金额";
					}else{
						String transamt = df1.format(Double.valueOf(request.getParameter("transamt")));
						UserAccount userAccount = userAccountService.getUserAccountByBaseId(investorId);
						if(Arith.sub(Double.valueOf(transamt),userAccount.getAvlbalance()) > 0){	// 投标金额限制不能超过可用余额
							message = "投标金额限制不能超过可用余额";
						}else{
							huifuparam.setTransAmt(transamt); // 交易金额
						}
					}
				}
				// 动态获取项目路径
				String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
				huifuparam.setRetUrl(basePath+"/huifu/autoTenderPlan/autoTenderPlanRetUrl.action"); // 页面返回URL
				huifuparam.setMerPriv(investorId.toString());	// 商户私有域
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getTenderPlanType()))
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))
				.append(StringUtils.trimToEmpty(huifuparam.getRetUrl()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()));
				String plainStr = buffer.toString();
				System.out.println("组装绑卡加签字符串原文:========="+plainStr);
				try {
					String ChkValue = SignUtils.encryptByRSA(plainStr);
					request.setAttribute("Version", huifuparam.getVersion());
					request.setAttribute("CmdId", huifuparam.getCmdId());
					request.setAttribute("MerCustId", huifuparam.getMerCustId());
					request.setAttribute("UsrCustId", huifuparam.getUsrCustId());
					request.setAttribute("TenderPlanType", huifuparam.getTenderPlanType());
					request.setAttribute("TransAmt", huifuparam.getTransAmt());
					request.setAttribute("RetUrl", huifuparam.getRetUrl());
					request.setAttribute("MerPriv", huifuparam.getMerPriv());
					request.setAttribute("ChkValue", ChkValue);
					String loginname = userBaseAccountInfo.getLoginname();
					insetAutoTenderPlan(huifuparam,investorId,loginname);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!message.equals("")){
					request.setAttribute("message", message);
					request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlan/AutoTenderPlan_Error.jsp").forward(request, response);
					System.out.println("message==========="+message);
				}else{
					request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlan/autoTenderPlan.jsp").forward(request, response);
					System.out.println("==========="+message);
				}
			}else{
				return "redirect:huifu/UserRegister.action";
			}
		}
		return null;
	}

	/**
	 * 新增自动投标计划
	 * @param huifuparam
	 * @param investorId
	 * @param loginname
	 */
	private void insetAutoTenderPlan(HuifuParams huifuparam,BigDecimal investorId,String loginname) {
		UserAutoTender uat = new UserAutoTender();
		uat.setBaseid(investorId);	// 用户ID
		uat.setIsblending((short) 0);	// 是否系统勾兑
		uat.setIsmanblending((short) 0);	// 是否人工勾兑
		uat.setPaycompany("汇付天下");	// 自动投标计划开启通道公司（汇付天下）
		uat.setOperatorman(loginname);	// 操作人
		if(userAutoTenderService.selectTenderPlanByBaseId(investorId) == null){	// 查询是否开启过自动投标计划
			userAutoTenderService.insertSelective(uat);
		}else{
			userAutoTenderService.updateByBaseIdSelective(uat);
		}
	}
}
