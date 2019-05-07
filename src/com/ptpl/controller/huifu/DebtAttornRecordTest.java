package com.ptpl.controller.huifu;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: DebtAttornRecordTest 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(债权转让测试类) 
* @author cjm
* @date 2016年10月22日 下午2:41:57 
* @version V1.0
 */
public class DebtAttornRecordTest {

	private BigDecimal id; // ID（主键）
	private String daorderno; // 债转流水号
	private BigDecimal daid;// 债权设置表Id
	private BigDecimal tenderid;// 标号ID
	private String tenderorderno;// 投标订单号
	private Short holddate;// 原投标持有天数
	private String pmiscrecman;// 平台杂项收款人
	private BigDecimal holdmanid;// 转让人Id
	private BigDecimal undertakemanid;// 承接人Id
 	private Double totalamount;// 承接总额（转让金额）
	private Double yearprofit;// 承接时年化收益率
	private Short datimes;// 债转次数
	private Double dahfee;// 债转手续费
	private Short datype;// 债转手续费收取类型（1定额，2百分比，3最低，4最高）
	private Short tendertype;// 投标方式(1手动，2自动)
	private Short isdeal;// 是否处理（0否，1是
	private Short isblending;// 是否系统勾兑（0未勾兑，1已勾兑）
	private Short ismanblending;// 是否人工勾兑（0未勾兑，1已勾兑）
	private Date sysbtime;// 系统勾兑时间
	private Date manbtime;// 人工勾兑时间
	private String paycompany;// 托管通道公司（汇付天下）
	private Date sysrectime;// 系统勾兑接收数据时间 第一次
	private Date receivetime;// 人工勾兑接收数据时间 第一次
	private String reqquerydata;// 请求数据包
	private String recresultdata;// 接收数据包
	private Short isaudit;// 是否审核
	private Date audittime;// 审核时间
	private String auditman;// 审核人
	private Date payoutdate;// 清算时间
	private Date dadate;// 转让时间
	private String remark;// 备注

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getDaorderno() {
		return daorderno;
	}

	public void setDaorderno(String daorderno) {
		this.daorderno = daorderno;
	}

	public BigDecimal getDaid() {
		return daid;
	}

	public void setDaid(BigDecimal daid) {
		this.daid = daid;
	}

	public BigDecimal getTenderid() {
		return tenderid;
	}

	public void setTenderid(BigDecimal tenderid) {
		this.tenderid = tenderid;
	}

	public String getTenderorderno() {
		return tenderorderno;
	}

	public void setTenderorderno(String tenderorderno) {
		this.tenderorderno = tenderorderno;
	}

	public Short getHolddate() {
		return holddate;
	}

	public void setHoldDate(Short holddate) {
		this.holddate = holddate;
	}

	public String getPmiscrecman() {
		return pmiscrecman;
	}

	public void setPmiscrecman(String pmiscrecman) {
		this.pmiscrecman = pmiscrecman;
	}

	public BigDecimal getHoldmanid() {
		return holdmanid;
	}

	public void setHoldmanid(BigDecimal holdmanid) {
		this.holdmanid = holdmanid;
	}

	public BigDecimal getUndertakemanid() {
		return undertakemanid;
	}

	public void setUndertakemanid(BigDecimal undertakemanid) {
		this.undertakemanid = undertakemanid;
	}
  
	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Double getYearprofit() {
		return yearprofit;
	}

	public void setYearprofit(Double yearprofit) {
		this.yearprofit = yearprofit;
	}

	public Short getDatimes() {
		return datimes;
	}

	public void setDatimes(Short datimes) {
		this.datimes = datimes;
	}

	public Double getDahfee() {
		return dahfee;
	}

	public void setDahfee(Double dahfee) {
		this.dahfee = dahfee;
	}

	public Short getDatype() {
		return datype;
	}

	public void setDatype(Short datype) {
		this.datype = datype;
	}

	public Short getTendertype() {
		return tendertype;
	}

	public void setTendertype(Short tendertype) {
		this.tendertype = tendertype;
	}

	public Short getIsdeal() {
		return isdeal;
	}

	public void setIsdeal(Short isdeal) {
		this.isdeal = isdeal;
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
		this.paycompany = paycompany;
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
		this.reqquerydata = reqquerydata;
	}

	public String getRecresultdata() {
		return recresultdata;
	}

	public void setRecresultdata(String recresultdata) {
		this.recresultdata = recresultdata;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
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

	public Date getPayoutdate() {
		return payoutdate;
	}

	public void setPayoutdate(Date payoutdate) {
		this.payoutdate = payoutdate;
	}

	public Date getDadate() {
		return dadate;
	}

	public void setDadate(Date dadate) {
		this.dadate = dadate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
