package com.ptpl.controller.moneymoremore.model;

/**
 * 网贷平台 转账提交参数
 * 
 */
public class LoanSubmitInfoBean
{
	/*
	 * 转账列表
	 */
	private String LoanJsonList;
	
	/*
	 * 平台乾多多标识
	 */
	private String PlatformMoneymoremore;
	
	/*
	 * 转账类型
	 */
	private Integer TransferAction;
	
	/*
	 * 操作类型
	 */
	private Integer Action;
	
	/*
	 * 转账方式
	 */
	private Integer TransferType;
	
	/*
	 * 到账时间
	 */
	private Integer ArrivalTime;
	
	/*
	 * 通过是否需要审核
	 */
	private Integer NeedAudit;
	
	/*
	 * 是否半自动批处理(暂不可用)
	 */
	private Integer DelayTransfer;
	
	/*
	 * 随机时间戳
	 */
	private String RandomTimeStamp;
	
	/*
	 * 备注1
	 */
	private String Remark1;
	/*
	 * 备注2
	 */
	private String Remark2;
	/*
	 * 备注3
	 */
	private String Remark3;
	
	/*
	 * 页面返回网址
	 */
	private String ReturnURL;
	
	/*
	 * 后台通知网址
	 */
	private String NotifyURL;
	/*
	 * 签名信息
	 */
	private String SignInfo;
	
	public String getLoanJsonList() {
		return LoanJsonList;
	}
	public void setLoanJsonList(String loanJsonList) {
		LoanJsonList = loanJsonList;
	}
	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}
	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}
	public Integer getTransferAction() {
		return TransferAction;
	}
	public void setTransferAction(Integer transferAction) {
		TransferAction = transferAction;
	}
	public Integer getAction() {
		return Action;
	}
	public void setAction(Integer action) {
		Action = action;
	}
	public Integer getTransferType() {
		return TransferType;
	}
	public void setTransferType(Integer transferType) {
		TransferType = transferType;
	}
	public Integer getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(Integer arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public Integer getNeedAudit() {
		return NeedAudit;
	}
	public void setNeedAudit(Integer needAudit) {
		NeedAudit = needAudit;
	}
	public Integer getDelayTransfer() {
		return DelayTransfer;
	}
	public void setDelayTransfer(Integer delayTransfer) {
		DelayTransfer = delayTransfer;
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
	public String getReturnURL() {
		return ReturnURL;
	}
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	public String getNotifyURL() {
		return NotifyURL;
	}
	public void setNotifyURL(String notifyURL) {
		NotifyURL = notifyURL;
	}
	public String getSignInfo() {
		return SignInfo;
	}
	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}
		
}
