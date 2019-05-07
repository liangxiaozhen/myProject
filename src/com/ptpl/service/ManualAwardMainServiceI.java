package com.ptpl.service;

import java.util.List;

import com.ptpl.model.ManualAwardMain;

public interface ManualAwardMainServiceI {

	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 获取手动颁奖主活动列表
	 */
	
	public List<ManualAwardMain> getManualAwardMainList(ManualAwardMain mam);
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 向手动颁奖主活动列表插入数据
	 */
	
	public int insertSelective(ManualAwardMain mam);
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 根据主活动编号查出主活动的数据
	 */
	
	public ManualAwardMain selectone(String activityNo);
	
	/**
	 * @author pxl
	 * @date 2016-11-26
	 * 根据属性查出主活动的数据
	 */
	public ManualAwardMain getManualAwardMain(ManualAwardMain mam);

	/**
	 * @author pxl
	 * @date 2016-11-26
	 * 更新主活动中的数据
	 */
	public int updateByPrimaryKeySelective(ManualAwardMain mam);
	
	/**
	 * @author pxl
	 * @date 2016-11-28
	 * 删除手动颁奖主活动
	 */
	public int deleteManualAwardMain(ManualAwardMain mam);

	/*查询主活动   但不等于自身*/
	public ManualAwardMain getManualAwardMainOnly(ManualAwardMain mam);

}
