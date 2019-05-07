package com.ptpl.model;

import com.alibaba.fastjson.annotation.JSONField;

/**对账返回参数类*/
public class PersonSaveReconciliation {
	/**充值人乾多多标识*/
    private String RechargeMoneymoremore;
    /**平台乾多多标识*/
    private String PlatformMoneymoremore;
    /**乾多多流水号 :银行返回订单号*/
    private String LoanNo;
    /**充值订单号*/
    private String OrderNo;
    /**充值金额*/
    private String Amount;
    /**手续费*/
    private String Fee;
    /**平台承担手续费*/
    private String FeePlatform;
    /**充值时间*/
    private String RechargeTime;
    /**充值类型空.网银充值1.代扣充值2.快捷支付3.汇款充值4.企业网银*/
    private String RechargeType;
    /**手续费类型*/
    private String FeeType;
    /**充值状态0.未充值1.成功2.失败*/
    private String RechargeState;
     

	
	public String getRechargeMoneymoremore() {
		return RechargeMoneymoremore;
	}
	@JSONField(name= "RechargeMoneymoremore")
	public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
		RechargeMoneymoremore = rechargeMoneymoremore;
	}
	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}
	@JSONField(name= "PlatformMoneymoremore")
	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}
	public String getLoanNo() {
		return LoanNo;
	}
	@JSONField(name= "LoanNo")
	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	@JSONField(name= "OrderNo")
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getAmount() {
		return Amount;
	}
	@JSONField(name= "Amount")
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getFee() {
		return Fee;
	}
	@JSONField(name= "Fee")
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getFeePlatform() {
		return FeePlatform;
	}
	@JSONField(name= "FeePlatform")
	public void setFeePlatform(String feePlatform) {
		FeePlatform = feePlatform;
	}
	public String getRechargeTime() {
		return RechargeTime;
	}
	@JSONField(name= "RechargeTime")
	public void setRechargeTime(String rechargeTime) {
		RechargeTime = rechargeTime;
	}
	public String getRechargeType() {
		return RechargeType;
	}
	@JSONField(name= "RechargeType")
	public void setRechargeType(String rechargeType) {
		RechargeType = rechargeType;
	}
	public String getFeeType() {
		return FeeType;
	}
	@JSONField(name= "FeeType")
	public void setFeeType(String feeType) {
		FeeType = feeType;
	}
	public String getRechargeState() {
		return RechargeState;
	}
	@JSONField(name= "RechargeState")
	public void setRechargeState(String rechargeState) {
		RechargeState = rechargeState;
	}
	@Override
	public String toString() {
		return "PersonSaveReconciliation [RechargeMoneymoremore=" + RechargeMoneymoremore + ", PlatformMoneymoremore="
				+ PlatformMoneymoremore + ", LoanNo=" + LoanNo + ", OrderNo=" + OrderNo + ", Amount=" + Amount
				+ ", Fee=" + Fee + ", FeePlatform=" + FeePlatform + ", RechargeTime=" + RechargeTime + ", RechargeType="
				+ RechargeType + ", FeeType=" + FeeType + ", RechargeState=" + RechargeState + "]";
	}
	
}
