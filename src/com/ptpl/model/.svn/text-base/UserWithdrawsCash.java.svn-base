package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 用户提现记录
 * @author xiaoy
 *
 * @date 2016年7月14日 上午9:48:34
 */
public class UserWithdrawsCash {
	//ID
	private BigDecimal id;
	//用户ID
	private BigDecimal baseid;
	//记录编号
	private String cashno;
	//提现金额
	private Double amount;
	//平台承担的手续费
	private Double fee;
	//用户实际承担的手续费
	private Double feewithdraws;
	//提现费率
	private Double feerate;
	//银行名称
	private String bankname;
	//开户城市
	private String city;
	//开户省份
	private String province;
	//开户行支行名称
	private String branchbankname;
	//用户名称
	private String username;
	//银行卡号
	private String cardno;
	//申请提现时间
	private Date applydate;
	private String applydateStr;
	//管理员审核时间
	private Date auditdate;
	private String auditdateStr;
	//审核人
	private String auditman;
	//勾兑人
	private String confirmman;
	//查询运算符
	private String operator;
	//提现状态  0待处理 1成功 2失败  3取消 4处理中 5待处理（用户提现取消）
	private Short status;
	//审核状态 0待审核 1审核成功  2审核失败   3无需审核 
	private Short isaudit;
	//是否可以取消提现  0否 1是
	private Short iscancel;
	//设备来源
	private Short originclient;
	//备注
	private String remark;
	//提现通道公司
	private String paycompany;
	//日提现次数
	private int daytotal;
	//日提现总额
	private double dayamount;
	//平台服务费
	private Double servfee;
	//服务费收取商户子账号
	private String servfeeacctid;
	//是否系统勾兑  0未勾兑 1已勾兑
	private Short isblending;
	//是否人工勾兑（0未勾兑，1已勾兑）
	private Short ismanblending;
	//勾兑是否异常  0否 1是
	private Short isexceptions;
	//系统勾兑时间
	private Date syschktime;
	//手工勾兑时间
	private Date checktime;
	//系统勾兑接收数据时间 第一次
	private Date sysrectime;
	//人工勾兑接收数据时间 第一次
	private Date receivetime;
	//请求数据包
	private String reqquerydata;
	//接收数据包
	private String recresultdata;
	//手续费收取对象  M 商户  U 客户
	private String feeobjflag;
	//手续费收取子账户
	private String feeacctid;
	//取现方式 FAST快速取现 GENERAL一般提现
	private String cashchl;
	//用户客户号
	private String usrcustid;
	//流水号
	private String bankreturnno;
	/*乾多多标识*/
	private String withdrawmoneymoremore;
	/*平台承担的手续费比例*/
	private String feepercent;
	/*平台扣除的免费提现额*/
	private String freelimit;
    //页面查询开始时间
    private Date startdate;
    //页面查询结束时间
    private Date enddate;
	/*平台分润*/
	private String feesplitting;
	
	/*用户基本账户信息*/
	private UserBaseAccountInfo ubai =new UserBaseAccountInfo();
	/*日期符号*/
	private String sign;

	
	public String getWithdrawmoneymoremore() {
		return withdrawmoneymoremore;
	}

	public void setWithdrawmoneymoremore(String withdrawmoneymoremore) {
		this.withdrawmoneymoremore = withdrawmoneymoremore;
	}

	public String getFeepercent() {
		return feepercent;
	}

	public void setFeepercent(String feepercent) {
		this.feepercent = feepercent;
	}

	public String getFreelimit() {
		return freelimit;
	}

	public void setFreelimit(String freelimit) {
		this.freelimit = freelimit;
	}

	public String getFeesplitting() {
		return feesplitting;
	}

	public void setFeesplitting(String feesplitting) {
		this.feesplitting = feesplitting;
	}

	public String getServfeeacctid()
	{
		return servfeeacctid;
	}

	public void setServfeeacctid(String servfeeacctid)
	{
		this.servfeeacctid = servfeeacctid;
	}

	public Short getIsblending()
	{
		return isblending;
	}

	public void setIsblending(Short isblending)
	{
		this.isblending = isblending;
	}

	public Short getIsexceptions()
	{
		return isexceptions;
	}

	public void setIsexceptions(Short isexceptions)
	{
		this.isexceptions = isexceptions;
	}

	public Date getSyschktime()
	{
		return syschktime;
	}

