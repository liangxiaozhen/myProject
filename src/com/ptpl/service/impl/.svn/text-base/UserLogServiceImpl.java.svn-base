package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserLogMapper;
 import com.ptpl.model.UserLog;
import com.ptpl.service.UserLogServiceI;
/**
 * 
 * 用户日志业务层
 * UserLogServiceI
 * 创建人:chenjiaming
 * 时间：2016年07月15日 11:47:43
 * @version 1.0.0
 *
 */
public class UserLogServiceImpl implements UserLogServiceI{
	
	@Autowired
	private UserLogMapper userLogMapper;
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(用户日志数据增加方法) 
	* @param @param userLog
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(UserLog userLog) {
 		return userLogMapper.insert(userLog);
	}
	 /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(用户日志数据增加方法，非空值) 
    * @param @param userLog
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(UserLog userLog) {
 		return userLogMapper.insertSelective(userLog);
	}
	 /**
     * 
     * @Title: deleteById 
     * @Description: TODO(用户日志根据Id 删除方法) 
     * @param @param userLog
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(UserLog userLog) {
 		return userLogMapper.deleteById(userLog);
	}
	  /**
     * 
     * @Title: updateById 
     * @Description: TODO(用户日志更新方法) 
     * @param @param userLog
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(UserLog userLog) {
 		return userLogMapper.updateById(userLog);
	}
	 /**
     * 
     * @Title: findUserLogs
     * @Description: TODO(用户日志查询全部) 
     * @param @param userLog
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<UserLog> findUserLogs(UserLog userLog) {
 		return userLogMapper.findUserLogs(userLog);
	}
	/**
     * 
    * @Title: findUserLogById 
    * @Description: TODO(根据Id查询对应的用户日志信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public UserLog findUserLogById(BigDecimal id) {
 		return userLogMapper.findUserLogById(id);
	}
}
