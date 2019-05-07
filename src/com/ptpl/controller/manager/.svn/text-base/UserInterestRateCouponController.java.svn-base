package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 管理员后台-用户使用券
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/coupon")
public class UserInterestRateCouponController extends BaseController {

	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;

	/**
	 * （条件）获取用户使用券列表
	 * @param coupon
	 * @param coupons
	 * @param voucher
	 * @param status
	 * @param isuse
	 * @param isaudit
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCouponList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryCouponList(UserInterestRateCoupon coupon) throws Exception {
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String,Object> map = new HashMap<String, Object>();
		Integer num = 1;
		Integer size = 9;
		
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);
		PageHelper.startPage(num, size);
		map.put("pageSize", size);
		
		// 条件回显
		String loginname = request.getParameter("userBaseAccountInfo.loginname");
		if(StringUtil.isNullStr(loginname)){
			coupon.setUserBaseAccountInfo(null);
		}
		// 调用service层的方法得到对象列表
		if(coupon.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(coupon.getUserBaseAccountInfo().getLoginname())){
			coupon.getUserBaseAccountInfo().setLoginname(setEncrypt(coupon.getUserBaseAccountInfo().getLoginname()));
		}
		List<UserInterestRateCoupon> couponList = userInterestRateCouponService.findCouponList(coupon);
		//String icrate = "";
		for (UserInterestRateCoupon interest : couponList) {
			if(interest.getIctime() != null){
				interest.setIctimeStr(sf.format(interest.getIctime())); // 处理券发放时间
			}
			if(interest.getIcdealtime() != null){
				interest.setIcdealtimeStr(sf.format(interest.getIcdealtime())); // 处理券处理时间
			}
			if(interest.getIcfailtime() != null){
				interest.setIcfailtimeStr(sf.format(interest.getIcfailtime())); // 处理券失效时间
			}
			/*// 券利率百分比转换
			if (!StringUtil.isNullStr(interest.getIcrate())) {
				NumberFormat nf = NumberFormat.getPercentInstance(); // 获取显示百分比的格式
				nf.setMaximumIntegerDigits(3); // 设置数值的整数部分允许的最大位数
				nf.setMaximumFractionDigits(2); // 设置数值的小数部分允许的最大位数
				icrate = nf.format(interest.getIcrate());
			}*/
			if(interest.getUserBaseAccountInfo()!=null){
				interest.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(interest.getUserBaseAccountInfo()));
			}
		}

		PageInfo<Object> pagehelper = initPagehelper(map, couponList);

		if(coupon.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(coupon.getUserBaseAccountInfo().getLoginname())){
			coupon.getUserBaseAccountInfo().setLoginname(getDecrypt(coupon.getUserBaseAccountInfo().getLoginname()));
		}		
		
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("uirctypemaps", ActAward_Constant.UIRCTYPE_MAP); // 券的类型常量map
		mv.addObject("ictypemaps", ActAward_Constant.RECTYPE_MAP); // 券的来源常量map
		mv.addObject("statusmaps", ActAward_Constant.STATUS_MAP); // 券状态常量map
		mv.addObject("awardmaps", ActAward_Constant.AWARD_MAP); // 奖品状态常量map
		mv.addObject("isauditmaps", ActAward_Constant.ISAUDIT_MAP); // 是否审核常量map
		
		//mv.addObject("icrate", icrate); // 利率百分比
		mv.addObject("echodata", coupon);
		// 指定视图
		mv.setViewName("admin/Coupon/userinterestratecouponList");
		return mv;
	}

	/**
	 * 查看用户使用券详情
	 * @param id
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "queryCouponDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryCouponDetail(BigDecimal id,String text) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			// 调用service层的方法得到对象详情
			UserInterestRateCoupon detail = userInterestRateCouponService.findCouponDetailById(id);
			if(detail.getIctime() != null){
				detail.setIctimeStr(sf.format(detail.getIctime())); // 处理券发放时间
			}
			if(detail.getIcdealtime() != null){
				detail.setIcdealtimeStr(sf.format(detail.getIcdealtime())); // 处理券处理时间
			}
			if(detail.getIcfailtime() != null){
				detail.setIcfailtimeStr(sf.format(detail.getIcfailtime())); // 处理券失效时间
			}
			if(detail.getAudittime() != null){
				detail.setAudittimeStr(sf.format(detail.getAudittime())); // 处理审核时间
			}
			// 券利率百分比转换
			if (!StringUtil.isNullStr(detail.getIcrate())) {
				NumberFormat nf = NumberFormat.getPercentInstance(); // 获取显示百分比的格式
				nf.setMaximumIntegerDigits(3); // 设置数值的整数部分允许的最大位数
				nf.setMaximumFractionDigits(2); // 设置数值的小数部分允许的最大位数
				double icrate = detail.getIcrate();
				mv.addObject("icrate", nf.format(icrate));
			}
			if(detail.getUserBaseAccountInfo()!=null){
				detail.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(detail.getUserBaseAccountInfo()));
			}
			// 返回ModelAndView
			mv.addObject("detail", detail);
			mv.addObject("uirctype", ActAward_Constant.UIRCTYPE_MAP.get(detail.getUirctype())); //券的类型
			mv.addObject("ictype", ActAward_Constant.RECTYPE_MAP.get(detail.getIctype())); // 券的来源
			mv.addObject("status", ActAward_Constant.STATUS_MAP.get(detail.getStatus())); // 券状态转换
			mv.addObject("isuse", ActAward_Constant.AWARD_MAP.get(detail.getIsuse())); // 是否使用
			mv.addObject("isaudit", ActAward_Constant.ISAUDIT_MAP.get(detail.getIsaudit())); // 是否审核
			// 指定视图
			if(text.trim().equals("查看详情")){
				mv.setViewName("admin/Coupon/userinterestratecouponDetail");
			} /*else if(text.trim().equals("审核")){
				mv.setViewName("admin/Coupon/UserInterestrateCoupon_Audit");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return mv;
	}
	
	/**
	 * 审核通过
	 * @param id
	 * @param red
	 */
	/*@RequestMapping("/auditPass")
	public void auditPass(UserInterestRateCoupon coupon) throws Exception {
		// 调用service 修改审核状态
		try {
			AdminUser admin=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			int rows = 0;
			coupon.setIsaudit(Red_Constant.ISAUDIT_YES);
			coupon.setStatus(Red_Constant.STATUS_NORMAL);
			coupon.setAuditman(admin.getUsername());
			coupon.setAudittime(new Date());
			rows = userInterestRateCouponService.updateByPrimaryKeySelective(coupon);
			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("result", "审核通过！");
			} else {
				map.put("result", "审核失败！");
			}
			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}*/
	
	/**
	 * @author pxl
	 * 将券作废掉
	 * @throws IOException 
	 */
	@RequestMapping("/couponInvalid")
	public void ticketInvalid(HttpServletResponse response,UserInterestRateCoupon coupon) throws IOException{
		coupon.setIsuse((short)6);
		int rows=userInterestRateCouponService.updataCouponById(coupon);
		Map<String,String> map = new HashMap<String,String>();
		if(rows>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr = JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
}
