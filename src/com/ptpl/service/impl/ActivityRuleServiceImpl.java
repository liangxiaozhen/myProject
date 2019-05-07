package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.ActivityRuleMapper;
 import com.ptpl.model.ActivityRule;
import com.ptpl.service.ActivityRuleServiceI;
/**
 * 
 * 标的活动规则业务层
 * ActivityRuleServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月25日 12:03:21
 * @version 1.0.0
 *
 */
public class ActivityRuleServiceImpl implements ActivityRuleServiceI{
	
	@Autowired
	private ActivityRuleMapper activityRuleMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的活动规则数据增加方法) 
	* @param @param activityRule
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public int insert(ActivityRule activityRule) {
 		return activityRuleMapper.insert(activityRule);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的活动规则数据增加方法，非空值) 
    * @param @param activityRule
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public int insertSelective(ActivityRule activityRule) {
 		return activityRuleMapper.insertSelective(activityRule);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的活动规则根据Id 删除方法) 
     * @param @param activityRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return activityRuleMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的活动规则更新方法) 
     * @param @param activityRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(ActivityRule activityRule) {
 		return activityRuleMapper.updateById(activityRule);
	}
	 /**
     * 
     * @Title: findActivityRules
     * @Description: TODO(标的活动规则查询全部) 
     * @param @param activityRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<ActivityRule> findActivityRules(ActivityRule activityRule) {
 		return activityRuleMapper.findActivityRules(activityRule);
	}
  	/**
     * 
    * @Title: findActivityRuleById
    * @Description: TODO(根据Id查询对应的标的活动规则信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public ActivityRule findActivityRuleById(BigDecimal id) {
 		return activityRuleMapper.findActivityRuleById(id);
	}
}
