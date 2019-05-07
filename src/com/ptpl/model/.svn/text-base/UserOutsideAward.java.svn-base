package com.ptpl.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:userOutsideAward
 * @Description:TODO(用户站外奖品实体类)
 * @author liuj
 * @date:2016/8/19 AM:11:36
 * @version 1.0
 */
public class UserOutsideAward implements Serializable{
	private static final long serialVersionUID = 1L;
	/*主键id*/
    private BigDecimal id;
    /*用户id*/
    private BigDecimal baseid;
    /*站外奖品编号*/
    private String uoawardno;
    /*站外奖品名称*/
    private String uoawardname;
    /*奖品来源类型(1.注册，2.完善资料，3.首投，4.累投，5.手动颁奖等)*/
    private Short uoatype;
    /*奖品发放时间*/
    private Date uoatime;
    /*奖品状态(1.入账 2.冻结  3.解冻)*/
    private Short status;
    /*是否发放(0.未发  1.已发)*/
    private Short issend;
    /*发放编号(虚拟)/快递编号(实物)*/
    private String sendno;
    /*是否使用(0.未用 1.使用)*/
    private Short isuse;
    /*是否审核(0.未审核 1.已审核)*/
    private Short isaudit;
    /*审核人*/
    private String auditman;
    /*审核时间*/
    private Date audittime;
    /*备注*/
    private String remark;
    
    private String uoatimestr;
    
    private String audittimestr;
    /*关联用户表*/
    private UserBaseAccountInfo userBaseAccountInfo;
    
	public UserBaseAccountInfo getUserBaseAccountInfo() {
		return userBaseAccountInfo;
	}

	public void setUserBaseAccountInfo(UserBaseAccountInfo userBaseAccountInfo) {
		this.userBaseAccountInfo = userBaseAccountInfo;
	}

	public String getUoatimestr() {
		return uoatimestr;
	}

	public void setUoatimestr(String uoatimestr) {
		this.uoatimestr = uoatimestr;
	}

	public String getAudittimestr() {
		return audittimestr;
	}

	public void setAudittimestr(String audittimestr) {
		this.audittimestr = audittimestr;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUoawardno() {
        return uoawardno;
    }

    public void setUoawardno(String uoawardno) {
        this.uoawardno = uoawardno == null ? null : uoawardno.trim();
    }

    public String getUoawardname() {
        return uoawardname;
    }

    public void setUoawardname(String uoawardname) {
        this.uoawardname = uoawardname == null ? null : uoawardname.trim();
    }

    public Short getUoatype() {
        return uoatype;
    }

    public void setUoatype(Short uoatype) {
        this.uoatype = uoatype;
    }

    public Date getUoatime() {
        return uoatime;
    }

    public void setUoatime(Date uoatime) {
        this.uoatime = uoatime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getIssend() {
        return issend;
    }

    public void setIssend(Short issend) {
        this.issend = issend;
    }

    public String getSendno() {
        return sendno;
    }

    public void setSendno(String sendno) {
        this.sendno = sendno == null ? null : sendno.trim();
    }

    public Short getIsuse() {
        return isuse;
    }

    public void setIsuse(Short isuse) {
        this.isuse = isuse;
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

	public BigDecimal getBaseid() {
		return baseid;
	}

	public void setBaseid(BigDecimal baseid) {
		this.baseid = baseid;
	}
}