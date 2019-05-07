package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.controller.moneymoremore.model.LoanWithdrawsCallbackBean;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
/**
 * 
 * @author xiaoy
 *      提现记录  接口
 * @date 2016年5月27日 下午4:40:15
 */
public interface UserWithdrawsCashServiceI {
	/**
	 * 添加提现记录（根据参数选择）
	 * @param userWithdrawsCash
	 * @return
	 */
	int insertSelective(UserWithdrawsCash uwdCash);
	/**
	 * 根据订单编号查询提现记录
	 * @param userWithdrawsCash
	 * @return 
	 */
	UserWithdrawsCash selectByCashNo(String cashNo);
	/**
	 * 更新提现记录
	 * @param userWithdrawsCash
	 * @return 
	 */
	int updateByCashNo(UserWithdrawsCash userWithdrawsCash);
	/**
	 * 提现成功，使用此方法更新记录
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年4月19日 上午10:03:56 
	 * @param userWithdrawsCash
	 * @return
	 */
	int updateForSuccess(UserWithdrawsCash userWithdrawsCash);
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
	
	void withdrawsCash(HttpServletRequest request,HttpServletResponse response,Double money,Double fee,UserBaseAccountInfo ubai,Short originClient);
}
