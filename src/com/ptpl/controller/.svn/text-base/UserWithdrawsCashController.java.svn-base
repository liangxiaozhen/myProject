package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.MMMBankCode;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.UserWithdrawsCash_Constant;
import com.ptpl.controller.manager.UserWithdrawsCashManagerController;
import com.ptpl.controller.moneymoremore.model.LoanWithdrawsBean;
import com.ptpl.model.CloseTime;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialTime;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.model.WithdrawsCashRate;
import com.ptpl.model.WithdrawsCashRstr;
import com.ptpl.service.CloseTimeServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialTimeServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.service.WithdrawsCashRateServiceI;
import com.ptpl.service.WithdrawsCashRstrServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

/**
 * 用户提现
 * 
 * @author xiaoy 提现 Controller
 * @date 2016年5月27日 下午4:38:15
 */
@Controller
@RequestMapping("/user/userwithdrawscash")
@Scope("prototype")
public class UserWithdrawsCashController extends BaseController {
	@Autowired
	private UserWithdrawsCashServiceI userWithdrawsCashService;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserBankCardServiceI userBankCardService;
	@Autowired
	private WithdrawsCashRstrServiceI withdrawsCashRstrService;
	@Autowired
	private WithdrawsCashRateServiceI withdrawsCashRateService;
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private CloseTimeServiceI closeTimeService;
	@Autowired
	private SpecialTimeServiceI specialTimeService;
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	@Autowired
	private UserAccountSafeInfoServiceI uasiService;
	@Autowired
	private QueryBlaneServiceI queryBlaneService;

