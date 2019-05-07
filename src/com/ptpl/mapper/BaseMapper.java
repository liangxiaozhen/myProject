package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;


public interface BaseMapper<T> {
		/**
		 * 新增
		 * @return int
		 */
		public int insert(T entity);
		/**
		 * 根据条件查找信息
		 * @return List
		 */
		public List<T> selectByCondition(T condition);
		/**
		 * 更新
		 * @return int
		 */
		public int update(T entity);
		/**
		 * 删除
		 * @return int
		 */
		public int delete(BigDecimal id);
}
