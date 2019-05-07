package com.ptpl.controller.huifu;

 import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.ptpl.model.AheadRepay;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.model.Plus;
import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserTender;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.DateUtil;
import com.ptpl.web.util.FixedBasisMortgageUtil;
import com.ptpl.web.util.FixedPaymentMortgageUtil;
import com.ptpl.web.util.OneTimeServicingUtil;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: OraceTest 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(数据库模拟数据  测试) 
* @author chenjiaming
* @date 2016年9月28日 下午4:10:04 
* @version V1.0
 */
public class OraceTest {
	
	
	public static DividedPayments getDividedPayment(){
		DividedPayments dividedPayments = new DividedPayments();
		
		dividedPayments.setId(new BigDecimal(1523));/*ID（主键）*/
		dividedPayments.setDporderno("21");/*分期还款流水号*/
		dividedPayments.setTenderid(new BigDecimal(1524));/*标号ID*/
		dividedPayments.setPeriods(5);/*期数(第几期)*/
		dividedPayments.setRepayday(StringUtil.getDateByString("2016-11-04", "yyyy-MM-dd"));/*还款日*/
		dividedPayments.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments.setCpinterest(80.00);/*当期利息*/
		dividedPayments.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/
		
		return dividedPayments ;
	}
	
	
	/*模拟债权转让*/ 
	public  static UserTender getUserTender(){
		UserTender userTender = new UserTender();
		userTender.setId(new BigDecimal(1));//主键ID
		userTender.setOrderno("20161110101745242856");//投标订单号（债转订单号） 20161110101745242851
		userTender.setOutaccountid(new BigDecimal(218));//投资方ID
		userTender.setInaccountid(new BigDecimal(219));//借款方ID
		userTender.setAmount(70.00);//投标金额
		userTender.setFee(2.00);//手续费
		userTender.setFeerate(0.5);//手续费率
		userTender.setTransfertype((short)1);//转账类型（1.账户转账，2.银行卡转账）
 		userTender.setOriginclient("1");//
		userTender.setRemark("备注");//
		userTender.setTenderid(new BigDecimal(223));//标的号ID 
		userTender.setTbegintime(StringUtil.getDateByString("2016-12-10", "yyyy-MM-dd"));//转账开始时间
		userTender.setTendtime(StringUtil.getDateByString("2016-12-10", "yyyy-MM-dd"));//转账完成时间时间
		userTender.setOlddaorderno("20161110101745242852");//原流水号20161110101745242850
		userTender.setHolddate(12);//原投标持有天数
		userTender.setTotalamount(120.00);//承接总金额
		userTender.setUtprinamount(20.00);//承接本金 
		userTender.setUtintamount(10.00);//承接利息
		userTender.setRestamount(10.00);//剩余金额 
		userTender.setYearprofit(0.12);//承接时年化收益率
		userTender.setDaproperty((short)1);//债转属性（1正常债转，2逾期债转）
 		userTender.setDadate(StringUtil.getDateByString("2016-11-02", "yyyy-MM-dd"));//转让时间
		userTender.setValuedate(StringUtil.getDateByString("2016-12-10", "yyyy-MM-dd"));//起息日
 		return userTender;
	}
	  
	
	/*模拟标的数据库数据*/
	public static TenderItem getTenderItem(){
		TenderItem tenderItem = new TenderItem();
		tenderItem.setId(new BigDecimal(889));
		tenderItem.setLoanappid(new BigDecimal(203));
		tenderItem.setTno("gjbd2016111014");//标号
		tenderItem.setTamount(1000.00);//标的金额
		tenderItem.setFinishtamount(1000.00);//已完成投标金额
		tenderItem.setTpro((short)1);//标的类型（担保标，信用标，抵押标，新手标）
		tenderItem.setLoantime(3);//借款周期
		tenderItem.setDayormonth("月");//天或月或年
		tenderItem.setTinterest(0.12);//标的利息(收益)
		tenderItem.setTstatus((short)2);//标的状态（1投标中，2已完成，3流标）
		tenderItem.setRepaymentpro((short)2);//还款资金方式(1一次还本付息  2：等额本金  3：等额本息   4：按期付息到期还本   99：其他)
		tenderItem.setRepaysetmode((short)1);//还款设置方式（自动，手动）
		tenderItem.setIsapartrepay((short)1);//是否支持部分还款
		tenderItem.setIsaoverduec((short)1);//是否允许逾期代偿
//		tenderItem.setOverduecno("");//逾期代偿表编号
		tenderItem.setIsaaheadrepay((short)1);//是否允许提前还款
//		tenderItem.setAheadrepayno("");//提前还款表编号
		tenderItem.setIsadebtattorn((short)1);//是否允许债权转让
//		tenderItem.setDebtattornno("");//债权转让表编号
		tenderItem.setIsaintexp((short)1);//是否设置利息管理费
		tenderItem.setIntexpno("23243");//利息管理费编号
		tenderItem.setRepaytimepoint("10:23:30");//还款时间点
		tenderItem.setRepaymode((short)1);//还款金额分配方式（1一次分配，2二次分配）
		tenderItem.setRetdate(StringUtil.getDateByString("2016-11-11", "yyyy-MM-dd"));
		tenderItem.setValuedate(StringUtil.getDateByString("2016-10-11 9:00:00", "yyyy-MM-dd HH:mm:ss"));//结标日（有时分秒）标状态改变的时候
		tenderItem.setValuepoint("10:00:00");//起息日时间点(10:00:00)
		tenderItem.setValuerule((short)2);//起息规则(1.结标日当天,2.结标日次日,3.结标日固定时间之前,4.结标日固定时间之后)
		
		tenderItem.setCompensatoryman("2");
		return tenderItem;
	}
	
