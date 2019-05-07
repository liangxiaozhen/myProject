package com.ptpl.model;

import java.math.BigDecimal;

/**
 * 奖品设置指定标号关联
 * @author admin
 *
 */
public class AwardTenderLink {

	private BigDecimal id;//奖品设置指定标号关联表id
	
	private BigDecimal awardId;//奖品设置表id
	
	private BigDecimal tenderId;//标的设置表id

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

	public BigDecimal getTenderId() {
		return tenderId;
	}

	public void setTenderId(BigDecimal tenderId) {
		this.tenderId = tenderId;
	}

	@Override
	public String toString() {
		return "AwardTenderLink [id=" + id + ", awardId=" + awardId + ", tenderId=" + tenderId + "]";
	}
	
}
