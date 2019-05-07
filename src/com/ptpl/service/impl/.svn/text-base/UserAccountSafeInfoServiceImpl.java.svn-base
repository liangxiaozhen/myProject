package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserAccountSafeInfoMapper;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserAccountSafeInfoServiceI;

public class UserAccountSafeInfoServiceImpl implements UserAccountSafeInfoServiceI {

	@Autowired
	UserAccountSafeInfoMapper userAccountSafeInfoMapper;

	@Override
	public int insert(UserAccountSafeInfo uasi) {

		return userAccountSafeInfoMapper.insert(uasi);
	}

	@Override
	public UserAccountSafeInfo getUserAccountSafeInfoById(BigDecimal id) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @Title: update @Description: TODO(根据用户名查询密码) @param @param
	 *         getLoginPassWordByLoginName @param @return 参数说明 @return int
	 *         返回类型 @author chenjiaming @throws
	 */
	@Override
	public UserAccountSafeInfo getLoginPassWordByLoginName(UserBaseAccountInfo userBaseAccountInfo) {
		return userAccountSafeInfoMapper.getLoginPassWordByLoginName(userBaseAccountInfo);
	}

	/**
	 * 
	 * @Title: update @Description: TODO(动态更新) @param @param
	 *         userAccountSafeInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	@Override
	public int update(UserAccountSafeInfo userAccountSafeInfo) {
		return userAccountSafeInfoMapper.update(userAccountSafeInfo);
	}

	/**
	 * 
	 * @Title: selectByBaseId @Description:
	 *         TODO(根据用户ID查询对应用户账号安全信息) @param @param id @param @return
	 *         参数说明 @return UserAccountSafeInfo 返回类型 @author chenjiaming @throws
	 */
	@Override
	public UserAccountSafeInfo selectByBaseId(BigDecimal id) {
		return userAccountSafeInfoMapper.selectByBaseId(id);
	}

	/**
	 * 
	 * @Title: deleteByBaseId @Description: TODO(根据用户ID删除) @param @param
	 *         id @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	@Override
	public int deleteByBaseId(BigDecimal id) {
		return userAccountSafeInfoMapper.deleteByBaseId(id);
	}

	@Override
	public int getUserAccountsafeInfoCountByUgrade(Integer ugrade) {
		return userAccountSafeInfoMapper.getUserAccountsafeInfoCountByUgrade(ugrade);
	}

	@Override
	public List<UserAccountSafeInfo> getmember() {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.getmember();
	}

	@Override
	public List<UserAccountSafeInfo> getuseradmin(Short ugrade) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.getuseradmin(ugrade);
	}

	/**
	 * @author shenggege
	 * @Description: TODO(根据用户登录名查找用户ID)
	 */
	@Override
	public UserAccountSafeInfo getByUsername(String username) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.getByUsername(username);
	}

	@Override
	public int getCountByUgrade(Integer ugrade) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.getCountByUgrade(ugrade);
	}

	@Override
	public int getCountByUgradeExp(Integer ugrade) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.getCountByUgradeExp(ugrade);
	}

	@Override
	public UserAccountSafeInfo selectBaseId(BigDecimal baseid) {
		// TODO Auto-generated method stub
		return userAccountSafeInfoMapper.selectBaseId(baseid);
	}

	@Override
	public Integer getIdByPwd(String pwd) {
		return userAccountSafeInfoMapper.getIdByPwd(pwd);
	}

	@Override
	public int updateBatchUserGrade(String ids, Short ugrade) {
		String[] arr = ids.split(",");
		int length = arr.length;//需要更新的数量
		int size = 0;//更新成功条数
		for (String id : arr) {
			UserAccountSafeInfo uasi = selectBaseId(new BigDecimal(id));
			uasi.setUgrade(ugrade);
			size += update(uasi);
		}
		return length == size ? 1 : 0;
	}
}
