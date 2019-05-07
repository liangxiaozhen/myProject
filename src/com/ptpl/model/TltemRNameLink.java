package com.ptpl.model;

import java.math.BigDecimal;
/**
 * @author liuj
 * 投标名单关联表
 */
public class TltemRNameLink {
    private BigDecimal id;//id

    private BigDecimal titemid;//标的id

    private String rnameno;//排除名单编号

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTitemid() {
        return titemid;
    }

    public void setTitemid(BigDecimal titemid) {
        this.titemid = titemid;
    }

    public String getRnameno() {
        return rnameno;
    }

    public void setRnameno(String rnameno) {
        this.rnameno = rnameno == null ? null : rnameno.trim();
    }
}