package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserDebtAttorn {
    private BigDecimal id;
    /**逾期期数,有逾期就存期数,没有逾期就存0*/
    private BigDecimal odperiods;
    /**标的债转设置表id*/
    private BigDecimal daid;
    /**投标订单号*/
    private String torderno;
    /**转让人id*/
    private BigDecimal baseid;
    /**投标金额*/
    private Double amount;
    /**债转金额*/
    private Double daamount;
    /**全部/部分债转 1:全部  2:部分*/
    private Short ispartda;
    /**债转系数:也就是债转比例*/
    private Double coefficient;
    /**承接时年化收益率:就是指收益*/
    private Double yearprofit;
    /**债转状态: 1:发布中  2:完成*/
    private Short dastatus;
    /**债转次数*/
    private Short datimes;
    /**设置人:登录名*/
    private String setman;
    /**设置时间*/
    private Date settime;
    private Date setstarttime; //为了方便查询区间段的
    private String setstarttimeStr;//为了数据库模糊查询
    private Date setendtime;
    private String setendtimeStr;
    /**备注*/
    private String remark;
    /**审核时间*/
    private Date audittime;
    /**审核时间*/
    private String auditman;
    /**标的号id*/
    private BigDecimal tenderid;
    /**债转编号:自己生成*/
    private String daorderno;
    /**定向债转码*/
    private String udapass;
    /**可承接金额*/
    private Double candaamount;
    /**投标记录表*/
    private UserTender usertender;
    /**标的设置表*/
    private TenderItem tenderitem;
    /**标的债转设置表*/
    private DebtAttorn debtattorn;
    /**标的还款计划表*/
    private RepayMent repayment;
    /**标的债转设置排出人员名单表*/
    private  DattornRNameLink dattornrnamelink;
    /**挂牌债转号*/
    private String lcid;
	/**利息债转系数*/
    private Double intcoefficient;
	/**债转多少天要下架:债转期限*/
	private Short deadline;
	/**滞纳金债转系数*/
    private Double latefeecoefficient;
	/**债转属性（1正常债转，2逾期债转）*/
	private Short daproperty;
	/**利息*/
	private Double intamount;
	/**滞纳金*/
	private Double ocamount;
	/**汇总金额*/
	private Double totalamount;
	/**汇总系数*/
	private Double totalfee;
	/**实际转让利息*/
	private Double factintamount;
	/**实际转让滞纳金*/
	private Double factocamount;
	/**年利率*100得到的数字*/
	private Double yearProfitStr;
	/**是否需要审核*/
	private Short isaudit;
	/**给承接人看的收益*/
	private Double profitStr;
	/**债转次数方式限制:1层级次数，2每人次数*/
	private int dattrestrict;
    /**页面选择条件的开始时间和结束时间*/
	private Date starttime;
	private Date endtime;
	/**页面查询条件一周内,一个月内,三个月内*/
	private String sign;
	

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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getDattrestrict() {
		return dattrestrict;
	}
	public void setDattrestrict(int dattrestrict) {
		this.dattrestrict = dattrestrict;
	}
	
	public BigDecimal getOdperiods() {
		return odperiods;
	}
	public void setOdperiods(BigDecimal odperiods) {
		this.odperiods = odperiods;
	}
	public Double getProfitStr() {
		return profitStr;
	}
	public void setProfitStr(Double profitStr) {
		this.profitStr = profitStr;
	}
	public Short getIsaudit() {
		return isaudit;
	}
	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}
	public Double getYearProfitStr() {
		return yearProfitStr;
	}
	public void setYearProfitStr(Double yearProfitStr) {
		this.yearProfitStr = yearProfitStr;
	}
	public Double getFactintamount() {
		return factintamount;
	}
	public void setFactintamount(Double factintamount) {
		this.factintamount = factintamount;
	}
	public Double getFactocamount() {
		return factocamount;
	}
	public void setFactocamount(Double factocamount) {
		this.factocamount = factocamount;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Double getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(Double totalfee) {
		this.totalfee = totalfee;
	}
	public Double getIntamount() {
		return intamount;
	}
	public void setIntamount(Double intamount) {
		this.intamount = intamount;
	}
	public Double getOcamount() {
		return ocamount;
	}
	public void setOcamount(Double ocamount) {
		this.ocamount = ocamount;
	}
	public String getLcid() {
		return lcid;
	}
	public void setLcid(String lcid) {
		this.lcid = lcid;
	}
	public DattornRNameLink getDattornrnamelink() {
		return dattornrnamelink;
	}
	public void setDattornrnamelink(DattornRNameLink dattornrnamelink) {
		this.dattornrnamelink = dattornrnamelink;
	}
	public UserTender getUsertender() {
		return usertender;
	}
	public void setUsertender(UserTender usertender) {
		this.usertender = usertender;
	}
	public TenderItem getTenderitem() {
		return tenderitem;
	}
	public void setTenderitem(TenderItem tenderitem) {
		this.tenderitem = tenderitem;
	}
	public DebtAttorn getDebtattorn() {
		return debtattorn;
	}
	public void setDebtattorn(DebtAttorn debtattorn) {
		this.debtattorn = debtattorn;
	}
	public RepayMent getRepayment() {
		return repayment;
	}
	public void setRepayment(RepayMent repayment) {
		this.repayment = repayment;
	}
	public String getDaorderno() {
		return daorderno;
	}
	public void setDaorderno(String daorderno) {
		this.daorderno = daorderno;
	}
	public String getUdapass() {
		return udapass;
	}
	public void setUdapass(String udapass) {
		this.udapass = udapass;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getTorderno() {
		return torderno;
	}
	public void setTorderno(String torderno) {
		this.torderno = torderno;
	}
	public BigDecimal getBaseid() {
		return baseid;
	}
	public void setBaseid(BigDecimal baseid) {
		this.baseid = baseid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getDaamount() {
		return daamount;
	}
	public void setDaamount(Double daamount) {
		this.daamount = daamount;
	}
	public Short getIspartda() {
		return ispartda;
	}
	public void setIspartda(Short ispartda) {
		this.ispartda = ispartda;
	}
	public Double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}
	public Short getDastatus() {
		return dastatus;
	}
	public void setDastatus(Short dastatus) {
		this.dastatus = dastatus;
	}
	public Short getDatimes() {
		return datimes;
	}
	public void setDatimes(Short datimes) {
		this.datimes = datimes;
	}
	public String getSetman() {
		return setman;
	}
	public void setSetman(String setman) {
		this.setman = setman;
	}
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		this.auditman = auditman;
	}
	public BigDecimal getTenderid() {
		return tenderid;
	}
	public void setTenderid(BigDecimal tenderid) {
		this.tenderid = tenderid;
	}
	
	public Date getSetstarttime() {
		return setstarttime;
	}
	public void setSetstarttime(Date setstarttime) {
		this.setstarttime = setstarttime;
	}
	public String getSetstarttimeStr() {
		return setstarttimeStr;
	}
	public void setSetstarttimeStr(String setstarttimeStr) {
		this.setstarttimeStr = setstarttimeStr;
	}
	public Date getSetendtime() {
		return setendtime;
	}
	public void setSetendtime(Date setendtime) {
		this.setendtime = setendtime;
	}
	public String getSetendtimeStr() {
		return setendtimeStr;
	}
	public void setSetendtimeStr(String setendtimeStr) {
		this.setendtimeStr = setendtimeStr;
	}
	
	public BigDecimal getDaid() {
		return daid;
	}
	public void setDaid(BigDecimal daid) {
		this.daid = daid;
	}
	public Double getYearprofit() {
		return yearprofit;
	}
	public void setYearprofit(Double yearprofit) {
		this.yearprofit = yearprofit;
	}
	public Double getCandaamount() {
		return candaamount;
	}
	public void setCandaamount(Double candaamount) {
		this.candaamount = candaamount;
	}
	
	public Double getIntcoefficient() {
		return intcoefficient;
	}
	public void setIntcoefficient(Double intcoefficient) {
		this.intcoefficient = intcoefficient;
	}
	public Short getDeadline() {
		return deadline;
	}
	public void setDeadline(Short deadline) {
		this.deadline = deadline;
	}
	public Double getLatefeecoefficient() {
		return latefeecoefficient;
	}
	public void setLatefeecoefficient(Double latefeecoefficient) {
		this.latefeecoefficient = latefeecoefficient;
	}
	public Short getDaproperty() {
		return daproperty;
	}
	public void setDaproperty(Short daproperty) {
		this.daproperty = daproperty;
	}
	@Override
	public String toString() {
		return "UserDebtAttorn [id=" + id + ", odperiods=" + odperiods + ", daid=" + daid + ", torderno=" + torderno
				+ ", baseid=" + baseid + ", amount=" + amount + ", daamount=" + daamount + ", ispartda=" + ispartda
				+ ", coefficient=" + coefficient + ", yearprofit=" + yearprofit + ", dastatus=" + dastatus
				+ ", datimes=" + datimes + ", setman=" + setman + ", settime=" + settime + ", setstarttime="
				+ setstarttime + ", setstarttimeStr=" + setstarttimeStr + ", setendtime=" + setendtime
				+ ", setendtimeStr=" + setendtimeStr + ", remark=" + remark + ", audittime=" + audittime + ", auditman="
				+ auditman + ", tenderid=" + tenderid + ", daorderno=" + daorderno + ", udapass=" + udapass
				+ ", candaamount=" + candaamount + ", usertender=" + usertender + ", tenderitem=" + tenderitem
				+ ", debtattorn=" + debtattorn + ", repayment=" + repayment + ", dattornrnamelink=" + dattornrnamelink
				+ ", lcid=" + lcid + ", intcoefficient=" + intcoefficient + ", deadline=" + deadline
				+ ", latefeecoefficient=" + latefeecoefficient + ", daproperty=" + daproperty + ", intamount="
				+ intamount + ", ocamount=" + ocamount + ", totalamount=" + totalamount + ", totalfee=" + totalfee
				+ ", factintamount=" + factintamount + ", factocamount=" + factocamount + ", yearProfitStr="
				+ yearProfitStr + ", isaudit=" + isaudit + ", profitStr=" + profitStr + "]";
	}
	
}