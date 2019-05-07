package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserLoanMaterialMapper;
import com.ptpl.model.UserLoanMaterial;
import com.ptpl.service.UserLoanMaterialServiceI;

public class UserLoanMaterialServiceImpl implements UserLoanMaterialServiceI {
   
	@Autowired
	private UserLoanMaterialMapper loanMaterialMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return loanMaterialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserLoanMaterial record) {
		return loanMaterialMapper.insertSelective(record);
	}

	@Override
	public UserLoanMaterial selectByPrimaryKey(BigDecimal id) {
		return loanMaterialMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(UserLoanMaterial record) {
		return loanMaterialMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLoanMaterial record) {
		return loanMaterialMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserLoanMaterial> selectAllUserLoanmat(UserLoanMaterial loanMaterial) {
		return loanMaterialMapper.selectAllUserLoanmat(loanMaterial);
	}

	@Override
	public int updateMaterialBynonid(String loanno, BigDecimal baseid) {
		return loanMaterialMapper.updateMaterialBynonid(loanno, baseid);
	}

	@Override
	public List<UserLoanMaterial> selectTogether(UserLoanMaterial loanMaterial) {
		return loanMaterialMapper.selectTogether(loanMaterial);
	}

	@Override
	public int deleteBylondid(UserLoanMaterial loanMaterial) {
		return loanMaterialMapper.deleteBylondid(loanMaterial);
	}

	@Override
	public List<UserLoanMaterial> lookuserloanMaterial(String loanno) {
		return loanMaterialMapper.lookuserloanMaterial(loanno);
	}

	@Override
	public List<UserLoanMaterial> isaudok(String loanno) {
		return loanMaterialMapper.isaudok(loanno);
	}

	@Override
	public int selectallsize(String loanno) {
		return loanMaterialMapper.selectallsize(loanno);
	}

	@Override
	public int selectbyok(String loanno) {
		return loanMaterialMapper.selectbyok(loanno);
	}

	 
}
