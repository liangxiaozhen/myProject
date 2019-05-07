package com.ptpl.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ConvertUtil {

	/**
	 * 从ip的字符串形式得到字节数组形式
	 * 
	 * @param ip
	 *            字符串形式的ip
	 * @return 字节数组形式的ip
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	/**
	 * 对原始字符串进行编码转换，如果失败，返回原始的字符串
	 * 
	 * @param s
	 *            原始字符串
	 * @param srcEncoding
	 *            源编码方式
	 * @param destEncoding
	 *            目标编码方式
	 * @return 转换编码后的字符串，失败返回原始字符串
	 */
	public static String getString(String s, String srcEncoding,
			String destEncoding) {
		try {
			return new String(s.getBytes(srcEncoding), destEncoding);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, String encoding) {
		try {
			return new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param offset
	 *            要转换的起始位置
	 * @param len
	 *            要转换的长度
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len,
			String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * @param ip
	 *            ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuffer sb = new StringBuffer();
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}

	/**
	 * 字符窜转换数字
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String str, int defaultValue) {
		int result = defaultValue;
		try {
			// 不是空转换int
			if (StringUtils.isNotBlank(str)) {
				result = Integer.parseInt(str.trim());
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 字符窜转换数字
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static long getLong(String str, long defaultValue) {
		long result = defaultValue;
		try {
			// 不是空转换int
			if (StringUtils.isNotBlank(str)) {
				result = Long.parseLong(str.trim());
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 时间格式转换
	 * 
	 * @param 传入时间
	 * @param 想要的时间格式
	 *            ，例如yyyy-MM-dd HH:mm:ss
	 * @return 返回时间字符串
	 */
	public static String FormatDate(Date date, String formatstr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatstr);
		return dateFormat.format(date);
	}

	/**
	 * 时间格式转换
	 * 
	 * @param date
	 * @param formatstr
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String FormatDateDefault(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 获得首字拼音 英文字母返回对应的大写字母 其他非简体汉字返回 '0'
	 * 
	 * @param str
	 * @return
	 * @author 刘施洁 2011-9-22 上午09:33:12 String
	 */
	// public static String getFirstPy(String str)
	// {
	// String spell = Spell.getPyByFirstWord(str);
	// if (spell != null && spell.length() > 1)
	// {
	// spell = spell.substring(0, 1);
	// }
	// return spell;
	// }

	/**
	 * 格式化Sql危险字符，类型：1普通字符串，2Like字符串，3Contains字符串
	 * 
	 * @param str
	 *            格式化字符串
	 * @param type
	 *            类型：1普通字符串，2Like字符串，3Contains字符串
	 * @author
	 * @version 1.0 2011-10-09
	 * @return 返回格式化后的字符串
	 */
	public static String SqlStringFormat(String str, int type) {
		// 普通
		if (type == 1) {
			str = str.replace("'", "''");
		}
		// 2Like
		else if (type == 2) {
			str = str.replace("[", "[[]");
			str = str.replace("%", "[%]");
			str = str.replace("_", "[_]");
			str = str.replace("'", "''");
		}
		// 3Contains
		else if (type == 3) {
			str = str.replace("[", "[[]");
			str = str.replace("%", "[%]");
			str = str.replace("_", "[_]");
			str = str.replace("'", "''");
		} else {
			str = str.replace("'", "''");
		}

		return str;
	}

	/**
	 * 截取字符串，包含中文
	 * 
	 * @return
	 */
	public final static String CutTrueLength(String strContent,
			int maxTrueLength) {
		String strNew = strContent;

		if (strContent == null || maxTrueLength <= 0) {
			strNew = "";

			return strNew;
		}

		int trueLen = TrueLength(strContent);

		if (trueLen > maxTrueLength) {
			for (int i = strContent.length() - 1; i > 0; i--) {
				strNew = strNew.substring(0, i);

				if (TrueLength(strNew) == maxTrueLength) {
					break;
				} else if (TrueLength(strNew) < maxTrueLength) {
					strNew += "";
					break;
				}
			}
		}

		return strNew + "...";
	}

	/**
	 * 字符串长度，包含中文
	 * 
	 * @param strContent
	 * @return
	 */
	public final static int TrueLength(String strContent) {
		int lengthTotal = 0;// 长度总计

		int n = strContent.length();

		String strWord = "";

		int asc;

		for (int i = 0; i < n; i++) {
			strWord = strContent.substring(i, i + 1);
			asc = strWord.charAt(0);
			if (asc < 0 || asc > 127)
				lengthTotal = lengthTotal + 2;
			else
				lengthTotal = lengthTotal + 1;
		}

		return lengthTotal;
	}

	/**
	 * 将日期转换为String
	 * 
	 * @param sStr
	 * @param sFormat
	 * @return
	 */
	public final static String DateConver(Date sStr, String sFormat) {
		String dReturnCode = null;
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		try {
			dReturnCode = sdf.format(sStr);
		} catch (Exception ex) {

		}
		return dReturnCode;
	}

	/**
	 * 字符串编码
	 * 
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public final static String UrlEncoder(String sStr, String sEnc) {
		String sReturnCode = "";
		try {
			sReturnCode = URLEncoder.encode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {

		}
		return sReturnCode;
	}

	/**
	 * 字符串解码
	 * 
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public final static String UrlDecoder(String sStr, String sEnc) {
		String sReturnCode = "";
		try {
			sReturnCode = URLDecoder.decode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {

		}
		return sReturnCode;
	}

	/**
	 * 对内容进行过滤
	 * 
	 * @param content
	 * @return
	 */
	public final static String ContentFiltering(String content) {
		if (content != null) {

			String regEx = "<.+?>"; // 表示标签
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(content);
			content = matcher.replaceAll("");

			return content;

		} else {
			return null;
		}
	}

	/**
	 * 模糊格式数组 12345转成1万
	 * 
	 * @author 刘施洁
	 * @2011-12-1下午01:12:36
	 * @param num
	 * @return
	 */
	public static String blurFormatLargeNum(long num) {
		String result = "";
		if (num > 100000000) {
			result = (int) (num / 100000000) + "亿";
		} else if (num > 10000) {
			result = (int) (num / 10000) + "万";
		} else {
			result = num + "";
		}
		return result;
	}

	/**
	 * 格式HTML
	 * 
	 * @param html
	 * @return
	 */
	public static String formatHTML(String html) {
		String str = html;
		try {
			str = html.replaceAll("<.*>", "").replaceAll("&nbsp;", "")
					.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll(
							"\\t", "");
		} catch (Exception e) {
		}
		return str;
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replaceAll("-", "");
	}

	public static String getRandomNum(int length) {
		try {
			if (length <= 0) {
				return "";
			}
			Random r = new Random();
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < length; i++) {
				result.append(Integer.toString(r.nextInt(10)));
			}
			return result.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
