package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.HSRepayMentFreezeController;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.InterestExpenseRecordServiceI;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.DateUtil;
import com.ptpl.web.util.DividedPaymentsComparator;
import com.ptpl.web.util.StringUtil;

public class RepayMentBaseDealImpl extends BaseController implements RepayMentBaseDealI{

	@Autowired
	private RepayMentServiceI repayMentServiceImpl;
	
	@Autowired
	private DividedPaymentsServiceI dividedPaymentsServiceI;
 	
	@Autowired
	private TenderItemServiceI tenderItemServiceI;
	
	@Autowired
	private  RepaymentDetailServiceI repaymentDetailServiceI;
	
	@Autowired
	private  RepaymentFrzServiceI repaymentFrzServiceI;
 	
	@Autowired
	private  UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	
	@Autowired
	private  InterestExpenseServiceI interestExpenseServiceI;
	
	@Autowired
	private   AheadRealRepaymentServiceI aheadRealRepaymentServiceI;
	
	@Autowired
	private  InterestExpenseRecordServiceI interestExpenseRecordServiceI;
	/**
	 * 
	* @Title: checkDividedPaymentsNormalRepayMent 
	* @Description: TODO(判断 正常还款是否跳期还款) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	@Override
	public boolean checkDividedPaymentsNormalRepayMent(DividedPayments dividedPayment) {
		boolean flag = true;
		Assert.notNull(dividedPayment, "'dividedPayment 标的借款人还款计划不能为null'");
		BigDecimal TenderID = dividedPayment.getTenderid();
		Integer     periods = dividedPayment.getPeriods();
		Assert.notNull(TenderID, "'TenderID 标的借款人还款计划标的ID不能为null'");
		Assert.notNull(periods, "'periods  标的借款人还款计划的还款期数不能为null'");
 		DividedPayments dividedPayment2 = new DividedPayments();
		dividedPayment2.setTenderid(TenderID);
		List<DividedPayments>  dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment2);
		if(!(dividedPayments.size() > 0)){
			flag = false;
 			return flag;
		}
		Collections.sort(dividedPayments, new DividedPaymentsComparator());//排序
		for(DividedPayments payments : dividedPayments){
			 if(payments.getPeriods() < periods){
 				 if(!(payments.getIscomplete().equals((short)1))){ /*是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)*/
						flag = false;
						break;
				 }
			 }
		}
 		return flag;
	}
	 
	/**
	 * 
	* @Title: CheckRepayMentOverdue 
	* @Description: TODO(定时判断是否逾期  更新标的还款计划是否逾期   更新具体还款计划是否逾期) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	@Override
	public void CheckRepayMentOverdue() {
 		//查询全部还款中的标的信息
		TenderItem tenderItem = new TenderItem();
 		tenderItem.setTstatus((short)7);//还款中
   		List<TenderItem> tenderItems = tenderItemServiceI.selectByCondition(tenderItem);
  		if(!(tenderItems.size() > 0)){
 			if(logger.isDebugEnabled()){
 				logger.debug("定时判断是否逾期！没有找到还款中的标的信息。");
 			}
  			return ;
 		}
 		
 		for(TenderItem tenderItem2 : tenderItems){
 			getTheCurrentPeriodDividedPayments(tenderItem2);
 			getTheCurrentPeriodRepayMents(tenderItem2);
 		}
 	}
	
	//判断标的还款计划是否逾期，逾期则更新逾期状态
	public void getTheCurrentPeriodDividedPayments(TenderItem tenderItem){
		if(tenderItem == null || tenderItem.getId() == null){
			return ;
		}
		
		DividedPayments dividedPayment = new DividedPayments();
		dividedPayment.setTenderid(tenderItem.getId());
		dividedPayment.setIsoverdue((short)0);//是否逾期1已经逾期0没有逾期
  		List<DividedPayments> dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment);
  		if(!(dividedPayments.size() > 0)){
  			return ;
  		}
  		
  		Date date = new Date();
  		date = StringUtil.getDateByString(StringUtil.formatDate(date, "yyyy-MM-dd"),"yyyy-MM-dd");//当前时间
  		for(DividedPayments dividedPayments2 : dividedPayments){
  			if(dividedPayments2.getIscomplete().equals((short)1)){//  是否还款完成0未还款 1已还款 2处理中 3部分还款
   				 continue;
  			}
  			
  			if(dividedPayments2.getIsoverdue().equals((short)1)){//是否逾期1已经逾期0没有逾期
  				continue;
  			}
  			
   			Date overdate = StringUtil.getDateByString(StringUtil.formatDate(DateUtil.getOverdueNumDay(tenderItem, dividedPayments2.getRepayday()), "yyyy-MM-dd"),"yyyy-MM-dd");//格式化后的当期逾期宽限日期还款时间
   			if(date.getTime() > overdate.getTime()){//逾期了
   				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("tenderid", dividedPayments2.getTenderid());//标号ID
 				map2.put("periods", dividedPayments2.getPeriods());//还款期数（第几期）
				map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
				map2.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
				List<RepayMent> repayMents = repayMentServiceImpl.findListRepayMentByConditions(map2);
				if(repayMents.size() > 0){//如果没有找到未还款的则不更新逾期状态（有可能正在处理中）
					dividedPayments2.setIsoverdue((short)1);//是否逾期1已经逾期0没有逾期
					dividedPaymentsServiceI.updateById(dividedPayments2);
				}
				
  			}
  		}
  		
	}
	
 	/**
	 * 
	* @Title: getTheCurrentPeriodRepayMents 
	* @Description: TODO(判断具体还款计划是否逾期，逾期则更新逾期状态) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public void getTheCurrentPeriodRepayMents(TenderItem tenderItem){
		if(tenderItem == null || tenderItem.getId() == null){
			return ;
		}
		
		DividedPayments dividedPayment = new DividedPayments();
		dividedPayment.setTenderid(tenderItem.getId());
		dividedPayment.setIsoverdue((short)0);//是否逾期1已经逾期0没有逾期
  		List<DividedPayments> dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment);
  		if(!(dividedPayments.size() > 0)){
  			return ;
  		}
  		
  		Date date = new Date();
  		date = StringUtil.getDateByString(StringUtil.formatDate(date, "yyyy-MM-dd"),"yyyy-MM-dd");//当前时间
  		for(DividedPayments dividedPayments2 : dividedPayments){
  			if(dividedPayments2.getIscomplete().equals((short)1)){//  是否还款完成0未还款 1已还款 2处理中 3部分还款
   				 continue;
  			}
   			
   			Date overdate = StringUtil.getDateByString(StringUtil.formatDate(DateUtil.getOverdueNumDay(tenderItem, dividedPayments2.getRepayday()), "yyyy-MM-dd"),"yyyy-MM-dd");//格式化后的当期逾期宽限日期还款时间
   			if(date.getTime() > overdate.getTime()){//当期时间大于还款宽限日期后。就是逾期了
   				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("tenderid", dividedPayments2.getTenderid());//标号ID
 				map2.put("periods", dividedPayments2.getPeriods());//还款期数（第几期）
				map2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
				map2.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
				List<RepayMent> repayMents = repayMentServiceImpl.findListRepayMentByConditions(map2);
				if(repayMents.size() > 0){//如果没有找到未还款的则不更新逾期状态（有可能正在处理中）
					 for(RepayMent ment : repayMents){
						 if(ment.getIsoverdue().equals((short)1)){
							 continue;
						 }
						 ment.setIsoverdue((short)1);//是否逾期1已经逾期0没有逾期
						 repayMentServiceImpl.updateById(ment);
					 }
				}
				
  			}
  		}
 	}
 	
	 
	/**
	 * 
	* @Title: calculateNormalRepayemInterestManageFee 
	* @Description: TODO(计算 正常还款利息管理费) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> calculateNormalRepayemInterestManageFee(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 不允许为空");
		Double interestManageFee = 0.00;
		InterestExpense interestExpense = null;
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put(RepayMent_Constant.FALG, false);
		hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
		hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
 		
		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
		if(tenderItem == null || tenderItem.getTpro() == null){
			return hashMap;
		}
		
		if(tenderItem.getIsaintexp() != null && !tenderItem.getIsaintexp().equals((short)1)){//利息管理费不收取
			return hashMap;
		}
		
		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectBaseId(repayMent.getInaccountid());
		if(userAccountSafeInfo == null || userAccountSafeInfo.getUgrade() == null){
			return hashMap;
		}
		
		interestExpense = findInterestExpenseByUgradeAndTtype(userAccountSafeInfo.getUgrade(), tenderItem.getTpro());
		if(interestExpense != null){
			Double rinterest = Double.valueOf(df1.format(
					repayMent.getRinterest() + repayMent.getRvoucherint() +
					repayMent.getRlvoucherint() + repayMent.getRintfee()));//收益利息
			if(rinterest > 0){
 				Short ietype = 1;//百分比
				interestManageFee =  Double.valueOf(df1.format(Arith.mul(interestExpense.getIepercent(), rinterest)));//利息管理费
				if(interestManageFee > interestExpense.getMaxiefee()){//该段最高利息管理收费金额
					interestManageFee = interestExpense.getMaxiefee();
					ietype = 2;//最高
				}
				
				repayMent.setInterestexpense(interestManageFee);//更新利息管理费
				repayMentServiceImpl.updateById(repayMent);
				
				insertInterestExpenseRecord(repayMent, interestExpense, ietype);//添加利息管理费记录
				
				hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
				hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
				hashMap.put(RepayMent_Constant.FALG, true);
				return hashMap;
			}
		} 
		
		if(logger.isDebugEnabled()){
			logger.debug("计算利息管理费找不到对应的利息管理费设置 'interestExpense'  标号是："+tenderItem.getTno()+",标类型编号时："+tenderItem.getTpro()+",会员等级编号是："+userAccountSafeInfo.getUgrade());
		}
		
 		return hashMap;
	}
	
	 
	/**
	 * 
	* @Title: calculateAheadRepayemInterestManageFee 
	* @Description: TODO(计算提前还款利息管理费) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> calculateAheadRepayemInterestManageFee(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 不允许为空");
		Double interestManageFee = 0.00;
		InterestExpense interestExpense = null;
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put(RepayMent_Constant.FALG, false);
		hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
		hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
 		
		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
		if(tenderItem == null || tenderItem.getTpro() == null){
			return hashMap;
		}
		
		if(tenderItem.getIsaintexp() != null && !tenderItem.getIsaintexp().equals((short)1)){//利息管理费不收取
			return hashMap;
		}
		
		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectBaseId(repayMent.getInaccountid());
		if(userAccountSafeInfo == null || userAccountSafeInfo.getUgrade() == null){
			return hashMap;
		}
		
		interestExpense = findInterestExpenseByUgradeAndTtype(userAccountSafeInfo.getUgrade(), tenderItem.getTpro());
		if(interestExpense != null){
			AheadRealRepayment ahead = aheadRealRepaymentServiceI.findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(), repayMent.getRbatchno());
			if(ahead != null){
 				Double rinterest = Double.valueOf(df1.format(
 						ahead.getRinterest() + ahead.getRvoucherint() +
 						ahead.getRlvoucherint() + ahead.getRintfee()));//收益利息
 				
 				if(rinterest > 0){
  					Short ietype = 1;//百分比
 					interestManageFee =  Double.valueOf(df1.format(Arith.mul(interestExpense.getIepercent(), rinterest)));//利息管理费
 					if(interestManageFee > interestExpense.getMaxiefee()){//该段最高利息管理收费金额
 						interestManageFee = interestExpense.getMaxiefee();
 						ietype = 2;//最高
 					}
 					
 					ahead.setInterestexpense(interestManageFee);//更新利息管理费
 					aheadRealRepaymentServiceI.updateById(ahead);
 					
 					repayMent.setInterestexpense(interestManageFee);//更新利息管理费
 					repayMentServiceImpl.updateById(repayMent);
 					
 					insertInterestExpenseRecord(repayMent, interestExpense, ietype);//添加利息管理费记录
 					
 					hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
 					hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
 					hashMap.put(RepayMent_Constant.FALG, true);
 					return hashMap;
 				}
			}
		} 		 
		
		if(logger.isDebugEnabled()){
			logger.debug("计算利息管理费找不到对应的利息管理费设置 'interestExpense'  标号是："+tenderItem.getTno()+",标类型编号时："+tenderItem.getTpro()+",会员等级编号是："+userAccountSafeInfo.getUgrade());
		}
		
 		return hashMap;
	}
	 
	/**
	 * 
	* @Title: calculateOverdueRepayemInterestManageFee 
	* @Description: TODO(计算逾期还款利息管理费) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> calculateOverdueRepayemInterestManageFee(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 不允许为空");
		Double interestManageFee = 0.00;
		InterestExpense interestExpense = null;
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put(RepayMent_Constant.FALG, false);
		hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
		hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
 		
		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
		if(tenderItem == null || tenderItem.getTpro() == null){
			return hashMap;
		}
		
		if(tenderItem.getIsaintexp() != null && !tenderItem.getIsaintexp().equals((short)1)){//利息管理费不收取
			return hashMap;
		}
		
		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectBaseId(repayMent.getInaccountid());
		if(userAccountSafeInfo == null || userAccountSafeInfo.getUgrade() == null){
			return hashMap;
		}
		
		interestExpense = findInterestExpenseByUgradeAndTtype(userAccountSafeInfo.getUgrade(), tenderItem.getTpro());
		if(interestExpense != null){
			Double rinterest = Double.valueOf(df1.format(
					repayMent.getRinterest() + repayMent.getRvoucherint() +
					repayMent.getRlvoucherint() + repayMent.getRintfee()));//收益利息
			if(rinterest > 0){
 				Short ietype = 1;//百分比
				interestManageFee =  Double.valueOf(df1.format(Arith.mul(interestExpense.getIepercent(), rinterest)));//利息管理费
				if(interestManageFee > interestExpense.getMaxiefee()){//该段最高利息管理收费金额
					interestManageFee = interestExpense.getMaxiefee();
					ietype = 2;//最高
				}
				
				repayMent.setInterestexpense(interestManageFee);//更新利息管理费
				repayMentServiceImpl.updateById(repayMent);
				
				insertInterestExpenseRecord(repayMent, interestExpense, ietype);//添加利息管理费记录
				
				hashMap.put(RepayMent_Constant.INTERESTMANAGEFEE, interestManageFee);
				hashMap.put(RepayMent_Constant.INTERESTEXPENSE, interestExpense);
				hashMap.put(RepayMent_Constant.FALG, true);
				return hashMap;
			}
		}  
		
		if(logger.isDebugEnabled()){
			logger.debug("计算利息管理费找不到对应的利息管理费设置 'interestExpense'  标号是："+tenderItem.getTno()+",标类型编号时："+tenderItem.getTpro()+",会员等级编号是："+userAccountSafeInfo.getUgrade());
		}
		
 		return hashMap;
	}
	 
	/**
	 * 
	* @Title: copyRepaymentDetailByRepayMent 
	* @Description: TODO(批次还款  把本批次具体的还款计划信息 复制到批次详情记录里面) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public RepaymentDetail copyRepaymentDetailByRepayMent(RepayMent repayMent){
		if(repayMent == null){
			return null;
		}
		try{
 			RepaymentDetail repaymentDetail = new RepaymentDetail();
 			repaymentDetail.setRorderno(repayMent.getRorderno());     //还款流水号
			repaymentDetail.setRbatchno(repayMent.getRbatchno());     //还款批次号
			repaymentDetail.setOutaccountid(repayMent.getOutaccountid());     //借款用户ID
			repaymentDetail.setInaccountid(repayMent.getInaccountid());     //投资用户ID
			repaymentDetail.setProxyaccountid(repayMent.getProxyaccountid());     //代还款人ID
			repaymentDetail.setPmiscrecmanid(repayMent.getPmiscrecmanid());     //平台杂项收款人id
			repaymentDetail.setUtorderno(repayMent.getUtorderno());     //投标/债转订单号
			repaymentDetail.setTenderid(repayMent.getTenderid());     //标号ID
			repaymentDetail.setPeriods(repayMent.getPeriods());     //还款期数（第几期）
			repaymentDetail.setIsdarepay(repayMent.getIsdarepay());     //是否债转还款 0 否 1 是
			repaymentDetail.setRprincipalint(repayMent.getRprincipalint());     //该批次还款总金额（本息）
			repaymentDetail.setRptotalamount(repayMent.getRptotalamount());     //该批次还款总金额（不含利息）
			repaymentDetail.setRptotalint(repayMent.getRptotalint());     //该批次还款总利息
			repaymentDetail.setRamount(repayMent.getRamount());     //还款本金金额
			repaymentDetail.setRinterest(repayMent.getRinterest());     //还款本金利息
			repaymentDetail.setRvoucher(repayMent.getRvoucher());     //该批次还款类现金
			repaymentDetail.setRvoucherint(repayMent.getRvoucherint());     //该批次还款类现金的利息
			repaymentDetail.setRlvoucher(repayMent.getRlvoucher());     //该批次还款假现金
			repaymentDetail.setRlvoucherint(repayMent.getRlvoucherint());     //假现金的利息
			repaymentDetail.setRintfee(repayMent.getRintfee());     //该批次加息劵利息
			repaymentDetail.setRestprincipal(repayMent.getRestprincipal());     //剩余本金
			repaymentDetail.setRestvoucher(repayMent.getRestvoucher());     //剩余类现金
			repaymentDetail.setDisablevoucher(repayMent.getDisablevoucher());     //失效类现金
			repaymentDetail.setDisablevoucherint(repayMent.getDisablevoucherint());     //失效类现金利息
			repaymentDetail.setRestlvoucher(repayMent.getRestlvoucher());     //剩余假现金
			repaymentDetail.setDisablelvoucher(repayMent.getDisablelvoucher());     //失效假现金
			repaymentDetail.setRestocamount(repayMent.getRestocamount());     //剩余滞纳金
			repaymentDetail.setDisableocamount(repayMent.getDisableocamount());     //失效滞纳金
			repaymentDetail.setVrestocamount(repayMent.getVrestocamount());     //剩余类现金滞纳金
			repaymentDetail.setDisablevocamount(repayMent.getDisablevocamount());     //失效类现金滞纳金
			repaymentDetail.setRestintprofit(repayMent.getRestintprofit());     //剩余加息卷收益
			repaymentDetail.setDisableintprofit(repayMent.getDisableintprofit());     //失效加息卷收益
			repaymentDetail.setRestamountintprofit(repayMent.getRestamountintprofit());     //剩余本金产生的利息
			repaymentDetail.setRestvoucherintprofit(repayMent.getRestvoucherintprofit());     //剩余类现金产生的利息
			repaymentDetail.setRestlvoucherintprofit(repayMent.getRestlvoucherintprofit());     //剩余假现金产生的利息
			repaymentDetail.setTransferprincipal(repayMent.getTransferprincipal());     //已转让本金（不含增益）
			repaymentDetail.setAuthcode(repayMent.getAuthcode());     //投标申请授权码
			repaymentDetail.setIsproxyrepay(repayMent.getIsproxyrepay());     //是否代偿（1是，0否）
			repaymentDetail.setIsoverdue(repayMent.getIsoverdue());     //是否逾期（0否，1是）
			repaymentDetail.setIsahead(repayMent.getIsahead());     //是否提前（0否，1是
			repaymentDetail.setRtime(repayMent.getRtime());     //还款时间（计划表生成时间）
			repaymentDetail.setSubmittime(repayMent.getSubmittime());     //借款人提交还款时间
			repaymentDetail.setRprealtime(repayMent.getRprealtime());     //还款实际到账日期
			repaymentDetail.setIsaudit(repayMent.getIsaudit());     //是否审核 1是 0否
			repaymentDetail.setAddtime(repayMent.getAddtime());     //制表时间
			
			/**
			 * 及时还款  ===》更新为系统审核
			 * 冻结还款  ===》更新为管理审核 ，审核通过并提交银行成功 更改提交状态为1，更新提交银行时间  处理结果文件更新银行返回码和还款状态【具体还款计划和批次还款明细】
			 * 
			 * 自动还款 ===》 还款模式为系统 ，
			 * 借款人还款【管理员操作代偿还款】 ===》 还款模式为人工
			 * 
			 * 利息管理费  ===》写进文件前计算利息管理费并更新进【具体还款计划表和批次还款明细计划表】
			 * 
			 * 逾期滞纳金  ===》写进文件前计算逾期滞纳金并更新进【具体还款计划表和批次还款明细计划表】
			 * 
			 * cjm 20170706 
			 */
			repaymentDetail.setOverdueamount(repayMent.getOverdueamount());     //逾期滞纳金额
			repaymentDetail.setInterestexpense(repayMent.getInterestexpense());     //投标利息的管理费
			repaymentDetail.setRepaystatus(repayMent.getRepaystatus());     //还款状态(1待还款  2审核中 2待处理  4处理中 5还款失败 6已还款)
			repaymentDetail.setRmode(repayMent.getRmode());     //还款模式（0初始  1人工，2系统，3线下 ）
			repaymentDetail.setRetcode(repayMent.getRetcode());     //银行返回码
			repaymentDetail.setAuditman(repayMent.getAuditman());     //审核人
			repaymentDetail.setAudittime(repayMent.getAudittime());     //审核时间
