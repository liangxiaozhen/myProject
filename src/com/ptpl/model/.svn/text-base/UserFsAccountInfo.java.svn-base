package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: UserFsAccountInfo 
* @Package com.ptpl.model 
* @Description: TODO(用户托管账户信息 实体类) 
* @author chenjiaming
* @date 2016年8月10日 下午12:14:16 
* @version V1.0
 */
public class UserFsAccountInfo {
	/*主键ID*/
    private BigDecimal id;
    /*用户ID*/
    private BigDecimal baseid;
    /*用户商户号  乾多多版本 是多多号   徽商银行 是电子账户*/
    private String usrcustid;
    /*商户号  乾多多版本 是平台标识号 徽商银行 是合作单位编号*/
    private String mercustid;
    /*交易唯一标识*/
    private String trxid;
    /*应答返回码*/
    private String respcode;
    /*应答描述*/
    private String respdesc;
    /*托管通道标识 如汇付天下 宝付*/
    private String channelidentifier;
    /*是否开通托管账户  1已开通 0未开通*/
    private Short isopenfsinfo;
    /*开通时间*/
    private Date openingtime;
    /*备注*/
    private String remark;
    /*用户登录账户（汇付接口 前缀 mykj_）*/
    private String usrloginname;
    /*用户真实名称*/
    private String usrname;
    /*账户类型（1个人，2企业）*/
    private Short  accounttype;
    /*用户的乾多多标识*/
    private String moneymoremoreid;
    /*姓名匹配手续费*/
    private Double authfee;
    /*实名认证状态 1.未实名认证  2.快捷支付认证 3.其他认证*/
    private Short authstate;
    /*随机时间戳*/
    private String randomtimestamp;
     /*开户方式  1半自动    2全自动*/
    private Short registertype;
    /*实名认证次数*/
    private Integer authtimes;
    /*托管手机号*/
    private String fsmobile;
    /*是否设置交易密码 0 未设置 1 已设置*/
    private  Short tradepass;
    
    private Short accPurpose;//账户用途（1普通，2红包，3手续费）
    
	public Short getAccPurpose() {
		return accPurpose;
	}

	public void setAccPurpose(Short accPurpose) {
		this.accPurpose = accPurpose;
	}

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

    public String getUsrcustid() {
        return usrcustid;
    }

    public void setUsrcustid(String usrcustid) {
        this.usrcustid = usrcustid == null ? null : usrcustid.trim();
    }

    public String getMercustid() {
        return mercustid;
    }

    public void setMercustid(String mercustid) {
        this.mercustid = mercustid == null ? null : mercustid.trim();
    }

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid == null ? null : trxid.trim();
    }

    public String getRespcode() {
        return respcode;
    }

    public void setRespcode(String respcode) {
        this.respcode = respcode == null ? null : respcode.trim();
    }

    public String getRespdesc() {
        return respdesc;
    }

    public void setRespdesc(String respdesc) {
        this.respdesc = respdesc == null ? null : respdesc.trim();
    }

    public String getChannelidentifier() {
        return channelidentifier;
    }

    public void setChannelidentifier(String channelidentifier) {
        this.channelidentifier = channelidentifier == null ? null : channelidentifier.trim();
    }

    public Short getIsopenfsinfo() {
        return isopenfsinfo;
    }

    public void setIsopenfsinfo(Short isopenfsinfo) {
        this.isopenfsinfo = isopenfsinfo;
    }

    public Date getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(Date openingtime) {
        this.openingtime = openingtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUsrloginname() {
		return usrloginname;
	}

	public void setUsrloginname(String usrloginname) {
		this.usrloginname = usrloginname;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public Short getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Short accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public String toString() {
		return "UserFsAccountInfo [id=" + id + ", baseid=" + baseid + ", usrcustid=" + usrcustid + ", mercustid="
				+ mercustid + ", trxid=" + trxid + ", respcode=" + respcode + ", respdesc=" + respdesc
				+ ", channelidentifier=" + channelidentifier + ", isopenfsinfo=" + isopenfsinfo + ", openingtime="
				+ openingtime + ", remark=" + remark + ", usrloginname=" + usrloginname + ", usrname=" + usrname
				+ ", accounttype=" + accounttype + "]";
	}

	public String getMoneymoremoreid() {
		return moneymoremoreid;
	}

	public void setMoneymoremoreid(String moneymoremoreid) {
		this.moneymoremoreid = moneymoremoreid;
	}

	public Double getAuthfee() {
		return authfee;
	}

	public void setAuthfee(Double authfee) {
		this.authfee = authfee;
	}

	public Short getAuthstate() {
		return authstate;
	}

	public void setAuthstate(Short authstate) {
		this.authstate = authstate;
	}

	public String getRandomtimestamp() {
		return randomtimestamp;
	}

	public void setRandomtimestamp(String randomtimestamp) {
		this.randomtimestamp = randomtimestamp;
	}

	public Short getRegistertype() {
		return registertype;
	}

	public void setRegistertype(Short registertype) {
		this.registertype = registertype;
	}

	public Integer getAuthtimes() {
		return authtimes;
	}

	public void setAuthtimes(Integer authtimes) {
		this.authtimes = authtimes;
	}

	public String getFsmobile() {
		return fsmobile;
	}

	public void setFsmobile(String fsmobile) {
		this.fsmobile = fsmobile;
	}

	public Short getTradepass() {
		return tradepass;
	}

	public void setTradepass(Short tradepass) {
		this.tradepass = tradepass;
	}
    
	
    
}