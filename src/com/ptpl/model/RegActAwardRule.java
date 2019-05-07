package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 注册活动奖励规则实体类
 * @author zhenglm
 */
public class RegActAwardRule {
	/** 主键ID */
    private BigDecimal id;
    /** 注册活动ID */
    private BigDecimal actid;
    /** 客户端限制 */
    private String crestrict;
    /** 指定业务（1.注册完成，2.安保密码设置，3.姓名匹配，4.手机号验证，5.邮箱验证，6.开通托管账户，7.银行卡绑定） */
    private Short business;
    /** 注册后限定几个小时内完成业务 */
    private Integer finishtime;
    /** 奖品的ID */
    private BigDecimal awardid;
    /** 奖品的名称 */
    private String awardname;
    /** 奖品份数 */
    private Integer awardcopies;
    /** 奖励金额 */
    private Double quota;
    /** 活动状态（0.待完善,1.待执行,2.执行中,3.已完成,4.已停用,5.已作废,6.已过期） */
    private Short status;
    /** 制表人 */
    private String addman;
    /** 制表时间 */
    private Date addtime;
    /** 备注 */
    private String remark;
    /** 是否为模板（0否，1是） */
    private Short istemplet;
    
    /**********关联表**********/
    /** 注册活动规则表 */
    private RegActivityRule regactivityrule;
    
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getActid() {
		return actid;
	}
	public void setActid(BigDecimal actid) {
		this.actid = actid;
	}
	public String getCrestrict() {
		return crestrict;
	}
	public void setCrestrict(String crestrict) {
		this.crestrict = crestrict;
	}
	public Short getBusiness() {
		return business;
	}
	public void setBusiness(Short business) {
		this.business = business;
	}
	public Integer getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(Integer finishtime) {
		this.finishtime = finishtime;
	}
	public BigDecimal getAwardid() {
		return awardid;
	}
	public void setAwardid(BigDecimal awardid) {
		this.awardid = awardid;
	}
	public String getAwardname() {
		return awardname;
	}
	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}
	public Integer getAwardcopies() {
		return awardcopies;
	}
	public void setAwardcopies(Integer awardcopies) {
		this.awardcopies = awardcopies;
	}
	public Double getQuota() {
		return quota;
	}
	public void setQuota(Double quota) {
		this.quota = quota;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getAddman() {
		return addman;
	}
	public void setAddman(String addman) {
		this.addman = addman;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Short getIstemplet() {
		return istemplet;
	}
	public void setIstemplet(Short istemplet) {
		this.istemplet = istemplet;
	}
	public RegActivityRule getRegactivityrule() {
		return regactivityrule;
	}
	public void setRegactivityrule(RegActivityRule regactivityrule) {
		this.regactivityrule = regactivityrule;
	}
	@Override
	public String toString() {
		return "RegActAwardRule [id=" + id + ", actid=" + actid + ", crestrict=" + crestrict + ", business=" + business
				+ ", finishtime=" + finishtime + ", awardid=" + awardid + ", awardname=" + awardname + ", awardcopies="
				+ awardcopies + ", quota=" + quota + ", status=" + status + ", addman=" + addman + ", addtime="
				+ addtime + ", remark=" + remark + ", istemplet=" + istemplet + "]";
	}
}