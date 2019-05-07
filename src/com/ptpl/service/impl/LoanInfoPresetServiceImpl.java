package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.LoanInfoPresetMapper;
import com.ptpl.mapper.LoanTypeObjectQuoteMapper;
import com.ptpl.model.LoanInfoPreset;
import com.ptpl.service.LoanInfoPresetServiceI;
import com.ptpl.web.util.PublicUtil;

public class LoanInfoPresetServiceImpl implements LoanInfoPresetServiceI {
   
	@Autowired
	private LoanInfoPresetMapper infoPresetMapper;
    @Autowired
    private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper; 
	
    
    @SuppressWarnings("unchecked")
	@Override
	public List<LoanInfoPreset> selectByConditionLoaninfo(LoanInfoPreset infoPreset) {
		return PublicUtil.decorateloanList(selectAllPreset(infoPreset), loanTypeObjectQuoteMapper);
	}
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return infoPresetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(LoanInfoPreset record) {
		return infoPresetMapper.insertSelective(record);
	}

	@Override
	public LoanInfoPreset selectByPrimaryKey(BigDecimal id) {
		return infoPresetMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<LoanInfoPreset> selectAllPreset(LoanInfoPreset infoPreset) {
		return infoPresetMapper.selectAllPreset(infoPreset);
	}
	@Override
	public String selectPresetNo() {
		return infoPresetMapper.selectPresetNo();
	}
	@Override
	public int updateByPrimaryKeySelective(LoanInfoPreset record) {
		return infoPresetMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<LoanInfoPreset> selectloanByQuote() {
		return infoPresetMapper.selectloanByQuote();
	}
	@Override
	public List<LoanInfoPreset> selectPresetByiscite() {
		return infoPresetMapper.selectPresetByiscite();
	}
	@Override
	public List<LoanInfoPreset> selectloanByQuoteyy() {
		return infoPresetMapper.selectloanByQuoteyy();
	}
}
