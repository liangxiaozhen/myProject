
package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SpecialTimeMapper;
import com.ptpl.model.CloseTime;
import com.ptpl.model.SpecialTime;
import com.ptpl.service.SpecialTimeServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 特定时间 service
 * 
 * @author xiaoy
 *
 * @version 2016年7月5日 上午10:19:17
 */
public class SpecialTimeServiceImpl implements SpecialTimeServiceI {
	@Autowired
	private SpecialTimeMapper specialtimeMapper;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return specialtimeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SpecialTime record) {
		// TODO Auto-generated method stub
		cleared(record);
		if (compareToDateTime(record)) {
			return specialtimeMapper.insertSelective(record);
		} else {
			// 存在重复时间段
			return -2;
		}

	}

	@Override
	public SpecialTime selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		SpecialTime sTime = specialtimeMapper.selectByPrimaryKey(id);
		// 设置添加时间str
		sTime.setAddtimeStr(sdf.format(sTime.getAddtime()));
		// 设置开始时间str
		sTime.setBtimeStr(sdf.format(sTime.getBtime()));
		// 设置结束时间str
		sTime.setEtimeStr(sdf.format(sTime.getEtime()));
		return sTime;
	}

	@Override
	public List<SpecialTime> selective(SpecialTime specialTime) {
		// TODO Auto-generated method stub
		List<SpecialTime> sTimes = specialtimeMapper.selective(specialTime);
		for (SpecialTime sTime : sTimes) {
			// 设置开始时间str
			sTime.setBtimeStr(sdf.format(sTime.getBtime()));
			// 设置结束时间str
			sTime.setEtimeStr(sdf.format(sTime.getEtime()));
		}
		return sTimes;
	}

	@Override
	public int updateByPrimaryKeySelective(SpecialTime record) {
		// TODO Auto-generated method stub
		if (compareToDateTime(record)) {
			return specialtimeMapper.updateByPrimaryKeySelective(record);
		} else {
			// 存在重复时间段
			return -2;
		}
	}

	/**
	 * 查询时间包名称和编号
	 */
	@Override
	public List<SpecialTime> selectTimeNoAndTimeName() {
		// TODO Auto-generated method stub
		return specialtimeMapper.selectTimeNoAndTimeName();
	}

	/**
	 * 根据类型查询时间包和名称
	 */
	@Override
	public List<SpecialTime> getTimeNoAndTimeName(String timeType) {
		// TODO Auto-generated method stub
		return specialtimeMapper.getTimeNoAndTimeName(timeType);
	}

	/**
	 * 根据时间名称查询时间编号
	 */
	@Override
	public String selectTimeNoForTimeName(String timeName, String timeType) {
		// TODO Auto-generated method stub
		return specialtimeMapper.selectTimeNoForTimeName(timeName, timeType);
	}

	@Override
	public List<SpecialTime> selectTNameTNoTType() {
		// TODO Auto-generated method stub
		return specialtimeMapper.selectTNameTNoTType();
	}

	/**
	 * 整理特定时间名称
	 * 
	 * @param record
	 */
	public void cleared(SpecialTime record) {
		// 查询时间编号
		String nameno = specialtimeMapper.selectTimeNoForTimeName(record.getTimename(), record.getTimetype());
		if (StringUtil.isEmpty(nameno)) {
			// 字符串后追加时间str
			nameno = StringUtil.getNameNoForName("GJTDSJ");
		}
		record.setTimeno(nameno);
	}

	/**
	 * 比较时间段 是否存在
	 * 
	 * @param record
	 * @return
	 */
	public boolean compareToDateTime(SpecialTime record) {
		SpecialTime sTime = new SpecialTime();
		// 以时间名称为条件查询
		sTime.setTimename(record.getTimename());
		// 以时间类型为条件查询
		sTime.setTimetype(record.getTimetype());
		// 以优先等级为条件查询
		sTime.setWlevel(record.getWlevel());
		int id = record.getId()==null?-1:record.getId().intValue();
		long b = record.getBtime().getTime();
		long e = record.getEtime().getTime();
		List<SpecialTime> sTimes = specialtimeMapper.selective(sTime);
		for (SpecialTime st : sTimes) {
			int sid=st.getId().intValue();
			if (id != sid) {
				// 开始时间
				long bTime = st.getBtime().getTime();
				// 结束时间
				long eTime = st.getEtime().getTime();
				// 添加的开始时间b和结束时间 e，在已存在的 时间段内
				if (b > bTime && e < eTime) {
					return false;
				} // 添加开始时间小于查询结束时间，添加结束时间大于查询结束时间
				if (b < eTime && e > eTime) {
					return false;
				} // 添加开始时间小于查询开始时间，添加结束时间大于查询开始时间
				if (b < bTime && e > bTime) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<SpecialTime> selectForTimeNo(String timeno)
	{
		// TODO Auto-generated method stub
		return specialtimeMapper.selectForTimeNo(timeno);
	}
}
