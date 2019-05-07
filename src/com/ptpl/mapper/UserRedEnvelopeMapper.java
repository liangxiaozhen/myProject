package com.ptpl.mapper;

import com.ptpl.model.UserRedEnvelope;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户红包Mapper
 * @author zhenglm
 *
 */
public interface UserRedEnvelopeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserRedEnvelope red);

    /**
     * 新增红包记录
     * @param red
     * @return
     */
    int insertSelective(UserRedEnvelope red);

    /**
     * 根据主键获取用户红包表记录详情
     * @param id
     * @return
     */
    UserRedEnvelope selectByPrimaryKey(BigDecimal id);

    /**
     * 更新红包记录
     * @param red
     * @return
     */
    int updateByPrimaryKeySelective(UserRedEnvelope red);
    
    /**
     * 获取用户红包表记录列表
     * @param red
     * @return
     */
    List<UserRedEnvelope> findRedEnvelopeList(UserRedEnvelope red);
    List<UserRedEnvelope> getselectAll(UserRedEnvelope redEnv);
    /**
     * 根据baseid获取用户红包表记录列表
     * @param red
     * @return
     */
    List<UserRedEnvelope> selectByBaseid(UserRedEnvelope red);

    
    /**
     * @author pxl
     * 将红包作废
     */
    int updateRedEnvById(UserRedEnvelope redEnv);

    
    /**
     * 根据ID查询用户红包记录
     * @param id
     * @return
     */
    UserRedEnvelope findUserRedEnvelopeById(BigDecimal id);
    
    /**
     * 根据登陆用户迷名获取红包记录表
     * @author fengck
     */
    List<UserRedEnvelope> getUserRedBaoByLoginName(String loginname);

    /**
     *查找时间段已领取的红包
     */
	List<UserRedEnvelope> findUserRedEnvelope(UserRedEnvelope ure);
	
	/**
	 *查找最近三个月的已领取的红包
	 */
	List<UserRedEnvelope> findRedEnvelopeListThreeMonths(UserRedEnvelope ure);
    
	
	 /**
     * 判断领取后的红包券是否到期
     */
	List<UserRedEnvelope> findRedEnvelopeListByStatus();
}