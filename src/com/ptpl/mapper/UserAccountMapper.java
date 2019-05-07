package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;

public interface UserAccountMapper {
    int insert(UserAccount record);

    int insertSelective(UserAccount record);
    
    /**
     * 
    * @Title: deleteByBaseId 
    * @Description: TODO(根据用户ID删除) 
    * @param @param id
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int deleteByBaseId(BigDecimal id);
    
    /**
     * 
    * @Title: getUserAccountByBaseId 
    * @Description: TODO(根据用户ID 查询对应实体信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserAccount    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserAccount  getUserAccountByBaseId(BigDecimal id);
    /**
     * 用户账户信息
     * @param ubai
     * @return
     */
    List<UserAccount> queryAllUserAccount(UserBaseAccountInfo ubai);
    /**
     * 修该用户信息表的信息:比如说可用余额,总资产等
     * @param userAccount
     * @return
     */
   int updateUseraccount(UserAccount userAccount);
   
   //修改总资产
   int updategetuseraccount(UserAccount userAccount);
   
   /**
    * 更新用户账户表的积分
    * @param usAc
    * @return
    */
   int updateUserAccountPoint(UserAccount usAc);
 
   /**
    * 更新用户账户表的类现金，假现金
    * @param usAc
    * @return
    */
	int updateUserAccountEnvelope(UserAccount ua);
	/**
	 * 通过登陆的用户名来查询资金情况
	 * @author fengCk
	 */
	
	UserAccount getUserAccountByLoginName(String loginname);
	
	/**
	 * 更新用户的账户余额和可用余额
	 */
	int updateUserAccountBalAvl(UserAccount usAc);
	
}