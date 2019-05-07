package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: RepayMent
 * @Package com.ptpl.model
 * @Description: TODO(还款记录表 实体类)
 * @author chenjiaming
 * @date 2016年9月8日 下午2:06:32
 * @version V1.0
 */
public class RepayMent {
	
	/**
 	 * 20170701 还款需求变更  cjm
 	 * 原还款状态：1未还款,2已还款,3已提前还款,4正常还款审核中,5正常还款处理中,6提前还款审核中，7提前还款处理中,8逾期还款审核中,9逾期还款处理中)
	 * 原是否审核状态：是否审核 0未审核 1待审核  2审核通过 3审核失败
	 * 修改为===》
	 * 还款状态：1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败   
	 * 是否审核状态：1是 0否
	 * 
	 * 新添加是否提前还款状态：
	 * isahead  是否提前（0否，1是）
  	 * 
	 * 20170706 徽商银行版本 还款模块逻辑  cjm
 	 * 及时还款 【
	 * 	借款人提交还款  ===》》调用还款冻结接口 进行资金冻结   ===》 repaymentFrz 添加还款批次记录 status状态为 初始 ===》冻结成功
 	 * ====》调用利息管理费计算方法利息管理费并更新进具体还款计划表repayment  ===>把具体还款计划信息写进文件 
	 * ===》写进文件成功 ===》更新具体还款计划信息 {repaymentfrz批次号，还款状态修改为待处理，修改repayment 具体还款计划状态为待处理}，repaymentdetail批次详情记录添加快照，
	 * ===》调用银行文件上传接口 ===》文件上传成功 {修改批次号repaymentfrz 状态为 处理中，修改repaymentdetail批次详情记录状态为处理中，修改repayment 具体还款计划状态为处理中}
	 * ===》写进文件失败 
	 * ======》文件上传失败  ===》
	 * 】
	 *       
 	 * 冻结还款【
	 * 	多了一步审核 ，逻辑同及时还款一致
	 * 】
	 * 
	 * 还款冻结成功后添加批次还款快照，写进文件成功 ，修改批次还款详情还款状态为待处理，上传文件成功修改还款状态为处理中   ====》
	 * 20170707 cjm
  	 */
	/* 主键ID */
	private BigDecimal id;
	/* 还款流水号 */
	private String rorderno;
	/* 还款批次号 */
	private String rbatchno;
	/*乾多多批量还款批次号*/
	private String hfbatchno;
	/* 借款用户ID */
	private BigDecimal outaccountid;
	/* 投资用户ID */
	private BigDecimal inaccountid;
	/* 代还款人ID */
	private BigDecimal proxyaccountid;
	/* 平台杂项收款人id 双乾接口 */
	private BigDecimal pmiscrecmanid;
	/* 投标订单号 /债权转让订单号  根据具体投标记录对应还款 */
	private String utorderno;
	/* 债权转让订单号  已作废*/
	private String daorderno;
	/* 标的ID */
	private BigDecimal tenderid;
	/* 还款期数（第几期） */
	private Integer periods;
	/* 催收费(已作废) */
	private Double remindamount;
	/* 是否债转还款(投标记录发生过债转 1是 0 否       注：如原投标发生债权转让  生成新还款计划 原投标数据保存为0，债权转让数据的保存为1) */
	private Short isdarepay;
	/* 还款模式（0初始  1人工，2系统，3线下） */
	private Short rmode;
	private Double transferprincipal; 			//已转让本金（不含增益） 转让的金额（原投资人转让该收益）
	private Double rprincipalint;				//当期还款总本息（总本金+总利息） 
	private Double rptotalamount;				//当期还款总金额(真本金+类现金)
	private Double rptotalint;					//当期还款总利息(当期真现金利息+当期假现金利息+当期类现金利息+当期加息券利息)
 	private Double ramount;						//当期还款本金（当期本金） 
	private Double rinterest;					//当期还款本金利息（当期本金利息） 
	private Double rvoucher; 					//当期还款类现金(类现金)
	private Double rvoucherint;					//当期还款类现金的利息(类现金利息)
	private Double rlvoucher;					//当期还款假现金(假现金)
	private Double rlvoucherint;				//当期还款假现金的利息(假现金)
	private Double rintfee	;					//当期加息劵利息
	private Double restprincipal;				//剩余本金 剩余本金 = 总本金 - （还款本金1期+..+还款本金n期）注意：不包括类现金，假现金 
	private Double restvoucher; 				//剩余类现金
	private Double restlvoucher; 				//剩余假现金
	private Double restocamount; 				//剩余滞纳金 剩余本金产生的滞纳金
	private Double vrestocamount; 				//剩余类现金滞纳金 剩余类现金产生的滞纳金
	private Double restintprofit; 			    //剩余加息卷利息 
	private Double restamountintprofit; 		//剩余本金产生的利息 （原收益+剩余加息劵收益）  * 本金
	private Double restvoucherintprofit; 		//剩余类现金产生的利息 （原收益+加息劵收益）   * 剩余类现金
	private Double restlvoucherintprofit; 		//剩余假现金产生的利息 （原收益+加息劵收益） * 剩余假现金
	private Double disablevoucher; 				//失效类现金
	private Double disablelvoucher; 			//失效假现金
	private Double disableocamount;			    //失效滞纳金 失效本金产生的滞纳金
	private Double disablevocamount; 			//失效类现金滞纳金 失效类现金产生的滞纳金
	private Double disableintprofit; 			//失效加息卷收益
	private Double disablevoucherint;			//失效类现金利息
 	/* 提前还款欠收利息 提前还款 */
	private Double bpenalty;
	/* 逾期滞纳金额 注意是否有代偿 */
	private Double overdueamount;
	/* 投标利息的管理费 投资人—》平台 */
	private Double interestexpense;
	/* 是否代偿（1是，0否） */
	private Short isproxyrepay;
	/*是否逾期 0否，1是*/
	private Short isoverdue;
	/* 还款总利息费 还款金额利息+提前还款欠收利息+逾期滞纳金额 */
	private Double fee;
	/* 还款时间 */
	private Date rtime;
	/* 是否审核 0否 1是*/
	private Short isaudit;
	/* 还款计划状态(1有效，2无效) */
	private Short planstatus;
	/* 还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败，7审核失败) */
	private Short repaystatus;
	/* 是否系统勾兑（0未勾兑，1已勾兑） */
	private Short isblending;
	/* 是否人工勾兑（0未勾兑，1已勾兑） */
	private Short ismanblending;
	/* 系统勾兑时间 */
	private Date sysbtime;
	/* 人工勾兑时间 */
	private Date manbtime;
	/* 托管通道公司（汇付天下） */
	private String paycompany;
	/* 系统勾兑接收数据时间 第一次 */
	private Date sysrectime;
	/* 人工勾兑接收数据时间 第一次 */
	private Date receivetime;
	/* 请求数据包 */
	private String reqquerydata;
	/* 接受数据包 */
	private String recresultdata;
	/* 审核人 */
	private String auditman;
	/* 制表时间 */
	private Date addtime;
	/* 审核时间 */
	private Date audittime;
	/*提交银行时间*/
	private Date operatetime;
	/* 备注 */
	private String remark;
	/*乾多多还款流水号*/
	private String loanno;
	/*投标申请授权码*/
	private String  authcode;
	/*银行返回码*/
	private String retcode;
	/*实际到账日期*/
	private Date rprealtime;
	/*是否提前（0否，1是）*/
	private Short isahead;
	/*借款人提交还款时间   
	 * 注  这个时间同批次提交的时候添加必须一致  
	 * 如果不一致在管理员后台同批次审核处理时会查出多条记录*/
	private Date submittime;
	/*是否提交银行  1是 0 否*/
	private Short issubmit;
	
