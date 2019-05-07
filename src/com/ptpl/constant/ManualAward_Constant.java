package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户红包、积分、使用券常量
 * 
 */
public class ManualAward_Constant {
	
	/**
	 * 活动类型
	 */
	public static short TYPE_ZHUCE=1;//注册活动
	public static short TYPE_LEITOU=2;//累投
	public static short TYPE_DANBIAO=3;//单标
	public static short TYPE_SHOUTOU=4;//首投
	public static short TYPE_MANUAL=5;//手动颁奖
	public static short TYPE_AUTONOMY=6;//自主颁奖
	
	public static Map<Short,String> TYPE_STATUS;
	static{
		TYPE_STATUS= new HashMap<Short,String>();
		TYPE_STATUS.put(TYPE_ZHUCE, "注册活动");
		TYPE_STATUS.put(TYPE_LEITOU, "累投活动");
		TYPE_STATUS.put(TYPE_DANBIAO, "单标活动");
		TYPE_STATUS.put(TYPE_SHOUTOU, "首投活动");
		TYPE_STATUS.put(TYPE_MANUAL, "手动颁奖");
		TYPE_STATUS.put(TYPE_AUTONOMY, "自主颁奖");
	}
	
	/**
	 * 活动状态
	 */
	public static short STATUS_IMPROVED=0;//待完善
	public static short STATUS_TOEXECUTED=1;//待执行
	public static short STATUS_COMPLETED = 3;//已完成
	public static short STATUS_DEACTIVATED=4;//已停用
	public static short STATUS_CANCELLED=5;//已作废
	public static short STATUS_EXPIRED=6;//已过期
	
	public static Map<Short,String> STATUS_MAP;
	static {
		STATUS_MAP = new HashMap<Short,String>();
		STATUS_MAP.put(STATUS_IMPROVED, "待完善");
		STATUS_MAP.put(STATUS_TOEXECUTED, "待执行");
		STATUS_MAP.put(STATUS_COMPLETED, "已完成");
		STATUS_MAP.put(STATUS_DEACTIVATED, "已停用");
		STATUS_MAP.put(STATUS_CANCELLED, "已作废");
		STATUS_MAP.put(STATUS_EXPIRED, "已过期");
	}
	
	
	/**
	 * 获奖人审核
	 */
	public static short AUDIT_YES=1;//需要审核
	public static short AUDIT_NO=2;//无需审核
	
	public static Map<Short,String> STATUS_AUDIT;
	static{
		STATUS_AUDIT = new HashMap<Short,String>();
		STATUS_AUDIT.put(AUDIT_YES, "需要审核");
		STATUS_AUDIT.put(AUDIT_NO, "无需审核");
	}
	
	
	/**
	 * 生成方式
	 */
	public static short RECTYPE_MANUAL = 1;//手动生成
	public static short RECTYPE_TEMPLATE = 2;//模板生成
	
	public static Map<Short,String> RECTYPE_MAP;
	static {
		RECTYPE_MAP = new HashMap<Short,String>();
		RECTYPE_MAP.put(RECTYPE_MANUAL, "手动生成");
		RECTYPE_MAP.put(RECTYPE_TEMPLATE, "模板生成");
	}
	/**
	 * 手动执行
	 */
	public static short RECTYPE_ALLOW = 1;//容许
	public static short RECTYPE_UNALLOW = 2;//不容许

	public static Map<Short,String> EXECUTE_MAP;
	static{
		EXECUTE_MAP = new HashMap<Short,String>();
		EXECUTE_MAP.put(RECTYPE_ALLOW, "容许");
		EXECUTE_MAP.put(RECTYPE_UNALLOW, "不容许");
	}
	
	/**
	 * 执行状态
	 */
	public static short STATUS_HANDMADE=1;//手工执行
	public static short STATUS_SYSTEM=2;//系统执行
	public static short STATUS_AUTONOMY=3;//自主执行
	public static short STATUS_LINKAGE=4;//联动执行
	
	public static  Map<Short,String> EXECUTE_STATUS;
	static{
		EXECUTE_STATUS =new HashMap<Short,String>();
		EXECUTE_STATUS.put(STATUS_HANDMADE, "手工执行");
		EXECUTE_STATUS.put(STATUS_SYSTEM, "系统执行");
		EXECUTE_STATUS.put(STATUS_AUTONOMY, "自主执行");
		EXECUTE_STATUS.put(STATUS_LINKAGE, "联动执行");
	}
	
	/**
	 * 是否作为模板
	 */
	public static short TEMPLATE_YES=1;//是
	public static short TEMPLATE_NO=2;//否
	
	public static  Map<Short,String> TEMPLATE_JUDGE;
	static{
		TEMPLATE_JUDGE = new HashMap<Short,String>();
		TEMPLATE_JUDGE.put(TEMPLATE_YES, "是");
		TEMPLATE_JUDGE.put(TEMPLATE_NO, "否");
	}
	
	/**
	 * 定向方式
	 */
	public static short SIZE_LIST=1;//大小名单
	public static short MEMBER_GRADE=2;//会员等级
	
