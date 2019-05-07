package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class UserBonus_Constant {

	/**
	 * 积分类型
	 */
	public static short RECTYPE_ZHUCE = 1;//注册
	public static short RECTYPE_LEITOU = 2;//累投
	public static short RECTYPE_DANBIAO = 3;//单标
	public static short RECTYPE_SHOUTOU = 4; //首投
	public static short RECTYPE_MANUALAWARD = 5; //手动颁奖
	public static short RECTYPE_ZIZHUAWARD= 6;//自主颁奖
	
	/**
	 * 积分状态
	 */
	/*public static short STATUS_UNAUDIT=1;//待审核
	public static short STATUS_UNDEAL=2;//待处理
*/	public static short STATUS_ALRECEIVE = 3;//已领取
	/*public static short STATUS_UNSURE=4;//待确认
	public static short STATUS_ALSURE=5;//已确认
	public static short STATUS_SENDING=6;//发货中
*/	public static short STATUS_FAILRECEIVE = 7;//领取失败
	
	/**
	 * 是否审核（红包、积分、使用券、奖品）
	 */
	public static short ISAUDIT_YES = 0;//是
	public static short ISAUDIT_NO= 1;//否
	
	/**
	 * 积分来源Map
	 */
	public static Map<Short,String> RECTYPE_MAP = new HashMap<Short, String>();
	static{
		RECTYPE_MAP.put(RECTYPE_ZHUCE, "注册");
		RECTYPE_MAP.put(RECTYPE_LEITOU,"累投");
		RECTYPE_MAP.put(RECTYPE_DANBIAO,"单标");
		RECTYPE_MAP.put(RECTYPE_SHOUTOU,"首投");
		RECTYPE_MAP.put(RECTYPE_MANUALAWARD,"手动颁奖");
		RECTYPE_MAP.put(RECTYPE_ZIZHUAWARD,"自主颁奖");
	}
	
	/**
	 * 状态MAP（积分状态，红包状态，券状态，奖品状态）
	 */
	public static Map<Short,String> STATUS_MAP;
	
	static {
		STATUS_MAP = new HashMap<Short,String>();
		/*STATUS_MAP.put(STATUS_UNAUDIT, "待审核");
		STATUS_MAP.put(STATUS_UNDEAL, "待处理");*/
		STATUS_MAP.put(STATUS_ALRECEIVE, "已领取");
		/*STATUS_MAP.put(STATUS_UNSURE, "待确认");
		STATUS_MAP.put(STATUS_ALSURE, "已确认");
		STATUS_MAP.put(STATUS_SENDING, "发货中");*/
		STATUS_MAP.put(STATUS_FAILRECEIVE, "领取失败");
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
}
