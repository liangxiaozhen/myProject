package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: AheadRealRepayment
 * @Package com.ptpl.model
 * @Description: TODO(提前实际还款记录实体类)
 * @author cjm
 * @date 2017年1月4日 上午11:29:41
 * @version V1.0
 */
public class AheadRealRepayment {
	/* 主键ID */
	private BigDecimal id;
	/*还款流水号 同还款计划表流水号*/
	private String rorderno;
	/*还款批次号*/
	private String rrealbatchno;
	/*汇付批量还款批次号*/
	private String hfbatchno;
	/*提前还款实际真现金*/
	private Double rptotalamount;
	/*提前还款实际类现金*/
	private Double rvoucher;
	/*提前还款实际总金额（本息）本金+类现金+实际总利息*/
	private Double rprincipalint;
	/*提前还款实际总利息 	本金利息+类现金利息*/
	private Double rptotalint;
	/*提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出*/
	private Double norectotalint;
	/*提前还款实际本金利息*/
	private Double rinterest;
	/*提前还款欠收本金利息*/
	private Double norecrinterest;
	/*提前还款实际类现金的利息*/
	private Double rvoucherint;
	/*提前还款欠收类现金的利息*/
	private Double norecrvoucherint;
	/*提前还款实际假现金的利息*/
	private Double rlvoucherint;
	/*提前还款欠收假现金的利息*/
	private Double norecrlvoucherint;
	/*提前还款实际加息劵利息*/
	private Double rintfee;
	/*提前还款欠收加息劵利息*/
	private Double norecrintfee;
	/*提前还款实际作废类现金的利息*/
	private Double discardvoucherint;
	/*提前还款欠收作废类现金的利息*/
	private Double norecdiscardvoucherint;
	/*欠收投资人利息罚金  (已作废)*/
	private Double oweintpenalty;
	/*投标利息的管理费 提前还款实际总利息 * 百分比*/
	private Double interestexpense;
	/*是否系统勾兑（0未勾兑，1已勾兑*/
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
	/*备注*/
	private String remark;
	/*制表时间*/
	private Date addtime;
	
	
	
	/*还款状态(已作废以还款计划表RepayMent为准     现用于前端数据查询)*/
	private Short repaystatus;
 	private RepaymentDetail repaymentDetail;
 
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

	public String getRrealbatchno() {
		return rrealbatchno;
	}

	public void setRrealbatchno(String rrealbatchno) {
		this.rrealbatchno = rrealbatchno == null ? null : rrealbatchno.trim();
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

	public Double getNorectotalint() {
		return norectotalint;
	}

	public void setNorectotalint(Double norectotalint) {
		this.norectotalint = norectotalint;
	}

	public Double getRinterest() {
		return rinterest;
	}

	public void setRinterest(Double rinterest) {
		this.rinterest = rinterest;
	}

	public Double getNorecrinterest() {
		return norecrinterest;
	}

	public void setNorecrinterest(Double norecrinterest) {
		this.norecrinterest = norecrinterest;
	}

	public Double getRvoucherint() {
		return rvoucherint;
	}

	public void setRvoucherint(Double rvoucherint) {
		this.rvoucherint = rvoucherint;
	}

	public Double getNorecrvoucherint() {
		return norecrvoucherint;
	}

	public void setNorecrvoucherint(Double norecrvoucherint) {
		this.norecrvoucherint = norecrvoucherint;
	}

	public Double getRlvoucherint() {
		return rlvoucherint;
	}

	public void setRlvoucherint(Double rlvoucherint) {
		this.rlvoucherint = rlvoucherint;
	}

	public Double getNorecrlvoucherint() {
		return norecrlvoucherint;
	}

	public void setNorecrlvoucherint(Double norecrlvoucherint) {
		this.norecrlvoucherint = norecrlvoucherint;
	}

	public Double getRintfee() {
		return rintfee;
	}

	public void setRintfee(Double rintfee) {
		this.rintfee = rintfee;
	}

	public Double getNorecrintfee() {
		return norecrintfee;
	}

	public void setNorecrintfee(Double norecrintfee) {
		this.norecrintfee = norecrintfee;
	}

	public Double getOweintpenalty() {
		return oweintpenalty;
	}

	public void setOweintpenalty(Double oweintpenalty) {
		this.oweintpenalty = oweintpenalty;
	}

	public Double getInterestexpense() {
		return interestexpense;
	}

	public void setInterestexpense(Double interestexpense) {
		this.interestexpense = interestexpense;
	}

	public Short getRepaystatus() {
		return repaystatus;
	}

	public void setRepaystatus(Short repaystatus) {
		this.repaystatus = repaystatus;
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

	public Double getRvoucher() {
		return rvoucher;
	}

	public void setRvoucher(Double rvoucher) {
		this.rvoucher = rvoucher;
	}
 
	public String getHfbatchno() {
		return hfbatchno;
	}

	public void setHfbatchno(String hfbatchno) {
		this.hfbatchno = hfbatchno;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Double getDiscardvoucherint() {
		return discardvoucherint;
	}

	public void setDiscardvoucherint(Double discardvoucherint) {
		this.discardvoucherint = discardvoucherint;
	}

	public Double getNorecdiscardvoucherint() {
		return norecdiscardvoucherint;
	}

	public void setNorecdiscardvoucherint(Double norecdiscardvoucherint) {
		this.norecdiscardvoucherint = norecdiscardvoucherint;
	}

	public RepaymentDetail getRepaymentDetail() {
		return repaymentDetail;
	}

	public void setRepaymentDetail(RepaymentDetail repaymentDetail) {
		this.repaymentDetail = repaymentDetail;
	}

	
	
}