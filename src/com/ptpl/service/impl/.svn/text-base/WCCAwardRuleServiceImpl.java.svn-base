package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.WCCAwardRuleMapper;
import com.ptpl.model.WCCAwardRule;
import com.ptpl.model.WithdrawsCashCoupon;
import com.ptpl.service.WCCAwardRuleServiceI;
/**
 * 
 * 提抵卷活动奖励规则设置业务层
 * WCCAwardRuleServiceI
 * 创建人:chenjiaming
 * 时间：2016年07月11日 17:39:27
 * @version 1.0.0
 *
 */
public class WCCAwardRuleServiceImpl implements WCCAwardRuleServiceI{
	
	@Autowired
	private WCCAwardRuleMapper wCCAwardRuleMapper;
	
	@Override
	public int insert(WCCAwardRule wCCAwardRule) {
 		return wCCAwardRuleMapper.insert(wCCAwardRule);
	}

	@Override
	public int insertSelective(WCCAwardRule wCCAwardRule) {
 		return wCCAwardRuleMapper.insertSelective(wCCAwardRule);
	}

	@Override
	public int deleteById(WCCAwardRule wCCAwardRule) {
 		return wCCAwardRuleMapper.deleteById(wCCAwardRule);
	}

	@Override
	public int update(WCCAwardRule wCCAwardRule) {
 		return wCCAwardRuleMapper.update(wCCAwardRule);
	}

	@Override
	public List<WCCAwardRule> findWCCAwardRules(WCCAwardRule wCCAwardRule) {
 		return wCCAwardRuleMapper.findWCCAwardRules(wCCAwardRule);
	}

	@Override
	public WCCAwardRule selectByPrimaryKey(BigDecimal id) {
 		return wCCAwardRuleMapper.selectByPrimaryKey(id);
	}

}
