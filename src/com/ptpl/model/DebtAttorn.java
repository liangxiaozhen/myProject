package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author：liuqh
 * @Description:标的债权转让设置
 */ 
public class DebtAttorn {
    private BigDecimal id;

    private String debtattornno;//债权转让编号

    private Short isdebtaudit;//债权转让审核

    private Short isocdebt;//是否支持逾期债转(1.支持，0,不支持)

    private String aownergrade;//允许债权人债转的等级(100000000000000000000000000000) 全1为所有会员
   
    private String removenameno;//债转排除名单表编号

    private Integer holdday;//持有时间（投标成功后多少天）

    private Integer intervalday;//距离下个还款日天数

    private Short isasplit;//是否允许拆分(1.允许,0,不允许)

    private Double attornmoney;//转让金额高值（如果允许拆分则可以部分转让）
    
    private Double attornmoneylow;//转让金额低值

    private Double minattornratio;//转让系数最高值

    private Double maxattornratio;//转让系数最低值（可以溢价）

    private String apurchasergrade;//允许购买人债转的等级(100000000000000000000000000000)全1为所有会员  购买人不能是借款人和出让人
    
    private String noapnameno;//不允许购买的用户名单表编号

    private Short isabuyallorpart;//购买人允许全额或部分购买

    private Short isadafeeon;//债转手续费开关(1.开,0,关,)

    private String ugrade;//债转手续费收取  允许的转让人等级（1，2，3，4，）
  
    private Double minattornmoney;//债转金额低值

    private Double maxattornmoney;//债转金额高值

    private Double quota;//定额收费

    private Double attornrate;//收取债转金额百分比

    private Double minfee;//收取最低值

    private Double maxfee;//收取最高值
    
    private Integer datimes;//债转次数限制

	private Short istemplet;//是否为模板（0否，1是）
	
	private BigDecimal tid;//标id
	
	private Short isaudit;//资金清算是否需要审核
	
	private Short feemode;//债转手续费计算方式(1.用户等级，2.根据投标时间)
	
	private BigDecimal hadday;//持有时间(>=100),单位:天
	
	private BigDecimal baseid;//baseid
	/**投标记录表*/
    private  UserTender usertender;
    /**标的设置表*/
    private  TenderItem tenderitem;
    /**还款计划具体记录表*/
    private  RepayMent repayment;
    /**标的债转设置排出人员名单表*/
    private  DattornRNameLink dattornrnamelink;
    
    private Integer aheadocday;//逾期前几天可以转让
    
    private String valuepoint;//起息日时间点
    
	private Short valuerule;//起息规则(1.承接日当天2.承接日次日3.固定时间点)
    
    private Short intdisable;//债转增益处理(1.全部失效2.按债转金额比例失效,3.不作废)
    
    private Short deadline;//债转期限(单位天)，要在规定时间内，有人承接，否则回下架
    
    private Short isfixed;//1.固定/0.不固定
    
    private Short dattrestrict;//债转次数方式限制(1.层级次数,2.每人次数)
    
    private String addman;//添加人
    
    private Date addtime;//设置时间
    
    private String remark;//备注
    
    private BigDecimal snlid;//定向名单列表Id
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDebtattornno() {
        return debtattornno;
    }

    public void setDebtattornno(String debtattornno) {
        this.debtattornno = debtattornno == null ? null : debtattornno.trim();
    }

    public Short getIsdebtaudit() {
        return isdebtaudit;
    }

    public void setIsdebtaudit(Short isdebtaudit) {
        this.isdebtaudit = isdebtaudit;
    }

    public Short getIsocdebt() {
        return isocdebt;
    }

    public void setIsocdebt(Short isocdebt) {
        this.isocdebt = isocdebt;
    }

    public String getAownergrade() {
        return aownergrade;
    }

    public void setAownergrade(String aownergrade) {
        this.aownergrade = aownergrade == null ? null : aownergrade.trim();
    }

    public String getRemovenameno() {
        return removenameno;
    }

    public void setRemovenameno(String removenameno) {
        this.removenameno = removenameno == null ? null : removenameno.trim();
    }

    public Integer getHoldday() {
        return holdday;
    }

    public void setHoldday(Integer holdday) {
        this.holdday = holdday;
    }

    public Integer getIntervalday() {
        return intervalday;
    }

    public void setIntervalday(Integer intervalday) {
        this.intervalday = intervalday;
    }

    public Short getIsasplit() {
        return isasplit;
    }

    public void setIsasplit(Short isasplit) {
        this.isasplit = isasplit;
    }

    public Double getAttornmoney() {
        return attornmoney;
    }

    public void setAttornmoney(Double attornmoney) {
        this.attornmoney = attornmoney;
    }

    public Double getMinattornratio() {
        return minattornratio;
    }

    public void setMinattornratio(Double minattornratio) {
        this.minattornratio = minattornratio;
    }

    public Double getMaxattornratio() {
        return maxattornratio;
    }

    public void setMaxattornratio(Double maxattornratio) {
        this.maxattornratio = maxattornratio;
    }

    public String getApurchasergrade() {
        return apurchasergrade;
    }

    public void setApurchasergrade(String apurchasergrade) {
        this.apurchasergrade = apurchasergrade == null ? null : apurchasergrade.trim();
    }

    public String getNoapnameno() {
        return noapnameno;
    }

    public void setNoapnameno(String noapnameno) {
        this.noapnameno = noapnameno == null ? null : noapnameno.trim();
    }

    public Short getIsabuyallorpart() {
        return isabuyallorpart;
    }

    public void setIsabuyallorpart(Short isabuyallorpart) {
        this.isabuyallorpart = isabuyallorpart;
    }

