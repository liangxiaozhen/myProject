package com.ptpl.model;

import java.math.BigDecimal;
/**
 * 用户推广第三方公司设置
 * @author xiaoy
 *
 * @date 2016年9月30日 上午9:30:44
 */
public class UserPromoThirdParty {
	//ID
    private BigDecimal id;
    //用户推广设置ID
    private BigDecimal upid;
    //第三方公司固定编码
    private String thirdpartycode;
    //第三方公司名称
    private String thirdpartyname;
    //推广开关
    private Short isopen;
    //备注
    private String remark;
    //用户基本信息
    private UserBaseAccountInfo ubai=new UserBaseAccountInfo();
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUpid() {
        return upid;
    }

    public void setUpid(BigDecimal upid) {
        this.upid = upid;
    }

    public String getThirdpartycode() {
        return thirdpartycode;
    }

    public void setThirdpartycode(String thirdpartycode) {
        this.thirdpartycode = thirdpartycode == null ? null : thirdpartycode.trim();
    }

    public String getThirdpartyname() {
        return thirdpartyname;
    }

    public void setThirdpartyname(String thirdpartyname) {
        this.thirdpartyname = thirdpartyname == null ? null : thirdpartyname.trim();
    }

    public Short getIsopen() {
        return isopen;
    }

    public void setIsopen(Short isopen) {
        this.isopen = isopen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public UserBaseAccountInfo getUbai()
	{
		return ubai;
	}

	public void setUbai(UserBaseAccountInfo ubai)
	{
		this.ubai = ubai;
	}
}