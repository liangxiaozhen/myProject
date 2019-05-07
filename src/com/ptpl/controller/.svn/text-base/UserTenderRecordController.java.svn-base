package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ptpl.service.TenderItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.StringUtil;

/***
 * 用户后台-投资记录
 * @author admin
 *
 */
@Controller
@RequestMapping("/user/tender")
public class UserTenderRecordController extends BaseController {

	@Autowired
	UserTenderServiceI userTenderService;
	
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	@Autowired
	TenderItemServiceI tenderItemServiceI;
	
   /**
    * 查询我的投资记录
    * @param usertender
    * @return
    * @throws Exception
    */
	@RequestMapping("/queryMyTenderRecord")
	public ModelAndView queryMyTenderRecord(UserTender usertender) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 从session中获取当前登录用户基本信息
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		if (userAccountSafeInfo == null) {
			mv.setViewName("user/login");
		} else {
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> maps = new HashMap<String, Object>();
			initPage(maps, pageNum, pageSize);
			BigDecimal outaccountid = userAccountSafeInfo.getBaseid(); // 从用户账户信息安全表中获取投资方ID
			usertender.setOutaccountid(outaccountid);
			String type = request.getParameter("type");
			System.out.println("type======================"+type);
			TenderItem tenderItem = new TenderItem();
			if(type != null && !type.equalsIgnoreCase("0") && !type.equalsIgnoreCase("1")){
				tenderItem.setTstatus(new Short(type));
				usertender.setTenderitem(tenderItem);
			}
			Short indexStr = userAccountSafeInfo.getUgrade(); // 获取投资用户等级
			List<UserTender> tenderList = userTenderService.findMyTenderRecord(usertender);
			for(UserTender tender : tenderList){
				if(tender.getTstatus() == 1 && tender.getValuedate() == null){
					if(tender.getTenderitem().getIsacancel() != null && tender.getTenderitem().getIsacancel() == 1){ // 判断投标是否允许撤回
						char[] allowcugrade = tender.getTenderitem().getAllowcugrade().toCharArray();
						if(allowcugrade[indexStr-1] == '1'){ // 判断投资用户是否在允许撤销投标的会员等级中
							tender.setIsallowrevoke((short) 1);
						}
					}
				}else{
					tender.setIsallowrevoke((short) 0);
				}
			}
			PageInfo<Object> pagehelper = initPagehelper(maps, tenderList);
			mv.addObject("tpropertymaps", TenderRecord_Constant.TPROPERTY_MAP);
			mv.addObject("tstatusmaps", TenderRecord_Constant.TSTATUS_MAP);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("type", type==null?0:type);
			mv.setViewName("user/tenderRecord/tenderRecord");
		}
		return mv;
	}
	
	
	/**
	 * 查询最近交易
	 * @Title: financialRecords
	 * @Description: TODO(查询最近交易)
	 * @param usertender
	 * @throws Exceptio
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/financialRecords")
	public void financialRecords(UserTender usertender) throws Exception {
		// 从session中获取当前登录用户基本信息
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		if(userAccountSafeInfo != null){
			BigDecimal outaccountid = userAccountSafeInfo.getBaseid(); // 从用户账户信息安全表中获取投资方ID
			usertender.setOutaccountid(outaccountid);
			List<UserTender> tenderList = userTenderService.findMyTenderRecord(usertender);
			for(UserTender tender : tenderList){
				tender.setTbegintimeStr(sf.format(tender.getTbegintime()));
			}
			String data = JSON.toJSONString(tenderList);
			sendJsonData(response, data);
		}else{
			sendJsonData(response, "logout");
		}
	}
	
	
	/**
	 * 查询我的投资记录
	 * @param usertender
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myTenderRecord")
	public ModelAndView myTenderRecord(UserTender usertender) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 从session中获取当前登录用户基本信息
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		if(userAccountSafeInfo != null){
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> maps = new HashMap<String, Object>();
			initPage(maps, pageNum, pageSize);
			String lState = request.getParameter("lState");
			String dateType = request.getParameter("dateType");
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			System.out.println(lState);
			System.out.println(dateType);
			System.out.println(startdate);
			System.out.println(enddate);
			if (StringUtil.isNotEmpty(startdate)) {
				usertender.setStarttime((StringUtil.stringforDateTwo(startdate)));
				dateType = "none";
			}
			if (StringUtil.isNotEmpty(enddate)) {
				usertender.setEndtime(StringUtil.stringforDateTwo(enddate));
				dateType = "none";
			}
			BigDecimal outaccountid = userAccountSafeInfo.getBaseid(); // 从用户账户信息安全表中获取投资方ID
			TenderItem tenderitem = new TenderItem();
			short tstatus = 4;
			if(StringUtil.isEmpty(lState))
				lState = "1";
			if(StringUtil.isEmpty(dateType))
				dateType = "all";
			switch (lState) {
			case "1": 
				tstatus = 4;
				break;
			case "2": 
				tstatus = 4;
				break;
			case "3": 
				tstatus = 3;
				break;
			case "4": 
				tstatus = 5;
				break;
			case "5": 
				tstatus = 4;
				break;
			default:
				break;
			}
			usertender.setTime(dateType);
			if(!lState.equals("5")){
				tenderitem.setTstatus(tstatus);
				usertender.setTenderitem(tenderitem);
			}else{
				usertender.setTstatus((short) 2);
			}
			usertender.setOutaccountid(outaccountid);
			List<UserTender> tenderList = userTenderService.findMyTenderRecord(usertender);
			for(UserTender tender : tenderList){
				TenderItem tenderItem = tenderItemServiceI.findTenderItemById(tender.getTenderid());
				if(tenderItem!=null) {
					tender.setTenderNo(tenderItem.getTno());//通过标Id查询到标号
					tender.setTbegintimeStr(sf.format(tender.getTbegintime()));
				}
			}
			PageInfo<Object> pagehelper = initPagehelper(maps, tenderList);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("lState", lState);
			mv.addObject("dateType", dateType);
			mv.addObject("startdate", startdate);
			mv.addObject("enddate", enddate);
			mv.setViewName("user/manager/myacceptlist");
		}else{
			mv.setViewName("redirect:/user/tologin.action");
		}
		return mv;
	}
}
