package com.ptpl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName UserPostAddress
 * @author liuj
 * @Description:TODO(用户奖品邮寄地址实体类)
 * @date:2016/8/19 PM:12:08
 * @version 1.0 
 */
public class UserPostAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	/*主键id*/
    private BigDecimal id;
    /*用户id*/
    private BigDecimal baseid;
    /*奖品编号*/
    private String awardno;
    /*省*/
    private String addressProvince;
    /*市*/
    private String addressCity;
    /*区*/
    private String addressDistrict;
    /*详细地址*/
    private String addressStreet;
    /*邮政编码*/
    private String zipcode;
    /*收货人姓名*/
    private String recipients;
    /*手机号码*/
    private String mobliephone;
    /*电话号码*/
    private String telephone;
    /*备注*/
    private String remark;
    /*默认地址*/
    private BigDecimal isdefaddress;
    
    /*关联用户基本信息*/
    private UserBaseAccountInfo userBaseAccountInfo;

    public BigDecimal getIsdefaddress() {
		return isdefaddress;
	}

	public void setIsdefaddress(BigDecimal isdefaddress) {
		this.isdefaddress = isdefaddress;
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

    public String getAwardno() {
        return awardno;
    }

    public void setAwardno(String awardno) {
        this.awardno = awardno == null ? null : awardno.trim();
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince == null ? null : addressProvince.trim();
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity == null ? null : addressCity.trim();
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict == null ? null : addressDistrict.trim();
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet == null ? null : addressStreet.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    public String getMobliephone() {
        return mobliephone;
    }

    public void setMobliephone(String mobliephone) {
        this.mobliephone = mobliephone == null ? null : mobliephone.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public UserBaseAccountInfo getUserBaseAccountInfo() {
		return userBaseAccountInfo;
	}

	public void setUserBaseAccountInfo(UserBaseAccountInfo userBaseAccountInfo) {
		this.userBaseAccountInfo = userBaseAccountInfo;
	}
}