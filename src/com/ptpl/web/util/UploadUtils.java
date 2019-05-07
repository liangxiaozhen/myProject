package com.ptpl.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author T-08-D-120
 * 
 */
public class UploadUtils {
	private static Logger logger = Logger.getLogger(UploadUtils.class);
	private static Properties p = new Properties();
	static {
		InputStream stream = UploadUtils.class.getClassLoader()
				.getResourceAsStream("ptpl.properties");
		try {
			p.load(stream);
			stream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获得上传的根目录
	 * 
	 * @return
	 */
	public static String getUploadDir() {
		return p.getProperty("upload_dir");
	}

	/**
	 * 获得退款上传的根目录
	 * 
	 * @return
	 */
	public static String getRefundUploadDir() {
		return p.getProperty("refund_dir");
	}

	/**
	 * 获得退款上传的根目录
	 * 
	 * @return
	 */
	public static String getProcessTxnDir() {
		return p.getProperty("process_dir");
	}

	/**
	 * 获得上传图片的路径
	 * 
	 * @return
	 */
	public static String getUploadImgDir() {
		return p.getProperty("Img_dir");
		//return GlobalContansts.configFilePrefix("D:")+"/web/imgUpload";
	}

	/**
	 * 路径
	 * 
	 * @return
	 */
	public static String getPath(String pathname) {
		return p.getProperty(pathname);
	}

	/**
	 * 获得上传文件的相对路�?
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getUploadFileOpPath(String fileName) {
		return DateUtil.getDate() + "/" + fileName;
	}

	/**
	 * 网贷平台操作指令 签名key
	 * 
	 * @return
	 */
	public static String getLoanKey() {
		return p.getProperty("loan_key");
	}
	
	/**
	 * 网贷平台操作指令 签名peivatekey
	 * 
	 * @return
	 */
	public static String getLoanPrivateKey() {
		return p.getProperty("loan_private_key");
	}
	
}
