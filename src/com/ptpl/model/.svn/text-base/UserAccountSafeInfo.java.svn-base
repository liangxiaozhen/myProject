package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: UserAccountSafeInfo
 * @Package com.ptpl.model
 * @Description: TODO(用户账户信息安全 实体类)
 * @author chenjiaming
 * @date 2016年7月14日 下午2:37:39
 * @version V1.0
 */
public class UserAccountSafeInfo {
	/* 主键ID */
	private BigDecimal id;
	/* 用户ID */
	private BigDecimal baseid;
	/* 登录密码(加密 sha) */
	private String loginpassword;
	/* 随机因子 */
	private String saltnumber;
	/* 安全密码(第1-3段) */
	private String securitypasswordone;
	/* 安全密码(第2-4段) */
	private String securitypasswordtwo;
	/* 密码保护问题1 */
	private String question1;
	/* 密码保护答案1 */
	private String answer1;
	/* 密码保护问题2 */
	private String question2;
	/* 密码保护答案2 */
	private String answer2;
	/* 密码保护问题3 */
	private String question3;
	/* 密码保护答案3 */
	private String answer3;
	/* 数字证书类型  */
	private Short digitalCAType;
	/* 数字证书编号  */
	private String digitalCANo;
	/* 申请证书时间  */
	private Date digitalCATime;
	/* 数字证书状态  */
	private Short digitalCAStatus;
	/* U盾编号 */
	private String UShieldNo;
	/* U盾状态*/
	private Short UShieldStatus;
	/* 风险等级（1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结） */
	private Short risklevel;
	/* 账户状态(1正常\0停用) */
	private Short status;
	/* 备注 */
	private String remark;
	/* 客户自定义安保问题 1自定义 2系统问题*/
	private String custquestion;
	/* 托管是否开通 （作废 以第三方托管账号表为主） */
	private Short isfundsupervision;
	/* 登陆限制（u盾、证书） */
	private Short loginresttype;
	/* 功能开关（充值，投资，还款，提现）0000000000 */
	private String functionswitch;
	/* 第三方绑定（qq，微信，新浪微博）0000000000根据占位判断  1微博 2QQ*/
	private String thirdpblogin;
	/* 最后一次登录时间 */
	private Date lastlogintime;
	/* 最后一次登录IP */
	private String lastloginip;
	/* 最后一次登录cookie */
	private String lastlogincookie;
	/*用户等级（1钻石，2铂金，3白银，4铜）*/
	private Short ugrade;
	/*会员类型 1.正常会员 2.体验会员*/
	private Short ugradetype;
	
