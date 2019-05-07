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
import com.github.pagehelper.StringUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AdminUser;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.PromoAgentGradeProfitServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
/**
 * 推广代理资质等级分润 设置 Controller
 * 
 * @author xiaoy
 *
 * @version 2016年10月1日 下午2:18:34
 */
@Controller
@RequestMapping("/admin/promoAgentGradeProfit")
@Scope("prototype")
public class PromoAgentGradeProfitManagerController extends BaseController {
	@Autowired
	PromoAgentGradeProfitServiceI promoAgentGradeProfitService;
	@Autowired
	UserPromoServiceI userPromoService;
	@Autowired
	AgentGradePromoAuthServiceI agentGradePromoAuthService;
	@Autowired
	AGTPLinkServiceI AGTPLinkService;
	@Autowired
	UserPromoThirdPartyServiceI userPromoThirdPartyService;
	/**
	 * 查询List 代理等级设置
	 * 
	 * @param promoAgentGradeProfit
	 * @return
	 */
	@RequestMapping(value = "queryGradeAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryGradeAll(PromoAgentGradeProfit promoAgentGradeProfit)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<PromoAgentGradeProfit> list = promoAgentGradeProfitService
				.selective(promoAgentGradeProfit);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/promoAgentGradeProfit/promoAgentGrade_list");
		mav.addObject("pagehelper", pagehelper);

		return mav;
	}
	/**
	 * 新增等级UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "insertGrade_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertGrade_UI()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/promoAgentGradeProfit/insert_promoAgentGrade");
		return mav;
	}

	/**
	 * 设置代理资质
	 * 
	 * @param promoAgentGradeProfit
	 * @throws Exception
	 */
	@RequestMapping(value = "addPromoAgentGrade", method = {RequestMethod.POST, RequestMethod.GET})
	public void addProxyGrade(PromoAgentGradeProfit promoAgentGradeProfit) throws Exception
	{
		Integer proxyGrade = promoAgentGradeProfit.getProxygrade();
		String proxyGradeName = promoAgentGradeProfit.getProxygradename();
		if (proxyGrade > 0 && StringUtil.isNotEmpty(proxyGradeName))
		{
			PromoAgentGradeProfit p = new PromoAgentGradeProfit();
			p.setProxygradename(proxyGradeName);
			int size = promoAgentGradeProfitService.selective(p).size();
			if (size < 1)
			{
				/*
				 * 增加推广级别，当前级别存在记录，那么当前记录到以后连续的级别自增 +1
				 */
				List<PromoAgentGradeProfit> pagpList = promoAgentGradeProfitService.selective(null);
				for (PromoAgentGradeProfit pagp : pagpList)
				{
					if (proxyGrade == pagp.getProxygrade())
					{
						proxyGrade++;
						pagp.setProxygrade(proxyGrade);
						promoAgentGradeProfitService.updateByPrimaryKeySelective(pagp);

					}
				}
				AdminUser adminUser = (AdminUser) request.getSession()
						.getAttribute(Session_Constant.ADMINUSER);
				promoAgentGradeProfit.setAddman(adminUser.getUsername());
				promoAgentGradeProfit.setAddtime(new Date());
				promoAgentGradeProfitService.insertSelective(promoAgentGradeProfit);
				/*
				 * 关联表新增数据
				 */
				List<AgentGradePromoAuth> agpaList = agentGradePromoAuthService.selective(null);
				for (AgentGradePromoAuth agpa : agpaList)
				{
					AGTPLink link = new AGTPLink();
					link.setIsopen(agpa.getIsopen());
					link.setAgpaid(agpa.getId());
					link.setProxygrade(promoAgentGradeProfit.getId().intValue());
					AGTPLinkService.insertSelective(link);
				}
				sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
			} else
			{
				sendJsonData(response, JSON.toJSONString("提示：推广资质已存在，操作失败。"));
			}
		}
	}
	/**
	 * 代理资质等级编辑UI
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateGrade_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView updateGrade_UI(BigDecimal id) throws Exception
	{
		PromoAgentGradeProfit promoAgentGradeProfit = promoAgentGradeProfitService
				.selectByPrimaryKey(id);
		List<AGTPLink> linkList = AGTPLinkService.selectByProxyGrade(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("promoAgentGradeProfit", promoAgentGradeProfit);
		mav.addObject("linkList", linkList);
		mav.setViewName("/admin/promoAgentGradeProfit/update_promoAgentGrade");
		return mav;
	}
	/**
	 * 代理资质等级编辑
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateGrade", method = {RequestMethod.POST, RequestMethod.GET})
	public void updateGrade(PromoAgentGradeProfit promoAgentGradeProfit, String isopen)
			throws Exception
	{
		BigDecimal id = promoAgentGradeProfit.getId();
		Integer proxyGrade = promoAgentGradeProfit.getProxygrade();
		String proxyGradeName = promoAgentGradeProfit.getProxygradename();

		if (proxyGrade != null && StringUtil.isNotEmpty(proxyGradeName))
		{
			/**
			 * 查询第三方推广List
			 */
			/*
			 * 第三推广
			 */
			UserPromo userPromo = new UserPromo();
			userPromo.setProxygrade(id);
			userPromo.setUserspecialflag((short) 0);
			List<UserPromo> upList = userPromoService.selective(userPromo);
			for (UserPromo up : upList)
			{
				userPromoThirdPartyService.updateByUPID(up.getId());
			}
			/*
			 * 代理等级第三方推广关联表
			 */
			List<AGTPLink> linkList = AGTPLinkService.selectByProxyGrade(id);
			for (AGTPLink link : linkList)
			{
				link.setIsopen((short) 0);
				AGTPLinkService.updateByPrimaryKeySelective(link);
			}
			/**
			 * 编辑关联的第三方推广启用停用
			 */
			if (isopen != null)
			{
				/*
				 * 选中多个推广链接
				 */
				int iden = isopen.indexOf(",");
				if (iden != -1)
				{
					String[] arr = isopen.split(",");
					for (String idStr : arr)
					{
						int openID = Integer.parseInt(idStr);
						for (AGTPLink link : linkList)
						{
							if (openID == link.getId().intValue())
							{
								link.setIsopen((short) 1);
								AGTPLinkService.updateByPrimaryKeySelective(link);
								updateUserPromoThirdParty(upList, link);
								break;
							}
						}
					}
				} else
				{
					/*
					 * 只选中一个推广链接
					 */
					AGTPLink link = AGTPLinkService.selectByPrimaryKey(new BigDecimal(isopen));
					link.setIsopen((short) 1);
					AGTPLinkService.updateByPrimaryKeySelective(link);
					updateUserPromoThirdParty(upList, link);
				}
			}
			/*
			 * 推广等级编辑
			 */
			PromoAgentGradeProfit p = promoAgentGradeProfitService.selectByPrimaryKey(id);
			if (p.getProxygrade() != 0 && promoAgentGradeProfit.getProxygrade() != 0)
			{
				/*
				 * 编辑推广级别，等级从低变高 2-->8 proxyGrade 为修改后等级
				 */
				if (proxyGrade > p.getProxygrade())
				{
					List<PromoAgentGradeProfit> pagpList = promoAgentGradeProfitService
							.selective(null);
					for (PromoAgentGradeProfit pagp : pagpList)
					{
						// 判断等级相同，并且ID不一样
						if (pagp.getId().intValue() != id.intValue())
						{
							// 从推广级别修改前的下一个等级开始-1，直到目前编辑的等级
							if (pagp.getProxygrade() > p.getProxygrade()
									&& pagp.getProxygrade() <= proxyGrade)
							{
								pagp.setProxygrade(pagp.getProxygrade()-1);
								promoAgentGradeProfitService.updateByPrimaryKeySelective(pagp);
							}
						}
					}
				} else if (proxyGrade < p.getProxygrade())
				{
					/*
					 * 编辑推广级别，等级从高变低 8-->2 proxyGrade 为修改后等级
					 */
					List<PromoAgentGradeProfit> pagpList = promoAgentGradeProfitService
							.selective(null);
					for (PromoAgentGradeProfit pagp : pagpList)
					{
						// 判断ID不一样
						if (pagp.getId().intValue() != id.intValue())
						{
							// 编辑后的会员等级开始+1
							if (proxyGrade == pagp.getProxygrade())
							{
								proxyGrade++;
								pagp.setProxygrade(proxyGrade);
								promoAgentGradeProfitService.updateByPrimaryKeySelective(pagp);
							}
						}
					}
				}
				promoAgentGradeProfitService.updateByPrimaryKeySelective(promoAgentGradeProfit);
				sendJsonData(response, JSON.toJSONString("提示：操作成功！"));
			} else if (p.getProxygrade() == 0)
			{
				p.setProxygradename(proxyGradeName);
				promoAgentGradeProfitService.updateByPrimaryKeySelective(p);
				sendJsonData(response, JSON.toJSONString("提示：操作成功！"));
			}
		}
	}
	/**
	 * 编辑用户第三方推广
	 * 
	 * @param upList
	 * @param link
	 */
	private void updateUserPromoThirdParty(List<UserPromo> upList, AGTPLink link)
	{
		AgentGradePromoAuth agpa = agentGradePromoAuthService.selectByPrimaryKey(link.getAgpaid());
		for (UserPromo up : upList)
		{
			UserPromoThirdParty uptp = new UserPromoThirdParty();
			uptp.setUpid(up.getId());
			uptp.setThirdpartyname(agpa.getThirdpartyname());
			List<UserPromoThirdParty> uptpList = userPromoThirdPartyService.selective(uptp);
			for (UserPromoThirdParty uptp1 : uptpList)
			{
				uptp1.setIsopen((short) 1);
				userPromoThirdPartyService.updateByPrimaryKeySelective(uptp1);
			}
		}
	}
	/**
	 * 删除代理资质等级 UI
	 * 
	 * @param promoAgentGradeProfit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGrade_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delGrade_UI(PromoAgentGradeProfit promoAgentGradeProfit) throws Exception
	{
		promoAgentGradeProfit = promoAgentGradeProfitService
				.selectByPrimaryKey(promoAgentGradeProfit.getId());
		PromoAgentGradeProfit p = new PromoAgentGradeProfit();
		p.setProxygrade(promoAgentGradeProfit.getProxygrade());
		int size = promoAgentGradeProfitService.selective(p).size();
		ModelAndView mav = new ModelAndView();
		mav.addObject("size", size);
		mav.addObject("promoAgentGradeProfit", promoAgentGradeProfit);
		mav.setViewName("/admin/promoAgentGradeProfit/delete_promoAgentGrade");
		return mav;
	}
	/**
	 * 删除代理资质等级
	 * 
	 * @param promoAgentGradeProfit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGrade", method = {RequestMethod.POST, RequestMethod.GET})
	public void delGrade(PromoAgentGradeProfit promoAgentGradeProfit) throws Exception
	{
		BigDecimal id = promoAgentGradeProfit.getId();
		if (id != null)
		{
			/*
			 * 用户推广设置，推广等级被删除，统一改为默认
			 */
			UserPromo userPromo = new UserPromo();
			userPromo.setProxygrade(id);
			List<UserPromo> upList = userPromoService.selective(userPromo);
			for (UserPromo p : upList)
			{
				p.setProxygrade(new BigDecimal(23));
				userPromoService.updateByPrimaryKey(p);
			}
			/*
			 * 删除当前推广等级，后面的推广等级自增
			 */
			PromoAgentGradeProfit p = promoAgentGradeProfitService.selectByPrimaryKey(id);
			int proxygrade = p.getProxygrade();
			List<PromoAgentGradeProfit> pagpList = promoAgentGradeProfitService.selective(null);
			for (PromoAgentGradeProfit pagp : pagpList)
			{
				int grade = pagp.getProxygrade();
				if (proxygrade + 1 == grade)
				{
					pagp.setProxygrade(pagp.getProxygrade() - 1);
					promoAgentGradeProfitService.updateByPrimaryKeySelective(pagp);
					proxygrade++;
				}
			}
			promoAgentGradeProfitService.deleteByPrimaryKey(id);
			/*
			 * 删除第三方推广 AGTPLink 关联表
			 */
			AGTPLinkService.deleteByProxyGrade(id);
			// agentGradePromoAuthService.deleteByProxyGrade(id.intValue());
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
}
