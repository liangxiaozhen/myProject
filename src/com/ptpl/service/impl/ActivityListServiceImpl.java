package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ActivityListMapper;
import com.ptpl.model.ActivityList;
import com.ptpl.service.ActivityListServiceI;

public class ActivityListServiceImpl implements ActivityListServiceI{

	@Autowired
	private ActivityListMapper activityListMapper;
	
	/**
	 * @author pxl
	 * 向活动列表中插入数据
	 * @param record
	 * @return
	 */
	public int insert(ActivityList record) {
		return activityListMapper.insert(record);
	}
	 /**
	  * @author pxl
	  * 查询活动列表中的数据
	  * @param record
	  * @return
	  */
	public List<ActivityList> getActivityList(ActivityList al) {
		return activityListMapper.getActivityList(al);
	}


	/**
	 * @author pxl
	 * @date 2016-11-18
	 * 更新活动列表（比如：状态）
	 */
	public int updateActivityList(ActivityList al) {
		return activityListMapper.updateActivityList(al);
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-19
	 * 活动列表详情
	 */
	public ActivityList getActListByAl(ActivityList al) {
		
		return activityListMapper.getActListByAl(al);
	}

	
	/**
	 * @author pxl
	 * @date 2016-11-28
	 * 根据活动编号删除对应的活动
	 */	
	public int deleteActivityListByActNo(String actno) {
		return activityListMapper.deleteActivityListByActNo(actno);
	}

	@Override
	public int insertSelective(ActivityList record) {
		return activityListMapper.insertSelective(record);
	}
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return activityListMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int deleteByActNo(String actno) {
		return activityListMapper.deleteByActNo(actno);
	}
	@Override
	public int updateByPrimaryKeySelective(ActivityList al) {
		return activityListMapper.updateByPrimaryKeySelective(al);
	}

}
