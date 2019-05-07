package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserMakeALoanMapper;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.service.UserMakeALoanServiceI;

public class UserMakeALoanServiceImpl implements UserMakeALoanServiceI {
	
	@Autowired
	UserMakeALoanMapper userMakeALoanMapper;

	@Override
	public int insertSelective(UserMakeALoan record) {
		return userMakeALoanMapper.insertSelective(record);
	}

	@Override
	public UserMakeALoan selectByPrimaryKey(BigDecimal id) {
		return userMakeALoanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserMakeALoan record) {
		return userMakeALoanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserMakeALoan> selectAllLoansRecord(UserMakeALoan record) {
		return userMakeALoanMapper.selectAllLoansRecord(record);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return userMakeALoanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByMLoanOrderNoSelective(UserMakeALoan record) {
		return userMakeALoanMapper.updateByMLoanOrderNoSelective(record);
	}

	@Override
	public List<UserMakeALoan> selectAll(UserMakeALoan record) {
		return userMakeALoanMapper.selectAll(record);
	}

	@Override
	public List<UserMakeALoan> selectByOrderNo(String orderno) {
		return userMakeALoanMapper.selectByOrderNo(orderno);
	}

	@Override
	public UserMakeALoan selectByMLoanOrderNo(String orderno) {
		return userMakeALoanMapper.selectByMLoanOrderNo(orderno);
	}

}
