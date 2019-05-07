package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.ptpl.service.RepaymentDetailServiceI;

 
 /**
 * 还款批量详情记录
 * @author cjm
 * 
 */
public class RepaymentDetail {
	/*ID(主键）*/
    private BigDecimal id;
    /*还款流水号*/
    private String rorderno;
    /*还款批次号*/
    private String rbatchno;
    /*借款用户ID*/
    private BigDecimal outaccountid;
    /*投资用户ID*/
    private BigDecimal inaccountid;
    /*代还款人ID*/
    private BigDecimal proxyaccountid;
    /*平台杂项收款人id*/
    private BigDecimal pmiscrecmanid;
    /*投标/债转订单号*/
    private String utorderno;
    /*标号ID*/
    private BigDecimal tenderid;
    /*还款期数（第几期）*/
    private Integer periods;
    /*是否债转还款 1是 0否*/
    private Short isdarepay;
    /*还款模式（0初始  1人工，2系统，3线下 ）*/
    private Short rmode;
    /*该批次还款总金额（本息）*/
    private Double rprincipalint;
    /*该批次还款总金额（不含利息）*/
    private Double rptotalamount;
    /*该批次还款总利息*/
    private Double rptotalint;
    /*还款本金金额*/
    private Double ramount;
    /*还款本金利息*/
    private Double rinterest;
    /*该批次还款类现金*/
    private Double rvoucher;
    /*该批次还款类现金的利息*/
    private Double rvoucherint;
    /*该批次还款假现金*/
    private Double rlvoucher;
    /*该批次还款假现金的利息*/
    private Double rlvoucherint;
    /*该批次加息劵利息*/
    private Double rintfee;
    /*剩余本金*/
    private Double restprincipal;
    /*已转让本金（不含增益）*/
    private Double transferprincipal;
    /*剩余类现金*/
    private Double restvoucher;
    /*失效类现金*/
    private Double disablevoucher;
    /*失效类现金利息*/
    private Double disablevoucherint;
    /*剩余假现金*/
    private Double restlvoucher;
    /*失效假现金*/
    private Double disablelvoucher;
    /*剩余滞纳金*/
    private Double restocamount;
    /*失效滞纳金*/
    private Double disableocamount;
    /*剩余类现金滞纳金*/
    private Double vrestocamount;
    /*失效类现金滞纳金*/
    private Double disablevocamount;
    /*剩余加息卷收益*/
    private Double restintprofit;
    /*失效加息卷收益*/
    private Double disableintprofit;
    /*剩余本金产生的利息*/
    private Double restamountintprofit;
    /*剩余类现金产生的利息*/
    private Double restvoucherintprofit;
    /*剩余假现金产生的利息*/
    private Double restlvoucherintprofit;
    /*投标申请授权码*/
    private String authcode;
    /*逾期滞纳金额*/
    private Double overdueamount;
    /*投标利息的管理费*/
    private Double interestexpense;
    /*是否代偿（1是，0否）*/
    private Short isproxyrepay;
    /*是否逾期（0否，1是）*/
    private Short isoverdue;
    /*是否提前（0否，1是*/
    private Short isahead;
    /*还款状态(1待还款  2审核中 3待处理  4处理中  5已还款  6还款失败 7审核失败)*/
    private Short repaystatus;
    /*银行返回码*/
    private String retcode;
    /*是否提交（1是，0否）*/
    private Short issubmit;
    /*还款时间（计划表生成时间）*/
    private Date rtime;
    /*借款人提交还款时间*/
    private Date submittime;
    /*实际还款操作时间*/
    private Date operatetime;
    /*还款实际到账日期*/
    private Date rprealtime;
    /*是否审核 1是 0否*/
    private Short isaudit;
    /*审核人*/
    private String auditman;
    /*审核时间*/
    private Date audittime;
    /*制表时间*/
    private Date addtime;
    /*备注*/
    private String remark;
    
    /*借款人*/
    private UserBaseAccountInfo outaccount;
    /*投资用户*/
    private UserBaseAccountInfo inaccount;
    /*代还款人*/
    private UserBaseAccountInfo proxyaccount;
    /*标的*/
    private TenderItem tenderItem;
    /*具体还款计划*/
    private RepayMent repayment; 
    /*提前实际还款记录*/
    private AheadRealRepayment aheadRealRepayment;
    
