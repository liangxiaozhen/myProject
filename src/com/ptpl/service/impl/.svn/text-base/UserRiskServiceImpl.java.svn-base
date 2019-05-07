package com.ptpl.service.impl;

import com.ptpl.mapper.UserRiskMapper;
import com.ptpl.model.UserRisk;
import com.ptpl.service.UserRiskServiceI;
import org.springframework.beans.factory.annotation.Autowired;

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
public class UserRiskServiceImpl implements UserRiskServiceI{
	
	@Autowired
	private UserRiskMapper userRiskMapper;
	
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
	@Override
	public int insert(UserRisk userRisk) {
 		return userRiskMapper.insert(userRisk);
	}
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
	@Override
	public int insertSelective(UserRisk userRisk) {
 		return userRiskMapper.insertSelective(userRisk);
	}
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
	@Override
	public int deleteById(BigDecimal id) {
 		return userRiskMapper.deleteById(id);
	}
	/**
     * 
     * @Title: deleteByBaseId 
     * @Description: TODO(用户风控根据用户Id 删除方法) 
     * @param @param userRisk
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteByBaseId(BigDecimal id) {
 		return userRiskMapper.deleteByBaseId(id);
	}
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
	@Override
	public int updateById(UserRisk userRisk) {
 		return userRiskMapper.updateById(userRisk);
	}
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
	@Override
	public List<UserRisk> findUserRisks(UserRisk userRisk) {
 		return userRiskMapper.findUserRisks(userRisk);
	}
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
	@Override
	public UserRisk findUserRiskById(BigDecimal id) {
 		return userRiskMapper.findUserRiskById(id);
	}
	

    /**
     * @author shenggege
     * @Description: TODO(根据用户登录名查找用户ID) 
     */
	@Override
	public UserRisk getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRiskMapper.getByUsername(username);
	}
	
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
	@Override
	public boolean ipIsBlacklist(String ip, UserRisk userRisk) {
		// TODO Auto-generated method stub
		return false;
	}
	
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
	@Override
	public boolean macIsBlacklist(String mac, UserRisk userRisk) {
		// TODO Auto-generated method stub
		return false;
	}
	
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
	@Override
	public boolean ipAddressIsBlacklist(String ipAddress, UserRisk userRisk) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @Title:addResident
	 * @Description: TODO 添加用户常驻城市
	 * @author shenggege
	 * @param city 城市
	 * @param userRisk 当前用户
	 */
	@Override
	public void addResident(Object city, UserRisk userRisk) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @Title:ipIsRegister
	 * @Description: TODO 验证用户IP是否注册过
	 * @author shenggege
	 * @param ip
	 * @return 
	 * true:注册过
	 * flase:没有注册过
	 */
	@Override
	public boolean ipIsRegister(String ip) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @Title:cookieIsRegister
	 * @Description: TODO  验证用户cookie是否注册过
	 * @author shenggege
	 * @param cookie
	 * @return
	 * true:注册过
	 * flase:没有注册过
	 */
	@Override
	public boolean cookieIsRegister(String cookie) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @Title: findAllBlackList
	 * @Description: TODO 查询所有黑名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	@Override
	public List<UserRisk> findAllBlackList(){
		return userRiskMapper.findAllBlackList();
	}
	 
	/**
	 * @Title: findAllWhiteList
	 * @Description: TODO 查询所有白名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 @Override
	 public List<UserRisk> findAllWhiteList(){
		 return userRiskMapper.findAllWhiteList();
	 }
		 
	/**
	 * @Title: findAllSuspiciousList
	 * @Description: TODO 查询所有风险名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 @Override
	 public List<UserRisk> findAllSuspiciousList(){
		 return userRiskMapper.findAllSuspiciousList();
	 }
			 
	/**
	 * @Title: findAllZymdList
	 * @Description: TODO 查询所有应急改密名单信息
	 * @author shenggege
	 * @param 
	 * @return
	 * @throws
	 */
	 @Override
	 public List<UserRisk> findAllZymdList(){
		 return userRiskMapper.findAllZymdList();
	 }
	 
	 /**
	  * @Title:selectUserRiskByCondition
	  * @Description: TODO 通过单条条件查询整条信息
	  * @author shenggege
	  */
	 @Override
	 public List<UserRisk> selectUserRiskByCondition(UserRisk userRisk){
		 return userRiskMapper.selectUserRiskByCondition(userRisk);
	 }
	@Override
	public List<UserRisk> selectDixngXiangCondition(UserRisk userRisk) {
		// TODO Auto-generated method stub
		return userRiskMapper.selectDixngXiangCondition(userRisk);
	}
}
