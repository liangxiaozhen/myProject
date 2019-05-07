package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: AheadRepayPlatformRecord 
* @Package com.ptpl.model 
* @Description: TODO(标的提前还款奖励平台记录 实体类)  
* @author cjm
* @date 2016年10月21日 下午12:21:52 
* @version V1.0
 */
public class AheadRepayPlatformRecord {
	/*ID（主键）*/
    private BigDecimal id;
    /*标号ID*/
    private BigDecimal tenderid;
    /*特定标Id 处理提前还款时，根据借款人开通的自动投标功能，发起自动投标的方式，实现补偿平台的欠收利息 –汇付接口*/
    private BigDecimal specialtenderid;
    /*提前还款奖励流水号*/
    private String arorderno;
    /*标的提前还款奖励设置表Id*/
    private BigDecimal arid;
    /*还款流水号 这里是提前还款批次号*/
    private String rorderno;
    /*平台杂项收款人*/
    private String pmiscrecman;
    /*借款人ID*/
    private BigDecimal bmanid;
    /*代还款人ID*/
    private BigDecimal proxyaccountid;
    /*投资人总欠收利息*/
    private Double allnorecieveint;
    /*补偿平台金额*/
    private Double amendspamount;
    /*是否处理(0否，1是)*/
    private Short isdeal;
    /*是否审核(0否，1是)*/
    private Short isaudit;
    /*是否系统勾兑（0未勾兑，1已勾兑）*/
    private Short isblending;
    /*是否人工勾兑（0未勾兑，1已勾兑）*/
    private Short ismanblending;
    /*系统勾兑时间*/
    private Date sysbtime;
    /*人工勾兑时间*/
    private Date manbtime;
    /*托管通道公司（汇付天下）*/
    private String paycompany;
    /*系统勾兑接收数据时间 第一次*/
    private Date sysrectime;
    /*人工勾兑接收数据时间 第一次*/
    private Date receivetime;
    /*请求数据包*/
    private String reqquerydata;
    /*接收数据包*/
    private String recresultdata;
    /*审核时间*/
    private Date audittime;
    /*审核人*/
    private String auditman;
    /*创建时间*/
    private Date madetime;
    /*清算日期*/
    private Date payoutdate;
    /*备注*/
    private String remark;

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

    public BigDecimal getSpecialtenderid() {
        return specialtenderid;
    }

    public void setSpecialtenderid(BigDecimal specialtenderid) {
        this.specialtenderid = specialtenderid;
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

    public String getPmiscrecman() {
        return pmiscrecman;
    }

    public void setPmiscrecman(String pmiscrecman) {
        this.pmiscrecman = pmiscrecman == null ? null : pmiscrecman.trim();
    }

    public BigDecimal getBmanid() {
        return bmanid;
    }

    public void setBmanid(BigDecimal bmanid) {
        this.bmanid = bmanid;
    }

    public BigDecimal getProxyaccountid() {
        return proxyaccountid;
    }

    public void setProxyaccountid(BigDecimal proxyaccountid) {
        this.proxyaccountid = proxyaccountid;
    }

    public Double getAllnorecieveint() {
        return allnorecieveint;
    }

    public void setAllnorecieveint(Double allnorecieveint) {
        this.allnorecieveint = allnorecieveint;
    }

    public Double getAmendspamount() {
        return amendspamount;
    }

    public void setAmendspamount(Double amendspamount) {
        this.amendspamount = amendspamount;
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