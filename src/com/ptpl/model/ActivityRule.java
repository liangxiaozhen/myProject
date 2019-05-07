package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: ActivityRule
 * @Package com.ptpl.model
 * @Description: TODO(标的活动规则 实体类)
 * @author chenjiaming
 * @date 2016年8月25日 上午11:35:32
 * @version V1.0
 */
public class ActivityRule {
	/*主键ID*/
	private BigDecimal id;
	/*活动编号*/
	private String actno;
	/*活动类型(1累投，2单标)*/
	private Short acttype;
	/*活动名称*/
	private String actname;
	/*设置日期*/
	private Date settime;
	/*活动生效日期*/
	private Date actbtime;
	/*活动截止日期*/
	private Date actetime;
	/*活动状态  1 已启动 2 未启动*/
	private Short status;
	/*累投计算方式（1标内，2全局） */
	private Short tctype;
	/*客户端限制(10位0000000000占位符标识 1pc,2ios,3安卓) */
	private String crestrict;
	/*会员等级(100000000000000000000000000000)*/
	private String ugrade;
	/*removeNameNo Varchar2(4000) 排除名单表编号*/
	private String removenameno;
	/*定向标号*/     
	private String specifytno;
	/*排除的标号*/
	private String canceltno;
	/*投标属性限制（10位0000000000占位符标识  10000000000新手标0100000000担保标001000000000信用标00010000000抵押标*/
	private String tattribute;
	/*投标期限限制(天数)*/
	private Integer tdayrestrict;
	/*投标收益限制最低值*/
	private Double tmlrrestrict;
	/*投标收益限制最高值*/
	private Double tmhrrestrict;
	/*获奖名单生成方式（1系统，2手动 */
	private Short gtype;
	/*获奖名单是否需要审核 1 需要审核 2 不需要*/
	private Short isauditalist;
	/*是否审核(该表) 1 需要审核 2 不需要*/
	private Short isaudit;
	/*审核人*/
	private String auditman;
	/*审核时间 */
	private Date audittime;
	/*制表人*/
	private String addman;
	/*备注*/
	private String remark;
	/*接收前台传递的多个会员等级*/
	private String[] ugradesx;
	/*接收前台传递的多个排除名单*/
	private String[] removenamenos;
	/*接收前台传递的投标属性*/
    private String[] tattributes;
    /*接收前台传递的客户端限制*/
    private String[] crestricts;
    /*接收前台传递的排除标号*/
    private String[] canceltnos;
    /*接收前台传递的定向标号*/
    private String[] specifytnos;
     
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getActno() {
		return actno;
	}

	public void setActno(String actno) {
		this.actno = actno == null ? null : actno.trim();
	}

	public Short getActtype() {
		return acttype;
	}

	public void setActtype(Short acttype) {
		this.acttype = acttype;
	}

	public String getActname() {
		return actname;
	}

	public void setActname(String actname) {
		this.actname = actname == null ? null : actname.trim();
	}

	public Date getSettime() {
		return settime;
	}

	public void setSettime(Date settime) {
		this.settime = settime;
	}

	public Date getActbtime() {
		return actbtime;
	}

	public void setActbtime(Date actbtime) {
		this.actbtime = actbtime;
	}

	public Date getActetime() {
		return actetime;
	}

	public void setActetime(Date actetime) {
		this.actetime = actetime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getTctype() {
		return tctype;
	}

	public void setTctype(Short tctype) {
		this.tctype = tctype;
	}

	public String getCrestrict() {
		return crestrict;
	}

	public void setCrestrict(String crestrict) {
		this.crestrict = crestrict == null ? null : crestrict.trim();
	}

	public String getUgrade() {
		return ugrade;
	}

	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}

	public String getSpecifytno() {
		return specifytno;
	}

	public void setSpecifytno(String specifytno) {
		this.specifytno = specifytno == null ? null : specifytno.trim();
	}

	public String getCanceltno() {
		return canceltno;
	}

	public void setCanceltno(String canceltno) {
		this.canceltno = canceltno == null ? null : canceltno.trim();
	}

	public String getTattribute() {
		return tattribute;
	}

	public void setTattribute(String tattribute) {
		this.tattribute = tattribute == null ? null : tattribute.trim();
	}

	public Integer getTdayrestrict() {
		return tdayrestrict;
	}

	public void setTdayrestrict(Integer tdayrestrict) {
		this.tdayrestrict = tdayrestrict;
	}

	public Double getTmlrrestrict() {
		return tmlrrestrict;
	}

	public void setTmlrrestrict(Double tmlrrestrict) {
		this.tmlrrestrict = tmlrrestrict;
	}

	public Double getTmhrrestrict() {
		return tmhrrestrict;
	}

	public void setTmhrrestrict(Double tmhrrestrict) {
		this.tmhrrestrict = tmhrrestrict;
	}

	public Short getGtype() {
		return gtype;
	}

	public void setGtype(Short gtype) {
		this.gtype = gtype;
	}

	public Short getIsauditalist() {
		return isauditalist;
	}

	public void setIsauditalist(Short isauditalist) {
		this.isauditalist = isauditalist;
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

	public String getRemovenameno() {
		return removenameno;
	}

	public void setRemovenameno(String removenameno) {
		this.removenameno = removenameno == null ? null : removenameno.trim();
	}

	public String[] getUgradesx() {
		return ugradesx;
	}

	public void setUgradesx(String[] ugradesx) {
		this.ugradesx = ugradesx;
	}

	public String[] getRemovenamenos() {
		return removenamenos;
	}

	public void setRemovenamenos(String[] removenamenos) {
		this.removenamenos = removenamenos;
	}

	public String[] getTattributes() {
		return tattributes;
	}

	public void setTattributes(String[] tattributes) {
		this.tattributes = tattributes;
	}

	public String getAddman() {
		return addman;
	}

	public void setAddman(String addman) {
		this.addman = addman;
	}

	public String[] getCrestricts() {
		return crestricts;
	}

	public void setCrestricts(String[] crestricts) {
		this.crestricts = crestricts;
	}

	public String[] getCanceltnos() {
		return canceltnos;
	}

	public void setCanceltnos(String[] canceltnos) {
		this.canceltnos = canceltnos;
	}

	public String[] getSpecifytnos() {
		return specifytnos;
	}

	public void setSpecifytnos(String[] specifytnos) {
		this.specifytnos = specifytnos;
	}

	 

}