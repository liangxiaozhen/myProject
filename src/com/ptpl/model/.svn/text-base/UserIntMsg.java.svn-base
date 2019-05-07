package com.ptpl.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户站内消息
 * Parameter:
 */
public class UserIntMsg {
    private BigDecimal id;      //主键

    private BigDecimal baseid;  //用户ID

    private Short msgtype;      //消息类型: 1系统  2投资  3借款  4奖品  5活动

    private String msgtitle;    //消息标题

    private String msgcontent;  //消息内容

    private Date msgtime;       //消息时间
    private String msgtimeString;  //消息时间字符串
    private Short isread;       //是否可读: 0未读  1已读

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

    public String getMsgtitle() {
        return msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle == null ? null : msgtitle.trim();
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public Date getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(Date msgtime) {
        this.msgtime = msgtime;
    }

    public Short getIsread() {
        return isread;
    }

    public void setIsread(Short isread) {
        this.isread = isread;
    }

    public String getMsgtimeString() {
        return msgtimeString;
    }

    public void setMsgtimeString(Date msgtime) {
        this.msgtimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(msgtime);;
    }
}