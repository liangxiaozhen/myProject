package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class CloseTime {
	private BigDecimal id;

	private String timetype;

	private String timename;

	private Date btime;

	private String addman;

	private String remark;

	private String timeno;

	private Date etime;

	private Date addtime;
	private String addtimeStr;
	private String btimeStr;
	private String etimeStr;
	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public String getTimetype()
	{
		return timetype;
	}

	public void setTimetype(String timetype)
	{
		this.timetype = timetype == null ? null : timetype.trim();
	}

	public String getTimename()
	{
		return timename;
	}

	public void setTimename(String timename)
	{
		this.timename = timename == null ? null : timename.trim();
	}

	public Date getBtime()
	{
		return btime;
	}

	public void setBtime(Date btime)
	{
		this.btime = btime;
	}

	public String getAddman()
	{
		return addman;
	}

	public void setAddman(String addman)
	{
		this.addman = addman == null ? null : addman.trim();
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark == null ? null : remark.trim();
	}

	public String getTimeno()
	{
		return timeno;
	}

	public void setTimeno(String timeno)
	{
		this.timeno = timeno == null ? null : timeno.trim();
	}

	public Date getEtime()
	{
		return etime;
	}

	public void setEtime(Date etime)
	{
		this.etime = etime;
	}

	public Date getAddtime()
	{
		return addtime;
	}

	public void setAddtime(Date addtime)
	{
		this.addtime = addtime;
	}

	public String getAddtimeStr()
	{
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr)
	{
		this.addtimeStr = addtimeStr;
	}

	public String getBtimeStr()
	{
		return btimeStr;
	}

	public void setBtimeStr(String btimeStr)
	{
		this.btimeStr = btimeStr;
	}

	public String getEtimeStr()
	{
		return etimeStr;
	}

	public void setEtimeStr(String etimeStr)
	{
		this.etimeStr = etimeStr;
	}

}