package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.BaseMapper;
import com.ptpl.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	protected BaseMapper<T> baseMapper;

	@Override
	public int insert(T entity) {

		return baseMapper.insert(entity);
	}

	@Override
	public List<T> selectByCondition(T condtion) {
		return baseMapper.selectByCondition(condtion);
	}

	@Override
	public int update(T entity) {
		return baseMapper.update(entity);
	}

	@Override
	public int delete(BigDecimal id) {
		return baseMapper.delete(id);
	}
}
