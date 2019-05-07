package com.ptpl.model;

import java.math.BigDecimal;
/**
 * @author liuj
 * 债转排除名单关联表
 */
public class DattornRNameLink {
	 
    private BigDecimal id;

    private BigDecimal tid;//标id

    private String ownerrnno;//转出人限制名单编号

    private String noapnno;//购买人限制名单编号

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOwnerrnno() {
        return ownerrnno;
    }

    public void setOwnerrnno(String ownerrnno) {
        this.ownerrnno = ownerrnno == null ? null : ownerrnno.trim();
    }

    public String getNoapnno() {
        return noapnno;
    }

    public void setNoapnno(String noapnno) {
        this.noapnno = noapnno == null ? null : noapnno.trim();
    }

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}
}