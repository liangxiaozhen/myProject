package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AGTPLinkMapper;
import com.ptpl.mapper.AgentGradePromoAuthMapper;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.service.AGTPLinkServiceI;

public class AGTPLinkServiceImpl implements AGTPLinkServiceI {

	@Autowired
	AGTPLinkMapper mapper;
	@Autowired
	AgentGradePromoAuthMapper agentGradePromoAuthMapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AGTPLink record)
	{
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public AGTPLink selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AGTPLink record)
	{
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AGTPLink> selectByProxyGrade(BigDecimal proxyGrade)
	{
		List<AGTPLink> list = mapper.selectByProxyGrade(proxyGrade);
		List<AgentGradePromoAuth> agpaList = agentGradePromoAuthMapper.selective(null);
		for (AGTPLink link : list)
		{
			for (AgentGradePromoAuth agpa : agpaList)
			{
				if (link.getAgpaid().equals(agpa.getId()))
				{
					link.setName(agpa.getThirdpartyname());
					break;
				}
			}
		}
		return list;
	}

	@Override
	public int deleteByAGPAID(BigDecimal agpaID)
	{
		// TODO Auto-generated method stub
		return mapper.deleteByAGPAID(agpaID);
	}

	@Override
	public int deleteByProxyGrade(BigDecimal proxyGrade)
	{
		// TODO Auto-generated method stub
		return mapper.deleteByProxyGrade(proxyGrade);
	}

	@Override
	public List<AGTPLink> selectByAGPAID(BigDecimal AGPAID)
	{
		// TODO Auto-generated method stub
		return mapper.selectByAGPAID(AGPAID);
	}

	@Override
	public AGTPLink selectByProxyGradeAndAGAPID(BigDecimal proxyGrade, BigDecimal AGPAID)
	{
		// TODO Auto-generated method stub
		return mapper.selectByProxyGradeAndAGAPID(proxyGrade, AGPAID);
	}

}
