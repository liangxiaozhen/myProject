package com.ptpl.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 返回数据中数组中的对象
 * @author admin
 *
 */
public class SaveReconciliationDtoList {
	/** 商户客户号 */
	private String  MerCustId;
	/**用户客户号 */
	private String 	UsrCustId;
	/**订单号 */
	private String  OrdId;
	/** 订单日期 */
	private String 	OrdDate;
	/** 充值金额 */
	private String 	TransAmt;
	/**汇付交易状态*/
	private String 	TransStat;
	/** 充值方式 */
	private String 	GateBusiId;
	/** 银行代号*/
	private String 	OpenBankId;
	/** 卡号*/
	private String 	OpenAcctId;
	/** 手续费*/
	private String  FeeAmt;
	/** 手续费扣款客户号*/
	private String  FeeCustId;
	/**手续费扣款子账户号*/
	private String  FeeAcctId;
	
	public String getMerCustId() {
		return MerCustId;
	}
	@JSONField(name="MerCustId")
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	@JSONField(name="UsrCustId")
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
	public String getOrdId() {
		return OrdId;
	}
	@JSONField(name="OrdId")
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getOrdDate() {
		return OrdDate;
	}
	@JSONField(name="OrdDate")
	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}
	public String getTransAmt() {
		return TransAmt;
	}
	@JSONField(name="TransAmt")
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	public String getTransStat() {
		return TransStat;
	}
	@JSONField(name="TransStat")
	public void setTransStat(String transStat) {
		TransStat = transStat;
	}
	public String getGateBusiId() {
		return GateBusiId;
	}
	@JSONField(name="GateBusiId")
	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}
	public String getOpenBankId() {
		return OpenBankId;
	}
	@JSONField(name="OpenBankId")
	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}
	public String getOpenAcctId() {
		return OpenAcctId;
	}
	@JSONField(name="OpenAcctId")
	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}
	public String getFeeAmt() {
		return FeeAmt;
	}
	@JSONField(name="FeeAmt")
	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}
	public String getFeeCustId() {
		return FeeCustId;
	}
	@JSONField(name="FeeCustId")
	public void setFeeCustId(String feeCustId) {
		FeeCustId = feeCustId;
	}
	public String getFeeAcctId() {
		return FeeAcctId;
	}
	@JSONField(name="FeeAcctId")
	public void setFeeAcctId(String feeAcctId) {
		FeeAcctId = feeAcctId;
	}

	
	 
}
