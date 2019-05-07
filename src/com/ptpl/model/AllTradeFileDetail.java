package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 批量接口 全流水文件
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年5月13日 上午10:08:31 
 *
 */
public class AllTradeFileDetail {
	//ID
    private BigDecimal id;
    //银行号
    private String bank;
    //电子账号
    private String cardnbr;
    // 交易金额
    private BigDecimal amount;
    //货币代码
    private Integer curNum;
    //交易金额符号 小于零等于C，大于零等于D
    private String crflag;
    //入账日期 YYYYMMDD
    private String valdate;
    //交易日期 YYYYMMDD
    private String inpdate;
    //自然日期 YYYYMMDD
    private String reldate;
    //交易时间 HH24MISSTT
    private String inptime;
    //交易流水号 卡系统内部流水号
    private String tranno;
    //关联交易流水号，该交易为手续费时，关联流水号为原交易的流水号
    private String oriTranno;
    //交易类型，参考<<交易类型与描述对应表>>
    private String transtype;
    //交易描述
    private String desline;
    //交易后余额
    private BigDecimal currBal;
    //对手交易账号
    private String forcardnbr;
    //冲正、撤销标志  1-已撤销/冲正    空或0-正常交易
    private Short revind;
    //保留域
    private String resv;
    //文件名称
    private String filename;
    //录入时间
    private Date entertime;
    //备注
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getCardnbr() {
        return cardnbr;
    }

    public void setCardnbr(String cardnbr) {
        this.cardnbr = cardnbr == null ? null : cardnbr.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCurNum() {
        return curNum;
    }

    public void setCurNum(Integer curNum) {
        this.curNum = curNum;
    }

    public String getCrflag() {
        return crflag;
    }

    public void setCrflag(String crflag) {
        this.crflag = crflag == null ? null : crflag.trim();
    }

    public String getValdate() {
        return valdate;
    }

    public void setValdate(String valdate) {
        this.valdate = valdate == null ? null : valdate.trim();
    }

    public String getInpdate() {
        return inpdate;
    }

    public void setInpdate(String inpdate) {
        this.inpdate = inpdate == null ? null : inpdate.trim();
    }

    public String getReldate() {
        return reldate;
    }

    public void setReldate(String reldate) {
        this.reldate = reldate == null ? null : reldate.trim();
    }

    public String getInptime() {
        return inptime;
    }

    public void setInptime(String inptime) {
        this.inptime = inptime == null ? null : inptime.trim();
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public String getOriTranno() {
        return oriTranno;
    }

    public void setOriTranno(String oriTranno) {
        this.oriTranno = oriTranno == null ? null : oriTranno.trim();
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype == null ? null : transtype.trim();
    }

    public String getDesline() {
        return desline;
    }

    public void setDesline(String desline) {
        this.desline = desline == null ? null : desline.trim();
    }

    public BigDecimal getCurrBal() {
        return currBal;
    }

    public void setCurrBal(BigDecimal currBal) {
        this.currBal = currBal;
    }

    public String getForcardnbr() {
        return forcardnbr;
    }

    public void setForcardnbr(String forcardnbr) {
        this.forcardnbr = forcardnbr == null ? null : forcardnbr.trim();
    }

    public Short getRevind() {
        return revind;
    }

    public void setRevind(Short revind) {
        this.revind = revind;
    }

    public String getResv() {
        return resv;
    }

    public void setResv(String resv) {
        this.resv = resv == null ? null : resv.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Date getEntertime() {
        return entertime;
    }

    public void setEntertime(Date entertime) {
        this.entertime = entertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}