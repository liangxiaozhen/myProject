package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserUpgradeRuleMapper;
import com.ptpl.model.UserUpgradeRule;
import com.ptpl.service.UserUpgradeRuleServiceI;

/**
 * @author liuj
 * @date:2016年08月29日 16:30
 * @description:用户等级升级规则
 */
public class UserUpgradeRuleServiceImpl implements UserUpgradeRuleServiceI{
    
	@Autowired
	private UserUpgradeRuleMapper userUpgradeRuleMapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return userUpgradeRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserUpgradeRule record) {
		return userUpgradeRuleMapper.insert(record);
	}

	@Override
	public int insertSelective(UserUpgradeRule record) {
		return userUpgradeRuleMapper.insertSelective(record);
	}

	@Override
	public UserUpgradeRule selectByPrimaryKey(BigDecimal id) {
		return userUpgradeRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserUpgradeRule record) {
		return userUpgradeRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserUpgradeRule record) {
		return userUpgradeRuleMapper.updateByPrimaryKey(record);
	}

	 @Override
	public List<UserUpgradeRule> getallUserRule(UserUpgradeRule rule) {
		return userUpgradeRuleMapper.getallUserRule(rule);
	}
 
}
