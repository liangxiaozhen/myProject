package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投标记录实体类
 * @author zhenglm
 *
 */
public class UserTender {
	/** 主键ID */
    private BigDecimal id; 
    /** 投标订单号 */
    private String orderno;
    /** 投资方ID */
    private BigDecimal outaccountid;
    /** 借款方ID */
    private BigDecimal inaccountid;
    /** 投标金额 */
    private Double amount;
    /** 手续费（默认0） */
    private Double fee;
    /** 手续费率（汇付接口默认设置最大为0.5） */
    private Double feerate;
    /** 转账类型（1.账户转账，2.银行卡转账） */
    private Short transfertype;
    /** 转出方余额 */
    private Double oabalance;
    /** 转入方余额 */
    private Double iabalance;
    /** 投标设备来源（pc,ipad,app） */
    private String originclient;
    /** 备注 */
    private String remark;
    /** 标的号ID */
    private BigDecimal tenderid;
    /** 标号 */
    private String tenderNo;
    /** 转账开始时间（日期型） */
    private Date tbegintime;
    /** 转账开始时间（用于页面展示） */
    private String tbegintimeStr;
    /** 转账完成时间（日期型） */
    private Date tendtime;
    /** 转账完成时间（用于页面展示） */
    private String tendtimeStr;
    /** 加息券ID */
    private BigDecimal icid;
    /** 红包ID */
    private BigDecimal reid;
    /** 标的属性（1.信用标，2.抵押标，3.担保标，4.新手标---和标的设置对应） */
    private Short tproperty;
    /** 约标码 */
    private String appointtenderpass;
    /** 是否冻结（0.不冻结，1.冻结，2.解冻） */
    private Short isfreeze;
    /** 投标的状态（0.取消，1.待审核,2.失败,3.撤销,4.完成,5.处理中） */
    private Short tstatus;
    /** 是否系统勾兑（0.未勾兑，1.已勾兑） */
    private Short isblending;
    /** 是否人工勾兑（0.未勾兑，1.已勾兑） */
    private Short ismanblending;
    /** 系统勾兑时间 */
    private Date sysbtime;
    /** 系统勾兑时间（用于页面展示） */
    private String sysbtimeStr;
    /** 人工勾兑时间 */
    private Date manbtime;
    /** 人工勾兑时间（用于页面展示） */
    private String manbtimeStr;
    /** 是否审核 */
    private Short isaudit;
    /** 审核人 */
    private String auditman;
    /** 审核时间 */
    private Date audittime;
    /** 审核时间（用于页面展示） */
    private String audittimeStr;
    /** 是否债转（0.未债转，1.全部债转，2.部分债转） */
    private Short isda;
    /** 投标方式（1.手动，2.自动） */
    private Short tendertype;
    /** 还款完成（0.否，1.是） */
    private Short isrepayend;
    /** 系统勾兑接收数据时间 第一次 */
    private Date sysrectime;
    /** 人工勾兑接收数据时间 第一次 */
    private Date receivetime;
    /** 请求数据包 */
    private String reqquerydata;
    /** 接收数据包 */
    private String recresultdata;
    /** 系统勾兑接收数据时间 第一次（用于页面展示） */
    private String sysrectimeStr;
    /** 人工勾兑接收数据时间 第一次（用于页面展示） */
    private String receivetimeStr;
    /** 投标通道公司 */
    private String paycompany;
    /** 冻结标识 */
    private String freezetrxid;
    /** 投标订单日期 */
    private Date tendertime;
    /** 投标订单日期（用于页面展示） */
    private String tendertimeStr;
    /** 居间费 */
    private Double mediacyfee;
    /** 担保费 */
    private Double guaranteefee;
    /** 风险保证金 */
    private Double riskguarantyfee;
    /** 债权设置表Id */
    private BigDecimal daid;
    /** 原流水号 */
    private String olddaorderno;
    /** 原投标持有天数 */
    private Integer holddate;
    /** 平台杂项收款人 */
    private String pmiscrecman;
    /** 承接总金额 */
    private Double totalamount;
    /** 承接本金 */
    private Double utprinamount;
    /** 承接利息 */
    private Double utintamount;
    /** 剩余金额 */
    private Double restamount;
    /** 承接时年化收益率 */
    private Double yearprofit;
    /** 债转次数 */
    private Short datimes;
    /** 手续费收取模式 */
    private Short feemode;
    /** 债转手续费 */
    private Double dahfee;
    /** 债转手续费收取类型（1定额，2百分比，3最低，4最高） */
    private Short datype;
    /** 投标属性（1原始投标，2债转投标） */
    private Short utproperty;
    /** 债转属性（1正常债转，2逾期债转） */
    private Short daproperty;
    /** 转让时间 */
    private Date dadate;
    /** 起息日 */
    private Date valuedate;
    /** 是否禁止债转上架（0.不禁止，1.禁止） */
    private Short isdisableda;
    /** 居间服务费收款人 */
    private String mrecman;
    /** 居间费收款人商户号 */
    private String mrecmancustid;
    /** 担保服务费收款人 */
    private String gfrecman;
    /** 担保费收款人商户号 */
    private String gfrecmancustid;
    /** 风险保证金收款人 */
    private String rgmrecman;
    /** 风险保证金收款人商户号 */
    private String rgmrecmancustid;
	/** 居间费设置表ID */
    private BigDecimal mfid; 
	/** 担保费设置表ID */
    private BigDecimal gfid; 
	/** 风险保证金设置表ID */
    private BigDecimal rgmid;
    /** 能否债转（0否，1能） */
    private Short isallowda;
    /** 本金金额 */
    private Double realamount;
    /** 类现金金额（多张券时 是多张汇总金额） */
    private Double voucheramount;
    /** 假现金金额（多张券时 是多张汇总金额） */
    private Double likevoucheramount;
    /** 是否允许投标撤销（用于页面判断是否可以投标撤销） */
    private Short isallowrevoke;
    /**承接滞纳金*/
    private Double ocamount;
    /** 投标申请授权码 */
    private String authcode;
    
