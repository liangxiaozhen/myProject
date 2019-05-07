package com.ptpl.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Decoder;

/**
 * 文件
 * 
 */
public class FileHelper
{
	/**
	 * 上传文件(-1.错误 -2.文件大小错误)
	 * 
	 * @param files
	 * @param sRealPath
	 * @param sNewName
	 * @param isjpg
	 * @return
	 */
	public static String uploadFile(File file, String sRealPath, String sNewName)
	{
		String sReturnCode = "";
		try
		{
			InputStream stream = FileHelper.class.getClassLoader().getResourceAsStream("/ptpl.properties");
			Properties p = new Properties();
			p.load(stream);
			
			if (file != null)
			{
				File savedir = new File(sRealPath);
				if (!savedir.exists())
				{
					savedir.mkdirs();
				}
				// 检测文件是否存在和大小
				// long s = getFileSize(file);
				// long filesize = GlobalConstant.FILESIZE;
				
				// if (s < 0 || s > filesize)
				// {
				// sReturnCode.clear();
				// sReturnCode.add("-2");
				// return sReturnCode;
				// }
				
				File newf = new File(savedir, sNewName);
				// String sExt = getFormatName(oldf);
				// if (sExt == null || sExt.isEmpty())
				// {
				// sExt = "jpg";
				// }
				// sExt = sExt.toLowerCase();
				FileUtils.copyFile(file, newf);
				sReturnCode = newf.getName();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			sReturnCode = "-1";
		}
		return sReturnCode;
	}
	
	/**
	 * 下载文件
	 * 
	 * @param oldpath
	 * @param newpath
	 */
	public static void download(String oldpath, String newpath)
	{
		try
		{
			URL url = new URL(oldpath);
			File outFile = new File(newpath);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true)
			{
				int readed = is.read(buff);
				if (readed == -1)
				{
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static boolean delFile(String path)
	{
		boolean flag = false;
		try
		{
			File file = new File(path);
			if (file.exists())
			{
				if (file.isFile())
				{
					file.delete();
				}
			}
			flag = true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static boolean delFile(File file)
	{
		boolean flag = false;
		try
		{
			if (file.exists())
			{
				if (file.isFile())
				{
					file.delete();
				}
			}
			flag = true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取文件类型
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getFormatName(Object o)
	{
		try
		{
			ImageInputStream iis = ImageIO.createImageInputStream(o);
			// 蔡威明 2011-10-26,如果iis为null，返回null
			if (iis == null)
			{
				return null;
			}
			Iterator iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext())
			{
				return null;
			}
			ImageReader ir = (ImageReader) iter.next();
			iis.close();
			return ir.getFormatName().trim();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 读取文件
	 * 
	 * @param 文件名
	 * @param 编码格式
	 * @return
	 */
	public static String read(String fileName, String encoding)
	{
		StringBuffer fileContent = new StringBuffer();
		try
		{
			// FileReader fr = new FileReader(fileName);
			// BufferedReader br = new BufferedReader(fr);
			
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileName), "GBK");
			BufferedReader reader = new BufferedReader(read);
			
			String str = "";
			while ((str = reader.readLine()) != null)
			{
				fileContent.append(str);
				fileContent.append(System.getProperty("line.separator"));
				// String[] strArray = str.split(",");
				// for (String s : strArray) {
				// System.out.println(s);
				// }
			}
			reader.close();
			read.close();
			// return builder.toString();
			// FileInputStream fis = new FileInputStream(fileName);
			// InputStreamReader isr = new InputStreamReader(fis, encoding);
			// BufferedReader br = new BufferedReader(isr);
			// String line = null;
			// while ((line = br.readLine()) != null) {
			// fileContent.append(line);
			// fileContent.append(System.getProperty("line.separator"));
			// }
			// fr.close();
			// br.close();
			// reader.close();
			// input.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fileContent.toString();
	}
	
	/**
	 * 写入文件
	 * 
	 * @param 写入文件类容
	 * @param 文件名称
	 * @param 编码格式
	 */
	public static void write(String fileContent, String fileName, String encoding)
	{
		try
		{
			// File fd = new File(GlobalConstant.EMAILFILE_PATH);
			// if (!fd.exists())
			// {
			// fd.mkdirs();
			// }
			// FileWriter fw = null;
			// fw = new FileWriter(fileName);
			// fw.write(fileContent);
			// fw.close();
			// FileOutputStream fos = new FileOutputStream(fileName);
			//
			// OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
			// osw.write(fileContent);
			// osw.flush();
			// osw.close();
			// fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得文件大小
	 * 
	 * @param file
	 * @return long
	 */
	public static long getFileSize(File f)
	{
		long s = -1;
		try
		{
			if (f != null && f.exists() && f.isFile())
			{
				FileInputStream fis = new FileInputStream(f);
				s = fis.available();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 将接收的字符串转换成图片保存
	 * 
	 * @param imgStr
	 *            二进制流转换的字符串
	 * @param imgPath
	 *            图片的保存路径
	 * @param imgName
	 *            图片的名称
	 * @return 1：保存正常 0：保存失败
	 */
	public static int saveToImgByStr(String imgStr, String imgPath, String imgName)
	{
		int stateInt = 1;
		try
		{
			// System.out.println("===imgStr.length()====>" + imgStr.length() + "=====imgStr=====>" + imgStr);
			File savedir = new File(imgPath);
			if (!savedir.exists())
			{
				savedir.mkdirs();
			}
			if (imgStr != null && imgStr.length() > 0)
			{
				// 将字符串转换成二进制，用于显示图片
				// 将上面生成的图片格式字符串 imgStr，还原成图片显示
				byte[] imgByte = hex2byte(imgStr);
				
				InputStream in = new ByteArrayInputStream(imgByte);
				
				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);
				
				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1)
				{
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();
			}
		}
		catch (Exception e)
		{
			stateInt = 0;
			e.printStackTrace();
		}
		
		return stateInt;
	}
	
	/**
	 * 将图片转换成字符串
	 * 
	 * @param imgPath
	 * @param imgName
	 * @return
	 */
	public static String saveToStrByImg(File file)
	{
		String result = "";
		try
		{
			byte[] by = saveToBytesByImg(file);
			result = byte2hex(by);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 字符串转二进制
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后的二进制数组
	 */
	public static byte[] hex2byte(String str)
	{ // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try
		{
			for (int i = 0; i < str.length(); i += 2)
			{
				b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 二进制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b)
	{
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
			{
				sb.append("0" + stmp);
			}
			else
			{
				sb.append(stmp);
			}
			
		}
		return sb.toString();
	}
	
	/**
	 * 将图片转换成二进制
	 * 
	 * @param imgPath
	 * @param imgName
	 * @return
	 */
	public static byte[] saveToBytesByImg(File file)
	{
		byte[] by = null;
		try
		{
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			by = new byte[fis.available()];
			bis.read(by);
			fis.close();
			bis.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return by;
	}
	
	/**
	 * 将二进制转换成图片保存
	 * 
	 * @param imgStr
	 *            二进制流转换的字符串
	 * @param imgPath
	 *            图片的保存路径
	 * @param imgName
	 *            图片的名称
	 * @return 1：保存正常 0：保存失败
	 */
	public static int saveToImgByBytes(File imgFile, String imgPath, String imgName)
	{
		
		int stateInt = 1;
		if (imgFile.length() > 0)
		{
			try
			{
				File savedir = new File(imgPath);
				if (!savedir.exists())
				{
					savedir.mkdirs();
				}
				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);
				
				FileInputStream fis = new FileInputStream(imgFile);
				
				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = fis.read(b)) != -1)
				{
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				fis.close();
				
			}
			catch (Exception e)
			{
				stateInt = 0;
				e.printStackTrace();
			}
			finally
			{
			}
		}
		return stateInt;
	}
	
	/**
	 * 将接收的Base64编码图片字符串转换成图片保存
	 * 
	 * @param imgStr
	 *            Base64编码图片字符串
	 * @param imgPath
	 *            图片的保存路径
	 * @param imgName
	 *            图片的名称
	 * @return 1：保存正常 0：保存失败
	 */
	public static int saveToImgByBase64(String imgStr, String imgPath, String imgName)
	{
		int stateInt = 1;
		try
		{
			// System.out.println("===imgStr.length()====>" + imgStr.length() + "=====imgStr=====>" + imgStr);
			File savedir = new File(imgPath);
			if (!savedir.exists())
			{
				savedir.mkdirs();
			}
			if (imgStr != null && imgStr.length() > 0)
			{
				imgStr = imgStr.replaceAll("\u0020","");
				imgStr = imgStr.replaceAll("\r\n","");
				// 将Base64字符串转换成二进制，用于显示图片
				// 将上面生成的图片格式字符串 imgStr，还原成图片显示
				byte[] imgByte = fromBase64(imgStr);
				
				InputStream in = new ByteArrayInputStream(imgByte);
				
				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);
				
				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1)
				{
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();
			}
		}
		catch (Exception e)
		{
			stateInt = 0;
			e.printStackTrace();
		}
		
		return stateInt;
	}
	
	/**
	 * 将接收的Base64编码图片字符串转换成图片保存
	 * 
	 * @param imgStr
	 *            Base64编码图片字符串
	 * @param imgPath
	 *            图片的保存路径
	 * @param imgName
	 *            图片的名称
	 * @return 1：保存正常 0：保存失败
	 */
	public static byte[] saveToImgByBase64_2(String imgStr, String imgPath, String imgName)
	{
		try
		{
			// System.out.println("===imgStr.length()====>" + imgStr.length() + "=====imgStr=====>" + imgStr);
			File savedir = new File(imgPath);
			if (!savedir.exists())
			{
				savedir.mkdirs();
			}
			if (imgStr != null && imgStr.length() > 0)
			{
				imgStr = imgStr.replaceAll("\u0020","");
				imgStr = imgStr.replaceAll("\r\n","");
				// 将Base64字符串转换成二进制，用于显示图片
				// 将上面生成的图片格式字符串 imgStr，还原成图片显示
				byte[] imgByte = fromBase64(imgStr);
				
				InputStream in = new ByteArrayInputStream(imgByte);
				
				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);
				
				byte[] b = new byte[1024];
				byte[] bytecontent = new byte[in.available()];
				int sumlength=0;
				int nRead = 0;
				while ((nRead = in.read(b)) != -1)
				{
					fos.write(b, 0, nRead);
					System.arraycopy(b, 0, bytecontent, sumlength, nRead);
					sumlength += nRead;
				}
				fos.flush();
				fos.close();
				in.close();
				
				return bytecontent;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	* @param p_Str Base64编码的字符窜
	* @return byte[]类型的照片信息
	* @throws IOException
	*/
	public  static byte[] fromBase64(String p_Str)  throws  IOException 
	{
		byte[] byteBuffer =  new  BASE64Decoder().decodeBuffer(p_Str);
		return byteBuffer;
	}
}
