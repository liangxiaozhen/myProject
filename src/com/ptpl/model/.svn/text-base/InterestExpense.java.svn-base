package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author：liuqh
 * @Description:标的利息管理费设置
 */ 
public class InterestExpense {
	
	/**
	 * 20170624  cjm 
	 * 
	 * 1 需求更改 以下字段作废处理：【
 	 * Tid  标号Id
	 * IntExpNo 利息管理费编号
	 * IERecManId 利息管理费收款人Id
	 * IERecMan 利息管理费收款人
	 * IEQuota  利息管理费定额
	 * minIEFee  该段最低利息管理收费金额
	 * 】
	 * 
	 * 2 暂时字段【
	 * Id 		ID（主键）
	 * GFIType  计算方式（1根据用户等级，2根据标的风险等级）
	 * TType    标的类型（1000000…） 30位
	 * uGrade   会员等级(100000000000000000000000000000…)		51位
	 * IEPercent 利息管理费百份比
	 * maxIEFee  该段最高利息管理收费金额
	 * isAudit   资金清算是否需要审核
	 * isTemplet  是否为模板（0否，1是）
	 * addMan     添加人
	 * addTime   设置时间
	 * remark    备注
 	 * 】
 	 * userGraderSel
 	 * tendRiskSel
	 */
    private BigDecimal id;//主键
     
    private Short gfitype;//计算方式（1根据用户等级，2根据标的风险等级
    
    private String ttype;//标的类型（1000000…） 30位
    
    private String ugrade;//会员等级(0000000000....) 51位     00000000000000000000000000000000000000  111111111111111111111111111111111111111

    private Double iepercent;//利息管理费百份比

    private Double maxiefee;//该段最高利息管理收费金额

    private Short isaudit;//资金清算是否需要审核 0否 1是
    
    private Short istemplet;//是否为模板（0否，1是）
     
    private String addman;//添加人
    
    private Date addtime;//添加时间
    
    private String remark;//备注
    
    private List<InterestExpense> expenses;//获取数据
    
    private String intexpno;//利息管理费编号  作废20170624 cjm

    private String ierecman;//利息管理费收款人  作废20170624 cjm

    private Double miniefee;//该段最低利息管理收费金额  作废20170624 cjm
    
    private BigDecimal tid;//标号id   作废20170624 cjm
    
    private BigDecimal ierecmanid;//利息管理费收款人Id   作废20170624 cjm
   
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
    
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getIntexpno() {
        return intexpno;
    }

    public void setIntexpno(String intexpno) {
        this.intexpno = intexpno == null ? null : intexpno.trim();
    }

    public String getIerecman() {
        return ierecman;
    }

    public void setIerecman(String ierecman) {
        this.ierecman = ierecman == null ? null : ierecman.trim();
    }

    public String getUgrade() {
        return ugrade;
    }

    public void setUgrade(String ugrade) {
        this.ugrade = ugrade;
    }

    public Double getIepercent() {
        return iepercent;
    }

    public void setIepercent(Double iepercent) {
        this.iepercent = iepercent;
    }

    public Double getMiniefee() {
        return miniefee;
    }

    public void setMiniefee(Double miniefee) {
        this.miniefee = miniefee;
    }

    public Double getMaxiefee() {
        return maxiefee;
    }

    public void setMaxiefee(Double maxiefee) {
        this.maxiefee = maxiefee;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

	@Override
	public String toString() {
		return "InterestExpense [id=" + id + ", intexpno=" + intexpno
				+ ", ierecman=" + ierecman + ", ugrade=" + ugrade
				+ ", iepercent=" + iepercent
				+ ", miniefee=" + miniefee + ", maxiefee=" + maxiefee
				+ ", istemplet=" + istemplet + ",tid="+tid+",ierecmanid="+ierecmanid+","
				+ "isaudit="+isaudit+",addman="+addman+",addtime="+addtime+",remark="+remark+"]";
	}

	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public BigDecimal getIerecmanid() {
		return ierecmanid;
	}

	public void setIerecmanid(BigDecimal ierecmanid) {
		this.ierecmanid = ierecmanid;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
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

	public List<InterestExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<InterestExpense> expenses) {
		this.expenses = expenses;
	}

	public Short getGfitype() {
		return gfitype;
	}

	public void setGfitype(Short gfitype) {
		this.gfitype = gfitype;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
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

	public String getTtypestr() {
		return ttypestr;
	}

	public void setTtypestr(String ttypestr) {
		this.ttypestr = ttypestr;
	}

	public String getSubugradestr() {
		return subugradestr;
	}

	public void setSubugradestr(String subugradestr) {
		this.subugradestr = subugradestr;
	}

	public String getSubttypestr() {
		return subttypestr;
	}

	public void setSubttypestr(String subttypestr) {
		this.subttypestr = subttypestr;
	}

	public List<UserGrade> getUsergrades() {
		return usergrades;
	}

	public void setUsergrades(List<UserGrade> usergrades) {
		this.usergrades = usergrades;
	}

	public List<LoanTypeObjectQuote> getLoantypeobjectquotes() {
		return loantypeobjectquotes;
	}

	public void setLoantypeobjectquotes(List<LoanTypeObjectQuote> loantypeobjectquotes) {
		this.loantypeobjectquotes = loantypeobjectquotes;
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
    
	
}