package com.ptpl.model;

import java.math.BigDecimal;

/**
 * 用户账户表 
 */

public class UserAccount {
	
    private BigDecimal id;

    private BigDecimal baseid;

    private Short accounttypeid;

    private Double balance=0d;

    private Double avlbalance=0d;

    private Double freezebalance=0d;
    
    private Double pendingprincipal=0d;
    
    private Double investProfit=0d;

    private BigDecimal bonuspoints=new BigDecimal(0);

    private BigDecimal avlbonuspoints=new BigDecimal(0);

    private BigDecimal freezebonuspoints=new BigDecimal(0);

    private Double redenvelope=0d;

    private Double avlredenvelope=0d;

    private Double freezeredenvelope=0d;

    private Short status=1;

    private String remark;
    
    private UserBaseAccountInfo ubai;
    
    
    private BigDecimal tradePoints =new BigDecimal(0);//用户交易积分
    
    private BigDecimal avlTradePoints =new BigDecimal(0);//可用交易积分
    
    private BigDecimal freezeTradePoints =new BigDecimal(0);//冻结交易积分

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getBaseid() {
		return baseid;
	}

	public void setBaseid(BigDecimal baseid) {
		this.baseid = baseid;
	}

	public Short getAccounttypeid() {
		return accounttypeid;
	}

	public void setAccounttypeid(Short accounttypeid) {
		this.accounttypeid = accounttypeid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAvlbalance() {
		return avlbalance;
	}

	public void setAvlbalance(Double avlbalance) {
		this.avlbalance = avlbalance;
	}

	public Double getFreezebalance() {
		return freezebalance;
	}

	public void setFreezebalance(Double freezebalance) {
		this.freezebalance = freezebalance;
	}

	public Double getPendingprincipal() {
		return pendingprincipal;
	}

	public void setPendingprincipal(Double pendingprincipal) {
		this.pendingprincipal = pendingprincipal;
	}

	public Double getInvestProfit() {
		return investProfit;
	}

	public void setInvestProfit(Double investProfit) {
		this.investProfit = investProfit;
	}

	public BigDecimal getBonuspoints() {
		return bonuspoints;
	}

	public void setBonuspoints(BigDecimal bonuspoints) {
		this.bonuspoints = bonuspoints;
	}

	public BigDecimal getAvlbonuspoints() {
		return avlbonuspoints;
	}

	public void setAvlbonuspoints(BigDecimal avlbonuspoints) {
		this.avlbonuspoints = avlbonuspoints;
	}

	public BigDecimal getFreezebonuspoints() {
		return freezebonuspoints;
	}

	public void setFreezebonuspoints(BigDecimal freezebonuspoints) {
		this.freezebonuspoints = freezebonuspoints;
	}

	public Double getRedenvelope() {
		return redenvelope;
	}

	public void setRedenvelope(Double redenvelope) {
		this.redenvelope = redenvelope;
	}

	public Double getAvlredenvelope() {
		return avlredenvelope;
	}

	public void setAvlredenvelope(Double avlredenvelope) {
		this.avlredenvelope = avlredenvelope;
	}

	public Double getFreezeredenvelope() {
		return freezeredenvelope;
	}

	public void setFreezeredenvelope(Double freezeredenvelope) {
		this.freezeredenvelope = freezeredenvelope;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserBaseAccountInfo getUbai() {
		return ubai;
	}

	public void setUbai(UserBaseAccountInfo ubai) {
		this.ubai = ubai;
	}

	public BigDecimal getTradePoints() {
		return tradePoints;
	}

	public void setTradePoints(BigDecimal tradePoints) {
		this.tradePoints = tradePoints;
	}

	public BigDecimal getAvlTradePoints() {
		return avlTradePoints;
	}

	public void setAvlTradePoints(BigDecimal avlTradePoints) {
		this.avlTradePoints = avlTradePoints;
	}

	public BigDecimal getFreezeTradePoints() {
		return freezeTradePoints;
	}

	public void setFreezeTradePoints(BigDecimal freezeTradePoints) {
		this.freezeTradePoints = freezeTradePoints;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", baseid=" + baseid + ", accounttypeid=" + accounttypeid + ", balance="
				+ balance + ", avlbalance=" + avlbalance + ", freezebalance=" + freezebalance + ", pendingprincipal="
				+ pendingprincipal + ", investProfit=" + investProfit + ", bonuspoints=" + bonuspoints
				+ ", avlbonuspoints=" + avlbonuspoints + ", freezebonuspoints=" + freezebonuspoints + ", redenvelope="
				+ redenvelope + ", avlredenvelope=" + avlredenvelope + ", freezeredenvelope=" + freezeredenvelope
				+ ", status=" + status + ", remark=" + remark + ", ubai=" + ubai + ", tradePoints=" + tradePoints
				+ ", avlTradePoints=" + avlTradePoints + ", freezeTradePoints=" + freezeTradePoints + "]";
	}
}