package com.ptpl.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.PromoRegInfo;
import com.ptpl.service.PromoRegInfoServiceI;

/**
 * 推广码注册用户记录(无根注册)后台管理Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年1月16日 下午4:22:00
 *
 */
@Controller
@RequestMapping("/admin/promoRegInfo")
@Scope("prototype")
public class PromoRegInfoManagerController extends BaseController {
	@Autowired
	private PromoRegInfoServiceI promoRegInfoService;

	/**
	 * 查询列表
	 * 
	 * @param promoRegInfo
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	public ModelAndView queryAll(PromoRegInfo promoRegInfo) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<PromoRegInfo> list = promoRegInfoService.selective(promoRegInfo);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.setViewName("admin/promoRegInfo/promoRegInfo_list");
		mav.addObject("promoRegInfo", promoRegInfo);
		return mav;
	}
}
