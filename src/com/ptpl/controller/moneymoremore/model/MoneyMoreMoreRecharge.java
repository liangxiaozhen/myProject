package com.ptpl.controller.moneymoremore.model;

public class MoneyMoreMoreRecharge {
	
	private  String RechargeMoneymoremore;//充值人乾多多标识
	private  String PlatformMoneymoremore;//平台乾多多标识
	private  String LoanNo;//乾多多流水号
	private  String OrderNo;//平台的充值订单号
	private  String Amount;//金额
	private  String Fee;//用户承担手续费
	private  String FeePlatform;//平台承担手续费
	private  String RechargeType;//充值类型
	private  String FeeType;//手续费类型
	private  String CardNoList;//当前绑定的银行卡号列表
	private  String RandomTimeStamp;//随机时间戳
	private  String Remark1;
	private  String Remark2;
	private  String Remark3;
	private	 String ResultCode;//返回码
	private	 String Message;//返回信息
	private  String ReturnTimes;//返回次数
	private  String SignInfo;//签名信息
	private  String CardNo;//银行卡号
	public String getRechargeMoneymoremore() {
		return RechargeMoneymoremore;
	}
	public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
		RechargeMoneymoremore = rechargeMoneymoremore;
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
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getFeePlatform() {
		return FeePlatform;
	}
	public void setFeePlatform(String feePlatform) {
		FeePlatform = feePlatform;
	}
	public String getRechargeType() {
		return RechargeType;
	}
	public void setRechargeType(String rechargeType) {
		RechargeType = rechargeType;
	}
	public String getFeeType() {
		return FeeType;
	}
	public void setFeeType(String feeType) {
		FeeType = feeType;
	}
	public String getCardNoList() {
		return CardNoList;
	}
	public void setCardNoList(String cardNoList) {
		CardNoList = cardNoList;
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
	public String getSignInfo() {
		return SignInfo;
	}
	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	@Override
	public String toString() {
		return "MoneyMoreMoreRecharge [RechargeMoneymoremore=" + RechargeMoneymoremore + ", PlatformMoneymoremore="
				+ PlatformMoneymoremore + ", LoanNo=" + LoanNo + ", OrderNo=" + OrderNo + ", Amount=" + Amount
				+ ", Fee=" + Fee + ", FeePlatform=" + FeePlatform + ", RechargeType=" + RechargeType + ", FeeType="
				+ FeeType + ", CardNoList=" + CardNoList + ", RandomTimeStamp=" + RandomTimeStamp + ", Remark1="
				+ Remark1 + ", Remark2=" + Remark2 + ", Remark3=" + Remark3 + ", ResultCode=" + ResultCode
				+ ", Message=" + Message + ", ReturnTimes=" + ReturnTimes + ", SignInfo=" + SignInfo + ", CardNo="
				+ CardNo + "]";
	}
	
}
