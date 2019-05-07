package com.ptpl.model;

import java.math.BigDecimal;
/**
 * 提现限制关闭时间关联表
 * @author xiaoy
 *
 * @date 2016年7月7日 下午1:49:43
 */
public class WRstrCTimeLink {
	private BigDecimal id;
	private BigDecimal wRstrId;
	private String cTimeNo;
	public BigDecimal getId()
	{
		return id;
	}
	public void setId(BigDecimal id)
	{
		this.id = id;
	}
	public BigDecimal getwRstrId()
	{
		return wRstrId;
	}
	public void setwRstrId(BigDecimal wRstrId)
	{
		this.wRstrId = wRstrId;
	}
	public String getcTimeNo()
	{
		return cTimeNo;
	}
	public void setcTimeNo(String cTimeNo)
	{
		this.cTimeNo = cTimeNo;
	}
	@Override
	public String toString()
	{
		return "WRstrCTimeLink [id=" + id + ", wRstrId=" + wRstrId
				+ ", cTimeNo=" + cTimeNo + "]";
	}
	
}
