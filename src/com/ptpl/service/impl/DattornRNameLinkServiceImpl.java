package com.ptpl.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.DattornRNameLinkMapper;
import com.ptpl.model.DattornRNameLink;
import com.ptpl.service.DattornRNameLinkServiceI;
/**
 * @author liuj
 * 债转排除名单服务实现层
 */
public class DattornRNameLinkServiceImpl implements DattornRNameLinkServiceI {

	@Autowired
	private DattornRNameLinkMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DattornRNameLink record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DattornRNameLink record) {
		return mapper.insertSelective(record);
	}

	@Override
	public DattornRNameLink selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DattornRNameLink record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DattornRNameLink record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public DattornRNameLink selectByTid(BigDecimal tid) {
		return mapper.selectByTid(tid);
	}

}
