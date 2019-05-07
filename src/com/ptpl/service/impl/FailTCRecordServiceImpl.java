package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.FailTCRecordMapper;
import com.ptpl.model.FailTCRecord;
import com.ptpl.service.FailTCRecordServiceI;

public class FailTCRecordServiceImpl implements FailTCRecordServiceI {
	
	protected SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	FailTCRecordMapper failTCRecordMapper;

	@Override
	public int insertSelective(FailTCRecord record) {
		return failTCRecordMapper.insertSelective(record);
	}

	@Override
	public FailTCRecord selectByPrimaryKey(BigDecimal id) {
		return failTCRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FailTCRecord record) {
		return failTCRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<FailTCRecord> findFailTCRecordList(FailTCRecord record) {
		return failTCRecordMapper.findFailTCRecordList(record);
	}

	@Override
	public void dealTime(FailTCRecord record) {
		if(record.getMadetime() != null){
			record.setMadetimeStr(sf.format(record.getMadetime()));
		}
		if(record.getDealdate() != null){
			record.setDealdateStr(sf.format(record.getDealdate()));
		}
		if(record.getAudittime() != null){
			record.setAudittimeStr(sf.format(record.getAudittime()));
		}
	}

	@Override
	public List<FailTCRecord> findFailTCRecordByInvestorId(FailTCRecord record) {
		return failTCRecordMapper.findFailTCRecordByInvestorId(record);
	}

}
