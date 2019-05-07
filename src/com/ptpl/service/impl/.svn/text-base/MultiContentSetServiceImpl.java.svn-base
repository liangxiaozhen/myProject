package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.MultiContentSetMapper;
import com.ptpl.model.MultiContentSet;
import com.ptpl.service.MultiContentSetServiceI;

public class MultiContentSetServiceImpl implements MultiContentSetServiceI {
   
	@Autowired
	private MultiContentSetMapper contentSetMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return contentSetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MultiContentSet record) {
		return contentSetMapper.insertSelective(record);
	}

	@Override
	public MultiContentSet selectByPrimaryKey(BigDecimal id) {
		return contentSetMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(MultiContentSet record) {
		return contentSetMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MultiContentSet> selectAllMult(MultiContentSet contentSet) {
		return contentSetMapper.selectAllMult(contentSet);
	}

	@Override
	public List<MultiContentSet> selectByMultiNo(String multino) {
		return contentSetMapper.selectByMultiNo(multino);
	}

	@Override
	public String selectLastNo() {
		return contentSetMapper.selectLastNo();
	}

	@Override
	public List<MultiContentSet> selectALLisneedmult() {
		return contentSetMapper.selectALLisneedmult();
	}

	@Override
	public int updateByMultiNo(MultiContentSet record) {
		return contentSetMapper.updateByMultiNo(record);
	}

	@Override
	public int updateByPrimaryKeySelective(MultiContentSet record) {
		return contentSetMapper.updateByPrimaryKeySelective(record);
	}
}
