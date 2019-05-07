package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.LoaninfoauditMapper;
import com.ptpl.model.Loaninfoaudit;
import com.ptpl.service.loanInfoAuditServiceI;
/**
 * 
 * @author lihs
 * @date:2016年08月19日 17:57:00
 * @description:借贷人信息审核业务
 */
public class LoaninfoauditServiceImpl implements loanInfoAuditServiceI {

	@Autowired
	private LoaninfoauditMapper loaninfoauditmapper;
	
	public int insert(Loaninfoaudit model) {
			return   loaninfoauditmapper.insert(model);		 
	}

	@Override
	public List<Loaninfoaudit> selectBaseByid(int id) {	
		List<Loaninfoaudit> listloan=new ArrayList<>();
		listloan=loaninfoauditmapper.selectBaseByid(id);
		return listloan;
	}

	@Override
	public Loaninfoaudit selectByPrimaryKey(BigDecimal id) {	
		return loaninfoauditmapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return loaninfoauditmapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Loaninfoaudit record) {
		return loaninfoauditmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Loaninfoaudit> selectAll() {
		return loaninfoauditmapper.selectAll();
	}

	@Override
	public List<Loaninfoaudit> selectIdAndauditstatus(Loaninfoaudit record) {
		return loaninfoauditmapper.selectIdAndauditstatus(record);
	}

	@Override
	public Loaninfoaudit selectminute(BigDecimal id) {
		return loaninfoauditmapper.selectminute(id);
	}

	@Override
	public List<Loaninfoaudit> selectliano(BigDecimal baseid) {
		return loaninfoauditmapper.selectliano(baseid);
	}

	@Override
	public Loaninfoaudit selectloaninfo(String liano) {
		return loaninfoauditmapper.selectloaninfo(liano);
	}

}
