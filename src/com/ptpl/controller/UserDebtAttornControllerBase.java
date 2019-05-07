package com.ptpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.model.UserTender;
import com.ptpl.service.DebtAttornBuyerServiceI;
import com.ptpl.service.DebtAttornFeeServiceI;
import com.ptpl.service.DebtAttornServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;

public class UserDebtAttornControllerBase extends BaseController{
	@Autowired
	DebtAttornServiceI debtAttornService;
	@Autowired
	UserDebtAttornServiceI userdebtattornService;
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	UserTenderServiceI userTenderService;
	@Autowired
	RepayMentServiceI repayMentService;
	@Autowired
	TenderItemServiceI tenderItemService;
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	@Autowired
	DebtAttornFeeServiceI debtAttornFeeService;
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	UserAccountServiceI userAccountService;
	@Autowired
	DebtAttornServiceI dAttornService;
	@Autowired
	DebtAttornBuyerServiceI debtAttornBuyerService;
	/**
	 * 比对相同的对象
	 * @param @param orderno
	 * @param @return
	 * @return UserTender
	 * @author jiangxueyou
	 */
	@SuppressWarnings("unchecked")
	public UserTender querySameObject(String orderno) {
		UserTender userTender = null;
		List<UserTender> tenderList = (List<UserTender>) request.getSession().getAttribute("tenderList2");// 从session中取到userdebtrron方法中存入的值
		for (UserTender userTender2 : tenderList) {
			if (orderno.equals(userTender2.getOrderno())) {
				userTender = userTender2;
				break;
			}
		}
		return userTender;
	}
}
