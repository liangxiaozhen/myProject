package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserAccountServiceI;

public class UserAccountServiceImpl implements UserAccountServiceI {
	
	@Autowired
	UserAccountMapper userAccountMapper;
	
	@Override
	public int insert(UserAccount userAccount) {
 		return userAccountMapper.insert(userAccount);
	}

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
	@Override
	public int deleteByBaseId(BigDecimal id) {
 		return userAccountMapper.deleteByBaseId(id);
	}

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
	@Override
	public UserAccount getUserAccountByBaseId(BigDecimal id) {
 		return userAccountMapper.getUserAccountByBaseId(id);
	}

	@Override
	public List<UserAccount> queryAllUserAccount(UserBaseAccountInfo ubai) {
		return userAccountMapper.queryAllUserAccount(ubai);
	}

	@Override
	public int updateUseraccount(UserAccount userAccount) {
		
		return userAccountMapper.updateUseraccount(userAccount);
	}

	@Override
	public int updategetuseraccount(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return userAccountMapper.updategetuseraccount(userAccount);
	}

	@Override
	public int updateUserAccountPoint(UserAccount usAc) {
		// TODO Auto-generated method stub
		return userAccountMapper.updateUserAccountPoint(usAc);
	}

	 /**
     * 更新用户账户表的类现金，假现金
     * @param usAc
     * @return
     */
	public int updateUserAccountEnvelope(UserAccount ua) {
		return userAccountMapper.updateUserAccountEnvelope(ua);
	}

	@Override
	public UserAccount getUserAccountByLoginName(String loginname) {
		// TODO Auto-generated method stub
		return userAccountMapper.getUserAccountByLoginName(loginname);
	}

	/**
	 * 更新用户余额和可用余额
	 */
	@Override
	public int updateUserAccountBalAvl(UserAccount usAc) {
		return userAccountMapper.updateUserAccountBalAvl(usAc);
	}

}
