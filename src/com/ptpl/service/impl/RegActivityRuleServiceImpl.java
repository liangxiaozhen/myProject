package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RegActivityRuleMapper;
import com.ptpl.model.RegActivityRule;
import com.ptpl.service.RegActivityRuleServiceI;

public class RegActivityRuleServiceImpl implements RegActivityRuleServiceI{
	
	@Autowired
	RegActivityRuleMapper regActivityRuleMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return regActivityRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertRegActivityRuleSelective(RegActivityRule regActivityRule) {
		return regActivityRuleMapper.insertRegActivityRuleSelective(regActivityRule);
	}

	@Override
	public List<RegActivityRule> findRegActivityRuleList(RegActivityRule regActivityRule) {
		return regActivityRuleMapper.findRegActivityRuleList(regActivityRule);
	}

	@Override
	public RegActivityRule selectByPrimaryKey(BigDecimal id) {
		return regActivityRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RegActivityRule regActivityRule) {
		return regActivityRuleMapper.updateByPrimaryKeySelective(regActivityRule);
	}

	@Override
	public RegActivityRule selectByActNo(String actno) {
		return regActivityRuleMapper.selectByActNo(actno);
	}
}
