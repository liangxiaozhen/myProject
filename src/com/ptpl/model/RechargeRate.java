package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RechargeRate {
	private BigDecimal id;
	/**是否启用*/
	private Short isuse;
	/**是否启用字符串*/
	private String isuseStr;
	/**是否审核*/
	private Short isaudit;
	/**是否审核字符串*/
	private String isauditStr;
	/**支付公司*/
	private String paycompany;
	/**会员等级*/
	private String ugrade;
	/**是否为模板*/
	private Short istemplet;
	/**是否为模板字符串*/
	private String istempletStr;
	/**会员等级字符串*/
	private String ugradeStr;
	/**会员等级数组*/
	private String[] ugrades;
	/**充值方式*/
	private Short rechartype;
	/**充值方式字符串*/
	private String rechartypeStr;
	/**分段最低金额*/
	private Double minmoney;
	/**分段最高金额*/
	private Double maxmoney;
	/**收费类型:定额,百分比*/
	private Short chargetype;
	/**收费类型字符串*/
	private String chargetypeStr;
	/**充值费率*/
	private Double charrate;
	/**收费定额*/
	private Double quota;
	/**银行编码*/
	private String bankcode;
	/**银行编码字符串*/
	private String bankcodeStr;
	/**最低收费金额*/
	private Double minfee;
	/**最高收费金额*/
	private Double maxfee;
	/**设置人*/
	private String addman;
	/**设置时间*/
	private Date addtime;
	/**设置时间字符串*/
	private String addtimeStr;
	/**审核人*/
	private String auditman;
	/**审核时间*/
	private Date audittime;
	/**审核时间字符串*/
	private String audittimeStr;
	/**备注*/
	private String remark;
	//充值人自付比例
	private Double selfpayratio;
	//充值人他付比例
	private Double proxypayratio;
	//3.28充值定额手续费设置
	private RechargeQuotaFee[] rechargeQuotaFee;
	//3.29充值排除名单关联表
	private RechargeSNLLink[] rechargeSNLLink;
	/*//接收前台返回数据
	private List<RechargeRate> rechargeRates;*/
	
	//3.28充值定额手续费设置
	private  List<RechargeQuotaFee> rechargeQuotaFeeList;
	//3.29充值排除名单关联表
	private  List<RechargeSNLLink> rechargeSNLLinkList;

	public BigDecimal getId() {
		return id;
	}

	public Double getSelfpayratio() {
		return selfpayratio;
	}

	public void setSelfpayratio(Double selfpayratio) {
		this.selfpayratio = selfpayratio;
	}

	public Double getProxypayratio() {
		return proxypayratio;
	}

	public void setProxypayratio(Double proxypayratio) {
		this.proxypayratio = proxypayratio;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUgrade() {
		return ugrade;
	}

	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}

	public String getUgradeStr() {
		return ugradeStr;
	}

	public void setUgradeStr(String ugradeStr) {
		this.ugradeStr = ugradeStr;
	}

	public String[] getUgrades() {
		return ugrades;
	}

	public void setUgrades(String[] ugrades) {
		this.ugrades = ugrades;
	}

	public Short getRechartype() {
		return rechartype;
	}

	public void setRechartype(Short rechartype) {
		this.rechartype = rechartype;
	}

	public Double getMinmoney() {
		return minmoney;
	}

	public void setMinmoney(Double minmoney) {
		this.minmoney = minmoney;
	}

	public Double getMaxmoney() {
		return maxmoney;
	}

	public void setMaxmoney(Double maxmoney) {
		this.maxmoney = maxmoney;
	}

	public Short getChargetype() {
		return chargetype;
	}

	public void setChargetype(Short chargetype) {
		this.chargetype = chargetype;
	}

	public Double getCharrate() {
		return charrate;
	}

	public void setCharrate(Double charrate) {
		this.charrate = charrate;
	}

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode == null ? null : bankcode.trim();
	}

	public Double getMinfee() {
		return minfee;
	}

	public void setMinfee(Double minfee) {
		this.minfee = minfee;
	}

	public Double getMaxfee() {
		return maxfee;
	}

	public void setMaxfee(Double maxfee) {
		this.maxfee = maxfee;
	}

	public String getAddman() {
		return addman;
	}

	public void setAddman(String addman) {
		this.addman = addman == null ? null : addman.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
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

	public String getAddtimeStr() {
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public String getRechartypeStr() {
		return rechartypeStr;
	}

	public void setRechartypeStr(String rechartypeStr) {
		this.rechartypeStr = rechartypeStr;
	}

	public String getChargetypeStr() {
		return chargetypeStr;
	}

	public void setChargetypeStr(String chargetypeStr) {
		this.chargetypeStr = chargetypeStr;
	}

	public Short getIsuse() {
		return isuse;
	}

	public void setIsuse(Short isuse) {
		this.isuse = isuse;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public String getPaycompany() {
		return paycompany;
	}

	public void setPaycompany(String paycompany) {
		this.paycompany = paycompany;
	}

	public String getIsuseStr() {
		return isuseStr;
	}

	public void setIsuseStr(String isuseStr) {
		this.isuseStr = isuseStr;
	}

	public String getIsauditStr() {
		return isauditStr;
	}

	public void setIsauditStr(String isauditStr) {
		this.isauditStr = isauditStr;
	}

	public String getBankcodeStr() {
		return bankcodeStr;
	}

	public void setBankcodeStr(String bankcodeStr) {
		this.bankcodeStr = bankcodeStr;
	}

	public Short getIstemplet() {
		return istemplet;
	}

	public void setIstemplet(Short istemplet) {
		this.istemplet = istemplet;
	}

	public String getIstempletStr() {
		return istempletStr;
	}

	public void setIstempletStr(String istempletStr) {
		this.istempletStr = istempletStr;
	}


	
	public RechargeQuotaFee[] getRechargeQuotaFee() {
		return rechargeQuotaFee;
	}

	public void setRechargeQuotaFee(RechargeQuotaFee[] rechargeQuotaFee) {
		this.rechargeQuotaFee = rechargeQuotaFee;
	}

	public RechargeSNLLink[] getRechargeSNLLink() {
		return rechargeSNLLink;
	}

	public void setRechargeSNLLink(RechargeSNLLink[] rechargeSNLLink) {
		this.rechargeSNLLink = rechargeSNLLink;
	}

	public List<RechargeQuotaFee> getRechargeQuotaFeeList() {
		return rechargeQuotaFeeList;
	}

	public void setRechargeQuotaFeeList(List<RechargeQuotaFee> rechargeQuotaFeeList) {
		this.rechargeQuotaFeeList = rechargeQuotaFeeList;
	}

	public List<RechargeSNLLink> getRechargeSNLLinkList() {
		return rechargeSNLLinkList;
	}

	public void setRechargeSNLLinkList(List<RechargeSNLLink> rechargeSNLLinkList) {
		this.rechargeSNLLinkList = rechargeSNLLinkList;
	}

	@Override
	public String toString() {
		return "RechargeRate [id=" + id + ", isuse=" + isuse + ", isuseStr=" + isuseStr + ", isaudit=" + isaudit
				+ ", isauditStr=" + isauditStr + ", paycompany=" + paycompany + ", ugrade=" + ugrade + ", istemplet="
				+ istemplet + ", istempletStr=" + istempletStr + ", ugradeStr=" + ugradeStr + ", ugrades="
				+ Arrays.toString(ugrades) + ", rechartype=" + rechartype + ", rechartypeStr=" + rechartypeStr
				+ ", minmoney=" + minmoney + ", maxmoney=" + maxmoney + ", chargetype=" + chargetype
				+ ", chargetypeStr=" + chargetypeStr + ", charrate=" + charrate + ", quota=" + quota + ", bankcode="
				+ bankcode + ", bankcodeStr=" + bankcodeStr + ", minfee=" + minfee + ", maxfee=" + maxfee + ", addman="
				+ addman + ", addtime=" + addtime + ", addtimeStr=" + addtimeStr + ", auditman=" + auditman
				+ ", audittime=" + audittime + ", audittimeStr=" + audittimeStr + ", remark=" + remark
				+ ", selfpayratio=" + selfpayratio + ", proxypayratio=" + proxypayratio + ", rechargeQuotaFee="
				+ Arrays.toString(rechargeQuotaFee) + ", rechargeSNLLink=" + Arrays.toString(rechargeSNLLink)
				+ ", rechargeQuotaFeeList=" + rechargeQuotaFeeList + ", rechargeSNLLinkList=" + rechargeSNLLinkList
				+ "]";
	}

}