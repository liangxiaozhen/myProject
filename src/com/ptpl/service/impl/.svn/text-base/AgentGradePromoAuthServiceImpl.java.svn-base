package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AgentGradePromoAuthMapper;
import com.ptpl.mapper.PromoAgentGradeProfitMapper;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.service.AgentGradePromoAuthServiceI;
/**
 * 代理级别的推广权限(第三方推广设置) service
 * 
 * @author xiaoy
 * 
 *
 * @version 2016年10月28日 下午4:24:47
 */
public class AgentGradePromoAuthServiceImpl implements AgentGradePromoAuthServiceI {
	@Autowired
	private AgentGradePromoAuthMapper agentGradePromoAuthMapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return agentGradePromoAuthMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AgentGradePromoAuth record)
	{
		// TODO Auto-generated method stub
		return agentGradePromoAuthMapper.insertSelective(record);
	}

	@Override
	public AgentGradePromoAuth selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return agentGradePromoAuthMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentGradePromoAuth record)
	{
		// TODO Auto-generated method stub
		return agentGradePromoAuthMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AgentGradePromoAuth> selective(AgentGradePromoAuth record)
	{
		
		return agentGradePromoAuthMapper.selective(record);
	}
}
