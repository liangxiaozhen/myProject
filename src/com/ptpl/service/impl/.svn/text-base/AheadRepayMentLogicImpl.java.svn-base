package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ptpl.controller.huifu.OraceTest;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.AheadRepay;
import com.ptpl.model.AheadRepayAward;
import com.ptpl.model.AheadRepayAwardRecord;
import com.ptpl.model.AheadRepayMentCalculatedParameter;
import com.ptpl.model.AheadRepayOneRecord;
import com.ptpl.model.AheadRepayPlatform;
import com.ptpl.model.AheadRepayPlatformRecord;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.AheadRepayAwardRecordServiceI;
import com.ptpl.service.AheadRepayAwardServiceI;
import com.ptpl.service.AheadRepayMentLogicI;
import com.ptpl.service.AheadRepayOneRecordServiceI;
import com.ptpl.service.AheadRepayPlatformRecordServiceI;
import com.ptpl.service.AheadRepayPlatformServiceI;
import com.ptpl.service.AheadRepayServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.DateUtil;
import com.ptpl.web.util.DividedPaymentsComparator;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: AheadRepayMentLogicImpl 
* @Package com.ptpl.service.impl 
* @Description: TODO(提前还款 逻辑处理实现类) 
* @author cjm
* @date 2016年12月24日 下午2:38:49 
* @version V1.0
 */
public class AheadRepayMentLogicImpl implements AheadRepayMentLogicI{

	private static DecimalFormat df = new DecimalFormat("###############################0.00");
	
 	@Autowired
	private DividedPaymentsServiceI dividedPaymentsServiceI;//借款人还款计划Service
	
	@Autowired
	private RepayMentServiceI repayMentServiceI;//投资人还款计划Service
	
	@Autowired
	private TenderItemServiceI tenderItemServiceI;//标的信息Service
	
	@Autowired
	private AheadRepayServiceI aheadRepayServiceI;//提前还款个人利息奖励设置Service
	
	@Autowired
	private AheadRepayAwardServiceI aheadRepayAwardServiceI;//提前还款个人奖品奖励设置Service
	
	@Autowired
	private AheadRepayPlatformServiceI  aheadRepayPlatformServiceI;//借款人奖励平台Service
	
	@Autowired
	private AheadRepayOneRecordServiceI  aheadRepayOneRecordServiceI;//标的提前还款奖励单个投资人记录 Service
	
	@Autowired
	private AheadRepayPlatformRecordServiceI  aheadRepayPlatformRecordServiceI;//标的提前还款奖励平台记录Service
	
	@Autowired
	private AheadRepayAwardRecordServiceI  aheadRepayAwardRecordServiceI;//标的提前还款奖品补偿记录Service
	
	@Autowired
	private AheadRealRepaymentServiceI aheadRealRepaymentServiceI;//提前实际还款记录Service
	
