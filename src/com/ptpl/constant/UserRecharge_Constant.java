package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 充值记录常量类
 * @author jxy
 *
 */
public class UserRecharge_Constant {
	/**列表页面*/
	public static final String List = "admin/userRecharge/userRechargeList";
	/**对账页面*/
	public static final String Blending = "admin/userRecharge/userRechargeBlending";
	/**
	 * 跳转到详情页面
	 */
	public static final String DETALIS = "admin/userRecharge/userRechargeDetalis";
	/**
	 * 修改充值记录的跳转页面
	 */
	public static final String EDITS = "admin/userRecharge/updateUserRecharge";
	/**
	 * 请求数据包
	 */
	public static final String REQUESTQUERY = "admin/userRecharge/requestQuery";
	/**
	 * 接收数据包
	 */
	public static final String RESPSNERESULT = "admin/userRecharge/respsneResult";
	/**
	 * 上传文件的时候的跳转页面
	 */
	public static final String FILEUPLOAD = "admin/userRecharge/userRechargeFileUpload";
	
	public static Map<String, String> BANK_MAP;
	static
	{
		BANK_MAP = new HashMap<String, String>();
		BANK_MAP.put("未知", "未知");
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
	
	public static Map<String, String> BANK_MAPTOCODE;
	static {
		BANK_MAPTOCODE = new HashMap<String, String>();
		BANK_MAPTOCODE.put("中国工商银行","ICBC");
		BANK_MAPTOCODE.put("中国农业银行","ABC");
		BANK_MAPTOCODE.put("招商银行","CMB");
		BANK_MAPTOCODE.put("中国建设银行","CCB");
		BANK_MAPTOCODE.put("北京银行","BCCB");
		BANK_MAPTOCODE.put( "北京农村商业银行","BJRCB");
		BANK_MAPTOCODE.put( "中国银行","BOC");
		BANK_MAPTOCODE.put( "交通银行","BOCOM");
		BANK_MAPTOCODE.put("中国民生银行","CMBC");
		BANK_MAPTOCODE.put("上海银行","BOS");
		BANK_MAPTOCODE.put( "渤海银行","CBHB");
		BANK_MAPTOCODE.put( "中国光大银行","CEB");
		BANK_MAPTOCODE.put( "兴业银行","CIB");
		BANK_MAPTOCODE.put( "中信银行","CITIC");
		BANK_MAPTOCODE.put( "浙商银行","CZB");
		BANK_MAPTOCODE.put( "广发银行","GDB");
		BANK_MAPTOCODE.put( "东亚银行","HKBEA");
		BANK_MAPTOCODE.put( "华夏银行","HXB");
		BANK_MAPTOCODE.put( "杭州银行","HZCB");
		BANK_MAPTOCODE.put( "南京银行","NJCB");
		BANK_MAPTOCODE.put( "平安银行","PINGAN");
		BANK_MAPTOCODE.put( "中国邮政储蓄银行","PSBC");
		BANK_MAPTOCODE.put("深圳发展银行","SDB" );
		BANK_MAPTOCODE.put( "浦东发展银行","SPDB");
		BANK_MAPTOCODE.put( "上海农村商业银行","SRCB");
	}
	/**
	 * 是否系统勾兑
	 */
		// 否
		public static final Short ISBLENDING_NO = 0;
		// 是
		public static final Short ISBLENDING_YES = 1;
		public static Map<Short, String> ISBLENDING_MAP;
		static
		{
			ISBLENDING_MAP = new HashMap<Short, String>();
			ISBLENDING_MAP.put(ISBLENDING_NO, "未勾兑");
			ISBLENDING_MAP.put(ISBLENDING_YES, "已勾兑");
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
			ISAUDIT_MAP.put(ISAUDIT_NO, "否");
			ISAUDIT_MAP.put(ISAUDIT_YES, "是");
		}
		/*
		 * 充值费率
		 */
		// 定额
		public static final Short RECHARRATE_QUOTA = 1;
		// 比例
		public static final Short RECHARRATE_SCALE = 2;
		public static Map<Short, String> RECHARRATE_MAP;
		static
		{
			RECHARRATE_MAP = new HashMap<Short, String>();
			RECHARRATE_MAP.put(RECHARRATE_QUOTA, "定额");
			RECHARRATE_MAP.put(RECHARRATE_SCALE, "比例");
		}
		/**
		 * 是否人工勾兑
		 */
		// 否
		public static final Short ISMANBLENDING_NO = 0;
		// 是
		public static final Short ISMANBLENDING_YES = 1;
		public static Map<Short, String> ISMANBLENDING_MAP;
		static
		{
			ISMANBLENDING_MAP = new HashMap<Short, String>();
			ISMANBLENDING_MAP.put(ISMANBLENDING_NO, "未勾兑");
			ISMANBLENDING_MAP.put(ISMANBLENDING_YES, "已勾兑");
		}
		/**
		 * 是否异常
		 */
		// 否
		public static final Short ISEXCEPTIONS_NO = 0;
		// 是
		public static final Short ISEXCEPTIONS_YES = 1;
		public static Map<Short, String> ISEXCEPTIONS_MAP;
		static
		{
			ISEXCEPTIONS_MAP = new HashMap<Short, String>();
			ISEXCEPTIONS_MAP.put(ISEXCEPTIONS_NO, "正常");
			ISEXCEPTIONS_MAP.put(ISEXCEPTIONS_YES, "异常");
		}
		
		/**
		 * 充值方式
		 */
		public static final Short RECHARGETYPE_ZROE = 0;
		public static final Short RECHARGETYPE_ONE = 1;
		public static final Short RECHARGETYPE_TWO = 2;
		public static final Short RECHARGETYPE_THREE = 3;
		public static final Short RECHARGETYPE_FOUR = 4;
		public static Map<Short, String> RECHARGETYPE_MAP;
		static
		{
			RECHARGETYPE_MAP = new HashMap<Short, String>();
			RECHARGETYPE_MAP.put(RECHARGETYPE_ZROE, "无");
			RECHARGETYPE_MAP.put(RECHARGETYPE_ONE, "个人网银");
			RECHARGETYPE_MAP.put(RECHARGETYPE_TWO, "企业网银");
			RECHARGETYPE_MAP.put(RECHARGETYPE_THREE, "快捷");
			RECHARGETYPE_MAP.put(RECHARGETYPE_FOUR, "汇款充值");
		}
		
		/**
		 * 充值状态
		 */
		public static final Short STATUS_ONE = 1;
		public static final Short STATUS_TWO = 2;
		public static final Short STATUS_THREE = 3;
		public static final Short STATUS_FOUR= 4;
		public static final Short STATUS_FIVE= 5;
		public static Map<Short, String> STATUS_MAP;
		static
		{
			STATUS_MAP = new HashMap<Short, String>();
			STATUS_MAP.put(STATUS_ONE, "成功");
			STATUS_MAP.put(STATUS_TWO, "失败");
			STATUS_MAP.put(STATUS_THREE, "取消");
			STATUS_MAP.put(STATUS_FOUR, "待充值");
		}
		/**
		 * 充值设备来源
		 */
		public static final Short ORIGINCLIENT_ONE = 1;
		public static final Short ORIGINCLIENT_TWO = 2;
		public static final Short ORIGINCLIENT_THREE = 3;
		public static final Short ORIGINCLIENT_FOUR = 4;
		public static Map<Short, String> ORIGINCLIENT_MAP;
		static
		{
			ORIGINCLIENT_MAP = new HashMap<Short, String>();
			ORIGINCLIENT_MAP.put(ORIGINCLIENT_ONE, "PC端");
			ORIGINCLIENT_MAP.put(ORIGINCLIENT_TWO, "ipad");
			ORIGINCLIENT_MAP.put(ORIGINCLIENT_THREE, "App");
			ORIGINCLIENT_MAP.put(ORIGINCLIENT_FOUR, "苹果端");
		}
		/**
		 * 请求数据包
		 */
		public static String reqquerydata="";
		/**
		 * 接收数据包
		 */
		public static String recresultdata="";
		
		
		public static String getReqquerydata() {
			return reqquerydata;
		}
		public static void setReqquerydata(String reqquerydata) {
			UserRecharge_Constant.reqquerydata = reqquerydata;
		}
		public static String getRecresultdata() {
			return recresultdata;
		}
		public static void setRecresultdata(String recresultdata) {
			UserRecharge_Constant.recresultdata = recresultdata;
		}
		/**
		 * 消息类型(系统充值对账)
		 */
		public static final String SAVERECONCILIATION = "SaveReconciliation";
		/**
		 * 消息类型(系统充值人工)
		 */
		public static final String QUERYTRANSDETAIL = "QueryTransDetail";
}
