package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: UserRisk 
* @Package com.ptpl.model 
* @Description: TODO(用户风控 实体类) 
* @author chenjiaming
* @date 2016年9月5日 下午3:31:30 
* @version V1.0
 */
public class UserRisk {
	/*主键ID*/
    private BigDecimal id;
    /*用户Id*/
    private BigDecimal baseid;
    /*用户名*/
    private String username;
    /*IP*/
    private String ip;
    /*cookie*/
    private String cookie;
    /*Mac地址*/
    private String mac;
    /*手机*/
    private String mobile;
    /*邮箱*/
    private String email;
    /*类型(1白名单,2黑名单,3应急改密名单,4风险名单)*/
    private Short type;
    /*添加人*/
    private String addman;
    /*添加时间*/
    private Date addtime;
    /*备注*/
    private String remark;
    /*会员等级*/
    private String grade;
    /*手机唯一标识*/
    private String mobileudid;
    /*是否取消验证码（0.否 1.是）*/
    private Short iscancelvcode;
    
    private String list;

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "UserRisk{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", cookie='" + cookie + '\'' +
                ", mac='" + mac + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", addman='" + addman + '\'' +
                ", addtime=" + addtime +
                ", remark='" + remark + '\'' +
                ", grade='" + grade + '\'' +
                ", mobileudid='" + mobileudid + '\'' +
                ", iscancelvcode=" + iscancelvcode +
                ", list='" + list + '\'' +
                '}';
    }
}