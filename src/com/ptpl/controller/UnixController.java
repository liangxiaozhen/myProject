package com.ptpl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;

public class UnixController{
	 static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	 static  Random rand=new Random();
     static int n=rand.nextInt(1000000);
     static String currentDateString = format.format(new Date());
	
	public static void main(String[] args) throws Exception {
		getUnixFile();
		UpFTP();
	}
	/**
	 * 将生成的文件上传到ftp服务器上
	 */
	public static void UpFTP(){
		    File imagefile = new File("F:\\"+"3004-APPZX0001-"+n+"-"+currentDateString);//指定的文件
			//创建ftp客户端
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("GBK");
			String hostname = "106.14.80.70";
			int port = 21;			
			String username = "gjftp";
			String password = "gjftp123123";
			try {
				//链接ftp服务器
				ftpClient.connect(hostname, port);
				//登录ftp
				ftpClient.login(username, password);
				int  reply = ftpClient.getReplyCode();  //返回的是ftp的状态码
				System.out.println("返回的状态码："+reply);
				
				//如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限下面有详细的解释。
				if (!FTPReply.isPositiveCompletion(reply)) { 
		            	ftpClient.disconnect();  
		                return ;  
		            }  
			        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//指定以什么形式传输
			        ftpClient.changeWorkingDirectory("/ftp/ptpapi");//指定上传ftp的目录
		            InputStream input = new FileInputStream(imagefile); //获取文件流
		            ftpClient.storeFile("3004-APPZX0001-"+n+"-"+currentDateString, input);//你指定的文件的名字 和输入流 上传
		            input.close();  
		            ftpClient.logout(); 
		    } catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally 
			{  
	            if (ftpClient.isConnected())
	            {  
	                try 
	                {  
	                	ftpClient.disconnect();  
	                } catch (IOException ioe) 
	                {  
						ioe.printStackTrace();
	                }  
	            } 
		
			}
	}
	/**
	 * 直接生成为unix格式   和windows格式 区别是 每一行结束是只有一个换行符  而windows有一个换行符和一个enter符号
	 * @author Administrator
	 *
	 */
	private static void getUnixFile() throws IOException, FileNotFoundException, UnsupportedEncodingException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserBaseAccountInfoServiceI bean = (UserBaseAccountInfoServiceI) context.getBean("userBaseAccountInfoService");
		List<UserBaseAccountInfo> list=bean.getBaseInfoAll();
		
        System.out.println("3004-APPZX0001-"+n+"-"+currentDateString);
        String fileName="3004-APPZX0001-"+n+"-"+currentDateString;
		File file=new File("f:\\"+fileName);
		if(!file.exists()){//创建文件
			file.createNewFile();
		}
		FileOutputStream fos=new FileOutputStream(file,true);//防止被覆盖
		