	/* 关联实体类 */
	private UserBaseAccountInfo useroutaccountid;//借款用户
	private UserBaseAccountInfo userinaccountid;//投资用户
	private UserBaseAccountInfo userproxyaccountid;//代偿人
	private TenderItem tenderitemtenderid;//标的
	/*提前还款实际到账记录*/
	private AheadRealRepayment aheadRealRepayment;

	private Short datemark;
	/*待收本金,不需要存进数据库的*/
	private Double totalamont;
	private String username;//用户名 数据库无此字段
	private String outname;//借款人用户名
	private String outrealname;//借款人真实姓名
	private String intname;//投资人用户名
	private String tno;//标号
	private String tname;//标名称
	private Double count;//合计
	private Short sumitrepayment;//还款提交状态  1待处理 6还款失败 7审核失败
	public Double getTotalamont() {
		return totalamont;
	}

	public void setTotalamont(Double totalamont) {
		this.totalamont = totalamont;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public String getRbatchno() {
		return rbatchno;
	}

	public void setRbatchno(String rbatchno) {
		this.rbatchno = rbatchno;
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
		this.utorderno = utorderno;
	}

	public Double getBpenalty() {
		return bpenalty;
	}

	public void setBpenalty(Double bpenalty) {
		this.bpenalty = bpenalty;
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

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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

	public BigDecimal getProxyaccountid() {
		return proxyaccountid;
	}

	public void setProxyaccountid(BigDecimal proxyaccountid) {
		this.proxyaccountid = proxyaccountid;
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

	public Double getOverdueamount() {
		return overdueamount;
	}

	public void setOverdueamount(Double overdueamount) {
		this.overdueamount = overdueamount;
	}

	public Double getRemindamount() {
		return remindamount;
	}

	public void setRemindamount(Double remindamount) {
		this.remindamount = remindamount;
	}

	public Short getRmode() {
		return rmode;
	}

	public void setRmode(Short rmode) {
		this.rmode = rmode;
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

	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public Short getIsdarepay() {
		return isdarepay;
	}

	public void setIsdarepay(Short isdarepay) {
		this.isdarepay = isdarepay;
	}

	public Double getRprincipalint() {
		return rprincipalint;
	}

	public void setRprincipalint(Double rprincipalint) {
		this.rprincipalint = rprincipalint;
	}

	public UserBaseAccountInfo getUseroutaccountid() {
		return useroutaccountid;
	}

	public void setUseroutaccountid(UserBaseAccountInfo useroutaccountid) {
		this.useroutaccountid = useroutaccountid;
	}

	public UserBaseAccountInfo getUserinaccountid() {
		return userinaccountid;
	}

	public void setUserinaccountid(UserBaseAccountInfo userinaccountid) {
		this.userinaccountid = userinaccountid;
	}

	public UserBaseAccountInfo getUserproxyaccountid() {
		return userproxyaccountid;
	}

	public void setUserproxyaccountid(UserBaseAccountInfo userproxyaccountid) {
		this.userproxyaccountid = userproxyaccountid;
	}

	public TenderItem getTenderitemtenderid() {
		return tenderitemtenderid;
	}

	public void setTenderitemtenderid(TenderItem tenderitemtenderid) {
		this.tenderitemtenderid = tenderitemtenderid;
	}

	public Short getRepaystatus() {
		return repaystatus;
	}

	public void setRepaystatus(Short repaystatus) {
		this.repaystatus = repaystatus;
	}

	public Short getDatemark() {
		return datemark;
	}

	public void setDatemark(Short datemark) {
		this.datemark = datemark;
	}

	public Double getRestprincipal() {
		return restprincipal;
	}

	public void setRestprincipal(Double restprincipal) {
		this.restprincipal = restprincipal;
	}

	public Short getPlanstatus() {
		return planstatus;
	}

	public void setPlanstatus(Short planstatus) {
		this.planstatus = planstatus;
	}

	public String getDaorderno() {
		return daorderno;
	}

	public void setDaorderno(String daorderno) {
		this.daorderno = daorderno;
	}

	public Double getRvoucher() {
		return rvoucher;
	}

	public void setRvoucher(Double rvoucher) {
		this.rvoucher = rvoucher;
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

	public Double getDisablevoucherint() {
		return disablevoucherint;
	}

	public void setDisablevoucherint(Double disablevoucherint) {
		this.disablevoucherint = disablevoucherint;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public String getHfbatchno() {
		return hfbatchno;
	}

	public void setHfbatchno(String hfbatchno) {
		this.hfbatchno = hfbatchno;
	}

	public AheadRealRepayment getAheadRealRepayment() {
		return aheadRealRepayment;
	}

	public void setAheadRealRepayment(AheadRealRepayment aheadRealRepayment) {
		this.aheadRealRepayment = aheadRealRepayment;
	}

	public String getOutname() {
		return outname;
	}

	public void setOutname(String outname) {
		this.outname = outname;
	}

	public String getIntname() {
		return intname;
	}

	public void setIntname(String intname) {
		this.intname = intname;
	}

	public String getLoanno() {
		return loanno;
	}

	public void setLoanno(String loanno) {
		this.loanno = loanno;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public Short getIsoverdue() {
		return isoverdue;
	}

	public void setIsoverdue(Short isoverdue) {
		this.isoverdue = isoverdue;
	}

	public Date getRprealtime() {
		return rprealtime;
	}

	public void setRprealtime(Date rprealtime) {
		this.rprealtime = rprealtime;
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

	public String getOutrealname() {
		return outrealname;
	}

	public void setOutrealname(String outrealname) {
		this.outrealname = outrealname;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public Short getIssubmit() {
		return issubmit;
	}

	public void setIssubmit(Short issubmit) {
		this.issubmit = issubmit;
	}

	public Short getSumitrepayment() {
		return sumitrepayment;
	}

	public void setSumitrepayment(Short sumitrepayment) {
		this.sumitrepayment = sumitrepayment;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
	public class RepayMentOderDetails{
		private String tname;
		private Double ramount;
 		private String rprealtime;
 		private String remark;
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		public Double getRamount() {
			return ramount;
		}
		public void setRamount(Double ramount) {
			this.ramount = ramount;
		}
 		 
		public String getRprealtime() {
			return rprealtime;
		}
		public void setRprealtime(String rprealtime) {
			this.rprealtime = rprealtime;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		
	
 }

 
}