package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.WithdrawsCashRate_Constant;
import com.ptpl.constant.WithdrawsCashRstr_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserGrade;
import com.ptpl.model.WithdrawsCashRate;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WithdrawsCashRateServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 提现费率
 * 
 * @author xiaoy
 *
 * @date 2016年5月31日 上午10:38:09
 */
@Controller
@RequestMapping("/admin/withdrawsCashRate")
@Scope("prototype")
public class WithdrawsCashRateManagerController extends BaseController {

	@Autowired
	private WithdrawsCashRateServiceI withdrawsCashRateService;
	@Autowired
	private UserGradeServiceI userGradeService;

	/**
	 * 查询提现费率设置
	 * 
	 * @param wdcRate
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllWithdrawsCashRate(WithdrawsCashRate wdcRate) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<WithdrawsCashRate> udcRates = withdrawsCashRateService.selective(wdcRate);
		PageInfo<Object> pagehelper = initPagehelper(map, udcRates);
		List<UserGrade> uGradeList = userGradeService.getAll(null);
		ModelAndView mav = new ModelAndView("admin/withdrawscashrate/withdrawscashrateList");
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("ugrades", uGradeList);
		mav.addObject("wdcRate", wdcRate);
		return mav;
	}

	/**
	 * 新增UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addWithdrawsCashRateUI() {
		List<UserGrade> uGrades = userGradeService.getAll(null);
		ModelAndView mav = new ModelAndView("admin/withdrawscashrate/insert_withdrawscashrate");
		mav.addObject("uGrades", uGrades);
		return mav;
	}

	/**
	 * 保存提现费率设置
	 * 
	 * @param wdcRate
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
	public void addWithdrawsCashRate(WithdrawsCashRate wdcRate) throws Exception {
		Double minMoney = wdcRate.getMinmoney();// 分段金额 最小金额
		Double maxMoney = wdcRate.getMaxmoney();// 分段金额 最大金额
		Short wdcMode = wdcRate.getWdcmode(); // 收费类型 1定额 2比例
		Short uGrade = wdcRate.getUgrade(); // 会员等级
		if (null == uGrade) {
			sendJsonData(response, JSON.toJSONString("提示，请选择会员等级。"));
			return;
		}
		if (null == minMoney || null == maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示，请完整填写金额范围。"));
			return;
		}
		if (minMoney >= maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示，金额范围不符合规范。"));
			return;
		}
		if (null == wdcMode) {
			sendJsonData(response, JSON.toJSONString("提示，请选择手续费类型。"));
			return;
		}
		if (wdcMode.shortValue() == 1) {// 定额
			Double quota = wdcRate.getQuota();// 固定金额
			if (null == quota) {
				sendJsonData(response, JSON.toJSONString("提示，请填写固定金额。"));
				return;
			}
		}
		if (wdcMode.shortValue() == 2) {// 比例
			Double rate = wdcRate.getWdcrate();// 提现费率
			if (null == rate) {
				sendJsonData(response, JSON.toJSONString("提示，请填写提现费率。"));
				return;
			}
			if (rate < 0 || rate > 100) {
				sendJsonData(response, JSON.toJSONString("提示，提现费率正确范围 0~100。"));
				return;
			}
		}
		/*
		 * 校验是否有全部等级
		 */
		// 当前存在的记录
		boolean countFlag = withdrawsCashRateService.getCount() == 0 ? true : false;
		boolean allFlag = 99 == uGrade.intValue();
		if (!countFlag) {
			WithdrawsCashRate rate = new WithdrawsCashRate();
			rate.setUgrade((short) 99);
			boolean sizeFlag = withdrawsCashRateService.selective(rate).size() > 0 ? true : false;
			if (allFlag && !sizeFlag) {
				sendJsonData(response, JSON.toJSONString("提示，已设置单个会员等级，如需设置全部会员等级，请删除所有单个会员等级。"));
				return;
			}
			if (!allFlag && sizeFlag) {
				sendJsonData(response, JSON.toJSONString("提示，已设置全部会员等级，如需设置单个会员等级，请删除全部会员等级。"));
				return;
			}
		}

