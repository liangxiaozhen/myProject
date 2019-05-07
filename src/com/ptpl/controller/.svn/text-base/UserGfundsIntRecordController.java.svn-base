package com.ptpl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.constant.GfundsIntRecord_Constant;
import com.ptpl.model.GfundsIntRecord;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.GfundsIntRecordServiceI;

/**
 * 用户后台-标的站岗利息记录
 * @ClassName: UserGfundsIntRecordController
 * @Description: TODO(用户后台-标的站岗利息记录)
 * @author zhenglm
 * @date 2016年8月21日 下午2:49:18
 *
 */
@Controller
@RequestMapping("/user/gfundsIntNotes")
public class UserGfundsIntRecordController extends BaseController {
	
	/** 标的站岗利息记录Service */
	@Autowired
	GfundsIntRecordServiceI gfundsIntRecordService;
	
	/**
	 * 查询当前用户的标的站岗利息记录
	 * @Title: queryMyGfundsIntNotes
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param funds
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/queryMyGfundsIntNotes")
	public ModelAndView queryMyGfundsIntNotes(GfundsIntRecord funds){
		ModelAndView mv = new ModelAndView();
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if(userBaseAccountInfo != null){
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> map = new HashMap<String, Object>();
			initPage(map, pageNum, pageSize);
			funds.setInvestorid(userBaseAccountInfo.getId());
			List<GfundsIntRecord> myFundsList = gfundsIntRecordService.findGfundsIntRecordByInvestorId(funds);
			PageInfo<Object> pagehelper = initPagehelper(map, myFundsList);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("isgrantmap", GfundsIntRecord_Constant.ISGRANT_MAP);
			mv.addObject("isauditmap", GfundsIntRecord_Constant.ISAUDIT_MAP);
			mv.addObject("df", df);
			mv.addObject("echodata", funds);
		}
		mv.setViewName("user/manager/usertender/gfundsIntRecord");
		return mv;
	}
}
