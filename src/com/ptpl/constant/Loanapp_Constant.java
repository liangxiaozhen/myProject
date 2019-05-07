package com.ptpl.constant;//

import java.util.HashMap;
import java.util.Map;

/**
 *  借款申请状态
 * @author :liuqh
 * @date 2017/6/29 17:45
 */
public class Loanapp_Constant {
    public static Short T1=1;//待审核
    public static Short T2=2;//审核失败
    public static Short T3=3;//待建标
    public static Short T4=4;//待录入
    public static Short T5=5;//待投标
    public static Short T6=6;//投标中
    public static Short T7=7;//已流标
    public static Short T8=8;//待放款
    public static Short T9=9;//待生成还款计划
    public static Short T10=10;//还款中
    public static Short T11=11;//已完成

    public static Short T12=12;//录入失败
    public static Short T13=13;//录入过期
    public static Short T14=14;//录入放弃
    public static Map<Short,String> LOANAPP_APPSTATUS_MAP;
    static {
        LOANAPP_APPSTATUS_MAP=new HashMap<Short,String>();
        LOANAPP_APPSTATUS_MAP.put(T1,"待审核");
        LOANAPP_APPSTATUS_MAP.put(T2,"审核失败");
        LOANAPP_APPSTATUS_MAP.put(T3,"待建标");
        LOANAPP_APPSTATUS_MAP.put(T4,"待录入");
        LOANAPP_APPSTATUS_MAP.put(T5,"待投标");
        LOANAPP_APPSTATUS_MAP.put(T6,"投标中");
        LOANAPP_APPSTATUS_MAP.put(T7,"已流标");
        LOANAPP_APPSTATUS_MAP.put(T8,"待放款");
        LOANAPP_APPSTATUS_MAP.put(T9,"待生成还款计划");
        LOANAPP_APPSTATUS_MAP.put(T10,"还款中");
        LOANAPP_APPSTATUS_MAP.put(T11,"已完成");
        LOANAPP_APPSTATUS_MAP.put(T12,"录入失败");
        LOANAPP_APPSTATUS_MAP.put(T13,"录入过期");
        LOANAPP_APPSTATUS_MAP.put(T14,"录入放弃");

    }
}
