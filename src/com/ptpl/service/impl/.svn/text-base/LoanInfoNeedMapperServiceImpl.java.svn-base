package com.ptpl.service.impl;

import com.ptpl.mapper.LoanInfoNeedMapper;
import com.ptpl.mapper.LoanTypeObjectQuoteMapper;
import com.ptpl.model.LoanInfoNeed;
import com.ptpl.service.LoanInfoNeedServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class LoanInfoNeedMapperServiceImpl implements LoanInfoNeedServiceI {
     
	 @Autowired
	 private LoanInfoNeedMapper infoNeedMapper;
	 
	@Autowired
	private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper;
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<LoanInfoNeed> selectByConditionLoaninfo(LoanInfoNeed infoNeed) {
		//把loanInfoNeed中的quoteObject（引用对象000000000000000000000000000000 装饰成：对就的xxxs标   1担保标、2信用标、3房产标、4车贷标	）
		return PublicUtil.decorateloanList(selectAll(infoNeed), loanTypeObjectQuoteMapper);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return infoNeedMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(LoanInfoNeed record) {
		return infoNeedMapper.insertSelective(record);
	}

	@Override
	public LoanInfoNeed selectByPrimaryKey(BigDecimal id) {
		return infoNeedMapper.selectByPrimaryKey(id);
	}

	@Override
	public LoanInfoNeed selectByPrimaryKey2(BigDecimal id) {
		return infoNeedMapper.selectByPrimaryKey2(id);
	}

	@Override
	public int updateByPrimaryKey(LoanInfoNeed record) {
		return infoNeedMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LoanInfoNeed> selectAll(LoanInfoNeed infoNeed) {
		return infoNeedMapper.selectAll(infoNeed);
	}

	@Override
	public String selectNeedNo() {
		return infoNeedMapper.selectNeedNo();
	}

	@Override
	public List<LoanInfoNeed> selectloanByQuote() {
		return infoNeedMapper.selectloanByQuote();
	}

	@Override
	public List<LoanInfoNeed> selectNeedByiscite() {
		return infoNeedMapper.selectNeedByiscite();
	}

	@Override
	public int updateByPrimaryKeySelective(LoanInfoNeed record) {
		return infoNeedMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<LoanInfoNeed> selectloanBypic() {
		return infoNeedMapper.selectloanBypic();
	}

	@Override
	public List<LoanInfoNeed> selectloanByQuoteyy() {
		return infoNeedMapper.selectloanByQuoteyy();
	}

	@Override
	public List<LoanInfoNeed> selectloanBypicyy() {
		return infoNeedMapper.selectloanBypicyy();
	}
}
