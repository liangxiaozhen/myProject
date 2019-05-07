package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 批量文件记录表
 */
public class BacthFileRecord {

	private BigDecimal id;//表id
	private String filePath;//文件路径    /batchfile/平台编号/业务名称/日期
	private String sendFileName;//上传文件名称
	private String getFileName;//下载文件名称
	private String coinstCode;//平台编号  800114
	private String PName;//平台名称
	private Date submitTime;//上传文件时间   干将—》银行
	private Date dealTime;//处理文件时间（到银行获取文件）
	private Short fileType;//业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款  5交易流水明细  6全流水
	private Short isSend;//是否已发送到银行     0.否   1.是
	private Short sendResult;//发送结果（是否成功）   0.失败    1.成功   
	private Short isDealResult;//是否已处理结果文件     0.否   1.是  2.作废
	private Short dealResult;//处理结果（是否成功）0.否  1.是
	private String remark;//备注
	
	private String batchNo;//文件业务批次号
	private String upResultCode;//银行返回码  上传文件
	private String downResultCode;//银行返回码  下载文件
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSendFileName() {
		return sendFileName;
	}
	public void setSendFileName(String sendFileName) {
		this.sendFileName = sendFileName;
	}
	public String getGetFileName() {
		return getFileName;
	}
	public void setGetFileName(String getFileName) {
		this.getFileName = getFileName;
	}
	public String getCoinstCode() {
		return coinstCode;
	}
	public void setCoinstCode(String coinstCode) {
		this.coinstCode = coinstCode;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public Short getFileType() {
		return fileType;
	}
	public void setFileType(Short fileType) {
		this.fileType = fileType;
	}
	public Short getIsSend() {
		return isSend;
	}
	public void setIsSend(Short isSend) {
		this.isSend = isSend;
	}
	public Short getSendResult() {
		return sendResult;
	}
	public void setSendResult(Short sendResult) {
		this.sendResult = sendResult;
	}
	public Short getIsDealResult() {
		return isDealResult;
	}
	public void setIsDealResult(Short isDealResult) {
		this.isDealResult = isDealResult;
	}
	public Short getDealResult() {
		return dealResult;
	}
	public void setDealResult(Short dealResult) {
		this.dealResult = dealResult;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getUpResultCode() {
		return upResultCode;
	}
	public void setUpResultCode(String upResultCode) {
		this.upResultCode = upResultCode;
	}
	public String getDownResultCode() {
		return downResultCode;
	}
	public void setDownResultCode(String downResultCode) {
		this.downResultCode = downResultCode;
	}
	@Override
	public String toString() {
		return "BacthFileRecord [id=" + id + ", filePath=" + filePath + ", sendFileName=" + sendFileName
				+ ", getFileName=" + getFileName + ", coinstCode=" + coinstCode + ", PName=" + PName + ", submitTime="
				+ submitTime + ", dealTime=" + dealTime + ", fileType=" + fileType + ", isSend=" + isSend
				+ ", sendResult=" + sendResult + ", isDealResult=" + isDealResult + ", dealResult=" + dealResult
				+ ", remark=" + remark + ", batchNo=" + batchNo + ", upResultCode=" + upResultCode + ", downResultCode="
				+ downResultCode + "]";
	}
}
