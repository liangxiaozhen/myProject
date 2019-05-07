package com.ptpl.service;

import com.ptpl.model.UserRisk;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 用户风控业务层
 * UserRiskServiceI
 * 创建人:chenjiaming
 * 时间：2016年09月05日 15:37:00
 * @version 1.0.0
 *
 */
public interface UserRiskServiceI {
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(用户风控数据增加方法) 
	* @param @param userRisk
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(UserRisk userRisk);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(用户风控数据增加方法，非空值) 
    * @param @param userRisk
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(UserRisk userRisk);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(用户风控根据Id 删除方法) 
     * @param @param userRisk
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
    /**
     * 
     * @Title: deleteById 
     * @Description: TODO(用户风控根据用户Id 删除方法) 
     * @param @param userRisk
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteByBaseId(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(用户风控更新方法) 
     * @param @param userRisk
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(UserRisk userRisk); 
     /**
     * 
     * @Title: findUserRisks
     * @Description: TODO(用户风控查询全部) 
     * @param @param userRisk
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<UserRisk> findUserRisks(UserRisk userRisk);
    
     /**
     * 
    * @Title: findUserRiskById
    * @Description: TODO(根据Id查询对应的用户风控信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserRisk findUserRiskById(BigDecimal id);
    
    /**
     * @author shenggege
     * @Description: TODO(根据用户登录名查找用户ID) 
     */
    public UserRisk getByUsername (String username);

    /**
     * @Title:ipIsBlacklist
     * @Description: TODO 验证用户ip是否为黑名单限制
     * @author shenggege
     * @param ip ip
     * @param userRisk 当前用户
     * @return
     * true:黑名单
     * flase:正常
     */
	public boolean ipIsBlacklist(String ip, UserRisk userRisk);
	
	/**
	 * @Title:macIsBlacklist
	 * @Description: TODO 验证用户mac是否为黑名单限制
	 * @author shenggege
	 * @param mac 手机mac
	 * @param userRisk 当前用户
	 * @return
	 * true:黑名单
	 * flase:正常
	 */
	public boolean macIsBlacklist(String mac, UserRisk userRisk);
	
	/**
	 * @Title:ipAddressIsBlacklist
	 * @Description: TODO 验证用户ip所属城市是否为黑名单限制
	 * @author shenggege
	 * @param ipAddress Ip所在地址
	 * @param userRisk 当前用户
	 * @return
	 * true:黑名单
	 * flase:正常
	 */
	public boolean ipAddressIsBlacklist(String ipAddress, UserRisk userRisk);
	
	/**
	 * @Title:addResident
	 * @Description: TODO 添加用户常驻城市
	 * @author shenggege
	 * @param city 城市
	 * @param userRisk 当前用户
	 */
	public void addResident(Object city, UserRisk userRisk);
	
	/**
	 * @Title:ipIsRegister
	 * @Description: TODO 验证用户IP是否注册过
	 * @author shenggege
	 * @param ip
	 * @return 
	 * true:注册过
	 * flase:没有注册过
	 */
	public boolean ipIsRegister(String ip);
	
	/**
	 * @Title:cookieIsRegister
	 * @Description: TODO  验证用户cookie是否注册过
	 * @author shenggege
	 * @param cookie
	 * @return
	 * true:注册过
	 * flase:没有注册过
	 */
	public boolean cookieIsRegister(String cookie);
	
	/**
	 * @Title: findAllBlackList
	 * @Description: TODO 查询所有黑名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 List<UserRisk> findAllBlackList();
	 
	/**
	 * @Title: findAllWhiteList
	 * @Description: TODO 查询所有白名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 List<UserRisk> findAllWhiteList();
		 
	/**
	 * @Title: findAllSuspiciousList
	 * @Description: TODO 查询所有风险名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 List<UserRisk> findAllSuspiciousList();
			 
	/**
	 * @Title: findAllZymdList
	 * @Description: TODO 查询所有应急改密名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 List<UserRisk> findAllZymdList();
	 
	 /**
	  * @Title:selectUserRiskByCondition
	  * @Description: TODO 通过单条条件查询整条信息
	  * @author shenggege
	  */
	 List<UserRisk> selectUserRiskByCondition(UserRisk userRisk);

	 List<UserRisk> selectDixngXiangCondition(UserRisk userRisk);
}