	public void setSyschktime(Date syschktime)
	{
		this.syschktime = syschktime;
	}

	public String getFeeobjflag()
	{
		return feeobjflag;
	}

	public void setFeeobjflag(String feeobjflag)
	{
		this.feeobjflag = feeobjflag;
	}

	public String getFeeacctid()
	{
		return feeacctid;
	}

	public void setFeeacctid(String feeacctid)
	{
		this.feeacctid = feeacctid;
	}

	public String getCashchl()
	{
		return cashchl;
	}

	public void setCashchl(String cashchl)
	{
		this.cashchl = cashchl;
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

	public String getCashno() {
		return cashno;
	}

	public void setCashno(String cashno) {
		this.cashno = cashno == null ? null : cashno.trim();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getFeerate() {
		return feerate;
	}

	public void setFeerate(Double feerate) {
		this.feerate = feerate;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname == null ? null : bankname.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getApplydate() {
		return applydate;
	}

	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}
	public String getAuditman() {
		return auditman;
	}

	public void setAuditman(String auditman) {
		this.auditman = auditman == null ? null : auditman.trim();
	}

	public String getConfirmman() {
		return confirmman;
	}

	public void setConfirmman(String confirmman) {
		this.confirmman = confirmman == null ? null : confirmman.trim();
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getPaycompany()
	{
		return paycompany;
	}

	public void setPaycompany(String paycompany)
	{
		this.paycompany = paycompany;
	}

	public int getDaytotal()
	{
		return daytotal;
	}

	public void setDaytotal(int daytotal)
	{
		this.daytotal = daytotal;
	}

	public double getDayamount()
	{
		return dayamount;
	}

	public void setDayamount(double dayamount)
	{
		this.dayamount = dayamount;
	}

	public Double getServfee()
	{
		return servfee;
	}

	public void setServfee(Double servfee)
	{
		this.servfee = servfee;
	}

	public Date getChecktime()
	{
		return checktime;
	}

	public void setChecktime(Date checktime)
	{
		this.checktime = checktime;
	}

	public Date getSysrectime()
	{
		return sysrectime;
	}

	public void setSysrectime(Date sysrectime)
	{
		this.sysrectime = sysrectime;
	}

	public Date getReceivetime()
	{
		return receivetime;
	}

	public void setReceivetime(Date receivetime)
	{
		this.receivetime = receivetime;
	}

	public String getReqquerydata()
	{
		return reqquerydata;
	}

	public void setReqquerydata(String reqquerydata)
	{
		this.reqquerydata = reqquerydata;
	}

	public String getRecresultdata()
	{
		return recresultdata;
	}

	public void setRecresultdata(String recresultdata)
	{
		this.recresultdata = recresultdata;
	}

	public Short getIsmanblending()
	{
		return ismanblending;
	}

	public void setIsmanblending(Short ismanblending)
	{
		this.ismanblending = ismanblending;
	}

	public String getUsrcustid()
	{
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid)
	{
		this.usrcustid = usrcustid;
	}

	public UserBaseAccountInfo getUbai()
	{
		return ubai;
	}

	public void setUbai(UserBaseAccountInfo ubai)
	{
		this.ubai = ubai;
	}

	public Short getIsaudit()
	{
		return isaudit;
	}

	public void setIsaudit(Short isaudit)
	{
		this.isaudit = isaudit;
	}

	public String getBankreturnno()
	{
		return bankreturnno;
	}

	public void setBankreturnno(String bankreturnno)
	{
		this.bankreturnno = bankreturnno;
	}

	public String getApplydateStr()
	{
		return applydateStr;
	}

	public void setApplydateStr(String applydateStr)
	{
		this.applydateStr = applydateStr;
	}

	public Short getIscancel()
	{
		return iscancel;
	}

	public void setIscancel(Short iscancel)
	{
		this.iscancel = iscancel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getBranchbankname() {
		return branchbankname;
	}

	public void setBranchbankname(String branchbankname) {
		this.branchbankname = branchbankname;
	}

	public Double getFeewithdraws() {
		return feewithdraws;
	}

	public void setFeewithdraws(Double feewithdraws) {
		this.feewithdraws = feewithdraws;
	}

	public String getAuditdateStr() {
		return auditdateStr;
	}

	public void setAuditdateStr(String auditdateStr) {
		this.auditdateStr = auditdateStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}


}