package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投标放款记录
 * @author zhenglm
 *
 */
public class UserMakeALoan {
	/** 主键ID */
    private BigDecimal id;
	/** 标的号ID */
    private BigDecimal tenderid;
	/** 投标订单号 */
    private String orderno;
	/** 放款订单号 */
    private String mloanorderno;
	/** 投资方ID */
    private BigDecimal outaccountid;
	/** 借款方ID */
    private BigDecimal inaccountid;
	/** 放款金额 */
    private Double amount;
	/** 放款开始时间 */
    private Date malbegintime;
	/** 放款完成时间 */
    private Date malendtime;
	/** 手续费（默认0） */
    private Double fee;
	/** 居间费 */
    private Double mediacyfee;
	/** 担保费 */
    private Double guaranteefee;
	/** 风险保证金 */
    private Double riskguarantyfee;
	/** 是否解冻（0.否，1.是） */
    private Short isthaw;
	/** 解冻订单号 */
    private String unfreezeordid;
	/** 冻结标识 */
    private String freezetrxid;
	/** 放款的状态（成功，失败） */
    private Short malstatus;
	/** 是否系统勾兑（0.未勾兑，1.已勾兑）*/
    private Short isblending;
	/** 是否人工勾兑（0.未勾兑，1.已勾兑）*/
    private Short ismanblending;
	/** 系统勾兑时间 */
    private Date sysbtime;
	/** 人工勾兑时间 */
    private Date manbtime;
	/** 放款通道公司 */
    private String paycompany;
	/** 系统勾兑接收数据时间 第一次 */
    private Date sysrectime;
	/** 人工勾兑接收数据时间 第一次 */
    private Date receivetime;
	/** 请求数据包 */
    private String reqquerydata;
	/** 接收数据包 */
    private String recresultdata;
	/** 是否审核 */
    private Short isaudit;
	/** 审核人 */
    private String auditman;
	/** 审核时间 */
    private Date audittime;
	/** 备注 */
    private String remark;
    
    /************关联用户基本信息表************/
	/** 投资方 */
    private UserBaseAccountInfo outaccount;
	/** 借款方 */
    private UserBaseAccountInfo inaccount;
    /************关联标的设置表************/
    private TenderItem tenderitem;

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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getMloanorderno() {
        return mloanorderno;
    }

    public void setMloanorderno(String mloanorderno) {
        this.mloanorderno = mloanorderno == null ? null : mloanorderno.trim();
    }

    public BigDecimal getOutaccountid() {
        return outaccountid;
    }

    public void setOutaccountid(BigDecimal outaccountid) {
        this.outaccountid = outaccountid;
    }

    public BigDecimal getInaccountid() {
        return inaccountid;
    }

    public void setInaccountid(BigDecimal inaccountid) {
        this.inaccountid = inaccountid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getMalbegintime() {
        return malbegintime;
    }

    public void setMalbegintime(Date malbegintime) {
        this.malbegintime = malbegintime;
    }

    public Date getMalendtime() {
        return malendtime;
    }

    public void setMalendtime(Date malendtime) {
        this.malendtime = malendtime;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getMediacyfee() {
        return mediacyfee;
    }

    public void setMediacyfee(Double mediacyfee) {
        this.mediacyfee = mediacyfee;
    }

    public Double getGuaranteefee() {
        return guaranteefee;
    }

    public void setGuaranteefee(Double guaranteefee) {
        this.guaranteefee = guaranteefee;
    }

    public Double getRiskguarantyfee() {
        return riskguarantyfee;
    }

    public void setRiskguarantyfee(Double riskguarantyfee) {
        this.riskguarantyfee = riskguarantyfee;
    }

    public Short getIsthaw() {
        return isthaw;
    }

    public void setIsthaw(Short isthaw) {
        this.isthaw = isthaw;
    }

    public String getUnfreezeordid() {
        return unfreezeordid;
    }

    public void setUnfreezeordid(String unfreezeordid) {
        this.unfreezeordid = unfreezeordid == null ? null : unfreezeordid.trim();
    }

    public String getFreezetrxid() {
        return freezetrxid;
    }

    public void setFreezetrxid(String freezetrxid) {
        this.freezetrxid = freezetrxid == null ? null : freezetrxid.trim();
    }

    public Short getMalstatus() {
        return malstatus;
    }

    public void setMalstatus(Short malstatus) {
        this.malstatus = malstatus;
    }

    public Short getIsblending() {
        return isblending;
    }

    public void setIsblending(Short isblending) {
        this.isblending = isblending;
    }

    public Short getIsmanblending() {
        return ismanblending;
    }

    public void setIsmanblending(Short ismanblending) {
        this.ismanblending = ismanblending;
    }

    public Date getSysbtime() {
        return sysbtime;
    }

    public void setSysbtime(Date sysbtime) {
        this.sysbtime = sysbtime;
    }

    public Date getManbtime() {
        return manbtime;
    }

    public void setManbtime(Date manbtime) {
        this.manbtime = manbtime;
    }

    public String getPaycompany() {
        return paycompany;
    }

    public void setPaycompany(String paycompany) {
        this.paycompany = paycompany == null ? null : paycompany.trim();
    }

    public Date getSysrectime() {
        return sysrectime;
    }

    public void setSysrectime(Date sysrectime) {
        this.sysrectime = sysrectime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public String getReqquerydata() {
        return reqquerydata;
    }

    public void setReqquerydata(String reqquerydata) {
        this.reqquerydata = reqquerydata == null ? null : reqquerydata.trim();
    }

    public String getRecresultdata() {
        return recresultdata;
    }

    public void setRecresultdata(String recresultdata) {
        this.recresultdata = recresultdata == null ? null : recresultdata.trim();
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public UserBaseAccountInfo getOutaccount() {
		return outaccount;
	}

	public void setOutaccount(UserBaseAccountInfo outaccount) {
		this.outaccount = outaccount;
	}

	public UserBaseAccountInfo getInaccount() {
		return inaccount;
	}

	public void setInaccount(UserBaseAccountInfo inaccount) {
		this.inaccount = inaccount;
	}

	public TenderItem getTenderitem() {
		return tenderitem;
	}

	public void setTenderitem(TenderItem tenderitem) {
		this.tenderitem = tenderitem;
	}
}