		 for (UserBaseAccountInfo userBaseAccountInfo : list) {
			 if(userBaseAccountInfo.getCertificationnumber()!=null&&userBaseAccountInfo.getCertificationnumber().length()>=15){
				 /**
				  * 定义字节数组
				  */
				 byte[] accountNum=new byte[18];//证件编号
				 byte[] IDType=new byte[2];//证件类型
				 byte[] name=new byte[60];//中文姓名
				 byte[] sex=new byte[1];//性别
				 byte[] phone=new byte[12];//手机号码
				 byte[] accountType=new byte[1];//账户类型
				 byte[] email=new byte[40];//email
				 byte[] appid=new byte[60];//请求方用户id
				 byte[] instid=new byte[9];//组织机构代码
				 byte[] taxid=new byte[30];//税务登记号
				 byte[] adno=new byte[20];//渠道推荐码
				 byte[] acc_type=new byte[1];//账户类型2
				 byte[] fucomcode=new byte[2];//基金公司代码
				 byte[] info=new byte[100];//请求方保留信息
				 byte[] account=new byte[32];//对公帐户号
				 byte[] busid=new byte[18];//营业执照编号
				 byte[] revers=new byte[27];//保留域
				 /**
				  * 定义变量
				  */
				StringBuffer sbAccountNum=new StringBuffer(userBaseAccountInfo.getCertificationnumber().toString());
				StringBuffer sbIDType=new StringBuffer();
				StringBuffer sbname=new StringBuffer(userBaseAccountInfo.getRealname().toString());
				StringBuffer sbsex=new StringBuffer("1");//性别默认为男  1
				StringBuffer sbphone=new StringBuffer(userBaseAccountInfo.getMobilephone().toString());
				StringBuffer sbaccountType=new StringBuffer(userBaseAccountInfo.getAccounttype().toString());
				StringBuffer sbemail=new StringBuffer();
				if(userBaseAccountInfo.getEmail()!=null){
					sbemail.append(userBaseAccountInfo.getEmail());
				}
				StringBuffer sbappid=new StringBuffer(userBaseAccountInfo.getId().toString());
				StringBuffer sbInstid=new StringBuffer();
				StringBuffer sbtaxid=new StringBuffer();
				StringBuffer sbadno=new StringBuffer();
				StringBuffer sbacc_type=new StringBuffer();
				StringBuffer sbfucomcode=new StringBuffer();
				StringBuffer sbinfo=new StringBuffer();
				StringBuffer sbaccount=new StringBuffer();
				StringBuffer sbbusid=new StringBuffer();
				StringBuffer sbrevers=new StringBuffer();
				
				/**
				 * 加入字节数组
				 */
				accountNum=sbAccountNum.toString().getBytes("GBK"); //将证件编号加入第一个自己数组
				if(userBaseAccountInfo.getCertificationnumber().length()==18){  
					sbIDType.append("01");
				}else if(userBaseAccountInfo.getCertificationnumber().length()==15){
					sbIDType.append("02");
				}else{
					sbIDType.append("20");
				}
				IDType=sbIDType.toString().getBytes("GBK");//根据证件编号写入证件类型在第二个字节数组
				name=sbname.toString().getBytes("GBK");//将真实姓名写入第三个字节数组
				System.out.println("真实姓名长度:"+name.length);
			    sex=sbsex.toString().getBytes("GBK");
				phone=sbphone.toString().getBytes("GBK");
				accountType=sbaccountType.toString().getBytes("GBK");
				email=sbemail.toString().getBytes("GBK");
				appid=sbappid.toString().getBytes("GBK");
				/**
				 * 拼接
				 */
			 int accountNumLen=accountNum.length;
			 int IDTypeLen=IDType.length;
			 int nameLen=name.length;
			 int sexLen=sex.length;
			 int phoneLen=phone.length;
			 int accountTypeLen=accountType.length;
			 int emailLen=email.length;
			 int appidLen=appid.length;
			 //证件编号
			 /**
			  * 写入的时候为了不让后面的将数组长度覆盖以空格填充
			  */
			 System.out.println("起始长度："+accountNumLen);
			 for (int i = accountNumLen; i <=18; i++) {
					if(accountNumLen<18){
						sbAccountNum.append(" ");
						accountNumLen++;
					} 
				}
			 System.out.println(sbAccountNum.length());
			 accountNum=sbAccountNum.toString().getBytes("GBK"); //将证件编号加入第一个自己数组
			 //中文姓名
			 System.out.println("名字长度："+nameLen);
			  for (int i = nameLen; i <= 60; i++) {
					 if(nameLen<60){
					 sbname.append(" ");
					 nameLen++;
				}
			 }
				 System.out.println("名字长度："+sbname.length());
				 name=sbname.toString().getBytes("GBK");//将真实姓名写入第三个字节数组
				 System.out.println("追加后的长度："+name.length);
			
			 //手机号码
			 for (int i = phoneLen; i <= 12; i++) {
					if(phoneLen<12){
						sbphone.append(" ");
						phoneLen++;
					} 
				}
			 phone=sbphone.toString().getBytes("GBK");
			 //email
			 for (int i = emailLen; i <= 40; i++) {
					if(emailLen<40){
						sbemail.append(" ");
						emailLen++;
					} 
				}
			 System.out.println("email:"+sbemail.length());
			 email=sbemail.toString().getBytes("GBK");
			 //请求方id
			 for (int i = appidLen; i <= 60; i++) {
					if(appidLen<60){
						sbappid.append(" ");
						appidLen++;
					} 
				}
			 appid=sbappid.toString().getBytes("GBK");
			if(sbInstid.toString().equals("")){
				for (int i = 1; i <= 9; i++) {
					sbInstid.append(" ");
				}
			} 
			if(sbtaxid.toString().equals("")){
				for (int i = 1; i <= 30; i++) {
					sbtaxid.append(" ");
				}
			} 
			if(sbadno.toString().equals("")){
				for (int i = 1; i <= 20; i++) {
					sbadno.append(" ");
				}
			} 
			if(sbacc_type.toString().equals("")){
				for (int i = 1; i <= 1; i++) {
					sbacc_type.append(" ");
				}
			} 
			if(sbfucomcode.toString().equals("")){
				for (int i = 1; i <= 2; i++) {
					sbfucomcode.append(" ");
				}
			} 
			if(sbinfo.toString().equals("")){
				for (int i = 1; i <= 100; i++) {
					sbinfo.append(" ");
				}
			} 
			if(sbaccount.toString().equals("")){
				for (int i = 1; i <= 32; i++) {
					sbaccount.append(" ");
				}
			} 
			if(sbbusid.toString().equals("")){
				for (int i = 1; i <= 18; i++) {
					sbbusid.append(" ");
				}
			} 
			if(sbrevers.toString().equals("")){
				for (int i = 1; i <= 27; i++) {
					sbrevers.append(" ");
				}
			} 
			/*System.out.println(sbInstid.toString().length());
			System.out.println(sbtaxid.toString().length());
			System.out.println(sbadno.toString().length());
			System.out.println(sbacc_type.toString().length());
			System.out.println(sbfucomcode.toString().length());
			System.out.println(sbinfo.toString().length());
			System.out.println(sbaccount.toString().length());
			System.out.println(sbbusid.toString().length());
			System.out.println(sbrevers.toString().length());*/
	// System.out.println("----->accountNum:"+accountNum.length+" sbIDType:"+IDType.length+" sbname:"+name.length+" sbsex:"+sex.length+" sbphone："+phone.length+" sbaccountType:"+accountType.length+" sbemail:"+email.length+" sbappid:"+appid.length);
	// System.out.println("开始："+sbname.toString()+"...");
	 // System.out.println("开始："+sbemail.toString()+"...");
	// System.out.println(sbsex.toString());
			// System.out.println(sbAccountNum.toString()+sbIDType.toString()+sbname.toString());
	 // System.out.println("写入的数据："+sbAccountNum.toString()+sbIDType.toString()+sbname.toString()+sbsex.toString()+sbphone.toString()+sbaccountType.toString()+sbemail.toString()+sbappid.toString()+sbInstid.toString()+sbtaxid.toString()+sbadno.toString()+sbacc_type.toString()+sbfucomcode.toString()+sbinfo.toString()+sbaccount.toString()+sbbusid.toString()+sbrevers.toString()+"结束 ");
	        /**
	         * 写入
	         */
			int accNum=accountNum.length;
	        int idNum=IDType.length;
	        int nameNum=name.length;
	        int sexNum=sex.length;
	        int phoNum=phone.length;
	        int accTypeNum=accountType.length;
	        int emailNum=email.length;
	        int appNum=appid.length;
	        int instiNum=sbInstid.toString().length();
	        int sbtaxidNum=sbtaxid.toString().length();
	        int sbadnoNum=sbadno.toString().length();
	        int sbacc_typeNum=sbacc_type.toString().length();
	        int sbfucomcodeNum=sbfucomcode.toString().length();
	        int sbinfoNum=sbinfo.toString().length();
	        int sbaccountNum=sbaccount.toString().length();
	        int sbbusidNum=sbbusid.toString().length();														
	        int sbreversNum=sbrevers.toString().length();
			 fos.write(sbAccountNum.toString().getBytes("GBK"),0,accNum);
	         fos.write(sbIDType.toString().getBytes("GBK"),0,idNum);
	         fos.write(sbname.toString().getBytes("GBK"),0,nameNum);
	         fos.write(sbsex.toString().getBytes("GBK"),0,sexNum);
	         fos.write(sbphone.toString().getBytes("GBK"),0,phoNum);
	         fos.write(sbaccountType.toString().getBytes("GBK"),0,accTypeNum);
	         fos.write(sbemail.toString().getBytes("GBK"),0,emailNum);
	         fos.write(sbappid.toString().getBytes("GBK"),0,appNum);
	         fos.write(sbInstid.toString().getBytes("GBK"),0,instiNum);
	         fos.write(sbtaxid.toString().getBytes("GBK"),0,sbtaxidNum);
	         fos.write(sbadno.toString().getBytes("GBK"),0,sbadnoNum);
	         fos.write(sbacc_type.toString().getBytes("GBK"),0,sbacc_typeNum);
	         fos.write(sbfucomcode.toString().getBytes("GBK"),0,sbfucomcodeNum);
	         fos.write(sbinfo.toString().getBytes("GBK"),0,sbinfoNum);
	         fos.write(sbaccount.toString().getBytes("GBK"),0,sbaccountNum);
	         fos.write(sbbusid.toString().getBytes("GBK"),0,sbbusidNum);
	         fos.write(sbrevers.toString().getBytes("GBK"),0,sbreversNum);
	         fos.write('\n');
	       
	         }
			 
	}
		 fos.close();//关闭io流
	} 
}
