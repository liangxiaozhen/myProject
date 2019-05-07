package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 标的流标补偿记录常量类
 * @author zhenglm
 *
 */
public class FailTCRecord_Constant {
	
	/** 奖励方式-1.金额 */
	public static final short PAYOUTTYPE_MONEY = 1;
	/** 奖励方式-2.奖品 */
	public static final short PAYOUTTYPE_AWARD = 2;
	/** 奖励方式-3.金额+奖品 */
	public static final short PAYOUTTYPE_MONEYANDAWARD = 3;

	/** 生成方式-1.人工生成 */
	public static final short CREATEWAY_MAN = 1;
	/** 生成方式-2.系统生成 */
	public static final short CREATEWAY_SYS = 2;

	/** 补偿是否发放-0.未发放 */
	public static final short ISGRANT_NO = 0;
	/** 补偿是否发放-1.已发放 */
	public static final short ISGRANT_YES = 1;
	/** 补偿是否发放-2.待确认 */
	public static final short ISGRANT_PENDING = 2;
	/** 补偿是否发放-3.已确认 */
	public static final short ISGRANT_CONFIRMED = 3;
	/** 补偿是否发放-4.发货中 */
	public static final short ISGRANT_DELIVERYING = 4;
	
	/** 是否审核-0.不需要审核 */
	public static final short ISAUDIT_NO = 0;
	/** 是否审核-1.待审核 */
	public static final short ISAUDIT_PENDING = 1;
	/** 是否审核-2.审核通过 */
	public static final short ISAUDIT_PASS = 2;
	/** 是否审核-3.审核不通过 */
	public static final short ISAUDIT_FAIL = 3;
	
	/**
	 * 奖励方式MAP-1.金额，2.奖品，3.金额+奖品
	 */
	public static final Map<Short,String> PAYOUTTYPE_MAP;
	static {
		PAYOUTTYPE_MAP = new HashMap<Short,String>();
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_MONEY, "金额");
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_AWARD, "奖品");
		PAYOUTTYPE_MAP.put(PAYOUTTYPE_MONEYANDAWARD, "金额+奖品");
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
	 * 补偿是否发放MAP-0.未发放，1.已发放，2.待确认，3.已确认，4.发货中
	 */
	public static final Map<Short,String> ISGRANT_MAP;
	static {
		ISGRANT_MAP = new HashMap<Short,String>();
		ISGRANT_MAP.put(ISGRANT_NO, "未发放");
		ISGRANT_MAP.put(ISGRANT_YES, "已发放");
		ISGRANT_MAP.put(ISGRANT_PENDING, "待确认");
		ISGRANT_MAP.put(ISGRANT_CONFIRMED, "已确认");
		ISGRANT_MAP.put(ISGRANT_DELIVERYING, "发货中");
	}
	
	public static final Map<Short,String> ISAUDIT_MAP;
	static {
		ISAUDIT_MAP = new HashMap<Short,String>();
		ISAUDIT_MAP.put(ISAUDIT_NO, "不需要审核");
		ISAUDIT_MAP.put(ISAUDIT_PENDING, "待审核");
		ISAUDIT_MAP.put(ISAUDIT_PASS, "审核通过");
		ISAUDIT_MAP.put(ISAUDIT_FAIL, "审核不通过");
	}
}
