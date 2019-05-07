package com.ptpl.web.util;

public class ZhanWeiFuUtil {
	/**
	 * 返回占位符字符串
	 * 
	 * @param size
	 *            字符串长度
	 * @return
	 */
	public static String getPlaceholder(int size)
	{
		String str = "";
		for (int i = 0; i < size; i++)
		{
			str = str + "0";
		}
		return str;
	}
	/**
	 * 设置占位符字符串 = 1
	 * 
	 * @param str
	 * @param idex
	 *            下标
	 * @return
	 */
	public static String setPlaceholder(String str, int idex)
	{
		StringBuffer buffer = new StringBuffer(str);
		String ss = buffer.replace(idex, idex + 1, "1").toString();
		return ss;
	}
	
	public static void main(String[] args) {
		String str = getPlaceholder(10);
		System.out.println(str);
		for(int i=1;i<=4;i++)
		{
			str = setPlaceholder(str, i-1);
			System.out.println(str);
		}
	}
}
