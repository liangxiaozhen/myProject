package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sun.istack.internal.NotNull;

/**
 * 自动投标计划实体类
 * @author zhenglm
 *
 */
public class UserAutoTender {
	/** 主键ID */
    private BigDecimal id;
	/** 标的号ID */
    private BigDecimal tenderid;
	/** 用户号 */
    private BigDecimal baseid;
	/** 投标计划类型 */
    private String tenderplantype;
	/** 投标金额 */
    @NotNull
    private Double transamt;
	/** 投标计划状态 */
    private Short status;
	/** 自动投标开始时间 */
    private Date uatbegintime;
	/** 自动投标完成时间 */
    private Date uatendtime;
	/** 是否系统勾兑（0.未勾兑，1.已勾兑）*/
    private Short isblending;
	/** 是否人工勾兑（0.未勾兑，1.已勾兑）*/
    private Short ismanblending;
	/** 系统勾兑时间 */
    private Date sysbtime;
	/** 人工勾兑时间 */
    private Date manbtime;
	/** 自动投标计划开启通道公司（汇付天下） */
    private String paycompany;
	/** 系统勾兑接收数据时间 第一次 */
    private Date sysrectime;
	/** 人工勾兑接收数据时间 第一次 */
    private Date receivetime;
	/** 请求数据包 */
    private String reqquerydata;
	/** 接收数据包 */
    private String recresultdata;
	/** 操作人 */
    private String operatorman;
	/** 备注 */
    private String remark;
    
    /********关联标的设置表********/
    private TenderItem tender;
    /********关联用户基本信息表********/
    private UserBaseAccountInfo userBaseInfo;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getTenderplantype() {
        return tenderplantype;
    }

    public void setTenderplantype(String tenderplantype) {
        this.tenderplantype = tenderplantype == null ? null : tenderplantype.trim();
    }

    public Double getTransamt() {
        return transamt;
    }

    public void setTransamt(Double transamt) {
        this.transamt = transamt;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getUatbegintime() {
        return uatbegintime;
    }

    public void setUatbegintime(Date uatbegintime) {
        this.uatbegintime = uatbegintime;
    }

    public Date getUatendtime() {
        return uatendtime;
    }

    public void setUatendtime(Date uatendtime) {
        this.uatendtime = uatendtime;
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

    public String getOperatorman() {
        return operatorman;
    }

    public void setOperatorman(String operatorman) {
        this.operatorman = operatorman == null ? null : operatorman.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public TenderItem getTender() {
		return tender;
	}

	public void setTender(TenderItem tender) {
		this.tender = tender;
	}

	public UserBaseAccountInfo getUserBaseInfo() {
		return userBaseInfo;
	}

	public void setUserBaseInfo(UserBaseAccountInfo userBaseInfo) {
		this.userBaseInfo = userBaseInfo;
	}
}