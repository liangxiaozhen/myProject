package com.ptpl.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 取现对账 返回参数 实体类
 * @author xiaoy
 *
 * @date 2016年11月9日 上午10:33:34
 */
public class CashReconciliation {
	//消息类型
	private String CmdId;
	//应答返回码
	private String RespCode;
	//应答返回描述
	private String RespDesc;
	//签名
	private String ChkValue;
	//版本号
	private String Version;
	//商户客户号
	private String MerCustId;
	//开始时间 YYYYMMDD
	private String BeginDate;
	//结束时间
	private String EndDate;
	//页数
	private String PageNum;
	//每页记录数
	private String PageSize;
	//记录总条数
	private String TotalItems;
	//手续费收取方
	private String FeeObj;
	//取现对账结果串
	private List<CashReconciliationD> CashReconciliationDtoList ;
	//报文
	private String PlainText;

	public String getCmdId()
	{
		return CmdId;
	}
	@JSONField(name="CmdId")
	public void setCmdId(String cmdId)
	{
		CmdId = cmdId;
	}

	public String getRespCode()
	{
		return RespCode;
	}
	@JSONField(name="RespCode")
	public void setRespCode(String respCode)
	{
		RespCode = respCode;
	}

	public String getRespDesc()
	{
		return RespDesc;
	}
	@JSONField(name="RespDesc")
	public void setRespDesc(String respDesc)
	{
		RespDesc = respDesc;
	}

	public String getChkValue()
	{
		return ChkValue;
	}
	@JSONField(name="ChkValue")
	public void setChkValue(String chkValue)
	{
		ChkValue = chkValue;
	}

	public String getVersion()
	{
		return Version;
	}
	@JSONField(name="Version")
	public void setVersion(String version)
	{
		Version = version;
	}

	public String getMerCustId()
	{
		return MerCustId;
	}
	@JSONField(name="MerCustId")
	public void setMerCustId(String merCustId)
	{
		MerCustId = merCustId;
	}

	public String getBeginDate()
	{
		return BeginDate;
	}
	@JSONField(name="BeginDate")
	public void setBeginDate(String beginDate)
	{
		BeginDate = beginDate;
	}

	public String getEndDate()
	{
		return EndDate;
	}
	@JSONField(name="EndDate")
	public void setEndDate(String endDate)
	{
		EndDate = endDate;
	}

	public String getPageNum()
	{
		return PageNum;
	}
	@JSONField(name="PageNum")
	public void setPageNum(String pageNum)
	{
		PageNum = pageNum;
	}

	public String getPageSize()
	{
		return PageSize;
	}
	@JSONField(name="PageSize")
	public void setPageSize(String pageSize)
	{
		PageSize = pageSize;
	}

	public String getTotalItems()
	{
		return TotalItems;
	}
	@JSONField(name="TotalItems")
	public void setTotalItems(String totalItems)
	{
		TotalItems = totalItems;
	}

	public String getFeeObj()
	{
		return FeeObj;
	}
	@JSONField(name="FeeObj")
	public void setFeeObj(String feeObj)
	{
		FeeObj = feeObj;
	}

	public List<CashReconciliationD> getCashReconciliationDtoList()
	{
		return CashReconciliationDtoList;
	}
	@JSONField(name="CashReconciliationDtoList")
	public void setCashReconciliationDtoList(
			List<CashReconciliationD> cashReconciliationDtoList)
	{
		CashReconciliationDtoList = cashReconciliationDtoList;
	}

	public String getPlainText()
	{
		return PlainText;
	}
	@JSONField(name="PlainText")
	public void setPlainText(String plainText)
	{
		PlainText = plainText;
	}
	
}
