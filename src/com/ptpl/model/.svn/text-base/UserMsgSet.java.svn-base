package com.ptpl.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户消息通知设置
 * Parameter:
 */
public class UserMsgSet {
    private List<UserMsgSet> userMsgSetList=new ArrayList<UserMsgSet>() ;//投资人
    public List<UserMsgSet> getUserMsgSetList() {
        return userMsgSetList;
    }

    public void setUserMsgSetList(List<UserMsgSet> userMsgSetList) {
        this.userMsgSetList = userMsgSetList;
    }

    private BigDecimal id;      //主键

    private BigDecimal baseid; //用户ID

    private Short msgtype;      //消息类型            1投资人  2借款人

    private String item;        //项目:               投标 回款 流标

    private Short intmsg;       //站内信（系统发送）  0关闭  1启用

    private Short sms;          //短信                0关闭  1启用

        private Short email;        //邮件                0关闭  1启用

    private Short app;          //手机APP             0关闭  1启用

    private Short wechat;       //微信服务号          0关闭  1启用

    private Date modifytime;    //修改时间:记录最后一次修改时间  记录日志 包括各个项目的关闭或启用状态

    private String remark;      //备注

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

    public Short getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Short msgtype) {
        this.msgtype = msgtype;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Short getIntmsg() {
        return intmsg;
    }

    public void setIntmsg(Short intmsg) {
        this.intmsg = intmsg;
    }

    public Short getSms() {
        return sms;
    }

    public void setSms(Short sms) {
        this.sms = sms;
    }

    public Short getEmail() {
        return email;
    }

    public void setEmail(Short email) {
        this.email = email;
    }

    public Short getApp() {
        return app;
    }

    public void setApp(Short app) {
        this.app = app;
    }

    public Short getWechat() {
        return wechat;
    }

    public void setWechat(Short wechat) {
        this.wechat = wechat;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "UserMsgSet{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", msgtype=" + msgtype +
                ", item='" + item + '\'' +
                ", intmsg=" + intmsg +
                ", sms=" + sms +
                ", email=" + email +
                ", app=" + app +
                ", wechat=" + wechat +
                ", modifytime=" + modifytime +
                ", remark='" + remark + '\'' +
                '}';
    }
}