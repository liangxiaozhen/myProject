package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class WithdrawsCashRate_Constant {
	/*
	 * 页面跳转
	 */
	// 详情
	public static final Short DETAIL = 1;
	// 修改
	public static final Short UPDATE = 2;
	// 审核
	public static final Short AUDIT = 3;
	//启用
	public static final Short USE=4;
	// List
	public static final String LIST_VIEW = "admin/withdrawscashrate/withdrawscashrateList";
	// 添加
	public static final String INSERT_VIEW = "admin/withdrawscashrate/insert_withdrawscashrate";
	//删除UI
	
	public static Map<Short, String> URL_MAP;
	static
	{
		URL_MAP = new HashMap<Short, String>();
		URL_MAP.put(DETAIL, "admin/withdrawscashrate/withdrawscashrate_Detail");
		URL_MAP.put(UPDATE, "admin/withdrawscashrate/update_withdrawscashrate");
		URL_MAP.put(AUDIT, "admin/withdrawscashrate/audit_withdrawscashrate");
		URL_MAP.put(USE, "admin/withdrawscashrate/isuse_withdrawscashrate");
	}
	/*
	 * 收费方式
	 */
	// 到账前
	public static final Short WDCTYPE_BEFORE = 1;
	// 到账后
	public static final Short WDCTYPE_AFTER = 2;
	public static Map<Short, String> WDCTYPE_MAP;
	static
	{
		WDCTYPE_MAP = new HashMap<Short, String>();
		WDCTYPE_MAP.put(WDCTYPE_BEFORE, "到账前扣");
		WDCTYPE_MAP.put(WDCTYPE_AFTER, "到账后扣");
	}
	/*
	 * 收费类型
	 */
	// 定额
	public static final Short WDCMODE_QUOTA = 1;
	// 比例
	public static final Short WDCMODE_SCALE = 2;
	public static Map<Short, String> WDCMODE_MAP;
	static
	{
		WDCMODE_MAP = new HashMap<Short, String>();
		WDCMODE_MAP.put(WDCMODE_QUOTA, "定额");
		WDCMODE_MAP.put(WDCMODE_SCALE, "比例");
	}
	/*
	 * 到账类型
	 */
	// 正常
	public static final Short REACHTYPE_NORMAL = 1;
	// 比例
	public static final Short REACHTYPE_IMMEDIATE = 2;
	public static Map<Short, String> REACHTYPE_MAP;
	static
	{
		REACHTYPE_MAP = new HashMap<Short, String>(); 
		REACHTYPE_MAP.put(REACHTYPE_NORMAL, "正常到账");
		REACHTYPE_MAP.put(REACHTYPE_IMMEDIATE, "即时到账");
	}
	/*
	 * 审核状态
	 */
	// 待审核
	public static final Short ISAUDIT_NO = 0;
	// 审核通过
	public static final Short ISAUDIT_PASS = 1;
	public static Map<Short, String> ISAUDIT_MAP;
	static
	{
		ISAUDIT_MAP = new HashMap<Short, String>();
		ISAUDIT_MAP.put(ISAUDIT_NO, "待审核");
		ISAUDIT_MAP.put(ISAUDIT_PASS, "审核通过");
	}
	/*
	 * 启用状态
	 */
	// 未启用
	public static final Short ISUSE_NO = 0;
	// 已启用
	public static final Short ISUSE_PASS = 1;

}
