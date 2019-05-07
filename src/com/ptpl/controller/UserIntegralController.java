package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.service.UserBonusPointsServiceI;

/**
 * 用户后台-用户积分
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/user/userPoints")
public class UserIntegralController extends BaseController {
	
	/**
	 * 用户积分service
	 */
	@Autowired
	UserBonusPointsServiceI userBonusPointsService;
	
	@RequestMapping("/queryMyBonusPoints")
	public ModelAndView queryMyBonusPoints(UserBonusPoints points){
		ModelAndView mv = new ModelAndView();
		if(getUserAccountSafeInfo().getBaseid() == null){
			mv.setViewName("user/login");
		}else{
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> maps = new HashMap<String, Object>();
			initPage(maps, pageNum, pageSize);
			// 从session中获取用户安全信息
			BigDecimal baseid = getUserAccountSafeInfo().getBaseid();
			points.setBaseid(baseid);
			List<UserBonusPoints> pointsList = userBonusPointsService.findPointsByBaseid(points);
			PageInfo<Object> pagehelper = initPagehelper(maps, pointsList);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("type", points.getStatus()==null?0:points.getStatus());
			mv.addObject("bptypemap", ActAward_Constant.RECTYPE_MAP);
			mv.addObject("statusmap", ActAward_Constant.STATUS_MAP);
			mv.setViewName("user/account/BonusPointsDetail");
		}
		return mv;
	}

}
