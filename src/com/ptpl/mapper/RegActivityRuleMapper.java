package com.ptpl.mapper;

import com.ptpl.model.RegActivityRule;
import java.math.BigDecimal;
import java.util.List;

/**
 * 注册活动规则Mapper
 * @author zhenglm
 */
public interface RegActivityRuleMapper {
	
	/**
	 * 删除注册活动规则
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增注册活动规则（参数可选）
     * @param regActivityRule
     * @return
     */
    int insertRegActivityRuleSelective(RegActivityRule regActivityRule);

    /**
     * （条件）获取注册活动规则列表
     * @param regActivityRule
     * @return
     */
    List<RegActivityRule> findRegActivityRuleList(RegActivityRule regActivityRule);
    
    /**
     * 查看注册活动规则详情
     * @param id
     * @return
     */
    RegActivityRule selectByPrimaryKey(BigDecimal id);

    /**
     * 根据主键ID更新注册活动规则（参数可选）
     * @param regActivityRule
     * @return
     */
    int updateByPrimaryKeySelective(RegActivityRule regActivityRule);
    
    /**
     * 根据活动编号查询注册活动
     * @param actno
     * @return
     */
    RegActivityRule selectByActNo(String actno);
}