	@Autowired
	private InterestExpenseServiceI interestExpenseServiceI;//利息管理费 service
	
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;//用户安全账号信息
	/**
	 * 
	* @Title: hasInvestorAheadRepayMent 
	* @Description: TODO(提前还款  校验标的还款计划是否跳期提前还款) 
	* @param @param TenderID  标的ID
 	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public boolean hasDividedPaymentsAheadRepayMent(DividedPayments dividedPayment) {
		boolean flag = true;
		Assert.notNull(dividedPayment, "'dividedPayment 标的借款人还款计划不能为null'");
		BigDecimal TenderID = dividedPayment.getTenderid();
		Integer     periods = dividedPayment.getPeriods();
		Assert.notNull(TenderID, "'TenderID 标的借款人还款计划标的ID不能为null'");
		Assert.notNull(periods, "'periods  标的借款人还款计划的还款期数不能为null'");
 		DividedPayments dividedPayment2 = new DividedPayments();
		dividedPayment2.setTenderid(TenderID);
		List<DividedPayments>  dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment2);
		if(dividedPayments.size() < 0){
			flag = false;
 			return flag;
		}
		Collections.sort(dividedPayments, new DividedPaymentsComparator());//排序
		for(DividedPayments payments : dividedPayments){
			 if(payments.getPeriods() < periods){
				 if(payments.getIscomplete().equals((short)1) || payments.getIscomplete().equals((short)3)){
					 continue;
				 }
				 if(payments.getIscomplete().equals((short)0)
						 || payments.getIsoverdue().equals((short)1)){//Iscomplete是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0) Isoverdue 是否逾期1已经逾期0没有逾期(标的截标时生成时默认0)
						flag = false;
						break;
				 }
			 }
		}
 		return flag;
	}
	/**
	 * 
	* @Title: hasDividedPaymentsAheadMultiperiod 
	* @Description: TODO(提前还款  校验标的还款计划是否隔期还款) 
	* @param @param TenderID  标的ID
 	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public boolean hasDividedPaymentsAheadMultiperiod(List<DividedPayments> dividedPayments) {
		boolean flag = true;
		if(!(dividedPayments.size() > 1)){
			flag = false;
			return flag;
		}
		
		for(int i = 0 ; i < dividedPayments.size()-1;i++){
			if((dividedPayments.get(i).getPeriods()+1) != (dividedPayments.get(i+1).getPeriods())){
				flag = false;
				break;
			}
		}
  		return flag;
	}
	/**
	 * 
	* @Title: hasInvestorAheadRepayMent 
	* @Description: TODO(提前还款  校验单个投资人是否跳期提前还款) 
	* @param @param TenderID  标的ID
 	* @param @return  参数说明 返回false  就是跳期了
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public boolean hasInvestorAheadRepayMent(RepayMent repayMent) {
		boolean flag = true;
		Assert.notNull(repayMent, "'repayMent' 投资人提前还款当期计划不能为null");
		Map<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("inaccountid", repayMent.getInaccountid());//投资用户ID
		hashMap.put("tenderid", repayMent.getTenderid());//标号ID
		hashMap.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
		hashMap.put("utorderno", repayMent.getUtorderno());//投标订单号 
  		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap);
 		if(repayMents.size() < 0){
 			flag = false;
 			return flag;
 		}
 		
 		for(RepayMent repayMent2 : repayMents){
 			if(repayMent2.getPeriods() < repayMent.getPeriods()){
 				if(!repayMent2.getRepaystatus().equals((short)5)){//1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败，7审核失败
   						flag = false;
 						break;
  				}
 			}
 		}
  		return flag;
	}
	/**
	 * 
	* @Title: hasInvestorAheadRepayMent 
	* @Description: TODO(提前还款  计算投资人当期持有利息(本金利息)) 
	* @param @param TenderID  标的ID
 	* @param @return  参数说明   repayMent  当期还款计划    date 日期
	* @return String  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentInterest(RepayMent repayMent) {
		Assert.notNull(repayMent, "'repayMent' 投资人当期还款计划不能为null");
 		Double holdRepayMentInterest = 0.00;
		if(repayMent.getRinterest() > 0){
			BigDecimal HoldDay = HoldRepayMentDay(repayMent);//投资人(K =（当期持有时间 / 当期总天数）)
			if(HoldDay.doubleValue() > 0){
    				holdRepayMentInterest  = Double.valueOf(df.format(new BigDecimal(repayMent.getRinterest().toString()).multiply(HoldDay)));//投资人当期持有的本金利息
 			}
		}
 		return holdRepayMentInterest;
	}

	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(类现金利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRvoucherint(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 投资人当期还款计划不能为null");
		Double holdRepayMentRvoucherint  =  0.00;//投资人当期持有的类现金利息
		if(repayMent.getRvoucherint() > 0){
			BigDecimal HoldDay = HoldRepayMentDay(repayMent);//投资人(K =（当期持有时间 / 当期总天数）)
			if(HoldDay.doubleValue() > 0){
 				holdRepayMentRvoucherint  = Double.valueOf(df.format(new BigDecimal(repayMent.getRvoucherint().toString()).multiply(HoldDay)))   ;//投资人当期持有的类现金利息
 			}
		}
 		return holdRepayMentRvoucherint;
	}
	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentDisablevoucherint 
	* @Description: TODO(提前还款 计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 投资人当期还款计划不能为null");
		Double holdRepayMentRvoucherint  =  0.00;//投资人当期持有的类现金利息
		if(repayMent.getRvoucherint() > 0){
			BigDecimal HoldDay = HoldRepayMentDay(repayMent);//投资人(K =（当期持有时间 / 当期总天数）)
			if(HoldDay.doubleValue() > 0){
 				holdRepayMentRvoucherint  = Double.valueOf(df.format(new BigDecimal(repayMent.getDisablevoucherint().toString()).multiply(HoldDay)))   ;//投资人当期持有的作废的类现金利息
 			}
		}
 		return holdRepayMentRvoucherint;
	}
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(假现金利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRlvoucherint(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 投资人当期还款计划不能为null");
		Double holdRepayMentRlvoucherint  =  0.00   ;//投资人当期持有的假现金利息
		if(repayMent.getRlvoucherint() > 0){
			BigDecimal HoldDay = HoldRepayMentDay(repayMent);//投资人(K =（当期持有时间 / 当期总天数）)
			if(HoldDay.doubleValue() > 0){
 				holdRepayMentRlvoucherint  = Double.valueOf(df.format(new BigDecimal(repayMent.getRlvoucherint().toString()).multiply(HoldDay)))   ;//投资人当期持有的假现金利息
 			}
		}
 		return holdRepayMentRlvoucherint;
	}
	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(加息券利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRintfee(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent' 投资人当期还款计划不能为null");
		Double holdRepayMentRintfee  =  0.00   ;//投资人当期持有的加息券利息
		if(repayMent.getRintfee() > 0){
			BigDecimal HoldDay = HoldRepayMentDay(repayMent);//投资人(K =（当期持有时间 / 当期总天数）)
			if(HoldDay.doubleValue() > 0){
  				holdRepayMentRintfee  = Double.valueOf(df.format(new BigDecimal(repayMent.getRintfee().toString()).multiply(HoldDay)))   ;//投资人当期持有的加息券利息
 			}
		}
 		return holdRepayMentRintfee;
	}
	
	
	/**
	  * 
	  * @Title: HoldRepayMentDay 
	  * @Description: TODO(计算投资人 (K=持有时间/当前总天数)) 
	  * @param @param repayMent 投资人当期还款计划
	  * @param @param date  借款人提前还款日期
  	  * @param @return  参数说明 
	  * @return Integer    返回类型 
	  * @author cjm
	  * @throws
	   */
	 public BigDecimal HoldRepayMentDay(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 投资人当期还款计划不能为null'");
	 	Integer HoldDay 		= 0 ;//投资人当期持有时间
	 	Integer ModthDayCount 	= 0;//当期总天数
	 	Date date = new Date();//提前还款当天日期
	 	if(repayMent.getPeriods() == 1){//第一期
			TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
			Assert.notNull(tenderItem, "'repayMent 标的对象不能为null'");
 			Date valuedate 		= StringUtil.getValuedate(tenderItem.getValuedate(),tenderItem.getValuepoint(),tenderItem.getValuerule());//起息日
 			Assert.notNull(valuedate, "'valuedate 标的起息日不能为null'");
 			HoldDay 			=  (int) DateUtil.getDateDifference(valuedate, date);//投资人当期持有时间
 			ModthDayCount 		=  (int) (HoldDay + DateUtil.getDateDifference(date, repayMent.getRtime()));//当期总天数
   		}else{
 			Map<String ,Object> maps = new HashMap<String,Object>();
 			maps.put("tenderid", repayMent.getTenderid());//标号ID
 			maps.put("utorderno",repayMent.getUtorderno());//投标订单号
 			maps.put("periods",  repayMent.getPeriods() - 1);//还款期数（第几期）
 			maps.put("planstatus",(short)1);//还款计划状态(1有效，2无效)
 			RepayMent repayMent2 = repayMentServiceI.findRepayMentByConditions(maps); 
 			Assert.notNull(repayMent2, "'repayMent2 投资人前一期还款计划不能为null'");
 			Date valuedate = repayMent2.getRtime();//起息日
 			HoldDay =  (int) DateUtil.getDateDifference(valuedate, date);//投资人当期持有时间
 			ModthDayCount 		=  (int) (HoldDay + DateUtil.getDateDifference(date, repayMent.getRtime()));//当期总天数
  		} 
	 	
	 	BigDecimal K = new BigDecimal(HoldDay.toString())
	 			.divide(new BigDecimal(ModthDayCount.toString()),10,BigDecimal.ROUND_DOWN);//持有时间/当前总天数
	 	return K;
	 }
	 
