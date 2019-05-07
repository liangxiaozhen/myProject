package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AheadRepayPlatformMapper;
import com.ptpl.model.AheadRepayPlatform;
import com.ptpl.service.AheadRepayPlatformServiceI;

public class AheadRepayPlatformServiceImpl implements AheadRepayPlatformServiceI {
    
	@Autowired
	private AheadRepayPlatformMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AheadRepayPlatform record) {
		return mapper.insertSelective(record);
	}

	@Override
	public AheadRepayPlatform selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AheadRepayPlatform record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
    
	@Override
	public List<AheadRepayPlatform> selectPayplatBytid(BigDecimal tid) {
		return mapper.selectPayplatBytid(tid);
	}

	@Override
	public List<AheadRepayPlatform> selectAheadRepayByStyle(AheadRepayPlatform aheadRepayPlatform) {
		// TODO Auto-generated method stub
		return mapper.selectAheadRepayByStyle(aheadRepayPlatform);
	}

	@Override
	public List<AheadRepayPlatform> selectAheadPlatMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectAheadPlatMap(map);
	}
}
