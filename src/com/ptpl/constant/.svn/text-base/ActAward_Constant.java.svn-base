package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户红包、积分、使用券常量
 * 
 */
public class ActAward_Constant {
	
	/**
	 * 获取方式类型（红包获取方式类型，积分类型，券类型，奖品类型）
	 */
	public static short RECTYPE_ZHUCE = 1;//注册
	public static short RECTYPE_WANSHANZILIAO = 2;//累投
	public static short RECTYPE_SHOUTOU = 3; //单标
	public static short RECTYPE_LAIYUAN = 4;//首投
	public static short RECTYPE_MAULADAWARD = 5;//手动颁奖
	public static short RECTYPE_ZIZHUAWARD = 6;//自主颁奖
	public static short RECTYPE_LIUBIAOBUCHANG = 8;//流标补偿
	
	/**
	 * 红包类型
	 */
	public static short RETYPE_XIANJIN = 1;//现金
	public static short RETYPE_LEIXIANJIN = 2;//类现金
	public static short RETYPE_JIAXIANJIN = 3;//假现金
	
	/**
	 * 状态（积分状态，红包状态，券状态，奖品状态）
	 */
	public static short STATUS_UNAUDIT=1;//待审核
	public static short STATUS_UNDEAL=2;//待处理
	public static short STATUS_ALRECEIVE = 3;//已领取
	public static short STATUS_UNSURE=4;//待确认
	public static short STATUS_ALSURE=5;//已确认
	public static short STATUS_SENDING=6;//发货中
	public static short STATUS_FAILRECEIVE = 7;//领取失败
	//徽商现金红包批量转账新加状态（需要银行那边处理结果）
	public static short STATUS_DAIFAFANG = 9;//待发放
	
	/**
	 * 是否审核（红包、积分、使用券、奖品）
	 */
	public static short ISAUDIT_YES = 0;//是
	public static short ISAUDIT_NO= 1;//否
	
	/**
	 * 券的类型
	 */
	public static short UIRCTYPE_JIAXI = 6;//加息券
	public static short UIRCTYPE_TIXIAN = 7;//提现抵用券
	public static short UIRCTYPE_CHONGZHI = 8;//充值抵用券
	
	/**
	 * 奖品状态（红包、使用券、奖品）
	 */
	public static short AWARD_NOTDUE = 1;//未到期
	public static short AWARD_CANUSE = 2;//可使用
	public static short AWARD_ALFROZEN = 3;//已冻结
	public static short AWARD_ALUSE=4;//已使用
	public static short AWARD_ALDUE=5;//已到期
	public static short AWARD_ALCANCEL=6;//已作废
	