	 /**
	  * 
	 * @Title: penaltyRamount 
	 * @Description: TODO(计算 借款人本金利息罚金(惩罚借款人)) 
	 * @param @param tenderid  标的ID 
	 * @param HoldInterestCount 欠收利息
	 * @param @return  参数说明 
	 * @return double    返回类型 
	 * @author cjm
	 * @throws
	  */
 	@Override
	public AheadRepay findAheadRepayByHoldInterestCount(BigDecimal tenderid,Double HoldInterestCount) {
  		List<AheadRepay> aheadRepays =  aheadRepayServiceI.selectAhpayBytid(tenderid);
 		AheadRepay aheadRepay = null;
  		if(aheadRepays.size() > 0){
 			for(AheadRepay aheadRepay2 : aheadRepays){
 				Double minNoReceiveInt = aheadRepay2.getMinnoreceiveint();//单个投资人累计本金欠收最小利息
 				Double maxNoReceiveInt = aheadRepay2.getMaxnoreceiveint();//单个投资人累计本金欠收最高利息
 				if(minNoReceiveInt <= HoldInterestCount 
 						|| HoldInterestCount <= maxNoReceiveInt){
 					aheadRepay = aheadRepay2;
 					break;
 				}
  			}
   		}
 		return aheadRepay;
	}
 	
