package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.CloseTimeMapper;
import com.ptpl.model.CloseTime;
import com.ptpl.service.CloseTimeServiceI;
import com.ptpl.web.util.StringUtil;

public class CloseTimeServiceImpl implements CloseTimeServiceI {
	@Autowired
	private CloseTimeMapper closeTimeMapper;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return closeTimeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CloseTime record)
	{
		// TODO Auto-generated method stub
		cleared(record);
		if (compareToDateTime(record))
		{
			return closeTimeMapper.insertSelective(record);
		} else
		{
			return -2;
		}
	}

	@Override
	public CloseTime selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		CloseTime closeTime = closeTimeMapper.selectByPrimaryKey(id);
		closeTime.setAddtimeStr(sdf.format(closeTime.getAddtime()));
		closeTime.setBtimeStr(sdf.format(closeTime.getBtime()));
		closeTime.setEtimeStr(sdf.format(closeTime.getEtime()));
		return closeTime;
	}

	@Override
	public int updateByPrimaryKeySelective(CloseTime record)
	{
		// TODO Auto-generated method stub
		if (compareToDateTime(record))
		{
			return closeTimeMapper.updateByPrimaryKeySelective(record);
		} else
		{
			return -2;
		}
	}

	@Override
	public List<CloseTime> selective(CloseTime record)
	{
		// TODO Auto-generated method stub
		List<CloseTime> list = closeTimeMapper.selective(record);
		for (CloseTime closeTime : list)
		{
			closeTime.setBtimeStr(sdf.format(closeTime.getBtime()));
			closeTime.setEtimeStr(sdf.format(closeTime.getEtime()));
		}
		return list;
	}

	@Override
	public List<CloseTime> selectTimeNameAndTimeNo()
	{
		// TODO Auto-generated method stub
		return closeTimeMapper.selectTimeNameAndTimeNo();
	}

	@Override
	public String selectTimeNoForTimeName(String timename)
	{
		// TODO Auto-generated method stub
		return closeTimeMapper.selectTimeNoForTimeName(timename);
	}

	/**
	 * 整理关闭时间名称
	 * 
	 * @param record
	 */
	public void cleared(CloseTime record)
	{
		String nameno = closeTimeMapper
				.selectTimeNoForTimeName(record.getTimename());
		if (StringUtil.isEmpty(nameno))
		{
			nameno = StringUtil.getNameNoForName("GJGBSJ");
		}
		record.setTimeno(nameno);
	}

	/**
	 * 比较时间段 是否存在
	 * 
	 * @param record
	 * @return
	 */
	public boolean compareToDateTime(CloseTime record)
	{
		CloseTime cTime = new CloseTime();
		// 以时间名称为条件查询
		cTime.setTimename(record.getTimename());
		int id = record.getId()==null?-1:record.getId().intValue();
		long b = record.getBtime().getTime();
		long e = record.getEtime().getTime();
		List<CloseTime> cTimes = closeTimeMapper.selective(cTime);
		for (CloseTime ct : cTimes)
		{
			int cid = ct.getId().intValue();
			if (id != cid)
			{
				// 开始时间
				long bTime = ct.getBtime().getTime();
				// 结束时间
				long eTime = ct.getEtime().getTime();
				// 添加的开始时间b和结束时间 e，在已存在的 时间段内
				if (b > bTime && e < eTime)
				{
					return false;
				} // 添加开始时间小于查询结束时间，添加结束时间大于查询结束时间
				if (b < eTime && e > eTime)
				{
					return false;
				} // 添加开始时间小于查询开始时间，添加结束时间大于查询开始时间
				if (b < bTime && e > bTime)
				{
					return false;
				}
			}
		}
		return true;
	}
}
