package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 标的站岗利息记录常量类
 * @author zhenglm
 *
 */
public class GfundsIntRecord_Constant {

	/**
	 * 奖励方式-1.定额
	 */
	public static final short PAYOUTTYPE_FIX = 1;
	
	/**
	 * 奖励方式-2.日奖励比例
	 */
	public static final short PAYOUTTYPE_RATIO = 2;
	
	/**
	 * 奖励方式-3.最高金额
	 */
	public static final short PAYOUTTYPE_MAX = 3;
	
	/**
	 * 生成方式-1.系统自动
	 */
	public static final short CREATEWAY_SYS = 1;
	
	/**
	 * 生成方式-1.人工生成
	 */
	public static final short CREATEWAY_MAN = 2;
	
	/**
	 * 是否发放-0.未发放
	 */
	public static final short ISGRANT_NO = 0;
	
	/**
	 * 是否发放-1.已发放
	 */
	public static final short ISGRANT_YES = 1;
	
	/**
	 * 是否审核-0.不需要审核
	 */
	public static final short ISAUDIT_NO = 0;
	/**
	 * 是否审核-1.待审核
	 */
	public static final short ISAUDIT_PENDING = 1;
	/**
	 * 是否审核-3.审核通过
	 */
	public static final short ISAUDIT_PASS = 2;
	/**
	 * 是否审核-4.审核不通过
	 */
	public static final short ISAUDIT_FAIL = 3;
	
	/**
	 * 奖励方式MAP-1.定额，2.日奖励比例，3.最高金额
	 */
	public static final Map<Short,String> PAYOUTTYPE_MAP;
	static {
		PAYOUTTYPE_MAP = new HashMap<Short,String>();
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_FIX, "定额");
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_RATIO, "日奖励比例");
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_FIX, "最高金额");
	}
	
	/**
	 * 生成方式MAP-1.系统自动，2.人工生成
	 */
	public static final Map<Short,String> CREATEWAY_MAP;
	static {
		CREATEWAY_MAP = new HashMap<Short,String>();
		CREATEWAY_MAP.put(CREATEWAY_SYS, "系统自动");
		CREATEWAY_MAP.put(CREATEWAY_MAN, "人工生成");
	}
	
	/**
	 * 是否发放MAP-0.未发放，2.已发放
	 */
	public static final Map<Short,String> ISGRANT_MAP;
	static {
		ISGRANT_MAP = new HashMap<Short,String>();
		ISGRANT_MAP.put(ISGRANT_NO, "未发放");
		ISGRANT_MAP.put(ISGRANT_YES, "已发放");
	}
	
	/**
	 * 是否审核MAP-0.不需要审核,1.待审核，2.审核通过，2.审核不通过
	 */
	public static final Map<Short,String> ISAUDIT_MAP;
	static {
		ISAUDIT_MAP = new HashMap<Short,String>();
		ISAUDIT_MAP.put(ISAUDIT_NO, "不需要审核");
		ISAUDIT_MAP.put(ISAUDIT_PENDING, "待审核");
		ISAUDIT_MAP.put(ISAUDIT_PASS, "审核通过");
		ISAUDIT_MAP.put(ISAUDIT_FAIL, "审核不通过");
	}
	
}
