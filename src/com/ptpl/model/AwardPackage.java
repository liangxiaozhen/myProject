package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author：liuqh
 * @Description:奖品包
 */ 
public class AwardPackage {
	
    private BigDecimal id;

    private String apno;//奖品包编号

    private String apname;//奖品包名称

    private Short iscancel;//是否下架   0.下架   1.上架

    private Long quantityall;//奖品包总份数

    private Long quantityrest;//奖品包剩余数

    private String invirtseq;//站内虚拟编号(弃用)

    private Integer invirtqty;//站内虚拟奖品份数(弃用)

    private String invirtno;//站内虚拟奖品编号(弃用)

    private Short invirtattr;//站内虚拟奖品种类(弃用)

    private String invirtname;//站内虚拟奖品名称(弃用)

    private String outvirtseq;//站外虚拟编号(弃用)

    private Integer outvirtqty;//站外虚拟奖品份数(弃用)

    private String outvirtno;//站外虚拟奖品编号(弃用)

    private Short outvirtattr;//站外虚拟奖品种类(弃用)

    private String outvirtname;//站外虚拟奖品名称(弃用)

    private String outrealseq;//站外实体拟编号(弃用)

    private Integer outrealqty;//站外实体奖品份数(弃用)

    private String outrealno;//站外实体奖品编号(弃用)

    private Short outrealattr;//站外实体奖品种类(弃用)

    private String outrealname;//站外实体奖品名称(弃用)

    private Date addtime;//添加时间

    private String addman;//添加人

    private String remark;//备注

    private Short isTemplet;//是否为模板   1.是   2否
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getApname() {
        return apname;
    }

    public void setApname(String apname) {
        this.apname = apname == null ? null : apname.trim();
    }

    public String getApno() {
        return apno;
    }

    public void setApno(String apno) {
        this.apno = apno == null ? null : apno.trim();
    }

    public Short getIscancel() {
        return iscancel;
    }

    public void setIscancel(Short iscancel) {
        this.iscancel = iscancel;
    }

    public Long getQuantityall() {
        return quantityall;
    }

    public void setQuantityall(Long quantityall) {
        this.quantityall = quantityall;
    }

    public Long getQuantityrest() {
        return quantityrest;
    }

    public void setQuantityrest(Long quantityrest) {
        this.quantityrest = quantityrest;
    }

    public String getInvirtseq() {
        return invirtseq;
    }

    public void setInvirtseq(String invirtseq) {
        this.invirtseq = invirtseq == null ? null : invirtseq.trim();
    }

    public Integer getInvirtqty() {
        return invirtqty;
    }

    public void setInvirtqty(Integer invirtqty) {
        this.invirtqty = invirtqty;
    }

    public String getInvirtno() {
        return invirtno;
    }

    public void setInvirtno(String invirtno) {
        this.invirtno = invirtno == null ? null : invirtno.trim();
    }

    public Short getInvirtattr() {
        return invirtattr;
    }

    public void setInvirtattr(Short invirtattr) {
        this.invirtattr = invirtattr;
    }

    public String getInvirtname() {
        return invirtname;
    }

    public void setInvirtname(String invirtname) {
        this.invirtname = invirtname == null ? null : invirtname.trim();
    }

    public String getOutvirtseq() {
        return outvirtseq;
    }

    public void setOutvirtseq(String outvirtseq) {
        this.outvirtseq = outvirtseq == null ? null : outvirtseq.trim();
    }

    public Integer getOutvirtqty() {
        return outvirtqty;
    }

    public void setOutvirtqty(Integer outvirtqty) {
        this.outvirtqty = outvirtqty;
    }

    public String getOutvirtno() {
        return outvirtno;
    }

    public void setOutvirtno(String outvirtno) {
        this.outvirtno = outvirtno == null ? null : outvirtno.trim();
    }

    public Short getOutvirtattr() {
        return outvirtattr;
    }

    public void setOutvirtattr(Short outvirtattr) {
        this.outvirtattr = outvirtattr;
    }

    public String getOutvirtname() {
        return outvirtname;
    }

    public void setOutvirtname(String outvirtname) {
        this.outvirtname = outvirtname == null ? null : outvirtname.trim();
    }

    public String getOutrealseq() {
        return outrealseq;
    }

    public void setOutrealseq(String outrealseq) {
        this.outrealseq = outrealseq == null ? null : outrealseq.trim();
    }

    public Integer getOutrealqty() {
        return outrealqty;
    }

    public void setOutrealqty(Integer outrealqty) {
        this.outrealqty = outrealqty;
    }

    public String getOutrealno() {
        return outrealno;
    }

    public void setOutrealno(String outrealno) {
        this.outrealno = outrealno == null ? null : outrealno.trim();
    }

    public Short getOutrealattr() {
        return outrealattr;
    }

    public void setOutrealattr(Short outrealattr) {
        this.outrealattr = outrealattr;
    }

    public String getOutrealname() {
        return outrealname;
    }

    public void setOutrealname(String outrealname) {
        this.outrealname = outrealname == null ? null : outrealname.trim();
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

	public Short getIsTemplet() {
		return isTemplet;
	}

	public void setIsTemplet(Short isTemplet) {
		this.isTemplet = isTemplet;
	}

	@Override
	public String toString() {
		return "AwardPackage [id=" + id + ", apno=" + apno + ", apname=" + apname + ", iscancel=" + iscancel
				+ ", quantityall=" + quantityall + ", quantityrest=" + quantityrest + ", addtime=" + addtime
				+ ", addman=" + addman + ", remark=" + remark + ", isTemplet=" + isTemplet + "]";
	}

}