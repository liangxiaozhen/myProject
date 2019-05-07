package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class Award {
	
    private BigDecimal id;
    
    private BigDecimal SNLId;//买家定向名单列表Id
    
    private String businessName;//定向名单列表的名称（用于前端显示）
    
    private String ano;//奖品编号

    private String aname;//奖品名称

    private Short atype;//奖品类型(1站内虚拟，2站外虚拟，3站外实物)
    
    private Short attribute;//奖品属性(1.站内虚拟:现金、类现金、假现金、积分、加息券、提现抵用券、充值抵用券  2.站外虚拟：电影标、手机话费  3.站外实物：手机、电脑、耳机、鼠标)

    private Short allswitch;//奖品交易方式总开关

    private Short subswitch;//奖品交易方式子开关

    private Short tradetype;//奖品交易方式

    private Date endtime;//有效期(积分没有限制)

    private String wcrestdate;//提现日期限制(弃用)

    private Double wcrestrict;//提现额度限制（弃用）

    private Short wctype;//提现到账方式限制(及时,普通)

    private String tattribute;//投标属性限制(0000000000占位符标识）
    
    private String tattributeStr;//投标属性限制字符串   前端显示
    
    private Integer tdayLimitl;//投标期限限制最低值
    
    private Integer tdayrestrict;//投标期限限制最高值
    
    private Double tmlrrestrict;//投标收益限制最低值

    private Double tmhrrestrict;//投标收益限制最高值

    private Short trtype;//投标还款方式 

    private Double tminmoney;//投标金额限制低值

    private Double tmaxmoney;//投标金额限制高值

    private Double cashorvoucher;//奖额(券，现金，类现金)只限于站内虚拟有用

    private Long points;//奖额数（弃用）

    private Long quantityall;//奖品总份数

    private Long quantityrest;//奖品剩余数

    private Date addtime;//添加时间

    private String addman;//添加人

    private String remark;//备注
    
    
    
    private Short iscancel;//是否下架   0.下架   1.上架
    
    private String ugrade;//会员等级限制(100000000000000000000000000000)全1为所有会员
    
    private String ugradeStr;//会员等级限制字符串   前端显示
    
    private String removenameno;//排除名单表编号（弃用）
    
    private Short usagerights;//使用权设置（系统设定，提现人自选）
    
    private String specialtno;//指定的标号
    
    
    private String awardRules;//领奖规则
    
    private String raiseIntObject;//加息对象  1.真金 ，2.类现金，3.假现金
    
    private String raiseIntObjectStr;//加息对象前端显示
    
    private Short isDefineAmount;//奖额是否自定义（1.是  2.否）站内虚拟
    
    
    private Short isDelete;//是否可以删除（1.是  2.否）
    
    private Short isTemplet;//是否为模板（1.是  2.否）
    
    private String awardUnit;//奖额 单位  1.%  2.元   3.分
    

    public BigDecimal getId() {
        return id;
    }

	public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getSNLId() {
		return SNLId;
	}

	public void setSNLId(BigDecimal sNLId) {
		SNLId = sNLId;
	}

	public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano == null ? null : ano.trim();
    }

    public Short getAtype() {
        return atype;
    }

    public void setAtype(Short atype) {
        this.atype = atype;
    }

    public Short getAttribute() {
        return attribute;
    }

    public void setAttribute(Short attribute) {
        this.attribute = attribute;
    }

    public Short getAllswitch() {
        return allswitch;
    }

    public void setAllswitch(Short allswitch) {
        this.allswitch = allswitch;
    }

    public Short getSubswitch() {
        return subswitch;
    }

    public void setSubswitch(Short subswitch) {
        this.subswitch = subswitch;
    }

    public Short getTradetype() {
        return tradetype;
    }

    public void setTradetype(Short tradetype) {
        this.tradetype = tradetype;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getWcrestdate() {
        return wcrestdate;
    }

    public void setWcrestdate(String wcrestdate) {
        this.wcrestdate = wcrestdate == null ? null : wcrestdate.trim();
    }

    public Double getWcrestrict() {
        return wcrestrict;
    }

    public void setWcrestrict(Double wcrestrict) {
        this.wcrestrict = wcrestrict;
    }

    public Short getWctype() {
        return wctype;
    }

    public void setWctype(Short wctype) {
        this.wctype = wctype;
    }

    public String getTattribute() {
        return tattribute;
    }

    public void setTattribute(String tattribute) {
        this.tattribute = tattribute == null ? null : tattribute.trim();
    }

    public Integer getTdayLimitl() {
		return tdayLimitl;
	}

	public void setTdayLimitl(Integer tdayLimitl) {
		this.tdayLimitl = tdayLimitl;
	}

	public Integer getTdayrestrict() {
        return tdayrestrict;
    }

    public void setTdayrestrict(Integer tdayrestrict) {
        this.tdayrestrict = tdayrestrict;
    }

    public Double getTmlrrestrict() {
        return tmlrrestrict;
    }

    public void setTmlrrestrict(Double tmlrrestrict) {
        this.tmlrrestrict = tmlrrestrict;
    }

    public Double getTmhrrestrict() {
        return tmhrrestrict;
    }

    public void setTmhrrestrict(Double tmhrrestrict) {
        this.tmhrrestrict = tmhrrestrict;
    }

    public Short getTrtype() {
        return trtype;
    }

    public void setTrtype(Short trtype) {
        this.trtype = trtype;
    }

    public Double getTminmoney() {
        return tminmoney;
    }

    public void setTminmoney(Double tminmoney) {
        this.tminmoney = tminmoney;
    }

    public Double getTmaxmoney() {
        return tmaxmoney;
    }

    public void setTmaxmoney(Double tmaxmoney) {
        this.tmaxmoney = tmaxmoney;
    }

    public Double getCashorvoucher() {
        return cashorvoucher;
    }

    public void setCashorvoucher(Double cashorvoucher) {
        this.cashorvoucher = cashorvoucher;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
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

	public Short getIscancel() {
		return iscancel;
	}

	public void setIscancel(Short iscancel) {
		this.iscancel = iscancel;
	}

	public String getUgrade() {
		return ugrade;
	}

	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}

	public String getRemovenameno() {
		return removenameno;
	}

	public void setRemovenameno(String removenameno) {
		this.removenameno = removenameno;
	}

	public Short getUsagerights() {
		return usagerights;
	}

	public void setUsagerights(Short usagerights) {
		this.usagerights = usagerights;
	}

	public String getSpecialtno() {
		return specialtno;
	}

	public void setSpecialtno(String specialtno) {
		this.specialtno = specialtno;
	}

	public String getAwardRules() {
		return awardRules;
	}

	public void setAwardRules(String awardRules) {
		this.awardRules = awardRules;
	}
	
	 public String getRaiseIntObject() {
		return raiseIntObject;
	}

	public void setRaiseIntObject(String raiseIntObject) {
		this.raiseIntObject = raiseIntObject;
	}
	
	
	public Short getIsDefineAmount() {
		return isDefineAmount;
	}

	public void setIsDefineAmount(Short isDefineAmount) {
		this.isDefineAmount = isDefineAmount;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public Short getIsTemplet() {
		return isTemplet;
	}

	public void setIsTemplet(Short isTemplet) {
		this.isTemplet = isTemplet;
	}

	public String getTattributeStr() {
		return tattributeStr;
	}

	public void setTattributeStr(String tattributeStr) {
		this.tattributeStr = tattributeStr;
	}

	public String getUgradeStr() {
		return ugradeStr;
	}

	public void setUgradeStr(String ugradeStr) {
		this.ugradeStr = ugradeStr;
	}

	public String getRaiseIntObjectStr() {
		return raiseIntObjectStr;
	}

	public void setRaiseIntObjectStr(String raiseIntObjectStr) {
		this.raiseIntObjectStr = raiseIntObjectStr;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}

	@Override
	public String toString() {
		return "Award [id=" + id + ", SNLId=" + SNLId + ", businessName=" + businessName + ", ano=" + ano + ", aname="
				+ aname + ", atype=" + atype + ", attribute=" + attribute + ", allswitch=" + allswitch + ", subswitch="
				+ subswitch + ", tradetype=" + tradetype + ", endtime=" + endtime + ", wcrestdate=" + wcrestdate
				+ ", wcrestrict=" + wcrestrict + ", wctype=" + wctype + ", tattribute=" + tattribute
				+ ", tattributeStr=" + tattributeStr + ", tdayrestrict=" + tdayrestrict + ", tmlrrestrict="
				+ tmlrrestrict + ", tmhrrestrict=" + tmhrrestrict + ", trtype=" + trtype + ", tminmoney=" + tminmoney
				+ ", tmaxmoney=" + tmaxmoney + ", cashorvoucher=" + cashorvoucher + ", points=" + points
				+ ", quantityall=" + quantityall + ", quantityrest=" + quantityrest + ", addtime=" + addtime
				+ ", addman=" + addman + ", remark=" + remark + ", iscancel=" + iscancel + ", ugrade=" + ugrade
				+ ", ugradeStr=" + ugradeStr + ", removenameno=" + removenameno + ", usagerights=" + usagerights
				+ ", specialtno=" + specialtno + ", awardRules=" + awardRules + ", raiseIntObject=" + raiseIntObject
				+ ", raiseIntObjectStr=" + raiseIntObjectStr + ", isDefineAmount=" + isDefineAmount + ", isDelete="
				+ isDelete + ", isTemplet=" + isTemplet + ", awardUnit=" + awardUnit + "]";
	}

	
}