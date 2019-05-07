package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 定向名单列表
 * @author pxl
 * @date 2016-12-6
 */
public class SpecialNameList {
	
	private BigDecimal id;//定向名单列表id
	
	private Short businessType;//业务类型（1.系统业务，2.短信通知）  定向类型

	private String systemBusType;//系统业务具体类型（见数据库说明书）  业务名单
	
	private String systemBusTypeStr;//系统业务具体类型字符串
	
	private String smsType;//短信通知具体类型 （见数据库说明书）
	
	private String smsTypeStr;//短信通知具体类型字符串
	
	private String businessNo;//定向名单编号
	
	private String businessName;//定向名单名称   标题
	
	private Short nameMode;//名单设置方式(1.大小名单，2.会员等级) 定向方式
	
	private Short isUse;//是否启用（1.启用 2.停用） 指定编号的定向名单 有没有启用
	
	private Short isQuote;//是否被引用（1.是  2.否） 被具体业务引用  比如手动颁奖
	
	private Short generateType;//名单生成方式（1.手动  2.模板）
	
	private Short isTemplet;//是否为模板  （1.是  2.否）
	
	private Date addTime;//添加时间
	
	private String addTimeStr;//添加时间字符串
	
	private String addMan;//添加人
	
	private String remark;//备注

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Short getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Short businessType) {
		this.businessType = businessType;
	}

	public String getSystemBusType() {
		return systemBusType;
	}

	public void setSystemBusType(String systemBusType) {
		this.systemBusType = systemBusType;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Short getNameMode() {
		return nameMode;
	}

	public void setNameMode(Short nameMode) {
		this.nameMode = nameMode;
	}

	public Short getIsUse() {
		return isUse;
	}

	public void setIsUse(Short isUse) {
		this.isUse = isUse;
	}

	public Short getIsQuote() {
		return isQuote;
	}

	public void setIsQuote(Short isQuote) {
		this.isQuote = isQuote;
	}

	public Short getGenerateType() {
		return generateType;
	}

	public void setGenerateType(Short generateType) {
		this.generateType = generateType;
	}

	public Short getIsTemplet() {
		return isTemplet;
	}

	public void setIsTemplet(Short isTemplet) {
		this.isTemplet = isTemplet;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSystemBusTypeStr() {
		return systemBusTypeStr;
	}

	public void setSystemBusTypeStr(String systemBusTypeStr) {
		this.systemBusTypeStr = systemBusTypeStr;
	}

	public String getSmsTypeStr() {
		return smsTypeStr;
	}

	public void setSmsTypeStr(String smsTypeStr) {
		this.smsTypeStr = smsTypeStr;
	}

	@Override
	public String toString() {
		return "SpecialNameList [id=" + id + ", businessType=" + businessType + ", systemBusType=" + systemBusType
				+ ", systemBusTypeStr=" + systemBusTypeStr + ", smsType=" + smsType + ", smsTypeStr=" + smsTypeStr
				+ ", businessNo=" + businessNo + ", businessName=" + businessName + ", nameMode=" + nameMode
				+ ", isUse=" + isUse + ", isQuote=" + isQuote + ", generateType=" + generateType + ", isTemplet="
				+ isTemplet + ", addTime=" + addTime + ", addTimeStr=" + addTimeStr + ", addMan=" + addMan + ", remark="
				+ remark + "]";
	}

}
