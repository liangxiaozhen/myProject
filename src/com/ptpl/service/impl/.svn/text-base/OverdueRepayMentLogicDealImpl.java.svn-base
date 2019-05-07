package com.ptpl.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ptpl.model.DividedPayments;
import com.ptpl.model.OverdueFeeRate;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.OverdueFeeRateServiceI;
import com.ptpl.service.OverdueRepayMentLogicDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.DateUtil;
 /**
 * 
* @ClassName: OverdueRepayMentLogicDealImpl 
* @Package com.ptpl.service.impl 
* @Description: TODO(逾期还款逻辑处理工具接口service实现类) 
* @author cjm
* @date 2017年2月9日 下午6:22:04 
* @version V1.0
 */
public  class OverdueRepayMentLogicDealImpl implements OverdueRepayMentLogicDealI {
 
	@Autowired
	private TenderItemServiceI tenderItemServiceI;
	
	@Autowired
	private DividedPaymentsServiceI dividedPaymentsServiceI;
	
	@Autowired
	private RepayMentServiceI repayMentServiceI;
	
	@Autowired
	private OverdueFeeRateServiceI overdueFeeRateServiceI;//逾期滞纳金费率设置Service
	/**
	 * 
	* @Title: checkOverdueByRepayMents 
	* @Description: TODO(判断前面的期数是否还款完成) 
	* @param @param repayMents
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	public boolean checkOverdueByRepayMents(List<RepayMent> repayMents,DividedPayments dividedPayments){
		return false;
	}
 	
	/**
	 * 
	 * @Title: isOverdue 
	 * @Description: TODO(是否逾期) 
	 * @param @return  参数说明 
	 * @return boolean 返回类型 
	 * @author cjm
	 * @throws
	 */
	public boolean isOverdue(DividedPayments dividedPayments){
		boolean flag = true;//返回false 表示逾期了 
		checkDividedPaymentParam(dividedPayments);//检查参数是否为null
 		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
		Assert.notNull(tenderItem," 'tenderItem' 标的对象不能为null");
		Assert.notNull(tenderItem.getGraceperiod(),"标的逾期宽限日不能为null");
 		Date date = new Date();//当天时间
 		Date graceDate = DateUtil.getOverdueNumDay(tenderItem, dividedPayments.getRepayday());//宽限日之后的日期
  		if(date.getTime() > graceDate.getTime()){//当天时间 大于 宽限日后的时间 逾期了
 			flag = false;
 		}
  		return flag;
 	}
  
	/**
 	 * 
 	* @Title: getOverdueDay 
 	* @Description: TODO(获取逾期天数 ) 
 	* @param @param dividedPayments  参数说明 
 	* @return void    返回类型 
 	* @author cjm
 	* @throws
 	 */
 	public Integer getOverdueDay(DividedPayments dividedPayments) {
 		Integer overdueDay = 0;//逾期天数
 		checkDividedPaymentParam(dividedPayments);//检查参数是否为null
  		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
		Assert.notNull(tenderItem," 'tenderItem' 标的对象不能为null");
		Assert.notNull(tenderItem.getGraceperiod(),"标的逾期宽限日不能为null");
 		Date date = new Date();//当天时间
 		Date graceDate = DateUtil.getOverdueNumDay(tenderItem, dividedPayments.getRepayday());//宽限日之后的日期
  		if(date.getTime() > graceDate.getTime()){//当天时间 大于 宽限日后的时间 逾期了
 			overdueDay = (int) DateUtil.getDateDifference(graceDate, date) ;
  		}
		return overdueDay;
	}
 
	 /**
	  * 
	 * @Title: getOverdueFeeRateByOverdueDay 
	 * @Description: TODO(根据逾期天数找到对应分段时间设置的逾期滞纳金费率设置) 
	 * @param @param overdueFeeRates 逾期滞纳金费率设置
	 * @param @param OverdueDay  逾期天数
	 * @param @return  参数说明 
	 * @return OverdueFeeRate    返回类型 
	 * @author cjm
	 * @throws 
	 * 
	 * 注意：
	 * see com.ptpl.model.TenderItem
	 * 请在调用之前跑判断逾期天数是否超过标的设置的逾期滞纳金计算截止天数
	 * 
	  */
	@Override
	public OverdueFeeRate getOverdueFeeRateByOverdueDay(List<OverdueFeeRate> overdueFeeRates, Integer OverdueDay) {
		OverdueFeeRate overdueFeeRate = null;
		if(overdueFeeRates != null && overdueFeeRates.size() > 0){
 			for(OverdueFeeRate overdueFeeRate2 : overdueFeeRates){
				if(overdueFeeRate2.getBeginday() <= OverdueDay
						&& overdueFeeRate2.getEndday() >= OverdueDay){
					overdueFeeRate = overdueFeeRate2;
					break;//跳出循环
				}
				
			}
		}
 		return overdueFeeRate;
	}
	
	/**
 	 * 
 	 * @Title: checkDividedPaymentParam 
 	 * @Description: TODO(验证 参数是否为null) 
 	 * @param @param dividedPayments  参数说明 
 	 * @return void    返回类型 
 	 * @author cjm
 	 * @throws
 	 */
	public void checkDividedPaymentParam(DividedPayments dividedPayments){
		Assert.notNull(dividedPayments," 'dividedPayments' 借款人具体还款计划对象不能为null");
		Assert.notNull(dividedPayments.getRepayday(), "借款人具体还款计划还款日不能为null");
		Assert.notNull(dividedPayments.getTenderid(), "借款人具体还款计划还款标号ID不能为null");
		Assert.notNull(dividedPayments.getPeriods(),  "借款人具体还款计划还款期数不能为null");
 	}

