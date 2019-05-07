package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 投标记录常量
 * @author zhenglm
 */
public class TenderRecord_Constant {

	/** 标的属性- 1.信用标 */
	public static final short TPROPERTY_CREDIT = 1;
	/** 标的属性- 2.抵押标 */
	public static final short TPROPERTY_MORTGAGE = 2;
	/** 标的属性- 3.担保标 */
	public static final short TPROPERTY_GUARANTEE = 3;
	/** 标的属性- 4.新手标 */
	public static final short TPROPERTY_NOVICE = 4;
	
	/** 投标的状态-0.取消 */
	public static final short TSTATUS_INITIAL = 0;
	/** 投标的状态-1.待审核 */
	public static final short TSTATUS_PENDINGAUDIT = 1;
	/** 投标的状态-2.失败 */
	public static final short TSTATUS_FAIL = 2;
	/** 投标的状态-3.撤销 */
	public static final short TSTATUS_REVOKE = 3;
	/** 投标的状态-4.已完成 */
	public static final short TSTATUS_COMPLETED = 4;
	/** 投标的状态-5.处理中 */
	public static final short TSTATUS_PROCESSING = 5;
	/** 投标的状态-6.处理失败 */
	public static final short TSTATUS_FALSE = 6;
	
	/** 转账类型-1.账户转账 */
	public static final short TRANSFERTYPE_ACCOUNT = 1;
	/** 转账类型-2.银行卡转账 */
	public static final short TRANSFERTYPE_BANKCARD = 2;
	
	/** 是否系统勾兑-0.未勾兑 */
	public static final short ISBLENDING_NO = 0;
	/** 是否系统勾兑-1.系统已勾兑 */
	public static final short ISBLENDING_YES = 1;
	
	/** 是否人工勾兑-0.未勾兑 */
	public static final short ISMANBLENDING_NO = 0;
	/** 是否人工勾兑-1.人工已勾兑 */
	public static final short ISMANBLENDING_YES = 1;
	
	/** 是否审核-0.无需审核 */
	public static final short ISAUDIT_UNWANTED = 0;
	/** 是否审核-1.未审核 */
	public static final short ISAUDIT_NO = 1;
	/** 是否审核-2.审核通过 */
	public static final short ISAUDIT_PASS = 2;
	/** 是否审核-3.审核失败 */
	public static final short ISAUDIT_FAIL = 3;

	/** 是否冻结-0.不冻结 */
	public static final short ISFREEZE_NOT_FREEZE = 0;
	/** 是否冻结-1.冻结 */
	public static final short ISFREEZE_FREEZE = 1;
	/** 是否冻结-2.已解冻 */
	public static final short ISFREEZE_UNFREEZE = 2;
	
	/** 投标设备来源-PC */
	public static final String ORIGINCLIENT_PC = "PC";
	/** 投标设备来源-安卓 */
	public static final String ORIGINCLIENT_ANDROID = "安卓";
	/** 投标设备来源-苹果 */
	public static final String ORIGINCLIENT_APPLE = "苹果"; 
	/** 投标设备来源-wpa */
	public static final String ORIGINCLIENT_WAP = "wap";
	/** 投标设备来源-微信 */
	public static final String ORIGINCLIENT_WECHAT = "微信"; 
	
	/** 是否债转-0.未债转 */
	public static final short ISDA_NO = 0;
	/** 是否债转-1.全部债转 */
	public static final short ISDA_ALL = 1;
	/** 是否债转-2.部分债转 */
	public static final short ISDA_PART = 2;
	
	/** 投标方式-1.手动 */
	public static final short TENDERTYPE_MANUAL = 1;
	/** 投标方式-2.自动 */
	public static final short TENDERTYPE_AUTOMATIC = 2;
	
	/** 还款完成-0.否 */
	public static final short ISREPAYEND_NO = 0;
	/** 还款完成-1.是 */
	public static final short ISREPAYEND_YES = 1;
	/** 还款完成-2.还款中 */
	public static final short ISREPAYEND_REPAYMENTING = 2;
	
	/** 投标属性-1.原始投标 */
	public static final short UTPROPERTY_ORIGINAL = 1;
	/** 投标属性-2.债转投标 */
	public static final short UTPROPERTY_DEBT = 2;
	/** 投标属性-3.假投标 */
	public static final short UTPROPERTY_FAKE = 3;
	
	/** 能否债转-0.不能 */
	public static final short ISALLOWDA_NO = 0;
	/** 能否债转-1.能 */
	public static final short ISALLOWDA_YES = 1;
	
	/** 标的属性MAP（1.担保标，2.信用标，3.抵押标，4.新手标） */
	public static Map<Short, String> TPROPERTY_MAP;
	static {
		TPROPERTY_MAP = new HashMap<Short, String>();
		TPROPERTY_MAP.put(TPROPERTY_CREDIT, "信用标");
		TPROPERTY_MAP.put(TPROPERTY_MORTGAGE, "抵押标");
		TPROPERTY_MAP.put(TPROPERTY_GUARANTEE, "担保标");
		TPROPERTY_MAP.put(TPROPERTY_NOVICE, "新手标");
	}
	
	/** 投标的状态MAP（0.取消，1.待审核，2.失败，3.撤销，4.已完成，5.处理中，6处理失败） */
	public static Map<Short, String> TSTATUS_MAP;
	static {
		TSTATUS_MAP = new HashMap<Short, String>();
		TSTATUS_MAP.put(TSTATUS_INITIAL, "取消");
		TSTATUS_MAP.put(TSTATUS_PENDINGAUDIT, "待放款");
		TSTATUS_MAP.put(TSTATUS_FAIL, "失败");
		TSTATUS_MAP.put(TSTATUS_REVOKE, "已撤销");
		TSTATUS_MAP.put(TSTATUS_COMPLETED, "成功");
		TSTATUS_MAP.put(TSTATUS_PROCESSING, "放款中");
		TSTATUS_MAP.put(TSTATUS_FALSE, "处理失败");
	}
	
