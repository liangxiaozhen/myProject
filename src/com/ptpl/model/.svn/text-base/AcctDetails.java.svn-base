package com.ptpl.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 账户信息
 * @author admin
 *
 */
public class AcctDetails {
	/**账户类型*/
	private String accttype;
	/**子账户id*/
	private String  subacctid;
	/**可用余额*/
	private Double  avlbal;
	/**总额*/
	private Double  acctbal;
	/**冻结金额*/
	private Double  frzbal;
	public String getAccttype() {
		return accttype;
	}
	@JSONField(name="AcctType")
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public String getSubacctid() {
		return subacctid;
	}
	@JSONField(name="SubAcctId")
	public void setSubacctid(String subacctid) {
		this.subacctid = subacctid;
	}
	public Double getAvlbal() {
		return avlbal;
	}
	@JSONField(name="AvlBal")
	public void setAvlbal(Double avlbal) {
		this.avlbal = avlbal;
	}
	public Double getAcctbal() {
		return acctbal;
	}
	@JSONField(name="AcctBal")
	public void setAcctbal(Double acctbal) {
		this.acctbal = acctbal;
	}
	public Double getFrzbal() {
		return frzbal;
	}
	@JSONField(name="FrzBal")
	public void setFrzbal(Double frzbal) {
		this.frzbal = frzbal;
	}
	@Override
	public String toString() {
		return "AcctDetails [accttype=" + accttype + ", subacctid=" + subacctid + ", avlbal=" + avlbal + ", acctbal="
				+ acctbal + ", frzbal=" + frzbal + "]";
	}
	
}
