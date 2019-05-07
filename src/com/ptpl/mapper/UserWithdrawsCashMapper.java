package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ptpl.model.UserWithdrawsCash;
/**
 * 
 * @author xiaoy
 *
 * @version 2016年5月27日 下午4:38:27
 */
public interface UserWithdrawsCashMapper {
	/**
	 * 添加提现记录（根据参数选择）
	 * @param userWithdrawsCash
	 * @return
	 */
	int insertSelective(UserWithdrawsCash userWithdrawsCash);
	/**
	 * 根据订单编号 查询提现记录
	 * @param id
	 * @return UserWithdrawsCash
	 */
	UserWithdrawsCash selectByCashNo(String cashNo);
	
	/**
	 * 更新提现记录（根据参数选择） 
	 * @param userWithdrawsCash
	 * @return
	 */
	int updateByCashNo(UserWithdrawsCash userWithdrawsCash);
	/**
	 * 动态查询
	 * @param userWithdrawsCash
	 * @return
	 */
	List<UserWithdrawsCash> selective(UserWithdrawsCash userWithdrawsCash);
	/**
	 * 用户后台
	 *   获取提现记录
	 * @param userId
	 * @return
	 */
	List<UserWithdrawsCash> getUseWithdrawsCashNote (UserWithdrawsCash uwdCash);
	/**
	 *  获取日提现次数和日提现金额
	 * @param baseid   id
	 * @return
	 */
	UserWithdrawsCash selectCountAmountForId (BigDecimal baseid);
	/**
	 *  对账查询
	 * @param userWithdrawsCash
	 * @return
	 */
	List<UserWithdrawsCash> selectContrast(UserWithdrawsCash userWithdrawsCash);

}