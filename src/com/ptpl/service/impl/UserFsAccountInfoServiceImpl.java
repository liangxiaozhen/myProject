package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.UserFsAccountInfoMapper;
 import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserFsAccountInfoServiceI;
/**
 * 
 * 用户托管账户信息业务层
 * UserFsAccountInfoServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月10日 12:20:02
 * @version 1.0.0
 *
 */
public class UserFsAccountInfoServiceImpl implements UserFsAccountInfoServiceI{
	
	@Autowired
	private UserFsAccountInfoMapper userFsAccountInfoMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(用户托管账户信息数据增加方法) 
	* @param @param userFsAccountInfo
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(UserFsAccountInfo userFsAccountInfo) {
 		return userFsAccountInfoMapper.insert(userFsAccountInfo);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(用户托管账户信息数据增加方法，非空值) 
    * @param @param userFsAccountInfo
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(UserFsAccountInfo userFsAccountInfo) {
 		return userFsAccountInfoMapper.insertSelective(userFsAccountInfo);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(用户托管账户信息根据Id 删除方法) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return userFsAccountInfoMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(用户托管账户信息更新方法) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(UserFsAccountInfo userFsAccountInfo) {
 		return userFsAccountInfoMapper.updateById(userFsAccountInfo);
	}
	 /**
     * 
     * @Title: findUserFsAccountInfos
     * @Description: TODO(用户托管账户信息查询全部 返回多个对象) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<UserFsAccountInfo> findUserFsAccountInfos(UserFsAccountInfo userFsAccountInfo) {
 		return userFsAccountInfoMapper.findUserFsAccountInfos(userFsAccountInfo);
	}
	
    /**
     * 
     * @Title: findUserFsAccountInfo
     * @Description: TODO(用户托管账户信息 条件查询 返回单个对象) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public UserFsAccountInfo findUserFsAccountInfo(UserFsAccountInfo userFsAccountInfo){
		return userFsAccountInfoMapper.findUserFsAccountInfo(userFsAccountInfo);
	}
  	/**
     * 
    * @Title: findUserFsAccountInfoById
    * @Description: TODO(根据Id查询对应的用户托管账户信息信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public UserFsAccountInfo findUserFsAccountInfoById(BigDecimal id) {
 		return userFsAccountInfoMapper.findUserFsAccountInfoById(id);
	}
	
	/**
     * 
    * @Title: findUserFsAccountInfoByBaseId 
    * @Description: TODO(根据baseIdId查询对应的用户托管账户信息 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public UserFsAccountInfo findUserFsAccountInfoByBaseId(BigDecimal baseid) {
 		return userFsAccountInfoMapper.findUserFsAccountInfoByBaseId(baseid);
	}
	
	/**
     * 
    * @Title: findUserFsAccountInfoByUsrCustId 
    * @Description: TODO(根据UsrCustId 用户商户号 查询对应的用户托管账户信息 ) 
    * @param @param UsrCustId
    * @param @return  参数说明 
    * @return UserFsAccountInfo    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public UserFsAccountInfo findUserFsAccountInfoByUsrCustId(String UsrCustId) {
 		return userFsAccountInfoMapper.findUserFsAccountInfoByUsrCustId(UsrCustId);
	}
	
	//获取徽商红包账户
	public UserFsAccountInfo getUsrCustId(UserFsAccountInfo u) {
		return userFsAccountInfoMapper.getUsrCustId(u);
	}
}
