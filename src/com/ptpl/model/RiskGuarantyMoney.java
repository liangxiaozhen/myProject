package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author：liuqh
 * @Description:标的风险保证金设置
 */ 
public class RiskGuarantyMoney {
    private BigDecimal id;

    private String riskgmno;//风险保证金编号

    private String rgmrecman;//风险保证金收款人

    private Short chargetype;//收费类型（1结标收取，2投标时收取）

    private Double minrgmmoney;//结标分段金额低值

    private Double maxrgmmoney;//结标分段金额高值

    private Double rgmquota;//风险保证金定额

    private Double rgmpercent;//风险保证金百份比

    private Double maxrgmfee;//该段最高风险保证金额

    private String  ugrade;//会员等级

    private Double rgmrate;//风险保证金费率（投标时收取）

    private Double maxrgmamount;//风险保证金最高收费（投标时收取）

    private Short istemplet;//是否为模板（0否，1是）
    
    private BigDecimal tid;//标id
    
    private BigDecimal rgmrecmanid;//风险保证金收款人Id
    
    private Short isaudit;//资金清算是否需要审核
    
    private String addman;//添加人
    
    private Date addtime;//添加时间
    
    private String remark;//备注
    
    private List<RiskGuarantyMoney> riskGuarantyMoneys;//储存前台传过来的数据

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRiskgmno() {
        return riskgmno;
    }

    public void setRiskgmno(String riskgmno) {
        this.riskgmno = riskgmno == null ? null : riskgmno.trim();
    }

    public String getRgmrecman() {
        return rgmrecman;
    }

    public void setRgmrecman(String rgmrecman) {
        this.rgmrecman = rgmrecman == null ? null : rgmrecman.trim();
    }

    public Short getChargetype() {
        return chargetype;
    }

    public void setChargetype(Short chargetype) {
        this.chargetype = chargetype;
    }

    public Double getMinrgmmoney() {
        return minrgmmoney;
    }

    public void setMinrgmmoney(Double minrgmmoney) {
        this.minrgmmoney = minrgmmoney;
    }

    public Double getMaxrgmmoney() {
        return maxrgmmoney;
    }

    public void setMaxrgmmoney(Double maxrgmmoney) {
        this.maxrgmmoney = maxrgmmoney;
    }

    public Double getRgmquota() {
        return rgmquota;
    }

    public void setRgmquota(Double rgmquota) {
        this.rgmquota = rgmquota;
    }

    public Double getRgmpercent() {
        return rgmpercent;
    }

    public void setRgmpercent(Double rgmpercent) {
        this.rgmpercent = rgmpercent;
    }

    public Double getMaxrgmfee() {
        return maxrgmfee;
    }

    public void setMaxrgmfee(Double maxrgmfee) {
        this.maxrgmfee = maxrgmfee;
    }

    public String  getUgrade() {
        return ugrade;
    }

    public void setUgrade(String  ugrade) {
        this.ugrade = ugrade;
    }

    public Double getRgmrate() {
        return rgmrate;
    }

    public void setRgmrate(Double rgmrate) {
        this.rgmrate = rgmrate;
    }

    public Double getMaxrgmamount() {
        return maxrgmamount;
    }

    public void setMaxrgmamount(Double maxrgmamount) {
        this.maxrgmamount = maxrgmamount;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

	@Override
	public String toString() {
		return "RiskGuarantyMoney [id=" + id + ", riskgmno=" + riskgmno + ", rgmrecman=" + rgmrecman + ", chargetype="
				+ chargetype + ", minrgmmoney=" + minrgmmoney + ", maxrgmmoney=" + maxrgmmoney + ", rgmquota="
				+ rgmquota + ", rgmpercent=" + rgmpercent + ", maxrgmfee=" + maxrgmfee + ", ugrade=" + ugrade
				+ ", rgmrate=" + rgmrate + ", maxrgmamount=" + maxrgmamount + ", istemplet=" + istemplet + ","
			    + "tid="+tid+",rgmrecmanid="+rgmrecmanid+",isaudit="+isaudit+""
			    + ",addman="+addman+"addtime="+addtime+",remark="+remark+"]";
	}

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public BigDecimal getRgmrecmanid() {
		return rgmrecmanid;
	}

	public void setRgmrecmanid(BigDecimal rgmrecmanid) {
		this.rgmrecmanid = rgmrecmanid;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public List<RiskGuarantyMoney> getRiskGuarantyMoneys() {
		return riskGuarantyMoneys;
	}

	public void setRiskGuarantyMoneys(List<RiskGuarantyMoney> riskGuarantyMoneys) {
		this.riskGuarantyMoneys = riskGuarantyMoneys;
	}

	public String getAddman() {
		return addman;
	}

	public void setAddman(String addman) {
		this.addman = addman;
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
		this.remark = remark;
	}
    	
}