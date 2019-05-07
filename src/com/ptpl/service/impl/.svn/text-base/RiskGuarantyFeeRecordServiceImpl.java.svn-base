package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RiskGuarantyFeeRecordMapper;
import com.ptpl.model.RiskGuarantyFeeRecord;
import com.ptpl.service.RiskGuarantyFeeRecordServiceI;

public class RiskGuarantyFeeRecordServiceImpl implements RiskGuarantyFeeRecordServiceI {
	
	@Autowired
	RiskGuarantyFeeRecordMapper riskGuarantyFeeRecordMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return riskGuarantyFeeRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(RiskGuarantyFeeRecord record) {
		return riskGuarantyFeeRecordMapper.insertSelective(record);
	}

	@Override
	public RiskGuarantyFeeRecord selectByPrimaryKey(BigDecimal id) {
		return riskGuarantyFeeRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskGuarantyFeeRecord record) {
		return riskGuarantyFeeRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<RiskGuarantyFeeRecord> selectAllByCondition(RiskGuarantyFeeRecord record) {
		return riskGuarantyFeeRecordMapper.selectAllByCondition(record);
	}

	@Override
	public RiskGuarantyFeeRecord selectByUtOrderNo(String orderno) {
		return riskGuarantyFeeRecordMapper.selectByUtOrderNo(orderno);
	}
}
