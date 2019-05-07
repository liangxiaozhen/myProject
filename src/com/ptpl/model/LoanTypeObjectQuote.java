package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author liuj
 * 借款类型对象设置
 */
public class LoanTypeObjectQuote {
    private BigDecimal id;//主键
    private Short serialno;//对象序号(1-30)
    private String objectname;//对象名称
    private Date addtime;//添加时间
    private String remark;//备注
    private String addman;//添加人
    private Short isuse;//是否启用
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Short getSerialno() {
        return serialno;
    }

    public void setSerialno(Short serialno) {
        this.serialno = serialno;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname == null ? null : objectname.trim();
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

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

	public Short getIsuse() {
		return isuse;
	}

	public void setIsuse(Short isuse) {
		this.isuse = isuse;
	}

    @Override
    public String toString() {
        return "LoanTypeObjectQuote{" +
                "id=" + id +
                ", serialno=" + serialno +
                ", objectname='" + objectname + '\'' +
                ", addtime=" + addtime +
                ", remark='" + remark + '\'' +
                ", addman='" + addman + '\'' +
                ", isuse=" + isuse +
                '}';
    }
}