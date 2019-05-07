package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: AheadRepayAwardRecord 
* @Package com.ptpl.model 
* @Description: TODO(标的提前还款奖品补偿记录 实体类) 
* @author cjm
* @date 2016年10月21日 上午11:24:41 
* @version V1.0
 */
public class AheadRepayAwardRecord {
	/*ID（主键）*/
    private BigDecimal id;
    /*标号ID*/
    private BigDecimal tenderid;
    /*提前还款奖励流水号*/
    private String arorderno;
    /*标的提前还款奖励设置表Id*/
    private BigDecimal arid;
    /*还款流水号*/
    private String rorderno;
    /*PRIN平台奖励奖品 (已作废)*/
    private String pawardname;
    /*PRIN平台奖励奖品编号  (已作废)*/
    private String pawardno;
    /*PRIN平台奖励奖品数  (已作废)*/
    private Double pawardcount;
    /*单个投资人增益总欠收利息*/
    private Double pluspoorint;
    /*PLUS平台罚金*/
    private Double plusppenalty;
    /*PLUS平台奖励奖品*/
    private String pluspaward;
    /*PLUS平台奖励奖品数*/
    private Integer pluspawardcount;
    /*是否发放(0否，1是)*/
    private Short isgrant;
    /*是否审核(0否，1是)*/
    private Short isaudit;
    /*审核时间*/
    private Date audittime;
    /*审核人*/
    private String auditman;
    /*创建时间*/
    private Date madetime;
    /*发放日期*/
    private Date payoutdate;
    /*备注*/
    private String remark;
    /*投资人ID*/
    private BigDecimal investorid;
    /*PLUS平台奖励奖品编号*/
    private String pluspawardno;
    /*提前还款批次号*/
    private String  arbatchno;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public String getArorderno() {
        return arorderno;
    }

    public void setArorderno(String arorderno) {
        this.arorderno = arorderno == null ? null : arorderno.trim();
    }

    public BigDecimal getArid() {
        return arid;
    }

    public void setArid(BigDecimal arid) {
        this.arid = arid;
    }

    public String getRorderno() {
        return rorderno;
    }

    public void setRorderno(String rorderno) {
        this.rorderno = rorderno == null ? null : rorderno.trim();
    }

    public String getPawardname() {
        return pawardname;
    }

    public void setPawardname(String pawardname) {
        this.pawardname = pawardname == null ? null : pawardname.trim();
    }

    public String getPawardno() {
        return pawardno;
    }

    public void setPawardno(String pawardno) {
        this.pawardno = pawardno == null ? null : pawardno.trim();
    }

    public Double getPawardcount() {
        return pawardcount;
    }

    public void setPawardcount(Double pawardcount) {
        this.pawardcount = pawardcount;
    }

    public Double getPluspoorint() {
        return pluspoorint;
    }

    public void setPluspoorint(Double pluspoorint) {
        this.pluspoorint = pluspoorint;
    }

    public Double getPlusppenalty() {
        return plusppenalty;
    }

    public void setPlusppenalty(Double plusppenalty) {
        this.plusppenalty = plusppenalty;
    }

    public String getPluspaward() {
        return pluspaward;
    }

    public void setPluspaward(String pluspaward) {
        this.pluspaward = pluspaward == null ? null : pluspaward.trim();
    }

    public Integer getPluspawardcount() {
        return pluspawardcount;
    }

    public void setPluspawardcount(Integer pluspawardcount) {
        this.pluspawardcount = pluspawardcount;
    }

    public Short getIsgrant() {
        return isgrant;
    }

    public void setIsgrant(Short isgrant) {
        this.isgrant = isgrant;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    public Date getMadetime() {
        return madetime;
    }

    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public Date getPayoutdate() {
        return payoutdate;
    }

    public void setPayoutdate(Date payoutdate) {
        this.payoutdate = payoutdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public BigDecimal getInvestorid() {
		return investorid;
	}

	public void setInvestorid(BigDecimal investorid) {
		this.investorid = investorid;
	}

	public String getPluspawardno() {
		return pluspawardno;
	}

	public void setPluspawardno(String pluspawardno) {
		this.pluspawardno = pluspawardno;
	}

	public String getArbatchno() {
		return arbatchno;
	}

	public void setArbatchno(String arbatchno) {
		this.arbatchno = arbatchno;
	}
    
    
    
}