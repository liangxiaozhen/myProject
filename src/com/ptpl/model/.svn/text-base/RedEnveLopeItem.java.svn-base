package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class RedEnveLopeItem {
	/** ID*/
    private BigDecimal id;
    
    /**流水号 */
    private String orderno;
    
    /**红包编号*/
    private String redenvelopeno;
    
    /**红包名称*/
    private String redenvelopename;
    
    /**红包金额*///平台发放现金红包，现金红包需要同步汇付账户
    private Float reamount;
    
    /**用户id*/
    private BigDecimal baseid;
    
    /*用户名*/
    private String username;
    
    /**业务类型*/
    //1流标补偿，2提前还款奖励，3注册活动，4投标活动,5手动颁奖--各种业务给用户发放现金红包时，需要调用汇付接口，汇付转账成功后，才给用户余额进账，用户奖品列表显示已发放
    private String businesstype;

    /**处理时间*/
    private Date sendtime;
    
    /**是否系统勾兑（0未勾兑，1已勾兑）*/
    private Short isblending;

    /**是否人工勾兑（0未勾兑，1已勾兑）*/
    private Short ismanblending;
    
    /**系统勾兑时间*/
    private Date sysbtime;
    
    /**人工勾兑时间*/
    private Date manbtime;

    /**托管通道公司（汇付天下）*/
    private String paycompany;

    /**系统勾兑接收数据时间 第一次*/
    private Date sysrectime;

    /**人工勾兑接收数据时间 第一次*/
    private Date receivetime;

    /**请求数据包*/
    private String reqquerydata;

    /**接收数据包*/
    private String recresultdata;
    /**备注*/
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getRedenvelopeno() {
        return redenvelopeno;
    }

    public void setRedenvelopeno(String redenvelopeno) {
        this.redenvelopeno = redenvelopeno == null ? null : redenvelopeno.trim();
    }

    public String getRedenvelopename() {
        return redenvelopename;
    }

    public void setRedenvelopename(String redenvelopename) {
        this.redenvelopename = redenvelopename == null ? null : redenvelopename.trim();
    }

    public Float getReamount() {
        return reamount;
    }

    public void setReamount(Float reamount) {
        this.reamount = reamount;
    }

    public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "RedEnveLopeItem [id=" + id + ", orderno=" + orderno + ", redenvelopeno=" + redenvelopeno
				+ ", redenvelopename=" + redenvelopename + ", reamount=" + reamount + ", baseid=" + baseid
				+ ", username=" + username + ", businesstype=" + businesstype + ", sendtime=" + sendtime
				+ ", isblending=" + isblending + ", ismanblending=" + ismanblending + ", sysbtime=" + sysbtime
				+ ", manbtime=" + manbtime + ", paycompany=" + paycompany + ", sysrectime=" + sysrectime
				+ ", receivetime=" + receivetime + ", reqquerydata=" + reqquerydata + ", recresultdata=" + recresultdata
				+ ", remark=" + remark + "]";
	}
    
}