package com.ptpl.model;

import java.math.BigDecimal;
/**
  * 单标前端信息
  * @author :liuqh
  * @date :2017/5/19 17:00
  */
public class TenderFrontEndSingle {
    private BigDecimal id;

    private String tno;//标号

    private String ttypename;//标的类型名称

    private Integer sno;//序号(编号排序)

    private String infono;//只是编号，此条数据的唯一标识，并不是公共资料的编号

    private String infoname;//项目名称(资料)

    private Integer source;//来源（1新增，2引用）

    private String content;//内容

    private Short isdisplay;//前端状态（1显示，0不显示）

    private Short infoattribute;//内容属性（1图片，2文字，3下拉，4标签）

    private String pictitle;//图片标题

    private Short isfixed;//应用范围（1通用，2唯一)

    private Short isedit;//是否已编辑(0未编辑  1编辑过)

    private Integer infotype;//资料类别ID

    private TenderFeiType tenderFeiType;//标的前端信息类别设置（多对一关联影射）




    private String typename;//用于封装给前端展视的字段，DB中并没有此字段

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public TenderFeiType getTenderFeiType() {
        return tenderFeiType;
    }

    public void setTenderFeiType(TenderFeiType tenderFeiType) {
        this.tenderFeiType = tenderFeiType;
    }

    public Integer getInfotype() {
        return infotype;
    }

    public void setInfotype(Integer infotype) {
        this.infotype = infotype;
    }

    public Short getIsedit() {
        return isedit;
    }

    public void setIsedit(Short isedit) {
        this.isedit = isedit;
    }

    public Short getIsfixed() {
        return isfixed;
    }

    public void setIsfixed(Short isfixed) {
        this.isfixed = isfixed;
    }

    public String getPictitle() {
        return pictitle;
    }

    public void setPictitle(String pictitle) {
        this.pictitle = pictitle;
    }

    public Short getInfoattribute() {
        return infoattribute;
    }

    public void setInfoattribute(Short infoattribute) {
        this.infoattribute = infoattribute;
    }

    public BigDecimal getId() {
        return id;

    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getTtypename() {
        return ttypename;
    }

    public void setTtypename(String ttypename) {
        this.ttypename = ttypename == null ? null : ttypename.trim();
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getInfono() {
        return infono;
    }

    public void setInfono(String infono) {
        this.infono = infono == null ? null : infono.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Short isdisplay) {
        this.isdisplay = isdisplay;
    }
}