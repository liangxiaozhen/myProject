package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.MediacyFeeRecordMapper;
import com.ptpl.model.MediacyFeeRecord;
import com.ptpl.service.MediacyFeeRecordServiceI;

public class MediacyFeeRecordServiceImpl implements MediacyFeeRecordServiceI {
	
	@Autowired
	MediacyFeeRecordMapper mediacyFeeRecordMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mediacyFeeRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MediacyFeeRecord record) {
		return mediacyFeeRecordMapper.insertSelective(record);
	}

	@Override
	public MediacyFeeRecord selectByPrimaryKey(BigDecimal id) {
		return mediacyFeeRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MediacyFeeRecord record) {
		return mediacyFeeRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<MediacyFeeRecord> selectAllByCondition(MediacyFeeRecord record) {
		return mediacyFeeRecordMapper.selectAllByCondition(record);
	}

	@Override
	public MediacyFeeRecord selectByUtOrderNo(String orderno) {
		return mediacyFeeRecordMapper.selectByUtOrderNo(orderno);
	}
}
