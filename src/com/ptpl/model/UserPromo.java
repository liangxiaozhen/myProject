package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 用户推广设置
 * 
 * @author xiaoy
 *
 * @date 2016年9月30日 上午9:30:13
 */
public class UserPromo {
	// ID
	private BigDecimal id;
	// 用户姓名
	private String name;
	// 用户登录名
	private String loginname;
	// 推广级别
	private BigDecimal proxygrade;
	// 推广资质 缺省 为 默认
	private String proxygradename;
	// 推广层数 （本级）
	private Long promolevels;
	// 推广层数 （父级）
	private Long parentlevels;
	// 上级用户名
	private String supname;
	// 上级用户登录名
	private String suploginname;
	// 托管用户是否开通 0 未开通 1已开通
	private Short isopenfsinfo;
	// 用户推广码
	private String promocode;
	// 上级用户推广码
	private String suppromocode;
	// 注册时间
	private Date regdate;
	// 推广人数
	private Long promonum;
	// 有效推广人数
	private Long validnum;
	// 推广费用总收入
	private Double promofee;
	// 下线备注
	private String subremark;
	// 管理员备注
	private String adminremark;
	// 备注
	private String remark;
	// 用户推广码修改次数
	private Short ismodify;
	// 是否有效
	private Short isvalid;
	// 用户推广第三方标记
	private Short userspecialflag;
	/*
	 * 上N层用户名
	 */
	private String suplevels2;
	private String suplevels3;
	private String suplevels4;
	private String suplevels5;
	private String suplevels6;
	private String suplevels7;
	private String suplevels8;
	private String suplevels9;
	private String suplevels10;
	private String suplevels12;
	private String suplevels13;
	private String suplevels14;
	private String suplevels15;
	private String suplevels16;
	private String suplevels17;
	private String suplevels18;
	private String suplevels19;
	private String suplevels20;
	private String suplevels11;
	private String suplevels22;
	private String suplevels23;
	private String suplevels24;
	private String suplevels25;
	private String suplevels26;
	private String suplevels27;
	private String suplevels28;
	private String suplevels29;
	private String suplevels21;
	private String suplevels30;
	private String suplevels32;
	private String suplevels33;
	private String suplevels34;
	private String suplevels35;
	private String suplevels36;
	private String suplevels37;
	private String suplevels38;
	private String suplevels39;
	private String suplevels1;
	private String suplevels31;
	private String suplevels40;
	private String suplevels42;
	private String suplevels43;
	private String suplevels44;
	private String suplevels45;
	private String suplevels46;
	private String suplevels47;
	private String suplevels48;
	private String suplevels49;
	private String suplevels41;
	private String suplevels50;
	private String suplevels52;
	private String suplevels53;
	private String suplevels54;
	private String suplevels55;
	private String suplevels56;
	private String suplevels57;
	private String suplevels58;
	private String suplevels59;
	private String suplevels51;
	private String suplevels60;
	private String suplevels62;
	private String suplevels63;
	private String suplevels64;
	private String suplevels65;
	private String suplevels66;
	private String suplevels67;
	private String suplevels68;
	private String suplevels69;
	private String suplevels61;
	private String suplevels70;
	private String suplevels72;
	private String suplevels73;
	private String suplevels74;
	private String suplevels75;
	private String suplevels76;
	private String suplevels77;
	private String suplevels78;
	private String suplevels79;
	private String suplevels71;
	private String suplevels80;
	private String suplevels82;
	private String suplevels83;
	private String suplevels84;
	private String suplevels85;
	private String suplevels86;
	private String suplevels87;
	private String suplevels88;
	private String suplevels89;
	private String suplevels81;
	private String suplevels90;
	private String suplevels92;
	private String suplevels93;
	private String suplevels94;
	private String suplevels95;
	private String suplevels96;
	private String suplevels97;
	private String suplevels98;
	private String suplevels99;
	private String suplevels91;
	private String suplevels100;

	/*
	 * 上N层是否有效
	 */
	private Short isvalid2;
	private Short isvalid3;
	private Short isvalid4;
	private Short isvalid5;
	private Short isvalid6;
	private Short isvalid7;
	private Short isvalid8;
	private Short isvalid9;
	private Short isvalid10;

	private Short isvalid11;
	private Short isvalid12;
	private Short isvalid13;
	private Short isvalid14;
	private Short isvalid15;
	private Short isvalid16;
	private Short isvalid17;
	private Short isvalid18;
	private Short isvalid19;
	private Short isvalid20;

	private Short isvalid21;
	private Short isvalid22;
	private Short isvalid23;
	private Short isvalid24;
	private Short isvalid25;
	private Short isvalid26;
	private Short isvalid27;
	private Short isvalid28;
	private Short isvalid29;
	private Short isvalid30;

	private Short isvalid31;
	private Short isvalid32;
	private Short isvalid33;
	private Short isvalid34;
	private Short isvalid35;
	private Short isvalid36;
	private Short isvalid37;
	private Short isvalid38;
	private Short isvalid39;
	private Short isvalid40;

	private Short isvalid41;
	private Short isvalid42;
	private Short isvalid43;
	private Short isvalid44;
	private Short isvalid45;
	private Short isvalid46;
	private Short isvalid47;
	private Short isvalid48;
	private Short isvalid49;
	private Short isvalid50;

	private Short isvalid51;
	private Short isvalid52;
	private Short isvalid53;
	private Short isvalid54;
	private Short isvalid55;
	private Short isvalid56;
	private Short isvalid57;
	private Short isvalid58;
	private Short isvalid59;
	private Short isvalid60;

