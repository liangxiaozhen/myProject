package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RechargeSNLLinkMapper;
import com.ptpl.model.RechargeSNLLink;
import com.ptpl.service.RechargeSNLLinkServiceI;

public class RechargeSNLLinkServiceImpl implements RechargeSNLLinkServiceI{
	
	@Autowired
	private RechargeSNLLinkMapper rechargeSNLLinkMapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return rechargeSNLLinkMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RechargeSNLLink record) {
		return rechargeSNLLinkMapper.insert(record);
	}

	@Override
	public int insertSelective(RechargeSNLLink record) {
		return rechargeSNLLinkMapper.insertSelective(record);
	}

	@Override
	public RechargeSNLLink selectByPrimaryKey(BigDecimal id) {
		return rechargeSNLLinkMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RechargeSNLLink record) {
		return rechargeSNLLinkMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RechargeSNLLink record) {
		return rechargeSNLLinkMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RechargeSNLLink> selectByrrid(BigDecimal id) {
		return rechargeSNLLinkMapper.selectByrrid(id);
	}

}
