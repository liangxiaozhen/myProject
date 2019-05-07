package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标的风险保证金记录表实体类
 * @author admin
 */
public class RiskGuarantyFeeRecord {
	/** 主键ID */
    private BigDecimal id;
	/** 风险保证金流水号 */
    private String rgmorderno;
	/** 投标订单号 */
    private String utorderno;
	/** 风险保证金设置表ID */
    private BigDecimal rgmid;
	/** 标号ID */
    private BigDecimal tenderid;
	/** 保证金收款人ID */
    private BigDecimal rgmmanid;
	/** 借款人ID */
    private BigDecimal bmanid;
	/** 投标金额 */
    private Double tenderamount;
	/** 保证金 */
    private Double guarantyfee;
	/** 保证金费率收取类型（1定额，2百分比，3最低，4最高） */
    private Short rgmtype;
	/** 收费方式（1结标后，2投标时） */
    private Short chargetype;
	/** 是否处理（0否，1是） */
    private Short isdeal;
	/** 是否审核 */
    private Short isaudit;
	/** 审核时间 */
    private Date audittime;
	/** 审核人 */
    private String auditman;
	/** 创建时间 */
    private Date madetime;
	/** 清算时间 */
    private Date payoutdate;
	/** 备注 */
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRgmorderno() {
        return rgmorderno;
    }

    public void setRgmorderno(String rgmorderno) {
        this.rgmorderno = rgmorderno == null ? null : rgmorderno.trim();
    }

    public String getUtorderno() {
        return utorderno;
    }

    public void setUtorderno(String utorderno) {
        this.utorderno = utorderno == null ? null : utorderno.trim();
    }

    public BigDecimal getRgmid() {
        return rgmid;
    }

    public void setRgmid(BigDecimal rgmid) {
        this.rgmid = rgmid;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public BigDecimal getRgmmanid() {
        return rgmmanid;
    }

    public void setRgmmanid(BigDecimal rgmmanid) {
        this.rgmmanid = rgmmanid;
    }

    public BigDecimal getBmanid() {
        return bmanid;
    }

    public void setBmanid(BigDecimal bmanid) {
        this.bmanid = bmanid;
    }

    public Double getTenderamount() {
        return tenderamount;
    }

    public void setTenderamount(Double tenderamount) {
        this.tenderamount = tenderamount;
    }

    public Double getGuarantyfee() {
        return guarantyfee;
    }

    public void setGuarantyfee(Double guarantyfee) {
        this.guarantyfee = guarantyfee;
    }

    public Short getRgmtype() {
        return rgmtype;
    }

    public void setRgmtype(Short rgmtype) {
        this.rgmtype = rgmtype;
    }

    public Short getChargetype() {
        return chargetype;
    }

    public void setChargetype(Short chargetype) {
        this.chargetype = chargetype;
    }

    public Short getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Short isdeal) {
        this.isdeal = isdeal;
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
}