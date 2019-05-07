package com.ptpl.controller.huifu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ptpl.model.AheadRepay;
import com.ptpl.model.AheadRepayAward;
import com.ptpl.model.AheadRepayMode;
import com.ptpl.model.AheadRepayPlatform;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: AheadOracleTest 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(提前还款 模拟数据测试类) 
* @author cjm
* @date 2016年12月1日 下午4:55:17 
* @version V1.0
 */
public class AheadOracleTest {
	
	/**
	 * 
	* @Title: getAheadRepayPlatform 
	* @Description: TODO(标的提前还款奖励平台设置) 
	* @param @return  参数说明 
	* @return List<AheadRepayPlatform>    返回类型 
	* @author cjm
	* @throws
	 */
	public static List<AheadRepayPlatform> getAheadRepayPlatform(){
		AheadRepayPlatform aheadRepayPlatform = new AheadRepayPlatform();
		aheadRepayPlatform.setTid(new BigDecimal(1));	//标号Id
		aheadRepayPlatform.setAheadrepaypno(StringUtil.getNo());//提前还款奖励平台编号
		aheadRepayPlatform.setIsforplatformon((short)1);//还款人补偿平台开关
		aheadRepayPlatform.setAwardrecman("admin");	//奖励平台收款人
		aheadRepayPlatform.setMinallnoreceiveint(0.00);//奖励平台投资人总欠收最小利息
		aheadRepayPlatform.setMaxallnoreceiveint(10.00);//奖励平台投资人总欠收最高利息
		aheadRepayPlatform.setAwardplatquota(0.00);//奖励平台定额
		aheadRepayPlatform.setAwardplatrate(0.50);//奖励平台百份比
		aheadRepayPlatform.setAwardplatminmoney(12.00);//奖励平台最小值
		aheadRepayPlatform.setAwardplatmaxmoney(50.00);//奖励平台最大值
		aheadRepayPlatform.setIsaudit((short)1);//资金清算是否需要审核
		aheadRepayPlatform.setIstemplet((short)1);//是否为模板（0否，1是）
	 
		AheadRepayPlatform aheadRepayPlatform2 = new AheadRepayPlatform();
		aheadRepayPlatform2.setTid(new BigDecimal(2));	//标号Id
		aheadRepayPlatform2.setAheadrepaypno(StringUtil.getNo());//提前还款奖励平台编号
		aheadRepayPlatform2.setIsforplatformon((short)1);//还款人补偿平台开关
		aheadRepayPlatform2.setAwardrecman("admin");	//奖励平台收款人
		aheadRepayPlatform2.setMinallnoreceiveint(10.00);//奖励平台投资人总欠收最小利息
		aheadRepayPlatform2.setMaxallnoreceiveint(100.00);//奖励平台投资人总欠收最高利息
		aheadRepayPlatform2.setAwardplatquota(0.00);//奖励平台定额
		aheadRepayPlatform2.setAwardplatrate(0.50);//奖励平台百份比
		aheadRepayPlatform2.setAwardplatminmoney(12.00);//奖励平台最小值
		aheadRepayPlatform2.setAwardplatmaxmoney(50.00);//奖励平台最大值
		aheadRepayPlatform2.setIsaudit((short)1);//资金清算是否需要审核
		aheadRepayPlatform2.setIstemplet((short)1);//是否为模板（0否，1是）
		
		AheadRepayPlatform aheadRepayPlatform3 = new AheadRepayPlatform();
		aheadRepayPlatform3.setTid(new BigDecimal(3));	//标号Id
		aheadRepayPlatform3.setAheadrepaypno(StringUtil.getNo());//提前还款奖励平台编号
		aheadRepayPlatform3.setIsforplatformon((short)1);//还款人补偿平台开关
		aheadRepayPlatform3.setAwardrecman("admin");	//奖励平台收款人
		aheadRepayPlatform3.setMinallnoreceiveint(100.00);//奖励平台投资人总欠收最小利息
		aheadRepayPlatform3.setMaxallnoreceiveint(200.00);//奖励平台投资人总欠收最高利息
		aheadRepayPlatform3.setAwardplatquota(0.00);//奖励平台定额
		aheadRepayPlatform3.setAwardplatrate(0.50);//奖励平台百份比
		aheadRepayPlatform3.setAwardplatminmoney(12.00);//奖励平台最小值
		aheadRepayPlatform3.setAwardplatmaxmoney(50.00);//奖励平台最大值
		aheadRepayPlatform3.setIsaudit((short)1);//资金清算是否需要审核
		aheadRepayPlatform3.setIstemplet((short)1);//是否为模板（0否，1是）
		
		List<AheadRepayPlatform> aheadRepayPlatforms = new ArrayList<AheadRepayPlatform>();
		aheadRepayPlatforms.add(aheadRepayPlatform);
		aheadRepayPlatforms.add(aheadRepayPlatform2);
		aheadRepayPlatforms.add(aheadRepayPlatform3);
 		return aheadRepayPlatforms;
	}
	
