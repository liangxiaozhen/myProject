package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author：liuqh
 * @Description:标的增益设置
 */ 
public class Plus {
    private BigDecimal id;

    private String plusno;//增益设置编号

    private Short isaint;//是否允许加息卷

    private Integer aonceint;//单次允许使用加息张数

    private Integer atotalint;//允许使用加息总张数

    private Double aoneqrofit;//允许单张加息收益

    private Short isavoucher;//是否允许类现金卷

    private Integer aoncevoucher;//单次允许类现金卷张数

    private Integer atotalvoucher;//总计允许类现金卷张数

    private Double aonevamount;//允许单张类现金额度

    private Short isalikevoucher;//是否允许假现金卷

    private Integer aoncelikevoucher;//单次允许假现金卷张数

    private Integer atotallikevoucher;//总计允许假现金卷张数

    private Double aonelvamount;//允许单张假现金额度

    private Integer aonceplusmode;//单次允许的增益方式

    private Integer atotalplusmode;//总计允许的增益方式

    private String payforplusman;//增益清算付款人（付款人不是还款人时，确保付款人已作自动授权，未做授权不可以设置，还款时需再次判断付款人是否开通自动授权）

    private Short clearmode;//清算方式（结标，首期，每期，尾期）

    private Short istemplet;//是否为模板（0否，1是）
    
    private BigDecimal tid;//标号id
    
    private Short isaudit;//资金清算是否需要审核
    
    private String addman;//添加人
    
    private Date addtime;//设置时间
    
    private String remark;//备注

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPlusno() {
        return plusno;
    }

    public void setPlusno(String plusno) {
        this.plusno = plusno == null ? null : plusno.trim();
    }

    public Short getIsaint() {
        return isaint;
    }

    public void setIsaint(Short isaint) {
        this.isaint = isaint;
    }

    public Integer getAonceint() {
        return aonceint;
    }

    public void setAonceint(Integer aonceint) {
        this.aonceint = aonceint;
    }

    public Integer getAtotalint() {
        return atotalint;
    }

    public void setAtotalint(Integer atotalint) {
        this.atotalint = atotalint;
    }

    public Double getAoneqrofit() {
        return aoneqrofit;
    }

    public void setAoneqrofit(Double aoneqrofit) {
        this.aoneqrofit = aoneqrofit;
    }

    public Short getIsavoucher() {
        return isavoucher;
    }

    public void setIsavoucher(Short isavoucher) {
        this.isavoucher = isavoucher;
    }

    public Integer getAoncevoucher() {
        return aoncevoucher;
    }

    public void setAoncevoucher(Integer aoncevoucher) {
        this.aoncevoucher = aoncevoucher;
    }

    public Integer getAtotalvoucher() {
        return atotalvoucher;
    }

    public void setAtotalvoucher(Integer atotalvoucher) {
        this.atotalvoucher = atotalvoucher;
    }

    public Double getAonevamount() {
        return aonevamount;
    }

    public void setAonevamount(Double aonevamount) {
        this.aonevamount = aonevamount;
    }

    public Short getIsalikevoucher() {
        return isalikevoucher;
    }

    public void setIsalikevoucher(Short isalikevoucher) {
        this.isalikevoucher = isalikevoucher;
    }

    public Integer getAoncelikevoucher() {
        return aoncelikevoucher;
    }

    public void setAoncelikevoucher(Integer aoncelikevoucher) {
        this.aoncelikevoucher = aoncelikevoucher;
    }

    public Integer getAtotallikevoucher() {
        return atotallikevoucher;
    }

    public void setAtotallikevoucher(Integer atotallikevoucher) {
        this.atotallikevoucher = atotallikevoucher;
    }

    public Double getAonelvamount() {
        return aonelvamount;
    }

    public void setAonelvamount(Double aonelvamount) {
        this.aonelvamount = aonelvamount;
    }

    public Integer getAonceplusmode() {
        return aonceplusmode;
    }

    public void setAonceplusmode(Integer aonceplusmode) {
        this.aonceplusmode = aonceplusmode;
    }

    public Integer getAtotalplusmode() {
        return atotalplusmode;
    }

    public void setAtotalplusmode(Integer atotalplusmode) {
        this.atotalplusmode = atotalplusmode;
    }

    public String getPayforplusman() {
        return payforplusman;
    }

    public void setPayforplusman(String payforplusman) {
        this.payforplusman = payforplusman == null ? null : payforplusman.trim();
    }

    public Short getClearmode() {
        return clearmode;
    }

    public void setClearmode(Short clearmode) {
        this.clearmode = clearmode;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

	@Override
	public String toString() {
		return "Plus [id=" + id + ", plusno=" + plusno + ", isaint=" + isaint + ", aonceint=" + aonceint
				+ ", atotalint=" + atotalint + ", aoneqrofit=" + aoneqrofit + ", isavoucher=" + isavoucher
				+ ", aoncevoucher=" + aoncevoucher + ", atotalvoucher=" + atotalvoucher + ", aonevamount=" + aonevamount
				+ ", isalikevoucher=" + isalikevoucher + ", aoncelikevoucher=" + aoncelikevoucher
				+ ", atotallikevoucher=" + atotallikevoucher + ", aonelvamount=" + aonelvamount + ", aonceplusmode="
				+ aonceplusmode + ", atotalplusmode=" + atotalplusmode + ", payforplusman=" + payforplusman
				+ ", clearmode=" + clearmode + ", istemplet=" + istemplet + ",tid="+tid+",isaudit="+isaudit+"]";
	}

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
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
    
}