package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.GuaranteeFeeRecordMapper;
import com.ptpl.model.GuaranteeFeeRecord;
import com.ptpl.service.GuaranteeFeeRecordServiceI;

public class GuaranteeFeeRecordServiceImpl implements GuaranteeFeeRecordServiceI {
	
	@Autowired
	GuaranteeFeeRecordMapper guaranteeFeeRecordMapper;
 
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return guaranteeFeeRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(GuaranteeFeeRecord record) {
		return guaranteeFeeRecordMapper.insertSelective(record);
	}

	@Override
	public GuaranteeFeeRecord selectByPrimaryKey(BigDecimal id) {
		return guaranteeFeeRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GuaranteeFeeRecord record) {
		return guaranteeFeeRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<GuaranteeFeeRecord> selectAllByCondition(GuaranteeFeeRecord record) {
		return guaranteeFeeRecordMapper.selectAllByCondition(record);
	}

	@Override
	public GuaranteeFeeRecord selectByUtOrderNo(String orderno) {
		return guaranteeFeeRecordMapper.selectByUtOrderNo(orderno);
	}
}