	private Short isvalid61;
	private Short isvalid62;
	private Short isvalid63;
	private Short isvalid64;
	private Short isvalid65;
	private Short isvalid66;
	private Short isvalid67;
	private Short isvalid68;
	private Short isvalid69;
	private Short isvalid70;

	private Short isvalid71;
	private Short isvalid72;
	private Short isvalid73;
	private Short isvalid74;
	private Short isvalid75;
	private Short isvalid76;
	private Short isvalid77;
	private Short isvalid78;
	private Short isvalid79;
	private Short isvalid80;

	private Short isvalid81;
	private Short isvalid82;
	private Short isvalid83;
	private Short isvalid84;
	private Short isvalid85;
	private Short isvalid86;
	private Short isvalid87;
	private Short isvalid88;
	private Short isvalid89;
	private Short isvalid90;

	private Short isvalid91;
	private Short isvalid92;
	private Short isvalid93;
	private Short isvalid94;
	private Short isvalid95;
	private Short isvalid96;
	private Short isvalid97;
	private Short isvalid98;
	private Short isvalid99;
	private Short isvalid100;
	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname == null ? null : loginname.trim();
	}

	public Long getPromolevels()
	{
		return promolevels;
	}

	public void setPromolevels(Long promolevels)
	{
		this.promolevels = promolevels;
	}

	public String getSupname()
	{
		return supname;
	}

	public void setSupname(String supname)
	{
		this.supname = supname == null ? null : supname.trim();
	}

	public String getSuploginname()
	{
		return suploginname;
	}

	public void setSuploginname(String suploginname)
	{
		this.suploginname = suploginname == null ? null : suploginname.trim();
	}

	public Short getIsopenfsinfo()
	{
		return isopenfsinfo;
	}

	public void setIsopenfsinfo(Short isopenfsinfo)
	{
		this.isopenfsinfo = isopenfsinfo;
	}

	public String getPromocode()
	{
		return promocode;
	}

	public void setPromocode(String promocode)
	{
		this.promocode = promocode == null ? null : promocode.trim();
	}

	public String getSuppromocode()
	{
		return suppromocode;
	}

	public void setSuppromocode(String suppromocode)
	{
		this.suppromocode = suppromocode == null ? null : suppromocode.trim();
	}

	public Date getRegdate()
	{
		return regdate;
	}

	public void setRegdate(Date regdate)
	{
		this.regdate = regdate;
	}

	public Long getPromonum()
	{
		return promonum;
	}

	public void setPromonum(Long promonum)
	{
		this.promonum = promonum;
	}

	public Double getPromofee()
	{
		return promofee;
	}

	public void setPromofee(Double promofee)
	{
		this.promofee = promofee;
	}

	public String getSubremark()
	{
		return subremark;
	}

	public void setSubremark(String subremark)
	{
		this.subremark = subremark == null ? null : subremark.trim();
	}

	public String getAdminremark()
	{
		return adminremark;
	}

	public void setAdminremark(String adminremark)
	{
		this.adminremark = adminremark == null ? null : adminremark.trim();
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark == null ? null : remark.trim();
	}

	public Short getIsmodify()
	{
		return ismodify;
	}

	public void setIsmodify(Short ismodify)
	{
		this.ismodify = ismodify;
	}

	public Long getParentlevels()
	{
		return parentlevels;
	}

	public void setParentlevels(Long parentlevels)
	{
		this.parentlevels = parentlevels;
	}

	public String getProxygradename()
	{
		return proxygradename;
	}

	public void setProxygradename(String proxygradename)
	{
		this.proxygradename = proxygradename;
	}

	public BigDecimal getProxygrade()
	{
		return proxygrade;
	}

	public void setProxygrade(BigDecimal proxygrade)
	{
		this.proxygrade = proxygrade;
	}

	public String getSuplevels2()
	{
		return suplevels2;
	}

	public void setSuplevels2(String suplevels2)
	{
		this.suplevels2 = suplevels2;
	}

	public String getSuplevels3()
	{
		return suplevels3;
	}

	public void setSuplevels3(String suplevels3)
	{
		this.suplevels3 = suplevels3;
	}

	public String getSuplevels4()
	{
		return suplevels4;
	}

	public void setSuplevels4(String suplevels4)
	{
		this.suplevels4 = suplevels4;
	}

	public String getSuplevels5()
	{
		return suplevels5;
	}

	public void setSuplevels5(String suplevels5)
	{
		this.suplevels5 = suplevels5;
	}

	public String getSuplevels6()
	{
		return suplevels6;
	}

	public void setSuplevels6(String suplevels6)
	{
		this.suplevels6 = suplevels6;
	}

	public String getSuplevels7()
	{
		return suplevels7;
	}

	public void setSuplevels7(String suplevels7)
	{
		this.suplevels7 = suplevels7;
	}

	public String getSuplevels8()
	{
		return suplevels8;
	}

	public void setSuplevels8(String suplevels8)
	{
		this.suplevels8 = suplevels8;
	}

	public String getSuplevels9()
	{
		return suplevels9;
	}

	public void setSuplevels9(String suplevels9)
	{
		this.suplevels9 = suplevels9;
	}

	public String getSuplevels10()
	{
		return suplevels10;
	}

	public void setSuplevels10(String suplevels10)
	{
		this.suplevels10 = suplevels10;
	}

	public String getSuplevels12()
	{
		return suplevels12;
	}

	public void setSuplevels12(String suplevels12)
	{
		this.suplevels12 = suplevels12;
	}

	public String getSuplevels13()
	{
		return suplevels13;
	}

	public void setSuplevels13(String suplevels13)
	{
		this.suplevels13 = suplevels13;
	}

	public String getSuplevels14()
	{
		return suplevels14;
	}

	public void setSuplevels14(String suplevels14)
	{
		this.suplevels14 = suplevels14;
	}

	public String getSuplevels15()
	{
		return suplevels15;
	}

	public void setSuplevels15(String suplevels15)
	{
		this.suplevels15 = suplevels15;
	}

	public String getSuplevels16()
	{
		return suplevels16;
	}

	public void setSuplevels16(String suplevels16)
	{
		this.suplevels16 = suplevels16;
	}

	public String getSuplevels17()
	{
		return suplevels17;
	}

	public void setSuplevels17(String suplevels17)
	{
		this.suplevels17 = suplevels17;
	}

	public String getSuplevels18()
	{
		return suplevels18;
	}

	public void setSuplevels18(String suplevels18)
	{
		this.suplevels18 = suplevels18;
	}

	public String getSuplevels19()
	{
		return suplevels19;
	}

	public void setSuplevels19(String suplevels19)
	{
		this.suplevels19 = suplevels19;
	}

	public String getSuplevels20()
	{
		return suplevels20;
	}

	public void setSuplevels20(String suplevels20)
	{
		this.suplevels20 = suplevels20;
	}

	public String getSuplevels11()
	{
		return suplevels11;
	}

	public void setSuplevels11(String suplevels11)
	{
		this.suplevels11 = suplevels11;
	}

	public String getSuplevels22()
	{
		return suplevels22;
	}

	public void setSuplevels22(String suplevels22)
	{
		this.suplevels22 = suplevels22;
	}

	public String getSuplevels23()
	{
		return suplevels23;
	}

	public void setSuplevels23(String suplevels23)
	{
		this.suplevels23 = suplevels23;
	}

	public String getSuplevels24()
	{
		return suplevels24;
	}

	public void setSuplevels24(String suplevels24)
	{
		this.suplevels24 = suplevels24;
	}

	public String getSuplevels25()
	{
		return suplevels25;
	}

	public void setSuplevels25(String suplevels25)
	{
		this.suplevels25 = suplevels25;
	}

	public String getSuplevels26()
	{
		return suplevels26;
	}

	public void setSuplevels26(String suplevels26)
	{
		this.suplevels26 = suplevels26;
	}

	public String getSuplevels27()
	{
		return suplevels27;
	}

	public void setSuplevels27(String suplevels27)
	{
		this.suplevels27 = suplevels27;
	}

	public String getSuplevels28()
	{
		return suplevels28;
	}

	public void setSuplevels28(String suplevels28)
	{
		this.suplevels28 = suplevels28;
	}

	public String getSuplevels29()
	{
		return suplevels29;
	}

	public void setSuplevels29(String suplevels29)
	{
		this.suplevels29 = suplevels29;
	}

	public String getSuplevels21()
	{
		return suplevels21;
	}

	public void setSuplevels21(String suplevels21)
	{
		this.suplevels21 = suplevels21;
	}

	public String getSuplevels30()
	{
		return suplevels30;
	}

	public void setSuplevels30(String suplevels30)
	{
		this.suplevels30 = suplevels30;
	}

	public String getSuplevels32()
	{
		return suplevels32;
	}

	public void setSuplevels32(String suplevels32)
	{
		this.suplevels32 = suplevels32;
	}

	public String getSuplevels33()
	{
		return suplevels33;
	}

	public void setSuplevels33(String suplevels33)
	{
		this.suplevels33 = suplevels33;
	}

	public String getSuplevels34()
	{
		return suplevels34;
	}

	public void setSuplevels34(String suplevels34)
	{
		this.suplevels34 = suplevels34;
	}

	public String getSuplevels35()
	{
		return suplevels35;
	}

	public void setSuplevels35(String suplevels35)
	{
		this.suplevels35 = suplevels35;
	}

	public String getSuplevels36()
	{
		return suplevels36;
	}

	public void setSuplevels36(String suplevels36)
	{
		this.suplevels36 = suplevels36;
	}

	public String getSuplevels37()
	{
		return suplevels37;
	}

	public void setSuplevels37(String suplevels37)
	{
		this.suplevels37 = suplevels37;
	}

	public String getSuplevels38()
	{
		return suplevels38;
	}

	public void setSuplevels38(String suplevels38)
	{
		this.suplevels38 = suplevels38;
	}

	public String getSuplevels39()
	{
		return suplevels39;
	}

	public void setSuplevels39(String suplevels39)
	{
		this.suplevels39 = suplevels39;
	}

	public String getSuplevels1()
	{
		return suplevels1;
	}

	public void setSuplevels1(String suplevels1)
	{
		this.suplevels1 = suplevels1;
	}

	public String getSuplevels40()
	{
		return suplevels40;
	}

	public void setSuplevels40(String suplevels40)
	{
		this.suplevels40 = suplevels40;
	}

	public String getSuplevels42()
	{
		return suplevels42;
	}

	public void setSuplevels42(String suplevels42)
	{
		this.suplevels42 = suplevels42;
	}

	public String getSuplevels43()
	{
		return suplevels43;
	}

	public void setSuplevels43(String suplevels43)
	{
		this.suplevels43 = suplevels43;
	}

	public String getSuplevels44()
	{
		return suplevels44;
	}

	public void setSuplevels44(String suplevels44)
	{
		this.suplevels44 = suplevels44;
	}

	public String getSuplevels45()
	{
		return suplevels45;
	}

	public void setSuplevels45(String suplevels45)
	{
		this.suplevels45 = suplevels45;
	}

	public String getSuplevels46()
	{
		return suplevels46;
	}

	public void setSuplevels46(String suplevels46)
	{
		this.suplevels46 = suplevels46;
	}

	public String getSuplevels47()
	{
		return suplevels47;
	}

	public void setSuplevels47(String suplevels47)
	{
		this.suplevels47 = suplevels47;
	}

	public String getSuplevels48()
	{
		return suplevels48;
	}

	public void setSuplevels48(String suplevels48)
	{
		this.suplevels48 = suplevels48;
	}

	public String getSuplevels49()
	{
		return suplevels49;
	}

	public void setSuplevels49(String suplevels49)
	{
		this.suplevels49 = suplevels49;
	}

	public String getSuplevels41()
	{
		return suplevels41;
	}

	public void setSuplevels41(String suplevels41)
	{
		this.suplevels41 = suplevels41;
	}

	public String getSuplevels50()
	{
		return suplevels50;
	}

	public void setSuplevels50(String suplevels50)
	{
		this.suplevels50 = suplevels50;
	}

	public String getSuplevels52()
	{
		return suplevels52;
	}

	public void setSuplevels52(String suplevels52)
	{
		this.suplevels52 = suplevels52;
	}

	public String getSuplevels53()
	{
		return suplevels53;
	}

	public void setSuplevels53(String suplevels53)
	{
		this.suplevels53 = suplevels53;
	}

	public String getSuplevels54()
	{
		return suplevels54;
	}

	public void setSuplevels54(String suplevels54)
	{
		this.suplevels54 = suplevels54;
	}

	public String getSuplevels55()
	{
		return suplevels55;
	}

	public void setSuplevels55(String suplevels55)
	{
		this.suplevels55 = suplevels55;
	}

	public String getSuplevels56()
	{
		return suplevels56;
	}

	public void setSuplevels56(String suplevels56)
	{
		this.suplevels56 = suplevels56;
	}

	public String getSuplevels57()
	{
		return suplevels57;
	}

	public void setSuplevels57(String suplevels57)
	{
		this.suplevels57 = suplevels57;
	}

	public String getSuplevels58()
	{
		return suplevels58;
	}

	public void setSuplevels58(String suplevels58)
	{
		this.suplevels58 = suplevels58;
	}

	public String getSuplevels59()
	{
		return suplevels59;
	}

	public void setSuplevels59(String suplevels59)
	{
		this.suplevels59 = suplevels59;
	}

	public String getSuplevels51()
	{
		return suplevels51;
	}

	public void setSuplevels51(String suplevels51)
	{
		this.suplevels51 = suplevels51;
	}

	public String getSuplevels60()
	{
		return suplevels60;
	}

	public void setSuplevels60(String suplevels60)
	{
		this.suplevels60 = suplevels60;
	}

	public String getSuplevels62()
	{
		return suplevels62;
	}

	public void setSuplevels62(String suplevels62)
	{
		this.suplevels62 = suplevels62;
	}

	public String getSuplevels63()
	{
		return suplevels63;
	}

	public void setSuplevels63(String suplevels63)
	{
		this.suplevels63 = suplevels63;
	}

	public String getSuplevels64()
	{
		return suplevels64;
	}

	public void setSuplevels64(String suplevels64)
	{
		this.suplevels64 = suplevels64;
	}

	public String getSuplevels65()
	{
		return suplevels65;
	}

	public void setSuplevels65(String suplevels65)
	{
		this.suplevels65 = suplevels65;
	}

	public String getSuplevels66()
	{
		return suplevels66;
	}

	public void setSuplevels66(String suplevels66)
	{
		this.suplevels66 = suplevels66;
	}

	public String getSuplevels67()
	{
		return suplevels67;
	}

	public void setSuplevels67(String suplevels67)
	{
		this.suplevels67 = suplevels67;
	}

	public String getSuplevels68()
	{
		return suplevels68;
	}

	public void setSuplevels68(String suplevels68)
	{
		this.suplevels68 = suplevels68;
	}

	public String getSuplevels69()
	{
		return suplevels69;
	}

	public void setSuplevels69(String suplevels69)
	{
		this.suplevels69 = suplevels69;
	}

	public String getSuplevels61()
	{
		return suplevels61;
	}

	public void setSuplevels61(String suplevels61)
	{
		this.suplevels61 = suplevels61;
	}

	public String getSuplevels70()
	{
		return suplevels70;
	}

	public void setSuplevels70(String suplevels70)
	{
		this.suplevels70 = suplevels70;
	}

	public String getSuplevels72()
	{
		return suplevels72;
	}

	public void setSuplevels72(String suplevels72)
	{
		this.suplevels72 = suplevels72;
	}

	public String getSuplevels73()
	{
		return suplevels73;
	}

	public void setSuplevels73(String suplevels73)
	{
		this.suplevels73 = suplevels73;
	}

	public String getSuplevels74()
	{
		return suplevels74;
	}

	public void setSuplevels74(String suplevels74)
	{
		this.suplevels74 = suplevels74;
	}

	public String getSuplevels75()
	{
		return suplevels75;
	}

	public void setSuplevels75(String suplevels75)
	{
		this.suplevels75 = suplevels75;
	}

	public String getSuplevels76()
	{
		return suplevels76;
	}

	public void setSuplevels76(String suplevels76)
	{
		this.suplevels76 = suplevels76;
	}

	public String getSuplevels77()
	{
		return suplevels77;
	}

	public void setSuplevels77(String suplevels77)
	{
		this.suplevels77 = suplevels77;
	}

	public String getSuplevels78()
	{
		return suplevels78;
	}

	public void setSuplevels78(String suplevels78)
	{
		this.suplevels78 = suplevels78;
	}

	public String getSuplevels79()
	{
		return suplevels79;
	}

	public void setSuplevels79(String suplevels79)
	{
		this.suplevels79 = suplevels79;
	}

	public String getSuplevels71()
	{
		return suplevels71;
	}

	public void setSuplevels71(String suplevels71)
	{
		this.suplevels71 = suplevels71;
	}

	public String getSuplevels80()
	{
		return suplevels80;
	}

	public void setSuplevels80(String suplevels80)
	{
		this.suplevels80 = suplevels80;
	}

	public String getSuplevels82()
	{
		return suplevels82;
	}

	public void setSuplevels82(String suplevels82)
	{
		this.suplevels82 = suplevels82;
	}

	public String getSuplevels83()
	{
		return suplevels83;
	}

	public void setSuplevels83(String suplevels83)
	{
		this.suplevels83 = suplevels83;
	}

	public String getSuplevels84()
	{
		return suplevels84;
	}

	public void setSuplevels84(String suplevels84)
	{
		this.suplevels84 = suplevels84;
	}

	public String getSuplevels85()
	{
		return suplevels85;
	}

	public void setSuplevels85(String suplevels85)
	{
		this.suplevels85 = suplevels85;
	}

	public String getSuplevels86()
	{
		return suplevels86;
	}

	public void setSuplevels86(String suplevels86)
	{
		this.suplevels86 = suplevels86;
	}

	public String getSuplevels87()
	{
		return suplevels87;
	}

	public void setSuplevels87(String suplevels87)
	{
		this.suplevels87 = suplevels87;
	}

	public String getSuplevels88()
	{
		return suplevels88;
	}

	public void setSuplevels88(String suplevels88)
	{
		this.suplevels88 = suplevels88;
	}

	public String getSuplevels89()
	{
		return suplevels89;
	}

	public void setSuplevels89(String suplevels89)
	{
		this.suplevels89 = suplevels89;
	}

	public String getSuplevels81()
	{
		return suplevels81;
	}

	public void setSuplevels81(String suplevels81)
	{
		this.suplevels81 = suplevels81;
	}

	public String getSuplevels90()
	{
		return suplevels90;
	}

	public void setSuplevels90(String suplevels90)
	{
		this.suplevels90 = suplevels90;
	}

	public String getSuplevels92()
	{
		return suplevels92;
	}

	public void setSuplevels92(String suplevels92)
	{
		this.suplevels92 = suplevels92;
	}

	public String getSuplevels93()
	{
		return suplevels93;
	}

	public void setSuplevels93(String suplevels93)
	{
		this.suplevels93 = suplevels93;
	}

	public String getSuplevels94()
	{
		return suplevels94;
	}

	public void setSuplevels94(String suplevels94)
	{
		this.suplevels94 = suplevels94;
	}

	public String getSuplevels95()
	{
		return suplevels95;
	}

	public void setSuplevels95(String suplevels95)
	{
		this.suplevels95 = suplevels95;
	}

	public String getSuplevels96()
	{
		return suplevels96;
	}

	public void setSuplevels96(String suplevels96)
	{
		this.suplevels96 = suplevels96;
	}

	public String getSuplevels97()
	{
		return suplevels97;
	}

	public void setSuplevels97(String suplevels97)
	{
		this.suplevels97 = suplevels97;
	}

	public String getSuplevels98()
	{
		return suplevels98;
	}

	public void setSuplevels98(String suplevels98)
	{
		this.suplevels98 = suplevels98;
	}

	public String getSuplevels99()
	{
		return suplevels99;
	}

	public void setSuplevels99(String suplevels99)
	{
		this.suplevels99 = suplevels99;
	}

	public String getSuplevels91()
	{
		return suplevels91;
	}

	public void setSuplevels91(String suplevels91)
	{
		this.suplevels91 = suplevels91;
	}

	public String getSuplevels100()
	{
		return suplevels100;
	}

	public void setSuplevels100(String suplevels100)
	{
		this.suplevels100 = suplevels100;
	}

	public Short getIsvalid2()
	{
		return isvalid2;
	}

	public void setIsvalid2(Short isvalid2)
	{
		this.isvalid2 = isvalid2;
	}

	public Short getIsvalid3()
	{
		return isvalid3;
	}

	public void setIsvalid3(Short isvalid3)
	{
		this.isvalid3 = isvalid3;
	}

	public Short getIsvalid4()
	{
		return isvalid4;
	}

	public void setIsvalid4(Short isvalid4)
	{
		this.isvalid4 = isvalid4;
	}

	public Short getIsvalid5()
	{
		return isvalid5;
	}

	public void setIsvalid5(Short isvalid5)
	{
		this.isvalid5 = isvalid5;
	}

	public Short getIsvalid6()
	{
		return isvalid6;
	}

	public void setIsvalid6(Short isvalid6)
	{
		this.isvalid6 = isvalid6;
	}

	public Short getIsvalid7()
	{
		return isvalid7;
	}

	public void setIsvalid7(Short isvalid7)
	{
		this.isvalid7 = isvalid7;
	}

	public Short getIsvalid8()
	{
		return isvalid8;
	}

	public void setIsvalid8(Short isvalid8)
	{
		this.isvalid8 = isvalid8;
	}

	public Short getIsvalid9()
	{
		return isvalid9;
	}

	public void setIsvalid9(Short isvalid9)
	{
		this.isvalid9 = isvalid9;
	}

	public Short getIsvalid10()
	{
		return isvalid10;
	}

	public void setIsvalid10(Short isvalid10)
	{
		this.isvalid10 = isvalid10;
	}

	public Short getIsvalid11()
	{
		return isvalid11;
	}

	public void setIsvalid11(Short isvalid11)
	{
		this.isvalid11 = isvalid11;
	}

	public Short getIsvalid12()
	{
		return isvalid12;
	}

	public void setIsvalid12(Short isvalid12)
	{
		this.isvalid12 = isvalid12;
	}

	public Short getIsvalid13()
	{
		return isvalid13;
	}

	public void setIsvalid13(Short isvalid13)
	{
		this.isvalid13 = isvalid13;
	}

	public Short getIsvalid14()
	{
		return isvalid14;
	}

	public void setIsvalid14(Short isvalid14)
	{
		this.isvalid14 = isvalid14;
	}

	public Short getIsvalid15()
	{
		return isvalid15;
	}

	public void setIsvalid15(Short isvalid15)
	{
		this.isvalid15 = isvalid15;
	}

	public Short getIsvalid16()
	{
		return isvalid16;
	}

	public void setIsvalid16(Short isvalid16)
	{
		this.isvalid16 = isvalid16;
	}

	public Short getIsvalid17()
	{
		return isvalid17;
	}

	public void setIsvalid17(Short isvalid17)
	{
		this.isvalid17 = isvalid17;
	}

	public Short getIsvalid18()
	{
		return isvalid18;
	}

	public void setIsvalid18(Short isvalid18)
	{
		this.isvalid18 = isvalid18;
	}

	public Short getIsvalid19()
	{
		return isvalid19;
	}

	public void setIsvalid19(Short isvalid19)
	{
		this.isvalid19 = isvalid19;
	}

	public Short getIsvalid20()
	{
		return isvalid20;
	}

	public void setIsvalid20(Short isvalid20)
	{
		this.isvalid20 = isvalid20;
	}

	public Short getIsvalid21()
	{
		return isvalid21;
	}

	public void setIsvalid21(Short isvalid21)
	{
		this.isvalid21 = isvalid21;
	}

	public Short getIsvalid22()
	{
		return isvalid22;
	}

	public void setIsvalid22(Short isvalid22)
	{
		this.isvalid22 = isvalid22;
	}

	public Short getIsvalid23()
	{
		return isvalid23;
	}

	public void setIsvalid23(Short isvalid23)
	{
		this.isvalid23 = isvalid23;
	}

	public Short getIsvalid24()
	{
		return isvalid24;
	}

	public void setIsvalid24(Short isvalid24)
	{
		this.isvalid24 = isvalid24;
	}

	public Short getIsvalid25()
	{
		return isvalid25;
	}

	public void setIsvalid25(Short isvalid25)
	{
		this.isvalid25 = isvalid25;
	}

	public Short getIsvalid26()
	{
		return isvalid26;
	}

	public void setIsvalid26(Short isvalid26)
	{
		this.isvalid26 = isvalid26;
	}

	public Short getIsvalid27()
	{
		return isvalid27;
	}

	public void setIsvalid27(Short isvalid27)
	{
		this.isvalid27 = isvalid27;
	}

	public Short getIsvalid28()
	{
		return isvalid28;
	}

	public void setIsvalid28(Short isvalid28)
	{
		this.isvalid28 = isvalid28;
	}

	public Short getIsvalid29()
	{
		return isvalid29;
	}

	public void setIsvalid29(Short isvalid29)
	{
		this.isvalid29 = isvalid29;
	}

	public Short getIsvalid30()
	{
		return isvalid30;
	}

	public void setIsvalid30(Short isvalid30)
	{
		this.isvalid30 = isvalid30;
	}

	public Short getIsvalid31()
	{
		return isvalid31;
	}

	public void setIsvalid31(Short isvalid31)
	{
		this.isvalid31 = isvalid31;
	}

	public Short getIsvalid32()
	{
		return isvalid32;
	}

	public void setIsvalid32(Short isvalid32)
	{
		this.isvalid32 = isvalid32;
	}

	public Short getIsvalid33()
	{
		return isvalid33;
	}

	public void setIsvalid33(Short isvalid33)
	{
		this.isvalid33 = isvalid33;
	}

	public Short getIsvalid34()
	{
		return isvalid34;
	}

	public void setIsvalid34(Short isvalid34)
	{
		this.isvalid34 = isvalid34;
	}

	public Short getIsvalid35()
	{
		return isvalid35;
	}

	public void setIsvalid35(Short isvalid35)
	{
		this.isvalid35 = isvalid35;
	}

	public Short getIsvalid36()
	{
		return isvalid36;
	}

	public void setIsvalid36(Short isvalid36)
	{
		this.isvalid36 = isvalid36;
	}

	public Short getIsvalid37()
	{
		return isvalid37;
	}

	public void setIsvalid37(Short isvalid37)
	{
		this.isvalid37 = isvalid37;
	}

	public Short getIsvalid38()
	{
		return isvalid38;
	}

	public void setIsvalid38(Short isvalid38)
	{
		this.isvalid38 = isvalid38;
	}

	public Short getIsvalid39()
	{
		return isvalid39;
	}

	public void setIsvalid39(Short isvalid39)
	{
		this.isvalid39 = isvalid39;
	}

	public Short getIsvalid40()
	{
		return isvalid40;
	}

	public void setIsvalid40(Short isvalid40)
	{
		this.isvalid40 = isvalid40;
	}

	public Short getIsvalid41()
	{
		return isvalid41;
	}

	public void setIsvalid41(Short isvalid41)
	{
		this.isvalid41 = isvalid41;
	}

	public Short getIsvalid42()
	{
		return isvalid42;
	}

	public void setIsvalid42(Short isvalid42)
	{
		this.isvalid42 = isvalid42;
	}

	public Short getIsvalid43()
	{
		return isvalid43;
	}

	public void setIsvalid43(Short isvalid43)
	{
		this.isvalid43 = isvalid43;
	}

	public Short getIsvalid44()
	{
		return isvalid44;
	}

	public void setIsvalid44(Short isvalid44)
	{
		this.isvalid44 = isvalid44;
	}

	public Short getIsvalid45()
	{
		return isvalid45;
	}

	public void setIsvalid45(Short isvalid45)
	{
		this.isvalid45 = isvalid45;
	}

	public Short getIsvalid46()
	{
		return isvalid46;
	}

	public void setIsvalid46(Short isvalid46)
	{
		this.isvalid46 = isvalid46;
	}

	public Short getIsvalid47()
	{
		return isvalid47;
	}

	public void setIsvalid47(Short isvalid47)
	{
		this.isvalid47 = isvalid47;
	}

	public Short getIsvalid48()
	{
		return isvalid48;
	}

	public void setIsvalid48(Short isvalid48)
	{
		this.isvalid48 = isvalid48;
	}

	public Short getIsvalid49()
	{
		return isvalid49;
	}

	public void setIsvalid49(Short isvalid49)
	{
		this.isvalid49 = isvalid49;
	}

	public Short getIsvalid50()
	{
		return isvalid50;
	}

	public void setIsvalid50(Short isvalid50)
	{
		this.isvalid50 = isvalid50;
	}

	public Short getIsvalid51()
	{
		return isvalid51;
	}

	public void setIsvalid51(Short isvalid51)
	{
		this.isvalid51 = isvalid51;
	}

	public Short getIsvalid52()
	{
		return isvalid52;
	}

	public void setIsvalid52(Short isvalid52)
	{
		this.isvalid52 = isvalid52;
	}

	public Short getIsvalid53()
	{
		return isvalid53;
	}

	public void setIsvalid53(Short isvalid53)
	{
		this.isvalid53 = isvalid53;
	}

	public Short getIsvalid54()
	{
		return isvalid54;
	}

	public void setIsvalid54(Short isvalid54)
	{
		this.isvalid54 = isvalid54;
	}

	public Short getIsvalid55()
	{
		return isvalid55;
	}

	public void setIsvalid55(Short isvalid55)
	{
		this.isvalid55 = isvalid55;
	}

	public Short getIsvalid56()
	{
		return isvalid56;
	}

	public void setIsvalid56(Short isvalid56)
	{
		this.isvalid56 = isvalid56;
	}

	public Short getIsvalid57()
	{
		return isvalid57;
	}

	public void setIsvalid57(Short isvalid57)
	{
		this.isvalid57 = isvalid57;
	}

	public Short getIsvalid58()
	{
		return isvalid58;
	}

	public void setIsvalid58(Short isvalid58)
	{
		this.isvalid58 = isvalid58;
	}

	public Short getIsvalid59()
	{
		return isvalid59;
	}

	public void setIsvalid59(Short isvalid59)
	{
		this.isvalid59 = isvalid59;
	}

	public Short getIsvalid60()
	{
		return isvalid60;
	}

	public void setIsvalid60(Short isvalid60)
	{
		this.isvalid60 = isvalid60;
	}

	public Short getIsvalid61()
	{
		return isvalid61;
	}

	public void setIsvalid61(Short isvalid61)
	{
		this.isvalid61 = isvalid61;
	}

	public Short getIsvalid62()
	{
		return isvalid62;
	}

	public void setIsvalid62(Short isvalid62)
	{
		this.isvalid62 = isvalid62;
	}

	public Short getIsvalid63()
	{
		return isvalid63;
	}

	public void setIsvalid63(Short isvalid63)
	{
		this.isvalid63 = isvalid63;
	}

	public Short getIsvalid64()
	{
		return isvalid64;
	}

	public void setIsvalid64(Short isvalid64)
	{
		this.isvalid64 = isvalid64;
	}

	public Short getIsvalid65()
	{
		return isvalid65;
	}

	public void setIsvalid65(Short isvalid65)
	{
		this.isvalid65 = isvalid65;
	}

	public Short getIsvalid66()
	{
		return isvalid66;
	}

	public void setIsvalid66(Short isvalid66)
	{
		this.isvalid66 = isvalid66;
	}

	public Short getIsvalid67()
	{
		return isvalid67;
	}

	public void setIsvalid67(Short isvalid67)
	{
		this.isvalid67 = isvalid67;
	}

	public Short getIsvalid68()
	{
		return isvalid68;
	}

	public void setIsvalid68(Short isvalid68)
	{
		this.isvalid68 = isvalid68;
	}

	public Short getIsvalid69()
	{
		return isvalid69;
	}

	public void setIsvalid69(Short isvalid69)
	{
		this.isvalid69 = isvalid69;
	}

	public Short getIsvalid70()
	{
		return isvalid70;
	}

	public void setIsvalid70(Short isvalid70)
	{
		this.isvalid70 = isvalid70;
	}

	public Short getIsvalid71()
	{
		return isvalid71;
	}

	public void setIsvalid71(Short isvalid71)
	{
		this.isvalid71 = isvalid71;
	}

	public Short getIsvalid72()
	{
		return isvalid72;
	}

	public void setIsvalid72(Short isvalid72)
	{
		this.isvalid72 = isvalid72;
	}

	public Short getIsvalid73()
	{
		return isvalid73;
	}

	public void setIsvalid73(Short isvalid73)
	{
		this.isvalid73 = isvalid73;
	}

	public Short getIsvalid74()
	{
		return isvalid74;
	}

	public void setIsvalid74(Short isvalid74)
	{
		this.isvalid74 = isvalid74;
	}

	public Short getIsvalid75()
	{
		return isvalid75;
	}

	public void setIsvalid75(Short isvalid75)
	{
		this.isvalid75 = isvalid75;
	}

	public Short getIsvalid76()
	{
		return isvalid76;
	}

	public void setIsvalid76(Short isvalid76)
	{
		this.isvalid76 = isvalid76;
	}

	public Short getIsvalid77()
	{
		return isvalid77;
	}

	public void setIsvalid77(Short isvalid77)
	{
		this.isvalid77 = isvalid77;
	}

	public Short getIsvalid78()
	{
		return isvalid78;
	}

	public void setIsvalid78(Short isvalid78)
	{
		this.isvalid78 = isvalid78;
	}

	public Short getIsvalid79()
	{
		return isvalid79;
	}

	public void setIsvalid79(Short isvalid79)
	{
		this.isvalid79 = isvalid79;
	}

	public Short getIsvalid80()
	{
		return isvalid80;
	}

	public void setIsvalid80(Short isvalid80)
	{
		this.isvalid80 = isvalid80;
	}

	public Short getIsvalid81()
	{
		return isvalid81;
	}

	public void setIsvalid81(Short isvalid81)
	{
		this.isvalid81 = isvalid81;
	}

	public Short getIsvalid82()
	{
		return isvalid82;
	}

	public void setIsvalid82(Short isvalid82)
	{
		this.isvalid82 = isvalid82;
	}

	public Short getIsvalid83()
	{
		return isvalid83;
	}

	public void setIsvalid83(Short isvalid83)
	{
		this.isvalid83 = isvalid83;
	}

	public Short getIsvalid84()
	{
		return isvalid84;
	}

	public void setIsvalid84(Short isvalid84)
	{
		this.isvalid84 = isvalid84;
	}

	public Short getIsvalid85()
	{
		return isvalid85;
	}

	public void setIsvalid85(Short isvalid85)
	{
		this.isvalid85 = isvalid85;
	}

	public Short getIsvalid86()
	{
		return isvalid86;
	}

	public void setIsvalid86(Short isvalid86)
	{
		this.isvalid86 = isvalid86;
	}

	public Short getIsvalid87()
	{
		return isvalid87;
	}

	public void setIsvalid87(Short isvalid87)
	{
		this.isvalid87 = isvalid87;
	}

	public Short getIsvalid88()
	{
		return isvalid88;
	}

	public void setIsvalid88(Short isvalid88)
	{
		this.isvalid88 = isvalid88;
	}

	public Short getIsvalid89()
	{
		return isvalid89;
	}

	public void setIsvalid89(Short isvalid89)
	{
		this.isvalid89 = isvalid89;
	}

	public Short getIsvalid90()
	{
		return isvalid90;
	}

	public void setIsvalid90(Short isvalid90)
	{
		this.isvalid90 = isvalid90;
	}

	public Short getIsvalid91()
	{
		return isvalid91;
	}

	public void setIsvalid91(Short isvalid91)
	{
		this.isvalid91 = isvalid91;
	}

	public Short getIsvalid92()
	{
		return isvalid92;
	}

	public void setIsvalid92(Short isvalid92)
	{
		this.isvalid92 = isvalid92;
	}

	public Short getIsvalid93()
	{
		return isvalid93;
	}

	public void setIsvalid93(Short isvalid93)
	{
		this.isvalid93 = isvalid93;
	}

	public Short getIsvalid94()
	{
		return isvalid94;
	}

	public void setIsvalid94(Short isvalid94)
	{
		this.isvalid94 = isvalid94;
	}

	public Short getIsvalid95()
	{
		return isvalid95;
	}

	public void setIsvalid95(Short isvalid95)
	{
		this.isvalid95 = isvalid95;
	}

	public Short getIsvalid96()
	{
		return isvalid96;
	}

	public void setIsvalid96(Short isvalid96)
	{
		this.isvalid96 = isvalid96;
	}

	public Short getIsvalid97()
	{
		return isvalid97;
	}

	public void setIsvalid97(Short isvalid97)
	{
		this.isvalid97 = isvalid97;
	}

	public Short getIsvalid98()
	{
		return isvalid98;
	}

	public void setIsvalid98(Short isvalid98)
	{
		this.isvalid98 = isvalid98;
	}

	public Short getIsvalid99()
	{
		return isvalid99;
	}

	public void setIsvalid99(Short isvalid99)
	{
		this.isvalid99 = isvalid99;
	}

	public Short getIsvalid100()
	{
		return isvalid100;
	}

	public void setIsvalid100(Short isvalid100)
	{
		this.isvalid100 = isvalid100;
	}

	public String getSuplevels31()
	{
		return suplevels31;
	}

	public void setSuplevels31(String suplevels31)
	{
		this.suplevels31 = suplevels31;
	}

	public Long getValidnum()
	{
		return validnum;
	}

	public void setValidnum(Long validnum)
	{
		this.validnum = validnum;
	}

	public Short getIsvalid()
	{
		return isvalid;
	}

	public void setIsvalid(Short isvalid)
	{
		this.isvalid = isvalid;
	}

	public Short getUserspecialflag()
	{
		return userspecialflag;
	}

	public void setUserspecialflag(Short userspecialflag)
	{
		this.userspecialflag = userspecialflag;
	}

}