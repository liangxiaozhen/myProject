package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: WithdrawsCashCoupon 
* @Package com.ptpl.model 
* @Description: TODO(提抵卷活动规则表（withdrawsCashCoupon) 
* @author chenjiaming
* @date 2016年7月7日 下午2:18:41 
* @version V1.0
 */
public class WithdrawsCashCoupon {
	/*ID（主键）*/
    private BigDecimal id;
    /*Varchar2(50)活动编号*/
    private String actno;
    /*Varchar2(50)活动名称*/
    private String actname;
     /*Date活动生效日期*/
    private Date actbtime;
    private Date settime;
    /*Date活动截止日期（精确到秒）*/
    private Date actetime;
    /*Number(2)活动状态（是否启用）1已启用 0未启用*/
    private Integer status;
    /*Number(2)活动规则（1升降级只发一次，2升降级重发，取消之前奖品）*/
    private Short actrule;
    /*Varchar2(50)执行时间点（每天执行一次）*/
    private String executetime;
    /*Varchar2(200)会员等级(100000000000000000000000000000)*/
    private String ugrade;
    /*Varchar2(4000)排除名单表编号*/
    private String removenameno;
    /*Number(2)获奖名单是否需要审核才生成 1需要审核 0不需要审核*/
    private Integer isauditalist;
    /*Varchar(50)制表人*/
    private String addman;
    /*Date制表时间*/
    private Date addtime;
    /*Varchar(200)备注*/
    private String remark;
    /*处理前台传递过来的会员等级*/
    private String[] ugrades;
    /*处理前台传递过来的排除名单编号*/
    private String[] removenamenos ;
     /*返回前台活动生效日期时间字符串*/
    private String actbtimestr;
    /*返回前台活动截止日期时间字符串*/
    private String actetimestr;
    /*返回前台制表时间字符串*/
    private String addtimestr;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno == null ? null : actno.trim();
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname == null ? null : actname.trim();
    }

    public Date getSettime() {
        return settime;
    }

    public void setSettime(Date settime) {
        this.settime = settime;
    }

    public Date getActbtime() {
        return actbtime;
    }

    public void setActbtime(Date actbtime) {
        this.actbtime = actbtime;
    }

    public Date getActetime() {
        return actetime;
    }

    public void setActetime(Date actetime) {
        this.actetime = actetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Short getActrule() {
        return actrule;
    }

    public void setActrule(Short actrule) {
        this.actrule = actrule;
    }

    public String getExecutetime() {
        return executetime;
    }

    public void setExecutetime(String executetime) {
        this.executetime = executetime == null ? null : executetime.trim();
    }

    public String getUgrade() {
        return ugrade;
    }

    public void setUgrade(String ugrade) {
        this.ugrade = ugrade == null ? null : ugrade.trim();
    }

    public String getRemovenameno() {
        return removenameno;
    }

    public void setRemovenameno(String removenameno) {
        this.removenameno = removenameno == null ? null : removenameno.trim();
    }

    public Integer getIsauditalist() {
        return isauditalist;
    }

    public void setIsauditalist(Integer isauditalist) {
        this.isauditalist = isauditalist;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

	public String[] getUgrades() {
		return ugrades;
	}

	public void setUgrades(String[] ugrades) {
		this.ugrades = ugrades;
	}

	public String[] getRemovenamenos() {
		return removenamenos;
	}

	public void setRemovenamenos(String[] removenamenos) {
		this.removenamenos = removenamenos;
	}

	public String getActbtimestr() {
		return actbtimestr;
	}

	public void setActbtimestr(String actbtimestr) {
		this.actbtimestr = actbtimestr;
	}

	public String getActetimestr() {
		return actetimestr;
	}

	public void setActetimestr(String actetimestr) {
		this.actetimestr = actetimestr;
	}

	public String getAddtimestr() {
		return addtimestr;
	}

	public void setAddtimestr(String addtimestr) {
		this.addtimestr = addtimestr;
	}
    
	
	
}