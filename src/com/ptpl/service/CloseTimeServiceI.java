package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.CloseTime;

public interface CloseTimeServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(CloseTime record);

	CloseTime selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(CloseTime record);
	/**
	 * 动态查询
	 * @param record
	 * @return
	 */
	List<CloseTime> selective(CloseTime record);
	/**
	 * 查询所有的关闭 时间名称 和 时间编号
	 * @return
	 */
	List<CloseTime> selectTimeNameAndTimeNo();
	/**
	 * 查询时间编号
	 * @param timename 时间名称
	 * @return
	 */
	String selectTimeNoForTimeName(String timename);
}
