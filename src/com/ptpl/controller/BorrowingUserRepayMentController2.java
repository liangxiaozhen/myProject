 

//package com.ptpl.controller;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.util.WebUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.github.miemiedev.mybatis.paginator.domain.Order;
//import com.github.pagehelper.PageHelper;
//import com.huishang.util.HSignUtil;
//import com.ptpl.constant.Session_Constant;
//import com.ptpl.controller.huishang.HSRepayMentFreezeController;
//import com.ptpl.core.annotation.Token;
//import com.ptpl.model.AheadRepay;
//import com.ptpl.model.AheadRepayAward;
//import com.ptpl.model.AheadRepayMentCalculatedParameter;
//import com.ptpl.model.AheadRepayMode;
//import com.ptpl.model.AheadRepayPlatform;
//import com.ptpl.model.DividedPayments;
//import com.ptpl.model.OverdueFeeRate;
//import com.ptpl.model.RepayMent;
//import com.ptpl.model.RepayMentFineDetail;
//import com.ptpl.model.RepayMentFineDetail.AheadRepayMentDetail;
//import com.ptpl.model.TenderItem;
//import com.ptpl.model.UserBaseAccountInfo;
//import com.ptpl.model.UserFsAccountInfo;
//import com.ptpl.model.UserTender;
//import com.ptpl.model.loanapp;
//import com.ptpl.service.AheadRepayMentLogicI;
//import com.ptpl.service.AheadRepayModeServiceI;
//import com.ptpl.service.DividedPaymentsServiceI;
//import com.ptpl.service.NormalRepayMentLogicI;
//import com.ptpl.service.OverdueFeeRateServiceI;
//import com.ptpl.service.OverdueRepayMentLogicDealI;
//import com.ptpl.service.RepayMentServiceI;
//import com.ptpl.service.TenderItemServiceI;
//import com.ptpl.service.ThirdRepayMentDealI;
//import com.ptpl.service.UserBaseAccountInfoServiceI;
//import com.ptpl.service.UserFsAccountInfoServiceI;
//import com.ptpl.service.UserTenderServiceI;
//import com.ptpl.service.loanappServiceI;
//import com.ptpl.web.util.Arith;
//import com.ptpl.web.util.DividedPaymentsComparator;
//import com.ptpl.web.util.StringUtil;
//
///**
// * 
//* @ClassName: BorrowingUserRepayMentController 
//* @Package com.ptpl.controller 
//* @Description: TODO(借款用户 操作Controller层) 
//* @author cjm
//* @date 2017年2月28日 下午2:51:51 
//* @version V1.0
// */
//@Controller
//@RequestMapping("/borrowingUser/repayMent")
//public class BorrowingUserRepayMentController2 extends BaseController{
//	
//	public static Log repayMentLog = LogFactory.getLog(BorrowingUserRepayMentController2.class);
//	
// 	@Autowired
//	private TenderItemServiceI  tenderItemServiceI;//标的 service
//	 
//	@Autowired
//	private DividedPaymentsServiceI dividedPaymentsServiceI;//标的分期还款计划service
//	 
//	@Autowired
//	private RepayMentServiceI repayMentServiceI;//还款计划记录表service
// 	 
//	@Autowired
//	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;//第三方托管账号
//	 
//	@Autowired
//	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;//用户基本信息Service
//	
//	@Autowired
//	private AheadRepayModeServiceI aheadRepayModeServiceI; //提前还款 设置Service
//	
//	@Autowired
//	private loanappServiceI loanappServiceI; //借款申请Service
//	
//	@Autowired
//	private AheadRepayMentLogicI aheadRepayMentLogicI;//提前还款 逻辑处理
//  	
//	@Autowired
//	private UserTenderServiceI userTenderServiceI; //投标记录 service
//	
//	@Autowired
//	private OverdueRepayMentLogicDealI overdueRepayMentLogicDealI;//逾期还款 逻辑处理接口
//	
//	@Autowired
//	private OverdueFeeRateServiceI overdueFeeRateServiceI;//逾期滞纳金费率设置Service
//	
//	@Autowired
//	private ThirdRepayMentDealI thirdRepayMentDealI;//第三方接口
//	
//	@Autowired
//	private NormalRepayMentLogicI normalRepayMentLogicI;//正常还款逻辑处理
//	
//	/**
//	 * @throws Exception 
//	 * 
//	* @Title: doRepayMentNormal 
//	* @Description: TODO(正常还款  全部) 
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	* 注：token 在UserLoanAppController.java
//	 */
//	@RequestMapping(value="/doManualRepayMentNormal",method=RequestMethod.POST)
//	@Token(remove = true)
//	public String doManualRepayMentNormal(HttpServletRequest request,HttpServletResponse response) throws Exception{
//  		String dividedPaymentsId = request.getParameter("opid");//还款计划ID
// 		UserBaseAccountInfo accountInfo = 	getUserBaseAccountInfo();
//		Map<String,String> hashMap = new HashMap<String,String>();
//		if(accountInfo == null){//用户没有登录 或session 失效
//			hashMap.put(Session_Constant.RESULT, "logout");
//			hashMap.put(Session_Constant.RESULTCODE, "logout");
//			hashMap.put(Session_Constant.MESSAGE, "提示：因您操作超时,请登录后重新操作！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		 }
//		
//		if(StringUtil.isEmpty(dividedPaymentsId)){//非法参数
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,借款人还款计划参数找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		 }
//		
// 		DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(dividedPaymentsId));//借款人还款计划对象
// 		if(dividedPayments == null ){
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"direp_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,借款人还款计划信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 		
// 		boolean cflag = normalRepayMentLogicI.checkDividedPaymentsNormalRepayMent(dividedPayments);
// 		if(!cflag){
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"jump_payment");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,不允许跳期还款,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 		
//		UserFsAccountInfo accountInfo2 = userFsAccountInfoServiceI .findUserFsAccountInfoByBaseId(accountInfo.getId());//第三方账号对象
//		if(accountInfo2 == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"accountInfo2_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,银行电子账号信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}	
//		
//		accountInfo2 = getDecryptionUserFsAccountInfoDetail(accountInfo2);//解密加密后的数据
//		TenderItem item = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
//  		if(item == null || item.getRepaymenttype() == null || item.getTno() == null){
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,标的设置信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
//		 
// 		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("outaccountid", accountInfo.getId());//借款用户ID
//		map.put("tenderid", dividedPayments.getTenderid());//标号ID
//		map.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
//		map.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		map.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
// 	    List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(map);
//	    if(!(repayMents.size() > 0)){
//  	    	hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"repayMentSize_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,投资人还款计划信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//	    }
//	    
//	    String rbatchno = StringUtil.getNo();//还款批次号
// 		Double totalamount = 0.00;
//		DecimalFormat decimalFormat = new DecimalFormat("#########################0.00");
// 		for(RepayMent repayMent3:repayMents){
//			totalamount = Double.valueOf(decimalFormat.format(
//					repayMent3.getRamount() + repayMent3.getRinterest() +
//					repayMent3.getRvoucher() + repayMent3.getRvoucherint() +
//					repayMent3.getDisablevoucher() + repayMent3.getDisablevoucherint() + totalamount));//借款人还款金额  = 本金+利息+类现金+类现金利息+作废的类现金+作废的类现金利息
// 		 }
// 		
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(accountInfo2.getUsrcustid(),totalamount.toString(), item.getTno(), HSignUtil.COINSTCODE+rbatchno);
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get(Session_Constant.RESULT);
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"balance_deficiency");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,账号余额不足,请重新操作或联系客服！");
//			}else{
//   				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"freeze_error");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！");
//			}
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//		}
//  		 
//  		Short repayMentType = item.getRepaymenttype();//还款类型（1及时，2冻结还款）
// 		if(repayMentType.equals((short)1)){
//  			boolean flag = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMents, rbatchno,accountInfo2.getUsrcustid(),totalamount.toString(),item.getTno());
//  			if(!flag){
//    			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,Session_Constant.FAIL);
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款提交失败,请重新操作或联系客服！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//  			}
//  			
//			int count = 0;
//		    dividedPayments.setIscomplete((short)2);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款(标的截标时生成时默认0)
//		    dividedPayments.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//		    count = dividedPaymentsServiceI.updateById(dividedPayments);
//		    if(count > 0){
//  			    hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.RESULTCODE,Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,还款提交成功！");
//		    }else{
//  		    	hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,Session_Constant.FAIL);
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,还款提交失败,请重新操作或联系客服！");
//		    }
//		    String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 		}else if(repayMentType.equals((short)2)){//冻结还款
// 			Short repaystatus = 0;
// 			Short planstatus  = 0;
// 			Short isaudit 	  = 0;
// 			boolean flag = false;
//  			for(RepayMent repayMent : repayMents){
// 				repaystatus = repayMent.getRepaystatus();/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//        		planstatus  = repayMent.getPlanstatus();//还款计划状态(1有效，2无效)
//        		isaudit	 = repayMent.getIsaudit();//是否审核 0未审核 1待审核  2审核通过 3审核失败
//        		if(!(repaystatus.equals((short)1))){
//         			continue;//只有未还款才继续执行
//        		}
//        		
//        		if(planstatus.equals((short)2)){
//         			continue;//只有有效才继续执行
//        		}
//        		
//        		if(isaudit.equals((short)0) || isaudit.equals((short)3)){
//         			repayMent.setIsaudit((short)1);//是否审核 0未审核 1待审核  2审核通过 3审核失败
//        		}
//          		
//        		if(!flag){
//         			flag = true;
//        		}
//        		
//        		repayMent.setRbatchno(rbatchno);//还款批次号
//        		repayMent.setRepaystatus((short)4);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//        		repayMentServiceI.updateById(repayMent);
// 			}
//  			
//  			if(flag){
//   			    hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.RESULTCODE,"isaudit_success");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提交客服审核成功！请等待客服审核处理!");
//		    }else{
// 		    	hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.RESULTCODE,"isaudit_success");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提交客服审核成功！请等待客服审核处理!");
//				HSRepayMentFreezeController.repayMentunFreeze(accountInfo2.getUsrcustid(), totalamount.toString(), item.getTno(), StringUtil.getNo(), HSignUtil.COINSTCODE+rbatchno);
//
//   			}
//  			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 		}
// 		 return null;	
// 	} 
//	
//	/**
//	 * @throws Exception 
// 	 * @Title: doNormalRepayMentPart 
//	 * @Description: TODO(正常还款 部分还款  ) 
//	 * @param @param request
//	 * @param @param response
//	 * @param @return  参数说明 
//	 * @return String    返回类型 
//	 * @author cjm
//	 * @throws
//	 * 注：token 在UserLoanAppController.java
//	 */
//	@RequestMapping(value="/doNormalRepayMentPart",method=RequestMethod.POST)
//	@Token(remove = true)
//	public String doNormalRepayMentPart(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		UserBaseAccountInfo userBaseAccountInfo = 	getUserBaseAccountInfo();
//		Map<String,String> hashMap = new HashMap<String,String>();
//		if(userBaseAccountInfo == null){//用户没有登录 或session 失效
// 			hashMap.put(Session_Constant.RESULT, "logout");
//			hashMap.put(Session_Constant.RESULTCODE,"logout");
//			hashMap.put(Session_Constant.MESSAGE, "提示：因您操作超时,请重新登录后操作");
// 			String str = JSON.toJSONString(hashMap);
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
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,借款人还款计划参数找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//  		UserFsAccountInfo userFsAccountInfo  = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
//  		if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
//    		hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,银行电子账号信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//  		}
//  		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//   		//数据合法性检验结束
//     	String[] opids = opid.split(",");//分割后的投资人还款计划ID
//  		RepayMent repayMent = null;//投资人还款具体计划对象
//  		List<RepayMent> repayMents = new ArrayList<RepayMent>();//投资人还款具体计划对象集合
//  		Double principalCount = 0.00;//借款人本次选择还款的金额总数
//  		DecimalFormat df = new DecimalFormat("#################################0.00");
//  		for(int i = 0;i < opids.length;i++){
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
//  		if(!(repayMents.size() > 0)){
//    		hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"repayMentSize_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,投资人还款计划信息找不到,请重新操作或联系客服！");
//  			String str = JSON.toJSONString(hashMap);
//  			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//  			return null;
//  		}
//   		
//   			/*这里进行标的还款类型判断 及时还款就直接调用还款接口还款，冻结还款就*/
//   			RepayMent repayMent2 = repayMents.get(0);
//   			
//   			Map<String,Object> hashMap2 = new HashMap<String,Object>();
//   			hashMap2.put("periods",  repayMent2.getPeriods());
//   			hashMap2.put("tenderid", repayMent2.getTenderid());
//   			DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap2);
//   			if(dividedPayments == null){
//     			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//   				hashMap.put(Session_Constant.RESULTCODE,"dividedPayments_null");
//   				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,借款人还款计划信息找不到,请重新操作或联系客服！");
//   				String str = JSON.toJSONString(hashMap);
//   				try {
//   					StringUtil.sendJsonData(response, str);
//   				} catch (IOException e) {
//   	 				e.printStackTrace();
//   				}
//   				return null;
//   			}
//   			
//   			boolean falg = normalRepayMentLogicI.checkDividedPaymentsNormalRepayMent(dividedPayments);
//   			if(!falg){//不允许跳期还款
//     			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//   				hashMap.put(Session_Constant.RESULTCODE,"jump_payment");
//   				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,不允许跳期还款,请重新操作或联系客服！");
//   				String str = JSON.toJSONString(hashMap);
//   				try {
//   					StringUtil.sendJsonData(response, str);
//   				} catch (IOException e) {
//   	 				e.printStackTrace();
//   				}
//   				return null;
//   			}
//    			
//   			TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent2.getTenderid());
//   			Short repayMentType = tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
//   			Assert.notNull(repayMentType, "'repayMentType 标的还款类型不能为null'");
//    		String rbatchno = StringUtil.getNo();
//   			Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),principalCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE+rbatchno);
//   			boolean fg = (boolean) resMap.get("flag");
//   			String tre = (String) resMap.get("result");
//    		if(!fg){
//    			if(tre.equals("balance_deficiency")){
//     				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//       				hashMap.put(Session_Constant.RESULTCODE,"balance_deficiency");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,余额不足,请重新操作或联系客服！");
//    			}else{
//      				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//       				hashMap.put(Session_Constant.RESULTCODE,"freeze_error");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！");
//    			}
//   				String str = JSON.toJSONString(hashMap);
//   				try {
//   					StringUtil.sendJsonData(response, str);
//   				} catch (IOException e) {
//   					e.printStackTrace();
//   				}
//   				return null;
//   			}
//    		
//      		if(repayMentType.equals((short)1)){//还款类型（1及时，2冻结还款）
//     			boolean flag = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMents,rbatchno,userFsAccountInfo.getUsrcustid(),principalCount.toString(),tenderItem.getTno());//还款参数写进文件 提交处理
//     			if(!flag){//还款提交失败  解冻还款
//      				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//       				hashMap.put(Session_Constant.RESULTCODE,"fail");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款提交失败,请重新操作或联系客服！");
//     				String str = JSON.toJSONString(hashMap);
//       				try {
//       					StringUtil.sendJsonData(response, str);
//       				} catch (IOException e) {
//       					e.printStackTrace();
//       				}
//       				return null;
//     			}
//     			
//   				int count = 0;
//   				dividedPayments.setIscomplete((short)3);//是否还款完成 0 没有完成还款 1已完成还款 2处理中 3部分还款(标的截标时生成时默认0)
//   			    dividedPayments.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//    				count = dividedPaymentsServiceI.updateById(dividedPayments);
//   				if(count > 0){
//     				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//       				hashMap.put(Session_Constant.RESULTCODE,Session_Constant.SUCCESS);
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,还款提交成功!");
//    		   }else{
//    				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//       				hashMap.put(Session_Constant.RESULTCODE,"fail");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：因网络响应不及时,还款提交失败！请重新操作或联系客服！");
//   				}
//   				String str = JSON.toJSONString(hashMap);
//   				try {
//   					StringUtil.sendJsonData(response, str);
//   				} catch (IOException e) {
//   					e.printStackTrace();
//   				}
//   				
//    		}else if(repayMentType.equals((short)2)){//还款类型（1及时，2冻结还款）
//    			boolean flag = false;
//    			Short repaystatus = 0;
//    			Short planstatus = 0;
//    			Short isaudit = 0;
//     			for(RepayMent ment : repayMents){
//   					repaystatus = repayMent.getRepaystatus();/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
// 	        		planstatus  = repayMent.getPlanstatus();//还款计划状态(1有效，2无效)
// 	        		isaudit		= repayMent.getIsaudit();/* 是否审核 0未审核 1待审核  2审核通过 3审核失败 */
//   	        		if(!(repaystatus.equals((short)1))){
// 	         			continue;//只有未还款才继续执行
// 	        		}
// 	        		
// 	        		if(planstatus.equals((short)2)){
// 	         			continue;//只有有效才继续执行
// 	        		}
// 	        		
// 	        		if(isaudit.equals((short)0) || isaudit.equals((short)3) ){
//  	         			ment.setIsaudit((short)1);/* 是否审核 0未审核 1待审核  2审核通过 3审核失败 */
// 	        		}
//  	        		
// 	        		if(!flag){
//  	        			flag = true;
// 	        		}
// 	        		
// 	        		ment.setRbatchno(rbatchno);//还款批次号
// 	        		ment.setRepaystatus((short)4);	/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//  	        		repayMentServiceI.updateById(ment);
//   				}
//   				if(flag){
//    				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//       				hashMap.put(Session_Constant.RESULTCODE,"isaudit_success");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提交客服审核成功！请等待客服审核处理！");
//   				}else{
//     				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//       				hashMap.put(Session_Constant.RESULTCODE,"isaudit_fail");
//       				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,已全部提交客服审核了！请等待客服审核处理!");
//    				HSRepayMentFreezeController.repayMentunFreeze(userFsAccountInfo.getUsrcustid(), principalCount.toString(), tenderItem.getTno(), StringUtil.getNo(), HSignUtil.COINSTCODE+rbatchno);
//
//   				}
//   				String str = JSON.toJSONString(hashMap);
//   				try {
//   					StringUtil.sendJsonData(response, str);
//   				} catch (IOException e) {
//   					e.printStackTrace();
//   				}
//   			} 
//    		return null;
//	}
//	
//	/**
//	 * 
//	* @Title: aheadRepayMent 
//	* @Description: TODO(提前还款  逻辑处理（用户选择提前还款操作后进了这里）) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value="/aheadRepayMent",method=RequestMethod.POST)
//	@Token(save = true)
//  	public String aheadRepayMent(HttpServletRequest request,HttpServletResponse response,Model model){
//		Map<String,String> hashMap = new HashMap<String,String>();
//   		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
// 		if(accountInfo == null){//用户没有登录
//  			hashMap.put(Session_Constant.RESULT, "logout");
//			hashMap.put(Session_Constant.RESULTCODE,"logout");
//			hashMap.put(Session_Constant.MESSAGE, "提示：因您操作超时,请重新登录后操作");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		String opid = request.getParameter("opid");//提前还款选择借款人还款具体计划的id（有1个或者多个）
//		if(StringUtil.isEmpty(opid)){//参数ID为null
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划参数找不到,请重新操作或联系客服");
// 			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
// 		}
//		
//		String[] opids = opid.split(",");
//		DividedPayments dividedPayment = null;//借款人还款具体计划
//		List<DividedPayments> dividedPayments = new ArrayList<DividedPayments>();
//		for(int i = 0 ; i < opids.length ; i++){
//			dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opids[i]));
//			if(dividedPayment != null){
// 				dividedPayments.add(dividedPayment);
//			}
//		}
//		
//		if(!(dividedPayments.size() > 0)){//没有找到借款人还款具体计划
//    		hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"divSize_error");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划信息找不到,请重新操作或联系客服");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		boolean flag = true;
//		if(dividedPayments.size() == 1){
//			if(dividedPayments.get(0).getIsoverdue() != null
//					&& dividedPayments.get(0).getIsoverdue().equals((short)1)){//有逾期 不能提前还款
//				flag = false;
//  			}
//		}else{
//			/**
//			 * 借款人选择了多期提前还款
//			 * 判断前面是否有逾期或没有还款的
//			 */
//			Collections.sort(dividedPayments,new DividedPaymentsComparator());
//			boolean fag = aheadRepayMentLogicI.hasDividedPaymentsAheadMultiperiod(dividedPayments);//判断是否隔期还款,不允许隔期还款
//			if(!fag){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"multiperiod_error");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！不允许隔期还款,请重新操作或联系客服");
//				 String st = JSON.toJSONString(hashMap);
//				 try {
//					StringUtil.sendJsonData(response, st);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return  null;
//			}
//			
//			if(dividedPayments.get(0).getIsoverdue() != null
//					&& dividedPayments.get(0).getIsoverdue().equals((short)1)){//有逾期 不能提前还款
//				flag = false;
//  			}
// 		}
//		
//		if(!flag){//有逾期了
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"isoverdued_error");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！有逾期请先偿还逾期！,请重新操作或联系客服");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return  null;
//		}
// 		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.get(0).getTenderid());
//		if(tenderItem == null || 
//				tenderItem.getIsapartrepay() == null){//标的对象为null
//   			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,标的信息找不到,请重新操作或联系客服");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		AheadRepayMode aheadRepayMode = aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);
//		if(aheadRepayMode == null){//标的提前还款对象为null
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"aheadRepayMode_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,标的提前还款设置信息找不到！,请重新操作或联系客服");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		Short apartrepay = tenderItem.getIsapartrepay();//是否支持部分还款
//		/**
//		 * 判断是否支持部分还款，如果支持部分还款就显示部分还款和全部还款
//		 * 如果不支持部队还款就计算借款人 欠收利息罚金 平台罚金 
//		 */
//  		if(apartrepay.equals((short)1)) {//支持部分还款
//			List<RepayMent> repayMents4 = new ArrayList<>();
//			List<RepayMent> repayMents5 = null;
// 			/*判断是否已经全部提交客服审核了 如果是 给用户提示*/
// 			for(DividedPayments dividePayments3:dividedPayments){
//				Map<String,Object> repayMentHashMap = new HashMap<String,Object>();
//				repayMentHashMap.put("tenderid", dividePayments3.getTenderid());
//				repayMentHashMap.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//				repayMentHashMap.put("repaystatus", (short)1);	/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//				repayMentHashMap.put("periods", dividePayments3.getPeriods());
// 				repayMents5 = repayMentServiceI.findListRepayMentByConditions(repayMentHashMap);
// 				if(repayMents5.size() > 0){
// 					repayMents4.addAll(repayMents5);
// 				}
//			}
// 			
// 			if(!(repayMents4.size() > 0)){
// 				hashMap.put(Session_Constant.RESULT,Session_Constant.PARAMSERROR);
// 				hashMap.put(Session_Constant.RESULTCODE,"repayMentSize_null");
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,未找到投资人还款计划信息,请重新操作或联系客服！");
// 				 String st = JSON.toJSONString(hashMap);
// 				 try {
// 					StringUtil.sendJsonData(response, st);
// 				} catch (IOException e) {
// 					e.printStackTrace();
// 				}
// 				return  null;
// 			}
// 			 
//			session.setAttribute(Session_Constant.AHEADREPAY_DIVMENTS, opid);//借款人选择提前还款的期数  后续操作需要此值
// 			hashMap.put(Session_Constant.RESULT, "apartSelet_success");
//			hashMap.put(Session_Constant.RESULTCODE,"apartSelet_success");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！提交客服审核成功！");
//			 String st = JSON.toJSONString(hashMap);
//			 try {
//				StringUtil.sendJsonData(response, st);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return  null;
// 		} else {//全部提前还款
// 			 
//			/*这里需要验证是否跳期还款*/
//			DividedPayments dividedPayments6 = dividedPayments.get(0);//当期
//			boolean checkIsRepayMent = aheadRepayMentLogicI.hasDividedPaymentsAheadRepayMent(dividedPayments6);//检验是否跳期还款
//			if(!checkIsRepayMent){//不允许跳期还款
//      			 hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//    			 hashMap.put(Session_Constant.RESULTCODE,"jumpPeriod_error");
//    			 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！不允许跳期还款！");
//				 String st = JSON.toJSONString(hashMap);
//				 try {
//					StringUtil.sendJsonData(response, st);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return  null;
//			}
//			
// 			/*判断提前还款 计息方式是按天计息 还是全额计息*/
//			DecimalFormat format = new DecimalFormat("#####################0.00");
//			Short Intmode = aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
// 			if(Intmode.equals((short)1)){//1占天利息
// 				Double ramountCount								= 0.00;//本次提前还款本金总和
// 				Double ramount									= 0.00;//单个投资人本次提前还款本金总和
// 				Double rinterest								= 0.00;//本金利息 （原应得利息）
//   				Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
// 				Double platformPenaltyRamountCount				= 0.00;//借款人补偿平台本金利息补偿总和
// 				Double penaltyRamountCount						= 0.00;//借款人补偿投资人本金利息补偿总和
// 				Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
// 				boolean falg = false;
// 				List<RepayMent> AheadRepayMentsGather = new ArrayList<>();
// 				List<RepayMent> AheadRepayMents = null;
// 				
// 				List<RepayMentFineDetail> repayMentFineDetails = new ArrayList<>();
// 				
// 				//计算提前还款当期的持有利息
// 				Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
// 				Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
// 				Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
// 				Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
// 				Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
// 				
// 				Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
// 				Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
// 				Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
// 				Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
// 				Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//  				
// 				Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
// 				Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
// 				Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
// 				Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
// 				Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
// 				Double penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
// 				Double platformPenaltyRamount					= 0.00;//借款人补偿平台本金利息补偿
//  				 
// 				/*因为不允许部分还款，所以是不存在有一部分人已经还款的情况的*/
// 				Map<String,Object> hashMapRepayAll = new HashMap<String,Object>();
// 				DividedPayments dividedPayments7 = dividedPayments.get(dividedPayments.size()-1);//本次提前还款最后一期
// 				hashMapRepayAll.put("tenderid", dividedPayments7.getTenderid());//标号ID
//  				hashMapRepayAll.put("periods", dividedPayments7.getPeriods());//还款期数（第几期）
// 				hashMapRepayAll.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
// 				hashMapRepayAll.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
// 				List<RepayMent> repayMentsAll = repayMentServiceI.findListRepayMentByConditions(hashMapRepayAll);
// 				if(repayMentsAll.size() == 0 ){
// 					return null;
// 				}
// 				String  utreoStr = "";
// 				for(RepayMent repayMent8 : repayMentsAll){
// 					if(repayMent8.getIsdarepay().equals((short)1)){//是否债转还款(投标记录发生过债转 1是 0 否       注：如原投标发生债权转让  生成新还款计划 原投标数据保存为0，债权转让数据的保存为1)
// 						utreoStr += repayMent8.getDaorderno() +",";
// 					}else{
//  						utreoStr += repayMent8.getUtorderno() +",";
// 					}
// 				}
// 				
// 				utreoStr = utreoStr.substring(0,utreoStr.lastIndexOf(","));//截取
// 				String[] utreoStrs 			= utreoStr.split(",");//分割 投标订单号
// 				UserTender userTender 		= null; //投标记录 对象
// 				UserBaseAccountInfo outUserBaseAccountInfo = null;//投资人用户基本信息对象
//  	 			for(int i = 0 ; i < utreoStrs.length ; i++){
//  					for(DividedPayments dividedPayments4 : dividedPayments){
//  						if(dividedPayments4.getPeriods() != null && dividedPayments4.getTenderid() != null){
//  							Map<String,Object> map2 = new HashMap<String,Object>();
//  							map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//  							map2.put("utordernoordaorderno",utreoStrs[i]);//投标订单号
//  							map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//  							map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//  							map2.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//  							AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//  							if(AheadRepayMents.size() > 0){
//  								AheadRepayMentsGather.addAll(AheadRepayMents);
//  							}
//   	 					}
// 	   				}
// 					
// 					RepayMentFineDetail fineDetail = new  RepayMentFineDetail();
//  					for(RepayMent ment : AheadRepayMentsGather){
// 						if(ment.getPeriods().equals(dividedPayments6.getPeriods())){//提前还款 当期还款
// 							boolean Aheadflag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//验证投资人是否跳期提前还款
// 							if(!Aheadflag){//投资人存在跳期还款 不允许提前还款
// 								falg = true;
// 								break;
// 							}
// 							
// 	 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
// 	 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
// 	 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
// 	 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//	 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
//
//  			 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
// 			 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
// 			 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
// 			 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
//			 				theCurrentPeriodBadCropDisablevoucherint= Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//
// 			 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodBadCropDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
// 			 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
// 			 				
// 							theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期持有利息总和
// 							ramount 							 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() +  ment.getDisablevoucher() + 0.00 + ramount));//单个投资人本次提前还款总本金
// 							ramountCount 						 = Double.valueOf(format.format(ramount + 0.00 + ramountCount));//本次提前还款总本金
// 							rinterest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() +  ment.getDisablevoucherint() + rinterest));//本金利息 （原应得利息）
//  						}else{
// 	  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  +  ment.getDisablevoucherint() + 0.00 + borrowerCompensationInvestorsCount));//单个投资人剩余期数欠收本金利息总和
// 	  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(borrowerCompensationInvestorsCount + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
// 							ramount 							 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramount));//单个投资人本次提前还款总本金
// 							ramountCount 						 = Double.valueOf(format.format(ramount + 0.00 + ramountCount));//本次提前还款总本金
// 							rinterest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  +  ment.getDisablevoucherint() + rinterest));//本金利息 （原应得利息）
//  						}
// 					}
//  					
//  					if(falg){//投资人存在跳期还款 不允许提前还款
//  						break;
//  					}
//  					
// 	 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + 0.00));//单个投资人欠收利息
// 					borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + pBorrowerCompensationInvestorsCount 
// 							+ theCurrentPeriodPBadCropInterestCount + borrowerCompensationPlatformCount));//全部投资人欠收利息
// 					
// 					Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
// 					if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
// 	 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments6.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
// 						if(aheadRepay != null){//找到符合补偿的区间段
// 							Short AwardType = aheadRepay.getAwardtype();//奖励方式（1惩罚借款人，2平台奖励，3 1+2）
// 							if(AwardType != null && AwardType.equals((short)1)
// 									|| AwardType.equals((short)3)){//惩罚借款人 或惩罚借款人和平台奖励
// 								if(aheadRepay.getPenaltyrate() != null && aheadRepay.getPenaltyrate() > 0){//百分比
// 									penaltyRamount =  Double.valueOf(format.format(singleInvestorsCount * aheadRepay.getPenaltyrate() + 0.00)) ;
// 									if(aheadRepay.getMaxpenalty() != null 
// 											&& penaltyRamount >= aheadRepay.getMaxpenalty()){//借款人罚金最大值
// 										penaltyRamount 	= aheadRepay.getMaxpenalty();
// 									}
// 									penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
// 								}else{//定额
// 									penaltyRamount 		= aheadRepay.getPenaltyquota() == null ? 0.00 : aheadRepay.getPenaltyquota();
// 									penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
// 								}
// 							}
// 						}
// 					}
// 					
// 					userTender = userTenderServiceI.findUserTenderByOrderno(utreoStrs[i]);
//  	 				if(userTender != null && userTender.getOutaccountid() != null){
//  	 					outUserBaseAccountInfo = userBaseAccountInfoServiceI
//  	 							.getUserBaseAccountInfoById(userTender.getOutaccountid());
//  	 					outUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(outUserBaseAccountInfo);//解密 加密的数据
//  	 					if(outUserBaseAccountInfo != null && outUserBaseAccountInfo.getLoginname() != null){
//  	 						fineDetail.setUsername(outUserBaseAccountInfo.getLoginname());/* 投资人用户名 */
//  	 					}
//  	 				}
//   	 				 fineDetail.setHoldrinterest(theCurrentPeriodHoldInterest + theCurrentPeriodHoldRvoucherint);/*单个投资人持有利息*/
// 					 fineDetail.setRamount(ramount);/* 单个投资人 本次提前还款全部本金 */
//  					 fineDetail.setHarvestfine(penaltyRamount);/* 欠收利息罚金 */
//  					 fineDetail.setRinterest(rinterest);/* 本金利息原应得利息） */
//  					 repayMentFineDetails.add(fineDetail);
//  					 
// 					 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
// 					 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
// 					 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
// 					 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
// 					 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
// 		 			
// 					 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
// 					 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
// 					 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
// 					 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
// 					 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
// 	 				
// 	  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
// 					 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
// 		  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
// 		 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
// 		 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
// 	 				 penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
//					 ramount 							 	= 0.00;//单个投资人本次提前还款总本金
//					 rinterest								= 0.00;//本金利息 （原应得利息）
//  	 				 AheadRepayMents 						= null;
// 	 				 AheadRepayMentsGather.clear();//清除已经计算过的值
// 	     		}
// 				
// 	  			if(falg){
// 	  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 	  				hashMap.put(Session_Constant.RESULTCODE,"jumpPeriod_error");
//    			 	hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！不允许跳期还款！");
// 					 String st = JSON.toJSONString(hashMap);
// 					 try {
// 						StringUtil.sendJsonData(response, st);
// 					} catch (IOException e) {
// 						e.printStackTrace();
// 					}
// 					return  null;
// 				}
// 	  			
// 	  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
// 	  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
// 	   				//计算借款人补偿平台罚金
// 	  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
// 	  						findAheadRepayPlatformByHoldInterestCount(dividedPayments6.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
// 	  				if(aheadRepayPlatform != null){//找到符合补偿的区间段
// 	  					if(aheadRepayPlatform.getAwardplatrate() != null 
// 	  							&& aheadRepayPlatform.getAwardplatrate() > 0){//百分比
// 	  						platformPenaltyRamount = Double.valueOf(format.format(borrowerCompensationPlatformCount * aheadRepayPlatform.getAwardplatrate()));
// 	  						if(aheadRepayPlatform.getAwardplatminmoney() != null 
// 	  								&& aheadRepayPlatform.getAwardplatmaxmoney() != null){
// 	  							if(aheadRepayPlatform.getAwardplatminmoney() <= platformPenaltyRamount){
// 	  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatminmoney();
// 	  							}else if(platformPenaltyRamount >= aheadRepayPlatform.getAwardplatmaxmoney()){
// 	  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatmaxmoney();
// 	  							}
// 	  						}
// 	  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));
// 	  					}else{//定额
// 	  						platformPenaltyRamount = aheadRepayPlatform.getAwardplatquota() == null ? 0.00 : aheadRepayPlatform.getAwardplatquota();
// 	  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));
// 	  					}
// 	  				}
// 	  			}
// 	  			Double Count = Double.valueOf(format.format(ramountCount + theCurrentPeriodHoldInterestCount 
// 	  					+ platformPenaltyRamountCount + penaltyRamountCount + 0.00));//该还总金额
// 	  			 
// 	  			session.setAttribute(Session_Constant.AHEADALLREPAYMENT_DIVMENTS, opid);
// 	  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, Count.toString());
//   	  			hashMap.put(Session_Constant.RESULT, "allAheadApartInterest_success");
// 	  			hashMap.put(Session_Constant.RESULTCODE,"allAheadApartInterest_success");
// 	  			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！");
// 	  			hashMap.put("list", JSON.toJSONString(repayMentFineDetails));
// 	  			hashMap.put("count", Count.toString());
// 	  			String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
// 				if(StringUtil.isNotEmpty(token)){//防止表单重复提交   
// 					hashMap.put("token", token);
// 				}
// 	  			String str = JSON.toJSONString(hashMap);
// 	  			try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
// 					e.printStackTrace();
//				}
//  	  			return null; 
//  	  			
//   			}else{//全额利息
//    			Double interestCount = 0.00;//本次提前还款利息总和
//  				Double ramountCount  = 0.00;//本次提前还款金额总和
//  				Double interest		 = 0.00;//单个投资人本次提前还款利息总和
//  				Double ramount		 = 0.00;//单个投资人本次提前还款金额总和
//  				DividedPayments allDividedPayments4 = dividedPayments.get(dividedPayments.size() - 1);//本次提前还款 最后一期
//   				Map<String,Object> hashMapRepayAll = new HashMap<String,Object>();
//  				hashMapRepayAll.put("tenderid", allDividedPayments4.getTenderid());//标号ID
//  				hashMapRepayAll.put("periods", allDividedPayments4.getPeriods());//还款期数（第几期）
// 				hashMapRepayAll.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
// 				hashMapRepayAll.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
// 				List<RepayMent> repayMentsAll = repayMentServiceI.findListRepayMentByConditions(hashMapRepayAll);
// 				
// 				if(!(repayMentsAll.size() > 0 )){
//   		  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 	   				hashMap.put(Session_Constant.RESULTCODE,"repaySize_error");
// 	   				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,没有找到投资人还款具体计划！请重新操作！");
//  	 	  			String str = JSON.toJSONString(hashMap);
// 	 	  			try {
// 						StringUtil.sendJsonData(response, str);
// 					} catch (IOException e) {
// 	 					e.printStackTrace();
// 					}
// 	   	 			return  null; 
// 				}
// 				
// 				String  utreoStr = "";
// 				for(RepayMent repayMent8 : repayMentsAll){
//  					if(repayMent8.getIsdarepay().equals((short)1)){//是否债转还款(投标记录发生过债转 1是 0 否       注：如原投标发生债权转让  生成新还款计划 原投标数据保存为0，债权转让数据的保存为1)
// 						utreoStr += repayMent8.getDaorderno() +",";
// 					}else{
//  						utreoStr += repayMent8.getUtorderno() +",";
// 					}
// 				}
// 				
// 				utreoStr = utreoStr.substring(0,utreoStr.lastIndexOf(","));//截取
// 				String[] utreoStrs 			= utreoStr.split(",");//分割 投标订单号
// 				UserTender userTender 		= null; //投标记录 对象
// 				UserBaseAccountInfo outUserBaseAccountInfo = null;//投资人用户基本信息对象
// 				List<RepayMent> AheadRepayMents2 = null;
// 				List<RepayMent> AheadRepayMentsGather2 = new ArrayList<RepayMent>();
// 				List<RepayMentFineDetail> RepayMentFineDetails = new ArrayList<RepayMentFineDetail>();
//  	 			for(int i = 0 ; i < utreoStrs.length ; i++){
//  	 				for(DividedPayments dividedPayments4 : dividedPayments){
//  	 					if(dividedPayments4.getPeriods() != null && dividedPayments4.getTenderid() != null){
//   	 						Map<String,Object> map2 = new HashMap<String,Object>();
//  	 						map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//  	 						map2.put("utordernoordaorderno",utreoStrs[i]);//投标订单号 or 债权转让订单号
//  	 						map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//  	 						map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//  	 						map2.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//  	 						AheadRepayMents2 = repayMentServiceI.findListRepayMentByConditions(map2);
//  	 						if(AheadRepayMents2.size() > 0){
//  	 							AheadRepayMentsGather2.addAll(AheadRepayMents2);
//  	 						}
//  	 					}
// 	   				}
//  	 				
//  	 				RepayMentFineDetail repayMentFineDetail = new RepayMentFineDetail();
//  	 				if(AheadRepayMentsGather2.size() > 0){
//  	 					for(RepayMent ment100 : AheadRepayMentsGather2){
//  	 						interest = Double.valueOf(format.format(ment100.getRinterest() + ment100.getRvoucherint() + ment100.getDisablevoucherint() + interest));
//  	 						ramount  = Double.valueOf(format.format(ment100.getRamount() + ment100.getRvoucher() + ment100.getDisablevoucher() + ramount));
//  	 					}
//  	 				}
//  	 				
//  	 				userTender = userTenderServiceI.findUserTenderByOrderno(utreoStrs[i]);
//  	 				if(userTender != null && userTender.getOutaccountid() != null){
//  	 					outUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(userTender.getOutaccountid());
//  	 					outUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(outUserBaseAccountInfo);//解密加密的数据
//  	 					if(outUserBaseAccountInfo != null && outUserBaseAccountInfo.getLoginname() != null ){
//  	 						repayMentFineDetail.setUsername(outUserBaseAccountInfo.getLoginname());/* 投资人用户名 */
//  	 					}
//  	 				}
//  	 				
//  	 				repayMentFineDetail.setRamount(ramount);/* 本金 */
//  	 				repayMentFineDetail.setRinterest(interest);/* 本金利息 （原应得利息）*/
//  	 				repayMentFineDetail.setHoldrinterest(interest);/* 持有利息 */
//  	 				repayMentFineDetail.setHarvestfine(0.00);/* 欠收利息罚金 */
//  	 				RepayMentFineDetails.add(repayMentFineDetail);
//  	 				
//  	 				interestCount = Double.valueOf(format.format(interest + interestCount));
//  	 				ramountCount  = Double.valueOf(format.format(ramount + ramountCount));
//  	 				
//   	 				AheadRepayMents2 = null;
//  	 				AheadRepayMentsGather2.clear();
//  	 				interest	 = 0.00;//单个投资人本次提前还款利息总和
//  	  				ramount		 = 0.00;//单个投资人本次提前还款金额总和
//  	 			}
//  	 			
//  	 			Double Count = Double.valueOf(format.format(ramountCount +  interestCount  + 0.00));
// 	  			session.setAttribute(Session_Constant.AHEADALLREPAYMENT_DIVMENTS, opid);
// 	  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, Count.toString());
// 	  			hashMap.put(Session_Constant.RESULT,"allAheadAllInterest_success");
// 	  			hashMap.put(Session_Constant.RESULTCODE,"allAheadAllInterest_success");
//  	  			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！");
//  	  			hashMap.put("list", JSON.toJSONString(RepayMentFineDetails));
// 	  			hashMap.put("count", Count.toString());
// 	  			String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
// 				if(StringUtil.isNotEmpty(token)){//防止表单重复提交   
// 					hashMap.put("token", token);
// 				}
// 	  			String str = JSON.toJSONString(hashMap);
// 	  			try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
// 					e.printStackTrace();
//				}
//   	 			return  null; 
//			}
//			
// 		}
// 	}
//	
//	 
//	/**
//	 * 
//	* @Title: selectPartAheadRepayMent 
//	* @Description: TODO(提前还款 显示部分投资人  让借款人选择部分投资人提前还款) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/selectPartAheadRepayMent",method = RequestMethod.POST)
//	public String selectPartAheadRepayMent(HttpServletRequest request,HttpServletResponse response ,
//			Model model ){
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//  		if(accountInfo == null){//用户没有登录
// 			model.addAttribute(Session_Constant.RESULT, "logout");
// 			model.addAttribute(Session_Constant.MESSAGE, "操作超时！请登录后重新操作！");
// 			return "user/manager/repayment/repaymenterror";
//		}
// 		
//		String opid = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAY_DIVMENTS);//借款人选择的提前还款期数id
//		if(StringUtil.isEmpty(opid)){
//   			model.addAttribute(Session_Constant.RESULT, "params_error");
// 			model.addAttribute(Session_Constant.MESSAGE, "操作失败！参数错误！请重新操作！");
// 			return "user/manager/repayment/repaymenterror";
//		}
//		
//  		String[] opids = opid.split(",");//借款人还款计划期数切割
//		DividedPayments dividedPayments = null;
//		List<DividedPayments> dividedPayments2 = new ArrayList<>();
//		for(int i = 0 ; i < opids.length ;i++){
//			dividedPayments = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opids[i]));
//			if(dividedPayments != null){
//				dividedPayments2.add(dividedPayments);
//			}
//		}
//		
//		if(!(dividedPayments2.size() > 0)){//没有找到借款人还款计划
//   			model.addAttribute(Session_Constant.RESULT, "params_error");
// 			model.addAttribute(Session_Constant.MESSAGE, "操作失败！没有找到借款人还款信息！请重新操作！");
// 			return "user/manager/repayment/repaymenterror";
//		}
//		
// 		List<RepayMent> repayMents  = new ArrayList<>();
//		List<RepayMent> repayMent = null;
//		Collections.sort(dividedPayments2,new DividedPaymentsComparator());
//		DividedPayments dividedPayments45 = dividedPayments2.get(0);
//		for(DividedPayments dividedPayment3 : dividedPayments2){
//			Map<String,Object> hashMaps = new HashMap<String,Object>();
//			hashMaps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			hashMaps.put("repaystatus", (short)1);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
// 			hashMaps.put("tenderid", dividedPayment3.getTenderid());//标号ID
//			hashMaps.put("periods", dividedPayment3.getPeriods());//还款期数（第几期）
//  			repayMent = repayMentServiceI.findListRepayMentByConditions(hashMaps);
// 			if(repayMent.size() > 0){
// 				repayMents.addAll(repayMent);
// 			}
//		}
//		
//		if(!(repayMents.size() > 0 )){//没有找到投资人还款计划
//			model.addAttribute(Session_Constant.RESULT, "params_error");
// 			model.addAttribute(Session_Constant.MESSAGE, "操作失败！没有找到投资人还款计划！请重新操作！");
// 			return "user/manager/repayment/repaymenterror";
//		}
//  	
// 		List<RepayMentFineDetail> repayMentFineDetails = new ArrayList<>();
//  		Set<String> hashSet = new HashSet<>();
// 		for(RepayMent ment : repayMents){
// 			if(ment.getIsdarepay().equals((short)1)){
// 				/*是否债转还款(投标记录发生过债转 1是 0 否   
// 				 *  注：如原投标发生部分债权转让  生成新还款计划 原投标数据保存为0，债权转让数据的保存为1) 
// 				 *  如：
// 				 *  A 投资1000元  转让500     给B   A原还款计划作废    生成新的还款计划       A 保存为 0  B 保存为1 
// 				 *  A 投资1000元  转让1000  给B   A原还款计划作废    生成新的还款计划      A 已全部转让 不需要重新生成    B 保存为1 
// 				 *  */
// 				if(!hashSet.contains(ment.getDaorderno())){
// 					hashSet.add(ment.getDaorderno());
// 				}
//  			}else{
//  				if(!hashSet.contains(ment.getUtorderno())){
// 					hashSet.add(ment.getUtorderno());
// 				}
// 			}
//  		}
// 		
//		Iterator<String> iterator = hashSet.iterator();
//		String utorderno = "";//投标订单号
//		Double ramount = 0.00;
//		Double rinterest = 0.00;
//		Double count = 0.00;
//		Double detailCount = 0.00;
//		RepayMent repayMentstr =  null;
//		UserBaseAccountInfo userBaseAccountInfo = null;
//		Short Hasinvestor = 0;
//		while(iterator.hasNext()){
// 			utorderno = iterator.next();
// 			RepayMentFineDetail detail = new RepayMentFineDetail();
// 			List<AheadRepayMentDetail> aheadRepayMentDetails = new ArrayList<>();
//			for(RepayMent ment : repayMents){
//				if(ment.getIsdarepay().equals((short)1)){//债权转让
//					if(ment.getDaorderno().equals(utorderno)){
//	 					ramount = Double.valueOf(df1.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00));
//	 					rinterest = Double.valueOf(df1.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucher() + 0.00));
//	 					count = Double.valueOf(df1.format(ramount + rinterest + 0.00));
//	 					detailCount = Double.valueOf(df1.format(count + detailCount));
//	 					if(repayMentstr == null){
//	  						repayMentstr = ment;
//	 					}
//	 					if(dividedPayments45.getPeriods().equals(ment.getPeriods())){
//	 						boolean hasinvestor = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//判断是否跳期还款
//	 						if(hasinvestor){
//	 							Hasinvestor = 1;
//	 						}
//	 					}
//	 					RepayMentFineDetail.AheadRepayMentDetail  aheadRepayMentDetail = detail.new AheadRepayMentDetail();
//	 					aheadRepayMentDetail.setRamount(ramount);
//	 					aheadRepayMentDetail.setRinterest(rinterest);
//	 					aheadRepayMentDetail.setCount(count);
//	 					aheadRepayMentDetail.setPeriod(ment.getPeriods());
//	 					aheadRepayMentDetails.add(aheadRepayMentDetail);
//	 				}
//				}else{
//					if(ment.getUtorderno().equals(utorderno)){
//	 					ramount = Double.valueOf(df1.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00));
//	 					rinterest = Double.valueOf(df1.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucher() + 0.00));
//	 					count = Double.valueOf(df1.format(ramount + rinterest + 0.00));
//	 					detailCount = Double.valueOf(df1.format(count + detailCount));
//	 					if(repayMentstr == null){
//	  						repayMentstr = ment;
//	 					}
//	 					if(dividedPayments45.getPeriods().equals(ment.getPeriods())){
//	 						boolean hasinvestor = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//判断是否跳期还款
//	 						if(hasinvestor){
//	 							Hasinvestor = 1;
//	 						}
//	 					}
//	 					RepayMentFineDetail.AheadRepayMentDetail  aheadRepayMentDetail = detail.new AheadRepayMentDetail();
//	 					aheadRepayMentDetail.setRamount(ramount);
//	 					aheadRepayMentDetail.setRinterest(rinterest);
//	 					aheadRepayMentDetail.setCount(count);
//	 					aheadRepayMentDetail.setPeriod(ment.getPeriods());
//	 					aheadRepayMentDetails.add(aheadRepayMentDetail);
//	 				}
//				}
// 			}
//			userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMentstr.getInaccountid());//用户基本信息
//			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);//解密加密的数据
//			if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
//				detail.setUsername(userBaseAccountInfo.getLoginname());//用户名
//			}
//			 
//			detail.setHasinvestor(Hasinvestor);
//			detail.setUtorderno(utorderno);
//			detail.setCount(detailCount);
//			detail.setAheadRepayMentDetail(aheadRepayMentDetails);
//			repayMentFineDetails.add(detail);
//			detailCount = 0.00;
//			repayMentstr = null;
//			Hasinvestor = 0;
//		}
// 		 model.addAttribute("repay", repayMentFineDetails);
//		 return "user/manager/repayment/listTemplateAheadRepayMentPartSelect";
// 	} 
//	/**
//	 * 
//	* @Title: selectPartAheadRepayMentAfter 
//	* @Description: TODO(提前还款 借款人选择 部分投资人后 计算罚金信息显示给借款人看) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value="/partAheadAfter",method = RequestMethod.POST)
//	@Token(save = true)
//	public String selectPartAheadRepayMentAfter(HttpServletRequest request,HttpServletResponse response,Model model){
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
// 		if(accountInfo == null){//用户没有登录
//			 model.addAttribute("aheadFineError", "用户没有登录");
//			 return "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		String opid = request.getParameter("opid");//借款人选择了的 部分投资人 投标订单号
//		String divOpid = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAY_DIVMENTS);//借款人选择的提前还款期数
// 		if(StringUtil.isEmpty(opid) || StringUtil.isEmpty(divOpid)){
// 			 model.addAttribute("aheadFineError", "参数错误");
//			 return "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		String[] divOPids	 = divOpid.split(",");
//		List<DividedPayments> dividedPayments = new ArrayList<>();
//		DividedPayments dividedPayments2 = null;
//		for(int i = 0 ; i < divOPids.length ; i++){
//			dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(divOPids[i])) ;
//			if(dividedPayments2 != null){
//				dividedPayments.add(dividedPayments2);
//			}
//		}
//		
//		Collections.sort(dividedPayments, new DividedPaymentsComparator());//排序
//		DividedPayments dividedPayments3 = dividedPayments.get(0);//提前还款当期
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments3.getTenderid());//标的信息
//		AheadRepayMode aheadRepayMode = aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);//提前还款设置
// 		Short intmode = aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
// 		
// 		DecimalFormat format = new DecimalFormat("#########################0.00");
// 		String[] opids 		 = opid.split(",");//部分投资人的投标订单号
// 		List<RepayMent> AheadRepayMentsGather = new ArrayList<>();//提前还款提前还款投资人集合
// 		List<RepayMent> AheadRepayMents = null;
// 		boolean falg = false;//投资人是否跳期还款
// 		
// 		String token = (String) WebUtils.getSessionAttribute(request, "SessToken");
//		if(StringUtil.isNotEmpty(token)){
//			model.addAttribute("token", token);//防止表单重复提交
//		}
//		
//		if(intmode.equals((short)1)){//1占天利息
//			//计算提前还款当期的持有利息
//			Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
//			Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//			Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
//			Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//			Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//			
// 			Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//			Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
//			Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//			Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
//			Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//  			
//			Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//			Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//			Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//			Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//			Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//			Double penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
//			Double platformPenaltyRamount					= 0.00;//借款人补偿平台本金利息补偿
//			
//			Double ramountCount								= 0.00;//本次提前还款本金总和
//			Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
//			Double platformPenaltyRamountCount				= 0.00;//借款人补偿平台本金利息补偿总和
//			Double penaltyRamountCount						= 0.00;//借款人补偿投资人本金利息补偿总和
//			Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
//			
//			Double rinterest								= 0.00;//原应得利息
//			Double ramount									= 0.00;//单个投资人还款本金
//			List<RepayMentFineDetail> repayMentFineDetails = new ArrayList<>();
//			UserTender tender = null;
//			UserBaseAccountInfo accountInfo2 = null;
//			
// 			for(int i = 0 ; i < opids.length ; i++){
//				for(DividedPayments dividedPayments4 : dividedPayments){
// 					Map<String,Object> map2 = new HashMap<String,Object>();
//					map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//					map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					map2.put("repaystatus", (short)1);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//					AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//					if(AheadRepayMents.size() > 0){
//						AheadRepayMentsGather.addAll(AheadRepayMents);
//					}
//   				}
//				 
//				 if(AheadRepayMentsGather.size() > 0){
//					 for(RepayMent ment : AheadRepayMentsGather){
//						 	if(ment.getRepaystatus().equals((2)) || ment.getRepaystatus().equals((3))){/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//								continue;//已还款的 跳出循环
//							}
//							
//							if(ment.getPlanstatus().equals((short)2)){//还款计划状态(1有效，2无效)
//								continue;//无效的 跳出循环
//							}
//							
//							if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//								boolean Aheadflag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//验证投资人是否跳期提前还款
//								if(!Aheadflag){//投资人存在跳期还款 不允许提前还款
//									falg = true;
//									break;
//								}
//								
//		 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
//		 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
//		 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
//		 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//		 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
//		 						
//				 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
//				 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
//				 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
//				 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
//				 				theCurrentPeriodBadCropDisablevoucherint= Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//				 				
//				 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
//				 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
//				 				
//								theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + 0.00));//投资人当期持有利息总和
//								ramount 						     = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramount));//本次提前还款总本金
//								rinterest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + rinterest));//原应得利息 (作废的类现金利息  还款时返回平台 不进投资人账号)) 
//								
//							}else{
//		  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + ment.getDisablevoucherint() + 0.00 + borrowerCompensationInvestorsCount));//单个投资人剩余期数欠收本金利息总和
//		  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(ment.getRlvoucherint() + ment.getRintfee()  + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
//		  		 				ramount 						     = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramount));//本次提前还款总本金
//		  		 				rinterest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + rinterest));//原应得利息 (作废的类现金利息  还款时返回平台 不进投资人账号)) 
//		  		 				
//							}
//						}
//				 }
//				
//				if(falg){//投资人存在跳期还款 不允许提前还款
//					break;
//				}
//				
// 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + theCurrentPeriodBadCropDisablevoucherint + 0.00));//单个投资人欠收利息
//				borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + pBorrowerCompensationInvestorsCount 
//						+ theCurrentPeriodPBadCropInterestCount + borrowerCompensationPlatformCount));//全部投资人欠收利息
//				
//				Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//				if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
// 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments3.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
//					if(aheadRepay != null){//找到符合补偿的区间段
//						Short AwardType = aheadRepay.getAwardtype();//奖励方式（1惩罚借款人，2平台奖励，3 1+2）
//						if(AwardType != null && AwardType.equals((short)1)
//								|| AwardType.equals((short)3)){//惩罚借款人 或惩罚借款人和平台奖励
//							if(aheadRepay.getPenaltyrate() != null && aheadRepay.getPenaltyrate() > 0){//百分比
//								penaltyRamount =  Double.valueOf(format.format(singleInvestorsCount * aheadRepay.getPenaltyrate() + 0.00)) ;
//								if(aheadRepay.getMaxpenalty() != null 
//										&& penaltyRamount >= aheadRepay.getMaxpenalty()){//借款人罚金最大值
//									penaltyRamount = aheadRepay.getMaxpenalty();
//								}
//								penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
//							}else{//定额
//								penaltyRamount = aheadRepay.getPenaltyquota() == null ? 0.00 : aheadRepay.getPenaltyquota();
//								penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
//							}
//						}
//					}
//				}
//				
//				RepayMentFineDetail detail = new RepayMentFineDetail();
//				tender = userTenderServiceI.findUserTenderByOrderno(opids[i]);
//				if(tender != null && tender.getOutaccountid() != null){
//					accountInfo2 = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(tender.getOutaccountid());
//					accountInfo2 = getDecryptionUserBaseAccountInfoDetail(accountInfo2);//解密加密的数据
//					if(accountInfo2 != null && accountInfo2.getLoginname() != null){
// 						detail.setUsername(accountInfo2.getLoginname());/* 投资人用户名 */
//					}
//				}
//				
//				 detail.setRamount(ramount);/* 本金 */
//				 detail.setRinterest(rinterest);/* 本金利息 （原应得利息）*/
//				 detail.setHoldrinterest(theCurrentPeriodHoldInterest + theCurrentPeriodHoldRvoucherint);/* 持有利息 */
//				 detail.setHarvestfine(penaltyRamount);/* 借款人补偿投资人欠收利息罚金 */
// 				 repayMentFineDetails.add(detail);
// 				 ramountCount = Double.valueOf(format.format(ramount + ramountCount));//提前还款本金总和
// 				 
//				 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
//				 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//				 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
//				 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//				 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//
//	 			
//				 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//				 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
//				 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//				 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
//				 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
// 				
//  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//				 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//	  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//	 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//	 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
// 				 penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
// 				 rinterest								= 0.00;//原应得利息
// 				 ramount								= 0.00;//单个投资人还款本金
// 				 AheadRepayMents 						= null;
// 				 AheadRepayMentsGather.clear();//清除已经计算过的值
//     		}
//			
//  			if(falg){
// 				 model.addAttribute("aheadFineError", "不允许投资人跳期还款");
//				 return "user/manager/repayment/aheadFine/aheadFineError";
//			}
//  			
//  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
//   				//计算借款人补偿平台罚金
//  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
//  						findAheadRepayPlatformByHoldInterestCount(dividedPayments3.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
//  				if(aheadRepayPlatform != null){//找到符合补偿的区间段
//  					if(aheadRepayPlatform.getAwardplatrate() != null 
//  							&& aheadRepayPlatform.getAwardplatrate() > 0){//百分比
//  						platformPenaltyRamount = Double.valueOf(format.format(borrowerCompensationPlatformCount * aheadRepayPlatform.getAwardplatrate()));
//  						if(aheadRepayPlatform.getAwardplatminmoney() != null 
//  								&& aheadRepayPlatform.getAwardplatmaxmoney() != null){
//  							if(aheadRepayPlatform.getAwardplatminmoney() <= platformPenaltyRamount){
//  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatminmoney();
//  							}else if(platformPenaltyRamount >= aheadRepayPlatform.getAwardplatmaxmoney()){
//  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatmaxmoney();
//  							}
//  						}
//  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));
//  					}else{//定额
//  						platformPenaltyRamount = aheadRepayPlatform.getAwardplatquota() == null ? 0.00 : aheadRepayPlatform.getAwardplatquota();
//  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));//平台总罚金
//  					}
//  				}
//  			}
//  			
//  			Double Count = Double.valueOf(format.format(ramountCount + theCurrentPeriodHoldInterestCount 
//  					+ platformPenaltyRamountCount + penaltyRamountCount + 0.00));
//  			if(platformPenaltyRamountCount > 0){
//   				model.addAttribute("platformPenaltyRamount", platformPenaltyRamountCount.toString());
//  			}
//  			session.setAttribute(Session_Constant.AHEADPARTREPAY_INACCOUNTID_UTORDERNO, opid);//把选中的部分投资人的投标订单号放进session 作用域
//  			session.setAttribute(Session_Constant.AHEADREPAY_DIVMENTS, divOpid);//把选中借款人还款具体计划放进session 作用域
//  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, Count.toString());//把选中借款人还款具体计划放进session 作用域
//   			model.addAttribute("repayMentFineDetails", repayMentFineDetails);
//  			model.addAttribute("count", Count.toString());
//   			return "user/manager/repayment/aheadFine/partAheadRepayPartInterestDetail";
//   			
//  		} else {//2全额利息
// 			Double InterestCount = 0.00;//本次提前还款 利息总和
// 			Double RamountCount  = 0.00;//本次提前还款 本金总和
// 			Double Interest      = 0.00;//单个投资人本次提前还款 利息总和
// 			Double Ramount 		 = 0.00;//单个投资人本次提前还款 本金总和
// 			List<RepayMent> repayMents100 = null;
// 			List<RepayMent> repayMent101  = new ArrayList<RepayMent>();
// 			List<RepayMentFineDetail> RepayMentFineDetail = new ArrayList<RepayMentFineDetail>();
// 			UserTender userTender 		  = null;
// 			UserBaseAccountInfo baseAccountInfo = null;
//  			for(int i = 0 ; i < opids.length ; i++){
//				for(DividedPayments dividedPayments4 : dividedPayments){
// 					Map<String,Object> map2 = new HashMap<String,Object>();
//					map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//					map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					map2.put("repaystatus", (short)1);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//					repayMents100 = repayMentServiceI.findListRepayMentByConditions(map2);
//					if(repayMents100.size() > 0){
//						repayMent101.addAll(repayMents100);
//					}
//   				}
// 				
//				if(repayMent101.size() > 0){
//					for(RepayMent ment : repayMent101){
//						if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//							boolean Aheadflag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//验证投资人是否跳期提前还款
//							if(!Aheadflag){//投资人存在跳期还款 不允许提前还款
//								falg = true;
//								break;
//							}
//							
//							Interest    = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + 0.00 + Interest));//本次提前还款 利息总和
//							Ramount 	= Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + Ramount));//本次提前还款 本金总和
//						}else{
//							Interest    = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + ment.getDisablevoucherint() + 0.00 + Interest));//本次提前还款 利息总和
//							Ramount     = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher()  + ment.getDisablevoucher() + 0.00 + Ramount));//本次提前还款 本金总和
//	 					}
//					}
//				}
//				 
//				if(falg){//投资人存在跳期还款 不允许提前还款
//					break;
//				}
//				
//				RepayMentFineDetail mentFineDetail = new RepayMentFineDetail();
// 				userTender = userTenderServiceI.findUserTenderByOrderno(opids[i]);
//				if(userTender != null && userTender.getOutaccountid() != null){
//					baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(userTender.getOutaccountid());
//					baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
//					if(baseAccountInfo != null && baseAccountInfo.getLoginname() != null){
//						mentFineDetail.setUsername(baseAccountInfo.getLoginname());/*投资人用户名*/
//					}
//				}
//				
//				mentFineDetail.setRinterest(Interest);/*本金利息 （原应得利息*/
//				mentFineDetail.setRamount(Ramount);/*本金*/
//				mentFineDetail.setHoldrinterest(Interest);/*持有利息*/
//				mentFineDetail.setHarvestfine(0.00);/*欠收利息罚金*/
//				RepayMentFineDetail.add(mentFineDetail);
//				
//				InterestCount = Double.valueOf(format.format(Interest + InterestCount));
//				RamountCount  = Double.valueOf(format.format(Ramount + RamountCount));
//				
//				Interest      = 0.00;//单个投资人本次提前还款 利息总和
//	 			Ramount 	  = 0.00;//单个投资人本次提前还款 本金总和
//	 			
//				repayMents100 = null;
//				repayMent101.clear();
//   			}
// 			
// 			if(falg){
// 				 model.addAttribute("aheadFineError", "不允许投资人跳期还款");
//				 return "user/manager/repayment/aheadFine/aheadFineError";
// 			}
// 			
// 			Double Count = Double.valueOf(format.format(RamountCount + InterestCount + 0.00));
//  			 
//   			session.setAttribute(Session_Constant.AHEADPARTREPAY_INACCOUNTID_UTORDERNO, opid);//把选中的部分投资人的投标订单号放进session 作用域
//  			session.setAttribute(Session_Constant.AHEADREPAY_DIVMENTS, divOpid);//把选中借款人还款具体计划放进session 作用域
//  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, Count.toString());//把选中借款人还款总金额session 作用域
//  			 
//   			model.addAttribute("repayMentFineDetails", RepayMentFineDetail);
//    		model.addAttribute("count", Count.toString());
//  			return "user/manager/repayment/aheadFine/partAheadRepayAllInterestDetail";
//  		}
// 	}
//	
//	/**
//	 * @throws Exception 
// 	* @Title: aheadPartRepayMentTask 
//	* @Description: TODO(提前还款   部分还款选择 调用提前还款接口还款 （这里是借款人选择了部分投资人还款，显示给借款人罚金金额和平台罚金确定后进这里的）) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/aheadPartRepayMentTask",method = RequestMethod.POST)
//	@Token(remove = true)
//	public String aheadPartRepayMentTask(HttpServletRequest request,HttpServletResponse response) throws Exception{
// 			UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//			Map<String,String> hashMap = new HashMap<String,String>();
//			if(accountInfo == null){//用户没有登录
//  	 			hashMap.put(Session_Constant.RESULT, "logout");
//				hashMap.put(Session_Constant.RESULTCODE,"logout");
//				hashMap.put(Session_Constant.MESSAGE, "提示：因您操作超时,请重新登录后操作");
// 				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//			
//			String opid = (String) request.getSession().getAttribute(Session_Constant.AHEADPARTREPAY_INACCOUNTID_UTORDERNO);;//借款人选择了的 部分投资人 投标订单号
//			String divOpid = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAY_DIVMENTS);//借款人选择的提前还款期数
//			String AheadRamountCount = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT);//借款人本次提前还款总金额
//			
//	 		if(StringUtil.isEmpty(opid) 
//	 				|| StringUtil.isEmpty(divOpid)
//	 				|| StringUtil.isEmpty(AheadRamountCount)){//session失效 参数为null 重新操作
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,参数错误,请重新操作或联系客服!");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//	  		
//			String[] divOPids	 = divOpid.split(",");
//			List<DividedPayments> dividedPayments = new ArrayList<>();
//			DividedPayments dividedPayments2 = null;
//			for(int i = 0 ; i < divOPids.length ; i++){
//				dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(divOPids[i])) ;
//				if(dividedPayments2 != null){
//					dividedPayments.add(dividedPayments2);
//				}
//			}
//			
//			Collections.sort(dividedPayments, new DividedPaymentsComparator());//排序
//			DividedPayments dividedPayments3 = dividedPayments.get(0);//提前还款当期
//			String bacthNo 		  = StringUtil.getNo();//本次提前还款 批次号
//			TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments3.getTenderid());//标的信息
//			if(tenderItem == null || tenderItem.getRepaymenttype() == null || tenderItem.getTno() == null){
//  	 			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"tenderItem_null");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,标的信息找不到,请重新操作或联系客服！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//	 		}
//			
//			loanapp loanapp = loanappServiceI.selectByPrimaryKey(tenderItem.getLoanappid());
//			if(loanapp == null ){
//  	 			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"loanapp_null");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,借款申请信息找不到,请重新操作或联系客服！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//	 		}
//			
//			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(loanapp.getBaseid());
//			if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"userFsAccountInfo_null");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,银行电子账号信息找不到,请重新操作或联系客服！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}	
//			
//			userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//			AheadRepayMode aheadRepayMode 	= aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);//提前还款设置
//			if(aheadRepayMode == null || aheadRepayMode.getIntmode() == null){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE,"aheadRepayMode_null");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,标的提前还款设置信息找不到,请重新操作或联系客服！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//  	  		 
//			Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),AheadRamountCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE + bacthNo);
//			boolean fg = (boolean) resMap.get("flag");
//			String tre = (String) resMap.get("result");
//			if(!fg){
//				if(tre.equals("balance_deficiency")){
//  					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//					hashMap.put(Session_Constant.RESULTCODE,"balance_deficiency");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,账号余额不足,请重新充值后操作或联系客服！");
//				}else{
//  	 				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//					hashMap.put(Session_Constant.RESULTCODE,"freeze_error");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！");
//				}
//					String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					return null;
//			}
// 			 
//			Short repaymentType				= tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
//	 		Short intmode 					= aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
//	   		DecimalFormat format			= new DecimalFormat("#########################0.00");
//	 		RepayMent repayMent 			= null;
//	 		String[] opids 		 			= opid.split(",");//部分投资人的投标订单号
//	 		List<RepayMent> AheadRepayMentsGather = new ArrayList<>();//提前还款提前还款投资人集合
//	 		List<RepayMent> AheadRepayMents = null;
// 			if(intmode.equals((short)1)){//1占天利息
// 				//计算提前还款当期的持有利息
//				Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
//				Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//				Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
//				Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//				Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//				
//				Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//				Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
//				Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//				Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
//				Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//				
//				Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
//				Double ramountCount								= 0.00;//本次提前还款本金总和
//				Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//				Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//				Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//				Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//				Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//				Double singlePulsInvestorsCount					= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//				
//				Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
// 				BigDecimal outaccountid 						= null;//借款人ID
//	 			for(int i = 0 ; i < opids.length ; i++){
//					for(DividedPayments dividedPayments4 : dividedPayments){
//	 					Map<String,Object> map2 = new HashMap<String,Object>();
//						map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//						map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//						map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//						map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//						map2.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//						AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//						if(AheadRepayMents.size() > 0){
//							AheadRepayMentsGather.addAll(AheadRepayMents);
//						}
//	   				}
//					
//					 if(AheadRepayMentsGather.size() > 0){
//						 for(RepayMent ment : AheadRepayMentsGather){
//								if(ment.getRepaystatus().equals((2)) || ment.getRepaystatus().equals((3))){/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//									continue;//已还款的 跳出循环
//								}
//								
//								if(ment.getPlanstatus().equals((short)2)){//还款计划状态(1有效，2无效)
//									continue;//无效的 跳出循环
//								}
//								
//								if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//	  		 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
//			 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
//			 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
//			 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//			 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
//
// 					 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
//					 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
//					 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
//					 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
//					 				theCurrentPeriodBadCropDisablevoucherint = Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//
//					 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodBadCropDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
//					 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
//					 				
//									theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期持有利息总和
//									ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher()  + ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//									AheadRepayMentCalculatedParameter Ater = new AheadRepayMentCalculatedParameter();
//									Ater.setHoldinterest(theCurrentPeriodHoldInterest); //投资人当期持有本金利息
//									Ater.setHoldrvoucherint(theCurrentPeriodHoldRvoucherint); //投资人当期持有类现金利息
//									Ater.setHoldrlvoucherint(theCurrentPeriodHoldRlvoucherint); //投资人当期持有假现金利息
//									Ater.setHoldrintfee(theCurrentPeriodHoldRintfee); //投资人当期持有加息券利息
//									Ater.setBadcropinterest(theCurrentPeriodBadCropInterest); //投资人当期欠收本金利息
//									Ater.setBadcroprvoucherint(theCurrentPeriodBadCropRvoucherint); //投资人当期欠收类现金利息
//									Ater.setBadcroprlvoucherint(theCurrentPeriodBadCropRlvoucherint); //投资人当期欠收假现金利息
//									Ater.setBadcroprintfee(theCurrentPeriodBadCropRintfee); //投资人当期欠收加息券利息
//									Ater.setDiscardvoucherint(theCurrentPeriodHoldDisablevoucherint);//提前还款实际作废类现金的利息
//									Ater.setNorecdiscardvoucherint(theCurrentPeriodBadCropDisablevoucherint);//提前还款欠收作废类现金的利息
//			 						aheadRepayMentLogicI.insertAheadRealRepaymentRecordByTheCurrentPeriod(ment, Ater, tenderItem, bacthNo);//添加提前还款实际利息记录
// 									//记录当期 提前还款  按天计息提前实际还款记录
//			 						ment.setRepaystatus((short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//			 						repayMentServiceI.updateById(ment);
//								}else{
//			  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + ment.getDisablevoucherint() + 0.00 + borrowerCompensationInvestorsCount));//单个投资人剩余期数欠收本金利息总和
//			  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(ment.getRlvoucherint() + ment.getRintfee()  + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
//			  		 				ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//			  		 				aheadRepayMentLogicI.insertAheadRealRepaymentRecordByOtherPeriod(ment,bacthNo);//添加提前还款实际利息记录
//			  		 				ment.setRepaystatus((short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//			 						repayMentServiceI.updateById(ment);
// 								}
//	 					 } 
//					}
//	 				  
//					if(AheadRepayMentsGather.size() > 0){
//		 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + theCurrentPeriodBadCropDisablevoucherint+ 0.00));//单个投资人欠收本金，类现金利息
//		 				singlePulsInvestorsCount					= Double.valueOf(format.format(pBorrowerCompensationInvestorsCount + theCurrentPeriodPBadCropInterestCount + 0.00));//单个投资人欠收增益利息
//						borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + singlePulsInvestorsCount + borrowerCompensationPlatformCount + 0.00));//全部投资人欠收利息
//						repayMent = AheadRepayMentsGather.get(0);
//						if(outaccountid == null){
//		 					outaccountid = repayMent.getOutaccountid();//借款人ID
//						}
//						//借款人补偿投资人本金利息欠收补偿
//						Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//						if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
//		 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments3.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
//		 					aheadRepayMentLogicI.insertAheadRepayRecord(aheadRepay, singleInvestorsCount, bacthNo, repayMent);//添加借款人补偿投资人本金欠收利息 记录
//						 }
// 						//平台补偿 投资人增益利息补偿
//						Short isPlusCompensateOn = tenderItem.getIspluscompensateon();//提前还款增益利息补偿开关(0 关 1开)
//						if(isPlusCompensateOn != null && ispicompensateon.equals((short)1) && singlePulsInvestorsCount > 0){
//		 					AheadRepayAward aheadRepayAward = aheadRepayMentLogicI.findAheadRepayAwardByHoldInterestCount(tenderItem.getId(), singlePulsInvestorsCount);
//		 					aheadRepayMentLogicI.insertAheadRepayAwardRecord(aheadRepayAward, singlePulsInvestorsCount, bacthNo, repayMent);//添加平台补偿投资人增益欠收利息 记录
//						} 
//						
//						 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
//						 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//						 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
//						 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//						 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//						 
//						 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//						 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
//						 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//						 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
//						 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//		 				
//		  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//						 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//			  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//			 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//			 			 
//			 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//			 			 singlePulsInvestorsCount				= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//		  				 
//		 				 AheadRepayMents 						= null;
//		 				 AheadRepayMentsGather.clear();//清除已经计算过的值
//					}
//	     		}
//				
//	   			//借款人补偿平台总投资人欠收利息
//	  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//	  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
//	   				//计算借款人补偿平台罚金
//	  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
//	  						findAheadRepayPlatformByHoldInterestCount(dividedPayments3.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
//	 				aheadRepayMentLogicI.insertAheadRepayPlatformRecord(aheadRepayPlatform, borrowerCompensationPlatformCount,bacthNo, tenderItem,outaccountid); //添加借款人补偿平台记录
//	  			}
//	  			
//	   			if(repaymentType.equals((short)1)){//1及时
//	  				List<RepayMent> repayMents5 = null;
//	  				List<RepayMent> repayMents6 = new ArrayList<>();
// 	  				for(DividedPayments dividedPayments4 : dividedPayments){
//	  					for(int i = 0 ; i < opids.length ; i++){
//	  	 					Map<String,Object> map2 = new HashMap<String,Object>();
//	  						map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//	  						map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//	  						map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//	  						map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//	  						map2.put("repaystatus", (short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//	  						repayMents5 = repayMentServiceI.findAheadRepayRordernoJoinCheck(map2);
//	  						if(repayMents5.size() > 0){
//	  							repayMents6.addAll(repayMents5);
//	  						}
//	  	   				}
// 	  					repayMents5 = null;
// 	  				}
//	  				
//	  				boolean falg = thirdRepayMentDealI.settingUpBatchAheadRepayMent(repayMents6, bacthNo,userFsAccountInfo.getUsrcustid(),AheadRamountCount,tenderItem.getTno());//提前还款接口调用
// 	  				if(falg){
//	  					//处理成功
// 	  	  				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//	  	  				hashMap.put(Session_Constant.RESULTCODE,"partDayAheadRepay_success");
//	  	  				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功! 还款提交成功!请等待处理结果！");
// 	  	  				String str = JSON.toJSONString(hashMap);
// 	  	  				for(DividedPayments dividedPayments4 : dividedPayments){
//  	  	  					dividedPayments4.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
// 	  	    				dividedPaymentsServiceI.updateById(dividedPayments4);
// 	  	  				}
//	  	  				try {
//	  	  					StringUtil.sendJsonData(response, str);
//	  	  				} catch (IOException e) {
//	  	  					e.printStackTrace();
//	  	  				}
//	  				}else{
//	  					//处理失败
// 	  	  				hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//	  	  				hashMap.put(Session_Constant.RESULTCODE,"partDayAheadRepay_fail");
//	  	  				hashMap.put(Session_Constant.MESSAGE, "提示：因网络异常！操作失败! 还款提交失败!请重新操作或联系客服");
//	  	  				String str = JSON.toJSONString(hashMap);
//	  	  				try {
//	  	  					StringUtil.sendJsonData(response, str);
//	  	  				} catch (IOException e) {
//	  	  					e.printStackTrace();
//	  	  				}
//	  				}
//	  				
//	  			}else{//冻结还款
//	   				List<RepayMent> repayMents5 = null;
//	  				List<RepayMent> repayMents6 = new ArrayList<>();
//	  				for(DividedPayments dividedPayments4 : dividedPayments){
//	  					for(int i = 0 ; i < opids.length ; i++){
//	  	 					Map<String,Object> map2 = new HashMap<String,Object>();
//	  						map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//	  						map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//	  						map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//	  						map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//	  						map2.put("repaystatus", (short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//	  						repayMents5 = repayMentServiceI.findListRepayMentByConditions(map2);
//	  						if(repayMents5.size() > 0){
//	  							repayMents6.addAll(repayMents5);
//	  						}
//	  	   				}
//	  				}
//	  				
//	  				if(repayMents6.size() > 0){
//	  					for(RepayMent repayMent2 : repayMents6){
//	  						if(repayMent2.getIsaudit().equals((short)0) || repayMent2.getIsaudit().equals((short)3)){//是否审核 0未审核 1待审核  2审核通过 3审核失败
//	  							repayMent2.setIsaudit((short)1);
//	  						}
//	  						repayMent2.setRbatchno(bacthNo);//还款批次号
//	  						repayMentServiceI.updateById(repayMent2);
//	  					}
//	  				} 
//	  				//处理成功
// 	  				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//  	  				hashMap.put(Session_Constant.RESULTCODE,"perDiem_submit_serviceAudit_success");
//  	  				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功! 还款提交审核成功!请等待客服审核处理！");
//	  				String str = JSON.toJSONString(hashMap);
//	  				try {
//	  					StringUtil.sendJsonData(response, str);
//	  				} catch (IOException e) {
//	  					e.printStackTrace();
//	  				}
//	   			}
//	  			
//			}else{//全额计息
//					List<RepayMent> repayMents5 = null;
//					List<RepayMent> repayMents6 = new ArrayList<>();
//	  				for(DividedPayments dividedPayments4 : dividedPayments){
//						for(int i = 0 ; i < opids.length ; i++){
//		 					Map<String,Object> map2 = new HashMap<String,Object>();
//							map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//							map2.put("utordernoordaorderno",opids[i]);//投标订单号  or 债权转让订单号
//							map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//							map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//							map2.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//							repayMents5 = repayMentServiceI.findListRepayMentByConditions(map2);
//							if(repayMents5.size() > 0){
//								repayMents6.addAll(repayMents5);
//							}
//						}
// 						repayMents5 = null;
// 					}
//	  				
//  				if(!(repayMents6.size() > 0)){//没有找到投资人的还款计划
//  					HSRepayMentFreezeController.repayMentunFreeze(userFsAccountInfo.getUsrcustid(), AheadRamountCount, tenderItem.getTno(), StringUtil.getNo(), HSignUtil.COINSTCODE + bacthNo);//解冻还款金额
//   					hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//  	  				hashMap.put(Session_Constant.RESULTCODE,"alldays_fail");
//  	  				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败! 没有找到投资人的还款计划信息！请重新操作或联系客服！");
//		    		String str = JSON.toJSONString(hashMap);
//		  			try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//		  			return null;
//  				}
//	  			 
//				if(repaymentType.equals((short)1)){//及时还款
//					boolean falg = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMents6, bacthNo, userFsAccountInfo.getUsrcustid(), AheadRamountCount, tenderItem.getTno());
//					if(falg){
//  						hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//	  	  				hashMap.put(Session_Constant.RESULTCODE,"alldays_success");
//	  	  				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功!还款提交成功！");
//			    		String str = JSON.toJSONString(hashMap);
//			  			try {
//							StringUtil.sendJsonData(response, str);
//						} catch (IOException e) {
//			 				e.printStackTrace();
//						}
//					}else{
//  						hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//	  	  				hashMap.put(Session_Constant.RESULTCODE,"alldays_fail");
//	  	  				hashMap.put(Session_Constant.MESSAGE, "提示：因网络响应不及时,操作失败!还款提交失败！请重新操作或联系客服！");
//			    		String str = JSON.toJSONString(hashMap);
//			  			try {
//							StringUtil.sendJsonData(response, str);
//						} catch (IOException e) {
//			 				e.printStackTrace();
//						}
//					}
// 				}else{//冻结还款
// 					
//					for(RepayMent repayMent2 : repayMents6){
//						if(repayMent2.getIsaudit().equals((short)0) || repayMent2.getIsaudit().equals((short)3)){
//							repayMent2.setIsaudit((short)1);//是否审核 0未审核 1待审核  2审核通过 3审核失败
//						}
//						repayMent2.setRepaystatus((short)4);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
// 						repayMent2.setRbatchno(bacthNo);//还款批次号
//						repayMentServiceI.updateById(repayMent2);
//					}
//  					hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//  	  				hashMap.put(Session_Constant.RESULTCODE,"alldays_isauditsuccess");
//  	  				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功!提交客服审核成功！请等待客服审核处理！");
//		    		String str = JSON.toJSONString(hashMap);
//		  			try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//				}
//				
// 	 		}
//	  		return null;
//  	}
//	
//	/**
//	 * 
//	* @Title: selectAllAheadRepayMent 
//	* @Description: TODO(提前还款  全部提前还款  借款人选择期数后  ，计算罚金 本金，利息给借款人看  （注意 这里的全部提前还款 有可能有一部分人已经提前还款了）) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/selectAllAheadRepayMent" ,method = RequestMethod.POST)
//	@Token(save = true)
//	public String selectAllAheadRepayMent(HttpServletRequest request,HttpServletResponse response,Model model){
// 		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//		if(accountInfo == null){
// 			model.addAttribute("aheadFineError", "用户没有登录");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		String opid  = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAY_DIVMENTS);//借款人选择的提前还款期数
//		if(StringUtil.isEmpty(opid)){
// 			model.addAttribute("aheadFineError", "参数错误");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		String[] opids = opid.split(",");
//		List<DividedPayments> dividedPayments = new ArrayList<DividedPayments>();
//		DividedPayments dividedPayments2 = null;
//		for(int i =0;i < opids.length;i++){
//			dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opids[i]));
//			if(dividedPayments2 != null){
//				dividedPayments.add(dividedPayments2);
//			}
//		}
//		
//		if(!(dividedPayments.size() > 0)){//没有找到还款计划
//			model.addAttribute("aheadFineError", "没有找到还款计划");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		Collections.sort(dividedPayments, new DividedPaymentsComparator());//排序
//		DividedPayments dividedPayments3 = dividedPayments.get(0);//当期提前还款的期数
//		Assert.notNull(dividedPayments3, "dividedPayments3 '本次提前还款当期借款人还款计划不能为null'");
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments3.getTenderid());
// 		if(tenderItem == null || tenderItem.getTno() == null){//标的对象为null
// 			model.addAttribute("aheadFineError", "标的对象为null");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		AheadRepayMode aheadRepayMode = aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);
//		if(aheadRepayMode == null || aheadRepayMode.getIntmode() == null){//标的提前还款对象为null
// 			model.addAttribute("aheadFineError", "参数错误");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
// 		Map<String,Object> hashMap2 = new HashMap<String,Object>();
//		hashMap2.put("tenderid", dividedPayments3.getTenderid());//标号ID
//		hashMap2.put("periods", dividedPayments3.getPeriods());//还款期数（第几期）
//		hashMap2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMap2.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		List<RepayMent> repayMents4 = repayMentServiceI.findListRepayMentByConditions(hashMap2);
// 		if(!(repayMents4.size() > 0)){
// 			model.addAttribute("aheadFineError", "没有找到可以还款的计划信息");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		boolean falg1 = true;
//		for(RepayMent repayMent5 : repayMents4){
//			falg1 = aheadRepayMentLogicI.hasInvestorAheadRepayMent(repayMent5);//返回false 跳期还款
//			if(!falg1){
//				break;
//			}
//		}
//		
//		if(!falg1){//有投资人跳期还款了
// 			model.addAttribute("aheadFineError", "不允许跳期还款");
//			return  "user/manager/repayment/aheadFine/aheadFineError";
//		}
//		
//		Short intmode = aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
//		DecimalFormat format = new DecimalFormat("###########################0.00");
//		
//		String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
//		if(StringUtil.isNotEmpty(token)){//防止表单重复提交
//			model.addAttribute("token", token);
//		}
// 		
//		if(intmode.equals((short)1)){//占天利息
// 			DividedPayments dividedPayments4 = dividedPayments.get(dividedPayments.size()-1);
//			Assert.notNull(dividedPayments4, "dividedPayments4 '提前还款当期借款人还款计划不能为null'");
//			Map<String,Object> hashMap3 = new HashMap<String,Object>();
//			hashMap3.put("tenderid", dividedPayments4.getTenderid());//标号ID
//			hashMap3.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//			hashMap3.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			hashMap3.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//			List<RepayMent> repayMents5 = repayMentServiceI.findListRepayMentByConditions(hashMap3);
//			if(!(repayMents5.size() > 0)){
//				return null;
//			}
//			
//			String utoStr = "";//投标订单号
//			for(RepayMent repayMent6 : repayMents5){
//				if(repayMent6.getIsdarepay().equals((short)1)){
//					utoStr += repayMent6.getDaorderno() + ",";
//				}else{
// 					utoStr += repayMent6.getUtorderno() + ",";
//				}
//			}
//			
//			utoStr = utoStr.substring(0,utoStr.lastIndexOf(","));
//			String[] uutoStrs = utoStr.split(",");//投标订单号
//			
//			dividedPayments4  = null; 
//			hashMap3 		  = null;
//			repayMents5       = null;
//			//计算提前还款当期的持有利息
//			Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
//			Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//			Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
//			Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//			Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//			
//			Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//			Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
//			Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//			Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
//			Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//
// 			
//			Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//			Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//			Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//			Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//			Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//			Double penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
//			Double platformPenaltyRamount					= 0.00;//借款人补偿平台本金利息补偿
//			
//			Double ramountCount								= 0.00;//本次提前还款本金总和
//			Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
//			Double platformPenaltyRamountCount				= 0.00;//借款人补偿平台本金利息补偿总和
//			Double penaltyRamountCount						= 0.00;//借款人补偿投资人本金利息补偿总和
//			Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
//			
//			Double ramount									= 0.00;//单个投资人的本金总和
//			Double interest									= 0.00;//单个投资人的利息总和
//			
//			List<RepayMent>  AheadRepayMents 				= null;
//			List<RepayMent>  AheadRepayMentsGather			= new ArrayList<RepayMent>();
//			boolean falg 									= false;
//			List<RepayMentFineDetail> repayMentFineDetails = new ArrayList<>();
//			UserTender tender = null;
//			UserBaseAccountInfo accountInfo2 = null;
//  			for(int i = 0 ; i < uutoStrs.length ; i++){
//				for(DividedPayments dividedPayments5 : dividedPayments){
// 					Map<String,Object> map2 = new HashMap<String,Object>();
//					map2.put("tenderid", dividedPayments5.getTenderid());//标号ID
//					map2.put("utordernoordaorderno",uutoStrs[i]);//投标订单号
//					map2.put("periods", dividedPayments5.getPeriods());//还款期数（第几期）
//					map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					map2.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//					AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//					if(AheadRepayMents.size() > 0){
//						AheadRepayMentsGather.addAll(AheadRepayMents);
//					}
//   				}
//				 
//				 if(AheadRepayMentsGather.size() > 0){
//					 for(RepayMent ment : AheadRepayMentsGather){
//							if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//								boolean Aheadflag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//验证投资人是否跳期提前还款
//								if(!Aheadflag){//投资人存在跳期还款 不允许提前还款
//									falg = true;
//									break;
//								}
//								
//		 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
//		 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
//		 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
//		 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//		 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
//
//		 						
//				 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
//				 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
//				 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
//				 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
//				 				theCurrentPeriodBadCropDisablevoucherint = Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//
//				 				
//				 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodBadCropDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
//				 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
//				 				
//								theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期持有利息总和
//								interest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + interest ));
//								ramount 						 	 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramount));//本次提前还款总本金
//							}else{
//		  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + 0.00 + borrowerCompensationInvestorsCount));//单个投资人剩余期数欠收本金利息总和
//		  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(ment.getRlvoucherint() + ment.getRintfee()  + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
//		  		 				ramount 							 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher()  + ment.getDisablevoucher() + 0.00 + ramount));//本次提前还款总本金
//		  		 				interest							 = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + interest ));
//							}
//						}
//				 }
//				
//				if(falg){//投资人存在跳期还款 不允许提前还款
//					break;
//				}
//				
// 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + theCurrentPeriodBadCropDisablevoucherint + 0.00));//单个投资人欠收利息
//				borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + pBorrowerCompensationInvestorsCount 
//						+ theCurrentPeriodPBadCropInterestCount + borrowerCompensationPlatformCount));//全部投资人欠收利息
//				
//				Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//				if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
// 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments3.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
//					if(aheadRepay != null){//找到符合补偿的区间段
//						Short AwardType = aheadRepay.getAwardtype();//奖励方式（1惩罚借款人，2平台奖励，3 1+2）
//						if(AwardType != null && AwardType.equals((short)1)
//								|| AwardType.equals((short)3)){//惩罚借款人 或惩罚借款人和平台奖励
//							if(aheadRepay.getPenaltyrate() != null && aheadRepay.getPenaltyrate() > 0){//百分比
//								penaltyRamount =  Double.valueOf(format.format(singleInvestorsCount * aheadRepay.getPenaltyrate() + 0.00)) ;
//								if(aheadRepay.getMaxpenalty() != null 
//										&& penaltyRamount >= aheadRepay.getMaxpenalty()){//借款人罚金最大值
//									penaltyRamount = aheadRepay.getMaxpenalty();
//								}
//								penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
//							}else{//定额
//								penaltyRamount = aheadRepay.getPenaltyquota() == null ? 0.00 : aheadRepay.getPenaltyquota();
//								penaltyRamountCount = Double.valueOf(format.format(penaltyRamount + penaltyRamountCount));//借款人补偿投资人欠收利息总罚金
//							}
//						}
//					}
//				}
//				
//				RepayMentFineDetail detail = new RepayMentFineDetail();
//				tender = userTenderServiceI.findUserTenderByOrderno(uutoStrs[i]);
//				if(tender != null && tender.getOutaccountid() != null){
//					accountInfo2 = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(tender.getOutaccountid());
//					accountInfo2 = getDecryptionUserBaseAccountInfoDetail(accountInfo2);//解密加密后的数据
//					if(accountInfo2 != null && accountInfo2.getLoginname() != null){
// 						detail.setUsername(accountInfo2.getLoginname());/* 投资人用户名 */
//					}
//				}
//				
//				detail.setRamount(ramount);/* 本金 */
//				detail.setRinterest(interest);/* 本金利息 （原应得利息）*/
//				detail.setHoldrinterest(theCurrentPeriodHoldInterest + theCurrentPeriodHoldRvoucherint);/* 持有利息 */
//				detail.setHarvestfine(penaltyRamount);/* 欠收利息罚金 */
//				repayMentFineDetails.add(detail);
//				
//				 ramountCount = Double.valueOf(format.format(ramountCount + ramount ));
//				 
//				 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
//				 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//				 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
//				 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//				 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//
//	 			
//				 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//				 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
//				 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//				 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
//				 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//
// 				
//  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//				 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//	  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//	 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//	 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
// 				 penaltyRamount							= 0.00;//借款人补偿投资人本金利息补偿
// 				 
// 				 ramount								= 0.00;//单个投资人的本金总和
// 				 interest								= 0.00;//单个投资人的利息总和
// 				 
// 				 AheadRepayMents 						= null;
// 				 AheadRepayMentsGather.clear();//清除已经计算过的值
//     		}
//			
//  			if(falg){//不允许投资人跳期还款 
//				model.addAttribute("aheadFineError", "不允许跳期还款");
//				return  "user/manager/repayment/aheadFine/aheadFineError";
//			}
//  			
//  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
//   				//计算借款人补偿平台罚金
//  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
//  						findAheadRepayPlatformByHoldInterestCount(dividedPayments3.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
//  				if(aheadRepayPlatform != null){//找到符合补偿的区间段
//  					if(aheadRepayPlatform.getAwardplatrate() != null 
//  							&& aheadRepayPlatform.getAwardplatrate() > 0){//百分比
//  						platformPenaltyRamount = Double.valueOf(format.format(borrowerCompensationPlatformCount * aheadRepayPlatform.getAwardplatrate()));
//  						if(aheadRepayPlatform.getAwardplatminmoney() != null 
//  								&& aheadRepayPlatform.getAwardplatmaxmoney() != null){
//  							if(aheadRepayPlatform.getAwardplatminmoney() <= platformPenaltyRamount){
//  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatminmoney();
//  							}else if(platformPenaltyRamount >= aheadRepayPlatform.getAwardplatmaxmoney()){
//  								platformPenaltyRamount = aheadRepayPlatform.getAwardplatmaxmoney();
//  							}
//  						}
//  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));
//  					}else{//定额
//  						platformPenaltyRamount = aheadRepayPlatform.getAwardplatquota() == null ? 0.00 : aheadRepayPlatform.getAwardplatquota();
//  						platformPenaltyRamountCount = Double.valueOf(format.format(platformPenaltyRamountCount + platformPenaltyRamount));//平台总罚金
//  					}
//  				}
//  			}
//  			
//  			if(platformPenaltyRamountCount > 0){
//  				model.addAttribute("platformPenaltyRamountCount",platformPenaltyRamountCount.toString());
//  			}
//  			Double Count = Double.valueOf(format.format(ramountCount + theCurrentPeriodHoldInterestCount 
//  					+ platformPenaltyRamountCount + penaltyRamountCount + 0.00));
//  			session.setAttribute(Session_Constant.AHEADREPAY_DIVMENTS, opid);
//  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, Count.toString());
//  			
//   			 model.addAttribute("repayMentFineDetails", repayMentFineDetails);
//   			 model.addAttribute("count",Count.toString());
//  			return "user/manager/repayment/aheadFine/allAheadRepayPartInterestDetailAllowPartRepay"; 
// 		}else{//全额计息
//  			DividedPayments dividedPayments100 = dividedPayments.get(dividedPayments.size() - 1);
// 			Map<String,Object> hashMap3 = new HashMap<String,Object>();
//  			hashMap3.put("tenderid", dividedPayments100.getTenderid());//标号ID
// 			hashMap3.put("periods", dividedPayments100.getPeriods());//还款期数（第几期）
// 			hashMap3.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
// 			hashMap3.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//			List<RepayMent> repayMentsAll = repayMentServiceI.findListRepayMentByConditions(hashMap3);
//			if(repayMentsAll.size() == 0 ){
//				model.addAttribute("aheadFineError", "没有找到信息");
//				return  "user/manager/repayment/aheadFine/aheadFineError";
//			}
//			
//			String  utreoStr = "";
//			for(RepayMent repayMent8 : repayMentsAll){
//				utreoStr += repayMent8.getUtorderno() +",";
//			}
//			
//			utreoStr = utreoStr.substring(0,utreoStr.lastIndexOf(","));//截取
//			String[] utreoStrs 			= utreoStr.split(",");//分割 投标订单号
//			UserTender userTender 		= null; //投标记录 对象
//			UserBaseAccountInfo outUserBaseAccountInfo = null;//投资人用户基本信息对象
//			List<RepayMent> repayMents8 = null;
// 			List<RepayMent> repayMents9 = new ArrayList<RepayMent>();
// 			List<RepayMentFineDetail> mentFineDetails = new ArrayList<>();
// 			Double ramountCount			= 0.00;//还款本金
// 			Double interestCount		= 0.00;//还款利息
// 			Double ramount				= 0.00;//单个投资人还款本金
// 			Double interest				= 0.00;//单个投资人还款利息
// 			Double count				= 0.00;//总和
// 			for(int i = 0 ; i < utreoStrs.length; i++){
//  				for(DividedPayments dividedPayments90 : dividedPayments){
// 					Map<String,Object> hashMap8 = new HashMap<String,Object>();
// 					hashMap8.put("tenderid", dividedPayments90.getTenderid());//标号ID
// 					hashMap8.put("periods", dividedPayments90.getPeriods());//还款期数（第几期）
// 					hashMap8.put("utorderno",utreoStrs[i]);//投标订单号
// 					hashMap8.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
// 					hashMap8.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
// 					repayMents8 = repayMentServiceI.findListRepayMentByConditions(hashMap8);
// 					if(repayMents8.size() > 0){
// 						repayMents9.addAll(repayMents8);
// 					}
//  				}
//  				
//  				if(repayMents9.size() > 0){
//  					for(RepayMent repayMents10 : repayMents9){
//  						ramount  = Double.valueOf(format.format(repayMents10.getRamount() + repayMents10.getRvoucher() + repayMents10.getDisablevoucher() + ramount));
//  						interest = Double.valueOf(format.format(repayMents10.getRinterest() + repayMents10.getRvoucherint() + repayMents10.getDisablevoucherint() + interest));
//  	 				}
//   				}
//  				
//  				RepayMentFineDetail detail = new RepayMentFineDetail();
//  				userTender  = userTenderServiceI.findUserTenderByOrderno(utreoStrs[i]);
//  				if(userTender != null && userTender.getOutaccountid() != null){
//  					outUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(userTender.getOutaccountid());
//  					outUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(outUserBaseAccountInfo);//解密加密后的数据
//  					if(outUserBaseAccountInfo != null && outUserBaseAccountInfo.getLoginname() != null){
//   						detail.setUsername(outUserBaseAccountInfo.getLoginname());/* 投资人用户名 */
//  					}
//  				}
//  				detail.setRamount(ramount);/* 本金 */
//  				detail.setRinterest(interest);/* 本金利息 （原应得利息）*/
//  				detail.setHoldrinterest(interest);/* 持有利息 */
//  				detail.setHarvestfine(0.00);/* 欠收利息罚金 */
//   				mentFineDetails.add(detail);
//  				
//  				ramountCount  = Double.valueOf(format.format(ramountCount + ramount));
//  				interestCount = Double.valueOf(format.format(interest + interestCount));
//  				
//  				ramount     = 0.00;//单个投资人还款本金
//  				interest    = 0.00;//单个投资人还款利息
//  				
//  				repayMents8 = null;
//  				repayMents9.clear();
// 			}
// 			 
// 			count = Double.valueOf(format.format(ramountCount + interestCount));
// 			session.setAttribute(Session_Constant.AHEADREPAY_DIVMENTS, opid);
//  			session.setAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT, count.toString());
//   			model.addAttribute("repayMentFineDetails", mentFineDetails);
//  			model.addAttribute("count", count.toString());
//  			return "user/manager/repayment/aheadFine/allAheadRepayAllInterestDetailAllowPartRepay";
//		}
// 	}
//	
//	/**
//	 * @throws Exception 
//	* @Title: selectAllAheadRepayMentTask 
//	* @Description: TODO(提前还款  全部提前还款，计算罚金，补偿给借款人看了，同意后进这里 （这里标的设置允许部分还款）) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/selectAllAheadRepayMentTask",method = RequestMethod.POST)
//	@Token(remove= true)
//	public String selectAllAheadRepayMentTask(HttpServletRequest request ,HttpServletResponse response) throws Exception{
//		String divOpid = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAY_DIVMENTS);
//		String AheadRamountCount = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT);//借款人本次提前还款总金额
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//		if(accountInfo == null){
//  			hashMap.put(Session_Constant.RESULT, "logout");
//			hashMap.put(Session_Constant.RESULTCODE,"logout");
//			hashMap.put(Session_Constant.MESSAGE, "提示：因您操作超时！请重新登录后重新操作！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		
//		if(StringUtil.isEmpty(divOpid) || StringUtil.isEmpty(AheadRamountCount)){
// 			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！借款人还款计划参数找不到!请重新操作或联系客服!");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
// 		String[] divOpids = divOpid.split(",");
//		List<DividedPayments> dividedPayments = new ArrayList<>();
//		DividedPayments dividedPayments2 	  = null;
//		for(int i = 0;i < divOpids.length;i++){
//			dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(divOpids[i]));
//			if(dividedPayments2 != null){
//				dividedPayments.add(dividedPayments2);
//			}
//		}
//		
//		if(!(dividedPayments.size() > 0)){//没有找到借款人的具体还款计划
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"divSize_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！没有找到借款人的具体还款计划！请重新操作或联系客服！");
//			
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}
//		
//		Collections.sort(dividedPayments, new DividedPaymentsComparator());
//		DividedPayments dividedPayments3 = dividedPayments.get(0);
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments3.getTenderid());
//		if(tenderItem == null || tenderItem.getTno() == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,没有找到标的信息！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
// 		}
//		
//		loanapp loanapp = loanappServiceI.selectByPrimaryKey(tenderItem.getLoanappid());//借款申请
//		if(loanapp == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"loanapp_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,没有找到借款申请信息！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}
//		
//		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(loanapp.getBaseid());//第三方托管账号
//		if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,没有找到银行电子账号信息！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//		AheadRepayMode aheadRepayMode 	= aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);//提前还款设置
//		if(aheadRepayMode == null || aheadRepayMode.getIntmode() == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE,"aheadRepayMode_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,没有找到银行电子账号信息！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 			return null;
//		}
// 		
//		String bacthNo 		  = StringUtil.getNo();//本次提前还款 批次号
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),AheadRamountCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE+bacthNo);
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get("result");
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 				hashMap.put(Session_Constant.RESULTCODE,"balance_deficiency");
// 				hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！账号余额不足！请请充值后重新操作或联系客服！");
//			}else{
//   				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 				hashMap.put(Session_Constant.RESULTCODE,"freeze_error");
// 				hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,还款冻结失败！请重新操作或联系客服！");
//			}
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//		}
//		
// 		DecimalFormat format  = new DecimalFormat("####################0.00");
//		Short repaymentType				= tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
// 		Short intmode 					= aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
//		if(intmode.equals((short)1)){//按天计息
//			List<RepayMent> AheadRepayMentsGather = new ArrayList<RepayMent>();
//			List<RepayMent> AheadRepayMents		  = null;	
//			DividedPayments dividedPaymentsre = dividedPayments.get(dividedPayments.size()-1);
//			Assert.notNull(dividedPaymentsre, "dividedPayments4 '提前还款当期借款人还款计划不能为null'");
//			Map<String,Object> hashMap3 = new HashMap<String,Object>();
//			hashMap3.put("tenderid", dividedPaymentsre.getTenderid());//标号ID
//			hashMap3.put("periods", dividedPaymentsre.getPeriods());//还款期数（第几期）
//			hashMap3.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			hashMap3.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//			List<RepayMent> repayMents5 = repayMentServiceI.findListRepayMentByConditions(hashMap3);
// 			if(!(repayMents5.size() > 0)){
// 				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 			hashMap.put(Session_Constant.RESULTCODE,"repaySize_error");
//	 			hashMap.put(Session_Constant.MESSAGE, "提示:操作失败！因网络响应不及时,没有找到投资人的还款计划信息！请重新操作或联系客服！");
//				 String str = JSON.toJSONString(hashMap);
//				 try {
//					StringUtil.sendJsonData(response, str);
//				 } catch (IOException e) {
//					e.printStackTrace();
//				 }
// 				return null;
//			}
//			
//			String utoStr = "";
//			for(RepayMent repayMent12 : repayMents5){
// 				if(repayMent12.getIsdarepay().equals((short)1)){
//					utoStr += repayMent12.getDaorderno() + ",";
//				}else{
// 					utoStr += repayMent12.getUtorderno() + ",";
//				}
//			}
//			
//			utoStr = utoStr.substring(0, utoStr.lastIndexOf(","));
//			String[] utoStrs = utoStr.split(",");
//			
//			//计算提前还款当期的持有利息
//			Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
//			Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//			Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
//			Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//			Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//			 
//			Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//			Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
//			Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//			Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
//			Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//
// 			
//			Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
//			Double ramountCount								= 0.00;//本次提前还款本金总和
//			Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//			Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//			Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//			Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//			Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//			Double singlePulsInvestorsCount					= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//			
//			Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
//			 
//			BigDecimal outaccountid 						= null;//借款人ID
//			RepayMent repayMent 							= null;
// 			for(int i = 0 ; i < utoStrs.length ; i++){
// 				/*根据投标订单号  来查询提前还款的数据*/
//				for(DividedPayments dividedPayments4 : dividedPayments){
// 					Map<String,Object> map2 = new HashMap<String,Object>();
//					map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					map2.put("utordernoordaorderno",utoStrs[i]);//投标订单号 or 债权转让订单号
//					map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					map2.put("repaystatus", (short)1);	/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//					if(AheadRepayMents.size() > 0){
//						AheadRepayMentsGather.addAll(AheadRepayMents);
//					}
//   				}
//				
//				 if(AheadRepayMentsGather.size() > 0){
//					 for(RepayMent ment : AheadRepayMentsGather){
//							if(ment.getRepaystatus().equals((2)) || ment.getRepaystatus().equals((3))){	/* 还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) */
//								continue;//已还款的 跳出循环
//							}
//							
//							if(ment.getPlanstatus().equals((short)2)){//还款计划状态(1有效，2无效)
//								continue;//无效的 跳出循环
//							}
//							
//							if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//		 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
//		 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
//		 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
//		 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//		 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
// 		 						
//				 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
//				 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
//				 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
//				 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
//				 				theCurrentPeriodBadCropDisablevoucherint = Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//
// 				 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodBadCropDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
//				 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
//				 				
//								theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期持有利息总和
//								ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//								AheadRepayMentCalculatedParameter Ater = new AheadRepayMentCalculatedParameter();
//								Ater.setHoldinterest(theCurrentPeriodHoldInterest); //投资人当期持有本金利息
//								Ater.setHoldrvoucherint(theCurrentPeriodHoldRvoucherint); //投资人当期持有类现金利息
//								Ater.setHoldrlvoucherint(theCurrentPeriodHoldRlvoucherint); //投资人当期持有假现金利息
//								Ater.setHoldrintfee(theCurrentPeriodHoldRintfee); //投资人当期持有加息券利息
//								Ater.setBadcropinterest(theCurrentPeriodBadCropInterest); //投资人当期欠收本金利息
//								Ater.setBadcroprvoucherint(theCurrentPeriodBadCropRvoucherint); //投资人当期欠收类现金利息
//								Ater.setBadcroprlvoucherint(theCurrentPeriodBadCropRlvoucherint); //投资人当期欠收假现金利息
//								Ater.setBadcroprintfee(theCurrentPeriodBadCropRintfee); //投资人当期欠收加息券利息
//								Ater.setDiscardvoucherint(theCurrentPeriodHoldDisablevoucherint);//提前还款实际作废类现金的利息
//								Ater.setNorecdiscardvoucherint(theCurrentPeriodBadCropDisablevoucherint);//提前还款欠收作废类现金的利息
//		 						aheadRepayMentLogicI.insertAheadRealRepaymentRecordByTheCurrentPeriod(ment, Ater, tenderItem, bacthNo);//添加提前还款实际利息记录
//								//记录当期 提前还款  按天计息提前实际还款记录
//							}else{
//		  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + 0.00 + borrowerCompensationInvestorsCount + theCurrentPeriodBadCropDisablevoucherint));//单个投资人剩余期数欠收本金利息总和
//		  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(ment.getRlvoucherint() + ment.getRintfee()  + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
//		  		 				ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() +  ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//		  		 				aheadRepayMentLogicI.insertAheadRealRepaymentRecordByOtherPeriod(ment,bacthNo);//添加提前还款实际利息记录
//							}
//							ment.setRepaystatus((short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//							if(!(repaymentType.equals((short)1))){//冻结还款
//								if(ment.getIsaudit().equals((short)0) || ment.getIsaudit().equals((short)3)){
//									ment.setIsaudit((short)1);//是否审核 0未审核 1审核中 2审核通过 3审核不通过 
// 		  						}
//							}
// 							repayMentServiceI.updateById(ment);//更改原还款计划 还款状态为提前还款处理中
// 					 }
//				}
//				
//				if(AheadRepayMentsGather.size() > 0){
//	 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + 0.00));//单个投资人欠收本金，类现金利息
//	 				singlePulsInvestorsCount					= Double.valueOf(format.format(pBorrowerCompensationInvestorsCount + theCurrentPeriodPBadCropInterestCount + 0.00));//单个投资人欠收增益利息
//					borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + singlePulsInvestorsCount + borrowerCompensationPlatformCount + 0.00));//全部投资人欠收利息
//					repayMent = AheadRepayMentsGather.get(0);
//					if(outaccountid == null){
//	 					outaccountid = repayMent.getOutaccountid();//借款人ID
//					}
//					//借款人补偿投资人本金利息欠收补偿
//					Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//					if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
//	 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments3.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
//	 					aheadRepayMentLogicI.insertAheadRepayRecord(aheadRepay, singleInvestorsCount, bacthNo, repayMent);//添加借款人补偿投资人本金欠收利息 记录
//					 }
//					
//					//平台补偿 投资人增益利息补偿
//					Short isPlusCompensateOn = tenderItem.getIspluscompensateon();//提前还款增益利息补偿开关(0 关 1开)
//					if(isPlusCompensateOn != null && ispicompensateon.equals((short)1) && singlePulsInvestorsCount > 0){
//	 					AheadRepayAward aheadRepayAward = aheadRepayMentLogicI.findAheadRepayAwardByHoldInterestCount(tenderItem.getId(), singlePulsInvestorsCount);
//	 					aheadRepayMentLogicI.insertAheadRepayAwardRecord(aheadRepayAward, singlePulsInvestorsCount, bacthNo, repayMent);//添加平台补偿投资人增益欠收利息 记录
//					} 
//					
//					 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
//					 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//					 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
//					 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//					 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//
//		 			
//					 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//					 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
//					 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//					 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
//					 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//
//	 				
//	  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//					 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//		  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//		 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//		 			 
//		 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//		 			 singlePulsInvestorsCount				= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//	  				 
//	 				 AheadRepayMents 						= null;
//	 				 AheadRepayMentsGather.clear();//清除已经计算过的值
//				}
//     		}
//  		
//  			//借款人补偿平台总投资人欠收利息
//  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
//   				//计算借款人补偿平台罚金
//  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
//  						findAheadRepayPlatformByHoldInterestCount(dividedPayments3.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
// 				aheadRepayMentLogicI.insertAheadRepayPlatformRecord(aheadRepayPlatform, borrowerCompensationPlatformCount,bacthNo, tenderItem,outaccountid); //添加借款人补偿平台记录
//  			}
//  			
//  			if(repaymentType.equals((short)1)){//及时还款
//  				List<RepayMent> repayMents4 = null;
//  				List<RepayMent> repayMentsCount = new ArrayList<>();
//   				for(DividedPayments dividedPayments4 : dividedPayments){
//  					Map<String,Object> hashMap4 = new HashMap<String,Object>();
//   					hashMap4.put("tenderid", dividedPayments4.getTenderid());//标号ID
//   					hashMap4.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//  					hashMap4.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//  					hashMap4.put("repaystatus", (short)6);//*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					repayMents4 = repayMentServiceI.findAheadRepayRordernoJoinCheck(hashMap4);
//					if(repayMents4.size() > 0){
//						repayMentsCount.addAll(repayMents4);
// 					} 
//					repayMents4.clear();
//   				}
//  				boolean fa = thirdRepayMentDealI.settingUpBatchAheadRepayMent(repayMentsCount, bacthNo, userFsAccountInfo.getUsrcustid(), AheadRamountCount.toString(), tenderItem.getTno());
//   				if(fa){
//    				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//  		 			hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_partDays_success");
//  		 			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提交提前还款成功");
//  					String str = JSON.toJSONString(hashMap);
//  					for(DividedPayments dividedPayments4 : dividedPayments){
//   	  					dividedPayments4.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//  	    				dividedPaymentsServiceI.updateById(dividedPayments4);
//	  	  			}
//  					 try {
//  						StringUtil.sendJsonData(response, str);
//  					 } catch (IOException e) {
//  						e.printStackTrace();
//  					 }
//  					 return null;
//  					 
//  				}else{
//    				hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//  		 			hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_partDays_fail");
//  		 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,提交提前还款失败,请重新操作或联系客服！");
// 					 String str = JSON.toJSONString(hashMap);
// 					 try {
// 						StringUtil.sendJsonData(response, str);
// 					 } catch (IOException e) {
// 						e.printStackTrace();
// 					 }
// 					 return null;
// 					 
//  				}
// 			}else{//2冻结还款
// 				 hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//		 		 hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_partDays_isauditSuccess");
//		 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交客服审核成功！");
//				 String str = JSON.toJSONString(hashMap);
//				 try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				 return null;
//			}
//  			
//  		}else {//全额计息
//  			if(repaymentType.equals((short)1)){//及时还款
// 				List<RepayMent> repayMents8 = null;
// 	  			List<RepayMent> repayMents9 = new ArrayList<RepayMent>();
// 				 for(DividedPayments dividedPayments8 : dividedPayments){
//					 Map<String,Object> hashMap5 = new HashMap<String,Object>();
//					 hashMap5.put("tenderid", dividedPayments8.getTenderid());//标号ID
//					 hashMap5.put("periods", dividedPayments8.getPeriods());//还款期数（第几期）
//					 hashMap5.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					 hashMap5.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					 repayMents8 = repayMentServiceI.findListRepayMentByConditions(hashMap5);
//					 if(repayMents8.size() > 0){
//						 repayMents9.addAll(repayMents8);
//					 }
//					 repayMents8.clear();
// 				 }
// 				boolean b = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMents9, bacthNo, userFsAccountInfo.getUsrcustid(), AheadRamountCount, tenderItem.getTno());
//  				if(b){
//  					 hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//			 		 hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_allDays_success");
//			 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交成功!");
//					String str = JSON.toJSONString(hashMap);
//					for(DividedPayments dividedPayments4 : dividedPayments){
// 	  	  					dividedPayments4.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//	  	    				dividedPaymentsServiceI.updateById(dividedPayments4);
//	  	  			}
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//				 }else{
// 					 hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//			 		 hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_allDays_fail");
//			 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,提前还款提交失败！");
//					String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//				 }
//			}else{//2冻结还款
//				List<RepayMent> repayMents18 = null;
//	  			List<RepayMent> repayMents19 = new ArrayList<RepayMent>();
//  				 for(DividedPayments dividedPayments8 : dividedPayments){
//					 Map<String,Object> hashMap5 = new HashMap<String,Object>();
//					 hashMap5.put("tenderid", dividedPayments8.getTenderid());//标号ID
//					 hashMap5.put("periods", dividedPayments8.getPeriods());//还款期数（第几期）
//					 hashMap5.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					 hashMap5.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					 repayMents18 = repayMentServiceI.findListRepayMentByConditions(hashMap5);
//					 if(repayMents18.size() > 0){
//						 repayMents19.addAll(repayMents18);
//					 }
//					 repayMents18.clear();
//				 }
//  				 
//  				 if(repayMents19.size() > 0){
//  					  for(RepayMent ment9 : repayMents19){
//  						  if(ment9.getIsaudit().equals((short)0) || ment9.getIsaudit().equals((short)3)){
//  							  ment9.setIsaudit((short)1);//是否审核 0未审核 1待审核 2审核通过 3审核不通过 
//  						  }
//  						  ment9.setRbatchno(bacthNo);//还款批次号
//  						  ment9.setRepaystatus((short)4);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//  						  repayMentServiceI.updateById(ment9);
//  					  }
//    				 hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//			 		 hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_allDays_isauditSuccess");
//			 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交客服审核成功！");
// 					 String str = JSON.toJSONString(hashMap);
// 					 try {
// 						 StringUtil.sendJsonData(response, str);
// 					 } catch (IOException e) {
// 						 e.printStackTrace();
// 					 } 
// 					 return null;
//  					  
//  				 }else{
//     				 hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//			 		 hashMap.put(Session_Constant.RESULTCODE,"allAheadRepay_allDays_isaudited");
//			 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,已经提交客服审核了，请等待客服审核通过！");
//  					 String str = JSON.toJSONString(hashMap);
//  					 try {
//  						 StringUtil.sendJsonData(response, str);
//  					 } catch (IOException e) {
//  						 e.printStackTrace();
//  					 } 
//  					 return null;
//  				 }
//			}
//			
//		}
// 	}
//	
//	/**
//	 * @throws Exception 
// 	 * @Title: aheadAllRepayMentTask 
//	 * @Description: TODO(提前还款 (全部提前还款)借款人选择还款期数 显示给借款人看借款人欠收利息罚金，平台罚金后，借款人同意还款后进了这里（注意 进这里标设置的是不允许部分还款）) 
//	 * @param @param request
//	 * @param @param response
//	 * @param @return  参数说明 
//	 * @return String    返回类型 
//	 * @author cjm
//	 * @throws
//	 */
//	@RequestMapping(value = "/aheadAllRepayMentTask",method = RequestMethod.POST)
//	@Token(remove = true)
//	public String aheadAllRepayMentTask(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String divOpid = (String) request.getSession().getAttribute(Session_Constant.AHEADALLREPAYMENT_DIVMENTS);//本次提前还款 的期数id 字符串
//		String AheadRamountCount = (String) request.getSession().getAttribute(Session_Constant.AHEADREPAYMENT_RAMOUNTCOUNT);//借款人本次提前还款总金额
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//		if(accountInfo == null){
// 			 hashMap.put(Session_Constant.RESULT, "logout");
//	 		 hashMap.put(Session_Constant.RESULTCODE,"logout");
//	 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因您操作超时,请重新登录后操作！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		if(StringUtil.isEmpty(divOpid) || StringUtil.isEmpty(AheadRamountCount)){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//	 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,借款人还款计划参数找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		 
//		String[] divOpids = divOpid.split(",");
//		List<DividedPayments> dividedPayments = new ArrayList<>();
//		DividedPayments dividedPayments2 	  = null;
//		for(int i = 0;i < divOpids.length;i++){
//			dividedPayments2 = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(divOpids[i]));
//			if(dividedPayments2 != null){
//				dividedPayments.add(dividedPayments2);
//			}
//		}
//		if(!(dividedPayments.size() > 0)){
// 			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		hashMap.put(Session_Constant.RESULTCODE,"divPaySize_null");
//	 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,借款人还款计划信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Collections.sort(dividedPayments, new DividedPaymentsComparator());
//		DividedPayments dividedPayments3 = dividedPayments.get(0);
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments3.getTenderid());
//		if(tenderItem == null || tenderItem.getTno() == null ||  tenderItem.getRepaymenttype()== null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		 hashMap.put(Session_Constant.RESULTCODE,"tenderItem_null");
//	 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,标的信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		loanapp loanapp = loanappServiceI.selectByPrimaryKey(tenderItem.getLoanappid());//借款申请
//		if(loanapp == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		 hashMap.put(Session_Constant.RESULTCODE,"loanapp_null");
//	 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,借款申请信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(loanapp.getBaseid());//第三方托管账号
//		if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
// 			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		 hashMap.put(Session_Constant.RESULTCODE,"userFsAccountInfo_null");
//	 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,银行电子账号信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//		AheadRepayMode aheadRepayMode 	= aheadRepayModeServiceI.selectModeBytid(tenderItem.getId()).get(0);//提前还款设置
//		if(aheadRepayMode == null || aheadRepayMode.getIntmode() == null){
//  			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//	 		 hashMap.put(Session_Constant.RESULTCODE,"aheadRepayMode_null");
//	 		 hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,提前还款设置信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		 
//		String bacthNo 	= StringUtil.getNo();//本次提前还款 批次号
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),AheadRamountCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE + bacthNo);
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get("result");
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//		 		hashMap.put(Session_Constant.RESULTCODE,"balance_deficiency");
//		 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,账号余额不足,请充值后重新操作或联系客服！");
//			}else{
//   				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 		 		hashMap.put(Session_Constant.RESULTCODE,"freeze_error");
// 		 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！");
// 			}
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//		}
//		
//		Short repaymentType				= tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
// 		Short intmode 					= aheadRepayMode.getIntmode();//还利息方式(1占天利息，2全额利息)
//		if(intmode.equals((short)1)){//按天计息
//			DecimalFormat format  = new DecimalFormat("####################0.00");
//			List<RepayMent> AheadRepayMentsGather = new ArrayList<RepayMent>();
//			List<RepayMent> AheadRepayMents		  = null;	
//			boolean falg 						  = false;
//			
//			Map<String,Object> hashMap2 = new HashMap<String,Object>();
//			hashMap2.put("tenderid", dividedPayments3.getTenderid());//标号ID
//			hashMap2.put("periods", dividedPayments3.getPeriods());//还款期数（第几期）
//			hashMap2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			hashMap2.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//			List<RepayMent> AheadRepayMents2 = repayMentServiceI.findListRepayMentByConditions(hashMap2);
//			String utoStr = "";
//			for(RepayMent repayMent12 : AheadRepayMents2){
//				if(repayMent12.getIsdarepay().equals((short)1)){//是否债转还款(投标记录发生过债转 1是 0 否       注：如原投标发生债权转让  生成新还款计划 原投标数据保存为0，债权转让数据的保存为1)
//					utoStr += repayMent12.getDaorderno() +",";
//				}else{
//					utoStr += repayMent12.getUtorderno() +",";
//				}
// 			}
//			 
//			
//			utoStr = utoStr.substring(0, utoStr.lastIndexOf(","));
//			String[] utoStrs = utoStr.split(",");
//			 
//			//计算提前还款当期的持有利息
//			Double theCurrentPeriodBadCropInterest 			= 0.00;//投资人当期欠收本金利息
//			Double theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//			Double theCurrentPeriodBadCropRlvoucherint		= 0.00;//投资人当期欠收假现金利息
//			Double theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//			Double theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//			
//			Double theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//			Double theCurrentPeriodHoldRvoucherint 			= 0.00;//投资人当期持有类现金利息
//			Double theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//			Double theCurrentPeriodHoldRintfee 				= 0.00;//投资人当期持有加息劵利息
//			Double theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
// 			
//			Double theCurrentPeriodHoldInterestCount 		= 0.00;//全部投资人当期持有利息总和
//			Double ramountCount								= 0.00;//本次提前还款本金总和
//			Double theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//			Double theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//			Double borrowerCompensationInvestorsCount       = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//			Double pBorrowerCompensationInvestorsCount      = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//			Double singleInvestorsCount						= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//			Double singlePulsInvestorsCount					= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//			
//			Double borrowerCompensationPlatformCount		= 0.00;//全部投资人欠收利息总和
// 			BigDecimal outaccountid 						= null;//借款人ID
//			RepayMent repayMent 							= null;
// 			for(int i = 0 ; i < utoStrs.length ; i++){
// 				/*根据投标订单号  来查询提前还款的数据*/
//				for(DividedPayments dividedPayments4 : dividedPayments){
// 					Map<String,Object> map2 = new HashMap<String,Object>();
//					map2.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					map2.put("utordernoordaorderno",utoStrs[i]);//投标订单号
//					map2.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					map2.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					AheadRepayMents = repayMentServiceI.findListRepayMentByConditions(map2);
//					if(AheadRepayMents.size() > 0){
//						AheadRepayMentsGather.addAll(AheadRepayMents);
//					}
//   				}
//				
//				 if(AheadRepayMentsGather.size() > 0){
//					 for(RepayMent ment : AheadRepayMentsGather){
//							if(ment.getRepaystatus().equals((2)) || ment.getRepaystatus().equals((3))){/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//								continue;//已还款的 跳出循环
//							}
//							
//							if(ment.getPlanstatus().equals((short)2)){//还款计划状态(1有效，2无效)
//								continue;//无效的 跳出循环
//							}
//							
//							if(ment.getPeriods().equals(dividedPayments3.getPeriods())){//提前还款 当期还款
//								boolean Aheadflag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(ment);//验证投资人是否跳期提前还款
//								if(!Aheadflag){//投资人存在跳期还款 不允许提前还款
//									falg = true;
//									break;
//								}
//								
//		 						theCurrentPeriodHoldInterest    	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentInterest(ment);//投资人当期持有本金利息
//		 						theCurrentPeriodHoldRvoucherint 	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRvoucherint(ment);//投资人当期持有类现金利息
//		 						theCurrentPeriodHoldRlvoucherint	 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRlvoucherint(ment);//投资人当期持有假现金利息
//		 						theCurrentPeriodHoldRintfee 		 = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentRintfee(ment);//投资人当期持有加息券利息
//		 						theCurrentPeriodHoldDisablevoucherint = aheadRepayMentLogicI.calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(ment);//计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
//		 						 
//				 				theCurrentPeriodBadCropInterest      = Double.valueOf(format.format(ment.getRinterest() - theCurrentPeriodHoldInterest + 0.00));//投资人当期欠收本金利息
//				 				theCurrentPeriodBadCropRvoucherint   = Double.valueOf(format.format(ment.getRvoucherint() - theCurrentPeriodHoldRvoucherint + 0.00));//投资人当期欠收类现金利息
//				 				theCurrentPeriodBadCropRlvoucherint  = Double.valueOf(format.format(ment.getRlvoucherint() - theCurrentPeriodHoldRlvoucherint + 0.00));//投资人当期欠收假现金利息
//				 				theCurrentPeriodBadCropRintfee  	 = Double.valueOf(format.format(ment.getRintfee() - theCurrentPeriodHoldRintfee + 0.00));//投资人当期欠收加息券利息
// 				 				theCurrentPeriodBadCropDisablevoucherint = Double.valueOf(format.format(ment.getDisablevoucherint() - theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期欠收作废的类现金利息 还款时返回平台 不进投资人账号)
//				 				
// 				 				theCurrentPeriodBadCropInterestCount = Double.valueOf(format.format(theCurrentPeriodBadCropInterest + theCurrentPeriodBadCropRvoucherint + theCurrentPeriodBadCropDisablevoucherint + 0.00));//投资人当期欠收本金和类现金利息总和
//				 				theCurrentPeriodPBadCropInterestCount= Double.valueOf(format.format(theCurrentPeriodBadCropRlvoucherint + theCurrentPeriodBadCropRintfee + 0.00));//投资人当期欠收假现金和加息卷利息总和
//				 				
//								theCurrentPeriodHoldInterestCount    = Double.valueOf(format.format(theCurrentPeriodHoldRvoucherint + theCurrentPeriodHoldInterest + theCurrentPeriodHoldInterestCount + theCurrentPeriodHoldDisablevoucherint + 0.00));//投资人当期持有利息总和
//								ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//								AheadRepayMentCalculatedParameter Ater = new AheadRepayMentCalculatedParameter();
//								Ater.setHoldinterest(theCurrentPeriodHoldInterest); //投资人当期持有本金利息
//								Ater.setHoldrvoucherint(theCurrentPeriodHoldRvoucherint); //投资人当期持有类现金利息
//								Ater.setHoldrlvoucherint(theCurrentPeriodHoldRlvoucherint); //投资人当期持有假现金利息
//								Ater.setHoldrintfee(theCurrentPeriodHoldRintfee); //投资人当期持有加息券利息
//								Ater.setBadcropinterest(theCurrentPeriodBadCropInterest); //投资人当期欠收本金利息
//								Ater.setBadcroprvoucherint(theCurrentPeriodBadCropRvoucherint); //投资人当期欠收类现金利息
//								Ater.setBadcroprlvoucherint(theCurrentPeriodBadCropRlvoucherint); //投资人当期欠收假现金利息
//								Ater.setBadcroprintfee(theCurrentPeriodBadCropRintfee); //投资人当期欠收加息券利息
//								Ater.setDiscardvoucherint(theCurrentPeriodHoldDisablevoucherint);//提前还款实际作废类现金的利息
//								Ater.setNorecdiscardvoucherint(theCurrentPeriodBadCropDisablevoucherint);//提前还款欠收作废类现金的利息
//		 						aheadRepayMentLogicI.insertAheadRealRepaymentRecordByTheCurrentPeriod(ment, Ater, tenderItem, bacthNo);//添加提前还款实际利息记录
//								//记录当期 提前还款  按天计息提前实际还款记录
//							}else{
//		  		 				borrowerCompensationInvestorsCount   = Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint()  + ment.getDisablevoucherint() + 0.00 + borrowerCompensationInvestorsCount));//单个投资人剩余期数欠收本金利息总和
//		  		 				pBorrowerCompensationInvestorsCount  = Double.valueOf(format.format(ment.getRlvoucherint() + ment.getRintfee()  + 0.00 + pBorrowerCompensationInvestorsCount));//单个投资人剩余期数欠收增益利息总和
//		  		 				ramountCount 						 = Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + 0.00 + ramountCount));//本次提前还款总本金
//		  		 				aheadRepayMentLogicI.insertAheadRealRepaymentRecordByOtherPeriod(ment,bacthNo);//添加提前还款实际利息记录
//							}
//							ment.setRepaystatus((short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//							if(repaymentType.equals((short)2)){//1及时还款2冻结还款
//								if(ment.getIsaudit().equals((short)0) || ment.getIsaudit().equals((short)3)){
//									ment.setIsaudit((short)1);//是否审核 0待审核 1客服审核中 2审核通过 3审核不通过 
//								}
//							} 
//							repayMentServiceI.updateById(ment);//更改原还款计划 还款状态为提前还款处理中
// 					 }
//				}
//				
//				if(falg){//投资人存在跳期还款 不允许提前还款
//					break;
//				}
//				
//				if(AheadRepayMentsGather.size() > 0){
//	 				singleInvestorsCount						= Double.valueOf(format.format(borrowerCompensationInvestorsCount + theCurrentPeriodBadCropInterestCount + 0.00));//单个投资人欠收本金，类现金利息
//	 				singlePulsInvestorsCount					= Double.valueOf(df.format(pBorrowerCompensationInvestorsCount + theCurrentPeriodPBadCropInterestCount + 0.00));//单个投资人欠收增益利息
//					borrowerCompensationPlatformCount 			= Double.valueOf(format.format(singleInvestorsCount + singlePulsInvestorsCount + borrowerCompensationPlatformCount + 0.00));//全部投资人欠收利息
//					repayMent = AheadRepayMentsGather.get(0);
//					if(outaccountid == null){
//	 					outaccountid = repayMent.getOutaccountid();//借款人ID
//					}
//					//借款人补偿投资人本金利息欠收补偿
//					Short ispicompensateon = tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
//					if(ispicompensateon != null && ispicompensateon.equals((short)1) && singleInvestorsCount > 0){
//	 					AheadRepay aheadRepay = aheadRepayMentLogicI.findAheadRepayByHoldInterestCount(dividedPayments3.getTenderid(), singleInvestorsCount);//提前还款个人利息奖励设置
//	 					aheadRepayMentLogicI.insertAheadRepayRecord(aheadRepay, singleInvestorsCount, bacthNo, repayMent);//添加借款人补偿投资人本金欠收利息 记录
//					 }
//					
//					//平台补偿 投资人增益利息补偿
//					Short isPlusCompensateOn = tenderItem.getIspluscompensateon();//提前还款增益利息补偿开关(0 关 1开)
//					if(isPlusCompensateOn != null && ispicompensateon.equals((short)1) && singlePulsInvestorsCount > 0){
//	 					AheadRepayAward aheadRepayAward = aheadRepayMentLogicI.findAheadRepayAwardByHoldInterestCount(tenderItem.getId(), singlePulsInvestorsCount);
//	 					aheadRepayMentLogicI.insertAheadRepayAwardRecord(aheadRepayAward, singlePulsInvestorsCount, bacthNo, repayMent);//添加平台补偿投资人增益欠收利息 记录
//					} 
//					
//					 theCurrentPeriodBadCropInterest 		= 0.00;//投资人当期欠收本金利息
//					 theCurrentPeriodBadCropRvoucherint		= 0.00;//投资人当期欠收类现金利息
//					 theCurrentPeriodBadCropRlvoucherint	= 0.00;//投资人当期欠收假现金利息
//					 theCurrentPeriodBadCropRintfee			= 0.00;//投资人当期欠收加息劵利息
//					 theCurrentPeriodBadCropDisablevoucherint	= 0.00;//投资人当期欠收作废类现金利息  不进投资人账号 借款人还款后平台收取
//		 			
//					 theCurrentPeriodHoldInterest 			= 0.00;//投资人当期持有本金利息 
//					 theCurrentPeriodHoldRvoucherint 		= 0.00;//投资人当期持有类现金利息
//					 theCurrentPeriodHoldRlvoucherint 		= 0.00;//投资人当期持有假现金利息 
//					 theCurrentPeriodHoldRintfee 			= 0.00;//投资人当期持有加息劵利息
//					 theCurrentPeriodHoldDisablevoucherint 	= 0.00;//投资人当期持有作废类现金利息
//	 				
//	  				 theCurrentPeriodBadCropInterestCount 	= 0.00;//投资人当期欠收本金和类现金利息总和
//					 theCurrentPeriodPBadCropInterestCount 	= 0.00;//投资人当期欠收加息卷和假现金利息总和
//		  			 borrowerCompensationInvestorsCount     = 0.00;//单个投资剩余期数欠收本金和类现金利息总和
//		 			 pBorrowerCompensationInvestorsCount    = 0.00;//单个投资剩余期数欠收假现金利息和加息券利息总和
//		 			 
//		 			 singleInvestorsCount					= 0.00;//单个投资人本金利息和类现金利息总和（包括当期欠收利息和提前还款其他期数欠收利息）
//		 			 singlePulsInvestorsCount				= 0.00;//单个投资人欠收的增益利息总和（包括当期欠收增益利息和提前还款其他期数欠收增益利息）
//	  				 
//	 				 AheadRepayMents 						= null;
//	 				 AheadRepayMentsGather.clear();//清除已经计算过的值
//				}
//     		}
//			
//  			if(falg){
// 				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 		 		hashMap.put(Session_Constant.RESULTCODE,"AheadRepay_jump");
// 		 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,不允许投资人跳期还款,请重新操作或联系客服！");
// 			
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//  			
//  			//借款人补偿平台总投资人欠收利息
//  			Short isforplatformon = tenderItem.getIsforplatformon();//提前还款还款人补偿平台开关(0,关,1开)
//  			if(isforplatformon != null && isforplatformon.equals((short)1) && borrowerCompensationPlatformCount > 0){
//   				//计算借款人补偿平台罚金
//  				AheadRepayPlatform aheadRepayPlatform = aheadRepayMentLogicI.
//  						findAheadRepayPlatformByHoldInterestCount(dividedPayments3.getTenderid(), borrowerCompensationPlatformCount);//提前还款奖励平台设置对象
// 				aheadRepayMentLogicI.insertAheadRepayPlatformRecord(aheadRepayPlatform, borrowerCompensationPlatformCount,bacthNo, tenderItem,outaccountid); //添加借款人补偿平台记录
//  			}
//  			
//  			if(repaymentType.equals((short)1)){//及时还款
//  				List<RepayMent> repayMents4 = null;
//  				List<RepayMent> repayMentCount = new ArrayList<>();
//  				boolean fa = false;
//  				for(DividedPayments dividedPayments4 : dividedPayments){
//  					Map<String,Object> hashMap4 = new HashMap<String,Object>();
//   					hashMap4.put("tenderid", dividedPayments4.getTenderid());//标号ID
//   					hashMap4.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//  					hashMap4.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//  					hashMap4.put("repaystatus", (short)6);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					repayMents4 = repayMentServiceI.findListRepayMentByConditions(hashMap4);
//					if(repayMents4.size() > 0){
//						repayMentCount.addAll(repayMents4);
//					} 
//					repayMents4.clear();
//   				}
//  				
//  				if(repayMentCount.size() > 0){
//  					fa = thirdRepayMentDealI.settingUpBatchAheadRepayMent(repayMentCount, bacthNo, userFsAccountInfo.getUsrcustid(), AheadRamountCount, tenderItem.getTno());
//  				}
// 
//  				if(fa){
//   					hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//  	 		 		hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_partdays_success");
//  	 		 		hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交成功！");
//  					 String str = JSON.toJSONString(hashMap);
//  					for(DividedPayments dividedPayments4 : dividedPayments){
// 	  	  					dividedPayments4.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//	  	    				dividedPaymentsServiceI.updateById(dividedPayments4);
//	  	  				}
//  					 try {
//  						StringUtil.sendJsonData(response, str);
//  					} catch (IOException e) {
//  						e.printStackTrace();
//  					}
//  					 return null;
//  					 
//  				}else{
//    				hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//  	 		 		hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_partdays_fail");
//  	 		 		hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,提前还款提交失败,请重新操作或联系客服！");
//  					
// 					 String str = JSON.toJSONString(hashMap);
// 					 try {
// 						StringUtil.sendJsonData(response, str);
// 					} catch (IOException e) {
// 						e.printStackTrace();
// 					}
// 					 return null;
//  				}
//  				
//			}else{//2冻结还款
//  				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//	 		 	hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_partdays_isauditSuccess");
//	 		 	hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交客服审核成功,请等待客服审核通过!");
//				 String str = JSON.toJSONString(hashMap);
//				 try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				 return null;
//				 
//			}
//		}else{//全额计息 直接调用正常还款接口
//			List<RepayMent> repayMents3 = null;
//			List<RepayMent> repayMentsCount = new ArrayList<RepayMent>();
//			if(repaymentType.equals((short)1)){//及时还款
//				for(DividedPayments dividedPayments4 : dividedPayments){
//					Map<String,Object> maps = new HashMap<String,Object>();
//					maps.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					maps.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					maps.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					repayMents3 =  repayMentServiceI.findListRepayMentByConditions(maps);
//					if(repayMents3.size() > 0){
//						repayMentsCount.addAll(repayMents3);
//					}
//					repayMents3.clear();
//				}
//				
//				boolean falg = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMentsCount, bacthNo, userFsAccountInfo.getUsrcustid(), AheadRamountCount, tenderItem.getTno());
//				if(falg){
//  					hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//		 		 	hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_alldays_success");
//		 		 	hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款成功提交成功！");
//		 		 	for(DividedPayments dividedPayments4 : dividedPayments){
// 	  	  					dividedPayments4.setRsubmittype((short)1);/*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
//	  	    				dividedPaymentsServiceI.updateById(dividedPayments4);
//	  	  			}
//				}else{
//  					hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//		 		 	hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_alldays_fail");
//		 		 	hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时，提前还款成功提交失败,请重新操作或联系客服！");
//				}
//				
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				} 
//				
//			}else{//2冻结还款
//				for(DividedPayments dividedPayments4 : dividedPayments){
//					Map<String,Object> maps = new HashMap<String,Object>();
//					maps.put("tenderid", dividedPayments4.getTenderid());//标号ID
//					maps.put("periods", dividedPayments4.getPeriods());//还款期数（第几期）
//					maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//					maps.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//					repayMents3 =  repayMentServiceI.findListRepayMentByConditions(maps);
//					if(repayMents3.size() > 0){
//						for(RepayMent repayMent4 : repayMents3){
//							if(repayMent4.getIsaudit().equals((short)0) || repayMent4.getIsaudit().equals((short)3)){//是否审核 0待审核 1客服审核中 2审核通过 3审核不通过 
//								repayMent4.setIsaudit((short)1);
//							}
//							repayMent4.setRepaystatus((short)4);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//							repayMentServiceI.updateById(repayMent4);
//						}
//					}
//				}
//				
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交客服审核成功,请等待客服审核通过！");
//				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//	 		 	hashMap.put(Session_Constant.RESULTCODE,"allaheadrepay_alldays_isauditSuccess");
//	 		 	hashMap.put(Session_Constant.MESSAGE, "提示：操作成功,提前还款提交客服审核成功,请等待客服审核通过！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				} 
//			}
//		}
// 		return null;
//	}
//	
//	/**
//	 * 
//	 * @Title: doOverdueRepayMentSelect 
//	 * @Description: TODO(逾期还款) 
//	 * @param @return  参数说明 
//	 * @return String    返回类型 
//	 * @author cjm
//	 * @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentSelect",method = RequestMethod.POST)
//	@Token(save = true)
//	public String doOverdueRepayMentSelect(HttpServletRequest request,HttpServletResponse responses){
//		String opid = request.getParameter("opid");//还款计划ID
//		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){//还款计划ID为空
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "opid_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！参数错误,借款人还款计划信息找不到,请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
// 		if(dividedPayment == null ){//没有找到还款计划
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！参数错误,借款人还款计划信息找不到,请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		boolean flag = overdueRepayMentLogicDealI.isOverdue(dividedPayment);//出于安全性考虑  判断是否逾期   返回false 逾期
//		if(flag){//没有逾期
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "Overdue_error");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！未逾期不能逾期还款,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		boolean falg2 = overdueRepayMentLogicDealI.CheckBeforeIsRepayMentByDividedPayments(dividedPayment);//判断前面是否还款完成
//		if(!falg2){//请先把完成前面的还款
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "beforeIsRepayMent_error");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！前面的还款未结清,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的设置
//		if(tenderItem == null || tenderItem.getTno() == null || tenderItem.getIsapartrepay() == null){//没有找到还款计划
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！参数错误,因网络响应不及时,标的设置信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Map<String,Object> OverDueHashMap = new HashMap<String,Object>();
//		OverDueHashMap.put("tenderid", dividedPayment.getTenderid());
//		OverDueHashMap.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		OverDueHashMap.put("repaystatus", (short)1);/* 还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//		OverDueHashMap.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(OverDueHashMap);
//		if(!(repayMents.size() > 0)){//没有找到还款计划
//			hashMap.put(Session_Constant.RESULT,Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "repayMentSize_error");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！参数错误,投资人还款计划信息找不到,请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
//		Double  overdueAmountCount = 0.00;//逾期滞纳金额总和
//		Double  count              = 0.00;//合计
//		DecimalFormat format = new DecimalFormat("###################0.00");
//		Integer overdueDay = overdueRepayMentLogicDealI.getOverdueDay(dividedPayment);//逾期天数
//		OverdueFeeRate overdueFeeRate = null;//逾期滞纳金费率设置对象
//	 	if(tenderItem.getOcmaxday().intValue() >= overdueDay){//逾期滞纳金计算截止天数(逾期后 超过该天数  不再计算滞纳金)
//			List<OverdueFeeRate> overdueFeeRates = overdueFeeRateServiceI.findOverdueFeeRatesByTid(tenderItem.getId());
//				overdueFeeRate = overdueRepayMentLogicDealI.getOverdueFeeRateByOverdueDay(overdueFeeRates, overdueDay);
//		}
// 	 	
//	 	Double ramount   		= 0.00 ;//当期还款本金（当期本金） 
//		Double rinterest 		= 0.00 ;//当期还本金利息
//   		Double Feerate	   	 	= 0.00 ;//逾期利率
//		Double overdueAmount 	= 0.00;//逾期滞纳金额
//		Double  repayCount   	= 0.00;
// 		Short isapartrepay 		= tenderItem.getIsapartrepay();//是否支持部分还款 1允许 0 不允许
//		if(isapartrepay != null && isapartrepay.equals((short)1)){//允许部分还款
//			if(overdueFeeRate != null
//					&& overdueFeeRate.getFeeratetype() != null){
//				if(overdueFeeRate.getFeeratetype().equals((short)1)){//费率计算方式(1.等比例,2.不等比例)
//					 for(RepayMent repayMent :repayMents){
//					     ramount  		= Double.valueOf(format.format(repayMent.getRamount() + repayMent.getDisablevoucher() + repayMent.getRvoucher()));//本金 + 类金 + 作废类金 
//					     rinterest 		= Double.valueOf(format.format(repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint()));// 本息 + 类息 + 作废类息
//					     Feerate		= overdueFeeRate.getFeerate();//逾期利率
//					     overdueAmount 	= Double.valueOf(format.format(Arith.mul(Arith.mul(Feerate, (ramount)), overdueDay)));//逾期滞纳金额
//					     repayMent.setOverdueamount(overdueAmount);
// 						 repayMentServiceI.updateById(repayMent);
//  						 overdueAmountCount = Double.valueOf(format.format(overdueAmount + overdueAmountCount));
//						 repayCount = Double.valueOf(format.format(ramount + rinterest +  overdueAmount));
// 						 count  = Double.valueOf(format.format(repayCount + count));
//  					 }
//					 
//		  }else{
//					for(RepayMent repayMent :repayMents){
//						 ramount  		= Double.valueOf(format.format(repayMent.getRamount() + repayMent.getDisablevoucher() + repayMent.getRvoucher()));//本金 + 类金 + 作废类金 
//					     rinterest 		= Double.valueOf(format.format(repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint()));// 本息 + 类息 + 作废类息
//					     Feerate		= overdueFeeRate.getFeerate();//逾期利率
//					     overdueAmount 	= Double.valueOf(format.format(Arith.mul(Arith.mul(Feerate, (ramount)), overdueDay)));//逾期滞纳金额
//						 repayMent.setOverdueamount(overdueAmount);
// 						 repayMentServiceI.updateById(repayMent);
// 						  
//						 repayCount = Double.valueOf(format.format(ramount + rinterest + overdueAmount));
// 						 count  = Double.valueOf(format.format(repayCount + count));
// 						 overdueAmountCount = Double.valueOf(format.format(overdueAmount + overdueAmountCount));
// 					 }
//				}
//			}
//			
// 			session.setAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS,opid);
// 			hashMap.put(Session_Constant.RESULT,"apartrepay");
// 			hashMap.put(Session_Constant.RESULTCODE, "apartrepay");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作成功！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//			
//		}else{//不允许部分还款 
//  			List<RepayMentFineDetail> repayMentFineDetails = new ArrayList<RepayMentFineDetail>();
// 			if(overdueFeeRate != null
//					&& overdueFeeRate.getFeeratetype() != null){
// 				UserBaseAccountInfo baseAccountInfo = null;
//				if(overdueFeeRate.getFeeratetype().equals((short)1)){//费率计算方式(1.等比例,2.不等比例)
//					 for(RepayMent repayMent :repayMents){
//						 RepayMentFineDetail detail = new RepayMentFineDetail();
//						 ramount  		= Double.valueOf(format.format(repayMent.getRamount() + repayMent.getDisablevoucher() + repayMent.getRvoucher()));//本金 + 类金 + 作废类金 
//					     rinterest 		= Double.valueOf(format.format(repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint()));// 本息 + 类息 + 作废类息
//					     Feerate		= overdueFeeRate.getFeerate();//逾期利率
//					     overdueAmount 	= Double.valueOf(format.format(Arith.mul(Arith.mul(Feerate, (ramount)), overdueDay)));//逾期滞纳金额
//						 repayMent.setOverdueamount(overdueAmount);
// 						 repayMentServiceI.updateById(repayMent);
// 						 if(repayMent.getInaccountid() != null){
//							  baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
//							  baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
//							 if(baseAccountInfo.getLoginname() != null){
//								 detail.setUsername(baseAccountInfo.getLoginname());
//							 }
//						 }
//						 overdueAmountCount = Double.valueOf(format.format(overdueAmount + overdueAmountCount));
//						 repayCount = Double.valueOf(format.format(ramount + rinterest + overdueAmount));
//						 repayMent.setCount(repayCount);
//						 count  = Double.valueOf(format.format(repayCount + count));
// 						 detail.setCount(repayCount);
//						 detail.setRamount(ramount);
//						 detail.setRinterest(rinterest);
// 						 detail.setOverdueAmount(overdueAmount);
//						 repayMentFineDetails.add(detail);
// 					 }
//					 
//				}else{
//					for(RepayMent repayMent :repayMents){
//						 RepayMentFineDetail detail = new RepayMentFineDetail();
//						 ramount  		= Double.valueOf(format.format(repayMent.getRamount() + repayMent.getDisablevoucher() + repayMent.getRvoucher()));//本金 + 类金 + 作废类金 
//					     rinterest 		= Double.valueOf(format.format(repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint()));// 本息 + 类息 + 作废类息
//					     Feerate		= overdueFeeRate.getFeerate();//逾期利率
//					     overdueAmount 	= Double.valueOf(format.format(Arith.mul(Arith.mul(Feerate, (ramount)), overdueDay)));//逾期滞纳金额
//						 repayMent.setOverdueamount(overdueAmount);
// 						 repayMentServiceI.updateById(repayMent);
//						 if(repayMent.getInaccountid() != null){
//							  baseAccountInfo = userBaseAccountInfoServiceI
//									 .getUserBaseAccountInfoById(repayMent.getInaccountid());
//							  baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
//							 if(baseAccountInfo.getLoginname() != null){
//								 detail.setUsername(baseAccountInfo.getLoginname());
//							 }
//						 }
//						 repayCount = Double.valueOf(format.format(ramount + rinterest + overdueAmount));
//						 repayMent.setCount(repayCount);
//						 count  = Double.valueOf(format.format(repayCount + count));
// 						 overdueAmountCount = Double.valueOf(format.format(overdueAmount + overdueAmountCount));
// 						 detail.setCount(repayCount);
//						 detail.setRamount(ramount);
//						 detail.setRinterest(rinterest);
// 						 detail.setOverdueAmount(overdueAmount);
//						 repayMentFineDetails.add(detail);
//					 }
//				}
//			}else{
//				UserBaseAccountInfo baseAccountInfo = null;
//				for(RepayMent  repayMent : repayMents){
//					RepayMentFineDetail detail = new RepayMentFineDetail();
//					if(repayMent.getInaccountid() != null){
//						  baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
//						  baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
//						 if(baseAccountInfo.getLoginname() != null){//用户名
//							 detail.setUsername(baseAccountInfo.getLoginname());
//						 }
// 					 }
//					   ramount  		= Double.valueOf(format.format(repayMent.getRamount() + repayMent.getDisablevoucher() + repayMent.getRvoucher()));//本金 + 类金 + 作废类金 
//					   rinterest 		= Double.valueOf(format.format(repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint()));// 本息 + 类息 + 作废类息
//					   overdueAmount    = Double.valueOf(format.format(repayMent.getOverdueamount()));
//					   repayCount = Double.valueOf(format.format(ramount + rinterest + overdueAmount));
// 					   detail.setCount(repayCount);
//					   detail.setRamount(ramount);
//					   detail.setRinterest(rinterest);
// 					   detail.setOverdueAmount(overdueAmount);
//					   count  = Double.valueOf(format.format(repayCount + count));
//					   repayMentFineDetails.add(detail);
//				}
//			} 
//			hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//			hashMap.put("count", count.toString());
//			hashMap.put("list", JSON.toJSONString(repayMentFineDetails));
//			String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
//			if(StringUtil.isNotEmpty(token)){
//				hashMap.put("token", token);
//			}
//  			session.setAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS,opid);//借款人还款计划ID
//   			String str = JSON.toJSONString(hashMap);
//  			try {
//				StringUtil.sendJsonData(responses, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 		}
// 	}
//	
//	/**
//	 * 
//	* @Title: OverdueRepayMentTask 
//	* @Description: TODO(逾期全部还款调用接口  注意这里是不允许部分还款) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/OverdueRepayMentTask",method = RequestMethod.POST)
//	@Token(remove = true)
//	public String OverdueRepayMentTask(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){
// 			hashMap.put(Session_Constant.RESULT,Session_Constant.PARAMSERROR);
// 			hashMap.put(Session_Constant.RESULTCODE, "opid_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！借款人还款计划参数信息找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null || dividedPayment.getTenderid() == null || dividedPayment.getPeriods() == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！借款人还款信息找不到！请重新操作或联系客服");
//			
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的设置
//		if(tenderItem == null || tenderItem.getTno() == null || tenderItem.getRepaymenttype() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！标的设置信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		
//		Map<String,Object> hashMapRepayMent = new HashMap<>();
//		hashMapRepayMent.put("tenderid", dividedPayment.getTenderid());
//		hashMapRepayMent.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMapRepayMent.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		hashMapRepayMent.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMapRepayMent);
// 		
//		if(!(repayMents.size() > 0)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"repayMentSize_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//		RepayMent repaMent2 = repayMents.get(0);
//		UserFsAccountInfo  userFsAccountInfo  = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repaMent2.getOutaccountid());
//		if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！因网络响应不及时,银行电子账号信息找不到！请重新操作或联系客服");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//  		
//		String batchno = StringUtil.getNo();//还款批次号
//		
//		Double totalamount 	= 0.00;//本次还款总金额
//		Double ramount 		= 0.00;//当期还款本金（当期本金
//		Double rinterest 	= 0.00;//当期还款本金利息（当期本金利息） 
//		DecimalFormat df = new DecimalFormat("################0.00");
//		for(RepayMent ment : repayMents){
//			ramount 	= ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + ment.getOverdueamount();//本 + 类 + 作废类
//			rinterest 	= ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint();//本息 + 类息 + 作废类息
//			totalamount = Double.valueOf(df.format(ramount + rinterest + totalamount));//滞纳金 + 本 + 类 + 作废类 + 本息 + 类息 + 作废类息
//		}
// 		
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),totalamount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE + batchno);//还款冻结
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get(Session_Constant.RESULT);
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//用户可用余额不足
//				hashMap.put(Session_Constant.RESULTCODE, "balance_deficiency");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,余额不足！请充值后重新操作");
//			}else{
// 				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//还款冻结失败
// 				hashMap.put(Session_Constant.RESULTCODE, "freeze_error");
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,因网络响应不及时,还款冻结失败！请重新操作");
//			}
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Short repaymentType	= tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
//		if(repaymentType.equals((short)1)){
//			boolean falg = thirdRepayMentDealI.settingUpBatchOverdueRepayMent(repayMents, batchno, userFsAccountInfo.getUsrcustid(), totalamount.toString(), tenderItem.getTno());
//			if(falg){
//				hashMap.put(Session_Constant.RESULT,Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.RESULTCODE, "success");
//				hashMap.put(Session_Constant.MESSAGE,"提示：操作成功！逾期还款提交成功！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}else{
//				hashMap.put(Session_Constant.RESULT,Session_Constant.FAIL);
//				hashMap.put(Session_Constant.RESULTCODE, "fail");
//				hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！因网络响应不及时,逾期还款提交失败！请重新操作或联系客服");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//			
//		}else{//冻结还款
// 			for(RepayMent ment : repayMents){
//				 if(ment.getIsaudit().equals((short)0) || ment.getIsaudit().equals((short)3)){
//					 ment.setIsaudit((short)1);//是否审核 0未审核 1待审核  2审核通过 3审核失败
//				 }
//				 ment.setRbatchno(batchno);//还款批次号
//				 ment.setRepaystatus((short)8);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//				 repayMentServiceI.updateById(ment);
//			}
// 			hashMap.put(Session_Constant.RESULT,Session_Constant.SUCCESS);
//			hashMap.put(Session_Constant.RESULTCODE, "audit_success");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作成功!提交客服审核成功！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 			
// 		}
//		
//	}
//
//	/**
//	 * 
//	* @Title: doOverdueRepayMentPartSelect 
//	* @Description: TODO(逾期还款 部分选择) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentPartSelect",method = {RequestMethod.POST,RequestMethod.GET})
//	@Token(save = true)
//	public String doOverdueRepayMentPartSelect(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//逾期还款 借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "opid_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划参数找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划信息找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//		Map<String,Object> hashMap1 = new HashMap<String,Object>();
//		hashMap1.put("tenderid", dividedPayment.getTenderid());
//		hashMap1.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMap1.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)*/
//		hashMap1.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap1);
//		if(!(repayMents.size() > 0)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "repayMentSize_error");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！投资人还款计划信息找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		 List<RepayMentFineDetail> fineDetails = new ArrayList<RepayMentFineDetail>();
//		 DecimalFormat format = new DecimalFormat("########################0.00");
//		 Double ramount   =  0.00 ;//当期还款本金（当期本金） 
//		 Double rinterest = 0.00;//当期还本金利息
//		 Double rvoucher  =  0.00;//当期还款类现金(类现金)
//		 Double rvoucherint   = 0.00 ;//当期还款类现金利息
//		 Double overdueAmount = 0.00 ;//逾期滞纳金额
//		 String rorderno	 = "";//还款流水号
//		 Double repayCount    =  0.00;
//		 
//		for(RepayMent ment : repayMents){
//			RepayMentFineDetail detail = new RepayMentFineDetail();
//			if(ment.getInaccountid() != null){
//				 UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI
//						 .getUserBaseAccountInfoById(ment.getInaccountid());
//				 baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
// 				 if(baseAccountInfo.getLoginname() != null){
//					 detail.setUsername(baseAccountInfo.getLoginname());
//				 }
//			 }
//			 ramount   = ment.getRamount();//当期还款本金（当期本金） 
//			 rinterest = ment.getRinterest();//当期还本金利息
//			 rvoucher = ment.getRvoucher() + ment.getDisablevoucher();//当期还款类现金(类现金)
//			 rvoucherint = ment.getRvoucherint() + ment.getDisablevoucherint();//当期还款类现金利息
//			 
//			 overdueAmount = ment.getOverdueamount();//逾期滞纳金额
//			 rorderno   = ment.getRorderno();//还款流水号
// 			 repayCount = Double.valueOf(format.format(ramount + rinterest + rvoucher + rvoucherint + overdueAmount));
//			 detail.setCount(repayCount);
//			 detail.setRamount(ramount);
//			 detail.setRinterest(rinterest);
//			 detail.setRvoucher(rvoucherint);
//			 detail.setRvoucherint(rvoucherint);
//			 detail.setOverdueAmount(overdueAmount);
//			 detail.setRorderno(rorderno);
//			 fineDetails.add(detail);
//		}
//		hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//		hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！");
//		String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
//		if(StringUtil.isNotEmpty(token)){//防止表单重复提交
//			hashMap.put("token", token);
//		}
//		hashMap.put("list", JSON.toJSONString(fineDetails));
//		String str = JSON.toJSONString(hashMap);
//		try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	* @Title: doOverdueRepayMentNormalAll 
//	* @Description: TODO(逾期还款 选择全部  注意 ： 这里是标的设置允许部分还款  ) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentNormalAll" ,method = {RequestMethod.POST,RequestMethod.GET})
//	@Token(save = true)
//	public String doOverdueRepayMentNormalAll(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//逾期还款 借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR); 
//			hashMap.put(Session_Constant.RESULTCODE, "opid_null"); 
// 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划参数找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR); 
//			hashMap.put(Session_Constant.RESULTCODE, "dividedPayment_null"); 
// 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！借款人还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		
//		Map<String,Object> hashMap1 = new HashMap<String,Object>();
//		hashMap1.put("tenderid", dividedPayment.getTenderid());
//		hashMap1.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMap1.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		hashMap1.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap1);
//		if(!(repayMents.size() > 0)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "repayMentSize_null"); 
// 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！投资人还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		 List<RepayMentFineDetail> fineDetails = new ArrayList<RepayMentFineDetail>();
//		 DecimalFormat format = new DecimalFormat("########################0.00");
//		 Double ramount   			= 0.00 ;//当期还款本金（当期本金） 
//		 Double rinterest 			= 0.00;//当期还本金利息
// 		 Double overdueAmount 		= 0.00 ;//逾期滞纳金额
//  		 Double repayCount    		= 0.00;//本次还款总额
//		 Double count		 		= 0.00;//单个投资人还款总金额
//		for(RepayMent ment : repayMents){
//			RepayMentFineDetail detail = new RepayMentFineDetail();
//			if(ment.getInaccountid() != null){
//				 UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI
//						 .getUserBaseAccountInfoById(ment.getInaccountid());
//				 baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);//解密加密后的数据
//				 if(baseAccountInfo.getLoginname() != null){//用户登录名
//					 detail.setUsername(baseAccountInfo.getLoginname());
//				 }
//			 }
// 			 ramount   			= Double.valueOf(format.format(ment.getRamount() + ment.getRvoucher() +  ment.getDisablevoucher()));//本金+ 类金 + 作废类金
//			 rinterest 			= Double.valueOf(format.format(ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint()));//本息 + 类息 + 作废类息
//			 overdueAmount 		= ment.getOverdueamount();//逾期滞纳金额
// 			 repayCount 		= Double.valueOf(format.format(ramount + rinterest + overdueAmount));
//			 count 				= Double.valueOf(format.format(repayCount + count ));
//			 detail.setCount(repayCount);//合计
//			 detail.setRamount(ramount);//本金+ 类金 + 作废类金
//			 detail.setRinterest(rinterest);//本息 + 类息 + 作废类息
// 			 detail.setOverdueAmount(overdueAmount);
// 			 fineDetails.add(detail);
//		}
//		
//		session.setAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS,opid);//借款人还款计划ID
// 		hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
// 		hashMap.put("list", JSON.toJSONString(fineDetails));
//		hashMap.put("count", count.toString());
//		String token = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
//		if(StringUtil.isNotEmpty(token)){
//			hashMap.put("token", token);
//		}
//		String str = JSON.toJSONString(hashMap);
//		try {
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
//	/**
//	 * 
//	* @Title: doOverdueRepayMentPartSelectTask 
//	* @Description: TODO(逾期还款 部分逾期还款 调用接口) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentPartSelectTask" ,method = RequestMethod.POST)
//	@Token(remove = true)
//	public String doOverdueRepayMentPartSelectTask(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"opid_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！借款人还款计划参数找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		String rorderno = request.getParameter("rorderno");//选中的还款流水号
//		if(StringUtil.isEmpty(rorderno)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"rorderno_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！选中的还款参数找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		String[] rordernos = rorderno.split(",");
//		List<RepayMent> repayMents = new ArrayList<>();
//		RepayMent repayMent = null;
//		for(int i = 0 ; i < rordernos.length; i++){
//			repayMent = repayMentServiceI.findRepayMentByRorderno(rordernos[i]);
//			if(repayMent != null){
//				repayMents.add(repayMent);
//			}
//		}
//		
//		if(!(repayMents.size() > 0)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"repayMentSize_error");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//		RepayMent repaMent2 = repayMents.get(0);
//		UserFsAccountInfo  userFsAccountInfo  = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repaMent2.getOutaccountid());
//		if(userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！第三方信息找不到！请重新操作或联系客服");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！借款人还款信息找不到！请重新操作或联系客服");
//			
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的设置
//		if(tenderItem == null || tenderItem.getTno() == null || tenderItem.getRepaymenttype() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！标的设置信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 		
//		String batchno = StringUtil.getNo();//还款批次号
//		
//		Double totalamount 	= 0.00;//本次还款总金额
//		Double ramount 		= 0.00;//当期还款本金（当期本金
//		Double rinterest 	= 0.00;//当期还款本金利息（当期本金利息） 
//		DecimalFormat df = new DecimalFormat("################0.00");
//		for(RepayMent ment : repayMents){
//			ramount 	= ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() + ment.getOverdueamount();
//			rinterest 	= ment.getRinterest() + ment.getRvoucherint() +ment.getDisablevoucherint();
//			totalamount = Double.valueOf(df.format(ramount + rinterest + totalamount));
//		}
// 		
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),totalamount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE+batchno);//还款冻结
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get(Session_Constant.RESULT);
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//用户可用余额不足
//				hashMap.put(Session_Constant.RESULTCODE, "balance_deficiency");
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,余额不足！请充值后重新操作");
//			}else{
// 				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//还款冻结失败
// 				hashMap.put(Session_Constant.RESULTCODE, "freeze_error");
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,还款冻结失败！请重新操作");
//			}
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Short repaymentType	= tenderItem.getRepaymenttype();//还款类型（1及时，2冻结还款）
//		if(repaymentType.equals((short)1)){
//			boolean falg = thirdRepayMentDealI.settingUpBatchOverdueRepayMent(repayMents, batchno, userFsAccountInfo.getUsrcustid(), totalamount.toString(), tenderItem.getTno());
//			if(falg){
//				hashMap.put(Session_Constant.RESULT,Session_Constant.SUCCESS);
//				hashMap.put(Session_Constant.RESULTCODE, "success");
//				hashMap.put(Session_Constant.MESSAGE,"提示：操作成功！逾期还款提交成功！");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}else{
//				hashMap.put(Session_Constant.RESULT,Session_Constant.FAIL);
//				hashMap.put(Session_Constant.RESULTCODE, "fail");
//				hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！逾期还款提交失败！请重新操作或联系客服");
//				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//			
//		}else{//冻结还款
// 			for(RepayMent ment : repayMents){
//				 if(ment.getIsaudit().equals((short)0) || ment.getIsaudit().equals((short)3)){
//					 ment.setIsaudit((short)1);//是否审核 0未审核 1待审核  2审核通过 3审核失败
//				 }
//				 ment.setRbatchno(batchno);//还款批次号
//				 ment.setRepaystatus((short)8);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//				 repayMentServiceI.updateById(ment);
//			}
// 			hashMap.put(Session_Constant.RESULT,Session_Constant.SUCCESS);
//			hashMap.put(Session_Constant.RESULTCODE, "audit_success");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作成功!提交客服审核成功！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
// 			
// 		}
// 	}
//	
//	
//	/**
//	 * 
//	* @Title: doOverdueRepayMentAllTask 
//	* @Description: TODO(逾期还款 全部逾期还款 接口调用  注意：这里是不允许部分还款的) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentAllTask" ,method = RequestMethod.POST)
//	@Token(remove = true)
// 	public String doOverdueRepayMentAllTask(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "opid_null");//参数错误
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,借款人还款计划参数找不到！请重新操作或联系客服");//参数错误
//			
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！因网络响应不及时,借款人还款计划信息找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的设置
//		if(tenderItem == null ||tenderItem.getTno() == null || tenderItem.getRepaymenttype() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,标的设置信息找不到！请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Map<String,Object> hashMap1 = new HashMap<String,Object>();
//		hashMap1.put("tenderid", dividedPayment.getTenderid());
//		hashMap1.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMap1.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		hashMap1.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap1);
//		if(!(repayMents.size() > 0)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "repayMentSize_null"); 
// 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,投资人还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMents.get(0).getOutaccountid());
//		if(userFsAccountInfo == null ||userFsAccountInfo.getUsrcustid() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,银行电子账号信息找不到！请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//		DecimalFormat df = new DecimalFormat("###############0.00");
//		Double ramountCount = 0.00;//总金额
//		for(RepayMent ment : repayMents){
//			ramountCount = Double.valueOf(df.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher()
//			+ ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + ramountCount));
//		}
//		
//		String bacthNo = StringUtil.getNo();//批次号
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),ramountCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE + bacthNo);
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get(Session_Constant.RESULT);
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE, "balance_deficiency");//用户可用余额不足
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,账号余额不足,请充值后重新操作！");
//			}else{
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 				hashMap.put(Session_Constant.RESULTCODE, "freeze_error");//还款冻结失败
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,还款冻结失败,请重新操作或联系客服");
//			}
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		if(tenderItem.getRepaymenttype().equals((short)1)){//还款类型（1及时，2冻结还款）
//			   boolean falg = thirdRepayMentDealI.settingUpBatchOverdueRepayMent(repayMents, bacthNo, userFsAccountInfo.getUsrcustid(), ramountCount.toString(), tenderItem.getTno());
//			   if(falg){
//				   hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//					hashMap.put(Session_Constant.RESULTCODE, "success");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！逾期还款提交成功！");
//		 			String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//			   }else{
//				   hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//					hashMap.put(Session_Constant.RESULTCODE, "fail");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,逾期还款提交失败！请重新操作或联系客服！");
//		 			String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//			   }
//		}else{
//			for(RepayMent repayMent : repayMents){
//				if(repayMent.getIsaudit().equals((short)0) || repayMent.getIsaudit().equals((short)3)){
//					repayMent.setIsaudit((short)1);/* 是否审核 0未审核 1待审核  2审核通过 3审核失败 */
//				}
//				repayMent.setRbatchno(bacthNo);// 还款批次号
//				repayMent.setRepaystatus((short)8);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//				repayMentServiceI.updateById(repayMent);
//			}
//			
//			hashMap.put(Session_Constant.RESULT,Session_Constant.SUCCESS);
//			hashMap.put(Session_Constant.RESULTCODE, "success");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！逾期还款提交客服审核成功！请等待客服审核处理!");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 	}
//  	
//	
//	/**
//	 * 
//	* @Title: doOverdueRepayMentNormalAllTask 
//	* @Description: TODO(逾期还款 全部逾期还款 接口调用  注意：这里是允许部分还款的) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/doOverdueRepayMentNormalAllTask" ,method = RequestMethod.POST)
//	@Token(remove = true)
// 	public String doOverdueRepayMentNormalAllTask(HttpServletRequest request,HttpServletResponse response){
//		String opid = (String) request.getSession().getAttribute(Session_Constant.OVERDUEREPAYMENT_DIVMENTS);//借款人还款计划ID
// 		Map<String,String> hashMap = new HashMap<String,String>();
//		if(StringUtil.isEmpty(opid)){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE, "opid_null");//参数错误
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,借款人还款计划参数找不到！请重新操作或联系客服");//参数错误
//			
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//  		
//		DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsById(new BigDecimal(opid));
//		if(dividedPayment == null){
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);//参数错误
//			hashMap.put(Session_Constant.RESULTCODE,"dividedPayment_null");
//			hashMap.put(Session_Constant.MESSAGE,"提示：操作失败！因网络响应不及时,借款人还款计划信息找不到！请重新操作或联系客服！");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayment.getTenderid());//标的设置
//		if(tenderItem == null ||tenderItem.getTno() == null || tenderItem.getRepaymenttype() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "tenderItem_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,标的设置信息找不到！请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		Map<String,Object> hashMap1 = new HashMap<String,Object>();
//		hashMap1.put("tenderid", dividedPayment.getTenderid());
//		hashMap1.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//		hashMap1.put("repaystatus", (short)1);/*还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中) */
//		hashMap1.put("periods", dividedPayment.getPeriods());
//		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap1);
//		if(!(repayMents.size() > 0)){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "repayMentSize_null"); 
// 			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,投资人还款计划信息找不到！请重新操作或联系客服");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMents.get(0).getOutaccountid());
//		if(userFsAccountInfo == null ||userFsAccountInfo.getUsrcustid() == null){//参数错误
//			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//			hashMap.put(Session_Constant.RESULTCODE, "userFsAccountInfo_null");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,银行电子账号信息找不到！请重新操作或联系客服！");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密加密后的数据
//		
//		DecimalFormat df = new DecimalFormat("###############0.00");
//		Double ramountCount = 0.00;//总金额
//		for(RepayMent ment : repayMents){
//			ramountCount = Double.valueOf(df.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher()
//			+ ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + ramountCount));
//		}
//		
//		String bacthNo = StringUtil.getNo();//批次号
//		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(),ramountCount.toString(), tenderItem.getTno(), HSignUtil.COINSTCODE + bacthNo);
//		boolean fg = (boolean) resMap.get("flag");
//		String tre = (String) resMap.get("result");
//		if(!fg){
//			if(tre.equals("balance_deficiency")){
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
//				hashMap.put(Session_Constant.RESULTCODE, "balance_deficiency");//用户可用余额不足
//				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,账号余额不足,请充值后重新操作！");
//			}else{
//				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
// 				hashMap.put(Session_Constant.RESULTCODE, "freeze_error");//还款冻结失败
// 				hashMap.put(Session_Constant.MESSAGE, "提示：操作失败,还款冻结失败,请重新操作或联系客服");
//			}
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//		
//		if(tenderItem.getRepaymenttype().equals((short)1)){//还款类型（1及时，2冻结还款）
//			   boolean falg = thirdRepayMentDealI.settingUpBatchOverdueRepayMent(repayMents, bacthNo, userFsAccountInfo.getUsrcustid(), ramountCount.toString(), tenderItem.getTno());
//			   if(falg){
//				   hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//					hashMap.put(Session_Constant.RESULTCODE, "success");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！逾期还款提交成功！");
//		 			String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//			   }else{
//				   hashMap.put(Session_Constant.RESULT, Session_Constant.FAIL);
//					hashMap.put(Session_Constant.RESULTCODE, "fail");
//					hashMap.put(Session_Constant.MESSAGE, "提示：操作失败！因网络响应不及时,逾期还款提交失败！请重新操作或联系客服！");
//		 			String str = JSON.toJSONString(hashMap);
//					try {
//						StringUtil.sendJsonData(response, str);
//					} catch (IOException e) {
//		 				e.printStackTrace();
//					}
//					return null;
//			   }
//		}else{
//			for(RepayMent repayMent : repayMents){
//				if(repayMent.getIsaudit().equals((short)0) || repayMent.getIsaudit().equals((short)3)){
//					repayMent.setIsaudit((short)1);/* 是否审核 0未审核 1待审核  2审核通过 3审核失败 */
//				}
//				repayMent.setRbatchno(bacthNo);// 还款批次号
//				repayMent.setRepaystatus((short)8);//还款状态(1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
//				repayMentServiceI.updateById(repayMent);
//			}
//			
//			hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
//			hashMap.put(Session_Constant.RESULTCODE, "success");
//			hashMap.put(Session_Constant.MESSAGE, "提示：操作成功！逾期还款提交客服审核成功！请等待客服审核处理!");
// 			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return null;
//		}
// 	}
//	
//	/**
//	 * 显示最近的收款信息
//	 * @param request
//	 * @param response
//	 * @return
//	 * @author cjm
//	 */
//	@RequestMapping(value = "/repayMentDetails",method = {RequestMethod.POST})
//	public String repayMentDetails(HttpServletRequest request,HttpServletResponse response){
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
//		 int num = 1;
//		 int pageSize = 5;
// 		 String sort = "id.desc";
//		 Order.formString(sort);
//		 PageHelper.startPage(num, pageSize);
////		 repayMentServiceI.
//		
//		return null;
// 	}
//  	
// 	/**
//	 * 
//	* @Title: isClient 
//	* @Description: TODO(判断当期http 客户端) 
//	* @param @param request
//	* @param @return    设定文件 
//	* @return String    返回类型 
//	* @author   cjm  
//	* @throws
//	 */
//    public String isClient(HttpServletRequest request) {
//        String userAgent = request.getHeader("user-agent").toLowerCase();
//        if(userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true){ // 判断当前客户端是否为PC
//        	return "d";
//        }else if(userAgent == null || userAgent.indexOf("android") == -1 ? false : true){ // 判断当前客户端是否为android
//        	return "a";
//        }else if(userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true){ // 判断当前客户端是否为iPhone
//        	return "p";
//        }else if(userAgent == null || userAgent.indexOf("wap") == -1 ? false : true){ // 判断当前客户端是否为wap
//        	return "o";
//        }else if(userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true){ // 判断当前客户端是否为微信
//        	return "w";
//        }
//        return "";
//    }
//
//}
