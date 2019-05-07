package com.ptpl.model;

import java.math.BigDecimal;

/**
 * 标的前端信息
 */
public class TenderFrontEnd {
    private BigDecimal id;

    private BigDecimal ttypeid;//标的类型Id

    private String ttypename;//标的类型名称

    private String infoname;//项目名称(资料)

    private Integer source;//来源（1新增，2引用）

    private Short infoattribute;//内容属性（1图片，2文字，3下拉，4标签）

    private Short isfixed;//应用范围（1通用，2唯一）

    private String content;//内容

    private Short status;//状态（1启用，0停用）

    private String infono;//编号（唯一标识）

    private String pictitle;//图片标题

    private Integer infotype;//资料类别ID


    private String content_text;//用于接收jsp页面传文本类型时的数据，DB中是没有这个字段的

    private Short issynchisbid;//是否同步存量标:1同步  0:不同步

    private String content_label;//用于接收jsp页面传标签类型时的数据，DB中是没有这个字段的

    public Integer getInfotype() {
        return infotype;
    }

    public void setInfotype(Integer infotype) {
        this.infotype = infotype;
    }

    public String getContent_label() {
        return content_label;
    }

    public void setContent_label(String content_label) {
        this.content_label = content_label;
    }

    public Short getIssynchisbid() {
        return issynchisbid;
    }

    public void setIssynchisbid(Short issynchisbid) {
        this.issynchisbid = issynchisbid;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }

    public String getPictitle() {
        return pictitle;
    }

    public void setPictitle(String pictitle) {
        this.pictitle = pictitle;
    }

    public String getInfono() {
        return infono;
    }

    public void setInfono(String infono) {
        this.infono = infono;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTtypeid() {
        return ttypeid;
    }

    public void setTtypeid(BigDecimal ttypeid) {
        this.ttypeid = ttypeid;
    }

    public String getTtypename() {
        return ttypename;
    }

    public void setTtypename(String ttypename) {
        this.ttypename = ttypename == null ? null : ttypename.trim();
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname == null ? null : infoname.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Short getInfoattribute() {
        return infoattribute;
    }

    public void setInfoattribute(Short infoattribute) {
        this.infoattribute = infoattribute;
    }

    public Short getIsfixed() {
        return isfixed;
    }

    public void setIsfixed(Short isfixed) {
        this.isfixed = isfixed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}