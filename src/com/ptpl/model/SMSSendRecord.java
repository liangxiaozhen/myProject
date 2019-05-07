package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: SMSSendRecord 
* @Package com.ptpl.model 
* @Description: TODO(短信发送记录 实体类) 
* @author chenjiaming
* @date 2016年8月24日 下午2:20:14 
* @version V1.0
 */
public class SMSSendRecord {
	/*主键ID*/
    private BigDecimal id;
    /*用户ID*/
    private BigDecimal baseid;
    /*电话号码*/
    private String mobile;
    /*验证码*/
    private String vercode;
    /*发送时间*/
    private Date sendtime;
    /*短信内容*/
    private String smscontent;
    /*发送方式（1手工2系统）*/
    private Short sendtype;
    /*补发时间*/
    private Date reissuetime;
    /*备注*/
    private String remark;
    /*用户名*/
    private String username;
    /*状态  1成功 0失败*/
    private Short status;
    /*短信通道公司*/
    private String smsccompany;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode == null ? null : vercode.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent == null ? null : smscontent.trim();
    }

    public Short getSendtype() {
        return sendtype;
    }

    public void setSendtype(Short sendtype) {
        this.sendtype = sendtype;
    }

    public Date getReissuetime() {
        return reissuetime;
    }

    public void setReissuetime(Date reissuetime) {
        this.reissuetime = reissuetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getSmsccompany() {
		return smsccompany;
	}

	public void setSmsccompany(String smsccompany) {
		this.smsccompany = smsccompany;
	}
}