package com.ptpl.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.StateUtils;

@Controller
@RequestMapping(value = "/user/userAccInExRecord")
public class UserAccInExRecordController extends BaseController {
	@Autowired
	private AccInExRecordServiceI accInExRecordService;

	@Autowired
	private UserAccountServiceI userAccountService;

	@Autowired
	private UserWithdrawsCashServiceI userWtihdrawsCashService;

	@RequestMapping(value = "/list")
	public ModelAndView userAccInExRecord(AccInExRecord record) {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		record.setBaseid(ubai.getId());
		// 当前页面，每页条数
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = (String) request.getParameter("startdate");
		String enddate = (String) request.getParameter("enddate");
		try {
			if (StringUtils.isNotEmpty(startdate))
				record.setBtime(sf2.parse(startdate));
			if (StringUtils.isNotEmpty(enddate))
				record.setEtime(sf2.parse(enddate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<AccInExRecord> list = accInExRecordService.queryAllUser(record);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		// 可用余额
		String avlBalance = df.format(userAccount.getAvlbalance());
		String avlBalanceA = avlBalance.substring(0, avlBalance.length() - 2);
		String avlBalanceB = avlBalance.substring(avlBalance.length() - 2);
		// 冻结余额
		String freezeBalance = df.format(userAccount.getFreezebalance());
		String freezeBalanceA = freezeBalance.substring(0, freezeBalance.length() - 2);
		String freezeBalanceB = freezeBalance.substring(freezeBalance.length() - 2);

		ModelAndView mav = new ModelAndView();
		mav.addObject("avlBalanceA", avlBalanceA);
		mav.addObject("avlBalanceB", avlBalanceB);
		mav.addObject("freezeBalanceA", freezeBalanceA);
		mav.addObject("freezeBalanceB", freezeBalanceB);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("startdate", startdate);
		mav.addObject("state", state);
		mav.addObject("enddate", enddate);
		mav.addObject("sign", record.getSign());
		mav.addObject("type", record.getType());
		mav.setViewName("/user/manager/useraccinexrecord/useraccinexrecord");
		return mav;
	}

	@RequestMapping(value = "/carryLog")
	public ModelAndView withdrawLog(UserWithdrawsCash uwdCash) {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		uwdCash.setBaseid(ubai.getId());
		// 当前页面，每页条数
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = (String) request.getParameter("startdate");
		String enddate = (String) request.getParameter("enddate");
		
		try {
			if (StringUtils.isNotEmpty(startdate))
				uwdCash.setStartdate(sf2.parse(startdate));
			if (StringUtils.isNotEmpty(enddate))
				uwdCash.setEnddate(sf2.parse(enddate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserWithdrawsCash> list = userWtihdrawsCashService.getUseWithdrawsCashNote(uwdCash);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		// 可用余额
		String avlBalance = df.format(userAccount.getAvlbalance());
		String avlBalanceA = avlBalance.substring(0, avlBalance.length() - 2);
		String avlBalanceB = avlBalance.substring(avlBalance.length() - 2);
		// 冻结余额
		String freezeBalance = df.format(userAccount.getFreezebalance());
		String freezeBalanceA = freezeBalance.substring(0, freezeBalance.length() - 2);
		String freezeBalanceB = freezeBalance.substring(freezeBalance.length() - 2);

		ModelAndView mav = new ModelAndView();
		mav.addObject("avlBalanceA", avlBalanceA);
		mav.addObject("avlBalanceB", avlBalanceB);
		mav.addObject("freezeBalanceA", freezeBalanceA);
		mav.addObject("freezeBalanceB", freezeBalanceB);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("startdate", startdate);
		mav.addObject("enddate", enddate);
		mav.addObject("sign", uwdCash.getSign());
		mav.addObject("status", uwdCash.getStatus());
		mav.setViewName("/user/manager/useraccinexrecord/carryLog");
		return mav;
	}

	/**
	 * 用户查看资金明细
	 * 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll(HttpServletRequest request, HttpServletResponse response, AccInExRecord record) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		ModelAndView mav = new ModelAndView();
		UserBaseAccountInfo ubai = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		record.setBaseid(ubai.getId());
		record.setType(record.getType() == null ? null : record.getType() == 0 ? null : record.getType());
		List<AccInExRecord> list = accInExRecordService.queryAllUser(record);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		mav.addObject("df", df);
		mav.addObject("state", state);
		mav.addObject("type", record.getType() == null ? 0 : record.getType());
		mav.addObject("btime", record.getBtime());
		mav.addObject("etime", record.getEtime());
		mav.addObject("pagehelper", pagehelper);
		mav.setViewName("user/account/accInExRecord");
		return mav;
	}
}