	/**
	 * 
	* @Title: getaheadRepayAward 
	* @Description: TODO(标的提前还款个人奖品奖励设置) 
	* @param @return  参数说明 
	* @return List<AheadRepayAward>    返回类型 
	* @author cjm
	* @throws
	 */
	public static List<AheadRepayAward> getaheadRepayAward(){
		AheadRepayAward aheadRepayAward = new AheadRepayAward();
		aheadRepayAward.setTid(new BigDecimal(1)); //标号Id
		aheadRepayAward.setAheadrepayano(StringUtil.getNo()); //提前还款奖品奖励编号
		aheadRepayAward.setIspluscompensateon((short)1); //增益利息补偿开关
		aheadRepayAward.setMinplusnoreceiveint(0.00); //单个投资人累计增益欠收最小利息
		aheadRepayAward.setMaxplusnoreceiveint(10.00); //单个投资人累计增益欠收最高利息
		aheadRepayAward.setPlusawardtype((short)1); //增益奖励方式（1平台罚金，2平台奖励，3 1+2）
		aheadRepayAward.setPluspenaltyname("平台奖励名称"); //增益平台罚金奖励名称
		aheadRepayAward.setPluspenaltyquota(0.00); //增益平台罚金定额
		aheadRepayAward.setPluspenaltyrate(0.20); //增益平台罚金百分比（不设置定额时）
		aheadRepayAward.setPlusmaxpenalty(0.01); //增益平台罚金最大值
		aheadRepayAward.setPluspawardname("增益平台"); //增益平台奖励奖品名称
		aheadRepayAward.setPluspawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepayAward.setPluspawardquota(10.00); //增益平台奖励定额
		aheadRepayAward.setPluspawardrate(0.12); //增益平台奖励百分比
		aheadRepayAward.setPlusmaxpaward(56.00); //增益平台奖励最大值
		aheadRepayAward.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepayAward.setIstemplet((short)0); //是否为模板（0否，1是）
	 
		AheadRepayAward aheadRepayAward2 = new AheadRepayAward();
		aheadRepayAward2.setTid(new BigDecimal(2)); //标号Id
		aheadRepayAward2.setAheadrepayano(StringUtil.getNo()); //提前还款奖品奖励编号
		aheadRepayAward2.setIspluscompensateon((short)1); //增益利息补偿开关
		aheadRepayAward2.setMinplusnoreceiveint(10.00); //单个投资人累计增益欠收最小利息
		aheadRepayAward2.setMaxplusnoreceiveint(100.00); //单个投资人累计增益欠收最高利息
		aheadRepayAward2.setPlusawardtype((short)1); //增益奖励方式（1平台罚金，2平台奖励，3 1+2）
		aheadRepayAward2.setPluspenaltyname("平台奖励名称"); //增益平台罚金奖励名称
		aheadRepayAward2.setPluspenaltyquota(0.00); //增益平台罚金定额
		aheadRepayAward2.setPluspenaltyrate(0.20); //增益平台罚金百分比（不设置定额时）
		aheadRepayAward2.setPlusmaxpenalty(0.01); //增益平台罚金最大值
		aheadRepayAward2.setPluspawardname("增益平台"); //增益平台奖励奖品名称
		aheadRepayAward2.setPluspawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepayAward2.setPluspawardquota(10.00); //增益平台奖励定额
		aheadRepayAward2.setPluspawardrate(0.12); //增益平台奖励百分比
		aheadRepayAward2.setPlusmaxpaward(56.00); //增益平台奖励最大值
		aheadRepayAward2.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepayAward2.setIstemplet((short)0); //是否为模板（0否，1是）
		
		AheadRepayAward aheadRepayAward3 = new AheadRepayAward();
		aheadRepayAward3.setTid(new BigDecimal(3)); //标号Id
		aheadRepayAward3.setAheadrepayano(StringUtil.getNo()); //提前还款奖品奖励编号
		aheadRepayAward3.setIspluscompensateon((short)1); //增益利息补偿开关
		aheadRepayAward3.setMinplusnoreceiveint(100.00); //单个投资人累计增益欠收最小利息
		aheadRepayAward3.setMaxplusnoreceiveint(1000.00); //单个投资人累计增益欠收最高利息
		aheadRepayAward3.setPlusawardtype((short)1); //增益奖励方式（1平台罚金，2平台奖励，3 1+2）
		aheadRepayAward3.setPluspenaltyname("平台奖励名称"); //增益平台罚金奖励名称
		aheadRepayAward3.setPluspenaltyquota(0.00); //增益平台罚金定额
		aheadRepayAward3.setPluspenaltyrate(0.20); //增益平台罚金百分比（不设置定额时）
		aheadRepayAward3.setPlusmaxpenalty(0.01); //增益平台罚金最大值
		aheadRepayAward3.setPluspawardname("增益平台"); //增益平台奖励奖品名称
		aheadRepayAward3.setPluspawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepayAward3.setPluspawardquota(10.00); //增益平台奖励定额
		aheadRepayAward3.setPluspawardrate(0.12); //增益平台奖励百分比
		aheadRepayAward3.setPlusmaxpaward(56.00); //增益平台奖励最大值
		aheadRepayAward3.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepayAward3.setIstemplet((short)0); //是否为模板（0否，1是）
	
		List<AheadRepayAward> aheadRepayAwards = new ArrayList<AheadRepayAward>();
		aheadRepayAwards.add(aheadRepayAward);
		aheadRepayAwards.add(aheadRepayAward2);
		aheadRepayAwards.add(aheadRepayAward3);
		
	  return aheadRepayAwards;
	}
	/**
	 * 
	* @Title: getAheadRepay 
	* @Description: TODO(标的提前还款个人利息奖励设置) 
	* @param @return  参数说明 
	* @return AheadRepay    返回类型 
	* @author cjm
	* @throws
	 */
	public static List<AheadRepay> getAheadRepay(){
		AheadRepay aheadRepay  = new AheadRepay ();
		aheadRepay.setTid(new BigDecimal(1)); //标号Id
		aheadRepay.setAheadrepayno(StringUtil.getNo()); //提前还款个人利息奖励编号
		aheadRepay.setIspicompensateon((short)1); //本金利息补偿开关
		aheadRepay.setMinnoreceiveint(0.01); //单个投资人累计本金欠收最小利息
		aheadRepay.setMaxnoreceiveint(10.00); //单个投资人累计本金欠收最高利息
		aheadRepay.setAwardtype((short)3); //奖励方式（1惩罚借款人，2平台奖励，3 1+2）
		aheadRepay.setLoanpenaltyname("惩罚借款人"); //借款人罚金奖励名称
		aheadRepay.setPenaltyquota(10.00); //借款人罚金定额
		aheadRepay.setPenaltyrate(1.00); //借款人罚金百分比（不设置定额时）
		aheadRepay.setMaxpenalty(60.00); //借款人罚金最大值
		aheadRepay.setPawardname("奖品"); //平台奖励奖品名称
		aheadRepay.setPawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepay.setPawardquota(0.00); //平台奖励定额
		aheadRepay.setPawardrate(0.12); //平台奖励百分比
		aheadRepay.setMaxpaward(12.00); //平台奖励最大值
		aheadRepay.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepay.setIstemplet((short)1); //是否为模板（0否，1是）
		
		AheadRepay aheadRepay2  = new AheadRepay ();
		aheadRepay2.setTid(new BigDecimal(2)); //标号Id
		aheadRepay2.setAheadrepayno(StringUtil.getNo()); //提前还款个人利息奖励编号
		aheadRepay2.setIspicompensateon((short)1); //本金利息补偿开关
		aheadRepay2.setMinnoreceiveint(10.00); //单个投资人累计本金欠收最小利息
		aheadRepay2.setMaxnoreceiveint(100.00); //单个投资人累计本金欠收最高利息
		aheadRepay2.setAwardtype((short)3); //奖励方式（1惩罚借款人，2平台奖励，3 1+2）
		aheadRepay2.setLoanpenaltyname("惩罚借款人"); //借款人罚金奖励名称
		aheadRepay2.setPenaltyquota(0.00); //借款人罚金定额
		aheadRepay2.setPenaltyrate(0.12); //借款人罚金百分比（不设置定额时
		aheadRepay2.setMaxpenalty(60.00); //借款人罚金最大值
		aheadRepay2.setPawardname("奖品"); //平台奖励奖品名称
		aheadRepay2.setPawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepay2.setPawardquota(0.00); //平台奖励定额
		aheadRepay2.setPawardrate(0.12); //平台奖励百分比
		aheadRepay2.setMaxpaward(12.00); //平台奖励最大值
		aheadRepay2.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepay.setIstemplet((short)1); //是否为模板（0否，1是）
		
		AheadRepay aheadRepay3  = new AheadRepay ();
		aheadRepay3.setTid(new BigDecimal(3)); //标号Id
		aheadRepay3.setAheadrepayno(StringUtil.getNo()); //提前还款个人利息奖励编号
		aheadRepay3.setIspicompensateon((short)1); //本金利息补偿开关
		aheadRepay3.setMinnoreceiveint(100.00); //单个投资人累计本金欠收最小利息
		aheadRepay3.setMaxnoreceiveint(1000.00); //单个投资人累计本金欠收最高利息
		aheadRepay3.setAwardtype((short)3); //奖励方式（1惩罚借款人，2平台奖励，3 1+2）
		aheadRepay3.setLoanpenaltyname("惩罚借款人"); //借款人罚金奖励名称
		aheadRepay3.setPenaltyquota(0.00); //借款人罚金定额
		aheadRepay3.setPenaltyrate(0.12); //借款人罚金百分比（不设置定额时
		aheadRepay3.setMaxpenalty(60.00); //借款人罚金最大值
		aheadRepay3.setPawardname("奖品"); //平台奖励奖品名称
		aheadRepay3.setPawardno(StringUtil.getNo()); //平台奖励奖品编号
		aheadRepay3.setPawardquota(0.00); //平台奖励定额
		aheadRepay3.setPawardrate(0.12); //平台奖励百分比
		aheadRepay3.setMaxpaward(12.00); //平台奖励最大值
		aheadRepay3.setIsaudit((short)1); //资金清算是否需要审核
		aheadRepay3.setIstemplet((short)1); //是否为模板（0否，1是）
   		 
		List<AheadRepay> aheadRepays  = new ArrayList<AheadRepay>();
		aheadRepays.add(aheadRepay);
		aheadRepays.add(aheadRepay2);
		aheadRepays.add(aheadRepay3);
 		return aheadRepays;
	
	
	}
	/**
	 * 
	* @Title: getAheadRepayMode 
	* @Description: TODO(提前还款方式设置) 
	* @param @return  参数说明 
	* @return AheadRepayMode    返回类型 
	* @author cjm
	* @throws
	 */
	public static AheadRepayMode getAheadRepayMode(){
		AheadRepayMode aheadRepayMode = new AheadRepayMode();
		aheadRepayMode.setId(new BigDecimal(1));  //主键
		aheadRepayMode.setTid(new BigDecimal(2));  //标号id
		aheadRepayMode.setRepaytype((short)1); //还款方式(等额本金，等额本息，先息后本，一次性还本付息)
		aheadRepayMode.setArepaymode((short)2); //提前还款类型(1全部，2部分)
		aheadRepayMode.setAperiods(5);  //1当期，多期（填写具体数字）
		aheadRepayMode.setIntmode((short)1);  //还利息方式(1占天利息，2全额利息)
		aheadRepayMode.setAddman("admin");  //添加人
		aheadRepayMode.setAddtime(new Date()); //设置时间
		aheadRepayMode.setRemark("备注"); //备注
 		return aheadRepayMode;
	}
}
