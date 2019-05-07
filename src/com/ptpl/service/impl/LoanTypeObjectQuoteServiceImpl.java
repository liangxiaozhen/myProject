package com.ptpl.service.impl;

import com.ptpl.mapper.LoanTypeObjectQuoteMapper;
import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class LoanTypeObjectQuoteServiceImpl implements LoanTypeObjectQuoteServiceI {
    
	@Autowired
	private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper;


	@Override
	public int insertSelective(LoanTypeObjectQuote record) {
		return loanTypeObjectQuoteMapper.insertSelective(record);
	}

	@Override
	public LoanTypeObjectQuote selectByPrimaryKey(BigDecimal id) {
		return loanTypeObjectQuoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(LoanTypeObjectQuote record) {
		return loanTypeObjectQuoteMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LoanTypeObjectQuote> gettypeObjectQuotes(LoanTypeObjectQuote loanTypeObjectQuote) {
		return loanTypeObjectQuoteMapper.gettypeObjectQuotes(loanTypeObjectQuote);
	}

	@Override
	public List<LoanTypeObjectQuote> selectIsuse() {
		return loanTypeObjectQuoteMapper.selectIsuse();
	}

	@Override
	public int updateByPrimaryKeySelective(LoanTypeObjectQuote record) {
		return loanTypeObjectQuoteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public LoanTypeObjectQuote selectBySerialNo(BigDecimal serialno) {
		return loanTypeObjectQuoteMapper.selectBySerialNo(serialno);
	}

	@Override
	public Short selectMaxSerialNo() {
		return loanTypeObjectQuoteMapper.selectMaxSerialNo();
	}


}
