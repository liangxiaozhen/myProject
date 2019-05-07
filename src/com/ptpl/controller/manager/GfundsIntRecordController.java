package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.GfundsIntRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.GfundsIntRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.service.GfundsIntRecordServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 管理后台-标的站岗利息记录controller
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/gfundsIntRecord")
public class GfundsIntRecordController extends BaseController {

	/** 标的站岗利息记录Service */
	@Autowired
	GfundsIntRecordServiceI gfundsIntRecordService;
	
	/** 标的设置Service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/**
	 * 查询标的站岗利息记录列表
	 * @param funds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryGfundsIntRecord")
	public ModelAndView queryGfundsIntRecord(GfundsIntRecord funds) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		if(funds.getInvestor()!=null && StringUtil.isNotEmpty(funds.getInvestor().getLoginname())){
			funds.getInvestor().setLoginname(AES.getEncrypt(funds.getInvestor().getLoginname()));
		}
		List<GfundsIntRecord> fundsList = gfundsIntRecordService.findGfundsIntRecord(funds);
		for(GfundsIntRecord fund : fundsList){
			fund.getInvestor().setLoginname(AES.getDecrypt(fund.getInvestor().getLoginname()));
			gfundsIntRecordService.dealTime(fund);
		}
		PageInfo<Object> pagehelper = initPagehelper(maps, fundsList);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("df", df);
		mv.addObject("payouttypemap", GfundsIntRecord_Constant.PAYOUTTYPE_MAP);
		mv.addObject("createwaymap", GfundsIntRecord_Constant.CREATEWAY_MAP);
		mv.addObject("isgrantmap", GfundsIntRecord_Constant.ISGRANT_MAP);
		mv.addObject("isauditmap", GfundsIntRecord_Constant.ISAUDIT_MAP);
		mv.addObject("echodata", funds);
		mv.setViewName("admin/gfundsIntRecord/GfundsIntRecord_List");
		return mv;
	}
	
	/**
	 * 转到标的站岗利息记录详情页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forwardView")
	public ModelAndView forwardView(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		queryDeatail(id, mv);
		mv.setViewName("admin/gfundsIntRecord/GfundsIntRecord_Detail");
		return mv;
	}
	
	/**
	 * 转发到标的站岗利息记录审核页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forwardAudit")
	public ModelAndView forwardAudit(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		queryDeatail(id, mv);
		mv.setViewName("admin/gfundsIntRecord/GfundsIntRecord_Audit");
		return mv;
	}
	
	/**
	 * 查询标的站岗利息记录详情
	 * @param id
	 * @param mv
	 */
	private void queryDeatail(BigDecimal id, ModelAndView mv){
		GfundsIntRecord detail = gfundsIntRecordService.selectByPrimaryKey(id);
		gfundsIntRecordService.dealTime(detail);
		mv.addObject("detail", detail);
		mv.addObject("df", df);
		mv.addObject("payouttype", GfundsIntRecord_Constant.PAYOUTTYPE_MAP.get(detail.getPayouttype()));
		mv.addObject("createway", GfundsIntRecord_Constant.CREATEWAY_MAP.get(detail.getCreateway()));
		mv.addObject("isgrant", GfundsIntRecord_Constant.ISGRANT_MAP.get(detail.getIsgrant()));
		mv.addObject("isaudit", GfundsIntRecord_Constant.ISAUDIT_MAP.get(detail.getIsaudit()));
	}
	
	/**
	 * 审核操作
	 * @param funds
	 */
	@RequestMapping("/audit")
	public void audit(GfundsIntRecord funds){
		AdminUser admin = getAdminUser();
		Map<String,String> map = new HashMap<String,String>();
		try {
			if(admin.getUsername() != null && funds != null && funds.getIsaudit() != null){
				funds.setAuditman(admin.getUsername());
				funds.setAudittime(new Date());
				int rows = 0;
				rows = gfundsIntRecordService.updateByPrimaryKeySelective(funds);
				if(rows>0){
					map.put("result", "success");
				}else{
					map.put("result", "fail");
				}
			}else{
				map.put("result", "error");
			}
			String str = JSON.toJSONString(map);
			sendJsonData(response, str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
