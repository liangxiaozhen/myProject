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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.service.SMSChannelServiceI;
import com.ptpl.service.SysNoticeBizServiceI;

/**
 * 短信通道管理Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月27日 上午11:27:16
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin/sMSChannel")
public class SMSChannelManagerController extends BaseController {

	@Autowired
	private SMSChannelServiceI sMSChannelService;
	@Autowired
	private SysNoticeBizServiceI sysNoticeBizService;

	/**
	 * 查询短信通道管理List
	 * 
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	public ModelAndView querAll(SMSChannel channel) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<SMSChannel> list = sMSChannelService.selective(null);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.setViewName("admin/SMSChannel/SMSChannelList");
		return mav;
	}

	/**
	 * 跳转新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI")
	public ModelAndView insert_UI() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/SMSChannel/SMSChannelAdd");
		return mav;
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
			SMSChannel sms = sMSChannelService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("smsChannel", sms);
			mav.setViewName("admin/SMSChannel/update_SMSChannel");
			return mav;
		}
		return null;
	}

	/**
	 * 新增短信通道
	 * 
	 * @param smsChannel
	 * @throws Exception
	 */
	@RequestMapping(value = "insert")
	public void insert(SMSChannel smsChannel) throws Exception {
		String data = "fail";
		if (smsChannel != null) {
			if (StringUtil.isEmpty(smsChannel.getPusername()))
				return;
			if (StringUtil.isEmpty(smsChannel.getPpassword()))
				return;
			if (smsChannel.getIsuse() == null)
				return;
			AdminUser admin = getAdminUser();
			smsChannel.setAddman(admin.getUsername());
			smsChannel.setAddtime(new Date());
			int iden = sMSChannelService.insertSelective(smsChannel);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 更新短信通道設置
	 * 
	 * @param smsChannel
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	public void update(SMSChannel smsChannel) throws Exception {
		String data = "fail";
		if (smsChannel != null) {
			if (StringUtil.isEmpty(smsChannel.getPusername()))
				return;
			if (StringUtil.isEmpty(smsChannel.getPpassword()))
				return;

			if (smsChannel.getIsuse() == null)
				return;
			AdminUser admin = getAdminUser();
			smsChannel.setAddman(admin.getUsername());
			smsChannel.setAddtime(new Date());
			int iden = sMSChannelService.updateByPrimaryKeySelective(smsChannel);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转删除界面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete_UI")
	public ModelAndView delete_UI(BigDecimal id) {
		if (id != null) {
			SySNoticeBiz sysNoticeBiz = new SySNoticeBiz();
			sysNoticeBiz.setSmscid(id);
			List<SySNoticeBiz> list = sysNoticeBizService.selective(sysNoticeBiz);
			SMSChannel sms = sMSChannelService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			if (list.size() > 0) {
				mav.addObject("list", list);
			} else {
				mav.addObject("smsChannel", sms);
			}
			mav.setViewName("admin/SMSChannel/delete_SMSChannel");
			return mav;
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	public void delete(BigDecimal id) throws Exception {
		String data = "fail";
		if (id != null) {
			int iden = sMSChannelService.deleteByPrimaryKey(id);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 查看详情页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "queryById")
	public ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			SMSChannel sms = sMSChannelService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("smsChannel", sms);
			mav.setViewName("admin/SMSChannel/SMSChannelDetail");
			return mav;
		}
		return null;
	}
}
