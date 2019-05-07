package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.model.UserGradeExp;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeExpServiceI;
import com.ptpl.service.UserGradeServiceI;

/**
 * 体验会员等级设置Controller
 * 
 * @author xiaoy
 *
 * @date 2016年11月26日 上午11:32:42
 */
@Controller
@RequestMapping("admin/userGradeExp")
@Scope("prototype")
public class UserGradeExpManagerController extends BaseController {
	@Autowired
	private UserGradeExpServiceI userGradeExpService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;
	/**
	 * 查询List
	 * 
	 * @param userGradeExp
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView queryAll(UserGradeExp userGradeExp) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserGradeExp> list = userGradeExpService.selective(userGradeExp);
		for (UserGradeExp exp : list) {
			// 会员等级人员
			int number = userAccountSafeInfoService.getCountByUgradeExp(exp.getUgrade().intValue());
			exp.setNumber(number);
		}
		List<UserGrade> userGradeList = userGradeService.getGroupByUgradeAndUgradeName();
		userGradeList.remove(0);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("userGradeList", userGradeList);
		mav.addObject("userGradeExp", userGradeExp);
		mav.setViewName("admin/userGradeExp/userGradeExp_list");
		return mav;
	}

	/**
	 * 跳转新增UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView insert_UI() {
		List<UserGrade> userGradeList = userGradeService.getGroupByUgradeAndUgradeName();
		userGradeList.remove(0);
		List<SpecialNameList> snList=specialNameListService.getSpecialNameListForUserGradeExp();
		ModelAndView mav = new ModelAndView();
		//体验会员可选定向名单
		mav.addObject("testSpecialNameList", snList);
		mav.addObject("userGradeList", userGradeList);
		mav.setViewName("admin/userGradeExp/insert_userGradeExp");
		return mav;
	}

	/**
	 * 新增
	 * 
	 * @param userGradeExp
	 * @throws Exception
	 */
	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	public void insert(UserGradeExp userGradeExp) throws Exception {
		String data = "fail";
		if (userGradeExp != null && userGradeExp.getUgrade() != null) {
			AdminUser admin = getAdminUser();
			userGradeExp.setAddman(admin.getUsername());
			userGradeExp.setAddtime(new Date());
			int iden = userGradeExpService.insertSelective(userGradeExp);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转编辑UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView update_UI(BigDecimal id) {
		UserGradeExp userGradeExp = userGradeExpService.selectByPrimaryKey(id);
		List<SpecialNameList> snList=specialNameListService.getSpecialNameListForUserGradeExp();
		ModelAndView mav = new ModelAndView();
		mav.addObject("userGradeExp", userGradeExp);
		//体验会员可选定向名单
		mav.addObject("testSpecialNameList", snList);
		mav.setViewName("admin/userGradeExp/update_userGradeExp");
		return mav;
	}

	/**
	 * 编辑
	 * 
	 * @param userGradeExp
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.GET })
	public void update(UserGradeExp userGradeExp) throws Exception {
		String data = "提示：编辑失败。";
		if (userGradeExp != null) {
			AdminUser admin = getAdminUser();
			userGradeExp.setAddman(admin.getUsername());
			userGradeExp.setAddtime(new Date());
			int iden = userGradeExpService.updateByPrimaryKeySelective(userGradeExp);
			if (iden > 0)
				data = "提示:编辑成功。";
			if(iden==-1)
				data="提示:普通会员等级状态为 停用，体验会员等级状态修改失败。";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 启用
	 * 
	 * @param id
	 * @throws Exception
	 */
	// @RequestMapping(value = "isUse", method = { RequestMethod.POST,
	// RequestMethod.GET })
	// public void isUse(BigDecimal id) throws Exception {
	// String data = "fail";
	// if (id != null) {
	// UserGradeExp userGradeExp = userGradeExpService.selectByPrimaryKey(id);
	// UserGrade userGrade = new UserGrade();
	// userGrade.setUgrade(new BigDecimal(userGradeExp.getUgrade()));
	// userGrade = userGradeService.selectiveForNormal(userGrade).get(0);
	// Short status = userGrade.getStatus();
	// if (status.equals((short) 1)) {
	// AdminUser admin = getAdminUser();
	// userGradeExp.setAddman(admin.getUsername());
	// userGradeExp.setAddtime(new Date());
	// userGradeExp.setStatus((short) 1);
	// int iden = userGradeExpService.updateByPrimaryKeySelective(userGradeExp);
	// if (iden > 0)
	// data = "success";
	// }
	// }
	// sendJsonData(response, JSON.toJSONString(data));
	// }

	/**
	 * 停用
	 * 
	 * @param id
	 * @throws Exception
	 */
	// @RequestMapping(value = "cancel", method = { RequestMethod.POST,
	// RequestMethod.GET })
	// public void cancel(BigDecimal id) throws Exception {
	// String data = "fail";
	// if (id != null) {
	// UserGradeExp userGradeExp = new UserGradeExp();
	// userGradeExp.setId(id);
	// userGradeExp.setStatus((short) 0);
	// AdminUser admin = getAdminUser();
	// userGradeExp.setAddman(admin.getUsername());
	// userGradeExp.setAddtime(new Date());
	// int iden = userGradeExpService.updateByPrimaryKeySelective(userGradeExp);
	// if (iden > 0)
	// data = "success";
	// }
	// sendJsonData(response, JSON.toJSONString(data));
	// }

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "queryById")
	public ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			ModelAndView mav = new ModelAndView();
			UserGradeExp userGradeExp = userGradeExpService.selectByPrimaryKey(id);
			mav.setViewName("admin/userGradeExp/userGradeExp_Detail");
			mav.addObject("userGradeExp", userGradeExp);
			return mav;
		}
		return null;
	}

	/**
	 * 跳转删除UI
	 * 
	 * @param id
	 * 
	 * @return
	 */
	@RequestMapping(value = "del_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView del_UI(BigDecimal id) {
		if (id != null) {
			UserGradeExp userGradeExp = userGradeExpService.selectByPrimaryKey(id);
			int number = userAccountSafeInfoService.getCountByUgradeExp(userGradeExp.getUgrade().intValue());
			userGradeExp.setNumber(number);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/userGradeExp/delete_userGradeExp");
			mav.addObject("userGradeExp", userGradeExp);
			return mav;
		}
		return null;
	}

	/**
	 * 删除 m
	 * 
	 * @param id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "del", method = { RequestMethod.POST, RequestMethod.GET })
	public void delete(BigDecimal id) throws Exception {
		if (id != null) {
			String data = "fail";
			int iden = userGradeExpService.deleteByPrimaryKey(id);
			if (iden > 0)
				data = "success";
			sendJsonData(response, JSON.toJSONString(data));
		}
	}

	/**
	 * 查看体验定向名单
	 * 
	 * @param isExpSpecific
	 * @param expSNLID
	 * @return
	 */
	@RequestMapping(value = "queryExpSNL")
	public ModelAndView queryExpSNL(BigDecimal isExpSpecific, BigDecimal expSNLID) {
		if (expSNLID != null && isExpSpecific != null) {
			ModelAndView mav = new ModelAndView();
			if (isExpSpecific.intValue() == 1) {
				Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.getUserForSNLID(expSNLID);
				mav.setViewName("admin/userGradeExp/userGradeExp_EXPSNL");
				mav.addObject("ubaiList", ubaiList);
				return mav;
			}
		}
		return null;
	}

	/**
	 * 查看会员等级中的用户
	 * 
	 * @param ugrade
	 * @return
	 */
	@RequestMapping(value = "queryUsers")
	private ModelAndView queryUsers(Short ugrade) {
		if (ugrade != null) {
			List<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.selectUsersBysTasteuGrade(ugrade);
			ModelAndView mav = new ModelAndView();
			mav.addObject("ubaiList", ubaiList);
			mav.setViewName("admin/userGrade/userGrade_number");
			return mav;
		}
		return null;
	}
}
