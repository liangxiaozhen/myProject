package com.ptpl.mapper;

import com.ptpl.model.UserUpgradeRule;
import java.math.BigDecimal;
import java.util.List;

public interface UserUpgradeRuleMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserUpgradeRule record);

    int insertSelective(UserUpgradeRule record);

    UserUpgradeRule selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserUpgradeRule record);

    int updateByPrimaryKey(UserUpgradeRule record);
    
    //获取所有的用户升级规则
    List<UserUpgradeRule> getallUserRule(UserUpgradeRule rule);
}