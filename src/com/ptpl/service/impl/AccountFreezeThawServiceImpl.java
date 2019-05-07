package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AccountFreezeThawMapper;
import com.ptpl.model.AccountFreezeThaw;
import com.ptpl.service.AccountFreezeThawServiceI;

public class AccountFreezeThawServiceImpl implements AccountFreezeThawServiceI {
	
	@Autowired
	AccountFreezeThawMapper accountFreezeThawMapper;
	@Override
	public void insert(AccountFreezeThaw record) {
		accountFreezeThawMapper.insert(record);

	}

	@Override
	public void insertSelective(AccountFreezeThaw record) {
		accountFreezeThawMapper.insertSelective(record);
	}

	@Override
	public AccountFreezeThaw getByTrxid(String trxid) {
		
		return accountFreezeThawMapper.getByTrxid(trxid);
	}

	@Override
	public void update(AccountFreezeThaw record) {
		accountFreezeThawMapper.update(record);

	}


	@Override
	public AccountFreezeThaw getByOrdId(String OrdId) {
		return accountFreezeThawMapper.getByOrdId(OrdId);
	}

	@Override
	public List<AccountFreezeThaw> queryAll(AccountFreezeThaw aft) {
		return accountFreezeThawMapper.queryAll(aft);
	}

	@Override
	public List<AccountFreezeThaw> getLableSelect(AccountFreezeThaw accountfreezethaw) {
		return accountFreezeThawMapper.getLableSelect(accountfreezethaw);
	}

}
