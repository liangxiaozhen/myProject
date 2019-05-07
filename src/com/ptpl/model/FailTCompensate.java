package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author:liuqh
 * @date:2016年07月09日 19:58:12
 * @description:流标补偿设置
 */
public class FailTCompensate {
    private BigDecimal id;

    private String failtcno;//流标补偿编号

    private Short isintcompensateon;//利息方式补偿开关

    private String intugrade;//利息方式会员等级

    private Double minmoney;//分段最低投资金额

    private Double maxmoney;//分段最高投资金额

    private Double quota;//定额补偿金

    private Double dayawardrate;//日奖励费率

    private Double maxcompensate;//最高补偿金额

    private Short isawardcompensateon;//奖品补偿方式开关

    private String awardname;//奖品名称

    private Double awardquota;//奖额

    private Double awardrate;//奖品百分比

    private Double awardmaxmoney;//奖品最大值

    private Short istemplet;//是否为模板（0否，1是）
    
    private BigDecimal tid;//标id
    
    private String awardno;//奖品编号
    
    private Short  isaudit;//资金清算是否需要审核
    
    private String awardugrade;//奖品补偿方式会员等级
    
    private Double minmoneyaward;//分段投资最底金额
    
    private Double maxmoneyaward;//分段最高投资金额
    
    private String addman;//添加人
    
    private Date addtime;//设置时间
    
    private String remark;//备注
    
    private List<FailTCompensate> failTCompensates;//获取前台穿过来数据
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFailtcno() {
        return failtcno;
    }

    public void setFailtcno(String failtcno) {
        this.failtcno = failtcno == null ? null : failtcno.trim();
    }

    public Short getIsintcompensateon() {
        return isintcompensateon;
    }

    public void setIsintcompensateon(Short isintcompensateon) {
        this.isintcompensateon = isintcompensateon;
    }

    public String getIntugrade() {
        return intugrade;
    }

    public void setIntugrade(String intugrade) {
        this.intugrade = intugrade;
    }

    public Double getMinmoney() {
        return minmoney;
    }

    public void setMinmoney(Double minmoney) {
        this.minmoney = minmoney;
    }

    public Double getMaxmoney() {
        return maxmoney;
    }

    public void setMaxmoney(Double maxmoney) {
        this.maxmoney = maxmoney;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getDayawardrate() {
        return dayawardrate;
    }

    public void setDayawardrate(Double dayawardrate) {
        this.dayawardrate = dayawardrate;
    }

    public Double getMaxcompensate() {
        return maxcompensate;
    }

    public void setMaxcompensate(Double maxcompensate) {
        this.maxcompensate = maxcompensate;
    }

    public Short getIsawardcompensateon() {
        return isawardcompensateon;
    }

    public void setIsawardcompensateon(Short isawardcompensateon) {
        this.isawardcompensateon = isawardcompensateon;
    }

    public String getAwardname() {
        return awardname;
    }

    public void setAwardname(String awardname) {
        this.awardname = awardname == null ? null : awardname.trim();
    }

    public Double getAwardquota() {
        return awardquota;
    }

    public void setAwardquota(Double awardquota) {
        this.awardquota = awardquota;
    }

    public Double getAwardrate() {
        return awardrate;
    }

    public void setAwardrate(Double awardrate) {
        this.awardrate = awardrate;
    }

    public Double getAwardmaxmoney() {
        return awardmaxmoney;
    }

    public void setAwardmaxmoney(Double awardmaxmoney) {
        this.awardmaxmoney = awardmaxmoney;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

	@Override
	public String toString() {
		return "FailTCompensate [id=" + id + ", failtcno=" + failtcno + ", isintcompensateon=" + isintcompensateon
				+ ", intugrade=" + intugrade + ", minmoney=" + minmoney + ", maxmoney=" + maxmoney + ", quota=" + quota
				+ ", dayawardrate=" + dayawardrate + ", maxcompensate=" + maxcompensate + ", isawardcompensateon="
				+ isawardcompensateon + ", awardname=" + awardname + ", awardquota=" + awardquota + ", awardrate="
				+ awardrate + ", awardmaxmoney=" + awardmaxmoney + ", istemplet=" + istemplet + ",tid="+tid+",awardno="+awardno+","
				+ "isaudit="+isaudit+",minmoneyaward="+minmoneyaward+",maxmoneyaward="+maxmoneyaward+",awardugrade="+awardugrade+"]";
	}

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public String getAwardno() {
		return awardno;
	}

	public void setAwardno(String awardno) {
		this.awardno = awardno;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

 
	public Double getMinmoneyaward() {
		return minmoneyaward;
	}

	public void setMinmoneyaward(Double minmoneyaward) {
		this.minmoneyaward = minmoneyaward;
	}

	public Double getMaxmoneyaward() {
		return maxmoneyaward;
	}

	public void setMaxmoneyaward(Double maxmoneyaward) {
		this.maxmoneyaward = maxmoneyaward;
	}

	public List<FailTCompensate> getFailTCompensates() {
		return failTCompensates;
	}

	public void setFailTCompensates(List<FailTCompensate> failTCompensates) {
		this.failTCompensates = failTCompensates;
	}

	public String getAwardugrade() {
		return awardugrade;
	}

	public void setAwardugrade(String awardugrade) {
		this.awardugrade = awardugrade;
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