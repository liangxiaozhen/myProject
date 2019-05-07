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
import com.ptpl.constant.FailTCRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.FailTCRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.service.FailTCRecordServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 管理后台-标的流标补偿记录
 * @ClassName: FailTCRecordController
 * @Description: TODO(管理后台-标的流标补偿记录)
 * @author zhenglm
 * @date 2016年9月01日 下午3:14:13
 *
 */
@Controller
@RequestMapping("/admin/failTCRecord")
public class FailTCRecordController extends BaseController {
	
	/** 标的流标补偿记录Service */
	@Autowired
	FailTCRecordServiceI failTCRecordService;
	
	/** 标的设置Service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	@RequestMapping("/queryFailTCRecordLsit")
	public ModelAndView queryFailTCRecordLsit(FailTCRecord failtcrecord){
		ModelAndView mv = new ModelAndView();
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		if(failtcrecord.getUserbaseinfo()!=null && StringUtil.isNotEmpty(failtcrecord.getUserbaseinfo().getLoginname())){
			failtcrecord.getUserbaseinfo().setLoginname(AES.getEncrypt(failtcrecord.getUserbaseinfo().getLoginname()));
		}
		List<FailTCRecord> recordList = failTCRecordService.findFailTCRecordList(failtcrecord);
		for(FailTCRecord record : recordList){
			record.getUserbaseinfo().setLoginname(AES.getDecrypt(record.getUserbaseinfo().getLoginname()));
			failTCRecordService.dealTime(record);
		}
		PageInfo<Object> pagehelper = initPagehelper(map, recordList);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("df", df);
		mv.addObject("payouttypemap", FailTCRecord_Constant.PAYOUTTYPE_MAP);
		mv.addObject("isgrantmap", FailTCRecord_Constant.ISGRANT_MAP);
		mv.addObject("isauditmap", FailTCRecord_Constant.ISAUDIT_MAP);
		mv.addObject("echodata", failtcrecord);
		mv.setViewName("admin/failTCRecord/failTCRecord_List");
		return mv;
	}
	
	/**
	 * 跳转到标的流标补偿记录详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardView")
	public ModelAndView forwardView(BigDecimal id){
		ModelAndView mv = new ModelAndView();
		queryDetail(id, mv);
		mv.setViewName("admin/failTCRecord/failTCRecord_Detail");
		return mv;
	}
	
	/**
	 * 跳转到标的流标补偿记录审核页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardAudit")
	public ModelAndView forwardAudit(BigDecimal id){
		ModelAndView mv = new ModelAndView();
		queryDetail(id, mv);
		mv.setViewName("admin/failTCRecord/failTCRecord_Audit");
		return mv;
	}
	
	/**
	 * 查询标的流标补偿记录详情
	 * @param id
	 * @param mv
	 */
	private void queryDetail(BigDecimal id, ModelAndView mv){
		FailTCRecord detail = failTCRecordService.selectByPrimaryKey(id);
		if(detail!=null){
			detail.getUserbaseinfo().setLoginname(AES.getDecrypt(detail.getUserbaseinfo().getLoginname()));
		}
		failTCRecordService.dealTime(detail);
		mv.addObject("detail", detail);
		mv.addObject("payouttype", FailTCRecord_Constant.PAYOUTTYPE_MAP.get(detail.getPayouttype()));
		mv.addObject("createway", FailTCRecord_Constant.CREATEWAY_MAP.get(detail.getCreateway()));
		mv.addObject("isgrant", FailTCRecord_Constant.ISGRANT_MAP.get(detail.getIsgrant()));
		mv.addObject("isaudit", FailTCRecord_Constant.ISAUDIT_MAP.get(detail.getIsaudit()));
		mv.addObject("df", df);
	}
	
	/**
	 * 审核标的流标补偿记录操作
	 * @param record
	 */
	@RequestMapping("/audit")
	public void audit(FailTCRecord record){
		AdminUser admin = getAdminUser();
		Map<String,String> map = new HashMap<String,String>();
		try{
			if(admin.getUsername() != null && record != null && record.getIsaudit() != null){
				record.setAuditman(admin.getUsername());
				record.setAudittime(new Date());
				int rows = 0;
				rows = failTCRecordService.updateByPrimaryKeySelective(record);
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
