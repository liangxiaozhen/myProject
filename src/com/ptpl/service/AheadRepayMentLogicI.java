package com.ptpl.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ptpl.model.AheadRepay;
import com.ptpl.model.AheadRepayAward;
import com.ptpl.model.AheadRepayMentCalculatedParameter;
import com.ptpl.model.AheadRepayPlatform;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
 
/**
 * 
* @ClassName: AheadRepayMnetLogicI 
* @Package com.ptpl.service 
* @Description: TODO(提前还款 逻辑处理接口类) 
* @author cjm
* @date 2016年12月24日 下午2:36:47 
* @version V1.0
 */
public interface AheadRepayMentLogicI {
	
	/**
	 * 
	* @Title: hasDividedPaymentsAheadRepayMent 
	* @Description: TODO(提前还款  校验标的还款计划是否跳期提前还款) 
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	public boolean hasDividedPaymentsAheadRepayMent(DividedPayments dividedPayments);
	
	/**
 	* @Title: hasDividedPaymentsAheadRepayMent 
	* @Description: TODO(提前还款  校验标的还款计划是否隔期还款) 
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	public boolean hasDividedPaymentsAheadMultiperiod(List<DividedPayments> dividedPayments);
	
	/**
	 * 
	* @Title: hasInvestorAheadRepayMent 
	* @Description: TODO(提前还款  校验单个投资人是否跳期提前还款) 
	* @param @param repayMent
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	public boolean hasInvestorAheadRepayMent(RepayMent repayMent);
	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(本金利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentInterest(RepayMent repayMent);
	
		
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(类现金利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRvoucherint(RepayMent repayMent);
	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentDisablevoucherint 
	* @Description: TODO(提前还款 计算投资人当期持有利息(作废的类现金利息  还款时返回平台 不进投资人账号)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentDisablevoucherint(RepayMent repayMent);
	
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(假现金利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRlvoucherint(RepayMent repayMent);
	/**
	 * 
	* @Title: calculateTheCurrentPeriodHoldRepayMentInterest 
	* @Description: TODO(提前还款 计算投资人当期持有利息(加息券利息)) 
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author cjm
	* @throws
	 */
	public double calculateTheCurrentPeriodHoldRepayMentRintfee(RepayMent repayMent); 
	
	  /**
	   * 
	  * @Title: HoldRepayMentDay 
	  * @Description: TODO(计算投资人 当期持有时间) 
	  * @param @param repayMent 投资人当期还款计划
	  * @param @param date  借款人提前还款日期
	  * @param @return  参数说明 
	  * @return Integer    返回类型 
	  * @author cjm
	  * @throws
	   */
	 public BigDecimal HoldRepayMentDay(RepayMent repayMent);
	 /**
	  * 
	 * @Title: findAheadRepayByHoldInterestCount 
	 * @Description: TODO(取到符合 投资人欠收利息区间段的 提前还款个人利息奖励设置表对象) 
	 * @param @param tenderid  标的ID
	 * @param @param HoldInterestCount   投资人欠收利息总和
	 * @param @return  参数说明 
	 * @return AheadRepay    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public AheadRepay findAheadRepayByHoldInterestCount(BigDecimal tenderid,Double HoldInterestCount);
	 /**
	  * 
	 * @Title: findAheadRepayAwardByHoldInterestCount 
	 * @Description: TODO(取到符合 投资人增益利息欠收利息区间段的 提前还款个人奖品奖励设置表对象) 
	 * @param @param tenderid  标的ID
	 * @param @param HoldInterestCount   投资人欠收利息总和
	 * @param @return  参数说明 
	 * @return AheadRepayAward    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public AheadRepayAward findAheadRepayAwardByHoldInterestCount(BigDecimal tenderid,Double HoldInterestCount); 
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
	 public AheadRepayPlatform findAheadRepayPlatformByHoldInterestCount(BigDecimal tenderid,Double HoldInterestCount); 
	 
	 /**
	  * 
	 * @Title: insertAheadRepayRecord 
	 * @Description: TODO(添加  标的提前还款奖励单个投资人记录) 
	 * @param @param AheadRepay
	 * @param @param HoldInterestCount  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public void insertAheadRepayRecord(AheadRepay aheadRepay,Double HoldInterestCount,String bacthNo,RepayMent repayMent);
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
	 public void insertAheadRepayAwardRecord(AheadRepayAward aheadRepayAward,Double HoldInterestCount,String bacthNo,RepayMent repayMent);
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
	 public void insertAheadRepayPlatformRecord(AheadRepayPlatform aheadRepayPlatform,Double HoldInterestCount,String bacthNo,TenderItem tenderItem,BigDecimal outaccountid);
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
	 public void insertAheadRealRepaymentRecordByTheCurrentPeriod(RepayMent repayMent,
			 AheadRepayMentCalculatedParameter aheadRepayMentCalculatedParameter,TenderItem tenderItem,String bacthNo);
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
	 public void insertAheadRealRepaymentRecordByOtherPeriod(RepayMent repayMent,String bacthNo);
	 
	 /**
	  * 
	 * @Title: insertAheadRealRepaymentRecordByTheCurrentPeriod 
	 * @Description: TODO(添加 提前实际还款记录 [全额计息]) 
	 * @param @param repayMent 原还款计划
	 * @param @param    参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public void insertAheadRealRepaymentRecordByRepayMent(RepayMent repayMent,String bacthNo);
	 
	 /**
	  * 
	 * @Title: calculatedInterestManagementFee 
	 * @Description: TODO(计算利息管理费) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public Double calculatedInterestManagementFee(TenderItem tenderItem,
			 UserAccountSafeInfo userAccountSafeInfo,Double rinterestCount);
	 
	  /**
	   * 
	  * @Title: deleteAheadRealRepaymentRecord 
	  * @Description: TODO(删除提前还款实际利息记录   注意：只有标的还款类型是 及时还款，并且写进文件失败才调用此方法 ) 
	  * @param @param ment
	  * @param @param bacthNo    设定文件 
	  * @return void    返回类型 
	  * @author   cjm  
	  * @throws
	   */
	 public void deleteAheadRealRepaymentRecord(String bacthNo);
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
	 public void deleteAheadRepayRecord(String bacthNo);
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
 	 public void deleteAheadRepayAwardRecord(String bacthNo);
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
	 public void deleteAheadRepayPlatformRecord(String bacthNo);
	 
}
