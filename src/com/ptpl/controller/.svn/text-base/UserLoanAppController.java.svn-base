package com.ptpl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.core.annotation.Token;
import com.ptpl.model.AheadRepayMode;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.loanapp;
import com.ptpl.service.AheadRepayModeServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.DateUtil;
import com.ptpl.web.util.DividedPaymentsComparator;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: UserLoanAppController 
* @Package com.ptpl.controller 
* @Description: TODO(借款记录 控制层) 
* @author cjm
* @date 2016年11月4日 上午11:31:17 
* @version V1.0
 */
@Controller
@RequestMapping("/user/userLoanApp")
public class UserLoanAppController extends BaseController{

	@Autowired
	private loanappServiceI loanappServiceI;
	@Autowired
	private TenderItemServiceI tenderItemServiceI;
	@Autowired
	private DividedPaymentsServiceI dividedPaymentsServiceI;
	@Autowired
	private RepayMentServiceI repayMentServiceI;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	@Autowired
	private AheadRepayModeServiceI aheadRepayModeServiceI;
	@Autowired
	private QueryBlaneServiceI queryBlaneService;
 	
	@Autowired
	private RepayMentBaseDealI repayMentBaseDealI;//还款通用处理接口
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	
	@Autowired
	private UserAccountServiceI userAccountService;
	/**
	 * 
	* @Title: list 
	* @Description: TODO(显示还款标的记录 列表) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		String status = request.getParameter("status");
		int num = 1;
		int pageSize = 20;
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		Map<String,Object> hashMap = new HashMap<>();
		if(StringUtil.isNotEmpty(status) && status.equals("1")){//还款中
			hashMap.put("tstatus", (short)7);
		}else if(StringUtil.isNotEmpty(status) && status.equals("2")){//已还款
			hashMap.put("tstatus", (short)8);
		}
		hashMap.put("baseid", userBaseAccountInfo.getId());
 		List<TenderItem> tenderItems = tenderItemServiceI.getTenderItemsLeftJoinLoanAppByCondition(hashMap);
 		
 		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
		//查询本地账户表
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userBaseAccountInfo.getId());
  		if(ufs!=null && ufs.getIsopenfsinfo()==1){
			Map<String,String> map= queryBlaneService.queryBlane(AES.getDecrypt(ufs.getUsrcustid()));//获取到余额查询的返回
			if(map.size() > 0){
				//更新本地用户账户表记录
				userAccount.setBalance(Double.valueOf(map.get("CURR_BAL")));
				userAccount.setAvlbalance( Double.valueOf(map.get("AVAIL_BAL")));
				userAccount.setFreezebalance(Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL"))));
				userAccountService.updateUserAccountBalAvl(userAccount);
 			} 
		}
 		
 		PageInfo<TenderItem> pagehelper = new PageInfo<TenderItem>(tenderItems);
		pagehelper.setFirstPage(1);
     	 int lasePageNum = 0;
     	 if(pagehelper.getTotal() % pageSize ==0){
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
     	 }else{
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
     	 }
 		ModelAndView modelAndView = new ModelAndView();
 		modelAndView.setViewName("user/manager/repayment/repayment");
		
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("status", status);
		modelAndView.addObject("userAccount", userAccount);
    	return modelAndView;
	}
	
	/**
	 * 
	* @Title: listTemplate 
	* @Description: TODO(还款list记录 分页) 
	* @param @param request
	* @param @param response
	* @param @param loanapp
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value="/listTemplate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView listTemplate(HttpServletRequest request,HttpServletResponse response,loanapp loanapp){
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		String pageNo = request.getParameter("pageNo");
		String pSize = request.getParameter("pageSize");
 		if(userBaseAccountInfo != null){
 			int num = 1;
			int pageSize = 20;
			if(StringUtil.isNotEmpty(pageNo)){
				num = Integer.valueOf(pageNo);
			}
			
			if(StringUtil.isNotEmpty(pSize)){
				pageSize = Integer.valueOf(pSize);
			}
			
			String sort = "id.desc";
			Order.formString(sort);
			PageHelper.startPage(num, pageSize);
			
			String status = request.getParameter("status");
			Map<String,Object> hashMap = new HashMap<>();
			if(StringUtil.isNotEmpty(status) && status.equals("1")){//还款中
				hashMap.put("tstatus", (short)7);
			}else if(StringUtil.isNotEmpty(status) && status.equals("2")){//已还款
				hashMap.put("tstatus", (short)10);
			}
 			hashMap.put("baseid", userBaseAccountInfo.getId());
	 		List<TenderItem> tenderItems = tenderItemServiceI.getTenderItemsLeftJoinLoanAppByCondition(hashMap);

			PageInfo<TenderItem> pagehelper = new PageInfo<TenderItem>(tenderItems);
			pagehelper.setFirstPage(1);
			int lasePageNum = 0;
			if(pagehelper.getTotal() % pageSize == 0){
				lasePageNum = (int)pagehelper.getTotal() / pageSize;
			}else{
				lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
			}
			pagehelper.setLastPage(lasePageNum);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("user/manager/repayment/listTemplateTenderItemDetail");
			modelAndView.addObject("pagehelper", pagehelper);
			return modelAndView;
		} 
		return null;
	}
	/**
	 * 
	* @Title: showAllTendItemDetail 
	* @Description: TODO(根据借款申请ID显示全部标的信息) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value="/showAllTendItemDetail",method=RequestMethod.POST)
	public String showAllTendItemDetail(HttpServletRequest request,HttpServletResponse response ,Model model){
		String opid = request.getParameter("opid");
		if(StringUtil.isNotEmpty(opid)){
			Map<String,Object> maps = new HashMap<String,Object>();
			maps.put("loanappid", new BigDecimal(opid));
			List<TenderItem> tenderItems = tenderItemServiceI.findTenderItemByCondition(maps);
			model.addAttribute("tenderItems", tenderItems);
			return "user/manager/repayment/listTemplateTenderItemDetail";
		}
 		return null;
		
	}
	
	/**
	 * 
	*  
	* @Description:TODO(根据标的ID显示标的全部具体还款计划信息) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value="/showRepaymentPlanDateil",method=RequestMethod.POST)
	@Token(save = true)
	public String showRepaymentPlanDateil(HttpServletRequest request,HttpServletResponse response ,Model model){
		String opid = request.getParameter("opid");//标的ID
		if(StringUtil.isNotEmpty(opid)){
			TenderItem tenderItem = tenderItemServiceI.findTenderItemById(new BigDecimal(opid));
			if(tenderItem != null){
				Short Isaaheadrepay = tenderItem.getIsaaheadrepay();//允许提前还款 1允许 0 不允许
				Short isapartrepay = tenderItem.getIsapartrepay();//是否支持部分还款 1允许 0 不允许
				AheadRepayMode aheadRepayMode = null;
				Short arepaymode = 1;//提前还款类型(1全部，2部分)
				Integer aperiods = 0;//1当期，多期（填写具体数字
 				if(Isaaheadrepay.equals((short)1)){//允许提前还款
					aheadRepayMode = aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);//提前还款设置
					if(aheadRepayMode != null){
 						arepaymode	   = aheadRepayMode.getArepaymode();//提前还款类型(1全部，2部分)
 						aperiods 	   = aheadRepayMode.getAperiods();
					}
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				/** 
				 * 1是否允许提前还款
 				 * 2显示提前还款 和正常还款 按钮操作
				 */

