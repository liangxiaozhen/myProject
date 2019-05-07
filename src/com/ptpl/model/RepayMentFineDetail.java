package com.ptpl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RepayMentFineDetail
 * @Package com.ptpl.model
 * @Description: TODO(提前还款 显示罚金是   显示投资人的具体信息)
 * @author cjm
 * @date 2017年2月4日 上午9:37:16
 * @version V1.0
 */
public class RepayMentFineDetail {

	/* 投资人用户名 */
	private String username;
	/* 本金 */
	private Double ramount;
	/* 本金利息 （原应得利息）*/
	private Double rinterest;
	/* 类现金 */
	private Double rvoucher;
	/* 类现金利息 */
	private Double rvoucherint;
	/*合计*/
	private Double count;
	/*逾期滞纳金*/
	private Double overdueAmount;
	/* 持有利息 */
	private Double holdrinterest;
	/* 欠收利息罚金 */
	private Double harvestfine;
	/*还款流水号*/
	private String rorderno;
	/*投标订单号*/
	private String utorderno;
	/*判断是否跳期还款  1否  0是*/
	private Short hasinvestor; 
 	
	private List<AheadRepayMentDetail> aheadRepayMentDetail;
	 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getRamount() {
		return ramount;
	}

	public void setRamount(Double ramount) {
		this.ramount = ramount;
	}

	public Double getRinterest() {
		return rinterest;
	}

	public void setRinterest(Double rinterest) {
		this.rinterest = rinterest;
	}

	public Double getHoldrinterest() {
		return holdrinterest;
	}

	public void setHoldrinterest(Double holdrinterest) {
		this.holdrinterest = holdrinterest;
	}

	public Double getHarvestfine() {
		return harvestfine;
	}

	public void setHarvestfine(Double harvestfine) {
		this.harvestfine = harvestfine;
	}

	public Double getRvoucher() {
		return rvoucher;
	}

	public void setRvoucher(Double rvoucher) {
		this.rvoucher = rvoucher;
	}

	public Double getRvoucherint() {
		return rvoucherint;
	}

	public void setRvoucherint(Double rvoucherint) {
		this.rvoucherint = rvoucherint;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(Double overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}
  	
	public List<AheadRepayMentDetail> getAheadRepayMentDetail() {
		return aheadRepayMentDetail;
	}

	public void setAheadRepayMentDetail(List<AheadRepayMentDetail> aheadRepayMentDetail) {
		this.aheadRepayMentDetail = aheadRepayMentDetail;
	}
 
 	public String getUtorderno() {
		return utorderno;
	}

	public void setUtorderno(String utorderno) {
		this.utorderno = utorderno;
	}
  
	public Short getHasinvestor() {
		return hasinvestor;
	}

	public void setHasinvestor(Short hasinvestor) {
		this.hasinvestor = hasinvestor;
	}




	public class AheadRepayMentDetail{
		/* 本金 */
		private Double ramount;
		/* 本金利息 （原应得利息）*/
		private Double rinterest;
		/*期数*/
		private Integer period;
		/*还款状态*/
		private Short repaystatus;
		/*合计*/
		private Double count;
		public Double getRamount() {
			return ramount;
		}
		public void setRamount(Double ramount) {
			this.ramount = ramount;
		}
		public Double getRinterest() {
			return rinterest;
		}
		public void setRinterest(Double rinterest) {
			this.rinterest = rinterest;
		}
		public Double getCount() {
			return count;
		}
		public void setCount(Double count) {
			this.count = count;
		}
		public Integer getPeriod() {
			return period;
		}
		public void setPeriod(Integer period) {
			this.period = period;
		}
		public Short getRepaystatus() {
			return repaystatus;
		}
		public void setRepaystatus(Short repaystatus) {
			this.repaystatus = repaystatus;
		}
		
		
	}
	
}
