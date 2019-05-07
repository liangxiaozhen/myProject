package com.ptpl.web.util;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: HuifuParams
 * @Package com.ptpl.web.util
 * @Description: TODO(汇付天下 参数工具类)
 * @author chenjiaming
 * @date 2016年8月9日 下午2:40:26
 * @version V1.0
 */
public class HuifuParams implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/* 版本号目前固定为 10，如版本升级，能向前兼容 */
	private String Version = "10";

	/* 消息类型 必须 每一种消息类型代表一种交易 */
	private String CmdId;

	/* 商户客户号 必须 由汇付生成，商户的唯一性标识 */
	private String MerCustId = "6000060004166478";

	/* 用户客户号 */
	private String UsrCustId;
	
	/*子账户类型*/
	private String SubAcctType;
	/*子账户号*/
	private String SubAcctId;

	/*
	 * 商户后台应答地址 必须 通过后台异步通知，商户网站都应在应答接收页面 输出 RECV_ORD_ID 字样的字符串，表明商户已经 收到该笔交易结果
	 */
	private String BgRetUrl;

	/* 页面返回 URL */
	private String RetUrl;

	/*
	 * 页面返回 URL private String RetUrl /*用户号 可选 商户下的平台用户号，在每个商户下唯一（必须是 6-25
	 * 位的半角字符）
	 */
	private String UsrId;

	/* 真实名称 用户的真实姓名 */
	private String UsrName;

	/* 证件类型 可选 00-- 身份证 */
	private String IdType = "00";

	/* 证件号码 用户证件号码 */
	private String IdNo;

	/* 手机号 可选 本平台系统提供按照手机号查询订单的功能 */
	private String UsrMp;

	/* 用户 Email 可选 操作员的 Email */
	private String UsrEmail;

	/*
	 * 商户私有域 为商户的自定义字段，该字段在交易完成后由本平 台原样返回
	 */
	private String MerPriv;

	/*
	 * 页面类型 PageType 为空：即自适应风格页面 PageType =1：app 应用风格页面（无标题） PageType =2：app
	 * 应用风格页面（有标题
	 */
	private String PageType;

	/*
	 * 编码集 加签验签的时候，商户需告知汇付其系统编码，汇 付在验签的时候进行相应的编码转换验签
	 */
	private String CharSet = "UTF-8";

	/* 签名 */
	private String ChkValue;

	/*
	 * 用户状态 ‘ N’ – 正常 ‘ A’ – 待激活，登录后需要用户激活 ‘ C’ – 被关闭，临时关闭，不能登陆 ‘ D’ – 销户
	 */
	private String UsrStat;

	/* 身份证号 用户身份证号 */
	private String CertId;

	/* 应答返回码000--调用成功 */
	private String RespCode;

	/* 应答描述 */
	private String RespDesc;

	/** 充值字段 */
	/* 订单日期 */
	private String OrdDate;
	/* 支付网关业务代号 */
	private String GateBusiId;
	/* 借贷记标记 D--借记，储蓄卡 C--贷记 ，信用卡 */
	private String DcFlag;
	/* 入参扩展域 */
	/** 充值返回参数 */
	/* 二级应答返回码 */
	private String SecRespCode;
	/* 二级应答描述 */
	private String SecRespDesc;
	/* 开户银行代号 */
	private String GateBankId;
	/* 本平台交易唯一标识 8 位本平台日期+10 位系统流水号 */
	private String TrxId;

	/* 返参扩展域 */
	private String RespExt;
	/* 订单号 */
	private String OrdId;
	/* 交易金额 */
	private String TransAmt;
	/* 商户收取服务费金商户收取服务费金 */
	private String ServFee;
	/* 商户子账户号 */
	private String ServFeeAcctId;
	/* 备注 */
	private String Remark;
	/* 入参扩展域 */
	private String ReqExt;
	/* 手续费收取对象 */
	private String FeeObjFlag;
	/* 手续费收取子账户 */
	private String FeeAcctId;
	/* 取现渠道 */
	private String CashChl;
	/* 实际到账金额 */
	private String RealTransAmt;
	/* 开户银行账号 */
	private String OpenAcctId;
	/* 开户银行代号 */
	private String OpenBankId;
	/* 手续费金额 */
	private String FeeAmt;
	/* 手续费扣款客户号 */
	private String FeeCustId;
	private String AuditFlag;
	/* 消息类型 */
	private String RespType;

	/********** 企业开户字段 *************/
	/* 组织机构代码 */
	private String InstuCode;
	/* 营业执照编号 */
	private String BusiCode;
	/* 税务登记号 */
	private String TaxCode;
	/*
	 * 担保类型是否担保类型，Y：是 N：否第一次请求开户，如果该字段为空，默认为
	 * N,如果需要修改企业开户信息，重复请求，若该字段为空，默认为上一次的类型
	 */
	private String GuarType;
	/* 企业用户备案金 */
	private String GuarCorpEarnestAmt;
	/* 审核状态 审核过程中的状态 I--初始 T--提交 P--审核中 R--审核拒绝 F--开户失败 K--开户中 Y--开户成功 */
	private String AuditStat;
	/* 审核状态描述 */
	private String AuditDesc;
	/* 开户银行账号 */
	private String CardId;
	/********** 自动扣款(还款) **********/
	/* 借款用户ID */
	private BigDecimal outaccountid;
	/* 投资用户ID */
	private BigDecimal inaccountid;
	/* 代还款人ID */
	private BigDecimal proxyaccountid;
	/* 标号ID */
	private BigDecimal tenderid;
 	/* 还款本金 */
	private String PrincipalAmt;
	/* 还款利息 */
	private String InterestAmt;
	/* 垫资/代偿对象 */
	private String DzObject;
	/*请求扩展参数 浏览器参数传递*/
	private String ReqExtStr;
	/*分账账号串参数 浏览器参数传递*/
	private String DivDetailsStr;

	/********** 主动/自动投标字段 *************/
	/** 最大投资手续费率 */
	private String MaxTenderRate;
	/** 借款人信息 */
	private String BorrowerDetails;
	/** 借款人客户号 */
	private String BorrowerCustId;
	/** 借款金额 */
	private String BorrowerAmt;
	/** 借款手续费率 */
	private String BorrowerRate;
	/** 项目ID */
	private String ProId;
	/** 是否冻结 */
	private String IsFreeze;
	/** 冻结订单号 */
	private String FreezeOrdId;
	/** 代金券出账子账户 */
	private String AcctId;
	/** 代金券金额 */
	private String VocherAmt;
	/** 冻结标识 */
	private String FreezeTrxId;

	/********** 自动扣款（放款）字段 *************/
	/** 出账客户号 */
	private String OutCustId;
	/** 扣款手续费 */
	private String Fee;
	/** 订单号 */
	private String SubOrdId;
	/** 订单日期 */
	private String SubOrdDate;
	/** 入账客户号 */
	private String InCustId;
	/** 分账账户串 */
	private String DivDetails;
	/** 分账商户号 */
	private String DivCustId;
	/** 分账账户号 */
	private String DivAcctId;
	/** 分账金额 */
	private String DivAmt;
	/** 是否默认 */
	private String IsDefault;
	/** 是否解冻 */
	private String IsUnFreeze;
	/** 解冻订单号 */
	private String UnFreezeOrdId;
	/** 代金券金额 */
	private String loansVocherAmt;
	/** 出账子账户 */
	private String OutAcctId;
	/** 入账子账户 */
	private String InAcctId;
	/**入账账户类型 */
	private String InAcctType;
	
	/********** 自动投标计划 *************/
	/** 投标计划类型 */
	private String TenderPlanType;

	/***** 债权转让字段 *****/

	/* 转让人客户号 */
	private String SellCustId;
	/* 转让金额 */
	private String CreditAmt;
	/* 承接金额 */
	private String CreditDealAmt;
	/* 债权转让明细 */
	private String BidDetails;
	/* 承接人客户号 */
	private String BuyCustId;
	/* 挂牌债权 ID */
	private String LcId;
	/* 挂牌债权总金额 */
	private String TotalLcAmt;
	/* 被转让的投标订单号 */
	private String BidOrdId;
	/* 被转让的投标订单日期 */
	private String BidOrdDate;
	/* 转让金额 */
	private String BidCreditAmt;
	/* 明细转让金额 */
	private String BorrowerCreditAmt;
	/* 已还款金额 */
	private String PrinAmt;
	private String periods;
	/******** 标的信息字段  ******/
	private String BidType;
	private String BorrTotAmt;
	private String YearRate;
	private String RetInterest;
	private String LastRetDate;
	private String BidStartDate;
	private String BidEndDate;
	private String RetType;
	private String GuarantType;
	private String BidProdType;
	private String RiskCtlType;
	private String LimitMinBidAmt;
	private String LimitBidSum;
	private String LimitMaxBidSum;
	private String LimitMinBidSum;
	private String BidPayforState;
	private String BorrType;
	private String BorrCustId;
	private String BorrBusiCode;
	private String BorrCertType;
	private String BorrCertId;
	private String BorrMobiPhone;
	private String BorrPhone;
	private String BorrWorkYear;
	private String BorrIncome;
	private String BorrMarriage;
	private String BorrEmail;

	private String BidName;
	private String LoanPeriod;
	private String RetDate;
	private String Recommer;
	private String BorrPurpose;
	private String OverdueProba;
	private String BorrName;
	private String BorrEdu;
	private String BorrAddr;
	private String BorrWork;

	/**** 批量还款接口 *****/
	/*还款批次号*/
	private String BatchId;
	/*商户还款订单日期*/
	private String MerOrdDate;
	/*成功条数*/
	private String SucNum;
	/*失败条数*/
	private String FailNum;
	/*错误信息*/
	private String ErrMsg;
	/*单条返回码*/
	private String ItemCode;
	/*还款账户串*/
	private String InDetails;
	/*垫资代偿对象*/
	private String DzBorrCustId;
	
	/*放款还款对账*/
	/*开始时间*/
	private String BeginDate;
	/*开始时间*/
	private String EndDate;
	/*开始页数*/
	private String PageNum;
	/*条数*/
	private String PageSize;
	/*交易查询类型*/
	private String QueryTransType;
	/* 用于浏览器字符串传递  */
	private String browserStr;

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getCmdId() {
		return CmdId;
	}

	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getUsrId() {
		return UsrId;
	}

	public void setUsrId(String usrId) {
		UsrId = usrId;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	public String getIdType() {
		return IdType;
	}

	public void setIdType(String idType) {
		IdType = idType;
	}

	public String getIdNo() {
		return IdNo;
	}

	public void setIdNo(String idNo) {
		IdNo = idNo;
	}

	public String getUsrMp() {
		return UsrMp;
	}

	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}

	public String getUsrEmail() {
		return UsrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		UsrEmail = usrEmail;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getPageType() {
		return PageType;
	}

	public void setPageType(String pageType) {
		PageType = pageType;
	}

	public String getCharSet() {
		return CharSet;
	}

	public void setCharSet(String charSet) {
		CharSet = charSet;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getUsrStat() {
		return UsrStat;
	}

	public void setUsrStat(String usrStat) {
		UsrStat = usrStat;
	}

	public String getCertId() {
		return CertId;
	}

	public void setCertId(String certId) {
		CertId = certId;
	}

	public String getRespCode() {
		return RespCode;
	}

	public void setRespCode(String respCode) {
		RespCode = respCode;
	}

	public String getRespDesc() {
		return RespDesc;
	}

	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getServFee() {
		return ServFee;
	}

	public void setServFee(String servFee) {
		ServFee = servFee;
	}

	public String getServFeeAcctId() {
		return ServFeeAcctId;
	}

	public void setServFeeAcctId(String servFeeAcctId) {
		ServFeeAcctId = servFeeAcctId;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	public String getFeeObjFlag() {
		return FeeObjFlag;
	}

	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
	}

	public String getFeeAcctId() {
		return FeeAcctId;
	}

	public void setFeeAcctId(String feeAcctId) {
		FeeAcctId = feeAcctId;
	}

	public String getCashChl() {
		return CashChl;
	}

	public void setCashChl(String cashChl) {
		CashChl = cashChl;
	}

	public String getRealTransAmt() {
		return RealTransAmt;
	}

	public void setRealTransAmt(String realTransAmt) {
		RealTransAmt = realTransAmt;
	}

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getFeeAmt() {
		return FeeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}

	public String getFeeCustId() {
		return FeeCustId;
	}

	public void setFeeCustId(String feeCustId) {
		FeeCustId = feeCustId;
	}

	public String getOrdDate() {
		return OrdDate;
	}

	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}

	public String getGateBusiId() {
		return GateBusiId;
	}

	public String getSecRespCode() {
		return SecRespCode;
	}

	public void setSecRespCode(String secRespCode) {
		SecRespCode = secRespCode;
	}

	public String getSecRespDesc() {
		return SecRespDesc;
	}

	public void setSecRespDesc(String secRespDesc) {
		SecRespDesc = secRespDesc;
	}

	public String getGateBankId() {
		return GateBankId;
	}

	public void setGateBankId(String gateBankId) {
		GateBankId = gateBankId;
	}

	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}

	public String getDcFlag() {
		return DcFlag;
	}

	public void setDcFlag(String dcFlag) {
		DcFlag = dcFlag;
	}

	public String getRespType() {
		return RespType;
	}

	public void setRespType(String respType) {
		RespType = respType;
	}

	public String getInstuCode() {
		return InstuCode;
	}

	public void setInstuCode(String instuCode) {
		InstuCode = instuCode;
	}

	public String getBusiCode() {
		return BusiCode;
	}

	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}

	public String getTaxCode() {
		return TaxCode;
	}

	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}

	public String getGuarType() {
		return GuarType;
	}

	public void setGuarType(String guarType) {
		GuarType = guarType;
	}

	public String getGuarCorpEarnestAmt() {
		return GuarCorpEarnestAmt;
	}

	public void setGuarCorpEarnestAmt(String guarCorpEarnestAmt) {
		GuarCorpEarnestAmt = guarCorpEarnestAmt;
	}

	public String getAuditStat() {
		return AuditStat;
	}

	public void setAuditStat(String auditStat) {
		AuditStat = auditStat;
	}

	public String getAuditDesc() {
		return AuditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		AuditDesc = auditDesc;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}
	@Override
	public String toString() {
		return "HuifuParams [Version=" + Version + ", CmdId=" + CmdId + ", MerCustId=" + MerCustId + ", UsrCustId="
				+ UsrCustId + ", SubAcctType=" + SubAcctType + ", SubAcctId=" + SubAcctId + ", BgRetUrl=" + BgRetUrl
				+ ", RetUrl=" + RetUrl + ", UsrId=" + UsrId + ", UsrName=" + UsrName + ", IdType=" + IdType + ", IdNo="
				+ IdNo + ", UsrMp=" + UsrMp + ", UsrEmail=" + UsrEmail + ", MerPriv=" + MerPriv + ", PageType="
				+ PageType + ", CharSet=" + CharSet + ", ChkValue=" + ChkValue + ", UsrStat=" + UsrStat + ", CertId="
				+ CertId + ", RespCode=" + RespCode + ", RespDesc=" + RespDesc + ", OrdDate=" + OrdDate
				+ ", GateBusiId=" + GateBusiId + ", DcFlag=" + DcFlag + ", SecRespCode=" + SecRespCode
				+ ", SecRespDesc=" + SecRespDesc + ", GateBankId=" + GateBankId + ", TrxId=" + TrxId + ", RespExt="
				+ RespExt + ", OrdId=" + OrdId + ", TransAmt=" + TransAmt + ", ServFee=" + ServFee + ", ServFeeAcctId="
				+ ServFeeAcctId + ", Remark=" + Remark + ", ReqExt=" + ReqExt + ", FeeObjFlag=" + FeeObjFlag
				+ ", FeeAcctId=" + FeeAcctId + ", CashChl=" + CashChl + ", RealTransAmt=" + RealTransAmt
				+ ", OpenAcctId=" + OpenAcctId + ", OpenBankId=" + OpenBankId + ", FeeAmt=" + FeeAmt + ", FeeCustId="
				+ FeeCustId + ", AuditFlag=" + AuditFlag + ", RespType=" + RespType + ", InstuCode=" + InstuCode
				+ ", BusiCode=" + BusiCode + ", TaxCode=" + TaxCode + ", GuarType=" + GuarType + ", GuarCorpEarnestAmt="
				+ GuarCorpEarnestAmt + ", AuditStat=" + AuditStat + ", AuditDesc=" + AuditDesc + ", CardId=" + CardId
				+ ", PrincipalAmt=" + PrincipalAmt + ", InterestAmt=" + InterestAmt + ", DzObject=" + DzObject
				+ ", MaxTenderRate=" + MaxTenderRate + ", BorrowerDetails=" + BorrowerDetails + ", BorrowerCustId="
				+ BorrowerCustId + ", BorrowerAmt=" + BorrowerAmt + ", BorrowerRate=" + BorrowerRate + ", ProId="
				+ ProId + ", IsFreeze=" + IsFreeze + ", FreezeOrdId=" + FreezeOrdId + ", AcctId=" + AcctId
				+ ", VocherAmt=" + VocherAmt + ", FreezeTrxId=" + FreezeTrxId + ", OutCustId=" + OutCustId + ", Fee="
				+ Fee + ", SubOrdId=" + SubOrdId + ", SubOrdDate=" + SubOrdDate + ", InCustId=" + InCustId
				+ ", DivDetails=" + DivDetails + ", DivCustId=" + DivCustId + ", DivAcctId=" + DivAcctId + ", DivAmt="
				+ DivAmt + ", IsDefault=" + IsDefault + ", IsUnFreeze=" + IsUnFreeze + ", UnFreezeOrdId="
				+ UnFreezeOrdId + ", loansVocherAmt=" + loansVocherAmt + ", OutAcctId=" + OutAcctId + ", InAcctId="
				+ InAcctId + ", SellCustId=" + SellCustId + ", CreditAmt=" + CreditAmt + ", CreditDealAmt="
				+ CreditDealAmt + ", BidDetails=" + BidDetails + ", BuyCustId=" + BuyCustId + ", LcId=" + LcId
				+ ", TotalLcAmt=" + TotalLcAmt + ", BidOrdId=" + BidOrdId + ", BidOrdDate=" + BidOrdDate
				+ ", BidCreditAmt=" + BidCreditAmt + ", BorrowerCreditAmt=" + BorrowerCreditAmt + ", PrinAmt=" + PrinAmt
				+ ", BidType=" + BidType + ", BorrTotAmt=" + BorrTotAmt + ", YearRate=" + YearRate + ", RetInterest="
				+ RetInterest + ", LastRetDate=" + LastRetDate + ", BidStartDate=" + BidStartDate + ", BidEndDate="
				+ BidEndDate + ", RetType=" + RetType + ", GuarantType=" + GuarantType + ", BidProdType=" + BidProdType
				+ ", RiskCtlType=" + RiskCtlType + ", LimitMinBidAmt=" + LimitMinBidAmt + ", LimitBidSum=" + LimitBidSum
				+ ", LimitMaxBidSum=" + LimitMaxBidSum + ", LimitMinBidSum=" + LimitMinBidSum + ", BidPayforState="
				+ BidPayforState + ", BorrType=" + BorrType + ", BorrCustId=" + BorrCustId + ", BorrBusiCode="
				+ BorrBusiCode + ", BorrCertType=" + BorrCertType + ", BorrCertId=" + BorrCertId + ", BorrMobiPhone="
				+ BorrMobiPhone + ", BorrPhone=" + BorrPhone + ", BorrWorkYear=" + BorrWorkYear + ", BorrIncome="
				+ BorrIncome + ", BorrMarriage=" + BorrMarriage + ", BorrEmail=" + BorrEmail + ", BidName=" + BidName
				+ ", LoanPeriod=" + LoanPeriod + ", RetDate=" + RetDate + ", Recommer=" + Recommer + ", BorrPurpose="
				+ BorrPurpose + ", OverdueProba=" + OverdueProba + ", BorrName=" + BorrName + ", BorrEdu=" + BorrEdu
				+ ", BorrAddr=" + BorrAddr + ", BorrWork=" + BorrWork + ", browserStr=" + browserStr + "]";
	}

	public String getSubAcctType() {
		return SubAcctType;
	}

	public void setSubAcctType(String subAcctType) {
		SubAcctType = subAcctType;
	}

	public String getSubAcctId() {
		return SubAcctId;
	}

	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
	}

	public String getAuditFlag() {
		return AuditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
	}

	public String getOutCustId() {
		return OutCustId;
	}

	public void setOutCustId(String outCustId) {
		OutCustId = outCustId;
	}

	public String getSubOrdId() {
		return SubOrdId;
	}

	public void setSubOrdId(String subOrdId) {
		SubOrdId = subOrdId;
	}

	public String getSubOrdDate() {
		return SubOrdDate;
	}

	public void setSubOrdDate(String subOrdDate) {
		SubOrdDate = subOrdDate;
	}

	public String getOutAcctId() {
		return OutAcctId;
	}

	public void setOutAcctId(String outAcctId) {
		OutAcctId = outAcctId;
	}

	public String getPrincipalAmt() {
		return PrincipalAmt;
	}

	public void setPrincipalAmt(String principalAmt) {
		PrincipalAmt = principalAmt;
	}

	public String getInterestAmt() {
		return InterestAmt;
	}

	public void setInterestAmt(String interestAmt) {
		InterestAmt = interestAmt;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getInCustId() {
		return InCustId;
	}

	public void setInCustId(String inCustId) {
		InCustId = inCustId;
	}

	public String getInAcctId() {
		return InAcctId;
	}

	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}

	public String getDzObject() {
		return DzObject;
	}

	public void setDzObject(String dzObject) {
		DzObject = dzObject;
	}

	public String getDivDetails() {
		return DivDetails;
	}

	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}

	public String getDivCustId() {
		return DivCustId;
	}

	public void setDivCustId(String divCustId) {
		DivCustId = divCustId;
	}

	public String getDivAcctId() {
		return DivAcctId;
	}

	public void setDivAcctId(String divAcctId) {
		DivAcctId = divAcctId;
	}

	public String getDivAmt() {
		return DivAmt;
	}

	public void setDivAmt(String divAmt) {
		DivAmt = divAmt;
	}

	public String getMaxTenderRate() {
		return MaxTenderRate;
	}

	public void setMaxTenderRate(String maxTenderRate) {
		MaxTenderRate = maxTenderRate;
	}

	public String getBorrowerDetails() {
		return BorrowerDetails;
	}

	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

	public String getBorrowerCustId() {
		return BorrowerCustId;
	}

	public void setBorrowerCustId(String borrowerCustId) {
		BorrowerCustId = borrowerCustId;
	}

	public String getBorrowerAmt() {
		return BorrowerAmt;
	}

	public void setBorrowerAmt(String borrowerAmt) {
		BorrowerAmt = borrowerAmt;
	}

	public String getBorrowerRate() {
		return BorrowerRate;
	}

	public void setBorrowerRate(String borrowerRate) {
		BorrowerRate = borrowerRate;
	}

	public String getProId() {
		return ProId;
	}

	public void setProId(String proId) {
		ProId = proId;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return FreezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		FreezeOrdId = freezeOrdId;
	}

	public String getAcctId() {
		return AcctId;
	}

	public void setAcctId(String acctId) {
		AcctId = acctId;
	}

	public String getVocherAmt() {
		return VocherAmt;
	}

	public void setVocherAmt(String vocherAmt) {
		VocherAmt = vocherAmt;
	}

	public String getFreezeTrxId() {
		return FreezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
	}

	public String getIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}

	public String getIsUnFreeze() {
		return IsUnFreeze;
	}

	public void setIsUnFreeze(String isUnFreeze) {
		IsUnFreeze = isUnFreeze;
	}

	public String getUnFreezeOrdId() {
		return UnFreezeOrdId;
	}

	public void setUnFreezeOrdId(String unFreezeOrdId) {
		UnFreezeOrdId = unFreezeOrdId;
	}

	public String getLoansVocherAmt() {
		return loansVocherAmt;
	}

	public void setLoansVocherAmt(String loansVocherAmt) {
		this.loansVocherAmt = loansVocherAmt;
	}

	public String getSellCustId() {
		return SellCustId;
	}

	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}

	public String getCreditAmt() {
		return CreditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		CreditAmt = creditAmt;
	}

	public String getCreditDealAmt() {
		return CreditDealAmt;
	}

	public void setCreditDealAmt(String creditDealAmt) {
		CreditDealAmt = creditDealAmt;
	}

	public String getBidDetails() {
		return BidDetails;
	}

	public void setBidDetails(String bidDetails) {
		BidDetails = bidDetails;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
	}

	public String getLcId() {
		return LcId;
	}

	public void setLcId(String lcId) {
		LcId = lcId;
	}

	public String getTotalLcAmt() {
		return TotalLcAmt;
	}

	public void setTotalLcAmt(String totalLcAmt) {
		TotalLcAmt = totalLcAmt;
	}

	public String getBidOrdId() {
		return BidOrdId;
	}

	public void setBidOrdId(String bidOrdId) {
		BidOrdId = bidOrdId;
	}

	public String getBidOrdDate() {
		return BidOrdDate;
	}

	public void setBidOrdDate(String bidOrdDate) {
		BidOrdDate = bidOrdDate;
	}

	public String getBidCreditAmt() {
		return BidCreditAmt;
	}

	public void setBidCreditAmt(String bidCreditAmt) {
		BidCreditAmt = bidCreditAmt;
	}

	public String getBorrowerCreditAmt() {
		return BorrowerCreditAmt;
	}

	public void setBorrowerCreditAmt(String borrowerCreditAmt) {
		BorrowerCreditAmt = borrowerCreditAmt;
	}

	public String getPrinAmt() {
		return PrinAmt;
	}

	public void setPrinAmt(String prinAmt) {
		PrinAmt = prinAmt;
	}

	public String getBrowserStr() {
		return browserStr;
	}

	public void setBrowserStr(String browserStr) {
		this.browserStr = browserStr;
	}

	public String getBidType() {
		return BidType;
	}

	public void setBidType(String bidType) {
		BidType = bidType;
	}

	public String getBorrTotAmt() {
		return BorrTotAmt;
	}

	public void setBorrTotAmt(String borrTotAmt) {
		BorrTotAmt = borrTotAmt;
	}

	public String getYearRate() {
		return YearRate;
	}

	public void setYearRate(String yearRate) {
		YearRate = yearRate;
	}

	public String getRetInterest() {
		return RetInterest;
	}

	public void setRetInterest(String retInterest) {
		RetInterest = retInterest;
	}

	public String getLastRetDate() {
		return LastRetDate;
	}

	public void setLastRetDate(String lastRetDate) {
		LastRetDate = lastRetDate;
	}

	public String getBidStartDate() {
		return BidStartDate;
	}

	public void setBidStartDate(String bidStartDate) {
		BidStartDate = bidStartDate;
	}

	public String getBidEndDate() {
		return BidEndDate;
	}

	public void setBidEndDate(String bidEndDate) {
		BidEndDate = bidEndDate;
	}

	public String getRetType() {
		return RetType;
	}

	public void setRetType(String retType) {
		RetType = retType;
	}

	public String getGuarantType() {
		return GuarantType;
	}

	public void setGuarantType(String guarantType) {
		GuarantType = guarantType;
	}

	public String getBidProdType() {
		return BidProdType;
	}

	public void setBidProdType(String bidProdType) {
		BidProdType = bidProdType;
	}

	public String getRiskCtlType() {
		return RiskCtlType;
	}

	public void setRiskCtlType(String riskCtlType) {
		RiskCtlType = riskCtlType;
	}

	public String getLimitMinBidAmt() {
		return LimitMinBidAmt;
	}

	public void setLimitMinBidAmt(String limitMinBidAmt) {
		LimitMinBidAmt = limitMinBidAmt;
	}

	public String getLimitBidSum() {
		return LimitBidSum;
	}

	public void setLimitBidSum(String limitBidSum) {
		LimitBidSum = limitBidSum;
	}

	public String getLimitMaxBidSum() {
		return LimitMaxBidSum;
	}

	public void setLimitMaxBidSum(String limitMaxBidSum) {
		LimitMaxBidSum = limitMaxBidSum;
	}

	public String getLimitMinBidSum() {
		return LimitMinBidSum;
	}

	public void setLimitMinBidSum(String limitMinBidSum) {
		LimitMinBidSum = limitMinBidSum;
	}

	public String getBidPayforState() {
		return BidPayforState;
	}

	public void setBidPayforState(String bidPayforState) {
		BidPayforState = bidPayforState;
	}

	public String getBorrType() {
		return BorrType;
	}

	public void setBorrType(String borrType) {
		BorrType = borrType;
	}

	public String getBorrCustId() {
		return BorrCustId;
	}

	public void setBorrCustId(String borrCustId) {
		BorrCustId = borrCustId;
	}

	public String getBorrBusiCode() {
		return BorrBusiCode;
	}

	public void setBorrBusiCode(String borrBusiCode) {
		BorrBusiCode = borrBusiCode;
	}

	public String getBorrCertType() {
		return BorrCertType;
	}

	public void setBorrCertType(String borrCertType) {
		BorrCertType = borrCertType;
	}

	public String getBorrCertId() {
		return BorrCertId;
	}

	public void setBorrCertId(String borrCertId) {
		BorrCertId = borrCertId;
	}

	public String getBorrMobiPhone() {
		return BorrMobiPhone;
	}

	public void setBorrMobiPhone(String borrMobiPhone) {
		BorrMobiPhone = borrMobiPhone;
	}

	public String getBorrPhone() {
		return BorrPhone;
	}

	public void setBorrPhone(String borrPhone) {
		BorrPhone = borrPhone;
	}

	public String getBorrWorkYear() {
		return BorrWorkYear;
	}

	public void setBorrWorkYear(String borrWorkYear) {
		BorrWorkYear = borrWorkYear;
	}

	public String getBorrIncome() {
		return BorrIncome;
	}

	public void setBorrIncome(String borrIncome) {
		BorrIncome = borrIncome;
	}

	public String getBorrMarriage() {
		return BorrMarriage;
	}

	public void setBorrMarriage(String borrMarriage) {
		BorrMarriage = borrMarriage;
	}

	public String getBorrEmail() {
		return BorrEmail;
	}

	public void setBorrEmail(String borrEmail) {
		BorrEmail = borrEmail;
	}

	public String getBidName() {
		return BidName;
	}

	public void setBidName(String bidName) {
		BidName = bidName;
	}

	public String getLoanPeriod() {
		return LoanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		LoanPeriod = loanPeriod;
	}

	public String getRetDate() {
		return RetDate;
	}

	public void setRetDate(String retDate) {
		RetDate = retDate;
	}

	public String getRecommer() {
		return Recommer;
	}

	public void setRecommer(String recommer) {
		Recommer = recommer;
	}

	public String getBorrPurpose() {
		return BorrPurpose;
	}

	public void setBorrPurpose(String borrPurpose) {
		BorrPurpose = borrPurpose;
	}

	public String getOverdueProba() {
		return OverdueProba;
	}

	public void setOverdueProba(String overdueProba) {
		OverdueProba = overdueProba;
	}

	public String getBorrName() {
		return BorrName;
	}

	public void setBorrName(String borrName) {
		BorrName = borrName;
	}

	public String getBorrEdu() {
		return BorrEdu;
	}

	public void setBorrEdu(String borrEdu) {
		BorrEdu = borrEdu;
	}

	public String getBorrAddr() {
		return BorrAddr;
	}

	public void setBorrAddr(String borrAddr) {
		BorrAddr = borrAddr;
	}

	public String getBorrWork() {
		return BorrWork;
	}

	public void setBorrWork(String borrWork) {
		BorrWork = borrWork;
	}

	public String getTenderPlanType() {
		return TenderPlanType;
	}

	public void setTenderPlanType(String tenderPlanType) {
		TenderPlanType = tenderPlanType;
	}

	public String getReqExtStr() {
		return ReqExtStr;
	}

	public void setReqExtStr(String reqExtStr) {
		ReqExtStr = reqExtStr;
	}

	public String getDivDetailsStr() {
		return DivDetailsStr;
	}

	public void setDivDetailsStr(String divDetailsStr) {
		DivDetailsStr = divDetailsStr;
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

	public BigDecimal getTenderid() {
		return tenderid;
	}

	public void setTenderid(BigDecimal tenderid) {
		this.tenderid = tenderid;
	}

	public String getBatchId() {
		return BatchId;
	}

	public void setBatchId(String batchId) {
		BatchId = batchId;
	}

	public String getMerOrdDate() {
		return MerOrdDate;
	}

	public void setMerOrdDate(String merOrdDate) {
		MerOrdDate = merOrdDate;
	}

	public String getSucNum() {
		return SucNum;
	}

	public void setSucNum(String sucNum) {
		SucNum = sucNum;
	}

	public String getFailNum() {
		return FailNum;
	}

	public void setFailNum(String failNum) {
		FailNum = failNum;
	}

	public String getErrMsg() {
		return ErrMsg;
	}

	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}

	public String getItemCode() {
		return ItemCode;
	}

	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}

	public String getInDetails() {
		return InDetails;
	}

	public void setInDetails(String inDetails) {
		InDetails = inDetails;
	}

	public String getDzBorrCustId() {
		return DzBorrCustId;
	}

	public void setDzBorrCustId(String dzBorrCustId) {
		DzBorrCustId = dzBorrCustId;
	}

	public String getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getPageNum() {
		return PageNum;
	}

	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}

	public String getPageSize() {
		return PageSize;
	}

	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}

	public String getQueryTransType() {
		return QueryTransType;
	}

	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getInAcctType()
	{
		return InAcctType;
	}

	public void setInAcctType(String inAcctType)
	{
		InAcctType = inAcctType;
	}

	
}
