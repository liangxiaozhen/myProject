package com.ptpl.controller.manager;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.PromoAgentGradeProfitServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 系统后台 用户推广设置controller
 * 
 * @author xiaoy
 *
 * @date 2016年9月30日 上午11:35:35
 */
@Controller
@RequestMapping("/admin/userPromo")
@Scope("prototype")
public class UserPromoManagerController extends BaseController {
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private PromoAgentGradeProfitServiceI promoAgentGradeProfitService;
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	@Autowired
	private AGTPLinkServiceI AGTPLinkService;
	@Autowired
	private AgentGradePromoAuthServiceI agentGradePromoAuthService;
	/** 推广上级集合 **/
	private List<UserPromo> supList = new ArrayList<UserPromo>();
	/** 推广下级集合 **/
	protected List<UserPromo> childList = new ArrayList<UserPromo>();

	/**
	 * 用户推广设置
	 * 
	 * @param userPromo
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView queryAll(UserPromo userPromo) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserPromo> list = userPromoService.selective(userPromo);
		for (UserPromo up : list) {
			String loginname = up.getLoginname();
			String name = up.getName();
			if (StringUtils.isNotEmpty(loginname)) {
				up.setLoginname(AES.getDecrypt(loginname));
			}
			if (StringUtils.isNotEmpty(name)) {
				up.setName(AES.getDecrypt(name));
			}
		}
		List<PromoAgentGradeProfit> gradeNames = promoAgentGradeProfitService.getGradeName();
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userPromo", userPromo);
		mav.addObject("gradeNames", gradeNames);
		mav.setViewName("admin/userPromo/userPromo_list");
		mav.addObject("pagehelper", pagehelper);
		return mav;
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryById", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			UserPromo userPromo = userPromoService.selectByPrimaryKey(id);
			// 获取第三方推广
			UserPromoThirdParty uptp = new UserPromoThirdParty();
			uptp.setUpid(id);
			List<UserPromoThirdParty> list = userPromoThirdPartyService.selective(uptp);
			Long levels = userPromo.getPromolevels();
			// 获取推广上级 并判断是否有效
			String supPromoCode = userPromo.getSuppromocode();
			getSupPromo(supPromoCode);
			cleanSupList(supList, levels, userPromo);
			// 获取推广下级
			String name = userPromo.getLoginname();
			childList = userPromoService.selectBySupName(name);
			cleanChildList(childList, levels);
			ModelAndView mav = new ModelAndView();
			mav.addObject("userPromo", userPromo);
			mav.addObject("list", list);
			mav.setViewName("admin/userPromo/userPromo_Detail");
			mav.addObject("supList", supList);
			mav.addObject("childList", childList);
			return mav;
		}
		return null;
	}

	/**
	 * 整理推广下级
	 * 
	 * @param childList
	 * @param levels
	 */
	private void cleanChildList(List<UserPromo> childList, Long promolevels) {
		try {
			UserPromo u = new UserPromo();
			Class<?> clazz = u.getClass();
			int size = childList.size();
			for (int i = 0; i < size; i++) {
				UserPromo up = childList.get(i);
				// 下级推广层级
				Long childPromoLevels = up.getPromolevels();
				// 当前推广层级 - 下级推广层级
				Long levels = promolevels - childPromoLevels;
				up.setPromolevels(levels);
				Method method = clazz.getDeclaredMethod("getIsvalid" + (0 - levels + 1));
				Short isValid = (Short) method.invoke(up);
				if (isValid.intValue() != 0)
					up.setIsvalid((short) 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 整理推广上级
	 * 
	 * @param supList
	 * @param promolevels
	 */
	private void cleanSupList(List<UserPromo> supList, Long promolevels, UserPromo userPromo) {
		try {
			Class<?> clazz = userPromo.getClass();
			int size = supList.size();
			for (int i = 0; i < size; i++) {
				Method method = clazz.getDeclaredMethod("getIsvalid" + (i + 2));
				Short isValid = (Short) method.invoke(userPromo);
				UserPromo up = supList.get(i);
				// 上级推广层级
				Long supPromoLevels = up.getPromolevels();
				if (isValid.intValue() != 0)
					up.setIsvalid((short) 1);
				// 当前推广层级 - 上级推广层级
				Long levels = promolevels - supPromoLevels;
				up.setPromolevels(levels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑 UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView update_UI(BigDecimal id) {
		if (id != null) {
			UserPromo userPromo = userPromoService.selectByPrimaryKey(id);
			List<PromoAgentGradeProfit> list = promoAgentGradeProfitService.getGradeName();
			ModelAndView mav = new ModelAndView();
			mav.addObject("userPromo", userPromo);
			mav.addObject("p", list);
			mav.setViewName("admin/userPromo/update_userPromo");
			return mav;
		}
		return null;
	}

	/**
	 * 编辑
	 * 
	 * @param userPromo
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.GET })
	public void update(UserPromo userPromo) throws Exception {
		if (userPromo != null) {
			/*
			 * 编辑用户第三方推广
			 */
			int proxyGrade = userPromo.getProxygrade().intValue();
			BigDecimal id = userPromo.getId();
			/*
			 * 用户第三方推广
			 */
			UserPromoThirdParty uptp = new UserPromoThirdParty();
			uptp.setUpid(id);
			List<UserPromoThirdParty> uptpList = userPromoThirdPartyService.selective(uptp);
			/*
			 * 第三方推广
			 */
			List<AGTPLink> AGTPList = AGTPLinkService.selectByProxyGrade(new BigDecimal(proxyGrade));
			for (UserPromoThirdParty uptp1 : uptpList) {
				for (AGTPLink link : AGTPList) {
					String name = agentGradePromoAuthService.selectByPrimaryKey(link.getAgpaid()).getThirdpartyname();
					if (name.equals(uptp1.getThirdpartyname())) {
						uptp1.setIsopen(link.getIsopen());
						userPromoThirdPartyService.updateByPrimaryKeySelective(uptp1);
					}
				}
			}
			userPromoService.updateByPrimaryKey(userPromo);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}

	/**
	 * 批量编辑 UI
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchUpdateForUI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView batchUpdate_UI(String ids) {
		ids = ids.substring(0, ids.length() - 1);
		String[] arr = ids.split(",");
		List<PromoAgentGradeProfit> list = promoAgentGradeProfitService.getGradeName();
		ModelAndView mav = new ModelAndView();
		mav.addObject("proxygradeNames", list);
		mav.addObject("count", arr.length);
		mav.addObject("ids", ids);
		mav.setViewName("admin/userPromo/batchupdate_userPromo");
		return mav;
	}

	/**
	 * 批量编辑
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public void batchUpdate(String ids, UserPromo userPromo) throws Exception {
		if (userPromo.getProxygrade() != null) {
			// 管理员备注
			String adminRemark = userPromo.getAdminremark();
			// 代理等级
			BigDecimal proxyGrade = userPromo.getProxygrade();
			String[] arr = ids.split(",");
			for (String id : arr) {
				UserPromo up = new UserPromo();
				up.setId(new BigDecimal(id));
				up.setAdminremark(adminRemark);
				up.setProxygrade(proxyGrade);
				userPromoService.updateByPrimaryKey(up);
			}
			sendJsonData(response, JSON.toJSONString("操作：编辑成功。"));
		}
	}

	/**
	 * 获取推广上级
	 * 
	 * @param code
	 */
	public void getSupPromo(String code) {
		if (StringUtil.isNotEmpty(code)) {
			UserPromo userPromo = userPromoService.selectByPromoCode(code);
			// 上级推广码
			String supPromoCode = userPromo.getSuppromocode();
			if (userPromo != null) {
				supList.add(userPromo);
				getSupPromo(supPromoCode);
			}
		}
	}
}
