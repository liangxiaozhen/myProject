package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author liuj
 * 借款资料项目引用设置
 */
public class LoanItemQuote {
    private BigDecimal id;//主键

    private String liqno;//资料编号(YYZl0001)

    private Short infoattribute;//资料属性(1公共 ，2补充)

    private String infotype;//借款类型(1担保标、2信用标、)   该字段已删了

    private BigDecimal quoteobjectid;//引用项目Id(可以是选择资料表 和 自填资料表 的Id)

    private Short isneed;//是否必填

    private Short isuse;//是否启用

    private String remark;//备注

    private String addman;//添加人

    private Date addtime;//操作时间

    private Short quoteproperty;//引用项目属性（1自填，2预设置）

    private String quotename;//引用项目名称

    private Short iscite;//资料是否被引用1.是,0否

    private String strquote;//n.自填，p.选填

    private Short seriesno;//用于排序的序号

    private LoanInfoNeed loanInfoNeed;//自填类的引用类型

    private LoanInfoPreset loanInfoPreset;//选择类的引用类型


    public LoanInfoNeed getLoanInfoNeed() {
        return loanInfoNeed;
    }

    public void setLoanInfoNeed(LoanInfoNeed loanInfoNeed) {
        this.loanInfoNeed = loanInfoNeed;
    }

    public LoanInfoPreset getLoanInfoPreset() {
        return loanInfoPreset;
    }

    public void setLoanInfoPreset(LoanInfoPreset loanInfoPreset) {
        this.loanInfoPreset = loanInfoPreset;
    }

    public Short getSeriesno() {
        return seriesno;
    }

    public void setSeriesno(Short seriesno) {
        this.seriesno = seriesno;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLiqno() {
        return liqno;
    }

    public void setLiqno(String liqno) {
        this.liqno = liqno == null ? null : liqno.trim();
    }

    public Short getInfoattribute() {
        return infoattribute;
    }

    public void setInfoattribute(Short infoattribute) {
        this.infoattribute = infoattribute;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype == null ? null : infotype.trim();
    }

    public BigDecimal getQuoteobjectid() {
        return quoteobjectid;
    }

    public void setQuoteobjectid(BigDecimal quoteobjectid) {
        this.quoteobjectid = quoteobjectid;
    }

    public Short getIsneed() {
        return isneed;
    }

    public void setIsneed(Short isneed) {
        this.isneed = isneed;
    }

    public Short getIsuse() {
        return isuse;
    }

    public void setIsuse(Short isuse) {
        this.isuse = isuse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Short getQuoteproperty() {
        return quoteproperty;
    }

    public void setQuoteproperty(Short quoteproperty) {
        this.quoteproperty = quoteproperty;
    }

    public String getQuotename() {
        return quotename;
    }

    public void setQuotename(String quotename) {
        this.quotename = quotename == null ? null : quotename.trim();
    }

	public Short getIscite() {
		return iscite;
	}

	public void setIscite(Short iscite) {
		this.iscite = iscite;
	}

	public String getStrquote() {
		return strquote;
	}

	public void setStrquote(String strquote) {
		this.strquote = strquote;
	}
}