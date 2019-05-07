
package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class UserWithdrawsCash_Constant {
	// List
	public static final String List = "admin/userwithdrawscash/userwithdrawscashList";
	// 批量审核UI
	public static final String BATCHAUDITUI = "admin/userwithdrawscash/batchaudit_userwithdrawscash";
	// 批量取消UI
	public static final String BATCHCANCELUI = "admin/userwithdrawscash/batchcancel_userwithdrawscash";
	// 详情
	public static final short DETAIL = 1;
	// 编辑
	public static final short UPDATE = 2;
	// 审核
	public static final short AUDIT = 3;
	// 取消
	public static final short CANCEL = 4;
	// 审核action
	public static final String AUDIT_ACTION = "queryAllForAudit.action";
	// 编辑（对账）action
	public static final String UPDATE_ACTION = "queryAllForUpdate.action";
	// 提现取消 action
	public static final String CANCEL_ACTION = "queryAllForCancel.action";
	// 查看 action
	public static final String QUERY_ACTION = "queryAll.action";

	public static final Integer WITHDRAWNOTE = 12;
	
	public static Map<Short, String> URL_MAP;
	static
	{
		URL_MAP = new HashMap<Short, String>();
		URL_MAP.put(DETAIL, "admin/userwithdrawscash/userwithdrawscash_Detail");
		URL_MAP.put(UPDATE, "admin/userwithdrawscash/update_userwithdrawscash");
		URL_MAP.put(AUDIT, "admin/userwithdrawscash/audit_userwithdrawscash");
		URL_MAP.put(CANCEL, "admin/userwithdrawscash/cancel_userwithdrawscash");
	}
}
