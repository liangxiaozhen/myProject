package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author liuj
 * 借款资料自填类选项设置
 */
public class LoanInfoNeed {

    private BigDecimal id;//主键

    private String linno;//可编辑资料编号KBJZl0001

    private String infoname;//资料名称(可以自定义编辑)

    private Short infotype;//资料类型(1图片, 2文本)

    private String quoteobject;//引用对象1担保标、2信用标(0000000000....)

    private Short isneed;//是否启用

    private String infodescription;//描述

    private String remark;//备注 管理员

    private String addman;//添加人

    private Date addtime;//添加时间

    private Short charlength;//字符长度限制(1-999)

    private Short iscite;//是否被引用

    private LoanItemQuote itemQuote;//项目实体类映射

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLinno() {
        return linno;
    }

    public void setLinno(String linno) {
        this.linno = linno == null ? null : linno.trim();
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname == null ? null : infoname.trim();
    }

    public Short getInfotype() {
        return infotype;
    }

    public void setInfotype(Short infotype) {
        this.infotype = infotype;
    }

    public String getQuoteobject() {
        return quoteobject;
    }

    public void setQuoteobject(String quoteobject) {
        this.quoteobject = quoteobject == null ? null : quoteobject.trim();
    }

    public Short getIsneed() {
        return isneed;
    }

    public void setIsneed(Short isneed) {
        this.isneed = isneed;
    }

    public String getInfodescription() {
        return infodescription;
    }

    public void setInfodescription(String infodescription) {
        this.infodescription = infodescription == null ? null : infodescription.trim();
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

    public Short getCharlength() {
        return charlength;
    }

    public void setCharlength(Short charlength) {
        this.charlength = charlength;
    }

	public Short getIscite() {
		return iscite;
	}

	public void setIscite(Short iscite) {
		this.iscite = iscite;
	}

	public LoanItemQuote getItemQuote() {
		return itemQuote;
	}

	public void setItemQuote(LoanItemQuote itemQuote) {
		this.itemQuote = itemQuote;
	}
}