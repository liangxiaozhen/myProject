package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
* @ClassName: OverdueRecord 
* @Package com.ptpl.model 
* @Description: TODO(标的逾期代还款记录  实体类) 
* @author cjm
* @date 2017年2月10日 下午2:49:57 
* @version V1.0
 */
public class OverdueRecord {
	/*ID(主键）*/
    private BigDecimal id;
    /*逾期还款流水号*/
    private String ororderno;
    /*逾期还款设置表Id*/
    private BigDecimal orid;
    /*标号ID*/
    private BigDecimal tenderid;
    /*还款流水号*/
    private String rorderno;
    /*还款批次号*/
    private String rbatchno;
    /*平台杂项收款人   已作废*/
    private String pmiscrecman;
    /*借款用户ID*/
    private BigDecimal bmanid;
    /*投资人ID*/
    private BigDecimal investorid;
    /*代还款人ID*/
    private BigDecimal proxyaccountid;
    /*应还款日期*/
    private Date needrdate;
    /*还款期数(第几期)*/
    private Integer periods;
    /*逾期天数*/
    private Integer oday;
    /*欠投资人逾期总金额    不含假现金  本金+类现金*/
    private Double ototalamount;
    /*垫付总金额*/
    private Double advototalamount;
    /*欠投资人逾期总利息*/
    private Double ototalint;
    /*垫付总利息*/
    private Double advototalint;
    /*逾期本金*/
    private Double oamount;
    /*垫付逾期本金*/
    private Double advoamount;
    /*逾期类现金*/
    private Double oavoucher;
    /*垫付类现金*/
    private Double advoavoucher;
    /*逾期假现金*/
    private Double oalvoucher;
    /*垫付假现金*/
    private Double advoalvoucher;
    /*逾期本金利息*/
    private Double oamountint;
    /*垫付本金利息*/
    private Double advoamountint;
    /*逾期类现金利息*/
    private Double oavoucherint;
    /*垫付类现金利息*/
    private Double advoavoucherint;
    /*逾期假现金利息*/
    private Double oalvoucherint;
    /*垫付假现金利息*/
    private Double advoalvoucherint;
    /*逾期加息劵利息*/
    private Double ointerest;
    /*垫付加息劵利息*/
    private Double advointerest;
    /*滞纳金*/
    private Double ofee;
    /*垫付滞纳金*/
    private Double advofee;
    /*垫付方式（00000）位置代表含义   1本金2利息3滞纳金4暂无5暂无  
					（位置取值含义   1百分比，2最高（相当于最大收费）  ） 
					例：12100 表示  
					第一位 本金按照百分比算； 
					第二位 利息取最高值； 
					第三位 滞纳金按照百分比算
     */
    private String advpaytype;
    /*是否处理（0否，1是*/
    private Short isdeal;
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
    /*是否审核*/
    private Short isaudit;
    /*审核人*/
    private String auditman;
    /*审核时间*/
    private Date audittime;
    /*创建时间*/
    private Date madetime;
    /*清算日期*/
    private Date dealdate;
    /*备注*/
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrorderno() {
        return ororderno;
    }

    public void setOrorderno(String ororderno) {
        this.ororderno = ororderno == null ? null : ororderno.trim();
    }

    public BigDecimal getOrid() {
        return orid;
    }

