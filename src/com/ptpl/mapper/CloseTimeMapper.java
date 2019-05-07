package com.ptpl.mapper;

import com.ptpl.model.CloseTime;
import java.math.BigDecimal;
import java.util.List;

public interface CloseTimeMapper {
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