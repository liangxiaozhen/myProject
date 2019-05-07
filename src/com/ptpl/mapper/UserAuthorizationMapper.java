package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserAuthorization;

/**
 * 
 * 用户授权记录Dao接口层
 * UserAuthorizationMapper
 * 创建人:cjm
 * 时间：2017年02月27日 17:00:38
 * @version 1.0.0
 *
 */
public interface UserAuthorizationMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(用户授权记录数据增加方法) 
	* @param @param userAuthorization
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
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
    * @author chenjiaming
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
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(用户授权记录 更新方法) 
     * @param @param userAuthorization
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(UserAuthorization userAuthorization); 
     /**
     * 
     * @Title: findUserAuthorizations
     * @Description: TODO(用户授权记录 查询全部) 
     * @param @param userAuthorization
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<UserAuthorization> findUserAuthorizations(UserAuthorization userAuthorization);
    
     /**
     * 
    * @Title: findUserAuthorizationById 
    * @Description: TODO(根据Id查询对应的用户授权记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
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
