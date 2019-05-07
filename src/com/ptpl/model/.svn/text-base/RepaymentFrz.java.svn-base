package com.ptpl.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.web.util.StringUtil;

/**
 * 
 * @ClassName: RepaymentFrz
 * @Description: TODO(还款批量冻结解冻记录)
 * @author cjm
 * @date 2017年4月21日 下午4:48:00
 *
 */
public class RepaymentFrz {
	/*ID(主键）*/
	private BigDecimal id;
	/*用户ID*/
	private BigDecimal baseid;
	/*冻结提交时间*/
	private Date frztime;
	/*冻结返回时间*/
	private Date returntime;
	/*还款批次号 作为还款记录表批次号*/
	private String batchno;
	/*冻结申请流水号*/
	private String serino;
	/*冻结金额*/
	private BigDecimal amount;
	/*标的编号*/
	private String product;
	/*备注*/
	private String remark;
	/*解冻申请流水号*/
	private String thawserino;
	/*解冻提交时间*/
	private Date thawtime;
	/*解冻返回时间*/
	private Date thawreturntime;
	/*还款状态(0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8解冻成功 )*/
	private Short status;
	/*成功笔数*/
	private Integer successcount = 0;
	/*失败笔数*/
	private Integer failcount = 0;
	/*冻结银行返回码*/
	private String retcode;
	/*解冻银行返回码*/
	private String thawretcode;
	/*是否提交（1是，0否）*/
	private Short issubmit;
	/*是否系统勾兑（0未勾兑，1已勾兑） 勾兑冻结记录*/
	private Short isblending;
	/*是否人工勾兑（0未勾兑，1已勾兑）*/
	private Short ismanblending;
	/*系统勾兑时间*/
	private Date sysbtime;
	/*人工勾兑时间*/
	private Date manbtime;
	/*系统勾兑接收数据时间 第一次*/
	private Date sysrectime;
	/*人工勾兑接收数据时间 第一次*/
	private Date receivetime;
	/*请求数据包*/
	private String reqquerydata;
	/*接收数据包*/
	private String recresultdata;
	/*借款人提交还款时间*/
	private Date submittime;
	/*还款实际到账日期*/
	private Date rprealtime;
	/*是否审核 1是，0否*/
	private Short isaudit;
	/*审核人  无需审核时，审核人为系统*/
	private String auditman;
	/*审核时间*/
	private Date audittime;
    /*电子账号*/
	private String  cardnbr;
	/*是否债转还款*/
	private Short  isdarepay;
	/*是否提前（0否，1是）*/
	private Short isahead;
	/*是否代偿（1是，0否） */
	private Short isproxyrepay;
	/*是否逾期 0否，1是*/
	private Short isoverdue;
	
	private Integer count;
	
	private UserBaseAccountInfo useroutaccountid;//借款用户
 	private TenderItem tenderitemtenderid;//标的
	
	/**
	 * 以下数据库无此字段  用于前端显示
	 */
 	private String frztimestr;
	private String returntimestr;
	private String thawtimestr;
	private String thawreturntimestr;
	private String outloginname;//借款人
	private String tno;//标号
	private String tname;//标名称
	private Short statusisaudanddealwait;//审核中 或者 待处理状态的信息
	private Short failandwaitstatus;;//取消 或者 冻结成功状态的信息
	
 	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getBaseid() {
		return baseid;
	}

	public void setBaseid(BigDecimal baseid) {
		this.baseid = baseid;
	}
 
	public Date getFrztime() {
		return frztime;
	}

	public void setFrztime(Date frztime) {
		this.frztime = frztime;
	}

	public Date getReturntime() {
		return returntime;
	}

	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
 
	public String getSerino() {
		return serino;
	}