    public void setOrid(BigDecimal orid) {
        this.orid = orid;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
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

    public BigDecimal getInvestorid() {
        return investorid;
    }

    public void setInvestorid(BigDecimal investorid) {
        this.investorid = investorid;
    }

    public BigDecimal getProxyaccountid() {
        return proxyaccountid;
    }

    public void setProxyaccountid(BigDecimal proxyaccountid) {
        this.proxyaccountid = proxyaccountid;
    }

    public Date getNeedrdate() {
        return needrdate;
    }

    public void setNeedrdate(Date needrdate) {
        this.needrdate = needrdate;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Integer getOday() {
        return oday;
    }

    public void setOday(Integer oday) {
        this.oday = oday;
    }

    public Double getOamount() {
        return oamount;
    }

    public void setOamount(Double oamount) {
        this.oamount = oamount;
    }

    public Double getAdvoamount() {
        return advoamount;
    }

    public void setAdvoamount(Double advoamount) {
        this.advoamount = advoamount;
    }

    public Double getOinterest() {
        return ointerest;
    }

    public void setOinterest(Double ointerest) {
        this.ointerest = ointerest;
    }

    public Double getAdvointerest() {
        return advointerest;
    }

    public void setAdvointerest(Double advointerest) {
        this.advointerest = advointerest;
    }

    public Double getOfee() {
        return ofee;
    }

    public void setOfee(Double ofee) {
        this.ofee = ofee;
    }

    public Double getAdvofee() {
        return advofee;
    }

    public void setAdvofee(Double advofee) {
        this.advofee = advofee;
    }

    public String getAdvpaytype() {
        return advpaytype;
    }

    public void setAdvpaytype(String advpaytype) {
        this.advpaytype = advpaytype == null ? null : advpaytype.trim();
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

    public Date getMadetime() {
        return madetime;
    }

    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public Date getDealdate() {
        return dealdate;
    }

    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRbatchno() {
        return rbatchno;
    }

    public void setRbatchno(String rbatchno) {
        this.rbatchno = rbatchno == null ? null : rbatchno.trim();
    }

    public Double getOtotalamount() {
        return ototalamount;
    }

    public void setOtotalamount(Double ototalamount) {
        this.ototalamount = ototalamount;
    }

    public Double getAdvototalamount() {
        return advototalamount;
    }

    public void setAdvototalamount(Double advototalamount) {
        this.advototalamount = advototalamount;
    }

    public Double getOtotalint() {
        return ototalint;
    }

    public void setOtotalint(Double ototalint) {
        this.ototalint = ototalint;
    }

    public Double getAdvototalint() {
        return advototalint;
    }

    public void setAdvototalint(Double advototalint) {
        this.advototalint = advototalint;
    }

    public Double getOavoucher() {
        return oavoucher;
    }

    public void setOavoucher(Double oavoucher) {
        this.oavoucher = oavoucher;
    }

    public Double getAdvoavoucher() {
        return advoavoucher;
    }

    public void setAdvoavoucher(Double advoavoucher) {
        this.advoavoucher = advoavoucher;
    }

    public Double getOalvoucher() {
        return oalvoucher;
    }

    public void setOalvoucher(Double oalvoucher) {
        this.oalvoucher = oalvoucher;
    }

    public Double getAdvoalvoucher() {
        return advoalvoucher;
    }

    public void setAdvoalvoucher(Double advoalvoucher) {
        this.advoalvoucher = advoalvoucher;
    }

    public Double getOamountint() {
        return oamountint;
    }

    public void setOamountint(Double oamountint) {
        this.oamountint = oamountint;
    }

    public Double getAdvoamountint() {
        return advoamountint;
    }

    public void setAdvoamountint(Double advoamountint) {
        this.advoamountint = advoamountint;
    }

    public Double getOavoucherint() {
        return oavoucherint;
    }

    public void setOavoucherint(Double oavoucherint) {
        this.oavoucherint = oavoucherint;
    }

    public Double getAdvoavoucherint() {
        return advoavoucherint;
    }

    public void setAdvoavoucherint(Double advoavoucherint) {
        this.advoavoucherint = advoavoucherint;
    }

    public Double getOalvoucherint() {
        return oalvoucherint;
    }

    public void setOalvoucherint(Double oalvoucherint) {
        this.oalvoucherint = oalvoucherint;
    }

    public Double getAdvoalvoucherint() {
        return advoalvoucherint;
    }

    public void setAdvoalvoucherint(Double advoalvoucherint) {
        this.advoalvoucherint = advoalvoucherint;
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
}