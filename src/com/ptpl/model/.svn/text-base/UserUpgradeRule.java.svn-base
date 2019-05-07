package com.ptpl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName UserUpgradeRule
 * @author liuj
 * @Description:TODO(用户等级升级规则)
 * @date:2016/8/29 PM:15:48
 * @version 1.0 
 */
public class UserUpgradeRule implements Serializable{
	private static final long serialVersionUID = 1L;
	/*编号*/
    private BigDecimal id;
    /*级别*/
    private Integer grade;
    /*升级方式(1.购买 2.兑换积分)*/
    private Integer method;
    /*时间有效期*/
    private Long effecttime;
    /*支付金额*/
    private Double payamount;
    /*需要积分*/
    private Long needbonuspoints;
    /*备注*/
    private String unit;
    /*单位（年月日）*/
    private String remark;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Long getEffecttime() {
        return effecttime;
    }

    public void setEffecttime(Long effecttime) {
        this.effecttime = effecttime;
    }

    public Double getPayamount() {
        return payamount;
    }

    public void setPayamount(Double payamount) {
        this.payamount = payamount;
    }

    public Long getNeedbonuspoints() {
        return needbonuspoints;
    }

    public void setNeedbonuspoints(Long needbonuspoints) {
        this.needbonuspoints = needbonuspoints;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}
}