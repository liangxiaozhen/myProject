package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Award_Constant {
	
	//奖品是否自定义（1.是   2.否）
	public static final Short DEFINITION_YES=1;
	public static final Short DEFINITION_NO=2;
	
	//奖品类型(1站内虚拟，2站外虚拟，3站外实物)
	public static final Short ATYPE_ZHANNEIXUNI=1;
	public static final Short ATYPE_ZHANWAIXUNI=2;
	public static final Short ATYPE_ZHANWAISHIWU=3;
	
	//奖品交易方式（互换，积分,余额，,互换+积分+余额）
	public static final Short tradetype_huhuan=0;
	public static final Short tradetype_zhifen=1;
	public static final Short tradetype_yue=2;
	public static final Short tradetype_huhuan_zhifen_yue=3;
	
	//用于存放奖品交易方式
	public static  Map<Short, String> tradetype_map;
	
	//用于把所有奖品类型放入map
	public static Map<Short,String> attributeData_map;
	public static final String[] attributeData_value={
													"现金券",
													"类现金券",
													"假现金券",
													"交易积分",//4
													"系统积分",//5
													"加息卷",//6
													/*"提现抵用券",
													"充值抵用券",*/
													/*"电影券",
													"手机充值券",
													"游戏点券",
													"手机",
													"鼠标",
													"耳机",
													"服饰"*/
													"虚拟其它",
													"实体其它"
													};
	public static final Short[] attributeData_key={1,2,3,4,5,6,31,61};
	
	//定义一个map用于存放奖品类型
	public static  Map<Short,String> ATYPE_MAP;
	
	//用于按奖品类型来存放对应的奖品属性集合
	public static  Map<Short,Map<Short,String>> atype_attributeData;
	
	//用于存放站内虚拟这种类型的奖品
	public static Map<Short,String> zhanneixuni;
	
	//用于存放站外虚拟这种类型的奖品
	public static Map<Short,String> zhanwaixuni;
	
	//用于存放站外实物这种类型的奖品
	public static Map<Short,String> zhanwaishiwu;
	
	//奖品是否自定义
	public static Map<Short,String> isdefinition;
	
	static{
	 
		isdefinition = new HashMap<Short,String>();
		isdefinition.put(DEFINITION_YES, "是");
		isdefinition.put(DEFINITION_NO, "否");
		
		//初始化奖品交易方式
		tradetype_map=new HashMap<Short,String>();
		tradetype_map.put(tradetype_huhuan, "互换");
		tradetype_map.put(tradetype_zhifen, "积分");
		tradetype_map.put(tradetype_yue, "余额");
		tradetype_map.put(tradetype_huhuan_zhifen_yue, "互换+积分+余额");
		
		//把所有奖品类型放入map中用于在某个奖品详细页面中显示奖品的类型
		attributeData_map=new HashMap<Short,String>();
		for(int i=0;i<attributeData_key.length;i++){
			attributeData_map.put(attributeData_key[i], attributeData_value[i]);
		}
		
		//初始化奖品类型的数据
		ATYPE_MAP=new HashMap<Short,String>();
		ATYPE_MAP.put(ATYPE_ZHANNEIXUNI,"站内虚拟" );
		ATYPE_MAP.put(ATYPE_ZHANWAIXUNI,"站外虚拟");
		ATYPE_MAP.put(ATYPE_ZHANWAISHIWU,"站外实物");
		
		//初始化站内虚拟奖品属性的数据
		zhanneixuni=new HashMap<Short,String>();
		zhanneixuni.put((short)1,"现金券");
		zhanneixuni.put((short)2,"类现金券");
		zhanneixuni.put((short)3,"假现金券");
		zhanneixuni.put((short)4,"交易积分");
		zhanneixuni.put((short)5,"系统积分");
		zhanneixuni.put((short)6,"加息卷");
		/*zhanneixuni.put((short)7,"提现抵用券");
		zhanneixuni.put((short)8,"充值抵用券");*/
		
		//初始化站外虚拟奖品属性的数据
		zhanwaixuni=new HashMap<Short,String>();
		zhanwaixuni.put((short)31,"虚拟其它");
		/*zhanwaixuni.put((short)32,"手机充值券");
		zhanwaixuni.put((short)33,"游戏点券");*/
		
		//初始化站外实物奖品属性的数据
		zhanwaishiwu=new HashMap<Short,String>();
		zhanwaishiwu.put((short)61,"实体其它");
		/*zhanwaishiwu.put((short)62,"鼠标");
		zhanwaishiwu.put((short)63,"耳机");
		zhanwaishiwu.put((short)64,"服饰");*/
		
		//初始化按奖品类型来存放对应的奖品属性集合，用于ajax根据所选奖品的类型来动态加载相应的奖品属性
		atype_attributeData=new HashMap<Short,Map<Short,String>>();
		atype_attributeData.put(ATYPE_ZHANNEIXUNI, zhanneixuni);
		atype_attributeData.put(ATYPE_ZHANWAIXUNI, zhanwaixuni);
		atype_attributeData.put(ATYPE_ZHANWAISHIWU, zhanwaishiwu);
	}
	
	public static void main(String[] args) {
		String jsonString = JSON.toJSONString(atype_attributeData);
		System.out.println(jsonString);
	}
	
	/**
	 * 加息对象
	 */
	public static final Short ZHENJIN=1;//真金
	public static final Short LEIXIANJIN=2;//类现金
	public static final Short JIAXIANJIN=3;//假现金
	
	public static Map<Short, String> READY_CASH;
	static{
		READY_CASH = new HashMap<Short,String>();
		READY_CASH.put(ZHENJIN, "真金");
		READY_CASH.put(LEIXIANJIN, "类现金");
		READY_CASH.put(JIAXIANJIN, "假现金");
	}
	
	/**
	 * 投标属性限制
	 */
	public static final Short XINYONG_BIAO=1;//信用标
	public static final Short DIYA_BIAO=2;//抵押标
	public static final Short DANBAO_BIAO=3;//担保标
	public static final Short XINSHOU_BIAO=4;//新手标
	
	public static Map<Short,String> BIAO_ATTRIBUTE;
	
	static{
		BIAO_ATTRIBUTE = new HashMap<Short,String>();
		BIAO_ATTRIBUTE.put(XINYONG_BIAO, "信用标");
		BIAO_ATTRIBUTE.put(DIYA_BIAO, "抵押标");
		BIAO_ATTRIBUTE.put(DANBAO_BIAO, "担保标");
		BIAO_ATTRIBUTE.put(XINSHOU_BIAO, "新手标");
	}
}
