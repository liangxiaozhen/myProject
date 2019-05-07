package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author liuj
 * 标的债权转让手续费设置表
 */
public class DebtAttornFee {
	
    private BigDecimal id;//主键

    private BigDecimal tid;//标号id

    private String debtattornfno;//债全转让手续费编号

    private Short isadafeeon;//债转手续费开关(1.开,0,关)

    private Short feemode;//债转手续费计算方式(1.用户等级2.投标时间)

    private Integer hadday;//持有时间(单位：天)

    private String ugrade;//转让人会员等级(手续费收取设置1111111....)

    private Double minattornmoney;//债转金额低值

    private Double maxattornmoney;//债转金额高值

    private Double quota;//定额收费

    private Double attornrate;//收取债转金额百分比

    private Double minfee;//收取最低值

    private Double maxfee;//收取最高值

    private Short isaudit;//资金清算是否需要审核

    private Short istemplet;//是否为模板

    private String addman;//添加人

    private Date addtime;//设置时间

    private String remark;//备注
    
    private List<DebtAttornFee> debtAttornFees;//获取前台数据
    

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getTid() {
        return tid;
    }

    public void setTid(BigDecimal tid) {
        this.tid = tid;
    }

    public String getDebtattornfno() {
        return debtattornfno;
    }

    public void setDebtattornfno(String debtattornfno) {
        this.debtattornfno = debtattornfno == null ? null : debtattornfno.trim();
    }

    public Short getIsadafeeon() {
        return isadafeeon;
    }

    public void setIsadafeeon(Short isadafeeon) {
        this.isadafeeon = isadafeeon;
    }

    public Short getFeemode() {
        return feemode;
    }

    public void setFeemode(Short feemode) {
        this.feemode = feemode;
    }

    public Integer getHadday() {
        return hadday;
    }

    public void setHadday(Integer hadday) {
        this.hadday = hadday;
    }

    public String getUgrade() {
        return ugrade;
    }

    public void setUgrade(String ugrade) {
        this.ugrade = ugrade == null ? null : ugrade.trim();
    }

    public Double getMinattornmoney() {
        return minattornmoney;
    }

    public void setMinattornmoney(Double minattornmoney) {
        this.minattornmoney = minattornmoney;
    }

    public Double getMaxattornmoney() {
        return maxattornmoney;
    }

    public void setMaxattornmoney(Double maxattornmoney) {
        this.maxattornmoney = maxattornmoney;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getAttornrate() {
        return attornrate;
    }

    public void setAttornrate(Double attornrate) {
        this.attornrate = attornrate;
    }

    public Double getMinfee() {
        return minfee;
    }

    public void setMinfee(Double minfee) {
        this.minfee = minfee;
    }

    public Double getMaxfee() {
        return maxfee;
    }

    public void setMaxfee(Double maxfee) {
        this.maxfee = maxfee;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
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

	public List<DebtAttornFee> getDebtAttornFees() {
		return debtAttornFees;
	}

	public void setDebtAttornFees(List<DebtAttornFee> debtAttornFees) {
		this.debtAttornFees = debtAttornFees;
	}


}