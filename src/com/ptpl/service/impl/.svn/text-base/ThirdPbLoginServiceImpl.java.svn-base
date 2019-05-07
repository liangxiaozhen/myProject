package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ThirdPbLoginMapper;
 import com.ptpl.model.ThirdPbLogin;
import com.ptpl.service.ThirdPbLoginServiceI;
/**
 * 
 * 第三方登录业务层
 * ThirdPbLoginServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月03日 15:25:40
 * @version 1.0.0
 *
 */
public class ThirdPbLoginServiceImpl implements ThirdPbLoginServiceI{
	
	@Autowired
	private ThirdPbLoginMapper thirdPbLoginMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(第三方登录数据增加方法) 
	* @param @param thirdPbLogin
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.insert(thirdPbLogin);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(第三方登录数据增加方法，非空值) 
    * @param @param thirdPbLogin
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.insertSelective(thirdPbLogin);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(第三方登录根据Id 删除方法) 
     * @param @param thirdPbLogin
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return thirdPbLoginMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(第三方登录更新方法) 
     * @param @param thirdPbLogin
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.updateById(thirdPbLogin);
	}
	 /**
     * 
     * @Title: findThirdPbLogins
     * @Description: TODO(第三方登录查询全部) 
     * @param @param thirdPbLogin
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<ThirdPbLogin> findThirdPbLogins(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.findThirdPbLogins(thirdPbLogin);
	}
  	/**
     * 
    * @Title: findThirdPbLoginById
    * @Description: TODO(根据Id查询对应的第三方登录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public List<ThirdPbLogin> findThirdPbLoginById(BigDecimal id) {
 		return thirdPbLoginMapper.findThirdPbLoginById(id);
	}
	
	 /**
     * 
    * @Title: selectByThirdUserIdAndThirdName 
    * @Description: TODO(根据第三方名称和第三方用户ID查询是否存在) 
    * @param @param thirdPbLogin
    * @param @return  参数说明 
    * @return ThirdPbLogin    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public ThirdPbLogin selectByThirdUserIdAndThirdName(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.selectByThirdUserIdAndThirdName(thirdPbLogin);
	}
	/**
     * 
    * @Title: selectByThirdUserIdAndThirdName 
    * @Description: TODO(根据第三方名称和第三方用户ID,用户ID查询是否存在) 
    * @param @param thirdPbLogin
    * @param @return  参数说明 
    * @return ThirdPbLogin    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public ThirdPbLogin selectByThirdUserIdAndThirdNameBaseID(ThirdPbLogin thirdPbLogin) {
 		return thirdPbLoginMapper.selectByThirdUserIdAndThirdNameBaseID(thirdPbLogin);
	}
	@Override
	public ThirdPbLogin getBaseIdByOtherParams(ThirdPbLogin thirdPbLogin) {
		return thirdPbLoginMapper.getBaseIdByOtherParams(thirdPbLogin);
	}
	@Override
	public ThirdPbLogin selectByPrimaryKey(BigDecimal id) {
		return thirdPbLoginMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteThirdUserByBaseIdAndThirdName(ThirdPbLogin thirdPbLogin) {
		return thirdPbLoginMapper.deleteThirdUserByBaseIdAndThirdName(thirdPbLogin);
	}
 
}
