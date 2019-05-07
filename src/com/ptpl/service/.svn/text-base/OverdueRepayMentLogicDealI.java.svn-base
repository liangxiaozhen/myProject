package com.ptpl.service;

import java.util.List;
import java.util.Map;

import com.ptpl.model.DividedPayments;
import com.ptpl.model.OverdueFeeRate;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;

/**
 * 
* @ClassName: OverdueRepayMentLogicDealI 
* @Package com.ptpl.service 
* @Description: TODO(逾期还款 逻辑处理) 
* @author cjm
* @date 2017年2月9日 下午6:18:19 
* @version V1.0
 */
public interface OverdueRepayMentLogicDealI {
 	 
	/**
	 * 
	* @Title: isOverdue 
	* @Description: TODO(是否逾期) 
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author cjm
	* @throws
	 */
	public boolean isOverdue(DividedPayments dividedPayments);
	
	/**
	 * 
	* @Title: getOverdueDay 
	* @Description: TODO(计算 逾期天数) 
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return Integer    返回类型 
	* @author cjm
	* @throws
	 */
	public Integer getOverdueDay(DividedPayments dividedPayments);
	
 	 /**
 	  * 
 	 * @Title: getOverdueFeeRateByOverdueDay 
 	 * @Description: TODO(根据逾期天数找到对应分段时间设置的逾期滞纳金费率设置) 
 	 * @param @param overdueFeeRates
 	 * @param @param OverdueDay
 	 * @param @return  参数说明 
 	 * @return OverdueFeeRate    返回类型 
 	 * @author cjm
 	 * @throws
 	  */
	public OverdueFeeRate  getOverdueFeeRateByOverdueDay(List<OverdueFeeRate> overdueFeeRates,Integer OverdueDay);
	
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
	public boolean CheckBeforeIsRepayMentByDividedPayments(DividedPayments dividedPayments);
	
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
	public boolean checkOverdueByRepayMents(List<RepayMent> repayMents,DividedPayments dividedPayments);
	
	
	/**
	 * 
	* @Title: calculateOverdueAmount 
	* @Description: TODO(计算逾期滞纳金   ) 
	* @param @param repayMent  投资人还款计划
	* @param @param tenderItem  标的设置
	* * @param @param dividedPayments  借款人还款计划
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> calculateOverdueAmount(RepayMent repayMent,TenderItem tenderItem,DividedPayments dividedPayments);
	
	
}
