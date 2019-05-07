package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pxl
 * @date 2016-11-16
 * 活动列表的活动状态
 *
 */
public class ActivityList_Constant {

	/**
	 * 活动状态
	 * 1启用，2失效，3启动中，4已执行 5未执行
	 */
	public static Short ACTIVITY_ENABLE=1;//启用
	public static Short ACTIVITY_INVALID=2;//失效
	public static Short ACTIVITY_STARTUP=3;//启动中
	public static Short ACTIVITY_EXECUTED=4;//已执行
	public static Short ACTIVITY_UNTAKEN=5;//未执行
	
	public static Map<Short,String> STATUS_MAP;
	static{
		STATUS_MAP = new HashMap<Short,String>();
		STATUS_MAP.put(ACTIVITY_ENABLE, "启用");
		STATUS_MAP.put(ACTIVITY_INVALID, "失效");
		STATUS_MAP.put(ACTIVITY_STARTUP, "启动中");
		STATUS_MAP.put(ACTIVITY_EXECUTED, "已执行");
		STATUS_MAP.put(ACTIVITY_UNTAKEN, "未执行");
		
	}
	
}
