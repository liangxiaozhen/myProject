package com.ptpl.model;

/**
 * 
 * @ClassName: AheadRepayMentCalculatedParameter
 * @Package com.ptpl.model
 * @Description: TODO(提前还款 提前实际还款记录计算利息的参数(数据库没有这个表，这个实体类只是为了方便提前还款实际金额记录))
 * @author cjm
 * @date 2017年1月5日 上午9:36:28
 * @version V1.0
 */
public class AheadRepayMentCalculatedParameter {
	/* 投资人当期持有本金利息 */
	private Double holdinterest;
	/* 投资人当期持有类现金利息 */
	private Double holdrvoucherint;
	/* 投资人当期持有假现金利息 */
	private Double holdrlvoucherint;
	/* 投资人当期持有加息券利息 */
	private Double holdrintfee;
	/* 投资人当期欠收本金利息 */
	private Double badcropinterest;
	/* 投资人当期欠收类现金利息 */
	private Double badcroprvoucherint;
	/* 投资人当期欠收假现金利息 */
	private Double badcroprlvoucherint;
	/* 投资人当期欠收加息券利息 */
	private Double badcroprintfee;
	/*提前还款实际作废类现金的利息*/
	private Double discardvoucherint;
	/*提前还款欠收作废类现金的利息*/
	private Double norecdiscardvoucherint;

	public Double getHoldinterest() {
		return holdinterest;
	}

	public void setHoldinterest(Double holdinterest) {
		this.holdinterest = holdinterest;
	}

	public Double getHoldrvoucherint() {
		return holdrvoucherint;
	}

	public void setHoldrvoucherint(Double holdrvoucherint) {
		this.holdrvoucherint = holdrvoucherint;
	}

	public Double getHoldrlvoucherint() {
		return holdrlvoucherint;
	}

	public void setHoldrlvoucherint(Double holdrlvoucherint) {
		this.holdrlvoucherint = holdrlvoucherint;
	}

	public Double getHoldrintfee() {
		return holdrintfee;
	}

	public void setHoldrintfee(Double holdrintfee) {
		this.holdrintfee = holdrintfee;
	}

	public Double getBadcropinterest() {
		return badcropinterest;
	}

	public void setBadcropinterest(Double badcropinterest) {
		this.badcropinterest = badcropinterest;
	}

	public Double getBadcroprvoucherint() {
		return badcroprvoucherint;
	}

	public void setBadcroprvoucherint(Double badcroprvoucherint) {
		this.badcroprvoucherint = badcroprvoucherint;
	}

	public Double getBadcroprlvoucherint() {
		return badcroprlvoucherint;
	}

	public void setBadcroprlvoucherint(Double badcroprlvoucherint) {
		this.badcroprlvoucherint = badcroprlvoucherint;
	}

	public Double getBadcroprintfee() {
		return badcroprintfee;
	}

	public void setBadcroprintfee(Double badcroprintfee) {
		this.badcroprintfee = badcroprintfee;
	}

	public Double getDiscardvoucherint() {
		return discardvoucherint;
	}

	public void setDiscardvoucherint(Double discardvoucherint) {
		this.discardvoucherint = discardvoucherint;
	}

	public Double getNorecdiscardvoucherint() {
		return norecdiscardvoucherint;
	}

	public void setNorecdiscardvoucherint(Double norecdiscardvoucherint) {
		this.norecdiscardvoucherint = norecdiscardvoucherint;
	}

	 
  
	
}
