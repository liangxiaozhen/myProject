package com.ptpl.mapper;

import com.ptpl.model.WCCAwardRule;
import java.math.BigDecimal;
import java.util.List;

public interface WCCAwardRuleMapper {
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(提抵卷奖励规则数据增加方法) 
	* @param @param wCCAwardRule
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(WCCAwardRule wCCAwardRule);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(提抵卷奖励规则数据增加方法，非空值) 
    * @param @param wCCAwardRule
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(WCCAwardRule wCCAwardRule);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(提抵卷奖励规则根据Id 删除方法) 
     * @param @param wCCAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(WCCAwardRule wCCAwardRule);
      /**
     * 
     * @Title: update 
     * @Description: TODO(提抵卷奖励规则更新方法) 
     * @param @param wCCAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  update(WCCAwardRule wCCAwardRule); 
     /**
     * 
     * @Title: findWCCAwardRules
     * @Description: TODO(提抵卷奖励规则查询全部) 
     * @param @param wCCAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<WCCAwardRule> findWCCAwardRules(WCCAwardRule wCCAwardRule);
    
    /**
     * 
     * @Title: findWCCAwardRules
     * @Description: TODO(提抵卷活动奖励规则    根据ID查询对应的信息) 
     * @param @param wCCAwardRule
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    WCCAwardRule selectByPrimaryKey(BigDecimal id);
  
   
}