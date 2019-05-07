package com.ptpl.service;

import java.util.List;
import java.util.Map;

import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.model.TenderItem;

public interface RepayMentBaseDealI {
	
	
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
	boolean checkDividedPaymentsNormalRepayMent(DividedPayments dividedPayment);

	 
	/**
	 * 
	* @Title: CheckRepayMentOverdue 
	* @Description: TODO(定时判断是否逾期  更新标的还款计划和标的具体还款计划是否逾期状态) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public void CheckRepayMentOverdue();
 	 
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
	public Map<String,Object> calculateNormalRepayemInterestManageFee(RepayMent repayMent);
	
	
	 
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
	public Map<String,Object> calculateAheadRepayemInterestManageFee(RepayMent repayMent);
	
	 
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
	public Map<String,Object> calculateOverdueRepayemInterestManageFee(RepayMent repayMent);
	 
	/**
	 * 
	* @Title: copyRepaymentDetailByRepayMent 
	* @Description: TODO(批次还款  把具体的还款信息 复制到批次详情记录里面) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public RepaymentDetail copyRepaymentDetailByRepayMent(RepayMent repayMent);
	
 	
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
	public boolean updateRepayMentRepayStatusForWaitDeal(List<RepayMent> repayMents);
	 
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
	public boolean updateRepayMentDetailRepayStatusForWaitDeal(List<RepaymentDetail> repaymentDetails);
	
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
	public boolean updateRepayMentRepayStatusForDealing(List<RepayMent> repayMent);
	
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
	public boolean updateRepayMentDetailRepayStatusForDealing(List<RepaymentDetail> repaymentDetails);
	
	/**
	 * 
	* @Title: updateRepayMentDetailRepayStatusForDealing 
	* @Description: TODO(批次还款  处理状态为取消和冻结成功) 
	* @param @param dividedPayment
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> repayMentFrzStatusDeal(TenderItem tenderItem);
}
