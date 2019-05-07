package com.ptpl.service.impl;

import com.ptpl.mapper.LoanItemQuoteMapper;
import com.ptpl.mapper.LoanTypeObjectQuoteMapper;
import com.ptpl.model.LoanItemQuote;
import com.ptpl.service.LoanItemQuoteServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class LoanItemQuoteServiceImpl implements LoanItemQuoteServiceI {
   
	@Autowired
	private LoanItemQuoteMapper itemQuoteMapper;
	@Autowired
	private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LoanItemQuote> selectByConditionLoaninfo(LoanItemQuote itemQuote) {
		return PublicUtil.decorateloanList(selectAllQuote(itemQuote), loanTypeObjectQuoteMapper);
	}
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return itemQuoteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(LoanItemQuote record) {
		return itemQuoteMapper.insertSelective(record);
	}

	@Override
	public LoanItemQuote selectByPrimaryKey(BigDecimal id) {
		return itemQuoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<LoanItemQuote> selectAllQuote(LoanItemQuote itemQuote) {
		return itemQuoteMapper.selectAllQuote(itemQuote);
	}
	@Override
	public int updateByPrimaryKeySelective(LoanItemQuote record) {
		return itemQuoteMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public String selectNeedNo() {
		return itemQuoteMapper.selectNeedNo();
	}
	@Override
	public LoanItemQuote selectloanByLiqno(String linno) {
		return itemQuoteMapper.selectloanByLiqno(linno);
	}
	@Override
	public List<LoanItemQuote> public_XuanZe() {
		return itemQuoteMapper.public_XuanZe();
	}
	@Override
	public List<LoanItemQuote> public_ZiTian() {
		return itemQuoteMapper.public_ZiTian();
	}

	@Override
	public List<LoanItemQuote> selectAllQuote_GongGong() {
		return itemQuoteMapper.selectAllQuote_GongGong();
	}

	@Override
	public List<LoanItemQuote> selectAllQuote_BuChong() {
		return itemQuoteMapper.selectAllQuote_BuChong();
	}
	@Override
	public Short selectMaxSeriesno1() {
		return itemQuoteMapper.selectMaxSeriesno1();
	}

	@Override
	public int updateByLiqno(LoanItemQuote itemQuote) {
		return itemQuoteMapper.updateByLiqno(itemQuote);
	}
	
}
