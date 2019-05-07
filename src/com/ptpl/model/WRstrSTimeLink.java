package com.ptpl.model;

import java.math.BigDecimal;
/**
 * 提现限制特定时间关联表
 * 
 * @author xiaoy
 *
 * @date 2016年7月7日 下午1:49:53
 */
public class WRstrSTimeLink {
	private BigDecimal id;
	private BigDecimal wRstrId;
	private String sTimeNo;
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
	public String getsTimeNo()
	{
		return sTimeNo;
	}
	public void setsTimeNo(String sTimeNo)
	{
		this.sTimeNo = sTimeNo;
	}
	@Override
	public String toString()
	{
		return "WRstrSTimeLink [id=" + id + ", wRstrId=" + wRstrId
				+ ", sTimeNo=" + sTimeNo + "]";
	}

}
