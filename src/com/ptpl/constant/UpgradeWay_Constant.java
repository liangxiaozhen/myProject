package com.ptpl.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuj
 * 用户等级升级规则
 */
public class UpgradeWay_Constant {
	/**
	 * 升级方式(1购买,2兑换积分)/处理方式(1购买,2兑换积分,3 系统赠送)
	 */
    public static short WAY_BUY= 1;
    public static short WAY_EXCHANGE= 2;
    public static short WAY_SYSTEM= 3;
    
    /**
	 * 升级方式map(1购买,2兑换积分)/处理方式map(1购买,2兑换积分,3 系统赠送)
	 */
    public static Map<Short, String> UPGRADEWAY_MAP;
    static{
    	UPGRADEWAY_MAP=new HashMap<Short,String>();
    	UPGRADEWAY_MAP.put(WAY_BUY, "购买");
    	UPGRADEWAY_MAP.put(WAY_EXCHANGE, "积分兑换");
    	UPGRADEWAY_MAP.put(WAY_SYSTEM, "系统赠送");
    }
}    
