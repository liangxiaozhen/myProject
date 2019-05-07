package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.ActivityAwardRuleMapper;
 import com.ptpl.model.ActivityAwardRule;
import com.ptpl.service.ActivityAwardRuleServiceI;
/**
 * 
 * 标的活动奖励规则业务层
 * ActivityAwardRuleServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月26日 16:14:51
 * @version 1.0.0
 *
 */
public class ActivityAwardRuleServiceImpl implements ActivityAwardRuleServiceI{
	
	@Autowired
	private ActivityAwardRuleMapper activityAwardRuleMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的活动奖励规则数据增加方法) 
	* @param @param activityAwardRule
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(ActivityAwardRule activityAwardRule) {
 		return activityAwardRuleMapper.insert(activityAwardRule);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的活动奖励规则数据增加方法，非空值) 
    * @param @param activityAwardRule
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(ActivityAwardRule activityAwardRule) {
 		return activityAwardRuleMapper.insertSelective(activityAwardRule);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的活动奖励规则根据Id 删除方法) 
     * @param @param activityAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return activityAwardRuleMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的活动奖励规则更新方法) 
     * @param @param activityAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(ActivityAwardRule activityAwardRule) {
 		return activityAwardRuleMapper.updateById(activityAwardRule);
	}
	 /**
     * 
     * @Title: findActivityAwardRules
     * @Description: TODO(标的活动奖励规则查询全部) 
     * @param @param activityAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<ActivityAwardRule> findActivityAwardRules(ActivityAwardRule activityAwardRule) {
 		return activityAwardRuleMapper.findActivityAwardRules(activityAwardRule);
	}
  	/**
     * 
    * @Title: findActivityAwardRuleById
    * @Description: TODO(根据Id查询对应的标的活动奖励规则信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public ActivityAwardRule findActivityAwardRuleById(BigDecimal id) {
 		return activityAwardRuleMapper.findActivityAwardRuleById(id);
	}
	/**
     * 
    * @Title: findActivityAwardRuleById 
    * @Description: TODO(根据标的活动Id查询对应的标的活动奖励规则 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public ActivityAwardRule findActivityAwardRuleByActid(BigDecimal id) {
 		return activityAwardRuleMapper.findActivityAwardRuleByActid(id);
	}
}
