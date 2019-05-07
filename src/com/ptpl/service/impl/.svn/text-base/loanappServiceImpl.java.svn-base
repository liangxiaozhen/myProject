package com.ptpl.service.impl;

import com.ptpl.mapper.OftenLoanListMapper;
import com.ptpl.mapper.loanappMapper;
import com.ptpl.model.OftenLoanList;
import com.ptpl.model.loanapp;
import com.ptpl.service.loanappServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class loanappServiceImpl implements loanappServiceI {

	@Autowired
	private loanappMapper loanappmapper;
	@Autowired
	private OftenLoanListMapper oftenLoanListMapper;

	@Override
	public int insert(loanapp record) {
		return loanappmapper.insert(record);
	}

	@Override
	public List<loanapp> selectbaseid(int id) {
		return loanappmapper.selectBaseByid(id);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return loanappmapper.deleteByPrimaryKey(id);
	}

	@Override
	public loanapp selectByPrimaryKey(BigDecimal id) {
		return loanappmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(loanapp record) {
		return loanappmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<loanapp> selectloanAll() {
		return loanappmapper.selectloanAll();
	}

	@Override
	public List<loanapp> selectloaninfos(loanapp record) {
		return loanappmapper.selectloaninfos(record);
	}

	@Override
	public List<Map<BigDecimal, BigDecimal>> findloanappCountByBaseid(loanapp record) {
		return loanappmapper.findloanappCountByBaseid(record);
	}

	@Override
	public int insertSelective(loanapp record) {
		return loanappmapper.insertSelective(record);
	}

	@Override
	public List<loanapp> selectloanAppbyappStatus() {
		return loanappmapper.selectloanAppbyappStatus();
	}

	@Override
	public List<loanapp> intervalloanapp(loanapp loanapp) {
		return loanappmapper.intervalloanapp(loanapp);
	}

	@Override
	public int selectcount(BigDecimal id) {
		return loanappmapper.selectcount(id);
	}

	@Override
	public List<loanapp> selectdaterange(String startdate, String enddate) {
		return loanappmapper.selectdaterange(startdate, enddate);
	}

	@Override
	public int updateByPrimaryKey(loanapp record) {
		return loanappmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<loanapp> selectByParam(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return loanappmapper.selectByParam(map);
	}

	/**
	 *
	 * @param loanapp 借款对象
	 * @param isoften 是否把该用户加入到常用借款人列表中：1是 0否
	 * @author liuqh
	 */
	@Override
	public void insertOrUpdateLoanappAndOftenLoan(loanapp loanapp, String isoften) {
		if (loanapp.getId() != null) {
			loanappmapper.updateByPrimaryKeySelective(loanapp);
		} else {
			loanappmapper.insertSelective(loanapp);
		}
		if ("1".equals(isoften)) {
			OftenLoanList oftenLoanList = new OftenLoanList();
			oftenLoanList.setBaseid(loanapp.getBaseid());
			oftenLoanList.setLoanappid(loanapp.getId());
			oftenLoanList.setAddtime(new Date());
			oftenLoanListMapper.insertSelective(oftenLoanList);
		}
	}

	@Override
	public List<loanapp> selectloanappAudit(loanapp loanapp) {
		return loanappmapper.selectloanappAudit(loanapp);
	}
}
