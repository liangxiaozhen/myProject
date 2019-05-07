package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author：liuqh
 * @Description:标的担保费率设置
 */ 
public class GuaranteeFee {
    private BigDecimal id;
    private Short chargetype;//收费类型（1结标收取，2投标时收取）
    private Double gfpercent;//担保费百份比
    private Double gfrate;//担保费费率（投标时收取）
    private Double maxgfamount;//担保费最高收费（投标时收取）
    private Short istemplet;//是否为模板（0否，1是）
    private Short isaudit;//资金清算是否需要审核
    private BigDecimal gfrecmanid;//担保服务费收款人Id
    private String addman;//添加人
    private Date addtime;//添加时间
    private String remark;//备注
    private String ugrade;//会员等级
    private List<GuaranteeFee> guaranteefees;//获取前台传来的数据
    private BigDecimal GFIType;//   计算方式（1根据用户等级，2根据标的风险等级）
    private String TType;//     标的类型（1000000…）30位

    private List<UserGrade> usergrades;
    private List<LoanTypeObjectQuote> loantypeobjectquotes;

    /*数据库无此字段信息*/
    private String addtimestr;
    private String ugradestr;
    private String subugradestr;

    private String ttypestr;
    private String subttypestr;

    private Short isusergrades = 0;//是否全部会员
    private Short isloantypeobjectquotes = 0;//是否全部标类型

    /*需求变更后用不到的字段*/
    private String usrname;//担保公司名称,用于前端的显示，数据库没有这个字段
    private String tno;//标编号，用于封装到前端显示，在数据库中并没有此字段
    private BigDecimal tid;//标id
    private Double mingfamount;//担保费最低收费（投标时收取）
    private String guaranteefeeno;//担保费编号
    private String gfrecman;//担保服务费收款人  为用户的资金托管账号
    private Double minnmmoney;//结标分段金额低值  NM (NodeMark 结标)
    private Double maxnmmoney;//结标分段金额高值
    private Double gfquota;//担保费定额
    private Double mingffee;//该段最低担保收费金额
    private Double maxgffee;//该段最高担保收费金额



    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUgrade() {
        return ugrade;
    }

    public void setUgrade(String ugrade) {
        this.ugrade = ugrade;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGuaranteefeeno() {
        return guaranteefeeno;
    }

    public void setGuaranteefeeno(String guaranteefeeno) {
        this.guaranteefeeno = guaranteefeeno == null ? null : guaranteefeeno.trim();
    }



    public String getGfrecman() {
        return gfrecman;
    }

    public void setGfrecman(String gfrecman) {
        this.gfrecman = gfrecman == null ? null : gfrecman.trim();
    }

    public Short getChargetype() {
        return chargetype;
    }

    public void setChargetype(Short chargetype) {
        this.chargetype = chargetype;
    }

    public Double getMinnmmoney() {
        return minnmmoney;
    }

    public void setMinnmmoney(Double minnmmoney) {
        this.minnmmoney = minnmmoney;
    }

    public Double getMaxnmmoney() {
        return maxnmmoney;
    }

    public void setMaxnmmoney(Double maxnmmoney) {
        this.maxnmmoney = maxnmmoney;
    }

    public Double getGfquota() {
        return gfquota;
    }

    public void setGfquota(Double gfquota) {
        this.gfquota = gfquota;
    }

    public Double getGfpercent() {
        return gfpercent;
    }

    public void setGfpercent(Double gfpercent) {
        this.gfpercent = gfpercent;
    }

    public Double getMingffee() {
        return mingffee;
    }

    public void setMingffee(Double mingffee) {
        this.mingffee = mingffee;
    }

    public Double getMaxgffee() {
        return maxgffee;
    }

    public void setMaxgffee(Double maxgffee) {
        this.maxgffee = maxgffee;
    }

    public Double getGfrate() {
        return gfrate;
    }

    public void setGfrate(Double gfrate) {
        this.gfrate = gfrate;
    }

    public Double getMingfamount() {
        return mingfamount;
    }

    public void setMingfamount(Double mingfamount) {
        this.mingfamount = mingfamount;
    }

    public Double getMaxgfamount() {
        return maxgfamount;
    }

    public void setMaxgfamount(Double maxgfamount) {
        this.maxgfamount = maxgfamount;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public BigDecimal getGfrecmanid() {
		return gfrecmanid;
	}

	public void setGfrecmanid(BigDecimal gfrecmanid) {
		this.gfrecmanid = gfrecmanid;
	}

	public List<GuaranteeFee> getGuaranteefees() {
		return guaranteefees;
	}

	public void setGuaranteefees(List<GuaranteeFee> guaranteefees) {
		this.guaranteefees = guaranteefees;
	}

	public String getAddman() {
		return addman;
	}

	public void setAddman(String addman) {
		this.addman = addman;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public BigDecimal getGFIType() {
        return GFIType;
    }

    public void setGFIType(BigDecimal GFIType) {
        this.GFIType = GFIType;
    }

    public String  getTType() {
        return TType;
    }

    public void setTType(String TType) {
        this.TType = TType;
    }

    public List<UserGrade> getUsergrades() {
        return usergrades;
    }

    public void setUsergrades(List<UserGrade> usergrades) {
        this.usergrades = usergrades;
    }

    public String getAddtimestr() {
        return addtimestr;
    }

    public void setAddtimestr(String addtimestr) {
        this.addtimestr = addtimestr;
    }

    public String getUgradestr() {
        return ugradestr;
    }

    public void setUgradestr(String ugradestr) {
        this.ugradestr = ugradestr;
    }

    public String getSubugradestr() {
        return subugradestr;
    }

    public void setSubugradestr(String subugradestr) {
        this.subugradestr = subugradestr;
    }

    public String getTtypestr() {
        return ttypestr;
    }

    public void setTtypestr(String ttypestr) {
        this.ttypestr = ttypestr;
    }

    public String getSubttypestr() {
        return subttypestr;
    }

    public void setSubttypestr(String subttypestr) {
        this.subttypestr = subttypestr;
    }

    public Short getIsusergrades() {
        return isusergrades;
    }

    public void setIsusergrades(Short isusergrades) {
        this.isusergrades = isusergrades;
    }

    public Short getIsloantypeobjectquotes() {
        return isloantypeobjectquotes;
    }

    public void setIsloantypeobjectquotes(Short isloantypeobjectquotes) {
        this.isloantypeobjectquotes = isloantypeobjectquotes;
    }

    public List<LoanTypeObjectQuote> getLoantypeobjectquotes() {
        return loantypeobjectquotes;
    }

    public void setLoantypeobjectquotes(List<LoanTypeObjectQuote> loantypeobjectquotes) {
        this.loantypeobjectquotes = loantypeobjectquotes;
    }
}