    private String tno;//标号
    private String tname;//标名称
    private String inloginname;//投资人用户名
    private String outloginname;//借款人用户名
    private String proxyloginname;//代偿人用户名
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRorderno() {
        return rorderno;
    }

    public void setRorderno(String rorderno) {
        this.rorderno = rorderno == null ? null : rorderno.trim();
    }

    public String getRbatchno() {
        return rbatchno;
    }

    public void setRbatchno(String rbatchno) {
        this.rbatchno = rbatchno == null ? null : rbatchno.trim();
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

    public BigDecimal getProxyaccountid() {
        return proxyaccountid;
    }

    public void setProxyaccountid(BigDecimal proxyaccountid) {
        this.proxyaccountid = proxyaccountid;
    }

    public BigDecimal getPmiscrecmanid() {
        return pmiscrecmanid;
    }

    public void setPmiscrecmanid(BigDecimal pmiscrecmanid) {
        this.pmiscrecmanid = pmiscrecmanid;
    }

    public String getUtorderno() {
        return utorderno;
    }

    public void setUtorderno(String utorderno) {
        this.utorderno = utorderno == null ? null : utorderno.trim();
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Short getIsdarepay() {
        return isdarepay;
    }

    public void setIsdarepay(Short isdarepay) {
        this.isdarepay = isdarepay;
    }

    public Short getRmode() {
        return rmode;
    }

    public void setRmode(Short rmode) {
        this.rmode = rmode;
    }

    public Double getRprincipalint() {
        return rprincipalint;
    }

    public void setRprincipalint(Double rprincipalint) {
        this.rprincipalint = rprincipalint;
    }

    public Double getRptotalamount() {
        return rptotalamount;
    }

    public void setRptotalamount(Double rptotalamount) {
        this.rptotalamount = rptotalamount;
    }

    public Double getRptotalint() {
        return rptotalint;
    }

    public void setRptotalint(Double rptotalint) {
        this.rptotalint = rptotalint;
    }

    public Double getRamount() {
        return ramount;
    }

    public void setRamount(Double ramount) {
        this.ramount = ramount;
    }

    public Double getRinterest() {
        return rinterest;
    }

    public void setRinterest(Double rinterest) {
        this.rinterest = rinterest;
    }

    public Double getRvoucher() {
        return rvoucher;
    }

    public void setRvoucher(Double rvoucher) {
        this.rvoucher = rvoucher;
    }

    public Double getRvoucherint() {
        return rvoucherint;
    }

    public void setRvoucherint(Double rvoucherint) {
        this.rvoucherint = rvoucherint;
    }

    public Double getRlvoucher() {
        return rlvoucher;
    }

    public void setRlvoucher(Double rlvoucher) {
        this.rlvoucher = rlvoucher;
    }

    public Double getRlvoucherint() {
        return rlvoucherint;
    }

    public void setRlvoucherint(Double rlvoucherint) {
        this.rlvoucherint = rlvoucherint;
    }

    public Double getRintfee() {
        return rintfee;
    }

    public void setRintfee(Double rintfee) {
        this.rintfee = rintfee;
    }

    public Double getRestprincipal() {
        return restprincipal;
    }

    public void setRestprincipal(Double restprincipal) {
        this.restprincipal = restprincipal;
    }

    public Double getTransferprincipal() {
        return transferprincipal;
    }

    public void setTransferprincipal(Double transferprincipal) {
        this.transferprincipal = transferprincipal;
    }

    public Double getRestvoucher() {
        return restvoucher;
    }

    public void setRestvoucher(Double restvoucher) {
        this.restvoucher = restvoucher;
    }

    public Double getDisablevoucher() {
        return disablevoucher;
    }

    public void setDisablevoucher(Double disablevoucher) {
        this.disablevoucher = disablevoucher;
    }

    public Double getDisablevoucherint() {
        return disablevoucherint;
    }

    public void setDisablevoucherint(Double disablevoucherint) {
        this.disablevoucherint = disablevoucherint;
    }

    public Double getRestlvoucher() {
        return restlvoucher;
    }

    public void setRestlvoucher(Double restlvoucher) {
        this.restlvoucher = restlvoucher;
    }

    public Double getDisablelvoucher() {
        return disablelvoucher;
    }

    public void setDisablelvoucher(Double disablelvoucher) {
        this.disablelvoucher = disablelvoucher;
    }

    public Double getRestocamount() {
        return restocamount;
    }

    public void setRestocamount(Double restocamount) {
        this.restocamount = restocamount;
    }

    public Double getDisableocamount() {
        return disableocamount;
    }

    public void setDisableocamount(Double disableocamount) {
        this.disableocamount = disableocamount;
    }

    public Double getVrestocamount() {
        return vrestocamount;
    }

    public void setVrestocamount(Double vrestocamount) {
        this.vrestocamount = vrestocamount;
    }

    public Double getDisablevocamount() {
        return disablevocamount;
    }

    public void setDisablevocamount(Double disablevocamount) {
        this.disablevocamount = disablevocamount;
    }

    public Double getRestintprofit() {
        return restintprofit;
    }

    public void setRestintprofit(Double restintprofit) {
        this.restintprofit = restintprofit;
    }

    public Double getDisableintprofit() {
        return disableintprofit;
    }

    public void setDisableintprofit(Double disableintprofit) {
        this.disableintprofit = disableintprofit;
    }

    public Double getRestamountintprofit() {
        return restamountintprofit;
    }

    public void setRestamountintprofit(Double restamountintprofit) {
        this.restamountintprofit = restamountintprofit;
    }

    public Double getRestvoucherintprofit() {
        return restvoucherintprofit;
    }

    public void setRestvoucherintprofit(Double restvoucherintprofit) {
        this.restvoucherintprofit = restvoucherintprofit;
    }

    public Double getRestlvoucherintprofit() {
        return restlvoucherintprofit;
    }

    public void setRestlvoucherintprofit(Double restlvoucherintprofit) {
        this.restlvoucherintprofit = restlvoucherintprofit;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode == null ? null : authcode.trim();
    }

    public Double getOverdueamount() {
        return overdueamount;
    }

    public void setOverdueamount(Double overdueamount) {
        this.overdueamount = overdueamount;
    }

    public Double getInterestexpense() {
        return interestexpense;
    }

    public void setInterestexpense(Double interestexpense) {
        this.interestexpense = interestexpense;
    }

    public Short getIsproxyrepay() {
        return isproxyrepay;
    }

    public void setIsproxyrepay(Short isproxyrepay) {
        this.isproxyrepay = isproxyrepay;
    }

    public Short getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(Short isoverdue) {
        this.isoverdue = isoverdue;
    }

    public Short getIsahead() {
        return isahead;
    }

    public void setIsahead(Short isahead) {
        this.isahead = isahead;
    }

    public Short getRepaystatus() {
        return repaystatus;
    }

    public void setRepaystatus(Short repaystatus) {
        this.repaystatus = repaystatus;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode == null ? null : retcode.trim();
    }

    public Short getIssubmit() {
        return issubmit;
    }

    public void setIssubmit(Short issubmit) {
        this.issubmit = issubmit;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public Date getRprealtime() {
        return rprealtime;
    }

    public void setRprealtime(Date rprealtime) {
        this.rprealtime = rprealtime;
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

	public UserBaseAccountInfo getProxyaccount() {
		return proxyaccount;
	}

	public void setProxyaccount(UserBaseAccountInfo proxyaccount) {
		this.proxyaccount = proxyaccount;
	}

	public TenderItem getTenderItem() {
		return tenderItem;
	}

	public void setTenderItem(TenderItem tenderItem) {
		this.tenderItem = tenderItem;
	}
 
	public RepayMent getRepayment() {
		return repayment;
	}

	public void setRepayment(RepayMent repayment) {
		this.repayment = repayment;
	}

	
	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}
	
	

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getInloginname() {
		return inloginname;
	}

	public void setInloginname(String inloginname) {
		this.inloginname = inloginname;
	}

	public String getOutloginname() {
		return outloginname;
	}

	public void setOutloginname(String outloginname) {
		this.outloginname = outloginname;
	}

	public String getProxyloginname() {
		return proxyloginname;
	}

	public void setProxyloginname(String proxyloginname) {
		this.proxyloginname = proxyloginname;
	}
	
	

	public AheadRealRepayment getAheadRealRepayment() {
		return aheadRealRepayment;
	}

	public void setAheadRealRepayment(AheadRealRepayment aheadRealRepayment) {
		this.aheadRealRepayment = aheadRealRepayment;
	}

	public static void main(String[] args) {
    	System.out.println(RepaymentDetailServiceI.class.getName());
		///System.out.println(detail);
	}
    
}