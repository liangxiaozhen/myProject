package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户红包、积分、使用券常量
 * @author zhenglm
 *
 */
public class Red_Constant {
	
	/**
	 * 获取方式类型（红包获取方式类型，积分类型，券类型，奖品类型）-1.注册
	 */
	public static short RECTYPE_ZHUCE = 1;
	/**
	 * 获取方式类型（红包获取方式类型，积分类型，券类型，奖品类型）-2.完善资料
	 */
	public static short RECTYPE_WANSHANZILIAO = 2;
	/**
	 * 获取方式类型（红包获取方式类型，积分类型，券类型，奖品类型）-3.首投
	 */
	public static short RECTYPE_SHOUTOU = 3; 
	/**
	 * 获取方式类型（红包获取方式类型，积分类型，券类型，奖品类型）-4.来源
	 */
	public static short RECTYPE_LAIYUAN = 4;
	
	/**
	 * 红包类型-1.现金
	 */
	public static short RETYPE_XIANJIN = 1;
	/**
	 * 红包类型-2.类现金
	 */
	public static short RETYPE_LEIXIANJIN = 2;
	/**
	 * 红包类型-3.假现金
	 */
	public static short RETYPE_JIAXIANJIN = 3;
	
	/**
	 * 状态（积分状态，红包状态，券状态，奖品状态）-1.正常
	 */
	public static short STATUS_NORMAL = 1;
	/**
	 * 状态（积分状态，红包状态，券状态，奖品状态）-2.处理中
	 */
	public static short STATUS_PROCESSING = 2;
	/**
	 * 状态（积分状态，红包状态，券状态，奖品状态）-3.失效
	 */
	public static short STATUS_INVALID = 3;
	
	/**
	 * 是否审核（红包、积分、使用券、奖品）-0.待审核
	 */
	public static short ISAUDIT_NO = 0;
	/**
	 * 是否审核（红包、积分、使用券、奖品）-1.审核通过
	 */
	public static short ISAUDIT_YES = 1;
	
	/**
	 * 券的类型-1.充值抵用券
	 */
	public static short UIRCTYPE_CHONGZHI = 1;
	/**
	 * 券的类型-2.提现抵用券
	 */
	public static short UIRCTYPE_TIXIAN = 2;
	/**
	 * 券的类型-3.充值抵用券
	 */
	public static short UIRCTYPE_JIAXI = 3;
	
	/**
	 * 是否使用（红包、使用券、奖品）-0.未用
	 */
	public static short ISUSE_NO = 0;
	/**
	 * 是否使用（红包、使用券、奖品）-1.投资
	 */
	public static short ISUSE_TOUZI = 1;
	/**
	 * 是否使用（红包、使用券、奖品）-2.交易
	 */
	public static short ISUSE_JIAOYI = 2;
	
	/**
	 * 是否发放-0.未发放
	 */
	public static short ISSUE_NO=0;
	/**
	 * 是否发放-1.已发放
	 */
	public static short ISSUE_YES=1;
	
	/**
	 * 获取方式类型MAP（红包获取方式类型，积分类型，券类型，奖品类型）（1.注册，2.完善资料，3.首投，4.来源）
	 */
	public static Map<Short,String> RECTYPE_MAP;
	static {
		RECTYPE_MAP = new HashMap<Short,String>();
		RECTYPE_MAP.put(RECTYPE_ZHUCE, "注册");
		RECTYPE_MAP.put(RECTYPE_WANSHANZILIAO, "完善资料");
		RECTYPE_MAP.put(RECTYPE_SHOUTOU, "首投");
		RECTYPE_MAP.put(RECTYPE_LAIYUAN, "来源");
	}
	
	/**
	 * 红包类型MAP（1.现金，2.类现金，3.假现金）
	 */
	public static Map<Short,String> RETYPE_MAP;
	static {
		RETYPE_MAP = new HashMap<Short,String>();
		RETYPE_MAP.put(RETYPE_XIANJIN, "现金");
		RETYPE_MAP.put(RETYPE_LEIXIANJIN, "类现金");
		RETYPE_MAP.put(RETYPE_JIAXIANJIN, "假现金");
	}
	
	/**
	 * 状态MAP（积分状态，红包状态，券状态，奖品状态）（1.正常，2.处理中，3.失效）
	 */
	public static Map<Short,String> STATUS_MAP;
	static {
		STATUS_MAP = new HashMap<Short,String>();
		STATUS_MAP.put(STATUS_NORMAL, "正常");
		STATUS_MAP.put(STATUS_PROCESSING, "处理中");
		STATUS_MAP.put(STATUS_INVALID, "失效");
	}

	/**
	 * 是否审核MAP（红包、积分、使用券、奖品）（0.待审核，1.审核通过）
	 */
	public static Map<Short,String> ISAUDIT_MAP;
	static {
		ISAUDIT_MAP = new HashMap<Short,String>();
		ISAUDIT_MAP.put(ISAUDIT_NO, "待审核");
		ISAUDIT_MAP.put(ISAUDIT_YES, "审核通过");
	}
	
	/**
	 * 券的类型MAP（1.充值抵用券，2.提现抵用券，3.加息券）
	 */
	public static Map<Short,String> UIRCTYPE_MAP;
	static {
		UIRCTYPE_MAP = new HashMap<Short,String>();
		UIRCTYPE_MAP.put(UIRCTYPE_CHONGZHI, "充值抵用券");
		UIRCTYPE_MAP.put(UIRCTYPE_TIXIAN, "提现抵用券");
		UIRCTYPE_MAP.put(UIRCTYPE_JIAXI, "加息券");
	}
	
	/**
	 * 是否使用MAP（红包、使用券、奖品）（0.未用，1.投资，2.交易）
	 */
	public static Map<Short,String> ISUSE_MAP;
	static {
		ISUSE_MAP = new HashMap<Short,String>();
		ISUSE_MAP.put(ISUSE_NO, "未用");
		ISUSE_MAP.put(ISUSE_TOUZI, "投资");
		ISUSE_MAP.put(ISUSE_JIAOYI, "交易");
	}
	
	/**
	 * 是否发放MAP（0.未发放，1.已发放）
	 */
	public static Map<Short,String> ISSUE_MAP;
	static {
		ISSUE_MAP = new HashMap<Short,String>();
		ISSUE_MAP.put(ISSUE_NO, "未发放");
		ISSUE_MAP.put(ISSUE_YES, "已发放");
	}
	
}
