package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class InterfaceChannel {
    private BigDecimal id;

    private String channo;

    private String channame;

    private String chancompany;

    private String chanvertion;

    private String chantype;

    private String chanurl;

    private Short isuse;

    private Date addtime;

    private String addman;

    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getChanno() {
        return channo;
    }

    public void setChanno(String channo) {
        this.channo = channo == null ? null : channo.trim();
    }

    public String getChanname() {
        return channame;
    }

    public void setChanname(String channame) {
        this.channame = channame == null ? null : channame.trim();
    }

    public String getChancompany() {
        return chancompany;
    }

    public void setChancompany(String chancompany) {
        this.chancompany = chancompany == null ? null : chancompany.trim();
    }

    public String getChanvertion() {
        return chanvertion;
    }

    public void setChanvertion(String chanvertion) {
        this.chanvertion = chanvertion == null ? null : chanvertion.trim();
    }

    public String getChantype() {
        return chantype;
    }

    public void setChantype(String chantype) {
        this.chantype = chantype == null ? null : chantype.trim();
    }

    public String getChanurl() {
        return chanurl;
    }

    public void setChanurl(String chanurl) {
        this.chanurl = chanurl == null ? null : chanurl.trim();
    }

    public Short getIsuse() {
        return isuse;
    }

    public void setIsuse(Short isuse) {
        this.isuse = isuse;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}