    /********************关联表********************/
    /** 投资方（关联用户基本信息表） */
    private UserBaseAccountInfo outaccount;
    /** 借款方 （关联用户基本信息表）*/
    private UserBaseAccountInfo inaccount;
    /** 标的设置表 */
    private TenderItem tenderitem;
	/**标的债转设置表*/
    private DebtAttorn debtattorn;
    /**还款计划具体记录表*/
    private RepayMent repayment;
    /**标的债转设置排出人员名单表*/
    private DattornRNameLink dattornrnamelink;
    /**逾期标正常标的标识:正常标识为1,逾期标识为2*/
    private int identifying;
    /**剩余本金:待收本金*/
    private Double surplusamount;
    /**持有天数*/
    private int days;
    /**债转期数:正常标就是0,逾期标就是实际债转期数*/	
    private int  periods;
    /**待收利息*/
    private Double nomalfee;
    /**待收滞纳金*/
    private Double latefee;
    /**债转金额*/
    private Double daamount;
    /**利息债转系数*/
    private Double interestcoefficient;
    /**滞纳金债转系数*/
    private Double latecoefficient;
    /**上架天数*/
    private int deadline;
    /**金额债转系数*/
    private Double coefficient;
    /**实际转出利息*/
    private Double totallixi;
    /**实际转出滞纳金*/
    private Double totalOcamount;
    /**实际转出债转金额*/
    private Double totaldaamount;
    /**可上架天数*/
    private int day;
    /**还款方式*/
    private String repaymentpro;
    /**逾期标志*/
    private String latamark;
    /** 托管方流水号 */
    private String loanno;
    
    /********************页面时间筛选********************/
    /**可转让的查询条件的标记*/
    private String sign;
    /** 投标记录页面时间筛选查询（一周、一月、三月） */
    private String time;
    /** 投标记录页面时间筛选查询（开始时间） */
    private Date starttime;
    /** 投标记录页面时间筛选查询（结束时间） */
    private Date endtime;


	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getLatamark() {
		return latamark;
	}

