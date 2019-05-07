package com.ptpl.controller.manager;

import java.math.BigDecimal;
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
import com.ptpl.mapper.PromoAgentGradeProfitMapper;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 代理级别的推广权限(第三方推广设置) Controller
 * 
 * @author xiaoy
 *
 * @version 2016年10月28日 下午4:21:05
 */
@Controller
@RequestMapping("admin/agentGradePromoAuth")
@Scope("prototype")
public class AgentGradePromoAuthManagerController extends BaseController {
	@Autowired
	private AgentGradePromoAuthServiceI agentGradePromoAuthService;
	@Autowired
	private PromoAgentGradeProfitMapper promoAgentGradeProfitMapper;
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private AGTPLinkServiceI AGTPLinkService;
	/**
	 * 第三方推广设置界面
	 * 
	 * @param agentGradePromoAuth  查询条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAll(AgentGradePromoAuth agentGradePromoAuth)
	{
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<AgentGradePromoAuth> list = agentGradePromoAuthService.selective(agentGradePromoAuth);
		List<AgentGradePromoAuth> nameList = agentGradePromoAuthService.selective(null);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/agentGradePromoAuth/agentGradePromoAuth_list");
		mav.addObject("agentGradePromoAuth", agentGradePromoAuth);
		mav.addObject("nameList", nameList);
		mav.addObject("pagehelper", pagehelper);
		return mav;
	}
	/**
	 * 新增UI
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insert_UI()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/agentGradePromoAuth/insert_agentGradePromoAuth");
		return mav;
	}
	/**
	 * 新增第三方推广
	 * 
	 * @param thirdPartyName
	 * @param thirdPartyCode
	 * @throws Exception
	 */
	@RequestMapping(value = "insert", method = {RequestMethod.POST, RequestMethod.GET})
	public void insert(String thirdPartyName, String thirdPartyCode) throws Exception
	{
		/*
		 * 第三方推广链接地址和第三方推广名称不能为空
		 */
		if (StringUtil.isNotEmpty(thirdPartyCode) && StringUtil.isNotEmpty(thirdPartyName))
		{
			String data = "fail";
			AgentGradePromoAuth agentGradePromoAuth = new AgentGradePromoAuth();
			agentGradePromoAuth.setThirdpartyname(thirdPartyName);
			/*
			 * 判断第三方推广名字是否重复
			 */
			int size = agentGradePromoAuthService.selective(agentGradePromoAuth).size();
			if (size == 0)
			{
				data = "success";
				/*
				 * 用户个人推广开关（三级开关）
				 */
				List<UserPromo> userPromos = userPromoService.getID();
				for (UserPromo userPromo : userPromos)
				{
					UserPromoThirdParty uptp = new UserPromoThirdParty();
					uptp.setUpid(userPromo.getId());
					uptp.setIsopen((short) 1);
					uptp.setThirdpartycode(thirdPartyCode);
					uptp.setThirdpartyname(thirdPartyName);
					userPromoThirdPartyService.insertSelective(uptp);
				}
				/*
				 * 第三方推广设置插入
				 */
				agentGradePromoAuth.setThirdpartycode(thirdPartyCode);
				agentGradePromoAuth.setIsopen((short) 1);
				agentGradePromoAuthService.insertSelective(agentGradePromoAuth);
				/*
				 * 代理等级与第三方推广关联表插入数据
				 */
				BigDecimal agpaID = agentGradePromoAuth.getId();
				List<PromoAgentGradeProfit> pagpList = promoAgentGradeProfitMapper.getGradeName();
				for (PromoAgentGradeProfit pagp : pagpList)
				{
					AGTPLink link = new AGTPLink();
					link.setAgpaid(agpaID);
					link.setProxygrade(pagp.getId().intValue());
					link.setIsopen((short) 1);
					AGTPLinkService.insertSelective(link);
				}
			}
			sendJsonData(response, JSON.toJSONString(data));
		}
	}
	/**
	 * 启用推广链接
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "isUse", method = {RequestMethod.POST, RequestMethod.GET})
	public void isUse(BigDecimal id) throws Exception
	{
		if (id != null)
		{	/*
			*第三方推广设置（一级开关）
			*/
			AgentGradePromoAuth agentGradePromoAuth = agentGradePromoAuthService
					.selectByPrimaryKey(id);
			agentGradePromoAuth.setIsopen((short) 1);
			agentGradePromoAuthService.updateByPrimaryKeySelective(agentGradePromoAuth);
			/*
			 * 代理等级第三方推广关联（二级开关）
			 */
			List<AGTPLink> AGTPList = AGTPLinkService.selectByAGPAID(id);
			for (AGTPLink link : AGTPList)
			{
				if (link.getIsopen().equals((short) 0))
				{
					link.setIsopen((short) 1);
					AGTPLinkService.updateByPrimaryKeySelective(link);
				}
			}
			/*
			 * 用户个人推广设置（三级开关）
			 */
			UserPromoThirdParty uptp = new UserPromoThirdParty();
			uptp.setThirdpartyname(agentGradePromoAuth.getThirdpartyname());
			List<UserPromoThirdParty> uptpList = userPromoThirdPartyService.selective(uptp);
			for (UserPromoThirdParty uptp1 : uptpList)
			{
				if (uptp1.getIsopen().equals((short) 0))
				{
					uptp1.setIsopen((short) 1);
					userPromoThirdPartyService.updateByPrimaryKeySelective(uptp1);
				}
			}
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
	/**
	 * 停用推广链接
	 * 
	 * @param id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "cancel", method = {RequestMethod.POST, RequestMethod.GET})
	public void cancel(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			/*
			 * 第三方推广设置开关（一级开关）
			 */
			AgentGradePromoAuth agentGradePromoAuth = agentGradePromoAuthService
					.selectByPrimaryKey(id);
			agentGradePromoAuth.setIsopen((short) 0);
			agentGradePromoAuthService.updateByPrimaryKeySelective(agentGradePromoAuth);
			/*
			 * 代理等级第三方推广关联（二级开关）
			 */
			List<AGTPLink> AGTPList = AGTPLinkService.selectByAGPAID(id);
			for (AGTPLink link : AGTPList)
			{
				if (link.getIsopen().equals((short) 1))
				{
					link.setIsopen((short) 0);
					AGTPLinkService.updateByPrimaryKeySelective(link);
				}
			}
			/*
			 * 用户个人推广开关（三级开关）
			 */
			UserPromoThirdParty uptp = new UserPromoThirdParty();
			uptp.setThirdpartyname(agentGradePromoAuth.getThirdpartyname());
			List<UserPromoThirdParty> uptpList = userPromoThirdPartyService.selective(uptp);
			for (UserPromoThirdParty uptp1 : uptpList)
			{
				if (uptp1.getIsopen().equals((short) 1))
				{
					uptp1.setIsopen((short) 0);
					userPromoThirdPartyService.updateByPrimaryKeySelective(uptp1);
				}
			}
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
	/**
	 * 删除UI
	 * 
	 * @param thirdPartyName
	 * @return
	 */
	@RequestMapping(value = "del_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView del_UI(BigDecimal id, String thirdPartyName)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.addObject("thirdPartyName", thirdPartyName);
		mav.setViewName("admin/agentGradePromoAuth/delete_agentGradePromoAuth");
		return mav;
	}
	/**
	 * 删除
	 * 
	 * @param thirdPartyName
	 * @throws Exception
	 */
	@RequestMapping(value = "del", method = {RequestMethod.POST, RequestMethod.GET})
	public void del(BigDecimal id, String thirdPartyName) throws Exception
	{
		if (StringUtil.isNotEmpty(thirdPartyName))
		{
			// 根据第三方推广名称删除第三方推广设置
			agentGradePromoAuthService.deleteByPrimaryKey(id);
			// 删除推广资质与第三方推广关联表（二级开关）
			AGTPLinkService.deleteByAGPAID(id);
			// 根据第三方推广名称删除用户第三方推广设置
			userPromoThirdPartyService.deleteByThirdPartyName(thirdPartyName);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
	/**
	 * 编辑UI
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView update_UI(BigDecimal id)
	{
		if (id != null)
		{
			AgentGradePromoAuth agpa = agentGradePromoAuthService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("agpa", agpa);
			mav.setViewName("admin/agentGradePromoAuth/update_agentGradePromoAuth");
			return mav;
		}
		return null;
	}
	/**
	 * 编辑修改第三方推广设置
	 * 
	 * @param agpa 
	 * @param name 旧名称
	 */
	@RequestMapping(value = "update", method = {RequestMethod.POST, RequestMethod.GET})
	public void update(AgentGradePromoAuth agpa, String name) throws Exception
	{
		if (agpa != null)
		{
			String data = "fail";
			AgentGradePromoAuth agentGradePromoAuth = new AgentGradePromoAuth();
			agentGradePromoAuth.setThirdpartyname(agpa.getThirdpartyname());
			/*
			 * 判断第三方推广名字是否重复
			 */
			int size = agentGradePromoAuthService.selective(agentGradePromoAuth).size();
			if (size == 0)
			{
				data = "success";
				agentGradePromoAuthService.updateByPrimaryKeySelective(agpa);
				/*
				 * 编辑修改用户个人推广（三级开关）
				 */
				userPromoThirdPartyService.updateNameAndCode(name,
						agpa.getThirdpartycode(), agpa.getThirdpartyname());
			}
			sendJsonData(response, JSON.toJSONString(data));
		}
	}
}
