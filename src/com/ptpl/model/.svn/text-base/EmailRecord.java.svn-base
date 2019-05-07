package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
* @ClassName: EmailRecord 
* @Package com.ptpl.model 
* @Description: TODO(邮件发送记录实体类) 
* @author chenjiaming
* @date 2016年8月23日 下午5:00:23 
* @version V1.0
 */
public class EmailRecord {
	/*主键ID*/
    private BigDecimal id;
    /*用户ID*/
    private BigDecimal baseid;
    /*用户名*/
    private String username;
    /*发送email*/
    private String email;
    /*发送时间*/
    private Date sendtime;
    /*发送的email 内容*/
    private String emailcontent;
    /*发送方式（1手工,2系统）*/
    private Short sendtype;
    /*补发时间*/
    private Date reissuetime;
    /*备注*/
    private String remark;
    /*邮件补发类型 1绑定邮箱验证链接 2邮箱重置验证链接 3重置验证链接  4普通邮箱验证码 5邮件验证重置密码*/
    private Short messagetype;  
    /*发送端email*/
    private String sendemail;	
    /*发送状态 （1成功 0失败）*/
    private Short status;
      
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getEmailcontent() {
        return emailcontent;
    }

    public void setEmailcontent(String emailcontent) {
        this.emailcontent = emailcontent == null ? null : emailcontent.trim();
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

	public Short getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(Short messagetype) {
		this.messagetype = messagetype;
	}

	public String getSendemail() {
		return sendemail;
	}

	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	 
    
    
  }