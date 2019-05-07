package com.ptpl.web.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *注:通过IP地址库获取IP位置
 *1. 请求接口（GET）：http://ip.taobao.com/service/getIpInfo.php?ip=[ip地址字串]
 *2. 响应信息：（json格式的）国家 、省（自治区或直辖市）、市（县）、运营商
 *3. 返回数据格式：
 *{"code":0,"data":{"ip":"219.133.11.139","country":"\u4e2d\u56fd","area":"\u534e\u5317",
 *"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
 *"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
 *"county_id":"-1","isp_id":"100017"}}
 *其中code的值的含义为，0：成功，1：失败。 
 */

/**
 * 根据IP地址获取详细的地域信息
 * 
 * @project:personGocheck
 * @class:AddressUtils.java
 * @author:liusheng 
 * @date：2016/11/2 9:50
 */
public class AddressUtils 
{
	/**
	 * 
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAddresses(String content, String encodingString) throws UnsupportedEncodingException 
	{
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = this.getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			// 处理返回的省市区信息
			//json形式返回的格式
			//System.out.println(returnStr);
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				// 无效IP，局域网测试
				return "0";
			}
			String region = (temp[5].split(":"))[1].replaceAll("\"", "");
			// 省份
			region = decodeUnicode(region);

			String country = "";
			String area = "";
			// String region = "";
			String city = "";
			String county = "";
			String isp = "";
			for (int i = 0; i < temp.length; i++) {
				switch (i) {
				case 1:
					// 国家
					country = (temp[i].split(":"))[2].replaceAll("\"", "");
					country = decodeUnicode(country);
					break;
				case 3:
					// 片区
					area = (temp[i].split(":"))[1].replaceAll("\"", "");
					area = decodeUnicode(area);
					break;
				case 5:
					// 省份
					region = (temp[i].split(":"))[1].replaceAll("\"", "");
					region = decodeUnicode(region);
					break;
				case 7:
					// 市区
					city = (temp[i].split(":"))[1].replaceAll("\"", "");
					city = decodeUnicode(city);
					break;
				case 9:
					// 地区
					county = (temp[i].split(":"))[1].replaceAll("\"", "");
					county = decodeUnicode(county);
					break;
				case 11:
					// ISP公司
					isp = (temp[i].split(":"))[1].replaceAll("\"", "");
					isp = decodeUnicode(isp); 
					break;
				}
			}
			//输出结果用字符串拼接组合起来
			//输出结果:中国-->华南-->广东省-->深圳市-->罗湖区-->电信
			System.out.println(country + "-->" + area + "-->" + region + "-->" + city + "-->" + county + "-->" + isp);
			return region;
		}
		return null;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private String getResult(String urlStr, String content, String encoding) 
	{
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			// 新建连接实例
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接超时时间，单位毫秒
			connection.setConnectTimeout(2000);
			// 设置读取数据超时时间，单位毫秒
			connection.setReadTimeout(2000);
			// 是否打开输出流 true|false
			connection.setDoOutput(true);
			// 是否打开输入流true|false
			connection.setDoInput(true);
			// 提交方法POST|GET
			connection.setRequestMethod("POST");
			// 是否缓存true|false
			connection.setUseCaches(false);
			// 打开连接端口
			connection.connect();
			// 打开输出流往对端服务器写数据
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.writeBytes(content);
			// 刷新
			out.flush();
			// 关闭输出流
			out.close();
			// 往对端写完数据对端服务器返回数据,以BufferedReader流来读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				// 关闭连接
				connection.disconnect();
			}
		}
		return null;
	}

	/**
	 * unicode 转换成 中文
	 * 
	 * @author liusheng
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString)
	{
		//拼接
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

//	// 测试
//	public static void main(String[] args) {
//		//实例化AddressUtils
//		AddressUtils addressUtils=new AddressUtils();
//		//输入测试ip 220.168.56.127 为湖南省长沙市的ip
//		String ip="220.168.56.127";
//		String address="";
//		try {
//			//获得ip和返回的编码格式
//			address=addressUtils.getAddresses("ip=" + ip,  "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//能根据ip得到该用户的登录地址并输出
//		//System.out.println(address);
//	}
	
//	// 测试
//	public static void main(String[] args)
//	{
//		//实例化AddressUtils
//		AddressUtils addressUtils = new AddressUtils();
//		// 输入测试ip 219.133.11.139
//		String ip = "219.133.11.139";
//		String address = "";
//		try {
//			//获得ip和返回的编码格式
//			address = addressUtils.getAddresses("ip=" + ip, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//输出的省份
//		//System.out.println(address);
//	}
	
	// 测试
	public static void main(String[] args) {
		//实例化AddressUtils
		AddressUtils addressUtils=new AddressUtils();
		//输入测试ip 59.108.111.17 为北京海淀区ip
		String ip="59.108.111.17";
		String address="";
		try {
			//获得ip和返回的编码格式
			address=addressUtils.getAddresses("ip=" + ip,  "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//能根据ip得到该用户的登录地址并输出
		//System.out.println(address);
	}
}