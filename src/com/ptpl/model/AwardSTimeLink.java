package com.ptpl.model;

import java.math.BigDecimal;

/**
 * 奖品特定时间关联表
 */

public class AwardSTimeLink {

	private BigDecimal id;
	
	private BigDecimal awardId;//奖品表的id
	
	private String sTimeNo;//特定时间包编号

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getAwardId() {
		return awardId;
	}

	public void setAwardId(BigDecimal awardId) {
		this.awardId = awardId;
	}

	public String getsTimeNo() {
		return sTimeNo;
	}

	public void setsTimeNo(String sTimeNo) {
		this.sTimeNo = sTimeNo;
	}

	@Override
	public String toString() {
		return "AwardSTimeLink [id=" + id + ", awardId=" + awardId + ", sTimeNo=" + sTimeNo + "]";
	}
	
}
