package com.ptpl.service;

import com.ptpl.model.UserFsAccountInfo;

public interface UserBalanceQueryServiceI {
	/**
	 * 判断双乾的金额是不是很本地数据库一致 
	 * @param @param userFsAccountInfo
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	String saveUserBalance(UserFsAccountInfo userFsAccountInfo) throws Exception;
	/**
	 * 查询用户的余额,解析getUserBlanaceParams中返回的值,转化为字符串
	 * @param @param UsrCustId
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	/*public String getQueryBalanceBgByUsrCustId(String UsrCustId);*/
	
	/**
	 * 根据用户客户号,也就是双乾的用户乾多多号发送请求得到返回值
	 * @param @param UsrCustId
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	 String getUserBlanaceParams(UserFsAccountInfo userFsAccountInfo);
	 /**
	  * 更新用户账户信息
	  * @param @param result 必须要是双乾返回的
	  * @param @param userFsAccountInfo
	  * @return void
	  * @author jiangxueyou
	  */
	 void updateUserAccount(String result,UserFsAccountInfo userFsAccountInfo);

}
