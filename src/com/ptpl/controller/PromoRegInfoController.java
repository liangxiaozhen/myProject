package com.ptpl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.ptpl.model.PromoRegInfo;
import com.ptpl.service.PromoRegInfoServiceI;

@Controller
@RequestMapping("/promoRegInfo")
@Scope("prototype")
public class PromoRegInfoController extends BaseController {
	@Autowired
	private PromoRegInfoServiceI promoRegInfoService;

	/**
	 * 查询推广码
	 * 
	 * @param promoRegInfo
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	public ModelAndView queryByCode(PromoRegInfo promoRegInfo) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		ModelAndView mav = new ModelAndView();
		if (StringUtil.isNotEmpty(promoRegInfo.getRegusercode())) {
			Map<String, Object> map = new HashMap<String, Object>();
			initPage(map, pageNum, pageSize);
			List<PromoRegInfo> list = promoRegInfoService.selective(promoRegInfo);
			PageInfo<Object> pagehelper = initPagehelper(map, list);
			mav.addObject("pagehelper", pagehelper);
		}
		mav.setViewName("user/promoRegInfo/promoRegInfo_list");
		mav.addObject("promoRegInfo", promoRegInfo);
		return mav;
	}
}
