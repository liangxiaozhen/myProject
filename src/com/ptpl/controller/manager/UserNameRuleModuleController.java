package com.ptpl.controller.manager;

import java.math.BigDecimal;
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
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.service.UserNameRuleModuleServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 用户名规则设置
 * 
 * @author xiaoy
 *
 * @date 2016年11月11日 上午9:45:04
 */
@Controller
@RequestMapping("/admin/userNameRuleModule")
@Scope("prototype")
public class UserNameRuleModuleController extends BaseController {

	@Autowired
	private UserNameRuleModuleServiceI userNameRuleModuleService;

	/**
	 * 用户名规则设置信息
	 * 
	 * @param userNameRuleModule
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getUserNameRuleModuleList()
	{
		List<UserNameRuleModule> list = userNameRuleModuleService.selective(null);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("admin/ruleModule/userNameRuleModule_list");
		return mv;
	}
	/**
	 * 设置规则
	 * 
	 * @param unrm
	 */
	private void setRule(UserNameRuleModule unrm)
	{
		// 设置规则 a(可以与手机号相同),b(不能与手机号相同),c(系统自动同步手机号),d(系统按照规则代设置)
		String setRule = unrm.getSetrule();
		if (setRule.equals("a"))
		{
			unrm.setSetruledetail("用户名自填，可以与手机号相同");
		}
		if (setRule.equals("b"))
		{
			unrm.setSetruledetail("用户名自填，不能与手机号相同");
		}
		if (setRule.equals("c"))
		{
			unrm.setSetruledetail("用户名代填，系统自动同步手机号");
		}
		if (setRule.equals("d"))
		{
			String nameRule = unrm.getNamerule();
			unrm.setSetruledetail("用户名代填，系统按照规则代设置");
			if (nameRule.equals("a"))
				unrm.setNamecontent("预设字符+N位随机数");
			if (nameRule.equals("b"))
				unrm.setNamecontent("N位随机数");
		}
	}

	/**
	 * 启用
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "isUse", method = RequestMethod.POST)
	public void isUse(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			UserNameRuleModule unrm = new UserNameRuleModule();
			unrm.setId(id);
			unrm.setIsuse((short) 1);
			userNameRuleModuleService.updateByPrimaryKeySelective(unrm);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}

	/**
	 * 停用
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	public void cancel(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			UserNameRuleModule unrm = new UserNameRuleModule();
			unrm.setId(id);
			unrm.setIsuse((short) 0);
			userNameRuleModuleService.updateByPrimaryKeySelective(unrm);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
	/**
	 * 编辑修改UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView update_UI(BigDecimal id)
	{
		UserNameRuleModule unrm = userNameRuleModuleService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("unrm", unrm);
		mav.setViewName("admin/ruleModule/update_userNameRuleModule");
		return mav;
	}
	/**
	 * 编辑修改
	 * 
	 * @param unrm
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = {RequestMethod.POST})
	public void update(UserNameRuleModule unrm) throws Exception
	{
		String data = "fail";
		if (unrm != null)
		{
			/*
			 * 判断长度
			 */
			if (checkLength(unrm.getRandomlength(), unrm.getUsernamelength(),
					unrm.getUsernamemaxlength()))
				return;
			setRule(unrm);
			userNameRuleModuleService.updateByPrimaryKey(unrm);
			data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 * 判断用户名长度，随机位数长度
	 * 
	 * @param randomLength
	 *            随机位数长度
	 * @param userNameLength
	 *            用户名长度
	 * @return
	 */
	private boolean checkLength(Short randomLength, Short userNameLength, Short userNameMaxLength)
	{
		boolean flag = false;
		// 随机数长度
		if (randomLength != null)
		{
			if (randomLength < 4 || randomLength > 7)
				flag = true;
		}
		// 用户名长度
		if (userNameLength != null && userNameMaxLength != null)
		{
			if (userNameLength >= userNameMaxLength)
				flag = true;
		}
		return flag;
	}
}