	public void setLatamark(String latamark) {
		this.latamark = latamark;
	}

	public String getRepaymentpro() {
		return repaymentpro;
	}

	public void setRepaymentpro(String repaymentpro) {
		this.repaymentpro = repaymentpro;
	}

	public int getPeriods() {
		return periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}

	public Double getNomalfee() {
		return nomalfee;
	}

	public void setNomalfee(Double nomalfee) {
		this.nomalfee = nomalfee;
	}

	public Double getLatefee() {
		return latefee;
	}

	public void setLatefee(Double latefee) {
		this.latefee = latefee;
	}
	public Double getDaamount() {
		return daamount;
	}

	public void setDaamount(Double daamount) {
		this.daamount = daamount;
	}

	public Double getInterestcoefficient() {
		return interestcoefficient;
	}

	public void setInterestcoefficient(Double interestcoefficient) {
		this.interestcoefficient = interestcoefficient;
	}

	public Double getLatecoefficient() {
		return latecoefficient;
	}

	public void setLatecoefficient(Double latecoefficient) {
		this.latecoefficient = latecoefficient;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Double getTotallixi() {
		return totallixi;
	}

	public void setTotallixi(Double totallixi) {
		this.totallixi = totallixi;
	}

	public Double getTotalOcamount() {
		return totalOcamount;
	}

	public void setTotalOcamount(Double totalOcamount) {
		this.totalOcamount = totalOcamount;
	}

	public Double getTotaldaamount() {
		return totaldaamount;
	}

	public void setTotaldaamount(Double totaldaamount) {
		this.totaldaamount = totaldaamount;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getIdentifying() {
		return identifying;
	}

	public void setIdentifying(int identifying) {
		this.identifying = identifying;
	}

	public Double getSurplusamount() {
		return surplusamount;
	}

	public void setSurplusamount(Double surplusamount) {
		this.surplusamount = surplusamount;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
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

    public Short getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(Short transfertype) {
        this.transfertype = transfertype;
    }

    public Double getOabalance() {
        return oabalance;
    }

    public void setOabalance(Double oabalance) {
        this.oabalance = oabalance;
    }

    public Double getIabalance() {
        return iabalance;
    }

    public void setIabalance(Double iabalance) {
        this.iabalance = iabalance;
    }

    public String getOriginclient() {
        return originclient;
    }

    public void setOriginclient(String originclient) {
        this.originclient = originclient == null ? null : originclient.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public Date getTbegintime() {
        return tbegintime;
    }

    public void setTbegintime(Date tbegintime) {
        this.tbegintime = tbegintime;
    }

    public Date getTendtime() {
        return tendtime;
    }

    public void setTendtime(Date tendtime) {
        this.tendtime = tendtime;
    }

    public BigDecimal getIcid() {
        return icid;
    }

    public void setIcid(BigDecimal icid) {
        this.icid = icid;
    }

    public BigDecimal getReid() {
        return reid;
    }

    public void setReid(BigDecimal reid) {
        this.reid = reid;
    }

    public Short getTproperty() {
        return tproperty;
    }

    public void setTproperty(Short tproperty) {
        this.tproperty = tproperty;
    }

    public String getAppointtenderpass() {
        return appointtenderpass;
    }

    public void setAppointtenderpass(String appointtenderpass) {
        this.appointtenderpass = appointtenderpass == null ? null : appointtenderpass.trim();
    }

    public Short getIsfreeze() {
        return isfreeze;
    }

    public void setIsfreeze(Short isfreeze) {
        this.isfreeze = isfreeze;
    }

    public Short getTstatus() {
        return tstatus;
    }

    public void setTstatus(Short tstatus) {
        this.tstatus = tstatus;
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

	public String getTbegintimeStr() {
		return tbegintimeStr;
	}

	public void setTbegintimeStr(String tbegintimeStr) {
		this.tbegintimeStr = tbegintimeStr;
	}

	public String getTendtimeStr() {
		return tendtimeStr;
	}

	public void setTendtimeStr(String tendtimeStr) {
		this.tendtimeStr = tendtimeStr;
	}

	public String getSysbtimeStr() {
		return sysbtimeStr;
	}

	public void setSysbtimeStr(String sysbtimeStr) {
		this.sysbtimeStr = sysbtimeStr;
	}

	public String getManbtimeStr() {
		return manbtimeStr;
	}

	public void setManbtimeStr(String manbtimeStr) {
		this.manbtimeStr = manbtimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
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

	public Short getIsda() {
		return isda;
	}

	public void setIsda(Short isda) {
		this.isda = isda;
	}

	public Short getTendertype() {
		return tendertype;
	}

	public void setTendertype(Short tendertype) {
		this.tendertype = tendertype;
	}

	public Short getIsrepayend() {
		return isrepayend;
	}

	public void setIsrepayend(Short isrepayend) {
		this.isrepayend = isrepayend;
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

	public String getSysrectimeStr() {
		return sysrectimeStr;
	}

	public void setSysrectimeStr(String sysrectimeStr) {
		this.sysrectimeStr = sysrectimeStr;
	}

	public String getReceivetimeStr() {
		return receivetimeStr;
	}

	public void setReceivetimeStr(String receivetimeStr) {
		this.receivetimeStr = receivetimeStr;
	}

	public String getPaycompany() {
		return paycompany;
	}

	public void setPaycompany(String paycompany) {
		this.paycompany = paycompany;
	}

	public TenderItem getTenderitem() {
		return tenderitem;
	}

	public void setTenderitem(TenderItem tenderitem) {
		this.tenderitem = tenderitem;
	}

	public String getFreezetrxid() {
		return freezetrxid;
	}

	public void setFreezetrxid(String freezetrxid) {
		this.freezetrxid = freezetrxid;
	}

	public Date getTendertime() {
		return tendertime;
	}

	public void setTendertime(Date tendertime) {
		this.tendertime = tendertime;
	}

	public String getTendertimeStr() {
		return tendertimeStr;
	}

	public void setTendertimeStr(String tendertimeStr) {
		this.tendertimeStr = tendertimeStr;
	}

	public Double getMediacyfee() {
		return mediacyfee;
	}

	public void setMediacyfee(Double mediacyfee) {
		this.mediacyfee = mediacyfee;
	}

	public Double getGuaranteefee() {
		return guaranteefee;
	}

	public void setGuaranteefee(Double guaranteefee) {
		this.guaranteefee = guaranteefee;
	}

	public Double getRiskguarantyfee() {
		return riskguarantyfee;
	}

	public void setRiskguarantyfee(Double riskguarantyfee) {
		this.riskguarantyfee = riskguarantyfee;
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

	public DattornRNameLink getDattornrnamelink() {
		return dattornrnamelink;
	}

	public void setDattornrnamelink(DattornRNameLink dattornrnamelink) {
		this.dattornrnamelink = dattornrnamelink;
	}

	public BigDecimal getDaid() {
		return daid;
	}

	public void setDaid(BigDecimal daid) {
		this.daid = daid;
	}

	public String getOlddaorderno() {
		return olddaorderno;
	}

	public void setOlddaorderno(String olddaorderno) {
		this.olddaorderno = olddaorderno;
	}

	public Integer getHolddate() {
		return holddate;
	}

	public void setHolddate(Integer holddate) {
		this.holddate = holddate;
	}

	public String getPmiscrecman() {
		return pmiscrecman;
	}

	public void setPmiscrecman(String pmiscrecman) {
		this.pmiscrecman = pmiscrecman;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Double getUtprinamount() {
		return utprinamount;
	}

	public void setUtprinamount(Double utprinamount) {
		this.utprinamount = utprinamount;
	}

	public Double getUtintamount() {
		return utintamount;
	}

	public void setUtintamount(Double utintamount) {
		this.utintamount = utintamount;
	}

	public Double getRestamount() {
		return restamount;
	}

	public void setRestamount(Double restamount) {
		this.restamount = restamount;
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

	public Short getFeemode() {
		return feemode;
	}

	public void setFeemode(Short feemode) {
		this.feemode = feemode;
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

	public Short getUtproperty() {
		return utproperty;
	}

	public void setUtproperty(Short utproperty) {
		this.utproperty = utproperty;
	}

	public Short getDaproperty() {
		return daproperty;
	}

	public void setDaproperty(Short daproperty) {
		this.daproperty = daproperty;
	}

	public Date getDadate() {
		return dadate;
	}

	public void setDadate(Date dadate) {
		this.dadate = dadate;
	}

	public Date getValuedate() {
		return valuedate;
	}

	public void setValuedate(Date valuedate) {
		this.valuedate = valuedate;
	}

	public Short getIsdisableda() {
		return isdisableda;
	}

	public void setIsdisableda(Short isdisableda) {
		this.isdisableda = isdisableda;
	}

	public String getMrecman() {
		return mrecman;
	}

	public void setMrecman(String mrecman) {
		this.mrecman = mrecman;
	}

	public String getMrecmancustid() {
		return mrecmancustid;
	}

	public void setMrecmancustid(String mrecmancustid) {
		this.mrecmancustid = mrecmancustid;
	}

	public String getGfrecman() {
		return gfrecman;
	}

	public void setGfrecman(String gfrecman) {
		this.gfrecman = gfrecman;
	}

	public String getGfrecmancustid() {
		return gfrecmancustid;
	}

	public void setGfrecmancustid(String gfrecmancustid) {
		this.gfrecmancustid = gfrecmancustid;
	}

	public String getRgmrecman() {
		return rgmrecman;
	}

	public void setRgmrecman(String rgmrecman) {
		this.rgmrecman = rgmrecman;
	}

	public String getRgmrecmancustid() {
		return rgmrecmancustid;
	}

	public void setRgmrecmancustid(String rgmrecmancustid) {
		this.rgmrecmancustid = rgmrecmancustid;
	}

    public BigDecimal getMfid() {
		return mfid;
	}

	public void setMfid(BigDecimal mfid) {
		this.mfid = mfid;
	}

	public BigDecimal getGfid() {
		return gfid;
	}

	public void setGfid(BigDecimal gfid) {
		this.gfid = gfid;
	}

	public BigDecimal getRgmid() {
		return rgmid;
	}

	public void setRgmid(BigDecimal rgmid) {
		this.rgmid = rgmid;
	}

	public Short getIsallowda() {
		return isallowda;
	}

	public void setIsallowda(Short isallowda) {
		this.isallowda = isallowda;
	}

	public Double getRealamount() {
		return realamount;
	}

	public void setRealamount(Double realamount) {
		this.realamount = realamount;
	}

	public Double getVoucheramount() {
		return voucheramount;
	}

	public void setVoucheramount(Double voucheramount) {
		this.voucheramount = voucheramount;
	}

	public Double getLikevoucheramount() {
		return likevoucheramount;
	}

	public void setLikevoucheramount(Double likevoucheramount) {
		this.likevoucheramount = likevoucheramount;
	}

	public Short getIsallowrevoke() {
		return isallowrevoke;
	}

	public void setIsallowrevoke(Short isallowrevoke) {
		this.isallowrevoke = isallowrevoke;
	}

	public Double getOcamount() {
		return ocamount;
	}

	public void setOcamount(Double ocamount) {
		this.ocamount = ocamount;
	}

	public String getLoanno() {
		return loanno;
	}

	public void setLoanno(String loanno) {
		this.loanno = loanno;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

    public String  getTenderNo() {
        return tenderNo;
    }

    public void setTenderNo(String tenderNo) {
        this.tenderNo = tenderNo;
    }

    @Override
	public String toString() {
		return "UserTender [id=" + id + ", orderno=" + orderno + ", outaccountid=" + outaccountid + ", inaccountid="
				+ inaccountid + ", amount=" + amount + ", fee=" + fee + ", feerate=" + feerate + ", transfertype="
				+ transfertype + ", oabalance=" + oabalance + ", iabalance=" + iabalance + ", originclient="
				+ originclient + ", remark=" + remark + ", tenderid=" + tenderid + ", tbegintime=" + tbegintime
				+ ", tbegintimeStr=" + tbegintimeStr + ", tendtime=" + tendtime + ", tendtimeStr=" + tendtimeStr
				+ ", icid=" + icid + ", reid=" + reid + ", tproperty=" + tproperty + ", appointtenderpass="
				+ appointtenderpass + ", isfreeze=" + isfreeze + ", tstatus=" + tstatus + ", isblending=" + isblending
				+ ", ismanblending=" + ismanblending + ", sysbtime=" + sysbtime + ", sysbtimeStr=" + sysbtimeStr
				+ ", manbtime=" + manbtime + ", manbtimeStr=" + manbtimeStr + ", isaudit=" + isaudit + ", auditman="
				+ auditman + ", audittime=" + audittime + ", audittimeStr=" + audittimeStr + ", isda=" + isda
				+ ", tendertype=" + tendertype + ", isrepayend=" + isrepayend + ", sysrectime=" + sysrectime
				+ ", receivetime=" + receivetime + ", reqquerydata=" + reqquerydata + ", recresultdata=" + recresultdata
				+ ", sysrectimeStr=" + sysrectimeStr + ", receivetimeStr=" + receivetimeStr + ", paycompany="
				+ paycompany + ", freezetrxid=" + freezetrxid + ", tendertime=" + tendertime + ", tendertimeStr="
				+ tendertimeStr + ", mediacyfee=" + mediacyfee + ", guaranteefee=" + guaranteefee + ", riskguarantyfee="
				+ riskguarantyfee + ", daid=" + daid + ", olddaorderno=" + olddaorderno + ", holddate=" + holddate
				+ ", pmiscrecman=" + pmiscrecman + ", totalamount=" + totalamount + ", utprinamount=" + utprinamount
				+ ", utintamount=" + utintamount + ", restamount=" + restamount + ", yearprofit=" + yearprofit
				+ ", datimes=" + datimes + ", feemode=" + feemode + ", dahfee=" + dahfee + ", datype=" + datype
				+ ", utproperty=" + utproperty + ", daproperty=" + daproperty + ", dadate=" + dadate + ", valuedate="
				+ valuedate + ", isdisableda=" + isdisableda + ", mrecman=" + mrecman + ", mrecmancustid="
				+ mrecmancustid + ", gfrecman=" + gfrecman + ", gfrecmancustid=" + gfrecmancustid + ", rgmrecman="
				+ rgmrecman + ", rgmrecmancustid=" + rgmrecmancustid + ", mfid=" + mfid + ", gfid=" + gfid + ", rgmid="
				+ rgmid + ", isallowda=" + isallowda + ", realamount=" + realamount + ", voucheramount=" + voucheramount
				+ ", likevoucheramount=" + likevoucheramount + ", isallowrevoke=" + isallowrevoke + ", ocamount="
				+ ocamount + ", outaccount=" + outaccount + ", inaccount=" + inaccount + ", tenderitem=" + tenderitem
				+ ", debtattorn=" + debtattorn + ", repayment=" + repayment + ", dattornrnamelink=" + dattornrnamelink
				+ ", identifying=" + identifying + ", surplusamount=" + surplusamount + ", days=" + days + ", periods="
				+ periods + ", nomalfee=" + nomalfee + ", latefee=" + latefee + ", daamount=" + daamount
				+ ", interestcoefficient=" + interestcoefficient + ", latecoefficient=" + latecoefficient
				+ ", deadline=" + deadline + ", coefficient=" + coefficient + ", totallixi=" + totallixi
				+ ", totalOcamount=" + totalOcamount + ", totaldaamount=" + totaldaamount + ", day=" + day
				+ ", repaymentpro=" + repaymentpro + ", latamark=" + latamark + "]";
	}
}