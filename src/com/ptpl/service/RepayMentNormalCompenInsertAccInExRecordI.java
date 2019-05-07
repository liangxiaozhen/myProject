package com.ptpl.service;

import com.ptpl.model.RepayMent;

public interface RepayMentNormalCompenInsertAccInExRecordI {

	/**
	 * 
	 * @Title: InsertOutAccountByRamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为本金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertOutAccountByRamountAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByRamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为本金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByRamountAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertOutAccountByRinterestAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为本金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertOutAccountByRinterestAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByRinterestAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为本金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByRinterestAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertOutAccountByRvoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertOutAccountByRvoucherAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByRvoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByRvoucherAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertOutAccountByRvoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加借款人类型为类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertOutAccountByRvoucherintAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByRvoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByRvoucherintAccInExRecord(RepayMent repayMent);

	/**
	 * 
	 * @Title: InsertInAccountByDisablevoucherAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByDisablevoucherAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByDisableocamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效滞纳金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByDisableocamountAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByDisablevocamountAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金滞纳金收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByDisablevocamountAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByDisablevoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为失效类现金利息收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByDisablevoucherintAccInExRecord(RepayMent repayMent);
	/**
	 * 
	 * @Title: InsertInAccountByDisablevoucherintAccInExRecord 
	 * @Description: TODO(还款成功时 添加投资人类型为利息的管理费收支记录) 
	 * @param @param repayMent  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	 */
	void InsertInAccountByInterestexpenseAccInExRecord(RepayMent repayMent);

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
	void InsertNormalCompenRepayMentCountAccInExRecord(RepayMent repayMent);
}
