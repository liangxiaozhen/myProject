package com.ptpl.mapper;

import com.ptpl.model.SpecialTime;
import java.math.BigDecimal;
import java.util.List;
/**
 * 
 * @author xiaoy
 *
 * @version 2016年7月5日 上午10:18:29
 */
public interface SpecialTimeMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(SpecialTime record);

	SpecialTime selectByPrimaryKey(BigDecimal id);

	List<SpecialTime> selective(SpecialTime specialTime);

	int updateByPrimaryKeySelective(SpecialTime record);
	/**
	 * 查询所有时间包编号和时间包名称
	 * 
	 * @return
	 */
	List<SpecialTime> selectTimeNoAndTimeName();
	/**
	 * 查询 时间包编号， 时间包名称
	 * 
	 * @param timeType
	 *            时间包类型 如：奖品
	 * @return
	 */
	List<SpecialTime> getTimeNoAndTimeName(String timeType);
	/**
	 * 查询时间编号
	 * 
	 * @param timeName
	 *            时间包名称
	 * @param timeType
	 *            时间包类型
	 * @return
	 */
	String selectTimeNoForTimeName(String timeName, String timeType);
	/**
	 * 查询 时间包名称，时间包编号，时间包类型
	 * @return
	 */
	List<SpecialTime> selectTNameTNoTType();
	/**
	 * 查询特定时间    升序
	 * @param timeno  时间编号
	 * @return
	 */
	List<SpecialTime> selectForTimeNo(String timeno);
}