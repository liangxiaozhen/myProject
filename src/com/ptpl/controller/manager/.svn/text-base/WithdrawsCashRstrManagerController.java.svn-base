package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.WithdrawsCashRstr_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.CloseTime;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialTime;
import com.ptpl.model.UserGrade;
import com.ptpl.model.WithdrawsCashRstr;
import com.ptpl.service.CloseTimeServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialTimeServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WithdrawsCashRstrServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 提现限制设置
 * 
 * @author xiaoy
 *
 * @date 2016年7月13日 下午1:54:10
 */
@Controller
@RequestMapping("/admin/withdrawsCashRstr")
@Scope("prototype")
public class WithdrawsCashRstrManagerController extends BaseController {
	@Autowired
	private WithdrawsCashRstrServiceI withdrawsCashRstrService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private CloseTimeServiceI closeTimeService;
	@Autowired
	private SpecialTimeServiceI specialTimeService;

	/**
	 * 提现设置记录
	 * 
	 * @param wdcRstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllwdcRstr(WithdrawsCashRstr wdcRstr) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<WithdrawsCashRstr> wdcRstrs = withdrawsCashRstrService.selective(wdcRstr);
		List<UserGrade> uGrades = userGradeService.getAll(null);
		PageInfo<Object> pagehelper = initPagehelper(map, wdcRstrs);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("wdcRstr", wdcRstr);
		mav.addObject("ugrades", uGrades);
		mav.setViewName("admin/withdrawscashrstr/withdrawsCashRstrList");
		return mav;
	}

	/**
	 * 根据ID 查询记录
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryObject(BigDecimal id) {
		/*
		 * 名单列表
		 * 
		 * if (uid == WithdrawsCashRstr_Constant.DETAIL || uid ==
		 * WithdrawsCashRstr_Constant.AUDIT) { String nameNos =
		 * wdcRstr.getRemovenameno(); if (nameNos != null &&
		 * !"".equals(nameNos)) { List removeList = new ArrayList(); String[]
		 * rNameNos = nameNos.split(","); for (String nameno : rNameNos) {
		 * RemoveName removeName = new RemoveName();
		 * removeName.setNameno(nameno); List<RemoveName> rNames =
		 * removeNameService.selective(removeName); if (rNames.size() > 0) {
		 * removeList.add(rNames); } } mav.addObject("rNames", removeList); }
		 * //关闭时间 String cTimeNos = wdcRstr.getClosetimeno(); if (cTimeNos !=
		 * null && !"".equals(cTimeNos)) { List cTimeList = new ArrayList();
		 * String[] timeNos = cTimeNos.split(","); for (String timeNo : timeNos)
		 * { CloseTime closeTime = new CloseTime(); closeTime.setTimeno(timeNo);
		 * List<CloseTime> cTimes = closeTimeService.selective(closeTime); if
		 * (cTimes.size() > 0) { cTimeList.add(cTimes); } }
		 * mav.addObject("cTimes", cTimeList); } //特定时间 String sTimeNos =
		 * wdcRstr.getSpecialtimeno(); if (sTimeNos != null &&
		 * !"".equals(sTimeNos)) { List sTimeList = new ArrayList(); String[]
		 * timeNos = sTimeNos.split(","); for (String timeNo : timeNos) {
		 * SpecialTime sTime = new SpecialTime(); sTime.setTimeno(timeNo);
		 * List<SpecialTime> sTimes = specialTimeService.selective(sTime); if
		 * (sTimes.size() > 0) { sTimeList.add(sTimes); } }
		 * mav.addObject("sTimes", sTimeList); } }
		 */
		/*
		 * 编辑
		 * 
		 * if (uid == WithdrawsCashRstr_Constant.UPDATE) { List<RemoveName>
		 * rNameList = removeNameService.getRemoveName();
		 * mav.addObject("rNames", rNameList); List<CloseTime> cTimeList =
		 * closeTimeService.selectTimeNameAndTimeNo(); mav.addObject("cTimes",
		 * cTimeList); List<SpecialTime> sTimeList =
		 * specialTimeService.selectTNameTNoTType(); mav.addObject("sTimes",
		 * sTimeList); List<UserGrade> uGrades = userGradeService.getAll(null);
		 * mav.addObject("ugrades", uGrades); //编辑时，封装显示会员等级所需数据 String[]
		 * ugradeArr = StringUtil.getPlaceholderArrForShow(wdcRstr.getUgrade());
		 * String showUgrade = ""; for (String str : ugradeArr) { showUgrade +=
		 * str + ","; } wdcRstr.setUgrade(showUgrade.substring(0,
		 * showUgrade.length() - 1)); }
		 */
		WithdrawsCashRstr wdcRstr = withdrawsCashRstrService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("wdcRstr", wdcRstr);
		mav.setViewName("admin/withdrawscashrstr/withdrawsCashRstr_Detail");
		return mav;
	}

	/**
	 * 跳转 删除UI
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/del_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView del_UI(BigDecimal id) {
		if (null != id) {
			WithdrawsCashRstr wdcRstr = withdrawsCashRstrService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("wdcRstr", wdcRstr);
			mav.setViewName("admin/withdrawscashrstr/delete_withdrawsCashRstr");
			return mav;
		}
		return null;
	}

	/**
	 * 删除提现限制设置
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteById(BigDecimal id) throws Exception {
		String data = "提示：删除失败。";
		if (null != id) {
			int iden = withdrawsCashRstrService.deleteByPrimaryKey(id);
			if (iden > 0) {
				data = "提示：删除成功。";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 新增页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert_UI")
	public ModelAndView insertUI() {
		// List<RemoveName> rNameList = removeNameService.getRemoveName();
		// List<CloseTime> cTimeList =
		// closeTimeService.selectTimeNameAndTimeNo();
		// List<SpecialTime> sTimeList =
		// specialTimeService.selectTNameTNoTType();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/withdrawscashrstr/insert_withdrawsCashRstr");
		mav.addObject("uGrades", uGrades);
		// mav.addObject("rNames", rNameList);
		// mav.addObject("cTimes", cTimeList);
		// mav.addObject("sTimes", sTimeList);
		return mav;
	}

	/**
	 * 验证审核
	 * 
	 * @param ugrade
	 *            当前记录会员等级ugrade
	 * @param ugradeList
	 *            所有审核通过的会员等级
	 * @return
	 * 
	 * 		private int checkAudit(String ugrade, List<String> ugradeList) {
	 *         // 当前会员等级包含的下标 String[] arr =
	 *         StringUtil.getPlaceholderArr(ugrade); for (String str0 :
	 *         ugradeList) { // 审核通过的会员等级的下标 String[] arr1 =
	 *         StringUtil.getPlaceholderArr(str0); for (String str : arr) { for
	 *         (String str1 : arr1) { // 如果两者下标有相同的，返回false if
	 *         (str.equals(str1)) { return Integer.valueOf(str); } } } } return
	 *         -1; }
	 */
	/**
	 * 保存提现限制设置
	 * 
	 * @param wdcRstr
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertWdcRstr(WithdrawsCashRstr wdcRstr) throws Exception {
		Short ugrade = wdcRstr.getUgrade();// 会员等级
		Double minMoney = wdcRstr.getLowestmoney(); // 单笔最低可提现金额
		Double maxMoney = wdcRstr.getHighestmoney();// 单笔最高可提现金额
		Double dayMoneyRest = wdcRstr.getDaymoneyrest(); // 日提现金额限制
		Integer dayTimesRest = wdcRstr.getDaytimesrest();// 日提现次数限制
		if (null == ugrade) {
			sendJsonData(response, JSON.toJSONString("提示：请选择会员等级。"));
			return;
		}
		if (null == minMoney) {
			sendJsonData(response, JSON.toJSONString("提示：请输入单笔最低可提现金额。"));
			return;
		}
		if (null == maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示：请输入单笔最高可提现金额。"));
			return;
		}
		if (null == dayMoneyRest) {
			sendJsonData(response, JSON.toJSONString("提示：请输入日提现金额限制。"));
			return;
		}
		if (null == dayTimesRest) {
			sendJsonData(response, JSON.toJSONString("提示：请输入日提现次数限制。"));
			return;
		}

		boolean sizeFlag = withdrawsCashRstrService.getCount() == 0 ? true : false;
		if (!sizeFlag) {
			if (99 == ugrade.intValue()) {
				sendJsonData(response, JSON.toJSONString("提示：全部会员等级或单个会员等级设置已存在。"));
				return;
			}
			WithdrawsCashRstr rstr = new WithdrawsCashRstr();
			rstr.setUgrade(ugrade);
			boolean flag = withdrawsCashRstrService.selective(rstr).size() == 0 ? true : false;
			if (!flag) {
				sendJsonData(response, JSON.toJSONString("提示：此会员等级设置已存在。"));
				return;
			}
		}
		AdminUser adminUser = getAdminUser();
		wdcRstr.setAddtime(new Date());
		wdcRstr.setAddman(adminUser.getUsername());
		wdcRstr.setIscancel((short) 2);
		String data = "提示：操作失败。";
		int iden = withdrawsCashRstrService.insertSelective(wdcRstr);
		if (iden > 0) {
			data = "提示：操作成功。";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转编辑界面
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月26日 上午9:38:31
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update_UI")
	public ModelAndView update_UI(BigDecimal id) {
		if (id != null) {
			WithdrawsCashRstr wdcRstr = withdrawsCashRstrService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/withdrawscashrstr/update_withdrawsCashRstr");
			mav.addObject("wdcRstr", wdcRstr);
			return mav;
		}
		return null;
	}

	/**
	 * 编辑
	 * 
	 * @param wdcRstr
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public void update(WithdrawsCashRstr wdcRstr) throws Exception {
		wdcRstr.setUgrade(null);
		Double minMoney = wdcRstr.getLowestmoney(); // 单笔最低可提现金额
		Double maxMoney = wdcRstr.getHighestmoney();// 单笔最高可提现金额
		Double dayMoneyRest = wdcRstr.getDaymoneyrest(); // 日提现金额限制
		Integer dayTimesRest = wdcRstr.getDaytimesrest();// 日提现次数限制
		if (null == minMoney) {
			sendJsonData(response, JSON.toJSONString("提示：请输入单笔最低可提现金额。"));
			return;
		}
		if (null == maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示：请输入单笔最高可提现金额。"));
			return;
		}
		if (null == dayMoneyRest) {
			sendJsonData(response, JSON.toJSONString("提示：请输入日提现金额限制。"));
			return;
		}
		if (null == dayTimesRest) {
			sendJsonData(response, JSON.toJSONString("提示：请输入日提现次数限制。"));
			return;
		}
		String data = "提示：编辑失败。";
		AdminUser adminUser = getAdminUser();
		wdcRstr.setAddtime(new Date());
		wdcRstr.setAddman(adminUser.getUsername());
		int iden = withdrawsCashRstrService.updateByPrimaryKeySelective(wdcRstr);
		if (iden > 0) {
			data = "提示：编辑成功。";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 获取特定时间
	 * 
	 * @param wdcRstr
	 * @return
	 * 
	 * 		private String getSTimeNos(WithdrawsCashRstr wdcRstr) { String
	 *         stimeno = wdcRstr.getSpecialtimeno(); String stimenos =
	 *         wdcRstr.getStimenos() == null ? "" : wdcRstr.getStimenos();
	 *         switch (stimeno) { case "1": List<SpecialTime> sTimeList =
	 *         specialTimeService.selectTimeNoAndTimeName(); for (SpecialTime
	 *         stime : sTimeList) { stimenos += stime.getTimeno() + ","; }
	 *         stimenos = stimenos.substring(0, stimenos.length() - 1); break;
	 *         case "3": stimenos = ""; break; } return stimenos; }
	 */

	/**
	 * 获取关闭时间
	 * 
	 * @param wdcRstr
	 * @return
	 * 
	 * 		private String getCTimeNos(WithdrawsCashRstr wdcRstr) { String
	 *         ctimeno = wdcRstr.getClosetimeno(); String ctimenos =
	 *         wdcRstr.getCtimenos() == null ? "" : wdcRstr.getCtimenos();
	 *         switch (ctimeno) { case "1": List<CloseTime> cTimeList =
	 *         closeTimeService.selectTimeNameAndTimeNo(); for (CloseTime ctime
	 *         : cTimeList) { ctimenos += ctime.getTimeno() + ","; } ctimenos =
	 *         ctimenos.substring(0, ctimenos.length() - 1); break; case "3":
	 *         ctimenos = ""; break; } return ctimenos; }
	 */

	/**
	 * 获取人员名单编号
	 * 
	 * @param wdcRstr
	 * @return
	 * 
	 * 		private String getRNameNos(WithdrawsCashRstr wdcRstr) { String
	 *         rnameno = wdcRstr.getRemovenameno(); String rnamenos =
	 *         wdcRstr.getRnamenos() == null ? "" : wdcRstr.getRnamenos();
	 *         switch (rnameno) { case "1": List<RemoveName> rNameList =
	 *         removeNameService.getRemoveName(); for (RemoveName rname :
	 *         rNameList) { rnamenos += rname.getNameno() + ","; } rnamenos =
	 *         rnamenos.substring(0, rnamenos.length() - 1); break; case "3":
	 *         rnamenos = ""; break; } return rnamenos; }
	 */

	/**
	 * 获取会员等级字符串
	 * 
	 * @param wdcRstr
	 * @return
	 * 
	 * 		private String getUgradeStr(WithdrawsCashRstr wdcRstr) { String
	 *         ugrade = wdcRstr.getUgrade(); String ugrades =
	 *         wdcRstr.getUgrades(); // 1 全部会员等级 2 选择会员等级 switch (ugrade) { case
	 *         "1": List<UserGrade> uGrades = userGradeService.getAll(null);
	 *         ugrade = StringUtil.getPlaceholder(30); for (UserGrade uGrade :
	 *         uGrades) { ugrade = StringUtil.setPlaceholder(ugrade,
	 *         uGrade.getUgrade().intValue() - 1); } break; case "2": String[]
	 *         uArr = ugrades.split(","); ugrade =
	 *         StringUtil.setPlaceholderForArr(uArr, 30); break; } return
	 *         ugrade; }
	 */
}
