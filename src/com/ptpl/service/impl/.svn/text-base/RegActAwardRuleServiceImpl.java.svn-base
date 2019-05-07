package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RegActAwardRuleMapper;
import com.ptpl.model.RegActAwardRule;
import com.ptpl.service.RegActAwardRuleServiceI;

public class RegActAwardRuleServiceImpl implements RegActAwardRuleServiceI {
	
	@Autowired
	RegActAwardRuleMapper regActAwardRuleMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return regActAwardRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertRegActAwardRuleSelective(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.insertRegActAwardRuleSelective(regActAwardRule);
	}

	@Override
	public List<RegActAwardRule> findRegActAwardRuleList(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.findRegActAwardRuleList(regActAwardRule);
	}

	@Override
	public List<RegActAwardRule> selectByLinkRegActivityRule(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.selectByLinkRegActivityRule(regActAwardRule);
	}

	@Override
	public RegActAwardRule selectByPrimaryKey(BigDecimal id) {
		return regActAwardRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.updateByPrimaryKeySelective(regActAwardRule);
	}

	@Override
	public int updateByActId(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.updateByActId(regActAwardRule);
	}

	@Override
	public int deleteByActId(BigDecimal actid) {
		return regActAwardRuleMapper.deleteByActId(actid);
	}

	@Override
	public List<RegActAwardRule> selectByActId(BigDecimal actid) {
		return regActAwardRuleMapper.selectByActId(actid);
	}

	@Override
	public RegActAwardRule selectByActIdAndBusiness(RegActAwardRule regActAwardRule) {
		return regActAwardRuleMapper.selectByActIdAndBusiness(regActAwardRule);
	}
}
