package com.ptpl.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huishang.file.FileTransferDemoEncrypted;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

public class MyTest {

	public static void main(String[] args) {
		/*
		Pattern p = Pattern.compile("^\\S+");
		Matcher m = p.matcher("124124 1212");
		System.out.println(m.find());
		System.out.println(m.group());
		System.out.println(m.find());
		System.out.println(m.group());
		System.out.println(m.find());
		System.out.println(m.group());
		*/
		System.out.println("***************************");
		Pattern p = Pattern.compile("([a-z]+)([0-9]+)|([0-9]+)([a-z]+)");
		Matcher m = p.matcher("abc242--254rhr");
		while(m.find()){
			System.out.println(m.group());
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
		}
		 //System.out.println(AES.getEncrypt("13266653525"));
//dasfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		/*
		 * BacthFileRecord bacthFileRecord = new BacthFileRecord();
		 * bacthFileRecord.setFileType((short)5); //文件类型 5交易流水明细
		 * bacthFileRecord.setFilePath( "/bacthfile/800114/eve/20160510");//文件路径
		 * //bacthFileRecord.setSendFileName( "3004-EVE0054-20160510");
		 * bacthFileRecord.setGetFileName( "3004-EVE0054-20160510");//下载文件名称
		 * bacthFileRecord.setCoinstCode("800114");
		 * bacthFileRecord.setPName("干将P2P平台");
		 * bacthFileRecord.setIsSend((short)1);//是否发送银行
		 * bacthFileRecord.setIsDealResult((short)0);
		 */
			/*
		FileTransferDemoEncrypted file = new FileTransferDemoEncrypted();
		try {
			file.testHttDownload();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		// try {
		// reader();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
	

	public static void reader() throws IOException {
		File file = new File("D:/bacthfile/800114/ALEVEL/20160510", "3004-ALEVE0054-20160510");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
		String line = null;
		while ((line = br.readLine()) != null) {
			String bank = line.substring(0, 4).trim();// 银行号
			String cardnbr = line.substring(4, 23).trim();// 电子账号;
			String amount = line.substring(23, 40).trim();// 交易金额
			String cur_num = line.substring(40, 43).trim();// 货币代码
			String crflag = line.substring(43, 44).trim();// 交易金额符号
			String valdate = line.substring(44, 52).trim();// 入账日期
			String inpdate = line.substring(52, 60).trim();// 交易日期
			String reldate = line.substring(60, 68).trim();// 自然日期
			String inptime = line.substring(68, 76).trim();// 交易时间
			String tranno = line.substring(76, 82).trim();// 交易流水号
			String oriTranno = line.substring(82, 88).trim();// 关联交易流水号
			String transtype = line.substring(88, 92).trim();// 交易类型
			String desline = line.substring(92, 134).trim();// 交易描述
			String currBal = null;
			String forcardnbr = null;
			String revind = null;
			String resv = null;
			int index = desline.indexOf("0");
			if (index == -1) {
				currBal = line.substring(134, 151).trim();// 交易后余额
				forcardnbr = line.substring(151, 170).trim();// 对手交易账号
				revind = line.substring(170, 171).trim();// 冲正，撤销标志
				resv = line.substring(171, line.length()).trim();// 保留域
			} else {
				desline = desline.substring(0, index);
				index += 92;
				currBal = line.substring(index, index + 17).trim();// 交易后余额
				index += 17;
				forcardnbr = line.substring(index, index + 19).trim();// 对手交易账号
				index += 19;
				revind = line.substring(index, index + 1).trim();// 冲正，撤销标志
				index += 1;
				resv = line.substring(index, line.length()).trim();// 保留域
			}

			System.out.println("银行号=" + bank);
			System.out.println("电子账号=" + cardnbr);
			System.out.println("交易金额=" + amount);
			System.out.println("货币代码=" + cur_num);
			System.out.println("交易金额符号=" + crflag);
			System.out.println("入账日期=" + valdate);
			System.out.println("交易日期=" + inpdate);
			System.out.println("自然日期=" + reldate);
			System.out.println("交易时间=" + inptime);
			System.out.println("交易流水号=" + tranno);
			System.out.println("关联交易流水号=" + oriTranno);
			System.out.println("交易类型=" + transtype);
			System.out.println("交易描述=" + desline);
			System.out.println("交易后余额=" + currBal);
			System.out.println("对手交易账号=" + forcardnbr);
			System.out.println("冲正，撤销标志=" + revind);
			System.out.println("保留域=" + resv);
			System.out.println("===============================================");
		}
		br.close();
	}

	public static void bankCards() {
		/*
		 * 银行卡号格式转换
		 */
		String str = "6211024402028213449";
		StringBuilder sb = new StringBuilder(str);
		int length = str.length() / 4 + str.length();
		for (int i = 0; i < length; i++) {
			if (i % 5 == 0) {
				sb.insert(i, " ");
			}
		}
		sb.deleteCharAt(0);
		System.out.println(sb.toString());
	}

	public static void testSystemInfo() {
		SystemInfo syso = SystemInfo.getInstance();
		System.out.println("IP地址:" + syso.getIP());
		System.out.println("主机名称:" + syso.getHostName());
		System.out.println("获取C盘卷 序列号" + syso.getDiskNumber());
		System.out.println("Mac地址" + syso.getMac());
		System.out.println("获取当前系统名称" + syso.getSystemName());
	}

}