	public static  Map<Short,String> DIRECTION_TYPE;
	static{
		DIRECTION_TYPE = new HashMap<Short,String>();
		DIRECTION_TYPE.put(SIZE_LIST, "大小名单");
		DIRECTION_TYPE.put(MEMBER_GRADE, "会员等级");
	}
	/**
	 * 定向类型
	 */
	public static short SYSTEM_BUSINESS=1;//系统业务
	public static short MESSAGE_NOTICE=2;//短信通知
	
	public static  Map<Short,String> DIRECTION_MOLD;
	static{
		DIRECTION_MOLD = new HashMap<Short,String>();
		DIRECTION_MOLD.put(SYSTEM_BUSINESS, "系统业务");
		DIRECTION_MOLD.put(MESSAGE_NOTICE, "短信通知");
	}
	
	/**
	 * 引用状态
	 */
	public static short QUOTE_YES=1;//是
	public static short QUOTE_NO=2;//否
	
	public static  Map<Short,String> QUOTE_STATUS;
	static{
		QUOTE_STATUS = new HashMap<Short,String>();
		QUOTE_STATUS.put(QUOTE_YES, "是");
		QUOTE_STATUS.put(QUOTE_NO, "否");
	}
	
	/**
	 * 启用状态
	 */
	public static short USE_YES=1;//启用
	public static short USE_NO=2;//停用
	
	public static  Map<Short,String> USE_STATUS;
	static{
		USE_STATUS = new HashMap<Short,String>();
		USE_STATUS.put(QUOTE_YES, "启用");
		USE_STATUS.put(QUOTE_NO, "停用");
	}
	/**
	 * 系统业务具体类型
	 */
	public static short BIAOSHEZHI=1;//标的设置
	public static short ZHUANRANGREN=2;//债转转让人
	public static short CHENGJIEREN=3;//债转承接人
	public static short DANBIAO=4;//单标活动
	public static short LEITOU=5;//累投活动
	public static short ZIHUBANJAING=6;//自主领奖活动
	public static short MANUAL=7;//手动颁奖活动
	public static short CHONGZHIYUNXU=8;//充值人
	public static short ZCHUIYUAN=9;//正常会员
	public static short TYHUIYUAN=10;//体验会员
	public static short JIANGPINSHEZHI=11;//奖品设置
	public static short TIDIQUAN=12;//提低卷活动

	public static Map<Short,String> SYSTEMBUSTYPE;
	static{
		SYSTEMBUSTYPE = new HashMap<Short,String>();
		SYSTEMBUSTYPE.put(BIAOSHEZHI, "标的设置");
		SYSTEMBUSTYPE.put(ZHUANRANGREN, "债转转让人");
		SYSTEMBUSTYPE.put(CHENGJIEREN, "债转承接人");
		SYSTEMBUSTYPE.put(DANBIAO, "单标活动");
		SYSTEMBUSTYPE.put(LEITOU, "累投活动");
		SYSTEMBUSTYPE.put(ZIHUBANJAING, "自主领奖活动");
		SYSTEMBUSTYPE.put(MANUAL, "手动颁奖活动");
		SYSTEMBUSTYPE.put(CHONGZHIYUNXU, "充值人");
		SYSTEMBUSTYPE.put(ZCHUIYUAN, "正常会员");
		SYSTEMBUSTYPE.put(TYHUIYUAN, "体验会员");
		SYSTEMBUSTYPE.put(JIANGPINSHEZHI, "奖品设置");
		SYSTEMBUSTYPE.put(TIDIQUAN, "提低卷活动");
		
	}
	
	/**
	 * 短信通知具体类型
	 */
	public static short CZCG=1;//充值成功
	public static short TXCG=2;//提现成功
	public static short TQHKDZ=3;//提前还款到账
	public static short YQTX=4;//逾期提现
	public static short ZCHKDZ=5;//正常还款到账
	public static short HKCG=6;//还款成功
	public static short DXXZDL=7;//短信协助登录
	public static short DXYZMDL=8;//短信验证码登录
	public static short BPXX=9;//被迫下线
	public static short LIUBIAO=10;//流标
	public static short ZGLXDZ=11;//站岗利息到账
	
	public static Map<Short,String> SMSTYPE;
	static{
		
		SMSTYPE = new HashMap<Short,String>();
		SMSTYPE.put(CZCG, "充值成功");
		SMSTYPE.put(TXCG, "提现成功");
		SMSTYPE.put(TQHKDZ, "提前还款到账");
		SMSTYPE.put(YQTX, "逾期提现");
		SMSTYPE.put(ZCHKDZ, "正常还款到账");
		SMSTYPE.put(HKCG, "还款成功");
		SMSTYPE.put(DXXZDL, "短信协助登录");
		SMSTYPE.put(DXYZMDL, "短信验证码登录");
		SMSTYPE.put(BPXX, "被迫下线");
		SMSTYPE.put(LIUBIAO, "流标");
		SMSTYPE.put(ZGLXDZ, "站岗利息到账");
	}
	
	/**
	 * 奖品类型
	 */
	public static short LIPINBAO=1;//礼品包
	public static short JIANGPIN=2;//奖品
	public static Map<Short,String> AWARD_TYPE;
	static{
		AWARD_TYPE = new HashMap<Short,String>();
		AWARD_TYPE.put(LIPINBAO, "礼品包");
		AWARD_TYPE.put(JIANGPIN, "奖品");
	}
}
