package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.manager.UserGradeManagerController;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.model.UserGradeCash;
import com.ptpl.model.UserGradeExp;
import com.ptpl.model.UserUpgradeRecord;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeExpServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.UserUpgradeRecordServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 会员专区
 * 
 * @author xiaoy
 *
 * @date 2016年12月6日 上午10:34:31
 */
@Controller
@RequestMapping("user/userGrade")
@Scope("prototype")
public class UserGradeController extends BaseController {
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserGradeExpServiceI userGradeExpService;
	@Autowired
	private UserUpgradeRecordServiceI userUpgradeRecordService;

	/**
	 * 我的会员
	 * 
	 * @return
	 */
	@RequestMapping(value = "userGrade")
	private ModelAndView queryAll() {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccountSafeInfo uasi = getUserAccountSafeInfo();
		uasi = userAccountSafeInfoService.selectByBaseId(uasi.getBaseid());
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		String uGradeName = userGradeService.selectByUgrade(new BigDecimal(uasi.getUgrade())).getUgradename();
		List<UserUpgradeRecord> List = userUpgradeRecordService.selectByBaseID(ubai.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("userName", ubai.getLoginname());
		mav.addObject("bonusPoints", userAccount.getBonuspoints());
		mav.addObject("uGradeType", uasi.getUgradetype());
		mav.addObject("uGradeName", uGradeName);
		mav.addObject("list", List);
		mav.setViewName("user/userGrade/userGrade");
		return mav;
	}

	@RequestMapping(value = "buyGrade")
	private void buyGrade()
			throws Exception {/*
								 * UserBaseAccountInfo ubai =
								 * getUserBaseAccountInfo();
								 * System.out.println("baseid=" + ubai.getId());
								 * UserFsAccountInfo ufs = (UserFsAccountInfo)
								 * request.getSession()
								 * .getAttribute(Session_Constant.
								 * USERFSACCOUNTINFO); String URL = "http://" +
								 * request.getServerName() + ":" +
								 * request.getServerPort() +
								 * request.getContextPath(); try { UserGradeCash
								 * userGradeCash = new UserGradeCash();
								 * userGradeCash.setBgRetUrl(URL +
								 * "/huifu/userAccountPayment/bgcallback.action"
								 * ); userGradeCash.setMerPriv(ubai.getId() +
								 * "," + 2); userGradeCash.setTransAmt("12.12");
								 * userGradeCash.setUsrCustId(ufs.getUsrcustid()
								 * ); request.setAttribute("map",
								 * userGradeCash.getMap());
								 * request.getRequestDispatcher(
								 * "/WEB-INF/pages/UserGrade/buyGrade.jsp")
								 * .forward(request, response); } catch
								 * (Exception e) { e.printStackTrace(); }
								 */
		sendJsonData(response, JSON.toJSONString("fail"));
	}

	/**
	 * 跳转积分兑换会员界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "bonusPoints_UI")
	private ModelAndView bonusPoints_UI() {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		List<UserGrade> list = getUserGradeForRankNo();
		ModelAndView mav = new ModelAndView();
		mav.addObject("bonusPoints", userAccount.getBonuspoints());
		mav.setViewName("user/userGrade/bonusPoints");
		mav.addObject("list", list);
		return mav;
	}

	/**
	 * 获取会员排列序号大于当前用户会员排列序号大
	 * 
	 * @param uasi
	 * @return
	 */
	private List<UserGrade> getUserGradeForRankNo() {
		UserAccountSafeInfo uasi = getUserAccountSafeInfo();
		uasi = userAccountSafeInfoService.selectByBaseId(uasi.getBaseid());
		UserGrade ug = new UserGrade();
		ug.setUgrade(new BigDecimal(uasi.getUgrade()));
		ug = userGradeService.selective(ug).get(0);
		List<UserGrade> list = userGradeService.selectForBonusPointsAndCash(ug.getRankno());
		BigDecimal id = uasi.getBaseid();
		for (int i = 0; i < list.size(); i++) {
			boolean flag = false;
			UserGrade userGrade = list.get(i);
			if (userGrade.getIsspecify() == (short) 1) {
				Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.getUserForSNLID(userGrade.getSnlid());
				for (UserBaseAccountInfo ubai : ubaiList) {
					if (ubai.getId().equals(id)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					list.remove(userGrade);
					i--;
				}
			}
		}
		return list;
	}

	/**
	 * 按照排列序号获取所需系统积分
	 * 
	 * @param rankno
	 * @throws Exception
	 */
	@RequestMapping(value = "getNeedBonusPoints")
	private void getNeedBonusPoints(UserGrade userGrade) throws Exception {
		if (userGrade.getRankno() != null) {
			userGrade = userGradeService.selective(userGrade).get(0);
			BigDecimal needPoints = userGrade.getNeedpoints();
			sendJsonData(response, JSON.toJSONString(needPoints));
		}
	}

	/**
	 * 系统积分兑换会员等级
	 * 
	 * @param rankno
	 * @throws Exception
	 */
	@RequestMapping(value = "exchange")
	private void exchange(UserGrade userGrade) throws Exception {
		String data = "fail";
		if (userGrade.getRankno() != null) {
			UserAccountSafeInfo uasi = getUserAccountSafeInfo();
			uasi = userAccountSafeInfoService.selectByBaseId(uasi.getBaseid());
			// ID
			BigDecimal id = uasi.getBaseid();
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(id);
			// 查询所需积分
			userGrade = userGradeService.selective(userGrade).get(0);
			int needPoints = userGrade.getNeedpoints().intValue();

			// 是否定向升级
			short isSpecify = userGrade.getIsspecify();
			boolean flag = false;
			if (isSpecify == 1) {
				Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.getUserForSNLID(userGrade.getSnlid());
				for (UserBaseAccountInfo ubai : ubaiList) {
					if (ubai.getId().equals(id)) {
						flag = true;
						break;
					}
				}
			}
			// 不定向升级
			if (isSpecify == 2) {
				flag = true;
			}
			// flag=true,通过。可以升级
			if (flag) {
				if (needPoints <= userAccount.getBonuspoints().intValue()) {
					int grade = userGrade.getUgrade().intValue();
					int oldGrade = uasi.getUgrade().intValue();
					UserUpgradeRecord userUpgradeRecord = new UserUpgradeRecord();
					userUpgradeRecord.setBaseid(uasi.getBaseid());
					userUpgradeRecord.setBonuspoints(userAccount.getBonuspoints().longValue());
					userUpgradeRecord.setDeductbonuspoints(0L);
					userUpgradeRecord.setDealmode((short) 1);
					userUpgradeRecord.setDealtime(new Date());
					userUpgradeRecord.setGrade(grade);
					userUpgradeRecord.setPayamount(0d);
					userUpgradeRecord.setPaytype((short) 2);
					userUpgradeRecord.setOldgrade(oldGrade);
					userUpgradeRecord.setRemark(oldGrade + "级升级到" + grade + "级");
					int iden1 = userUpgradeRecordService.insertSelective(userUpgradeRecord);
					uasi.setUgrade((short) grade);
					uasi.setUgradetype((short) 1);
					int iden2 = userAccountSafeInfoService.update(uasi);
					if (iden1 > 0 && iden2 > 0)
						data = "success";
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转现金购买会员界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "cash_UI")
	private ModelAndView cash_UI() {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		// UserAccountSafeInfo uasi = getUserAccountSafeInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("showAvlBalance", df.format(userAccount.getAvlbalance()));
		mav.addObject("avlBalance", userAccount.getAvlbalance());
		mav.setViewName("user/userGrade/cash");
		return mav;
	}

	/**
	 * 获取普通会员等级
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "getNormalUgrade")
	private void getNormalUgrade() throws Exception {
		List<UserGrade> list = getUserGradeForRankNo();
		sendJsonData(response, JSON.toJSONString(list));
	}

	/**
	 * 获取体验会员等级
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "getTasteUgrade")
	private void getTasteUgrade() throws Exception {
		UserAccountSafeInfo uasi = getUserAccountSafeInfo();
		uasi = userAccountSafeInfoService.selectByBaseId(uasi.getBaseid());
		UserGrade userGrade = new UserGrade();
		userGrade.setUgrade(new BigDecimal(uasi.getUgrade()));
		short rankNo = userGradeService.selective(userGrade).get(0).getRankno();
		BigDecimal id = uasi.getBaseid();
		List<UserGradeExp> list = userGradeExpService.selectForUgrade(new BigDecimal(rankNo));
		for (int i = 0; i < list.size(); i++) {
			boolean flag = false;
			UserGradeExp uge = list.get(i);
			if (uge.getIsexpspecific() == (short) 1) {
				Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.getUserForSNLID(uge.getExpsnlid());
				for (UserBaseAccountInfo ubai : ubaiList) {
					if (ubai.getId().equals(id)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					list.remove(uge);
					i--;
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(list));
	}

	/**
	 * 获取兑换会员所需的金额
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "getNeedAmount")
	private void getNeedAmount(String uGradeType, UserGrade userGrade) throws Exception {
		if (StringUtil.isNotEmpty(uGradeType) && userGrade != null) {
			double amount = 0d;
			if (uGradeType.equals("1")) {
				userGrade = userGradeService.selectiveForNormal(userGrade).get(0);
				amount = userGrade.getNeedamount();
			}
			if (uGradeType.equals("2")) {
				UserGradeExp userGradeExp = new UserGradeExp();
				userGradeExp.setUserGrade(userGrade);
				userGradeExp = userGradeExpService.selective(userGradeExp).get(0);
				amount = userGradeExp.getAmount();
			}
			sendJsonData(response, JSON.toJSONString(amount));
		}
	}

	/**
	 * 现金购买会员
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "cash")
	private void cash(String uGradeType, UserGrade userGrade) throws Exception {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		BigDecimal id = ubai.getId();
		Short rankNo = userGrade.getRankno();
		if (StringUtil.isNotEmpty(uGradeType) && rankNo != null) {
			/*
			 * 先根据会员类型，排列序号查询 。会员升级所需金额
			 */
			double amount = 0d;
			boolean flag = false;
			// 普通会员
			if (uGradeType.equals("1")) {
				userGrade = userGradeService.selective(userGrade).get(0);
				amount = userGrade.getNeedamount();
				short isSpecify = userGrade.getIsspecify();
				if (isSpecify == 1) {
					Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService
							.getUserForSNLID(userGrade.getSnlid());
					for (UserBaseAccountInfo userbi : ubaiList) {
						if (userbi.getId().equals(id)) {
							flag = true;
							break;
						}
					}
				}
				if (isSpecify == 2) {
					flag = true;
				}
			}
			// 体验会员
			if (uGradeType.equals("2")) {
				UserGradeExp userGradeExp = new UserGradeExp();
				userGradeExp.setUserGrade(userGrade);
				userGradeExp = userGradeExpService.selective(userGradeExp).get(0);
				amount = userGradeExp.getAmount();
				short isExpSpecific = userGradeExp.getIsexpspecific();
				if (isExpSpecific == 1) {
					Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService
							.getUserForSNLID(userGradeExp.getExpsnlid());
					for (UserBaseAccountInfo userbi : ubaiList) {
						if (userbi.getId().equals(id)) {
							flag = true;
							break;
						}
					}
				}
				if (isExpSpecific == 2) {
					flag = true;
				}
			}
			if (flag) {
				UserFsAccountInfo ufs = (UserFsAccountInfo) request.getSession()
						.getAttribute(Session_Constant.USERFSACCOUNTINFO);
				UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
				if (userAccount.getAvlbalance() >= amount) {
					String URL = "http://" + request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					try {
						UserGradeCash userGradeCash = new UserGradeCash();
						userGradeCash.setBgRetUrl(URL + "/huifu/userAccountPayment/bgcallback.action");
						userGradeCash.setMerPriv(ubai.getId() + "," + rankNo + "," + uGradeType);
						userGradeCash.setTransAmt(df.format(amount));
						userGradeCash.setUsrCustId(ufs.getUsrcustid());
						request.setAttribute("map", userGradeCash.getMap());
						request.getRequestDispatcher("/WEB-INF/pages/UserGrade/buyGrade.jsp").forward(request,
								response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					sendJsonData(response, JSON.toJSONString("fail"));
				}
			}
		}
	}

	@RequestMapping(value = "backGrade")
	private void backGrade() throws Exception {
		String data = "fail";
		UserAccountSafeInfo uasi = getUserAccountSafeInfo();
		uasi = userAccountSafeInfoService.selectByBaseId(uasi.getBaseid());
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(uasi.getBaseid());
		short type = uasi.getUgradetype();
		if (type == 2) {
			/*
			 * 用户会员等级记录
			 */
			int grade = userUpgradeRecordService.selectOlduGradeByBaseID(uasi.getBaseid()).get(0).getGrade();
			UserUpgradeRecord record = new UserUpgradeRecord();
			record.setBaseid(uasi.getBaseid());
			record.setBonuspoints(userAccount.getBonuspoints().longValue());
			record.setDeductbonuspoints(0L);
			record.setDealmode((short) 3);
			record.setDealtime(new Date());
			record.setGrade(grade);
			record.setPayamount(0d);
			record.setPaytype((short) 3);
			record.setOldgrade(new Integer(uasi.getUgrade()));
			record.setRemark("用户手动停止体验会员");
			int iden1 = userUpgradeRecordService.insertSelective(record);
			/*
			 * 用户会员等级
			 */
			uasi.setUgradetype((short) 1);
			uasi.setUgrade(new Short((short) grade));
			int iden2 = userAccountSafeInfoService.update(uasi);
			if (iden1 > 0 && iden2 > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
}