//			repaymentDetail.setOperatetime(repayMent.getOperatetime());     //提交银行时间
			repaymentDetail.setIssubmit((short)0);     //是否提交（1是，0否）
			repaymentDetail.setRemark(repayMent.getRemark());     //备注
			return repaymentDetail;
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("copyRepaymentDetailByRepayMent,批次详情对象复制失败,复制对象RepayMent的还款流水号是："+repayMent.getRorderno());
			}
 			return null;
 		}
 	}
 
	
	
	/**
	 * 
	* @Title: updateRepayMentRepayStatusForWaitDeal 
	* @Description: TODO(文件写进成功后把具体的还款计划信息还款状态修改为待处理 ) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public boolean updateRepayMentRepayStatusForWaitDeal(List<RepayMent> repayMents){
 		if(repayMents.size() > 0){
 			if(logger.isDebugEnabled()){
 				logger.debug("调用文件写进成功后把具体的还款计划信息还款状态修改为待处理,还款批次号是："+repayMents.get(0).getRbatchno());
 			}
			for(RepayMent repayMent : repayMents){
				repayMent.setRepaystatus((short)3);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败,7审核失败) 
				repayMentServiceImpl.updateById(repayMent);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* @Title: updateRepayMentDetailRepayStatusForWaitDeal 
	* @Description: TODO(文件写进成功后把批次详情还款状态修改为待处理 ) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public boolean updateRepayMentDetailRepayStatusForWaitDeal(List<RepaymentDetail> repaymentDetails){
		if(repaymentDetails.size() > 0){
 			if(logger.isDebugEnabled()){
 				logger.debug("调用文件写进成功后把批次详情还款状态修改为待处理,还款批次号是："+repaymentDetails.get(0).getRbatchno());
 			}
			for(RepaymentDetail repaymentDetail : repaymentDetails){
				repaymentDetail.setRepaystatus((short)3);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败7审核失败) 
				repaymentDetailServiceI.updateById(repaymentDetail);
			}
			return true;
		}
		return false;
	}
	 
	
	/**
	 * 
	* @Title: updateRepayMentRepayStatusForDealing 
	* @Description: TODO(文件上传成功后把具体的还款计划信息还款状态修改为处理中) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public boolean updateRepayMentRepayStatusForDealing(List<RepayMent> repayMents){
		if(repayMents.size() > 0){
			if(logger.isDebugEnabled()){
 				logger.debug("调用文件上传成功后把具体的还款计划信息还款状态修改为处理中,还款批次号是："+repayMents.get(0).getRbatchno());
 			}
			for(RepayMent repayMent : repayMents){
				repayMent.setRepaystatus((short)4);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败7审核失败) 
				repayMent.setIssubmit((short)1);//是否提交银行  1是 0 否
				repayMent.setOperatetime(new Date());//提交银行时间
				repayMentServiceImpl.updateById(repayMent);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* @Title: updateRepayMentDetailRepayStatusForDealing 
	* @Description: TODO(文件上传成功后把批次详情还款计划信息还款状态修改为处理中) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public boolean updateRepayMentDetailRepayStatusForDealing(List<RepaymentDetail> repaymentDetails){
		if(repaymentDetails.size() > 0){
			if(logger.isDebugEnabled()){
 				logger.debug("调用文件上传成功后把批次详情还款计划信息还款状态修改为处理中,还款批次号是："+repaymentDetails.get(0).getRbatchno());
 			}
			for(RepaymentDetail repaymentDetail : repaymentDetails){
				repaymentDetail.setRepaystatus((short)4);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败7审核失败) 
				repaymentDetail.setIssubmit((short)1);//是否提交银行1是 0 否
				repaymentDetail.setOperatetime(new Date());//提交银行时间
				repaymentDetailServiceI.updateById(repaymentDetail);
 			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* @Title: updateRepayMentDetailRepayStatusForDealing 
	* @Description: TODO(批次还款  处理状态为取消和冻结成功,系统解冻处理) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> repayMentFrzStatusDeal(TenderItem tenderItem){
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put(RepayMent_Constant.RESULT, "tenderItem_null");
		hashMap.put(RepayMent_Constant.FALG, false);
		hashMap.put(RepayMent_Constant.MSG, "'tenderItem' 不能为空");
 		if(tenderItem != null && tenderItem.getTno() != null){
 			RepaymentFrz repaymentFrz2 = new RepaymentFrz();
 			repaymentFrz2.setProduct(tenderItem.getTno());//标号
 			repaymentFrz2.setFailandwaitstatus((short)1);//状态为取消或冻结成功
 			List<RepaymentFrz> repaymentFrzs = repaymentFrzServiceI.findRepaymentFrzs(repaymentFrz2);
 			
 			if(!(repaymentFrzs.size() > 0)){
 				hashMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
 				hashMap.put(RepayMent_Constant.FALG, true);
 				hashMap.put(RepayMent_Constant.MSG, "处理成功!");
 				return hashMap;
 			} 
 			
  			boolean falg2 = false;
 			for(RepaymentFrz repayMentfrz : repaymentFrzs){
 				//解冻还款
				Map<String,Object> resMap = HSRepayMentFreezeController.repayMentunFreeze(repayMentfrz.getCardnbr(), repayMentfrz.getAmount().toString(), repayMentfrz.getProduct(), StringUtil.getNo(), repayMentfrz.getSerino());
				boolean falg = (boolean) resMap.get(RepayMent_Constant.FALG);
				if(!falg){//解冻失败
 					try{
	 					   if(resMap.get(RepayMent_Constant.RESULT).equals("retcodeFlag")){
	 						  if(resMap.get(RepayMent_Constant.RETCODE).equals("CA110112")){//订单未存在
	 							 if(StringUtil.isNotEmpty(repayMentfrz.getBatchno())){
	 								   Map<String,Object> maps = new HashMap<>();
	 								   maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
	 								   maps.put("rbatchno",  repayMentfrz.getBatchno());//还款批次号
	 								   List<RepayMent> repayMentS = repayMentServiceImpl.findListRepayMentByConditions(maps);
	 								   if(repayMentS.size() > 0){
	 									   for(RepayMent repayMent2 : repayMentS){
	 										   if(repayMent2.getRepaystatus().equals((short)2)){
	 											   repayMent2.setRepaystatus((short)1);
	 											  repayMentServiceImpl.updateById(repayMent2);
	 										   }
	 									   }
	 								   }
	 				 				}
	 				 				   
	 				 			   //解冻成功后进行更新
	 							 	repayMentfrz.setStatus((short)8);//0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8解冻成功 
	 							 	repayMentfrz.setIsmanblending((short)1);//是否人工勾兑
	 							 	repayMentfrz.setManbtime(new Date());//人工勾兑时间
	 							    repaymentFrzServiceI.updateById(repayMentfrz);
	 							    
									hashMap.put(RepayMent_Constant.FALG, true);
									hashMap.put(RepayMent_Constant.MSG, "解冻成功！");
									hashMap.put(RepayMent_Constant.RESULT, "success");
	 							   return hashMap;
	 						  }
						   }
					   }catch(Exception e){
						   e.printStackTrace();
					   }
					
					hashMap.put(RepayMent_Constant.RESULT, "unFreeze_error");
					hashMap.put(RepayMent_Constant.FALG, false);
 					hashMap.put(RepayMent_Constant.MSG, "解冻失败！");
					if(logger.isDebugEnabled()){
						logger.debug("处理批次还款状态为[取消]或[冻结成功],处理失败！原因："+resMap.get(RepayMent_Constant.MSG).toString()+",标号是："+repayMentfrz.getProduct()+",冻结申请流水号是："+repayMentfrz.getSerino());
					}
					falg2 = false;
					break;
				}
				
				Map<String,Object> maps = new HashMap<>();
				maps.put("rbatchno", repayMentfrz.getBatchno());//批次号
 				maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
	 			List<RepayMent> repayMents = repayMentServiceImpl.findListRepayMentByConditions(maps);
	 			if(repayMents.size() > 0){
	 				for(RepayMent repayMent :repayMents){
  						if(repayMent.getRepaystatus().equals((short)2)){
   							repayMent.setRepaystatus((short)1);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败,7审核失败)
  							repayMentServiceImpl.updateById(repayMent);
  						}
					}
	 			}
	 			
				repayMentfrz.setStatus((short)8);//0初始 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8解冻成功
				repayMentfrz.setSysbtime(new Date());//系统勾兑时间
				repayMentfrz.setIsblending((short)1);//是否系统勾兑
				repaymentFrzServiceI.updateById(repayMentfrz);
				if(!falg2){
 					falg2 = true;
				}
				
  			}
  			
 			if(falg2){
 				hashMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
 				hashMap.put(RepayMent_Constant.FALG, true);
 				hashMap.put(RepayMent_Constant.MSG, "处理成功！");
 			}
  		}
		
		return hashMap;
 	}
	
	
	//根据会员等级和标类型找到对应的利息管理费设置
	protected InterestExpense findInterestExpenseByUgradeAndTtype(Short ugrade,Short tpro){
		List<InterestExpense> interestExpenses = getInterestExpensesByUGrade();
		InterestExpense interestExpense = null;
		if(interestExpenses.size() > 0){
			for(InterestExpense expense : interestExpenses){
				if(expense.getTtype() == null || expense.getUgrade() == null){
					continue;
				}
				
				/**
				 * 注：
				 * 标的类型编号时从1开始的
				 * 会员等级的编号时从0开始的【初始会员是0】
 				 */
				
				List<Integer> list = StringUtil.parsStringToList(expense.getTtype());
				List<Integer> list2 = StringUtil.pars(expense.getUgrade());
				for(Integer integer : list){//标类型比较
					boolean beakFlag = false;
 					if(integer.equals(tpro.intValue())){
						 for(Integer integer2 : list2){//会员等级比较
							 if(integer2.equals(ugrade.intValue())){
								 interestExpense = expense;
								 beakFlag = true;
								 break;
							 }
						 }
					}
 					
 					if(beakFlag){
 						break;
 					}
				}
  			}
		}
		return interestExpense;
	}
	
	//添加利息管理费记录
	protected void insertInterestExpenseRecord(RepayMent repayMent,InterestExpense interestExpense,Short ietype){
		
 		InterestExpenseRecord interestExpenseRecord1 = new InterestExpenseRecord();
		interestExpenseRecord1.setRorderno(repayMent.getRorderno());//还款流水号
		interestExpenseRecord1.setBatchno(repayMent.getRbatchno());//批次号
		InterestExpenseRecord interestExpenseRecord2 = interestExpenseRecordServiceI.findInterestExpenseRecord(interestExpenseRecord1);
		if(interestExpenseRecord2 != null){
			return ;
		}		
		
		InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
		interestExpenseRecord.setIeorderno(StringUtil.getNo());//利息管理费流水号
		interestExpenseRecord.setRorderno(repayMent.getRorderno()); //还款流水号
		interestExpenseRecord.setBatchno(repayMent.getRbatchno());//还款批次号
		interestExpenseRecord.setIeid(interestExpense.getId()); //利息管理费设置表Id
		interestExpenseRecord.setTenderid(repayMent.getTenderid());//标号ID
//		interestExpenseRecord.setIntexpmanid(intexpmanid);//利息管理费收款人ID
		interestExpenseRecord.setInvestorid(repayMent.getInaccountid());//投资人ID
		interestExpenseRecord.setIntexpfee(repayMent.getInterestexpense());//利息管理费
		interestExpenseRecord.setIetype(ietype);//利息管理费收取类型（1百分比，2最低，3最高）
		interestExpenseRecord.setIsdeal((short)0); //是否处理（0否，1是,2处理中）
		interestExpenseRecord.setMadetime(new Date()); //创建时间
		interestExpenseRecord.setPlanstatus((short)1);//利息管理费收取计划状态 1有效 2 无效
		interestExpenseRecord.setIeproperty(repayMent.getIsdarepay());//管理费属性 0正常投标1 债权转让后
		interestExpenseRecord.setIsaudit((short)1); //是否审核 1是 0否
		if(interestExpense.getIsaudit() != null && interestExpense.getIsaudit().equals((short)0)){
 			interestExpenseRecord.setIsaudit((short)0); //是否审核 1是 0否
			interestExpenseRecord.setAuditman("系统");//审核人
			interestExpenseRecord.setAudittime(new Date()); //审核时间
		}
 		
		interestExpenseRecordServiceI.insertSelective(interestExpenseRecord);
		
	}
	
	//找到计算方式是用户等级的利息管理费设置字段
	protected List<InterestExpense> getInterestExpensesByUGrade(){
		InterestExpense condition = new InterestExpense();
		condition.setGfitype((short)1);////计算方式（1根据用户等级，2根据标的风险等级
		List<InterestExpense>  interestExpenses = interestExpenseServiceI.selectByCondition(condition);
		if(interestExpenses.size() > 0){
 			return interestExpenses;
		}
		return null;
	}
	
 	//找到计算方式是标的风险等级的利息管理费设置字段
	protected List<InterestExpense> getInterestExpensesByRisk(){
		InterestExpense condition = new InterestExpense();
		condition.setGfitype((short)2);//计算方式（1根据用户等级，2根据标的风险等级
		List<InterestExpense>  interestExpenses = interestExpenseServiceI.selectByCondition(condition);
		if(interestExpenses.size() > 0){
 			return interestExpenses;
		}
		return null;
	}
}
