package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行卡常量
 * @author zhenglm
 *
 */
public class Constant {
	
	/**
	 * 卡类型-信用卡（1）
	 */
	public static Short CARDTYPE_XINYONGKA = 1;
	/**
	 * 卡类型-借记卡（2）
	 */
	public static Short CARDTYPE_JIEJIKA = 2;
	/**
	 * 卡类型-企业账户（3）
	 */
	public static Short CARDTYPE_QIYE = 3;
	
	/**
	 * 卡类型MAP（1.信用卡，2.借记卡，3.企业账户）
	 */
	public static Map<Short, String> CARDTYPE_MAP;
	static {
		CARDTYPE_MAP = new HashMap<Short, String>();
		CARDTYPE_MAP.put(CARDTYPE_XINYONGKA, "信用卡");
		CARDTYPE_MAP.put(CARDTYPE_JIEJIKA, "借记卡");
		CARDTYPE_MAP.put(CARDTYPE_QIYE, "企业账户");
	}
	
	/**
	 * 绑定方式-接口（1）
	 */
	public static Short BINDMODE_JIEKOU = 1;
	/**
	 * 绑定方式-人工（2）
	 */
	public static Short BINDMODE_RENGONG = 2; 
	
	/**
	 * 绑定方式MAP（ 1.接口，2.人工）
	 */
	public static Map<Short, String> BINDMODE_MAP;
	static {
		BINDMODE_MAP = new HashMap<Short, String>();
		BINDMODE_MAP.put(BINDMODE_JIEKOU, "接口");
		BINDMODE_MAP.put(BINDMODE_RENGONG, "人工");
	}
	
	/**
	 * 是否快捷绑卡-普通卡（1）
	 */
	public static Short ISFASTBINDCARD_PUTONG = 1;
	/**
	 * 是否快捷绑卡-快捷卡（2）
	 */
	public static Short ISFASTBINDCARD_KUAIJIE = 2; 
	
	/**
	 * 是否快捷绑卡MAP（1.普通卡,2.快捷卡）
	 */
	public static Map<Short, String> ISFASTBINDCARD_MAP;
	static {
		ISFASTBINDCARD_MAP = new HashMap<Short, String>();
		ISFASTBINDCARD_MAP.put(ISFASTBINDCARD_PUTONG, "普通卡");
		ISFASTBINDCARD_MAP.put(ISFASTBINDCARD_KUAIJIE, "快捷卡");
	}
	
	/**
	 * 是否默认取现卡-非默认卡（0）
	 */
	public static Short ISDEFAULTCARD_NO = 0;
	/**
	 * 是否默认取现卡-默认卡（1）
	 */
	public static Short ISDEFAULTCARD_YES = 1;
	
	/**
	 * 是否默认取现卡MAP（0.非默认卡，1.默认卡）
	 */
	public static Map<Short, String> ISDEFAULTCARD_MAP;
	static {
		ISDEFAULTCARD_MAP = new HashMap<Short, String>();
		ISDEFAULTCARD_MAP.put(ISDEFAULTCARD_NO, "非默认卡");
		ISDEFAULTCARD_MAP.put(ISDEFAULTCARD_YES, "默认卡");
	}
	
	/**
	 * 绑定状态-已绑定（1）
	 */
	public static Short BINDSTATUS_BIND = 1; 
	/**
	 * 绑定状态-已解绑（2）
	 */
	public static Short BINDSTATUS_UNBIND = 2;
	
	/**
	 * 绑定状态MAP（1.已绑定，2.已解绑）
	 */
	public static Map<Short, String> BINDSTATUS_MAP;
	static {
		BINDSTATUS_MAP = new HashMap<Short, String>();
		BINDSTATUS_MAP.put(BINDSTATUS_BIND, "已绑定");
		BINDSTATUS_MAP.put(BINDSTATUS_UNBIND, "已解绑");
	}
	
	/**
	 * 银行代号MAP
	 */
	public static Map<String, String> BANK_MAP;
	static {
		BANK_MAP = new HashMap<String, String>();
		BANK_MAP.put("ICBC", "中国工商银行");
		BANK_MAP.put("ABC", "中国农业银行");
		BANK_MAP.put("CMB", "招商银行");
		BANK_MAP.put("CCB", "中国建设银行");
		BANK_MAP.put("BCCB", "北京银行");
		BANK_MAP.put("BJRCB", "北京农村商业银行");
		BANK_MAP.put("BOC", "中国银行");
		BANK_MAP.put("BOCOM", "交通银行");
		BANK_MAP.put("CMBC", "中国民生银行");
		BANK_MAP.put("BOS", "上海银行");
		BANK_MAP.put("CBHB", "渤海银行");
		BANK_MAP.put("CEB", "中国光大银行");
		BANK_MAP.put("CIB", "兴业银行");
		BANK_MAP.put("CITIC", "中信银行");
		BANK_MAP.put("CZB", "浙商银行");
		BANK_MAP.put("GDB", "广发银行");
		BANK_MAP.put("HKBEA", "东亚银行");
		BANK_MAP.put("HXB", "华夏银行");
		BANK_MAP.put("HZCB", "杭州银行");
		BANK_MAP.put("NJCB", "南京银行");
		BANK_MAP.put("PINGAN", "平安银行");
		BANK_MAP.put("PSBC", "中国邮政储蓄银行");
		BANK_MAP.put("SDB", "深圳发展银行");
		BANK_MAP.put("SPDB", "浦东发展银行");
		BANK_MAP.put("SRCB", "上海农村商业银行");
	}
}
