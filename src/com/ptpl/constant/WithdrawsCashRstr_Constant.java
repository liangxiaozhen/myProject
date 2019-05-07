package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class WithdrawsCashRstr_Constant {
	public static final String LIST_VIEW = "admin/withdrawscashrstr/withdrawsCashRstrList";
	public static final String INSERT_VIEW = "admin/withdrawscashrstr/insert_withdrawsCashRstr";
	public static final String DELETE_VIEW = "admin/withdrawscashrstr/delete_withdrawsCashRstr";
	public static final short DETAIL = 1;
	public static final short UPDATE = 2;
	public static final short AUDIT =3;
	// 审核 action
	public static final String AUDIT_ACTION = "queryAllForAudit.action";
	// 编辑 action
	public static final String UPDATE_ACTION = "queryAllForUpdate.action";
	// 查看 action
	public static final String QUERY_ACTION = "queryAll.action";
	public static Map<Short, String> URL_MAP;
	static
	{
		URL_MAP = new HashMap<Short, String>();
		URL_MAP.put(DETAIL, "admin/withdrawscashrstr/withdrawsCashRstr_Detail");
		URL_MAP.put(UPDATE, "admin/withdrawscashrstr/update_withdrawsCashRstr");
		URL_MAP.put(AUDIT, "admin/withdrawscashrstr/audit_withdrawsCashRstr");
	}
	/*
	 * 启用状态
	 */
	// 未启用
	public static final Short ISUSE_NO = 0;
	// 已启用
	public static final Short ISUSE_PASS = 1;
}