				Date date = new Date();//当期日期
//   			    Date date = StringUtil.getDateByString("2017-04-06","yyyy-MM-dd");//当前时间
				date = StringUtil.getDateByString(format.format(date), "yyyy-MM-dd");
				Date overdate = null;//格式化后的当期逾期宽限日期还款时间
				Date Repayday = null;//当期还款日
 				DividedPayments payments = new DividedPayments();
				payments.setTenderid(new BigDecimal(opid));
				boolean fale = false;
				List<DividedPayments> dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(payments);
				Collections.sort(dividedPayments, new DividedPaymentsComparator());
				List<RepayMent> repayMents3 = null;
				for(DividedPayments dividedPayment2 :dividedPayments){
					Map<String,Object> hashMap = new HashMap<String,Object>();
  					hashMap.put("tenderid", dividedPayment2.getTenderid());//标号ID
					hashMap.put("periods", dividedPayment2.getPeriods());//还款期数（第几期）
					hashMap.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
					hashMap.put("sumitrepayment", (short)1);//还款提交状态  1待处理 6还款失败 7审核失败
 					repayMents3 =  repayMentServiceI.findListRepayMentByConditions(hashMap);
 					if(repayMents3.size() > 0 && !dividedPayment2.getIscomplete().equals((short)1)){
 						Repayday = StringUtil.getDateByString(format.format(dividedPayment2.getRepayday()),"yyyy-MM-dd");
						if(Repayday.getTime() == date.getTime()){//正常还款日
							boolean falg = repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayment2);
							if(!falg){
								break;
							}
							if(isapartrepay.equals((short)1)){//是否支持部分还款 1允许 0 不允许
								dividedPayment2.setRepaybutton((short)6);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
							}else{
								dividedPayment2.setRepaybutton((short)1);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
							}
							break;
						}else if(Repayday.getTime() < date.getTime()){//判断是否是逾期宽限日内
							boolean falg = repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayment2);
							if(!falg){
								break;
							}
							overdate = StringUtil.getDateByString(format.format(DateUtil.getOverdueNumDay(tenderItem, dividedPayment2.getRepayday())),"yyyy-MM-dd");//格式化后的当期逾期宽限日期还款时间
 							if(overdate.getTime() >= date.getTime()){//宽限日期内部算逾期
								if(isapartrepay.equals((short)1)){//是否支持部分还款 1允许 0 不允许
									dividedPayment2.setRepaybutton((short)6);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
								}else{
									dividedPayment2.setRepaybutton((short)1);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
								}
								break;
							}else{//逾期了
								dividedPayment2.setIsoverdue((short)1);//是否逾期1已经逾期0没有逾期(标的截标时生成时默认0)
								dividedPaymentsServiceI.updateById(dividedPayment2);
								dividedPayment2. setRepaybutton((short)5);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期
								break;
							}
						}else if(Repayday.getTime() > date.getTime()){//允许提前还款 1允许 0 不允许
							overdate = StringUtil.getDateByString(format.format(DateUtil.getAheadperiodNumDay(tenderItem, dividedPayment2.getRepayday())),"yyyy-MM-dd");//格式化后的当期逾期宽限日期还款时间
							if(overdate.getTime() <= date.getTime()){
								boolean falg = repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayment2);
								if(!falg){
									break;
								}
								
								if(isapartrepay.equals((short)1)){//是否支持部分还款 1允许 0 不允许
									dividedPayment2.setRepaybutton((short)6);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
								}else{
									dividedPayment2.setRepaybutton((short)1);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期 6正常还款下一步
								}
								break;
							}
							
