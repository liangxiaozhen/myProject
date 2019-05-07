package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户积分实体类
 * @author zhenglm
 *
 */
public class UserBonusPoints {
	/* 主键ID */
    private BigDecimal id;
    /* 账户基本信息表ID */
    private BigDecimal baseid;
    /* 关联用户基本信息表 */
    private UserBaseAccountInfo userBaseAccountInfo;
    /* 积分编号（对应奖品表编号） */
    private String ubpno;
    /* 积分来源(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖) */
    private Short bptype;
    /* 积分发放时间 */
    private Date bpstime;
    /* 积分 */
    private Long bonuspoints;
    /* 积分状态（1待审核   2待处理  3已领取  4待确认  5已经确认  6发货中  7领取失败  ） */
    private Short status;
    /* 用途 */
    private String purpose;
    /* 积分处理时间（入账） */
    private Date bpdealtime;
    /* 是否审核 */
    private Short isaudit;
    /* 审核人 */
    private String auditman;
    /* 审核时间 */
    private Date audittime;
    /* 备注 */
    private String remark;
    /* 积分发放时间（前台展示） */
    private String bpstimeStr;
    /* 积分处理时间（入账）（前台展示） */
    private String bpdealtimeStr;
    /*（审核时间前台展示） */
    private String audittimeStr;
    
    private Award award;//奖品
    
    private Short bpUseType;//积分类型   4交易积分 5系统积分 
    
    private String period;//时间段

	private String startdate;//开始时间
	
	private String enddate;//结束时间
    
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public UserBaseAccountInfo getUserBaseAccountInfo() {
		return userBaseAccountInfo;
	}

	public void setUserBaseAccountInfo(UserBaseAccountInfo userBaseAccountInfo) {
		this.userBaseAccountInfo = userBaseAccountInfo;
	}

	public String getUbpno() {
		return ubpno;
	}

	public void setUbpno(String ubpno) {
		this.ubpno = ubpno;
	}

	public Short getBptype() {
        return bptype;
    }

    public void setBptype(Short bptype) {
        this.bptype = bptype;
    }

    public Date getBpstime() {
        return bpstime;
    }

    public void setBpstime(Date bpstime) {
        this.bpstime = bpstime;
    }

    public Long getBonuspoints() {
        return bonuspoints;
    }

    public void setBonuspoints(Long bonuspoints) {
        this.bonuspoints = bonuspoints;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public Date getBpdealtime() {
        return bpdealtime;
    }

    public void setBpdealtime(Date bpdealtime) {
        this.bpdealtime = bpdealtime;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getBpstimeStr() {
		return bpstimeStr;
	}

	public void setBpstimeStr(String bpstimeStr) {
		this.bpstimeStr = bpstimeStr;
	}

	public String getBpdealtimeStr() {
		return bpdealtimeStr;
	}

	public void setBpdealtimeStr(String bpdealtimeStr) {
		this.bpdealtimeStr = bpdealtimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Short getBpUseType() {
		return bpUseType;
	}

	public void setBpUseType(Short bpUseType) {
		this.bpUseType = bpUseType;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
}