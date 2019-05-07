package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 
* @ClassName: UserBaseAccountInfo 
* @Package com.ptpl.model 
* @Description: TODO(用户基本信息 实体类) 
* @author chenjiaming
* @date 2016年7月14日 下午2:24:53 
* @version V1.0
 */
public class UserBaseAccountInfo {
	/*ID主键*/
    private BigDecimal id;
    /*头像图片路径*/
    private String imagepath;
    /*用户ID(6-8位数字）*/
    private String accountnumber;
    /*用户email地址（唯一）*/
    private String email;
    /*手机号：（唯一）*/
    private String mobilephone;
    /*用户类型：（个人、企业）1个人 2企业*/
    private Short accounttype;
    /*证件类型  01-身份证18位
			02-身份证15位
			20-其它
			25-企业社会信用代码
			注：企业开户时上送20或社会信用号25*/
    private String certificationtype;
    /*证件号码*/
    private String certificationnumber;
    /*用户名称（真实姓名）*/
    private String realname;
    /*联系电话：（非必须）*/
    private String telephone;
    /*登录方式（可选邮件、手机、全部）*/
    private Short logintype;
    /*注册类型：1.用户名注册 2.用户名+手机号注册 3.手机号注册 4邮箱注册 5第三方注册*/
    private Short regtype;
    /*注册日期：*/
    private Date regdate;
    /*注册IP*/
    private String regip;
    /*注册cookie*/
    private String regcookie;
    /*验证日期（邮件）*/
    private Date emailverifydate;
    /*验证日期（手机）*/
    private Date mobileverifydate;
    /*手机验证 1已验证 0未验证*/
    private Short ismobileverify;
    /*邮件验证 1已验证 0未验证*/
    private Short isemailverify;
    /*用户状态：（正常1、审核中2、停用0）*/
    private Short status;
    /*是否激活 1已激活 0未激活*/
    private Short isactive;
    /*是否实名认证: 1已认证 0 未认证*/
    private Short isreally;
    /*职业*/
    private String profession;
    /*省份*/
    private String addressProvince;
    /*市*/
    private String addressCity;
    /*区*/
    private String addressDistrict;
    /*详细地址*/
    private String addressStreet;
    /*推荐人*/
    private String referralinfo;
    /*备注*/
    private String remark;
    /*来源（pc 1,安卓 2，ios 3）*/
    private Short originclient;
    /*用户登录名*/
    private String loginname;
    /*本人推荐号*/
    private String referralselfno;
    /*推荐号*/
    private String referralno;
    /*性别 1男0女*/
    private Short sex;
    /*前台显示电话 号码 132****1236*/
    private String mobilephonestr;
    /*前台显示 ss*************/
    private String loingNameStr;
    /*前台显示 加密后的身份号码*/
    private String certificationnumberstr;
    /*前台显示 *************qq.com*/
    private String emailstr;
    /** 是否可以假投标 */
    private Short iscancheattender;
    /*关联实体类*/
    private UserFsAccountInfo userfsaccountinfo;
    private Date startregdate;//用于模糊搜索查询
    private Date endregdate;//用于模糊搜索查询
    
    //关联信息安全表
    private UserAccountSafeInfo uasi;
    
    private Short isopenfsinfo;//是否开通托管  数据库无此字段
    private Short tradepass;//是否设置交易密码 数据库无此字段

    public UserBaseAccountInfo(){
    	
    }
    
    public UserBaseAccountInfo (String certificationnumber,String realname){
    	this.certificationnumber = certificationnumber;
    	this.realname = realname;
    }
    
	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
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

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    public String getCertificationtype() {
        return certificationtype;
    }

    public void setCertificationtype(String certificationtype) {
        this.certificationtype = certificationtype;
    }

    public String getCertificationnumber() {
        return certificationnumber;
    }

