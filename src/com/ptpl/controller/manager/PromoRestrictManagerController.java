package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.PromoLevelRestrict;
import com.ptpl.model.PromoTotalRestrict;
import com.ptpl.service.PromoLevelRestrictServiceI;
import com.ptpl.service.PromoTotalRestrictServiceI;
/**
 * 推广限制Controller
 * 
 * @author xiaoy
 *
 * @date 2016年10月17日 下午4:51:12
 */
@Controller
@RequestMapping("/admin/promoRestrict")
@Scope("prototype")
public class PromoRestrictManagerController extends BaseController {
	@Autowired
	PromoTotalRestrictServiceI promoTotalRestrictService;
	@Autowired
	PromoLevelRestrictServiceI promoLevelRestrictService;
	/**
	 * 查询推广限制设置
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAll()
	{
		PromoTotalRestrict promoTotal = promoTotalRestrictService
				.selectByPrimaryKey(new BigDecimal(1));
		List<PromoLevelRestrict> list = promoLevelRestrictService.selective(null);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/promoRestrict/promoRestrict_list");
		mav.addObject("list", list);
		mav.addObject("promoTotal", promoTotal);
		return mav;
	}
	/**
	 * 更新 推广总人数限制，最大推广层数
	 * 
	 * @param pt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateTotal", method = {RequestMethod.POST, RequestMethod.GET})
	public void updateTotal(PromoTotalRestrict pt) throws Exception
	{
		Integer levelLitmit = pt.getLevellimit().intValue();
		if (levelLitmit != null && levelLitmit > 0)
		{
			List<PromoLevelRestrict> list = promoLevelRestrictService.selective(null);
			int size = list.size() + 1;
			if (levelLitmit <= size)
			{
				PromoTotalRestrict ptr = promoTotalRestrictService
						.selectByPrimaryKey(new BigDecimal(1));
				Short maxlevel = ptr.getIsmaxlevel();
				if (pt.getLevellimit() > maxlevel)
					pt.setIsmaxlevel(pt.getLevellimit());
				if (pt.getTotalnumlimit() == null)
					pt.setTotalnumlimit(0l);
				promoTotalRestrictService.updateByPrimaryKeySelective(pt);
				sendJsonData(response, JSON.toJSONString("提示：设置成功。"));
			} else
			{
				sendJsonData(response, JSON.toJSONString("提示：最大推广层级不能大于已设置的推广层级。"));
			}
		}
	}
	/**
	 * 新增推广层级人数限制 UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insert_UI()
	{
		ModelAndView mav = new ModelAndView();
		PromoTotalRestrict promoTotal = promoTotalRestrictService
				.selectByPrimaryKey(new BigDecimal(1));
		mav.addObject("size", promoTotal.getLevellimit());
		mav.setViewName("admin/promoRestrict/insert_promoRestrict");
		return mav;
	}
	/**
	 * 新增推广层级人数限制
	 * 
	 * @param promoLevel
	 * @throws Exception
	 */
	@RequestMapping(value = "insert", method = {RequestMethod.POST, RequestMethod.GET})
	public void insert(PromoLevelRestrict promoLevel) throws Exception
	{
		Integer size=promoLevelRestrictService.selective(null).size()+2;
		promoLevel.setPromolevel(size.shortValue());
		promoLevelRestrictService.insertSelective(promoLevel);
		sendJsonData(response, JSON.toJSONString("提示：设置成功。"));
	}
	/**
	 * 编辑推广层级人数限制UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView update_UI(BigDecimal id)
	{
		PromoLevelRestrict plr = promoLevelRestrictService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("plr", plr);
		mav.setViewName("admin/promoRestrict/update_promoRestrict");
		return mav;
	}
	/**
	 * 编辑推广层级人数限制
	 * 
	 * @param promoLevel
	 */
	@RequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.GET})
	public void update(PromoLevelRestrict promoLevel) throws Exception
	{
		promoLevelRestrictService.updateByPrimaryKeySelective(promoLevel);
		sendJsonData(response, JSON.toJSONString("提示：编辑成功。"));
	}
}
