package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.RepayMent;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.service.DebtAttornServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserTenderServiceI;
/**
 * 后台管理员债转
 * @author admin
 *
 */
@Controller
@RequestMapping("/admin/userdebtattorn")
public class UserDebtAttornManagerController extends BaseController{
	@Autowired
	UserDebtAttornServiceI   userDebtAttornService;
	@Autowired
	DebtAttornServiceI   debtAttornService;
	@Autowired
	UserDebtAttornServiceI  userdebtattornService;
	@Autowired
	UserTenderServiceI userTenderService;
	@Autowired
	RepayMentServiceI repayMentService;
	/**
	 * 后台保存债转记录
	* @Title: saveObject 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param userDebtAttorn
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView saveObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// 获取登录的用户
			UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			ModelAndView mav = new ModelAndView();
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map map = new HashMap();
			
		    initPage(map, pageNum, pageSize);
			List<UserDebtAttorn>  userDebtAttornList = userDebtAttornService.getAllList(null);
			PageInfo<Object> pagehelper = initPagehelper(map, userDebtAttornList);
			if(user!=null){
				mav.setViewName("admin/userdebtattorn/rocrdList");
				return mav;
			}
			ModelAndView modelAndView  = new ModelAndView();
			modelAndView.addObject("pagehelper", pagehelper);
			
			modelAndView.addObject("df", df);
			return mav;
	}
	/**
	 * 后台显示债转列表
	* @Title: displayList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param userDebtAttorn
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/queryList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView displayList(HttpServletRequest request, HttpServletResponse response,UserDebtAttorn userdebtattorn) throws Exception {
		// 获取登录的用户
		AdminUser adminuser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		ModelAndView mav = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if(userdebtattorn.getSetstarttime()!=null){
			userdebtattorn.setSetstarttimeStr(sf.format(userdebtattorn.getSetstarttime()));
		}
		if(userdebtattorn.getSetendtime()!=null){
			userdebtattorn.setSetendtimeStr(sf.format(userdebtattorn.getSetendtime()));
		}
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		List<UserDebtAttorn>  userDebtAttornList = userDebtAttornService.getAllList(userdebtattorn);
		for (UserDebtAttorn userDebtAttorn2 : userDebtAttornList) {
			DebtAttorn dAttorn = debtAttornService.selectByTid(userDebtAttorn2.getTenderid());
			userDebtAttorn2.setDattrestrict(dAttorn.getDattrestrict().intValue());
		}
		PageInfo<Object> pagehelper = initPagehelper(map, userDebtAttornList);
		if(adminuser!=null){
			mav.setViewName("admin/userdebtattorn/rocrdList");
			mav.addObject("pagehelper", pagehelper);
			mav.addObject("userdebtattorn", userdebtattorn);
			mav.addObject("df", df);
		}
		return mav; 
	}
	/**
	 * 审核页面跳转
	* @Title: updateAudit 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param id
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateAudit(HttpServletRequest request, HttpServletResponse response,
			BigDecimal id) {
		UserDebtAttorn  ub  = userDebtAttornService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ub", ub);
		mav.addObject("df1", df1);
		mav.setViewName("admin/userdebtattorn/audit");
		return mav;
	}
	/**
	 * @throws Exception 
	 * 审核通过,修改债转状态,并且更新收支记录表和用户账户表
	* @Title: audit 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param id
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public void audit(HttpServletRequest request, HttpServletResponse response,
			BigDecimal  id) throws Exception {
		String data = "";
		String remark = request.getParameter("remark");
		UserDebtAttorn  userdebtattorn  = userDebtAttornService.selectByPrimaryKey(id);
		AdminUser adminuser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		UserTender userTender = userTenderService.findUserTenderByOrderno(userdebtattorn.getTorderno());
		DebtAttorn debtAttorn = debtAttornService.selectByTid(userdebtattorn.getTenderid());
		//当前时间:也就是债转时间
		Date d = new Date(); 
		//当前债转时
		if(userdebtattorn.getDaproperty()==1){//正常债转
			RepayMent repayMent = userdebtattornService.returnObject(userTender, d);
			int days = (int)((repayMent.getRtime().getTime()-d.getTime())/86400000);
			if(days>=debtAttorn.getIntervalday()){//判断距离下个还款日
				data="success";
			}else{
				data="false";
			}
		}else{
			data="success";
		}
		if(data.equals("success")){
			userdebtattorn.setDastatus((short)2);
			userdebtattorn.setRemark(remark);
			userdebtattorn.setAuditman(adminuser.getUsername());
			userdebtattorn.setAudittime(d);
			userDebtAttornService.updateByPrimaryKeySelective(userdebtattorn);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("userdebtattorn", userdebtattorn);
		mav.addObject("df1", df1);
		String jsonStr = JSON.toJSONString(data);
		sendJsonData(response, jsonStr);
	}
	/**
	 * 详情页面
	* @Title: details 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param daorderno
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/details", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView details(HttpServletRequest request, HttpServletResponse response,String daorderno) throws Exception {
		ModelAndView mav = new ModelAndView();
		UserDebtAttorn ub = userdebtattornService.getdaorderno(daorderno);
		mav.addObject("ub", ub);
		mav.setViewName("admin/userdebtattorn/details");
		mav.addObject("sf", sf);
		return mav;
		
	}
	
}
