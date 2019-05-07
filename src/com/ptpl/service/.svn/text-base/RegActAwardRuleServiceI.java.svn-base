package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RegActAwardRule;

/**
 * 注册活动奖励规则Service
 * @author zhenglm
 */
public interface RegActAwardRuleServiceI {
	
	/**
	 * 删除注册活动奖励规则
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增注册活动奖励规则（参数可选）
     * @param record
     * @return
     */
    int insertRegActAwardRuleSelective(RegActAwardRule regActAwardRule);
    
    /**
     * （条件）获取注册活动奖励规则列表
     * @param record
     * @return
     */
    List<RegActAwardRule> findRegActAwardRuleList(RegActAwardRule regActAwardRule);
    
    /**
     * （条件）获取注册活动奖励规则列表（关联注册活动规则表）
     * @param record
     * @return
     */
    List<RegActAwardRule> selectByLinkRegActivityRule(RegActAwardRule regActAwardRule);
    
    /**
     * 查看注册活动奖励规则详情
     * @param id
     * @return
     */
    RegActAwardRule selectByPrimaryKey(BigDecimal id);

    /**
     * 修改注册活动奖励规则（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RegActAwardRule regActAwardRule);

    /**
     * 根据注册活动ID批量更新注册奖励活动状态
     * @param record
     * @return
     */
    int updateByActId(RegActAwardRule regActAwardRule);
	
	/**
	 * 根据注册活动ID删除注册活动奖励规则
	 * @param actid
	 * @return
	 */
    int deleteByActId(BigDecimal actid);
    
    /**
     * 根据注册活动ID查看注册活动奖励规则
     * @param actid
     * @return
     */
    List<RegActAwardRule> selectByActId(BigDecimal actid);
    
    /**
     * 根据注册活动ID和指定业务查看注册活动奖励规则
     * @param regActAwardRule
     * @return
     */
    RegActAwardRule selectByActIdAndBusiness(RegActAwardRule regActAwardRule);
}
