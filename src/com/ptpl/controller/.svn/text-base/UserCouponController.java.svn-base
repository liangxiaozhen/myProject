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
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.UserInterestRateCouponServiceI;

/**
 * 用户后台-使用券明细
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/user/InterestRateCoupon")
public class UserCouponController extends BaseController {
	
	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;
	
	/**
	 * 查询用户的使用券明细
	 * @param coupon
	 * @return
	 */
	@RequestMapping("/queryMyVoucher")
	public ModelAndView queryMyVoucher (UserInterestRateCoupon coupon){
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
			coupon.setBaseid(baseid);
			List<UserInterestRateCoupon> nouseList = userInterestRateCouponService.findIsUseByBaseid(coupon); // 是否使用了使用券
			PageInfo<Object> pagehelper = initPagehelper(maps, nouseList);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("type", coupon.getIsuse()==null?0:coupon.getIsuse());
			mv.addObject("bptypemap", ActAward_Constant.RECTYPE_MAP); // 使用券获取方式类型MAP
			mv.addObject("uirctypemap", ActAward_Constant.UIRCTYPE_MAP); // 券的类型MAP
			mv.addObject("awardmap", ActAward_Constant.AWARD_MAP); // 券的状态
			mv.setViewName("user/account/InterestRateCouponDetail");
		}
		return mv;
	}
}