    public void setCertificationnumber(String certificationnumber) {
        this.certificationnumber = certificationnumber == null ? null : certificationnumber.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Short getLogintype() {
        return logintype;
    }

    public void setLogintype(Short logintype) {
        this.logintype = logintype;
    }

    public Short getRegtype() {
        return regtype;
    }

    public void setRegtype(Short regtype) {
        this.regtype = regtype;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getEmailverifydate() {
        return emailverifydate;
    }

    public void setEmailverifydate(Date emailverifydate) {
        this.emailverifydate = emailverifydate;
    }

    public Date getMobileverifydate() {
        return mobileverifydate;
    }

    public void setMobileverifydate(Date mobileverifydate) {
        this.mobileverifydate = mobileverifydate;
    }

    public Short getIsmobileverify() {
        return ismobileverify;
    }

    public void setIsmobileverify(Short ismobileverify) {
        this.ismobileverify = ismobileverify;
    }

    public Short getIsemailverify() {
        return isemailverify;
    }

    public void setIsemailverify(Short isemailverify) {
        this.isemailverify = isemailverify;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getIsactive() {
        return isactive;
    }

    public void setIsactive(Short isactive) {
        this.isactive = isactive;
    }

    public Short getIsreally() {
        return isreally;
    }

    public void setIsreally(Short isreally) {
        this.isreally = isreally;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet == null ? null : addressStreet.trim();
    }

    public String getReferralinfo() {
        return referralinfo;
    }

    public void setReferralinfo(String referralinfo) {
        this.referralinfo = referralinfo == null ? null : referralinfo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getOriginclient() {
        return originclient;
    }

    public void setOriginclient(Short originclient) {
        this.originclient = originclient;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getReferralselfno() {
        return referralselfno;
    }

    public void setReferralselfno(String referralselfno) {
        this.referralselfno = referralselfno == null ? null : referralselfno.trim();
    }

    public String getReferralno() {
        return referralno;
    }

    public void setReferralno(String referralno) {
        this.referralno = referralno == null ? null : referralno.trim();
    }

	public String getMobilephonestr() {
		return mobilephonestr;
	}

	public void setMobilephonestr(String mobilephonestr) {
		this.mobilephonestr = mobilephonestr;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public String getRegcookie() {
		return regcookie;
	}

	public void setRegcookie(String regcookie) {
		this.regcookie = regcookie;
	}

	public String getLoingNameStr() {
		return loingNameStr;
	}

	public void setLoingNameStr(String loingNameStr) {
		this.loingNameStr = loingNameStr;
	}

	public String getEmailstr() {
		return emailstr;
	}

	public void setEmailstr(String emailstr) {
		this.emailstr = emailstr;
	}

	public UserFsAccountInfo getUserfsaccountinfo() {
		return userfsaccountinfo;
	}

	public void setUserfsaccountinfo(UserFsAccountInfo userfsaccountinfo) {
		this.userfsaccountinfo = userfsaccountinfo;
	}

	public Date getStartregdate() {
		return startregdate;
	}

	public void setStartregdate(Date startregdate) {
		this.startregdate = startregdate;
	}

	public Date getEndregdate() {
		return endregdate;
	}

	public void setEndregdate(Date endregdate) {
		this.endregdate = endregdate;
	}

	public Short getIscancheattender() {
		return iscancheattender;
	}

	public void setIscancheattender(Short iscancheattender) {
		this.iscancheattender = iscancheattender;
	}
	
	

	public String getCertificationnumberstr() {
		return certificationnumberstr;
	}

	public void setCertificationnumberstr(String certificationnumberstr) {
		this.certificationnumberstr = certificationnumberstr;
	}
	
	

	 

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}
	
	

	public Short getIsopenfsinfo() {
		return isopenfsinfo;
	}

	public void setIsopenfsinfo(Short isopenfsinfo) {
		this.isopenfsinfo = isopenfsinfo;
	}

	public Short getTradepass() {
		return tradepass;
	}

	public void setTradepass(Short tradepass) {
		this.tradepass = tradepass;
	}

	public UserAccountSafeInfo getUasi() {
		return uasi;
	}

	public void setUasi(UserAccountSafeInfo uasi) {
		this.uasi = uasi;
	}

	@Override
	public String toString() {
		return "UserBaseAccountInfo [id=" + id + ", imagepath=" + imagepath + ", accountnumber=" + accountnumber
				+ ", email=" + email + ", mobilephone=" + mobilephone + ", accounttype=" + accounttype
				+ ", certificationtype=" + certificationtype + ", certificationnumber=" + certificationnumber
				+ ", realname=" + realname + ", telephone=" + telephone + ", logintype=" + logintype + ", regtype="
				+ regtype + ", regdate=" + regdate + ", regip=" + regip + ", regcookie=" + regcookie
				+ ", emailverifydate=" + emailverifydate + ", mobileverifydate=" + mobileverifydate
				+ ", ismobileverify=" + ismobileverify + ", isemailverify=" + isemailverify + ", status=" + status
				+ ", isactive=" + isactive + ", isreally=" + isreally + ", profession=" + profession
				+ ", addressProvince=" + addressProvince + ", addressCity=" + addressCity + ", addressDistrict="
				+ addressDistrict + ", addressStreet=" + addressStreet + ", referralinfo=" + referralinfo + ", remark="
				+ remark + ", originclient=" + originclient + ", loginname=" + loginname + ", referralselfno="
				+ referralselfno + ", referralno=" + referralno + ", mobilephonestr=" + mobilephonestr
				+ ", loingNameStr=" + loingNameStr + ", emailstr=" + emailstr + ", iscancheattender=" + iscancheattender
				+ ", userfsaccountinfo=" + userfsaccountinfo + ", startregdate=" + startregdate + ", endregdate="
				+ endregdate + "]";
	}

}