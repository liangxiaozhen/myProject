package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.TltemRNameLinkMapper;
import com.ptpl.model.TltemRNameLink;
import com.ptpl.service.TltemRNameLinkServiceI;

public class TltemRNameLinkServiceImpl implements TltemRNameLinkServiceI {

	@Autowired
	private TltemRNameLinkMapper tltemrnamelinkmapper;
	
	@Override
	public int insert(TltemRNameLink record) {
		return tltemrnamelinkmapper.insert(record);
	}

	@Override
	public int insertSelective(TltemRNameLink record) {
		return tltemrnamelinkmapper.insertSelective(record);
	}

	@Override
	public List<TltemRNameLink> findTenderRemoveNameByTid(BigDecimal tid) {
		return tltemrnamelinkmapper.findTenderRemoveNameByTid(tid);
	}

}
