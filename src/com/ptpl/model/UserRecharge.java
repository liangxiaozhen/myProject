package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserRecharge {
	/**id*/
    private BigDecimal id;
    /**用户id*/
    private BigDecimal baseid;
    /**充值订单号*/
    private String rechargeno;
    /**银行返回充值订单号,由返回的txtid代替*/
    private String bankreturnno;
    /**充值金额*/
    private Double amount;
    /**充值开始时间*/
    private Date starttime;
    /**充值开始时间字符串*/
    private String starttimeStr;
    /**充值结束时间*/
    private Date endtime;
    /**充值结束时间字符串*/
    private String endtimeStr;
    /**系统勾兑时间*/
    private Date syschktime;
    /**系统勾兑时间字符串*/
    private String syschktimeStr;
    /**充值人,也就是用户名*/
    private String applyman;
    /**手工勾兑时间*/
    private Date checktime;
    /**手工勾兑时间字符串*/
    private String  checktimeStr;
    /**对账人*/
    private String checkman;
    /**充值方式*/
    private Short rechargetype;
    /**充值方式字符串*/
    private String rechargetypeStr;
    /**充值银行代号*/
    private String bankname;
    /**充值银行字符串*/
    private String banknameStr;
    /**支付公司*/
    private String paycompany;
    /**人工第一次勾兑时间*/
    private Date receivetime;
    /**人工第一次勾兑时间字符串*/
    private String  receivetimeStr;
    /**是否系统勾兑*/
    private Short isblending;
    /**是否系统勾兑字符串*/
    private String isblendingStr;
    /**是否人工勾兑*/
    private Short ismanblending;
    /**是否人工勾兑字符串*/
    private String ismanblendingStr;
    /**是否异常*/
    private Short isexceptions;
    /**是否异常字符串*/
    private String isexceptionsStr;
    /**充值状态*/
    private Short status;
    /**充值状态字符串*/
    private String statusStr;
    /**充值设备来源*/
    private Short originclient;
    /**充值设备来源字符串*/
    private String originclientStr;
    /**备注*/
    private String remark;
    /**请求数据包*/
    private String reqquerydata;
    /**接收数据包*/
    private String recresultdata;
    /**充值手续费*/
    private Double recharfee;
    /**充值费率*/
    private Double recharrate;
    /**商户客户号*/
    private String mercustid;
    /**手续费收取方*/
    private String feeobjflag;
    /**卡号:只针对快捷的时候有用*/
    private String cardno;;
    /**系统第一次勾兑时间*/
    private Date sysrectime;
    /**系统第一次勾兑时间字符串*/
    private String sysrectimeStr;
    /**再次充值的标识*/
    private String uuid;
    /**再次充值的标识*/
    private String urid;
    /**实际入账金额*/
    private Double theamountcredited;
    /**用户基本信息表*/
    private UserBaseAccountInfo ubai =new UserBaseAccountInfo();
    /**充值页面查询标识*/
    private String sign;
    /**充值开始时间页面用于比对的时间*/
    private Date start;
    /**充值结束时间页面用于比对的时间*/
    private Date end;
    
    
    public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Double getTheamountcredited() {
		return theamountcredited;
	}

	public void setTheamountcredited(Double theamountcredited) {
		this.theamountcredited = theamountcredited;
	}

	public String getUrid() {
		return urid;
	}

	public void setUrid(String urid) {
		this.urid = urid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public UserBaseAccountInfo getUbai() {
		return ubai;
	}

	public void setUbai(UserBaseAccountInfo ubai) {
		this.ubai = ubai;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    

    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getRechargeno() {
        return rechargeno;
    }

    public void setRechargeno(String rechargeno) {
        this.rechargeno = rechargeno == null ? null : rechargeno.trim();
    }

    public String getBankreturnno() {
        return bankreturnno;
    }

    public void setBankreturnno(String bankreturnno) {
        this.bankreturnno = bankreturnno == null ? null : bankreturnno.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getSyschktime() {
        return syschktime;
    }

    public void setSyschktime(Date syschktime) {
        this.syschktime = syschktime;
    }

    public String getApplyman() {
        return applyman;
    }

    public void setApplyman(String applyman) {
        this.applyman = applyman == null ? null : applyman.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public String getCheckman() {
        return checkman;
    }

    public void setCheckman(String checkman) {
        this.checkman = checkman == null ? null : checkman.trim();
    }

    public Short getRechargetype() {
        return rechargetype;
    }

    public void setRechargetype(Short rechargetype) {
        this.rechargetype = rechargetype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
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

    public Short getIsexceptions() {
        return isexceptions;
    }

    public void setIsexceptions(Short isexceptions) {
        this.isexceptions = isexceptions;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getOriginclient() {
        return originclient;
    }

    public void setOriginclient(Short originclient) {
        this.originclient = originclient;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Double getRecharfee() {
        return recharfee;
    }

    public void setRecharfee(Double recharfee) {
        this.recharfee = recharfee;
    }

    public Double getRecharrate() {
        return recharrate;
    }

    public void setRecharrate(Double recharrate) {
        this.recharrate = recharrate;
    }

	public String getStarttimeStr() {
		return starttimeStr;
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return endtimeStr;
	}

	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}

	public String getSyschktimeStr() {
		return syschktimeStr;
	}

	public void setSyschktimeStr(String syschktimeStr) {
		this.syschktimeStr = syschktimeStr;
	}

	public String getChecktimeStr() {
		return checktimeStr;
	}

	public void setChecktimeStr(String checktimeStr) {
		this.checktimeStr = checktimeStr;
	}

	public String getReceivetimeStr() {
		return receivetimeStr;
	}

	public void setReceivetimeStr(String receivetimeStr) {
		this.receivetimeStr = receivetimeStr;
	}

	public String getRechargetypeStr() {
		return rechargetypeStr;
	}

	public void setRechargetypeStr(String rechargetypeStr) {
		this.rechargetypeStr = rechargetypeStr;
	}

	public String getBanknameStr() {
		return banknameStr;
	}

	public void setBanknameStr(String banknameStr) {
		this.banknameStr = banknameStr;
	}

	public String getPaycompany() {
		return paycompany;
	}

	public void setPaycompany(String paycompany) {
		this.paycompany = paycompany;
	}

	public String getIsblendingStr() {
		return isblendingStr;
	}

	public void setIsblendingStr(String isblendingStr) {
		this.isblendingStr = isblendingStr;
	}

	public String getIsmanblendingStr() {
		return ismanblendingStr;
	}

	public void setIsmanblendingStr(String ismanblendingStr) {
		this.ismanblendingStr = ismanblendingStr;
	}

	public String getIsexceptionsStr() {
		return isexceptionsStr;
	}

	public void setIsexceptionsStr(String isexceptionsStr) {
		this.isexceptionsStr = isexceptionsStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getOriginclientStr() {
		return originclientStr;
	}

	public void setOriginclientStr(String originclientStr) {
		this.originclientStr = originclientStr;
	}

	public Date getSysrectime() {
		return sysrectime;
	}

	public void setSysrectime(Date sysrectime) {
		this.sysrectime = sysrectime;
	}

	public String getSysrectimeStr() {
		return sysrectimeStr;
	}

	public void setSysrectimeStr(String sysrectimeStr) {
		this.sysrectimeStr = sysrectimeStr;
	}

	public String getMercustid() {
		return mercustid;
	}

	public void setMercustid(String mercustid) {
		this.mercustid = mercustid;
	}

	public String getFeeobjflag() {
		return feeobjflag;
	}

	public void setFeeobjflag(String feeobjflag) {
		this.feeobjflag = feeobjflag;
	}

	@Override
	public String toString() {
		return "UserRecharge [id=" + id + ", baseid=" + baseid + ", rechargeno=" + rechargeno + ", bankreturnno="
				+ bankreturnno + ", amount=" + amount + ", starttime=" + starttime + ", starttimeStr=" + starttimeStr
				+ ", endtime=" + endtime + ", endtimeStr=" + endtimeStr + ", syschktime=" + syschktime
				+ ", syschktimeStr=" + syschktimeStr + ", applyman=" + applyman + ", checktime=" + checktime
				+ ", checktimeStr=" + checktimeStr + ", checkman=" + checkman + ", rechargetype=" + rechargetype
				+ ", rechargetypeStr=" + rechargetypeStr + ", bankname=" + bankname + ", banknameStr=" + banknameStr
				+ ", paycompany=" + paycompany + ", receivetime=" + receivetime + ", receivetimeStr=" + receivetimeStr
				+ ", isblending=" + isblending + ", isblendingStr=" + isblendingStr + ", ismanblending=" + ismanblending
				+ ", ismanblendingStr=" + ismanblendingStr + ", isexceptions=" + isexceptions + ", isexceptionsStr="
				+ isexceptionsStr + ", status=" + status + ", statusStr=" + statusStr + ", originclient=" + originclient
				+ ", originclientStr=" + originclientStr + ", remark=" + remark + ", reqquerydata=" + reqquerydata
				+ ", recresultdata=" + recresultdata + ", recharfee=" + recharfee + ", recharrate=" + recharrate
				+ ", mercustid=" + mercustid + ", feeobjflag=" + feeobjflag + ", cardno=" + cardno + ", sysrectime="
				+ sysrectime + ", sysrectimeStr=" + sysrectimeStr + ", uuid=" + uuid + ", urid=" + urid
				+ ", theamountcredited=" + theamountcredited + ", ubai=" + ubai + ", sign=" + sign + "]";
	}

}