	/** 转账类型MAP（1.账户转账，2.银行卡转账） */
	public static Map<Short, String> TRANSFERTYPE_MAP;
	static {
		TRANSFERTYPE_MAP = new HashMap<Short, String>();
		TRANSFERTYPE_MAP.put(TRANSFERTYPE_ACCOUNT, "账户转账");
		TRANSFERTYPE_MAP.put(TRANSFERTYPE_BANKCARD, "银行卡转账");
	}
	
	/** 是否系统勾兑MAP（0.未勾兑，1.系统已勾兑） */
	public static Map<Short, String> ISBLENDING_MAP;
	static {
		ISBLENDING_MAP = new HashMap<Short, String>();
		ISBLENDING_MAP.put(ISBLENDING_NO, "未勾兑");
		ISBLENDING_MAP.put(ISBLENDING_YES, "系统已勾兑");
	}
	
	/** 是否人工勾兑MAP（0.未勾兑，1.人工已勾兑） */
	public static Map<Short, String> ISMANBLENDING_MAP;
	static {
		ISMANBLENDING_MAP = new HashMap<Short, String>();
		ISMANBLENDING_MAP.put(ISMANBLENDING_NO, "未勾兑");
		ISMANBLENDING_MAP.put(ISMANBLENDING_YES, "人工已勾兑");
	}
	
	/** 是否审核MAP（0.无需审核，1.未审核，2.审核通过，3.审核失败） */
	public static Map<Short, String> ISAUDIT_MAP;
	static {
		ISAUDIT_MAP = new HashMap<Short, String>();
		ISAUDIT_MAP.put(ISAUDIT_UNWANTED, "无需审核");
		ISAUDIT_MAP.put(ISAUDIT_NO, "未审核");
		ISAUDIT_MAP.put(ISAUDIT_PASS, "审核通过");
		ISAUDIT_MAP.put(ISAUDIT_FAIL, "审核失败");
	}
	
	/** 是否冻结MAP（1.冻结，2.已解冻） */
	public static Map<Short, String> ISFREEZE_MAP;
	static {
		ISFREEZE_MAP = new HashMap<Short, String>();
		ISFREEZE_MAP.put(ISFREEZE_NOT_FREEZE, "不冻结");
		ISFREEZE_MAP.put(ISFREEZE_FREEZE, "冻结");
		ISFREEZE_MAP.put(ISFREEZE_UNFREEZE, "已解冻");
	}
	
	/** 投标设备来源MAP（PC，安卓，苹果，wap,微信） */
	public static Map<String, String> ORIGINCLIENT_MAP;
	static {
		ORIGINCLIENT_MAP = new HashMap<String, String>();
		ORIGINCLIENT_MAP.put(ORIGINCLIENT_PC, "PC");
		ORIGINCLIENT_MAP.put(ORIGINCLIENT_ANDROID, "安卓");
		ORIGINCLIENT_MAP.put(ORIGINCLIENT_APPLE, "苹果");
		ORIGINCLIENT_MAP.put(ORIGINCLIENT_WAP, "wap");
		ORIGINCLIENT_MAP.put(ORIGINCLIENT_WECHAT, "微信");
	}
	
	/** 是否债转MAP（0.未债转，1.全部债转，2.部分债转） */
	public static Map<Short, String> ISDA_MAP;
	static {
		ISDA_MAP = new HashMap<Short, String>();
		ISDA_MAP.put(ISDA_NO, "未债转");
		ISDA_MAP.put(ISDA_ALL, "全部债转");
		ISDA_MAP.put(ISDA_PART, "部分债转");
	}
	
	/** 投标方式MAP（1.手动，2.自动） */
	public static Map<Short, String> TENDERTYPE_MAP;
	static {
		TENDERTYPE_MAP = new HashMap<Short, String>();
		TENDERTYPE_MAP.put(TENDERTYPE_MANUAL, "手动");
		TENDERTYPE_MAP.put(TENDERTYPE_AUTOMATIC, "自动");
	}
	
	/** 还款完成MAP（0.否，1.是） */
	public static Map<Short, String> ISREPAYEND_MAP;
	static {
		ISREPAYEND_MAP = new HashMap<Short, String>();
		ISREPAYEND_MAP.put(ISREPAYEND_NO, "否");
		ISREPAYEND_MAP.put(ISREPAYEND_YES, "是");
		ISREPAYEND_MAP.put(ISREPAYEND_REPAYMENTING, "还款中");
	}
	
	/** 投标属性MAP（1.原始投标，2.债转投标，3.假投标） */
	public static Map<Short, String> UTPROPERTY_MAP;
	static {
		UTPROPERTY_MAP = new HashMap<Short, String>();
		UTPROPERTY_MAP.put(UTPROPERTY_ORIGINAL, "原始投标");
		UTPROPERTY_MAP.put(UTPROPERTY_DEBT, "债转投标");
		UTPROPERTY_MAP.put(UTPROPERTY_FAKE, "假投标");
	}
	
	/** 能否债转MAP（0.不能，1.能） */
	public static Map<Short, String> ISALLOWDA_MAP;
	static {
		ISALLOWDA_MAP = new HashMap<Short, String>();
		ISALLOWDA_MAP.put(ISALLOWDA_NO, "不能");
		ISALLOWDA_MAP.put(ISALLOWDA_YES, "能");
	}
}
