package com.ptpl.web.util;

import java.sql.Date;
import java.util.Calendar;

public class GetStringNo {
	
	
	/**
	 * 获取cookies
	 * @return
	 */
	public static String getCookies(){
		Calendar ct = Calendar.getInstance();
		String cookies = "GJ" + ct.get(Calendar.YEAR) + ""
				+ (ct.get(Calendar.MONTH) + 1) + ""
				+ ct.get(Calendar.DAY_OF_MONTH) + ""
				+ ct.get(Calendar.HOUR_OF_DAY) + ""
				+ ct.get(Calendar.MINUTE) + ""
				+ ct.get(Calendar.SECOND)+
				+ ct.get(Calendar.MILLISECOND);
		return cookies;
	}
	/**
	 * 获取批次号
	 * @return
	 */
	public static String getBatchNo() {
		Calendar ct = Calendar.getInstance();
		String batchno = "" + (ct.get(Calendar.YEAR)+"").substring(2) + ""
				+ (ct.get(Calendar.MONTH) + 1) + ""
				+ ct.get(Calendar.DAY_OF_MONTH) + ""
				+ ct.get(Calendar.HOUR_OF_DAY) + ""
				+ ct.get(Calendar.MINUTE) + ""
				+ ct.get(Calendar.SECOND)
				+ ct.get(Calendar.MILLISECOND);
		return batchno;
	}
	//获取订单流水号
	public static String getOrderinfo(){
		long itmp1 = Math.round(Math.random() * 9);
		long itmp2 = Math.round(Math.random() * 9);
		long itmp3 = Math.round(Math.random() * 9);
		Calendar ct = Calendar.getInstance();
		StringBuffer orderinfo = new StringBuffer();
		orderinfo.append(getYearDay())
                 .append(getTimeString())
                 .append(getStr3(ct.get(Calendar.MILLISECOND)))
                 .append(String.valueOf(itmp1))
                 .append(String.valueOf(itmp2))
                 .append(String.valueOf(itmp3))
				;
		return orderinfo.toString();
	}
	//年月日  YYYYMMDD
	public static String getYearDay(){
		Calendar ct = Calendar.getInstance();
		return ct.get(Calendar.YEAR) + getStr2(ct.get(Calendar.MONTH)+1) + getStr2(ct.get(Calendar.DAY_OF_MONTH));
	}
	//时分秒 HHMMSS
	public static String getTimeString(){
		Calendar ct = Calendar.getInstance();
		return getStr2(ct.get(Calendar.HOUR_OF_DAY)) + getStr2(ct.get(Calendar.MINUTE)) + getStr2(ct.get(Calendar.SECOND));
	}
	//月日时分秒--两位
	public static String getStr2(int num){
		String str2 = null;
		if(String.valueOf(num).length()==1){
			str2="0"+num;
		}else{
			str2=num+"";
		}
		return str2 ;
	}
	//毫秒数--三位
	public static String getStr3(int num){
		String str3 = null;
		if(String.valueOf(num).length()==1){
			str3="00"+num;
		}else if(String.valueOf(num).length()==2){
			str3="0"+num;
		}else{
			str3=""+num;
		}
		return str3 ;
	}
	
	
	public static Date getTime(){
//		生成系统时间
		Calendar rightNow = Calendar.getInstance();
		Date now = new Date(rightNow.getTimeInMillis());
		return now;
	}
	
	public int getMonthTime(){
		Calendar c  = Calendar.getInstance();   
		c.setTime(new java.util.Date());   
		int month = c.get(Calendar.MONTH)+1;   
		return month;
	}
	
	
	 
	public static void main(String[] args) {
//		Calendar currenttime = Calendar.getInstance();
//		String ssss=""+ currenttime.get(currenttime.YEAR);
		while(true){
			System.out.println(getOrderinfo());
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	
}
