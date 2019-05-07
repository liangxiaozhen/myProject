package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserFsAccountInfo;

/**
 * 
 * 用户托管账户信息业务层
 * UserFsAccountInfoServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月10日 12:20:02
 * @version 1.0.0
 *
 */
public interface UserFsAccountInfoServiceI {

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
    int insert(UserFsAccountInfo userFsAccountInfo);

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
    int insertSelective(UserFsAccountInfo userFsAccountInfo);
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
    int deleteById(BigDecimal id);
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
     int  updateById(UserFsAccountInfo userFsAccountInfo); 
     /**
     * 
     * @Title: findUserFsAccountInfos
     * @Description: TODO(用户托管账户信息查询全部) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<UserFsAccountInfo> findUserFsAccountInfos(UserFsAccountInfo userFsAccountInfo);
    
    /**
     * 
     * @Title: findUserFsAccountInfo
     * @Description: TODO(用户托管账户信息 条件查询) 
     * @param @param userFsAccountInfo
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    UserFsAccountInfo findUserFsAccountInfo(UserFsAccountInfo userFsAccountInfo);
    
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
    UserFsAccountInfo findUserFsAccountInfoById(BigDecimal id);
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
    UserFsAccountInfo findUserFsAccountInfoByBaseId(BigDecimal baseid);
    
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
    UserFsAccountInfo findUserFsAccountInfoByUsrCustId(String UsrCustId);

    //获取徽商红包账户
	UserFsAccountInfo getUsrCustId(UserFsAccountInfo u);
  
   
}
