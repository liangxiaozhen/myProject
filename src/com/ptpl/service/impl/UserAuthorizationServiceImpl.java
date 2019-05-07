package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.UserAuthorizationMapper;
 import com.ptpl.model.UserAuthorization;
import com.ptpl.service.UserAuthorizationServiceI;
/**
 * 
 * 用户授权记录业务层
 * UserAuthorizationServiceI
 * 创建人:cjm
 * 时间：2017年02月27日 17:00:38
 * @version 1.0.0
 *
 */
public class UserAuthorizationServiceImpl implements UserAuthorizationServiceI{
	
	@Autowired
	private UserAuthorizationMapper userAuthorizationMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(用户授权记录数据增加方法) 
	* @param @param userAuthorization
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(UserAuthorization userAuthorization) {
 		return userAuthorizationMapper.insert(userAuthorization);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(用户授权记录数据增加方法，非空值) 
    * @param @param userAuthorization
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(UserAuthorization userAuthorization) {
 		return userAuthorizationMapper.insertSelective(userAuthorization);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(用户授权记录根据Id 删除方法) 
     * @param @param userAuthorization
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return userAuthorizationMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(用户授权记录更新方法) 
     * @param @param userAuthorization
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(UserAuthorization userAuthorization) {
 		return userAuthorizationMapper.updateById(userAuthorization);
	}
	 /**
     * 
     * @Title: findUserAuthorizations
     * @Description: TODO(用户授权记录查询全部) 
     * @param @param userAuthorization
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<UserAuthorization> findUserAuthorizations(UserAuthorization userAuthorization) {
 		return userAuthorizationMapper.findUserAuthorizations(userAuthorization);
	}
  	/**
     * 
    * @Title: findUserAuthorizationById
    * @Description: TODO(根据Id查询对应的用户授权记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserAuthorization    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public UserAuthorization findUserAuthorizationById(BigDecimal id) {
 		return userAuthorizationMapper.findUserAuthorizationById(id);
	}
	
	/**
     * 
    * @Title: findUserAuthorizationByBaseId
    * @Description: TODO(根据baseID查询对应的用户授权记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserAuthorization    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public UserAuthorization findUserAuthorizationByBaseId(BigDecimal baseID) {
 		return userAuthorizationMapper.findUserAuthorizationByBaseId(baseID);
	}
}
