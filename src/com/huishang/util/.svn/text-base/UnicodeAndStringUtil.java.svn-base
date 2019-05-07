package com.huishang.util;
/**
 * 
* @ClassName: UnicodeAndStringUtil 
* @Description: TODO(中文转成Unicode 工具类) 
* @author cjm 
* @date 2017年3月31日 下午2:07:15 
*
 */
public class UnicodeAndStringUtil {

	/**
	 * 
	* @Title: unicodeByString 
	* @Description: TODO(unicode 转成中文) 
	* @param @param unicode
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static String unicodeByString(String unicode) {
 	    StringBuffer string = new StringBuffer();
 	    String[] hex = unicode.split("\\\\u");
 	    for (int i = 1; i < hex.length; i++) {
 	        int data = Integer.parseInt(hex[i], 16);
 	        string.append((char) data);
	    }
 	    return string.toString();
	}

	/**
	 * 
	* @Title: stringByUnicode 
	* @Description: TODO(中文转成Unicode) 
	* @param @param string
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
  public static String stringByUnicode(String string) {
 	    StringBuffer unicode = new StringBuffer();
 	    for (int i = 0; i < string.length(); i++) {
 	        char c = string.charAt(i);
 	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
 	    return unicode.toString();
	}
}