	/**
	 * 用户 提现界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/withdraw", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryUserById() throws Exception {
		ModelAndView mav = new ModelAndView();
		UserBaseAccountInfo userInfo = getUserBaseAccountInfo();
		// UserAccount userAccount =
		// userAccountService.getUserAccountByBaseId(userInfo.getId());
		UserBankCard userBankCard = new UserBankCard();
		userBankCard.setBaseid(userInfo.getId());
		userBankCard.setIsdefaultcard((short) 1);
		userBankCard = userBankCardService.findIsDefaultCard(userBankCard);
		if (userBankCard != null) {
			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService
					.findUserFsAccountInfoByBaseId(userInfo.getId());
			Map<String, String> map = queryBlaneService.queryBlane(AES.getDecrypt(userFsAccountInfo.getUsrcustid()));
			mav.addObject("isopen", "success");
			String bankCode = AES.getDecrypt(userBankCard.getCardno());
			bankCode = bankCode.substring(0, 4) + "**** ****" + bankCode.substring(bankCode.length() - 4);
			mav.addObject("bankCode", bankCode);
			mav.addObject("bankName", userBankCard.getBankname());
			mav.addObject("avlbalance", map.get("AVAIL_BAL"));
			mav.addObject("tradePass", userFsAccountInfo.getTradepass());
		}
		mav.setViewName("user/manager/userwithdraw/carrya");
		return mav;
	}

	@RequestMapping(value = "withdrawPro")
	public void proWithdraw() throws Exception {
		// 用户基本信息
		UserBaseAccountInfo userInfo = getUserBaseAccountInfo();
		if(userInfo.getIsreally().intValue()==0){
			sendJsonData(response, JSON.toJSONString("2"));
			return;
		}
		// 用户托管账户信息
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userInfo.getId());
		if (userFsAccountInfo.getIsopenfsinfo().intValue() == 0) {
			sendJsonData(response, JSON.toJSONString("1"));
			return;
		}
		// 用户银行卡
		UserBankCard userBankCard = new UserBankCard();
		userBankCard.setBaseid(userInfo.getId());
		userBankCard.setIsdefaultcard((short) 1);
		userBankCard = userBankCardService.findIsDefaultCard(userBankCard);
		if (null == userBankCard) {
			sendJsonData(response, JSON.toJSONString("0"));
			return;
		}
		// 交易密码
		if (userFsAccountInfo.getTradepass() == 0) {
			sendJsonData(response, JSON.toJSONString("3"));
			return;
		}
		sendJsonData(response, JSON.toJSONString("success"));
	}

	@RequestMapping(value = "/withdrawDetails")
	public void withdrawDetails() throws Exception {
		UserBaseAccountInfo userInfo = getUserBaseAccountInfo();
		if (userInfo != null) {
			UserWithdrawsCash uwdCash = new UserWithdrawsCash();
			uwdCash.setBaseid(userInfo.getId());
			List<UserWithdrawsCash> userWithdrawsCashs = userWithdrawsCashService.getUseWithdrawsCashNote(uwdCash);
			sendJsonData(response, JSON.toJSONString(userWithdrawsCashs));
		}
	}

	/**
	 * 前端提示提现手续费
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月8日 下午5:19:52
	 * @param money
	 */
	@RequestMapping(value = "checkFee")
	public synchronized void checkFee(Double money) throws Exception {
		Double withdrawfee = 0d;
		if (null != money && money > 0) {
			UserBaseAccountInfo ubai = getUserBaseAccountInfo();
			if (ubai == null)
				return;
			BigDecimal id = ubai.getId();// baseid
			UserAccountSafeInfo uasi = uasiService.selectByBaseId(id);
			Short ugrade = uasi.getUgrade();// 会员等级
			/*
			 * 提现费率
			 */
			WithdrawsCashRate wdcRate = new WithdrawsCashRate();
			wdcRate.setUgrade((short) 99);
			wdcRate.setMinmoney(money);// 分段金额
			wdcRate = withdrawsCashRateService.getWithdrawsCashRate(wdcRate);
			if (null == wdcRate) {
				WithdrawsCashRate wdcRate1 = new WithdrawsCashRate();
				wdcRate1.setUgrade(ugrade);
				wdcRate1.setMinmoney(money);
				wdcRate = withdrawsCashRateService.getWithdrawsCashRate(wdcRate1);
				if (null == wdcRate) {
					sendJsonData(response, JSON.toJSONString(withdrawfee));
					return;
				}
			}
			Short wdcMode = wdcRate.getWdcmode();// 收费类型
			if (wdcMode == null) {
				sendJsonData(response, JSON.toJSONString(withdrawfee));
				return;
			}
			if (wdcMode.intValue() == 1) {// 固定金额
				Double quota = wdcRate.getQuota();
				if (null == quota) {
					sendJsonData(response, JSON.toJSONString(withdrawfee));
					return;
				}
				withdrawfee = quota;
			}
			if (wdcMode.intValue() == 2) {// 比例
				Double rate = wdcRate.getWdcrate();// 手续费比例
				Double minFee = wdcRate.getMinfee();// 最小手续费比例
				Double maxFee = wdcRate.getMaxfee();// 最大手续费比例
				if (null == rate) {
					sendJsonData(response, JSON.toJSONString(withdrawfee));
					return;
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
				withdrawfee = fee;
			}
			sendJsonData(response, JSON.toJSONString(withdrawfee));
		}
		sendJsonData(response, JSON.toJSONString(withdrawfee));
	}

	/**
	 * 验证用户提现
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月26日 下午5:15:27
	 * @param money
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkDrawMoney")
	public void checkDrawMoney(Double money) throws Exception {
		if (null != money && money > 0) {
			UserBaseAccountInfo ubai = getUserBaseAccountInfo();
			if (ubai == null)
				return;
			BigDecimal id = ubai.getId();// baseid
			UserAccountSafeInfo uasi = uasiService.selectByBaseId(id);
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
					sendJsonData(response, JSON.toJSONString("提示：提现设置未设置，请联系客服。"));
					return;
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
				sendJsonData(response, JSON.toJSONString("提示：单笔提现金额范围：" + lowestmoney + "~" + highestmoney));
				return;
			}
			UserWithdrawsCash uwdCash = userWithdrawsCashService.selectCountAmountForId(id);
			int dayNum = uwdCash.getDaytotal();
			double dayAmount = uwdCash.getDayamount();
			if (dayNum >= daytimesrest.intValue()) {
				sendJsonData(response, JSON.toJSONString("提示：超出当日提现次数。"));
				return;
			}
			if (Arith.sub(daymoneyrest, dayAmount) < money) {
				sendJsonData(response, JSON.toJSONString("提示：超出当日提现额度。"));
				return;
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
					sendJsonData(response, JSON.toJSONString("提示：提现费率未设置，请联系客服。"));
					return;
				}
			}
			Short wdcMode = wdcRate.getWdcmode();// 收费类型
			if (wdcMode == null) {
				sendJsonData(response, JSON.toJSONString("提示：提现费率设置未完整，请联系客服。"));
				return;
			}
			if (wdcMode.intValue() == 1) {// 固定金额
				Double quota = wdcRate.getQuota();
				if (null == quota) {
					sendJsonData(response, JSON.toJSONString("提示：提现费率设置未完整，请联系客服。"));
					return;
				}
				request.getSession().setAttribute("moneyFee", quota);
			}
			if (wdcMode.intValue() == 2) {// 比例
				Double rate = wdcRate.getWdcrate();
				Double minFee = wdcRate.getMinfee();
				Double maxFee = wdcRate.getMaxfee();
				if (null == rate) {
					sendJsonData(response, JSON.toJSONString("提示：提现费率设置未完整，请联系客服。"));
					return;
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
				request.getSession().setAttribute("moneyFee", fee);
			}
			request.getSession().setAttribute("checkFlag", "pass");
			sendJsonData(response, JSON.toJSONString("checkPass"));
		}

	}

	/**
	 * 提现
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月27日 上午10:15:56
	 * @param amount
	 */
	@RequestMapping(value = "drawmoney")
	public synchronized void drawmoney(Double amount) {
		UserBaseAccountInfo userInfo = getUserBaseAccountInfo();
		if (userInfo != null) {
			String check = (String) request.getSession().getAttribute("checkFlag");
			Double fee = (Double) request.getSession().getAttribute("moneyFee");
			if (!"pass".equals(check))
				return;
			request.getSession().setAttribute("checkFlag", "");
			if (amount != null && amount > 0) {
				userWithdrawsCashService.withdrawsCash(request, response, amount, fee, userInfo, (short) 1);
			} else {
				try {
					response.sendRedirect("/user/tologin.action");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
