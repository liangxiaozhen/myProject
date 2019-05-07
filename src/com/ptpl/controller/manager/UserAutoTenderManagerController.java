package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAutoTender;
import com.ptpl.service.UserAutoTenderServiceI;

@Controller
@RequestMapping("/admin/autoTenderPan")
public class UserAutoTenderManagerController extends BaseController {

	/** 自动投标计划 */
	@Autowired
	UserAutoTenderServiceI userAutoTenderService;

	/**
	 * （条件）获取用户自动投标计划列表
	 * 
	 * @param request
	 * @param tproperty
	 * @param orderno
	 * @param originclient
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAutoTender", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAutoTender(UserAutoTender userAutoTender)
			throws Exception {

		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		
		if(userAutoTender.getUserBaseInfo()!=null&&StringUtils.isNotBlank(userAutoTender.getUserBaseInfo().getLoginname())){
			userAutoTender.getUserBaseInfo().setLoginname(setEncrypt(userAutoTender.getUserBaseInfo().getLoginname()));
		}
		
		// 调用service层的方法得到对象列表
		List<UserAutoTender> tenderPanList = userAutoTenderService.selectAll(userAutoTender);
		for(UserAutoTender u:tenderPanList){
			u.setUserBaseInfo(getDecryptionUserBaseAccountInfoDetail(u.getUserBaseInfo()));
		}
		PageInfo<Object> pagehelper = initPagehelper(maps, tenderPanList);
		
		if(userAutoTender.getUserBaseInfo()!=null&&StringUtils.isNotBlank(userAutoTender.getUserBaseInfo().getLoginname())){
			userAutoTender.getUserBaseInfo().setLoginname(getDecrypt(userAutoTender.getUserBaseInfo().getLoginname()));
		}
		
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		// 条件回显
		mv.addObject("echodata", userAutoTender);
		mv.addObject("title", "自动投标计划");
		// 指定视图
		mv.setViewName("admin/userAutoTender/UserAutoTender_List");
		return mv;

	}


	/**
	 * 查看自动投标计划详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPlanDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryPlanDetail(BigDecimal id)
			throws Exception {
		UserAutoTender detail = userAutoTenderService.selectByPrimaryKey(id);
		detail.getUserBaseInfo().setLoginname(getDecrypt(detail.getUserBaseInfo().getLoginname()));
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("detail", detail);
		// 指定视图
		mv.setViewName("admin/userAutoTender/UserAutoTender_Detail");
		return mv;

	}

}
