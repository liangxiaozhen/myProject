package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.OverdueRecoveryMapper;
import com.ptpl.model.OverdueRecovery;
import com.ptpl.service.OverdueRecoveryServiceI;

public class OverdueRecoveryServiceImpl implements OverdueRecoveryServiceI {
    
	@Autowired
	private OverdueRecoveryMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(OverdueRecovery record) {
		return mapper.insertSelective(record);
	}

	@Override
	public OverdueRecovery selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OverdueRecovery record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OverdueRecovery record) {
		return mapper.updateByPrimaryKey(record);
	}
    
	@Override
	public List<OverdueRecovery> selectoverRecBytid(BigDecimal tid) {
		return mapper.selectoverRecBytid(tid);
	}
}
