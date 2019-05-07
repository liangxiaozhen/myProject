package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class RemoveName {
	// ID
	private BigDecimal id;
	// 主目录
	private String nametype;
	// 名单编号
	private String nameno;
	// 子目录
	private String name;
	// 用户真实姓名
	private String realname;
	// 设置时间
	private Date addtime;
	// 设置时间 字符串类型
	private String addtimeStr;
	// 设置人
	private String addman;
	// 备注
	private String remark;
	// 用户登录名
	private String loginname;
	// 用户ID
	private BigDecimal baseid;
	// 多个用户ID
	private String baseids;
	//页面显示会员等级名称
	private UserGrade ugrade;
	//是否启用 0否 1是
	private Short isuse;
	//子目录人数
	private int nameCount;
	//主目录人数
	private int nameTypeCount;
	//目录编辑前 旧编号
	private String oldnameno;
	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public String getNametype()
	{
		return nametype;
	}

	public void setNametype(String nametype)
	{
		this.nametype = nametype == null ? null : nametype.trim();
	}

	public String getNameno()
	{
		return nameno;
	}

	public void setNameno(String nameno)
	{
		this.nameno = nameno == null ? null : nameno.trim();
	}

	public String getRealname()
	{
		return realname;
	}

	public void setRealname(String realname)
	{
		this.realname = realname == null ? null : realname.trim();
	}

	public Date getAddtime()
	{
		return addtime;
	}

	public void setAddtime(Date addtime)
	{
		this.addtime = addtime;
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

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname == null ? null : loginname.trim();
	}

	public BigDecimal getBaseid()
	{
		return baseid;
	}

	public void setBaseid(BigDecimal baseid)
	{
		this.baseid = baseid;
	}

	public String getAddtimeStr()
	{
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr)
	{
		this.addtimeStr = addtimeStr;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "RemoveName [id=" + id + ", nametype=" + nametype + ", nameno="
				+ nameno + ", name=" + name + ", realname=" + realname
				+ ", addtime=" + addtime + ", addtimeStr=" + addtimeStr
				+ ", addman=" + addman + ", remark=" + remark + ", loginname="
				+ loginname + ", baseid=" + baseid + "]";
	}

	public String getBaseids()
	{
		return baseids;
	}

	public void setBaseids(String baseids)
	{
		this.baseids = baseids;
	}

	public UserGrade getUgrade()
	{
		return ugrade;
	}

	public void setUgrade(UserGrade ugrade)
	{
		this.ugrade = ugrade;
	}

	public Short getIsuse()
	{
		return isuse;
	}

	public void setIsuse(Short isuse)
	{
		this.isuse = isuse;
	}

	public int getNameTypeCount()
	{
		return nameTypeCount;
	}

	public void setNameTypeCount(int nameTypeCount)
	{
		this.nameTypeCount = nameTypeCount;
	}

	public int getNameCount()
	{
		return nameCount;
	}

	public void setNameCount(int nameCount)
	{
		this.nameCount = nameCount;
	}

	public String getOldnameno()
	{
		return oldnameno;
	}

	public void setOldnameno(String oldnameno)
	{
		this.oldnameno = oldnameno;
	}

}