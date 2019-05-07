package com.ptpl.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.constant.UserWithdrawsCash_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 提现记录 Controller
 * 
 * @author xiaoy
 * @date 2016年5月27日 下午4:38:15
 */
@Controller
@RequestMapping("/admin/userWithdrawsCash")
@Scope("prototype")
public class UserWithdrawsCashManagerController extends BaseController {
	@Autowired
	UserWithdrawsCashServiceI userWithdrawsCashService;
	@Autowired
	UserAccountSafeInfoServiceI userAccountSafeInfoServiceImpl;

	/**
	 * 提现记录
	 * 
	 * @param userwdc
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll(UserWithdrawsCash userwdc) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserWithdrawsCash> userWithdrawsCashs = userWithdrawsCashService.selective(userwdc);
		for (UserWithdrawsCash cash : userWithdrawsCashs) {
			String username=cash.getUsername();
			if(StringUtils.isNotEmpty(username)){
			cash.setUsername(AES.getDecrypt(username));
			}
		}
		PageInfo<Object> pagehelper = initPagehelper(map, userWithdrawsCashs);
		ModelAndView mav = new ModelAndView("admin/userwithdrawscash/userwithdrawscashList");
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("backfilldata", userwdc);
		return mav;
	}
	/**
	 * 编辑修改（提现对账出现异常后）
	 * 
	 * @param uwc
	 * @throws Exception
	 */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// public void update(UserWithdrawsCash uwc) throws Exception
	// {
	// AdminUser admin = getAdminUser();
	// if (admin != null)
	// {
	// uwc.setIsexceptions((short) 0);
	// int iden = userWithdrawsCashService.updateByCashNo(uwc);
	// String data = null;
	// if (iden > 0)
	// {
	// data = "提示：编辑成功。";
	// } else
	// {
	// data = "提示：编辑失败。";
	// }
	// sendJsonData(response, data);
	// }
	// }

	/**
	 * 跳转详情，审核，编辑，取消页面
	 * 
	 * @param cashNo
	 *            订单编号
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/queryByCashNo", method = RequestMethod.POST)
	public ModelAndView queryDeatilsByCashNo(String cashNo) {
		AdminUser admin = getAdminUser();
		if (admin != null) {
			if (cashNo != null && !"".equals(cashNo)) {
				UserWithdrawsCash uwc = userWithdrawsCashService.selectByCashNo(cashNo);
				String bankNo = StringUtil.formatBankNo(AES.getDecrypt(uwc.getCardno()));
				/*
				 * 银行卡格式转换
				 */
				uwc.setCardno(bankNo);
				uwc.getUbai().setRealname(AES.getDecrypt(uwc.getUbai().getRealname()));
				ModelAndView mav = new ModelAndView();
				mav.addObject("uwc", uwc);
				mav.setViewName("admin/userwithdrawscash/userwithdrawscash_Detail");
				return mav;
			}
		}
		return null;
	}

	/**
	 * 跳转人工对账页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/artificialReconciliatio", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView artificialReconciliatio() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/userwithdrawscash/artificialreconciliatio");
		return mav;
	}
}
