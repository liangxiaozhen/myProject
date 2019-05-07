package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ptpl.model.AccInExRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.UserAccount;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.RepayMentNormalCompenInsertAccInExRecordI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 正常代偿记录未添加  20170624 cjm 后面再添加记录
 * @author admin
 *
 */
public class RepayMentNormalCompenInsertAccInExRecordImpl implements RepayMentNormalCompenInsertAccInExRecordI{
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
	 * @Description: TODO(正常代偿还款成功时 添加代偿人类型为本金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertOutAccountByRamountAccInExRecord(RepayMent repayMent){
		Assert.notNull(repayMent, "'repayMent 不能为null'");
		Double ramount	 		= repayMent.getRamount();//本金
		if(ramount > 0){
				UserAccount proxyAccount = getUserAccount(repayMent.getProxyaccountid());
				Double balance 			= 0.00;//用户总资产
				Double avlbalance		= 0.00;//可用余额
				Double freezebalance	= 0.00;//冻结余额
				if(proxyAccount != null){
 					balance 		= proxyAccount.getBalance() - ramount;//用户总资产
					avlbalance		= proxyAccount.getAvlbalance() - ramount;//可用余额
					freezebalance	= proxyAccount.getFreezebalance() == null ? 0.00 : proxyAccount.getFreezebalance();//冻结余额
					proxyAccount.setBalance(balance);//用户总资产
					proxyAccount.setAvlbalance(avlbalance);//可用余额
					userAccountServiceI.updateUseraccount(proxyAccount);
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
	 * @Title: InsertOutAccountByRinterestAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为本金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertOutAccountByRinterestAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByRinterestAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为本金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByRinterestAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertOutAccountByRvoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertOutAccountByRvoucherAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByRvoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByRvoucherAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertOutAccountByRvoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertOutAccountByRvoucherintAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByRvoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByRvoucherintAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByDisablevoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByDisablevoucherAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByDisableocamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效滞纳金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByDisableocamountAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByDisablevocamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金滞纳金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByDisablevocamountAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByDisablevoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByDisablevoucherintAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertInAccountByDisablevoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为利息的管理费收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	public void InsertInAccountByInterestexpenseAccInExRecord(RepayMent repayMent){
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
	 * @Title: InsertNormalRepayMentCountAccInExRecord 
	 * @Description: TODO(正常还款  添加记录总控制) 
	 * @param @param repayMent
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @author   cjm  
	 * @throws
	 */
	public void InsertNormalCompenRepayMentCountAccInExRecord(RepayMent repayMent){
		
	}
}
