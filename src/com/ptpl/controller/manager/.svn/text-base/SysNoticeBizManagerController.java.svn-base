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
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.service.SMSChannelServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.SysNoticeBizServiceI;

/**
 * 系统通知业务设置controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月26日 下午6:12:45
 *
 */
@Controller
@RequestMapping("admin/sysNoticeBiz")
@Scope("prototype")
public class SysNoticeBizManagerController extends BaseController {
	@Autowired
	private SysNoticeBizServiceI sysNoticeBizService;
	@Autowired
	private SMSChannelServiceI smsChannelService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;

	@RequestMapping(value = "queryAll")
	public ModelAndView queryAll(SySNoticeBiz sysNoticeBiz) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<SySNoticeBiz> sysNoticeBizList = sysNoticeBizService.selective(null);
		PageInfo<Object> pagehelper = initPagehelper(map, sysNoticeBizList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.setViewName("admin/sysNoticeBiz/sysNoticeBiz_list");
		return mav;
	}

	/**
	 * 开启短信发送开关
	 * 
	 * @param sms
	 * @throws Exception
	 */
	@RequestMapping(value = "open")
	public void open(SySNoticeBiz sysNoticeBiz) throws Exception {
		String data = "fail";
		if (sysNoticeBiz.getId() != null) {
			AdminUser admin = getAdminUser();
			sysNoticeBiz.setAddman(admin.getUsername());
			sysNoticeBiz.setAddtime(new Date());
			sysNoticeBiz.setIsopen((short) 1);
			int iden = sysNoticeBizService.updateByPrimaryKeySelective(sysNoticeBiz);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 关闭短信发送开关
	 * 
	 * @param sms
	 * @throws Exception
	 */
	@RequestMapping(value = "close")
	public void close(SySNoticeBiz sysNoticeBiz) throws Exception {
		String data = "fail";
		if (sysNoticeBiz.getId() != null) {
			AdminUser admin = getAdminUser();
			sysNoticeBiz.setAddman(admin.getUsername());
			sysNoticeBiz.setAddtime(new Date());
			sysNoticeBiz.setIsopen((short) 0);
			int iden = sysNoticeBizService.updateByPrimaryKeySelective(sysNoticeBiz);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI")
	public ModelAndView update_UI(BigDecimal id) {
		if (id != null) {
			/*
			 * 查询定向名单
			
			SpecialNameList name = new SpecialNameList();
			name.setIsUse((short) 1);
			name.setBusinessType((short) 2);
			List<SpecialNameList> snameList = specialNameListService.getSpecialNameListByNo(name);
			 */
			/*
			 * 查询启用状态的短信通道
			 */
			SMSChannel sms = new SMSChannel();
			sms.setIsuse((short) 1);
			List<SMSChannel> list = smsChannelService.selective(sms);
			
			SySNoticeBiz sys = sysNoticeBizService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("sysNoticeBiz", sys);
			mav.addObject("list", list);
			//mav.addObject("specialNameList", snameList);
			mav.setViewName("admin/sysNoticeBiz/update_sysNoticeBiz");
			return mav;
		}
		return null;
	}

	/**
	 * 更新
	 * 
	 * @param sysNoticeBiz
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SySNoticeBiz sysNoticeBiz) throws Exception {
		String data = "fail";
		if (sysNoticeBiz != null) {
			AdminUser admin = getAdminUser();
			sysNoticeBiz.setAddman(admin.getUsername());
			sysNoticeBiz.setAddtime(new Date());
			int iden = sysNoticeBizService.updateByPrimaryKeySelective(sysNoticeBiz);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
}
