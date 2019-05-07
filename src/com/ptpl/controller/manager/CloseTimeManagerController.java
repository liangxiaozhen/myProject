package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.CloseTime_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.CloseTime;
import com.ptpl.service.CloseTimeServiceI;
/**
 * 关闭时间
 * 
 * @author xiaoy
 *
 * @date 2016年7月13日 下午1:55:26
 */
@Controller
@RequestMapping("/admin/closeTime")
@Scope("prototype")
public class CloseTimeManagerController extends BaseController {
	@Autowired
	private CloseTimeServiceI closeTimeService;
	/**
	 * 关闭时间列表
	 * 
	 * @param closeTime
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAllCloseTime(CloseTime closeTime)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<CloseTime> closeTimes = closeTimeService.selective(closeTime);
		List<CloseTime> timeNames = closeTimeService.selectTimeNameAndTimeNo();
		PageInfo<Object> pagehelper = initPagehelper(map, closeTimes);

		if (closeTime.getBtime() != null)
			closeTime.setBtimeStr(sf.format(closeTime.getBtime()));
		if (closeTime.getEtime() != null)
			closeTime.setEtimeStr(sf.format(closeTime.getEtime()));
		ModelAndView mav = new ModelAndView();

		// mav.addObject("names", names);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("closetime", closeTime);
		mav.addObject("timenames", timeNames);
		mav.addObject("action", "queryAll.action");
		mav.setViewName(CloseTime_Constant.LIST_VIEW);
		return mav;
	}
	/**
	 * 关闭时间设置
	 * 
	 * @param closeTime
	 * @return
	 */
	@RequestMapping(value = "/queryAllForUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAllForUpdate(CloseTime closeTime)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<CloseTime> closeTimes = closeTimeService.selective(closeTime);
		List<CloseTime> timeNames = closeTimeService.selectTimeNameAndTimeNo();
		PageInfo<Object> pagehelper = initPagehelper(map, closeTimes);

		if (closeTime.getBtime() != null)
			closeTime.setBtimeStr(sf.format(closeTime.getBtime()));
		if (closeTime.getEtime() != null)
			closeTime.setEtimeStr(sf.format(closeTime.getEtime()));
		ModelAndView mav = new ModelAndView();

		// mav.addObject("names", names);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("closetime", closeTime);
		mav.addObject("timenames", timeNames);
		mav.addObject("action", "queryAllForUpdate.action");
		mav.setViewName(CloseTime_Constant.LIST_VIEW);
		return mav;
	}
	/**
	 * 根据ID 查询记录
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/queryById", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryObject(BigDecimal id, Short uid)
	{
		CloseTime cTime = closeTimeService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CloseTime_Constant.URL_MAP.get(uid));
		mav.addObject("closetime", cTime);
		return mav;
	}
	/**
	 * 编辑修改
	 * 
	 * @param cTime
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
	public void update(CloseTime cTime) throws Exception
	{
		if (cTime != null)
		{
			AdminUser adminUser = (AdminUser) request.getSession()
					.getAttribute(Session_Constant.ADMINUSER);
			cTime.setAddman(adminUser.getUsername());
			cTime.setAddtime(new Date());
			// 判断时间 time<0 则表示 结束时间小于开始时间
			long time = cTime.getEtime().getTime() - cTime.getBtime().getTime();
			Map<String, String> map = new HashMap<String, String>();;
			if (time < 0)
			{
				// 失败
				map.put("result", "1");
			} else
			{
				// 成功
				int iden = closeTimeService.updateByPrimaryKeySelective(cTime);
				if (iden > 0)
				{
					map.put("result", "2");
				} else
				{
					map.put("result", "3");
				}
			}
			String json = JSON.toJSONString(map);
			sendJsonData(response, json);
		}
	}
	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public void deleteById(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			closeTimeService.deleteByPrimaryKey(id);
			String jsonStr = JSON.toJSONString("操作：删除成功");
			sendJsonData(response, jsonStr);
		}
	}
	/**
	 * 新增
	 * 
	 * @param rName
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCloseTime", method = {RequestMethod.POST, RequestMethod.GET})
	public void insertCloseTime(CloseTime cTime) throws Exception
	{
		if (cTime != null)
		{
			AdminUser adminUser = (AdminUser) request.getSession()
					.getAttribute(Session_Constant.ADMINUSER);
			cTime.setAddman(adminUser.getUsername());
			cTime.setAddtime(new Date());
			long time = cTime.getEtime().getTime() - cTime.getBtime().getTime();
			Map<String, String> map = new HashMap<String, String>();;
			if (time < 0)
			{
				// 失败 开始时间大于结束时间
				map.put("result", "1");
			} else
			{
				int iden = closeTimeService.insertSelective(cTime);
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
	 */
	@RequestMapping(value = "/insert_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertUI()
	{

		ModelAndView mav = new ModelAndView();
		mav.setViewName(CloseTime_Constant.INSERT_VIEW);
		List<CloseTime> timeNames = closeTimeService.selectTimeNameAndTimeNo();
		mav.addObject("timeNames", timeNames);
		return mav;
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
		CloseTime cTime = closeTimeService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CloseTime_Constant.DELETE_VIEW);
		mav.addObject("cTime", cTime);
		return mav;
	}
}
