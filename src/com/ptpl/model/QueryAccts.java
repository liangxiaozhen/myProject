package com.ptpl.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 商户汇付返回
 * @author admin
 *
 */
public class QueryAccts {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String ChkValue;
	private String Version;
	private List<AcctDetails> AcctDetailsList;
	private String MerCustId;
	private String PlaintStr;
	public String getCmdId() {
		return CmdId;
	}
	@JSONField(name="CmdId")
	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}
	public String getRespCode() {
		return RespCode;
	}
	@JSONField(name="RespCode")
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getRespDesc() {
		return RespDesc;
	}
	@JSONField(name="RespDesc")
	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
	}
	public String getChkValue() {
		return ChkValue;
	}
	@JSONField(name="ChkValue")
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
	public String getVersion() {
		return Version;
	}
	@JSONField(name="Version")
	public void setVersion(String version) {
		Version = version;
	}
	public List<AcctDetails> getAcctDetailsList() {
		return AcctDetailsList;
	}
	@JSONField(name="AcctDetails")
	public void setAcctDetailsList(List<AcctDetails> acctDetailsList) {
		AcctDetailsList = acctDetailsList;
	}
	public String getMerCustId() {
		return MerCustId;
	}
	@JSONField(name="MerCustId")
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getPlaintStr() {
		return PlaintStr;
	}
	@JSONField(name="PlaintStr")
	public void setPlaintStr(String plaintStr) {
		PlaintStr = plaintStr;
	}
	@Override
	public String toString() {
		return "QueryAccts [CmdId=" + CmdId + ", RespCode=" + RespCode + ", RespDesc=" + RespDesc + ", ChkValue="
				+ ChkValue + ", Version=" + Version + ", AcctDetailsList=" + AcctDetailsList + ", MerCustId="
				+ MerCustId + ", PlaintStr=" + PlaintStr + "]";
	}
	
}
