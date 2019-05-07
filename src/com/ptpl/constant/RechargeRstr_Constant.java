package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class RechargeRstr_Constant {
	/*
	 * 页面跳转
	 */
	// 详情
	public static final Short DETAIL = 1;
	// 修改(就是编辑)
	public static final Short UPDATE = 2;
	/**
	 * 跳转到List页面
	 */
	public static final String LIST_VIEW = "admin/rechargeRstr/rechargeRstrList";
	public static final String LISTRECROD_VIEW = "admin/rechargeRstr/rechargeRstrListRecrod";
	// 添加
	public static final String INSERT_VIEW = "admin/rechargeRstr/insertRechargeRstr";
	/**
	 * 跳转到审核页面
	 */
	public static final String AUDIT = "admin/rechargeRstr/auditRechargeRstr";
	public static Map<Short, String> URL_MAP;
	static
	{
		URL_MAP = new HashMap<Short, String>();
		URL_MAP.put(DETAIL, "admin/rechargeRstr/rechargeRstrDetalis");
		URL_MAP.put(UPDATE, "admin/rechargeRstr/rechargeRstrEdits");
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
	 * 充值方式
	 */
	// 0
	// 1
	public static final Short RECHARTYPE_ONE = 1;
	// 2
	public static final Short RECHARTYPE_TWO = 2;
	public static final Short RECHARTYPE_THREE = 3;
	public static final Short RECHARTYPE_FOUR = 4;
	public static Map<Short, String> RECHARTYPE_MAP;
	static
	{
		RECHARTYPE_MAP = new HashMap<Short, String>();
		RECHARTYPE_MAP.put(RECHARTYPE_ONE, "个人网银");
		RECHARTYPE_MAP.put(RECHARTYPE_TWO, "企业网银");
		RECHARTYPE_MAP.put(RECHARTYPE_THREE, "快捷支付");
		RECHARTYPE_MAP.put(RECHARTYPE_FOUR, "汇款充值");
	}
	/**
	 * 手续费类型
	 */
	// 0
	public static final Short FEETYPE_ZERO = 0;
	// 1
	public static final Short FEETYPE_ONE = 1;
	// 2
	public static final Short FEETYPE_TWO = 2;
	public static final Short FEETYPE_THREE = 3;
	public static final Short FEETYPE_FOUR = 4;
	public static Map<Short, String> FEETYPE_MAP;
	static
	{
		FEETYPE_MAP = new HashMap<Short, String>();
		FEETYPE_MAP.put(FEETYPE_ZERO, "无");
		FEETYPE_MAP.put(FEETYPE_ONE, "全额自付");
		FEETYPE_MAP.put(FEETYPE_TWO, "全额他付");
		FEETYPE_MAP.put(FEETYPE_THREE, "充值成功时从充值人账户扣除与提现手续费的差值");
		FEETYPE_MAP.put(FEETYPE_FOUR, "充值成功时从平台自有账户扣除与提现手续费的差值");
	}
	/*
	 * 会员等级
	 */
	//所有等级
	public static final Short RECHARGERSTR_UGRADEALL=1;
	public static Map<Short, String> UGRADE_MAP;
	static
	{
		UGRADE_MAP = new HashMap<Short, String>();
		UGRADE_MAP.put(RECHARGERSTR_UGRADEALL, "全部等级");
	}
	/*
	 * 会员等级
	 */
	//所有等级
	public static final Short RECHARGERSTR_REMOVENOALL=1;
	public static Map<Short, String> REMOVENO_MAP;
	static
	{
		REMOVENO_MAP = new HashMap<Short, String>();
		REMOVENO_MAP.put(RECHARGERSTR_REMOVENOALL, "全部名单");
	}
}
