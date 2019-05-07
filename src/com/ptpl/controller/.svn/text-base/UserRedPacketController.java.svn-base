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
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserRedEnvelopeServiceI;

/**
 * 用户后台-用户红包
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/user/userPacket")
public class UserRedPacketController extends BaseController {
	
	/**
	 * 用户红包service
	 */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	@RequestMapping("/queryMyRedPacket")
	public ModelAndView queryMyRedPacket(UserRedEnvelope userRedEnvelope){
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
			userRedEnvelope.setBaseid(baseid);
			List<UserRedEnvelope> redList = userRedEnvelopeService.selectByBaseid(userRedEnvelope);
			PageInfo<Object> pagehelper = initPagehelper(maps, redList);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("type", userRedEnvelope.getIsuse()==null?0:userRedEnvelope.getIsuse());
			mv.addObject("rectypemap", ActAward_Constant.RECTYPE_MAP);
			mv.addObject("awardmap", ActAward_Constant.AWARD_MAP);
			mv.setViewName("user/account/RedEnvelopeDetail");
		}
		return mv;
	}
}