		String data = "提示：操作失败。";
		WithdrawsCashRate seletive = new WithdrawsCashRate();// 查询对比条件
		seletive.setUgrade(uGrade);
		List<WithdrawsCashRate> list = withdrawsCashRateService.selective(seletive);
		boolean flag = true;
		if (list.size() > 0) {
			for (WithdrawsCashRate rate : list) {
				Double minMoney1 = rate.getMinmoney();
				Double maxMoney1 = rate.getMaxmoney();
				if (!(maxMoney < minMoney1 || minMoney > maxMoney1)) {
					flag = false;
					if (allFlag) {
						data = "提示：全部会员等级" + ", 金额区间：" + minMoney1 + "-" + maxMoney1 + " 已设置。";
					}
					if (!allFlag) {
						String ugradeName = userGradeService.getCodeByUserGradeName(new BigDecimal(uGrade));
						data = "提示：" + ugradeName + ", 金额区间：" + minMoney1 + "-" + maxMoney1 + " 已设置。";
					}
					break;
				}
			}
		}
		if (flag) {
			AdminUser user = getAdminUser();// 获取当前管理员对象
			wdcRate.setAddman(user.getUsername());// 设置人用户名
			wdcRate.setAddtime(new Date());// 设置时间
			int iden = withdrawsCashRateService.insertSelective(wdcRate);
			if (iden > 0) {
				data = "提示：操作成功。";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 更新记录
	 * 
	 * @param wdcRate
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public void update(WithdrawsCashRate wdcRate) throws Exception {
		Double minMoney = wdcRate.getMinmoney();// 分段金额 最小金额
		Double maxMoney = wdcRate.getMaxmoney();// 分段金额 最大金额
		Short wdcMode = wdcRate.getWdcmode(); // 收费类型 1定额 2比例
		Short uGrade = wdcRate.getUgrade(); // 会员等级
		if (null == minMoney || null == maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示，请完整填写金额范围。"));
			return;
		}
		if (minMoney >= maxMoney) {
			sendJsonData(response, JSON.toJSONString("提示，金额范围不符合规范。"));
			return;
		}
		if (null == wdcMode) {
			sendJsonData(response, JSON.toJSONString("提示，请选择手续费类型。"));
			return;
		}
		if (wdcMode.shortValue() == 1) {// 定额
			Double quota = wdcRate.getQuota();// 固定金额
			wdcRate.setMaxfee(null);
			wdcRate.setMinfee(null);
			wdcRate.setWdcrate(null);
			if (null == quota) {
				sendJsonData(response, JSON.toJSONString("提示，请填写固定金额。"));
				return;
			}
		}
		if (wdcMode.shortValue() == 2) {// 比例
			Double rate = wdcRate.getWdcrate();// 提现费率
			wdcRate.setQuota(null);
			if (null == rate) {
				sendJsonData(response, JSON.toJSONString("提示，请填写提现费率。"));
				return;
			}
			if (rate < 0 || rate > 100) {
				sendJsonData(response, JSON.toJSONString("提示，提现费率正确范围 0~100。"));
				return;
			}
		}

		String data = "提示：操作失败。";
		WithdrawsCashRate seletive = new WithdrawsCashRate();// 查询对比条件
		seletive.setUgrade(uGrade);
		List<WithdrawsCashRate> list = withdrawsCashRateService.selective(seletive);
		boolean allFlag = 99 == uGrade.intValue();
		boolean flag = true;
		if (list.size() > 0) {
			for (WithdrawsCashRate rate : list) {
				if (!rate.getId().equals(wdcRate.getId())) {
					Double minMoney1 = rate.getMinmoney();
					Double maxMoney1 = rate.getMaxmoney();
					if (!(maxMoney < minMoney1 || minMoney > maxMoney1)) {
						flag = false;
						if (allFlag) {
							data = "提示：全部会员等级" + ", 金额区间：" + minMoney1 + "-" + maxMoney1 + " 已设置。";
						}
						if (!allFlag) {
							String ugradeName = userGradeService.getCodeByUserGradeName(new BigDecimal(uGrade));
							data = "提示：" + ugradeName + ", 金额区间：" + minMoney1 + "-" + maxMoney1 + " 已设置。";
						}
						break;
					}
				}
			}
		}
		if (flag) {
			AdminUser user = getAdminUser();
			wdcRate.setAddman(user.getUsername());
			wdcRate.setAddtime(new Date());
			int iden = withdrawsCashRateService.updateByPrimaryKeySelective(wdcRate);
			if (iden > 0) {
				data = "提示：编辑成功。";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 根据ID 查询记录
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryById")
	public ModelAndView queryObject(BigDecimal id) {
		if (id != null) {
			WithdrawsCashRate wdcRate = withdrawsCashRateService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("wdcRate", wdcRate);
			mav.setViewName("admin/withdrawscashrate/withdrawscashrate_Detail");
			return mav;
		}
		return null;
	}

	/**
	 * 跳转编辑UI
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月21日 上午10:39:39
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update_UI")
	public ModelAndView update_UI(BigDecimal id) {
		if (null != id) {
			ModelAndView mav = new ModelAndView();
			WithdrawsCashRate wdcRate = withdrawsCashRateService.selectByPrimaryKey(id);
			mav.addObject("wdcRate", wdcRate);
			mav.setViewName("admin/withdrawscashrate/update_withdrawscashrate");
			return mav;
		}
		return null;
	}

	/**
	 * 跳转 删除UI
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/del_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView del_UI(BigDecimal id) throws Exception {
		if (id != null) {
			ModelAndView mav = new ModelAndView();
			WithdrawsCashRate wdcRate = withdrawsCashRateService.selectByPrimaryKey(id);
			mav.addObject("wdcRate", wdcRate);
			mav.setViewName("admin/withdrawscashrate/delete_withdrawscashrate");
			return mav;
		}
		return null;

	}

	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteById(BigDecimal id) throws Exception {
		String data = "提示：删除失败。";
		if (id != null) {
			int iden = withdrawsCashRateService.deleteByPrimaryKey(id);
			if (iden > 0) {
				data = "提示：删除成功。";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
}
