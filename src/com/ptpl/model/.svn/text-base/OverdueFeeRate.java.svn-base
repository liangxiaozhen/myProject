package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author liuj
 * 逾期滞纳金费率设置
 */
public class OverdueFeeRate {
	
    private BigDecimal id;//主键id

    private BigDecimal tid;//标的id

    private Short feeratetype;//费率计算方式(1.等比例,2.不等比例)

    private Integer beginday;//分段开始时间

    private Long endday;//分段结束时间

    private Double feerate;//滞纳金率

    private String addman;//添加人	

    private Date addtime;//设置时间

    private String remark;//备注

    private OverdueCompensate overdueCompensate;
    
    private List<OverdueFeeRate> overduefeerates;//获取数据

    public OverdueCompensate getOverdueCompensate() {
        return overdueCompensate;
    }

    public void setOverdueCompensate(OverdueCompensate overdueCompensate) {
        this.overdueCompensate = overdueCompensate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTid() {
        return tid;
    }

    public void setTid(BigDecimal tid) {
        this.tid = tid;
    }

    public Short getFeeratetype() {
        return feeratetype;
    }

    public void setFeeratetype(Short feeratetype) {
        this.feeratetype = feeratetype;
    }

    public Integer getBeginday() {
        return beginday;
    }

    public void setBeginday(Integer beginday) {
        this.beginday = beginday;
    }

    public Long getEndday() {
        return endday;
    }

    public void setEndday(Long endday) {
        this.endday = endday;
    }

    public Double getFeerate() {
        return feerate;
    }

    public void setFeerate(Double feerate) {
        this.feerate = feerate;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public List<OverdueFeeRate> getOverduefeerates() {
		return overduefeerates;
	}

	public void setOverduefeerates(List<OverdueFeeRate> overduefeerates) {
		this.overduefeerates = overduefeerates;
	}
}