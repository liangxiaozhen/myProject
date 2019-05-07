package com.ptpl.controller.huishang.model;

/**
 * 
 * @ClassName: RepayMentParamters
 * @Description: TODO(批量文件上传参数实体类)
 * @author cjm
 * @date 2017年4月24日 下午2:34:00
 *
 */
public class RepayMentParamters {

	/* 到期还款参数 */
	private String batch;// 批次号 BATCH N 6 1 6 M 对于文件名称中“XXXXXX”
	private String type;// 业务类别 TYPE N 3 7 9 M 002-到期还款 003-平台逾期代偿/担保公司代偿
	private String date;// 日期 DATE N 8 10 17 M YYYYMMDD，需与文件名中的日期一致
	private String cardnbro;// 扣款账号 CARDNBRO A 19 18 36 M 002到期还款-实际借款人/名义借款人
							// 003代偿-担保人电子账号/平台备付金账号
	private Double amount;// 扣账(本金)金额 AMOUNT N 12 37 48 C
							// "整数10位，小数2位；还款时此处填写还款本金；"
	private Double intamt;// 扣账利息金额 INTAMT N 12 49 60 C 仅到期还款时有效,整数10位，小数2位；
	private String cardnbri;// 转入账号 CARDNBRI A 19 61 79 M 向左对齐，右补空白
	private String curr;// 币种 CURR N 3 80 82 M 156：人民币；
	private String outfeeway;// 转出方手续费扣款方式 OUTFEEWAY N 1 83 83 M
								// "0：指定金额；1：同产品设置；"
	private Double outfeeamt;// 转出方手续费扣款金额 OUTFEEAMT N 11 84 94 C
								// "2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
	private String infeeway;// 转入方手续费扣款方式 INFEEWAY N 1 95 95 M "0：指定金额；1：同产品设置；"
	private Double infeeamt;// 转入方手续费扣款金额 INFEEAMT N 11 96 106 C
							// "2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
	private String product;// 标的编号 PRODUCT A 6 107 112 M 投标时使用的标的编号一致
	private String authcode;// 投标申请授权码 AUTHCODE A 20 113 132 M
							// "非自动投标时必送；自动投标时爱钱帮模式必送；"
	private String endflg;// 还款结束标志 ENDFLG A 1 133 133 C
							// "1：已结清；其它：还款中；此标志仅还款时使用；"
	private String thdrese;// 第三方保留域 THDRESE A 100 134 233 C 第三方机构扩展使用，结果文件原样返回
	private String serino;// 投标申请流水号 SERINO A 40 234 273 M 投标上送流水号
	private String rese;// 保留域 RESE A 60 274 333 C
	
	
	
	/* 到期还款参数   结果文件*/
	
	private String rspcode;//处理响应码	RSPCODE
	
 	private Double billamount;//实际扣账金额
	
  	private Double rifeeamt;//转入方手续费实际扣款金额
	
  	private Double routfeeamt;//转出方手续费实际扣款金额
 
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getOutfeeway() {
		return outfeeway;
	}

	public void setOutfeeway(String outfeeway) {
		this.outfeeway = outfeeway;
	}

	public Double getOutfeeamt() {
		return outfeeamt;
	}

	public void setOutfeeamt(Double outfeeamt) {
		this.outfeeamt = outfeeamt;
	}

	public String getInfeeway() {
		return infeeway;
	}

	public void setInfeeway(String infeeway) {
		this.infeeway = infeeway;
	}

	public Double getInfeeamt() {
		return infeeamt;
	}

	public void setInfeeamt(Double infeeamt) {
		this.infeeamt = infeeamt;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getEndflg() {
		return endflg;
	}

	public void setEndflg(String endflg) {
		this.endflg = endflg;
	}

	public String getThdrese() {
		return thdrese;
	}

	public void setThdrese(String thdrese) {
		this.thdrese = thdrese;
	}

	public String getSerino() {
		return serino;
	}

	public void setSerino(String serino) {
		this.serino = serino;
	}

	public String getRese() {
		return rese;
	}

	public void setRese(String rese) {
		this.rese = rese;
	}

	public String getRspcode() {
		return rspcode;
	}

	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}

	public Double getBillamount() {
		return billamount;
	}

	public void setBillamount(Double billamount) {
		this.billamount = billamount;
	}

	public Double getRifeeamt() {
		return rifeeamt;
	}

	public void setRifeeamt(Double rifeeamt) {
		this.rifeeamt = rifeeamt;
	}

	public Double getRoutfeeamt() {
		return routfeeamt;
	}

	public void setRoutfeeamt(Double routfeeamt) {
		this.routfeeamt = routfeeamt;
	}
	
	

}
