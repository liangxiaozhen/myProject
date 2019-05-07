package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AheadRepayAwardMapper;
import com.ptpl.model.AheadRepayAward;
import com.ptpl.service.AheadRepayAwardServiceI;

public class AheadRepayAwardServiceImpl implements AheadRepayAwardServiceI {

	@Autowired
	private AheadRepayAwardMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AheadRepayAward record) {
		return mapper.insertSelective(record);
	}

	@Override
	public AheadRepayAward selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AheadRepayAward record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
     
	@Override
	public List<AheadRepayAward> selectahpayAwardBytid(BigDecimal tid) {
		return mapper.selectahpayAwardBytid(tid);
	}

	@Override
	public List<AheadRepayAward> selectAheadRepayZY(AheadRepayAward aheadRepayAward) {
		// TODO Auto-generated method stub
		return mapper.selectAheadRepayZY(aheadRepayAward);
	}

	@Override
	public List<AheadRepayAward> selectAheadAwardMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectAheadAwardMap(map);
	}
}
