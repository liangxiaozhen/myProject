package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 用户使用券实体类
 * @author zhenglm
 * 
 */
public class UserInterestRateCoupon {
	/*** 主键ID */
    private BigDecimal id;
    /*** 账户基本信息表ID */
    private BigDecimal baseid;
    /** 关联用户基本信息表 */
    private UserBaseAccountInfo userBaseAccountInfo;
    /** 券编号（对应奖品表编号） */
    private String uircno;
    /** 券的来源(来源，1.注册，2.完善资料，3.首投等) */
    private Short ictype;
    /** 券发放时间 */
    private Date ictime;
    /** 券状态（1入账 2冻结 3解冻） */
    private Short status;
    /** 券利率 */
    private Double icrate;
    /** 券处理时间（入账）*/
    private Date icdealtime;
    /** 券失效时间 */
    private Date icfailtime;
    /** 用途 */
    private String purpose;
    /** 是否审核 */
    private Short isaudit;
    /** 审核人 */
    private String auditman;
    /** 审核时间 */
    private Date audittime;
    /** 备注 */
    private String remark;
    /** 券的类型（1充值抵用，2提现抵用，3加息） */
    private Short uirctype;
    /** 奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）*/
    private Short isuse;
    /** 抵用金额（如果是具体金额,则卷利率为0） */
    private Double vouchercash;
    /** 券发放时间（前台展示） */
    private String ictimeStr;
    /** 券处理时间（入账）（前台展示）*/
    private String icdealtimeStr;
    /** 券失效时间（前台展示）*/
    private String icfailtimeStr;
    /** 审核时间（前台展示）*/
    private String audittimeStr;
    /*** 使用日期 */
    private Date usedate;
    /*** 使用日期（前台显示） */
    private Date usedateStr;
    
    //关联奖品表
    private Award award;
    
    private String cSort;//排序方式

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

	public String getUircno() {
		return uircno;
	}

	public void setUircno(String uircno) {
		this.uircno = uircno;
	}

	public Short getIctype() {
        return ictype;
    }

    public void setIctype(Short ictype) {
        this.ictype = ictype;
    }

    public Date getIctime() {
        return ictime;
    }

    public void setIctime(Date ictime) {
        this.ictime = ictime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Double getIcrate() {
        return icrate;
    }

    public void setIcrate(Double icrate) {
        this.icrate = icrate;
    }

    public Date getIcdealtime() {
        return icdealtime;
    }

    public void setIcdealtime(Date icdealtime) {
        this.icdealtime = icdealtime;
    }

    public Date getIcfailtime() {
        return icfailtime;
    }

    public void setIcfailtime(Date icfailtime) {
        this.icfailtime = icfailtime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
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

	public Short getUirctype() {
		return uirctype;
	}

	public void setUirctype(Short uirctype) {
		this.uirctype = uirctype;
	}

	public Short getIsuse() {
		return isuse;
	}

	public void setIsuse(Short isuse) {
		this.isuse = isuse;
	}

	public Double getVouchercash() {
		return vouchercash;
	}

	public void setVouchercash(Double vouchercash) {
		this.vouchercash = vouchercash;
	}

	public String getIctimeStr() {
		return ictimeStr;
	}

	public void setIctimeStr(String ictimeStr) {
		this.ictimeStr = ictimeStr;
	}

	public String getIcdealtimeStr() {
		return icdealtimeStr;
	}

	public void setIcdealtimeStr(String icdealtimeStr) {
		this.icdealtimeStr = icdealtimeStr;
	}

	public String getIcfailtimeStr() {
		return icfailtimeStr;
	}

	public void setIcfailtimeStr(String icfailtimeStr) {
		this.icfailtimeStr = icfailtimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public Date getUsedate() {
		return usedate;
	}

	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}

	public Date getUsedateStr() {
		return usedateStr;
	}

	public void setUsedateStr(Date usedateStr) {
		this.usedateStr = usedateStr;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public String getcSort() {
		return cSort;
	}

	public void setcSort(String cSort) {
		this.cSort = cSort;
	}

	@Override
	public String toString() {
		return "UserInterestRateCoupon [id=" + id + ", baseid=" + baseid + ", userBaseAccountInfo="
				+ userBaseAccountInfo + ", uircno=" + uircno + ", ictype=" + ictype + ", ictime=" + ictime + ", status="
				+ status + ", icrate=" + icrate + ", icdealtime=" + icdealtime + ", icfailtime=" + icfailtime
				+ ", purpose=" + purpose + ", isaudit=" + isaudit + ", auditman=" + auditman + ", audittime="
				+ audittime + ", remark=" + remark + ", uirctype=" + uirctype + ", isuse=" + isuse + ", vouchercash="
				+ vouchercash + ", ictimeStr=" + ictimeStr + ", icdealtimeStr=" + icdealtimeStr + ", icfailtimeStr="
				+ icfailtimeStr + ", audittimeStr=" + audittimeStr + ", usedate=" + usedate + ", usedateStr="
				+ usedateStr + "]";
	}
	
	
	
}