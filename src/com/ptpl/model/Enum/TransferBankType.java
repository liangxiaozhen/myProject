package com.ptpl.model.Enum;

public enum TransferBankType {
	
	/**
	 * 等待付款
	 */
	Pending,
	/**
	 * 付款成功
	 */
	Success,
	/**
	 * 付款失败
	 */
	Failed,
	/**
	 * 处理中
	 */
	Processing,
	/**
	 * 已退回
	 */
	Returned,
	/**
	 * 审核
	 */
	Audit,
	/**
	 * 银行处理中
	 */
	ProcessBank,
	/**
	 * 银企直连审核
	 */
	BankComAudit,
	/**
	 * 银企直连成功
	 */
	BankComSuccess,
	/**
	 * 异常01   处理中异常
	 */ 
	Abnormal01,
	/**
	 * 异常02  结果异常
	 */
	Abnormal02,
	/**
	 * 已校验（针对半自动）
	 */
	Checked;
}
