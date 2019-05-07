package com.ptpl.controller.manager;

import java.math.BigDecimal;
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
import com.github.pagehelper.StringUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.SpecialTime_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SpecialTime;
import com.ptpl.service.SpecialTimeServiceI;

/**
 * 特定时间
 * 
 * @author xiaoy
 *
 * @date 2016年7月13日 下午1:55:16
 */
@Controller
@RequestMapping("/admin/specialTime")
@Scope("prototype")
public class SpecialTimeManagerController extends BaseController {
	@Autowired
	private SpecialTimeServiceI specialTimeService;
	/**
	 * 特定时间列表
	 * 
	 * @param specialTime
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAllSpecialTime(SpecialTime specialTime)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<SpecialTime> specialTimes = specialTimeService.selective(specialTime);
		PageInfo<Object> pagehelper = initPagehelper(map, specialTimes);

		if (specialTime.getBtime() != null)
			specialTime.setBtimeStr(sf.format(specialTime.getBtime()));
		if (specialTime.getEtime() != null)
			specialTime.setEtimeStr(sf.format(specialTime.getEtime()));
		List<SpecialTime> timeNames = null;
		if (StringUtil.isNotEmpty(specialTime.getTimetype()))
			timeNames = specialTimeService.getTimeNoAndTimeName(specialTime.getTimetype());
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("specialTime", specialTime);
		mav.addObject("timeNames", timeNames);
		mav.addObject("action", "queryAll.action");
		mav.setViewName(SpecialTime_Constant.LIST_VIEW);
		return mav;
	}
	/**
	 * 特定时间设置
	 * 
	 * @param specialTime
	 * @return
	 */
	@RequestMapping(value = "/queryAllForUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAllForUpdate(SpecialTime specialTime)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<SpecialTime> specialTimes = specialTimeService.selective(specialTime);
		PageInfo<Object> pagehelper = initPagehelper(map, specialTimes);
		if (specialTime.getBtime() != null)
			specialTime.setBtimeStr(sf.format(specialTime.getBtime()));
		if (specialTime.getEtime() != null)
			specialTime.setEtimeStr(sf.format(specialTime.getEtime()));
		List<SpecialTime> timeNames = null;
		if (StringUtil.isNotEmpty(specialTime.getTimetype()))
			timeNames = specialTimeService.getTimeNoAndTimeName(specialTime.getTimetype());
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("specialTime", specialTime);
		mav.addObject("timeNames", timeNames);
		mav.addObject("action", "queryAllForUpdate.action");
		mav.setViewName(SpecialTime_Constant.LIST_VIEW);
		return mav;
	}
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/queryById", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryObject(BigDecimal id, Short uid)
	{
		SpecialTime sTime = specialTimeService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(SpecialTime_Constant.URL_MAP.get(uid));
		mav.addObject("specialtime", sTime);
		return mav;
	}
	/**
	 * 编辑保存特定时间
	 * 
	 * @param specialTime
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
	public void updateSpecialTime(SpecialTime specialTime) throws Exception
	{
		if (specialTime != null)
		{
			AdminUser adminUser = (AdminUser) request.getSession()
					.getAttribute(Session_Constant.ADMINUSER);
			specialTime.setAddman(adminUser.getUsername());
			specialTime.setAddtime(new Date());
			long time = specialTime.getEtime().getTime() - specialTime.getBtime().getTime();
			Map<String, String> map = new HashMap<String, String>();
			if (time < 0)
			{ // 失败 开始时间大于结束时间
				map.put("result", "1");
			} else
			{
				int iden = specialTimeService.updateByPrimaryKeySelective(specialTime);
				if (iden != -2)
				{
					// 成功
					map.put("result", "2");
				} else
				{
					// 存在重叠时间段
					map.put("result", "3");
				}
			}
			String json = JSON.toJSONString(map);
			sendJsonData(response, json);
		}
	}
	/**
	 * 删除时间包
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public void deleteById(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			specialTimeService.deleteByPrimaryKey(id);
			String jsonStr = JSON.toJSONString("操作：删除成功");
			sendJsonData(response, jsonStr);
		}
	}
	/**
	 * 保存时间包
	 * 
	 * @param specialTime
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSpecialTime", method = {RequestMethod.POST, RequestMethod.GET})
	public void insertSpecialTime(SpecialTime specialTime) throws Exception
	{
		if (specialTime != null)
		{
			AdminUser adminUser = (AdminUser) request.getSession()
					.getAttribute(Session_Constant.ADMINUSER);
			specialTime.setAddman(adminUser.getUsername());
			specialTime.setAddtime(new Date());
			long time = specialTime.getEtime().getTime() - specialTime.getBtime().getTime();
			Map<String, String> map = new HashMap<String, String>();
			if (time < 0)
			{ // 失败 开始时间大于结束时间
				map.put("result", "1");
			} else
			{
				int iden = specialTimeService.insertSelective(specialTime);
				if (iden != -2)
				{
					// 成功
					map.put("result", "2");
				} else
				{
					// 存在重叠时间段
					map.put("result", "3");
				}
			}
			String json = JSON.toJSONString(map);
			sendJsonData(response, json);
		}
	}
	/**
	 * 跳转 新增页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertSpecialTimeUI()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(SpecialTime_Constant.INSERT_VIEW);
		return mav;
	}
	/**
	 * 获取时间包名称
	 * 
	 * @param timeType
	 *            时间类型
	 * @throws Exception
	 */
	@RequestMapping(value = "/querySpecialTimeName", method = {RequestMethod.POST,
			RequestMethod.GET})
	public void querySpecialTimeName(String timeType) throws Exception
	{
		if (timeType != null && !"".equals(timeType))
		{
			List<SpecialTime> names = specialTimeService.getTimeNoAndTimeName(timeType);
			String jsonStr = JSON.toJSONString(names);
			sendJsonData(response, jsonStr);
		}
	}
	/**
	 * 跳转 删除页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delUI(BigDecimal id)
	{
		if (id != null)
		{
			SpecialTime sTime = specialTimeService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.setViewName(SpecialTime_Constant.DELETE_VIEW);
			mav.addObject("sTime", sTime);
			return mav;
		}
		return null;
	}
}
