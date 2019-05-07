//package com.ptpl.controller.huifu;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.locks.ReentrantLock;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.ContentTooLongException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.fastjson.JSON;
//import com.huifu.util.SignUtils;
//import com.mysql.fabric.xmlrpc.base.Array;
//import com.ptpl.constant.Session_Constant;
//import com.ptpl.controller.BaseController;
//import com.ptpl.controller.SpringContextHolder;
//import com.ptpl.model.AheadRepay;
//import com.ptpl.model.AheadRepayMode;
//import com.ptpl.model.AheadRepayPlatform;
//import com.ptpl.model.DividedPayments;
//import com.ptpl.model.RepayMent;
//import com.ptpl.model.TenderItem;
//import com.ptpl.model.UserBaseAccountInfo;
//import com.ptpl.model.UserFsAccountInfo;
//import com.ptpl.model.loanapp;
//import com.ptpl.service.AheadRepayModeServiceI;
//import com.ptpl.service.DividedPaymentsServiceI;
//import com.ptpl.service.InterestExpenseServiceI;
//import com.ptpl.service.RepayMentServiceI;
//import com.ptpl.service.TenderItemServiceI;
//import com.ptpl.service.UserBaseAccountInfoServiceI;
//import com.ptpl.service.UserFsAccountInfoServiceI;
//import com.ptpl.service.UserTenderServiceI;
//import com.ptpl.service.loanappServiceI;
//import com.ptpl.web.util.DateUtil;
//import com.ptpl.web.util.DividedPaymentsComparator;
//import com.ptpl.web.util.HuifuParams;
//import com.ptpl.web.util.MD5;
//import com.ptpl.web.util.RepayMentLogicUtil;
//import com.ptpl.web.util.StringUtil;
//
///**
// * 
//* @ClassName: HuifuRepayMentController 
//* @Package com.ptpl.controller.huifu 
//* @Description: TODO(汇付天下 用户还款 Controller) 
//* @author chenjiaming
//* @date 2016年9月20日 上午11:07:13 
//* @version V1.0
// */
//@Controller
//@RequestMapping("/huifu/repayMent")
//public class HuifuRepayMentController2 extends BaseController{
//	
// 	 public static Log repayMentLog = LogFactory.getLog(HuifuRepayMentController2.class);
// 	 
//   	/**
//	 * 标的 service
//	 */
//	@Autowired
//	private TenderItemServiceI  tenderItemServiceI;
//	/**
//	 * 标的分期还款计划service
//	 */
//	@Autowired
//	private DividedPaymentsServiceI dividedPaymentsServiceI;
//	/**
//	 * 还款计划记录表service
//	 */
//	@Autowired
//	private RepayMentServiceI repayMentServiceI;
//   
//	/**
//	 * 第三方托管账号表
//	 */
//	@Autowired
//	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
//   
// 	 
//	/**
//	 * 
//	* @Title: doRepayMentNormal 
//	* @Description: TODO(正常还款 手动) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value="/doManualRepayMentNormal")
//	public String doManualRepayMentNormal(HttpServletRequest request,HttpServletResponse response){
//		/**
//		 * 正常还款 思路
//		 * 用户手动还款操作后进来了，查出还款期数信息，再判断余额是否够还款
//		 * 判断标的设置信息，还款类型是及时还款还是冻结还款，及时还款就调用还款接口，冻结还款就更改还款状态让客服审核
//		 * 及时还款：调用正常还款接口
//		 * 注意：汇付批量还款接口最多500笔 
//		 */
// 		String dividedPaymentsId = request.getParameter("opid");//还款计划ID
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
// 		if(accountInfo != null){
// 			DividedPayments dividedPayments = SpringContextHolder.getBean(DividedPaymentsServiceI.class)
//					.findDividedPaymentsById(new BigDecimal(dividedPaymentsId));
//			UserFsAccountInfo accountInfo2 = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class)
//					.findUserFsAccountInfoByBaseId(accountInfo.getId());
//			String AvlBal = repayMentServiceI.getAvlBalByUsrCustId(accountInfo2.getUsrcustid());//查询余额
// 			Map<String,String> hashMap = new HashMap<String,String>();
//			if(Double.valueOf(AvlBal) >= dividedPayments.getCurrentpi()){//够余额
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("outaccountid", accountInfo.getId());//借款用户ID
//				map.put("tenderid", dividedPayments.getTenderid());//标号ID
//				map.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
//				map.put("repaystatus", (short)1);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//				map.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			    Integer countRepayMents = repayMentServiceI.countRepayMentByConditions(map);//还款总数
//			    List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(map);
//			    if(repayMents.size() > 0){
//			    		HuifuBatchProcessingRepayMent(countRepayMents, repayMents, request, dividedPayments);//批量还款处理
// 					    int count = 0;
//					    dividedPayments.setIscomplete((short)2);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款(标的截标时生成时默认0)
//					    count = dividedPaymentsServiceI.updateById(dividedPayments);
//					    if(count > 0){
// 					    	hashMap.put("result", "success");
//					    }else{
//					    	hashMap.put("result", "fail");
//					    }
//			    }
//			}else{//余额不足
//				hashMap.put("result", "avlbal_deficiency");//余额不足
//			}
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return str;
// 		}
//		return null;
//	} 
//	
//	/**
//	 * 
//	* @Title: doNormalRepayMentPart 
//	* @Description: TODO(正常还款        部分还款  ) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value="/doNormalRepayMentPart",method=RequestMethod.POST)
//	public String doNormalRepayMentPart(HttpServletRequest request,HttpServletResponse response){
//		UserBaseAccountInfo userBaseAccountInfo = 	getUserBaseAccountInfo();
//		Map<String,String> hashMap = new HashMap<String,String>();
//		if(userBaseAccountInfo == null){//用户没有登录 或session 失效
//			hashMap.put("result", "logout");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		String opid = request.getParameter("opid");
//  		if(StringUtil.isEmpty(opid)){//参数为null
// 			hashMap.put("result", "opid_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		
//  		//数据有效检验结束
//  		String[] opids = opid.split(",");//分割后的投资人还款计划ID
//  		RepayMent repayMent = null;//投资人还款具体计划对象
//  		List<RepayMent> repayMents = new ArrayList<RepayMent>();//投资人还款具体计划对象集合
//  		Double principalCount = 0.00;//借款人本次选择还款的金额总数
//  		DecimalFormat df = new DecimalFormat("#################################0.00");
//  		for(int i = 0;i<opids.length;i++){
//  			repayMent = repayMentServiceI.findRepayMentById(new BigDecimal(opids[i]));//找到对应的投资人的还款具体计划
//  			if(repayMent != null){
//  				principalCount = Double.valueOf(df.format(
//  						repayMent.getRamount() + repayMent.getRinterest() + 
//  						repayMent.getRvoucher()+ repayMent.getRvoucherint() + 
//  						repayMent.getDisablevoucher() + repayMent.getDisablevoucherint() +
//  						principalCount));//借款人还款金额  = 本金+利息+类现金+类现金利息+作废的类现金+作废的类现金利息
//   				repayMents.add(repayMent);//装进集合
//  			}
//  		}
//  		
//  		if(repayMents.size() > 0){
//  			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.
//  					findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
//  			String avlBal = repayMentServiceI.getAvlBalByUsrCustId(userFsAccountInfo.getUsrcustid());//借款人账号可用余额
//  			if(Double.valueOf(avlBal)  < principalCount){//借款人账号可用余额不足
//  				hashMap.put("result", "avlbal_insufficient");
//  				String str = JSON.toJSONString(hashMap);
//  				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
// 					e.printStackTrace();
//				}
//  				return null;
//  			}
//  			Map<String,Object> hashMap2 = new HashMap<String,Object>();
//  			hashMap2.put("periods", repayMents.get(0).getPeriods());
//  			hashMap2.put("tenderid", repayMents.get(0).getTenderid());
//   			DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap2);
//   			HuifuBatchProcessingRepayMent(repayMents.size(), repayMents, request, dividedPayments);//批量还款处理
//			int count = 0;
//		    dividedPayments.setIscomplete((short)3);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款(标的截标时生成时默认0)
//		    count = dividedPaymentsServiceI.updateById(dividedPayments);
//		    if(count > 0){
//			   hashMap.put("result", "success");
//		    }else{
//		    	hashMap.put("result", "fail");
//		    }
//		    String str = JSON.toJSONString(hashMap);
//		    try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//     	}else{
//  			hashMap.put("result", "repay_null");//没有找到还款对象
//  			String str = JSON.toJSONString(hashMap);
//  			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//   		}
//		return null;
//	}
//	/**
//	 * 
//	* @Title: doAheadRepayMent 
//	* @Description: TODO(提前还款) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	
//	@RequestMapping(value="/doAheadRepayMent",method=RequestMethod.POST)
//	public String doAheadRepayMent(HttpServletRequest request,HttpServletResponse response){
// 		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//		Map<String,String> hashMap = new HashMap<String,String>();
//		if(accountInfo == null){//用户米有登录
//			hashMap.put("result", "logout");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
// 		@SuppressWarnings("unchecked")
//		Map<String,String> hashMap2 = (HashMap<String, String>) request.getSession().getAttribute(Session_Constant.AHEADREPAY_CALCULATE);
// 		if(hashMap2 == null){//session 失效
// 			hashMap.put("result", "session_exit");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 		
// 	    String opid = hashMap2.get("opids");
// 	    if(StringUtil.isEmpty(opid)){//参数非法
// 	    	hashMap.put("result", "params_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 	    }
// 	    
// 	    String[] opids = opid.split(",");
// 	    List<DividedPayments> dividedPayments = new ArrayList<>();//还款期数集合
// 	    DividedPayments dividedPayment2 = null;
//  	    for(int i = 0;i<opids.length;i++){
//  	    	dividedPayment2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opids[i]));
// 	    	dividedPayments.add(dividedPayment2);
// 	    }
//  	    
//  	    if(!(dividedPayments.size() > 0)){//找不到标的还款计划
//  	    	hashMap.put("result", "dividedPayments_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//  	    }
//   	    /**********验证结束**********/
//   	    
// 		 Collections.sort(dividedPayments,new DividedPaymentsComparator());//排序
// 		 DividedPayments dividedPayment = dividedPayments.get(0);//还款当期
// 		 TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的信息
// 		 if(tenderItem == null ){//没有找到标的信息
// 			hashMap.put("result", "tenderItem_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 		 
//  		AheadRepayMode aheadRepayMode = AheadOracleTest.getAheadRepayMode();//提前还款设置
// 		if(aheadRepayMode == null 
// 				|| aheadRepayMode.getArepaymode() == null
// 				|| aheadRepayMode.getIntmode() == null){//没有找到标的提前还款设置
// 			hashMap.put("result", "aheadRepayMode_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 		
// 		if(aheadRepayMode.getIntmode().equals((short)1)){//还利息方式(1占天利息，2全额利息)
// 			Date date = new Date();
// 			List<AheadRepayPlatform> aheadRepayPlatforms 	=  AheadOracleTest.getAheadRepayPlatform();//标的提前还款奖励平台设置
//			List<AheadRepay> aheadRepays 				    =  AheadOracleTest.getAheadRepay();//提前还款补偿投资人
//			
//			Integer aheadDay 								= 0;//持有天数
//			Integer periodsDay 								= 0;//当期总天数
//			if(dividedPayment.getPeriods() == 1){//如果是一期
//				Date startDate = StringUtil.
//						getValuedate(tenderItem.getValuedate(), tenderItem.getValuepoint(), tenderItem.getValuerule());
//				aheadDay = (int) DateUtil.getDateDifference(startDate, date);//持有天数
//				Integer aheadDay2 = (int) DateUtil.getDateDifference(date, dividedPayment.getRepayday());
//				periodsDay = aheadDay + aheadDay2;
//	 		}else{
//	 			Map<String,Object> hashMap3 = new HashMap<String,Object>();
//	 			hashMap3.put("tenderid", dividedPayment.getTenderid());//标ID
//	 			hashMap3.put("periods", dividedPayment.getPeriods() - 1);//期数
//	  			DividedPayments dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap3);
//	  			aheadDay = (int) DateUtil.getDateDifference(dividedPayments2.getRepayday(), date);//持有天数
//	  			Integer aheadDay2 = (int) DateUtil.getDateDifference(date, dividedPayment.getRepayday());
//				periodsDay = aheadDay + aheadDay2;//当期的总天数
//			}
//			
//			Double dayDou 	= new BigDecimal(1).divide(new BigDecimal(periodsDay.toString()),10,BigDecimal.ROUND_DOWN).doubleValue();//K = 1 / 月总天数
//			
//			
// 		}else if(aheadRepayMode.getIntmode().equals((short)2)){//2全额利息
// 			
// 		}
// 		return null;
//	}
//	/**
//	 * 
//	* @Title: doAheadRepaymentByCalculate 
//	* @Description: TODO(提前还款 手动提前 计算罚金) 
//	* @param @return  参数说明 
//	* @return String  返回类型 
//	* @author cjm
//	* @throws
//	 */ 
//	@RequestMapping(value="/doAheadRepaymentByCalculate",method = RequestMethod.POST)
//	public String doAheadRepaymentByCalculate(HttpServletRequest request,HttpServletResponse response){
//		Map<String,String> hashMap = new HashMap<String,String>();
//		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
//		if(baseAccountInfo == null){
// 			hashMap.put("result","logout");//用户米有登录
// 			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
// 		String opid = request.getParameter("opid");
// 		String periods = request.getParameter("periods");
// 		String[] periodss = periods.split(",");
//      	if(StringUtil.isEmpty(opid) && StringUtil.isEmpty(periods)){
//			hashMap.put("result","params_null");//参数错误 
// 			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
// 		} 
//     	
//     	String[] ops = opid.split(",");
//     	List<DividedPayments> dividedPayments5 = new ArrayList<>();
//     	DividedPayments dividedPayment2 = null;
//     	for(int i = 0;i<ops.length;i++){
//     		dividedPayment2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(ops[i]));
//     		dividedPayments5.add(dividedPayment2);
//     	}
//     	Collections.sort(dividedPayments5, new DividedPaymentsComparator());//排序
//  		DividedPayments dividedPayment = dividedPayments5.get(0);
//		if(dividedPayment == null){
//			hashMap.put("result","network_error");//网络异常 没有找到当期还款
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
// 		}
//		
//     	if(!(periodss.length == 1)){
//     		boolean flag = RepayMentLogicUtil.getCheckAheadRepayMent(periods);
//      		if(!flag){
//      			hashMap.put("result","check_error");//验证不通过 请按顺序选择还款期数
//      			String str = JSON.toJSONString(hashMap);
//      			try {
//      				StringUtil.sendJsonData(response, str);
//      			} catch (IOException e) {
//      				e.printStackTrace();
//      			}
//      			return null;
//      		}
//      	} 
//		
//		DividedPayments	dividedPayments = new DividedPayments();
// 		dividedPayments.setTenderid(dividedPayment.getTenderid());//标的ID
//		dividedPayments.setLgperiods(dividedPayment.getPeriods());//提前还款当期
//		List<DividedPayments> dividedPaymentss = dividedPaymentsServiceI.findDividedPaymentss(dividedPayments);
//		boolean checkFlag = RepayMentLogicUtil.getCheckAheadRepayMentBylgPeriod(dividedPaymentss);
//		if(!checkFlag){
//			hashMap.put("result","check_error");//验证不通过 请按顺序选择还款期数
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
////							Date date = new Date();
//		Date date = StringUtil.getDateByString("2017-02-10 12:12:12", "yyyy-MM-dd HH:mm:ss");
//		if(dividedPayment.getIsoverdue().equals((short)1)){//有逾期不能提前还款
//			hashMap.put("result","isoverdue");//已经逾期
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Integer count = (int) DateUtil.getDateDifference(dividedPayment.getRepayday(), date);
//		if(count > 0){
//			dividedPayment.setIsoverdue((short)1);//逾期
////			dividedPaymentsServiceI.updateById(dividedPayment);
//			hashMap.put("result","isoverdue");//已经逾期
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
//		if(count == 0){
// 			hashMap.put("result","isnormal");//正常还款日
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
//		/**********提前还款 校验结束***************/
// 		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());
//		if(tenderItem == null
//				|| tenderItem.getIspicompensateon() == null
//				|| tenderItem.getIsforplatformon()  == null){
// 			hashMap.put("result","tenderItem_null");//标的信息为null
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
//		AheadRepayMode aheadRepayMode  = AheadOracleTest.getAheadRepayMode();//提前还款
//		if(aheadRepayMode == null 
//				|| aheadRepayMode.getIntmode() == null){
// 			hashMap.put("result","aheadRepayMode_null");//标的提前还款信息为null
//			String str = JSON.toJSONString(hashMap);
//	 		try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//			return null;
//		}
//		
//		if(aheadRepayMode.getIntmode() != null && aheadRepayMode.getIntmode().equals((short)2)){//还利息方式(1占天利息，2全额利息)
//			DividedPayments dividedPayments2 = null;
//			Double cpprincipal = 0.00;//还款本金
//			Double currentPeriodInterest = 0.00;//还款利息
//			Double countPrincipal = 0.00;//合计
//				for(int i = 0;i<ops.length;i++){
//				    dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(ops[i]));
//				    cpprincipal = Double.valueOf(df.format(dividedPayments2.getCpprincipal()+cpprincipal));
//				    currentPeriodInterest = Double.valueOf(df.format(dividedPayments2.getCpinterest()+currentPeriodInterest));
// 				}
//				countPrincipal = Double.valueOf(df.format(cpprincipal + currentPeriodInterest));
//				hashMap.put("result","success_ahead");//成功
//				hashMap.put("cpprincipal", cpprincipal.toString());//还款本金
//				hashMap.put("currentPeriodInterest", currentPeriodInterest.toString());//还款利息
//				hashMap.put("countPrincipal", countPrincipal.toString());//合计
//				hashMap.put("opids", opid);//id
// 				session.setAttribute(Session_Constant.AHEADREPAY_CALCULATE,hashMap);
// 				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
// 					e.printStackTrace();
//				}
//				
//		}else if(aheadRepayMode.getIntmode() != null && aheadRepayMode.getIntmode().equals((short)1)){//按天计息
//			Short Ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//			Short Isforplatformon  = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//			Integer aheadDay 	   = 0;//持有天数
//			Integer periodsDay 	   = 0;//当期总天数
//			if(dividedPayment.getPeriods() == 1){//如果是一期
//				Date startDate = StringUtil.
//						getValuedate(tenderItem.getValuedate(), tenderItem.getValuepoint(), tenderItem.getValuerule());
//				aheadDay = (int) DateUtil.getDateDifference(startDate, date);
//				Integer aheadDay2 = (int) DateUtil.getDateDifference(date, dividedPayment.getRepayday());
//				periodsDay = aheadDay + aheadDay2;
//			}else{
//				Map<String,Object> hashMap2 = new HashMap<String,Object>();
//				hashMap2.put("tenderid", dividedPayment.getTenderid());//标ID
//				hashMap2.put("periods", dividedPayment.getPeriods() - 1);//期数
//				DividedPayments dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap2);
//				aheadDay = (int) DateUtil.getDateDifference(dividedPayments2.getRepayday(), date);
//				Integer aheadDay2 = (int) DateUtil.getDateDifference(date, dividedPayment.getRepayday());
//				periodsDay = aheadDay + aheadDay2;
//			}
//			Double dayDou 						= new BigDecimal(1).divide(new BigDecimal(periodsDay.toString()),10,BigDecimal.ROUND_DOWN).doubleValue();//K = 1 / 月总天数
//			Double mentInterest  				= 0.00;//投资人当期该得的利息
//			Double mentBadCropInterest	 		= 0.00;//投资人欠收利息
//			Double aheadRepaysPrincipal 		= 0.00;//借款人补偿投资人 总罚金
//			Double currentPeriodInterest 		= Double.valueOf(df.format(dividedPayment.getCpinterest() * aheadDay * dayDou));//当期该还的利息
//			Double badCropInterest 				= Double.valueOf(df.format(dividedPayment.getCpinterest() - currentPeriodInterest));//投资人总欠收利息
//			Double cpprincipal 					= dividedPayment.getCpprincipal();//提前还款当期本金
//			Double platformPrincipal 			= 0.00; 
//			DividedPayments dividedPayments3 	= null;
//			Map<String,Object> hashMap3 		= new HashMap<String,Object>();
//			hashMap3.put("tenderid", dividedPayment.getTenderid());//标号ID
//			hashMap3.put("periods", dividedPayment.getPeriods());//还款期数（第几期）
//			hashMap3.put("repaystatus", (short)1);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//			hashMap3.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			List<RepayMent> repayMents2 		= repayMentServiceI.findListRepayMentByConditions(hashMap3);//当期投资人投标信息
//			for(RepayMent ment : repayMents2){
//				mentInterest = Double.valueOf(df.format((ment.getRinterest() + ment.getRvoucherint()) * aheadDay * dayDou));//当期该还的利息
//				mentBadCropInterest = Double.valueOf(df.format((ment.getRinterest() + ment.getRvoucherint())- mentInterest));//单个投资人欠收利息
// 			}
//			
//			List<RepayMent> repayMents = new ArrayList<RepayMent>();//计算提前还款后面选择的期数
//	 		List<RepayMent> repayMents3 = null;
//			for(int i = 1;i<ops.length;i++){
//			    dividedPayments3 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(ops[i]));
//				Map<String,Object> hashMap4 = new HashMap<String,Object>();
//				hashMap4.put("tenderid", dividedPayments3.getTenderid());//标号ID
//				hashMap4.put("periods", dividedPayments3.getPeriods());//还款期数（第几期）
//				hashMap4.put("repaystatus", (short)1);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//				hashMap4.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			    repayMents3 = repayMentServiceI.findListRepayMentByConditions(hashMap4);
//			    repayMents.addAll(repayMents3);
//				badCropInterest = Double.valueOf(df.format(dividedPayments3.getCpinterest() + badCropInterest));//投资人总欠收利息
//				cpprincipal = Double.valueOf(df.format(dividedPayments3.getCpprincipal() + cpprincipal));//提前还款当期本金
//			}
//			 
//			if(Ispicompensateon.equals((short)1)){//提前还款本金利息补偿开
//				List<AheadRepay> aheadRepays =  AheadOracleTest.getAheadRepay();//提前还款补偿投资人
// 				for(RepayMent ment : repayMents2){
// 					mentInterest = Double.valueOf(df.format((ment.getRinterest() + ment.getRvoucherint()) * aheadDay * dayDou));//当期该还的利息
// 					mentBadCropInterest = Double.valueOf(df.format((ment.getRinterest() + ment.getRvoucherint())- mentInterest));//单个投资人欠收利息
// 					aheadRepaysPrincipal = Double.valueOf(df.format(RepayMentLogicUtil.toCalculatePrincipalInterest(aheadRepays, mentBadCropInterest) + aheadRepaysPrincipal));//借款人补偿投资人 罚金
//				}
// 				
// 				for(RepayMent ment :repayMents){
// 					mentBadCropInterest = Double.valueOf(df.format((ment.getRinterest() + ment.getRvoucherint())));//单个投资人欠收利息
// 		 			aheadRepaysPrincipal = Double.valueOf(df.format(RepayMentLogicUtil.toCalculatePrincipalInterest(aheadRepays, mentBadCropInterest) + aheadRepaysPrincipal));
// 				}
// 			}
//			
//			if(Isforplatformon.equals((short)1)){//提前还款还款人补偿平台开
//				List<AheadRepayPlatform> aheadRepayPlatforms = AheadOracleTest.getAheadRepayPlatform();//标的提前还款奖励平台设置
//				platformPrincipal = Double.valueOf(df.format(RepayMentLogicUtil.toCalculateAheadRepayPlatform(aheadRepayPlatforms, badCropInterest)));//补偿平台罚金
// 			}
//			
// 	 		Double countPrincipal = Double.valueOf(df.format(platformPrincipal + aheadRepaysPrincipal + currentPeriodInterest + cpprincipal));//共支出
//	 		hashMap.put("result", "success");
//			hashMap.put("platformPrincipal", platformPrincipal.toString());//补偿平台罚金
//			hashMap.put("aheadRepaysPrincipal", aheadRepaysPrincipal.toString());//补偿投资人罚息
//			hashMap.put("currentPeriodInterest", currentPeriodInterest.toString());//当期该还的利息
//			hashMap.put("cpprincipal", cpprincipal.toString());//提前还款本金
//			hashMap.put("countPrincipal", countPrincipal.toString());//合计
//			hashMap.put("opids", opid);//id
//			session.setAttribute(Session_Constant.AHEADREPAY_CALCULATE,hashMap);
//	  		String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//	 			e.printStackTrace();
//			}
//		}	
//  			return null;
//	}
//	
//	/**
//	 * 
//	* @Title: doRepayMentOverdue 
//	* @Description: TODO(逾期还款) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	public String doRepayMentOverdue(){
//		return "";
//	}
//	
//	
//	/**
//	* @throws IOException 
//	* @throws ServletException 
// 	* @Title: geHuifuParams 
//	* @Description: TODO(用户还款 接口调用) 
//	* @param @return  参数说明 
//	* @return HuifuParams    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	public void geHuifuRequestParams(HuifuParams huifuParams,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("==========进来了啊geHuifuRequestParams==========");
//		/*加签格式Version+CmdId+MerCustId+ProId+OrdId+OrdDate+OutCustId+SubOrdId+SubOrdDate+OutAcctId+PrincipalAmt+
//		InterestAmt+Fee+InCustId+InAcctId+DivDetails+FeeObjFlag+DzObject+BgRetUrl+MerPriv+ReqExt*/
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
// 		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getPrincipalAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInterestAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFee()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getDivDetails()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFeeObjFlag()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getDzObject()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
//		String str = buffer.toString();
//		MD5 md5 = new MD5();
//		String str2 = md5.getMD5Info(str).toLowerCase();
//  		String ChkValue;
//		try {
//			ChkValue = SignUtils.encryptByRSA(str2);
// 			if(StringUtil.isNotEmpty(ChkValue)){
// 				huifuParams.setChkValue(ChkValue);
// 			}
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}
// 		request.setAttribute("Version", huifuParams.getVersion());//版本号 Version  30
//		request.setAttribute("CmdId", huifuParams.getCmdId());//消息类型 CmdId 此处是Repayment
//		request.setAttribute("MerCustId", huifuParams.getMerCustId());//商户客户号 MerCustId  
//		request.setAttribute("ProId", huifuParams.getProId());//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//		request.setAttribute("OrdId", huifuParams.getOrdId());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
//		request.setAttribute("OrdDate", huifuParams.getOrdDate());//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		request.setAttribute("OutCustId", huifuParams.getOutCustId());//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识
//		request.setAttribute("SubOrdId", huifuParams.getSubOrdId());//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//					/*订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一 
//					 * 如果本次交易从属于另一个交易流水，
//					 * 	则需要通过填写该流水号来进行关联例如：本次放款：商户流水号是 OrdId，
//					 * 日期 是OrdDate，关联投标订单流水是SubOrdId，日期是 SubOrdDate*/
//		request.setAttribute("SubOrdDate", huifuParams.getSubOrdDate());//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		request.setAttribute("OutAcctId", huifuParams.getOutAcctId());//出账子账户 OutAcctId  可选 用户在汇付的虚拟资金账户号
//		request.setAttribute("PrincipalAmt", huifuParams.getPrincipalAmt());//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//		request.setAttribute("InterestAmt", huifuParams.getInterestAmt());//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//		request.setAttribute("Fee", huifuParams.getFee());//扣款手续费 Fee  必须 放款或扣款的手续费
//		request.setAttribute("InCustId", huifuParams.getInCustId());//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识
//		request.setAttribute("InAcctId", huifuParams.getInAcctId());//入账子账户 InAcctId  可选 用户在汇付的虚拟资金账户号
//		request.setAttribute("DivDetails", huifuParams.getDivDetailsStr());//分账账户串 DivDetails 可选	
//									/*放款 1.0、还款 1.0、债权转让 1.0 数据格式：
//									[{"DivAcctId":"MDT000023"，
//									"DivAmt":"1.00"}，
//									{"DivAcctId":"MDT000024"，
//									"DivAmt":"2.00"}，
//									{"DivAcctId":"MDT000025"，
//									"DivAmt":"3.00"}]
//									放款 2.0、还款 2.0、还款 3.0 数据格式：
//									[{"DivCustId":"6000060000009547"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"1.00"}，
//									{"DivCustId":"6000060000002526"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"2.00"}，
//									{"DivCustId":"6000060000002528"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"3.00"}]
//									（当 Fee！=0 时是必填项）*/
//		request.setAttribute("DivCustId", huifuParams.getDivCustId());//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//		request.setAttribute("DivAcctId", huifuParams.getDivAcctId());//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//		request.setAttribute("DivAmt", huifuParams.getDivAmt());//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//		request.setAttribute("FeeObjFlag", huifuParams.getFeeObjFlag());//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
//		request.setAttribute("DzObject", huifuParams.getDzObject());//垫资/代偿对象 DzObject  可选 如果是垫资还款必传商户或者担保企业垫资/代偿对象
//		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址BgRetUrl  必须 通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID字样的字符串表明商户已经收到该笔交易结果
//		request.setAttribute("ReqExt", huifuParams.getReqExtStr());//入参扩展域 ReqExt  可选 用于扩展请求参数
//		request.setAttribute("ChkValue", huifuParams.getChkValue());//签名 ChkValue  必须
//		request.setAttribute("CharSet", huifuParams.getCharSet());//编码格式
// 		request.getRequestDispatcher("/WEB-INF/pages/RepayMent/repayMent.jsp").forward(request, response);
//  	}
//	
//   /**
// 	* @Title: test 
//	* @Description: TODO(汇付测试无用  生产环境删除 ) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	*/
//	@RequestMapping("/test")
//	public String test(){
//		return "huifuhuizong/Repayment";
//	}
//	
//	/**
//	 * 
//	* @Title: test2 
//	* @Description: TODO(生产环境删除) 
//	* @param @param request
//	* @param @param response
//	* @param @param huifuParams
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/test2",method={RequestMethod.POST,RequestMethod.GET})
//	public String test2(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
//		System.out.println("-------------------ghgfhgfhgfh");
// 		huifuParams.setVersion("30");//版本号 Version  30
//		huifuParams.setCmdId("Repayment");//消息类型 CmdId 此处是Repayment
//		//商户客户号 MerCustId  6000060004166478
//		//huifuParams.setProId("gjbh201609051627");//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//		huifuParams.setOrdId(StringUtil.getNo());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
//		huifuParams.setOrdDate(StringUtil.formatDate(new Date(),"yyyyMMdd"));//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		//huifuParams.setOutCustId("6000060005290553");//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识  6000060005290553  6000060005410674
//		//huifuParams.setSubOrdId("20160924165458939243");//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//			/*订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一 
//			 * 如果本次交易从属于另一个交易流水，
//			 * 	则需要通过填写该流水号来进行关联例如：本次放款：商户流水号是 OrdId，
//			 * 日期 是OrdDate，关联投标订单流水是SubOrdId，日期是 SubOrdDate*/
//		//huifuParams.setSubOrdDate("20160924");//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		//huifuParams.setOutAcctId("MDT000001");//出账子账户 OutAcctId  可选 用户在汇付的虚拟资金账户号
//		//huifuParams.setPrincipalAmt("0.00");//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//		//huifuParams.setInterestAmt("0.00");//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//		//huifuParams.setInCustId("6000060004190334");//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识       投资人  6000060004191208
//		//huifuParams.setFee("1.00");//扣款手续费 Fee  必须 放款或扣款的手续费
//		//huifuParams.setFeeObjFlag("O");//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
//		//huifuParams.setInAcctId("");//入账子账户 InAcctId  可选 用户在汇付的虚拟资金账户号
//		//huifuParams.setDivCustId("6000060004166478");//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//		//huifuParams.setDivAcctId("SDT000001");//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//		//huifuParams.setDivAmt("1.00");//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//		String str2 = "";
//		if(!huifuParams.getFee().equalsIgnoreCase("0.00")){
// 			String str = "[{\"DivCustId\":\""+huifuParams.getDivCustId()+"\","+
//					"\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\","+
//					"\"DivAmt\":\""+huifuParams.getDivAmt()+"\"}]";
//			  str2 = "[{&quot;DivCustId&quot;:&quot;"+huifuParams.getDivCustId()+"&quot;,"+
//					"&quot;DivAcctId&quot;:&quot;"+huifuParams.getDivAcctId()+"&quot;,"+
//					"&quot;DivAmt&quot;:&quot;"+huifuParams.getDivAmt()+"&quot;}"+
//					"]";							
//			huifuParams.setDivDetails(str);//分账账户串 DivDetails 可选	
//		}
//							/*放款 1.0、还款 1.0、债权转让 1.0 数据格式：
//							[{"DivAcctId":"MDT000023"，
//							"DivAmt":"1.00"}，
//							{"DivAcctId":"MDT000024"，
//							"DivAmt":"2.00"}，
//							{"DivAcctId":"MDT000025"，
//							"DivAmt":"3.00"}]
//							放款 2.0、还款 2.0、还款 3.0 数据格式：
//							[{"DivCustId":"6000060000009547"，
//							"DivAcctId":"MDT000001"，
//							"DivAmt":"1.00"}，
//							{"DivCustId":"6000060000002526"，
//							"DivAcctId":"MDT000001"，
//							"DivAmt":"2.00"}，
//							{"DivCustId":"6000060000002528"，
//							"DivAcctId":"MDT000001"，
//							"DivAmt":"3.00"}]
//							（当 Fee！=0 时是必填项）*/
////		huifuParams.setDzObject("6000060005290553");//垫资/代偿对象 DzObject  可选 如果是垫资还款必传商户或者担保企业垫资/代偿对象  这里填写原借款人汇付客户号
//		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/repayMent/repayMentCallBack.action");//商户后台应答地址BgRetUrl  必须 通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID字样的字符串表明商户已经收到该笔交易结果
////入参扩展域 ReqExt  可选 用于扩展请求参数
////签名 ChkValue  必须
//		/*加签格式Version+CmdId+MerCustId+ProId+OrdId+OrdDate+OutCustId+SubOrdId+SubOrdDate+OutAcctId+PrincipalAmt+
//		InterestAmt+Fee+InCustId+InAcctId+DivDetails+FeeObjFlag+DzObject+BgRetUrl+MerPriv+ReqExt*/
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
// 		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getPrincipalAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInterestAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFee()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getDivDetails()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFeeObjFlag()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getDzObject()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
//		String str34 = buffer.toString();
//		MD5 md5 = new MD5();
//		String str234 = md5.getMD5Info(str34).toLowerCase();
//  		String ChkValue;
//		try {
//			ChkValue = SignUtils.encryptByRSA(str234);
// 			if(StringUtil.isNotEmpty(ChkValue)){
// 				huifuParams.setChkValue(ChkValue);
// 			}
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}
//		System.out.println("============"+huifuParams.getChkValue());
//		request.setAttribute("Version", huifuParams.getVersion());//版本号 Version  30
//		request.setAttribute("CmdId", huifuParams.getCmdId());//消息类型 CmdId 此处是Repayment
//		request.setAttribute("MerCustId", huifuParams.getMerCustId());//商户客户号 MerCustId  
//		request.setAttribute("ProId", huifuParams.getProId());//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//		request.setAttribute("OrdId", huifuParams.getOrdId());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
//		request.setAttribute("OrdDate", huifuParams.getOrdDate());//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		request.setAttribute("OutCustId", huifuParams.getOutCustId());//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识
//		request.setAttribute("SubOrdId", huifuParams.getSubOrdId());//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//					/*订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一 
//					 * 如果本次交易从属于另一个交易流水，
//					 * 	则需要通过填写该流水号来进行关联例如：本次放款：商户流水号是 OrdId，
//					 * 日期 是OrdDate，关联投标订单流水是SubOrdId，日期是 SubOrdDate*/
//		request.setAttribute("SubOrdDate", huifuParams.getSubOrdDate());//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		request.setAttribute("OutAcctId", huifuParams.getOutAcctId());//出账子账户 OutAcctId  可选 用户在汇付的虚拟资金账户号
//		request.setAttribute("PrincipalAmt", huifuParams.getPrincipalAmt());//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//		request.setAttribute("InterestAmt", huifuParams.getInterestAmt());//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//		request.setAttribute("Fee", huifuParams.getFee());//扣款手续费 Fee  必须 放款或扣款的手续费
//		request.setAttribute("InCustId", huifuParams.getInCustId());//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识
//		request.setAttribute("InAcctId", huifuParams.getInAcctId());//入账子账户 InAcctId  可选 用户在汇付的虚拟资金账户号
//		request.setAttribute("DivDetails", str2);//分账账户串 DivDetails 可选	
//									/*放款 1.0、还款 1.0、债权转让 1.0 数据格式：
//									[{"DivAcctId":"MDT000023"，
//									"DivAmt":"1.00"}，
//									{"DivAcctId":"MDT000024"，
//									"DivAmt":"2.00"}，
//									{"DivAcctId":"MDT000025"，
//									"DivAmt":"3.00"}]
//									放款 2.0、还款 2.0、还款 3.0 数据格式：
//									[{"DivCustId":"6000060000009547"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"1.00"}，
//									{"DivCustId":"6000060000002526"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"2.00"}，
//									{"DivCustId":"6000060000002528"，
//									"DivAcctId":"MDT000001"，
//									"DivAmt":"3.00"}]
//									（当 Fee！=0 时是必填项）*/
//		request.setAttribute("DivCustId", huifuParams.getDivCustId());//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//		request.setAttribute("DivAcctId", huifuParams.getDivAcctId());//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//		request.setAttribute("DivAmt", huifuParams.getDivAmt());//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//		request.setAttribute("FeeObjFlag", huifuParams.getFeeObjFlag());//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
//		request.setAttribute("DzObject", huifuParams.getDzObject());//垫资/代偿对象 DzObject  可选 如果是垫资还款必传商户或者担保企业垫资/代偿对象
//		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址BgRetUrl  必须 通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID字样的字符串表明商户已经收到该笔交易结果
//		request.setAttribute("ReqExt", huifuParams.getReqExt());//入参扩展域 ReqExt  可选 用于扩展请求参数
//		request.setAttribute("ChkValue", huifuParams.getChkValue());//签名 ChkValue  必须
//		request.setAttribute("CharSet", huifuParams.getCharSet());//编码格式
//		return "huifu/repayMent";
//   }
//	
//	/**
//	 * 
//	* @Title: repayMentCallBack 
//	* @Description: TODO(后台应答 地址) 
//	* @param @param huifuParams
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@RequestMapping(value="/repayMentCallBack")
//	public String repayMentCallBack(HuifuParams huifuParams,HttpServletRequest request,HttpServletResponse response){
//		System.out.println("=========huifuParams==getCmdId============="+huifuParams.getCmdId());
//		System.out.println("=========huifuParams=====getRespCode=========="+huifuParams.getRespCode());
//		System.out.println("=========huifuParams===getRespDesc============"+huifuParams.getRespDesc());
//		System.out.println("=========huifuParams=======getMerCustId========"+huifuParams.getMerCustId());
//		System.out.println("=========huifuParams====getProId==========="+huifuParams.getProId());
//		System.out.println("=========huifuParams======getOrdId========="+huifuParams.getOrdId());
//		System.out.println("=========huifuParams=====getOrdDate=========="+huifuParams.getOrdDate());
//		System.out.println("=========huifuParams=getOutCustId=============="+huifuParams.getOutCustId());
//		System.out.println("=========huifuParams=====getSubOrdId=========="+huifuParams.getSubOrdId());
//		System.out.println("=========huifuParams======getSubOrdDate========="+huifuParams.getSubOrdDate());
//		System.out.println("=========huifuParams=====getOutAcctId=========="+huifuParams.getOutAcctId());
//		System.out.println("=========huifuParams====getPrincipalAmt==========="+huifuParams.getPrincipalAmt());
//		System.out.println("=========huifuParams=====getInterestAmt=========="+huifuParams.getInterestAmt());
//		System.out.println("=========huifuParams=====getFee=========="+huifuParams.getFee());
//		System.out.println("=========huifuParams=====getInCustId=========="+huifuParams.getInCustId());
//		System.out.println("=========huifuParams=====getInAcctId=========="+huifuParams.getInAcctId());
//		System.out.println("=========huifuParams=====getFeeObjFlag=========="+huifuParams.getFeeObjFlag());
//		System.out.println("=========huifuParams=====getDzObject=========="+huifuParams.getDzObject());
//		System.out.println("=========huifuParams=====getBgRetUrl=========="+huifuParams.getBgRetUrl());
//		System.out.println("=========huifuParams=====getMerPriv=========="+huifuParams.getMerPriv());
//		System.out.println("=========huifuParams=====getRespExt========="+huifuParams.getRespExt());
//		System.out.println("=========huifuParams=====getChkValue========="+huifuParams.getChkValue());
//		/*CmdId + RespCode + MerCustId + ProId + OrdId + OrdDate + OutCustId + SubOrdId+
//		SubOrdDate+ OutAcctId + PrincipalAmt + InterestAmt + Fee + InCustId+ InAcctId+
//		FeeObjFlag + DzObject + BgRetUrl+ MerPriv + RespExt
//		先对此明文串做 md5 加密，再将 md5 加密后的密文做汇付的 RSA 验签*/
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getSubOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getPrincipalAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInterestAmt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFee()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getFeeObjFlag()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getDzObject()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getRespExt()));
//		String str = buffer.toString();
//		MD5 md5 = new MD5();
//		String str1 = md5.getMD5Info(str).toLowerCase();
//		boolean flag = false;
//		try {
//			flag = SignUtils.verifyByRSA(str1, huifuParams.getChkValue());
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}
//		if(flag){
//			if(huifuParams.getRespCode().equals("000")){
//				RepayMent repayMent = repayMentServiceI.findRepayMentByRorderno(huifuParams.getOrdId());
//				System.out.println(repayMent);
//				repayMent.setRepaystatus((short)2);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//				repayMentServiceI.updateById(repayMent);
// 			} 
// 		}
//		return null;
//	}	
//	
//	/**
//	 * 
//	* @Title: HuifuBatchProcessingRepayMent 
//	* @Description: TODO(汇付还款 批量处理 因为汇付还款不能超过500笔) 
//	* @param @param countRepayMents 还款总条数
//	* @param @param repayMents 投资人还款具体计划集合
//	* @param @param request   
//	* @param @param dividedPayments  借款人还款具体计划
//	* @return void    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	public void HuifuBatchProcessingRepayMent(Integer countRepayMents,List<RepayMent> repayMents ,HttpServletRequest request,DividedPayments dividedPayments){
//		Integer pageSize = 500;//批量还款条数
//	    if(countRepayMents <= pageSize){//条数小于或等于500条，不需要处理，直接调汇付批量还款接口
//	    	if(repayMents.size() > 0){
//		    		repayMentServiceI.settingUpBatchRepayMent(request, dividedPayments,repayMents);//调用汇付批量还款接口
//	    	}
//	    }else{ 
//		    	List<RepayMent> subRepayMents = null;//截取后抛送汇付的list集合
//	    	int size = 0;
//	    	if(countRepayMents % pageSize == 0){
//		    	  size = countRepayMents / pageSize;
//		    	}else{
//		    		  size = countRepayMents / pageSize + 1;
//	    	}
//	    	for(int i = 0;i < size;i++){
//		    		if(i == size-1){
//	    			subRepayMents = repayMents.subList(pageSize * i, pageSize * i+1); 
//	    			if(!subRepayMents.get(subRepayMents.size()-1).getId()
//	    					.equals(repayMents.get(repayMents.size()-1).getId())){
//		    				subRepayMents.add(repayMents.get(repayMents.size()-1));//截取不包括后面的,所以要重新添加
//	    			}
//	    		}else{
//	    				subRepayMents = repayMents.subList(pageSize * i, pageSize * i+2);
//		    		}
//		    		if(subRepayMents.size() > 0){
//		    				repayMentServiceI.settingUpBatchRepayMent(request, dividedPayments,subRepayMents);//调用汇付批量还款接口
//		    		}
//	    	}			    	 
//	    }
//	} 	
//}
