package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.LoanCertPicpathMapper;
import com.ptpl.model.LoanCertPicpath;
import com.ptpl.service.LoanCertPicpathServiceI;

public class LoanCertPicpathServiceImpl  implements LoanCertPicpathServiceI{
	
	@Autowired
	private LoanCertPicpathMapper loanicertpicpathmapper;

	@Override
	public int insert(LoanCertPicpath record) {
		return loanicertpicpathmapper.insert(record);
	}

	@Override
	public List<LoanCertPicpath> selectbybaseid(String liano) {
		return loanicertpicpathmapper.selectbybaseid(liano);
	}

	@Override
	public int deleteBybaseid(String liano) {
		return loanicertpicpathmapper.deleteBybaseid(liano);
	}

}
