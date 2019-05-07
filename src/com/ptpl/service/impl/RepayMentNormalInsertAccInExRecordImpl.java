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
import com.ptpl.service.RepayMentNormalInsertAccInExRecordI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: RepayMentNormalInsertAccInExRecordImpl 
* @Description: TODO(正常还款 收支记录) 
* @author cjm 
* @date 2017年6月19日 下午7:03:55 
*
 */
public class RepayMentNormalInsertAccInExRecordImpl implements RepayMentNormalInsertAccInExRecordI{


	@Autowired
	private UserAccountServiceI userAccountServiceI;
	
	@Autowired
	private AccInExRecordServiceI accInExRecordServiceI;
	
	/**
  	 * 
  	* @Title: getUserAccount 
  	* @Description: TODO(根据用户ID查询用户账号UserAccount信息) 
  	* @param @param baseid
  	* @param @return  参数说明 
  	* @return UserAccount    返回类型 
  	* @author cjm
  	* @throws
  	 */
 	public	UserAccount getUserAccount(BigDecimal baseid){
		Assert.notNull(baseid, "'baseid 不能为null'");
		UserAccount userAccount = userAccountServiceI.getUserAccountByBaseId(baseid);
		return userAccount;
	}
  	    /**
	     *
	    * @Title: InsertOutAccountByRamountAccInExRecord 
	    * @Description: TODO(还款成功时 添加借款人类型为本金收支记录) 
	    * @param @param repayMent  参数说明 
	    * @return void    返回类型 
	    * @author cjm
	    * @throws
	     */
	public void InsertOutAccountByRamountAccInExRecord(RepayMent repayMent){
 		Assert.notNull(repayMent, "'repayMent 不能为null'");
		Double ramount	 		= repayMent.getRamount();//本金
		if(ramount > 0){
				UserAccount OutAccount = getUserAccount(repayMent.getOutaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(OutAccount != null){
 					balance 		= OutAccount.getBalance() - ramount;//用户总资产
					avlbalance		= OutAccount.getAvlbalance() - ramount;//可用余额
					freezebalance	= OutAccount.getFreezebalance() == null ? 0.00 : OutAccount.getFreezebalance();//冻结余额
					OutAccount.setBalance(balance);//用户总资产
					OutAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(OutAccount);
				}
	  			
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
  				accInExRecord.setAmount(0.00);//平台账户金额
  				accInExRecord.setPinamount(0.00);//平台进账
  				accInExRecord.setPoutamount(0.00);//平台出账
  				accInExRecord.setType((short)24);//业务类型
   				accInExRecord.setInamount(0.00);//收入
  				accInExRecord.setOutamount(ramount);//支出
  				accInExRecord.setRecordtime(new Date());//发生时间
  				accInExRecord.setDescription("正常还款本金支出");//说明
  				accInExRecord.setRemark("正常还款本金支出");//备注
   				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
  				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
  				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额
  				accInExRecord.setPaccount("MDT000001");//平台账户
  				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
  			   accInExRecordServiceI.insertSelective(accInExRecord);
 		}
 	}
  	/**
  	 * 
  	* @Title: InsertInAccountByRamountAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为本金收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertInAccountByRamountAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
 			Double ramount	 		= repayMent.getRamount();//本金
 			if(ramount > 0){
 				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() + ramount;//用户总资产
					avlbalance		= InAccount.getAvlbalance() + ramount;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
 				
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)24);//业务类型
				accInExRecord.setInamount(ramount);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款本金收入");//说明
				accInExRecord.setRemark("正常还款本金收入");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

				accInExRecordServiceI.insertSelective(accInExRecord);
  		}
   	}
  	/**
  	 * 
  	* @Title: InsertOutAccountByRinterestAccInExRecord 
  	* @Description: TODO(还款成功时 添加借款人类型为本金利息收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertOutAccountByRinterestAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
		Double rinterest 			= repayMent.getRinterest();//本金利息
		if(rinterest > 0){
			UserAccount OutAccount = getUserAccount(repayMent.getOutaccountid());
			Double balance 			= 0.00;//用户总资产
			Double avlbalance		= 0.00;//可用余额
			Double freezebalance	= 0.00;//冻结余额
			if(OutAccount != null){
				balance 	    = OutAccount.getBalance() - rinterest;//用户总资产
				avlbalance		= OutAccount.getAvlbalance() - rinterest;//可用余额
				freezebalance	= OutAccount.getFreezebalance();//冻结余额
				OutAccount.setBalance(balance);//用户总资产
				OutAccount.setAvlbalance(avlbalance);//可用余额
				userAccountServiceI.updateUseraccount(OutAccount);
			}
			
			AccInExRecord accInExRecord = new AccInExRecord();
			accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
			accInExRecord.setAmount(0.00);//平台账户金额
			accInExRecord.setPinamount(0.00);//平台进账
			accInExRecord.setPoutamount(0.00);//平台出账
			accInExRecord.setType((short)22);//业务类型
			accInExRecord.setInamount(0.00);//收入
			accInExRecord.setOutamount(rinterest);//支出
			accInExRecord.setRecordtime(new Date());//发生时间
			accInExRecord.setDescription("正常还款本金利息支出");//说明
			accInExRecord.setRemark("正常还款本金利息支出");//备注
			accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
			accInExRecord.setAieorderno(StringUtil.getNo());//流水号
			accInExRecord.setPaccount("MDT000001");//平台账户
			accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			
			accInExRecord.setFreebalance(freezebalance);//用户冻结余额
			accInExRecord.setBalance(avlbalance);//用户可用余额
  			accInExRecord.setTotalbalance(balance);//用户总余额
			accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	 /**
  	  * 
  	 * @Title: InsertInAccountByRinterestAccInExRecord 
  	 * @Description: TODO(还款成功时 添加投资人类型为本金利息收支记录) 
  	 * @param @param repayMent  参数说明 
  	 * @return void    返回类型 
  	 * @author cjm
  	 * @throws
  	  */
  	public void InsertInAccountByRinterestAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
		Double rinterest 		= repayMent.getRinterest();//本金利息
		if(rinterest > 0){
			UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
			Double balance 			= 0.00;//用户总资产
			Double avlbalance		= 0.00;//可用余额
			Double freezebalance	= 0.00;//冻结余额
			if(InAccount != null){
				balance 		= InAccount.getBalance() + rinterest;//用户总资产
				avlbalance		= InAccount.getAvlbalance() + rinterest;//可用余额
				freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
				InAccount.setBalance(balance);//用户总资产
				InAccount.setAvlbalance(avlbalance);//可用余额
				userAccountServiceI.updateUseraccount(InAccount);
			}
			AccInExRecord accInExRecord = new AccInExRecord();
			accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
			accInExRecord.setAmount(0.00);//平台账户金额
			accInExRecord.setPinamount(0.00);//平台进账
			accInExRecord.setPoutamount(0.00);//平台出账
			accInExRecord.setType((short)22);//业务类型
			accInExRecord.setInamount(rinterest);//收入
			accInExRecord.setOutamount(0.00);//支出
			accInExRecord.setRecordtime(new Date());//发生时间
			accInExRecord.setDescription("正常还款本金利息收入");//说明
			accInExRecord.setRemark("正常还款本金利息收入");//备注
			accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
			accInExRecord.setAieorderno(StringUtil.getNo());//流水号
			accInExRecord.setPaccount("MDT000001");//平台账户
			accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			
			accInExRecord.setFreebalance(freezebalance);//用户冻结余额
			accInExRecord.setBalance(avlbalance);//用户可用余额
  			accInExRecord.setTotalbalance(balance);//用户总余额
  			
			accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertOutAccountByRvoucherAccInExRecord 
  	* @Description: TODO(还款成功时 添加借款人类型为类现金收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertOutAccountByRvoucherAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
 		Double rvoucher 		= repayMent.getRvoucher(); //类现金
		if(rvoucher > 0){
			
			UserAccount OutAccount = getUserAccount(repayMent.getOutaccountid());
			Double balance 			= 0.00;//用户总资产
			Double avlbalance		= 0.00;//可用余额
			Double freezebalance	= 0.00;//冻结余额
			if(OutAccount != null){
				balance 	    = OutAccount.getBalance() - rvoucher;//用户总资产
				avlbalance		= OutAccount.getAvlbalance() - rvoucher;//可用余额
				freezebalance	= OutAccount.getFreezebalance();//冻结余额
				OutAccount.setBalance(balance);//用户总资产
				OutAccount.setAvlbalance(avlbalance);//可用余额
				userAccountServiceI.updateUseraccount(OutAccount);
			}
			
			AccInExRecord accInExRecord = new AccInExRecord();
			accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
			accInExRecord.setAmount(0.00);//平台账户金额
			accInExRecord.setPinamount(0.00);//平台进账
			accInExRecord.setPoutamount(0.00);//平台出账
			accInExRecord.setType((short)23);//业务类型
			accInExRecord.setInamount(0.00);//收入
			accInExRecord.setOutamount(rvoucher);//支出
			accInExRecord.setRecordtime(new Date());//发生时间
			accInExRecord.setDescription("正常还款类现金支出");//说明
			accInExRecord.setRemark("正常还款类现金支出");//备注
			accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
			accInExRecord.setAieorderno(StringUtil.getNo());//流水号
			accInExRecord.setPaccount("MDT000001");//平台账户
			accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
			
			accInExRecord.setFreebalance(freezebalance);//用户冻结余额
			accInExRecord.setBalance(avlbalance);//用户可用余额
  			accInExRecord.setTotalbalance(balance);//用户总余额

			accInExRecordServiceI.insertSelective(accInExRecord);
	   }
   	}
  	 /**
  	  * 
  	 * @Title: InsertInAccountByRvoucherAccInExRecord 
  	 * @Description: TODO(还款成功时 添加投资人类型为类现金收支记录) 
  	 * @param @param repayMent  参数说明 
  	 * @return void    返回类型 
  	 * @author cjm
  	 * @throws
  	  */
  	public void InsertInAccountByRvoucherAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
  		Double rvoucher 		= repayMent.getRvoucher(); //类现金
 			if(rvoucher > 0){
 				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() + rvoucher;//用户总资产
					avlbalance		= InAccount.getAvlbalance() + rvoucher;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
 				
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)23);//业务类型
				accInExRecord.setInamount(rvoucher);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款类现金收入");//说明
				accInExRecord.setRemark("正常还款类现金收入");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

				accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertOutAccountByRvoucherintAccInExRecord 
  	* @Description: TODO(还款成功时 添加借款人类型为类现金利息收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertOutAccountByRvoucherintAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
		Double rvoucherint 	 	= repayMent.getRvoucherint();//类现金利息
		if(rvoucherint > 0){
			
				UserAccount OutAccount = getUserAccount(repayMent.getOutaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(OutAccount != null){
					balance 		= OutAccount.getBalance() - rvoucherint;//用户总资产
					avlbalance		= OutAccount.getAvlbalance() - rvoucherint;//可用余额
					freezebalance	= OutAccount.getFreezebalance() == null ? 0.00 : OutAccount.getFreezebalance();//冻结余额
					OutAccount.setBalance(balance);//用户总资产
					OutAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(OutAccount);
				}
				
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getOutaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)20);//业务类型
				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(rvoucherint);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款类现金利息支出");//说明
				accInExRecord.setRemark("正常还款类现金利息支出");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			    accInExRecordServiceI.insertSelective(accInExRecord);
		}
    }
  	 /**
  	  * 
  	 * @Title: InsertInAccountByRvoucherintAccInExRecord 
  	 * @Description: TODO(还款成功时 添加投资人类型为类现金利息收支记录) 
  	 * @param @param repayMent  参数说明 
  	 * @return void    返回类型 
  	 * @author cjm
  	 * @throws
  	  */
  	public	void InsertInAccountByRvoucherintAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
  			Double rvoucherint 	 	= repayMent.getRvoucherint();//类现金利息
 			if(rvoucherint > 0){
 				
 				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() + rvoucherint;//用户总资产
					avlbalance		= InAccount.getAvlbalance() + rvoucherint;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
 				
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)20);//业务类型
				accInExRecord.setInamount(rvoucherint);//收入
				accInExRecord.setOutamount(0.00);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款类现金利息收入");//说明
				accInExRecord.setRemark("正常还款类现金利息收入");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			    accInExRecordServiceI.insertSelective(accInExRecord);
  		}
   	}
   	 
  	/**
  	 * 
  	* @Title: InsertInAccountByDisablevoucherAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为失效类现金收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertInAccountByDisablevoucherAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
	 
			Double disablevoucher	= repayMent.getDisablevoucher();//失效类现金
			if(disablevoucher > 0){
				
				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() - disablevoucher;//用户总资产
					avlbalance		= InAccount.getAvlbalance() - disablevoucher;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
				
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(disablevoucher);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)26);//业务类型
				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disablevoucher);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款失效类现金支出");//说明
				accInExRecord.setRemark("正常还款失效类现金支出");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			    accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertInAccountByDisableocamountAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为失效滞纳金收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertInAccountByDisableocamountAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
	 
			Double disableocamount	= repayMent.getDisableocamount();//失效滞纳金
			if(disableocamount > 0){
				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() - disableocamount;//用户总资产
					avlbalance		= InAccount.getAvlbalance() - disableocamount;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
				
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)27);//业务类型
				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disableocamount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款失效滞纳金支出");//说明
				accInExRecord.setRemark("正常还款失效滞纳金支出");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			    accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertInAccountByDisablevocamountAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为失效类现金滞纳金收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertInAccountByDisablevocamountAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
	 
			Double disablevocamount	= repayMent.getDisablevocamount();//失效类现金滞纳金
			if(disablevocamount > 0){
				UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() - disablevocamount;//用户总资产
					avlbalance		= InAccount.getAvlbalance() - disablevocamount;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
				
 				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(0.00);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)28);//业务类型
 				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(disablevocamount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款失效类现金滞纳金支出");//说明
				accInExRecord.setRemark("正常还款失效类现金滞纳金支出");//备注
 				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			    accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertInAccountByDisablevoucherintAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为失效类现金利息收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	public void InsertInAccountByDisablevoucherintAccInExRecord(RepayMent repayMent){
  		Assert.notNull(repayMent, "'repayMent 不能为null'");
 		Double ramount	 		= repayMent.getDisablevoucherint();//失效类现金利息
	    if(ramount > 0){
		    	UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
						balance 		= InAccount.getBalance() - ramount;//用户总资产
					avlbalance		= InAccount.getAvlbalance() - ramount;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
	    	
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(ramount);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)30);//业务类型
				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(ramount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款失效类现金利息支出");//说明
				accInExRecord.setRemark("正常还款失效类现金利息支出");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

			   accInExRecordServiceI.insertSelective(accInExRecord);
 		}
   	}
  	/**
  	 * 
  	* @Title: InsertInAccountByDisablevoucherintAccInExRecord 
  	* @Description: TODO(还款成功时 添加投资人类型为利息的管理费收支记录) 
  	* @param @param repayMent  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
	@Override
	public void InsertInAccountByInterestexpenseAccInExRecord(RepayMent repayMent) {
		Assert.notNull(repayMent, "'repayMent 不能为null'");
 			Double ramount	 		= repayMent.getInterestexpense();//利息的管理费
		    if(ramount > 0){
		    	UserAccount InAccount = getUserAccount(repayMent.getInaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(InAccount != null){
 					balance 		= InAccount.getBalance() - ramount;//用户总资产
					avlbalance		= InAccount.getAvlbalance() - ramount;//可用余额
					freezebalance	= InAccount.getFreezebalance() == null ? 0.00 : InAccount.getFreezebalance();//冻结余额
					InAccount.setBalance(balance);//用户总资产
					InAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(InAccount);
				}
		    	
				AccInExRecord accInExRecord = new AccInExRecord();
				accInExRecord.setBaseid(repayMent.getInaccountid());//用户ID
				accInExRecord.setAmount(0.00);//平台账户金额
				accInExRecord.setPinamount(ramount);//平台进账
				accInExRecord.setPoutamount(0.00);//平台出账
				accInExRecord.setType((short)17);//业务类型
				accInExRecord.setInamount(0.00);//收入
				accInExRecord.setOutamount(ramount);//支出
				accInExRecord.setRecordtime(new Date());//发生时间
				accInExRecord.setDescription("正常还款利息的管理费支出");//说明
				accInExRecord.setRemark("正常还款利息的管理费支出");//备注
				accInExRecord.setStatus((short)1);//状态（摘要） 0冻结  1成功 2失败
				accInExRecord.setAieorderno(StringUtil.getNo());//流水号
				accInExRecord.setPaccount("MDT000001");//平台账户
				accInExRecord.setBorderno(repayMent.getRorderno());//业务流水号
				
				accInExRecord.setFreebalance(freezebalance);//用户冻结余额
  				accInExRecord.setBalance(avlbalance);//用户可用余额
	  			accInExRecord.setTotalbalance(balance);//用户总余额

				accInExRecordServiceI.insertSelective(accInExRecord);
 		}
 	}

	@Override
	public void InsertNormalRepayMentCountAccInExRecord(RepayMent repayMent) {
		InsertOutAccountByRamountAccInExRecord(repayMent);//还款成功时 添加借款人类型为本金收支记录
		InsertInAccountByRamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为本金收支记录
		InsertOutAccountByRinterestAccInExRecord(repayMent);//还款成功时 添加借款人类型为本金利息收支记录
 		InsertInAccountByRinterestAccInExRecord(repayMent);//还款成功时 添加投资人类型为本金利息收支记录
		InsertOutAccountByRvoucherAccInExRecord(repayMent);//还款成功时 添加借款人类型为类现金收支记录
		InsertInAccountByRvoucherAccInExRecord(repayMent);//还款成功时 添加投资人类型为类现金收支记录
		InsertOutAccountByRvoucherintAccInExRecord(repayMent);//还款成功时 添加借款人类型为类现金利息收支记录
		InsertInAccountByRvoucherintAccInExRecord(repayMent);//还款成功时 添加投资人类型为类现金利息收支记录
 		InsertInAccountByDisablevoucherAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金收支记录
		InsertInAccountByDisableocamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效滞纳金收支记录
		InsertInAccountByDisablevocamountAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金滞纳金收支记录
		InsertInAccountByDisablevoucherintAccInExRecord(repayMent);//还款成功时 添加投资人类型为失效类现金利息收支记录
		InsertInAccountByInterestexpenseAccInExRecord(repayMent);//还款成功时 添加投资人类型为利息的管理费收支记录
 	}
	
	
}
