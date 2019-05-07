package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ThirdPbLogin;

/**
 * 
 * 第三方登录Dao接口层
 * ThirdPbLoginMapper
 * 创建人:chenjiaming
 * 时间：2016年08月03日 15:25:40
 * @version 1.0.0
 *
 */
public interface ThirdPbLoginMapper {

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
    int insert(ThirdPbLogin thirdPbLogin);

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
    int insertSelective(ThirdPbLogin thirdPbLogin);
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
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(第三方登录 更新方法) 
     * @param @param thirdPbLogin
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(ThirdPbLogin thirdPbLogin); 
     /**
     * 
     * @Title: findThirdPbLogins
     * @Description: TODO(第三方登录 查询全部) 
     * @param @param thirdPbLogin
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<ThirdPbLogin> findThirdPbLogins(ThirdPbLogin thirdPbLogin);
    
     /**
     * 
    * @Title: findThirdPbLoginById 
    * @Description: TODO(根据baseId查询对应的第三方用户 信息) 
    * @param @param id
     */
    List<ThirdPbLogin> findThirdPbLoginById(BigDecimal id);
    
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
    ThirdPbLogin selectByThirdUserIdAndThirdName(ThirdPbLogin thirdPbLogin);
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
    ThirdPbLogin selectByThirdUserIdAndThirdNameBaseID(ThirdPbLogin thirdPbLogin);

	ThirdPbLogin getBaseIdByOtherParams(ThirdPbLogin thirdPbLogin);
	
	ThirdPbLogin selectByPrimaryKey(BigDecimal id);
	int deleteThirdUserByBaseIdAndThirdName(ThirdPbLogin thirdPbLogin);
}
