package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityAwardList {
	
    private BigDecimal id;//ID

    private String actid;//活动编号

    private BigDecimal baseid;//用户ID

    private String username;//用户名

    private String awardno;//奖品编号

    private String awardname;//奖品名称

    private Double awardmoney;//获奖金额

    private String awardUnit;//金额单位
    
    private Integer awardquantity;//奖品个数
    //发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 
    private Short status;//奖品状态（发放，待发，取消）

    private String mademan;//制作人（活动制作人）

    private Date madetime;//制作时间

    private String auditman;//审核人（审核）

    private Date audittime;//审核时间

    private String dealman;//处理人(发奖品)

    private Date dealtime;//处理时间

    private String remark;//备注
    
    //奖品属性（1.现金红包，2.类现金，3.假现金  4.交易积分，5.系统积分 6.加息券 7.提现抵用券8.充值抵用券 31.电影券 32.手机充值券 33.游戏点券 61.手机 62.鼠标 63.耳机  64.服饰）
    private Short awardattribute;
   
    private String adminRemark;//管理员备注 ，用来提示用户收货信息错误
    
    //关联奖品表
    private Award award;
    
    //关联活动表
    private ActivityList al;
    
    //关联用户基本信息表
    private UserBaseAccountInfo userbai;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

	public Short getAwardattribute() {
		return awardattribute;
	}

	public void setAwardattribute(Short awardattribute) {
		this.awardattribute = awardattribute;
	}

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAwardno() {
        return awardno;
    }

    public void setAwardno(String awardno) {
        this.awardno = awardno == null ? null : awardno.trim();
    }

    public String getAwardname() {
        return awardname;
    }

    public void setAwardname(String awardname) {
        this.awardname = awardname == null ? null : awardname.trim();
    }

    public Double getAwardmoney() {
        return awardmoney;
    }

    public void setAwardmoney(Double awardmoney) {
        this.awardmoney = awardmoney;
    }

    public Integer getAwardquantity() {
        return awardquantity;
    }

    public void setAwardquantity(Integer awardquantity) {
        this.awardquantity = awardquantity;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMademan() {
        return mademan;
    }

    public void setMademan(String mademan) {
        this.mademan = mademan == null ? null : mademan.trim();
    }

    public Date getMadetime() {
        return madetime;
    }

    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getDealman() {
        return dealman;
    }

    public void setDealman(String dealman) {
        this.dealman = dealman == null ? null : dealman.trim();
    }

    public Date getDealtime() {
        return dealtime;
    }

    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	public String getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public ActivityList getAl() {
		return al;
	}

	public void setAl(ActivityList al) {
		this.al = al;
	}

	public UserBaseAccountInfo getUserbai() {
		return userbai;
	}

	public void setUserbai(UserBaseAccountInfo userbai) {
		this.userbai = userbai;
	}

	public String toString() {
		return "ActivityAwardList [id=" + id + ", actid=" + actid + ", baseid=" + baseid + ", username=" + username
				+ ", awardno=" + awardno + ", awardname=" + awardname + ", awardmoney=" + awardmoney
				+ ", awardquantity=" + awardquantity + ", status=" + status + ", mademan=" + mademan + ", madetime="
				+ madetime + ", auditman=" + auditman + ", audittime=" + audittime + ", dealman=" + dealman
				+ ", dealtime=" + dealtime + ", remark=" + remark + ", awardattribute=" + awardattribute
				+ ", adminRemark=" + adminRemark + "]";
	}

}