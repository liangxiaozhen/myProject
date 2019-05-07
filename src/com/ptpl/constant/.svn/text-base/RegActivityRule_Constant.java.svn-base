package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册活动规则常量类
 * @author zhenglm
 */
public class RegActivityRule_Constant {

	/** 活动状态-0.待完善 */
	public static final short STATUS_NOCOMPLETE = 0;
	/** 活动状态-1.待执行 */
	public static final short STATUS_PENDINGEXECUTE = 1;
	/** 活动状态-2.执行中 */
	public static final short STATUS_EXECUTING = 2;
	/** 活动状态-3.已完成 */
	public static final short STATUS_EXECUTED = 3;
	/** 活动状态-4.已停用 */
	public static final short STATUS_STOPPED = 4;
	/** 活动状态-5.已作废 */
	public static final short STATUS_SUPERSEDED = 5;
	/** 活动状态-6.已过期 */
	public static final short STATUS_OVERDUE = 6;
	
	/** 获奖名单是否需要审核才生成-1.需要审核 */
	public static final short ISAUDITALIST_YES = 1;
	/** 获奖名单是否需要审核才生成-2.无需审核 */
	public static final short ISAUDITALIST_NO = 2;
	
	/** 是否判断注册时间-0.否 */
	public static final short ISCONSIDERREGTIME_NO = 0;
	/** 是否判断注册时间-1.是 */
	public static final short ISCONSIDERREGTIME_YES = 1;
	
	/** 是否为模板-0.否 */
	public static final short ISTEMPLET_NO = 0;
	/** 是否为模板-1.是 */
	public static final short ISTEMPLET_YES = 1;

	/** 生成方式-1.手动生成 */
	public static final short GENERATETYPE_MANUAL = 1;
	/** 生成方式-2.模板生成 */
	public static final short GENERATETYPE_TEMPLATE = 2;
	
	/** 活动状态MAP-0.待完善,1.待执行,2.执行中,3.已完成,4.已停用,5.已作废,6.已过期  */
	public static Map<Short, String> STATUS_MAP;
	static {
		STATUS_MAP = new HashMap<Short, String>();
		STATUS_MAP.put(STATUS_NOCOMPLETE, "待完善");
		STATUS_MAP.put(STATUS_PENDINGEXECUTE, "待执行");
		STATUS_MAP.put(STATUS_EXECUTING, "执行中");
		STATUS_MAP.put(STATUS_EXECUTED, "已完成");
		STATUS_MAP.put(STATUS_STOPPED, "已停用");
		STATUS_MAP.put(STATUS_SUPERSEDED, "已作废");
		STATUS_MAP.put(STATUS_OVERDUE, "已过期");
	}

	/** 获奖名单是否需要审核才生成MAP-0.无需审核，1.需要审核 */
	public static Map<Short, String> ISAUDITALIST_MAP;
	static {
		ISAUDITALIST_MAP = new HashMap<Short, String>();
		ISAUDITALIST_MAP.put(ISAUDITALIST_YES, "需要审核");
		ISAUDITALIST_MAP.put(ISAUDITALIST_NO, "无需审核");
	}

	/** 是否判断注册时间MAP-0.否，1.是 */
	public static Map<Short, String> ISCONSIDERREGTIME_MAP;
	static {
		ISCONSIDERREGTIME_MAP = new HashMap<Short, String>();
		ISCONSIDERREGTIME_MAP.put(ISCONSIDERREGTIME_NO, "否");
		ISCONSIDERREGTIME_MAP.put(ISCONSIDERREGTIME_YES, "是");
	}

	/** 是否为模板MAP-0.否，1.是 */
	public static Map<Short, String> ISTEMPLET_MAP;
	static {
		ISTEMPLET_MAP = new HashMap<Short, String>();
		ISTEMPLET_MAP.put(ISTEMPLET_NO, "否");
		ISTEMPLET_MAP.put(ISTEMPLET_YES, "是");
	}

	/** 生成方式MAP-1.手动生成，2.模板生成 */
	public static Map<Short,String> GENERATETYPE_MAP;
	static {
		GENERATETYPE_MAP = new HashMap<Short,String>();
		GENERATETYPE_MAP.put(GENERATETYPE_MANUAL, "手动生成");
		GENERATETYPE_MAP.put(GENERATETYPE_TEMPLATE, "模板生成");
	}
}
