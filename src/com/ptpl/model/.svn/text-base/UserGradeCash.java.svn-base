package com.ptpl.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.huifu.util.SignUtils;
import com.ptpl.web.util.StringUtil;
/**
 * 用户账户支付实体类 用于会员购买
 * 
 * @author xiaoy
 *
 * @date 2016年12月12日 下午6:42:49
 */
public class UserGradeCash {
	private String Version = "10";
	private String CmdId = "UsrAcctPay";
	private String OrdId = StringUtil.getNo();
	private String UsrCustId;
	private String MerCustId = "6000060004166478";
	private String TransAmt;
	private String InAcctId = "MDT000001";
	private String InAcctType = "MERDT";
	private String RetUrl="";
	private String BgRetUrl;
	private String MerPriv;
	public String getVersion()
	{
		return Version;
	}
	public void setVersion(String version)
	{
		Version = version;
	}
	public String getCmdId()
	{
		return CmdId;
	}
	public void setCmdId(String cmdId)
	{
		CmdId = cmdId;
	}
	public String getOrdId()
	{
		return OrdId;
	}
	public void setOrdId(String ordId)
	{
		OrdId = ordId;
	}
	public String getUsrCustId()
	{
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId)
	{
		UsrCustId = usrCustId;
	}
	public String getMerCustId()
	{
		return MerCustId;
	}
	public void setMerCustId(String merCustId)
	{
		MerCustId = merCustId;
	}
	public String getTransAmt()
	{
		return TransAmt;
	}
	public void setTransAmt(String transAmt)
	{
		TransAmt = transAmt;
	}
	public String getInAcctId()
	{
		return InAcctId;
	}
	public void setInAcctId(String inAcctId)
	{
		InAcctId = inAcctId;
	}
	public String getInAcctType()
	{
		return InAcctType;
	}
	public void setInAcctType(String inAcctType)
	{
		InAcctType = inAcctType;
	}
	public String getRetUrl()
	{
		return RetUrl;
	}
	public void setRetUrl(String retUrl)
	{
		RetUrl = retUrl;
	}
	public String getBgRetUrl()
	{
		return BgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl)
	{
		BgRetUrl = bgRetUrl;
	}
	public String getMerPriv()
	{
		return MerPriv;
	}
	public void setMerPriv(String merPriv)
	{
		MerPriv = merPriv;
	}
	public Map<String, String> getMap() throws Exception
	{
		/*
		 * 版本号 Version 消息类型 CmdId 订单号 OrdId 用户客户号 UsrCustId 商户客户号 MerCustId 交易金额
		 * TransAmt 入账子账户 InAcctId 入账账户类型 InAcctType 页面返回 URL RetUrl 商户后台应答地址
		 * BgRetUrl 商户私有域 MerPriv 签名 ChkValue Version +CmdId + OrdId + UsrCustId
		 * +MerCustId + TransAmt+ InAcctId +InAcctType + RetUrl +BgRetUrl+
		 * MerPriv
		 */
			// Map
			Map<String, String> map = new HashMap<String, String>();
			map.put("Version", Version);
			map.put("CmdId", CmdId);
			map.put("OrdId", OrdId);
			map.put("UsrCustId", UsrCustId);
			map.put("MerCustId", MerCustId);
			map.put("TransAmt", TransAmt);
			map.put("InAcctId", InAcctId);
			map.put("InAcctType", InAcctType);
			map.put("RetUrl", RetUrl);
			map.put("BgRetUrl", BgRetUrl);
			map.put("MerPriv", MerPriv);
			// StringBuffer
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(Version)).append(StringUtils.trimToEmpty(CmdId))
					.append(StringUtils.trimToEmpty(OrdId))
					.append(StringUtils.trimToEmpty(UsrCustId))
					.append(StringUtils.trimToEmpty(MerCustId))
					.append(StringUtils.trimToEmpty(TransAmt))
					.append(StringUtils.trimToEmpty(InAcctId))
					.append(StringUtils.trimToEmpty(InAcctType))
					.append(StringUtils.trimToEmpty(RetUrl))
					.append(StringUtils.trimToEmpty(BgRetUrl))
					.append(StringUtils.trimToEmpty(MerPriv));
			// ChkValue
			String plainStr = buffer.toString();
			System.out.println(plainStr);
			map.put("ChkValue", SignUtils.encryptByRSA(plainStr));
			return map;
	}
}
