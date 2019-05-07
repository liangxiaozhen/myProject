package com.ptpl.controller.manager;

import java.math.BigDecimal;
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
import com.ptpl.controller.BaseController;
import com.ptpl.model.MessageTemplate;
import com.ptpl.service.MessageTemplateServiceI;

/**
 * 短信内容模板编辑Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月29日 下午3:30:34
 *
 */
@Controller
@RequestMapping("/admin/messageTemplate")
@Scope("prototype")
public class MessageTemplateManagerController extends BaseController {

	@Autowired
	private MessageTemplateServiceI messageTemplateService;

	/**
	 * 查询短信内容模板
	 * 
	 * @param messageTemplate
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	public ModelAndView queryAll() {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<MessageTemplate> list = messageTemplateService.selective(null);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.setViewName("admin/messageTemplate/messageTemplate_list");
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
			MessageTemplate msgTemplate = messageTemplateService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("messageTemplate", msgTemplate);
			mav.setViewName("admin/messageTemplate/update_MessageTemplate");
			return mav;
		}
		return null;
	}

	/**
	 * 更新
	 * 
	 * @param msgTemplate
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	public void update(MessageTemplate msgTemplate) throws Exception {
		String data = "fail";
		if (msgTemplate != null) {
			int iden = messageTemplateService.updateByPrimaryKeySelective(msgTemplate);
			if (iden > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
}
