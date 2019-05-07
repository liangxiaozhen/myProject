package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 批量文件记录表的状态常量
 */
public class BacthFileRecord_Constant {
	
	/*
	 * 业务文件类型
	 */
	public static short KAIHU = 1;//开户
	public static short RENTRANSFER = 2;//红包转账
	public static short RONGZIKOUKUAN = 3;//融资扣款
	public static short DAOQIHUANKUAN = 4;//到期还款
	public static short EVE =5; //交易明细流水;
	public static short ALEVE=6; //全流水明细
	public static Map<Short,String> BUSINESSTYPE;//业务文件类型map
	static{
		BUSINESSTYPE = new HashMap<Short,String>();
		BUSINESSTYPE.put(KAIHU, "开户");
		BUSINESSTYPE.put(RENTRANSFER, "红包转账");
		BUSINESSTYPE.put(RONGZIKOUKUAN, "融资扣款");
		BUSINESSTYPE.put(DAOQIHUANKUAN, "到期还款");
		BUSINESSTYPE.put(EVE, "交易明细流水");
		BUSINESSTYPE.put(ALEVE, "全流水");
	}
	
	/*
	 * 是否已发送到银行
	 */
	public static short SENDNO = 0;//否
	public static short SENDYES = 1;//是
	public static Map<Short,String> SENDBANK;//是否已发送到银行map
	static{
		SENDBANK = new HashMap<Short,String>();
		SENDBANK.put(SENDNO, "否");
		SENDBANK.put(SENDYES, "是");
	}
	
	/*
	 * 发送结果
	 */
	public static short RESULTNO = 0;//失败
	public static short RESULTYES = 1;//成功
	public static Map<Short,String> SENDRESULT;//发送结果map
	static{
		SENDRESULT = new HashMap<Short,String>();
		SENDRESULT.put(RESULTNO, "失败");
		SENDRESULT.put(RESULTYES, "成功");
	}
	
	/*
	 * 是否已处理文件
	 */
	public static short DEALNO = 0;//否
	public static short DEALYES = 1;//是
	public static short DEALCANCEL = 2;//作废
	public static Map<Short,String> ISDEALFILE;//是否已处理文件map
	static{
		ISDEALFILE = new HashMap<Short,String>();
		ISDEALFILE.put(DEALNO, "否");
		ISDEALFILE.put(DEALYES, "是");
		ISDEALFILE.put(DEALCANCEL, "作废");
	}
	
	/*
	 * 处理结果（是否成功）
	 */
	public static short DEALRESULTNO = 0;//失败
	public static short DEALRESULTYES = 1;//成功
	public static Map<Short,String> DEALRESULT;//是否已处理文件map
	static{
		DEALRESULT = new HashMap<Short,String>();
		DEALRESULT.put(DEALRESULTNO, "失败");
		DEALRESULT.put(DEALRESULTYES, "成功");
	}
}
