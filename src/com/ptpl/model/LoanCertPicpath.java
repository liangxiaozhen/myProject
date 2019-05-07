package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class LoanCertPicpath {
    private BigDecimal id;

    private BigDecimal baseid;

    private Short certtype;

    private String certname;

    private String certinfopath;

    private Date addtime;

    private String remark;

    private String liano;//借款人资料申请编号

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

    public Short getCerttype() {
        return certtype;
    }

    public void setCerttype(Short certtype) {
        this.certtype = certtype;
    }

    public String getCertname() {
        return certname;
    }

    public void setCertname(String certname) {
        this.certname = certname == null ? null : certname.trim();
    }

    public String getCertinfopath() {
        return certinfopath;
    }

    public void setCertinfopath(String certinfopath) {
        this.certinfopath = certinfopath == null ? null : certinfopath.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLiano() {
        return liano;
    }

    public void setLiano(String liano) {
        this.liano = liano == null ? null : liano.trim();
    }
}