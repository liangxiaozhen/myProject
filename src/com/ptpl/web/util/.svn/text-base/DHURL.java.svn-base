package com.ptpl.web.util;

import java.net.*;
import java.io.*;
import javax.activation.DataHandler;

/**
 * 
 * 获取网站页面信息
 * 
 */
public class DHURL {
	URL url = null;
	DataHandler dh = null;

	public static void main(String args[]) {
		DHURL test = new DHURL();
		String strUrl = "https://www.tuandai.com/member/Validate/index.aspx?method=identity#s_identity";
		test.setURL(strUrl);
		try {
			test.doit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setURL(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("这不是一个正确的URL。");
			System.exit(1);
		}
	}

	public void doit() throws IOException {

		System.out.print("DataHandler信息创建中....");
		dh = new DataHandler(url);
		System.out.println("...读取并创建完成.");
		System.out.println("这个文件的MimeType信息是: " + dh.getContentType());
		System.out.println("这个文件的名称是 : " + dh.getName());
		try {
			InputStream is = dh.getInputStream();
			byte[] data = new byte[1024];
			int count = -1;
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			if (is != null) {
				while ((count = is.read(data, 0, 1024)) != -1) {
					outStream.write(data, 0, count);
				}
				System.out.println(new String(outStream.toByteArray(), "UTF-8"));// 这个编码你要根据文件的编码信息来哦，要不可能会出现乱码。
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
