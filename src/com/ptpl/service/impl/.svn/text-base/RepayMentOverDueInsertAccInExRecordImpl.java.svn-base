package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ptpl.model.AccInExRecord;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.RepayMent;
import com.ptpl.model.UserAccount;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.RepayMentOverDueInsertAccInExRecordI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: RepayMentOverDueInsertAccInExRecordImpl 
* @Description: TODO(逾期还款 收支记录) 
* @author cjm 
* @date 2017年6月19日 下午6:34:14 
*
 */
public class RepayMentOverDueInsertAccInExRecordImpl implements RepayMentOverDueInsertAccInExRecordI{

	@Autowired
	private UserAccountServiceI userAccountServiceI;
	
	@Autowired
	private AccInExRecordServiceI accInExRecordServiceI;
	
 
	/**
    *
   * @Title: InsertOverdueOutAccountByRamountAccInExRecord 
   * @Description: TODO(逾期还款成功时 添加借款人类型为本金收支记录) 
   * @param @param repayMent  参数说明 
   * @return void    返回类型 
   * @author cjm
   * @throws
    */
public void InsertOverdueOutAccountByRamountAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
 		Double ramount	 		= repayMent.getRptotalamount();//本金
		if(ramount > 0){
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
  				accInExRecord.setAmount(0.00);//平台账户金额
  				accInExRecord.setPinamount(0.00);//平台进账
  				accInExRecord.setPoutamount(0.00);//平台出账
  				accInExRecord.setType((short)24);//业务类型
  				accInExRecord.setInamount(0.00);//收入
  				accInExRecord.setOutamount(ramount);//支出
  				accInExRecord.setRecordtime(new Date());//发生时间
  				accInExRecord.setDescription("逾期还款本金支出");//说明
  				accInExRecord.setRemark("逾期还款本金支出");//备注
  				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
  				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
  				accInExRecord.setPaccount("MDT000001");//平台账户
  				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
  			    accInExRecordServiceI.insertSelective(accInExRecord);
 			 
		}
  }
	/**
	 * 
	* @Title: InsertOverdueInAccountByRamountAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为本金收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByRamountAccInExRecord(RepayMent repayMent){
	Assert.notNull(repayMent, "'repayMent 不能为null'");
	AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 
	if(aheadRealRepayment != null){
		Double ramount	 		= aheadRealRepayment.getRptotalamount();//本金
		if(ramount > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)24);//业务类型
 				accInExRecord.setInamount(ramount);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款本金收入");//说明
				accInExRecord.setRemark("逾期还款本金收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			    accInExRecordServiceI.insertSelective(accInExRecord);
		 	
		} 
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueOutAccountByRinterestAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加借款人类型为本金利息收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueOutAccountByRinterestAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
    AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
	 
	if(aheadRealRepayment != null){
		Double rinterest 			= aheadRealRepayment.getRinterest();//本金利息
		if(rinterest > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)22);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(rinterest);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款本金利息支出");//说明
				accInExRecord.setRemark("逾期还款本金利息支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				  accInExRecordServiceI.insertSelective(accInExRecord);
 		}
	}
 	}
	 /**
	  * 
	 * @Title: InsertOverdueInAccountByRinterestAccInExRecord 
	 * @Description: TODO(逾期还款成功时 添加投资人类型为本金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	public void InsertOverdueInAccountByRinterestAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	if(aheadRealRepayment != null){
		Double rinterest 		= aheadRealRepayment.getRinterest();//本金利息
		if(rinterest > 0){
 			AccInExRecord accInExRecord = new AccInExRecord();
			accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)22);//业务类型
 				accInExRecord.setInamount(rinterest);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款本金利息收入");//说明
				accInExRecord.setRemark("逾期还款本金利息收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				accInExRecordServiceI.insertSelective(accInExRecord);
 			}
		}
 	}
	/**
	 * 
	* @Title: InsertOverdueOutAccountByRvoucherAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加借款人类型为类现金收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueOutAccountByRvoucherAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 
	if(aheadRealRepayment != null){
		Double rvoucher 		= aheadRealRepayment.getRvoucher(); //类现金
		if(rvoucher > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)23);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(rvoucher);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款类现金支出");//说明
				accInExRecord.setRemark("逾期还款类现金支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				accInExRecordServiceI.insertSelective(accInExRecord);
			 
		}
	}
 	}
	 /**
	  * 
	 * @Title: InsertOverdueInAccountByRvoucherAccInExRecord 
	 * @Description: TODO(逾期还款成功时 添加投资人类型为类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	public void InsertOverdueInAccountByRvoucherAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	if(aheadRealRepayment != null){
		Double rvoucher 		= aheadRealRepayment.getRvoucher(); //类现金
		if(rvoucher > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)23);//业务类型
 				accInExRecord.setInamount(rvoucher);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款类现金收入");//说明
				accInExRecord.setRemark("逾期还款类现金收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
 				 accInExRecordServiceI.insertSelective(accInExRecord);
			 
		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueOutAccountByRvoucherintAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加借款人类型为类现金利息收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueOutAccountByRvoucherintAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	 
	if(aheadRealRepayment != null){
		Double rvoucherint 	 	= aheadRealRepayment.getRvoucherint();//类现金利息
		if(rvoucherint > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)20);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(rvoucherint);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款类现金利息支出");//说明
				accInExRecord.setRemark("逾期还款类现金利息支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
			 
		}
	}
 	}
	 /**
	  * 
	 * @Title: InsertOverdueInAccountByRvoucherintAccInExRecord 
	 * @Description: TODO(逾期还款成功时 添加投资人类型为类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	public	void InsertOverdueInAccountByRvoucherintAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	if(aheadRealRepayment != null){
		Double rvoucherint 	 	= aheadRealRepayment.getRvoucherint();//类现金利息
		if(rvoucherint > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)20);//业务类型
 				accInExRecord.setInamount(rvoucherint);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款类现金利息收入");//说明
				accInExRecord.setRemark("逾期还款类现金利息收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			    accInExRecordServiceI.insertSelective(accInExRecord);
 		}
	}
	 
	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByRlvoucherintAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为假现金的利息收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByRlvoucherintAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	if(aheadRealRepayment != null){
		Double rlvoucherint	 		= aheadRealRepayment.getRlvoucherint();//假现金的利息
		if(rlvoucherint > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(rlvoucherint);//平台出账
				accInExRecord.setType((short)19);//业务类型
 				accInExRecord.setInamount(rlvoucherint);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款假现金的利息收入");//说明
				accInExRecord.setRemark("逾期还款假现金的利息收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
 		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByRintfeeAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为加息劵利息收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByRintfeeAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 	if(aheadRealRepayment != null){
		Double rintfee	 		= aheadRealRepayment.getRintfee();//加息劵利息
		if(rintfee > 0){
			 
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(rintfee);//平台出账
				accInExRecord.setType((short)21);//业务类型
 				accInExRecord.setInamount(rintfee);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款加息劵利息收入");//说明
				accInExRecord.setRemark("逾期还款加息劵利息收入");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			 accInExRecordServiceI.insertSelective(accInExRecord);
		 
		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByDisablevoucherAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为失效类现金收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByDisablevoucherAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 
	if(aheadRealRepayment != null){
		Double disablevoucher	= repayMent.getDisablevoucher();//失效类现金
		if(disablevoucher > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(disablevoucher);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)26);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disablevoucher);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款失效类现金支出");//说明
				accInExRecord.setRemark("逾期还款失效类现金支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
		 
		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByDisableocamountAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为失效滞纳金收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByDisableocamountAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
	 
	if(aheadRealRepayment != null){
		Double disableocamount	= repayMent.getDisableocamount();//失效滞纳金
		if(disableocamount > 0){
		 
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)27);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disableocamount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款失效滞纳金支出");//说明
				accInExRecord.setRemark("逾期还款失效滞纳金支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
			 
		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByDisablevocamountAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为失效类现金滞纳金收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByDisablevocamountAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 
	if(aheadRealRepayment != null){
		Double disablevocamount	= repayMent.getDisablevocamount();//失效类现金滞纳金
		if(disablevocamount > 0){
			 
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)28);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disablevocamount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款失效类现金滞纳金支出");//说明
				accInExRecord.setRemark("逾期还款失效类现金滞纳金支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				  accInExRecordServiceI.insertSelective(accInExRecord);
 		}
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByDisablevoucherintAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为失效类现金利息收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void InsertOverdueInAccountByDisablevoucherintAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
	 
	if(aheadRealRepayment != null){
		Double ramount	 		= repayMent.getDisablevoucherint();//失效类现金利息
	    if(ramount > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(ramount);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)30);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(ramount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款失效类现金利息支出");//说明
				accInExRecord.setRemark("逾期还款失效类现金利息支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
 	    }	
	}
 	}
	/**
	 * 
	* @Title: InsertOverdueInAccountByInterestexpenseAccInExRecord 
	* @Description: TODO(逾期还款成功时 添加投资人类型为利息的管理费收支记录) 
	* @param @param repayMent  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
@Override
public void InsertOverdueInAccountByInterestexpenseAccInExRecord(RepayMent repayMent) {
	Assert.notNull(repayMent, "'repayMent 不能为null'");
	AheadRealRepayment aheadRealRepayment  = repayMent.getAheadRealRepayment();
 
	if(aheadRealRepayment != null){
		Double ramount	 		= aheadRealRepayment.getInterestexpense();//利息的管理费
	    if(ramount > 0){
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(ramount);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)17);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(ramount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("逾期还款利息的管理费支出");//说明
				accInExRecord.setRemark("逾期还款利息的管理费支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				 accInExRecordServiceI.insertSelective(accInExRecord);
 	    }	
	}
 }
	@Override
	public void InsertOverdueRepayMentCountAccInExRecord(RepayMent repayMent) {
		InsertOverdueOutAccountByRamountAccInExRecord(repayMent);//还款成功时 添加借款人类型为本金收支记录
		InsertOverdueInAccountByRamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为本金收支记录
		
		InsertOverdueOutAccountByRinterestAccInExRecord(repayMent);//还款成功时 添加借款人类型为本金利息收支记录
 		InsertOverdueInAccountByRinterestAccInExRecord(repayMent);//还款成功时 添加投资人类型为本金利息收支记录
 		
		InsertOverdueOutAccountByRvoucherAccInExRecord(repayMent);//还款成功时 添加借款人类型为类现金收支记录
		InsertOverdueInAccountByRvoucherAccInExRecord(repayMent);//还款成功时 添加投资人类型为类现金收支记录
		
		InsertOverdueOutAccountByRvoucherintAccInExRecord(repayMent);//还款成功时 添加借款人类型为类现金利息收支记录
		InsertOverdueInAccountByRvoucherintAccInExRecord(repayMent);//还款成功时 添加投资人类型为类现金利息收支记录
		
		InsertOverdueInAccountByRlvoucherintAccInExRecord(repayMent);//还款成功时 添加投资人类型为假现金的利息收支记录
		InsertOverdueInAccountByRintfeeAccInExRecord(repayMent);//还款成功时 添加投资人类型为加息劵利息收支记录
		
		InsertOverdueInAccountByDisablevoucherAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金收支记录
		InsertOverdueInAccountByDisableocamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效滞纳金收支记录
		
		InsertOverdueInAccountByDisablevocamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金滞纳金收支记录
		InsertOverdueInAccountByDisablevoucherintAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金利息收支记录
		
		InsertOverdueInAccountByInterestexpenseAccInExRecord(repayMent);//还款成功时 添加投资人类型为利息的管理费收支记录
 	}
}
