package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: DividedPayments 
* @Package com.ptpl.model 
* @Description: TODO(标的分期还款计划 实体类) 
* @author chenjiaming
* @date 2016年9月27日 下午4:29:45 
* @version V1.0
 */
public class DividedPayments {
	/*ID（主键）*/
    private BigDecimal id;
    /*分期还款流水号*/
    private String dporderno;
    /*标号ID*/
    private BigDecimal tenderid;
    /*期数(第几期)*/
    private Integer periods;
    /*还款日*/
    private Date repayday;
    /*当期本息 (当期总的本+息)*/
    private Double currentpi;
    /*当期本金 当期本金(cp=current period)*/
    private Double cpprincipal;
    /*当期利息*/
    private Double cpinterest;
    /*是否还款完成0未还款 1已还款 2部分还款(标的截标时生成时默认0)*/
    private Short iscomplete;
    /*备注*/
    private String remark;
    /*是否逾期1已经逾期0没有逾期(标的截标时生成时默认0)*/
    private Short isoverdue;
    /*剩余本金*/
    private Double restprincipal;
    /*是否审核 0否 1是  2 已审核 */
    private Short  isaudit;
    
    /*还款提交方式（1借款人提交，2系统提交（自动还款），3管理员代提交）*/
    private Short rsubmittype;
    /*提交还款时间（自动还款 具体操作的时间）*/
    private Date operatetime;
    /*提交还款次数*/
    private Integer rptimes;
    /*批量还款文件上传状态（0未上传，1上传成功 2 上传失败）*/
    private Short filestatus;
    
    /*用户基本信息*/
    private UserBaseAccountInfo baseAccountInfo;
    /*标的信息*/
    private TenderItem tenderItem;
    /*投资人还款计划*/
    private RepayMent ment;
     
    private Short repaybutton;//前台展示还款操作按钮 1正常还款 2未到还款日 3提前还款 4不允许提前还款 5逾期还款  数据库无此字段
    private Short isapartrepay;//前台展示部分或全部还款操作按钮  数据库无此字段
    private Integer lgperiods; //期数小于还款期数 用于提前还款 验证  数据库无此字段 
    private Short notequalsiscomplete;//用于数据条件查询 数据库无此字段 
    private String username;//用户名 数据库无此字段
    private String avlbal;//账号余额 数据库无此字段
    private Integer isauditpassnubmer;//通过审核人数 数据库无此字段
    private Double  totalamount; //本批次还款金额 数据库无此字段
    private Integer totalnubmer;//总条数 数据库无此字段
    private String tendertitle;//标题   数据库无此字段
    private Short adminrepaybutton;//管理员后台展示还款操作按钮  1正常还款  2提前还款 3 逾期还款
    
    private String tno;
    private String tname;
    private Short repaystatus;//投资人还款状态
    private Short planstatus;//投资人还款是否有效
    private Short repayisaudit;//投资人还款审核状态
    private Short repayisauditor;//投资人还款审核状态
    
    private Short repaystatusor;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDporderno() {
        return dporderno;
    }

    public void setDporderno(String dporderno) {
        this.dporderno = dporderno == null ? null : dporderno.trim();
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Date getRepayday() {
        return repayday;
    }

    public void setRepayday(Date repayday) {
        this.repayday = repayday;
    }

    public Double getCurrentpi() {
        return currentpi;
    }

    public void setCurrentpi(Double currentpi) {
        this.currentpi = currentpi;
    }

    public Double getCpprincipal() {
        return cpprincipal;
    }

    public void setCpprincipal(Double cpprincipal) {
        this.cpprincipal = cpprincipal;
    }

    public Double getCpinterest() {
        return cpinterest;
    }

    public void setCpinterest(Double cpinterest) {
        this.cpinterest = cpinterest;
    }

    public Short getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Short iscomplete) {
        this.iscomplete = iscomplete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(Short isoverdue) {
        this.isoverdue = isoverdue;
    }

	public Double getRestprincipal() {
		return restprincipal;
	}

	public void setRestprincipal(Double restprincipal) {
		this.restprincipal = restprincipal;
	}

	public Short getRepaybutton() {
		return repaybutton;
	}

	public void setRepaybutton(Short repaybutton) {
		this.repaybutton = repaybutton;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public Integer getLgperiods() {
		return lgperiods;
	}

	public void setLgperiods(Integer lgperiods) {
		this.lgperiods = lgperiods;
	}

	public Short getIsapartrepay() {
		return isapartrepay;
	}

	public void setIsapartrepay(Short isapartrepay) {
		this.isapartrepay = isapartrepay;
	}

	public Short getNotequalsiscomplete() {
		return notequalsiscomplete;
	}

	public void setNotequalsiscomplete(Short notequalsiscomplete) {
		this.notequalsiscomplete = notequalsiscomplete;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvlbal() {
		return avlbal;
	}

	public void setAvlbal(String avlbal) {
		this.avlbal = avlbal;
	}

	public Integer getIsauditpassnubmer() {
		return isauditpassnubmer;
	}

	public void setIsauditpassnubmer(Integer isauditpassnubmer) {
		this.isauditpassnubmer = isauditpassnubmer;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Integer getTotalnubmer() {
		return totalnubmer;
	}

	public void setTotalnubmer(Integer totalnubmer) {
		this.totalnubmer = totalnubmer;
	}

	public String getTendertitle() {
		return tendertitle;
	}

	public void setTendertitle(String tendertitle) {
		this.tendertitle = tendertitle;
	}

	public Short getAdminrepaybutton() {
		return adminrepaybutton;
	}

	public void setAdminrepaybutton(Short adminrepaybutton) {
		this.adminrepaybutton = adminrepaybutton;
	}

	public UserBaseAccountInfo getBaseAccountInfo() {
		return baseAccountInfo;
	}

	public void setBaseAccountInfo(UserBaseAccountInfo baseAccountInfo) {
		this.baseAccountInfo = baseAccountInfo;
	}

	public TenderItem getTenderItem() {
		return tenderItem;
	}

	public void setTenderItem(TenderItem tenderItem) {
		this.tenderItem = tenderItem;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Short getRepaystatus() {
		return repaystatus;
	}

	public void setRepaystatus(Short repaystatus) {
		this.repaystatus = repaystatus;
	}

	public Short getRepaystatusor() {
		return repaystatusor;
	}

	public void setRepaystatusor(Short repaystatusor) {
		this.repaystatusor = repaystatusor;
	}

	public RepayMent getMent() {
		return ment;
	}

	public void setMent(RepayMent ment) {
		this.ment = ment;
	}

	public Short getPlanstatus() {
		return planstatus;
	}

	public void setPlanstatus(Short planstatus) {
		this.planstatus = planstatus;
	}

	public Short getRepayisaudit() {
		return repayisaudit;
	}

	public void setRepayisaudit(Short repayisaudit) {
		this.repayisaudit = repayisaudit;
	}

	public Short getRepayisauditor() {
		return repayisauditor;
	}

	public void setRepayisauditor(Short repayisauditor) {
		this.repayisauditor = repayisauditor;
	}

	public Short getRsubmittype() {
		return rsubmittype;
	}

	public void setRsubmittype(Short rsubmittype) {
		this.rsubmittype = rsubmittype;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public Integer getRptimes() {
		return rptimes;
	}

	public void setRptimes(Integer rptimes) {
		this.rptimes = rptimes;
	}

	public Short getFilestatus() {
		return filestatus;
	}

	public void setFilestatus(Short filestatus) {
		this.filestatus = filestatus;
	}
  
	
}