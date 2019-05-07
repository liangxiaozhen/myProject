package com.ptpl.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.MMMParams;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.controller.huifu.OraceTest;
import com.ptpl.mapper.RepayMentMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.model.Plus;
import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepayMentAuditDeal;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.model.loanapp;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.InterestExpenseRecordServiceI;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.PlusPayoutRecordServiceI;
import com.ptpl.service.PlusServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBalanceQueryServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.FixedBasisMortgageUtil;
import com.ptpl.web.util.FixedPaymentMortgageUtil;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.MD5;
import com.ptpl.web.util.OneTimeServicingUtil;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 还款记录业务层
 * RepayMentServiceI
 * 创建人:chenjiaming
 * 时间：2016年09月08日 14:46:55
 * @version 1.0.0
 *
 */
public class RepayMentServiceImpl implements RepayMentServiceI{
 	
	public static Log repayMentLog = LogFactory.getLog(RepayMentServiceImpl.class);
	
 	@Autowired
	private RepayMentMapper repayMentMapper;
 	
  	@Autowired
	private TenderItemServiceI  tenderItemServiceI;//标的设置Service
  	
  	@Autowired
	private UserTenderServiceI userTenderServiceI;//投标记录Service
  	
  	@Autowired
	private InterestExpenseRecordServiceI interestExpenseRecordServiceI;  //利息管理费Service
  	
 	@Autowired
	private loanappServiceI ioanappServiceI;//借款申请 Service
 	
 	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;// 托管账号 Service
 	
 	@Autowired
	private PlusPayoutRecordServiceI plusPayoutRecordServiceI; //增益清算 记录Service
 	
	@Autowired
	private PlusServiceI plusServiceI;//标的增益设置Service
	
