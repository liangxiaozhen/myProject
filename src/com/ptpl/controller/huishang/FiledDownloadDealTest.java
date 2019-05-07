package com.ptpl.controller.huishang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.codehaus.jackson.util.BufferRecycler;

import com.huishang.util.AesUtil;
import com.huishang.util.HSignUtil;

/**
 * 
* @ClassName: FiledDownloadDealTest 
* @Description: TODO(文件处理 ) 
* @author cjm 
* @date 2017年4月18日 下午4:04:35 
*
 */
public class FiledDownloadDealTest {

	
	
	public void test() throws IOException{
		String path = File.separator;
		File file = new File("D:"+path+"DEMO"+path+"client"+path+"download"+path+"30040000"+path+"3004-APPZX0002RES-000006-20160510");
		System.out.println(file);
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		System.out.println(bufferedReader.readLine());
		char[] charer = new char[364];
		String line = null;
		while((line = bufferedReader.readLine()) != null){
			System.out.println(line);
			byte[] data = line.getBytes("GBK");
 			byte[] CARDNBR = new byte[19];
			System.arraycopy(data, 0, CARDNBR, 0, 19);
			System.out.println(new String(CARDNBR));
			
			byte[] IDNO  = new byte[18];
			System.arraycopy(data, 19, IDNO, 0, 18);
			System.out.println(new String(IDNO));
			
			byte[] IDTYPE  = new byte[2];
			System.arraycopy(data, 37, IDTYPE, 0, 2);
			System.out.println(new String(IDTYPE));
			
			byte[] FLAG  = new byte[1];
			System.arraycopy(data, 39, FLAG, 0, 1);
			System.out.println(new String(FLAG));
			
			byte[] ERRCODE  = new byte[3];
			System.arraycopy(data, 40, ERRCODE, 0, 3);
			System.out.println(new String(ERRCODE));
			
			byte[] NAME  = new byte[60];
			System.arraycopy(data, 43, NAME, 0, 60);
			System.out.println(new String(NAME,"GBK"));
			
			byte[] ACCTYPE  = new byte[1];
			System.arraycopy(data, 103, ACCTYPE, 0, 1);
			System.out.println(new String(ACCTYPE));
			
			byte[] APPID  = new byte[60];
			System.arraycopy(data, 104, APPID, 0, 60);
			System.out.println(new String(APPID));
			
			byte[] MOPHONE  = new byte[12];
			System.arraycopy(data, 164, MOPHONE, 0, 12);
			System.out.println(new String(MOPHONE));
			
			byte[] INFO  = new byte[100];
			System.arraycopy(data, 176, INFO, 0, 100);
			System.out.println(new String(INFO));
			
			byte[] REVERS  = new byte[88];
			System.arraycopy(data, 276, REVERS, 0, 88);
			System.out.println(new String(REVERS));
		}
 
   		bufferedReader.close();
  		inputStreamReader.close();
  		fileInputStream.close();
  		 
 	}
	
	public static void main(String[] args) throws IOException {
 		new FiledDownloadDealTest().test();
		
		
	}
}
