package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分类奖品总开关
 * @author admin
 *
 */
public class DiffAwardSwitch {

	private BigDecimal id;
	
	private Short attribute;//奖品属性
	
	private String awardType;//奖品属性名称（现金，积分，券, 红包）

	private Short allSwitch = new Short((short)1);//奖品交易方式总开关（1.开   2.关）

	private Date operateTime;// 操作时间

	private String operateMan;//操作人
	
	private String remark;//备注

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Short getAttribute() {
		return attribute;
	}

	public void setAttribute(Short attribute) {
		this.attribute = attribute;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public Short getAllSwitch() {
		return allSwitch;
	}

	public void setAllSwitch(Short allSwitch) {
		this.allSwitch = allSwitch;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateMan() {
		return operateMan;
	}

	public void setOperateMan(String operateMan) {
		this.operateMan = operateMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DiffAwardSwitch [id=" + id + ", attribute=" + attribute + ", awardType=" + awardType + ", allSwitch="
				+ allSwitch + ", operateTime=" + operateTime + ", operateMan=" + operateMan + ", remark=" + remark
				+ "]";
	}
	
}
