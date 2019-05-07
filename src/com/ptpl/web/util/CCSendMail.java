
package com.ptpl.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





public class CCSendMail {
	/**
	 * 邮箱发送信息
	 */
	public static boolean setSendMail(String to_mail_address, String setText,String subjectText,
			                          String send_mail_address,String password,String smtp_server	) {
		boolean flag=false;
		try {
			String from_mail_address = send_mail_address;
			Authenticator auth = new PopupAuthenticator1(send_mail_address, password);
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", send_mail_address);
			mailProps.put("password", password);
			mailProps.put("mail.smtp.host", smtp_server);
			//Session mailSession = Session.getDefaultInstance(mailProps, auth);
			Session mailSession = Session.getInstance(mailProps, auth);  
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
			message.setSubject(subjectText);
			MimeMultipart multi = new MimeMultipart("related");
			BodyPart textBodyPart = new MimeBodyPart();
//			message.setDataHandler(new javax.activation.DataHandler(
//					new StringDataSource(setText, "text/html")));
			textBodyPart.setContent(setText,"text/html;charset=utf-8");

			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			message.saveChanges();
			Transport.send(message);
			flag = true;
		} catch (Exception ex) {
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体错误原因：");
			ex.printStackTrace(System.err);
		}
		return flag;
	}
	    
	/**
	 * 邮箱发送信息 带html格式
	 */
	public static boolean setSendMail2(String to_mail_address, String setText,String subjectText,
			                          String send_mail_address,String password,String smtp_server	) {
		boolean flag=false;
		try {
			String from_mail_address = send_mail_address;
			Authenticator auth = new PopupAuthenticator1(send_mail_address, password);
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", send_mail_address);
			mailProps.put("password", password);
			mailProps.put("mail.smtp.host", smtp_server);
			//Session mailSession = Session.getDefaultInstance(mailProps, auth);
			Session mailSession = Session.getInstance(mailProps, auth);  
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
			message.setSubject(subjectText);
			MimeMultipart multi = new MimeMultipart("related");
			BodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(setText, "text/html;charset=gbk");
			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			Transport.send(message);
			flag = true;
		} catch (Exception ex) {
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体错误原因：");
			ex.printStackTrace(System.err);
		}
		return flag;
	}
	 
	/**
	 * 邮箱发送信息
	 */
	/*public static boolean sendMail(String send_mail_address,String password,String smtp_server,
			String to_mail_address, String subjectText, String setText)
	{
		boolean flag = false;
		try
		{			
			String from_mail_address = send_mail_address;
			Authenticator auth = new PopupAuthenticator1(send_mail_address, password);
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", send_mail_address);
			mailProps.put("password", password);
			mailProps.put("mail.smtp.host", smtp_server);
			// Session mailSession = Session.getDefaultInstance(mailProps, auth);
			Session mailSession = Session.getInstance(mailProps, auth);
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
			message.setSubject(subjectText);
			MimeMultipart multi = new MimeMultipart("related");
			BodyPart textBodyPart = new MimeBodyPart();
			message.setDataHandler(new javax.activation.DataHandler(new StringDataSource(setText, "text/html")));
			textBodyPart.setText(setText);
			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			message.saveChanges();
			Transport.send(message);
			flag = true;
		}
//		String subjectText = "The system e-mail,Please do not reply";
//		String content = "这是一封测试邮件。";
//		String to_email = "825628070@qq.com";
//		String from_email = "philip_fu@95epay.com";
//		String password = "ABC123abc";
//		String smtp_server = "smtp.95epay.com";
//		setSendMail(to_email,content,subjectText,from_email,password,smtp_server);
		catch (Exception ex)
		{
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体错误原因：");
			ex.printStackTrace(System.err);
		}
		return flag;
	}*/
	
	public static void main(String[] args)
	{
//		String str = "825628070@qq.com";
//		System.out.println(str.split("@")[0].substring(0, 4)+"****"+
//		str.split("@")[0].substring((str.split("@")[0].length()-4), (str.split("@")[0].length()))+"@"
//		+str.split("@")[1]);
		//System.out.println(str.split("@")[1]);
		String subjectText = "The system e-mail,Please do not reply";
		String content = "这是一封测试邮件。";
		String to_email = "23426820@qq.com";
		String from_email = "web@wangdaibus.com";
		String password = "ABC123a";
		String smtp_server = "smtp.exmail.qq.com";
		setSendMail(to_email,content,subjectText,from_email,password,smtp_server);
		
  	}
}

class PopupAuthenticator1 extends Authenticator {
	private String username;
	private String password;

	public PopupAuthenticator1(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}
}
