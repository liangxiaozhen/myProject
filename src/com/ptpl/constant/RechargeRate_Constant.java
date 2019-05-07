package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class RechargeRate_Constant {
	/**
	 * 充值费率表
	 */
	public static final String LIST = "admin/rechargeRate/rechargeRateList";
	/**
	 * 充值费率记录表
	 */
	public static final String RECORD = "admin/rechargeRate/rechargeRateRecordList";
	/**
	 * 跳转到详情页面
	 */
	public static final String DETALIS = "admin/rechargeRate/rechargeRateDetalis";
	/**
	 * 跳转到编辑页面
	 */
	public static final String EDITS = "admin/rechargeRate/rechargeRateEdits";
	/**
	 * 跳转到添加页面
	 */
	public static final String INSERT = "admin/rechargeRate/insertRechargeRate";
	/**
	 * 跳转到审核页面
	 */
	public static final String AUDIT = "admin/rechargeRate/auditRechargeRate";
	/**
	 * 充值方式
	 */
	public static final Short RECHARTYPE_ONE = 1;
	public static final Short RECHARTYPE_TWO = 2;
	public static final Short RECHARTYPE_THREE = 3;
	public static final Short RECHARTYPE_FOUR = 4;
	public static Map<Short, String> RECHARTYPE_MAP;
	static
	{
		RECHARTYPE_MAP = new HashMap<Short, String>();
		RECHARTYPE_MAP.put(RECHARTYPE_ONE, "个人网银");
		RECHARTYPE_MAP.put(RECHARTYPE_TWO, "企业网银");
		RECHARTYPE_MAP.put(RECHARTYPE_THREE, "快捷");
		RECHARTYPE_MAP.put(RECHARTYPE_FOUR, "汇款充值");
	}
	/*
	 * 收费类型
	 */
	// 定额
	public static final Short RECHARGEMODE_QUOTA = 1;
	// 比例
	public static final Short RECHARGEMODE_SCALE = 2;
	public static Map<Short, String> RECHARMODE_MAP;
	static
	{
		RECHARMODE_MAP = new HashMap<Short, String>();
		RECHARMODE_MAP.put(RECHARGEMODE_QUOTA, "定额");
		RECHARMODE_MAP.put(RECHARGEMODE_SCALE, "比例");
	}
	/*
	 * 是否作为模板
	 */
	// 否
	public static final Short ISTEMPLET_NO = 0;
	// 是
	public static final Short ISTEMPLET_YES = 1;
	public static Map<Short, String> ISTEMPLET_MAP;
	static
	{
		ISTEMPLET_MAP = new HashMap<Short, String>();
		ISTEMPLET_MAP.put(ISTEMPLET_NO, "否");
		ISTEMPLET_MAP.put(ISTEMPLET_YES, "是");
	}

	/**
	 * 是否审核
	 */
		// 否
		public static final Short ISAUDIT_NO = 0;
		// 是
		public static final Short ISAUDIT_YES = 1;
		public static Map<Short, String> ISAUDIT_MAP;
		static
		{
			ISAUDIT_MAP = new HashMap<Short, String>();
			ISAUDIT_MAP.put(ISAUDIT_NO, "待审核");
			ISAUDIT_MAP.put(ISAUDIT_YES, "审核通过");
		}
		/**
		 * 是否启用
		 */
		// 否
		public static final Short ISUSE_NO = 0;
		// 是
		public static final Short ISUSE_YES = 1;
		public static Map<Short, String> ISUSE_MAP;
		static
		{
			ISUSE_MAP = new HashMap<Short, String>();
			ISUSE_MAP.put(ISUSE_NO, "关闭");
			ISUSE_MAP.put(ISUSE_YES, "启用");
		}
		/**
		 * 是否启用
		 */
		// 否
		public static Map<String, String> BANKCODE_MAP;
		static
		{
			BANKCODE_MAP = new HashMap<String, String>();
			BANKCODE_MAP.put("ICBC", "中国工商银行");
			BANKCODE_MAP.put("ABC", "中国农业银行");
			BANKCODE_MAP.put("CMB", "招商银行");
			BANKCODE_MAP.put("CCB", "中国建设银行");
			BANKCODE_MAP.put("BCCB", "北京银行");
			BANKCODE_MAP.put("BJRCB", "北京农村商业银行");
			BANKCODE_MAP.put("BOC", "中国银行");
			BANKCODE_MAP.put("BOCOM", "交通银行");
			BANKCODE_MAP.put("CMBC", "中国民生银行");
			BANKCODE_MAP.put("BOS", "上海银行");
			BANKCODE_MAP.put("CBHB", "渤海银行");
			BANKCODE_MAP.put("CEB", "中国光大银行");
			BANKCODE_MAP.put("CIB", "兴业银行");
			BANKCODE_MAP.put("CITIC", "中信银行");
			BANKCODE_MAP.put("CZB", "浙商银行");
			BANKCODE_MAP.put("GDB", "广发银行");
			BANKCODE_MAP.put("HKBEA", "东亚银行");
			BANKCODE_MAP.put("HXB", "华夏银行");
			BANKCODE_MAP.put("HZCB", "杭州银行");
			BANKCODE_MAP.put("NJCB", "南京银行");
			BANKCODE_MAP.put("PINGAN", "平安银行");
			BANKCODE_MAP.put("PSBC", "中国邮政储蓄银行");
			BANKCODE_MAP.put("SDB", "深圳发展银行");
			BANKCODE_MAP.put("SPDB", "浦东发展银行");
			BANKCODE_MAP.put("SRCB", "上海农村商业银行");
		}
		/*
		 * 会员等级
		 */
		//所有等级
		public static final Short RECHARGERATE_UGRADEALL=1;
		public static Map<Short, String> UGRADE_MAP;
		static
		{
			UGRADE_MAP = new HashMap<Short, String>();
			UGRADE_MAP.put(RECHARGERATE_UGRADEALL, "全部等级");
		}
}
