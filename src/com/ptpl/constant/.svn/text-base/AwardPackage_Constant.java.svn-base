package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class AwardPackage_Constant {
	// 是否下架（1代表上架，0代表下架）
	public static final Short iscancel_xiajia = 0;
	public static final Short iscancel_shangjia = 1;
	public static Map<Short, String> iscancel_map;

	static {
		iscancel_map = new HashMap<Short, String>();
		iscancel_map.put(iscancel_shangjia, "上架");
		iscancel_map.put(iscancel_xiajia, "下架");
	}
	
	/*
	 *是否为模板
	 */
	public static final Short ISTEMPLET_YES = 1;//是
	public static final Short ISTEMPLET_NO = 2;//否
	public static Map<Short, String> ISTEMPLET_MAP;
	static{
		ISTEMPLET_MAP = new HashMap<Short,String>();
		ISTEMPLET_MAP.put(ISTEMPLET_YES, "是");
		ISTEMPLET_MAP.put(ISTEMPLET_NO, "否");
	}
}
