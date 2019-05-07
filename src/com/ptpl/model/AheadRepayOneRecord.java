package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: AheadRepayOneRecord 
* @Package com.ptpl.model 
* @Description: TODO(标的提前还款奖励单个投资人记录 实体类) 
* @author cjm
* @date 2016年10月21日 上午10:30:15 
* @version V1.0
 */
public class AheadRepayOneRecord {
	/*ID（主键）*/
    private BigDecimal id;
    /*标号ID*/
    private BigDecimal tenderid;
    /*提前还款奖励流水号*/
    private String arorderno;
    /*标的提前还款奖励设置表Id*/
    private BigDecimal arid;
    /*还款流水号*/
    private String rorderno;
    /*单个投资人本金欠收利息*/
    private Double prinpoorint;
    /*PRIN借款人罚金*/
    private Double bpenalty;
    
    /*平台奖励奖品*/
    private String pawardname;
    /*平台奖励奖品编号*/
    private String pawardno;
    /*平台奖励奖品数*/
    private Integer pawardcount;
    
    /*罚金奖励方式（1定额，2百分比，3最高）*/
    private Short penaltytype;
    /*是否处理（0否，1是*/
    private Short isgrant;
    /*是否审核 0未审核 1审核通过  2审核不通过*/
    private Short isaudit;
    /*审核时间*/
    private Date audittime;
    /*审核人*/
    private String auditman;
    /*创建时间*/
    private Date madetime;
    /*处理日期*/
    private Date payoutdate;
    /*备注*/
    private String remark;
    /*借款用户ID*/
    private BigDecimal bmanid;
    /*投资人ID*/
    private BigDecimal investorid;
    /*提前还款批次号*/
    private String arbatchno;
    /*以下字段 数据库无此字段  用于页面显示*/
    private String bmanidusername;//借款人用户名
    private String investoridusername;//投资人用户名
    private String tno;//标号
    private String tname;//标名称
     
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTenderid() {
        return tenderid;
    }

    public void setTenderid(BigDecimal tenderid) {
        this.tenderid = tenderid;
    }

    public String getArorderno() {
        return arorderno;
    }

    public void setArorderno(String arorderno) {
        this.arorderno = arorderno == null ? null : arorderno.trim();
    }

    public BigDecimal getArid() {
        return arid;
    }

    public void setArid(BigDecimal arid) {
        this.arid = arid;
    }

    public String getRorderno() {
        return rorderno;
    }

    public void setRorderno(String rorderno) {
        this.rorderno = rorderno == null ? null : rorderno.trim();
    }

    public Double getPrinpoorint() {
        return prinpoorint;
    }

    public void setPrinpoorint(Double prinpoorint) {
        this.prinpoorint = prinpoorint;
    }

    public Double getBpenalty() {
        return bpenalty;
    }

    public void setBpenalty(Double bpenalty) {
        this.bpenalty = bpenalty;
    }

    public Short getPenaltytype() {
        return penaltytype;
    }

    public void setPenaltytype(Short penaltytype) {
        this.penaltytype = penaltytype;
    }

    public Short getIsgrant() {
        return isgrant;
    }

    public void setIsgrant(Short isgrant) {
        this.isgrant = isgrant;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    public Date getMadetime() {
        return madetime;
    }

    public void setMadetime(Date madetime) {
        this.madetime = madetime;
    }

    public Date getPayoutdate() {
        return payoutdate;
    }

    public void setPayoutdate(Date payoutdate) {
        this.payoutdate = payoutdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public BigDecimal getBmanid() {
		return bmanid;
	}

	public void setBmanid(BigDecimal bmanid) {
		this.bmanid = bmanid;
	}

	public BigDecimal getInvestorid() {
		return investorid;
	}

	public void setInvestorid(BigDecimal investorid) {
		this.investorid = investorid;
	}

	public String getBmanidusername() {
		return bmanidusername;
	}

	public void setBmanidusername(String bmanidusername) {
		this.bmanidusername = bmanidusername;
	}

	public String getInvestoridusername() {
		return investoridusername;
	}

	public void setInvestoridusername(String investoridusername) {
		this.investoridusername = investoridusername;
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

	public String getPawardname() {
		return pawardname;
	}

	public void setPawardname(String pawardname) {
		this.pawardname = pawardname;
	}

	public String getPawardno() {
		return pawardno;
	}

	public void setPawardno(String pawardno) {
		this.pawardno = pawardno;
	}

	public Integer getPawardcount() {
		return pawardcount;
	}

	public void setPawardcount(Integer pawardcount) {
		this.pawardcount = pawardcount;
	}

	public String getArbatchno() {
		return arbatchno;
	}

	public void setArbatchno(String arbatchno) {
		this.arbatchno = arbatchno;
	}
    
    
}