	@Autowired
	private DividedPaymentsServiceI dividedPaymentServiceI;  //还款计划 Service
	
 	
 	/**
	 * 
	* @Title: doInsertPlusPayoutRecord 
	* @Description: TODO(增益清算 还款时生成增益清算记录) 
	* @param @param repayMents
	* @param @param clearmode  清算方式1结标，2首期，3按期，4尾期）
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public void doInsertPlusPayoutRecord(List<RepayMent> repayMents,RepayMent repayMent,Short clearmode) {
		if(repayMents != null && repayMents.size() > 0){
			for(RepayMent repayMent2 : repayMents){
	 			 Double intprofit 				= 0.00;//加息券收益
	 			 Double voucherprofit 			= 0.00;//类现金收益
	 			 Double likevoucherprofit 		= 0.00;//假现金收益
	 			 boolean isRintfeeBoolean		= false;//是否产生加息劵利息
	 			 boolean isRlvoucherintBoolean	= false;//是否产生假现金的利息
	 			 boolean isRvoucherintBoolean	= false;//是否产生类现金的利息
	     		 if(repayMent2.getRintfee() > 0){//当期加息劵利息
	    			 intprofit = repayMent2.getRintfee(); 
	    			 isRintfeeBoolean   = true;
	 			 }
	     		 
	 			 if(repayMent2.getRlvoucherint() > 0){//当期还款假现金的利息
	 				likevoucherprofit = repayMent2.getRlvoucherint();
	 				isRlvoucherintBoolean = true;
				 }
	 			 
	 			 if(repayMent2.getRvoucherint() > 0){//当期还款类现金的利息
	 				voucherprofit = repayMent2.getRvoucherint();
	 				isRvoucherintBoolean  = true;
				 }
	 			 
	 			 if(isRintfeeBoolean || isRlvoucherintBoolean || isRvoucherintBoolean){
	 				PlusPayoutRecord payoutRecord = plusPayoutRecordServiceI
	 						.findPlusPayoutRecordByRorderno(repayMent2.getRorderno());
	 				 if(payoutRecord == null){
		  				 Plus plus  = plusServiceI.findPlusByTid(repayMent2.getTenderid());
		 				 UserTender tender = userTenderServiceI.findUserTenderByOrderno(repayMent2.getUtorderno());
		 				 Assert.notNull(plus.getId(), "标的增益设置表Id 不能为null");
		 				 Assert.notNull(repayMent2.getTenderid(), "标号ID 不能为null");
		 				 Assert.notNull(tender.getId(), "投标ID 不能为null");
		 				 Assert.notNull(repayMent2.getRorderno(), "还款流水号 不能为null");
		 				 Assert.notNull(clearmode, "clearmode清算方式 不能为null");
		    			 PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
		  				 plusPayoutRecord.setPporderno(StringUtil.getNo());  //增益清算流水号
		  				 plusPayoutRecord.setPpid(plus.getId());  //标的增益设置表Id
		  				 plusPayoutRecord.setTenderid(repayMent2.getTenderid());  //标号ID
		  				 plusPayoutRecord.setUsetenderid(tender.getId());  //投标id
		  				 plusPayoutRecord.setInvestorid(repayMent2.getInaccountid());  //投资人ID
		  				 plusPayoutRecord.setIntprofit(intprofit);  //加息券收益
		  				 plusPayoutRecord.setVoucherprofit(voucherprofit);  //类现金收益
		  				 plusPayoutRecord.setLikevoucherprofit(likevoucherprofit);  //假现金收益
		  				 plusPayoutRecord.setClearmode(clearmode);  //清算方式（1结标，2首期，3按期，4尾期）
		  				 plusPayoutRecord.setIsgrant((short)0);  //是否发放(0否，1是,2处理中)
		  				 plusPayoutRecord.setIsblending((short)0);  //是否系统勾兑（0未勾兑，1已勾兑）
		  				 plusPayoutRecord.setIsmanblending((short)0);  //是否人工勾兑（0未勾兑，1已勾兑）
		  				 plusPayoutRecord.setPaycompany("乾多多");  //托管通道公司（汇付天下）
		  				 plusPayoutRecord.setMadetime(new Date());  //创建时间
		  				 plusPayoutRecord.setIsaudit((short)0);  //是否审核 0 未审核 1 审核通过 2 审核不通过
		  				 plusPayoutRecord.setRorderno(repayMent2.getRorderno());  //还款流水号
		  				 plusPayoutRecordServiceI.insertSelective(plusPayoutRecord);
	 				 }
	 			 }
	   		}
		}
		
		if(repayMent != null){
			Double intprofit 				= 0.00;//加息券收益
			 Double voucherprofit 			= 0.00;//类现金收益
			 Double likevoucherprofit 		= 0.00;//假现金收益
			 boolean isRintfeeBoolean		= false;//是否产生加息劵利息
			 boolean isRlvoucherintBoolean	= false;//是否产生假现金的利息
			 boolean isRvoucherintBoolean	= false;//是否产生类现金的利息
    		 if(repayMent.getRintfee() > 0){//当期加息劵利息
   			 intprofit = repayMent.getRintfee(); 
   			 isRintfeeBoolean   = true;
			 }
    		 
			 if(repayMent.getRlvoucherint() > 0){//当期还款假现金的利息
				likevoucherprofit = repayMent.getRlvoucherint();
				isRlvoucherintBoolean = true;
			 }
			 
			 if(repayMent.getRvoucherint() > 0){//当期还款类现金的利息
				voucherprofit = repayMent.getRvoucherint();
				isRvoucherintBoolean  = true;
			 }
			 
			 if(isRintfeeBoolean || isRlvoucherintBoolean || isRvoucherintBoolean){
				PlusPayoutRecord payoutRecord = plusPayoutRecordServiceI
						.findPlusPayoutRecordByRorderno(repayMent.getRorderno());
				 if(payoutRecord == null){
	  				 Plus plus  = plusServiceI.findPlusByTid(repayMent.getTenderid());
	 				 UserTender tender = userTenderServiceI.findUserTenderByOrderno(repayMent.getUtorderno());
	 				 Assert.notNull(plus.getId(), "标的增益设置表Id 不能为null");
	 				 Assert.notNull(repayMent.getTenderid(), "标号ID 不能为null");
	 				 Assert.notNull(tender.getId(), "投标ID 不能为null");
	 				 Assert.notNull(repayMent.getRorderno(), "还款流水号 不能为null");
	 				 Assert.notNull(clearmode, "clearmode清算方式 不能为null");
	    			 PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
	  				 plusPayoutRecord.setPporderno(StringUtil.getNo());  //增益清算流水号
	  				 plusPayoutRecord.setPpid(plus.getId());  //标的增益设置表Id
	  				 plusPayoutRecord.setTenderid(repayMent.getTenderid());  //标号ID
	  				 plusPayoutRecord.setUsetenderid(tender.getId());  //投标id
	  				 plusPayoutRecord.setInvestorid(repayMent.getInaccountid());  //投资人ID
	  				 plusPayoutRecord.setIntprofit(intprofit);  //加息券收益
	  				 plusPayoutRecord.setVoucherprofit(voucherprofit);  //类现金收益
	  				 plusPayoutRecord.setLikevoucherprofit(likevoucherprofit);  //假现金收益
	  				 plusPayoutRecord.setClearmode(clearmode);  //清算方式（1结标，2首期，3按期，4尾期）
	  				 plusPayoutRecord.setIsgrant((short)0);  //是否发放(0否，1是,2处理中)
	  				 plusPayoutRecord.setIsblending((short)0);  //是否系统勾兑（0未勾兑，1已勾兑）
	  				 plusPayoutRecord.setIsmanblending((short)0);  //是否人工勾兑（0未勾兑，1已勾兑）
	  				 plusPayoutRecord.setPaycompany("乾多多");  //托管通道公司（汇付天下）
	  				 plusPayoutRecord.setMadetime(new Date());  //创建时间
	  				 plusPayoutRecord.setIsaudit((short)0);  //是否审核 0 未审核 1 审核通过 2 审核不通过
	  				 plusPayoutRecord.setRorderno(repayMent.getRorderno());  //还款流水号
	  				 plusPayoutRecordServiceI.insertSelective(plusPayoutRecord);
				 }
			 }
		}
 		 
	}
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(还款记录数据增加方法) 
	* @param @param repayMent
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(RepayMent repayMent) {
 		return repayMentMapper.insert(repayMent);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(还款记录数据增加方法，非空值) 
    * @param @param repayMent
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(RepayMent repayMent) {
 		return repayMentMapper.insertSelective(repayMent);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(还款记录根据Id 删除方法) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return repayMentMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(还款记录更新方法) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(RepayMent repayMent) {
 		return repayMentMapper.updateById(repayMent);
	}
	 /**
     * 
     * @Title: findRepayMents
     * @Description: TODO(还款记录查询全部) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<RepayMent> findRepayMents(RepayMent repayMent) {
 		return repayMentMapper.findRepayMents(repayMent);
	}
  	/**
     * 
    * @Title: findRepayMentById
    * @Description: TODO(根据Id查询对应的还款记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public RepayMent findRepayMentById(BigDecimal id) {
 		return repayMentMapper.findRepayMentById(id);
	}
	/**
     * 
    * @Title: findRepayMentsByBaseId 
    * @Description: TODO(根据用户Id查询对应的还款记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public List<RepayMent> findRepayMentsByBaseId(RepayMent repayMent) {
 		return repayMentMapper.findRepayMentsByBaseId(repayMent);
	}
	
	/**
     * 
    * @Title: findRepayMentByRorderno 
    * @Description: TODO(根据还款流水号查询对应的还款记录信息) 
    * @param @param utorderno
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author chenjiaming
    * @throws
     */
 	@Override
	public RepayMent findRepayMentByRorderno(String rorderno) {
 		return repayMentMapper.findRepayMentByRorderno(rorderno);
	}
 	/**
    * 
    * @Title: findRepayMentByConditions 
    * @Description: TODO(条件查询) 
    * @param @param maps
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author cjm
    * @throws
     */
 	@Override
	public RepayMent findRepayMentByConditions(Map<String,Object> maps){
 		return repayMentMapper.findRepayMentByConditions(maps);
 	}
 	/**
     * 
     * @Title: findListRepayMentByConditions 
     * @Description: TODO(条件查询) 
     * @param @param maps
     * @param @return  参数说明 
     * @return List<RepayMent>    返回类型 
     * @author cjm
     * @throws
      */
	@Override
	public List<RepayMent> findListRepayMentByConditions(Map<String, Object> maps) {
 		return repayMentMapper.findListRepayMentByConditions(maps);
	}
	/**
     * 根据标的ID,借款人ID,投资人ID查找已经成功还款的最大期数
    * @Title: findInaccountInfoRepayMentMaxPeriodsByConditions 
    * @Description: TODO(根据标的ID,借款人ID,投资人ID查找已经成功还款的最大期数) 
    * @param @param maps
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author cjm
    * @throws
     */
 	@Override
	public RepayMent findInaccountInfoRepayMentMaxPeriodsByConditions(Map<String, Object> maps) {
 		return repayMentMapper.findInaccountInfoRepayMentMaxPeriodsByConditions(maps);
	}
/*	@Override
	public RepayMent getRtimeforRepayMent(RepayMent repayment) {
		return repayMentMapper.getRtimeforRepayMent(repayment);
	}*/
	@Override
	public RepayMent getEndRtimeforRepayMent(RepayMent repayment) {
		return repayMentMapper.getEndRtimeforRepayMent(repayment);
	}
	@Override
	public Double getAmountforRepayMent(RepayMent repayment) {
		return repayMentMapper.getAmountforRepayMent(repayment);
	}
	@Override
	public Double getTotalforRepayMent(RepayMent repayment) {
		return repayMentMapper.getTotalforRepayMent(repayment);
	}
	@Override
	public RepayMent getRtimeAndPeriodsforRepayMent(RepayMent repayment) {
		return repayMentMapper.getRtimeAndPeriodsforRepayMent(repayment);
	}
	@Override
	public RepayMent getRtimeAndPeriodsforRepayMent2(RepayMent repayment) {
		return repayMentMapper.getRtimeAndPeriodsforRepayMent2(repayment);
	}
	@Override
	public RepayMent getrepaystatus(RepayMent repayment) {
		return repayMentMapper.getrepaystatus(repayment);
	}
	@Override
	public RepayMent getAndPeriods(RepayMent repayment) {
		return repayMentMapper.getAndPeriods(repayment);
	}
	 /**
     *  
    * @Title: countRepayMentByConditions 
    * @Description: TODO(根据条件查询总条数) 
    * @param @param UsrCustId
    * @param @return  参数说明 
    * @return Integer    返回类型 
    * @author cjm
    * @throws
     */
	public Integer countRepayMentByConditions(Map<String, Object> maps) {
 		return repayMentMapper.countRepayMentByConditions(maps);
	}
	@Override
	public Double getTotalAmountRepayMent(RepayMent repayment) {
		return repayMentMapper.getTotalAmountRepayMent(repayment);
	}
 	 
