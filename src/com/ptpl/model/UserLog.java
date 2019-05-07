package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: UserLog 
* @Package com.ptpl.model 
* @Description: TODO(用户 日志 实体类 ) 
* @author chenjiaming
* @date 2016年7月15日 上午11:40:54 
* @version V1.0
 */
public class UserLog {
	/*ID（主键）*/
    private BigDecimal id;
    /*用户Id*/
    private BigDecimal baseid;
    /*用户名*/
    private String username;
    /*用户类型 1普通用户  2管理员用户*/
    private Short usertype;
    /*IP*/
    private String ip;
    /*cookie*/
    private String cookie;
    /*登录时间*/
    private Date logintime;
    /*操作内容（涉及修改）*/
    private String opercontent;
    /*操作时间*/
    private Date opertime;
    /*备注*/
    private String remark;
    
    private String logintimestr;
    private String opertimestr;
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

    public Short getUsertype() {
        return usertype;
    }

    public void setUsertype(Short usertype) {
        this.usertype = usertype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getOpercontent() {
        return opercontent;
    }

    public void setOpercontent(String opercontent) {
        this.opercontent = opercontent == null ? null : opercontent.trim();
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getLogintimestr() {
		return logintimestr;
	}

	public void setLogintimestr(String logintimestr) {
		this.logintimestr = logintimestr;
	}

	public String getOpertimestr() {
		return opertimestr;
	}

	public void setOpertimestr(String opertimestr) {
		this.opertimestr = opertimestr;
	}
    
    
}