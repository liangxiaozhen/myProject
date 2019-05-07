package com.ptpl.model.Enum;

/*
 * 提现状态
 */
public enum WithDrawStatus {
	/**
	 * 待处理
	 */
	Pending,
	/**
	 * 成功
	 */
	Sucess,
	/**
	 * 审核
	 */
	Audit,
	/**
	 * 退回
	 */
	Return,
	/**
	 * 待平台审核
	 */
	PlatformAudit,
	/**
	 * 银企直连审核
	 */
	BankComAudit,
	/**
	 * 银企直连成功
	 */
	BankComSucess,
	/**
	 * 异常01   处理异常
	 */
	Abnormal01,
	/**
	 * 异常02   结果异常
	 */
	Abnormal02,
	/**
	 * 已核对（针对半自动）
	 */
	Checked;
}
