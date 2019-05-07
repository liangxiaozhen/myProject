package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标的流标补偿记录实体类
 * 
 * @author zhenglm
 *
 */
public class FailTCRecord {
	/** ID（主键） */
	private BigDecimal id;
	/** 流标补偿流水号 */
	private String ftcorderno;
	/** 流标补偿设置表Id */
	private BigDecimal ftcid;
	/** 标号ID */
	private BigDecimal tenderid;
	/** 平台杂项付款人 */
	private String pmiscpayman;
	/** 投标订单号 */
	private String utorderno;
	/** 投资人ID */
	private BigDecimal investorid;
	/** 投标金额 */
	private Double tenderamount;
	/** 奖励金额 */
	private Double rewardamount;
	/** 奖励方式（1金额，2奖品，3金额+奖品） */
	private Short payouttype;
	/** 奖品名称 */
	private String awardname;
	/** 奖品编号（奖励方式2，3才有） */
	private String awardno;
	/** 奖额（奖励方式,2，3才有） */
	private Double awardamount;
	/** 记录生成方式（系统自动，人工生成） */
	private Short createway;
	/** 补偿是否发放（0.未发放，1.已发放，2.待确认 发放后，余额增加，有奖品的话，奖品也增加） */
	private Short isgrant;
	/** 是否系统勾兑（0.未勾兑，1.已勾兑）*/
	private Short isblending;
	/** 是否人工勾兑（0.未勾兑，1.已勾兑）*/
	private Short ismanblending;
	/** 系统勾兑时间 */
	private Date sysbtime;
	/** 人工勾兑时间 */
	private Date manbtime;
	/** 托管通道公司 */
	private String paycompany;
	/** 系统勾兑接收数据时间 第一次 */
	private Date sysrectime;
	/** 人工勾兑接收数据时间 第一次 */
	private Date receivetime;
	/** 请求数据包 */
	private String reqquerydata;
	/** 接收数据包 */
	private String recresultdata;
	/** 创建时间 */
	private Date madetime;
	/** 创建时间（页面展示） */
	private String madetimeStr;
	/** 清算日期 */
	private Date dealdate;
	/** 清算日期（页面展示） */
	private String dealdateStr;
	/** 是否审核 */
	private Short isaudit;
	/** 审核时间 */
	private Date audittime;
	/** 审核时间（页面展示） */
	private String audittimeStr;
	/** 审核人 */
	private String auditman;
	/** 备注 */
	private String remark;
	/** 奖品份数*/
	private Integer awardcopies;
	/** 管理员备注（ 用来提示用户收货信息错误） */
	private String adminremark;
	
	/***************关联表***************/
	/** 关联标的流标补偿设置表 */
	private FailTCompensate failtcompensate;
	/** 关联标的设置表 */
	private TenderItem tenderitem;
	/** 关联用户基本信息表 */
	private UserBaseAccountInfo userbaseinfo;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getFtcorderno() {
		return ftcorderno;
	}

	public void setFtcorderno(String ftcorderno) {
		this.ftcorderno = ftcorderno;
	}

	public BigDecimal getFtcid() {
		return ftcid;
	}

	public void setFtcid(BigDecimal ftcid) {
		this.ftcid = ftcid;
	}

	public BigDecimal getTenderid() {
		return tenderid;
	}

	public void setTenderid(BigDecimal tenderid) {
		this.tenderid = tenderid;
	}

	public String getPmiscpayman() {
		return pmiscpayman;
	}

	public void setPmiscpayman(String pmiscpayman) {
		this.pmiscpayman = pmiscpayman;
	}

	public String getUtorderno() {
		return utorderno;
	}

	public void setUtorderno(String utorderno) {
		this.utorderno = utorderno;
	}

	public BigDecimal getInvestorid() {
		return investorid;
	}

	public void setInvestorid(BigDecimal investorid) {
		this.investorid = investorid;
	}

	public Double getTenderamount() {
		return tenderamount;
	}

	public void setTenderamount(Double tenderamount) {
		this.tenderamount = tenderamount;
	}

	public Double getRewardamount() {
		return rewardamount;
	}

	public void setRewardamount(Double rewardamount) {
		this.rewardamount = rewardamount;
	}

	public Short getPayouttype() {
		return payouttype;
	}

	public void setPayouttype(Short payouttype) {
		this.payouttype = payouttype;
	}

	public String getAwardname() {
		return awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	public String getAwardno() {
		return awardno;
	}

	public void setAwardno(String awardno) {
		this.awardno = awardno;
	}

	public Double getAwardamount() {
		return awardamount;
	}

	public void setAwardamount(Double awardamount) {
		this.awardamount = awardamount;
	}

	public Short getCreateway() {
		return createway;
	}

	public void setCreateway(Short createway) {
		this.createway = createway;
	}

	public Short getIsgrant() {
		return isgrant;
	}

	public void setIsgrant(Short isgrant) {
		this.isgrant = isgrant;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMadetimeStr() {
		return madetimeStr;
	}

	public void setMadetimeStr(String madetimeStr) {
		this.madetimeStr = madetimeStr;
	}

	public String getDealdateStr() {
		return dealdateStr;
	}

	public void setDealdateStr(String dealdateStr) {
		this.dealdateStr = dealdateStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public FailTCompensate getFailtcompensate() {
		return failtcompensate;
	}

	public void setFailtcompensate(FailTCompensate failtcompensate) {
		this.failtcompensate = failtcompensate;
	}

	public TenderItem getTenderitem() {
		return tenderitem;
	}

	public void setTenderitem(TenderItem tenderitem) {
		this.tenderitem = tenderitem;
	}

	public UserBaseAccountInfo getUserbaseinfo() {
		return userbaseinfo;
	}

	public void setUserbaseinfo(UserBaseAccountInfo userbaseinfo) {
		this.userbaseinfo = userbaseinfo;
	}

	public Integer getAwardcopies() {
		return awardcopies;
	}

	public void setAwardcopies(Integer awardcopies) {
		this.awardcopies = awardcopies;
	}

	public String getAdminremark() {
		return adminremark;
	}

	public void setAdminremark(String adminremark) {
		this.adminremark = adminremark;
	}
}