    public Short getIsadafeeon() {
        return isadafeeon;
    }

    public void setIsadafeeon(Short isadafeeon) {
        this.isadafeeon = isadafeeon;
    }

    public String  getUgrade() {
        return ugrade;
    }

    public void setUgrade(String  ugrade) {
        this.ugrade = ugrade;
    }

    public Double getMinattornmoney() {
        return minattornmoney;
    }

    public void setMinattornmoney(Double minattornmoney) {
        this.minattornmoney = minattornmoney;
    }

    public Double getMaxattornmoney() {
        return maxattornmoney;
    }

    public void setMaxattornmoney(Double maxattornmoney) {
        this.maxattornmoney = maxattornmoney;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getAttornrate() {
        return attornrate;
    }

    public void setAttornrate(Double attornrate) {
        this.attornrate = attornrate;
    }

    public Double getMinfee() {
        return minfee;
    }

    public void setMinfee(Double minfee) {
        this.minfee = minfee;
    }

    public Double getMaxfee() {
        return maxfee;
    }

    public void setMaxfee(Double maxfee) {
        this.maxfee = maxfee;
    }
    
    public Integer getDatimes() {
    	return datimes;
    }
    
    public void setDatimes(Integer datimes) {
    	this.datimes = datimes;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }


	@Override
	public String toString() {
		return "DebtAttorn [id=" + id + ", debtattornno=" + debtattornno + ", isdebtaudit=" + isdebtaudit
				+ ", isocdebt=" + isocdebt + ", aownergrade=" + aownergrade + ", removenameno=" + removenameno
				+ ", holdday=" + holdday + ", intervalday=" + intervalday + ", isasplit=" + isasplit + ", attornmoney="
				+ attornmoney + ", attornmoneylow=" + attornmoneylow + ", minattornratio=" + minattornratio
				+ ", maxattornratio=" + maxattornratio + ", apurchasergrade=" + apurchasergrade + ", noapnameno="
				+ noapnameno + ", isabuyallorpart=" + isabuyallorpart + ", isadafeeon=" + isadafeeon + ", ugrade="
				+ ugrade + ", minattornmoney=" + minattornmoney + ", maxattornmoney=" + maxattornmoney + ", quota="
				+ quota + ", attornrate=" + attornrate + ", minfee=" + minfee + ", maxfee=" + maxfee + ", datimes="
				+ datimes + ", istemplet=" + istemplet + ", tid=" + tid + ", isaudit=" + isaudit + ", feemode="
				+ feemode + ", hadday=" + hadday + ", baseid=" + baseid + ", usertender=" + usertender + ", tenderitem="
				+ tenderitem + ", repayment=" + repayment + ", dattornrnamelink=" + dattornrnamelink + ",aheadocday="+aheadocday+","
				+ "valuepoint="+valuepoint+",valuerule="+valuerule+",intdisable="+intdisable+",deadline="+deadline+",isfixed="+isfixed+""
				+ ",dattrestrict="+dattrestrict+",addman="+addman+",addtime="+addtime+",remark="+remark+"]";
	}

	public DattornRNameLink getDattornrnamelink() {
		return dattornrnamelink;
	}

	public void setDattornrnamelink(DattornRNameLink dattornrnamelink) {
		this.dattornrnamelink = dattornrnamelink;
	}

	public UserTender getUsertender() {
		return usertender;
	}

	public void setUsertender(UserTender usertender) {
		this.usertender = usertender;
	}

	public TenderItem getTenderitem() {
		return tenderitem;
	}

	public void setTenderitem(TenderItem tenderitem) {
		this.tenderitem = tenderitem;
	}

	public BigDecimal getBaseid() {
		return baseid;
	}

	public void setBaseid(BigDecimal baseid) {
		this.baseid = baseid;
	}

	public RepayMent getRepayment() {
		return repayment;
	}
	public Double getAttornmoneylow() {
		return attornmoneylow;
	}

	public void setAttornmoneylow(Double attornmoneylow) {
		this.attornmoneylow = attornmoneylow;
	}

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public Short getFeemode() {
		return feemode;
	}

	public void setFeemode(Short feemode) {
		this.feemode = feemode;
	}

	public BigDecimal getHadday() {
		return hadday;
	}

	public void setHadday(BigDecimal hadday) {
		this.hadday = hadday;
	}


	public void setRepayment(RepayMent repayment) {
		this.repayment = repayment;
	}

	public Integer getAheadocday() {
		return aheadocday;
	}

	public void setAheadocday(Integer aheadocday) {
		this.aheadocday = aheadocday;
	}

	public Short getValuerule() {
		return valuerule;
	}

	public void setValuerule(Short valuerule) {
		this.valuerule = valuerule;
	}

	public Short getIntdisable() {
		return intdisable;
	}

	public void setIntdisable(Short intdisable) {
		this.intdisable = intdisable;
	}

	public Short getDeadline() {
		return deadline;
	}

	public void setDeadline(Short deadline) {
		this.deadline = deadline;
	}

	public String getValuepoint() {
		return valuepoint;
	}

	public void setValuepoint(String valuepoint) {
		this.valuepoint = valuepoint;
	}

	public Short getIsfixed() {
		return isfixed;
	}

	public void setIsfixed(Short isfixed) {
		this.isfixed = isfixed;
	}

	public Short getDattrestrict() {
		return dattrestrict;
	}

	public void setDattrestrict(Short dattrestrict) {
		this.dattrestrict = dattrestrict;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		this.addman = addman;
	}

	public BigDecimal getSnlid() {
		return snlid;
	}

	public void setSnlid(BigDecimal snlid) {
		this.snlid = snlid;
	}
}