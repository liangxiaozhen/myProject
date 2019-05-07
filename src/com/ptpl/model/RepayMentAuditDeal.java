package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 管理后台  还款审核处理  数据库无此表  
 * 只有于显示操作 【审核通过或失败】
 * @author admin
 *
 */
public class RepayMentAuditDeal {
	/* 还款批次号 */
	private String rbatchno;
 	/*同一批次还款明细*/
	private List<RepayMent> repayMents;
	/*同一批次的本金总和*/
	private Double ramountcount ;
	/*同一批次的利息总和*/
	private Double rinterestcount;
	/*是否代偿 0否 1是*/
	private Short isproxyrepay;
	/*是否逾期  0否 1是*/
	private Short isoverdue;
	/*是否提前  0否 1是*/
	private Short isahead;
	/*提交时间*/
	private Date submittime;
	/*借款人用户名*/
	private String loginname;
	/*借款人真实姓名*/
	private String realname;
	/*标号*/
	private String tno;
	/*标题*/
	private String tname;
	/*笔数*/
	private BigDecimal count;
	/*前台显示时间*/
	private String submittimestr;
	/*前台借款人用户名*/
	private String loginnamestr;
	/*前台借款人真实姓名*/
	private String realnamestr;
	public String getRbatchno() {
		return rbatchno;
	}


	public void setRbatchno(String rbatchno) {
		this.rbatchno = rbatchno;
	}


	public List<RepayMent> getRepayMents() {
		return repayMents;
	}


	public void setRepayMents(List<RepayMent> repayMents) {
		this.repayMents = repayMents;
	}


	public Double getRamountcount() {
		return ramountcount;
	}


	public void setRamountcount(Double ramountcount) {
		this.ramountcount = ramountcount;
	}


	public Double getRinterestcount() {
		return rinterestcount;
	}


	public void setRinterestcount(Double rinterestcount) {
		this.rinterestcount = rinterestcount;
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


	public Date getSubmittime() {
		return submittime;
	}


	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}


	public String getLoginname() {
		return loginname;
	}


	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getTno() {
		return tno;
	}


	public void setTno(String tno) {
		this.tno = tno;
	}


	public String getSubmittimestr() {
		return submittimestr;
	}


	public void setSubmittimestr(String submittimestr) {
		this.submittimestr = submittimestr;
	}


	public String getLoginnamestr() {
		return loginnamestr;
	}


	public void setLoginnamestr(String loginnamestr) {
		this.loginnamestr = loginnamestr;
	}


	public String getRealnamestr() {
		return realnamestr;
	}


	public void setRealnamestr(String realnamestr) {
		this.realnamestr = realnamestr;
	}


	public BigDecimal getCount() {
		return count;
	}


	public void setCount(BigDecimal count) {
		this.count = count;
	}


	public String getTname() {
		return tname;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	
}