							if(Isaaheadrepay != null && Isaaheadrepay.equals((short)1)){
 								if(!fale){
									fale = true;
								}
								if(arepaymode.equals((short)1)){//允许全部提前
									dividedPayment2. setRepaybutton((short)3);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期
								}else{
									aperiods --;
									if(aperiods >= 0){
										dividedPayment2. setRepaybutton((short)3);//1正常还款 2未到还款日 3提前还款 4不允许提前还款 5已逾期
									}
								}
							}
						}
					} 
				}
				if(aheadRepayMode != null && fale){
					model.addAttribute("aheadRepayMode", aheadRepayMode);
				}
				String token = (String) WebUtils.getSessionAttribute(request, "SessToken");
				if(StringUtil.isNotEmpty(token)){
					model.addAttribute("token", token);
				}
				model.addAttribute("dividedPayments", dividedPayments);
				return "user/manager/repayment/listTemplateRepaymentPlanDateil";
			}
		}
 		return null;
		
	}
 	/**
	 * 
	* @Title: doRepayMentPartSelect 
	* @Description: TODO(正常还款 显示部分人给借款人(选择) )
  	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value="/doRepayMentPartSelect",method=RequestMethod.POST)
	@Token(save = true)
	public ModelAndView doRepayMentPartSelect(HttpServletRequest request,HttpServletResponse response){
		String opid = request.getParameter("opid");
 		if(StringUtil.isNotEmpty(opid)){
			DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
			if(dividedPayments != null
					&& dividedPayments.getTenderid() != null
					&& dividedPayments.getPeriods() != null){
   				 Map<String,Object> maps = new HashMap<String,Object>();
				 maps.put("tenderid", dividedPayments.getTenderid());//标的ID
				 maps.put("periods", dividedPayments.getPeriods());//期数
				 maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
				 maps.put("sumitrepayment", (short)1);//还款提交状态  1待处理 6还款失败 7审核失败
				 int num = 1;
				 int pageSize = 15;
				 String sort = "id.desc";
				 Order.formString(sort);
				 PageHelper.startPage(num, pageSize);
				 List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
				 for(RepayMent repayMent : repayMents){
					 UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
					 userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);
					 if(userBaseAccountInfo.getLoginname() != null){
						 repayMent.setUsername(userBaseAccountInfo.getLoginname());
					 }
				 }
			     PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
				 pagehelper.setFirstPage(1);
				 int lasePageNum = 0;
				 if(pagehelper.getTotal() % pageSize == 0){
					lasePageNum = (int)pagehelper.getTotal() / pageSize;
				 }else{
					lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
				 }
				 pagehelper.setLastPage(lasePageNum);
				 ModelAndView modelAndView = new  ModelAndView();
				 modelAndView.setViewName("user/manager/repayment/listTemplateNormalRepayMentSelect");
				 modelAndView.addObject("pagehelper", pagehelper);
				 modelAndView.addObject("opid", opid);
				 return modelAndView;
				 
  			}
		}
		return null;	
 	}
	
	/**
	 * 
	* @Title: listNormalTemplate 
	* @Description: TODO(正常还款 显示部分人给借款人(选择) 分页) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/listNormalTemplate")
	@Token(save = true)
	public ModelAndView listNormalTemplate (HttpServletRequest request,HttpServletResponse response){
 		String opid = request.getParameter("opid");
		DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
		if(dividedPayments == null){
			return null;
		}
		String pageNo = request.getParameter("pageNo");
		String pSize = request.getParameter("pageSize");
		int num = 1;
		int pageSize = 15;
		if(StringUtil.isNotEmpty(pageNo)){
			num = Integer.valueOf(pageNo);
		}
		
		if(StringUtil.isNotEmpty(pSize)){
			pageSize = Integer.valueOf(pSize);
		}
		
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		 Map<String,Object> maps = new HashMap<String,Object>();
		 maps.put("tenderid", dividedPayments.getTenderid());//标的ID
		 maps.put("periods", dividedPayments.getPeriods());//期数
		 maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
		 maps.put("sumitrepayment", (short)1);//还款提交状态  1待处理 6还款失败 7审核失败
		 List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
		 for(RepayMent repayMent : repayMents){
			 UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
			 userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);
			 if(userBaseAccountInfo.getLoginname() != null){
				 repayMent.setUsername(userBaseAccountInfo.getLoginname());
			 }
		 }
		PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % pageSize == 0){
			lasePageNum = (int)pagehelper.getTotal() / pageSize;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
		}
		pagehelper.setLastPage(lasePageNum);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/manager/repayment/listTemplateNormalRepayMentSelect");
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("opid", opid);
		return modelAndView;
  	}
}
