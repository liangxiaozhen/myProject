package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author Administrator
 * 申请借款记录
 */
public class loanapp {
    private BigDecimal id;//主键

    private BigDecimal baseid;//用户ID

    private String loanno;//借款编号

    private Short loantype;//借款类型(从借款类型对象里拿)
    
    private String loantypestr;//接受借款类型

    private Double loanamount;//借款金额

    private Date apptime;//申请时间

    private Long appday;//借款周期

    private Double loanrate;//借款利率

    private Short isneedconfirm;//是否需要借款人确认

    private Short appstatus;//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成

    private String auditman;//审核人

    private Date audittime;//审核时间

    private String remark;//审核备注

    private Double receiptsamount;//已入账借款金额

    private Date finishtime;//完成时间

    private String loanname;//借款标题名称

    private String loanpurposedesc;//借款标题名称

    private Short repaymenttype;//还款方式(1.一次性还本付息2.等额本金3.等额本息4.按期付息到期还本)

    private Short isappointtender;//是否为约标  0：否  1：是

    private Short isautorepay;//是否同意自动还款  0:不同意  1：同意

    private String unit;//周期单位(天，月，年)

    private String proxyman;//借款申请代提交人(平台管理人员代用户提交)

    private String liano;//借款资料编号(用户借款审核资料记录表，编号)

    private Short repaystatus;//无用字段

    private String ifapporigin;//接口申请来源(如分期乐-1000,京东商城-1111)

    private Short apptype;//申请方式(1.自申请2.代申请3.接口申请)

    private Short mastatus;//资料审核状态(1未填写，2待审核，3审核中，4审核成功，5审核失败)

    private Double riskmvalue;//风控值

    private String riskmorigin;//风控来源

    private String riskmlevel;//风控等级
    
    private Short isaitender;//是否允许发标  0:不允许  1：允许
    
    private Short ismulttender;//是否拆标   0不允许，1允许
    
    private Short splitnum;//拆标数
    
    private String period;
    
    private int npers;//已还款期数
    
    private String startdate;//申请时间
    
    private String enddate;//申请时间

    private Date tbegintime;//标的开始日期，在建标的时候录入




    private String loginname;//用户登陆名，用于封装到建标列表页面中显示的，数据库不存在这个字段

    private String realname;//用户真实名，用于封装到建标列表页面中显示的，数据库不存在这个字段

    private String loanamountstr;//借款金额,用于封装到建标列表页面中显示的，数据库不存在这个字段

    public String getLoanamountstr() {
        return loanamountstr;
    }

    public void setLoanamountstr(String loanamountstr) {
        this.loanamountstr = loanamountstr;
    }

    public Date getTbegintime() {
        return tbegintime;
    }

    public void setTbegintime(Date tbegintime) {
        this.tbegintime = tbegintime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getLoanno() {
        return loanno;
    }

    public void setLoanno(String loanno) {
        this.loanno = loanno == null ? null : loanno.trim();
    }

    public Short getLoantype() {
        return loantype;
    }

    public void setLoantype(Short loantype) {
        this.loantype = loantype;
    }

    public Double getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(Double loanamount) {
        this.loanamount = loanamount;
    }

    public Date getApptime() {
        return apptime;
    }

    public void setApptime(Date apptime) {
        this.apptime = apptime;
    }

    public Long getAppday() {
        return appday;
    }

    public void setAppday(Long appday) {
        this.appday = appday;
    }

    public Double getLoanrate() {
        return loanrate;
    }

    public void setLoanrate(Double loanrate) {
        this.loanrate = loanrate;
    }

    public Short getIsneedconfirm() {
        return isneedconfirm;
    }

    public void setIsneedconfirm(Short isneedconfirm) {
        this.isneedconfirm = isneedconfirm;
    }

    public Short getAppstatus() {
        return appstatus;
    }

    public void setAppstatus(Short appstatus) {
        this.appstatus = appstatus;
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

    public Double getReceiptsamount() {
        return receiptsamount;
    }

    public void setReceiptsamount(Double receiptsamount) {
        this.receiptsamount = receiptsamount;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getLoanname() {
        return loanname;
    }

    public void setLoanname(String loanname) {
        this.loanname = loanname == null ? null : loanname.trim();
    }

    public String getLoanpurposedesc() {
        return loanpurposedesc;
    }

    public void setLoanpurposedesc(String loanpurposedesc) {
        this.loanpurposedesc = loanpurposedesc == null ? null : loanpurposedesc.trim();
    }

    public Short getRepaymenttype() {
        return repaymenttype;
    }

    public void setRepaymenttype(Short repaymenttype) {
        this.repaymenttype = repaymenttype;
    }

    public Short getIsappointtender() {
        return isappointtender;
    }

    public void setIsappointtender(Short isappointtender) {
        this.isappointtender = isappointtender;
    }

    public Short getIsautorepay() {
        return isautorepay;
    }

    public void setIsautorepay(Short isautorepay) {
        this.isautorepay = isautorepay;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getProxyman() {
        return proxyman;
    }

    public void setProxyman(String proxyman) {
        this.proxyman = proxyman == null ? null : proxyman.trim();
    }

    public String getLiano() {
        return liano;
    }

    public void setLiano(String liano) {
        this.liano = liano == null ? null : liano.trim();
    }

    public Short getRepaystatus() {
        return repaystatus;
    }

    public void setRepaystatus(Short repaystatus) {
        this.repaystatus = repaystatus;
    }

    public String getIfapporigin() {
        return ifapporigin;
    }

    public void setIfapporigin(String ifapporigin) {
        this.ifapporigin = ifapporigin == null ? null : ifapporigin.trim();
    }

    public Short getApptype() {
        return apptype;
    }

    public void setApptype(Short apptype) {
        this.apptype = apptype;
    }

    public Short getMastatus() {
        return mastatus;
    }

    public void setMastatus(Short mastatus) {
        this.mastatus = mastatus;
    }

    public Double getRiskmvalue() {
        return riskmvalue;
    }

    public void setRiskmvalue(Double riskmvalue) {
        this.riskmvalue = riskmvalue;
    }

    public String getRiskmorigin() {
        return riskmorigin;
    }

    public void setRiskmorigin(String riskmorigin) {
        this.riskmorigin = riskmorigin == null ? null : riskmorigin.trim();
    }

    public String getRiskmlevel() {
        return riskmlevel;
    }

    public void setRiskmlevel(String riskmlevel) {
        this.riskmlevel = riskmlevel == null ? null : riskmlevel.trim();
    }

	public String getLoantypestr() {
		return loantypestr;
	}

	public void setLoantypestr(String loantypestr) {
		this.loantypestr = loantypestr;
	}

	public Short getIsaitender() {
		return isaitender;
	}

	public void setIsaitender(Short isaitender) {
		this.isaitender = isaitender;
	}

	public Short getIsmulttender() {
		return ismulttender;
	}

	public void setIsmulttender(Short ismulttender) {
		this.ismulttender = ismulttender;
	}

	public Short getSplitnum() {
		return splitnum;
	}

	public void setSplitnum(Short splitnum) {
		this.splitnum = splitnum;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getNpers() {
		return npers;
	}

	public void setNpers(int npers) {
		this.npers = npers;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
}