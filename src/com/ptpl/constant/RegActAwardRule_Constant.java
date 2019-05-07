package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册活动奖励规则常量类
 * @author zhenglm
 */
public class RegActAwardRule_Constant {

	/** 客户端限制-1.PC */
	public static final String CRESTRICT_PC = "1";
	/** 客户端限制-2.安卓 */
	public static final String CRESTRICT_ANDROID = "2";
	/** 客户端限制-3.苹果 */
	public static final String CRESTRICT_IPHONE = "3";
	/** 客户端限制-4.wap */
	public static final String CRESTRICT_WAP = "4";
	/** 客户端限制-5.微信 */
	public static final String CRESTRICT_WECHAT = "5";
	
	/** 客户端限制MAP-1.PC，2.安卓，3.苹果，4.wap，5.微信 */
	public static final Map<String, String> CRESTRICT_MAP;
	static {
		CRESTRICT_MAP = new HashMap<String, String>();
		CRESTRICT_MAP.put(CRESTRICT_PC, "PC");
		CRESTRICT_MAP.put(CRESTRICT_ANDROID, "安卓");
		CRESTRICT_MAP.put(CRESTRICT_IPHONE, "苹果");
		CRESTRICT_MAP.put(CRESTRICT_WAP, "wap");
		CRESTRICT_MAP.put(CRESTRICT_WECHAT, "微信");
	}
	
	/** 指定业务-1.注册完成 */
	public static final Short BUSINESS_REGISTER = 1;
	/** 客户端限制-2.姓名匹配 */
	public static final Short BUSINESS_NAME = 2;
	/** 客户端限制-3.手机号验证 */
	public static final Short BUSINESS_TELPHONE = 3;
	/** 客户端限制-4.开通托管账户 */
	public static final Short BUSINESS_TRUSTEESHIP = 4;
	/** 客户端限制-5.银行卡绑定 */
	public static final Short BUSINESS_BANKCARD = 5;
	/** 客户端限制-6.安保密码设置 */
	public static final Short BUSINESS_SECURITY = 6;
	/** 客户端限制-7.邮箱验证 */
	public static final Short BUSINESS_MAILBOX = 7;
	
	/** 指定业务MAP-1.注册完成，2.姓名匹配，3.手机号验证，4.开通托管账户，5.银行卡绑定，6.安保密码设置，7.邮箱验证  */
	public static final Map<Short, String> BUSINESS_MAP;
	static {
		BUSINESS_MAP = new HashMap<Short, String>();
		BUSINESS_MAP.put(BUSINESS_REGISTER, "注册完成");
		BUSINESS_MAP.put(BUSINESS_NAME, "姓名匹配");
		BUSINESS_MAP.put(BUSINESS_TELPHONE, "手机号验证");
		BUSINESS_MAP.put(BUSINESS_TRUSTEESHIP, "开通托管账户");
		BUSINESS_MAP.put(BUSINESS_BANKCARD, "银行卡绑定");
		BUSINESS_MAP.put(BUSINESS_SECURITY, "安保密码设置");
		BUSINESS_MAP.put(BUSINESS_MAILBOX, "邮箱验证");
	}
}
