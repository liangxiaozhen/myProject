package com.ptpl.model;
/**
 * 系统管理员 实体类
 * 
 */
import java.math.BigDecimal;
import java.util.Date;

public class AdminUser {
	/*主键ID 用户Id*/
    private BigDecimal id;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*全名*/
    private String fullname;
    /*邮箱地址*/
    private String email;
    /*电话*/
    private String mobilephone;
    /*登录错误次数*/
    private Integer errorcount;
    /*最后登录时间*/
    private Date logintime;
    /*最后修改时间*/
    private Date modifypwdtime;
    /*备注*/
    private String remark;
    /*禁止登录0未禁止1禁止*/
    private String forbid;
    /*注册时间*/
    private Date regdate;
    /*返回前台的登录时间*/
    private String logintimestr;
    /*返回前台的注册时间*/
    private String regdatestr;
    /*对应的角色 用户与角色的关系是 一个用户只有一个角色 */
    private SystemRole systemRole;
 
     public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public Integer getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(Integer errorcount) {
        this.errorcount = errorcount;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getModifypwdtime() {
        return modifypwdtime;
    }

    public void setModifypwdtime(Date modifypwdtime) {
        this.modifypwdtime = modifypwdtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getForbid() {
		return forbid;
	}

	public void setForbid(String forbid) {
		this.forbid = forbid ;
	}

	public String getLogintimestr() {
		return logintimestr;
	}

	public void setLogintimestr(String logintimestr) {
		this.logintimestr = logintimestr;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRegdatestr() {
		return regdatestr;
	}

	public void setRegdatestr(String regdatestr) {
		this.regdatestr = regdatestr;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}
    
     
}