 	@Override
	public RepayMent getOverduePrincipal(RepayMent repayment) {
		return repayMentMapper.getOverduePrincipal(repayment);
	}
	@Override
	public List<RepayMent> selectRepayMentList(RepayMent repayment) {
		return repayMentMapper.selectRepayMentList(repayment);
	}

	@Override
	public Double getTotalPeriods(RepayMent repayment) {
		return repayMentMapper.getTotalPeriods(repayment);
	}

	@Override
	public RepayMent getPeriodsforRepayMent(RepayMent repayment) {
		return repayMentMapper.getPeriodsforRepayMent(repayment);
	}

	@Override
	public Double selectAlreadyRepaymentAmount(RepayMent repayment) {
		return repayMentMapper.selectAlreadyRepaymentAmount(repayment);
	}
	/**
  	 * 
  	* @Title: findAheadRepayRordernoJoinCheck 
  	* @Description: TODO(和提前还款 实际到账金额联查 ) 
  	* @param @param maps
  	* @param @return  参数说明 
  	* @return List<RepayMent>    返回类型 
  	* @author cjm
  	* @throws
  	 */
 	@Override
	public List<RepayMent> findAheadRepayRordernoJoinCheck(Map<String, Object> maps) {
 		return repayMentMapper.findAheadRepayRordernoJoinCheck(maps);
	}

 	/**
  	 * 
  	* @Title: findJoinCheckRepayMentByRorderno 
  	* @Description: TODO(联表查询  根据还款流水号) 
  	* @param @param rorderno
  	* @param @return  参数说明 
  	* @return RepayMent    返回类型 
  	* @author cjm
  	* @throws
  	 */
	@Override
	public RepayMent findJoinCheckRepayMentByRorderno(String rorderno) {
 		return repayMentMapper.findJoinCheckRepayMentByRorderno(rorderno);
	}

	@Override
	public RepayMent selectRepayMent(RepayMent repayment) {
		return repayMentMapper.selectRepayMent(repayment);
	}
	
	/**
  	 * 
  	* @Title: findListRepayMentJoinTendAndUserInfoByConditions 
  	* @Description: TODO(联表查询  借款人and投资人and标的信息) 
  	* @param @param maps
  	* @param @return    设定文件 
  	* @return List<RepayMent>    返回类型 
  	* @author   cjm  
  	* @throws
  	 */
 	@Override
	public List<RepayMent> findListRepayMentJoinTendAndUserInfoByConditions(Map<String, Object> maps) {
 		return repayMentMapper.findListRepayMentJoinTendAndUserInfoByConditions(maps);
	}
  
 }
