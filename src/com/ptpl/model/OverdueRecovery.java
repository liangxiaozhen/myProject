package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author liuj
 * 标的逾期平台追偿设置
 */
public class OverdueRecovery {
	
    private BigDecimal id;//主键

    private BigDecimal tid;//标的id

    private String overduerno;//逾期追偿编号

    private Short isprecoveryon;//是否设置平台追偿(1.设置,0不设)

    private BigDecimal pmiscrecmanid;//平台追偿费收款人id

    private String pmiscrecman;//平台追偿费收款人

    private Double ocminmoney;//分段逾期本金低值

    private Double ocmaxmoney;//分段逾期本金高值

    private Double occquota;//逾期本金追偿定额收费金额

    private Double toccrate;//逾期本金追偿收费费率

    private Double mincfees;//逾期本金追偿该段金额最低收费

    private Double maxcfees;//逾期本金追偿该段金额最高收费

    private Short isaudit;//资金清算是否需要审核

    private Short istemplet;//是否为模板（0否，1是）

    private String addman;//添加人

    private Date addtime;//设置时间

    private String remark;//备注
    
    private List<OverdueRecovery> recoveries;

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

    public String getOverduerno() {
        return overduerno;
    }

    public void setOverduerno(String overduerno) {
        this.overduerno = overduerno == null ? null : overduerno.trim();
    }

    public Short getIsprecoveryon() {
        return isprecoveryon;
    }

    public void setIsprecoveryon(Short isprecoveryon) {
        this.isprecoveryon = isprecoveryon;
    }

    public BigDecimal getPmiscrecmanid() {
        return pmiscrecmanid;
    }

    public void setPmiscrecmanid(BigDecimal pmiscrecmanid) {
        this.pmiscrecmanid = pmiscrecmanid;
    }

    public String getPmiscrecman() {
        return pmiscrecman;
    }

    public void setPmiscrecman(String pmiscrecman) {
        this.pmiscrecman = pmiscrecman == null ? null : pmiscrecman.trim();
    }

    public Double getOcminmoney() {
        return ocminmoney;
    }

    public void setOcminmoney(Double ocminmoney) {
        this.ocminmoney = ocminmoney;
    }

    public Double getOcmaxmoney() {
        return ocmaxmoney;
    }

    public void setOcmaxmoney(Double ocmaxmoney) {
        this.ocmaxmoney = ocmaxmoney;
    }

    public Double getOccquota() {
        return occquota;
    }

    public void setOccquota(Double occquota) {
        this.occquota = occquota;
    }

    public Double getToccrate() {
        return toccrate;
    }

    public void setToccrate(Double toccrate) {
        this.toccrate = toccrate;
    }

    public Double getMincfees() {
        return mincfees;
    }

    public void setMincfees(Double mincfees) {
        this.mincfees = mincfees;
    }

    public Double getMaxcfees() {
        return maxcfees;
    }

    public void setMaxcfees(Double maxcfees) {
        this.maxcfees = maxcfees;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
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

	public List<OverdueRecovery> getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(List<OverdueRecovery> recoveries) {
		this.recoveries = recoveries;
	}
}