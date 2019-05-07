package com.ptpl.controller.moneymoremore.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 乾多多清算系统 
 * 	提现接口
 *  返回参数
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年2月25日 上午11:02:40 
 *
 */
public class LoanWithdrawsCallbackBean {
	// 提现乾多多标识
	String WithdrawMoneymoremore;
	// 平台乾多多标识
	String PlatformMoneymoremore ;
	// 乾多多流水号
	String LoanNo;
	// 平台的提现订单号
	String OrderNo;
	// 金额
	String Amount;
	// 用户承担的最高手续费
	String FeeMax;
	// 用户实际承担的手续费金额
	String FeeWithdraws;
	// 平台承担的手续费比例
	String FeePercent;
	// 平台承担的手续费金额
	String Fee;
	// 平台扣除的免费提现额
	String FreeLimit;
	// 上浮费率
	String FeeRate;
	// 平台分润
	String FeeSplitting;
	// 随机时间戳
	String RandomTimeStamp;
	// 自定义备注
	String Remark1;
	// 自定义备注
	String Remark2;
	// 自定义备注
	String Remark3;
	// 返回码
	String ResultCode;
	// 签名信息
	String SignInfo;
	/*----------------------------------*/
	// 返回信息
	String Message;
	// 返回次数
	String ReturnTimes;
	public String getWithdrawMoneymoremore() {
		return WithdrawMoneymoremore;
	}
	public void setWithdrawMoneymoremore(String withdrawMoneymoremore) {
		WithdrawMoneymoremore = withdrawMoneymoremore;
	}
	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}
	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}
	public String getLoanNo() {
		return LoanNo;
	}
	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getFeeMax() {
		return FeeMax;
	}
	public void setFeeMax(String feeMax) {
		FeeMax = feeMax;
	}
	public String getFeeWithdraws() {
		return FeeWithdraws;
	}
	public void setFeeWithdraws(String feeWithdraws) {
		FeeWithdraws = feeWithdraws;
	}
	public String getFeePercent() {
		return FeePercent;
	}
	public void setFeePercent(String feePercent) {
		FeePercent = feePercent;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getFreeLimit() {
		return FreeLimit;
	}
	public void setFreeLimit(String freeLimit) {
		FreeLimit = freeLimit;
	}
	public String getFeeRate() {
		return FeeRate;
	}
	public void setFeeRate(String feeRate) {
		FeeRate = feeRate;
	}
	public String getFeeSplitting() {
		return FeeSplitting;
	}
	public void setFeeSplitting(String feeSplitting) {
		FeeSplitting = feeSplitting;
	}
	public String getRandomTimeStamp() {
		return RandomTimeStamp;
	}
	public void setRandomTimeStamp(String randomTimeStamp) {
		RandomTimeStamp = randomTimeStamp;
	}
	public String getRemark1() {
		return Remark1;
	}
	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}
	public String getRemark2() {
		return Remark2;
	}
	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}
	public String getRemark3() {
		return Remark3;
	}
	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getSignInfo() {
		return SignInfo;
	}
	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getReturnTimes() {
		return ReturnTimes;
	}
	public void setReturnTimes(String returnTimes) {
		ReturnTimes = returnTimes;
	}
	public LoanWithdrawsCallbackBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoanWithdrawsCallbackBean(String withdrawMoneymoremore, String platformMoneymoremore, String loanNo,
			String orderNo, String amount, String feeMax, String feeWithdraws, String feePercent, String fee,
			String freeLimit, String feeRate, String feeSplitting, String randomTimeStamp, String remark1,
			String remark2, String remark3, String resultCode, String signInfo, String message, String returnTimes) {
		super();
		WithdrawMoneymoremore = withdrawMoneymoremore;
		PlatformMoneymoremore = platformMoneymoremore;
		LoanNo = loanNo;
		OrderNo = orderNo;
		Amount = amount;
		FeeMax = feeMax;
		FeeWithdraws = feeWithdraws;
		FeePercent = feePercent;
		Fee = fee;
		FreeLimit = freeLimit;
		FeeRate = feeRate;
		FeeSplitting = feeSplitting;
		RandomTimeStamp = randomTimeStamp;
		Remark1 = remark1;
		Remark2 = remark2;
		Remark3 = remark3;
		ResultCode = resultCode;
		SignInfo = signInfo;
		Message = message;
		ReturnTimes = returnTimes;
	}
	/**
	 * 组合数据原文
	 * @return
	 */
	public String getPlainStr(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(WithdrawMoneymoremore)
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(StringUtils.trimToEmpty(LoanNo))
				.append(StringUtils.trimToEmpty(OrderNo))
				.append(StringUtils.trimToEmpty(Amount))
				.append(StringUtils.trimToEmpty(FeeMax))
				.append(StringUtils.trimToEmpty(FeeWithdraws))
				.append(StringUtils.trimToEmpty(FeePercent))
				.append(StringUtils.trimToEmpty(Fee))
				.append(StringUtils.trimToEmpty(FreeLimit))
				.append(StringUtils.trimToEmpty(FeeRate))
				.append(StringUtils.trimToEmpty(FeeSplitting))
				.append(StringUtils.trimToEmpty(RandomTimeStamp))
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ResultCode));
		return buffer.toString();
	}
}
