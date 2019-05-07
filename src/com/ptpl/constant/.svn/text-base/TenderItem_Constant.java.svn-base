package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

public class TenderItem_Constant {

	//起息规则
	public static Short VALUERULE_T = 1;//结标日当天
	public static Short VALUERULE_T1 = 2;//结标日次日
	public static Short VALUERULE_TTIME = 3;//结标时间点

	// 计息方式(1.一次还本付息 2.等额本金 3.等额本息 4.按期付息到期还本)
	public static Short REPAYMENTPRO_DENGEBENXI = 1;

	public static Short REPAYMENTPRO_DENGEBENJIN = 2;
	public static Short REPAYMENTPRO_YICHIXINGHUANBENFUXI = 3;
	public static Short REPAYMENTPRO_XIANXIHOUBEN = 4;

	//标状态（11种）
	public static Short T1=1;//待录入
	public static Short T2=2;//待投标
	public static Short T3=3;//投标中
	public static Short T4=4;//已流标
	public static Short T5=5;//待放款
	public static Short T6=6;//待生成还款计划
	public static Short T7=7;//还款中
	public static Short T8=8;//已完成

	public static Short T9=9;//录入失败
	public static Short T10=10;//录入过期
	public static Short T11=11;//录入放弃



	//标上架状态
	public static Short ONANDDOWN_0=0;//待上架
	public static Short ONANDDOWN_1=1;//已上架
	public static Short ONANDDOWN_2=2;//已下架

	//计息方式
	public static Map<Short, String> REPAYMENTPRO_MAP;
	
	//标的状态
	public static Map<Short, String> TSTATUS_MAP;
	
	//起息方式
	public static Map<Short, String> VALUERULE_MAP;

	//上架状态
	public static Map<Short,String> ONANDDOWN_MAP;

	// 标状态
	static {
		TSTATUS_MAP =new HashMap<Short,String>();
		TSTATUS_MAP.put(T1, "待录入");
		TSTATUS_MAP.put(T2, "待投标");
		TSTATUS_MAP.put(T3, "投标中");
		TSTATUS_MAP.put(T4, "已流标");
		TSTATUS_MAP.put(T5, "待放款");
		TSTATUS_MAP.put(T6, "待生成还款计划");
		TSTATUS_MAP.put(T7, "还款中");
		TSTATUS_MAP.put(T8, "已完成");

		TSTATUS_MAP.put(T9,"录入失败");
		TSTATUS_MAP.put(T10,"录入过期");
		TSTATUS_MAP.put(T11,"录入放弃");

		//计息方式maps
		REPAYMENTPRO_MAP = new HashMap<Short, String>();
		REPAYMENTPRO_MAP.put(REPAYMENTPRO_DENGEBENXI, "一次还本付息");
		REPAYMENTPRO_MAP.put(REPAYMENTPRO_DENGEBENJIN, "等额本金");
		REPAYMENTPRO_MAP.put(REPAYMENTPRO_YICHIXINGHUANBENFUXI, "等额本息");
		REPAYMENTPRO_MAP.put(REPAYMENTPRO_XIANXIHOUBEN, "按期付息到期还本");
		
		//起息规则(1.结标日当天,2.结标日次日,3.结标时间点)
		VALUERULE_MAP = new HashMap<Short, String>();
		VALUERULE_MAP.put(VALUERULE_T, "生成还款日当天");
		VALUERULE_MAP.put(VALUERULE_T1, "生成还款日次日");
		VALUERULE_MAP.put(VALUERULE_TTIME, "生成还款日指定时间点前后");

		//上架状态
		ONANDDOWN_MAP=new HashMap<Short,String>();
		ONANDDOWN_MAP.put(ONANDDOWN_0, "待上架");
		ONANDDOWN_MAP.put(ONANDDOWN_1, "已上架");
		ONANDDOWN_MAP.put(ONANDDOWN_2, "已下架");
	}
}
