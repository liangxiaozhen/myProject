package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserBonusPoints;

/**
 * 用户积分Service
 * @author zhenglm
 *
 */
public interface UserBonusPointsServiceI {
	/**
     * 新增用户积分记录
     * @param record
     * @return
     */
    int insertSelective(UserBonusPoints record);
    
    /**
     * 新增用户积分记录
     * @param record
     * @return
     */
    int insert(UserBonusPoints record);
    
    /**
     *（条件）查询用户积分列表
     * @param points
     * @return
     */
    List<UserBonusPoints> findBonusPointsList(UserBonusPoints points);
    List<UserBonusPoints> getfindBonusPointsList(UserBonusPoints ubp);
    /**
     * 根据主键查看用户积分详情
     * @param id
     * @return
     */
    UserBonusPoints findPointsDetailById(BigDecimal id);
    
    /**
     * 根据baseid查看用户积分列表
     * @param points
     * @return
     */
    List<UserBonusPoints> findPointsByBaseid (UserBonusPoints points);
    
    /**
     * 修改用户积分记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserBonusPoints record);
    
    /**
     * 根据登陆的用户名查看积分
     * @author Fengck
     */
    
    UserBonusPoints getUserBounsPointsByLoginName(String loginname);

    /**
     * 根据时间按段来查询积分记录
     */
	List<UserBonusPoints> findPointsByPeriod(UserBonusPoints ubp);
}
