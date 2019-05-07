package com.ptpl.controller;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
/**
 * 用户后台 我的推广
 * 
 * @author xiaoy
 *
 * @date 2016年10月10日 下午2:23:39
 */
@Controller
@RequestMapping("/user/userPromo")
@Scope("prototype")
public class UserPromoController extends BaseController {
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	/**
	 * 我的推广页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "userPromo", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAll()
	{
		UserBaseAccountInfo ubai =getUserBaseAccountInfo();
		ModelAndView mav = new ModelAndView();
		if (ubai != null)
		{
			// 用户名
			String loginName = ubai.getLoginname();
			// 真实姓名
			String realName = ubai.getRealname();
			UserPromo userPromo = userPromoService.selectByPrimaryKey(ubai.getId());
			mav.addObject("userPromo", userPromo);
			if (StringUtil.isNotEmpty(loginName) && StringUtil.isNotEmpty(realName))
			{
				try
				{
					
					List<UserPromo> childList = selectPromoChildList(userPromo);
					clean(childList, userPromo.getPromolevels());
					/*
					 * 用户第三方推广链接
					 */
					UserPromoThirdParty uptp = new UserPromoThirdParty();
					uptp.setUpid(ubai.getId());
					List<UserPromoThirdParty> uptpList = userPromoThirdPartyService.selective(uptp);
					for (UserPromoThirdParty uptp1 : uptpList)
					{
						uptp1.setThirdpartycode(uptp1.getThirdpartycode().replaceAll("XXX",
								ubai.getId().toString()));
					}
					
					mav.addObject("childList", childList);//推广下级集合
					mav.addObject("uptpList", uptpList);//第三方推广集合
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			mav.setViewName("user/userPromo/userPromo_list");
			return mav;
		}
		mav.setViewName("redirect:/user/tologin.action");
		return mav;
	}
	/**
	 * 查询下级有效推广
	 * 
	 * @param userPromo
	 * @return
	 * @throws Exception
	 */
	private List<UserPromo> selectPromoChildList(UserPromo userPromo) throws Exception
	{
		List<UserPromo> list = new ArrayList<UserPromo>();
		String loginName = userPromo.getLoginname();
		Class<?> clazz = userPromo.getClass();
		for (int i = 2; i < 101; i++)
		{
			Method getSuplevel = clazz.getDeclaredMethod("setSuplevels" + i, String.class);
			Method getIsValid = clazz.getDeclaredMethod("setIsvalid" + i, Short.class);
			UserPromo up = new UserPromo();
			getSuplevel.invoke(up, loginName);
			getIsValid.invoke(up, (short) 1);
			List<UserPromo> ups = userPromoService.selective(up);
			if (ups.size() < 1)
				break;
			for (UserPromo uPromo : ups)
			{
				list.add(uPromo);
			}
		}
		return list;
	}
	/**
	 * 编辑 UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView update_UI(BigDecimal id)
	{
		UserPromo userPromo = userPromoService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userPromo", userPromo);
		mav.setViewName("user/userPromo/update_userPromo");
		return mav;
	}
	/**
	 * 编辑推荐码 UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateCode_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView updateCode_UI(BigDecimal id)
	{
		UserPromo userPromo = userPromoService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userPromo", userPromo);
		mav.setViewName("user/userPromo/update_userPromo_code");
		return mav;
	}
	/**
	 * 编辑
	 * 
	 * @param userPromo
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = {RequestMethod.POST})
	public void update(UserPromo userPromo) throws Exception
	{
		String data = "fail";
		if (userPromo != null)
		{
			// 被编辑的ID
			BigDecimal id = userPromo.getId();
			// 上级备注
			String subRemark = userPromo.getSubremark();
			/*
			 * set对象
			 */
			UserPromo up = new UserPromo();
			up.setId(id);
			up.setSubremark(subRemark);
			int iden = userPromoService.updateByPrimaryKey(up);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 * 编辑邀请码
	 * 
	 * @param userPromo
	 * @throws Exception
	 */
	@RequestMapping(value = "updateCode", method = {RequestMethod.POST})
	public void updateCode(UserPromo userPromo, String oldPromoCode) throws Exception
	{
		String data = "fail";
		String newPromoCode = userPromo.getPromocode();
		Pattern pattern = Pattern
				.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？].*");
		boolean flag = pattern.matcher(newPromoCode).matches();
		if (!flag)
		{
			if (userPromo != null)
			{
				UserPromo up = new UserPromo();
				up.setId(userPromo.getId());
				up.setIsmodify((short) 1);
				up.setPromocode(newPromoCode.trim());
				int iden = userPromoService.updateByPrimaryKey(up);
				int iden2 = userPromoService.updatePromoCode(oldPromoCode, newPromoCode.trim());
				if (iden > 0 && iden2 > 0)
					data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 * 整理
	 * 
	 * @param list
	 * @param level
	 */
	private void clean(List<UserPromo> list, long level)
	{
		if (list.size() > 0)
		{
			for (int i = 0; i < list.size(); i++)
			{
				UserPromo userPromo = list.get(i);
				// 姓名
				String name = userPromo.getName();
				// 用户名
				String loginName = userPromo.getLoginname();
				// 推广层级
				Long levels = userPromo.getPromolevels();
				if (StringUtil.isNotEmpty(name))
				{
					// 真实姓名 *
					name = "*" + name.substring(name.length() - 1);
					// 用户名 *
					loginName = loginName.substring(0, 1) + "*****"
							+ loginName.substring(loginName.length() - 1);
					levels = levels - level;
					userPromo.setName(name);
					userPromo.setLoginname(loginName);
					userPromo.setPromolevels(levels);
				} else
				{
					list.remove(userPromo);
					i--;
				}
			}
		}
	}
}
