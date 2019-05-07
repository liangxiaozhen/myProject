package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserLog;

/**
 * 
 * 用户日志业务层
 * UserLogMapper
 * 创建人:chenjiaming
 * 时间：2016年07月15日 11:47:43
 * @version 1.0.0
 *
 */
public interface UserLogMapper {

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
    int insert(UserLog userLog);

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
    int insertSelective(UserLog userLog);
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
    int deleteById(UserLog userLog);
      /**
     * 
     * @Title: update 
     * @Description: TODO(用户日志 更新方法) 
     * @param @param userLog
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(UserLog userLog); 
     /**
     * 
     * @Title: findUserLogs
     * @Description: TODO(用户日志 查询全部) 
     * @param @param userLog
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<UserLog> findUserLogs(UserLog userLog);
    
     /**
     * 
    * @Title: findUserLogById 
    * @Description: TODO(根据Id查询对应的用户日志 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserLog findUserLogById(BigDecimal id);
}
