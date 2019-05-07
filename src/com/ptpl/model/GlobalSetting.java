package com.ptpl.model;

import java.math.BigDecimal;
public class GlobalSetting {

    //全局表Id
    private BigDecimal id;

    //网站名称
    private String websitename;

    //网站抬头
    private String title;

    //网站关键词
    private String keyworld;

    //网站描述
    private String description;

    //开关
    private String strstatus;

    //年率天数(360/365)
    private Integer annualrate;

    //实名认证次数
    private Integer authtimes;

    //干将识别码
    private String gjidcode;

    //业务编号前缀
    private String presetstr;

    //应急改密方式设置
    private Integer urgentmethod;

    //全局验证验证码
    private Integer globalverifycode;

    //会员升级方向       
    private Integer upgrade;
    /*系统自动提交还款次数限制 借款人设置了自动还款且超过了具体次数后，转为调用代偿人账户还款*/
    private Integer autorptimesltd;
    /*系统自动提交还款提交间隔（单位 分钟）*/
    private Integer autorpstinvl;

    /*徽商银行平台红包账户lxf*/
    private String pREAccount;

    /*徽商银行平台手续费账户lxf*/
    private String pFeeAccount;

    /*新手投标判定规则lxf*/
    private Integer newerBidRule;

    /*新手投标次数限制lxf*/
    private Integer newerBidCount;

    /*新手累投金额限制lxf*/
    private Float newerBidAmount;

    /*新手投标注册时间天数限制lxf*/
    private Integer newerBidDayLimit;

    /*流标缓冲时间单位分钟*/
    private Integer failTTime;

    public Integer getFailTTime() {
        return failTTime;
    }

    public void setFailTTime(Integer failTTime) {
        this.failTTime = failTTime;
    }

    public String getpREAccount() {
        return pREAccount;
    }

    public void setpREAccount(String pREAccount) {
        this.pREAccount = pREAccount;
    }

    public String getpFeeAccount() {
        return pFeeAccount;
    }

    public void setpFeeAccount(String pFeeAccount) {
        this.pFeeAccount = pFeeAccount;
    }

    public Integer getNewerBidRule() {
        return newerBidRule;
    }

    public void setNewerBidRule(Integer newerBidRule) {
        this.newerBidRule = newerBidRule;
    }

    public Integer getNewerBidCount() {
        return newerBidCount;
    }

    public void setNewerBidCount(Integer newerBidCount) {
        this.newerBidCount = newerBidCount;
    }

    public Float getNewerBidAmount() {
        return newerBidAmount;
    }

    public void setNewerBidAmount(Float newerBidAmount) {
        this.newerBidAmount = newerBidAmount;
    }

    public Integer getNewerBidDayLimit() {
        return newerBidDayLimit;
    }

    public void setNewerBidDayLimit(Integer newerBidDayLimit) {
        this.newerBidDayLimit = newerBidDayLimit;
    }
    public String getPresetstr() {
        return presetstr;
    }

    public void setPresetstr(String presetstr) {
        this.presetstr = presetstr;
    }

    public Integer getAuthtimes() {
        return authtimes;
    }

    public void setAuthtimes(Integer authtimes) {
        this.authtimes = authtimes;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename == null ? null : websitename.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeyworld() {
        return keyworld;
    }

    public void setKeyworld(String keyworld) {
        this.keyworld = keyworld == null ? null : keyworld.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStrstatus() {
        return strstatus;
    }

    public void setStrstatus(String strstatus) {
        this.strstatus = strstatus == null ? null : strstatus.trim();
    }

    public Integer getAnnualrate() {
        return annualrate;
    }

    public void setAnnualrate(Integer annualrate) {
        this.annualrate = annualrate;
    }

    public Integer getAutorptimesltd() {
        return autorptimesltd;
    }

    public void setAutorptimesltd(Integer autorptimesltd) {
        this.autorptimesltd = autorptimesltd;
    }

    public Integer getAutorpstinvl() {
        return autorpstinvl;
    }

    public void setAutorpstinvl(Integer autorpstinvl) {
        this.autorpstinvl = autorpstinvl;
    }

    public String getGjidcode() {
        return gjidcode;
    }

    public void setGjidcode(String gjidcode) {
        this.gjidcode = gjidcode;
    }

    public Integer getUrgentmethod() {
        return urgentmethod;
    }

    public void setUrgentmethod(Integer urgentmethod) {
        this.urgentmethod = urgentmethod;
    }

    public Integer getGlobalverifycode() {
        return globalverifycode;
    }

    public void setGlobalverifycode(Integer globalverifycode) {
        this.globalverifycode = globalverifycode;
    }

    public Integer getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Integer upgrade) {
        this.upgrade = upgrade;
    }


}