	/*模拟标的利息管理费数据库数据设置*/
	public  static List<InterestExpense> getInterestExpenses(){
		InterestExpense interestExpense = new InterestExpense();
		interestExpense.setId(new BigDecimal(1));
		interestExpense.setIntexpno("bdlx20160928");//利息管理费编号
		interestExpense.setIerecman("11");//利息管理费收款人
		interestExpense.setUgrade("000000111111111111111111111111");//会员等级
		interestExpense.setIepercent(0.01);//利息管理费百份比 ，每次还款本金*利率
		interestExpense.setMiniefee(10.00);//该段最低利息管理收费金额
		interestExpense.setMaxiefee(800.00);//该段最高利息管理收费金额
		
		InterestExpense interestExpense1 = new InterestExpense();
		interestExpense1.setId(new BigDecimal(2));
		interestExpense1.setIntexpno("bdlx20160928");//利息管理费编号
		interestExpense1.setIerecman("11");//利息管理费收款人
		interestExpense1.setUgrade("100001");//会员等级
		interestExpense1.setIepercent(0.02);//利息管理费百份比
		interestExpense1.setMiniefee(10.00);//该段最低利息管理收费金额
		interestExpense1.setMaxiefee(800.00);//该段最高利息管理收费金额
		
		InterestExpense interestExpense2 = new InterestExpense();
		interestExpense2.setId(new BigDecimal(3));
		interestExpense2.setIntexpno("bdlx20160928");//利息管理费编号
		interestExpense2.setIerecman("11");//利息管理费收款人
		interestExpense2.setUgrade("0001");//会员等级
		interestExpense2.setIepercent(0.03);//利息管理费百份比
		interestExpense2.setMiniefee(10.00);//该段最低利息管理收费金额
		interestExpense2.setMaxiefee(800.00);//该段最高利息管理收费金额
		
		InterestExpense interestExpense3 = new InterestExpense();
		interestExpense3.setId(new BigDecimal(4));
		interestExpense3.setIntexpno("bdlx20160928");//利息管理费编号
		interestExpense3.setIerecman("11");//利息管理费收款人
		interestExpense3.setUgrade("001");//会员等级
		interestExpense3.setIepercent(0.04);//利息管理费百份比
		interestExpense3.setMiniefee(10.00);//该段最低利息管理收费金额
		interestExpense3.setMaxiefee(800.00);//该段最高利息管理收费金额
		
		InterestExpense interestExpense4 = new InterestExpense();
		interestExpense4.setId(new BigDecimal(5));
		interestExpense4.setIntexpno("bdlx20160928");//利息管理费编号
		interestExpense4.setIerecman("11");//利息管理费收款人
		interestExpense4.setUgrade("01");//会员等级
		interestExpense4.setIepercent(0.05);//利息管理费百份比
		interestExpense4.setMiniefee(10.00);//该段最低利息管理收费金额
		interestExpense4.setMaxiefee(800.00);//该段最高利息管理收费金额
		
		List<InterestExpense> interestExpenses = new ArrayList<>();
		interestExpenses.add(interestExpense);
		interestExpenses.add(interestExpense1);
		interestExpenses.add(interestExpense2);
		interestExpenses.add(interestExpense3);
		interestExpenses.add(interestExpense4);
		
 		return interestExpenses;
	}
	

 	
	/*模拟标的分期还款计划（dividedPayments）*/
 	public  static List<DividedPayments> getDividedPayments(){
		DividedPayments dividedPayments = new DividedPayments();
		dividedPayments.setId(new BigDecimal(11));/*ID（主键）*/
		dividedPayments.setDporderno("21");/*分期还款流水号*/
		dividedPayments.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments.setPeriods(2);/*期数(第几期)*/
		dividedPayments.setRepayday(StringUtil.getDateByString("2018-11-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments.setCpinterest(80.00);/*当期利息*/
		dividedPayments.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/
   		
//		DividedPayments dividedPayments1 = new DividedPayments();
//		dividedPayments1.setId(new BigDecimal(12));/*ID（主键）*/
//		dividedPayments1.setDporderno("");/*分期还款流水号*/
//		dividedPayments1.setTenderid(new BigDecimal(461));/*标号ID*/
//		dividedPayments1.setPeriods(2);/*期数(第几期)*/
//		dividedPayments1.setRepayday(StringUtil.getDateByString("2016-10-04", "yyyy-MM-dd"));/*还款日*/
//		dividedPayments1.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
//		dividedPayments1.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
//		dividedPayments1.setCpinterest(80.00);/*当期利息*/
//		dividedPayments1.setIscomplete((short)1);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
//		dividedPayments1.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments2 = new DividedPayments();
		dividedPayments2.setId(new BigDecimal(13));/*ID（主键）*/
		dividedPayments2.setDporderno("21");/*分期还款流水号*/
		dividedPayments2.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments2.setPeriods(1);/*期数(第几期)*/
		dividedPayments2.setRepayday(StringUtil.getDateByString("2016-10-07", "yyyy-MM-dd"));/*还款日*/
		dividedPayments2.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments2.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments2.setCpinterest(80.00);/*当期利息*/
		dividedPayments2.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments2.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments3 = new DividedPayments();
		dividedPayments3.setId(new BigDecimal(14));/*ID（主键）*/
		dividedPayments3.setDporderno("12");/*分期还款流水号*/
		dividedPayments3.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments3.setPeriods(4);/*期数(第几期)*/
		dividedPayments3.setRepayday(StringUtil.getDateByString("2017-02-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments3.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments3.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments3.setCpinterest(80.00);/*当期利息*/
		dividedPayments3.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments3.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments4 = new DividedPayments();
		dividedPayments4.setId(new BigDecimal(15));/*ID（主键）*/
		dividedPayments4.setDporderno("12");/*分期还款流水号*/
		dividedPayments4.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments4.setPeriods(5);/*期数(第几期)*/
		dividedPayments4.setRepayday(StringUtil.getDateByString("2017-03-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments4.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments4.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments4.setCpinterest(80.00);/*当期利息*/
		dividedPayments4.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments4.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments5 = new DividedPayments();
		dividedPayments5.setId(new BigDecimal(16));/*ID（主键）*/
		dividedPayments5.setDporderno("12");/*分期还款流水号*/
		dividedPayments5.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments5.setPeriods(6);/*期数(第几期)*/
		dividedPayments5.setRepayday(StringUtil.getDateByString("2017-04-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments5.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments5.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments5.setCpinterest(80.00);/*当期利息*/
		dividedPayments5.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments5.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments6 = new DividedPayments();
		dividedPayments6.setId(new BigDecimal(17));/*ID（主键）*/
		dividedPayments6.setDporderno("12");/*分期还款流水号*/
		dividedPayments6.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments6.setPeriods(7);/*期数(第几期)*/
		dividedPayments6.setRepayday(StringUtil.getDateByString("2017-05-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments6.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments6.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments6.setCpinterest(80.00);/*当期利息*/
		dividedPayments6.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments6.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments7 = new DividedPayments();
		dividedPayments7.setId(new BigDecimal(18));/*ID（主键）*/
		dividedPayments7.setDporderno("12");/*分期还款流水号*/
		dividedPayments7.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments7.setPeriods(8);/*期数(第几期)*/
		dividedPayments7.setRepayday(StringUtil.getDateByString("2017-06-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments7.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments7.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments7.setCpinterest(80.00);/*当期利息*/
		dividedPayments7.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments7.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments8 = new DividedPayments();
		dividedPayments8.setId(new BigDecimal(19));/*ID（主键）*/
		dividedPayments8.setDporderno("12");/*分期还款流水号*/
		dividedPayments8.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments8.setPeriods(9);/*期数(第几期)*/
		dividedPayments8.setRepayday(StringUtil.getDateByString("2017-07-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments8.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments8.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments8.setCpinterest(80.00);/*当期利息*/
		dividedPayments8.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments8.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

		
		DividedPayments dividedPayments9 = new DividedPayments();
		dividedPayments9.setId(new BigDecimal(20));/*ID（主键）*/
		dividedPayments9.setDporderno("12");/*分期还款流水号*/
		dividedPayments9.setTenderid(new BigDecimal(461));/*标号ID*/
		dividedPayments9.setPeriods(10);/*期数(第几期)*/
		dividedPayments9.setRepayday(StringUtil.getDateByString("2017-08-11", "yyyy-MM-dd"));/*还款日*/
		dividedPayments9.setCurrentpi(1080.00); /*当期本息 (当期总的本+息)*/
		dividedPayments9.setCpprincipal(1000.00);/*当期本金 当期本金(cp=current period)*/
		dividedPayments9.setCpinterest(80.00);/*当期利息*/
		dividedPayments9.setIscomplete((short)0);/*是否还款完成1已完成还款0没有完成还款(标的截标时生成时默认0)*/
		dividedPayments9.setIsoverdue((short)0);/*是否逾期1已结逾期0没有逾期(标的截标时生成时默认0)*/

 		
		List<DividedPayments> dividedPaymentss = new ArrayList<>();
		dividedPaymentss.add(dividedPayments);
		dividedPaymentss.add(dividedPayments4);
		dividedPaymentss.add(dividedPayments6);
		dividedPaymentss.add(dividedPayments3);
		dividedPaymentss.add(dividedPayments5);
		dividedPaymentss.add(dividedPayments8);
//		dividedPaymentss.add(dividedPayments1);
		dividedPaymentss.add(dividedPayments7);
		dividedPaymentss.add(dividedPayments9);
		dividedPaymentss.add(dividedPayments2);
 		return dividedPaymentss;
	}
	/*标的提前还款设置表*/
 	public static AheadRepay getAheadRepay(){
 		AheadRepay aheadRepay = new AheadRepay();
 		aheadRepay.setId(new BigDecimal(1));    //
 		aheadRepay.setAheadrepayno("GJTQHK2016102014");    //提前还款编号
 		aheadRepay.setIspicompensateon((short)1);    //本金利息补偿开关（PI=Principal interest ）1开0关
 		aheadRepay.setMinnoreceiveint(1.00);    //单个投资人累计本金欠收最小利息
 		aheadRepay.setMaxnoreceiveint(100.00);    //单个投资人累计本金欠收最高利息
 		aheadRepay.setAwardtype((short)1);    //奖励方式（1惩罚借款人，2平台奖励，3 1+2）
 		aheadRepay.setLoanpenaltyname("提前还款罚金");    //借款人罚金奖励名称
 		aheadRepay.setPenaltyquota(0.00);    //借款人罚金定额
 		aheadRepay.setPenaltyrate(0.12);    //借款人罚金百分比（不设置定额时）
 		aheadRepay.setMaxpenalty(10000.00);    //借款人罚金最大值
 		aheadRepay.setPawardname("平台奖励投资人");    //平台奖励名称
 		aheadRepay.setPawardquota(0.00);    //平台奖励定额
 		aheadRepay.setPawardrate(0.12);    //平台奖励百分比
 		aheadRepay.setMaxpaward(2000.00);   //平台奖励最大值
 		aheadRepay.setIspluscompensateon((short)1);    //增益利息补偿开关 1开0关
 		aheadRepay.setMinplusnoreceiveint(1.00);    //单个投资人累计增益欠收最小利息
 		aheadRepay.setMaxplusnoreceiveint(200.00);    //单个投资人累计增益欠收最高利息
 		aheadRepay.setPlusawardtype((short)1);    //增益奖励方式（1平台罚金，2平台奖励，3 1+2）
 		aheadRepay.setPluspenaltyname("增益平台罚金奖励名称");    //增益平台罚金奖励名称
 		aheadRepay.setPluspenaltyquota(0.00);    //增益平台罚金定额
 		aheadRepay.setPluspenaltyrate(0.12);    //增益平台罚金百分比（不设置定额时）
 		aheadRepay.setPlusmaxpenalty(500.00);    //增益平台罚金最大值
  		aheadRepay.setIsforplatformon((short)1);    //还款人补偿平台开关
 		aheadRepay.setAwardrecman("平台");    //奖励平台收款人
 		aheadRepay.setMinallnoreceiveint(1.00);    //奖励平台投资人总欠收最小利息
 		aheadRepay.setMaxallnoreceiveint(1000.00);    //奖励平台投资人总欠收最高利息
 		aheadRepay.setAwardplatquota(0.00);    //奖励平台定额
 		aheadRepay.setAwardplatrate(0.12);    //奖励平台百份比
 		aheadRepay.setAwardplatminmoney(1.00);    //奖励平台最小值
 		aheadRepay.setAwardplatmaxmoney(5000.00);    //奖励平台最大值
 		aheadRepay.setIstemplet((short)0);    //是否为模板（0否，1是）
 		aheadRepay.setIscompensatory((short)1);    //提前还款代偿开关
 		aheadRepay.setCompensatoryman("坦保企业");    //提前还款代偿人
 		aheadRepay.setTid(new BigDecimal(1));    //标id
 		aheadRepay.setCompensatorymanid(new BigDecimal(3));    //提前还款代偿人id
 		aheadRepay.setPawardno("");    //平台奖励奖品编号
 		aheadRepay.setPluspawardno("");    //平台奖励奖品编号(增益奖励)
 		aheadRepay.setIsaudit((short)0);    //是否审核
 	 
 		return aheadRepay;
 	}
	
 	public  static List<DividedPayments> getDividedPayments36(){
 		DividedPayments dividedPayments1 = new DividedPayments();
 		dividedPayments1.setId(new BigDecimal(1));
 		dividedPayments1.setPeriods(1);
 		
 		DividedPayments dividedPayments2 = new DividedPayments();
 		dividedPayments2.setId(new BigDecimal(2));
 		dividedPayments2.setPeriods(2);
 		
 		DividedPayments dividedPayments3 = new DividedPayments();
 		dividedPayments3.setId(new BigDecimal(3));
 		dividedPayments3.setPeriods(3);
 		
 		DividedPayments dividedPayments4 = new DividedPayments();
 		dividedPayments4.setId(new BigDecimal(4));
 		dividedPayments4.setPeriods(4);
 		
 		DividedPayments dividedPayments5 = new DividedPayments();
 		dividedPayments5.setId(new BigDecimal(5));
 		dividedPayments5.setPeriods(5);
 		
 		DividedPayments dividedPayments6 = new DividedPayments();
 		dividedPayments6.setId(new BigDecimal(6));
 		dividedPayments6.setPeriods(6);
 	
 		DividedPayments dividedPayments7 = new DividedPayments();
 		dividedPayments7.setId(new BigDecimal(7));
 		dividedPayments7.setPeriods(7);
 		
 		DividedPayments dividedPayments8 = new DividedPayments();
 		dividedPayments8.setId(new BigDecimal(8));
 		dividedPayments8.setPeriods(8);
 		 
 		DividedPayments dividedPayments9 = new DividedPayments();
 		dividedPayments9.setId(new BigDecimal(9));
 		dividedPayments9.setPeriods(9);
 		
 		DividedPayments dividedPayments10 = new DividedPayments();
 		dividedPayments10.setId(new BigDecimal(10));
 		dividedPayments10.setPeriods(10);
 		
 		DividedPayments dividedPayments11 = new DividedPayments();
 		dividedPayments11.setId(new BigDecimal(11));
 		dividedPayments11.setPeriods(11);
 		
 		DividedPayments dividedPayments12 = new DividedPayments();
 		dividedPayments12.setId(new BigDecimal(12));
 		dividedPayments12.setPeriods(12);
 		
 		DividedPayments dividedPayments13 = new DividedPayments();
 		dividedPayments13.setId(new BigDecimal(13));
 		dividedPayments13.setPeriods(13);
 		
 		DividedPayments dividedPayments14 = new DividedPayments();
 		dividedPayments14.setId(new BigDecimal(14));
 		dividedPayments14.setPeriods(14);
 		
 		DividedPayments dividedPayments15 = new DividedPayments();
 		dividedPayments15.setId(new BigDecimal(15));
 		dividedPayments15.setPeriods(15);
 		
 		DividedPayments dividedPayments16 = new DividedPayments();
 		dividedPayments16.setId(new BigDecimal(16));
 		dividedPayments16.setPeriods(16);
 		
 		DividedPayments dividedPayments17 = new DividedPayments();
 		dividedPayments17.setId(new BigDecimal(17));
 		dividedPayments17.setPeriods(17);
 		
 		DividedPayments dividedPayments18 = new DividedPayments();
 		dividedPayments18.setId(new BigDecimal(18));
 		dividedPayments18.setPeriods(18);
 		
 		DividedPayments dividedPayments19 = new DividedPayments();
 		dividedPayments19.setId(new BigDecimal(19));
 		dividedPayments19.setPeriods(19);
 		
 		DividedPayments dividedPayments20 = new DividedPayments();
 		dividedPayments20.setId(new BigDecimal(20));
 		dividedPayments20.setPeriods(20);
 		
 		DividedPayments dividedPayments21 = new DividedPayments();
 		dividedPayments21.setId(new BigDecimal(21));
 		dividedPayments21.setPeriods(21);
 		
 		DividedPayments dividedPayments22 = new DividedPayments();
 		dividedPayments22.setId(new BigDecimal(22));
 		dividedPayments22.setPeriods(22);
 		  
 		
 		DividedPayments dividedPayments23 = new DividedPayments();
 		dividedPayments23.setId(new BigDecimal(23));
 		dividedPayments23.setPeriods(23);
 		
 		DividedPayments dividedPayments24 = new DividedPayments();
 		dividedPayments24.setId(new BigDecimal(24));
 		dividedPayments24.setPeriods(24);
 		
 		DividedPayments dividedPayments25 = new DividedPayments();
 		dividedPayments25.setId(new BigDecimal(25));
 		dividedPayments25.setPeriods(25);
 		
 		DividedPayments dividedPayments26 = new DividedPayments();
 		dividedPayments26.setId(new BigDecimal(26));
 		dividedPayments26.setPeriods(26);
 		
 		DividedPayments dividedPayments27 = new DividedPayments();
 		dividedPayments27.setId(new BigDecimal(27));
 		dividedPayments27.setPeriods(27);
 		
 		DividedPayments dividedPayments28 = new DividedPayments();
 		dividedPayments28.setId(new BigDecimal(28));
 		dividedPayments28.setPeriods(28);
 		
 		DividedPayments dividedPayments29 = new DividedPayments();
 		dividedPayments29.setId(new BigDecimal(29));
 		dividedPayments29.setPeriods(29);
 		
 		DividedPayments dividedPayments30 = new DividedPayments();
 		dividedPayments30.setId(new BigDecimal(30));
 		dividedPayments30.setPeriods(30);
 		
 		DividedPayments dividedPayments31 = new DividedPayments();
 		dividedPayments31.setId(new BigDecimal(31));
 		dividedPayments31.setPeriods(31);
 		
 		DividedPayments dividedPayments32 = new DividedPayments();
 		dividedPayments32.setId(new BigDecimal(32));
 		dividedPayments32.setPeriods(32);
 		
 		DividedPayments dividedPayments33 = new DividedPayments();
 		dividedPayments33.setId(new BigDecimal(33));
 		dividedPayments33.setPeriods(33);
 		
 		
 		DividedPayments dividedPayments34 = new DividedPayments();
 		dividedPayments34.setId(new BigDecimal(34));
 		dividedPayments34.setPeriods(34);
 		
 		
 		DividedPayments dividedPayments35 = new DividedPayments();
 		dividedPayments35.setId(new BigDecimal(35));
 		dividedPayments35.setPeriods(35);
 		
 		
 		DividedPayments dividedPayments36 = new DividedPayments();
 		dividedPayments36.setId(new BigDecimal(36));
 		dividedPayments36.setPeriods(36);
  		
 		List<DividedPayments> dividedPaymentss = new ArrayList<>();
		dividedPaymentss.add(dividedPayments1);
		dividedPaymentss.add(dividedPayments2);
		dividedPaymentss.add(dividedPayments3);
		dividedPaymentss.add(dividedPayments4);
		dividedPaymentss.add(dividedPayments5);
		dividedPaymentss.add(dividedPayments6);
		dividedPaymentss.add(dividedPayments7);
		dividedPaymentss.add(dividedPayments8);
		dividedPaymentss.add(dividedPayments9);
		dividedPaymentss.add(dividedPayments10);
		dividedPaymentss.add(dividedPayments11);
		dividedPaymentss.add(dividedPayments12);
		dividedPaymentss.add(dividedPayments13);
		dividedPaymentss.add(dividedPayments14);
		dividedPaymentss.add(dividedPayments15);
		dividedPaymentss.add(dividedPayments16);
		dividedPaymentss.add(dividedPayments17);
		dividedPaymentss.add(dividedPayments18);
		dividedPaymentss.add(dividedPayments19);
		dividedPaymentss.add(dividedPayments20);
		dividedPaymentss.add(dividedPayments21);
		dividedPaymentss.add(dividedPayments22);
		dividedPaymentss.add(dividedPayments23);
		dividedPaymentss.add(dividedPayments24);
		dividedPaymentss.add(dividedPayments25);
		dividedPaymentss.add(dividedPayments26);
		dividedPaymentss.add(dividedPayments27);
		dividedPaymentss.add(dividedPayments28);
		dividedPaymentss.add(dividedPayments29);
		dividedPaymentss.add(dividedPayments30);
		dividedPaymentss.add(dividedPayments31);
		dividedPaymentss.add(dividedPayments32);
		dividedPaymentss.add(dividedPayments33);
		dividedPaymentss.add(dividedPayments34);
		dividedPaymentss.add(dividedPayments35);
		dividedPaymentss.add(dividedPayments36);
  		return dividedPaymentss;
  	}
	
	
	
	
	
	
	
	
	
	
	
	
// 	@RequestMapping(value="/doNormalRepayMent1")
//	public void doNormalRepayMent1(HttpServletRequest request,HttpServletResponse response){
// 		HuifuParams huifuParams = new HuifuParams();
//		huifuParams.setVersion("30");//版本号 Version  30
//		huifuParams.setCmdId("Repayment");//消息类型 CmdId 此处是Repayment
//		huifuParams.setOutAcctId("MDT000001");//出账子账户 OutAcctId  可选 用户在汇付的虚拟资金账户号
//		//商户客户号 MerCustId  6000060004166478
//		huifuParams.setProId("gjbh201609051627");//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//		huifuParams.setOrdId(StringUtil.getNo());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
//		huifuParams.setOrdDate(StringUtil.formatDate(new Date(),"yyyyMMdd"));//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		huifuParams.setOutCustId("6000060005290553");//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识  6000060005290553  6000060005410674
//		huifuParams.setSubOrdId("20160924165458939243");//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//			/*订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一 
//			 * 如果本次交易从属于另一个交易流水，
//			 * 	则需要通过填写该流水号来进行关联例如：本次放款：商户流水号是 OrdId，
//			 * 日期 是OrdDate，关联投标订单流水是SubOrdId，日期是 SubOrdDate*/
//		huifuParams.setSubOrdDate("20160924");//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		huifuParams.setPrincipalAmt("0.00");//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//		huifuParams.setInterestAmt("0.00");//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//		huifuParams.setInCustId("6000060004190334");//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识       投资人  6000060004191208
//		huifuParams.setFee("1.00");//扣款手续费 Fee  必须 放款或扣款的手续费
//		huifuParams.setFeeObjFlag("O");//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
// 		huifuParams.setDivCustId("6000060004166478");//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//		huifuParams.setDivAcctId("SDT000001");//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//		huifuParams.setDivAmt("1.00");//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//		String str = "[{\"DivCustId\":\""+huifuParams.getDivCustId()+"\","+
//					 "\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\","+
//					 "\"DivAmt\":\""+huifuParams.getDivAmt()+"\"}]";
//		String str2 = "[{&quot;DivCustId&quot;:&quot;"+huifuParams.getDivCustId()+"&quot;,"+
//		"&quot;DivAcctId&quot;:&quot;"+huifuParams.getDivAcctId()+"&quot;,"+
//		"&quot;DivAmt&quot;:&quot;"+huifuParams.getDivAmt()+"&quot;}"+
//		"]";	
//		huifuParams.setDivDetailsStr(str2);
//		huifuParams.setDivDetails(str);//分账账户串 DivDetails 可选	
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
// 		try {
// 			geHuifuRequestParams(huifuParams, request, response);//调用汇付 用户还款接口
//		} catch (ServletException e) {
// 			e.printStackTrace();
//		} catch (IOException e) {
// 			e.printStackTrace();
//		} 
//}
 	
//	RepayMent repayMent = new RepayMent();
// 	repayMent.setRorderno(StringUtil.getActno("GJHK"));//还款流水号
// 	repayMent.setRbatchno(StringUtil.getActno("GJHKPC"));//还款批次号
// 	repayMent.setOutaccountid(huifuParams.getOutaccountid());//借款用户ID
// 	repayMent.setInaccountid(outInfo.getBaseid());//投资用户ID
// 	repayMent.setProxyaccountid(new BigDecimal(127));//代还款人ID
// 	repayMent.setUtorderno(huifuParams.getSubOrdId());//投标订单号
//  repayMent.setTenderid(tenderItem.getId());//标号ID
// 	repayMent.setPeriods(dividedPayment1.getPeriods());//还款期数（第几期）
// 	repayMent.setIsdarepay((short)1);//是否债转还款(投标记录发生过债转 1是0否)
// 	repayMent.setRmode((short)1);//还款模式（1人工，2系统，3线下）
// 	repayMent.setRprincipalint(20.00);//还款金额（本息）
// 	repayMent.setRamount(20.00);//还款金额（本金）
// 	repayMent.setRinterest(20.00);//还款金额（利息）
// 	repayMent.setBpenalty(20.00);//提前还款欠收利息 提前还款
// 	repayMent.setOverdueamount(20.00);//逾期滞纳金额 注意是否有代偿
// 	repayMent.setInterestexpense(20.00);//投标利息的管理费 投资人—》平台
// 	repayMent.setIsproxyrepay((short)1);//是否代偿（1是，2否
// 	repayMent.setFee(20.00);//还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额
// 	repayMent.setRtime(new Date());//还款时间
// 	repayMent.setIsaudit((short)2);//是否审核  0不需要审核 1待审核 2审核通过 3审核不通过
// 	int count = 0;
// 	count = repayMentServiceI.insertSelective(repayMent);
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
// 	request.setAttribute("Version", huifuParams.getVersion());//版本号 Version  30
//	request.setAttribute("CmdId", huifuParams.getCmdId());//消息类型 CmdId 此处是Repayment
//	request.setAttribute("MerCustId", huifuParams.getMerCustId());//商户客户号 MerCustId  
//	request.setAttribute("ProId", huifuParams.getProId());//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//	request.setAttribute("OrdId", huifuParams.getOrdId());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
//	request.setAttribute("OrdDate", huifuParams.getOrdDate());//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//	request.setAttribute("OutCustId", huifuParams.getOutCustId());//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识
//	request.setAttribute("SubOrdId", huifuParams.getSubOrdId());//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//				/*订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一 
//				 * 如果本次交易从属于另一个交易流水，
//				 * 	则需要通过填写该流水号来进行关联例如：本次放款：商户流水号是 OrdId，
//				 * 日期 是OrdDate，关联投标订单流水是SubOrdId，日期是 SubOrdDate*/
//	request.setAttribute("SubOrdDate", huifuParams.getSubOrdDate());//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//	request.setAttribute("OutAcctId", huifuParams.getOutAcctId());//出账子账户 OutAcctId  可选 用户在汇付的虚拟资金账户号
//	request.setAttribute("PrincipalAmt", huifuParams.getPrincipalAmt());//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//	request.setAttribute("InterestAmt", huifuParams.getInterestAmt());//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//	request.setAttribute("Fee", huifuParams.getFee());//扣款手续费 Fee  必须 放款或扣款的手续费
//	request.setAttribute("InCustId", huifuParams.getInCustId());//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识
//	request.setAttribute("InAcctId", huifuParams.getInAcctId());//入账子账户 InAcctId  可选 用户在汇付的虚拟资金账户号
//	request.setAttribute("DivDetails", str2);//分账账户串 DivDetails 可选	
//								/*放款 1.0、还款 1.0、债权转让 1.0 数据格式：
//								[{"DivAcctId":"MDT000023"，
//								"DivAmt":"1.00"}，
//								{"DivAcctId":"MDT000024"，
//								"DivAmt":"2.00"}，
//								{"DivAcctId":"MDT000025"，
//								"DivAmt":"3.00"}]
//								放款 2.0、还款 2.0、还款 3.0 数据格式：
//								[{"DivCustId":"6000060000009547"，
//								"DivAcctId":"MDT000001"，
//								"DivAmt":"1.00"}，
//								{"DivCustId":"6000060000002526"，
//								"DivAcctId":"MDT000001"，
//								"DivAmt":"2.00"}，
//								{"DivCustId":"6000060000002528"，
//								"DivAcctId":"MDT000001"，
//								"DivAmt":"3.00"}]
//								（当 Fee！=0 时是必填项）*/
//	request.setAttribute("DivCustId", huifuParams.getDivCustId());//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//	request.setAttribute("DivAcctId", huifuParams.getDivAcctId());//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//	request.setAttribute("DivAmt", huifuParams.getDivAmt());//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//	request.setAttribute("FeeObjFlag", huifuParams.getFeeObjFlag());//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
//	request.setAttribute("DzObject", huifuParams.getDzObject());//垫资/代偿对象 DzObject  可选 如果是垫资还款必传商户或者担保企业垫资/代偿对象
//	request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址BgRetUrl  必须 通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID字样的字符串表明商户已经收到该笔交易结果
//	request.setAttribute("ReqExt", huifuParams.getReqExt());//入参扩展域 ReqExt  可选 用于扩展请求参数
//	request.setAttribute("ChkValue", huifuParams.getChkValue());//签名 ChkValue  必须
//	request.setAttribute("CharSet", huifuParams.getCharSet());//编码格式
 	
 	
//  /**
//  * 
// * @Title: settingUpBatchRepayMent 
// * @Description: TODO(批量还款，参数设置) 
// * @param   参数说明 
// * @return void    返回类型 
// * @author chenjiaming
// * @throws
//  */
//	@Override
//	public void settingUpBatchRepayMent(HttpServletRequest request,DividedPayments dividedPayment) {
//		TenderItem tenderItem =	OraceTest.getTenderItem();//标的信息
//		Assert.notNull(tenderItem, "标的对象不能为null");
//		Map<String,Object> hashMap = new HashMap<String,Object>();
//		hashMap.put("tenderid", tenderItem.getId());
//		userTenderServiceI.findAllUserTender(hashMap);
//		List<UserTender> userTenders = userTenderServiceI.findAllUserTender(hashMap);//投标记录
//		Assert.notNull(userTenders, "投标记录的对象不能为null");
//		String periodsStr = dividedPayment.getPeriods().toString(); 
//		int periods = dividedPayment.getPeriods();//还款期数
//		String repaymentpro = tenderItem.getRepaymentpro().toString();//标的还款方式
//		String tno = tenderItem.getTno();//标号
//		int totalMonth = tenderItem.getLoantime();//借款周期
//		double yearRate = tenderItem.getTinterest();//年化利率
//		Assert.hasText(periodsStr,    "还款期数不能为null");
//		Assert.hasText(repaymentpro, "标的还款类型不能为null");
//		Assert.hasText(tno, "标的标号不能为null");
//		Assert.hasText(String.valueOf(totalMonth), "标的借款周期不能为null");
//		Assert.hasText(String.valueOf(yearRate), "标的年化利率不能为null");
// 	String tendertimeStr = "";//投标订单日期
//		String monthPrincipalStr = "";//每月还款本金
// 	String monthInterestStr  = "";//每月还款利息
// 	DecimalFormat  df   = new DecimalFormat("################0.00");
//		for(UserTender userTender : userTenders){
//			System.out.println(userTender.getId()+"================getId===============");
//			tendertimeStr = StringUtil.formatDate(userTender.getTendertime(), "yyyyMMdd");
//			Assert.hasText(userTender.getOrderno(), "投标订单号不能为null");
//			Assert.hasText(tendertimeStr, "投标订单日期不能为null");
//			Assert.hasText(userTender.getOutaccountid().toString(),"投资方ID不能为null");
//			Assert.hasText(userTender.getInaccountid().toString(), "借款方ID不能为null");
//			Assert.hasText(userTender.getTenderid().toString(), "标的号ID不能为null");
//			Assert.hasText(userTender.getIsda().toString(), "是否债转不能为null");
//			UserFsAccountInfo outaccounInfo =	userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userTender.getOutaccountid());//第三方托管账号 投资方
//			UserFsAccountInfo inaccountInfo =	userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userTender.getInaccountid());//第三方托管账号 借款方
//   		HuifuParams huifuParams = new HuifuParams();
//  		huifuParams.setOrdId(StringUtil.getNo());//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
// 		huifuParams.setOrdDate(StringUtil.formatDate(new Date(),"yyyyMMdd"));//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//			huifuParams.setProId(tno);//标的 ID ProId 可选 若是商户已有存管银行，则该字段必填
//			huifuParams.setInCustId(outaccounInfo.getUsrcustid());//入账客户号 InCustId  必须 由汇付生成，用户的唯一性标识       投资人  6000060004191208
//			huifuParams.setOutCustId(inaccountInfo.getUsrcustid());//出账客户号 OutCustId 必须 由汇付生成，用户的唯一性标识  6000060005290553  6000060005410674
//			huifuParams.setSubOrdId(userTender.getOrderno());//订单号 SubOrdId  必须 由商户的系统生成，必须保证唯一
//	  		huifuParams.setSubOrdDate(tendertimeStr);//订单日期 SubOrdDate 必须 格式为 YYYYMMDD，例如：20130307
//	  		double totalPrincipal = userTender.getAmount();//借款金额
//	    	boolean dayorMonthBoolean = false;//还款周期是天时 还款类型只能一次性还本付息
//	    	boolean lasePeriodBoolean = false;//是否是最后一期
//	    	int scale = 2;
//	    	if(tenderItem.getDayormonth().equals("年")){
//	    		totalMonth = tenderItem.getLoantime();//借款周期
//	    		totalMonth = totalMonth * 12;
//	    	}else if(tenderItem.getDayormonth().equals("天")){
//		    		dayorMonthBoolean = true;
//		    }
//		    	
//	    	if(totalMonth-periods == 0 || dayorMonthBoolean){//最后一期
//	    		lasePeriodBoolean = true;
//	    	}
//	  		
//	  		if(repaymentpro.equals("1") && !dayorMonthBoolean){//等额本金
//		    	double monthPrincipal = FixedBasisMortgageUtil.getMonthPrincipal(totalPrincipal, totalMonth, scale);//每月还款本金
//		    	Map<Integer,BigDecimal> monthInterest =FixedBasisMortgageUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);
//		    	monthPrincipalStr = df.format(monthPrincipal).toString();
//		    	monthInterestStr  = df.format(monthInterest.get(periods).doubleValue());
//		    }else if(repaymentpro.equals("2") && !dayorMonthBoolean){//等额本息
//		    	System.out.println(totalMonth);
//		    	Map<Integer,BigDecimal> monthPrincipal = FixedPaymentMortgageUtil.getMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
//		    	Map<Integer,BigDecimal> monthInterest =  FixedPaymentMortgageUtil.getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
//		    	monthPrincipalStr = df.format(monthPrincipal.get(periods).doubleValue()).toString();
//		    	monthInterestStr =  df.format(monthInterest.get(periods).doubleValue()).toString();
//		    }else if(repaymentpro.equals("3") && !dayorMonthBoolean){//先息后本
//		    	if(lasePeriodBoolean){//还款最后一期
//		    		  monthPrincipalStr = df.format(totalPrincipal).toString();//还款总本金
//	   		    	  monthInterestStr =  df.format(OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale)).toString();//每月还款利息
//		    	}else{
//		    		  monthPrincipalStr = "0.00";//每月还款本金
//	   		    	  monthInterestStr = df.format(OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale)).toString().toString();//每月还款利息
//		    	}
//		    }else if(repaymentpro.equals("4") || dayorMonthBoolean){//一次性还本付息
//		    	monthInterestStr  =df.format(OneTimeServicingUtil.getOneTimeInterestCount(totalPrincipal, totalMonth, yearRate, scale)).toString();//一次性还本付息总利息
//		    	monthPrincipalStr = df.format(totalPrincipal).toString();//借款本金
//		    }
//	  		
//	  		System.out.println("=========monthInterestStr============="+monthPrincipalStr);
//	    	System.out.println("=========monthInterestStr============="+monthInterestStr);
//	  		Assert.hasText(monthPrincipalStr, "每月还款本金不能为null");
//		    Assert.hasText(monthInterestStr,  "每月还款利息不能为null");
//	  		huifuParams.setPrincipalAmt(monthPrincipalStr);//还款本金 PrincipalAmt 必须 本次还款本金，本金不能超过放款金额
//			huifuParams.setInterestAmt(monthInterestStr);//还款利息 InterestAmt  必须 本次还利息，利息不能超过投标时
//			
//			huifuParams.setFee("1.00");//扣款手续费 Fee  必须 放款或扣款的手续费
//			huifuParams.setFeeObjFlag("O");//手续费收取对象标志 I/O FeeObjFlag  可选 I--向入款客户号 InCustId 收取 O--向出款客户号 OutCustId 收取	若 fee 大于 0.00，FeeObjFlag 为必填参数
//	 		huifuParams.setDivCustId("6000060004166478");//分账商户号 DivCustId  必须 DivDetails 参数下的二级参数分账商户号
//			huifuParams.setDivAcctId("SDT000001");//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数分账客户号
//			huifuParams.setDivAmt("1.00");//分账金额 DivAmt  	  必须 DivDetails 参数下的二级参数分账金额，保留两位小数
//			String str = "[{\"DivCustId\":\""+huifuParams.getDivCustId()+"\","+
//						 "\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\","+
//						 "\"DivAmt\":\""+huifuParams.getDivAmt()+"\"}]"; 
//			huifuParams.setDivDetails(str);//分账账户串 DivDetails 可选	
//			
//			RepayMent repayMent = new RepayMent();
//		 	repayMent.setRorderno(huifuParams.getOrdId());//还款流水号
//		 	repayMent.setRbatchno(StringUtil.getActno("GJHKPC"));//还款批次号
//		 	repayMent.setOutaccountid(outaccounInfo.getBaseid());//借款用户ID
//		 	repayMent.setInaccountid(inaccountInfo.getBaseid());//投资用户ID
//		 	repayMent.setProxyaccountid(new BigDecimal(127));//代还款人ID
//		 	repayMent.setUtorderno(huifuParams.getSubOrdId());//投标订单号
//		    repayMent.setTenderid(tenderItem.getId());//标号ID
//		 	repayMent.setPeriods(dividedPayment.getPeriods());//还款期数（第几期）
//		 	repayMent.setIsdarepay((short)0);//是否债转还款(投标记录发生过债转 1是0否)
//		 	repayMent.setRmode((short)1);//还款模式（1人工，2系统，3线下）
//		 	double strsd = Double.parseDouble(monthPrincipalStr) + Double.parseDouble(monthInterestStr);
//		 	repayMent.setRprincipalint(Double.valueOf(df.format(strsd)));//还款金额（本息）
//		 	repayMent.setRamount(Double.parseDouble(monthPrincipalStr));//还款金额（本金）
//		 	repayMent.setRinterest(Double.parseDouble(monthInterestStr));//还款金额（利息）
//		 	repayMent.setBpenalty(0.00);//提前还款欠收利息 提前还款
//		 	repayMent.setOverdueamount(0.00);//逾期滞纳金额 注意是否有代偿
//		 	repayMent.setInterestexpense(0.00);//投标利息的管理费 投资人—》平台
//		 	repayMent.setIsproxyrepay((short)1);//是否代偿（1是，2否
//		 	repayMent.setFee(0.00);//还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额
//		 	repayMent.setRtime(new Date());//还款时间
//		 	repayMent.setRepaystatus((short)4);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//		 	repayMent.setIsaudit((short)2);//是否审核  0不需要审核 1待审核 2审核通过 3审核不通过
//		 	int count = 0;
//		 	count = repayMentMapper.insertSelective(repayMent);
//		 	if(count > 0){
//		 		doHuifuSingleRepayMent(request,huifuParams);//单个还款
//		 	}
//		}
//	}
 	
 	
 	
 	
 	 
// 	/**
//     * 
//    * @Title: settingUpBatchRepayMent 
//    * @Description: TODO(批量还款，参数设置) 
//    * @param   参数说明 
//    * @return void    返回类型 
//    * @author chenjiaming
//    * @throws
//     */ 
// 	@Override
//	public void settingUpBatchRepayMent(HttpServletRequest request, DividedPayments dividedPayment) {
// 		Assert.notNull(dividedPayment, "dividedPayment 不能为null");
// 		Assert.hasText(dividedPayment.getPeriods().toString(), "还款期数不能为null");
// // 		TenderItem tenderItem = tenderItemServiceI.
// 		TenderItem tenderItem =	OraceTest.getTenderItem();//标的信息
// 		Assert.notNull(tenderItem, "tenderItem 不能为null");
// 		Assert.hasText(tenderItem.getLoanappid().toString(), "tenderItem.getLoanappid() 借款申请ID 不能为null");
// 		String isaintexp = tenderItem.getIsaintexp().toString();//是否设置利息管理费1是0否
// 		String repaymentpro = tenderItem.getRepaymentpro().toString();//标的还款方式
//		int totalMonth = tenderItem.getLoantime();//借款周期
//		int periods = dividedPayment.getPeriods();//还款期数
//		double yearRate = tenderItem.getTinterest();//年化利率
//		
//		if(repaymentpro.equals("4")){//标的还款类型是一次性还本付息时 还款期数永远只有一期
//			periods = 1;
//		}
//		
// 		Assert.hasText(isaintexp, "是否设置利息管理费 不能为null");
// 		String intexpno = "";
// 		if(isaintexp.equals("1")){//设置利息管理费
// 			intexpno = tenderItem.getIntexpno();
// 			Assert.hasText(intexpno, "利息管理费编号不能为null");
// 		}
//  		
// 		loanapp loanapp = ioanappServiceI.selectByPrimaryKey(tenderItem.getLoanappid());//借款申请
// 		Assert.notNull(loanapp, "loanapp 不能为null");
// 		Assert.hasText(loanapp.getBaseid().toString(), "loanapp.getBaseid() 不能为null");
//
// 		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(loanapp.getBaseid());//借款用户
// 		Assert.notNull(userFsAccountInfo, "userFsAccountInfo 不能为null");
// 		Assert.hasText(userFsAccountInfo.getUsrcustid().toString(), "userFsAccountInfo.getUsrcustid() 不能为null");
// 		
// 		HuifuParams huifuParams = new HuifuParams();
//		huifuParams.setVersion("20");//版本号 Version 必须
//		huifuParams.setCmdId("BatchRepayment");//消息类型 CmdId 变长 String 必须 此 处 为 BatchRepayment
////		huifuParams.setMerCustId("6000060004166478");//商户客户号 MerCustId 必须  
//		huifuParams.setOutCustId(userFsAccountInfo.getUsrcustid().toString());//出账客户号 OutCustId 必须   
// 		huifuParams.setProId(tenderItem.getTno());//项目 ID ProId 可选 若 4.3.5 主动投标/4.3.6 自动投标的借款人信息 BorrowerDetails 字段的项目 ID ProId 有值，则必填，否则为可选
//		huifuParams.setOutAcctId("MDT000001");//出账客户号  出账子账户 OutAcctId 可选 出款客户为商户时此字段必款
//		huifuParams.setBatchId(StringUtil.getNo());//还款批次号 BatchId 变长 20 位 必须 由商户生成，在商户下唯一，打印此字段用来结束异步消息接收
//		huifuParams.setMerOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd"));//商户还款订单日期 MerOrdDate 定长 8 位 必须 批量还款订单日期
//		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/BatchRepayment/BatchRepaymentCallBack.action");//商户后台应答地址 BgRetUrl  必须
//   		StringBuffer buffer = new StringBuffer();
//		buffer.append("{\"InDetails\":[");
//		DecimalFormat df = new DecimalFormat("################0.00");
//		boolean dayorMonthBoolean = false;//还款周期是天时 还款类型只能一次性还本付息
//		boolean lasePeriodBoolean = false;//是否是最后一期
//		Short ietype = 1; //利息管理费收取类型（1百分比，2最低，3最高）
//		double monthPrincipalDou = 0.00; //每期还款金额
//		double monthInterestDou = 0.00; //每期还款利息
//		boolean doBatchRepay = false;//是否调用批量还款接口
//		int scale = 2;
//		if(tenderItem.getDayormonth().equals("年")){
//			totalMonth = tenderItem.getLoantime();//借款周期
//			totalMonth = totalMonth * 12;
//		}else if(tenderItem.getDayormonth().equals("天")){
//			dayorMonthBoolean = true;
//		}
//		
//		if(totalMonth-periods == 0 || dayorMonthBoolean){//最后一期
//			lasePeriodBoolean = true;
//		}
//		String GJHKPCActno = StringUtil.getActno("GJHKPC");//还款批次号
//		/****************************循环参数拼接开始*********************************************/
//		Map<String,Object> hashMap = new HashMap<String,Object>();
//		hashMap.put("tenderid", tenderItem.getId());//标的ID
//		List<UserTender> userTenders = userTenderServiceI.findAllUserTender(hashMap);
//		for(UserTender userTender : userTenders){
//			Map<String,Object> maps = new HashMap<String,Object>();
//			maps.put("utorderno", userTender.getOrderno());//投标订单号
//			maps.put("periods", periods);//期数
//			RepayMent repayMent1 = repayMentMapper.findRepayMentByConditions(maps);
//			if(repayMent1 != null && repayMent1.getRepaystatus() == 2){//本期投标已还款
// 				continue;
// 			}else{
// 				    doBatchRepay = true;//是否调用批量还款接口
// 					double totalPrincipal = userTender.getAmount();//投资人投资金额
//		 	  		if(repaymentpro.equals("1") && !dayorMonthBoolean){//等额本金
//		 	  			if(lasePeriodBoolean){//还款最后一期
//		 	  				double monthPrincipal = FixedBasisMortgageUtil.getLastMonthPrincipal(totalPrincipal, totalMonth, scale);//每月还款本金
//		 			    	Map<Integer,BigDecimal> monthInterest = FixedBasisMortgageUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//		 			    	monthPrincipalDou = monthPrincipal;
//		 			    	monthInterestDou  = monthInterest.get(periods).doubleValue();
//		 	  			}else{
//		 	  				double monthPrincipal = FixedBasisMortgageUtil.getMonthPrincipal(totalPrincipal, totalMonth, scale);//每月还款本金
//		 			    	Map<Integer,BigDecimal> monthInterest = FixedBasisMortgageUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//		 			    	monthPrincipalDou = monthPrincipal;
//		 			    	monthInterestDou  = monthInterest.get(periods).doubleValue();
//		 	  			}
//		 		    }else if(repaymentpro.equals("2") && !dayorMonthBoolean){//等额本息
//				    	if(lasePeriodBoolean){//还款最后一期
//				    		monthPrincipalDou = FixedPaymentMortgageUtil.getLastMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
//				    		Map<Integer,BigDecimal> monthInterest =  FixedPaymentMortgageUtil.getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
//				    		monthInterestDou =  monthInterest.get(periods).doubleValue();
//				    	}else{
//		 		    		Map<Integer,BigDecimal> monthPrincipal = FixedPaymentMortgageUtil.getMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
//		 		    		Map<Integer,BigDecimal> monthInterest =  FixedPaymentMortgageUtil.getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
//		 		    		monthPrincipalDou = monthPrincipal.get(periods).doubleValue();
//		 		    		monthInterestDou =  monthInterest.get(periods).doubleValue();
//				    	}
//				    }else if(repaymentpro.equals("3") && !dayorMonthBoolean){//先息后本
//				    	if(lasePeriodBoolean){//还款最后一期
//				    		  monthPrincipalDou = totalPrincipal;//还款总本金
//			   		    	  monthInterestDou =  OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//				    	}else{
//				    		  monthPrincipalDou = 0.00;//每月还款本金
//			   		    	  monthInterestDou = OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//				    	}
//				    }else if(repaymentpro.equals("4") || dayorMonthBoolean){//一次性还本付息
//				    	monthInterestDou  = OneTimeServicingUtil.getOneTimeInterestCount(totalPrincipal, totalMonth, yearRate, scale) ;//一次性还本付息总利息
//				    	monthPrincipalDou =  totalPrincipal ;//借款本金
//				    }
//			    	
//			  		System.out.println(monthInterestDou);
//			  		System.out.println(monthPrincipalDou);
//			  		
//			  		Assert.hasText(Double.toString(monthInterestDou),  "monthInterestDou  不能为null");
//			  		Assert.hasText(Double.toString(monthPrincipalDou), "monthPrincipalDou 不能为null");
//			  		double remainingDou = 0.00;
//			  		if(periods == 1){
// 			  			  remainingDou = totalPrincipal - monthPrincipalDou;//剩余还款本金
//			  		}else{
//			  			Map<String,Object> maps2 = new HashMap<String,Object>();
//						maps2.put("utorderno", userTender.getOrderno());//投标订单号
//						maps2.put("periods", periods-1);//期数utorderno
//			  			RepayMent repayMent = repayMentMapper.findRepayMentByConditions(maps2);
//			  			Assert.notNull(repayMent, "repayMent 不能为null");
//			  			remainingDou = Double.parseDouble(df.format(repayMent.getRestprincipal() - monthPrincipalDou))   ;
//			  		}
//					UserFsAccountInfo outaccountid = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userTender.getOutaccountid());//投资人
//					Assert.notNull(outaccountid, "outaccountid 不能为null");
//			 		Assert.hasText(outaccountid.getBaseid().toString(), "outaccountid.getBaseid() 不能为null");
//			 		
//			 		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(outaccountid.getBaseid());//投资人账号安全信息
//		 			Assert.notNull(userAccountSafeInfo, "baseAccountInfo 不能为null");
//			 		Assert.hasText(userAccountSafeInfo.getUgrade().toString(), "userAccountSafeInfo.getUgrade() 不能为null");
//			 		String fee = "0.00";//利息
//			 		double transAmtDou = monthInterestDou + monthPrincipalDou;//交易金额
//		 	 		System.out.println(transAmtDou+"========transAmtDou============");
//		 	 		String transAmt = df.format(transAmtDou).toString();//交易金额
//		 	 		System.out.println(transAmt+"========transAmt============");
//		 	 		String feeObjFlag ="I";//手续费收取对象 I向投资人收取  O向借款人收取
//			 		double principal = 0.00;//还款本金
//			 		InterestExpense interestExpense = null;
//			 		if(isaintexp.equals("1")){//设置利息管理费
//			 			List<InterestExpense> interestExpenses = OraceTest.getInterestExpenses();
//			 			for(InterestExpense interestExpense1 : interestExpenses){
//		 	 				char[] charit = interestExpense1.getUgrade().toCharArray();
//			 				if(charit.length > (userAccountSafeInfo.getUgrade()-1) && charit[userAccountSafeInfo.getUgrade()-1] == '1'  ){
//			 					interestExpense = interestExpense1;
//			 					break;
//			 				} 
//			 			}
//			 			
//			 		if(interestExpense != null){
//		  	 		    	double iepercent =  interestExpense.getIepercent();//利息管理费百份比
//			 		    	double miniefee  =  interestExpense.getMiniefee();//该段最低利息管理收费金额
//			 		    	double maxiefee  =  interestExpense.getMaxiefee();//该段最高利息管理收费金额
//		 	 		    	double fee1 = Arith.round(Arith.mul(principal, iepercent), 2);
//			 		    	if(fee1 <= miniefee){
//			 		    		ietype = 2;//利息管理费收取类型（1百分比，2最低，3最高）
//			 		    		fee1 = miniefee;
//			 		    	} 
//			 		    	if(fee1 >= maxiefee){
//			 		    		ietype = 3;//利息管理费收取类型（1百分比，2最低，3最高）
//			 		    		fee1 = maxiefee;
//			 		    	}
//		  	 				fee = df.format(fee1).toString();//手续费
//		 	 			}
//			 		}else{
//			 			fee = "0.00";
//			 		}
//			 		
//		 	 		String OrdId = StringUtil.getNo();//还款订单号
//		 			String str =  "{\"InCustId\":\""+outaccountid.getUsrcustid()+"\",\"InAcctId\":\"MDT000001\","+
//								  "\"OrdId\":\""+OrdId+"\",\"SubOrdId\":\""+userTender.getOrderno()+"\","+
//								  "\"FeeObjFlag\":\""+feeObjFlag+"\",\"TransAmt\":\""+transAmt+"\",\"Fee\":\""+fee+"\","+
//								  "\"DivDetails\":[{\"DivCustId\":\""+outaccountid.getMercustid()+"\","+
//								  "\"DivAcctId\":\"MDT000001\",\"DivAmt\":\""+fee+"\"}]}";
//					buffer.append(str);
//					buffer.append(",");
//					/**********还款计划具体记录开始*************/
//					if(repayMent1 == null){
//						RepayMent repayMent = new RepayMent();
//						repayMent.setRorderno(OrdId);//还款流水号
//						repayMent.setRbatchno(GJHKPCActno);//还款批次号
//						repayMent.setOutaccountid(userFsAccountInfo.getBaseid());//借款用户ID
//						repayMent.setInaccountid(outaccountid.getBaseid());//投资用户ID
//						repayMent.setProxyaccountid(new BigDecimal(219));//代还款人ID
//						repayMent.setUtorderno(userTender.getOrderno());//投标订单号
//						repayMent.setTenderid(tenderItem.getId());//标号ID
//						repayMent.setPeriods(dividedPayment.getPeriods());//还款期数（第几期）
//						repayMent.setIsdarepay((short)0);//是否债转还款(投标记录发生过债转 1是0否)
//						repayMent.setRmode((short)1);//还款模式（1人工，2系统，3线下）
//						repayMent.setRprincipalint(Double.parseDouble(transAmt));//还款金额（本息）
//						repayMent.setRamount(monthPrincipalDou);//还款金额（本金）
//						repayMent.setRinterest(monthInterestDou);//还款金额（利息）
//						repayMent.setRestprincipal(remainingDou);//剩余本金
//						repayMent.setBpenalty(0.00);//提前还款欠收利息 提前还款
//						repayMent.setOverdueamount(0.00);//逾期滞纳金额 注意是否有代偿
//						repayMent.setInterestexpense(Double.parseDouble(fee));//投标利息的管理费 投资人—》平台
//						repayMent.setIsproxyrepay((short)1);//是否代偿（1是，2否
//						repayMent.setFee(0.00);//还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额
//						repayMent.setRtime(new Date());//还款时间
//						repayMent.setRepaystatus((short)4);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//						repayMent.setIsaudit((short)2);//是否审核  0不需要审核 1待审核 2审核通过 3审核不通过
//						repayMent.setPaycompany("汇付天下");//托管通道公司（汇付天下）
// 						repayMentMapper.insertSelective(repayMent);
//					} 
//					/**********还款计划具体记录结束*************/
//					
//					/**********标的利息管理费记录开始*************/
////					InterestExpenseRecord interestExpenseRecord1 = null;
////					if(repayMent1 != null){
//// 						  interestExpenseRecord1 = interestExpenseRecordServiceI.
////								findInterestExpenseRecordByRorderno(repayMent1.getRorderno());
////					}
////					if(interestExpenseRecord1 == null){//数据库没有这条数据
//// 						InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
////						interestExpenseRecord.setIeorderno(StringUtil.getNo());//利息管理费流水号
////						interestExpenseRecord.setRorderno(OrdId);//还款流水号
////						if(interestExpense != null){
////							interestExpenseRecord.setIeid(interestExpense.getId());//利息管理费设置表Id
////						}
////						interestExpenseRecord.setTenderid(tenderItem.getId());//标号ID
////						interestExpenseRecord.setIntexpmanid(outaccountid.getBaseid());//利息管理费收款人ID
////						interestExpenseRecord.setInvestorid(userFsAccountInfo.getBaseid());//投资人ID
////						interestExpenseRecord.setIntexpfee(Double.parseDouble(fee));//利息管理费
////						interestExpenseRecord.setIetype(ietype);//利息管理费收取类型（1百分比，2最低，3最高）
////						interestExpenseRecord.setIsdeal((short)1);//是否处理（0否，1是）
////						interestExpenseRecord.setMadetime(new Date());//创建时间
////						interestExpenseRecordServiceI.insertSelective(interestExpenseRecord);
////					}
//  					/**********标的利息管理费记录结束*************/
//			}
//		}
//		/****************************循环参数拼接结束*********************************************/
// 		buffer.delete(buffer.length()-1, buffer.length());
//		buffer.append("]}");
//		String InDetailsStr = buffer.toString();
//		huifuParams.setInDetails(InDetailsStr);//还款账户串 InDetails 变长 String 必须
////		if(doBatchRepay){//调用批量还款接口
//// 			String result = doHuifuBatchRepayment(huifuParams, request);
////			System.out.println(result);
////		}
// 	}
 
 	
 	
 	
 	
 	
 	
 	
// 	/**
//	 * 
//	* @Title: AutoGenerateRepayMents 
//	* @Description: TODO(截标的时候自动生成 投标还款具体计划记录) 
//	* @param @param tenderItemId
//	* @param @throws InterruptedException  参数说明 
//	* @return void    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	public static void AutoGenerateRepayMents(BigDecimal tenderItemId){
//		Assert.notNull(tenderItemId, "'tenderItemId' 不能为null");
// //   		TenderItem  tenderItem = tenderItemServiceI.findTenderItemById(tenderItemId);
////  		Assert.notNull(tenderItem, "tenderItem 不能为null");
//		TenderItem  tenderItem = OraceTest.getTenderItem();//标的信息
 	
//  		double totalPrincipal       = tenderItem.getTamount();//标的金额
//  		double famountDou       = tenderItem.getFinishtamount();//已完成投标金额
//  		int    totalMonth    = tenderItem.getLoantime();//标的借款周期
//  		String dayormonthStr    = tenderItem.getDayormonth();//天或月或年
//  		double yearRate    = tenderItem.getTinterest();//标的利息(收益)
//  		Short  tstatusSrt       = tenderItem.getTstatus();//标的状态（1投标中，2已完成，3流标）
//  		if(!tstatusSrt.equals((short)2)){//判断标的状态
//  			throw new IllegalArgumentException("标的状态错误！！！ 标的没有完成投标!!!!");
//  		}
//  		Short isaintexp = tenderItem.getIsaintexp();//是否设置利息管理费 1是 0否
//  		if(isaintexp == null ){
//  			throw new IllegalArgumentException("标的是否设置利息管理费 不能为null!!!!");
//  		}
//  		boolean isaintexpBoolean = false;//是否设置利息管理费
//  		List<InterestExpense> interestExpenses = null;//利息管理设置
//  		if(isaintexp.equals((short)1)){//设置了利息管理收费
//  			InterestExpense condition = new InterestExpense();
//  			condition.setTid(tenderItemId);
////   			interestExpenses = interestExpenseServiceI.selectByCondition(condition);
//   			interestExpenses = OraceTest.getInterestExpenses();
//   			Assert.notNull(interestExpenses, "'interestExpenses' 标的利息管理费不能为null");
//			isaintexpBoolean = true;//设置利息管理费
//		}
//   		Short  repaymentproSrt  = tenderItem.getRepaymentpro();//标的还款方式01：一次还本付息02：等额本金03：等额本息04：按期付息到期还本99：其他
//  		Date   retdate          = tenderItem.getRetdate();//首次还款日期
// 		DecimalFormat df = new DecimalFormat("################0.00");//格式化金额
//		boolean lasePeriodBoolean = false;//是否是最后一期
//   		boolean dayorMonthBoolean = false;//还款周期是天时 还款类型只能一次性还本付息
// 		double monthPrincipalDou = 0.00; //每期还款金额
//		double monthInterestDou = 0.00; //每期还款利息
//		double restprincipal = 0.00;//剩余还款本金
//		double currentpi = 0.00;//还款本息（本金+利息）
//		double monthPrincipal = 0.00;//每月还款本金 计算公式常量
//		Short ietype = 1; //利息管理费收取类型（1百分比，2最低，3最高）
//		double interestexpense = 0.00;//利息管理费
//		double countInterestexpense = 0.00;//总利息管理费
//		double countInterestexpense1 = 0.00;
//		BigDecimal outaccountid = null;//借款用户ID
//		BigDecimal inaccountid = null;//投资用户ID
//		BigDecimal proxyaccountid = null;//代还款人ID
//		String utorderno = "";//投标订单号 根据具体投标记录对应还款
//		RepayMent repayMent2 = null;//防止重复添加
//		UserAccountSafeInfo outInfo = null;//投资人账号安全信息对象
//		InterestExpense interestExpense2 = null;//利息管理费设置对象
// 		Date repayday = null;//还款日期
//		int scale = 2;
//		Map<String,Object> condition = new HashMap<String,Object>();
//		condition.put("tenderid", tenderItemId);//标的ID
//		condition.put("tstatus", (short)1);//投标成功 2失败 1 成功
//		List<UserTender> userTenders =  usertenderServiceI.findUserTenderRecord(condition);
//		Assert.notNull(userTenders, "'userTenders' 不能为null");
//  		String rorderno = "";//还款流水号
//  		for(UserTender userTender : userTenders){
//   			countInterestexpense1 = 0.00;
//   			lasePeriodBoolean = false;//是否最后一期
// 			rorderno = StringUtil.getNo();//还款流水号
// 			totalPrincipal  = userTender.getAmount();//借款金额
//			outaccountid    = userTender.getInaccountid();//借款用户ID
//			inaccountid     = userTender.getOutaccountid();//投资用户ID
//			proxyaccountid  = new BigDecimal(216);//tenderItem.getCompensatoryman(); //代还款人ID
//			utorderno       = userTender.getOrderno();//投标订单号 根据具体投标记录对应还款
// 			Assert.notNull(inaccountid,"'inaccountid' 投资用户ID不能为null");
//			Assert.notNull(proxyaccountid,"'proxyaccountid' 代还款人ID不能为null");
//			Assert.notNull(outaccountid,"'outaccountid' 借款用户ID不能为null");
//			
//			Assert.hasText(utorderno,"'utorderno'投标订单号 不能为null");
//			Assert.hasText(rorderno, "'rorderno' 还款流水号不能为null");
//			outInfo = userAccountSafeInfoServiceI.selectByBaseId(outaccountid);//投资人账号安全信息对象
//			Assert.hasText(Double.toString(totalPrincipal), "'totalPrincipal' 借款金额不能为null");
//			Assert.notNull(outInfo, "'outInfo' 投资人账号安全信息对象不能为null");
//			String ugrade = outInfo.getUgrade().toString();//投资人会员等级
//			Assert.hasText(ugrade, "'ugrade' 投资人账号安全信息对象，会员等级不能为null");
// 			if(dayormonthStr.equals("年")){
//				totalMonth = tenderItem.getLoantime();
//				totalMonth = totalMonth * 12;
//			}else if(dayormonthStr.equals("天")){
//				dayorMonthBoolean = true;
//			}
//			
//			if(repaymentproSrt.equals((short)1)){//借款类型是一次性还本付息时 生成还款计划为一期
//				dayorMonthBoolean = true;
//			}
//			
//			if(isaintexpBoolean){//设置了标的利息管理费收取
//				interestExpense2 = getInterestExpense(interestExpenses, outInfo);
//				Assert.notNull(interestExpense2, "'interestExpense2' 标的利息管理费设置对象不能null");
//				double iepercent =  interestExpense2.getIepercent();//利息管理费百份比
// 		    	double miniefee  =  interestExpense2.getMiniefee();//该段最低利息管理收费金额
// 		    	double maxiefee  =  interestExpense2.getMaxiefee();//该段最高利息管理收费金额
//	 		    double fee1 = Arith.round(Arith.mul(totalPrincipal, iepercent), 2);
// 		    	if(fee1 <= miniefee){
// 		    		ietype = 2;//利息管理费收取类型（1百分比，2最低，3最高）
// 		    		fee1 = miniefee;
// 		    	} 
// 		    	if(fee1 >= maxiefee){
// 		    		ietype = 3;//利息管理费收取类型（1百分比，2最低，3最高）
// 		    		fee1 = maxiefee;
// 		    	}
// 		    	  interestexpense = Double.parseDouble(df.format(fee1));//利息管理费
// 		    	  countInterestexpense = Double.parseDouble(df.format(fee1));//总利息管理费
//			}
//			 
//			if(dayorMonthBoolean){//生成还款计划为一期
//				repayday = tenderItem.getRetdate();//首次还款时间
//	 			if(dayormonthStr.equals("天")){//借款周期是天时 
//	 				yearRate = yearRate / 365;//日利率
//	 				monthInterestDou = Double.parseDouble(df.format(totalPrincipal * yearRate * totalMonth));//借款总利息（计算公式 ： 借款本金 * 日利率 * 借款周期）
//	 				monthPrincipalDou = Double.parseDouble(df.format(totalPrincipal));//借款本金
//	 				currentpi  = Double.parseDouble(df.format(monthInterestDou +  monthPrincipalDou));//当期本息 (当期总的本+息)
//	 			}else{
//	  				monthInterestDou  = Double.parseDouble(df.format(OneTimeServicingUtil.getOneTimeInterestCount(totalPrincipal, totalMonth, yearRate, scale)));//一次性还本付息总利息
//	 				monthPrincipalDou = Double.parseDouble(df.format(totalPrincipal));//借款本金
//	 				currentpi  = Double.parseDouble(df.format(monthInterestDou +  monthPrincipalDou));//当期本息 (当期总的本+息)
//	  			}
// 	 			
//	 			Map<String,Object> hashMap = new HashMap<String,Object>();
//	 			hashMap.put("tenderid", tenderItem.getId());//标的ID
//	 			hashMap.put("utorderno", utorderno);//投标订单号 根据具体投标记录对应还款
// 	 			hashMap.put("periods", 1);//期数
//	 			repayMent2 = repayMentServiceI.findRepayMentByConditions(hashMap);
//	  			if(repayMent2 == null){//防止重复添加
//	 	  				/*************************标的分期还款计划开始***********************************/
//		 	  	  		RepayMent repayMent = new RepayMent();
//		 	  	  		repayMent.setRorderno(rorderno);//还款流水号
// 		 	  	  		repayMent.setOutaccountid(outaccountid);//借款用户ID
//		 	  	  		repayMent.setInaccountid(inaccountid);//投资用户ID
//		 	  	  		repayMent.setProxyaccountid(proxyaccountid);//代还款人ID
// 		 	  	  		repayMent.setUtorderno(utorderno);//投标订单号 根据具体投标记录对应还款
//		 	  	  		repayMent.setTenderid(tenderItem.getId());//标号ID
//		 	  	  		repayMent.setPeriods(1);//还款期数（第几期）
//		 	  	   		repayMent.setIsdarepay((short)0);//是否债转还款(投标记录发生过债转 1是0否)
//		 	  	  		repayMent.setRmode((short)1);//还款模式（1人工，2系统，3线下）
//		 	  	  		repayMent.setRprincipalint(currentpi);//还款金额（本息）
//		 	  	  		repayMent.setRamount(monthPrincipalDou);//还款金额（本金）
//		 	  	  		repayMent.setRinterest(monthInterestDou);//还款金额（利息）
//		 	  	  		repayMent.setBpenalty(0.00);//提前还款欠收利息 提前还款
//		 	  	  		repayMent.setOverdueamount(0.00);//逾期滞纳金额 注意是否有代偿
//		 	  	  		repayMent.setInterestexpense(interestexpense);//投标利息的管理费 投资人—》平台
//		 	  	  		repayMent.setIsproxyrepay((short)2);//是否代偿（1是，2否）
//		 	  	  		repayMent.setFee(interestexpense);//还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额
//  		 	  	  		repayMent.setRepaystatus((short)1);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//  		 	  	     	repayMent.setIsaudit((short)1);//是否审核  0不需要审核 1待审核 2审核通过 3审核不通过
//		 	  	  		repayMent.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
//		 	  	  		repayMent.setRtime(repayday);//还款时间
//		 	  	  		repayMent.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
//		 	  	   		repayMent.setPaycompany("汇付天下");//托管通道公司（汇付天下）
//		 	  	   		repayMent.setPlanstatus((short)1);//还款计划状态(1有效，2无效)
//		 	  	   		repayMent.setRestprincipal(restprincipal);//剩余本金
//		 	  	 		/*******************标的分期还款计划结束*****************************************/
//	 	  				int count = 0;
//	 	  				count = repayMentServiceI.insertSelective(repayMent);
//	  			} 
//	  			
//	  			if(isaintexpBoolean){//设置了标的利息管理费收取
//	  				InterestExpenseRecord interestExpenseRecord3 = null;
//	  			    if(repayMent2 != null){
//	  			    	interestExpenseRecord3 = interestExpenseRecordServiceI.findInterestExpenseRecordByRorderno(repayMent2.getRorderno());
//	  			    }
//	  				/**********标的利息管理费记录开始*************/
// 					if(interestExpenseRecord3 == null){//数据库没有这条数据
//						InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
//						interestExpenseRecord.setIeorderno(StringUtil.getNo());//利息管理费流水号
//						interestExpenseRecord.setRorderno(rorderno);//还款流水号
//						if(interestExpense2 != null){
//							interestExpenseRecord.setIeid(interestExpense2.getId());//利息管理费设置表Id  
//						}
//						interestExpenseRecord.setTenderid(tenderItem.getId());//标号ID
//						if(interestExpense2.getIerecmanid() != null){
// 							interestExpenseRecord.setIntexpmanid(interestExpense2.getIerecmanid());//利息管理费收款人ID 汇付天下没有这个接口
//						}
//						interestExpenseRecord.setInvestorid(userTender.getOutaccountid());//投资人ID
//						interestExpenseRecord.setIntexpfee(interestexpense);//利息管理费
//						interestExpenseRecord.setIetype(ietype);//利息管理费收取类型（1百分比，2最低，3最高）
//						interestExpenseRecord.setIsdeal((short)0);//是否处理（0否，1是）
//						interestExpenseRecord.setMadetime(new Date());//创建时间
//						interestExpenseRecord.setPlanstatus((short)1);/*利息管理费收取计划状态 1有效 2 无效*/
//	   					interestExpenseRecord.setIeproperty((short)1); /*管理费属性 1正常投标2 债权转让后*/
//						interestExpenseRecordServiceI.insertSelective(interestExpenseRecord);
//					}
//					/**********标的利息管理费记录结束*************/
//	  			}
//	 		}else{
//	 	 			for(int i = 1 ;i <= totalMonth;i++){
//	  	 				 if(i == totalMonth){//最后一期 countInterestexpense
//		 					lasePeriodBoolean = true;
//		 				 }
//		 				 if(repaymentproSrt.equals((short)2) && !dayorMonthBoolean){//等额本金
//		 					if(lasePeriodBoolean){//还款最后一期
//			 	  				monthPrincipal = FixedBasisMortgageUtil.getLastMonthPrincipal(totalPrincipal, totalMonth, scale);//每月还款本金
//			 			    	Map<Integer,BigDecimal> monthInterest = FixedBasisMortgageUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//			 			    	monthPrincipalDou = monthPrincipal;//每月还款本金
//			 			    	monthInterestDou  = monthInterest.get(i).doubleValue();//每月还款利息
//			 	  			}else{
//			 	  				monthPrincipal = FixedBasisMortgageUtil.getMonthPrincipal(totalPrincipal, totalMonth, scale);//每月还款本金
//			 			    	Map<Integer,BigDecimal> monthInterest = FixedBasisMortgageUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//			 			    	monthPrincipalDou = monthPrincipal;//每月还款本金
//			 			    	monthInterestDou  = monthInterest.get(i).doubleValue();//每月还款利息
//			 	  			}
//					    }else if(repaymentproSrt.equals((short)3) && !dayorMonthBoolean){//等额本息
//					    	if(lasePeriodBoolean){//还款最后一期
//					    		monthPrincipalDou = FixedPaymentMortgageUtil.getLastMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
//					    		Map<Integer,BigDecimal> monthInterest =  FixedPaymentMortgageUtil.getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
//					    		monthInterestDou =  monthInterest.get(i).doubleValue();//每月还款利息
//					    	}else{
//			 		    		Map<Integer,BigDecimal> monthPrincipalMap = FixedPaymentMortgageUtil.getMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
//			 		    		Map<Integer,BigDecimal> monthInterest =  FixedPaymentMortgageUtil.getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
//			 		    		monthPrincipalDou = monthPrincipalMap.get(i).doubleValue();//每月还款本金
//			 		    		monthInterestDou =  monthInterest.get(i).doubleValue();//每月还款利息
//					    	}
//					    }else if(repaymentproSrt.equals((short)4) || dayorMonthBoolean){//按期付息到期还本 
//					    	if(lasePeriodBoolean){//还款最后一期
//					    		  monthPrincipalDou = totalPrincipal;//还款总本金
//				   		    	  monthInterestDou =  OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//					    	}else{
//					    		  monthPrincipalDou = 0.00;//每月还款本金
//				   		    	  monthInterestDou = OneTimeServicingUtil.getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);//每月还款利息
//					    	}
//					    } 
//		 				
//		 				monthPrincipalDou = Double.parseDouble(df.format(monthPrincipalDou));//还款本金
//		 				monthInterestDou  = Double.parseDouble(df.format(monthInterestDou));//还款利息
//		 				
//		 				if(i == 1){
//  		 					repayday = retdate;//还款日期
//		 					restprincipal = totalPrincipal - monthPrincipalDou;//剩余还款本金
//				  		}else{
//				  			repayday = DateUtil.addMonth(tenderItem.getRetdate(), i-1);//还款日期
//				  			Map<String,Object> maps3 = new HashMap<String,Object>();
//				  			maps3.put("tenderid", tenderItem.getId());//标的ID
//				  			maps3.put("utorderno", utorderno);//投标订单号 根据具体投标记录对应还款
//				  			maps3.put("periods", i-1);//分期还款计划期数
//				  			RepayMent repayMent3 = repayMentServiceI.findRepayMentByConditions(maps3);//分期还款计划信息
//				  		    Assert.notNull(repayMent3, "'repayMent3' 不能为null");
//				  			restprincipal = Double.parseDouble(df.format(repayMent3.getRestprincipal() - monthPrincipalDou));//每期剩余还款本金
// 				  		}
//		 				
//		 				currentpi  = Double.parseDouble(df.format(monthInterestDou +  monthPrincipalDou));//当期本息 (当期总的本+息)
//		 				rorderno = StringUtil.getNo();
//		 				Map<String,Object> maps2 = new HashMap<String,Object>();
//		 				maps2.put("tenderid", tenderItem.getId());//标的ID
//		 				maps2.put("utorderno", utorderno);//投标订单号 根据具体投标记录对应还款
//		 				maps2.put("periods", i);//分期还款计划期数
//		 				repayMent2 = repayMentServiceI.findRepayMentByConditions(maps2);//分期还款计划信息
//		 	  			if(repayMent2 == null){//防止重复添加
//		 	  				/*************************标的分期还款计划开始***********************************/
//			 	  	  		RepayMent repayMent = new RepayMent();
//			 	  	  		repayMent.setRorderno(rorderno);//还款流水号
// 			 	  	  		repayMent.setOutaccountid(outaccountid);//借款用户ID
//			 	  	  		repayMent.setInaccountid(inaccountid);//投资用户ID
//			 	  	  		repayMent.setProxyaccountid(proxyaccountid);//代还款人ID
// 			 	  	  		repayMent.setUtorderno(utorderno);//投标订单号 根据具体投标记录对应还款
//			 	  	  		repayMent.setTenderid(tenderItem.getId());//标号ID
//			 	  	  		repayMent.setPeriods(i);//还款期数（第几期）
//			 	  	   		repayMent.setIsdarepay((short)0);//是否债转还款(投标记录发生过债转 1是0否)
//			 	  	  		repayMent.setRmode((short)1);//还款模式（1人工，2系统，3线下）
//			 	  	  		repayMent.setRprincipalint(currentpi);//还款金额（本息）
//			 	  	  		repayMent.setRamount(monthPrincipalDou);//还款金额（本金）
//			 	  	  		repayMent.setRinterest(monthInterestDou);//还款金额（利息）
//			 	  	  		repayMent.setBpenalty(0.00);//提前还款欠收利息 提前还款
//			 	  	  		repayMent.setOverdueamount(0.00);//逾期滞纳金额 注意是否有代偿
//			 	  	  		repayMent.setInterestexpense(interestexpense);//投标利息的管理费 投资人—》平台
//			 	  	  		repayMent.setIsproxyrepay((short)2);//是否代偿（1是，2否）
//			 	  	  		repayMent.setFee(interestexpense);//还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额
//  			 	  	  		repayMent.setRepaystatus((short)1);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
// 			 	  	    	repayMent.setIsaudit((short)1);//是否审核  0不需要审核 1待审核 2审核通过 3审核不通过
//			 	  	  		repayMent.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
//			 	  	  		repayMent.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
//			 	  	   		repayMent.setPaycompany("汇付天下");//托管通道公司（汇付天下）
//			 	  	   	    repayMent.setRtime(repayday);//还款时间
//			 	  	   		repayMent.setRestprincipal(restprincipal);//剩余本金
//			 	  	   		repayMent.setPlanstatus((short)1);//还款计划状态(1有效，2无效)
//			 	  	 		/*******************标的分期还款计划结束*****************************************/
//		 	  				int count = 0;
//		 	  				count = repayMentServiceI.insertSelective(repayMent);
//	 	 	  			}
//		 	  			if(isaintexpBoolean){//设置了标的利息管理费收取
//		 	  				interestexpense = Double.parseDouble(df.format(countInterestexpense / totalMonth));//利息管理费
//		 	  				if(lasePeriodBoolean){//最后一期
//		 	  					interestexpense = Double.parseDouble(df.format(countInterestexpense - countInterestexpense1));
//		 	  				}else{
//		 	  					countInterestexpense1 += interestexpense;
//		 	  					countInterestexpense1 = Double.parseDouble(df.format(countInterestexpense1));
//  		 	  				}
//			 	  			InterestExpenseRecord interestExpenseRecord3 = null;
//			  			    if(repayMent2 != null){
//			  			    	interestExpenseRecord3 = interestExpenseRecordServiceI.findInterestExpenseRecordByRorderno(repayMent2.getRorderno());
//			  			    }
//			  				/**********标的利息管理费记录开始*************/
//		 					if(interestExpenseRecord3 == null){//数据库没有这条数据
//								InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
//								interestExpenseRecord.setIeorderno(StringUtil.getNo());//利息管理费流水号
//								interestExpenseRecord.setRorderno(rorderno);//还款流水号
//								if(interestExpense2 != null){
//									interestExpenseRecord.setIeid(interestExpense2.getId());//利息管理费设置表Id  
//								}
//								interestExpenseRecord.setTenderid(tenderItem.getId());//标号ID
//								if(interestExpense2.getIerecmanid() != null){
//		 							interestExpenseRecord.setIntexpmanid(interestExpense2.getIerecmanid());//利息管理费收款人ID 汇付天下没有这个接口
//								}
//								interestExpenseRecord.setInvestorid(userTender.getOutaccountid());//投资人ID
//								interestExpenseRecord.setIntexpfee(interestexpense);//利息管理费
//								interestExpenseRecord.setIetype(ietype);//利息管理费收取类型（1百分比，2最低，3最高）
//								interestExpenseRecord.setIsdeal((short)0);//是否处理（0否，1是）
//								interestExpenseRecord.setMadetime(new Date());//创建时间
//								interestExpenseRecord.setPlanstatus((short)1);/*利息管理费收取计划状态 1有效 2 无效*/
//			   					interestExpenseRecord.setIeproperty((short)1); /*管理费属性 1正常投标2 债权转让后*/
//								interestExpenseRecordServiceI.insertSelective(interestExpenseRecord);
//							}
//							/**********标的利息管理费记录结束*************/
//		 	  			}
// 					}
//			}
//		}
// 		 
//  	}
// 	
// 	
 	
//List<InterestExpense> interestExpenses = null;//利息管理设置
//UserRedEnvelope envelope 				= null;//用户红包对象
//InterestExpense buyinterestExpense2 	= null;//债权承接人利息管理费收取
//InterestExpense selinterestExpense2 	= null;//债权转让人利息管理费收取
//UserInterestRateCoupon  userInterestRateCoupon = null;//用户使用加息券对象
//Double rptotalintCount	= 0.00;//还款本息总和(真本金+类现金+真本金利息+假现金利息+类现金利息+加息券利息)
//Double interestCount		= 0.00;//还款利息总和(真本金利息+假现金利息+类现金利息+加息券利息)
//Double rptotalamountCount	= 0.00;//还款本金总和(真本金+类现金)
//Double rptotalamount	= 0.00;//当期还款总金额(真本金+类现金)	
//Double rptotalint	= 0.00;//当期还款总利息	(真现金利息+假现金利息+类现金利息+加息券利息)	
//Double ontRptotalintCount	= 0.00;//当期还款本息	 	
//Double ramountCount	= 0.00;//真现金总和
//Double rlvoucherCount	 = 0.00;//假现金总和
//Double rvoucherCount	= 0.00;//类现金总和
//Double icrateCount	= 0.00;//加息券利率总和
//Double rinterestCount	= 0.00;//真现金的利息总和
//Double rlvoucherintCount	= 0.00;//假现金的利息总和
//Double rvoucherintCount	= 0.00;//类现金的利息总和
//Double rintfeeCount	= 0.00;//加息劵利息总和
//Double ramount	= 0.00;//当期还款真现金
//Double rlvoucher	= 0.00;//当期还款假现金
//Double rvoucher	= 0.00;//当期还款类现金
//Double rinterest	= 0.00;//当期还款真现金的利息
//Double rlvoucherint	= 0.00;//当期还款假现金的利息
//Double rvoucherint  = 0.00;//当期还款类现金的利息
//Double rintfee = 0.00;//当期加息卷的利息
//Double restprincipal	= 0.00;//剩余本金
//Double restvoucher	= 0.00;//剩余类现金
//Double restlvoucher	= 0.00;//剩余假现金
//Double restamountintprofit	= 0.00;//剩余本金产生的利息 （原收益+剩余加息劵收益）*本金
//Double restvoucherintprofit	= 0.00;//剩余类现金产生的利息 （原收益+加息劵收益）*剩余类现金
//Double restlvoucherintprofit	= 0.00;//剩余假现金产生的利息 （原收益+加息劵收益）*剩余假现金
//Double restintprofit	= 0.00;//剩余加息卷收益的利息
//Double buyinterestexpense = 0.00;//债权承接人利息管理费
//Double selinterestexpense = 0.00;//债权转让利息管理费
//Double selcountInterestexpense = 0.00;//转让人总利息管理费
//Double buycountInterestexpense = 0.00;//承接人总利息管理费
//boolean isClassEnvelopeBoolean= false;//是否使用了类现金 false 没有使用
//boolean isFalseEnvelopeBoolean= false;//是否使用了假现金 false 没有使用
//boolean isIcrateBoolean= false;//是否使用了加息券 false 没有使用
//boolean isaintexpBoolean 	= false;//是否设置利息管理费 默认不是
//boolean dayorMonthBoolean = false;//还款计划是否是一期 默认不是
//boolean lasePeriodBoolean = false;//是否最后一期 默认不是
//boolean isDebtAttornBoolean = false;//是否全部债权转让 默认不是
//Short selietype = 1;//债权转让人利息管理费收取类型（1百分比，2最低，3最高）
//Short buygietype = 1;//债权承接人利息管理费收取类型（1百分比，2最低，3最高）
//DecimalFormat df = new DecimalFormat("################0.00");//格式化金额
  
 	
 	
 	
 	
 	
 	
// 	for(RepayMent repayMent : repayMents){
//		 Double intprofit 				= 0.00;//加息券收益
//		 Double voucherprofit 			= 0.00;//类现金收益
//		 Double likevoucherprofit 		= 0.00;//假现金收益
//		 boolean isRintfeeBoolean		= false;//是否产生加息劵利息
//		 boolean isRlvoucherintBoolean	= false;//是否产生假现金的利息
//		 boolean isRvoucherintBoolean	= false;//是否产生类现金的利息
//		 if(repayMent.getRintfee() > 0){//当期加息劵利息
//			 intprofit = repayMent.getRintfee(); 
//			 isRintfeeBoolean   = true;
//		 }
//		 if(repayMent.getRlvoucherint() > 0){//当期还款假现金的利息
//			likevoucherprofit = repayMent.getRlvoucherint();
//			isRlvoucherintBoolean = true;
//		 }
//		 if(repayMent.getRvoucherint() > 0){//当期还款类现金的利息
//			voucherprofit = repayMent.getRvoucherint();
//			isRvoucherintBoolean  = true;
//		 }
//		 if(isRintfeeBoolean || isRlvoucherintBoolean || isRvoucherintBoolean){
//			PlusPayoutRecord payoutRecord = plusPayoutRecordServiceI
//					.findPlusPayoutRecordByRorderno(repayMent.getRorderno());
//			 if(payoutRecord == null){
// 				 Plus plus  = plusServiceI.findPlusByTid(repayMent.getTenderid());
//				 UserTender tender = userTenderServiceI.findUserTenderByOrderno(repayMent.getUtorderno());
//				 Assert.notNull(plus.getId(), "标的增益设置表Id 不能为null");
//				 Assert.notNull(repayMent.getTenderid(), "标号ID 不能为null");
//				 Assert.notNull(tender.getId(), "投标ID 不能为null");
//				 Assert.notNull(repayMent.getRorderno(), "还款流水号 不能为null");
//				 Assert.notNull(clearmode, "clearmode清算方式 不能为null");
//   			 PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
// 				 plusPayoutRecord.setPporderno(StringUtil.getNo());  //增益清算流水号
// 				 plusPayoutRecord.setPpid(plus.getId());  //标的增益设置表Id
// 				 plusPayoutRecord.setTenderid(repayMent.getTenderid());  //标号ID
// 				 plusPayoutRecord.setUsetenderid(tender.getId());  //投标id
// 				 plusPayoutRecord.setInvestorid(repayMent.getInaccountid());  //投资人ID
// 				 plusPayoutRecord.setIntprofit(intprofit);  //加息券收益
// 				 plusPayoutRecord.setVoucherprofit(voucherprofit);  //类现金收益
// 				 plusPayoutRecord.setLikevoucherprofit(likevoucherprofit);  //假现金收益
// 				 plusPayoutRecord.setClearmode(clearmode);  //清算方式（1结标，2首期，3按期，4尾期）
// 				 plusPayoutRecord.setIsgrant((short)0);  //是否发放(0否，1是,2处理中)
// 				 plusPayoutRecord.setIsblending((short)0);  //是否系统勾兑（0未勾兑，1已勾兑）
// 				 plusPayoutRecord.setIsmanblending((short)0);  //是否人工勾兑（0未勾兑，1已勾兑）
// 				 plusPayoutRecord.setPaycompany("汇付天下");  //托管通道公司（汇付天下）
// 				 plusPayoutRecord.setMadetime(new Date());  //创建时间
// 				 plusPayoutRecord.setIsaudit((short)0);  //是否审核 0 未审核 1 审核通过 2 审核不通过
// 				 plusPayoutRecord.setRorderno(repayMent.getRorderno());  //还款流水号
// 				 plusPayoutRecordServiceI.insertSelective(plusPayoutRecord);
//			 }
//		 }
//		}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
}
