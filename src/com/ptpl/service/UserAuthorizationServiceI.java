package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserAuthorization;

/**
 * 
 * 用户授权记录业务层
 * UserAuthorizationServiceI
 * 创建人:cjm
 * 时间：2017年02月27日 17:00:38
 * @version 1.0.0
 *
 */
public interface UserAuthorizationServiceI {

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
    int insert(UserAuthorization userAuthorization);

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
    int insertSelective(UserAuthorization userAuthorization);
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
    int deleteById(BigDecimal id);
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
     int  updateById(UserAuthorization userAuthorization); 
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
    List<UserAuthorization> findUserAuthorizations(UserAuthorization userAuthorization);
    
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
    UserAuthorization findUserAuthorizationById(BigDecimal id);
  
    /**
     * 
    * @Title: findUserAuthorizationByBaseId 
    * @Description: TODO(根据baseId查询对应的用户授权记录信息) 
    * @param @return  参数说明 
    * @return UserAuthorization    返回类型 
    * @author cjm
    * @throws
     */
    UserAuthorization findUserAuthorizationByBaseId(BigDecimal baseID);
}