	/**
	 * 
	* @Title: CheckBeforeIsRepayMentByDividedPayments 
	* @Description: TODO(检查之前是否还款) 
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public boolean CheckBeforeIsRepayMentByDividedPayments(DividedPayments dividedPayment) {
		boolean  flag = true;
		if(dividedPayment == null){
			flag = false;
			return flag;
		}
		DividedPayments dividedPayment2 = new DividedPayments();
		dividedPayment2.setTenderid(dividedPayment.getTenderid());
		List<DividedPayments> dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment2);
		for(DividedPayments dividedPayments2 : dividedPayments){
			if(dividedPayments2.getPeriods() < dividedPayment.getPeriods()){
				if(dividedPayments2.getIscomplete().equals((short)0) 
						|| dividedPayments2.getIscomplete().equals((short)2)){
					//是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)
					//这里不对（部分还款）进行验证 后续操作进验证
					flag = false;
					break;
				}
			}
		}
  		return flag;
	}

	/**
	 * 
	* @Title: calculateOverdueAmount 
	* @Description: TODO(计算逾期滞纳金   ) 
	* @param @param repayMent  投资人还款计划
	* @param @param tenderItem  标的设置
	* @param @param dividedPayments  借款人还款计划
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	@Override
	public Map<String, Object> calculateOverdueAmount(RepayMent repayMent, TenderItem tenderItem,DividedPayments dividedPayments) {
 		Map<String,Object> hashMap = new HashMap<>();
 		if(repayMent == null){
 			hashMap.put("result", "fail");
 			hashMap.put("resultCode", "repayMent_null");
 			hashMap.put("message", "提示：操作失败！投资人还款计划信息找不到！请重新操作或联系客服！");
 			return hashMap;
 		}
 		
 		if(tenderItem == null || tenderItem.getOcmaxday() == null){
 			hashMap.put("result", "fail");
 			hashMap.put("resultCode", "tenderItem_null");
 			hashMap.put("message", "提示：操作失败！标的设置信息找不到！请重新操作或联系客服！");
 			return hashMap;
 		}
 		
 		if(dividedPayments == null){
 			hashMap.put("result", "fail");
 			hashMap.put("resultCode", "dividedPayments_null");
 			hashMap.put("message", "提示：操作失败！借款人还款计划信息找不到！请重新操作或联系客服！");
 			return hashMap;
 		}
 		
 		Integer overdueDay = getOverdueDay(dividedPayments);//逾期天数
 		if(tenderItem.getOcmaxday().intValue() >= overdueDay){//逾期滞纳金计算截止天数(逾期后 超过该天数  不再计算滞纳金)
 			overdueDay = tenderItem.getOcmaxday().intValue();
 		}
 		
 		List<OverdueFeeRate> overdueFeeRates = overdueFeeRateServiceI.findOverdueFeeRatesByTid(tenderItem.getId());
 		if(!(overdueFeeRates.size() > 0)){
 			hashMap.put("result", "fail");
 			hashMap.put("resultCode", "overdueFeeRateSize_null");
 			hashMap.put("message", "提示：操作失败！逾期滞纳金费率设置信息找不到！请重新操作或联系客服！");
 			return hashMap;
 		}
 		
 		OverdueFeeRate overdueFeeRate = null;//逾期滞纳金费率设置对象
 		DecimalFormat df 		= new DecimalFormat("###################0.00");
 		Double restocamount     = 0.00; //剩余滞纳金 剩余本金产生的滞纳金
 		Double vrestocamount    = 0.00; //剩余类现金滞纳金 剩余类现金产生的滞纳金
 		Double disableocamount  = 0.00;	//失效滞纳金 失效本金产生的滞纳金
 		Double disablevocamount = 0.00; //失效类现金滞纳金 失效类现金产生的滞纳金
 		Double ramount 			= 0.00; //当期现金产生的滞纳金
 		Double rvoucher 		= 0.00; //当期类现金产生的滞纳金
  		/*如果有多条肯定不是等比例设置*/
 		if(overdueFeeRates.size() == 1){
 			overdueFeeRate = overdueFeeRates.get(0);
 			if(overdueFeeRate.getBeginday() < overdueDay){
 				if(overdueFeeRate.getEndday().intValue() < overdueDay){
 					/*如果逾期天数 超过逾期滞纳金结束时间  以结束时间为主*/
 					overdueDay = overdueFeeRate.getEndday().intValue();
 				}
 				
 				if(overdueFeeRate.getFeerate() == null){
 					hashMap.put("result", "fail");
 		 			hashMap.put("resultCode", "feerate_null");
 		 			hashMap.put("message", "提示：操作失败！逾期滞纳金费率设置滞纳金率找不到！请重新操作或联系客服！");
 		 			return hashMap;
 				}
 				
 				restocamount  		= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getRestprincipal())), overdueDay)));//剩余滞纳金 剩余本金产生的滞纳金
 				vrestocamount 		= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getRestvoucher())), overdueDay)));//剩余类现金滞纳金 剩余类现金产生的滞纳金
 				disableocamount 	= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getDisableocamount())), overdueDay)));//失效滞纳金 失效本金产生的滞纳金
 				disablevocamount 	= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getDisablevoucher())), overdueDay)));//失效类现金滞纳金 失效类现金产生的滞纳金
 				ramount 			= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getRamount())), overdueDay)));//当期现金产生的滞纳金
 				rvoucher 			= Double.valueOf(df.format(Arith.mul(Arith.mul(overdueFeeRate.getFeerate(), (repayMent.getRvoucher())), overdueDay)));//当期类现金产生的滞纳金
  				/*暂时不考虑做这个*/
 			}
  		}else{
 			
 			
 		}
  		return hashMap;
	}
}