 	/**
	  * 
	 * @Title: penaltyRamount 
	 * @Description: TODO(取到符合 投资人增益利息欠收利息区间段的 提前还款个人奖品奖励设置表对象) 
	 * @param @param tenderid  标的ID
	 * @param @param HoldInterestCount   投资人欠收增益利息总和
	 * @param @return  参数说明 
	 * @return AheadRepayAward    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public AheadRepayAward findAheadRepayAwardByHoldInterestCount(BigDecimal tenderid, Double HoldInterestCount) {
 		AheadRepayAward aheadRepayAward = null;
		List<AheadRepayAward> aheadRepayAwards = aheadRepayAwardServiceI.selectahpayAwardBytid(tenderid);
		if(aheadRepayAwards.size() > 0){
			for(AheadRepayAward aheadRepayAward2 : aheadRepayAwards){
 				Double Minplusnoreceiveint = aheadRepayAward2.getMinplusnoreceiveint();//单个投资人累计增益欠收最小利息
 				Double Maxplusnoreceiveint = aheadRepayAward2.getMaxplusnoreceiveint();//单个投资人累计增益欠收最高利息
 				if(Minplusnoreceiveint <= HoldInterestCount 
 						|| HoldInterestCount <= Maxplusnoreceiveint){
 					aheadRepayAward = aheadRepayAward2;
 					break;
 				}
  			}
		}
 		return aheadRepayAward;
	}
	
	
	 /**
	  * 
	 * @Title: findAheadRepayPlatformByHoldInterestCount 
	 * @Description: TODO(取到符合 全部投资人欠收利息区间段的 提前还款奖励平台设置对象（注意是全部投资人的欠收利息总和，不是单个投资人。）) 
	 * @param @param tenderid  标的ID
	 * @param @param HoldInterestCount   全部投资人欠收利息总和
	 * @param @return  参数说明 
	 * @return AheadRepayPlatform    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public AheadRepayPlatform findAheadRepayPlatformByHoldInterestCount(BigDecimal tenderid, Double HoldInterestCount) {
		AheadRepayPlatform aheadRepayPlatform = null;
		List<AheadRepayPlatform> aheadRepayPlatforms = aheadRepayPlatformServiceI.selectPayplatBytid(tenderid);
		if(aheadRepayPlatforms.size() > 0){
			for(AheadRepayPlatform aheadRepayPlatform2 : aheadRepayPlatforms){
 				Double Minallnoreceiveint = aheadRepayPlatform2.getMinallnoreceiveint();//奖励平台投资人总欠收最小利息
 				Double Maxallnoreceiveint = aheadRepayPlatform2.getMaxallnoreceiveint();//奖励平台投资人总欠收最高利息
 				if(Minallnoreceiveint <= HoldInterestCount 
 						|| HoldInterestCount <= Maxallnoreceiveint){
 					aheadRepayPlatform = aheadRepayPlatform2;
 					break;
 				}
  			}
		}
 		return aheadRepayPlatform;
	}
	/**
	 * 
	 * @Title: insertAheadRepayRecord 
	 * @Description: TODO(添加  标的提前还款奖励单个投资人记录) 
	 * @param @param AheadRepay
	 * @param @param HoldInterestCount  参数说明 
	 * @param @param bacthNo  参数说明 
 	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public void insertAheadRepayRecord(AheadRepay aheadRepay, Double HoldInterestCount ,String bacthNo,RepayMent repayMent) {
		if(aheadRepay != null){//找到符合补偿的区间段
			AheadRepayOneRecord aheadRepayOneRecord = new AheadRepayOneRecord();
			aheadRepayOneRecord.setTenderid(repayMent.getTenderid());//标号ID
			aheadRepayOneRecord.setArorderno(StringUtil.getNo());//提前还款奖励流水号
			aheadRepayOneRecord.setArid(aheadRepay.getId());//标的提前还款奖励设置表Id
			aheadRepayOneRecord.setRorderno(repayMent.getRorderno());//还款流水号
			aheadRepayOneRecord.setArbatchno(bacthNo);//提前还款批次号
			aheadRepayOneRecord.setPrinpoorint(HoldInterestCount);//单个投资人本金欠收利息
			aheadRepayOneRecord.setIsgrant((short)0);//是否处理（0否，1是)
			aheadRepayOneRecord.setIsaudit((short)0);//是否审核 0否 1是
			aheadRepayOneRecord.setMadetime(new Date());//创建时间
			aheadRepayOneRecord.setBmanid(repayMent.getOutaccountid());//借款用户ID
			aheadRepayOneRecord.setInvestorid(repayMent.getInaccountid());//投资人ID
  			Short AwardType 		= aheadRepay.getAwardtype();//奖励方式（1惩罚借款人，2平台奖励，3 1+2）
			Double penaltyRamount  	= 0.00;
			Short penaltytype		= 1;//罚金奖励方式（1定额，2百分比，3最高）
			if(AwardType != null && AwardType.equals((short)1)
					|| AwardType.equals((short)3)){//惩罚借款人 或惩罚借款人和平台奖励
				if(aheadRepay.getPenaltyrate() != null && aheadRepay.getPenaltyrate() > 0){//百分比
					penaltyRamount =  Double.valueOf(df.format(HoldInterestCount * aheadRepay.getPenaltyrate() + 0.00)) ;
					penaltytype = 2;
					if(aheadRepay.getMaxpenalty() != null 
							&& penaltyRamount >= aheadRepay.getMaxpenalty()){//借款人罚金最大值
						penaltyRamount = aheadRepay.getMaxpenalty();
						penaltytype = 3;
					}
 				}else{//定额
					penaltyRamount = aheadRepay.getPenaltyquota() == null ? 0.00 : aheadRepay.getPenaltyquota();
  				}
				aheadRepayOneRecord.setBpenalty(penaltyRamount); //PRIN借款人罚金
				aheadRepayOneRecord.setPenaltytype(penaltytype); //罚金奖励方式（1定额，2百分比，3最高）
			}
			
			if(aheadRepay.getPawardname() != null){
				aheadRepayOneRecord.setPawardname(aheadRepay.getPawardname()); /*平台奖励奖品*/
			}
			
			if(aheadRepay.getPawardno() != null){
				aheadRepayOneRecord.setPawardno(aheadRepay.getPawardno());/*平台奖励奖品编号*/
			}
			
			if(aheadRepay.getPawardcount() != null){
 				aheadRepayOneRecord.setPawardcount(aheadRepay.getPawardcount());/*平台奖励奖品数*/
			}
 			aheadRepayOneRecordServiceI.insertSelective(aheadRepayOneRecord);
 		}
	}
	/**
	  * 
	 * @Title: insertAheadRepayAwardRecord 
	 * @Description: TODO(添加 标的提前还款奖品补偿记录) 
	 * @param @param aheadRepayAward
	 * @param @param HoldInterestCount  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public void insertAheadRepayAwardRecord(AheadRepayAward aheadRepayAward, Double HoldInterestCount,String bacthNo,RepayMent repayMent) {
		if(aheadRepayAward != null 
				&& repayMent != null
				&& repayMent.getTenderid() != null
				&& repayMent.getTenderid() != null){//个人奖品奖励设置
			AheadRepayAwardRecord aheadRepayAwardRecord = new AheadRepayAwardRecord();
			aheadRepayAwardRecord.setTenderid(repayMent.getTenderid()); ;//标号ID
			aheadRepayAwardRecord.setInvestorid(repayMent.getInaccountid()); ;//投资人ID
			aheadRepayAwardRecord.setArid(aheadRepayAward.getId()); ;//标的提前还款奖励设置表Id
			aheadRepayAwardRecord.setArorderno(StringUtil.getNo()); ;//提前还款奖励流水号
			aheadRepayAwardRecord.setRorderno(repayMent.getRorderno()); ;//还款流水号
			aheadRepayAwardRecord.setArbatchno(bacthNo);//提前还款批次号
 			aheadRepayAwardRecord.setPluspoorint(HoldInterestCount); ;//单个投资人增益总欠收利息
 			aheadRepayAwardRecord.setIsgrant((short)0); ;//是否发放(0否，1是)
 			aheadRepayAwardRecord.setIsaudit((short)0); ;//是否审核(0否，1是)
 			aheadRepayAwardRecord.setMadetime(new Date()); ;//创建时间
 			
 			Double aheadRepayAwardRamount = 0.00;//平台罚金
			String  plusPAwardName 		= "";//平台奖励名称
			String  plusPAwardNo   		= "";//平台奖励编号
			Integer plusPAwardCount		= 0 ;// 奖品份数
 			Short plusAwardType = aheadRepayAward.getPlusawardtype();//增益奖励方式（1平台罚金，2平台奖励，3 1+2）
			if(plusAwardType != null && plusAwardType.equals((short)1)){//1平台罚金
				 if(aheadRepayAward.getPluspenaltyrate() != null 
						 &&  aheadRepayAward.getPluspenaltyrate() > 0){//欠收增益利息百分比
					aheadRepayAwardRamount = Double.valueOf(df.format(HoldInterestCount * aheadRepayAward.getPluspenaltyrate() + 0.00));
					if(aheadRepayAward.getPlusmaxpenalty() != null 
							&& aheadRepayAward.getPlusmaxpenalty() > 0
							&& aheadRepayAwardRamount >= aheadRepayAward.getPlusmaxpenalty()){
						aheadRepayAwardRamount = aheadRepayAward.getPlusmaxpenalty();
					}
				 }else if(aheadRepayAward.getPluspenaltyquota() != null 
						 && aheadRepayAward.getPluspenaltyquota() > 0) {//定额
					aheadRepayAwardRamount = Double.valueOf(df.format(aheadRepayAward.getPluspenaltyquota() + 0.00));
				 }
			}else if(plusAwardType != null && plusAwardType.equals((short)2)){//2平台奖励
				   plusPAwardName 		= aheadRepayAward.getPluspawardname();//平台奖励名称
				   plusPAwardNo   		= aheadRepayAward.getPluspawardno();//平台奖励编号
//				   plusPAwardCount		= aheadRepayAward.getplusPAwardCount // 奖品份数
				   plusPAwardCount		= 1;
 			}else{//3 1+2 Integer
 				if(aheadRepayAward.getPluspenaltyrate() != null 
						 &&  aheadRepayAward.getPluspenaltyrate() > 0){//欠收增益利息百分比
					aheadRepayAwardRamount = Double.valueOf(df.format(HoldInterestCount * aheadRepayAward.getPluspenaltyrate() + 0.00));
					if(aheadRepayAward.getPlusmaxpenalty() != null 
							&& aheadRepayAward.getPlusmaxpenalty() > 0
							&& aheadRepayAwardRamount >= aheadRepayAward.getPlusmaxpenalty()){
						aheadRepayAwardRamount = aheadRepayAward.getPlusmaxpenalty();
					}
				 }else if(aheadRepayAward.getPluspenaltyquota() != null 
						 && aheadRepayAward.getPluspenaltyquota() > 0) {//定额
					aheadRepayAwardRamount = Double.valueOf(df.format(aheadRepayAward.getPluspenaltyquota() + 0.00));
				 }
 				 plusPAwardName 		= aheadRepayAward.getPluspawardname();//平台奖励名称
			     plusPAwardNo   		= aheadRepayAward.getPluspawardno();//平台奖励编号
//				 plusPAwardCount		= aheadRepayAward.getplusPAwardCount // 奖品份数
			     plusPAwardCount		= 1;
			}
			
 			aheadRepayAwardRecord.setPlusppenalty(aheadRepayAwardRamount); ;//PLUS平台罚金
 			if(StringUtil.isNotEmpty(plusPAwardName)){
  				aheadRepayAwardRecord.setPluspaward(plusPAwardName); ;//PLUS平台奖励奖品
 			}
 			if(StringUtil.isNotEmpty(plusPAwardNo)){
  				aheadRepayAwardRecord.setPluspawardno(plusPAwardNo); ;//PLUS平台奖励奖品编号
 			}
  			aheadRepayAwardRecord.setPluspawardcount(plusPAwardCount); ;//PLUS平台奖励奖品数
 			aheadRepayAwardRecordServiceI.insertSelective(aheadRepayAwardRecord);
		}
	}
	/**
	  * 
	 * @Title: insertAheadRepayPlatformRecord 
	 * @Description: TODO(添加 标的提前还款奖励平台记录) 
	 * @param @param AheadRepayPlatform
	 * @param @param HoldInterestCount  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public void insertAheadRepayPlatformRecord(AheadRepayPlatform aheadRepayPlatform, Double HoldInterestCount,
			String bacthNo,TenderItem tenderItem,BigDecimal outaccountid) {
		if(aheadRepayPlatform != null
				&& tenderItem != null){//找到符合补偿的区间段
			AheadRepayPlatformRecord aheadRepayPlatformRecord = new AheadRepayPlatformRecord();
			aheadRepayPlatformRecord.setTenderid(tenderItem.getId());  //标号ID
  			aheadRepayPlatformRecord.setArorderno(StringUtil.getNo());  //提前还款奖励流水号
			aheadRepayPlatformRecord.setArid(aheadRepayPlatform.getId());  //标的提前还款奖励设置表Id
			aheadRepayPlatformRecord.setRorderno(bacthNo);  //还款流水号
			if(tenderItem.getPmiscrecman() != null){
				aheadRepayPlatformRecord.setPmiscrecman(tenderItem.getPmiscrecman());//平台杂项收款人
			}
			aheadRepayPlatformRecord.setBmanid(outaccountid);//借款人ID
			if(tenderItem.getAheadcompensatorymanid() != null){
				aheadRepayPlatformRecord.setProxyaccountid(tenderItem.getAheadcompensatorymanid());  //代还款人ID
			}
			aheadRepayPlatformRecord.setAllnorecieveint(HoldInterestCount);  //投资人总欠收利息
			aheadRepayPlatformRecord.setIsdeal((short)0);  //是否处理(0否，1是)
			aheadRepayPlatformRecord.setIsaudit((short)0);  //是否审核(0否，1是)
			aheadRepayPlatformRecord.setIsblending((short)0);  //是否系统勾兑（0未勾兑，1已勾兑）
			aheadRepayPlatformRecord.setIsmanblending((short)0);  //是否人工勾兑（0未勾兑，1已勾兑）
 			aheadRepayPlatformRecord.setPaycompany(StringUtil.BANKNAME);  //托管通道公司（汇付天下）
			aheadRepayPlatformRecord.setMadetime(new Date());  //创建时间
 			
			Double platformPenaltyRamount = 0.00;//平台罚金
				if(aheadRepayPlatform.getAwardplatrate() != null 
						&& aheadRepayPlatform.getAwardplatrate() > 0){//百分比
					platformPenaltyRamount = Double.valueOf(df.format(HoldInterestCount * aheadRepayPlatform.getAwardplatrate()));
					if(aheadRepayPlatform.getAwardplatminmoney() != null 
							&& aheadRepayPlatform.getAwardplatmaxmoney() != null){
						if(aheadRepayPlatform.getAwardplatminmoney() >= platformPenaltyRamount){//最小值
							platformPenaltyRamount = aheadRepayPlatform.getAwardplatminmoney();
						}else if(platformPenaltyRamount >= aheadRepayPlatform.getAwardplatmaxmoney()){//最大值
							platformPenaltyRamount = aheadRepayPlatform.getAwardplatmaxmoney();
						}
					}
 				}else{//定额
					platformPenaltyRamount = aheadRepayPlatform.getAwardplatquota() == null ? 0.00 : aheadRepayPlatform.getAwardplatquota();
 				}
				aheadRepayPlatformRecord.setAmendspamount(platformPenaltyRamount); ; //补偿平台金额
				aheadRepayPlatformRecordServiceI.insertSelective(aheadRepayPlatformRecord);
			}
	}
	/**
	  * 
	 * @Title: insertAheadRealRepaymentRecordByTheCurrentPeriod 
	 * @Description: TODO(添加 提前实际还款记录  提前还款当期[按天计息]) 
	 * @param @param repayMent 原还款计划
	 * @param @param    参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public void insertAheadRealRepaymentRecordByTheCurrentPeriod(RepayMent repayMent,
			AheadRepayMentCalculatedParameter aher,TenderItem tenderItem,String bacthNo) {
 		 AheadRealRepayment realRepayment = aheadRealRepaymentServiceI.
				 findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(), bacthNo);
		 if(realRepayment == null){//防止重复添加
			 Double Rptotalint 		= Double.valueOf(df.format(aher.getHoldinterest() + aher.getHoldrvoucherint() + 0.00));//提前还款实际总利息 	本金利息+类现金利息
			 Double Rprincipalint 	= Double.valueOf(df.format(repayMent.getRamount() + repayMent.getRvoucher() + Rptotalint + 0.00));//提前还款实际总金额（本息）本金+类现金+实际总利息
			 Double Norectotalint	= Double.valueOf(df.format(aher.getBadcropinterest() + aher.getBadcroprvoucherint() + 0.00));//提前还款欠收总利息 本金欠收利息+类现金欠收利息
			 Double RptotalintCount	= Double.valueOf(df.format(Rptotalint + aher.getHoldrvoucherint() + aher.getHoldrintfee() + 0.00));//提前还款实际总利息  本金利息+类现金利息+假现金利息+加息卷利息
			 UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(repayMent.getInaccountid());
			 Double Interestexpense = 0.00;//投标利息的管理费 提前还款实际总利息 * 百分比
			 if(userAccountSafeInfo != null){
				  Interestexpense	= calculatedInterestManagementFee(tenderItem, userAccountSafeInfo, RptotalintCount);//投标利息的管理费 提前还款实际总利息 * 百分比
			 }
 			 AheadRealRepayment aheadRealRepayment = new AheadRealRepayment();
 			 aheadRealRepayment.setRorderno(repayMent.getRorderno()); //还款流水号 同还款计划表流水号 
 			 aheadRealRepayment.setRrealbatchno(bacthNo); //还款批次号
			 aheadRealRepayment.setRptotalamount(repayMent.getRamount()); //提前还款实际真现金
			 aheadRealRepayment.setRvoucher(repayMent.getRvoucher()); //提前还款实际类现金
			 aheadRealRepayment.setRprincipalint(Rprincipalint); //提前还款实际总金额（本息）本金+类现金+实际总利息
			 aheadRealRepayment.setRptotalint(Rptotalint); //提前还款实际总利息 	本金利息+类现金利息
			 aheadRealRepayment.setNorectotalint(Norectotalint); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
			 aheadRealRepayment.setRinterest(aher.getHoldinterest()); //提前还款实际本金利息
			 aheadRealRepayment.setRvoucherint(aher.getHoldrvoucherint()); //提前还款实际类现金的利息
			 aheadRealRepayment.setRlvoucherint(aher.getHoldrlvoucherint()); //提前还款实际假现金的利息
			 aheadRealRepayment.setRintfee(aher.getHoldrintfee()); //提前还款实际加息劵利息
			 aheadRealRepayment.setNorecrinterest(aher.getBadcropinterest()); //提前还款欠收本金利息
			 aheadRealRepayment.setNorecrvoucherint(aher.getBadcroprvoucherint()); //提前还款欠收类现金的利息
			 aheadRealRepayment.setNorecrlvoucherint(aher.getBadcroprlvoucherint()); //提前还款欠收假现金的利息
			 aheadRealRepayment.setNorecrintfee(aher.getBadcroprintfee()); //提前还款欠收加息劵利息
			 aheadRealRepayment.setInterestexpense(Interestexpense); //投标利息的管理费 提前还款实际总利息 * 百分比
 			 aheadRealRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
			 aheadRealRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
			 aheadRealRepayment.setPaycompany(StringUtil.BANKNAME); //托管通道公司（汇付天下
			 aheadRealRepayment.setAddtime(new Date());//制表时间
			 aheadRealRepayment.setDiscardvoucherint(0.00);//提前还款实际作废类现金的利息
			 aheadRealRepayment.setNorecdiscardvoucherint(repayMent.getDisablevoucherint());//提前还款欠收作废类现金的利息
			 aheadRealRepayment.setDiscardvoucherint(aher.getDiscardvoucherint());//提前还款实际作废类现金的利息
			 aheadRealRepayment.setNorecdiscardvoucherint(aher.getNorecdiscardvoucherint());//提前还款欠收作废类现金的利息
			 aheadRealRepaymentServiceI.insertSelective(aheadRealRepayment);
		 }else{
			 Double Rptotalint 		= Double.valueOf(df.format(aher.getHoldinterest() + aher.getHoldrvoucherint() + 0.00));//提前还款实际总利息 	本金利息+类现金利息
			 Double Rprincipalint 	= Double.valueOf(df.format(repayMent.getRamount() + repayMent.getRvoucher() + Rptotalint + 0.00));//提前还款实际总金额（本息）本金+类现金+实际总利息
			 Double Norectotalint	= Double.valueOf(df.format(aher.getBadcropinterest() + aher.getBadcroprvoucherint() + 0.00));//提前还款欠收总利息 本金欠收利息+类现金欠收利息
			 Double RptotalintCount	= Double.valueOf(df.format(Rptotalint + aher.getHoldrvoucherint() + aher.getHoldrintfee() + 0.00));//提前还款实际总利息  本金利息+类现金利息+假现金利息+加息卷利息
			 UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(repayMent.getInaccountid());
			 Double Interestexpense = 0.00;//投标利息的管理费 提前还款实际总利息 * 百分比
			 if(userAccountSafeInfo != null){
				  Interestexpense	= calculatedInterestManagementFee(tenderItem, userAccountSafeInfo, RptotalintCount);//投标利息的管理费 提前还款实际总利息 * 百分比
			 }
  			 
 			realRepayment.setRorderno(repayMent.getRorderno()); //还款流水号 同还款计划表流水号 
   			realRepayment.setRrealbatchno(bacthNo); //还款批次号
  			realRepayment.setRptotalamount(repayMent.getRamount()); //提前还款实际真现金
  			realRepayment.setRvoucher(repayMent.getRvoucher()); //提前还款实际类现金
  			realRepayment.setRprincipalint(Rprincipalint); //提前还款实际总金额（本息）本金+类现金+实际总利息
  			realRepayment.setRptotalint(Rptotalint); //提前还款实际总利息 	本金利息+类现金利息
  			realRepayment.setNorectotalint(Norectotalint); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
  			realRepayment.setRinterest(aher.getHoldinterest()); //提前还款实际本金利息
  			realRepayment.setRvoucherint(aher.getHoldrvoucherint()); //提前还款实际类现金的利息
  			realRepayment.setRlvoucherint(aher.getHoldrlvoucherint()); //提前还款实际假现金的利息
  			realRepayment.setRintfee(aher.getHoldrintfee()); //提前还款实际加息劵利息
  			realRepayment.setNorecrinterest(aher.getBadcropinterest()); //提前还款欠收本金利息
  			realRepayment.setNorecrvoucherint(aher.getBadcroprvoucherint()); //提前还款欠收类现金的利息
  			realRepayment.setNorecrlvoucherint(aher.getBadcroprlvoucherint()); //提前还款欠收假现金的利息
			realRepayment.setNorecrintfee(aher.getBadcroprintfee()); //提前还款欠收加息劵利息
			realRepayment.setInterestexpense(Interestexpense); //投标利息的管理费 提前还款实际总利息 * 百分比
 			realRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
			realRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
			realRepayment.setPaycompany(StringUtil.BANKNAME); //托管通道公司（汇付天下
			realRepayment.setAddtime(new Date());//制表时间
			realRepayment.setDiscardvoucherint(aher.getDiscardvoucherint());//提前还款实际作废类现金的利息
			realRepayment.setNorecdiscardvoucherint(aher.getNorecdiscardvoucherint());//提前还款欠收作废类现金的利息
 			aheadRealRepaymentServiceI.updateById(realRepayment);
		 }
 	}
	/**
	  * 
	 * @Title: insertAheadRealRepaymentRecordByOtherPeriod 
	 * @Description: TODO(添加 提前实际还款记录 提前还款其他期数[按天计息]) 
	 * @param @param repayMent 原还款计划
	 * @param @param    参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public void insertAheadRealRepaymentRecordByOtherPeriod(RepayMent repayMent,String bacthNo) {
		/**
		 * 因为提前还款的期数不是当期的，所以持有天数为0，持有利息全部为0.
		 */
		 AheadRealRepayment realRepayment = aheadRealRepaymentServiceI.
				 findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(),bacthNo);//根据原还款具体计划的还款流水号查询是否存在
		 if(realRepayment == null){//防止重复添加
 			 Double Rprincipalint 	= Double.valueOf(df.format(repayMent.getRamount() + repayMent.getRvoucher()  + 0.00));//提前还款实际总金额（本息）本金+类现金+实际总利息
			 Double Norectotalint	= Double.valueOf(df.format(repayMent.getRinterest() + repayMent.getRvoucherint() + 0.00));//提前还款欠收总利息 本金欠收利息+类现金欠收利息
  			 AheadRealRepayment aheadRealRepayment = new AheadRealRepayment();
  			 
  			 aheadRealRepayment.setRorderno(repayMent.getRorderno()); //还款流水号 同还款计划表流水号 
  			 aheadRealRepayment.setRrealbatchno(bacthNo); //还款批次号
			 aheadRealRepayment.setRptotalamount(repayMent.getRamount()); //提前还款实际真现金
			 aheadRealRepayment.setRvoucher(repayMent.getRvoucher()); //提前还款实际类现金
			 aheadRealRepayment.setRprincipalint(Rprincipalint); //提前还款实际总金额（本息）本金+类现金+实际总利息
			 aheadRealRepayment.setRptotalint(0.00); //提前还款实际总利息 	本金利息+类现金利息
			 aheadRealRepayment.setNorectotalint(Norectotalint); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
			 aheadRealRepayment.setRinterest(0.00); //提前还款实际本金利息
			 aheadRealRepayment.setRvoucherint(0.00); //提前还款实际类现金的利息
			 aheadRealRepayment.setRlvoucherint(0.00); //提前还款实际假现金的利息
			 aheadRealRepayment.setRintfee(0.00); //提前还款实际加息劵利息
			 aheadRealRepayment.setNorecrinterest(repayMent.getRinterest()); //提前还款欠收本金利息
			 aheadRealRepayment.setNorecrvoucherint(repayMent.getRvoucherint()); //提前还款欠收类现金的利息
			 aheadRealRepayment.setNorecrlvoucherint(repayMent.getRlvoucherint()); //提前还款欠收假现金的利息
			 aheadRealRepayment.setNorecrintfee(repayMent.getRintfee()); //提前还款欠收加息劵利息
			 aheadRealRepayment.setInterestexpense(0.00); //投标利息的管理费 提前还款实际总利息 * 百分比
 			 aheadRealRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
			 aheadRealRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
			 aheadRealRepayment.setPaycompany(StringUtil.BANKNAME); //托管通道公司（汇付天下
			 aheadRealRepayment.setAddtime(new Date());//制表时间
			 aheadRealRepayment.setDiscardvoucherint(0.00);//提前还款实际作废类现金的利息
			 aheadRealRepayment.setNorecdiscardvoucherint(repayMent.getDisablevoucherint());//提前还款欠收作废类现金的利息
			 aheadRealRepaymentServiceI.insertSelective(aheadRealRepayment);
		 }else{
			 Double Rprincipalint 	= Double.valueOf(df.format(repayMent.getRamount() + repayMent.getRvoucher()  + 0.00));//提前还款实际总金额（本息）本金+类现金+实际总利息
			 Double Norectotalint	= Double.valueOf(df.format(repayMent.getRinterest() + repayMent.getRvoucherint() + 0.00));//提前还款欠收总利息 本金欠收利息+类现金欠收利息
   			 
 			realRepayment.setRorderno(repayMent.getRorderno()); //还款流水号 同还款计划表流水号 
    		realRepayment.setRrealbatchno(bacthNo); //还款批次号
   			realRepayment.setRptotalamount(repayMent.getRamount()); //提前还款实际真现金
   			realRepayment.setRvoucher(repayMent.getRvoucher()); //提前还款实际类现金
   			realRepayment.setRprincipalint(Rprincipalint); //提前还款实际总金额（本息）本金+类现金+实际总利息
   			realRepayment.setRptotalint(0.00); //提前还款实际总利息 	本金利息+类现金利息
   			realRepayment.setNorectotalint(Norectotalint); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
   			realRepayment.setRinterest(0.00); //提前还款实际本金利息
   			realRepayment.setRvoucherint(0.00); //提前还款实际类现金的利息
   			realRepayment.setRlvoucherint(0.00); //提前还款实际假现金的利息
   			realRepayment.setRintfee(0.00); //提前还款实际加息劵利息
   			realRepayment.setNorecrinterest(repayMent.getRinterest()); //提前还款欠收本金利息
   			realRepayment.setNorecrvoucherint(repayMent.getRvoucherint()); //提前还款欠收类现金的利息
   			realRepayment.setNorecrlvoucherint(repayMent.getRlvoucherint()); //提前还款欠收假现金的利息
   			realRepayment.setNorecrintfee(repayMent.getRintfee()); //提前还款欠收加息劵利息
   			realRepayment.setInterestexpense(0.00); //投标利息的管理费 提前还款实际总利息 * 百分比
    		realRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
   			realRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
   			realRepayment.setPaycompany(StringUtil.BANKNAME); //托管通道公司（汇付天下
   			realRepayment.setAddtime(new Date());//制表时间
   			realRepayment.setDiscardvoucherint(0.00);//提前还款实际作废类现金的利息
   			realRepayment.setNorecdiscardvoucherint(repayMent.getDisablevoucherint());//提前还款欠收作废类现金的利息
 			aheadRealRepaymentServiceI.updateById(realRepayment);
		 }
	}
	
	
	 /**
	  * 
	 * @Title: insertAheadRealRepaymentRecordByRepayMent 
	 * @Description: TODO(添加 提前实际还款记录 [全额计息]) 
	 * @param @param repayMent 原还款计划
	 * @param @param    参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public void insertAheadRealRepaymentRecordByRepayMent(RepayMent repayMent,String bacthNo){
		 AheadRealRepayment realRepayment = aheadRealRepaymentServiceI.
				 findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(),bacthNo);//根据原还款具体计划的还款流水号查询是否存在
		 if(realRepayment == null){//防止重复添加
    		 AheadRealRepayment aheadRealRepayment = new AheadRealRepayment();
   			 aheadRealRepayment.setRorderno(repayMent.getRorderno()); //还款流水号 同还款计划表流水号 
  			 aheadRealRepayment.setRrealbatchno(bacthNo); //还款批次号
			 aheadRealRepayment.setRptotalamount(repayMent.getRamount()); //提前还款实际真现金
			 aheadRealRepayment.setRvoucher(repayMent.getRvoucher()); //提前还款实际类现金
			 aheadRealRepayment.setRprincipalint(repayMent.getRprincipalint()); //提前还款实际总金额（本息）本金+类现金+实际总利息
			 aheadRealRepayment.setRptotalint(repayMent.getRptotalint()); //提前还款实际总利息 	本金利息+类现金利息
			 aheadRealRepayment.setNorectotalint(0.00); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
			 aheadRealRepayment.setRinterest(repayMent.getRinterest()); //提前还款实际本金利息
			 aheadRealRepayment.setRvoucherint(repayMent.getRvoucherint()); //提前还款实际类现金的利息
			 aheadRealRepayment.setRlvoucherint(repayMent.getRlvoucherint()); //提前还款实际假现金的利息
			 aheadRealRepayment.setRintfee(repayMent.getRintfee()); //提前还款实际加息劵利息
			 aheadRealRepayment.setNorecrinterest(repayMent.getRinterest()); //提前还款欠收本金利息
			 aheadRealRepayment.setNorecrvoucherint(repayMent.getRvoucherint()); //提前还款欠收类现金的利息
			 aheadRealRepayment.setNorecrlvoucherint(repayMent.getRlvoucherint()); //提前还款欠收假现金的利息
			 aheadRealRepayment.setNorecrintfee(repayMent.getRintfee()); //提前还款欠收加息劵利息
			 aheadRealRepayment.setInterestexpense(repayMent.getInterestexpense()); //投标利息的管理费 提前还款实际总利息 * 百分比
 			 aheadRealRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
			 aheadRealRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
			 aheadRealRepayment.setPaycompany(StringUtil.BANKNAME); //托管通道公司（汇付天下
			 aheadRealRepayment.setAddtime(new Date());//制表时间
			 aheadRealRepayment.setDiscardvoucherint(0.00);//提前还款实际作废类现金的利息
			 aheadRealRepayment.setNorecdiscardvoucherint(repayMent.getDisablevoucherint());//提前还款欠收作废类现金的利息
			 aheadRealRepaymentServiceI.insertSelective(aheadRealRepayment);
		 } 
	 }
	
	/**
	  * 
	 * @Title: calculatedInterestManagementFee 
	 * @Description: TODO(计算利息管理费) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	@Override
	public Double calculatedInterestManagementFee(TenderItem tenderItem,
			UserAccountSafeInfo userAccountSafeInfo,Double rinterestCount) {
		Double InterestManagementFee = 0.00;
 		Short isaintexp = tenderItem.getIsaintexp();//是否设置利息管理费 1是 0否
		if(isaintexp.equals((short)1)){//设置了利息管理收费
 			InterestExpense condition = new InterestExpense();
			condition.setTid(tenderItem.getId());
			List<InterestExpense> interestExpenses = interestExpenseServiceI.selectByCondition(condition);//利息管理设置
			if(interestExpenses.size() > 0){
				InterestExpense interestExpense = AutoGenerateRepayMentUtil.getInterestExpense(interestExpenses, userAccountSafeInfo);
				if(interestExpense != null){
					Short Ietype = 1;//利息管理费收取类型（1百分比，2最低，3最高）
 					double iepercent 			=  interestExpense.getIepercent();//利息管理费百份比
 					double maxiefee 	 		=  interestExpense.getMaxiefee();//该段最高利息管理收费金额
					double fee1 = Arith.round(Arith.mul(rinterestCount, iepercent), 2);
 
					if(fee1 >= maxiefee){
						Ietype 					= 3;//利息管理费收取类型（1百分比，2最低，3最高）
						fee1   					= maxiefee;
					}
					InterestManagementFee 			= Double.valueOf(df.format(fee1));//利息管理费
 				}
				
 			}
  		}
		return InterestManagementFee;
	}
	
	
	/**
	   * 
	  * @Title: deleteAheadRealRepaymentRecord 
	  * @Description: TODO(删除提前还款实际利息记录   注意：只有标的还款类型是 及时还款，并且写进文件失败才调用此方法 ) 
 	  * @param @param bacthNo    提前还款批次号
	  * @return void    返回类型 
	  * @author   cjm  
	  * @throws
	   */
	 public void deleteAheadRealRepaymentRecord(String bacthNo){
		 aheadRealRepaymentServiceI.deleteByRrealbatchno(bacthNo);
	 }
	 /**
	  * 
	 * @Title: deleteAheadRepayRecord 
	 * @Description: TODO(删除借款人补偿投资人本金欠收利息记录 注意：只有标的还款类型是 及时还款，并且写进文件失败才调用此方法 ) 
	 * @param @param ment
	 * @param @param bacthNo    设定文件 
	 * @return void    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 public void deleteAheadRepayRecord(String bacthNo){
		 aheadRepayOneRecordServiceI.deleteByArbatchno(bacthNo);
 	 }
	 /**
	  * 
	 * @Title: deleteAheadRepayAwardRecord 
	 * @Description: TODO(删除平台补偿投资人增益欠收利息 记录 注意：只有标的还款类型是 及时还款，并且写进文件失败才调用此方法 ) 
	 * @param @param ment
	 * @param @param bacthNo    设定文件 
	 * @return void    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 public void deleteAheadRepayAwardRecord(String bacthNo){
		 aheadRepayAwardRecordServiceI.deleteByArbatchno(bacthNo);
 	 }
	 /**
	  * 
	 * @Title: deleteAheadRepayPlatformRecord 
	 * @Description: TODO(删除借款人补偿平台记录 注意：只有标的还款类型是 及时还款，并且写进文件失败才调用此方法) 
	 * @param @param ment
	 * @param @param bacthNo    设定文件 
	 * @return void    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 public void deleteAheadRepayPlatformRecord(String bacthNo){
 		 aheadRepayPlatformRecordServiceI.deleteByRorderno(bacthNo);
	 }
	 
 }
