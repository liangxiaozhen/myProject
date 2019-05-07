package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

public interface BaseService<T> {
	//新增
	public int insert(T entity);
	
	//根据条件查询
	public List<T> selectByCondition(T condition);
	
	//更新
	public int update(T entity);
	
	//删除
	public int delete(BigDecimal id);
	
}
