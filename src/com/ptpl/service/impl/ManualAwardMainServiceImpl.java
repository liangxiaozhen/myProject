package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ManualAwardMainMapper;
import com.ptpl.model.ManualAwardMain;
import com.ptpl.service.ManualAwardMainServiceI;

public class ManualAwardMainServiceImpl implements ManualAwardMainServiceI {

	@Autowired
	private ManualAwardMainMapper manualAwardMainMapper;
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 获取手动颁奖主活动列表
	 */
	public List<ManualAwardMain> getManualAwardMainList(ManualAwardMain mam) {
		return manualAwardMainMapper.getManualAwardMainList(mam);
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 向手动颁奖主活动列表插入数据
	 */
	public int insertSelective(ManualAwardMain mam){
		return manualAwardMainMapper.insertSelective(mam);
	}

	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 根据主活动编号查出主活动列表的数据
	 */
	public ManualAwardMain selectone(String activityNo) {
		
		return manualAwardMainMapper.selectone(activityNo);
	}

	/**
	 * @author pxl
	 * @date 2016-11-26
	 * 根据属性查出主活动的数据
	 */
	public ManualAwardMain getManualAwardMain(ManualAwardMain mam) {
		return manualAwardMainMapper.getManualAwardMain(mam);
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-26
	 * 更新主活动中的数据
	 */
	
	public int updateByPrimaryKeySelective(ManualAwardMain mam){
		return manualAwardMainMapper.updateByPrimaryKeySelective(mam);
	}

	/**
	 * @author pxl
	 * @date 2016-11-28
	 * 删除手动颁奖主活动
	 */
	public int deleteManualAwardMain(ManualAwardMain mam) {
		return manualAwardMainMapper.deleteManualAwardMain(mam);
	}

	/*查询主活动  但不等于自身*/
	public ManualAwardMain getManualAwardMainOnly(ManualAwardMain mam) {
		return manualAwardMainMapper.getManualAwardMainOnly(mam);
	}

}
