package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.GfundsIntRecordMapper;
import com.ptpl.model.GfundsIntRecord;
import com.ptpl.service.GfundsIntRecordServiceI;

public class GfundsIntRecordServiceImpl implements GfundsIntRecordServiceI {
	
	protected SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	GfundsIntRecordMapper gfundsIntRecordMapper;
	
	@Override
	public int insertSelective(GfundsIntRecord record) {
		return gfundsIntRecordMapper.insertSelective(record);
	}

	@Override
	public GfundsIntRecord selectByPrimaryKey(BigDecimal id) {
		return gfundsIntRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GfundsIntRecord record) {
		return gfundsIntRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<GfundsIntRecord> findGfundsIntRecord(GfundsIntRecord record) {
		return gfundsIntRecordMapper.findGfundsIntRecord(record);
	}

	@Override
	public void dealTime(GfundsIntRecord funds) {
		if(funds.getSysbtime() != null){
			funds.setSysbtimeStr(sf.format(funds.getSysbtime()));
		}
		if(funds.getManbtime() != null){
			funds.setManbtimeStr(sf.format(funds.getManbtime()));
		}
		if(funds.getSysrectime() != null){
			funds.setSysrectimeStr(sf.format(funds.getSysrectime()));
		}
		if(funds.getReceivetime() != null){
			funds.setReceivetimeStr(sf.format(funds.getReceivetime()));
		}
		if(funds.getMadetime() != null){
			funds.setMadetimeStr(sf.format(funds.getMadetime()));
		}
		if(funds.getDealdate() != null){
			funds.setDealdateStr(sf.format(funds.getDealdate()));
		}
		if(funds.getAudittime() != null){
			funds.setAudittimeStr(sf.format(funds.getAudittime()));
		}
	}

	@Override
	public List<GfundsIntRecord> findGfundsIntRecordByInvestorId(GfundsIntRecord record) {
		return gfundsIntRecordMapper.findGfundsIntRecordByInvestorId(record);
	}
	
}
