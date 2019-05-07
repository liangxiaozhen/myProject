package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author liuj
 * 提前还款方式设置
 */
public class AheadRepayMode {

    private BigDecimal id;//主键

    private BigDecimal tid;//标号id

    private Short repaytype;//还款方式(等额本金，等额本息，先息后本，一次性还本付息)

    private Short arepaymode;//提前还款类型(1全部，2部分)

    private Integer aperiods;//1当期，多期（填写具体数字）

    private Short intmode;//还利息方式(1占天利息，2全额利息)

    private String addman;//添加人

    private Date addtime;//设置时间

    private String remark;//备注



    private Short intmodetian;//占天,数据库没有这个字段

    private Short intmodequane;//全额,数据库没有这个字段

    private Integer aperiodqi;//期数,数据库没有这个字段

    private TenderItem tenderitem;//引用类型对象 标设置对象

    public TenderItem getTenderitem() {
        return tenderitem;
    }

    public void setTenderitem(TenderItem tenderitem) {
        this.tenderitem = tenderitem;
    }

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

    public Short getRepaytype() {
        return repaytype;
    }

    public void setRepaytype(Short repaytype) {
        this.repaytype = repaytype;
    }

    public Short getArepaymode() {
        return arepaymode;
    }

    public void setArepaymode(Short arepaymode) {
        this.arepaymode = arepaymode;
    }

    public Integer getAperiods() {
        return aperiods;
    }

    public void setAperiods(Integer aperiods) {
        this.aperiods = aperiods;
    }

    public Short getIntmode() {
        return intmode;
    }

    public void setIntmode(Short intmode) {
        this.intmode = intmode;
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

    public Integer getAperiodqi() {
        return aperiodqi;
    }

    public void setAperiodqi(Integer aperiodqi) {
        this.aperiodqi = aperiodqi;
    }

    public Short getIntmodetian() {
        return intmodetian;
    }

    public void setIntmodetian(Short intmodetian) {
        this.intmodetian = intmodetian;
    }

    public Short getIntmodequane() {
        return intmodequane;
    }

    public void setIntmodequane(Short intmodequane) {
        this.intmodequane = intmodequane;
    }
}