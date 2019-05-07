package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ActivityList;

public interface ActivityListServiceI {
	/**
	 * @author pxl
	 * 向活动列表中插入数据
	 * @param record
	 * @return
	 */
	 int insert(ActivityList record);

	 /**
	  * 新增活动列表（参数可选）
	  * @param record
	  * @return
	  */
	 int insertSelective(ActivityList record);
	 
	 /**
	  * @author pxl
	  * 查询活动列表中的数据
	  * @param record
	  * @return
	  */
	public List<ActivityList> getActivityList(ActivityList al);
	
	/**
	 * @author pxl
	 * @date 2016-11-18
	 * 更新活动列表（比如：状态）
	 */
	public int updateActivityList(ActivityList al);
	
	/**
	 * @author pxl
	 * @date 2016-11-19
	 * 活动列表详情
	 */
	ActivityList getActListByAl(ActivityList al);

	
	/**
	 * @author pxl
	 * @date 2016-11-28
	 * 根据活动编号删除对应的活动
	 */
	int deleteActivityListByActNo(String actno);

	
	/**
	 * 删除活动列表
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(BigDecimal id);

	
	/**
	 * 根据活动编号actno删除活动列表
	 * @param id
	 * @return
	 */
	int deleteByActNo(String actno);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(根据活动编号更新活动列表)
	 * @param al
	 * @return int
	 */
	int updateByPrimaryKeySelective(ActivityList al);

}
