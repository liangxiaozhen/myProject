package com.ptpl.controller.moneymoremore.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.moneymoremore.util.RsaHelper;

/**
 * 乾多多清算系统 提现接口 提交参数
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月27日 上午9:21:01
 *
 */
public class LoanWithdrawsBean {
	/* 用户乾多多标识 */
	String WithdrawMoneymoremore;
	/* 平台乾多多标识 */
	String PlatformMoneymoremore;
	/* 订单号 */
	String OrderNo;
	/* 提现金额 */
	String Amount;
	/* 用户承担的手续费 */
	String FeeQuota;
	/* 银行卡号 不加密 */
	String CardNo;
	/* 银行卡号 进行加密 */
	String encryptCardNo;
	/* 银行卡类型 */
	String CardType;
	/* 银行代码 */
	String BankCode;
	/* 开户行支行名字 */
	String BranchBankName;
	/* 开户行省份 */
	String Province;
	/* 开户行城市 */
	String City;
	/* 随机时间戳 */
	String RandomTimeStamp;
	String Remark1;
	String Remark2;
	String Remark3;
	/* 页面返回网址 */
	String ReturnURL;
	/* 后台通知网址 */
	String NotifyURL;
	/* 签名信息 */
	String SingInfo;

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

	public String getFeeQuota() {
		return FeeQuota;
	}

	public void setFeeQuota(String feeQuota) {
		FeeQuota = feeQuota;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getEncryptCardNo() {
		return encryptCardNo;
	}

	public void setEncryptCardNo(String encryptCardNo) {
		this.encryptCardNo = encryptCardNo;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getBranchBankName() {
		return BranchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		BranchBankName = branchBankName;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
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
	/**
	 * 获取签名信息
	 * @return
	 */
	public String getSingInfo() {
		return SingInfo;
	}

	public void setSingInfo(String singInfo) {
		SingInfo = singInfo;
	}

	/**
	 * 组合参数
	 * 
	 * @return
	 */
	public String getPlanStr() {
		/*
		 * WithdrawMoneymoremore + PlatformMoneymoremore + OrderNo + Amount +
		 * FeeQuota + CardNo + CardType + BankCode + BranchBankName + Province +
		 * City + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ReturnURL +
		 * NotifyURL
		 */
		StringBuffer buffer = new StringBuffer();
		buffer.append(WithdrawMoneymoremore).append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(StringUtils.trimToEmpty(OrderNo)).append(StringUtils.trimToEmpty(Amount))
				.append(StringUtils.trimToEmpty(FeeQuota)).append(StringUtils.trimToEmpty(CardNo))
				.append(StringUtils.trimToEmpty(CardType)).append(StringUtils.trimToEmpty(BankCode))
				.append(StringUtils.trimToEmpty(BranchBankName)).append(StringUtils.trimToEmpty(Province))
				.append(StringUtils.trimToEmpty(City)).append(StringUtils.trimToEmpty(RandomTimeStamp))
				.append(StringUtils.trimToEmpty(Remark1)).append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3)).append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
		return buffer.toString();
	}
	/**
	 * 讲提交参数封装成MAP
	 * @return
	 */
	public Map<String, String> getMap(){
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("WithdrawMoneymoremore", WithdrawMoneymoremore);
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("OrderNo", OrderNo);
		hashMap.put("Amount", Amount);
		hashMap.put("FeeQuota", FeeQuota);
		hashMap.put("CardNo", encryptCardNo);
		hashMap.put("CardType", CardType);
		hashMap.put("BankCode", BankCode);
		hashMap.put("Province", Province);
		hashMap.put("City", City);
		hashMap.put("RandomTimeStamp", RandomTimeStamp);
		hashMap.put("Remark1", Remark1);
		hashMap.put("Remark2", Remark2);
		hashMap.put("Remark3", Remark3);
		hashMap.put("ReturnURL", ReturnURL);
		hashMap.put("NotifyURL", NotifyURL);
		hashMap.put("SignInfo", getSingInfo());
		return hashMap;
	}
	public LoanWithdrawsBean(String withdrawMoneymoremore, String platformMoneymoremore, String orderNo, String amount,
			String feeQuota, String cardNo, String encryptCardNo, String cardType, String bankCode,
			String branchBankName, String province, String city, String randomTimeStamp, String remark1, String remark2,
			String remark3, String returnURL, String notifyURL, String singInfo) {
		super();
		WithdrawMoneymoremore = withdrawMoneymoremore;
		PlatformMoneymoremore = platformMoneymoremore;
		OrderNo = orderNo;
		Amount = amount;
		FeeQuota = feeQuota;
		CardNo = cardNo;
		this.encryptCardNo = encryptCardNo;
		CardType = cardType;
		BankCode = bankCode;
		BranchBankName = branchBankName;
		Province = province;
		City = city;
		RandomTimeStamp = randomTimeStamp;
		Remark1 = remark1;
		Remark2 = remark2;
		Remark3 = remark3;
		ReturnURL = returnURL;
		NotifyURL = notifyURL;
		SingInfo = singInfo;
	}

	public LoanWithdrawsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}