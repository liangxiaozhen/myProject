package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserBonus_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserBonusPointsServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 管理员后台-用户积分
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/points")
public class UserBonusPointsController extends BaseController {

	/**
	 * 用户积分Service
	 */
	@Autowired
	UserBonusPointsServiceI userBonusPointsService;

	/**
	 * （条件）查询用户积分列表
	 */
	@RequestMapping(value = "/queryPointsList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryPointsList(HttpServletRequest request,UserBonusPoints ubp)throws Exception {
		
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int num = 1;
		int size = 9;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);
		PageHelper.startPage(num, size);

		// 调用service层的方法得到对象列表
		if(ubp.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(ubp.getUserBaseAccountInfo().getLoginname())){
			ubp.getUserBaseAccountInfo().setLoginname(setEncrypt(ubp.getUserBaseAccountInfo().getLoginname()));
		}
		List<UserBonusPoints> pointsList = userBonusPointsService.getfindBonusPointsList(ubp);
		for(UserBonusPoints u:pointsList){
			if(u.getUserBaseAccountInfo()!=null){
				u.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(u.getUserBaseAccountInfo()));
				//System.out.println(u.getUserBaseAccountInfo().getLoginname());
			}
		}
		PageInfo<UserBonusPoints> pagehelper = new PageInfo<UserBonusPoints>(pointsList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		if(ubp.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(ubp.getUserBaseAccountInfo().getLoginname())){
			ubp.getUserBaseAccountInfo().setLoginname(getDecrypt(ubp.getUserBaseAccountInfo().getLoginname()));
		}
		
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		// 条件回显
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("ubp",ubp);
	
		modelAndView.addObject("rectypemap",UserBonus_Constant.RECTYPE_MAP);//积分来源
		modelAndView.addObject("statusmap",UserBonus_Constant.STATUS_MAP);//积分状态
		//modelAndView.addObject("isauditmaps",UserBonus_Constant.ISAUDIT_MAP);//是否审核
		// 指定视图
		modelAndView.setViewName("admin/Points/userbonuspointsList");
		return modelAndView;
	}

	/**
	 * 查看用户积分详情
	 */
	@RequestMapping(value = "/queryPointsDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryPointsDetail(BigDecimal id,String text) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			// 调用service层的方法得到对象详情
			UserBonusPoints detail = userBonusPointsService.findPointsDetailById(id);
			if (detail.getBpstime() != null) {
				detail.setBpstimeStr(sf.format(detail.getBpstime())); // 处理积分发放时间
			}
			if (detail.getBpdealtime() != null) {
				detail.setBpdealtimeStr(sf.format(detail.getBpdealtime())); // 处理积分处理时间
			}
			if (detail.getAudittime() != null) {
				detail.setAudittimeStr(sf.format(detail.getAudittime())); // 处理积分审核时间
			}
			if(detail.getUserBaseAccountInfo()!=null&&detail.getUserBaseAccountInfo().getLoginname()!=null&&!"".equals(detail.getUserBaseAccountInfo())){
				detail.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(detail.getUserBaseAccountInfo()));
			}
			mv.addObject("pointdetail", detail);
			mv.addObject("bptype", UserBonus_Constant.RECTYPE_MAP.get(detail.getBptype())); // 积分来源MAP
			mv.addObject("status", UserBonus_Constant.STATUS_MAP.get(detail.getStatus())); // 积分状态MAP
			mv.addObject("isaudit", UserBonus_Constant.ISAUDIT_MAP.get(detail.getIsaudit())); // 是否审核MAP
			if(text.trim().equals("查看详情")){
				mv.setViewName("admin/Points/UserBonusPoints_Detail");
			}/* else if(text.trim().equals("审核")){
				mv.setViewName("admin/Points/UserBonusPoints_Audit");
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
	@RequestMapping("/auditPass")
	public void auditPass(UserBonusPoints bonus) throws Exception {
		// 调用service 修改审核状态
		try {
			AdminUser admin=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			int rows = 0;
			bonus.setIsaudit((short) 0);
			bonus.setStatus((short) 1);
			bonus.setAuditman(admin.getUsername());
			bonus.setAudittime(new Date());
			rows = userBonusPointsService.updateByPrimaryKeySelective(bonus);
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
	}
	
}
