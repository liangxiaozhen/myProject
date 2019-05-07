package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ActivityAwardRule;

/**
 * 
 * 标的活动奖励规则业务层
 * ActivityAwardRuleServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月26日 16:14:51
 * @version 1.0.0
 *
 */
public interface ActivityAwardRuleServiceI {

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
    int insert(ActivityAwardRule activityAwardRule);

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
    int insertSelective(ActivityAwardRule activityAwardRule);
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
    int deleteById(BigDecimal id);
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
     int  updateById(ActivityAwardRule activityAwardRule); 
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
    List<ActivityAwardRule> findActivityAwardRules(ActivityAwardRule activityAwardRule);
    
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
    ActivityAwardRule findActivityAwardRuleById(BigDecimal id);
    
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
    ActivityAwardRule findActivityAwardRuleByActid(BigDecimal id);
  
   
}
