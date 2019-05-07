package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author liuj
 */
public class LoanInfoPreset {
    private BigDecimal id;//主键

    private String linno;//预设资料编号(YSZl0001)

    private String infoname;//资料名称(可自定义编辑)

    private Short infotype;//资料类型

    private String multino;//多选内容名称的编号(MultiContentSet 表的MultiNo)

    private Short oneormulti;//单选或多选

    private String quoteobject;//引用对象(1担保标、2信用标.)

    private Short isneed;//是否启用

    private String infodescription;//描述

    private String remark;//备注 管理员

    private String addman;//添加人

    private Date addtime;//操作时间

    private Short iscite;//是否被引用

    private LoanItemQuote itemQuote;//引用项目映射

    private List<MultiContentSet> multiContentSets;//选择类多选内容设置表

    public List<MultiContentSet> getMultiContentSets() {
        return multiContentSets;
    }

    public void setMultiContentSets(List<MultiContentSet> multiContentSets) {
        this.multiContentSets = multiContentSets;
    }

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

    public String getMultino() {
        return multino;
    }

    public void setMultino(String multino) {
        this.multino = multino == null ? null : multino.trim();
    }

    public Short getOneormulti() {
        return oneormulti;
    }

    public void setOneormulti(Short oneormulti) {
        this.oneormulti = oneormulti;
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