	public void setSerino(String serino) {
		this.serino = serino;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
 
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getThawserino() {
		return thawserino;
	}

	public void setThawserino(String thawserino) {
		this.thawserino = thawserino;
	}

	public Date getThawtime() {
		return thawtime;
	}

	public void setThawtime(Date thawtime) {
		this.thawtime = thawtime;
	}

	public Date getThawreturntime() {
		return thawreturntime;
	}

	public void setThawreturntime(Date thawreturntime) {
		this.thawreturntime = thawreturntime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(Integer successcount) {
		this.successcount = successcount;
	}

	public Integer getFailcount() {
		return failcount;
	}

	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public Short getIssubmit() {
		return issubmit;
	}

	public void setIssubmit(Short issubmit) {
		this.issubmit = issubmit;
	}

	public Short getIsblending() {
		return isblending;
	}

	public void setIsblending(Short isblending) {
		this.isblending = isblending;
	}

	public Short getIsmanblending() {
		return ismanblending;
	}

	public void setIsmanblending(Short ismanblending) {
		this.ismanblending = ismanblending;
	}

	public Date getSysbtime() {
		return sysbtime;
	}

	public void setSysbtime(Date sysbtime) {
		this.sysbtime = sysbtime;
	}

	public Date getManbtime() {
		return manbtime;
	}

	public void setManbtime(Date manbtime) {
		this.manbtime = manbtime;
	}

	public Date getSysrectime() {
		return sysrectime;
	}

	public void setSysrectime(Date sysrectime) {
		this.sysrectime = sysrectime;
	}

	public Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}

	public String getReqquerydata() {
		return reqquerydata;
	}

	public void setReqquerydata(String reqquerydata) {
		this.reqquerydata = reqquerydata;
	}

	public String getRecresultdata() {
		return recresultdata;
	}

	public void setRecresultdata(String recresultdata) {
		this.recresultdata = recresultdata;
	}

	public Date getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	public Date getRprealtime() {
		return rprealtime;
	}

	public void setRprealtime(Date rprealtime) {
		this.rprealtime = rprealtime;
	}

	public Short getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Short isaudit) {
		this.isaudit = isaudit;
	}

	public String getAuditman() {
		return auditman;
	}

	public void setAuditman(String auditman) {
		this.auditman = auditman;
	}

	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
 

	public String getFrztimestr() {
		return frztimestr;
	}

	public void setFrztimestr(String frztimestr) {
		this.frztimestr = frztimestr;
	}

	public String getReturntimestr() {
		return returntimestr;
	}

	public void setReturntimestr(String returntimestr) {
		this.returntimestr = returntimestr;
	}

	public String getThawtimestr() {
		return thawtimestr;
	}

	public void setThawtimestr(String thawtimestr) {
		this.thawtimestr = thawtimestr;
	}

	public String getThawreturntimestr() {
		return thawreturntimestr;
	}

	public void setThawreturntimestr(String thawreturntimestr) {
		this.thawreturntimestr = thawreturntimestr;
	}

	public String getCardnbr() {
		return cardnbr;
	}

	public void setCardnbr(String cardnbr) {
		this.cardnbr = cardnbr;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getThawretcode() {
		return thawretcode;
	}

	public void setThawretcode(String thawretcode) {
		this.thawretcode = thawretcode;
	}

	public Short getIsahead() {
		return isahead;
	}

	public void setIsahead(Short isahead) {
		this.isahead = isahead;
	}

	public Short getIsproxyrepay() {
		return isproxyrepay;
	}

	public void setIsproxyrepay(Short isproxyrepay) {
		this.isproxyrepay = isproxyrepay;
	}

	public Short getIsoverdue() {
		return isoverdue;
	}

	public void setIsoverdue(Short isoverdue) {
		this.isoverdue = isoverdue;
	}

	public Short getIsdarepay() {
		return isdarepay;
	}

	public void setIsdarepay(Short isdarepay) {
		this.isdarepay = isdarepay;
	}

	public UserBaseAccountInfo getUseroutaccountid() {
		return useroutaccountid;
	}

	public void setUseroutaccountid(UserBaseAccountInfo useroutaccountid) {
		this.useroutaccountid = useroutaccountid;
	}
 
	public TenderItem getTenderitemtenderid() {
		return tenderitemtenderid;
	}

	public void setTenderitemtenderid(TenderItem tenderitemtenderid) {
		this.tenderitemtenderid = tenderitemtenderid;
	}

	public String getOutloginname() {
		return outloginname;
	}

	public void setOutloginname(String outloginname) {
		this.outloginname = outloginname;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Short getStatusisaudanddealwait() {
		return statusisaudanddealwait;
	}

	public void setStatusisaudanddealwait(Short statusisaudanddealwait) {
		this.statusisaudanddealwait = statusisaudanddealwait;
	}

	public Short getFailandwaitstatus() {
		return failandwaitstatus;
	}

	public void setFailandwaitstatus(Short failandwaitstatus) {
		this.failandwaitstatus = failandwaitstatus;
	}
	 
}