package com.ptpl.controller.huishang.model;

import java.util.Date;

/**
 * P2P产品融资扣款处理结果类
 * @ClassName: FinTranResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenglm
 * @date 2017年4月20日 下午3:01:14
 *
 */
public class FinTranResult {
	/** 批次号 */
	private String batch;
	/** 业务类别 */
	private String type;
	/** 日期 */
	private Date date;
	/** 处理响应码 */
	private String rspcode;
	/** 扣款电子账号 */
	private String cardnbro;
	/** 扣账（本金）金额 */
	private Double amount;
	/** 扣账利息金额 */
	private Double intamt;
	/** 实际扣账金额 */
	private Double billamount;
	/** 转入电子账号 */
	private String cardnbri;
	/** 币种 */
	private String curr;
	/** 转出方手续费扣款方式 */
	private Short outfeeway;
	/** 转出方手续费扣款金额 */
	private Double outfeeamt;
	/** 转出方手续费实际扣款金额 */
	private Double routfeeamt;
	/** 转入方手续费扣款方式 */
	private Short infeeway;
	/** 转入方手续费扣款金额 */
	private Double infeeamt;
	/** 转入方手续费实际扣款金额 */
	private Double rifeeamt;
	/** 标的编号 */
	private String product;
	/** 投标申请流水号 */
	private String serialno;
	/** 第三方保留域 */
	private String thdrese;
	/** 保留域 */
	private String rese;
	
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRspcode() {
		return rspcode;
	}
	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}
	public String getCardnbro() {
		return cardnbro;
	}
	public void setCardnbro(String cardnbro) {
		this.cardnbro = cardnbro;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getIntamt() {
		return intamt;
	}
	public void setIntamt(Double intamt) {
		this.intamt = intamt;
	}
	public Double getBillamount() {
		return billamount;
	}
	public void setBillamount(Double billamount) {
		this.billamount = billamount;
	}
	public String getCardnbri() {
		return cardnbri;
	}
	public void setCardnbri(String cardnbri) {
		this.cardnbri = cardnbri;
	}
	public String getCurr() {
		return curr;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
	public Short getOutfeeway() {
		return outfeeway;
	}
	public void setOutfeeway(Short outfeeway) {
		this.outfeeway = outfeeway;
	}
	public Double getOutfeeamt() {
		return outfeeamt;
	}
	public void setOutfeeamt(Double outfeeamt) {
		this.outfeeamt = outfeeamt;
	}
	public Double getRoutfeeamt() {
		return routfeeamt;
	}
	public void setRoutfeeamt(Double routfeeamt) {
		this.routfeeamt = routfeeamt;
	}
	public Short getInfeeway() {
		return infeeway;
	}
	public void setInfeeway(Short infeeway) {
		this.infeeway = infeeway;
	}
	public Double getInfeeamt() {
		return infeeamt;
	}
	public void setInfeeamt(Double infeeamt) {
		this.infeeamt = infeeamt;
	}
	public Double getRifeeamt() {
		return rifeeamt;
	}
	public void setRifeeamt(Double rifeeamt) {
		this.rifeeamt = rifeeamt;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getThdrese() {
		return thdrese;
	}
	public void setThdrese(String thdrese) {
		this.thdrese = thdrese;
	}
	public String getRese() {
		return rese;
	}
	public void setRese(String rese) {
		this.rese = rese;
	}

}
