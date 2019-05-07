package com.ptpl.service;

import java.util.Map;

import com.ptpl.model.UserAccount;

public interface QueryBlaneServiceI {
	/**
	 * @throws Exception 
	 * 余额查询
	 * @param @param ufs
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 * @throws  
	 */
	Map<String,String> queryBlane(String usercustid);
	/**
	 * 修改用户信息
	 * @param @param map
	 * @param @param userAccount
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	int updateUserAccount(Map<String, String> map, UserAccount userAccount);
	/**
	 * 查询余额+修改用户信息
	 * @param @param usercustid
	 * @param @param baseid
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	int queryBlaneAndUpdateUserAccount(String usercustid,String baseid);

}