	/**
	 * 获取方式类型MAP（红包获取方式类型，积分类型，券类型，奖品类型）（1.注册，2.累投， 4.单标   4.首投，5.手动颁奖 6.自主颁奖）
	 */
	public static Map<Short,String> RECTYPE_MAP;
	static {
		RECTYPE_MAP = new HashMap<Short,String>();
		RECTYPE_MAP.put(RECTYPE_ZHUCE, "注册");
		RECTYPE_MAP.put(RECTYPE_WANSHANZILIAO, "累投");
		RECTYPE_MAP.put(RECTYPE_SHOUTOU, "单标");
		RECTYPE_MAP.put(RECTYPE_LAIYUAN, "首投");
		RECTYPE_MAP.put(RECTYPE_MAULADAWARD, "手动颁奖");
		RECTYPE_MAP.put(RECTYPE_ZIZHUAWARD, "自主颁奖");
		RECTYPE_MAP.put(RECTYPE_LIUBIAOBUCHANG, "流标补偿");
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
	 * 状态MAP（积分状态，红包状态，券状态，奖品状态）
	 */
	public static Map<Short,String> STATUS_MAP;
	static {
		STATUS_MAP = new HashMap<Short,String>();
		STATUS_MAP.put(STATUS_UNAUDIT, "待审核");
		STATUS_MAP.put(STATUS_UNDEAL, "待处理");
		STATUS_MAP.put(STATUS_ALRECEIVE, "已领取");
		STATUS_MAP.put(STATUS_UNSURE, "待确认");
		STATUS_MAP.put(STATUS_ALSURE, "已确认");
		STATUS_MAP.put(STATUS_SENDING, "发货中");
		STATUS_MAP.put(STATUS_FAILRECEIVE, "领取失败");
		STATUS_MAP.put(STATUS_DAIFAFANG, "待发放");
	}
	
	public static Map<Short,String> STATUS_AUDIT_MAP;
	static {
		STATUS_AUDIT_MAP = new HashMap<Short,String>();
		STATUS_AUDIT_MAP.put(STATUS_UNAUDIT, "待审核");//1
		STATUS_AUDIT_MAP.put(STATUS_UNDEAL, "待处理");//2
		STATUS_AUDIT_MAP.put(STATUS_UNSURE, "待确认");//4
		STATUS_AUDIT_MAP.put(STATUS_FAILRECEIVE, "领取失败");//7
	}
	
	public static Map<Short,String> STATUS_SEND_MAP;
	static {
		STATUS_SEND_MAP = new HashMap<Short,String>();
		STATUS_SEND_MAP.put(STATUS_UNDEAL, "待处理");//2
		STATUS_SEND_MAP.put(STATUS_ALRECEIVE, "已领取");//3
		STATUS_SEND_MAP.put(STATUS_UNSURE, "待确认");//4
		STATUS_SEND_MAP.put(STATUS_ALSURE, "已确认");//5
		STATUS_SEND_MAP.put(STATUS_SENDING, "发货中");//6
		STATUS_SEND_MAP.put(STATUS_FAILRECEIVE, "领取失败");//7
		STATUS_SEND_MAP.put(STATUS_DAIFAFANG, "待发放");//9
	}
	
	public static Map<Short,String> AWARDSTATUS_MAP;
	static {
		AWARDSTATUS_MAP = new HashMap<Short,String>();
		AWARDSTATUS_MAP.put(STATUS_ALRECEIVE, "已领取");
		AWARDSTATUS_MAP.put(STATUS_FAILRECEIVE, "领取失败");
	}
	

	/**
	 * 是否审核MAP（红包、积分、使用券、奖品）
	 */
	public static Map<Short,String> ISAUDIT_MAP;
	static {
		ISAUDIT_MAP = new HashMap<Short,String>();
		ISAUDIT_MAP.put(ISAUDIT_YES, "是");
		ISAUDIT_MAP.put(ISAUDIT_NO, "否");
	}
	
	/**
	 * 券的类型MAP（8.充值抵用券，7.提现抵用券，6.加息券）
	 */
	public static Map<Short,String> UIRCTYPE_MAP;
	static {
		UIRCTYPE_MAP = new HashMap<Short,String>();
		UIRCTYPE_MAP.put(UIRCTYPE_CHONGZHI, "充值抵用券");
		UIRCTYPE_MAP.put(UIRCTYPE_TIXIAN, "提现抵用券");
		UIRCTYPE_MAP.put(UIRCTYPE_JIAXI, "加息券");
	}
	
	/**
	 * 奖品状态MAP（红包、使用券、奖品）
	 */
	public static Map<Short,String> AWARD_MAP;
	static {
		AWARD_MAP = new HashMap<Short,String>();
		AWARD_MAP.put(AWARD_NOTDUE, "未到期");
		AWARD_MAP.put(AWARD_CANUSE, "可使用");
		AWARD_MAP.put(AWARD_ALFROZEN, "已冻结");
		AWARD_MAP.put(AWARD_ALUSE, "已使用");
		AWARD_MAP.put(AWARD_ALDUE, "已到期");
		AWARD_MAP.put(AWARD_ALCANCEL, "已作废");
	}	
}
