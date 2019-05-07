package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标的担保费记录表实体类
 * @author zhenglm
 */
public class GuaranteeFeeRecord {
	/** 主键ID */
    private BigDecimal id;
	/** 担保费流水号 */
    private String gforderno;
	/** 投标订单号 */
    private String utorderno;
	/** 担保费设置表ID */
    private BigDecimal gfid;
	/** 标号ID */
    private BigDecimal tenderid;
	/** 担保费收款人ID */
    private BigDecimal guaranteemanid;
	/** 借款人ID */
    private BigDecimal bmanid;
	/** 投标金额 */
    private Double nodemarkamount;
	/** 担保费 */
    private Double guaranteefee;
	/** 担保费收取的类型（1定额，2百分比，3最低，4最高） */
    private Short gftype;
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
	/** 处理时间 */
    private Date payoutdate;
	/** 备注 */
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGforderno() {
        return gforderno;
    }

    public void setGforderno(String gforderno) {
        this.gforderno = gforderno == null ? null : gforderno.trim();
    }

    public String getUtorderno() {
        return utorderno;
    }

    public void setUtorderno(String utorderno) {
        this.utorderno = utorderno == null ? null : utorderno.trim();
    }

    public BigDecimal getGfid() {
        return gfid;
    }

    public void setGfid(BigDecimal gfid) {
        this.gfid = gfid;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public BigDecimal getGuaranteemanid() {
        return guaranteemanid;
    }

    public void setGuaranteemanid(BigDecimal guaranteemanid) {
        this.guaranteemanid = guaranteemanid;
    }

    public BigDecimal getBmanid() {
        return bmanid;
    }

    public void setBmanid(BigDecimal bmanid) {
        this.bmanid = bmanid;
    }

    public Double getNodemarkamount() {
        return nodemarkamount;
    }

    public void setNodemarkamount(Double nodemarkamount) {
        this.nodemarkamount = nodemarkamount;
    }

    public Double getGuaranteefee() {
        return guaranteefee;
    }

    public void setGuaranteefee(Double guaranteefee) {
        this.guaranteefee = guaranteefee;
    }

    public Short getGftype() {
        return gftype;
    }

    public void setGftype(Short gftype) {
        this.gftype = gftype;
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