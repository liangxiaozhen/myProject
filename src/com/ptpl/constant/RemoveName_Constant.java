package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class RemoveName_Constant {
	public static final String LIST_VIEW = "admin/removename/removenameList";
	
	public static final String NAMELIST_VIEW = "admin/removename/removenameList_nametype";
	public static final String INSERT_VIEW = "admin/removename/insert_removename";
	public static final String BATCHINSERT="admin/removename/batchinsert_removename";
	public static final String BATCHDELUI="admin/removename/batchdelete_removename";
	public static final String BATCHUPDATE="admin/removename/batchupdate_removename";
	public static final String INSERTNAMETYPE_VIEW = "admin/removename/insert_removename_nametype";
	public static short DETAIL = 1;
	public static short UPDATE = 2;
	public static short DELETE =3;
	// 编辑 action
	public static final String UPDATE_ACTION = "queryAllForUpdate.action";
	// 查看 action
	public static final String QUERY_ACTION = "queryAll.action";
	// 查看 action
	public static final String QUERYNAMETYPE_ACTION = "queryAllNameType.action";
	// 编辑 action
	public static final String NAMETYPEUPDATE_ACTION = "queryAllNameTypeForUpdate.action";
	public static Map<Short, String> URL_MAP;
	static
	{
		URL_MAP = new HashMap<Short, String>();
		URL_MAP.put(DETAIL, "admin/removename/removename_Detail");
		URL_MAP.put(UPDATE, "admin/removename/update_removename");
		URL_MAP.put(DELETE, "admin/removename/delete_removename");
	}
}