	private String lastlogintimestr;
	/*前端显示会员名称*/
	private String ugradenamestr;
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

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword == null ? null : loginpassword.trim();
	}

	public String getSaltnumber() {
		return saltnumber;
	}

	public void setSaltnumber(String saltnumber) {
		this.saltnumber = saltnumber == null ? null : saltnumber.trim();
	}

	public String getSecuritypasswordone() {
		return securitypasswordone;
	}

	public void setSecuritypasswordone(String securitypasswordone) {
		this.securitypasswordone = securitypasswordone == null ? null : securitypasswordone.trim();
	}

	public String getSecuritypasswordtwo() {
		return securitypasswordtwo;
	}

	public void setSecuritypasswordtwo(String securitypasswordtwo) {
		this.securitypasswordtwo = securitypasswordtwo == null ? null : securitypasswordtwo.trim();
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1 == null ? null : question1.trim();
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1 == null ? null : answer1.trim();
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2 == null ? null : question2.trim();
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2 == null ? null : answer2.trim();
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3 == null ? null : question3.trim();
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3 == null ? null : answer3.trim();
	}

	public Short getRisklevel() {
		return risklevel;
	}

	public void setRisklevel(Short risklevel) {
		this.risklevel = risklevel;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getCustquestion() {
		return custquestion;
	}

	public void setCustquestion(String custquestion) {
		this.custquestion = custquestion == null ? null : custquestion.trim();
	}

	public Short getIsfundsupervision() {
		return isfundsupervision;
	}

	public void setIsfundsupervision(Short isfundsupervision) {
		this.isfundsupervision = isfundsupervision;
	}

	public Short getLoginresttype() {
		return loginresttype;
	}

	public void setLoginresttype(Short loginresttype) {
		this.loginresttype = loginresttype;
	}

	public String getFunctionswitch() {
		return functionswitch;
	}

	public void setFunctionswitch(String functionswitch) {
		this.functionswitch = functionswitch == null ? null : functionswitch.trim();
	}

	public String getThirdpblogin() {
		return thirdpblogin;
	}

	public void setThirdpblogin(String thirdpblogin) {
		this.thirdpblogin = thirdpblogin == null ? null : thirdpblogin.trim();
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip == null ? null : lastloginip.trim();
	}

	public String getLastlogincookie() {
		return lastlogincookie;
	}

	public void setLastlogincookie(String lastlogincookie) {
		this.lastlogincookie = lastlogincookie == null ? null : lastlogincookie.trim();
	}

	public Short getDigitalCAType() {
		return digitalCAType;
	}

	public void setDigitalCAType(Short digitalCAType) {
		this.digitalCAType = digitalCAType;
	}

	public String getDigitalCANo() {
		return digitalCANo;
	}

	public void setDigitalCANo(String digitalCANo) {
		this.digitalCANo = digitalCANo;
	}

	public Date getDigitalCATime() {
		return digitalCATime;
	}

	public void setDigitalCATime(Date digitalCATime) {
		this.digitalCATime = digitalCATime;
	}

	public Short getDigitalCAStatus() {
		return digitalCAStatus;
	}

	public void setDigitalCAStatus(Short digitalCAStatus) {
		this.digitalCAStatus = digitalCAStatus;
	}

	public String getUShieldNo() {
		return UShieldNo;
	}

	public void setUShieldNo(String uShieldNo) {
		UShieldNo = uShieldNo;
	}

	public Short getUShieldStatus() {
		return UShieldStatus;
	}

	public void setUShieldStatus(Short uShieldStatus) {
		UShieldStatus = uShieldStatus;
	}

	public Short getUgrade() {
		return ugrade;
	}

	public void setUgrade(Short ugrade) {
		this.ugrade = ugrade;
	}

	public Short getUgradetype()
	{
		return ugradetype;
	}

	public void setUgradetype(Short ugradetype)
	{
		this.ugradetype = ugradetype;
	}

	public String getLastlogintimestr() {
		return lastlogintimestr;
	}

	public void setLastlogintimestr(String lastlogintimestr) {
		this.lastlogintimestr = lastlogintimestr;
	}

	@Override
	public String toString() {
		return "UserAccountSafeInfo [id=" + id + ", baseid=" + baseid + ", loginpassword=" + loginpassword
				+ ", saltnumber=" + saltnumber + ", securitypasswordone=" + securitypasswordone
				+ ", securitypasswordtwo=" + securitypasswordtwo + ", question1=" + question1 + ", answer1=" + answer1
				+ ", question2=" + question2 + ", answer2=" + answer2 + ", question3=" + question3 + ", answer3="
				+ answer3 + ", digitalCAType=" + digitalCAType + ", digitalCANo=" + digitalCANo + ", digitalCATime="
				+ digitalCATime + ", digitalCAStatus=" + digitalCAStatus + ", UShieldNo=" + UShieldNo
				+ ", UShieldStatus=" + UShieldStatus + ", risklevel=" + risklevel + ", status=" + status + ", remark="
				+ remark + ", custquestion=" + custquestion + ", isfundsupervision=" + isfundsupervision
				+ ", loginresttype=" + loginresttype + ", functionswitch=" + functionswitch + ", thirdpblogin="
				+ thirdpblogin + ", lastlogintime=" + lastlogintime + ", lastloginip=" + lastloginip
				+ ", lastlogincookie=" + lastlogincookie + ", ugrade=" + ugrade + ", ugradetype=" + ugradetype
				+ ", lastlogintimestr=" + lastlogintimestr + "]";
	}

	public String getUgradenamestr() {
		return ugradenamestr;
	}

	public void setUgradenamestr(String ugradenamestr) {
		this.ugradenamestr = ugradenamestr;
	}
	
}